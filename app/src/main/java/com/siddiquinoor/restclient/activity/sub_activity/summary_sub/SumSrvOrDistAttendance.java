package com.siddiquinoor.restclient.activity.sub_activity.summary_sub;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.siddiquinoor.restclient.activity.DistributionActivity;
import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.SummaryDistributionListAttendanceAdapter;
import com.siddiquinoor.restclient.views.adapters.SummaryServiceListAdapter;
import com.siddiquinoor.restclient.views.adapters.SummaryServiceListModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;
import com.siddiquinoor.restclient.views.spinner.SpinnerLoader;

import java.util.ArrayList;
import java.util.List;


public class SumSrvOrDistAttendance extends BaseActivity {
    private final String TAG = SumSrvOrDistAttendance.class.getName();
    private SQLiteHandler sqlH;
    // private  int position;
    private Spinner spCriteriaS;                                                                // spinner Criteria SumRegLay4TotalHHRecords
    private String idCriteria;
    private String strCriteria;

    private ListView lv_summaryList;
    private SummaryServiceListAdapter adapterServiceAttendance;
    private SummaryDistributionListAttendanceAdapter adapterDistAttendance;
    private ArrayList<SummaryServiceListModel> srvListArray = new ArrayList<SummaryServiceListModel>();
    private Button btn_home;
    private Button btn_sammary;
    private final Context mContext = SumSrvOrDistAttendance.this;
    private String idCountry;
    private String idSrvMonth_Code;
    private String holderMonth_str;

    private String idDonor;
    private String idAward, strAward, strProgram, idProgram;
    // private String idProg;
    private ADNotificationManager dialog;
    private String flag;
    private TextView tv_pageTitle;
    private TextView tv_listTitle_statusNserl;
    private Spinner spAward, spProgram, spServiceMonth;
    private Spinner spDistributionType;
    private String idDistributionType;
    private String strDistType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Intent intent = getIntent();
        flag = intent.getStringExtra(KEY.FLAG);
        if (flag.equals(KEY.SRV_FLAG))
            setContentView(R.layout.activity_service_summary_attendance_list);
        else {
            setContentView(R.layout.activity_distribution_summary_attendance_list);
        }

        sqlH = new SQLiteHandler(this);
        dialog = new ADNotificationManager();
        viewReference();

        setAllButtonListener();


        idCountry = intent.getStringExtra(KEY.COUNTRY_ID);

        String dir = intent.getStringExtra(KEY.DIR_CLASS_NAME_KEY);

        if (dir.equals("SummaryServiceCriteria")) {


            idDonor = intent.getStringExtra(KEY.DONOR_CODE);
            idAward = intent.getStringExtra(KEY.AWARD_CODE);
            strAward = intent.getStringExtra(KEY.AWARD_NAME);
            idProgram = intent.getStringExtra(KEY.PROGRAM_CODE);
            strProgram = intent.getStringExtra(KEY.PROGRAM_NAME);
            idCriteria = intent.getStringExtra(KEY.CRITERIA_CODE);
            strCriteria = intent.getStringExtra(KEY.CRITERIA_NAME);
            idSrvMonth_Code = intent.getStringExtra(KEY.SERVICE_MONTH_CODE);
            holderMonth_str = intent.getStringExtra(KEY.SERVICE_MONTH_NAME);
            flag = intent.getStringExtra(KEY.FLAG);

            strDistType = intent.getStringExtra(KEY.DISTRIBUTION_TYPE_NAME);
            idDistributionType = intent.getStringExtra(KEY.DISTRIBUTION_TYPE_CODE);


            log();

            loadAward(idCountry);
            loadCriteria(idCountry, idDonor, idAward, idSrvMonth_Code, idProgram, flag);
            // }

        } else if (dir.equals("ServiceSummaryMenu")) {

            idDonor = intent.getStringExtra(KEY.DONOR_CODE);
            idAward = intent.getStringExtra(KEY.AWARD_CODE);
            strAward = intent.getStringExtra(KEY.AWARD_NAME);
            idProgram = intent.getStringExtra(KEY.PROGRAM_CODE);
            strProgram = intent.getStringExtra(KEY.PROGRAM_NAME);


            idSrvMonth_Code = intent.getStringExtra(KEY.SERVICE_MONTH_CODE);
            holderMonth_str = intent.getStringExtra(KEY.SERVICE_MONTH_NAME);

            flag = intent.getStringExtra(KEY.FLAG);

            log();
            loadAward(idCountry);
            loadCriteria(idCountry, idDonor, idAward, idSrvMonth_Code, idProgram, flag);

        }
        // not work with it yet
        else {
        }
        if (flag.equals(KEY.DIST_FLAG)) {
            tv_pageTitle.setText("Distribution Attendance");

//            tv_listTitle_statusNserl.setText("Status");
        }

    }

    private void setAllButtonListener() {
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent iHome = new Intent(mContext, MainActivity.class);
                startActivity(iHome);
            }
        });
        btn_sammary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Intent iSrvSummary = new Intent(mContext, SumSrvOrDistCriteria.class);

                iSrvSummary.putExtra(KEY.COUNTRY_ID, idCountry);
                iSrvSummary.putExtra(KEY.DIR_CLASS_NAME_KEY, "SummaryServiceAttendance");


                iSrvSummary.putExtra(KEY.DONOR_CODE, idDonor);
                iSrvSummary.putExtra(KEY.AWARD_CODE, idAward);
                iSrvSummary.putExtra(KEY.AWARD_NAME, strAward);
                iSrvSummary.putExtra(KEY.PROGRAM_CODE, idProgram);
                iSrvSummary.putExtra(KEY.PROGRAM_NAME, strProgram);
                iSrvSummary.putExtra(KEY.FLAG, flag);

                iSrvSummary.putExtra(KEY.SERVICE_MONTH_CODE, idSrvMonth_Code);
                iSrvSummary.putExtra(KEY.SERVICE_MONTH_NAME, holderMonth_str);
                iSrvSummary.putExtra(KEY.DISTRIBUTION_TYPE_NAME, strDistType);
                iSrvSummary.putExtra(KEY.DISTRIBUTION_TYPE_CODE, idDistributionType);

                startActivity(iSrvSummary);


            }
        });
    }


    private void log() {
        Log.d("SummaryService2",
                "idCountry : " + idCountry + " idDonor :" + idDonor
                        + "idAward : " + idAward + " strAward :" + strAward
                        + " idProgram :" + idProgram
                        + " strProgram :" + strProgram
                        + " idCriteria :" + idCriteria
                        + " strCriteria :" + strCriteria
                        + " idSrvMonth_Code :" + idSrvMonth_Code
                        + " holderMonth_str :" + holderMonth_str
        );
    }

    private void viewReference() {
        spAward = (Spinner) findViewById(R.id.su_srv_dist_A_spAward);
        spProgram = (Spinner) findViewById(R.id.su_srv_dist_A_spProgram);
        spServiceMonth = (Spinner) findViewById(R.id.sp_dist_A_opMonthListSSC);
        spDistributionType = (Spinner) findViewById(R.id.sp_srv_sum_distF_TypeSSA);

        spCriteriaS = (Spinner) findViewById(R.id.sp_criteriaListSum);
        lv_summaryList = (ListView) findViewById(R.id.lv_ServiceSumList);
        btn_home = (Button) findViewById(R.id.btnHomeFooter);
        btn_sammary = (Button) findViewById(R.id.btnRegisterFooter);

        tv_pageTitle = (TextView) findViewById(R.id.srv_distAttendance);
        tv_listTitle_statusNserl = (TextView) findViewById(R.id.list_title_sl_N_count);

        setUpGoBackButton();
        setUpHomeButton();
    }


    private void setUpGoBackButton() {
        btn_sammary.setText("");
        Drawable imageGoto = getResources().getDrawable(R.drawable.goto_back);
        btn_sammary.setCompoundDrawablesRelativeWithIntrinsicBounds(imageGoto, null, null, null);
        btn_sammary.setPadding(180, 10, 180, 10);
    }

    private void setUpHomeButton() {

        btn_home.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btn_home.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        btn_home.setPadding(180, 10, 180, 10);
    }

    /**
     * Load: Award
     *
     * @param idCountry
     */

    private void loadAward(final String idCountry) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.ADM_COUNTRY_AWARD_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + idCountry + "'";

        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.ADM_COUNTRY_AWARD_TABLE, criteria, null, false);


        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listAward);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spAward.setAdapter(dataAdapter);


        if (idAward != null) {
            for (int i = 0; i < spAward.getCount(); i++) {
                String award = spAward.getItemAtPosition(i).toString();
                if (award.equals(strAward)) {
                    position = i;
                }
            }
            spAward.setSelection(position);
        }


        spAward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strAward = ((SpinnerHelper) spAward.getSelectedItem()).getValue();
                String awardCode = ((SpinnerHelper) spAward.getSelectedItem()).getId();

                idAward = awardCode;
                if (awardCode.length() > 2) {
                    idDonor = awardCode.substring(0, 2);
                    idAward = awardCode.substring(2);
                    loadDistributionType(idCountry, idDonor, idAward);
                    Log.d(TAG, "idAward : " + idAward + " donor id :" + idAward.substring(0, 2));
                }
                Log.d(TAG, "awardCode : " + awardCode);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Award Spinner


    /***
     * LOAD:: DistributionType
     */
    private void loadDistributionType(final String cCode, final String donorCode, final String awardCode) {
        int position = 0;

        ArrayAdapter<CharSequence> adptMartial = ArrayAdapter.createFromResource(
                this, R.array.arrDistributionType, R.layout.spinner_layout);

        adptMartial.setDropDownViewResource(R.layout.spinner_layout);
        spDistributionType.setAdapter(adptMartial);


        if (idDistributionType != null) {
            for (int i = 0; i < spDistributionType.getCount(); i++) {
                String type = spDistributionType.getItemAtPosition(i).toString();
                if (type.equals(strDistType)) {
                    position = i;
                }
            }
            spDistributionType.setSelection(position);
        }


        /*** Experiments*/

        spDistributionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String type = parent.getItemAtPosition(position).toString();

                if (type.equals("None")) {
                    strDistType = "None";
                    idDistributionType = DistributionActivity.NONE;
                } else if (type.equals("Food")) {
                    strDistType = "Food";
                    idDistributionType = DistributionActivity.FOOD_TYPE;
                } else if (type.equals("Non Food")) {
                    strDistType = "Non Food";
                    idDistributionType = DistributionActivity.NON_FOOD_TYPE;
                } else if (type.equals("Cash")) {
                    strDistType = "Cash";
                    idDistributionType = DistributionActivity.CASH_TYPE;
                } else {
                    strDistType = "Voucher";
                    idDistributionType = DistributionActivity.VOUCHER_TYPE;
                }
                loadProgram(idAward, idDonor, idCountry);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * LOAD :: Program
     */
    private void loadProgram(final String idAward, final String donorId, final String idcCode) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.ADM_AWARD_CODE_COL + "='" + idAward + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.ADM_DONOR_CODE_COL + "='" + donorId + "'";

        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.COUNTRY_PROGRAM_TABLE, criteria, null, false);


        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listProgram);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spProgram.setAdapter(dataAdapter);


        if (idProgram != null) {
            for (int i = 0; i < spProgram.getCount(); i++) {
                String prog = spProgram.getItemAtPosition(i).toString();
                if (prog.equals(strProgram)) {
                    position = i;
                }
            }
            spProgram.setSelection(position);
        }


        spProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getValue();
                idProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getId();
                // if(idProgram.length()>2){
                Log.d(TAG, "load Prog data " + idProgram);


                loadServiceMonth(idcCode);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * LOAD :: load operation Month
     */
    private void loadServiceMonth(String countryCode) {//final String idCountry){

        int position = 0;
        String criteria;
        if (flag.equals(KEY.DIST_FLAG))
            criteria = SQLiteQuery.getDistributionMonths_WHERE_Condition(countryCode);
        else
            criteria = SQLiteQuery.getServiceMonths_WHERE_Service_Open_Condition(countryCode);

        List<SpinnerHelper> listMonth = sqlH.getListAndID(SQLiteHandler.OP_MONTH_TABLE, criteria, null, false);


        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listMonth);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spServiceMonth.setAdapter(dataAdapter);


        if (idSrvMonth_Code != null) {
            for (int i = 0; i < spServiceMonth.getCount(); i++) {
                String month = spServiceMonth.getItemAtPosition(i).toString();
                if (month.equals(holderMonth_str)) {
                    position = i;
                }
            }
            spServiceMonth.setSelection(position);
        }

       /* if (!idDist.isEmpty()) {
            spDistrict.setSelection( getSpinnerIndex(spDistrict,idDist) );
        }*/

        spServiceMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                holderMonth_str = ((SpinnerHelper) spServiceMonth.getSelectedItem()).getValue();
                String srvMonth_Code = ((SpinnerHelper) spServiceMonth.getSelectedItem()).getId();
                if (srvMonth_Code.length() > 2) {

                    /** I don't want change the query Code that whys use sub string
                     * //                    idCountry = idServiceMonth.substring(0, 4);
                     * //                    donorId = idServiceMonth.substring(4, 6);
                     *  //                    awardId = idServiceMonth.substring(6, 8);
                     * */
                    idSrvMonth_Code = srvMonth_Code.substring(8);
                    Log.d(TAG, "In the Service Attendance " +
                            " idCountry :" + idCountry + " donorId : " + id + " idSrvMonth_Code : " + idSrvMonth_Code);
                    loadCriteria(idCountry, idDonor, idAward, idSrvMonth_Code, idProgram, flag);
//                    loadServiceSummaryCriteriaList(idCountry, idDonor, idAward, idOpMonthCode, idProgram, flag);
                }
                // loadCriteria(idServiceMonth.substring(2),idServiceMonth.substring(0,2),idCountry);
//                Log.d(TAG, "idServiceMonth : " + idServiceMonth + " strSrvMonth :" + strSrvMonth);
                //Log.d(TAG, "ID is: " + idDist);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    /**
     * LOAD :: Criteria
     */
    private void loadCriteria(final String cCode, final String donorCode, final String awardCord,
                              final String opMCode, final String progCode, final String srvDsFlag) {

        SpinnerLoader.loadCriteriaLoader(mContext, sqlH, spCriteriaS, idCriteria, strCriteria,
                SQLiteQuery.loadCriteriaForDistributionSummary(cCode, donorCode, awardCord,
                        progCode));


        spCriteriaS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCriteria = ((SpinnerHelper) spCriteriaS.getSelectedItem()).getValue();
                idCriteria = ((SpinnerHelper) spCriteriaS.getSelectedItem()).getId();
                if (idCriteria.length() > 2) {
                    String progCode = idCriteria.substring(0, 3);
                    String srvCode = idCriteria.substring(3);


                    loadServiceNDistributionSummaryAttendanceList(cCode, donorCode, awardCord,
                            opMCode, progCode, srvCode, idDistributionType, srvDsFlag);
                }

//                Log.d(TAG, "idCriteria : " + idCriteria + " strCriteria :" + strCriteria);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    public void loadServiceNDistributionSummaryAttendanceList(String idCountry, String idDonor, String idAwarad, String idOpMonth, String idProgram, String idService, String distFlag, String srvORDistFlag) {
        Log.d(TAG, "In load service List ");
        // use veriable to like operation
        List<SummaryServiceListModel> srvList = sqlH.getTotalServiceNDistributionAttendanceSummary(idCountry, idDonor, idAwarad, idOpMonth, idProgram, idService, distFlag, srvORDistFlag);//SQHandler 783:Line
        if (srvList.size() != 0) {
            srvListArray.clear();
            for (SummaryServiceListModel data : srvList) {
                // add contacts data in arrayList
                srvListArray.add(data);
            }
            if (srvORDistFlag.equals(KEY.SRV_FLAG)) {
                adapterServiceAttendance = new SummaryServiceListAdapter(this, srvListArray);
                adapterServiceAttendance.notifyDataSetChanged();
                lv_summaryList.setAdapter(adapterServiceAttendance);

                lv_summaryList.setFocusableInTouchMode(true);
            } else {
                adapterDistAttendance = new SummaryDistributionListAttendanceAdapter(this, srvListArray);

                adapterDistAttendance.notifyDataSetChanged();
                lv_summaryList.setAdapter(adapterDistAttendance);

                lv_summaryList.setFocusableInTouchMode(true);
            }

        } else {
            /** clean the list view */
            srvListArray.clear();
            if (srvORDistFlag.equals(KEY.SRV_FLAG)) {
                adapterServiceAttendance = new SummaryServiceListAdapter(this, srvListArray);
                adapterServiceAttendance.notifyDataSetChanged();
                lv_summaryList.setAdapter(adapterServiceAttendance);

            } else {

                adapterDistAttendance = new SummaryDistributionListAttendanceAdapter(this, srvListArray);

                adapterDistAttendance.notifyDataSetChanged();
                lv_summaryList.setAdapter(adapterDistAttendance);


            }


            dialog.showInfromDialog(mContext, "No Data", "No Data found");
        }
    }



    @Override
    public void onBackPressed() {

        //super.onBackPressed();
    }
}
