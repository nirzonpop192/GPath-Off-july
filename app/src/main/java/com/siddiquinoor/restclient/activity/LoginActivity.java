package com.siddiquinoor.restclient.activity;

/**
 * Activity for login validation and collect existing data from online
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.3.0
 * @since 1.0
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.controller.AppConfig;
import com.siddiquinoor.restclient.controller.AppController;
import com.siddiquinoor.restclient.data_model.AdmCountryDataModel;
import com.siddiquinoor.restclient.data_model.FDPItem;
import com.siddiquinoor.restclient.data_model.ProgramMasterDM;
import com.siddiquinoor.restclient.data_model.ServiceCenterItem;
import com.siddiquinoor.restclient.data_model.TemOpMonth;
import com.siddiquinoor.restclient.data_model.VillageItem;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.network.ConnectionDetector;
import com.siddiquinoor.restclient.parse.Parser;
import com.siddiquinoor.restclient.utils.FileUtils;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.views.notifications.AlertDialogManager;
import com.siddiquinoor.restclient.views.notifications.CustomToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.siddiquinoor.restclient.utils.UtilClass.DISTRIBUTION_OPERATION_MODE_NAME;
import static com.siddiquinoor.restclient.utils.UtilClass.OTHER_OPERATION_MODE;
import static com.siddiquinoor.restclient.utils.UtilClass.OTHER_OPERATION_MODE_NAME;
import static com.siddiquinoor.restclient.utils.UtilClass.REGISTRATION_OPERATION_MODE_NAME;
import static com.siddiquinoor.restclient.utils.UtilClass.SERVICE_OPERATION_MODE_NAME;
import static com.siddiquinoor.restclient.utils.UtilClass.TRANING_N_ACTIVITY_OPERATION_MODE_NAME;

//import java.util.logging.Handler;


public class LoginActivity extends BaseActivity {
    // LogCat tag
    private static final String TAG = LoginActivity.class.getSimpleName();
    public static final String REG_HOUSE_HOLD_DATA = "reg_house_hold_data";
    public static final String REG_MEMBER_DATA = "reg_member_data";
    public static final String REG_MEMBER_PROG_GROUP_DATA = "reg_member_prog_grp_data";
    public static final String SERVICE_DATA = "service_data";
    public static final String ALL_DATA = "all_data";
    public static final String DYNAMIC_TABLE = "dynamic_table";
    public static final String ENU_TABLE = "enu_table";
    private static final int REG_MODE = 0;
    private static final int DIST_MODE = 1;
    private static final int SERV_MODE = 2;
    private static final int OTHER_MODE = 3;
    public static final String TRAINING_N_ACTIVITY = "trainingNActivity";
    public static final int TRAINING_MODE = 4;


    /**
     * function to verify login details & select 2 village
     */
    List<VillageItem> villageNameList = new ArrayList<VillageItem>();
    List<AdmCountryDataModel> countryNameList = new ArrayList<AdmCountryDataModel>();
    Dialog mdialog;
    ArrayList<VillageItem> aL_itemsSelected = new ArrayList<VillageItem>();
    ArrayList<AdmCountryDataModel> aCountryL_itemsSelected = new ArrayList<AdmCountryDataModel>();
    ArrayList<VillageItem> selectedVillageList = new ArrayList<VillageItem>();
    ArrayList<AdmCountryDataModel> selectedCountryList = new ArrayList<AdmCountryDataModel>();
    boolean[] itemChecked;
    boolean[] itemCheckedOpearationMode;

    String[] villageNameStringArray;
    String[] countryNameStringArray;
    private final String[] operationModeStringArray = {REGISTRATION_OPERATION_MODE_NAME, DISTRIBUTION_OPERATION_MODE_NAME, SERVICE_OPERATION_MODE_NAME, OTHER_OPERATION_MODE_NAME, TRANING_N_ACTIVITY_OPERATION_MODE_NAME};


    // Login Button
    private Button btnLogin;
    // User hhName Input box
    private EditText inputUsername;
    //password input box
    private EditText inputPassword;
    //progress mdialog wigedt
    private ProgressDialog barPDialog; //Bar Progress Dialog
    //progress handler
    private Handler barPDialogHandler;
    //sqlLite Database handler
    private SQLiteHandler db;
    // connection Detector
    ConnectionDetector cd;
    // flag for connectivity
    Boolean isInternetAvailable = false;
    //alert Dialog Manager
    AlertDialogManager alert;
    //Application configuration
    private AppConfig ac;
    //progress mdialog
    private ProgressDialog pDialog;
    // size  = 0, int type
//    private int size = 0;
    //mContext
    private final Context mContext = LoginActivity.this;
    //exit button
    private Button btnExit;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    private Button btnClean;
    String strCountryMode = "";

    String temSelectedProgram;

    String tem;

    /**
     * variable is only used for {@link #getOperationModeAlert(String, String)} method
     * Clicking on an item  store the value in temValue}
     * which is used in positive Button
     */
    private String temValue;

    private TextView tvDeviceId;


    private Button btnImportDb;

    /**
     * function to verify login details & select 2 FDP
     */

    List<ServiceCenterItem> serviceCenterNameList = new ArrayList<ServiceCenterItem>();
    ArrayList<ServiceCenterItem> selectedServiceCenterList = new ArrayList<ServiceCenterItem>();
    ArrayList<FDPItem> aLfdp_itemsSelected = new ArrayList<FDPItem>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        barPDialogHandler = new Handler();
        viewReference();
        /**
         * Initialize Button and Input Boxes
         */


        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        editor = settings.edit(); //2


        pDialog = new ProgressDialog(this);                                                         // Progress mdialog
        pDialog.setCancelable(false);


        db = new SQLiteHandler(getApplicationContext());                                             // SQLite database handler


        cd = new ConnectionDetector(getApplicationContext());                                       // connectivity manager


        setListener();
        createDeviceIDFile();


        /**
         * import db
         */
        btnImportDb = (Button) findViewById(R.id.btnImport);
        btnImportDb.setVisibility(View.GONE);

        String path = Environment.getExternalStorageDirectory().getPath() + "/" + SQLiteHandler.DATABASE_NAME;
        File newDb = new File(path);
        if (newDb.exists()) {
            btnImportDb.setVisibility(View.VISIBLE);
        }
        btnImportDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImportDbAsycnTask importDbAsycnTask = new ImportDbAsycnTask();
                importDbAsycnTask.execute();
            }
        });


        String macAddress = UtilClass.getDeviceId(mContext);                                      // get mac address

        tvDeviceId.setText(macAddress);

        String root = Environment.getExternalStorageDirectory().toString();

        String folderPath = root + "/GpathOffline/";
        File folder = new File(folderPath);

        if (!folder.exists())
            folder.mkdirs();

        File[] files = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.matches("PCI_.*\\.db");
            }
        });
        if (files != null) {
            for (File file : files) {
                if (file.exists()) {
                    File newDb_1 = new File(path);
                    btnImportDb.setVisibility(View.VISIBLE);
                    file.renameTo(newDb_1);
                }


            }
        }


    }


    private class ImportDbAsycnTask extends AsyncTask<Void, Integer, String> {

        private boolean dbImportStatusFlag;


        private ImportDbAsycnTask() {
            dbImportStatusFlag = false;
        }

        @Override
        protected String doInBackground(Void... params) {
            dbImportStatusFlag = importDataBase();
            return "successes";
        }

        /**
         * Initiate the dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            startProgressBar("Importing Database !");

        }


        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            hideProgressBar();
            String ddf = dbImportStatusFlag ? "Import completed " : " import failed";
            CustomToast.show(mContext, ddf);

//            logoutUser();

            if (dbImportStatusFlag) {
                FileUtils.deleteExportDatabase();

                btnImportDb.setVisibility(View.GONE);
            }

        }
    }

    private void hideProgressBar() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    /**
     * @param msg text massage
     */
    private void startProgressBar(String msg) {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage(msg);
        pDialog.setCancelable(true);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.show();
    }


    private boolean importDataBase() {
        boolean flag = false;

        try {
            String path = Environment.getExternalStorageDirectory().getPath() + "/" + SQLiteHandler.DATABASE_NAME;


            File newDb = new File(path);
            if (newDb.exists()) {
                /***
                 *  here isValidLocalLogin() only use for to create PCI.db file in in
                 *  package database folder . other wise the import db operation fails
                 */
                db.isValidLocalLogin("", "");

                flag = db.importDatabase(path, LoginActivity.this);
                File file = new File(path);                                                         // delete
                file.delete();

                db.reCreateSurveyTable();
            } else flag = false;


        } catch (IOException e) {
            e.printStackTrace();
        }
        String startDate = "";
        try {
            startDate = getDateTime(true);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /**
         *
         * if database is imported than save a flag for not to sync with online
         */
        if (flag) {
            editor.putBoolean(UtilClass.SYNC_MODE_KEY, false);
            editor.putString(UtilClass.IMPORT_DATE_TIME_KEY, startDate);
        } else
            editor.putBoolean(UtilClass.SYNC_MODE_KEY, true);
        editor.commit();
        return flag;
    }


    private void viewReference() {
        inputUsername = (EditText) findViewById(R.id.user_name);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnClean = (Button) findViewById(R.id.btnClean);
        tvDeviceId = (TextView) findViewById(R.id.tv_deviceId);

    }

    private void createDeviceIDFile() {
        String macAddress = "";
        macAddress = UtilClass.getDeviceId(LoginActivity.this);
        try {

            FileOutputStream fOut = openFileOutput("EMI.txt", MODE_WORLD_READABLE);
            fOut.write(macAddress.getBytes());
            fOut.close();


            File sd = Environment.getExternalStorageDirectory();                                    // get the internal root directories

            if (sd.canWrite()) {
                File currentDB = new File("/data/data/" + getPackageName() + "/files/EMI.txt");
                File backupDB = new File(sd, "EMI.txt");

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();

                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void setListener() {

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Exit Application?");
                builder.setMessage("Click yes to exit!");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                        finish();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog exitDailog = builder.create();
                exitDailog.show();


            }
        });
        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (db.selectUploadSyntextRowCount() > 0) {
                    //Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
                    showAlert("There are records not yet Synced. Clean attempt denied");
                } else {


                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Delete Database?");
                    builder.setMessage("Sure to delete database?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            try {
                                dialog.dismiss();
                                editor.putBoolean(UtilClass.SYNC_MODE_KEY, true);
                                editor.commit();
                                db.deleteUsersWithSelected_LayR4_FDP_Srv_Country();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog exitDailog = builder.create();
                    exitDailog.show();


                }
            }
        });

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String user_name = "";
                String password = "";
                // in developments mode
                if (ac.DEV_ENVIRONMENT) {
                    user_name = "nkalam";
                    password = "p3";
                } else {
                    user_name = inputUsername.getText().toString().trim();
                    password = inputPassword.getText().toString().trim();
                }

                // Check for empty data in the form
                if (user_name.trim().length() > 0 && password.trim().length() > 0) {

                    if (db.isValidLocalLogin(user_name, password)) {

                        gotoHomePage();

                    } else {
                        boolean syncMode = settings.getBoolean(UtilClass.SYNC_MODE_KEY, true);
                        if (syncMode) {
                            /**
                             * This block determine is Internet available
                             */
                            isInternetAvailable = cd.isConnectingToInternet();
                            if (isInternetAvailable) {
                                /***
                                 * This if  block determine is there any un-synchronized  data exits in local device
                                 */
                                if (db.selectUploadSyntextRowCount() > 0) {
                                    /**
                                     * This block check the user is country admin or not
                                     * if the the user is country admin or admin
                                     * than the app will be unlocked . but will remain for previous user
                                     */
                                    if (db.isValidAdminLocalLogin(user_name, password)) {
                                        gotoHomePage();
                                    } else {
                                        showAlert(getResources().getString(R.string.unsyn_msg));
                                    }

                                } else {
//                                    pDialog = new ProgressDialog(mContext);
//                                    pDialog.setCancelable(false);
//                                    pDialog.setMessage("Loading..");
//                                    pDialog.show();

                                    getOperationModeAlert(user_name, password);                     // for selecting operation Mood
                                }


                            } else
                                showAlert("Check your internet connectivity!!");
                        } else {

                            if (db.isValidAdminLocalLogin(user_name, password)) {
                                gotoHomePage();
                            } else {
                                HashMap<String, String> user = db.getUserDetails();
                                showAlert("This device should be operated offline by " + user.get("name"));
                            }


                        }

                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }

            }

        });
    }


    String strOperationMode = "";

    /**
     * @param user_name user name
     * @param password  password
     */

    private void getOperationModeAlert(final String user_name, final String password) {
        aCountryL_itemsSelected = (ArrayList<AdmCountryDataModel>) insertCountryNameListToSArray();
        itemCheckedOpearationMode = new boolean[operationModeStringArray.length];
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Operation Mode");

        builder.setSingleChoiceItems(operationModeStringArray, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                strOperationMode = "";
                strOperationMode = operationModeStringArray[which];
            }
        });
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (!strOperationMode.equals("")) {

                    for (int mode_index = 0; mode_index < itemCheckedOpearationMode.length; mode_index++) {
                        if (operationModeStringArray[mode_index].equals(strOperationMode)) {

                            // remove shred preference dependence from app
                            String entryTime = "";

                            try {
                                entryTime = getDateTime();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            db.insertIntoDeviceOperationMode((mode_index + 1), strOperationMode, user_name, entryTime);
                            pDialog = new ProgressDialog(mContext);
                            pDialog.setCancelable(false);
                            pDialog.setMessage("Downloading  data .");
                            pDialog.show();
                            switch (mode_index) {
                                case REG_MODE:
                                    mdialog.dismiss();
                                    checkVillageSelection(user_name, password, "1");
                                    break;
                                case DIST_MODE:
                                    mdialog.dismiss();

                                    checkFDPSelection(user_name, password);
                                    break;
                                case SERV_MODE:
                                    mdialog.dismiss();
                                    checkProgramSelection(user_name, password);
                                    break;

                                case OTHER_MODE:
                                    mdialog.dismiss();
                                    checkCountrySelection(user_name, password, "4");


                                    break;
                                case TRAINING_MODE:
                                    mdialog.dismiss();
                                    checkVillageSelection(user_name, password, "5");
                                    break;

                                default:
                                    hideDialog();
                                    mdialog.dismiss();
                                    Toast.makeText(LoginActivity.this, "Select  any one", Toast.LENGTH_SHORT).show();
                                    break;

                            }
                        } else {
                            hideDialog();
                            mdialog.dismiss();
                        }

                    }
                } else {
                    mdialog.dismiss();
                    hideDialog();
                }


            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                hideDialog();


                mdialog.dismiss();
            }
        });
        mdialog = builder.create();
        mdialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean syncMode = settings.getBoolean(UtilClass.SYNC_MODE_KEY, true);
        if (!syncMode)
            btnClean.setEnabled(false);
    }

    private void gotoHomePage() {
        setLogin(true);        // login success

        // Getting local User information
        HashMap<String, String> user = db.getUserDetails();
        setUserName(user.get("name"));                                                              // Setting Username into session
        setStaffID(user.get("code"));                                                               // Setting StaffCode into session
        setUserID(user.get("username"));
        setUserPassword(user.get("password"));

        setUserCountryCode(user.get("c_code"));                                                     // Setting Country Code into session

        /**
         *  Launch main activity
         *  */
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }


    public void checkServiceCenterSelection(final String user_name, final String password, final String cCode, final String donorCode, final String awardCode, final String progCode, final String opMothCode, final String distFlag) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";


        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                /***
                 * @deis: IN THIS STRING RESPONSE WRITE THE JSON DATA
                 *
                 */
                AppController.getInstance().getRequestQueue().getCache().clear();

                String CountryNo = "0";
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                        int size = 0;
                        // count no countries assigne
                        if (!jObj.isNull(Parser.COUNTRIE_NO)) {

                            JSONArray village = jObj.getJSONArray(Parser.COUNTRIE_NO);

                            size = village.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject vil = village.getJSONObject(i);

                                CountryNo = vil.getString("CountryNo");

                            }
                        }

                        if (!jObj.isNull(Parser.COUNTRIES_JSON_A)) {
                            countryNameList.clear();
                            countryNameList = Parser.AdmCountryParser(jObj.getJSONArray(Parser.COUNTRIES_JSON_A));
                        }


                        if (!jObj.isNull(Parser.DOB_SERVICE_CENTER_JSON_A)) {// this is not servie
                            JSONArray dob_service_centers = jObj.getJSONArray(Parser.DOB_SERVICE_CENTER_JSON_A);
                            size = dob_service_centers.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject dob_service_center = dob_service_centers.getJSONObject(i);

                                String AdmCountryCode = dob_service_center.getString(Parser.ADM_COUNTRY_CODE);
                                String SrvCenterCode = dob_service_center.getString(Parser.SRV_CENTER_CODE);
                                String SrvCenterName = dob_service_center.getString(Parser.SRV_CENTER_NAME);

                                ServiceCenterItem servCenterItem = new ServiceCenterItem();
                                servCenterItem.setAdmCountryCode(AdmCountryCode);
                                servCenterItem.setServiceCenterCode(SrvCenterCode);
                                servCenterItem.setServiceCenterName(SrvCenterName);
                                serviceCenterNameList.add(servCenterItem);


                            }
                        }


                        hideDialog();


                        getServiceCenterAlert(user_name, password, false);


                    } else {
                        // Error in login. Invalid UserName or Password
                        hideDialog();
                        String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());

                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_service_center_name");
                params.put("user_name", user_name);
                params.put("password", password);
                params.put("country_code", cCode);
                params.put("donor_code", donorCode);
                params.put("award_code", awardCode);
                params.put("program_code", progCode);
                params.put("opMothCode", opMothCode);
                params.put("distFlag", distFlag);


                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    public void checkProgramSelection(final String user_name, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login_";
//        AdmProgramNameList.clear();

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                AppController.getInstance().getRequestQueue().getCache().clear();                   // clear


                hideDialog();               // hide the Dialog bar

                String CountryNo = "0";
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                        int size = 0;
/**
 * Clean the Temporary Data Base
 */
                        db.cleanTemTableForService();
                        // count no countries assigne
                        if (!jObj.isNull(Parser.COUNTRIE_NO)) {

                            CountryNo = Parser.NumberOfCounteryAssignedUserParser(jObj.getJSONArray(Parser.COUNTRIE_NO));
                        }


                        if (!jObj.isNull(Parser.COUNTRIES_JSON_A)) {
                            countryNameList.clear();
                            countryNameList = Parser.AdmCountryParser(jObj.getJSONArray(Parser.COUNTRIES_JSON_A));
                        }


                        if (!jObj.isNull(Parser.ADM_OP_MONTH_JSON_A)) {
                            JSONArray adm_op_months = jObj.getJSONArray(Parser.ADM_OP_MONTH_JSON_A);
                            size = adm_op_months.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject adm_op_month = adm_op_months.getJSONObject(i);

                                String AdmCountryCode = adm_op_month.getString(Parser.ADM_COUNTRY_CODE);
                                String AdmDonorCode = adm_op_month.getString(Parser.ADM_DONOR_CODE);
                                String AdmAwardCode = adm_op_month.getString(Parser.ADM_AWARD_CODE);
                                String OpCode = adm_op_month.getString(Parser.OP_CODE);
                                String OpMonthCode = adm_op_month.getString(Parser.OP_MONTH_CODE);
                                String MonthLabel = adm_op_month.getString(Parser.MONTH_LABEL);
                                String UsaStartDate = adm_op_month.getString(Parser.USA_START_DATE);
                                String UsaEndDate = adm_op_month.getString(Parser.USA_END_DATE);
                                String Status = adm_op_month.getString("Status");
                                db.addTemporaryOpMonth(AdmCountryCode, AdmDonorCode, AdmAwardCode, OpCode, OpMonthCode, MonthLabel, UsaStartDate, UsaEndDate, Status);


                            }
                        }


                        if (!jObj.isNull(Parser.ADM_PROGRAM_MASTER_JSON_A)) {
                            JSONArray adm_program_masters = jObj.getJSONArray(Parser.ADM_PROGRAM_MASTER_JSON_A);
                            size = adm_program_masters.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject adm_program_master = adm_program_masters.getJSONObject(i);

                                String AdmCountryCode = adm_program_master.getString("AdmCountryCode");
                                String AdmProgCode = adm_program_master.getString(Parser.ADM_PROG_CODE);
                                String AdmAwardCode = adm_program_master.getString(Parser.ADM_AWARD_CODE);
                                String AdmDonorCode = adm_program_master.getString(Parser.ADM_DONOR_CODE);
                                String ProgName = adm_program_master.getString(Parser.PROG_NAME);
                                String ProgShortName = adm_program_master.getString(Parser.PROG_SHORT_NAME);

                                db.addTemporaryAdmCountryProgram(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, ProgName, ProgShortName);

                            }
                        }


                        hideDialog();


                        // if user hsa 1 country assigned
                        if (CountryNo.equals("1")) {
                            getProgramAlert(user_name, password, countryNameList.get(0).getAdmCountryCode());

                        } else {
                            selectedCountryList.clear();
                            getCountryAlert(user_name, password, UtilClass.SERVICE_OPERATION_MODE);
                        }


                    } else {
                        // Error in login. Invalid UserName or Password
                        hideDialog();
                        String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_programName");
                params.put("user_name", user_name);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    /**
     * function to verify login details & select 2 FDP
     */

    List<FDPItem> fdpNameList = new ArrayList<FDPItem>();
    ArrayList<FDPItem> selectedFdpList = new ArrayList<FDPItem>();
    String[] fdpNameStringArray;

    public void checkFDPSelection(final String user_name, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";


        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                /***
                 * @deis: IN THIS STRING RESPONSE WRITE THE JSON DATA
                 *
                 */
                AppController.getInstance().getRequestQueue().getCache().clear();

                /**
                 * hide the Dialog bar
                 */
                hideDialog();

                String CountryNo = "";
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                        int size = 0;


                        if (!jObj.isNull(Parser.COUNTRIE_NO)) {

                            CountryNo = Parser.NumberOfCounteryAssignedUserParser(jObj.getJSONArray(Parser.COUNTRIE_NO));
                        }

                        if (!jObj.isNull(Parser.COUNTRIES_JSON_A)) {
                            countryNameList.clear();
                            countryNameList = Parser.AdmCountryParser(jObj.getJSONArray(Parser.COUNTRIES_JSON_A));
                        }


                        if (!jObj.isNull(Parser.STAFF_FDP_ACCESS_JSON_A)) {

                            JSONArray village = jObj.getJSONArray(Parser.STAFF_FDP_ACCESS_JSON_A);
                            fdpNameList.clear();
                            size = village.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject fdp_list = village.getJSONObject(i);

                                //String GeoLayRName = fdp_list.getString("GeoLayRName");
                                String AdmCountryCode = fdp_list.getString("AdmCountryCode");
                                String FDPCode = fdp_list.getString("FDPCode");
                                String FDPName = fdp_list.getString("FDPName");


                                FDPItem fdpItem = new FDPItem();
                                fdpItem.setAdmCountryCode(AdmCountryCode);
                                fdpItem.setFDPCode(FDPCode);
                                fdpItem.setFDPName(FDPName);
                                fdpNameList.add(fdpItem);


                            }
                        }
                        hideDialog();
                        // if user hsa 1 country assigned
                        if (CountryNo.equals("1")) {
                            getFDPAlert(user_name, password, false);
                        } else {
                            selectedCountryList.clear();
                            getCountryAlert(user_name, password, UtilClass.DISTRIBUTION_OPERATION_MODE);
                        }


                    } else {
                        // Error in login. Invalid UserName or Password
                        hideDialog();
                        String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_fdp_name");
                params.put("user_name", user_name);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void checkCountrySelection(final String user_name, final String password, final String operationMode) {
        String tag_string_req = "req_country";

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                /***                 * Clear the Cache memory                 */

                AppController.getInstance().getRequestQueue().getCache().clear();


                hideDialog();   //                                          hide the Dialog bar

                String CountryNo = "0";
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                        int size = 0;

                        if (!jObj.isNull(Parser.COUNTRIE_NO)) {

                            JSONArray jsonArray = jObj.getJSONArray(Parser.COUNTRIE_NO);

                            size = jsonArray.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject vil = jsonArray.getJSONObject(i);
                                CountryNo = vil.getString("CountryNo");

                            }
                        }


                        if (!jObj.isNull(Parser.COUNTRIES_JSON_A)) {
                            countryNameList.clear();
                            countryNameList = Parser.AdmCountryParser(jObj.getJSONArray(Parser.COUNTRIES_JSON_A));
                        }


                        hideDialog();
                        /**
                         *  if user haa 1 country assigned
                         */

                        if (CountryNo.equals("1")) {


                            JSONArray jaary = new JSONArray();


//                            Log.d("CHAPA", "jeson to string :" + jaary.toString());
                            /** for Other  MOde*/
                            checkLogin(user_name, password, jaary, "4"); // checking online

                            editor.putInt(UtilClass.OPERATION_MODE, UtilClass.OTHER_OPERATION_MODE);
                            editor.commit();

                        } else {
                            selectedCountryList.clear();
                            getCountryAlert(user_name, password, UtilClass.OTHER_OPERATION_MODE);
                        }


                    } else {
                        // Error in login. Invalid UserName or Password
                        hideDialog();
                        String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());

                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_village_name");
                params.put("user_name", user_name);
                params.put("password", password);
                params.put("operation_mode", operationMode);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void checkVillageSelection(final String user_name, final String password, final String operationMode) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";


        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                AppController.getInstance().getRequestQueue().getCache().clear();


                hideDialog();                           // hide the Dialog bar

                String CountryNo = "0";
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                        int size = 0;

                        if (!jObj.isNull(Parser.COUNTRIE_NO)) {

                            CountryNo = Parser.NumberOfCounteryAssignedUserParser(jObj.getJSONArray(Parser.COUNTRIE_NO));
                        }


                        if (!jObj.isNull(Parser.COUNTRIES_JSON_A)) {
                            countryNameList.clear();
                            countryNameList = Parser.AdmCountryParser(jObj.getJSONArray(Parser.COUNTRIES_JSON_A));
                        }


                        if (!jObj.isNull(Parser.VILLAGE_JSON_A)) {

                            villageNameList.clear();
                            villageNameList = Parser.villageParser(jObj.getJSONArray(Parser.VILLAGE_JSON_A));

                        }
                        hideDialog();
                        // if user hsa 1 country assigned
                        if (CountryNo.equals("1")) {
                            getVillageAlert(user_name, password, false, operationMode);
                        } else {
                            selectedCountryList.clear();
                            if (operationMode.equals("1"))
                                getCountryAlert(user_name, password, UtilClass.REGISTRATION_OPERATION_MODE);
                            else
                                getCountryAlert(user_name, password, UtilClass.TRANING_n_ACTIVITY_OPERATION_MODE);
                        }


                    } else {
                        // Error in login. Invalid UserName or Password

                        String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();

                        refreshTheActivity();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_village_name");
                params.put("user_name", user_name);
                params.put("password", password);
                params.put("password", password);
                params.put("operation_mode", operationMode);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    public List<FDPItem> insertFDPNameListToSArray(boolean countrySpec) {
        int i;
        if (countrySpec) {
            ArrayList<FDPItem> temCountySpecicFDPList = new ArrayList<FDPItem>();
            for (i = 0; i < fdpNameList.size(); ++i) {
                if (selectedCountryList.get(0).getAdmCountryCode().equals(fdpNameList.get(i).getAdmCountryCode())) {
                    temCountySpecicFDPList.add(fdpNameList.get(i));
                }

            }
            fdpNameList.clear();
            for (i = 0; i < temCountySpecicFDPList.size(); ++i) {

                fdpNameList.add(temCountySpecicFDPList.get(i));


            }


            fdpNameStringArray = new String[fdpNameList.size()];

            for (i = 0; i < fdpNameList.size(); ++i) {

                FDPItem fdpItem = fdpNameList.get(i);
                fdpNameStringArray[i] = fdpItem.getFDPName();

            }


            return fdpNameList;
        } else {
            fdpNameStringArray = new String[fdpNameList.size()];

            for (i = 0; i < fdpNameList.size(); ++i) {

                FDPItem fdpItem = fdpNameList.get(i);
                fdpNameStringArray[i] = fdpItem.getFDPName();

            }


            return fdpNameList;
        }

    }


    public List<VillageItem> insertVillageNameListToSArray(boolean countrySpec) {
        int i;
        if (countrySpec) {
            ArrayList<VillageItem> temCountySpecicVillageList = new ArrayList<VillageItem>();
            for (i = 0; i < villageNameList.size(); ++i) {
                if (selectedCountryList.get(0).getAdmCountryCode().equals(villageNameList.get(i).getAdmCountryCode())) {
                    temCountySpecicVillageList.add(villageNameList.get(i));
                }

            }
            villageNameList.clear();
            for (i = 0; i < temCountySpecicVillageList.size(); ++i) {

                villageNameList.add(temCountySpecicVillageList.get(i));


            }
/***
 * convert into array string
 */

            villageNameStringArray = new String[villageNameList.size()];

            for (i = 0; i < villageNameList.size(); ++i) {

                VillageItem villageItem = villageNameList.get(i);
                villageNameStringArray[i] = villageItem.getLayR4ListName();

            }


            return villageNameList;
        } else {
            villageNameStringArray = new String[villageNameList.size()];

            for (i = 0; i < villageNameList.size(); ++i) {

                VillageItem villageItem = villageNameList.get(i);
                villageNameStringArray[i] = villageItem.getLayR4ListName();

            }


            return villageNameList;
        }

    }


    public List<AdmCountryDataModel> insertCountryNameListToSArray() {
        int i;
        countryNameStringArray = new String[countryNameList.size()];

        for (i = 0; i < countryNameList.size(); ++i) {

            AdmCountryDataModel countryItem = countryNameList.get(i);
            countryNameStringArray[i] = countryItem.getAdmCountryName();

        }


        return countryNameList;

    }


    private void getCountryAlert(final String user_name, final String password, final int operationMode) {
        aCountryL_itemsSelected = (ArrayList<AdmCountryDataModel>) insertCountryNameListToSArray();
        if (countryNameStringArray.length > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select  A Country ");
            builder.setSingleChoiceItems(countryNameStringArray, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    strCountryMode = "";
                    strCountryMode = countryNameStringArray[which];
                }
            });

            builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    /**
                     * Clean the table
                     */
                    selectedVillageList.clear();
                    selectedCountryList.clear();
                    selectedServiceCenterList.clear();
                    if (!strCountryMode.equals("")) {

                        for (int i = 0; i < countryNameStringArray.length; i++) {
                            /**
                             * store the selected country in selectedCountryList
                             */
                            if (countryNameStringArray[i].equals(strCountryMode)) {
                                selectedCountryList.add(aCountryL_itemsSelected.get(i));
                            }
                        }
                        switch (operationMode) {
                            case UtilClass.REGISTRATION_OPERATION_MODE:
                            case UtilClass.TRANING_n_ACTIVITY_OPERATION_MODE:
                                getVillageAlert(user_name, password, true, String.valueOf(operationMode));
                                break;
                            case UtilClass.DISTRIBUTION_OPERATION_MODE:
                                getFDPAlert(user_name, password, true);
                                break;
                            case UtilClass.SERVICE_OPERATION_MODE:
                                getProgramAlert(user_name, password, selectedCountryList.get(0).getAdmCountryCode());

                                break;

                            case UtilClass.OTHER_OPERATION_MODE:
                                /**
                                 * only need the json array for put parameter
                                 */

                                JSONArray jaary = new JSONArray();
                                /**
                                 * only for multiple Country Access user
                                 * value will be insert
                                 */
                                db.insertSelectedCountry(selectedCountryList.get(0).getAdmCountryCode(), selectedCountryList.get(0).getAdmCountryName());

                                jaary = UtilClass.jsonConverter(selectedCountryList.get(0).getAdmCountryCode());
                                Log.d(TAG, "jeson to string :" + jaary.toString());
                                /** for Other  MOde*/
                                checkLogin(user_name, password, jaary, "4"); // checking online

                                editor.putInt(UtilClass.OPERATION_MODE, UtilClass.OTHER_OPERATION_MODE);
                                editor.commit();


                                break;
                        }
                    } else {
                        mdialog.dismiss();
                        hideDialog();
                    }

                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    aL_itemsSelected.clear();
                    selectedCountryList.clear();
                    dialog.dismiss();
                }
            });
            mdialog = builder.create();
            mdialog.show();
        }
    }


    private void getProgramAlert(final String user_name, final String password, String countryCode) {


        final List<ProgramMasterDM> programNames = db.getProgramListNames(countryCode);


        final String[] proNamesString = new String[programNames.size()];
        for (int i = 0; i < programNames.size(); ++i) {
            ProgramMasterDM data = programNames.get(i);
            proNamesString[i] = data.getProgName();

        }


        itemChecked = new boolean[proNamesString.length];
        if (proNamesString.length > 0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select  A Program");


            builder.setSingleChoiceItems(proNamesString, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    temSelectedProgram = "";
                    temSelectedProgram = proNamesString[which];

                }
            })

                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int pos) {

                            String selectedProgCode = "";
                            String selectedCountryCode = "";
                            String selectedDonorCode = "";
                            String selectedAwardCode = "";
                            for (int i = 0; i < itemChecked.length; i++) {
                                /**
                                 * if indexed item is selected  than
                                 */
                                if (programNames.get(i).getProgName().equals(temSelectedProgram)) { //simplify code itemChecked[i] == true
                                    // todo : update in database
                                    selectedCountryCode = programNames.get(i).getAdmCountryCode();
                                    selectedDonorCode = programNames.get(i).getAdmDonorCode();
                                    selectedAwardCode = programNames.get(i).getAdmAwardCode();
                                    selectedProgCode = programNames.get(i).getAdmProgCode();


                                }
                            }


                            if (selectedProgCode.length() > 0) {
                                /**
                                 * opMonth dialog
                                 */
                                hideDialog();
                                getOpMonthAlert(user_name, password, selectedCountryCode, selectedDonorCode, selectedAwardCode, selectedProgCode);
                            }


                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {


                        }
                    });
            mdialog = builder.create();
            mdialog.show();
        } else {
            hideDialog();
            Toast.makeText(LoginActivity.this, " Contact Admin.", Toast.LENGTH_LONG).show();
        }

    }


    private void getOpMonthAlert(final String user_name, final String password, final String countryCode, final String selectedDonorCode, final String selectedAwardCode, final String selectedProgCode) {


        final List<TemOpMonth> opMonths = db.getOpMonthList(countryCode);


        final String[] opMonthNamesString = new String[opMonths.size()];
        for (int i = 0; i < opMonths.size(); ++i) {
            TemOpMonth data = opMonths.get(i);
            opMonthNamesString[i] = data.getOpMonthLable();

        }


        itemChecked = new boolean[opMonthNamesString.length];
        if (opMonthNamesString.length > 0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select  A Month");


            builder.setSingleChoiceItems(opMonthNamesString, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    tem = "";
                    tem = opMonthNamesString[which];

                }
            })

                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int pos) {

                            String selectedOpMonthCode = "";


                            for (int i = 0; i < itemChecked.length; i++) {
                                /**
                                 * if indexed item is selected  than
                                 */
                                if (opMonths.get(i).getOpMonthLable().equals(tem)) { //simplify code itemChecked[i] == true
                                    // todo : update in database
                                    selectedOpMonthCode = opMonths.get(i).getOpMonthCode();


                                }
                            }


                            if (selectedOpMonthCode.length() > 0) {
                                hideDialog();
                                getTypeFlagAlert(user_name, password, countryCode, selectedDonorCode, selectedAwardCode, selectedProgCode, selectedOpMonthCode);
                            }


                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {


                        }
                    });
            mdialog = builder.create();
            mdialog.show();
        } else {
            hideDialog();
            Toast.makeText(LoginActivity.this, "No Service Month is open for Service. Contact Admin. ", Toast.LENGTH_LONG).show();
        }

    }


    private void getTypeFlagAlert(final String user_name, final String password, final String countryCode, final String selectedDonorCode, final String selectedAwardCode, final String selectedProgCode, final String selectedOpMonthCode) {


        final String[] typeFlagNames = mContext.getResources().getStringArray(R.array.arrflagType);


        itemChecked = new boolean[typeFlagNames.length];
        if (typeFlagNames.length > 0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select  Type");


            builder.setSingleChoiceItems(typeFlagNames, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    tem = "";
                    tem = typeFlagNames[which];

                }
            })

                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int pos) {

                            String typeFlag = "";


                            if (tem.equals("Food")) {

                                typeFlag = DistributionActivity.FOOD_TYPE;
                            } else if (tem.equals("Non Food")) {

                                typeFlag = DistributionActivity.NON_FOOD_TYPE;
                            } else if (tem.equals("Cash")) {

                                typeFlag = DistributionActivity.CASH_TYPE;
                            } else {

                                typeFlag = DistributionActivity.VOUCHER_TYPE;
                            }


                            if (typeFlag.length() > 0) {
                                hideDialog();


                                checkServiceCenterSelection(user_name, password, countryCode, selectedDonorCode, selectedAwardCode, selectedProgCode, selectedOpMonthCode, typeFlag);

                            }


                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {


                        }
                    });
            mdialog = builder.create();
            mdialog.show();
        } else {
            hideDialog();
            Toast.makeText(LoginActivity.this, "No OpMonth is open for Service. Contact Admin. ", Toast.LENGTH_LONG).show();
        }

    }


    private void getServiceCenterAlert(final String user_name, final String password, boolean countrySpecificFlag) {

        final ArrayList<ServiceCenterItem> aLServiceCenter_itemsSelected = (ArrayList<ServiceCenterItem>) serviceCenterNameList; //(ArrayList<ServiceCenterItem>) insertServiceCenterNameListToSArray(countrySpecificFlag);

        final String[] serviceCenterNameStringArray = new String[serviceCenterNameList.size()];

        for (int i = 0; i < serviceCenterNameList.size(); ++i) {
            ServiceCenterItem serviceCenterItem = serviceCenterNameList.get(i);
            serviceCenterNameStringArray[i] = serviceCenterItem.getServiceCenterName();
        }
        itemChecked = new boolean[serviceCenterNameStringArray.length];
        if (serviceCenterNameStringArray.length > 0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Max TWO Service Centers ");
            builder.setMultiChoiceItems(serviceCenterNameStringArray, null,
                    new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int selectedItemId, boolean isSelected) {
                            if (isSelected) {

                                if (((AlertDialog) dialog).getListView().getCheckedItemCount() <= 2) {
                                    itemChecked[selectedItemId] = isSelected;


                                } else {
                                    Toast.makeText(LoginActivity.this, "You can not permitted to select more than Two ServiceCenter ", Toast.LENGTH_SHORT).show();

                                    ((AlertDialog) dialog).getListView().setItemChecked(selectedItemId, false);
                                }
                            } else if (aLServiceCenter_itemsSelected.contains(selectedItemId)) {
                                aLServiceCenter_itemsSelected.remove(Integer.valueOf(selectedItemId));

                            }
                        }
                    })
                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int pos) {
                            //Your logic when OK button is clicked
                            db.deleteFromSelectedServiceCenter();
                            selectedServiceCenterList.clear();
                            for (int i = 0; i < itemChecked.length; i++) {

                                if (itemChecked[i])
                                    selectedServiceCenterList.add(aLServiceCenter_itemsSelected.get(i));

                            }

                            if (selectedServiceCenterList.size() > 0) {
                                JSONArray serviceCenterJSONarry = UtilClass.srvCenterCodeJSONConverter("LoginActivity", selectedServiceCenterList, db);
                                aLServiceCenter_itemsSelected.clear();
                                Log.d(TAG, " Service Center  jeson to string :" + serviceCenterJSONarry.toString());

                                /** 3 is operation code Service */
                                checkLogin(user_name, password, serviceCenterJSONarry, "3");                        // checking online
                                editor.putInt(UtilClass.OPERATION_MODE, 3);
                                editor.commit();
                            } else {
                                hideDialog();
                                Toast.makeText(LoginActivity.this, "No Community Center in service center . Contact Admin.", Toast.LENGTH_LONG).show();
                            }


                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {


                        }
                    });
            mdialog = builder.create();
            mdialog.show();
        } else {
            hideDialog();
            Toast.makeText(LoginActivity.this, "No Service Center assigned. Contact Admin.", Toast.LENGTH_LONG).show();
        }

    }


    private void getFDPAlert(final String user_name, final String password, boolean countrySpecificFlag) {

        aLfdp_itemsSelected = (ArrayList<FDPItem>) insertFDPNameListToSArray(countrySpecificFlag);

        itemChecked = new boolean[fdpNameStringArray.length];
        if (fdpNameStringArray.length > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Max TWO FDPs ");
            builder.setMultiChoiceItems(fdpNameStringArray, null,
                    new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int selectedItemId, boolean isSelected) {
                            if (isSelected) {

                                if (((AlertDialog) dialog).getListView().getCheckedItemCount() <= 2) {
                                    itemChecked[selectedItemId] = isSelected;


                                } else {
                                    Toast.makeText(LoginActivity.this, "You can not permitted to select more than Two FDP", Toast.LENGTH_SHORT).show();

                                    ((AlertDialog) dialog).getListView().setItemChecked(selectedItemId, false);
                                }
                            } else if (aLfdp_itemsSelected.contains(selectedItemId)) {
                                aLfdp_itemsSelected.remove(Integer.valueOf(selectedItemId));

                            }
                        }
                    })
                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int pos) {
                            //Your logic when OK button is clicked
                            db.deleteFromSelectedFDP();
                            selectedFdpList.clear();
                            for (int i = 0; i < itemChecked.length; i++) {
                                if (itemChecked[i]) {
                                    selectedFdpList.add(aLfdp_itemsSelected.get(i));

                                }
                            }

                            JSONArray fdpJSONarry = UtilClass.fdpCodeJSONConverter("LoginActivity", selectedFdpList, db);
                            aLfdp_itemsSelected.clear();

                            /** 2 is operation code Distribution */
                            checkLogin(user_name, password, fdpJSONarry, "2"); // checking online
                            editor.putInt(UtilClass.OPERATION_MODE, 2);
                            editor.commit();

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {


                        }
                    });
            mdialog = builder.create();
            mdialog.show();
        } else {
            // hideDialog();
            Toast.makeText(LoginActivity.this, "No FDP assigned. Contact Admin.", Toast.LENGTH_LONG).show();
            refreshTheActivity();
        }

    }


    private void getVillageAlert(final String user_name, final String password, boolean countrySpecificFlag, final String operationMode) {

        aL_itemsSelected = (ArrayList<VillageItem>) insertVillageNameListToSArray(countrySpecificFlag);

        itemChecked = new boolean[villageNameStringArray.length];
        if (villageNameStringArray.length > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select " + aL_itemsSelected.get(0).getGeoLayRName() + " Maximum Two ");
            builder.setMultiChoiceItems(villageNameStringArray, null,
                    new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int selectedItemId, boolean isSelected) {
                            if (isSelected) {

                                if (((AlertDialog) dialog).getListView().getCheckedItemCount() <= 2) {
                                    itemChecked[selectedItemId] = isSelected;


                                } else {
                                    Toast.makeText(LoginActivity.this, "You can not permitted to select more than Two " + aL_itemsSelected.get(0).getGeoLayRName(), Toast.LENGTH_SHORT).show();

                                    ((AlertDialog) dialog).getListView().setItemChecked(selectedItemId, false);
                                }
                            } else if (aL_itemsSelected.contains(selectedItemId)) {
                                aL_itemsSelected.remove(Integer.valueOf(selectedItemId));

                            }
                        }
                    })
                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int pos) {
                            //Your logic when OK button is clicked
                            db.deleteFromSelectedVillage();
                            selectedVillageList.clear();
                            for (int i = 0; i < itemChecked.length; i++) {
                                if (itemChecked[i]) {
                                    selectedVillageList.add(aL_itemsSelected.get(i));

                                }
                            }

                            JSONArray jaary = UtilClass.layR4CodeJSONConverter("LoginActivity", selectedVillageList, db);
                            aL_itemsSelected.clear();
                            Log.d(TAG, "jeson to string :" + jaary.toString());
                            /** for registration */
                            checkLogin(user_name, password, jaary, operationMode); // checking online

                            if (operationMode.equals("1"))
                                editor.putInt(UtilClass.OPERATION_MODE, UtilClass.REGISTRATION_OPERATION_MODE);
                            else
                                editor.putInt(UtilClass.OPERATION_MODE, UtilClass.TRANING_n_ACTIVITY_OPERATION_MODE);

                            editor.commit();


                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {


                            Toast.makeText(LoginActivity.this, "No " + aL_itemsSelected.get(0).getGeoLayRName() + " Selected. Sync attempt denied." +
                                    "\n Select " + aL_itemsSelected.get(0).getGeoLayRName() + " to Sync properly.\n Try to login again.", Toast.LENGTH_SHORT).show();
                            aL_itemsSelected.clear();

                        }
                    });
            mdialog = builder.create();
            mdialog.show();
        } else {

            Toast.makeText(LoginActivity.this, "No village assigned. Contact Admin.", Toast.LENGTH_LONG).show();

            refreshTheActivity();


        }

    }

    private void refreshTheActivity() {
        finish();
        startActivity(getIntent());
    }


    /**
     * function to verify login details in mysql db
     */
    public void checkLogin(final String user_name, final String password, final JSONArray selectedVilJArry, final String operationMode) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";
        pDialog = new ProgressDialog(mContext);
        pDialog.setCancelable(false);
        pDialog.setMessage("Downloading  data .");
        pDialog.show();

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                AppController.getInstance().getRequestQueue().getCache().clear();
                writeJSONToTextFile(response, ALL_DATA);


                String errorResult = response.substring(9, 14);                                 // DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY


                boolean error = !errorResult.equals("false");
                if (!error) {

                    Log.d("TAG", "Before downLoad RegNHouseHold 6" + "  user_name:" + user_name + " password :" + password + " selectedVilJArry:" + selectedVilJArry + "operationMode:" + operationMode);
                    downLoadRegNHouseHold(user_name, password, selectedVilJArry, operationMode);

                } else {
                    // Error in login. Invalid UserName or Password
                    hideDialog();
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(),
                            errorMsg, Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_valid_user");
                params.put("user_name", user_name);
                params.put("password", password);
                params.put("lay_r_code_j", selectedVilJArry.toString());
                params.put("operation_mode", operationMode);
                Log.d("TAG1", "params:" + params.toString());
                return params;
            }

        };
        Log.d("TAG1", "strReq:" + strReq.toString());

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    /**
     * function to verify login details download RegN house hold
     */
    public void downLoadRegNHouseHold(final String user_Name, final String pass_word, final JSONArray selectedVilJArry, final String operationMode) {
        // Tag used to cancel the request
        String tag_string_req = "req_reg_hh";


        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                /***
                 * @deis: IN THIS STRING RESPONSE WRITE THE JSON DATA
                 *
                 */
                AppController.getInstance().getRequestQueue().getCache().clear();
                writeJSONToTextFile(response, REG_HOUSE_HOLD_DATA);

                /**
                 *  DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY
                 */

                String errorResult = response.substring(9, 14);

                int size = 0;

                boolean error = !errorResult.equals("false");

                if (!error) {

                    /**
                     * if registration json size is equal zero than this
                     * method call itself recursively
                     */
                    try {
                        JSONObject jObj = new JSONObject(response);

                        if (!jObj.isNull(Parser.REGISTRATION_JSON_A)) {

                            JSONArray registration = jObj.getJSONArray(Parser.REGISTRATION_JSON_A);


                            size = registration.length();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (size == 0 && !operationMode.equals("4")) {
                        downLoadRegNHouseHold(user_Name, pass_word, selectedVilJArry, operationMode);
                        Log.e(TAG, " house hold member is not found ");
                    } else {
                        Log.d("TAG", "Before downLoad RegNMembers 5" + "  user_Name:" + user_Name + " pass_word :" + pass_word + " selectedVilJArry:" + selectedVilJArry + "operationMode:" + operationMode);
                        downLoadRegNMembers(user_Name, pass_word, selectedVilJArry, operationMode);

                    }

                } else {
                    // Error in login. Invalid UserName or Password
                    hideDialog();
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(),
                            errorMsg, Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_reg_house_hold");
                params.put("user_name", user_Name);
                params.put("password", pass_word);
                params.put("lay_r_code_j", selectedVilJArry.toString());
                params.put("operation_mode", operationMode);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    /**
     * function to verify login details download RegN member data
     */
    public void downLoadRegNMembers(final String user_Name, final String pass_word, final JSONArray selectedVilJArry, final String operationMode) {
        // Tag used to cancel the request
        String tag_string_req = "req_reg";


        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                /***
                 *  IN THIS STRING RESPONSE WRITE THE JSON DATA
                 */
                AppController.getInstance().getRequestQueue().getCache().clear();
                writeJSONToTextFile(response, REG_MEMBER_DATA);


                // DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY

                String errorResult = response.substring(9, 14);

/**
 * if Json string get false than it return false
 */
                boolean error = !errorResult.equals("false");

                int size = 0;

                if (!error) {

                    JSONObject jObj = null;
                    try {
                        jObj = new JSONObject(response);

                        // Adding existing members data into local database
                        if (!jObj.isNull(Parser.MEMBERS_JSON_A)) {

                            JSONArray members = jObj.getJSONArray(Parser.MEMBERS_JSON_A);
                            size = members.length();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (size == 0 && !operationMode.equals("4")) {
                        downLoadRegNMembers(user_Name, pass_word, selectedVilJArry, operationMode);

                        Log.e(TAG, "member data no download ");
                    } else {
                        System.setProperty("http.keepAlive", "false");
                        Log.d("TAG", "Before downLoad RegNMemberProgGroup 4" + "  user_Name:" + user_Name + " pass_word :" + pass_word + " selectedVilJArry:" + selectedVilJArry + "operationMode:" + operationMode);
                        downLoadRegNMemberProgGroup(user_Name, pass_word, selectedVilJArry, operationMode);
                    }


                } else {
                    // Error in login. Invalid UserName or Password
                    hideDialog();
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(),
                            errorMsg, Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_reg_member");
                params.put("user_name", user_Name);
                params.put("password", pass_word);
                params.put("lay_r_code_j", selectedVilJArry.toString());
                params.put("operation_mode", operationMode);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    /**
     * function to verify login details download RegN member prog group code
     */
    public void downLoadRegNMemberProgGroup(final String user_Name, final String pass_word, final JSONArray selectedVilJArry, final String operationMode) {
        // Tag used to cancel the request
        String tag_string_req = "req_reg_mem_grp";


        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                // clear catch
                AppController.getInstance().getRequestQueue().getCache().clear();
                writeJSONToTextFile(response, REG_MEMBER_PROG_GROUP_DATA);

                Log.d("DIM", " After RegN Member Prog Group in txt stape: 4");


                String errorResult = response.substring(9, 14);                                     // DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY


                boolean error = !errorResult.equals("false");

                if (!error) {
                    System.setProperty("http.keepAlive", "false");
                    Log.d("TAG", "Before downLoad ServiceData 3" + "  user_Name:" + user_Name + " pass_word :" + pass_word + " selectedVilJArry:" + selectedVilJArry + "operationMode:" + operationMode);
                    downLoadServiceData(user_Name, pass_word, selectedVilJArry, operationMode);

                } else {
                    // Error in login. Invalid UserName or Password
                    hideDialog();
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(),
                            errorMsg, Toast.LENGTH_LONG).show();
                    // hideDialog();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_reg_mem_grp_data");
                params.put("user_name", user_Name);
                params.put("password", pass_word);
                params.put("lay_r_code_j", selectedVilJArry.toString());
                params.put("operation_mode", operationMode);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    /**
     * function to verify login details download Service
     */
    public void downLoadServiceData(final String user_Name, final String pass_word, final JSONArray selectedVilJArry, final String operationMode) {
        // Tag used to cancel the request
        String tag_string_req = "req_reg";


        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                // clear catch
                AppController.getInstance().getRequestQueue().getCache().clear();
                writeJSONToTextFile(response, SERVICE_DATA);

                Log.d("DIM", " After write data in Service Data . step :5");


                String errorResult = response.substring(9, 14);


                boolean error = !errorResult.equals("false");

                if (!error) {

                    System.setProperty("http.keepAlive", "false");
                    Log.d("TAG", "Before downLoad AssignProgSrv 2" + "  user_Name:" + user_Name + " pass_word :" + pass_word + " selectedVilJArry:" + selectedVilJArry + "operationMode:" + operationMode);
                    downLoadAssignProgSrv(user_Name, pass_word, selectedVilJArry, operationMode);

                } else {
                    // Error in login. Invalid UserName or Password
                    hideDialog();
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());

                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_service_data");
                params.put("user_name", user_Name);
                params.put("password", pass_word);
                params.put("lay_r_code_j", selectedVilJArry.toString());
                params.put("operation_mode", operationMode);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    /**
     * function to verify login details download RegN AssProgSrv in mysql db
     */
    /**
     * @param user_Name            staff user Name
     * @param pass_word            staff user Password
     * @param selectedLayRCodeJSON selected  village array
     * @param operationMode        operation Mode
     */
    public void downLoadAssignProgSrv(final String user_Name, final String pass_word, final JSONArray selectedLayRCodeJSON, final String operationMode) {
        // Tag used to cancel the request
        String tag_string_req = "req_ass_prog";


        StringRequest strReq = new StringRequest(Method.POST, AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                AppController.getInstance().getRequestQueue().getCache().clear();                   //IN THIS STRING RESPONSE WRITE THE JSON DATA
                writeJSONToTextFile(response, "reg_ass_prog_srv_data");

                Log.d("DIM", " After Load Assign Program Service in txt last stap :6");


                String errorResult = response.substring(9, 14);                                     // DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY


                boolean error = !errorResult.equals("false");                                       // If Json String  get False than it return false

                if (!error) {

                    // Log.d("TAG", "Before Downloading Dynamic " + "  user_Name:" + user_Name +
                    // " pass_word :" + pass_word + " selectedLayRCodeJSON:" + selectedLayRCodeJSON +
                    // "operationMode:" + operationMode);
                    downLoadDynamicData(user_Name, pass_word, selectedLayRCodeJSON, operationMode);

                } else {
                    // Error in login. Invalid UserName or Password
                    hideDialog();
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(),
                            errorMsg, Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_reg_assn_prog");
                params.put("user_name", user_Name);
                params.put("password", pass_word);
                params.put("lay_r_code_j", selectedLayRCodeJSON.toString());
                params.put("operation_mode", operationMode);

                return params;
            }

        };


        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);                      // Adding request to request queue
    }


    /**
     * function to verify login details download RegN AssProgSrv in mysql db
     */
    public void downLoadDynamicData(final String user_Name, final String pass_word, final JSONArray selectedVilJArry, final String operationMode) {
        // Tag used to cancel the request
        String tag_string_req = "req_ass_prog";
        Log.d(TAG, "operationMode: " + operationMode);

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {                                               //IN THIS STRING RESPONSE WRITE THE JSON DATA

                AppController.getInstance().getRequestQueue().getCache().clear();
                writeJSONToTextFile(response, DYNAMIC_TABLE);

                Log.d(TAG, " After Loading Dynamic Table in txt last stap :7");


                String errorResult = response.substring(9, 14);                                     //DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY
                boolean error = !errorResult.equals("false");                                       // If Json String  get False than it return false

                if (!error) {

                    downLoadTrainingActivity(user_Name, pass_word, selectedVilJArry, operationMode);                                        // IF GET NO ERROR  THAN GOTO THE MAIN ACTIVITY

                } else {
                    // Error in login. Invalid UserName or Password
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_dynamic_table");
                params.put("user_name", user_Name);
                params.put("password", pass_word);
                params.put("lay_r_code_j", selectedVilJArry.toString());
                params.put("operation_mode", operationMode);

                return params;
            }

        };


        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);          // Adding request to request queue
    }

    public void downLoadTrainingActivity(final String user_Name, final String pass_word, final JSONArray selectedVilJArry, final String operationMode) {
        // Tag used to cancel the request
        String tag_string_req = "trainingNActivity";

        StringRequest strReq = new StringRequest(Method.POST, AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                AppController.getInstance().getRequestQueue().getCache().clear();                   // clear catch
                writeJSONToTextFile(response, TRAINING_N_ACTIVITY);

                Log.d(TAG, " After Loading Dynamic Table in txt last step :8");


                hideDialog();

                String errorResult = response.substring(9, 14);                                     // DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY

                boolean error = !errorResult.equals("false");                                       // If Json String  get False than it return false

                if (!error) {

                    downLoadEnuTable(user_Name, pass_word);


                } else {
                    // Error in login. Invalid UserName or Password
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());


            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "down_load_training_n_activity");
                params.put("user_name", user_Name);
                params.put("password", pass_word);
                params.put("lay_r_code_j", selectedVilJArry.toString());
                params.put("operation_mode", operationMode);

                return params;
            }
        };


        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    public void downLoadEnuTable(final String user_Name, final String pass_word) {
        // Tag used to cancel the request
        String tag_string_req = "enu";

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK_ENU, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                AppController.getInstance().getRequestQueue().getCache().clear();  // clear catch
                writeJSONToTextFile(response, ENU_TABLE);

                Log.d(TAG, " After Loading Dynamic Table in txt last stap :7");


                hideDialog();


                /**
                 *  DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY
                 */

                String errorResult = response.substring(9, 14);


                boolean error = !errorResult.equals("false");                                       //If Json String  get False than it return false

                if (!error) {                                                                       // IF GET NO ERROR  THAN GOTO THE MAIN ACTIVITY

                    setLogin(true);                                                                     // login success
                    /**
                     *  Launch main activity
                     */
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    setUserID(user_Name);
                    setUserPassword(pass_word);
                    editor.putBoolean(IS_APP_FIRST_RUN, true);
                    editor.commit();
                    finish();
                    startActivity(intent);
                } else {
                    // Error in login. Invalid UserName or Password
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(),
                            errorMsg, Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());


            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
