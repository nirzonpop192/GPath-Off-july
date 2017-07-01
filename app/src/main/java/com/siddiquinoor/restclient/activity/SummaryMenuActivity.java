package com.siddiquinoor.restclient.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.sub_activity.summary_sub.DistributionSummaryMenu;
import com.siddiquinoor.restclient.activity.sub_activity.summary_sub.GroupSummary;
import com.siddiquinoor.restclient.activity.sub_activity.summary_sub.ServiceSummaryMenu;
import com.siddiquinoor.restclient.activity.sub_activity.summary_sub.SumRegLay4TotalHHRecords;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.activity.sub_activity.summary_sub.SummaryAssignCriteria;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

/**
 * This is SumRegLay4TotalHHRecords Option Page
 */


public class SummaryMenuActivity extends BaseActivity {
    private RadioButton rbHouseHold, rbService, rbDistribution;
    private Button btnGo, btnHome;
    private String idCountry;
    private RadioButton rbAssign;
    private RadioButton rbtGroup;

    private ADNotificationManager dialog;
    private final Context mContext = SummaryMenuActivity.this;
    private SQLiteHandler sqlH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_all_summary);

        initial();

//        sdkcfjadsklhckjxdchkj
        String operationModeName = sqlH.getDeviceOperationModeName();
        idCountry = sqlH.getSelectedCountryCode(operationModeName);

        viewAccessController();
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (rbHouseHold.isChecked()) {
                    Intent iRegSum = new Intent(SummaryMenuActivity.this, SumRegLay4TotalHHRecords.class);
                    iRegSum.putExtra(KEY.COUNTRY_ID, idCountry);
                    finish();
                    startActivity(iRegSum);
                } else if (rbService.isChecked()) {
                    Intent iSrvSum = new Intent(SummaryMenuActivity.this, ServiceSummaryMenu.class);
                    iSrvSum.putExtra(KEY.COUNTRY_ID, idCountry);
                    iSrvSum.putExtra(KEY.FLAG, KEY.SRV_FLAG);
                    iSrvSum.putExtra(KEY.DIR_CLASS_NAME_KEY, "SummaryMenuActivity");
                    finish();
                    startActivity(iSrvSum);
                } else if (rbAssign.isChecked()) {
                    Intent iAsgSum = new Intent(SummaryMenuActivity.this, SummaryAssignCriteria.class);
                    iAsgSum.putExtra(KEY.COUNTRY_ID, idCountry);
                    finish();
                    startActivity(iAsgSum);
                } else if (rbDistribution.isChecked()) {
                    Intent iDistSum = new Intent(SummaryMenuActivity.this, DistributionSummaryMenu.class);
                    iDistSum.putExtra(KEY.COUNTRY_ID, idCountry);
                    iDistSum.putExtra(KEY.FLAG, KEY.DIST_FLAG);
                    iDistSum.putExtra(KEY.DIR_CLASS_NAME_KEY, "SummaryMenuActivity");
                    finish();
                    startActivity(iDistSum);
                } else if (rbtGroup.isChecked()) {
                    Intent iGrpSum = new Intent(SummaryMenuActivity.this, GroupSummary.class);
                    finish();
                    startActivity(iGrpSum);
                    //   intent.putExtra(KEY.COUNTRY_ID, idCountry);
                } else
                    dialog.showErrorDialog(mContext, "No Menu is selected yet");


            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent iHome = new Intent(mContext, MainActivity.class);
                startActivity(iHome);
            }
        });
    }

    private void initial() {
        reference();
        dialog = new ADNotificationManager();
        sqlH = new SQLiteHandler(mContext);
        rbDistribution.setEnabled(false);
        rbHouseHold.setEnabled(false);
        rbService.setEnabled(false);
        rbAssign.setEnabled(false);
        rbtGroup.setEnabled(false);
    }


    private void viewAccessController() {

//        SharedPreferences settings;
//
//        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
//        int operationMode = settings.getInt(UtilClass.OPERATION_MODE, 0);
        String operationModeName = sqlH.getDeviceOperationModeName();
//        Log.d("NIR1", "operation mode : " + operationMode);
        switch (operationModeName) {
            case UtilClass.REGISTRATION_OPERATION_MODE_NAME:
                rbHouseHold.setEnabled(true);
                rbAssign.setEnabled(true);
                rbtGroup.setEnabled(true);

                break;
            case UtilClass.DISTRIBUTION_OPERATION_MODE_NAME:
                rbDistribution.setEnabled(true);
                break;
            case UtilClass.SERVICE_OPERATION_MODE_NAME:
                rbService.setEnabled(true);

                break;
        }
    }

    private void reference() {
        rbHouseHold = (RadioButton) findViewById(R.id.rbtn_household_samm);
        rbService = (RadioButton) findViewById(R.id.rbtn_service_samm);
        rbDistribution = (RadioButton) findViewById(R.id.rbtn_distribution_samm);
        rbAssign = (RadioButton) findViewById(R.id.rbtn_Assign_summary);
        rbtGroup = (RadioButton) findViewById(R.id.rbtn_Group_summary);


        btnHome = (Button) findViewById(R.id.btnRegisterFooter);
        btnGo = (Button) findViewById(R.id.btnHomeFooter);


    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();                                                                    // turn  of the back press
    }

    /**
     * calling getWidth() and getHeight() too early:
     * When  the UI has not been sized and laid out on the screen yet..
     *
     * @param hasFocus the value will be true when UI is focus
     */

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        setUpHomeButton();
        setUpGoButton();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpGoButton() {
        btnGo.setText("");
        Drawable imageGoto = getResources().getDrawable(R.drawable.goto_forward);
        btnGo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, imageGoto, null, null);

//        setPaddingButton(mContext, imageGoto, btnGo);
        btnHome.setPadding(-1, 10, -1, 10);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpHomeButton() {

        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(null, imageHome, null, null);
        btnHome.setPadding(-1, 10, -1, 10);
    }




  /*  @Override
    public void onBackPressed() {
       *//* Intent noBackpressWork=new Intent(SummaryMenuActivity.this,SummaryMenuActivity.class);
        startActivity(noBackpressWork);*//*
    }*/
}
