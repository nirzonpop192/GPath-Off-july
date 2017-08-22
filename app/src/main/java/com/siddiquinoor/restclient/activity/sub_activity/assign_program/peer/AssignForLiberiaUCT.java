package com.siddiquinoor.restclient.activity.sub_activity.assign_program.peer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.SummaryMenuActivity;
import com.siddiquinoor.restclient.activity.AssignActivity;
import com.siddiquinoor.restclient.activity.DistributionActivity;
import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.activity.RegisterLiberia;
import com.siddiquinoor.restclient.activity.ServiceActivity;
import com.siddiquinoor.restclient.controller.validation.AssigneValidation;
import com.siddiquinoor.restclient.data_model.CTDataModel;
import com.siddiquinoor.restclient.data_model.RegNAssgProgSrv;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;

import com.siddiquinoor.restclient.utils.KEY;

import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.data_model.adapters.AssignDataModel;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Refat Hasan, Faisal Mohammad on 1/30/2016.
 *
 * @version 2.3.38
 */
public class AssignForLiberiaUCT extends BaseActivity implements View.OnClickListener {

    AlertDialog goToDialog;
    Intent intent;
    private String idCountry;

    public static final String YES = "Y";
    public static final String NO = "N";
   /* public static final String EVD_AFFECTED_HOUSEHOLDS_C1 = "EVD-affected Households-C1";
    public static final String CFED_HEADED_HOUSEHOLD_C2 = "CFED Headed Household-C2";
    public static final String PLW_CU2_C3 = "PLW/CU2-C3";*/
    //TextViews
    TextView tViewHouseHoldName, tViewHouseHoldId, tViewMemberName, tViewMemberId, tViewCriteria, tViewDate;

    //spinner
    //Spinner spinnerCriteriaDetails;
    //Button
    Button btnAssign, btnSave, btnHome, btnSummary;

    String strRegDate;

    String holderStrAward, holderStrProgram, holderStrCriteria, holderStrVillage;

    private AssignDataModel assignMemData;

    RadioGroup choiceGroup;
    RadioButton c11;
    RadioButton c21;
    RadioButton c31, c32, c33, c34, c35, c36, c37, c38;

    //    String rb11, rb21, rb31, rb32, rb33, rb34, rb35, rb36, rb37, rb38;
    boolean recordExistenceInCT = false;
   // private AssigneValidation validation;
    private CTDataModel ctDataModel;


    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat formatUSA = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
    private Context context;

    Context mContext;
    SQLiteHandler sqlH;
    private ADNotificationManager dialog;
    private String memberId15D;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_assign_for_liberia_uct);
        fieldIntialization();


        viewReference();


        Intent intent = getIntent();
        // get all data from intent
        getDataFromIntent(intent);

        idCountry = assignMemData.getCountryCode();
        if (sqlH.ifExistsInRegNAssProgSrv(assignMemData)){
        String tem = sqlH.getRegDate_RegNAsgProgSrv(assignMemData);
        Log.d("UCT","In RegAssProgSrv Reg date:"+tem);
            tViewDate.setText(tem);
        }
        else {
            tViewDate.setText("");
        }
        setTextToTextView();
        // chek the Recode Exits
        recordExistenceInCT = sqlH.ifDataExiteInRegNCT(assignMemData.getCountryCode(), assignMemData.getDistrictCode(), assignMemData.getUpazillaCode(), assignMemData.getUnitCode(), assignMemData.getVillageCode(), assignMemData.getHh_id(), assignMemData.getMemId());
        radioButtonVisibilityController();
        setViewsListener();


    }

    private void fieldIntialization() {
        mContext = AssignForLiberiaUCT.this;
        sqlH = new SQLiteHandler(this);
        ctDataModel = new CTDataModel();
        dialog = new ADNotificationManager();
        assignMemData = new AssignDataModel();
    }

    // set the listener
    private void setViewsListener() {
        tViewDate.setOnClickListener(this);
        btnAssign.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnHome.setOnClickListener(this);
        btnSummary.setOnClickListener(this);
    }

    private void radioButtonVisibilityController() {
        c11.setVisibility(View.GONE);
        c21.setVisibility(View.GONE);
        c31.setVisibility(View.GONE);
        c32.setVisibility(View.GONE);
        c33.setVisibility(View.GONE);
        c34.setVisibility(View.GONE);
        c35.setVisibility(View.GONE);
        c36.setVisibility(View.GONE);
        c37.setVisibility(View.GONE);
        c38.setVisibility(View.GONE);
        int che = sqlH.checkAssignCriteriaInCT_TableForLiberia(assignMemData.getCountryCode(), assignMemData.getDistrictCode(), assignMemData.getUpazillaCode(), assignMemData.getUnitCode(), assignMemData.getVillageCode(), assignMemData.getHh_id(), assignMemData.getMemId());
        switch (tViewCriteria.getText().toString()) {

            case KEY.EVD_AFFECTED_HOUSEHOLDS_C1:
                c11.setVisibility(View.VISIBLE);
                if (recordExistenceInCT) {

                    if (che == 1) {
                        c11.setChecked(true);
                    }
                }


                break;
            case KEY.CFED_HEADED_HOUSEHOLD_C2:
                c21.setVisibility(View.VISIBLE);
                if (recordExistenceInCT) {

                    if (che == 2) {
                        c21.setChecked(true);
                    }
                }

                break;
            case KEY.PLW_CU2_C3:
                c31.setVisibility(View.VISIBLE);
                c32.setVisibility(View.VISIBLE);
                c33.setVisibility(View.VISIBLE);
                c34.setVisibility(View.VISIBLE);
                c35.setVisibility(View.VISIBLE);
                c36.setVisibility(View.VISIBLE);
                c37.setVisibility(View.VISIBLE);
                c38.setVisibility(View.VISIBLE);

                if (recordExistenceInCT) {

                    switch (che) {
                        case 3:
                            c31.setChecked(true);
                            break;
                        case 4:
                            c32.setChecked(true);

                            break;
                        case 5:
                            c33.setChecked(true);

                            break;
                        case 6:
                            c34.setChecked(true);

                            break;
                        case 7:
                            c35.setChecked(true);

                            break;
                        case 8:
                            c36.setChecked(true);

                            break;
                        case 9:
                            c37.setChecked(true);

                            break;
                        case 10:
                            c38.setChecked(true);
                            break;

                    }
                }
                break;

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_assign_liberia_save_uct:
                saveData();
                break;
            case R.id.btn_liberia_goAssignePage_uct:

                gotoAssignBeneficiaryPage();
                break;
            case R.id.txt_assign_lib_RegDate_uct:
                setRegDate();
                break;
            case R.id.btnHomeFooter:

                //goToMainActivity((Activity)mContext);
                goToAlert();
                break;
            case R.id.btnRegisterFooter:

                goToSummaryActivity((Activity)mContext,assignMemData.getCountryCode());
                break;


        }

    }

    private void saveData() {


        checkCiteriaDetails();
        String regDate = getStrRegDate();
        AssigneValidation validation = new AssigneValidation();
        // todo :  set validation
        RegNAssgProgSrv data =new RegNAssgProgSrv();
        data=setterRegNAssProgSrvDataModel();
        int validitionCode = validation.assigne_PEER_awardValidation(regDate, mContext, data);
        switch (validitionCode) {
            case AssigneValidation.INVALID_DATE_FORMAT:
                dialog.showErrorDialog(mContext, "Invalid Date Format ");
                break;
            case AssigneValidation.INVALID_DATE:
                dialog.showErrorDialog(mContext, "Invalid Date Range ");
                break;
            case AssigneValidation.MEM_ALREADY_ASSIGNED_D_S:
                dialog.showErrorDialog(mContext, "Invalid Attem to Save .This member already Assigned in Orther Criteria");
                break;
            case AssigneValidation.VALIDATION_OK:
                SQLServerSyntaxGenerator assignMemToUCT_Criretia=new SQLServerSyntaxGenerator();

                //insert for server
                assignMemToUCT_Criretia = setterForUploadTableSyntaxt(regDate);
                if (sqlH.ifExistsInRegNAssProgSrv(assignMemData)) {
                    // this member exits
                    // if the grd code is null then it will update the pervious values

                    //update for server /
                    sqlH.insertIntoUploadTable(assignMemToUCT_Criretia.updateRegAssProgSrvForAssign());

                    // set the grd code here than update the regAss program Srv
                    int id = sqlH.editMemberDataIn_RegNAsgProgSrv(assignMemData);
                  //  Toast.makeText(mContext, "The data  " + id + " is updated in RegAssign program table ", Toast.LENGTH_SHORT).show();
                    //  int id=sqlH.updateRegistrationRecord();

                } else {
                    // insert member as new entry
                    assignMemData.setRegNDate(regDate);
                    long id = sqlH.addMemberDataInto_RegNAsgProgSrv(assignMemData);
                    sqlH.insertIntoUploadTable(assignMemToUCT_Criretia.insertIntoRegAssProgSrv());
                   // Toast.makeText(mContext, "id  " + id + " is insert in RegAssign program table ", Toast.LENGTH_SHORT).show();
                }
                if (sqlH.ifDataExiteInRegNCT(assignMemData.getCountryCode(), assignMemData.getDistrictCode(), assignMemData.getUpazillaCode(), assignMemData.getUnitCode(), assignMemData.getVillageCode(), assignMemData.getHh_id(), assignMemData.getMemId())) {
                    // todo: update the the assigne
                  //  dialog.showInfromDialog(mContext, "ALREADY ISERTED", "DATA IS ALREADY INSERTED");
                } else {

                    sqlH.addMemIntoCT_Table(ctDataModel);
                    //Toast.makeText(getBaseContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
                    sqlH.insertIntoUploadTable(assignMemToUCT_Criretia.insertIntoRegN_CT());
                }// end of inner else

                break;
        }// end of switch
        Toast.makeText(mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();

    }

    private RegNAssgProgSrv setterRegNAssProgSrvDataModel() {
        RegNAssgProgSrv tem = new RegNAssgProgSrv();
        tem.setCountryCode(assignMemData.getCountryCode());
        tem.setDistrictCode(assignMemData.getDistrictCode());
        tem.setUpazillaCode(assignMemData.getUpazillaCode());
        tem.setUnitCode(assignMemData.getUnitCode());
        tem.setVillageCode(assignMemData.getVillageCode());
        tem.setDonorCode(assignMemData.getDonor_code());
        tem.setAwardCode(assignMemData.getAward_code());
        tem.setHhId(assignMemData.getHh_id());
        tem.setMemberId(assignMemData.getMemId());
        tem.setProgramCode(assignMemData.getProgram_code());
        tem.setServiceCode(assignMemData.getService_code());
        return tem;
    }

    private SQLServerSyntaxGenerator setterForUploadTableSyntaxt(String registrationDate) {
        SQLServerSyntaxGenerator data = new SQLServerSyntaxGenerator();
        data.setAdmCountryCode(assignMemData.getCountryCode());
        data.setLayR1ListCode(assignMemData.getDistrictCode());
        data.setLayR2ListCode(assignMemData.getUpazillaCode());
        data.setLayR3ListCode(assignMemData.getUnitCode());
        data.setLayR4ListCode(assignMemData.getVillageCode());
        data.setAdmDonorCode(assignMemData.getDonor_code());
        data.setAdmAwardCode(assignMemData.getAward_code());
        data.setHHID(assignMemData.getHh_id());
        data.setMemID(assignMemData.getMemId());
        data.setProgCode(assignMemData.getProgram_code());
        data.setSrvCode(assignMemData.getService_code());
        data.setRegNDate(registrationDate);
        data.setEntryBy(assignMemData.getEntryBy());
        data.setEntryDate(assignMemData.getEntryDate());

        data.setC11_CT_PR(ctDataModel.getC11CtPr());
        data.setC21_CT_PR(ctDataModel.getC21CtPr());
        data.setC31_CT_PR(ctDataModel.getC31CtPr());
        data.setC32_CT_PR(ctDataModel.getC32CtPr());
        data.setC33_CT_PR(ctDataModel.getC33CtPr());
        data.setC34_CT_PR(ctDataModel.getC34CtPr());
        data.setC35_CT_PR(ctDataModel.getC35CtPr());
        data.setC36_CT_PR(ctDataModel.getC36CtPr());
        data.setC37_CT_PR(ctDataModel.getC37CtPr());
        data.setC38_CT_PR(ctDataModel.getC38CtPr());
        String grdCode;
        String grdDate;

        assignMemData.setRegNDate(registrationDate);
        grdCode = sqlH.getGRDDefaultActiveReason(assignMemData.getProgram_code(), assignMemData.getService_code());
        assignMemData.setGrdCode(grdCode);
        grdDate = sqlH.getGraduatedDate(assignMemData.getCountryCode(), assignMemData.getDonor_code(), assignMemData.getAward_code());
        assignMemData.setGrdDate(grdDate);
        data.setGRDCode(grdCode);
        data.setGRDDate(grdDate);
        String entryBy = getStaffID();
        String entryDate = "";
        try {
            entryDate = getDateTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        data.setEntryBy(entryBy);
        data.setEntryDate(entryDate);
        ctDataModel.setEntryBy(entryBy);
        ctDataModel.setEntryDate(entryDate);
        return data;
    }

    private void viewReference() {
        tViewHouseHoldName = (TextView) findViewById(R.id.txt_assign_lib_HHName_uct);
        tViewHouseHoldId = (TextView) findViewById(R.id.txt_assign_lib_HHID_uct);
        tViewMemberName = (TextView) findViewById(R.id.txt_assign_lib_MemberName_uct);
        tViewMemberId = (TextView) findViewById(R.id.txt_assign_lib_MemberID_uct);
        tViewCriteria = (TextView) findViewById(R.id.txt_assign_lib_Criteria_uct);
        tViewDate = (TextView) findViewById(R.id.txt_assign_lib_RegDate_uct);
        btnSave = (Button) findViewById(R.id.btn_assign_liberia_save_uct);
        btnAssign = (Button) findViewById(R.id.btn_liberia_goAssignePage_uct);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnHome.setText("Go To ");
        btnSummary = (Button) findViewById(R.id.btnRegisterFooter);
        btnSummary.setText("SumRegLay4TotalHHRecords");

        choiceGroup = (RadioGroup) findViewById(R.id.choice_uct);

        c11 = (RadioButton) findViewById(R.id.evdAffectedHH_uct);
        c21 = (RadioButton) findViewById(R.id.ChFhEhDh_uct);
        c31 = (RadioButton) findViewById(R.id.a_uct);
        c32 = (RadioButton) findViewById(R.id.b_uct);
        c33 = (RadioButton) findViewById(R.id.c_uct);
        c34 = (RadioButton) findViewById(R.id.d_uct);
        c35 = (RadioButton) findViewById(R.id.e_uct);
        c36 = (RadioButton) findViewById(R.id.f_uct);
        c37 = (RadioButton) findViewById(R.id.g_uct);
        c38 = (RadioButton) findViewById(R.id.h_uct);

    }

    /**
     * @param intent
     */
    private void getDataFromIntent(Intent intent) {


        assignMemData.setHh_name(intent.getStringExtra(KEY.ASSIGN_HOUSEHOLD_NAME));
        assignMemData.setHh_mm_name(intent.getStringExtra(KEY.ASSIGN_HOUSEHOLD_NAME));
        assignMemData.setAssign_criteria(intent.getStringExtra(KEY.ASSIGN_CRITERIA_STRING));


        assignMemData.setHh_id(intent.getStringExtra(KEY.ASSIGN_HOUSEHOLD_ID));
        assignMemData.setMemId(intent.getStringExtra(KEY.ASSIGN_HOUSEHOLD_MEMBER_ID));
        assignMemData.setC_code(intent.getStringExtra(KEY.ASSIGN_COUNTRY_CODE));
        assignMemData.setDistrictCode(intent.getStringExtra(KEY.ASSIGN_DISTRICT_CODE));
        assignMemData.setUpazillaCode(intent.getStringExtra(KEY.ASSIGN_UPOZILLA_CODE));
        assignMemData.setUnitCode(intent.getStringExtra(KEY.ASSIGN_UNIT_CODE));
        assignMemData.setVillageCode(intent.getStringExtra(KEY.ASSIGN_VILLAGE_CODE));
        assignMemData.setHh_id(intent.getStringExtra(KEY.ASSIGN_HOUSEHOLD_ID));
        assignMemData.setMemId(intent.getStringExtra(KEY.ASSIGN_HOUSEHOLD_MEMBER_ID));
        assignMemData.setEntryBy(intent.getStringExtra(KEY.ASSIGN_ENTRY_BY));
        assignMemData.setAward_code(intent.getStringExtra(KEY.ASSIGN_AWARD_CODE));
        assignMemData.setProgram_code(intent.getStringExtra(KEY.ASSIGN_PROGRAM_CODE));
        assignMemData.setService_code(intent.getStringExtra(KEY.ASSIGN_SERVICE_CODE));
        assignMemData.setDonor_code(intent.getStringExtra(KEY.ASSIGN_DONER_CODE));

        //all 3 program
        holderStrAward = intent.getStringExtra(KEY.ASSIGN_AWARD_STRING);
        holderStrProgram = intent.getStringExtra(KEY.ASSIGN_PROGRAM_STRING);
        holderStrCriteria = intent.getStringExtra(KEY.ASSIGN_CRITERIA_STRING);
        holderStrVillage = intent.getStringExtra(KEY.ASSIGN_VILLAGE_STRING);

        memberId15D = intent.getExtras().getString(KEY.MEMBER_ID);


        assignMemData.setRegNDate(sqlH.getMemberRegNDate(assignMemData));//db

    }

    public void setTextToTextView() {
        // Use utilMethod as local veriable
        UtilClass utilMethod=new UtilClass();
        tViewHouseHoldName.setText(assignMemData.getHh_name());
        tViewHouseHoldId.setText(assignMemData.getHh_id());
        tViewMemberName.setText(assignMemData.getHh_mm_name());
        tViewMemberId.setText(assignMemData.getMemId());
        tViewCriteria.setText(assignMemData.getAssign_criteria());
        // todo: change : get data from Reg Table
      /*  String regDate=sqlH.getRegDateFromRegNAssignProgSrv(assignMemData.getCountryCode(),assignMemData.getLayR1Code(),assignMemData.getUpazillaCode(),
                assignMemData.getUnitCode(),assignMemData.getVillageCode(),assignMemData.getHh_id(),assignMemData.getMemberId(),assignMemData.getProgram_code(),assignMemData.getService_code(),assignMemData.getDonor_code(),assignMemData.getAward_code());
        if (!regDate.equals("")||regDate.length()>0)
            tViewDate.setText(assignMemData.getRegNDate());

        setStrRegDate(utilMethod.setDateFormatFromTV(tViewDate));*/

    }



    public void checkCiteriaDetails() {
        int selectedId = choiceGroup.getCheckedRadioButtonId();

        ctDataModel.setCountryCode(assignMemData.getCountryCode());
        ctDataModel.setDistrictCode(assignMemData.getDistrictCode());
        ctDataModel.setUpazillaCode(assignMemData.getUpazillaCode());
        ctDataModel.setUnitCode(assignMemData.getUnitCode());
        ctDataModel.setVillageCode(assignMemData.getVillageCode());
        ctDataModel.setHhId(assignMemData.getHh_id());
        ctDataModel.setMemID(assignMemData.getMemId());

    // by Defauls all value should be no
        ctDataModel.setC11CtPr(NO);
        ctDataModel.setC21CtPr(NO);
        ctDataModel.setC31CtPr(NO);
        ctDataModel.setC32CtPr(NO);
        ctDataModel.setC33CtPr(NO);
        ctDataModel.setC34CtPr(NO);
        ctDataModel.setC35CtPr(NO);
        ctDataModel.setC36CtPr(NO);
        ctDataModel.setC37CtPr(NO);
        ctDataModel.setC38CtPr(NO);

        switch (selectedId) {
            case R.id.evdAffectedHH_uct:
                ctDataModel.setC11CtPr(YES);
                break;

            case R.id.ChFhEhDh_uct:
                ctDataModel.setC21CtPr(YES);
                break;
            case R.id.a_uct:
                ctDataModel.setC31CtPr(YES);
                break;
            case R.id.b_uct:
                ctDataModel.setC32CtPr(YES);
                break;

            case R.id.c_uct:
                ctDataModel.setC33CtPr(YES);
                break;
            case R.id.d_uct:
                ctDataModel.setC34CtPr(YES);
                break;
            case R.id.e_uct:
                ctDataModel.setC35CtPr(YES);
                break;
            case R.id.f_uct:
                ctDataModel.setC36CtPr(YES);
                break;
            case R.id.g_uct:
                ctDataModel.setC37CtPr(YES);
                break;
            case R.id.h_uct:
                ctDataModel.setC38CtPr(YES);
                break;
        }
    }

    private void gotoAssignBeneficiaryPage() {
        /** @date : 2015-10-08 */

  /*      Intent iAssign = new Intent(mContext, OldAssignActivity.class);
        finish();

        iAssign.putExtra(KEY.COUNTRY_ID, assignMemData.getCountryCode());
        iAssign.putExtra(OldAssignActivity.SUB_ASSIGN_DIR, true);
        iAssign.putExtra(OldAssignActivity.ASSIGN_AWARD_CODE, assignMemData.getAward_code());
        iAssign.putExtra(OldAssignActivity.ASSIGN_AWARD_STR, holderStrAward);
        iAssign.putExtra(OldAssignActivity.ASSIGN_PROGRAM_CODE, assignMemData.getProgram_code());
        iAssign.putExtra(OldAssignActivity.ASSIGN_PROGRAM_STR, holderStrProgram);
        iAssign.putExtra(OldAssignActivity.ASSIGN_DONOR_CODE, assignMemData.getDonor_code());
        iAssign.putExtra(OldAssignActivity.ASSIGN_CRITERIA_CODE, assignMemData.getService_code());// service Code is criteria Code
        iAssign.putExtra(OldAssignActivity.ASSIGN_CRITERIA_STR, holderStrCriteria);
        iAssign.putExtra(OldAssignActivity.ASSIGN_VILLAGE_CODE, assignMemData.getVillageCode());
        iAssign.putExtra(OldAssignActivity.ASSIGN_VILLAGE_STR, holderStrVillage);
        iAssign.putExtra(OldAssignActivity.ASSIGN_DISTRICT_CODE, assignMemData.getLayR1Code());
        iAssign.putExtra(OldAssignActivity.ASSIGN_UPZELA_CODE, assignMemData.getUpazillaCode());
        iAssign.putExtra(OldAssignActivity.ASSIGN_UNIT_CODE, assignMemData.getUnitCode());
        //   iAssign.putExtra("Ass_DIR", true);

        startActivity(iAssign);
*/


        Intent iAssign = new Intent(mContext, AssignActivity.class);
        finish();

        iAssign.putExtra(KEY.COUNTRY_ID, assignMemData.getCountryCode());
        iAssign.putExtra(AssignActivity.SUB_ASSIGN_DIR, true);
        iAssign.putExtra(AssignActivity.ASSIGN_AWARD_CODE, assignMemData.getAward_code());
        iAssign.putExtra(AssignActivity.ASSIGN_AWARD_STR, holderStrAward);
        iAssign.putExtra(AssignActivity.ASSIGN_PROGRAM_CODE, assignMemData.getProgram_code());
        iAssign.putExtra(AssignActivity.ASSIGN_PROGRAM_STR, holderStrProgram);
        iAssign.putExtra(AssignActivity.ASSIGN_DONOR_CODE, assignMemData.getDonor_code());
        iAssign.putExtra(AssignActivity.ASSIGN_CRITERIA_CODE, assignMemData.getService_code());
        iAssign.putExtra(AssignActivity.ASSIGN_CRITERIA_STR, holderStrCriteria);
        iAssign.putExtra(KEY.MEMBER_ID,memberId15D);


        startActivity(iAssign);
    }

    private void dateProcessing() {

        String tem = sqlH.getRegDate_RegNAsgProgSrv(assignMemData);
        if (tem.equals("null")) {
            tem = "";
        }

        tViewDate.setText(tem);

        if (!tViewDate.getText().toString().equals("")) {
            String temp = tViewDate.getText().toString();
            // to save the date format YYYY-MM-DD
            temp = temp.substring(6) + "-" + temp.substring(0, 5);
            setStrRegDate(temp);
        }

    }

    public void updateRegDate() {
        setStrRegDate(format.format(calendar.getTime()));
        tViewDate.setText(formatUSA.format(calendar.getTime()));
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


    public String getStrRegDate() {
        return strRegDate;
    }

    public void setStrRegDate(String strRegDate) {
        this.strRegDate = strRegDate;
    }

    public void goToAlert()
    {
        final CharSequence [] items = getResources().getStringArray(R.array.uct_assign_goto_array);

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(AssignForLiberiaUCT.this, android.R.style.Theme_Holo_Light_Dialog));

        builder.setTitle("GO TO:");


        builder.setIcon(R.drawable.navigation_icon);
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                switch (item)
                {
                    case 0:
                        finish();
                        intent = new Intent(AssignForLiberiaUCT.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        finish();
                        intent = new Intent(AssignForLiberiaUCT.this,DistributionActivity.class);
                        intent.putExtra(KEY.DIR_CLASS_NAME_KEY,"AssignForLiberiaUCT");
                        intent.putExtra(KEY.COUNTRY_ID,sqlH.selectCountryCode());
                        startActivity(intent);
                        break;
                    case 2:
                        finish();
                        intent = new Intent(AssignForLiberiaUCT.this,ServiceActivity.class);
                        intent.putExtra(KEY.DIR_CLASS_NAME_KEY,"AssignForLiberiaUCT");
                        intent.putExtra(KEY.COUNTRY_ID,sqlH.selectCountryCode());
                        startActivity(intent);
                        break;
                    case 3:
                        finish();
                        intent = new Intent(AssignForLiberiaUCT.this,SummaryMenuActivity.class);
                        intent.putExtra(KEY.COUNTRY_ID,sqlH.selectCountryCode());
                        startActivity(intent);
                        break;
                    case 4:
                        finish();
                        intent = new Intent(AssignForLiberiaUCT.this, RegisterLiberia.class);
                        intent.putExtra("country_code",sqlH.selectCountryCode());
                        startActivity(intent);
                        break;
                }
                goToDialog.dismiss();
            }
        });
        goToDialog = builder.create();
        goToDialog.show();
        int titleDividerId = goToDialog.getContext().getResources().getIdentifier("titleDivider","id","android");//("android:id/titleDivider",null,null);
        //   View titleDivider = activityDialog.findViewById(titleDividerId);
        View titleDivider = goToDialog.getWindow().getDecorView().findViewById(titleDividerId);
        if (titleDivider!=null)
            titleDivider.setBackgroundColor(getResources().getColor(R.color.blue));
// setAlertDevider();

    }
}
