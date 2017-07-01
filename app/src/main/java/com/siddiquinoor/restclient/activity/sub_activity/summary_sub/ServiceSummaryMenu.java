package com.siddiquinoor.restclient.activity.sub_activity.summary_sub;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.SummaryMenuActivity;

import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;
import com.siddiquinoor.restclient.views.spinner.SpinnerLoader;

import java.util.List;

public class ServiceSummaryMenu extends BaseActivity {

    private Button btnSummary;
    private Button btnShow;
    private ADNotificationManager dialog;
    private SQLiteHandler sqlH;
    private final Context mContext = ServiceSummaryMenu.this;
    private String idCountry;
    private RadioButton rbServiceSummary, rbItemSummary, rbServiceAttendance, rbItemAttendance;
    private String idAward;
    private String idDonor;
    private String strAward;
    private String idProgram;
    private String strProgram;
    private Spinner spAward, spProgram, spServiceMonth;
    private final String TAG = ServiceSummaryMenu.class.getSimpleName();
    private String idServiceMonth;
    private String strSrvMonth;
    private String idOpMonthCode;
    private String flag;
    private TextView tvPageTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_summary_menu);
        viewReference();
        dialog = new ADNotificationManager();

        sqlH = new SQLiteHandler(mContext);
        rbItemSummary.setVisibility(View.GONE);
        rbItemAttendance.setVisibility(View.GONE);


        Intent intent = getIntent();
        idCountry = intent.getStringExtra(KEY.COUNTRY_ID);
        flag = intent.getStringExtra(KEY.FLAG);
        String dir;
         dir=intent.getStringExtra(KEY.DIR_CLASS_NAME_KEY);
       if(dir.equals("SummaryMenuActivity")){
           loadAward(idCountry);
       }
        else {
           idAward=intent.getStringExtra(KEY.AWARD_CODE);
           strAward=intent.getStringExtra(KEY.AWARD_NAME);
           idProgram=intent.getStringExtra(KEY.PROGRAM_CODE);
           strProgram=intent.getStringExtra(KEY.PROGRAM_NAME);
           idServiceMonth=intent.getStringExtra(KEY.SERVICE_MONTH_CODE);
           strSrvMonth=intent.getStringExtra(KEY.SERVICE_MONTH_NAME);
           loadAward(idCountry);
       }




        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent iSummary = new Intent(ServiceSummaryMenu.this, SummaryMenuActivity.class);
                iSummary.putExtra(KEY.COUNTRY_ID, idCountry);
                startActivity(iSummary);
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (idAward.equals("00")) {
                    dialog.showErrorDialog(mContext, "Select Award");
                } else if (idProgram.equals("00")) {
                    dialog.showErrorDialog(mContext, "Select Program");
                } else if (idServiceMonth.equals("00")) {
                    dialog.showErrorDialog(mContext, "Select Month");
                } else
                    goToSubPages();


            }
        });


        if (flag.equals(KEY.DIST_FLAG)){
            tvPageTitle.setText("Service Summary");
            rbServiceSummary.setText("SumRegLay4TotalHHRecords");
            rbServiceAttendance.setText("Distribution Attendance");
        }


    }

    private void goToSubPages() {
        Intent intent = null;
        if (rbServiceSummary.isChecked()) {
            intent = new Intent(mContext, SumSrvOrDistCriteria.class);
            intent.putExtra(KEY.DIR_CLASS_NAME_KEY, "ServiceSummaryMenu");
            intent.putExtra(KEY.COUNTRY_ID, idCountry);
            intent.putExtra(KEY.FLAG, flag);
            intent.putExtra(KEY.DONOR_CODE, idDonor);
            intent.putExtra(KEY.AWARD_CODE, idAward);
            intent.putExtra(KEY.AWARD_NAME, strAward);
            intent.putExtra(KEY.PROGRAM_CODE, idProgram);
            intent.putExtra(KEY.PROGRAM_NAME, strProgram);
            intent.putExtra(KEY.SERVICE_MONTH_CODE, idOpMonthCode);
            intent.putExtra(KEY.SERVICE_MONTH_NAME, strSrvMonth);
        } else if (rbItemSummary.isChecked()) {
            intent = new Intent(mContext, SummaryServiceOrDistributionItemize.class);
            intent.putExtra(KEY.DIR_CLASS_NAME_KEY, "ServiceSummaryMenu");
            intent.putExtra(KEY.FLAG, flag);
            intent.putExtra(KEY.COUNTRY_ID, idCountry);
            intent.putExtra(KEY.DONOR_CODE, idDonor);
            intent.putExtra(KEY.AWARD_CODE, idAward);
            intent.putExtra(KEY.AWARD_NAME, strAward);
            intent.putExtra(KEY.PROGRAM_CODE, idProgram);
            intent.putExtra(KEY.PROGRAM_NAME, strProgram);
            intent.putExtra(KEY.SERVICE_MONTH_CODE, idOpMonthCode);
            intent.putExtra(KEY.SERVICE_MONTH_NAME, strSrvMonth);
        } else if (rbServiceAttendance.isChecked()) {
            intent = new Intent(mContext, SumSrvOrDistAttendance.class);
            intent.putExtra(KEY.DIR_CLASS_NAME_KEY, "ServiceSummaryMenu");
            intent.putExtra(KEY.FLAG, flag);
            intent.putExtra(KEY.COUNTRY_ID, idCountry);
            intent.putExtra(KEY.DONOR_CODE, idDonor);
            intent.putExtra(KEY.AWARD_CODE, idAward);
            intent.putExtra(KEY.AWARD_NAME, strAward);
            intent.putExtra(KEY.PROGRAM_CODE, idProgram);
            intent.putExtra(KEY.PROGRAM_NAME, strProgram);
            intent.putExtra(KEY.SERVICE_MONTH_CODE, idOpMonthCode);
            intent.putExtra(KEY.SERVICE_MONTH_NAME, strSrvMonth);
        } else if (rbItemAttendance.isChecked()) {
            intent = new Intent(mContext, SummaryServiceOrDistributionItemizeAttendance.class);
            intent.putExtra(KEY.DIR_CLASS_NAME_KEY, "ServiceSummaryMenu");
            intent.putExtra(KEY.FLAG, flag);
            intent.putExtra(KEY.COUNTRY_ID, idCountry);
            intent.putExtra(KEY.DONOR_CODE, idDonor);
            intent.putExtra(KEY.AWARD_CODE, idAward);
            intent.putExtra(KEY.AWARD_NAME, strAward);
            intent.putExtra(KEY.PROGRAM_CODE, idProgram);
            intent.putExtra(KEY.PROGRAM_NAME, strProgram);
            intent.putExtra(KEY.SERVICE_MONTH_CODE, idOpMonthCode);
            intent.putExtra(KEY.SERVICE_MONTH_NAME, strSrvMonth);
        } else
            dialog.showErrorDialog(mContext, "No Menu is selected yet");


        if (intent != null) {
        //    finish();
            startActivity(intent);
        }
    }

    private void viewReference() {

        spAward = (Spinner) findViewById(R.id.su_srv_spAward);
        spProgram = (Spinner) findViewById(R.id.su_srv_spProgram);
        spServiceMonth = (Spinner) findViewById(R.id.sp_opMonthListSSC);
        btnSummary = (Button) findViewById(R.id.btnRegisterFooter);
        btnShow = (Button) findViewById(R.id.btnHomeFooter);
        rbServiceSummary = (RadioButton) findViewById(R.id.rbtn_srv_summary);
        tvPageTitle = (TextView) findViewById(R.id.tv_srv_dstPageTitle);


        rbItemSummary = (RadioButton) findViewById(R.id.rbtn_Itemized_summary);
        rbItemAttendance = (RadioButton) findViewById(R.id.rbtn_itemize_attendance);
        rbServiceAttendance = (RadioButton) findViewById(R.id.rbtn_serv_attendance);
        setUpGoBackButton();
        setUpShowButton();

    }


    private void setUpGoBackButton() {
        btnSummary.setText("");
        Drawable imageGoto = getResources().getDrawable(R.drawable.goto_back);
        btnSummary.setCompoundDrawablesRelativeWithIntrinsicBounds(imageGoto, null, null, null);
        btnSummary.setPadding(180, 10, 180, 10);
    }

    private void setUpShowButton() {

        btnShow.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.show);
        btnShow.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        btnShow.setPadding(180, 10, 180, 10);
    }


    /**
     * LOAD :: Award
     */
    private void loadAward(final String idCountry) {

        SpinnerLoader.loadAwardLoader(mContext, sqlH, spAward, idCountry, idAward, strAward);


        spAward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strAward = ((SpinnerHelper) spAward.getSelectedItem()).getValue();
                String awardCode = ((SpinnerHelper) spAward.getSelectedItem()).getId();

                idAward = awardCode;
                if (awardCode.length() > 2) {
                    idDonor = awardCode.substring(0, 2);
                    idAward = awardCode.substring(2);
                    loadProgram(idAward, idDonor, idCountry);
                    Log.d(TAG, "idAward : " + idAward + " donor id :" + idAward.substring(0, 2));
                }
                Log.d(TAG, "awardCode : " + awardCode);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Award Spinner


    /**
     * LOAD :: Program
     */
    private void loadProgram(final String idAward, final String donorId, final String idcCode) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.ADM_AWARD_CODE_COL + "='" + idAward + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.ADM_DONOR_CODE_COL + "='" + donorId + "'";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.COUNTRY_PROGRAM_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listProgram);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
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
                if (!idProgram.equals("00")){
                    if (sqlH.checkAdmCountryProgramsVoucherFlag(idCountry, idDonor, idAward, idProgram)) {
                        rbItemSummary.setVisibility(View.VISIBLE);
                        rbItemAttendance.setVisibility(View.VISIBLE);
                    } else {
                        rbItemSummary.setVisibility(View.GONE);
                        rbItemAttendance.setVisibility(View.GONE);
                    }

                    loadServiceMonth(idcCode);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * LOAD :: load Service Month
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


        if (idServiceMonth != null) {
            for (int i = 0; i < spServiceMonth.getCount(); i++) {
                String month = spServiceMonth.getItemAtPosition(i).toString();
                if (month.equals(strSrvMonth)) {
                    position = i;
                }
            }
            spServiceMonth.setSelection(position);
        }



        spServiceMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strSrvMonth = ((SpinnerHelper) spServiceMonth.getSelectedItem()).getValue();
                idServiceMonth = ((SpinnerHelper) spServiceMonth.getSelectedItem()).getId();
                if (idServiceMonth.length() > 2) {

                    /** I don't want change the query Code that whys use sub string
                     * //                    idCountry = idServiceMonth.substring(0, 4);
                     * //                    donorId = idServiceMonth.substring(4, 6);
                     *  //                    awardId = idServiceMonth.substring(6, 8);
                     * */
                    idOpMonthCode = idServiceMonth.substring(8);
                    Log.d(TAG, "in if condition sERVICE sUMMARY bY cRITERIA \n" +
                            " idCountry :" + idCountry + " donorId : " + id + " idOpMonthCode : " + idOpMonthCode);
//                    loadServiceSummaryCriteriaList(idCountry, donorId, awardId, idOpMonthCode);
                }


                Log.d(TAG, "idServiceMonth : " + idServiceMonth + " strSrvMonth :" + strSrvMonth);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


}
