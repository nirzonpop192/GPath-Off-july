package com.siddiquinoor.restclient.activity.sub_activity.service_sub;

/**
 * Author: Faisal Mohammad
 * This class show the Record Details
 */

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
import android.widget.TextView;

import com.siddiquinoor.restclient.activity.SummaryMenuActivity;
import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.ServiceActivity;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.ServiceDataModel;
import com.siddiquinoor.restclient.views.adapters.ServiceSlDataModle;
import com.siddiquinoor.restclient.views.adapters.ServiceRecordListDataModleAdapter;

import java.util.ArrayList;

public class ServiceRecordDetails extends BaseActivity implements AdapterView.OnItemClickListener {
    private TextView tv_award;
    private TextView tv_criteria;
    private TextView tv_opMonth;

    private TextView tv_mmId;
    private TextView tv_VillageName;
    private TextView tv_MemberName;
    private Button btn_home;
    private Button btnSummary;
    private Button btnGoToService;
    // private View btn_Service_noNeed; // remove it
    private ListView lv_service_sl;
    private Context mcontext;
    private SQLiteHandler sqlH;
    private ServiceDataModel srvData;

/*    private String holderCountryId;
    private String holderAwardId;
    private String holderAwardStr;
    private String holderDonorId;
    private String holderCriteriaStr;*/
    // private  String holderServiceCenter;

    ArrayList<ServiceSlDataModle> srvSlArrayList = new ArrayList<ServiceSlDataModle>();
    ServiceRecordListDataModleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_record);
        viewReference();
        mcontext = ServiceRecordDetails.this;
        sqlH = new SQLiteHandler(this);

        ServiceDataModel sPerson = getIntent().getExtras().getParcelable(KEY.SERVICE_DATA_OBJECT_KEY);
        srvData = sPerson;
        /**
         * Null point Safety
         */

        if (sPerson != null) {
      /*      holderCountryId = sPerson.getC_code(); // for back to the service page with id load Awar
            holderAwardId = sPerson.getAward_code(); // for back to the service page with id
            holderAwardStr = sPerson.getAwardName(); // for back to the service page with id
            holderDonorId = sPerson.getDonor_code(); // for back to the service page with id
            holderCriteriaStr = sPerson.getCriteriaName(); // for back to the service page with id
*/
            tv_award.setText(sPerson.getAwardName());
            tv_criteria.setText(sPerson.getCriteriaName());
            tv_opMonth.setText(sPerson.getOpMonthStr());

            tv_mmId.setText(sPerson.getNewID());
            tv_VillageName.setText(sPerson.getVillageName());
            tv_MemberName.setText(sPerson.getHh_mm_name());

            loadServiceSlListData(sPerson.getC_code(),
                    sPerson.getDistrictCode(), sPerson.getUpazillaCode(), sPerson.getUnitCode(), sPerson.getVillageCode(),
                    sPerson.getHHID(), sPerson.getMemberId(), sPerson.getOpCode(), sPerson.getOpMontheCode(), sPerson.getDonor_code()
                    , sPerson.getAward_code(), sPerson.getProgram_code(), sPerson.getService_code());


            Log.d("NIR0","Service Record: idGroup idGroupCat:"+sPerson.getTemIdGroupCat()+" strGroupCat: "+sPerson.getTemStrGroupCat()
           +"   idGroup:"+sPerson.getTemIdGroup()+" strGroup : "+sPerson.getTemStrGroup()
            );
        }


        setListener();


    }

    private void setListener() {
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent iHome = new Intent(mcontext, MainActivity.class);
                startActivity(iHome);
            }
        });
        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(mcontext, SummaryMenuActivity.class));
            }
        });
        btnGoToService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //** working later to work
                finish();
                Intent iService = new Intent(mcontext, ServiceActivity.class);



                iService.putExtra(KEY.DIR_CLASS_NAME_KEY, "ServiceRecordDetails");
                iService.putExtra(KEY.COUNTRY_ID, srvData.getC_code());
                iService.putExtra(KEY.AWARD_CODE, srvData.getAward_code());
                iService.putExtra(KEY.AWARD_NAME, srvData.getAwardName());
                iService.putExtra(KEY.DONOR_CODE, srvData.getDonor_code());
                iService.putExtra(KEY.CRITERIA_NAME, srvData.getCriteriaName());
                iService.putExtra(KEY.CRITERIA_CODE, srvData.getCriteriaId());
                iService.putExtra(KEY.SERVICE_CENTER_CODE, srvData.getServiceCenterCode());
                iService.putExtra(KEY.SERVICE_CENTER_NAME, srvData.getTemServiceCenterName());
                iService.putExtra(KEY.SERVICE_DATE, srvData.getTemServiceDate());
                iService.putExtra(KEY.SERVICE_MONTH_CODE, srvData.getTemIdServiceMonth());
                iService.putExtra(KEY.SERVICE_MONTH_NAME, srvData.getTemStrSrvMonth());

                iService.putExtra(KEY.GROUP_NAME, srvData.getTemStrGroup());
                iService.putExtra(KEY.GROUP_CODE, srvData.getTemIdGroup());
                iService.putExtra(KEY.GROUP_CATEGORY_CODE, srvData.getTemIdGroupCat());
                iService.putExtra(KEY.GROUP_CATEGORY_NAME, srvData.getTemStrGroupCat());


                startActivity(iService);
            }
        });
    }

    /**
     * @param cCode Country Code
     * @param disCode District Code
     * @param upCode
     * @param uCode
     * @param vCode
     * @param hhID
     * @param mmID
     * @param opCode
     * @param opMCode
     * @param donorCode
     * @param awardCode
     * @param progCode
     * @param srvCode
     */

    private void loadServiceSlListData(String cCode, String disCode, String upCode, String uCode, String vCode,
                                       String hhID, String mmID, String opCode, String opMCode,
                                       String donorCode, String awardCode, String progCode, String srvCode) {

        ArrayList<ServiceSlDataModle> srvDatalist = sqlH.getServiceDetailsForMember(cCode, donorCode,
                awardCode, disCode, upCode, uCode, vCode, hhID, mmID, opCode, opMCode, progCode, srvCode);

        srvSlArrayList.clear();
        for (ServiceSlDataModle srvdata : srvDatalist) {
            // add contacts data in arrayList
            srvSlArrayList.add(srvdata);
        }


        adapter = new ServiceRecordListDataModleAdapter((Activity) mcontext, srvSlArrayList, srvData);
        adapter.notifyDataSetChanged();
        lv_service_sl.setAdapter(adapter);
        lv_service_sl.setOnItemClickListener(this);
        lv_service_sl.setFocusableInTouchMode(true);
    }


    private void viewReference() {
        tv_award = (TextView) findViewById(R.id.edt_Srv_Rec_award);
        tv_criteria = (TextView) findViewById(R.id.edt_Srv_Rec_criteria);
        tv_opMonth = (TextView) findViewById(R.id.edt_Srv_Rec_operationMonth);

        tv_mmId = (TextView) findViewById(R.id.edt_Srv_Rec_mmId);
        tv_VillageName = (TextView) findViewById(R.id.edt_Srv_Rec_villageName);
        tv_MemberName = (TextView) findViewById(R.id.edt_Srv_Rec_mmName);
        btn_home = (Button) findViewById(R.id.btnHomeFooter);
        btnGoToService = (Button) findViewById(R.id.btnRegisterFooter);

        lv_service_sl = (ListView) findViewById(R.id.lv_ServiceRecord);

        btnSummary = (Button) findViewById(R.id.btn_srvRecordGoServiceSummaryPage);
        // btnGoToService = (Button) findViewById(R.id.btn_goServicePage);


        setUpHomeButton();
        setUpSummaryButton();
        setUpGoToServiceButton();

    }


    private void setUpGoToServiceButton() {
        btnGoToService.setText("");
        Drawable saveImage = getResources().getDrawable(R.drawable.goto_back);
        btnGoToService.setCompoundDrawablesRelativeWithIntrinsicBounds(saveImage, null, null, null);
        btnGoToService.setPadding(180, 10, 180, 10);
    }

    private void setUpSummaryButton() {
        btnSummary.setText("");
        Drawable summeryImage = getResources().getDrawable(R.drawable.summession_b);
        btnSummary.setCompoundDrawablesRelativeWithIntrinsicBounds(summeryImage, null, null, null);
        btnSummary.setPadding(380, 10, 380, 10);
    }


    private void setUpHomeButton() {

        btn_home.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btn_home.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        btn_home.setPadding(180, 10, 180, 10);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }


}
