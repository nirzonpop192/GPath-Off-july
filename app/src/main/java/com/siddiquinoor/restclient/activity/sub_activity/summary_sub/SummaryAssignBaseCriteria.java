package com.siddiquinoor.restclient.activity.sub_activity.summary_sub;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.SummaryOfMemberAssignedListModel;
import com.siddiquinoor.restclient.views.adapters.SummaryOfMemberAssignedListAdapter;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.util.ArrayList;
import java.util.List;

public class SummaryAssignBaseCriteria extends BaseActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = SummaryAssignBaseCriteria.class.getSimpleName();
    private TextView tvProgram, tvVillage;

    private TextView tvCriteria;
    private String strCriteria;
    private String idCriteria;
    private String idVillage;
    private String strVillage;
    private String idProgram;
    private String strProgram;
    private String idCountry;
    private String idAward;
    private String idService;
    private ListView lv_assignList;
    private SQLiteHandler sqlH;
    private Context mContext;
    private ArrayList<SummaryOfMemberAssignedListModel> assignLisArray = new ArrayList<SummaryOfMemberAssignedListModel>();
    private SummaryOfMemberAssignedListAdapter adapter;
    private String idDonor;
    private String idDistrict;
    private String idUpazila;
    private String idUnit;
    private Button btnHome;
    private Button btnAssignSummary;
    private ADNotificationManager dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_summary_assign_base_criteria);
        viewReference();
        mContext = this;
        dialog = new ADNotificationManager();

        sqlH = new SQLiteHandler(mContext);
        getDataFromIntent();
        tvProgram.setText(strProgram);

        tvVillage.setText(strVillage);
        tvCriteria.setText(strCriteria);
        loadAssignSummaryList(idCountry, idDistrict, idUpazila, idUnit, idVillage, idDonor, idAward, idProgram, idService);

        setAllListener();


    }

    private void setAllListener() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(mContext, MainActivity.class));
            }
        });
        btnAssignSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent iAssSummayCri = new Intent(mContext, SummaryAssignCriteria.class);
//                iAssSummayCri.putExtra("COUNTRY_ID", idCountry);
                iAssSummayCri.putExtra(KEY.COUNTRY_ID, idCountry);
                iAssSummayCri.putExtra("ASS_SUMM_DIR", true);
                iAssSummayCri.putExtra(KEY.PROGRAM_CODE, idProgram);
                iAssSummayCri.putExtra(KEY.PROGRAM_NAME, strProgram);
                iAssSummayCri.putExtra(KEY.VILLAGE_CODE, idVillage);
                iAssSummayCri.putExtra(KEY.VILLAGE_NAME, strVillage);
                startActivity(iAssSummayCri);
            }
        });
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        idCriteria = intent.getStringExtra("Assign_SumCRITERIA_ID");
        /** @description: IdCriteria = Program Code[***] + Service Code [**] */
        idService = idCriteria.substring(3, 5);
        strCriteria = intent.getStringExtra("Assign_SumCRITERIA_STR");

        idVillage = intent.getStringExtra(KEY.VILLAGE_CODE);
        strVillage = intent.getStringExtra(KEY.VILLAGE_NAME);

        idProgram = intent.getStringExtra(KEY.PROGRAM_CODE);
        strProgram = intent.getStringExtra(KEY.PROGRAM_NAME);

        idCountry = intent.getStringExtra(KEY.COUNTRY_ID);
        idDonor = intent.getStringExtra(KEY.DONOR_CODE);
        idAward = intent.getStringExtra(KEY.AWARD_CODE);
        idDistrict = intent.getStringExtra(KEY.DISTRICT_CODE);
        idUpazila = intent.getStringExtra(KEY.UPAZILLA_CODE);
        idUnit = intent.getStringExtra(KEY.UNIT_CODE);
        Log.d(TAG, " in idCountry:" + idCountry);

       /* Log.d(TAG, " in Assigne summary idService   \n" + idService +
             //   "strCriteria \n" + strCriteria +
             //   "idVillage   \n" + idVillage +
             //   "strVillage  \n" + strVillage +
             //   "idProgram   \n" + idProgram +
             //   "strProgram  \n" + strProgram +
                "idCountry   \n" + idCountry +
                "idDonor     \n" + idDonor +
                "idAward     \n" + idAward +
                "idDistrict  \n" + idDistrict +
                "idUpazila   \n" + idUpazila +
                "idUnit      " + idUnit);*/


    }

    private void viewReference() {
        tvProgram = (TextView) findViewById(R.id.tv_assignSummarProg);
        tvVillage = (TextView) findViewById(R.id.tv_assignSummarVillage);
        tvCriteria = (TextView) findViewById(R.id.tv_assignSummarCriteria);
        lv_assignList = (ListView) findViewById(R.id.lv_assignSummaryList);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnAssignSummary = (Button) findViewById(R.id.btnRegisterFooter);
        btnAssignSummary.setText("");


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
        setUpGoToAssgnButton();
        setUpHomeButton();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpGoToAssgnButton() {
        btnAssignSummary.setText("");
        Drawable backImage = getResources().getDrawable(R.drawable.goto_back);
        btnAssignSummary.setCompoundDrawablesRelativeWithIntrinsicBounds(backImage, null, null, null);
        setPaddingButton(mContext, backImage, btnAssignSummary);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpHomeButton() {
        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, btnHome);
    }

    /**
     * : 2015-10-17
     * : Faisal Mohammad
     * description: LOAD :: Criteria in list view
     */
    public void loadAssignSummaryList(String cCode, String disCode, String upCode, String unCode, String donorCode, String vCode, String awardCode, String progCode, String srvCode) {
        Log.d(TAG, "In load service List ");


        // use veriable to like operation
        List<SummaryOfMemberAssignedListModel> assignList = sqlH.getTotalListOfMemberRAssignedSummary(cCode, disCode, upCode, unCode, vCode, donorCode, awardCode, progCode, srvCode);
        if (assignList.size() != 0) {
            assignLisArray.clear();
            for (SummaryOfMemberAssignedListModel data : assignList) {
                // add contacts data in arrayList
                assignLisArray.add(data);

            }

            adapter = new SummaryOfMemberAssignedListAdapter((Activity) mContext, assignLisArray);
            adapter.notifyDataSetChanged();
            lv_assignList.setAdapter(adapter);
            lv_assignList.setOnItemClickListener(this);
            lv_assignList.setFocusableInTouchMode(true);
            Toast.makeText(mContext, "Size : " + assignList.size(), Toast.LENGTH_SHORT).show();
        } else {
            dialog.showInfromDialog(mContext, "No Data", " No data for this village");
        }
        //hidePDialog();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "position : " + position);

    }

    /**
     * 2015-10-17

     * off the back press button
     */

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }


}
