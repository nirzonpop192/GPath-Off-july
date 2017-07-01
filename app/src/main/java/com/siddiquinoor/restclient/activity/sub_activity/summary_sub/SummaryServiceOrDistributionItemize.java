package com.siddiquinoor.restclient.activity.sub_activity.summary_sub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.SummaryCriteriaListAdapter;
import com.siddiquinoor.restclient.views.adapters.SummaryCriteriaModel;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.util.ArrayList;
import java.util.List;

public class SummaryServiceOrDistributionItemize extends BaseActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "SummaryServiceItemize";
    private ListView lv_ItemSummary;
    private Button btn_home;
    private Button btn_summaryMenu;
    private final Context mContext = SummaryServiceOrDistributionItemize.this;

    private String idCountry;

    private String idDonor,idAward;

    private String strAward;
    private String idProgram;
    private String strProgram;
    private String idOpMonthCode;
    private String strSrvMonth;

    private String idServiceMonth;
    private String strMonth;
    private SQLiteHandler sqlH;
    private ADNotificationManager dialog;
    private SummaryCriteriaListAdapter adapter;
    private ArrayList<SummaryCriteriaModel> criteriaArray = new ArrayList<SummaryCriteriaModel>();
    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_service_itemize);
        viewReference();

        dialog = new ADNotificationManager();

        sqlH = new SQLiteHandler(mContext);


        Intent intent = getIntent();
        idCountry = intent.getStringExtra(KEY.COUNTRY_ID);

        String dir = intent.getStringExtra(KEY.DIR_CLASS_NAME_KEY);

        if (dir.equals("ServiceSummaryMenu")) {
            /** For sub page  need to String name **/
            idServiceMonth = intent.getStringExtra(KEY.SERVICE_MONTH_CODE);
            strMonth = intent.getStringExtra(KEY.SERVICE_MONTH_NAME);
            idDonor = intent.getStringExtra(KEY.DONOR_CODE);
            idAward = intent.getStringExtra(KEY.AWARD_CODE);
            strAward = intent.getStringExtra(KEY.AWARD_NAME);
            idProgram = intent.getStringExtra(KEY.PROGRAM_CODE);
            strProgram = intent.getStringExtra(KEY.PROGRAM_NAME);
            idOpMonthCode = intent.getStringExtra(KEY.SERVICE_MONTH_CODE);
            flag = intent.getStringExtra(KEY.FLAG);
            //strSrvMonth = intent.getStringExtra(KEY.SERVICE_MONTH_NAME);
            log();


            loadServiceSummaryCriteriaList(idCountry, idDonor, idAward, idOpMonthCode, idProgram, flag);

        } else if (dir.equals("SummaryServiceItemizeAttendance")) {
            idDonor = intent.getStringExtra(KEY.DONOR_CODE);
            idAward = intent.getStringExtra(KEY.AWARD_CODE);
            strAward = intent.getStringExtra(KEY.AWARD_NAME);
            idProgram = intent.getStringExtra(KEY.PROGRAM_CODE);
            strProgram = intent.getStringExtra(KEY.PROGRAM_NAME);
            idOpMonthCode = intent.getStringExtra(KEY.SERVICE_MONTH_CODE);
            flag = intent.getStringExtra(KEY.FLAG);
            //strSrvMonth = intent.getStringExtra(KEY.SERVICE_MONTH_NAME);
            log();


            loadServiceSummaryCriteriaList(idCountry, idDonor, idAward, idOpMonthCode, idProgram, flag);
        } else {

        }


        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity((Activity) mContext);

            }
        });
        btn_summaryMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(mContext, ServiceSummaryMenu.class);
                i.putExtra(KEY.COUNTRY_ID, idCountry);
                i.putExtra(KEY.FLAG, flag);
                i.putExtra(KEY.DIR_CLASS_NAME_KEY, "SummaryServiceItemize");
                i.putExtra(KEY.AWARD_CODE, idAward);
                i.putExtra(KEY.AWARD_NAME, strAward);
                i.putExtra(KEY.PROGRAM_CODE, idProgram);
                i.putExtra(KEY.PROGRAM_NAME,strProgram);
                i.putExtra(KEY.SERVICE_MONTH_CODE, idOpMonthCode); // HERE SERVICE MONTH KEY IS ALSO DISTRUBUTION MONTH KEY
                i.putExtra(KEY.SERVICE_MONTH_NAME, strSrvMonth);// HERE SERVICE MONTH KEY IS ALSO DISTRUBUTION MONTH KEY
                startActivity(i);
            }
        });

        if (flag.equals(KEY.DIST_FLAG)) {
            btn_summaryMenu.setText("");
        }

    }

    private void log() {
        Log.d(TAG, "In idCountry: " + idCountry
                + " idDonor: " + idDonor + " idAward :" + idAward
                + " strAward: " + strAward + " idProgram :" + idProgram
                + " strProgram: " + strProgram + " idOpMonthCode :" + idOpMonthCode
                + " idServiceMonth: " + idServiceMonth
        );
    }


    private void viewReference() {
//        spServiceMonth = (Spinner) findViewById(R.id.sp_opMonthListSSC);
        lv_ItemSummary = (ListView) findViewById(R.id.lv_ServiceItemSum);
        btn_home = (Button) findViewById(R.id.btnHomeFooter);
        btn_summaryMenu = (Button) findViewById(R.id.btnRegisterFooter);
        btn_summaryMenu.setText("");
        setUpGoBackButton();
        setUpHomeButton();
    }

    private void setUpGoBackButton() {
        btn_summaryMenu.setText("");
        Drawable imageGoto = getResources().getDrawable(R.drawable.goto_back);
        btn_summaryMenu.setCompoundDrawablesRelativeWithIntrinsicBounds(imageGoto, null, null, null);
        btn_summaryMenu.setPadding(180, 10, 180, 10);
    }

    private void setUpHomeButton() {

        btn_home.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btn_home.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        btn_home.setPadding(180, 10, 180, 10);
    }



    /**
     * LOAD :: SumRegLay4TotalHHRecords of Criteria
     */
    public void loadServiceSummaryCriteriaList(String idCountry, String idDonor, String idAwarad, String idOpMonth, String idProg, String srvDistFlag) {
        Log.d(TAG, "In load service List ");


        // use veriable to like operation
        List<SummaryCriteriaModel> srvCriteriaList = sqlH.getSrvORDistExtendedItemSummaryList(idCountry, idDonor, idAwarad, idOpMonth, idProg, srvDistFlag);//SQHandler 783:Line

        if (srvCriteriaList.size() != 0) {
            criteriaArray.clear();
            for (SummaryCriteriaModel cdata : srvCriteriaList) {
                // add contacts data in arrayList
                criteriaArray.add(cdata);
            }
            //   adapter = new SummaryCriteriaListAdapter(this, criteriaArray);
            adapter = new SummaryCriteriaListAdapter(this, criteriaArray, idCountry, idDonor, idAwarad);
            adapter.notifyDataSetChanged();
            lv_ItemSummary.setAdapter(adapter);
            lv_ItemSummary.setOnItemClickListener(this);
            lv_ItemSummary.setFocusableInTouchMode(true);
        }
        //hidePDialog();
        else {
            criteriaArray.clear();
            adapter = new SummaryCriteriaListAdapter(this, criteriaArray, idCountry, idDonor, idDonor);
            adapter.notifyDataSetChanged();
            lv_ItemSummary.setAdapter(adapter);
            dialog.showInfromDialog(mContext, "NO Data", "No Data Found !");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//        Log.i(TAG, "You clicked Item: " + id + " at position:" + position);
        SummaryCriteriaModel item = (SummaryCriteriaModel) adapter.getItem(position);

        Intent iItemSpecAttendance = new Intent(mContext, SummaryServiceOrDistributionItemizeAttendance.class);

        iItemSpecAttendance.putExtra(KEY.COUNTRY_ID, idCountry);
        iItemSpecAttendance.putExtra(KEY.DIR_CLASS_NAME_KEY, "SummaryServiceItemize");


        iItemSpecAttendance.putExtra(KEY.DONOR_CODE, idDonor);
        iItemSpecAttendance.putExtra(KEY.AWARD_CODE, idAward);
        iItemSpecAttendance.putExtra(KEY.AWARD_NAME, strAward);
        iItemSpecAttendance.putExtra(KEY.PROGRAM_CODE, idProgram);
        iItemSpecAttendance.putExtra(KEY.PROGRAM_NAME, strProgram);

        iItemSpecAttendance.putExtra(KEY.ITEM_SPEC_CODE, item.getCriteria_id());
        iItemSpecAttendance.putExtra(KEY.ITEM_SPEC_NAME, item.getCriteria_name());

        iItemSpecAttendance.putExtra(KEY.SERVICE_MONTH_CODE, idOpMonthCode);
        iItemSpecAttendance.putExtra(KEY.SERVICE_MONTH_NAME, strMonth);
        iItemSpecAttendance.putExtra(KEY.FLAG, flag);
        startActivity(iItemSpecAttendance);


    }

}
