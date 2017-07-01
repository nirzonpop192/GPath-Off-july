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
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
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

public class LM extends BaseActivity {
    public static final int LM_GRD_DAYS_180 = 180;
    private final String TAG = LM.class.getName();
    TextView tv_HhName;
    //    TextView tv_HhID;
    TextView tv_MemberName;
    TextView tv_MemberId;
    TextView tv_AsCriteria;
    TextView tv_regDate;
    TextView tv_LmDate;
    //TextView tv_LM_Duration;
    Button btnSave;
    // TextView tvPageTitle;
    SQLiteHandler sqlH;
    private Button btnHome;
    private Button btnSummary;
    private Button btnBackToAssign;
    private ADNotificationManager erroDialog = new ADNotificationManager();
    private Context mContext;
    private SimpleDateFormat formatUSA = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


    /*   private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);*/
    private Calendar calendar = Calendar.getInstance();


    AssignDataModel assignMem = new AssignDataModel();

    private String memberId15D;


    /**
     * Spinner to Assign  Categories & Group Code
     */
    private Spinner spGroupCategories, spGroup, spActive;
    /**
     * Spinner Id  (Categories & Group& Active)
     */
    private String idGroupCat, idGroup, idActive;
    /**
     * Spinner Vaules Categories & Group
     */
    private String strGroupCat, strGroup;
    private EditText edtChildName;
    private Spinner spChildGender;
    private String strChildGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_assign_lactating_mother);

        mContext = LM.this;
        sqlH = new SQLiteHandler(this);

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
            calculateTheDaysBtnRegDateNLmDate();

        }


        setListeners();

        loadChildGender();
    }

    private void setListeners() {
        tv_regDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRegDate();
            }
        });
        tv_LmDate.setOnClickListener(new View.OnClickListener() {
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

    private void goToSummaryPage() {

        Intent iAssignSummary = new Intent(mContext, SummaryMenuActivity.class);
        iAssignSummary.putExtra(KEY.COUNTRY_ID, assignMem.getCountryCode());
        finish();

        startActivity(iAssignSummary);
    }

    private void calculateTheDaysBtnRegDateNLmDate() {
        String tem = sqlH.getRegDate_RegNAsgProgSrv(assignMem);
        if (tem.equals("null")) {
            tem = "";
        }

        tv_regDate.setText(tem);


        tv_LmDate.setText(sqlH.getLMDate_LM(assignMem));


    }

    private void setTextToTextViews() {
        tv_HhName.setText(assignMem.getHh_name());

        tv_MemberName.setText(assignMem.getHh_mm_name());
        tv_MemberId.setText(assignMem.getMemId());
        tv_AsCriteria.setText(assignMem.getAssign_criteria());
    }


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
                //Log.d(TAG, "Gender selected: " + strChildGender);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        SpinnerLoader.loadGroupCatLoader(mContext,sqlH,spGroupCategories,cCode,donorCode,awardCode,progCode,idGroupCat,strGroupCat);



        spGroupCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strGroupCat = ((SpinnerHelper) spGroupCategories.getSelectedItem()).getValue();
                idGroupCat = ((SpinnerHelper) spGroupCategories.getSelectedItem()).getId();

                if (idGroupCat.length() > 2)
                    loadGroup(cCode, donorCode, awardCode, progCode, idGroupCat);

                //   Log.d(TAG, "Group Category ,idGroupCat:" + idGroupCat + " strGroupCat : " + strGroupCat);

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
        int pos = 0;

        ArrayAdapter<CharSequence> adptMartial = ArrayAdapter.createFromResource(
                this, R.array.arrActive, R.layout.spinner_layout);

        adptMartial.setDropDownViewResource(R.layout.spinner_layout);
        spActive.setAdapter(adptMartial);

        if (idActive != null) {
            if (idActive.equals("Y"))
                pos = 0;
            else
                pos = 1;

            spActive.setSelection(pos);
        }


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


    private void saveAssignBeneficiary() {

        String regDate = tv_regDate.getText().toString();
        String lmDate = tv_LmDate.getText().toString();
        String childName = edtChildName.getText().toString();
        long days = 0;


        if (sqlH.get_ProgramMultipleSrv(assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getProgram_code()).equals("N")
                && sqlH.get_MemOthCriteriaLive(assignMem.getCountryCode(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getProgram_code(), assignMem.getService_code())) {
            erroDialog.showErrorDialog(mContext, "Member remains active in other criteria. Save attempt denied.");
        } else {

            if (regDate.equals("")) {
                erroDialog.showErrorDialog(mContext, "Missing Registration date. Save attempt denied.");
            } else if (lmDate.equals("")) {
                erroDialog.showErrorDialog(mContext, "Missing DOB date. Save attempt denied.");
            } else if (childName.equals("") || childName.length() == 0) {
                erroDialog.showErrorDialog(mContext, "Child name is empty");
            } else if (idGroup == null || idGroup.equals("00")) {
                erroDialog.showErrorDialog(mContext, "Missing Group. Save attempt denied.");
            } else if (assignMem.getMember_age() == null) {
                erroDialog.showErrorDialog(mContext, "Invalid age specified.");
            } else {
                days = getDateDifference(tv_regDate.getText().toString(), tv_LmDate.getText().toString()+" 00:00:00");


                /**
                 * Check the Difference between 2 dates
                 *
                 * */
                if (days > 0 && days < 180) {

                    Log.d(TAG, " reg Date : " + tv_regDate.getText().toString());

                    String entryBy = getStaffID();
                    assignMem.setEntryBy(entryBy);
                    String entryDate = "";
                    try {
                        entryDate = getDateTime();

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    assignMem.setEntryDate(entryDate);
                    SQLServerSyntaxGenerator assign_lm = new SQLServerSyntaxGenerator();
                    assign_lm.setAdmCountryCode(assignMem.getCountryCode());
                    assign_lm.setLayR1ListCode(assignMem.getDistrictCode());
                    assign_lm.setLayR2ListCode(assignMem.getUpazillaCode());
                    assign_lm.setLayR3ListCode(assignMem.getUnitCode());
                    assign_lm.setLayR4ListCode(assignMem.getVillageCode());
                    assign_lm.setAdmDonorCode(assignMem.getDonor_code());
                    assign_lm.setAdmAwardCode(assignMem.getAward_code());
                    assign_lm.setHHID(assignMem.getHh_id());
                    assign_lm.setMemID(assignMem.getMemId());
                    assign_lm.setProgCode(assignMem.getProgram_code());
                    assign_lm.setSrvCode(assignMem.getService_code());
                    assign_lm.setRegNDate(assignMem.getRegNDate());
                    assign_lm.setGRDCode(assignMem.getGrdCode());
                    assign_lm.setGRDDate(assignMem.getGrdDate());
                    assign_lm.setEntryBy(assignMem.getEntryBy());
                    assign_lm.setEntryDate(assignMem.getEntryDate());

                    if (sqlH.ifExistsInRegNAssProgSrv(assignMem)) {
                        /** this member exits
                         * Update  operation RegNAssProgSrv
                         *  set up the Registration Date in aBeneficiary
                         */
                        assignMem.setRegNDate(regDate);
                        assignMem.setGrdDate(calculateGRDDate(tv_LmDate.getText().toString()));
                        int id = sqlH.editMemberDataIn_RegNAsgProgSrv(assignMem);

                        /** over write  the setter method**/
                        assign_lm.setRegNDate(assignMem.getRegNDate());
                        assign_lm.setGRDCode(assignMem.getGrdCode());
                        assign_lm.setGRDDate(assignMem.getGrdDate());
                        /**
                         * update for server */

                        sqlH.insertIntoUploadTable(assign_lm.updateRegAssProgSrvForAssign());


                    } else {
                        // if not exits than add to the RegN
                        assignMem.setRegNDate(regDate);
                        assignMem.setGrdCode(sqlH.getGRDDefaultActiveReason(assignMem.getProgram_code(), assignMem.getService_code()));
                        assignMem.setGrdDate(calculateGRDDate(tv_LmDate.getText().toString()));
                        long id = sqlH.addMemberDataInto_RegNAsgProgSrv(assignMem);
                        /** over write  the setter method**/
                        assign_lm.setRegNDate(assignMem.getRegNDate());
                        assign_lm.setGRDCode(assignMem.getGrdCode());
                        assign_lm.setGRDDate(assignMem.getGrdDate());
                        /**
                         * insert for server */

                        sqlH.insertIntoUploadTable(assign_lm.insertIntoRegAssProgSrv());


                    }
                    // now it's turn for lM table


                    if (sqlH.ifExistsInLmdTable(assignMem)) {
                        //  if this member already exits in this table than reg, lmp & grd date will update
                        assignMem.setRegNDate(regDate);
                        assignMem.setGrdDate(calculateGRDDate(tv_LmDate.getText().toString()));
                        assignMem.setLmDate(lmDate);
                        long id = sqlH.upDateRegNLM(assignMem, lmDate);


                        assign_lm.setRegNDate(assignMem.getRegNDate());
                        assign_lm.setLMDOB(lmDate);
                        assign_lm.setLMGRDDate(assignMem.getGrdDate());
                        assign_lm.setGRDCode(assignMem.getGrdCode());

                        sqlH.insertIntoUploadTable(assign_lm.updateRegNLMForLactingMother());


                    } else {
                        assignMem.setRegNDate(regDate);
                        assignMem.setGrdCode(sqlH.getGRDDefaultActiveReason(assignMem.getProgram_code(), assignMem.getService_code()));
                        assignMem.setGrdDate(calculateGRDDate(tv_LmDate.getText().toString()));
                        assignMem.setLmDate(lmDate);
                        sqlH.addMemIntoRegN_LM(assignMem.getCountryCode(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getProgram_code(), assignMem.getService_code(), assignMem.getRegNDate(), assignMem.getGrdCode(), assignMem.getGrdDate(), lmDate, childName, strChildGender, entryBy, entryDate, "0");


                        assign_lm.setRegNDate(assignMem.getRegNDate());
                        assign_lm.setLMDOB(lmDate);
                        assign_lm.setLMGRDDate(assignMem.getGrdDate());
                        assign_lm.setGRDCode(assignMem.getGrdCode());

                        sqlH.insertIntoUploadTable(assign_lm.insertIntoRegNLMForLactatedMother());

                    }


                    assign_lm.setActive(idActive);
                    assign_lm.setGrpCode(idGroup);

                    /**                  * get Group layR  list Code from Community Group                 */
                    LayRCodes grpLayRListCode = sqlH.getLayRListFromCommunityGroup(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getProgram_code(), idGroup, strGroup);
                    assign_lm.setGrpLayR1ListCode(grpLayRListCode.getLayR1Code());
                    assign_lm.setGrpLayR2ListCode(grpLayRListCode.getLayR2Code());
                    assign_lm.setGrpLayR3ListCode(grpLayRListCode.getLayR3Code());
                    /**                     * check RegNmemProgGroup                     */
                    if (sqlH.ifExistsInRegNmemProgGroup(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getProgram_code(), assignMem.getService_code())) {
                        sqlH.editMemberIn_RegNmemProgGroup(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getProgram_code(), assignMem.getService_code(), idGroup, idActive, entryBy, entryDate, grpLayRListCode.getLayR1Code(), grpLayRListCode.getLayR2Code(), grpLayRListCode.getLayR3Code());

                        /**                         * Upload Syntax                         */
                        sqlH.insertIntoUploadTable(assign_lm.UpdateRegNMemProgGrp());


                    } else {
                        /**                         * Upload Syntax                         */
                        sqlH.insertIntoUploadTable(assign_lm.insertInToRegNMemProgGrp());

                        sqlH.addRegNmemProgGroup(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getProgram_code(), assignMem.getService_code(), idGroup, strGroup, idActive, entryBy, entryDate, grpLayRListCode.getLayR1Code(), grpLayRListCode.getLayR2Code(), grpLayRListCode.getLayR3Code());
                    }

                    sqlH.insertIntoUploadTable(assign_lm.sqlSpRegNMemAwardProgCombN_Save());

                    Toast.makeText(mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();

                }// end of  if (days > 0 && days < 180) block
                else {
                    erroDialog.showErrorDialog(mContext, "DOB is invalid or exceeds allowable age range under this category ");
                }// end of else

            }// end of else

        }// end of else


    }


    private long getDateDifference(String from, String to) {
        long days = 0;
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
     * date: 2015-11-23
     * calculate the the graduation date
     */
    private String calculateGRDDate(String inputDate) {
        String outputDate = "";
       /* SimpleDateFormat myFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
        SimpleDateFormat saveFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH); */

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat saveFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(myFormat.parse(inputDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DATE, LM_GRD_DAYS_180);

        outputDate = saveFormat.format(calendar.getTime());

        return outputDate;
    }

    private void viewReference() {
        tv_HhName = (TextView) findViewById(R.id.as_LM_ed_hhName);

        tv_MemberId = (TextView) findViewById(R.id.as_LM_ed_MemberID);
        tv_MemberName = (TextView) findViewById(R.id.as_LM_ed_MemberName);
        tv_AsCriteria = (TextView) findViewById(R.id.as_LM_ed_asCriteria);
        tv_regDate = (TextView) findViewById(R.id.as_LM_edt_regD);
        tv_LmDate = (TextView) findViewById(R.id.as_LM_edt_lmDate);
        edtChildName = (EditText) findViewById(R.id.as_LM_edt_ChildName);


        /**
         * 4 Button
         */

        btnSave = (Button) findViewById(R.id.btn_assign_save);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnSummary = (Button) findViewById(R.id.btnRegisterFooter);
        btnBackToAssign = (Button) findViewById(R.id.btn_LM_goAssignePage);


        /**
         *  4 Spinner
         */
        spGroupCategories = (Spinner) findViewById(R.id.sp_ass_lmGroupCategories);
        spGroup = (Spinner) findViewById(R.id.sp_ass_lmGroup);
        spActive = (Spinner) findViewById(R.id.sp_ass_lmActive);
        spChildGender = (Spinner) findViewById(R.id.spChildGender);


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


    /**
     * set date picker for registration
     */
    public void updateRegDate() {

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
//        setStrLMDate(format.format(calendar.getTime()));
        tv_LmDate.setText(formatUSA.format(calendar.getTime()));
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


}
