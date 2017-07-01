package com.siddiquinoor.restclient.activity.sub_activity.dynamic_table;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.controller.SessionManager;
import com.siddiquinoor.restclient.data_model.DTSurveyTableDataModel;
import com.siddiquinoor.restclient.data_model.SurveyModel;
import com.siddiquinoor.restclient.data_model.adapters.AssignDataModel;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.CalculationPadding;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.ReportViewPagerAdapter;

import java.util.ArrayList;

public class DT_ReportActivity extends AppCompatActivity {

    private Context mContext;

    private TextView tvDTTitle, tvDTAward, tvDTProgram, tvDTActivityTitle, tvSurveyNumber, tvDTMonthTitle;
    private ImageView ivLeft, ivRight, ivDeleteSurvey;
    private RelativeLayout rlIndicator;
    private ViewPager viewPager;

    private AssignDataModel.DynamicDataIndexDataModel data;
    private SQLiteHandler sqlH;
    private ReportViewPagerAdapter adapter;

    private SessionManager session;
    private int currentPosition = 0;
    private Button btnDynamicPage;

    private ArrayList<SurveyModel> surveyModels;
    // private Spinner spDtMonth;
    //  private String idMonth, strMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dt_report);
        mContext = this;

        data = getIntent().getParcelableExtra(KEY.DYNAMIC_INDEX_DATA_OBJECT_KEY);
        sqlH = new SQLiteHandler(mContext);
        session = new SessionManager(mContext);

        initUI();

        setListeners();

        generateSurveyList();

        btnDynamicPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // loadDtMonth(data.getcCode(), data.getOpMode());
//        setSurveyPager();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * view reference
     */
    private void initUI() {
        tvDTTitle = (TextView) findViewById(R.id.tvDTTitle);
        tvDTAward = (TextView) findViewById(R.id.tvDTAward);
        tvDTProgram = (TextView) findViewById(R.id.tvDTProgram);
        tvDTMonthTitle = (TextView) findViewById(R.id.tvDTMonthTitle);
        tvDTActivityTitle = (TextView) findViewById(R.id.tvDTActivityTitle);
        tvSurveyNumber = (TextView) findViewById(R.id.tvSurveyNumber);
        ivLeft = (ImageView) findViewById(R.id.ivLeft);
        ivRight = (ImageView) findViewById(R.id.ivRight);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ivDeleteSurvey = (ImageView) findViewById(R.id.ivDeleteSurvey);
        rlIndicator = (RelativeLayout) findViewById(R.id.rlIndicator);

        //    spDtMonth = (Spinner) findViewById(R.id.sp_dtReport_dtMonth);

        tvDTTitle.setText(data.getDtTittle());
        tvDTAward.setText("Award Name : " + data.getAwardName());
        String progName = data.getProgramCode().equals("000") ? " Cross Cutting" : data.getProgramName();
        tvDTProgram.setText("Program Name : " + progName);
        tvDTActivityTitle.setText("Activity Title  : " + data.getPrgActivityTitle());

        findViewById(R.id.btnHomeFooter).setVisibility(View.GONE);
        btnDynamicPage = (Button) findViewById(R.id.btnRegisterFooter);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        addIconHomeButton();
    }

    /**
     * couldn't use setPaddingButton()
     */

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addIconHomeButton() {


        btnDynamicPage.setText("");
        Drawable imageDynamic = getResources().getDrawable(R.drawable.dynamic_icon_42);
        btnDynamicPage.setCompoundDrawablesRelativeWithIntrinsicBounds(imageDynamic, null, null, null);


        CalculationPadding calPadd = new CalculationPadding();
        int leftPadd, rightPadd;
        leftPadd = rightPadd = calPadd.calculateViewPadding(mContext, imageDynamic, btnDynamicPage);
        btnDynamicPage.setPadding(leftPadd, 5, rightPadd, 5);


    }

// donn't delete  the bellow code
  /*  private void loadDtMonth(final String cCode, String opCode) {
        SpinnerLoader.loadDtMonthLoader(mContext, sqlH, spDtMonth, cCode, opCode, idMonth, strMonth);


        spDtMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strMonth = ((SpinnerHelper) spDtMonth.getSelectedItem()).getValue();
                idMonth = ((SpinnerHelper) spDtMonth.getSelectedItem()).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner*/


    private void generateSurveyList() {
        ArrayList<Integer> surveyList = sqlH.getSurveyList(data.getDtBasicCode(), data.getcCode(), data.getDonorCode(), data.getAwardCode(), data.getProgramCode(), session.getStaffId());
        surveyModels = new ArrayList<>();
        for (int surveyNum : surveyList) {
            SurveyModel surveyModel = new SurveyModel();
            ArrayList<DTSurveyTableDataModel> dtSurveyTableDataModels = sqlH.dtSurveyTableDataModels(surveyNum, data.getDtBasicCode(), data.getcCode(), data.getDonorCode(), data.getAwardCode(), data.getProgramCode(), session.getStaffId());
            surveyModel.setSurveyNum(surveyNum);
            surveyModel.setDtSurveyTableDataModels(dtSurveyTableDataModels);
            surveyModels.add(surveyModel);

            Log.e("SURVEY", "Survey Number:   " + surveyModel.getSurveyNum() + " ");

        }
        if (surveyModels != null && surveyModels.size() > 0) {
            rlIndicator.setVisibility(View.VISIBLE);
            ivDeleteSurvey.setVisibility(View.VISIBLE);
            setSurveyPager();
        } else {
            rlIndicator.setVisibility(View.GONE);
            ivDeleteSurvey.setVisibility(View.GONE);
        }
    }

    private void setSurveyPager() {
        adapter = new ReportViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragments(surveyModels);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(currentPosition);
        tvSurveyNumber.setText(adapter.getPageTitle(viewPager.getCurrentItem()));

    }

    private void setListeners() {
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("IVLEFT", "Pressed");
                if (currentPosition > 0) {
                    currentPosition--;
                    viewPager.setCurrentItem(currentPosition);
                }
            }
        });

        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("IVRIGHT", "Pressed");
                currentPosition++;
                if (currentPosition < surveyModels.size()) {
                    viewPager.setCurrentItem(currentPosition);
                } else {
                    currentPosition--;
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tvSurveyNumber.setText(adapter.getPageTitle(position));
                currentPosition = position;
                // setOpMonthCode
                String temp = "Month  : " + sqlH.getDtResponseMonthName(data.getcCode(), adapter.getSurveyModels(currentPosition).getDtSurveyTableDataModels().get(0).getOpMonthCode());
                tvDTMonthTitle.setText(temp);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ivDeleteSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Do you want to delete this survey?\nPress YES to delete, NO to abort.");
        builder.setCancelable(true);

        builder.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteSurvey();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * delete the survey
     */
    private void deleteSurvey() {
        int position = viewPager.getCurrentItem();
        SurveyModel surveyModel = surveyModels.get(position);
        DTSurveyTableDataModel dtSurveyTableDataModel = surveyModel.getDtSurveyTableDataModels().get(0);
        sqlH.deleteFromDTSurveyTable(dtSurveyTableDataModel.getDtBasic(), dtSurveyTableDataModel.getCountryCode(), dtSurveyTableDataModel.getDonorCode(),
                dtSurveyTableDataModel.getAwardCode(), dtSurveyTableDataModel.getProgramCode(), dtSurveyTableDataModel.getDtEnuId(), dtSurveyTableDataModel.getDtrSeq(),
                dtSurveyTableDataModel.getOpMode(), dtSurveyTableDataModel.getOpMonthCode(), surveyModel.getSurveyNum());
        surveyModels.remove(position);
        adapter.removeFragment(position);
        if (surveyModels.size() == 0) {
            rlIndicator.setVisibility(View.GONE);
            ivDeleteSurvey.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
        }
    }
}
