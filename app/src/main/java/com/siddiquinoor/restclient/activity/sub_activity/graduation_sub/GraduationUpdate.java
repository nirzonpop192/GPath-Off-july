package com.siddiquinoor.restclient.activity.sub_activity.graduation_sub;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.activity.GraduationActivity;
import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.data_model.adapters.GraduationGridDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GraduationUpdate extends BaseActivity {

    private final String TAG = GraduationUpdate.class.getName();

    private GraduationGridDataModel mGraduation;

    /**
     * TextView
     */
    private TextView tv_award, tv_program, tv_criteria, tv_village, tv_memberId, tv_memberName, tv_grdDate;


    private Spinner sp_grdReason;
    private SQLiteHandler sqlH;
    private Context mContext;
    private String idGRD;
    private String strGRDTitle;//Reason

    /**
     * Button
     */
    private Button btnSave, btnDelete, btnHome, btnBackToGraduation;


    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    private Calendar calendar = Calendar.getInstance();

    private String EntryBy;
    private String EntryDate;
    /**
     * Dialog
     */
    private ADNotificationManager dialog;

    private String srtGrdDate;
    private SimpleDateFormat formatUSA = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
    private String memberId15Digit;


    public String getSrtGrdDate() {
        return srtGrdDate;
    }

    public void setSrtGrdDate(String srtGrdDate) {
        this.srtGrdDate = srtGrdDate;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graduation_update);

        init();


        setAllViewListener();


        Intent intent = getIntent();
        mGraduation = intent.getExtras().getParcelable(KEY.GRADUATION_DETAILS_DATA_OBJECT_KEY);
        set15DigitId();

        setAllTextToTextView(mGraduation);
        loadReason(mGraduation.getCountryCode(), mGraduation.getProgram_code(), mGraduation.getService_code());


    }

    private void set15DigitId() {
        if (mGraduation != null) {
            memberId15Digit = mGraduation.getDistrictCode() + mGraduation.getUpazillaCode()
                    + mGraduation.getUnitCode() + mGraduation.getVillageCode() +
                    mGraduation.getHh_id() + mGraduation.getMember_Id();
        }

    }

    private void init() {
        mContext = GraduationUpdate.this;
        dialog = new ADNotificationManager();
        sqlH = new SQLiteHandler(mContext);
        viewReference();
    }

    private void setAllViewListener() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberGraduationDelete();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity((Activity) mContext);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String graduation_date, registration_date, end_date, start_date;
                start_date = sqlH.getMinDate(mGraduation.getCountryCode());
                end_date = sqlH.getMaxDate(mGraduation.getCountryCode());
                registration_date = sqlH.getMemberRegNDate(mGraduation);

                graduation_date = tv_grdDate.getText().toString();

                if (registration_date!=null){
                    if (graduation_date.length() > 0) {
                        DateFormat inputFormat = new SimpleDateFormat("mm-dd-yyyy", Locale.ENGLISH);
                        DateFormat outputFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
                        String inputDateStr = graduation_date;
                        Date date = null;
                        try {
                            date = inputFormat.parse(inputDateStr);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String outputDateStr = outputFormat.format(date);

                        if (outputDateStr.length() > 0) {
                            if (Integer.parseInt(idGRD) < 0 || !strGRDTitle.equals("Select Reason")) {

                                if (start_date != null && end_date != null) {
//                                    Log.e("Shuvo", "Graduation date:" + outputDateStr + " Start date: " + start_date + " end date : " +
//                                            end_date + " Registration date: " + registration_date);
                                    try {
                                        Boolean ok = isValidDateForGraduation(outputDateStr, start_date, end_date, registration_date);
                                        if (ok) {
                                            saveMemberGraduationData();
                                        } else
                                            Toast.makeText(GraduationUpdate.this, "Check graduation date.", Toast.LENGTH_SHORT).show();

                                    } catch (ParseException e) {
                                        Toast.makeText(GraduationUpdate.this, "Wrong Date Format, parse error!", Toast.LENGTH_SHORT).show();
                                    }

                                } else
                                    Toast.makeText(GraduationUpdate.this, "No valid date range found!", Toast.LENGTH_SHORT).show();

                            } else
                                Toast.makeText(GraduationUpdate.this, "Please enter a reason.", Toast.LENGTH_SHORT).show();

                        } else
                            Toast.makeText(GraduationUpdate.this, "Please Enter Graduation date.", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(GraduationUpdate.this, "Please Enter Graduation date.", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });

        tv_grdDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGraduationDate();
            }
        });

        btnBackToGraduation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToGraduationPage();

            }
        });
    }

    private boolean isValidDateForGraduation(String grdDate, String sDate, String eDate, String regDate) throws ParseException {

        String curr_date;
        if (isTheDateValidFormat(grdDate)) {
            curr_date = grdDate;
        } else {
            curr_date = grdDate + " 00:00:00.000";
        }

        Log.e("ShuvoCurr", curr_date);

        Date newDate = format.parse(curr_date);
        Log.e("ShuvoNew", String.valueOf(newDate.getTime()));

        Date startDate = format.parse(String.valueOf(sDate));
        Log.e("ShuvoNew", String.valueOf(startDate));
        Date endDate = format.parse(String.valueOf(eDate));
        Date registrationDate = format.parse(regDate);

        // return newDate.after(startDate)&& newDate.before(endDate) && newDate.after(registrationDate);
        return newDate.getTime() >= startDate.getTime() && newDate.getTime() <= endDate.getTime() &&
                newDate.getTime() > registrationDate.getTime();

    }

    private void backToGraduationPage() {

        finish();
        Intent iGraduation = new Intent(mContext, GraduationActivity.class);

        iGraduation.putExtra(KEY.COUNTRY_ID, mGraduation.getCountryCode());

        iGraduation.putExtra(KEY.MEMBER_ID, memberId15Digit);
        iGraduation.putExtra(KEY.DIR_CLASS_NAME_KEY, "GraduationUpdate");
        /**
         * For Spinner sate restore
         */

        iGraduation.putExtra(KEY.AWARD_CODE, mGraduation.getAward_code());
        iGraduation.putExtra(KEY.AWARD_NAME, mGraduation.getAward_name());
        iGraduation.putExtra(KEY.PROGRAM_CODE, mGraduation.getProgram_code());
        iGraduation.putExtra(KEY.PROGRAM_NAME, mGraduation.getProgram_name());
        iGraduation.putExtra(KEY.CRITERIA_CODE, mGraduation.getService_code());// Criteria Code = service Code
        iGraduation.putExtra(KEY.CRITERIA_NAME, mGraduation.getCriteria_name());

        startActivity(iGraduation);
    }

    private void setAllTextToTextView(GraduationGridDataModel data) {
        tv_award.setText(data.getAward_name());
        tv_program.setText(data.getProgram_name());
        tv_criteria.setText(data.getCriteria_name());
        tv_village.setText(data.getVillageName());


        tv_memberId.setText(memberId15Digit);
        tv_memberName.setText(data.getMember_name());
        tv_grdDate.setText(data.getGraduationDate());
    }


    private void memberGraduationDelete() {
        String grdMemberReason = sqlH.getGrdCodeForMember_RegNAssProgSrv(mGraduation.getCountryCode(),
                mGraduation.getDistrictCode(), mGraduation.getUpazillaCode(),
                mGraduation.getUnitCode(), mGraduation.getVillageCode(), mGraduation.getHh_id(),
                mGraduation.getMember_Id(), mGraduation.getProgram_code(),
                mGraduation.getService_code(), mGraduation.getDonor_code(), mGraduation.getAward_code());
        String grdDefaultExitReason = sqlH.getGRDDefaultExitReason(mGraduation.getProgram_code(),
                mGraduation.getService_code());
        if (grdMemberReason == null || grdMemberReason.equals("") || grdDefaultExitReason.equals("")) {
            Log.d(TAG, "grdMemberReason =" + grdMemberReason + " , grdDefaultExitReason = " + grdDefaultExitReason);
        } else {
            // If the grdCode is equal to Exit then
            if (grdMemberReason.equals(grdDefaultExitReason)) {
                Toast.makeText(mContext, "You are not able to Delete this member", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    EntryBy = getStaffID();
                    EntryDate = getDateTime();
                    sqlH.editMemberDataIn_RegNAsgProgSrv(null,
                            null,
                            EntryBy, EntryDate, mGraduation.getCountryCode(),
                            mGraduation.getDistrictCode(), mGraduation.getUpazillaCode(),
                            mGraduation.getUnitCode(), mGraduation.getVillageCode(), mGraduation.getHh_id(),
                            mGraduation.getMember_Id(), mGraduation.getProgram_code(),
                            mGraduation.getService_code(), mGraduation.getDonor_code(), mGraduation.getAward_code());

                    SQLServerSyntaxGenerator graduationQuery = new SQLServerSyntaxGenerator();
                    graduationQuery.setAdmCountryCode(mGraduation.getCountryCode());
                    graduationQuery.setLayR1ListCode(mGraduation.getDistrictCode());
                    graduationQuery.setLayR2ListCode(mGraduation.getUpazillaCode());
                    graduationQuery.setLayR3ListCode(mGraduation.getUnitCode());
                    graduationQuery.setLayR4ListCode(mGraduation.getVillageCode());
                    graduationQuery.setAdmAwardCode(mGraduation.getAward_code());
                    graduationQuery.setAdmDonorCode(mGraduation.getDonor_code());
                    graduationQuery.setHHID(mGraduation.getHh_id());
                    graduationQuery.setMemID(mGraduation.getMember_Id());
                    graduationQuery.setProgCode(mGraduation.getProgram_code());
                    graduationQuery.setSrvCode(mGraduation.getService_code());
                    graduationQuery.setEntryBy(EntryBy);
                    graduationQuery.setEntryDate(EntryDate);

                    sqlH.insertIntoUploadTable(graduationQuery.updateGraduation());
                    //Toast.makeText(mContext, "The data is delete", Toast.LENGTH_SHORT).show();
                         /*  GraduationDateCode dateCode= sqlH.getGRDPeopleDetial(mGraduation.getCountryCode(),
                                    mGraduation.getDistrictCode(), mGraduation.getUpazillaCode(),
                                    mGraduation.getUnitCode(), mGraduation.getVillageCode(), mGraduation.getHh_id(),
                                    mGraduation.getMember_Id(), mGraduation.getProgram_code(),
                                    mGraduation.getService_code(), mGraduation.getDonor_code(), mGraduation.getAward_code());
                            tv_grdDate.setText(dateCode.getGrdDate());
                            idGRD=dateCode.getGrdCode();*/

                    tv_grdDate.setText("");

                    loadReason(mGraduation.getCountryCode(), mGraduation.getProgram_code(), mGraduation.getService_code());
                    Toast.makeText(mContext, "The data is delete", Toast.LENGTH_SHORT).show();
                } catch (ParseException pe) {
                    pe.printStackTrace();
                    Toast.makeText(mContext, "Wrong Date Format, parse error!", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    private void saveMemberGraduationData() {
        if (sqlH.ifExistsInRegNAssProgSrv(mGraduation)) {
            sqlH.editMemberDataIn_RegNAsgProgSrv(getSrtGrdDate(), idGRD, EntryBy, EntryDate, mGraduation.getCountryCode(), mGraduation.getDistrictCode(), mGraduation.getUpazillaCode(),
                    mGraduation.getUnitCode(), mGraduation.getVillageCode(), mGraduation.getHh_id(),
                    mGraduation.getMember_Id(), mGraduation.getProgram_code(),
                    mGraduation.getService_code(), mGraduation.getDonor_code(), mGraduation.getAward_code());

            SQLServerSyntaxGenerator graduationQuery = new SQLServerSyntaxGenerator();
            graduationQuery.setAdmCountryCode(mGraduation.getCountryCode());
            graduationQuery.setLayR1ListCode(mGraduation.getDistrictCode());
            graduationQuery.setLayR2ListCode(mGraduation.getUpazillaCode());
            graduationQuery.setLayR3ListCode(mGraduation.getUnitCode());
            graduationQuery.setLayR4ListCode(mGraduation.getVillageCode());
            graduationQuery.setAdmAwardCode(mGraduation.getAward_code());
            graduationQuery.setAdmDonorCode(mGraduation.getDonor_code());
            graduationQuery.setHHID(mGraduation.getHh_id());
            graduationQuery.setMemID(mGraduation.getMember_Id());
            graduationQuery.setProgCode(mGraduation.getProgram_code());
            graduationQuery.setSrvCode(mGraduation.getService_code());
            graduationQuery.setGRDCode(idGRD);
            graduationQuery.setGRDDate(getSrtGrdDate());

            graduationQuery.setEntryBy(EntryBy);
            graduationQuery.setEntryDate(EntryDate);
            sqlH.insertIntoUploadTable(graduationQuery.updateGraduation());

            Toast.makeText(mContext, "The data is saved", Toast.LENGTH_SHORT).show();
        }

    } /*{
        boolean invalid = false;

        try {
            EntryBy = getStaffID();
            EntryDate = getDateTime();
            HashMap<String, String> dateRange = sqlH.getDateRange(mGraduation.getCountryCode());
            String start_date = dateRange.get("sdate");
            String end_date = dateRange.get("edate");
            Log.d(TAG, " start_date :" + start_date + " end_date " + end_date);
            if (tv_grdDate != null && start_date != null && end_date != null) {
                if (!getValidDateRange(getSrtGrdDate(), start_date, end_date)) {
                    invalid = true;
                    Log.d(TAG, " invalid " + invalid + " cause grd date ");
                    dialog.showInvalidDialog(mContext, "Date", "Invalid date specified");
                    //  Toast.makeText(mContext, "Registration date is not a valid date! Please select a valid date within the range!!", Toast.LENGTH_LONG).show();

                } else if (Integer.parseInt(idGRD) < 0) {
                    invalid = true;
                    Log.d(TAG, " invalid " + invalid);
                    Toast.makeText(mContext, "the reason is not Selected ! Please select Reason!!", Toast.LENGTH_LONG).show();
                } else if (invalid == false) {
                    Log.d(TAG, " invalid " + invalid);

                    if (sqlH.ifExistsInRegNAssProgSrv(mGraduation)) {
                        *//** the update operation  for local database*//*
                        sqlH.editMemberDataIn_RegNAsgProgSrv(getSrtGrdDate(), idGRD, EntryBy, EntryDate, mGraduation.getCountryCode(), mGraduation.getDistrictCode(), mGraduation.getUpazillaCode(),
                                mGraduation.getUnitCode(), mGraduation.getVillageCode(), mGraduation.getHh_id(),
                                mGraduation.getMember_Id(), mGraduation.getProgram_code(),
                                mGraduation.getService_code(), mGraduation.getDonor_code(), mGraduation.getAward_code());
                        */

    /**
     * the update operation  for SQL Server or Web database
     *//*
                        SQLServerSyntaxGenerator graduationQuery = new SQLServerSyntaxGenerator();
                        graduationQuery.setAdmCountryCode(mGraduation.getCountryCode());
                        graduationQuery.setLayR1ListCode(mGraduation.getDistrictCode());
                        graduationQuery.setLayR2ListCode(mGraduation.getUpazillaCode());
                        graduationQuery.setLayR3ListCode(mGraduation.getUnitCode());
                        graduationQuery.setLayR4ListCode(mGraduation.getVillageCode());
                        graduationQuery.setAdmAwardCode(mGraduation.getAward_code());
                        graduationQuery.setAdmDonorCode(mGraduation.getDonor_code());
                        graduationQuery.setHHID(mGraduation.getHh_id());
                        graduationQuery.setMemID(mGraduation.getMember_Id());
                        graduationQuery.setProgCode(mGraduation.getProgram_code());
                        graduationQuery.setSrvCode(mGraduation.getService_code());
                        graduationQuery.setGRDCode(idGRD);
                        graduationQuery.setGRDDate(getSrtGrdDate());

                        graduationQuery.setEntryBy(EntryBy);
                        graduationQuery.setEntryDate(EntryDate);
                        sqlH.insertIntoUploadTable(graduationQuery.updateGraduation());

                        Toast.makeText(mContext, "The data is saved", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "This data is not exit in RegNAssSrvProg!", Toast.LENGTH_SHORT).show();
                    }
                }

            } else {
                Toast.makeText(mContext, "No valid date range found!", Toast.LENGTH_SHORT).show();
            }
        } catch (ParseException pe) {
            pe.printStackTrace();
            Toast.makeText(mContext, "Wrong Date Format, parse error!", Toast.LENGTH_SHORT).show();
        }
    }*/
    private void viewReference() {
        /**
         * Text View
         */

        tv_award = (TextView) findViewById(R.id.tv_gradu_award);
        tv_program = (TextView) findViewById(R.id.tv_gradu_Program);
        tv_criteria = (TextView) findViewById(R.id.tv_gradu_Criteria);
        tv_village = (TextView) findViewById(R.id.tv_gradu_Village);

        tv_memberId = (TextView) findViewById(R.id.tv_gradu_MemberID);
        tv_memberName = (TextView) findViewById(R.id.tv_gradu_MemberName);
        tv_grdDate = (TextView) findViewById(R.id.tv_gradu_GRDDate);

        sp_grdReason = (Spinner) findViewById(R.id.spGraduationReason);

        btnBackToGraduation = (Button) findViewById(R.id.btnRegisterFooter);


        btnSave = (Button) findViewById(R.id.btn_Graduation_Save);

        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnDelete = (Button) findViewById(R.id.btn_Graduation_Delete);


    }

    /**
     * LOAD :: Reason
     */
    private void loadReason(String cCode, String pCode, String srvCode) { // Graduation Reason
        int position = 0;

        String criteria = " WHERE " + SQLiteHandler.ADM_PROG_CODE_COL + " = '" + pCode + "' And " + SQLiteHandler.ADM_SRV_CODE_COL +
                " = '" + srvCode + "' And " + SQLiteHandler.DEFAULT_CAT_ACTIVE_COL + " = 'N' And " +
                SQLiteHandler.DEFAULT_CAT_EXIT_COL + " = 'N' ORDER BY " + SQLiteHandler.GRD_TITLE_COL;

        // Spinner Drop down elements for District
        List<SpinnerHelper> listDistrict = sqlH.getListAndID(SQLiteHandler.REG_N_LUP_GRADUATION_TABLE, criteria, cCode, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listDistrict);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        sp_grdReason.setAdapter(dataAdapter);


        if (idGRD != null) {
            for (int i = 0; i < sp_grdReason.getCount(); i++) {
                String district = sp_grdReason.getItemAtPosition(i).toString();
                if (district.equals(strGRDTitle)) {
                    position = i;
                }
            }
            sp_grdReason.setSelection(position);
        }


        sp_grdReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                strGRDTitle = ((SpinnerHelper) sp_grdReason.getSelectedItem()).getValue();
                idGRD = ((SpinnerHelper) sp_grdReason.getSelectedItem()).getId();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner Reason


    /**
     * Time picker will appear
     */
    public void setGraduationDate() {
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

    public void updateRegDate() {


        setSrtGrdDate(format.format(calendar.getTime()));
        tv_grdDate.setText(formatUSA.format(calendar.getTime()));
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
        setUpBackButton();
        setUpHomeButton();
        setUpSaveButton();
        setUpDeleteButton();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpBackButton() {
        btnBackToGraduation.setText("");
        Drawable backImage = getResources().getDrawable(R.drawable.goto_back);
        btnBackToGraduation.setCompoundDrawablesRelativeWithIntrinsicBounds(backImage, null, null, null);
        setPaddingButton(mContext, backImage, btnBackToGraduation);
    }

    /**
     * Icon set by the method
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpHomeButton() {
        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, btnHome);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpSaveButton() {
        btnSave.setText("");
        Drawable imageSave = getResources().getDrawable(R.drawable.save_b);
        btnSave.setCompoundDrawablesRelativeWithIntrinsicBounds(imageSave, null, null, null);
        setPaddingButton(mContext, imageSave, btnSave);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpDeleteButton() {
        btnDelete.setText("");
        Drawable imageDelete = getResources().getDrawable(R.drawable.delete);
        btnDelete.setCompoundDrawablesRelativeWithIntrinsicBounds(imageDelete, null, null, null);
        setPaddingButton(mContext, imageDelete, btnDelete);
    }


    /**
     * to off Back press button
     */

    @Override
    public void onBackPressed() {
    }
}
