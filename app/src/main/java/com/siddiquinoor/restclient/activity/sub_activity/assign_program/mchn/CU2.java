package com.siddiquinoor.restclient.activity.sub_activity.assign_program.mchn;

import android.annotation.TargetApi;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;

import com.siddiquinoor.restclient.activity.SummaryMenuActivity;
import com.siddiquinoor.restclient.activity.AssignActivity;
import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.data_model.LayRCodes;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.data_model.adapters.AssignDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;
import com.siddiquinoor.restclient.views.spinner.SpinnerLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CU2 extends BaseActivity {


    private TextView tv_HhName, tv_MemberName, tv_MemberId, tv_AsCriteria, tv_regDate, tv_dobDate;

    private Button btnSave;
    // private TextView tv_CU_Duration;
    private SQLiteHandler sqlH;
    private Button btnHome;
    private Button btnSummary;
    private Button btnBackToAssign;
    private ADNotificationManager erroDialog = new ADNotificationManager();
    private Context mContext;
    private String holderStrAward;
    private String holderStrProgram;



    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private Calendar calendar = Calendar.getInstance();
    // private SimpleDateFormat formatUSA = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
    private SimpleDateFormat formatUSA = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    AssignDataModel assignMem = new AssignDataModel();
    private String memberId15D;
    private final String TAG = CU2.class.getName();

    //    private boolean isDir4mMemReg;
    private Spinner spGroup;
    private Spinner spChildGender;
    private Spinner spActive;
    private String strGroup, strChildGender;
    private String idGroup;

    private String idActive;
    private Spinner spGroupCategories;
    private String idGroupCat;
    private String strGroupCat;
    private EditText edtChildName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_assign_childen_under2);

        mContext = CU2.this;
        sqlH = new SQLiteHandler(this);
        //  context=AssignPage2.this;
        viewReference();

        // Intent intent;
        Intent mIntent = getIntent();
        assignMem = mIntent.getExtras().getParcelable(KEY.ASSIGN_DATA_OBJECT_KEY);
        memberId15D = mIntent.getExtras().getString(KEY.MEMBER_ID);
        if (assignMem != null) {

            idGroupCat = assignMem.getGroupCatCode();
            strGroupCat = assignMem.getGroupCatName();
            idGroup = assignMem.getGroupCode();
            strGroup = assignMem.getGroupName();
            idActive = assignMem.getActiveCode();

            setTextToTextViews();
            loadGroupCategory(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getProgram_code());
            calculatingTheNoDaysBtnRegDateNDOB();
            loadChildGender();
        }


        setListeners();


    }// end of on create


    /**
     * LOAD :: GENDER of Child Default value male
     */
    private void loadChildGender() {


        ArrayAdapter<CharSequence> adtGender = ArrayAdapter.createFromResource(
                this, R.array.spGenderItem, R.layout.spinner_layout);

        adtGender.setDropDownViewResource(R.layout.spinner_layout);
        spChildGender.setAdapter(adtGender);

        if (strChildGender != null) {
            spChildGender.setSelection(getSpinnerIndex(spChildGender, strChildGender));
        }

        spChildGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                strChildGender = parent.getItemAtPosition(position).toString();

                if (strChildGender.equals("Male"))
                    strChildGender = "M";
                else
                    strChildGender = "F";

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * LOAD :: GroupCategory
     *
     * @param cCode     Adm Country Code
     * @param donorCode Adm Donor Code
     * @param awardCode Adm Award Code
     * @param progCode  Adm Program Cod e
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
    private void loadGroup(final String cCode, final String donorCode, final String awardCode
            , final String progCode, final String grpCateCode) {

        SpinnerLoader.loadGroupLoader(mContext, sqlH, spGroup, cCode, donorCode, awardCode, progCode, grpCateCode, idGroup, strGroup);


        spGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strGroup = ((SpinnerHelper) spGroup.getSelectedItem()).getValue();
                idGroup = ((SpinnerHelper) spGroup.getSelectedItem()).getId();

                if (idGroup.length() > 2) {
                    loadActiveStatus();
                }


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


    private void setListeners() {
        tv_regDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRegDate();
            }
        });
        tv_dobDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLmDate();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAssignBeneficiary();
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHome = new Intent(mContext, MainActivity.class);
                finish();
                startActivity(iHome);
            }
        });
        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSummaryPage();
            }
        });
        btnBackToAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                gotoAssignBeneficiaryPage();
            }
        });
    }

    /**
     * do not delete
     */
    private void calculatingTheNoDaysBtnRegDateNDOB() {

        String tem = sqlH.getRegDate_RegNAsgProgSrv(assignMem);
        if (tem.equals("null")) {
            tem = "";
        }

        tv_regDate.setText(tem);

/**
 *  if data exit in Cu2 table  this method will show
 */
        tv_dobDate.setText(sqlH.getMemCU2DOB(assignMem));


    }

    private void goToSummaryPage() {

        Intent iAssignSummary = new Intent(mContext, SummaryMenuActivity.class);
        iAssignSummary.putExtra(KEY.COUNTRY_ID, assignMem.getCountryCode());
        finish();

        startActivity(iAssignSummary);
    }

    private void setTextToTextViews() {

        tv_HhName.setText(assignMem.getHh_name());
        tv_MemberName.setText(assignMem.getHh_mm_name());
        tv_MemberId.setText(assignMem.getNewId());
        tv_AsCriteria.setText(assignMem.getAssign_criteria());
    }


    /**
     * @since 2016-02-23
     */
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

    private void saveAssignBeneficiary() {
        // setLmDate();
        String regDate = tv_regDate.getText().toString();
        String dobDate = tv_dobDate.getText().toString();
        String childName = edtChildName.getText().toString();


        if (sqlH.get_ProgramMultipleSrv(assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getProgram_code()).equals("N")
                && sqlH.get_MemOthCriteriaLive(assignMem.getCountryCode(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getProgram_code(), assignMem.getService_code())) {
            erroDialog.showErrorDialog(mContext, "Member remains active in other criteria. Save attempt denied.");
        } else {

            if (regDate.equals("")) {
                erroDialog.showErrorDialog(mContext, "Missing Registration date. Save attempt denied.");
            } else if (dobDate.equals("")) {
                erroDialog.showErrorDialog(mContext, "Missing DOB date. Save attempt denied.");
            } else if (childName.equals("") || childName.length() == 0) {
                erroDialog.showErrorDialog(mContext, "Child name is empty");
            } else if (idGroup == null || idGroup.equals("00")) {
                erroDialog.showErrorDialog(mContext, "Missing Group. Save attempt denied.");
            } else if (assignMem.getMember_age() == null) {
                erroDialog.showErrorDialog(mContext, "Invalid age specified.");
            } else {
                long days = 0;
                /**
                 * this days valid check block
                 */
                days = getDateDifference(tv_regDate.getText().toString(), tv_dobDate.getText().toString()+" 00:00:00");

                if (days > 181 && days < 720) {

                    Log.d(TAG, " reg Date : " + tv_regDate.getText().toString());


                    String entryBy = getStaffID();
                    String entryDate = "";
                    assignMem.setEntryBy(entryBy);
                    try {

                        entryDate = getDateTime();

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    assignMem.setEntryDate(entryDate);

                    // this member exits
                    // if the grd code is null then it will update the previous values
                    assignMem.setRegNDate(regDate);
                    assignMem.setGrdCode(sqlH.getGRDDefaultActiveReason(assignMem.getProgram_code(), assignMem.getService_code()));
                    assignMem.setGrdDate(calculateGRDDate(tv_dobDate.getText().toString()));
                    assignMem.setDobDate(dobDate);

                    // set the grd code here than update the regAss program Srv


                    /**
                     * For upload getter setter
                     */
                    SQLServerSyntaxGenerator assign_cu2 = new SQLServerSyntaxGenerator();
                    assign_cu2.setAdmCountryCode(assignMem.getCountryCode());
                    assign_cu2.setLayR1ListCode(assignMem.getDistrictCode());
                    assign_cu2.setLayR2ListCode(assignMem.getUpazillaCode());
                    assign_cu2.setLayR3ListCode(assignMem.getUnitCode());
                    assign_cu2.setLayR4ListCode(assignMem.getVillageCode());
                    assign_cu2.setAdmDonorCode(assignMem.getDonor_code());
                    assign_cu2.setAdmAwardCode(assignMem.getAward_code());
                    assign_cu2.setHHID(assignMem.getHh_id());
                    assign_cu2.setMemID(assignMem.getMemId());
                    assign_cu2.setProgCode(assignMem.getProgram_code());
                    assign_cu2.setSrvCode(assignMem.getService_code());
                    assign_cu2.setEntryBy(assignMem.getEntryBy());
                    assign_cu2.setEntryDate(assignMem.getEntryDate());


                    /***
                     *  over write the code
                     */
                    assign_cu2.setRegNDate(assignMem.getRegNDate());
                    assign_cu2.setGRDCode(assignMem.getGrdCode());
                    assign_cu2.setGRDDate(assignMem.getGrdDate());


                    /**
                     * insert for server */


                    assign_cu2.setCU2DOB(assignMem.getDobDate());
                    assign_cu2.setCU2GRDDate(assignMem.getGrdDate());


                    assign_cu2.setActive(idActive);
                    assign_cu2.setGrpCode(idGroup);
                    /***
                     *  the RegNAssProgSrv
                     */
                    if (sqlH.ifExistsInRegNAssProgSrv(assignMem)) {


                        sqlH.editMemberDataIn_RegNAsgProgSrv(assignMem);

                        sqlH.insertIntoUploadTable(assign_cu2.updateRegAssProgSrvForAssign());

                    } else {

                        sqlH.addMemberDataInto_RegNAsgProgSrv(assignMem);

                        sqlH.insertIntoUploadTable(assign_cu2.insertIntoRegAssProgSrv());


                    }

                    /**
                     * RegNCU2 Insert Or update case
                     */


                    // update block
                    if (sqlH.ifExistsInCU2Table(assignMem)) {
                        long id = sqlH.editMemberDataIn_CU2(assignMem, dobDate);
                        sqlH.insertIntoUploadTable(assign_cu2.updateRegNCU2ForChildUnder());


                    } else {
                        // insert block


                        long id = sqlH.addMemIntoRegN_CU2(assignMem.getCountryCode(), assignMem.getDistrictCode()
                                , assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id()
                                , assignMem.getMemId(), assignMem.getProgram_code(), assignMem.getService_code(), assignMem.getRegNDate()
                                , assignMem.getGrdCode(), dobDate, assignMem.getGrdDate(), childName, strChildGender, entryBy, entryDate, "0");
                        /**
                         * Upload: Insert  syntax
                         */
                        sqlH.insertIntoUploadTable(assign_cu2.insertIntoRegNCU2ForChildUnder());

                    }

                    /**                  * get Group layR  list Code from Community Group                 */
                    LayRCodes grpLayRListCode = sqlH.getLayRListFromCommunityGroup(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getProgram_code(), idGroup, strGroup);
                    assign_cu2.setGrpLayR1ListCode(grpLayRListCode.getLayR1Code());
                    assign_cu2.setGrpLayR2ListCode(grpLayRListCode.getLayR2Code());
                    assign_cu2.setGrpLayR3ListCode(grpLayRListCode.getLayR3Code());

                    /**                     * check RegNmemProgGroup                     */
                    if (sqlH.ifExistsInRegNmemProgGroup(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getProgram_code(), assignMem.getService_code())) {
                        sqlH.editMemberIn_RegNmemProgGroup(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getProgram_code(), assignMem.getService_code(), idGroup, idActive, entryBy, entryDate, grpLayRListCode.getLayR1Code(), grpLayRListCode.getLayR2Code(), grpLayRListCode.getLayR3Code());

                        /**                         * Upload: Update Syntax                         */
                        sqlH.insertIntoUploadTable(assign_cu2.UpdateRegNMemProgGrp());


                    } else {
                        sqlH.addRegNmemProgGroup(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getProgram_code(), assignMem.getService_code(), idGroup, strGroup, idActive, entryBy, entryDate, grpLayRListCode.getLayR1Code(), grpLayRListCode.getLayR2Code(), grpLayRListCode.getLayR3Code());
                        /**                         * Upload: Insert Syntax                         */
                        sqlH.insertIntoUploadTable(assign_cu2.insertInToRegNMemProgGrp());


                    }
                    sqlH.insertIntoUploadTable(assign_cu2.sqlSpRegNMemAwardProgCombN_Save());
                    Toast.makeText(mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();


                }// end of  days range if block
                else {
                    erroDialog.showErrorDialog(mContext, "DOB is invalid or exceeds allowable age range under this category ");
                }// end of else block

            }// end of if no error found  else block

        }


    }

    /**
     * set date picker for registration
     */
    public void updateRegDate() {
//        setStrRegDate(format.format(calendar.getTime()));
        tv_regDate.setText(formatUSA.format(calendar.getTime()));
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
     * set date picker for Lm date
     */
    public void updateLmDate() {


        tv_dobDate.setText(formatUSA.format(calendar.getTime()));
    }

    public void setLmDate() {
        new DatePickerDialog(mContext, dat, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener dat = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLmDate();
        }
    };

    private void viewReference() {
        tv_HhName = (TextView) findViewById(R.id.as_CU2_ed_hhName);

        tv_MemberId = (TextView) findViewById(R.id.as_CU2_ed_MemberName);
        tv_MemberName = (TextView) findViewById(R.id.as_CU2_ed_MemberID);
        tv_AsCriteria = (TextView) findViewById(R.id.as_CU2_ed_asCriteria);
        // tvPageTitle= (TextView) findViewById(R.id.as_PW_e);
        tv_regDate = (TextView) findViewById(R.id.as_CU2_edt_regD);
        tv_dobDate = (TextView) findViewById(R.id.as_CU2_ed_dobDate);

        /**         * 4 Button         */

        btnSave = (Button) findViewById(R.id.btn_assign_cu2_save);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnSummary = (Button) findViewById(R.id.btnRegisterFooter);
        btnBackToAssign = (Button) findViewById(R.id.btn_cu2_goAssignePage);

        /**         * 4 Spinner         */

        spGroupCategories = (Spinner) findViewById(R.id.sp_ass_cu2GroupCategories);
        spGroup = (Spinner) findViewById(R.id.sp_ass_cu2Group);
        spActive = (Spinner) findViewById(R.id.sp_ass_cu2Active);
        spChildGender = (Spinner) findViewById(R.id.sp_cu2ChildGender);

        edtChildName = (EditText) findViewById(R.id.as_cu2_edt_ChildName);


    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        setUpHomeButton();
        setUpSummaryButton();
        setUpSaveButton();
        setUpGoToServiceButton();
    }

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
    private void setUpGoToServiceButton() {
        btnBackToAssign.setText("");
        Drawable gotoBackImage = getResources().getDrawable(R.drawable.goto_back);
        btnBackToAssign.setCompoundDrawablesRelativeWithIntrinsicBounds(gotoBackImage, null, null, null);

        setPaddingButton(mContext, gotoBackImage, btnBackToAssign);
    }

    private long getDateDifference(String from, String to) {
        long days = 0;
        // SimpleDateFormat myFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            Date dateFrom = myFormat.parse(from);
            Date dateTo = myFormat.parse(to);
            long difference = 0;
            difference = dateFrom.getTime() - dateTo.getTime();
            days = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 2015-11-23
     * calculate the the graduation date
     * todo amar ek ta geniric method tori korte hobe
     */
    private String calculateGRDDate(String inputDate) {
        String outputDate = "";
        // SimpleDateFormat inputFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat saveFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(inputFormat.parse(inputDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DATE, 720);

        outputDate = saveFormat.format(calendar.getTime());

        return outputDate;
    }
}
