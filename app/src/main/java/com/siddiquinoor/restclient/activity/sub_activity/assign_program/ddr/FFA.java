package com.siddiquinoor.restclient.activity.sub_activity.assign_program.ddr;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.AssignActivity;
import com.siddiquinoor.restclient.data_model.AssignDDR_FFA_DataModel;
import com.siddiquinoor.restclient.data_model.LayRCodes;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.data_model.adapters.AssignDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;
import com.siddiquinoor.restclient.views.spinner.SpinnerLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FFA extends BaseActivity {


    private TextView tv_MemberID, tv_MemberName, tv_HHName, tv_Criteria, tvRegDate;
    private TextView tv_PageTitle;
    private Spinner spGroupCategories, spGroup, spDisable, spActive;
    private String idGroupCat, idGroup, idActive;
    private String strGroupCat, strGroup;
    private RadioGroup radioGroup_mal_DDR;
    private RadioButton rb_1, rb_2, rb_3, rb_4, rb_5, rb_6, rb_7;
    int position;
    private final Context mContext = FFA.this;

    private String memberId15D;

    private static final String YES = "Y";
    private static final String NO = "N";
    private String idCountry;


    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat formatUSA = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);


    private AssignDDR_FFA_DataModel model = new AssignDDR_FFA_DataModel();


    private static final String TAG = FFA.class.getSimpleName();

    private Button btnSave, btnSummary, btnHome, btnBackToAssign;
    private TextView label_disable;

    private AssignDataModel assignMem = new AssignDataModel();
    private SQLiteHandler sqlH;


    private ADNotificationManager errorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_for_ddr_malwai);
        initi();
        disableControl();
        getIntentValueForRegnFFA();
        checkRadioButtonValue();

        /**
         * set Page Title
         */
        tv_PageTitle.setText("FFA");

        buttonActionListener();

        /**
         * resorting the the save data to view
         */
        resortingMemberData();


        loadGroupCategory(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getProgram_code());
    }


    /**
     * LOAD:: data
     * Resorting::
     */
    private void resortingMemberData() {
        /**
         * Search in RegNAssProgSrv
         */
        if (sqlH.ifExistsInRegNAssProgSrv(assignMem)) {
            String regDate = sqlH.getRegDateFromRegNAssignProgSrv(assignMem.getCountryCode(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getProgram_code(), assignMem.getService_code());
            tvRegDate.setText(regDate);

        }

        if (sqlH.ifDataExistIn_RegN_FFA(assignMem.getCountryCode(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId())) {
            AssignDDR_FFA_DataModel data = sqlH.getAssignDataIfExitsInRegNFFA_table(assignMem.getCountryCode(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId());
            if (data.getOrphanChildRb1().equals("Y")) {
                rb_1.setChecked(true);
            } else if (data.getElderlyHeadedRb2().equals("Y")) {
                rb_2.setChecked(true);
            } else if (data.getChronicallyIllRb3().equals("Y")) {
                rb_3.setChecked(true);
            } else if (data.getFemaleHeadedRb4().equals("Y")) {
                rb_4.setChecked(true);
            } else if (data.getCropFailureRb5().equals("Y")) {
                rb_5.setChecked(true);
            } else if (data.getChildrenRecSuppFeedNRb6().equals("Y")) {
                rb_6.setChecked(true);
            } else if (data.getWillingnessRb7().equals("Y")) {
                rb_7.setChecked(true);
            }
            idGroupCat = assignMem.getGroupCatCode();
            strGroupCat = assignMem.getGroupCatName();
            idGroup = assignMem.getGroupCode();
            strGroup = assignMem.getGroupName();
            idActive = assignMem.getActiveCode();
        }
    }

    private void initi() {
        viewReference();
        sqlH = new SQLiteHandler(this);
        errorDialog = new ADNotificationManager();
    }

    private void disableControl() {
        spDisable.setVisibility(View.GONE);
        label_disable.setVisibility(View.GONE);
    }


    /**
     *
     */
    private void viewReference() {
        //TEXT_VIEW

        tv_MemberID = (TextView) findViewById(R.id.txt_assign_mal_MemberID_ddr);
        tv_MemberName = (TextView) findViewById(R.id.txt_assign_mal_MemberName_ddr);
        tv_HHName = (TextView) findViewById(R.id.txt_assign_mal_HHName_ddr);
        tv_Criteria = (TextView) findViewById(R.id.txt_assign_mal_Criteria_ddr);
        tvRegDate = (TextView) findViewById(R.id.txt_assign_mal_Reg_Date_ddr);
        tv_PageTitle = (TextView) findViewById(R.id.tv_ass_page2Title);

        //Spinner

        spGroupCategories = (Spinner) findViewById(R.id.sp_ass_GroupCategories_mal_ddr);
        spGroup = (Spinner) findViewById(R.id.sp_ass_GroupCode_mal_ddr);
        spActive = (Spinner) findViewById(R.id.sp_ass_Active_mal_ddr);
        spDisable = (Spinner) findViewById(R.id.sp_ass_disable_mal_ddr);
        /**
         * Radio Group
         */
        radioGroup_mal_DDR = (RadioGroup) findViewById(R.id.radio_grp_mal_ddr);
        /**
         * radio Button
         */
        rb_1 = (RadioButton) findViewById(R.id.rb_ass_mal_ddr_1);
        rb_2 = (RadioButton) findViewById(R.id.rb_ass_mal_ddr_2);
        rb_3 = (RadioButton) findViewById(R.id.rb_ass_mal_ddr_3);
        rb_4 = (RadioButton) findViewById(R.id.rb_ass_mal_ddr_4);
        rb_5 = (RadioButton) findViewById(R.id.rb_ass_mal_ddr_5);
        rb_6 = (RadioButton) findViewById(R.id.rb_ass_mal_ddr_6);
        rb_7 = (RadioButton) findViewById(R.id.rb_ass_mal_ddr_7);


        //Button
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnSummary = (Button) findViewById(R.id.btnRegisterFooter);
        btnBackToAssign = (Button) findViewById(R.id.btn_mal_goAssignePage_ddr);
        btnSave = (Button) findViewById(R.id.btn_assign_mal_save_ddr);

        label_disable = (TextView) findViewById(R.id.lbl_disable);


    }

    /**
     * get value which pass by intent
     */
    private void getIntentValueForRegnFFA() {
        Intent intent;
        intent = getIntent();

        assignMem = intent.getParcelableExtra(KEY.ASSIGN_DATA_OBJECT_KEY);
        memberId15D = intent.getExtras().getString(KEY.MEMBER_ID);
        tv_MemberID.setText(assignMem.getNewId());
        tv_MemberName.setText(assignMem.getHh_mm_name());
        tv_HHName.setText(assignMem.getHh_name());
        tv_Criteria.setText(assignMem.getTemCriteriaString());
        idCountry = assignMem.getCountryCode();
    }


    /**
     * set the Radio button Listener
     */
    private void checkRadioButtonValue() {
        rb_1.setChecked(false);
        rb_2.setChecked(false);
        rb_3.setChecked(false);
        rb_4.setChecked(false);
        rb_5.setChecked(false);
        rb_6.setChecked(false);
        rb_7.setChecked(false);
        model.setOrphanChildRb1(NO);
        model.setChildHeadedRb1(NO);
        model.setElderlyHeadedRb2(NO);
        model.setChronicallyIllRb3(NO);
        model.setFemaleHeadedRb4(NO);
        model.setCropFailureRb5(NO);
        model.setChildrenRecSuppFeedNRb6(NO);
        model.setWillingnessRb7(NO);

        radioGroup_mal_DDR.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                position = group.indexOfChild(findViewById(checkedId));
                switch (position) {
                    case 0:
                        rb_1.setChecked(true);
                        model.setChildHeadedRb1(YES);
                        model.setElderlyHeadedRb2(NO);
                        model.setChronicallyIllRb3(NO);
                        model.setFemaleHeadedRb4(NO);
                        model.setCropFailureRb5(NO);
                        model.setChildrenRecSuppFeedNRb6(NO);
                        model.setWillingnessRb7(NO);
                        break;
                    case 1:
                        rb_2.setChecked(true);
                        model.setWillingnessRb7(NO);
                        model.setElderlyHeadedRb2(YES);
                        model.setChronicallyIllRb3(NO);
                        model.setFemaleHeadedRb4(NO);
                        model.setCropFailureRb5(NO);
                        model.setChildrenRecSuppFeedNRb6(NO);
                        model.setWillingnessRb7(NO);
                        break;
                    case 2:
                        rb_3.setChecked(true);
                        model.setElderlyHeadedRb2(NO);
                        model.setWillingnessRb7(NO);
                        model.setChildHeadedRb1(NO);
                        model.setChronicallyIllRb3(YES);
                        model.setFemaleHeadedRb4(NO);
                        model.setCropFailureRb5(NO);
                        model.setChildrenRecSuppFeedNRb6(NO);
                        break;
                    case 3:
                        rb_4.setChecked(true);
                        model.setChronicallyIllRb3(NO);
                        model.setWillingnessRb7(NO);
                        model.setChildHeadedRb1(NO);
                        model.setElderlyHeadedRb2(NO);
                        model.setFemaleHeadedRb4(YES);
                        model.setCropFailureRb5(NO);
                        model.setChildrenRecSuppFeedNRb6(NO);
                        break;
                    case 4:
                        rb_5.setChecked(true);
                        model.setFemaleHeadedRb4(NO);
                        model.setWillingnessRb7(NO);
                        model.setChildHeadedRb1(NO);
                        model.setElderlyHeadedRb2(NO);
                        model.setChronicallyIllRb3(NO);
                        model.setCropFailureRb5(YES);
                        model.setChildrenRecSuppFeedNRb6(NO);
                        break;
                    case 5:
                        rb_6.setChecked(true);
                        model.setCropFailureRb5(NO);
                        model.setWillingnessRb7(NO);
                        model.setChildHeadedRb1(NO);
                        model.setElderlyHeadedRb2(NO);
                        model.setChronicallyIllRb3(NO);
                        model.setFemaleHeadedRb4(NO);
                        model.setChildrenRecSuppFeedNRb6(YES);
                        break;
                    case 6:
                        rb_7.setChecked(true);
                        model.setChildrenRecSuppFeedNRb6(NO);
                        model.setWillingnessRb7(YES);
                        model.setChildHeadedRb1(NO);
                        model.setElderlyHeadedRb2(NO);
                        model.setChronicallyIllRb3(NO);
                        model.setFemaleHeadedRb4(NO);
                        model.setCropFailureRb5(NO);
                        break;
                }
            }
        });
    }

    private void buttonActionListener() {
        tvRegDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRegDate();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 11/6/2016  add Registration Validation  in all assigne
                saveMethod();
            }
        });

        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSummaryActivity(FFA.this, assignMem.getCountryCode());
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity(FFA.this);

            }
        });

        btnBackToAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoAssignBeneficiaryPage();
            }
        });
    }


    private void gotoAssignBeneficiaryPage() {


        Intent iAssign = new Intent(mContext, AssignActivity.class);
        finish();

        iAssign.putExtra(KEY.COUNTRY_ID, assignMem.getCountryCode());
        iAssign.putExtra(AssignActivity.SUB_ASSIGN_DIR, true);
        iAssign.putExtra(AssignActivity.ASSIGN_AWARD_CODE, assignMem.getAward_code());
        iAssign.putExtra(AssignActivity.ASSIGN_AWARD_STR, assignMem.getTemAwardString());
        iAssign.putExtra(AssignActivity.ASSIGN_PROGRAM_CODE, assignMem.getProgram_code());
        iAssign.putExtra(AssignActivity.ASSIGN_PROGRAM_STR, assignMem.getTemProgramString());
        iAssign.putExtra(AssignActivity.ASSIGN_DONOR_CODE, assignMem.getDonor_code());
        iAssign.putExtra(AssignActivity.ASSIGN_CRITERIA_CODE, assignMem.getService_code());
        iAssign.putExtra(AssignActivity.ASSIGN_CRITERIA_STR, assignMem.getTemCriteriaString());
        iAssign.putExtra(KEY.MEMBER_ID, memberId15D);


        startActivity(iAssign);

    }


    private void saveMethod() {

        if (sqlH.get_ProgramMultipleSrv(assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getProgram_code()).equals("N")
                && sqlH.get_MemOthCriteriaLive(assignMem.getCountryCode(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getProgram_code(), assignMem.getService_code())) {
            errorDialog.showErrorDialog(mContext, "Member remains active in other criteria. Save attempt denied.");
        } else {

            String regDate = tvRegDate.getText().toString();

            if (regDate.equals("")) {
                errorDialog.showErrorDialog(mContext, "Missing registration date. Save attempt denied.");
            } else if (idGroup == null || idGroup.equals("00")) {
                errorDialog.showErrorDialog(mContext, "Missing Group. Save attempt denied.");
            } else {


                String entryBy = getStaffID();
                assignMem.setEntryBy(entryBy);

                assignMem.setGrdCode(sqlH.getGRDDefaultActiveReason(assignMem.getProgram_code(), assignMem.getService_code()));

                assignMem.setRegNDate(tvRegDate.getText().toString());

                String entryDate = "";
                try {
                    entryDate = getDateTime();


                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String orphanedChildren = null;

                assignMem.setEntryDate(entryDate);

                /**
                 *  for upload
                 */

                assignMem.setRegNDate(regDate);
                assignMem.setGrdCode(sqlH.getGRDDefaultActiveReason(assignMem.getProgram_code(), assignMem.getService_code()));
                assignMem.setGrdDate(UtilClass.calculateGRDDate(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), sqlH));

/**
 * setter for syntax generator
 */

                SQLServerSyntaxGenerator assignFfa = new SQLServerSyntaxGenerator();

                assignFfa.setAdmCountryCode(idCountry);
                assignFfa.setLayR1ListCode(assignMem.getDistrictCode());
                assignFfa.setLayR2ListCode(assignMem.getUpazillaCode());
                assignFfa.setLayR3ListCode(assignMem.getUnitCode());
                assignFfa.setLayR4ListCode(assignMem.getVillageCode());
                assignFfa.setHHID(assignMem.getHh_id());
                assignFfa.setMemID(assignMem.getMemId());
                assignFfa.setAdmDonorCode(assignMem.getDonor_code());
                assignFfa.setAdmAwardCode(assignMem.getAward_code());
                assignFfa.setProgCode(assignMem.getProgram_code());
                assignFfa.setSrvCode(assignMem.getService_code());
                assignFfa.setRegNDate(regDate);
                assignFfa.setGRDCode(assignMem.getGrdCode());
                assignFfa.setGRDDate(assignMem.getGrdDate());
                assignFfa.setOrphanedChildren(orphanedChildren);
                assignFfa.setChildHeaded(model.getChildHeadedRb1());
                assignFfa.setElderlyHeaded(model.getElderlyHeadedRb2());
                assignFfa.setChronicallyIll(model.getChronicallyIllRb3());
                assignFfa.setFemaleHeaded(model.getFemaleHeadedRb4());
                assignFfa.setCropFailure(model.getCropFailureRb5());
                assignFfa.setChildrenRecSuppFeedN(model.getChildrenRecSuppFeedNRb6());
                assignFfa.setWillingness(model.getWillingnessRb7());
                assignFfa.setEntryBy(entryBy);
                assignFfa.setEntryDate(entryDate);

                assignFfa.setGrpCode(idGroup);
                assignFfa.setActive(idActive);


/**
 * RegNAssProgSrv
 */
                if (sqlH.ifExistsInRegNAssProgSrv(assignMem)) {


                    int id = sqlH.editMemberDataIn_RegNAsgProgSrv(assignMem);
                    //Syntax Generator
                    sqlH.insertIntoUploadTable(assignFfa.updateRegAssProgSrvForAssign());
                    Log.d(TAG, "Update Into Upload Table");
                } else {
                    sqlH.addMemberDataInto_RegNAsgProgSrv(assignMem);
                    sqlH.insertIntoUploadTable(assignFfa.insertIntoRegAssProgSrv());
                    Log.d(TAG, "Insert Into Upload Table");
                }
                /**                * FFA Table                */

                if (sqlH.ifDataExistIn_RegN_FFA(assignMem.getCountryCode(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId())) {


                    sqlH.editIntoDDR_RegN_FFATable(idCountry, assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), orphanedChildren, model.getChildHeadedRb1(), model.getElderlyHeadedRb2(), model.getChronicallyIllRb3(), model.getFemaleHeadedRb4(), model.getCropFailureRb5(), model.getChildrenRecSuppFeedNRb6(), model.getWillingnessRb7(), entryBy, entryDate);

                    /**                     * Upload Syntax
                     * update method
                     */
                    sqlH.insertIntoUploadTable(assignFfa.updateIntoRegN_FFA_table());

                } else {

                    sqlH.insertIntoDDR_RegN_FFATable(idCountry, assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), orphanedChildren, model.getChildHeadedRb1(), model.getElderlyHeadedRb2(), model.getChronicallyIllRb3(), model.getFemaleHeadedRb4(), model.getCropFailureRb5(), model.getChildrenRecSuppFeedNRb6(), model.getWillingnessRb7(), entryBy, entryDate);

                    /**                     * Upload Syntax  (insert method)                     */
                    sqlH.insertIntoUploadTable(assignFfa.insertIntoRegN_FFA_table());
                }// end of else

                /**                  * get Group layR  list Code from Community Group                 */
                LayRCodes grpLayRListCode = sqlH.getLayRListFromCommunityGroup(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getProgram_code(), idGroup,strGroup);
                assignFfa.setGrpLayR1ListCode(grpLayRListCode.getLayR1Code());
                assignFfa.setGrpLayR2ListCode(grpLayRListCode.getLayR2Code());
                assignFfa.setGrpLayR3ListCode(grpLayRListCode.getLayR3Code());
                /**                 * check group                 */
                if (sqlH.ifExistsInRegNmemProgGroup(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getProgram_code(), assignMem.getService_code())) {
                    sqlH.editMemberIn_RegNmemProgGroup(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getProgram_code(), assignMem.getService_code(), idGroup, idActive, entryBy, entryDate, grpLayRListCode.getLayR1Code(), grpLayRListCode.getLayR2Code(), grpLayRListCode.getLayR3Code());

                    /**                     * Upload Syntax                     */
                    sqlH.insertIntoUploadTable(assignFfa.UpdateRegNMemProgGrp());


                } else {
                    sqlH.insertIntoUploadTable(assignFfa.insertInToRegNMemProgGrp());
                    /**                     * Upload Syntax                     */
                    sqlH.addRegNmemProgGroup(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getProgram_code(), assignMem.getService_code(), idGroup, strGroup, idActive, entryBy, entryDate, grpLayRListCode.getLayR1Code(), grpLayRListCode.getLayR2Code(), grpLayRListCode.getLayR3Code());
                }
                /**                     * Upload Syntax (Sp)                     */
                sqlH.insertIntoUploadTable(assignFfa.sqlSpRegNMemAwardProgCombN_Save());


                Toast.makeText(mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();


            }


        }

    }

    public void updateRegDate() {
        tvRegDate.setText(formatUSA.format(calendar.getTime()));
    }

    public void setRegDate() {
        new DatePickerDialog(mContext, d, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateRegDate();
        }
    };


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
        setUpSummaryButton();
        setUpSaveButton();
        setUpGoToAssgnButton();
    }

    /**
     * Icon setup::
     */

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpHomeButton() {
        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, btnHome);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpSummaryButton() {
        btnSummary.setText("");
        Drawable summeryImage = getResources().getDrawable(R.drawable.summession_b);
        btnSummary.setCompoundDrawablesRelativeWithIntrinsicBounds(summeryImage, null, null, null);
        setPaddingButton(mContext, summeryImage, btnSummary);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpSaveButton() {
        btnSave.setText("");
        Drawable saveImage = getResources().getDrawable(R.drawable.save_b);
        btnSave.setCompoundDrawablesRelativeWithIntrinsicBounds(saveImage, null, null, null);
        setPaddingButton(mContext, saveImage, btnSave);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpGoToAssgnButton() {
        btnBackToAssign.setText("");
        Drawable backImage = getResources().getDrawable(R.drawable.goto_back);
        btnBackToAssign.setCompoundDrawablesRelativeWithIntrinsicBounds(backImage, null, null, null);
        setPaddingButton(mContext, backImage, btnBackToAssign);

    }

    /***
     * Spinner load
     */
    private void loadGroupCategory(final String cCode, final String donorCode, final String awardCode, final String progCode) {

        SpinnerLoader.loadGroupCatLoader(mContext, sqlH, spGroupCategories, cCode, donorCode, awardCode, progCode, idGroupCat, strGroupCat);


        spGroupCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strGroupCat = ((SpinnerHelper) spGroupCategories.getSelectedItem()).getValue();
                idGroupCat = ((SpinnerHelper) spGroupCategories.getSelectedItem()).getId();

                if (idGroupCat.length() > 2)
                    loadGroup(cCode, donorCode, awardCode, progCode, idGroupCat);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    private void loadGroup(final String cCode, final String donorCode, final String awardCode, final String progCode, final String grpCateCode) {


        SpinnerLoader.loadGroupLoader(mContext, sqlH, spGroup, cCode, donorCode, awardCode, progCode, grpCateCode, idGroup, strGroup);

        spGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strGroup = ((SpinnerHelper) spGroup.getSelectedItem()).getValue();
                idGroup = ((SpinnerHelper) spGroup.getSelectedItem()).getId();

                if (idGroup.length() > 2)
                    loadActiveStatus();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    } // end Load Spinner

    /**
     * LOAD:: Active Status
     */
    private void loadActiveStatus() {

        ArrayAdapter<CharSequence> adptMartial = ArrayAdapter.createFromResource(this, R.array.arrActive, R.layout.spinner_layout);
        adptMartial.setDropDownViewResource(R.layout.spinner_layout);
        spActive.setAdapter(adptMartial);

        spActive.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String strActive = parent.getItemAtPosition(position).toString();

                if (strActive.equals("Yes"))
                    idActive = "Y";

                else
                    idActive = "N";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
