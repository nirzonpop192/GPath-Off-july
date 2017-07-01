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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.siddiquinoor.restclient.activity.DistributionActivity;
import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
//import com.siddiquinoor.restclient.views.adapters.ServiceDataModel;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.DistributionSummaryCriteriaListAdapter;
import com.siddiquinoor.restclient.views.adapters.SummaryCriteriaListAdapter;
import com.siddiquinoor.restclient.views.adapters.SummaryCriteriaModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.util.ArrayList;
import java.util.List;


public class SumSrvOrDistCriteria extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private SQLiteHandler sqlH;
    private String idCountry;
    private String idDonor;
    private String idAward;
    private String strAward;
    private String idProgram;
    private String strProgram;
    private String idOpMonthCode;
    private String strSrvMonth;
    private String flag;

    private String idServiceMonth;
    private String strMonth;
    private final String TAG = "SummaryServiceCriteria";
    private ArrayList<SummaryCriteriaModel> criteriaArray = new ArrayList<SummaryCriteriaModel>();

    private SummaryCriteriaListAdapter adapterServiceSumCriteria;
    private DistributionSummaryCriteriaListAdapter adapterDistSumCriteria;
    private ListView lv_CriteriaSummary;
    private Button btn_home;
    private Button btn_sammary;
    private final Context mcontext = SumSrvOrDistCriteria.this;
    private ADNotificationManager dialog;
    private TextView tvPageTitle;
    private Spinner spAward, spProgram, spServiceMonth;
    private RelativeLayout rl_dist_table_title;
    private LinearLayout ll_table_title;
    private Spinner spDistributionType;
    private String idDistributionType;
    private String strDistType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_summary_service);
        Intent intent = getIntent();
        flag = intent.getStringExtra(KEY.FLAG);



        viewReference();
        dialog = new ADNotificationManager();
        //mcontext=SummaryServiceCriteria.this;
        sqlH = new SQLiteHandler(mcontext);

        if (flag.equals(KEY.SRV_FLAG)) {

            Log.d("NIR","In the service Criteria ");
            rl_dist_table_title.setVisibility(View.GONE);
        } else {

            ll_table_title.setVisibility(View.GONE);

            Log.d("NIR","In the Distribution Criteria ");
        }
        btn_home.setOnClickListener(this);
        btn_sammary.setOnClickListener(this);


//        Intent intent = getIntent();
        idCountry = intent.getStringExtra(KEY.COUNTRY_ID);

        String dir = intent.getStringExtra(KEY.DIR_CLASS_NAME_KEY);
        if (dir.equals("SummaryServiceAttendance")) {
//            idServiceMonth = intent.getStringExtra(KEY.SERVICE_MONTH_CODE);
//            strMonth = intent.getStringExtra(KEY.SERVICE_MONTH_NAME);

            idServiceMonth = intent.getStringExtra(KEY.SERVICE_MONTH_CODE);
            strSrvMonth = strMonth = intent.getStringExtra(KEY.SERVICE_MONTH_NAME);
            idDonor = intent.getStringExtra(KEY.DONOR_CODE);
            idAward = intent.getStringExtra(KEY.AWARD_CODE);
            strAward = intent.getStringExtra(KEY.AWARD_NAME);
            idProgram = intent.getStringExtra(KEY.PROGRAM_CODE);
            strProgram = intent.getStringExtra(KEY.PROGRAM_NAME);
            idOpMonthCode = intent.getStringExtra(KEY.SERVICE_MONTH_CODE);
            //strSrvMonth = intent.getStringExtra(KEY.SERVICE_MONTH_NAME);
            flag = intent.getStringExtra(KEY.FLAG);
            idDistributionType = intent.getStringExtra(KEY.DISTRIBUTION_TYPE_CODE);
            strDistType = intent.getStringExtra(KEY.DISTRIBUTION_TYPE_NAME);

            Log.d(TAG, "In SUSB idCountry: " + idCountry
                    + " idDonor: " + idDonor + " idAward :" + idAward
                    + " strAward: " + strAward + " idProgram :" + idProgram
                    + " strProgram: " + strProgram + " idOpMonthCode :" + idOpMonthCode
                    + " idServiceMonth: " + idServiceMonth
                    + " strDistType: " + strDistType
                    + " idDistributionType: " + idDistributionType
            );

            loadAward(idCountry);
            //         loadServiceSummaryCriteriaList(idCountry, idDonor, idAward, idOpMonthCode,idProgram,flag);
//            strSrvMonth=strMonth = intent.getStringExtra(KEY.SERVICE_MONTH_NAME);
//            idDonor = intent.getStringExtra(KEY.DONOR_CODE);
            /** For sub page no need to String name **/


        } else if (dir.equals("ServiceSummaryMenu")) {
            /** For sub page  need to String name **/
            idServiceMonth = intent.getStringExtra(KEY.SERVICE_MONTH_CODE);

            strSrvMonth = strMonth = intent.getStringExtra(KEY.SERVICE_MONTH_NAME);
            idDonor = intent.getStringExtra(KEY.DONOR_CODE);

            idAward = intent.getStringExtra(KEY.AWARD_CODE);
            strAward = intent.getStringExtra(KEY.AWARD_NAME);
            idProgram = intent.getStringExtra(KEY.PROGRAM_CODE);
            strProgram = intent.getStringExtra(KEY.PROGRAM_NAME);
            idOpMonthCode = intent.getStringExtra(KEY.SERVICE_MONTH_CODE);
            flag = intent.getStringExtra(KEY.FLAG);

            Log.d(TAG, "In idCountry: " + idCountry
                    + " idDonor: " + idDonor + " idAward :" + idAward
                    + " strAward: " + strAward + " idProgram :" + idProgram
                    + " strProgram: " + strProgram + " idOpMonthCode :" + idOpMonthCode
                    + " idServiceMonth: " + idServiceMonth
            );

            loadAward(idCountry);

            //         loadServiceSummaryCriteriaList(idCountry, idDonor, idAward, idOpMonthCode,idProgram,flag);

        } else {

        }

        if (flag.equals(KEY.DIST_FLAG)) {
            tvPageTitle.setText("SumRegLay4TotalHHRecords");

        }

    }

    private void viewReference() {

        spAward = (Spinner) findViewById(R.id.su_srv_dist_spAward);
        spProgram = (Spinner) findViewById(R.id.su_srv_dist_spProgram);
        spServiceMonth = (Spinner) findViewById(R.id.sp_dist_opMonthListSSC);
        spDistributionType = (Spinner) findViewById(R.id.sp_srv_sum_distF_TypeSSC);

        lv_CriteriaSummary = (ListView) findViewById(R.id.lv_ServiceSumCriteria);
        btn_home = (Button) findViewById(R.id.btnHomeFooter);
        btn_sammary = (Button) findViewById(R.id.btnRegisterFooter);
        tvPageTitle = (TextView) findViewById(R.id.tv_srv_distCriPageTitle);

        rl_dist_table_title= (RelativeLayout) findViewById(R.id.dist_table_title);
        ll_table_title= (LinearLayout) findViewById(R.id.table_title);
        btn_sammary.setText("Service SumRegLay4TotalHHRecords");

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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHomeFooter:
                finish();
                Intent iHome = new Intent(mcontext, MainActivity.class);
                startActivity(iHome);

                break;
            case R.id.btnRegisterFooter:
                Intent i = new Intent(mcontext, ServiceSummaryMenu.class);
                i.putExtra(KEY.COUNTRY_ID, idCountry);
                i.putExtra(KEY.FLAG, flag);
                i.putExtra(KEY.DIR_CLASS_NAME_KEY, "SummaryServiceItemize");
                i.putExtra(KEY.AWARD_CODE, idAward);
                i.putExtra(KEY.AWARD_NAME, strAward);
                i.putExtra(KEY.PROGRAM_CODE, idProgram);
                i.putExtra(KEY.PROGRAM_NAME, strProgram);
                i.putExtra(KEY.SERVICE_MONTH_CODE, idOpMonthCode); // HERE SERVICE MONTH KEY IS ALSO DISTRUBUTION MONTH KEY
                i.putExtra(KEY.SERVICE_MONTH_NAME, strSrvMonth);// HERE SERVICE MONTH KEY IS ALSO DISTRUBUTION MONTH KEY

                startActivity(i);
                break;
        }
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.i(TAG, "You clicked Item: " + id + " at position:" + position);
        SummaryCriteriaModel criteriaS;
        if (flag.equals(KEY.SRV_FLAG))
            criteriaS = (SummaryCriteriaModel) adapterServiceSumCriteria.getItem(position);
        else
            criteriaS = (SummaryCriteriaModel) adapterDistSumCriteria.getItem(position);

        Intent iServSummaryCri = new Intent(mcontext, SumSrvOrDistAttendance.class);
        iServSummaryCri.putExtra(KEY.COUNTRY_ID, idCountry);
        iServSummaryCri.putExtra(KEY.DIR_CLASS_NAME_KEY, "SummaryServiceCriteria");
        iServSummaryCri.putExtra(KEY.DONOR_CODE, idDonor);
        iServSummaryCri.putExtra(KEY.AWARD_CODE, idAward);
        iServSummaryCri.putExtra(KEY.AWARD_NAME, strAward);
        iServSummaryCri.putExtra(KEY.PROGRAM_CODE, idProgram);
        iServSummaryCri.putExtra(KEY.PROGRAM_NAME, strProgram);
        iServSummaryCri.putExtra(KEY.CRITERIA_CODE, criteriaS.getCriteria_id());
        iServSummaryCri.putExtra(KEY.CRITERIA_NAME, criteriaS.getCriteria_name());
        iServSummaryCri.putExtra(KEY.SERVICE_MONTH_CODE, idOpMonthCode);
        iServSummaryCri.putExtra(KEY.SERVICE_MONTH_NAME, strMonth);
        iServSummaryCri.putExtra(KEY.DISTRIBUTION_TYPE_CODE, idDistributionType);
        iServSummaryCri.putExtra(KEY.DISTRIBUTION_TYPE_NAME, strDistType);
        iServSummaryCri.putExtra(KEY.FLAG, flag);
        startActivity(iServSummaryCri);


    }

    private void loadAward(final String idCountry) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.ADM_COUNTRY_AWARD_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + idCountry + "'";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.ADM_COUNTRY_AWARD_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listAward);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
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

                    loadDistributionType(idCountry,idDonor,idAward);
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


                loadServiceMonth(idcCode);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner







 /*** LOAD:: DistributionType
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

                if(type.equals("None")){
                    strDistType = "None";
                    idDistributionType = DistributionActivity.NONE;
                }else if (type.equals("Food")) {
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
     * LOAD :: load operation Month
     */
    private void loadServiceMonth(String countryCode) {//final String idCountry){

        int position = 0;
        String criteria;
        if (flag.equals(KEY.DIST_FLAG))
            criteria = SQLiteQuery.getDistributionMonths_WHERE_Condition(countryCode);
        else
            criteria = SQLiteQuery.getServiceMonths_WHERE_Service_Open_Condition(countryCode);
        // Spinner Drop down elements for District
        List<SpinnerHelper> listMonth = sqlH.getListAndID(SQLiteHandler.OP_MONTH_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listMonth);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
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

       /* if (!idDist.isEmpty()) {
            spDistrict.setSelection( getSpinnerIndex(spDistrict,idDist) );
        }*/

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
                    loadServiceSummaryCriteriaList(idCountry, idDonor, idAward, idOpMonthCode, idProgram,idDistributionType, flag);
                }
                // String donorId=idServiceMonth.substring(0,1)
                // loadCriteria(idServiceMonth.substring(2),idServiceMonth.substring(0,2),idCountry);
                Log.d(TAG, "idServiceMonth : " + idServiceMonth + " strSrvMonth :" + strSrvMonth);
                //Log.d(TAG, "ID is: " + idDist);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner



    /**
     * LOAD :: SumRegLay4TotalHHRecords of Criteria for Service
     */
    public void loadServiceSummaryCriteriaList(String idCountry, String idDonor, String idAwarad, String idOpMonth, String idProg,String distFlag, String srvDistFlag) {
//        Log.d(TAG, "In load service List ");


        // use veriable to like operation
        List<SummaryCriteriaModel> srvCriteriaList;
        if (flag.equals(KEY.SRV_FLAG))
            srvCriteriaList = sqlH.getServiceSummaryCriteriaList(idCountry, idDonor, idAwarad, idOpMonth, idProg,distFlag);//SQHandler 783:Line
        else {
            srvCriteriaList = sqlH.getDistributionSummaryCriteriaList(idCountry, idDonor, idAwarad, idOpMonth, idProg,distFlag);//SQHandler 783:Line
        }

        Log.d(TAG,"Distbution size :"+srvCriteriaList.size());

        if (srvCriteriaList.size() != 0) {

            criteriaArray.clear();
            for (SummaryCriteriaModel cdata : srvCriteriaList) {
                // add contacts data in arrayList
                criteriaArray.add(cdata);
            }
            if (flag.equals(KEY.SRV_FLAG)) {
                adapterServiceSumCriteria = new SummaryCriteriaListAdapter(this, criteriaArray, idCountry, idDonor, idAwarad);
                adapterServiceSumCriteria.notifyDataSetChanged();
                lv_CriteriaSummary.setAdapter(adapterServiceSumCriteria);

            } else {
                adapterDistSumCriteria = new DistributionSummaryCriteriaListAdapter(this, criteriaArray);
                adapterDistSumCriteria.notifyDataSetChanged();
                lv_CriteriaSummary.setAdapter(adapterDistSumCriteria);


            }
            lv_CriteriaSummary.setOnItemClickListener(this);
            lv_CriteriaSummary.setFocusableInTouchMode(true);


/*
            adapterDistSumCriteria*/
        }
        //hidePDialog();

        else {
            criteriaArray.clear();
            if (flag.equals(KEY.SRV_FLAG)) {
                adapterServiceSumCriteria = new SummaryCriteriaListAdapter(this, criteriaArray, idCountry, idDonor, idDonor);
                adapterServiceSumCriteria.notifyDataSetChanged();
                lv_CriteriaSummary.setAdapter(adapterServiceSumCriteria);
            } else {
                adapterDistSumCriteria = new DistributionSummaryCriteriaListAdapter(this, criteriaArray);
                adapterDistSumCriteria.notifyDataSetChanged();
                lv_CriteriaSummary.setAdapter(adapterDistSumCriteria);

            }
            dialog.showInfromDialog(mcontext, "NO Data", "No Data Found !");
        }
    }



}
