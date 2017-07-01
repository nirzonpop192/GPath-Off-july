package com.siddiquinoor.restclient.manager;

/**
 * This class is responsible for Sync Data
 *
 * @author Noor vai ,Faisal mohammad , Refat,
 * @desc TechnoDhaka.
 * @version 1.3.0
 * @since 1.0
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.controller.AppConfig;
import com.siddiquinoor.restclient.controller.AppController;
import com.siddiquinoor.restclient.controller.SessionManager;

import com.siddiquinoor.restclient.network.ConnectionDetector;
import com.siddiquinoor.restclient.parse.Parser;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.views.notifications.AlertDialogManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class SyncDatabase {


    private String TAG = SyncDatabase.class.getSimpleName();

    private Context m_context;
    private Activity my_activity;


    // added by Siddiqui Noor on 23 Feb 2014 at 1:06pm
    public static ProgressDialog pDialogUpload;
    public static ProgressDialog pDialogDownload;
   // private int progressBarStatus = 0;
  //  private Handler progressBarHandler = new Handler();


    private ArrayList<dataUploadDB> queryData = new ArrayList<dataUploadDB>();
    private ArrayList<dataUploadDB> all_uploadQueryData;

    private int data_size;
    private int counter = 1;


    AlertDialogManager alert;
    SessionManager session;
    private AppConfig ac;
    private String username = "";
    private String password = "";
    private SQLiteHandler sqlH;

    private boolean isDownloadNeeded;
    private ConnectionDetector cd;


    public void setDownloadNeeded(boolean downloadNeeded) {
        isDownloadNeeded = downloadNeeded;
    }

    public SyncDatabase(Context context, Activity s_activity) {
        m_context = context;
        my_activity = s_activity;
        alert = new AlertDialogManager();
        pDialogUpload = new ProgressDialog(my_activity);
        session = new SessionManager(m_context);
        username = session.getUserID();
        password = session.getUserPassword();
        sqlH = new SQLiteHandler(m_context);
        setDownloadNeeded(false);
    }

    // here collect the data
    public void startTask() {
        /** here check the is available or not */
        queryData = sqlH.getUploadSyntaxData();
        Collections.sort(queryData);
        Log.d(TAG, " Upload table :Data found: " + queryData);

        cd = new ConnectionDetector(my_activity);
        boolean connection = cd.isConnectingToInternet();
        if (connection) {

            if (!queryData.isEmpty()) {
                CompleteTaskForUploadQuery completeTaskForUploadQuery = new CompleteTaskForUploadQuery();
                completeTaskForUploadQuery.execute();


            } else {

                /** set json array for selected village */


                pDialogUpload.setMessage("Downloading data ");


                if (connection) {
                    pDialogUpload.setCancelable(false);

                } else {
                    pDialogUpload.dismiss();

                }
                pDialogUpload.show();

                SharedPreferences settings;
// TODO: 11/28/2016   orther
                settings = my_activity.getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE); //1
                int operationMode = settings.getInt(UtilClass.OPERATION_MODE, 0);
                /** for sefty rea JSON*/
                JSONArray array = UtilClass.layR4CodeJSONConverter("SyncDatabase", sqlH.getSelectedVillageList(), sqlH);

                switch (operationMode) {
                    case UtilClass.REGISTRATION_OPERATION_MODE:
                        array = UtilClass.layR4CodeJSONConverter("SyncDatabase", sqlH.getSelectedVillageList(), sqlH);
                        break;
                    case UtilClass.DISTRIBUTION_OPERATION_MODE:

                        array = UtilClass.fdpCodeJSONConverter("SyncDatabase", sqlH.getSelectedFDPList(), sqlH);
                        break;

                    case UtilClass.SERVICE_OPERATION_MODE:

                        array = UtilClass.srvCenterCodeJSONConverter("SyncDatabase", sqlH.getSelectedServiceCenterList(), sqlH);
                        break;

                    default:
                        break;
                }

                // don't delete below code '
//                checkLoginAndDowenReftData(username, password, array, String.valueOf(operationMode));


            }

        } else {
            showAlert("Check your internet connectivity ");
        }

    }


    protected void showAlert(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(my_activity);
        builder.setTitle("Error:")
                .setMessage(msg)
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
    }

    private class CompleteTaskForUploadQuery extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            //  super.onPreExecute();
            prepareUploadQueryRequest(queryData);
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "Sql Que Finish");
        }

        @Override
        protected String doInBackground(Void... params) {
            return null;
        }
    }

    private void prepareUploadQueryRequest(ArrayList<dataUploadDB> temData) {
        final AppController global_Variable = (AppController) m_context;
        for (int i = 0; i < temData.size(); i++) {
            dataUploadDB data = new dataUploadDB();
            data._id = temData.get(i)._id;
            data._syntax = temData.get(i)._syntax;
            temData.set(i, data);
        }
        all_uploadQueryData = temData;
        data_size = temData.size();

        global_Variable.setTotalNumber(data_size);
        global_Variable.setDefaultNumber();

        LongTaskQuery longTaskQuery = new LongTaskQuery();
        longTaskQuery.execute();
    }

    private class LongTaskQuery extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "Starting Data upload : Data Size " + data_size);
            final AppController global_Variable = (AppController) m_context;
            int max = global_Variable.getTotalNumber();
            startProgressBar("Synchronizing database. Please be patient.", max);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                for (int i = 0; i < data_size; i++) {
                    Log.d(TAG, "Adding query into Que" + i + " of " + data_size);
                    publishProgress(i);
                    sqlSyncTaskDoInBack(all_uploadQueryData.get(i));
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            } catch (Exception e) {
                pDialogUpload.dismiss();
                return "UNKNOWN";
            }
            return null;
        }
    }

    private void sqlSyncTaskDoInBack(final dataUploadDB data) {
        // Tag used to cancel the request
        String tag_string_req = "Inserting Online SQL query";

        StringRequest strReq = new StringRequest(Method.POST, AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Synchronize response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                        String last_id = jObj.getString("Last_ID");

                        Log.d(TAG, "query Data added for: " + last_id);

                        //if(!ac.DEV_ENVIRONMENT) {
                        if (last_id.length() > 0) {
                            sqlH.uploadStatusFlagOfUploadTable(data._id);
                            counter++;
                        }
                        //}
                        //pDialogUpload.setProgress();

                        final AppController ac = (AppController) m_context;
                        ac.updateRecord();

                        pDialogUpload.setProgress(ac.getCurrentRecord());
                        // when all the unsync data upload  than it wiil dowenload
                        if (counter > data_size) {
                            AppController.getInstance().getRequestQueue().getCache().clear();
                            Log.d(TAG, "Finally  Registration Task Completed");
                            data_size = 33; // no of table
                            counter = 0;


                            pDialogUpload.setMessage("Downloading data ...");
                            pDialogUpload.setProgressStyle(ProgressDialog.STYLE_SPINNER);

                            /** set json array for selected village */


                            SharedPreferences settings;

                            settings = my_activity.getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE); //1
                            int operationMode = settings.getInt(UtilClass.OPERATION_MODE, 0);
                            /** for sefty rea SON*/
                            JSONArray array = UtilClass.layR4CodeJSONConverter("SyncDatabase", sqlH.getSelectedVillageList(), sqlH);

                            switch (operationMode) {
                                case UtilClass.REGISTRATION_OPERATION_MODE:
                                    array = UtilClass.layR4CodeJSONConverter("SyncDatabase", sqlH.getSelectedVillageList(), sqlH);
                                    break;
                                case UtilClass.DISTRIBUTION_OPERATION_MODE:

                                    array = UtilClass.fdpCodeJSONConverter("SyncDatabase", sqlH.getSelectedFDPList(), sqlH);
                                    break;

                                case UtilClass.SERVICE_OPERATION_MODE:

                                    array = UtilClass.srvCenterCodeJSONConverter("SyncDatabase", sqlH.getSelectedServiceCenterList(), sqlH);
                                    break;

// // TODO: 11/28/2016  for Other operation
                                default:
                                    break;
                            }


                            // don't delete below code '
//                            checkLoginAndDowenReftData(username, password, array, String.valueOf(operationMode));


                        }

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        pDialogUpload.dismiss();
                        alert.showAlertDialog(my_activity, "Synchronize Status", errorMsg, true);
                    }
                } catch (JSONException e) {
                    // JSON error
                    Log.e(TAG, "Error: " + e.getMessage());
                    e.printStackTrace();
                    pDialogUpload.dismiss();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                pDialogUpload.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "exequte_sql_query");
                params.put("sql_string", data._syntax);


                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    private void startProgressBar(String msg, int max) {

        //pDialogUpload = new ProgressDialog(my_activity, ProgressDialog.STYLE_SPINNER);
        pDialogUpload.setMessage(msg);
        pDialogUpload.setCancelable(false);
        pDialogUpload.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialogUpload.setProgress(0);
        pDialogUpload.setMax(max);
        pDialogUpload.show();
    }


    // Login And Synchronize

    /**
     * function to verify login details in online sql server  db
     */
    public void checkLoginAndDowenReftData(final String user_name, final String password, final JSONArray selectedVilJArry, final String operationMode) {
        // Tag used to cancel the request

        String tag_string_req = "req_login";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//                Log.d(TAG, "Login Response: " + response.toString());

                AppController.getInstance().getRequestQueue().getCache().clear();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                        int size = 0;

                        //setLogin(true); // user successfully logged in Create login session

                        /**       delete ONLY REFERENCE  */
                        sqlH.deleteReferenceTable();


                        // Adding data into Country Table
                        if (!jObj.isNull(Parser.COUNTRIES_JSON_A)) {
                            JSONArray countries = jObj.getJSONArray(Parser.COUNTRIES_JSON_A);
                            size = countries.length();
                            //   lunchBarDialog("countries",size);
                            for (int i = 0; i < size; i++) {
                                JSONObject country = countries.getJSONObject(i);

                                String AdmCountryCode = country.getString(Parser.ADM_COUNTRY_CODE);
                                String AdmCountryName = country.getString(Parser.ADM_COUNTRY_NAME);

                                sqlH.addCountry(AdmCountryCode, AdmCountryName);


                            }
                        }

                        pDialogUpload.setProgress(1);

                        // Adding data into Valid Registration Date Table
                        if (!jObj.isNull(Parser.VALID_DATES_JSON_A)) {
                            JSONArray valid_dates = jObj.getJSONArray(Parser.VALID_DATES_JSON_A);
                            size = valid_dates.length();
                            // lunchBarDialog("valid_dates",size);
                            for (int i = 0; i < size; i++) {
                                JSONObject valid_date = valid_dates.getJSONObject(i);
                                String AdmCountryCode = valid_date.getString(Parser.ADM_COUNTRY_CODE);
                                String StartDate = valid_date.getString(Parser.START_DATE);
                                String EndDate = valid_date.getString(Parser.END_DATE);

                                sqlH.addValidDateRange(AdmCountryCode, StartDate, EndDate);

                            }
                        }


//                        if (!jObj.isNull(Parser.GPS_GROUP_JSON_A)) {
//                            JSONArray gps_groups = jObj.getJSONArray(Parser.GPS_GROUP_JSON_A);
//                            size = gps_groups.length();
//
//                            for (int i = 0; i < size; i++) {
//                                JSONObject gps_group = gps_groups.getJSONObject(i);
//                                String GrpCode = gps_group.getString(Parser.GRP_CODE);
//                                String GrpName = gps_group.getString(Parser.GRP_NAME);
//                                String Description = gps_group.getString(Parser.DESCRIPTION);
//
//                                sqlH.addGpsGroup(GrpCode, GrpName, Description);
//
//                            }
//                        }
//
//                        if (!jObj.isNull(Parser.GPS_SUBGROUP_JSON_A)) {
//                            JSONArray gps_subgroups = jObj.getJSONArray(Parser.GPS_SUBGROUP_JSON_A);
//                            size = gps_subgroups.length();
//                            for (int i = 0; i < size; i++) {
//                                JSONObject gps_subgroup = gps_subgroups.getJSONObject(i);
//                                String GrpCode = gps_subgroup.getString(Parser.GRP_CODE);
//                                String SubGrpCode = gps_subgroup.getString(Parser.SUB_GRP_CODE);
//                                String SubGrpName = gps_subgroup.getString(Parser.SUB_GRP_NAME);
//                                String Description = gps_subgroup.getString(Parser.DESCRIPTION);
//                                sqlH.addGpsSubGroup(GrpCode, SubGrpCode, SubGrpName, Description);
//
//
//                            }
//                        }
                        // * Adding data into GPS Location Table

//                        if (!jObj.isNull(Parser.GPS_LOCATION_JSON_A)) {
//                            JSONArray gps_locations = jObj.getJSONArray(Parser.GPS_LOCATION_JSON_A);
//                            size = gps_locations.length();
//                            for (int i = 0; i < size; i++) {
//                                JSONObject gps_location = gps_locations.getJSONObject(i);
//                                String AdmCountryCode = gps_location.getString(Parser.ADM_COUNTRY_CODE);
//                                String GrpCode = gps_location.getString(Parser.GRP_CODE);
//                                String SubGrpCode = gps_location.getString(Parser.SUB_GRP_CODE);
//                                String LocationCode = gps_location.getString("LocationCode");
//                                String LocationName = gps_location.getString("LocationName");
//                                String Long = gps_location.getString("Long");
//                                String Latd = gps_location.getString("Latd");
//
//
//                                sqlH.addGpsLocation(AdmCountryCode, GrpCode, SubGrpCode, LocationCode, LocationName, Long, Latd);
//
//                            }
//                        }



                        if (!jObj.isNull(Parser.ADM_DONOR_JSON_A)) {

                            JSONArray adm_donors = jObj.getJSONArray(Parser.ADM_DONOR_JSON_A);
                            size = adm_donors.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject adm_donor = adm_donors.getJSONObject(i);

                                String AdmDonorCode = adm_donor.getString(Parser.ADM_DONOR_CODE);
                                String AdmDonorName = adm_donor.getString("AdmDonorName");
                                sqlH.addDonorName(AdmDonorCode, AdmDonorName);


                            }
                        }
                        if (!jObj.isNull(Parser.ADM_PROGRAM_MASTER_JSON_A)) {
                            JSONArray adm_program_masters = jObj.getJSONArray(Parser.ADM_PROGRAM_MASTER_JSON_A);
                            size = adm_program_masters.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject adm_program_master = adm_program_masters.getJSONObject(i);

                                String AdmProgCode = adm_program_master.getString(Parser.ADM_PROG_CODE);
                                String AdmAwardCode = adm_program_master.getString(Parser.ADM_AWARD_CODE);
                                String AdmDonorCode = adm_program_master.getString(Parser.ADM_DONOR_CODE);
                                String ProgName = adm_program_master.getString(Parser.PROG_NAME);
                                String ProgShortName = adm_program_master.getString(Parser.PROG_SHORT_NAME);
                                String MultipleSrv = adm_program_master.getString(Parser.MULTIPLE_SRV);
                                sqlH.addAdmProgramMaster(AdmProgCode, AdmAwardCode, AdmDonorCode, ProgName, ProgShortName, MultipleSrv);


                            }
                        }
                        if (!jObj.isNull(Parser.ADM_SERVICE_MASTER_JSON_A)) {
                            JSONArray adm_service_masters = jObj.getJSONArray(Parser.ADM_SERVICE_MASTER_JSON_A);
                            size = adm_service_masters.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject adm_service_master = adm_service_masters.getJSONObject(i);

                                String AdmProgCode = adm_service_master.getString(Parser.ADM_PROG_CODE);
                                String AdmSrvCode = adm_service_master.getString(Parser.ADM_SRV_CODE);
                                String AdmSrvName = adm_service_master.getString("AdmSrvName");
                                String AdmSrvShortName = adm_service_master.getString("AdmSrvShortName");

                                sqlH.addServiceMaster(AdmProgCode, AdmSrvCode, AdmSrvName, AdmSrvShortName);


                            }
                        }


                        if (!jObj.isNull(Parser.ADM_OP_MONTH_JSON_A)) {
                            JSONArray adm_op_months = jObj.getJSONArray(Parser.ADM_OP_MONTH_JSON_A);
                            size = adm_op_months.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject adm_op_month = adm_op_months.getJSONObject(i);

                                String AdmCountryCode = adm_op_month.getString(Parser.ADM_COUNTRY_CODE);
                                String AdmDonorCode = adm_op_month.getString(Parser.ADM_DONOR_CODE);
                                String AdmAwardCode = adm_op_month.getString(Parser.ADM_AWARD_CODE);
                                String OpCode = adm_op_month.getString("OpCode");
                                String OpMonthCode = adm_op_month.getString(Parser.OP_MONTH_CODE);
                                String MonthLabel = adm_op_month.getString("MonthLabel");
                                String StartDate = adm_op_month.getString(Parser.START_DATE);
                                String EndDate = adm_op_month.getString(Parser.END_DATE);
                                String UsaStartDate = adm_op_month.getString(Parser.USA_START_DATE);
                                String UsaEndDate = adm_op_month.getString(Parser.USA_END_DATE);
                                String Status = adm_op_month.getString("Status");
                                sqlH.addOpMonthFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, OpCode, OpMonthCode, MonthLabel, StartDate, EndDate, UsaStartDate, UsaEndDate, Status);
                            }
                        }





                        // * Adding data into  dob_service_center  Table


//                        if (!jObj.isNull(Parser.DOB_SERVICE_CENTER_JSON_A)) {// this is not servie
//                            JSONArray dob_service_centers = jObj.getJSONArray(Parser.DOB_SERVICE_CENTER_JSON_A);
//                            size = dob_service_centers.length();
//                            for (int i = 0; i < size; i++) {
//                                JSONObject dob_service_center = dob_service_centers.getJSONObject(i);
//
//                                String AdmCountryCode = dob_service_center.getString(Parser.ADM_COUNTRY_CODE);
//                                String SrvCenterCode = dob_service_center.getString(Parser.SRV_CENTER_CODE);
//                                String SrvCenterName = dob_service_center.getString(Parser.SRV_CENTER_NAME);
//
//                                // String SrvCenterAddress = dob_service_center.getString("SrvCenterAddress");
//                                //   String SrvCenterCatCode = dob_service_center.getString("SrvCenterCatCode");
//
//                                String FDPCode = dob_service_center.getString(Parser.FDP_CODE);
//
//                                // db.addServiceCenter(AdmCountryCode, SrvCenterCode, SrvCenterName, SrvCenterAddress, SrvCenterCatCode, FDPCode);
//                                sqlH.addServiceCenter(AdmCountryCode, SrvCenterCode, SrvCenterName, FDPCode);
//
//                            }
//                        }



                        // bellow code are not needed u can  delete it but after remove the php code first

//                        if (!jObj.isNull(Parser.STAFF_ACCESS_INFO_JSON_A)) {// this is not servie
//                            JSONArray staff_access_info_accesses = jObj.getJSONArray(Parser.STAFF_ACCESS_INFO_JSON_A);
//                            size = staff_access_info_accesses.length();
//                            for (int i = 0; i < size; i++) {
//                                JSONObject staff_access_info_access = staff_access_info_accesses.getJSONObject(i);
//
//                                String StfCode = staff_access_info_access.getString(Parser.STF_CODE);
//                                String AdmCountryCode = staff_access_info_access.getString(Parser.ADM_COUNTRY_CODE);
//                                String AdmDonorCode = staff_access_info_access.getString(Parser.ADM_DONOR_CODE);
//                                String AdmAwardCode = staff_access_info_access.getString(Parser.ADM_AWARD_CODE);
//                                String LayRListCode = staff_access_info_access.getString(Parser.LAY_R_LIST_CODE);
//                                String btnNew = staff_access_info_access.getString(Parser.BTN_NEW1);
//                                String btnSave = staff_access_info_access.getString(Parser.BTN_SAVE);
//                                String btnDel = staff_access_info_access.getString(Parser.BTN_DEL);
//                                String btnPepr = staff_access_info_access.getString(Parser.BTN_PEPR);
//                                String btnAprv = staff_access_info_access.getString(Parser.BTN_APRV);
//                                String btnRevw = staff_access_info_access.getString(Parser.BTN_REVW);
//                                String btnVrfy = staff_access_info_access.getString(Parser.BTN_VRFY);
//                                String btnDTran = staff_access_info_access.getString(Parser.BTN_D_TRAN);
//
//
//                                //String FDPCode = dbo_staff_geo_info_access.getString("FDPCode");
//                                String disCode = LayRListCode.substring(0, 2);
//                                String upCode = LayRListCode.substring(2, 4);
//                                String unCode = LayRListCode.substring(4, 6);
//                                String vCode = LayRListCode.substring(6);
//                                sqlH.addStaffGeoAccessInfo(StfCode, AdmCountryCode, AdmDonorCode, AdmAwardCode, LayRListCode, disCode, upCode, unCode, vCode, btnNew, btnSave, btnDel, btnPepr, btnAprv, btnRevw, btnVrfy, btnDTran);//, SrvCenterCatCode, FDPCode);
//
//                            }
//                        }


                        if (!jObj.isNull(Parser.LB_REG_HH_CATEGORY_JSON_A)) {
                            JSONArray lb_reg_hh_categorys = jObj.getJSONArray(Parser.LB_REG_HH_CATEGORY_JSON_A);
                            size = lb_reg_hh_categorys.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject lb_reg_hh_category = lb_reg_hh_categorys.getJSONObject(i);


                                String AdmCountryCode = lb_reg_hh_category.getString(Parser.ADM_COUNTRY_CODE);
                                String HHHeadCatCode = lb_reg_hh_category.getString(Parser.HH_HEAD_CAT_CODE);
                                String CatName = lb_reg_hh_category.getString(Parser.CAT_NAME);

                                sqlH.addHHCategory(AdmCountryCode, HHHeadCatCode, CatName);


                            }
                        }


                        if (!jObj.isNull(Parser.REG_LUP_GRADUATION_JSON_A)) {
                            JSONArray reg_lup_graduations = jObj.getJSONArray(Parser.REG_LUP_GRADUATION_JSON_A);
                            size = reg_lup_graduations.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject reg_lup_graduation = reg_lup_graduations.getJSONObject(i);

                                String AdmProgCode = reg_lup_graduation.getString(Parser.ADM_PROG_CODE);
                                String AdmSrvCode = reg_lup_graduation.getString(Parser.ADM_SRV_CODE);
                                String GRDCode = reg_lup_graduation.getString(Parser.GRD_CODE);
                                String GRDTitle = reg_lup_graduation.getString(Parser.GRD_TITLE);
                                String DefaultCatActive = reg_lup_graduation.getString(Parser.DEFAULT_CAT_ACTIVE);
                                String DefaultCatExit = reg_lup_graduation.getString(Parser.DEFAULT_CAT_EXIT);


                                sqlH.addGraduation(AdmProgCode, AdmSrvCode, GRDCode, GRDTitle, DefaultCatActive, DefaultCatExit);


                            }
                        }

                        // Adding data into Layer Label Table
                        if (!jObj.isNull(Parser.LAYER_LABELS_JSON_A)) {
                            JSONArray layer_labels = jObj.getJSONArray(Parser.LAYER_LABELS_JSON_A);
                            size = layer_labels.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject layer_label = layer_labels.getJSONObject(i);

                                String AdmCountryCode = layer_label.getString(Parser.ADM_COUNTRY_CODE);
                                String GeoLayRCode = layer_label.getString(Parser.GEO_LAY_R_CODE);
                                String GeoLayRName = layer_label.getString(Parser.GEO_LAY_R_NAME);
                                sqlH.addLayerLabel(AdmCountryCode, GeoLayRCode, GeoLayRName);


                            }
                        }




                        // Adding data into Relation Table
                        if (!jObj.isNull(Parser.RELATION_JSON_A)) {

                            JSONArray relation = jObj.getJSONArray(Parser.RELATION_JSON_A);

                            size = relation.length();

                            for (int i = 0; i < size; i++) {

                                JSONObject rel = relation.getJSONObject(i);


                                String Relation_Code = rel.getString(Parser.HH_RELATION_CODE);
                                String RelationName = rel.getString(Parser.RELATION_NAME);

                                sqlH.addRelation(Relation_Code, RelationName);


                            }
                        }


                        if (!jObj.isNull(Parser.REPORT_TEMPLATE)) {
                            JSONArray report_templates = jObj.getJSONArray(Parser.REPORT_TEMPLATE);
                            size = report_templates.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject report_template = report_templates.getJSONObject(i);

                                String AdmCountryCode = report_template.getString(Parser.ADM_COUNTRY_CODE);
                                String RptLabel = report_template.getString(Parser.RPT_LABEL);
                                String Code = report_template.getString(Parser.RPT_G_N_CODE);

                                sqlH.addCardType(AdmCountryCode, RptLabel, Code);


                            }
                        }


                        if (!jObj.isNull(Parser.CARD_PRINT_REASON)) {
                            JSONArray card_print_reasons = jObj.getJSONArray(Parser.CARD_PRINT_REASON);
                            size = card_print_reasons.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject card_print_reason = card_print_reasons.getJSONObject(i);

                                String ReasonCode = card_print_reason.getString(Parser.REASON_CODE);
                                String ReasonTitle = card_print_reason.getString(Parser.REASON_TITLE);

                                sqlH.addCardPrintReason(ReasonCode, ReasonTitle);


                            }
                        }


                        if (!jObj.isNull(Parser.STAFF_FDP_ACCESS_JSON_A)) {
                            JSONArray staff_fdp_accesses = jObj.getJSONArray(Parser.STAFF_FDP_ACCESS_JSON_A);
                            size = staff_fdp_accesses.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject staff_fdp_access = staff_fdp_accesses.getJSONObject(i);

                                String StfCode = staff_fdp_access.getString(Parser.STF_CODE);
                                String AdmCountryCode = staff_fdp_access.getString(Parser.ADM_COUNTRY_CODE);
                                String FDPCode = staff_fdp_access.getString(Parser.FDP_CODE);
                                String btnNew = staff_fdp_access.getString(Parser.BTN_NEW);
                                String btnSave = staff_fdp_access.getString(Parser.BTN_SAVE);
                                String btnDel = staff_fdp_access.getString(Parser.BTN_DEL);


                                sqlH.addStaffFDPAccess(StfCode, AdmCountryCode, FDPCode, btnNew, btnSave, btnDel);

                           /*     Log.d(TAG, "In addStaff FDP Access : StfCode : " + AdmCountryCode + " StfCode : " + StfCode + " LayR1ListCode : " + FDPCode + " btnNew : "
                                        + btnNew + " btnDel : " + btnDel);*/
                            }
                        }


                        if (!jObj.isNull(Parser.FDP_MASTER_JSON_A)) {
                            JSONArray fdp_masters = jObj.getJSONArray(Parser.FDP_MASTER_JSON_A);
                            size = fdp_masters.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject fdp_master = fdp_masters.getJSONObject(i);

                                String AdmCountryCode = fdp_master.getString(Parser.ADM_COUNTRY_CODE);
                                String FDPCode = fdp_master.getString(Parser.FDP_CODE);
                                String FDPName = fdp_master.getString(Parser.FDP_NAME);
                                String FDPCatCode = fdp_master.getString(Parser.FDP_CAT_CODE);
                                String WHCode = fdp_master.getString(Parser.WH_CODE);
                                String LayR1Code = fdp_master.getString(Parser.LAY_R_1_CODE);
                                String LayR2Code = fdp_master.getString(Parser.LAY_R_2_CODE);


                                sqlH.addFDPMaster(AdmCountryCode, FDPCode, FDPName, FDPCatCode, WHCode, LayR1Code, LayR2Code);

                                //  Log.d(TAG, "In Reg Mem Card Request Table: AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : "
                                //        + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode+ " HHID : " + HHID);
                            }
                        }

                        /**
                         * SrvTable for Service Data
                         */

                        if (!jObj.isNull(Parser.SERVICE_TABLE_JSON_A)) {
                            JSONArray services_table = jObj.getJSONArray(Parser.SERVICE_TABLE_JSON_A);
                            Parser.SrvTableParser(services_table, sqlH);

                        }


                        /**
                         * The total string Convert into JSON object
                         * */


                        /**
                         * Below Cod eis Ok By For performance issue we use parsing class
                         */

                        // publishProgress(++progressIncremental);

                        if (!jObj.isNull("service_exe_table")) {// this is not servie
                            JSONArray services_exe_table = jObj.getJSONArray("service_exe_table");
                            size = services_exe_table.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject service = services_exe_table.getJSONObject(i);

                                String AdmCountryCode = service.getString(Parser.ADM_COUNTRY_CODE);
                                String AdmDonorCode = service.getString(Parser.ADM_DONOR_CODE);
                                String AdmAwardCode = service.getString(Parser.ADM_AWARD_CODE);
                                String LayR1ListCode = service.getString(Parser.LAY_R_1_LIST_CODE);
                                String LayR2ListCode = service.getString(Parser.LAY_R_2_LIST_CODE);
                                String LayR3ListCode = service.getString(Parser.LAY_R_3_LIST_CODE);
                                String LayR4ListCode = service.getString(Parser.LAY_R_4_LIST_CODE);
                                String HHID = service.getString(Parser.HHID);
                                String MemID = service.getString(Parser.MEM_ID);
                                String ProgCode = service.getString(Parser.PROG_CODE);
                                String SrvCode = service.getString(Parser.SRV_CODE);
                                String OpCode = service.getString(Parser.OP_CODE);
                                String OpMonthCode = service.getString(Parser.OP_MONTH_CODE);
                                String VOItmSpec = service.getString(Parser.VO_ITM_SPEC);
                                String VOItmUnit = service.getString(Parser.VO_ITM_UNIT);
                                String VORefNumber = service.getString(Parser.VO_REF_NUMBER);
                                String VOItmCost = service.getString(Parser.VO_ITM_COST);

                                sqlH.addServiceExtendedFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, LayR1ListCode, LayR2ListCode, LayR3ListCode,
                                        LayR4ListCode, HHID, MemID, ProgCode, SrvCode, OpCode, OpMonthCode,
                                        VOItmSpec, VOItmUnit, VORefNumber, VOItmCost, "1");


                            }

                        }


                        if (!jObj.isNull("lup_srv_option_list")) {
                            JSONArray lup_srv_option_listDatas = jObj.getJSONArray("lup_srv_option_list");
                            size = lup_srv_option_listDatas.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject lup_srv_option_listData = lup_srv_option_listDatas.getJSONObject(i);
                                //AGR_DataModel data = new AGR_DataModel();
                                String countryCode = lup_srv_option_listData.getString(Parser.ADM_COUNTRY_CODE);

                                String programCode = lup_srv_option_listData.getString(Parser.PROG_CODE);
                                String serviceCode = lup_srv_option_listData.getString(Parser.SRV_CODE);
                                String LUPOptionCode = lup_srv_option_listData.getString(Parser.LUP_OPTION_CODE);
                                String LUPOptionName = lup_srv_option_listData.getString(Parser.LUP_OPTION_NAME);

                                sqlH.addInLupSrvOptionListFromOnline(countryCode, programCode, serviceCode, LUPOptionCode, LUPOptionName);


                            }
                        }


                        if (pDialogUpload.isShowing())
                            pDialogUpload.dismiss();

                        /***
                         * @deis: IN THIS STRING RESPONSE WRITE THE JSON DATA
                         *
                         */
                        AppController.getInstance().getRequestQueue().getCache().clear();
                    } else {
                        // Error in login. Invalid UserName or Password
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(m_context, errorMsg, Toast.LENGTH_LONG).show();
                        pDialogUpload.dismiss();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(m_context, "Unknown Error occurred " + e.getMessage(), Toast.LENGTH_LONG).show();
                    pDialogUpload.dismiss();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                Toast.makeText(m_context,
                        "Failed to retrieve data\r\nPlease try again checking your internet connectivity, Username and Password.", Toast.LENGTH_LONG).show();
                pDialogUpload.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "get_reference_data");
                params.put("user_name", user_name);
                params.put("password", password);
                params.put("lay_r_code_j", selectedVilJArry.toString());
                params.put("operation_mode", operationMode);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
    // end function





}

