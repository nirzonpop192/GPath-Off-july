package com.siddiquinoor.restclient.activity.sub_activity.assign_program.agr;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.SummaryMenuActivity;
import com.siddiquinoor.restclient.activity.AssignActivity;
import com.siddiquinoor.restclient.data_model.AGR_DataModel;
import com.siddiquinoor.restclient.data_model.LayRCodes;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.data_model.adapters.AssignDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;
import com.siddiquinoor.restclient.views.notifications.AlertDialogManager;
import com.siddiquinoor.restclient.views.spinner.SpinnerLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AGR extends BaseActivity {


    public static final int ELDERLY = 01;
    public static final int AGRICULTER_AND_HEALTH = 02;
    public static final int PRODUCER_GROUP = 03;
    public static final int IRRIGATION_GROUP = 04;
    public static final int LIVESTOCK_GROUP = 05;
    public static final int MARKETING_GROUP = 06;
    public static final int WE_VSL = 07;

    public static final String YES = "Y";
    public static final String NO = "N";


    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat formatUSA = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
    Context mContext = AGR.this;
    private AssignDataModel assignDataModel;
    /**
     * replace assignDataModel
     */
    private AssignDataModel temAssignMemData;
    SQLServerSyntaxGenerator assign_agr;
    private String idGroup;
    private String idGroupCat;
    private String strGroupCat;
    private String strGroup;
    private String idActive;
    private AssignDataModel assignMem;

    public String getStrRegDate() {
        return strRegDate;
    }


    private String strRegDate;

    public void setStrRegDate(String strRegDate) {
        this.strRegDate = strRegDate;
    }


    TextView tvHouseholdName, tvMemberName, tvMemberId, tvCriteria, tvRegDate, tvPageTitle;
    EditText edtLandSize, edtGoat, edtChicken, edtPigions, edtOther;

    TextView labelLandSize, labelElderly, labelWillingness, labelWinterCultivation, labelVurnaableHousehold, labelPlantingVcCrop, labelDependonGunyu, labelDisable, labelWealthRank, labelMemExitingGroup;

    Switch switchElderly, switchWillingness, switchWinterCultivation, switchVurnaableHousehold, switchDependonGunyu, switchDisable;

    TextView lblGroupCategories, lblGroup, lblActive, lblOtherAgActivities, lblGoat, lblChicken, lblPigeons, lblOther;
    Spinner spGroupCategories, spGroup, spActive;
    LinearLayout agOtherLinearLayout;
    CheckBox cbINVC, cbNASFAM, cbCU, cbOTHER;

    String holderStrAward, holderStrProgram, holderStrVillage;

    Spinner spVcCrop, spWealthRank, spMemExitingGroup;
    /**
     * Button
     */

    Button btnSave, btnBackToAssign, btnHome, btnSummary;

    public static final String TAG = AGR.class.getSimpleName();
    AGR_DataModel data = new AGR_DataModel();// AGR DATA MODEL CALL
    private String idVcCrop;
    private String strVcCrop;

    private String strGoat;

    private String strChicken;

    private String strPegion;

    private String strOtherSp;


    String holderStrCriteria;
    SQLiteHandler sqlH;


    String elderley = "N";
    String willingness = "N";
    String winterCultivation = "N";
    String vurnableHousehold = "N";
    String plantingVcCrop = "N";
    String disable = "N";
    String dependOnGunnyu = "N";

    private ADNotificationManager erroDialog = new ADNotificationManager();


    public String getDependOnGunnyu() {
        return dependOnGunnyu;
    }

    public void setDependOnGunnyu(String dependOnGunnyu) {
        this.dependOnGunnyu = dependOnGunnyu;
    }


    public String getPlantingVcCrop() {
        return plantingVcCrop;
    }

    public void setPlantingVcCrop(String plantingVcCrop) {
        this.plantingVcCrop = plantingVcCrop;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

    public String getVurnableHousehold() {
        return vurnableHousehold;
    }

    public void setVurnableHousehold(String vurnableHousehold) {
        this.vurnableHousehold = vurnableHousehold;
    }

    public String getWinterCultivation() {
        return winterCultivation;
    }

    public void setWinterCultivation(String winterCultivation) {
        this.winterCultivation = winterCultivation;
    }

    public String getWillingness() {
        return willingness;
    }

    public void setWillingness(String willingness) {
        this.willingness = willingness;
    }

    public String getElderley() {
        return elderley;
    }

    public void setElderley(String elderley) {
        this.elderley = elderley;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
//
        setContentView(R.layout.activity_assign_for_malwai_agr);
        assignDataModel = new AssignDataModel();
        sqlH = new SQLiteHandler(mContext);
        viewReference();
        Intent intent = getIntent();
        getDataFromIntent(intent);
        visibility();
        dateProcessing();

        setTextForOwnLivestock();

        // setValueCheckbox();


        setTextToTextView();
        titlePage();
        loadVcCrop();
        if (sqlH.ifDataExistIn_RegN_AGR(assignDataModel.getCountryCode(), assignDataModel.getDistrictCode(), assignDataModel.getUpazillaCode(), assignDataModel.getUnitCode(),
                assignDataModel.getVillageCode(), assignDataModel.getHh_id(), assignDataModel.getMemId())) {
            setVisibilityDataExists();
        }

        tvRegDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRegDate();
            }
        });

        switchElderly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setElderley(toggleButtonCheck(b));
            }
        });

        switchWillingness.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setWillingness(toggleButtonCheck(b));
            }
        });
        switchWinterCultivation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setWinterCultivation(toggleButtonCheck(b));
            }
        });
        switchVurnaableHousehold.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setVurnableHousehold(toggleButtonCheck(b));
            }
        });

        switchDependonGunyu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setDependOnGunnyu(toggleButtonCheck(b));
            }
        });


        switchDisable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                setDisable(toggleButtonCheck(b));


            }
        });

        final AlertDialogManager alertDialogManager = new AlertDialogManager();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvRegDate.getText().toString().equals("")) {
                    erroDialog.showErrorDialog(mContext, "Enter Registration Date ");
                } else {
                    // TODO: 11/6/2016  add Registration Validation  in all assigne  
                    save();
                }

            }


        });
        btnBackToAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gotoAssignBeneficiaryPage();

            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity((Activity) AGR.this);
            }
        });

        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSummaryPage();
            }
        });
    }


    private void goToSummaryPage() {

        Intent iAssignSummary = new Intent(mContext, SummaryMenuActivity.class);
        iAssignSummary.putExtra(KEY.COUNTRY_ID, assignDataModel.getCountryCode());
        finish();

        startActivity(iAssignSummary);
    }


    /**
     * LOAD :: GroupCategory
     *
     * @param cCode     Adm Country Code
     * @param donorCode Adm Donor Code
     * @param awardCode Adm Award Code
     * @param progCode  Adm Program Code
     */
    private void loadGroupCategory(final String cCode, final String donorCode, final String awardCode,
                                   final String progCode) {


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

    /**
     * LOAD :: Group
     * This method lode the data from table to spinner
     *
     * @param cCode       Country Code
     * @param donorCode   Donor Code
     * @param awardCode   award Code
     * @param progCode    Program Code
     * @param grpCateCode Group Categories Code
     */
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
     * ** LOAD: Active Status
     */
    private void loadActiveStatus() {
        SpinnerLoader.loadActiveStatusLoader(mContext, spActive, idActive);
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


    private void viewReference() {
        tvHouseholdName = (TextView) findViewById(R.id.tv_as_agr_mlwai_hhName);
        tvMemberName = (TextView) findViewById(R.id.tv_as_agr_mlwai_MemberName);
        tvMemberId = (TextView) findViewById(R.id.tv_as_agr_mlwai_MemberID);
        tvCriteria = (TextView) findViewById(R.id.tv_as_agr_mlwai_asCriteria);
        tvRegDate = (TextView) findViewById(R.id.tv_as_agr_mlwai_regD);
        edtLandSize = (EditText) findViewById(R.id.edt_as_agr_mlwai_landSize);
        tvPageTitle = (TextView) findViewById(R.id.tv_ass_pageTitle);

        edtGoat = (EditText) findViewById(R.id.edt_as_agr_mlwai_O_L_s_goat);
        edtChicken = (EditText) findViewById(R.id.edt_as_agr_mlwai_O_L_s_chicken);
        edtPigions = (EditText) findViewById(R.id.edt_as_agr_mlwai_O_L_s_Pigeons);
        edtOther = (EditText) findViewById(R.id.edt_as_agr_mlwai_O_L_s_other_specify);

        switchElderly = (Switch) findViewById(R.id.tog_as_agr_mlwai_elderley);
        switchWillingness = (Switch) findViewById(R.id.tog_as_agr_mlwai_willings);
        switchWinterCultivation = (Switch) findViewById(R.id.tog_as_agr_mlwai_winterCultivation);
        switchVurnaableHousehold = (Switch) findViewById(R.id.tog_as_agr_mlwai_vurnerablehh);
        switchDisable = (Switch) findViewById(R.id.tog_as_agr_mlwai_disable);
        switchDependonGunyu = (Switch) findViewById(R.id.tog_as_agr_mlwai_dependonganyu);
        labelElderly = (TextView) findViewById(R.id.tv_label_as_agr_mlwai_elderley);
        labelWillingness = (TextView) findViewById(R.id.tv_label_as_agr_mlwai_willingness);
        labelWinterCultivation = (TextView) findViewById(R.id.tv_label_as_agr_mlwai_winterCultivation);
        labelVurnaableHousehold = (TextView) findViewById(R.id.tv_label_as_agr_mlwai_vulnerablehh);
        labelPlantingVcCrop = (TextView) findViewById(R.id.tv_label_as_agr_mlwai_plantingvcCrop);
        labelDisable = (TextView) findViewById(R.id.tv_label_as_agr_mlwai_disable);
        labelWealthRank = (TextView) findViewById(R.id.tv_label_as_agr_mlwai_wealthRank);
        labelMemExitingGroup = (TextView) findViewById(R.id.tv_label_as_agr_mlwai_exitingGroup);
        labelLandSize = (TextView) findViewById(R.id.tv_label_as_agr_mlwai_landSize);
        labelDependonGunyu = (TextView) findViewById(R.id.tv_label_as_agr_mlwai_dependonGanyu);
        spVcCrop = (Spinner) findViewById(R.id.spiner_as_agr_mlwai_vcCrop);
        spWealthRank = (Spinner) findViewById(R.id.sp_ass_agrWealthRank);
        spMemExitingGroup = (Spinner) findViewById(R.id.sp_ass_agrMemExitGroup);

        /**         * 4 button         */
        btnSave = (Button) findViewById(R.id.btn_assign_agr_save);
        btnBackToAssign = (Button) findViewById(R.id.btn_agr_goAssignePage);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnSummary = (Button) findViewById(R.id.btnRegisterFooter);

        lblGroupCategories = (TextView) findViewById(R.id.lbl_ass_agrGroupCategories);
        lblGroup = (TextView) findViewById(R.id.lbl_ass_agrGroup);
        lblActive = (TextView) findViewById(R.id.lbl_ass_agrActive);

        lblOtherAgActivities = (TextView) findViewById(R.id.lbl_ass_agrotherAgActivities);
        lblGoat = (TextView) findViewById(R.id.lbl_ass_agr_O_L_S_goat);
        lblChicken = (TextView) findViewById(R.id.lbl_ass_agr_O_L_S_Chicken);
        lblPigeons = (TextView) findViewById(R.id.lbl_ass_agr_O_L_S_Pigeons);
        lblOther = (TextView) findViewById(R.id.lbl_ass_agr_O_L_S_other_specify);
        spGroupCategories = (Spinner) findViewById(R.id.sp_ass_agrGroupCategories);
        spGroup = (Spinner) findViewById(R.id.sp_ass_agrGroup);
        spActive = (Spinner) findViewById(R.id.sp_ass_agrActive);
        agOtherLinearLayout = (LinearLayout) findViewById(R.id.other_ag_activity_ll);
        cbINVC = (CheckBox) findViewById(R.id.o_ag_ac_invc);
        cbNASFAM = (CheckBox) findViewById(R.id.o_ag_ac_nasfam);
        cbCU = (CheckBox) findViewById(R.id.o_ag_ac_cu);
        cbOTHER = (CheckBox) findViewById(R.id.o_ag_ac_other);


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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpHomeButton() {

        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, btnHome);

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        setUpHomeButton();
        setUpGoToAssgnButton();
        setUpSaveButton();
        setUpSummaryButton();
    }


    private void updateAddededData() {
        //SQLiteQuery query = new SQLiteQuery();

        if (sqlH.ifExistsInRegNAssProgSrv(assignDataModel)) {
            /**             * TODO: DO SOME CALCULATION IF NEEDED             */
            assignDataModel.setRegNDate(strRegDate);
            int id = sqlH.editMemberDataIn_RegNAsgProgSrv(assignDataModel);

            sqlH.insertIntoUploadTable(assign_agr.updateRegAssProgSrvForAssign());
        }

        if (sqlH.ifDataExistIn_RegN_AGR(assignDataModel.getCountryCode(), assignDataModel.getDistrictCode(), assignDataModel.getUpazillaCode(), assignDataModel.getUnitCode(), assignDataModel.getVillageCode(), assignDataModel.getHh_id(), assignDataModel.getMemId())) {

            //TODO:: UPDATE TABLE TO SQLITE
        }


    }


    private String calculateGRDDate(String cCode, String donorCode, String awardCode) {
        return sqlH.getAwardGraduation(cCode, donorCode, awardCode);


    }

    /**
     * save data
     */
    private void save() {

        data.setStrOtherAgActivitiesINVC(NO);
        data.setStrOtherAgActivitiesNASFAM(NO);
        data.setStrOtherAgActivitiesCU(NO);
        data.setStrOtherAgActivitiesOther(NO);

        if (cbINVC.isChecked()) {
            data.setStrOtherAgActivitiesINVC(YES);

        }

        if (cbNASFAM.isChecked()) {
            data.setStrOtherAgActivitiesNASFAM(YES);
        }

        if (cbCU.isChecked()) {
            data.setStrOtherAgActivitiesCU(YES);
        }
        if (cbOTHER.isChecked()) {
            data.setStrOtherAgActivitiesOther(YES);
        }


        setTextForOwnLivestock();

        if (sqlH.get_ProgramMultipleSrv(temAssignMemData.getDonor_code(), temAssignMemData.getAward_code(), temAssignMemData.getProgram_code()).equals("N")
                && sqlH.get_MemOthCriteriaLive(temAssignMemData.getCountryCode(), temAssignMemData.getDistrictCode(), temAssignMemData.getUpazillaCode(), temAssignMemData.getUnitCode(), temAssignMemData.getVillageCode(), temAssignMemData.getHh_id(), temAssignMemData.getMemId(), temAssignMemData.getDonor_code(), temAssignMemData.getAward_code(), temAssignMemData.getProgram_code(), temAssignMemData.getService_code())) {
            erroDialog.showErrorDialog(mContext, "Member remains active in other criteria. Save attempt denied.");
        } else {
            String regDate = tvRegDate.getText().toString();

            if (regDate.equals("")) {
                erroDialog.showErrorDialog(mContext, "Missing registration date. Save attempt denied.");
            } else if (idGroup == null || idGroup.equals("00")) {
                erroDialog.showErrorDialog(mContext, "Missing Group. Save attempt denied.");
            } else {
                String entryBy = getStaffID();
                assignDataModel.setEntryBy(entryBy);
                //TODO: Have TO Set GRD Date After Discussing with Anaconda, Nazmul Kalam,Haouwar pula
                assignDataModel.setGrdCode(sqlH.getGRDDefaultActiveReason(assignDataModel.getProgram_code(), assignDataModel.getService_code()));

                assignDataModel.setRegNDate(tvRegDate.getText().toString());

                String entryDate = "";
                try {
                    entryDate = getDateTime();


                } catch (ParseException e) {
                    e.printStackTrace();
                }

                assignDataModel.setEntryDate(entryDate);

                /**
                 *  for upload
                 */


                assignMem.setGrdCode(sqlH.getGRDDefaultActiveReason(assignMem.getProgram_code(), assignMem.getService_code()));
                assignMem.setGrdDate(calculateGRDDate(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code()));
                saveDataForSqlServer();

                assign_agr.setGRDDate(assignMem.getGrdDate());
                assignDataModel.setRegNDate(strRegDate);

                /**                 * check RegNAssProgSrv                 */
                if (sqlH.ifExistsInRegNAssProgSrv(assignDataModel)) {

                    sqlH.editMemberDataIn_RegNAsgProgSrv(assignDataModel);
                    /**                     * Upload Syntax ( update )                     */
                       sqlH.insertIntoUploadTable(assign_agr.updateRegAssProgSrvForAssign());

                } else {
                    sqlH.addMemberDataInto_RegNAsgProgSrv(assignDataModel);
                    /**                     * Upload Syntax ( insert )                     */
                    sqlH.insertIntoUploadTable(assign_agr.insertIntoRegAssProgSrv());
                    // sqlSpRegNAssignProgSrv_Save

                }

              //  sqlH.insertIntoUploadTable(assign_agr.sqlSpRegNAssignProgSrv_Save());

                int srvCode = Integer.parseInt(assignDataModel.getService_code());
                if (srvCode != WE_VSL) {
                    /**                 * check RegN_AGR                 */
                    if (sqlH.ifDataExistIn_RegN_AGR(assignDataModel.getCountryCode(), assignDataModel.getDistrictCode(), assignDataModel.getUpazillaCode(), assignDataModel.getUnitCode(), assignDataModel.getVillageCode(), assignDataModel.getHh_id(), assignDataModel.getMemId())) {
                        String land = edtLandSize.getText().toString();

                        switch (srvCode) {
                            case ELDERLY:
                                sqlH.edtAssignAgerIn_Elderley(assignDataModel, getElderley(), strRegDate);

                                break;
                            case PRODUCER_GROUP:
                                sqlH.edtAssignAgerIn_PG(assignDataModel, land, getWillingness(), getDependOnGunnyu(), strRegDate, data.getStrOtherAgActivitiesINVC(), data.getStrOtherAgActivitiesNASFAM(), data.getStrOtherAgActivitiesCU(), data.getStrOtherAgActivitiesOther(), data.getIntGoat(), data.getIntChicken(), data.getIntPegion(), data.getIntOther());

                                break;
                            case MARKETING_GROUP:
                                sqlH.edtAssignAgerIn_MG(assignDataModel, land, getWillingness(), idVcCrop, strRegDate);

                                break;
                            case IRRIGATION_GROUP:
                                sqlH.edtAssignAgerIn_IG(assignDataModel, land, getWillingness(), getWinterCultivation(), strRegDate);

                                break;
                            case LIVESTOCK_GROUP:
                                sqlH.edtAssignAgerIn_LG(assignDataModel, land, getWillingness(), getVurnableHousehold(), strRegDate);

                                break;

                        }//End Of Switch
                    } else {
                        data.setCountryCode(assignDataModel.getCountryCode());

                        data.setDistrictCode(assignDataModel.getDistrictCode());
                        data.setUpazillaCode(assignDataModel.getUpazillaCode());
                        data.setUnitCode(assignDataModel.getUnitCode());
                        data.setVillageCode(assignDataModel.getVillageCode());
                        data.setHhId(assignDataModel.getHh_id());
                        data.setHhMemId(assignDataModel.getMemId());
                        data.setRegnDate(assignDataModel.getRegNDate());
                        data.setElderleyYN(getElderley());
                        data.setLandSize(edtLandSize.getText().toString());
                        data.setDepenonGanyu(getDependOnGunnyu());
                        data.setWillingness(getWillingness());
                        data.setWinterCultivation(getWinterCultivation());
                        data.setVulnerableHh(getVurnableHousehold());
                        data.setPlantingVcrop(idVcCrop);
                        data.setEntryBy(getStaffID());
                        try {
                            data.setEntryDate(getDateTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        sqlH.insertDataInto_RegNAgrTable(data, data.getStrOtherAgActivitiesINVC(), data.getStrOtherAgActivitiesNASFAM(), data.getStrOtherAgActivitiesCU(), data.getStrOtherAgActivitiesOther(), data.getIntGoat(), data.getIntChicken(), data.getIntPegion(), data.getIntOther());
                        sqlH.insertIntoUploadTable(assign_agr.insertIntoRegN_Agr_Table());


                    }/**                 * end of else                             */

                } else {
                    if (sqlH.ifDataExistIn_RegN_WE(assignDataModel.getCountryCode(), assignDataModel.getDistrictCode(), assignDataModel.getUpazillaCode(), assignDataModel.getUnitCode(), assignDataModel.getVillageCode(), assignDataModel.getHh_id(), assignDataModel.getMemId())) {

                        sqlH.editInto_RegN_WETable(assignDataModel.getCountryCode(), assignDataModel.getDistrictCode(), assignDataModel.getUpazillaCode(), assignDataModel.getUnitCode(), assignDataModel.getVillageCode(), assignDataModel.getHh_id(), assignDataModel.getMemId(), regDate, wealthRankCode, memExitingGroupCode, entryBy, entryDate);
                    } else {
                        sqlH.insertInto_RegN_WETable(assignDataModel.getCountryCode(), assignDataModel.getDistrictCode(), assignDataModel.getUpazillaCode(), assignDataModel.getUnitCode(), assignDataModel.getVillageCode(), assignDataModel.getHh_id(), assignDataModel.getMemId(), regDate, wealthRankCode, memExitingGroupCode, entryBy, entryDate);
                    }

                    sqlH.insertIntoUploadTable(assign_agr.sqlSpRegN_WE_Save_MW());
                }
                /**                  * get Group layR  list Code from Community Group                 */
                LayRCodes grpLayRListCode = sqlH.getLayRListFromCommunityGroup(temAssignMemData.getCountryCode(), temAssignMemData.getDonor_code(), temAssignMemData.getAward_code(), temAssignMemData.getProgram_code(), idGroup, strGroup);
                assign_agr.setGrpLayR1ListCode(grpLayRListCode.getLayR1Code());
                assign_agr.setGrpLayR2ListCode(grpLayRListCode.getLayR2Code());
                assign_agr.setGrpLayR3ListCode(grpLayRListCode.getLayR3Code());

                /**                 * check RegNMemProgGroup                 */
                if (sqlH.ifExistsInRegNmemProgGroup(temAssignMemData.getCountryCode(), temAssignMemData.getDonor_code(), temAssignMemData.getAward_code(), temAssignMemData.getDistrictCode(), temAssignMemData.getUpazillaCode(), temAssignMemData.getUnitCode(), temAssignMemData.getVillageCode(), temAssignMemData.getHh_id(), temAssignMemData.getMemId(), temAssignMemData.getProgram_code(), temAssignMemData.getService_code())) {

                    sqlH.editMemberIn_RegNmemProgGroup(temAssignMemData.getCountryCode(), temAssignMemData.getDonor_code(), temAssignMemData.getAward_code(), temAssignMemData.getDistrictCode(), temAssignMemData.getUpazillaCode(), temAssignMemData.getUnitCode(), temAssignMemData.getVillageCode(), temAssignMemData.getHh_id(), temAssignMemData.getMemId(), temAssignMemData.getProgram_code(), temAssignMemData.getService_code(), idGroup, idActive, entryBy, entryDate, grpLayRListCode.getLayR1Code(), grpLayRListCode.getLayR2Code(), grpLayRListCode.getLayR3Code());

                    /**                     * Upload Syntax (update)                    */
                    sqlH.insertIntoUploadTable(assign_agr.UpdateRegNMemProgGrp());

                } else {
                    sqlH.addRegNmemProgGroup(temAssignMemData.getCountryCode(), temAssignMemData.getDonor_code(), temAssignMemData.getAward_code(), temAssignMemData.getDistrictCode(), temAssignMemData.getUpazillaCode(), temAssignMemData.getUnitCode(), temAssignMemData.getVillageCode(), temAssignMemData.getHh_id(), temAssignMemData.getMemId(), temAssignMemData.getProgram_code(), temAssignMemData.getService_code(), idGroup, strGroup, idActive, entryBy, entryDate, grpLayRListCode.getLayR1Code(), grpLayRListCode.getLayR2Code(), grpLayRListCode.getLayR3Code());

                    /**                     * Upload Syntax ( insert )                     */
                    sqlH.insertIntoUploadTable(assign_agr.insertInToRegNMemProgGrp());

                }
                /**                     * Upload Syntax (Sp)                     */
                sqlH.insertIntoUploadTable(assign_agr.sqlSpRegNMemAwardProgCombN_Save());


                Toast.makeText(mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();

            }


        }


    }

    private void saveDataForSqlServer() {
        assign_agr = new SQLServerSyntaxGenerator();
        assign_agr.setAdmCountryCode(assignDataModel.getCountryCode());
        assign_agr.setAdmDonorCode(assignDataModel.getDonor_code());
        assign_agr.setAdmAwardCode(assignDataModel.getAward_code());
        assign_agr.setLayR1ListCode(assignDataModel.getDistrictCode());
        assign_agr.setLayR2ListCode(assignDataModel.getUpazillaCode());
        assign_agr.setLayR3ListCode(assignDataModel.getUnitCode());
        assign_agr.setLayR4ListCode(assignDataModel.getVillageCode());
        assign_agr.setProgCode(assignDataModel.getProgram_code());
        assign_agr.setSrvCode(assignDataModel.getService_code());
        assign_agr.setHHID(assignDataModel.getHh_id());
        assign_agr.setMemID(assignDataModel.getMemId());
        assign_agr.setRegNDate(assignDataModel.getRegNDate());
        assign_agr.setGRDCode(assignDataModel.getGrdCode());
        assign_agr.setGRDDate(assignDataModel.getGrdDate());
        assign_agr.setElderlyYN(getElderley());
        assign_agr.setEntryBy(assignDataModel.getEntryBy());
        assign_agr.setEntryDate(assignDataModel.getEntryDate());
        assign_agr.setLandSize(edtLandSize.getText().toString());
        assign_agr.setDependOnGanyu(getDependOnGunnyu());
        assign_agr.setWillingness(getWillingness());
        assign_agr.setWinterCultivation(getWinterCultivation());
        assign_agr.setVulnerableHH(getVurnableHousehold());
        assign_agr.setPlantingValueChainCrop(idVcCrop);
        assign_agr.setAgoInvc(data.getStrOtherAgActivitiesINVC());
        assign_agr.setAgoNasfam(data.getStrOtherAgActivitiesNASFAM());
        assign_agr.setAgoCu(data.getStrOtherAgActivitiesCU());
        assign_agr.setAgoOther(data.getStrOtherAgActivitiesOther());
        assign_agr.setLsGoat(data.getIntGoat());
        assign_agr.setLsChicken(data.getIntChicken());
        assign_agr.setLsPigeon(data.getIntPegion());
        assign_agr.setLsOther(data.getIntOther());

        assign_agr.setActive(idActive);
        assign_agr.setGrpCode(idGroup);
        assign_agr.setWealthRanking(wealthRankCode);
        assign_agr.setMemberExtGroup(memExitingGroupCode);

    }


    /**
     * @param b boolean
     * @return String type Data 'N' or 'Y'
     * check switch value N/Y
     */
    public String toggleButtonCheck(boolean b) {

        String s = "";
        if (b) {
            //   Toast.makeText(mContext, "Yes", Toast.LENGTH_SHORT).show();
            return "Y";
        } else {
            //    Toast.makeText(mContext, "No", Toast.LENGTH_SHORT).show();
            return "N";
        }
    }


    /**
     * @param intent Intent
     *               <p>
     *               get Data from Intent because of further use
     */
    private void getDataFromIntent(Intent intent) {


        assignDataModel.setHh_name(intent.getStringExtra(KEY.ASSIGN_HOUSEHOLD_NAME));
        assignDataModel.setHh_mm_name(intent.getStringExtra(KEY.ASSIGN_HOUSEHOLD_NAME));
        assignDataModel.setAssign_criteria(intent.getStringExtra(KEY.ASSIGN_CRITERIA_STRING));

        assignDataModel.setHh_id(intent.getStringExtra(KEY.ASSIGN_HOUSEHOLD_ID));
        assignDataModel.setMemId(intent.getStringExtra(KEY.ASSIGN_HOUSEHOLD_MEMBER_ID));
        assignDataModel.setC_code(intent.getStringExtra(KEY.ASSIGN_COUNTRY_CODE));
        assignDataModel.setDistrictCode(intent.getStringExtra(KEY.ASSIGN_DISTRICT_CODE));
        assignDataModel.setUpazillaCode(intent.getStringExtra(KEY.ASSIGN_UPOZILLA_CODE));
        assignDataModel.setUnitCode(intent.getStringExtra(KEY.ASSIGN_UNIT_CODE));
        assignDataModel.setVillageCode(intent.getStringExtra(KEY.ASSIGN_VILLAGE_CODE));
        assignDataModel.setHh_id(intent.getStringExtra(KEY.ASSIGN_HOUSEHOLD_ID));
        assignDataModel.setMemId(intent.getStringExtra(KEY.ASSIGN_HOUSEHOLD_MEMBER_ID));


        assignDataModel.setAward_code(intent.getStringExtra(KEY.ASSIGN_AWARD_CODE));
        assignDataModel.setProgram_code(intent.getStringExtra(KEY.ASSIGN_PROGRAM_CODE));
        assignDataModel.setService_code(intent.getStringExtra(KEY.ASSIGN_SERVICE_CODE));


        assignDataModel.setDonor_code(intent.getStringExtra(KEY.ASSIGN_DONER_CODE));

        holderStrAward = intent.getStringExtra(KEY.ASSIGN_AWARD_STRING);
        holderStrProgram = intent.getStringExtra(KEY.ASSIGN_PROGRAM_STRING);
        holderStrCriteria = intent.getStringExtra(KEY.ASSIGN_CRITERIA_STRING);
        holderStrVillage = intent.getStringExtra(KEY.ASSIGN_VILLAGE_STRING);


        assignDataModel.setRegNDate(sqlH.getMemberRegNDate(assignDataModel));

        temAssignMemData = intent.getExtras().getParcelable(KEY.ASSIGN_DATA_OBJECT_KEY);

        Intent mIntent = getIntent();
        assignMem = mIntent.getExtras().getParcelable(KEY.ASSIGN_DATA_OBJECT_KEY);
        memberId15D = mIntent.getExtras().getString(KEY.MEMBER_ID);

    }


    private void dateProcessing() {

        String tem = sqlH.getRegDate_RegNAsgProgSrv(assignDataModel);
        if (tem != null) {
            if (tem.equals("null")) {
                tem = "";
            }

            tvRegDate.setText(tem);

        }
    }

    public void updateRegDate() {
        setStrRegDate(format.format(calendar.getTime()));
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

    public void setTextToTextView() {
        tvHouseholdName.setText(assignDataModel.getHh_name());

        tvMemberName.setText(temAssignMemData.getHh_mm_name());
        tvMemberId.setText(temAssignMemData.getNewId());
        tvCriteria.setText(assignDataModel.getAssign_criteria());
        //  tvRegDate.setText(assignDataModel.getRegNDate());
    }

    /**
     * set page title
     */
    public void titlePage() {


        int srvCode = -1;
        srvCode = Integer.parseInt(assignDataModel.getService_code());

        if (srvCode != -1) {

            switch (srvCode) {
                case ELDERLY:
                    tvPageTitle.setText("Elderley");
                    break;
                case AGRICULTER_AND_HEALTH:
                    tvPageTitle.setText("Agriculture And Health");
                    break;
                case PRODUCER_GROUP:
                    tvPageTitle.setText("Producer Group");
                    break;
                case IRRIGATION_GROUP:
                    tvPageTitle.setText("Irrigation Group");
                    break;
                case LIVESTOCK_GROUP:
                    tvPageTitle.setText("Livestock Group");
                    break;
                case MARKETING_GROUP:
                    tvPageTitle.setText("Marketing Group");
                case WE_VSL:
                    tvPageTitle.setText("WE/VSL");

            }
        }
    }

    public void visibility() {
        int srvCode = Integer.parseInt(assignDataModel.getService_code());
        labelLandSize.setVisibility(View.GONE);
        labelWillingness.setVisibility(View.GONE);
        labelWinterCultivation.setVisibility(View.GONE);
        labelPlantingVcCrop.setVisibility(View.GONE);
        labelDependonGunyu.setVisibility(View.GONE);
        labelDisable.setVisibility(View.GONE);
        labelWealthRank.setVisibility(View.GONE);
        labelMemExitingGroup.setVisibility(View.GONE);
        labelVurnaableHousehold.setVisibility(View.GONE);
        labelElderly.setVisibility(View.GONE);
        lblGoat.setVisibility(View.GONE);
        lblChicken.setVisibility(View.GONE);
        lblOther.setVisibility(View.GONE);
        lblPigeons.setVisibility(View.GONE);
        lblOtherAgActivities.setVisibility(View.GONE);
        edtGoat.setVisibility(View.GONE);
        edtChicken.setVisibility(View.GONE);
        edtPigions.setVisibility(View.GONE);
        edtOther.setVisibility(View.GONE);
        switchWillingness.setVisibility(View.GONE);
        switchWinterCultivation.setVisibility(View.GONE);
        switchDisable.setVisibility(View.GONE);
        spVcCrop.setVisibility(View.GONE);
        spWealthRank.setVisibility(View.GONE);
        spMemExitingGroup.setVisibility(View.GONE);
        switchVurnaableHousehold.setVisibility(View.GONE);
        switchDependonGunyu.setVisibility(View.GONE);
        switchElderly.setVisibility(View.GONE);
        edtLandSize.setVisibility(View.GONE);
        lblGroupCategories.setVisibility(View.GONE);
        lblGroup.setVisibility(View.GONE);
        lblActive.setVisibility(View.GONE);
        spGroupCategories.setVisibility(View.GONE);
        spGroup.setVisibility(View.GONE);
        spActive.setVisibility(View.GONE);
        agOtherLinearLayout.setVisibility(View.GONE);
        cbINVC.setVisibility(View.GONE);
        cbNASFAM.setVisibility(View.GONE);
        cbCU.setVisibility(View.GONE);
        cbOTHER.setVisibility(View.GONE);


        switch (srvCode) {
            case ELDERLY:
                labelElderly.setVisibility(View.VISIBLE);
                switchElderly.setVisibility(View.VISIBLE);
                activeGroupSpinner();

                break;
            case AGRICULTER_AND_HEALTH:
                Toast.makeText(mContext, "Nothing to print", Toast.LENGTH_SHORT).show();
                break;
            case PRODUCER_GROUP:
                labelLandSize.setVisibility(View.VISIBLE);
                labelDependonGunyu.setVisibility(View.VISIBLE);
                labelWillingness.setVisibility(View.VISIBLE);

                edtLandSize.setVisibility(View.VISIBLE);
                switchWillingness.setVisibility(View.VISIBLE);
                switchDependonGunyu.setVisibility(View.VISIBLE);
                activeGroupSpinner();
                visableForPG();

                break;
            case IRRIGATION_GROUP:
                labelLandSize.setVisibility(View.VISIBLE);
                edtLandSize.setVisibility(View.VISIBLE);
                labelWillingness.setVisibility(View.VISIBLE);
                switchWillingness.setVisibility(View.VISIBLE);
                labelWinterCultivation.setVisibility(View.VISIBLE);
                switchWinterCultivation.setVisibility(View.VISIBLE);

                activeGroupSpinner();
                break;
            case LIVESTOCK_GROUP:
                labelLandSize.setVisibility(View.VISIBLE);
                edtLandSize.setVisibility(View.VISIBLE);
                labelWillingness.setVisibility(View.VISIBLE);
                switchWillingness.setVisibility(View.VISIBLE);
                labelVurnaableHousehold.setVisibility(View.VISIBLE);
                switchVurnaableHousehold.setVisibility(View.VISIBLE);
                activeGroupSpinner();
                break;
            case MARKETING_GROUP:
                labelLandSize.setVisibility(View.VISIBLE);
                edtLandSize.setVisibility(View.VISIBLE);
                labelWillingness.setVisibility(View.VISIBLE);
                switchWillingness.setVisibility(View.VISIBLE);
                labelPlantingVcCrop.setVisibility(View.VISIBLE);
                spVcCrop.setVisibility(View.VISIBLE);
                activeGroupSpinner();

                break;

            case WE_VSL:
                labelWealthRank.setVisibility(View.VISIBLE);
                labelMemExitingGroup.setVisibility(View.VISIBLE);
                spWealthRank.setVisibility(View.VISIBLE);
                spMemExitingGroup.setVisibility(View.VISIBLE);
                loadWealthRank();
                loadMemExitingGroup();
                activeGroupSpinner();
                break;

        }
    }

    private void activeGroupSpinner() {
        lblGroupCategories.setVisibility(View.VISIBLE);
        lblGroup.setVisibility(View.VISIBLE);
        lblActive.setVisibility(View.VISIBLE);
        spGroupCategories.setVisibility(View.VISIBLE);
        spGroup.setVisibility(View.VISIBLE);
        spActive.setVisibility(View.VISIBLE);

        idGroupCat = assignMem.getGroupCatCode();
        strGroupCat = assignMem.getGroupCatName();
        idGroup = assignMem.getGroupCode();
        strGroup = assignMem.getGroupName();
        idActive = assignMem.getActiveCode();

        loadGroupCategory(temAssignMemData.getCountryCode(), temAssignMemData.getDonor_code(), temAssignMemData.getAward_code(), temAssignMemData.getProgram_code());


    }

    /**
     * todo restore group code
     */
    private void setVisibilityDataExists() {

        int srvCode = Integer.parseInt(assignDataModel.getService_code());
        AGR_DataModel agr_dataModel = null;
        if (srvCode != WE_VSL) {
            agr_dataModel = sqlH.checkAssignCriteriaInAGR_TableForMalwai(assignDataModel.getCountryCode(), assignDataModel.getDistrictCode(),
                    assignDataModel.getUpazillaCode(), assignDataModel.getUnitCode(), assignDataModel.getVillageCode(),
                    assignDataModel.getHh_id(), assignDataModel.getMemId(), false);
        } else {
            agr_dataModel = sqlH.checkAssignCriteriaInRegN_WE(assignDataModel.getCountryCode(), assignDataModel.getDistrictCode(),
                    assignDataModel.getUpazillaCode(), assignDataModel.getUnitCode(), assignDataModel.getVillageCode(),
                    assignDataModel.getHh_id(), assignDataModel.getMemId());

        }


        switch (srvCode) {
            case ELDERLY:
                if (agr_dataModel != null) {
                    Log.d(TAG, agr_dataModel.getElderleyYN());
                    if (agr_dataModel.getElderleyYN().equals("Y")) {
                        switchElderly.setChecked(true);
                    }
                    if (agr_dataModel.getRegnDate() != null) {
                        tvRegDate.setText(agr_dataModel.getRegnDate());
                    }
                }

                break;
            case AGRICULTER_AND_HEALTH:
                break;
            case PRODUCER_GROUP:
                if (agr_dataModel.getWillingness().equals("Y")) {
                    switchWillingness.setChecked(true);
                }
                if (agr_dataModel.getDepenonGanyu().equals("Y")) {
                    switchDependonGunyu.setChecked(true);
                }
                if (!agr_dataModel.getLandSize().equals("") || agr_dataModel.getLandSize() != null) {
                    edtLandSize.setText(agr_dataModel.getLandSize());
                }
                if (agr_dataModel.getRegnDate() != null) {
                    tvRegDate.setText(agr_dataModel.getRegnDate());
                }

                if (agr_dataModel.getAgInvc().equals("Y")) {
                    cbINVC.setChecked(true);

                }


                if (agr_dataModel.getAgNasfam().equals("Y")) {
                    cbNASFAM.setChecked(true);

                }


                if (agr_dataModel.getAgCu().equals("Y")) {
                    cbCU.setChecked(true);

                }


                if (agr_dataModel.getAgOrther().equals("Y")) {
                    cbOTHER.setChecked(true);

                }

                edtGoat.setText(String.valueOf(agr_dataModel.getIntGoat()));
                edtChicken.setText(String.valueOf(agr_dataModel.getIntChicken()));
                edtPigions.setText(String.valueOf(agr_dataModel.getIntPegion()));
                edtOther.setText(String.valueOf(agr_dataModel.getIntOther()));


                break;
            case IRRIGATION_GROUP:
                if (agr_dataModel.getWillingness().equals("Y")) {
                    switchWillingness.setChecked(true);
                }
                if (agr_dataModel.getWinterCultivation().equals("Y")) {
                    switchWinterCultivation.setChecked(true);
                }
                if (!agr_dataModel.getLandSize().equals("") || agr_dataModel.getLandSize() != null) {
                    edtLandSize.setText(agr_dataModel.getLandSize());
                }
                if (agr_dataModel.getRegnDate() != null) {
                    tvRegDate.setText(agr_dataModel.getRegnDate());
                }
                break;
            case LIVESTOCK_GROUP:
                if (agr_dataModel.getWillingness().equals("Y")) {
                    switchWillingness.setChecked(true);
                }
                if (agr_dataModel.getVulnerableHh().equals("Y")) {
                    switchVurnaableHousehold.setChecked(true);
                }
                if (!agr_dataModel.getLandSize().equals("") || agr_dataModel.getLandSize() != null) {
                    edtLandSize.setText(agr_dataModel.getLandSize());
                }
                if (agr_dataModel.getRegnDate() != null) {
                    tvRegDate.setText(agr_dataModel.getRegnDate());
                }
                break;
            case MARKETING_GROUP:
                if (agr_dataModel.getWillingness().equals("Y")) {
                    switchWillingness.setChecked(true);
                }
                if (!agr_dataModel.getLandSize().equals("") || agr_dataModel.getLandSize() != null) {
                    edtLandSize.setText(agr_dataModel.getLandSize());
                }
                if (agr_dataModel.getRegnDate() != null) {
                    tvRegDate.setText(agr_dataModel.getRegnDate());
                }
                if (agr_dataModel.getPlantingVcrop() != null) {
                    idVcCrop = agr_dataModel.getPlantingVcrop();
                    strVcCrop = agr_dataModel.getStrPlantingVcrop();
                    loadVcCrop();
                }
                break;
            case WE_VSL:
                // // TODO: 2/20/2017   load  wealthRankCode code  and memExitingGroupCode

                if (agr_dataModel.getWealthRank() != null) {
                    wealthRankCode = agr_dataModel.getWealthRank();
                    loadWealthRank();
                }

                if (agr_dataModel.getMemExitGrp() != null) {
                    memExitingGroupCode = agr_dataModel.getMemExitGrp();
                    loadMemExitingGroup();
                }

                if (agr_dataModel.getRegNDate() != null) {
                    if (agr_dataModel.getRegNDate().length() > 0)
                        tvRegDate.setText(agr_dataModel.getRegNDate());
                    else tvRegDate.setHint("Date");
                }
                break;
        }
    }




    String wealthRankCode;

    private void loadWealthRank() {

        SpinnerLoader.loadActiveStatusLoader(mContext, spWealthRank, wealthRankCode);


        spWealthRank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strActive = parent.getItemAtPosition(position).toString();
                if (strActive.equals("Yes"))
                    wealthRankCode = "Y";
                else
                    wealthRankCode = "N";
                /** no dependence */

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    String memExitingGroupCode;

    private void loadMemExitingGroup() {

        SpinnerLoader.loadActiveStatusLoader(mContext, spMemExitingGroup, memExitingGroupCode);


        spMemExitingGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strActive = parent.getItemAtPosition(position).toString();
                if (strActive.equals("Yes"))
                    memExitingGroupCode = "Y";
                else
                    memExitingGroupCode = "N";
                /** no dependence */

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * LOAD :: VcCrop
     */
    private void loadVcCrop() {

        int position = 0;

        String criteria = " WHERE " + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + assignDataModel.getCountryCode() + "'"
                + " AND " + SQLiteHandler.PROG_CODE_COL + " = '" + assignDataModel.getProgram_code() + "' "
                + " AND " + SQLiteHandler.SRV_CODE_COL + " = '" + assignDataModel.getService_code() + "' ";


        List<SpinnerHelper> listOfVcCrop = sqlH.getListAndID(SQLiteHandler.LUP_SRV_OPTION_LIST_TABLE, criteria, null, false);


        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listOfVcCrop);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spVcCrop.setAdapter(dataAdapter);


        if (idVcCrop != null) {
            for (int i = 0; i < spVcCrop.getCount(); i++) {
                String district = spVcCrop.getItemAtPosition(i).toString();
                if (district.equals(strVcCrop)) {
                    position = i;
                }
            }
            spVcCrop.setSelection(position);
        }


        spVcCrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strVcCrop = ((SpinnerHelper) spVcCrop.getSelectedItem()).getValue();
                idVcCrop = ((SpinnerHelper) spVcCrop.getSelectedItem()).getId();

                /** no dependence */

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    private void gotoAssignBeneficiaryPage() {


        Intent iAssign = new Intent(mContext, AssignActivity.class);
        finish();

        iAssign.putExtra(KEY.COUNTRY_ID, temAssignMemData.getCountryCode());
        iAssign.putExtra(AssignActivity.SUB_ASSIGN_DIR, true);
        iAssign.putExtra(AssignActivity.ASSIGN_AWARD_CODE, temAssignMemData.getAward_code());
        iAssign.putExtra(AssignActivity.ASSIGN_AWARD_STR, temAssignMemData.getTemAwardString());
        iAssign.putExtra(AssignActivity.ASSIGN_PROGRAM_CODE, temAssignMemData.getProgram_code());
        iAssign.putExtra(AssignActivity.ASSIGN_PROGRAM_STR, temAssignMemData.getTemProgramString());
        iAssign.putExtra(AssignActivity.ASSIGN_DONOR_CODE, temAssignMemData.getDonor_code());
        iAssign.putExtra(AssignActivity.ASSIGN_CRITERIA_CODE, temAssignMemData.getService_code());
        iAssign.putExtra(AssignActivity.ASSIGN_CRITERIA_STR, temAssignMemData.getTemCriteriaString());
        iAssign.putExtra(KEY.MEMBER_ID, memberId15D);


        startActivity(iAssign);

    }

    private String memberId15D;


    private void visableForPG() {
        lblGoat.setVisibility(View.VISIBLE);
        lblChicken.setVisibility(View.VISIBLE);
        lblOther.setVisibility(View.VISIBLE);
        lblPigeons.setVisibility(View.VISIBLE);
        lblOtherAgActivities.setVisibility(View.VISIBLE);
        edtGoat.setVisibility(View.VISIBLE);
        edtChicken.setVisibility(View.VISIBLE);
        edtPigions.setVisibility(View.VISIBLE);
        edtOther.setVisibility(View.VISIBLE);
        agOtherLinearLayout.setVisibility(View.VISIBLE);
        cbINVC.setVisibility(View.VISIBLE);
        cbNASFAM.setVisibility(View.VISIBLE);
        cbCU.setVisibility(View.VISIBLE);
        cbOTHER.setVisibility(View.VISIBLE);

    }


    private void setTextForOwnLivestock() {
        strGoat = edtGoat.getText().toString();
        if (strGoat.length() > 0) {
            data.setIntGoat(Integer.parseInt(strGoat));

        }
        strChicken = edtChicken.getText().toString();
        if (strChicken.length() > 0) {
            data.setIntChicken(Integer.parseInt(strChicken));
        }

        strPegion = edtPigions.getText().toString();
        if (strPegion.length() > 0) {
            data.setIntPegion(Integer.parseInt(strPegion));

        }
        strOtherSp = edtOther.getText().toString();
        if (strOtherSp.length() > 0) {
            //agr_dataModel.setIntOther(0);
            data.setIntOther(Integer.parseInt(strOtherSp));
        }
    }

}
