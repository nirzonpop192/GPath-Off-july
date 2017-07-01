package com.siddiquinoor.restclient.activity.sub_activity.register_sub;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.AssignActivity;
import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.activity.Register;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.activity.sub_activity.summary_sub.ViewRecordDetail;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.ListDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Siddiqui on 5/4/2015.
 */
public class RegisterMember extends BaseActivity {

    private static final String TAG = RegisterMember.class.getSimpleName();

    Context mContext;


    private Button btnReg;
    private Button btnRecords;
    private Button btnSummary;
    private Button btnHome;
    private Button btngotToAssigne;
    private Button btn_mem_gotoHhDetails;

    private Button btnSaveMember;

    private String str_c_code;
    private String country_name;

    private String str_district;
    private String str_upazilla;
    private String str_union;
    private String str_village;

    private String layR1ListCode;
    private String layR2ListCode;
    private String str_unionCode;
    private String str_villageCode;


    private String str_entry_by;
    private String str_entry_date;
    private String str_agland;
    private String str_reg_date;


    private TextView tvHHID;
    private String str_hhID;

    private TextView tvHHName;
    private String str_hhName;

    private TextView tvMemID;
    private String str_hhMemID;

    private EditText txtMemName;
    private String str_MemName;

    private Spinner spGender;
    private String str_gender;

    private EditText txtAge;
    private String str_age;

    private Spinner spRelation;
    private String str_relation;
    private String idRelation;
    private String str_relation_code;

    private SQLiteHandler sqlH;

    private boolean is_edit = false;
    private int pID = 0;

    // for member edit
    private int mID = 0;
    private String hhID = null;
    private String MemID = null;
    private String MemberName = null;
    private String gender = null;


    private String lmp_date = null;
    private String child_dob = null;


    private String str_elderly = "";
    private String str_disabled = "";
    // private String idDonor;
    private String age = null;

    private String redirect = "";

    private Intent cIntent = null;

    int position = 0;

    private String criteria = "";
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private Calendar calendar = Calendar.getInstance();
    private Calendar calendarChildDOB = Calendar.getInstance();

    private Button btn_Clear;


    private ADNotificationManager dialog;
    private boolean isMemberSaved;

    public boolean isMemberSaved() {
        return isMemberSaved;
    }

    public void setIsMemberSaved(boolean isMemberSaved) {
        this.isMemberSaved = isMemberSaved;
    }

    LinearLayout lLayoutLiberiaContainer;
    LinearLayout lLayoutMalawiContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.reg_nhh_mem_schema);
        mContext = this;
        dialog = new ADNotificationManager();
        setIsMemberSaved(false);

        // SqLite database handler
        sqlH = new SQLiteHandler(getApplicationContext());
        viewReference();

        btngotToAssigne.setEnabled(false);
        btnSaveMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMemberData();
            }
        });


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iReg = new Intent(getApplicationContext(), Register.class);
                finish();
                iReg.putExtra("country_code", str_c_code);
                iReg.putExtra(KEY.DISTRICT, str_district);
                iReg.putExtra(KEY.UPAZILLA, str_upazilla);
                iReg.putExtra(KEY.UNIT, str_union);
                iReg.putExtra(KEY.VILLAGE_NAME, str_village);
                iReg.putExtra(KEY.DISTRICT_CODE, layR1ListCode);
                iReg.putExtra(KEY.UPAZILLA_CODE, layR2ListCode);
                iReg.putExtra(KEY.UNIT_CODE, str_unionCode);
                iReg.putExtra(KEY.VILLAGE_CODE, str_villageCode);
                Log.d("REFAT--->", str_district + "\n" + str_upazilla + "\n" + str_union + "\n" + str_village
                        + "\n" + layR1ListCode + "\n" + layR2ListCode + "\n" + str_unionCode
                        + "\n" + str_villageCode);


                startActivity(iReg);

            }
        });


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Intent iHome = new Intent(RegisterMember.this, MainActivity.class);
                startActivity(iHome);
            }
        });


        btn_mem_gotoHhDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoHouseholdDetailsPage();

            }
        });


        btngotToAssigne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAssigneNewMehod();

            }
        });

        btn_Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearField();
            }
        });


        cIntent = getIntent();

        is_edit = cIntent.getBooleanExtra("is_edit", false);
        str_hhID = cIntent.getStringExtra("str_hhID");
        str_hhName = cIntent.getStringExtra("str_hhName");
        redirect = cIntent.getStringExtra("redirect");
        str_c_code = cIntent.getStringExtra("str_c_code");
        str_district = cIntent.getStringExtra("str_district");
        str_upazilla = cIntent.getStringExtra("str_upazilla");
        str_union = cIntent.getStringExtra("str_union");
        str_village = cIntent.getStringExtra("str_village");
        layR1ListCode = cIntent.getStringExtra("str_districtCode");
        layR2ListCode = cIntent.getStringExtra("str_upazillaCode");
        str_unionCode = cIntent.getStringExtra("str_unionCode");
        str_villageCode = cIntent.getStringExtra("str_villageCode");

        str_entry_by = cIntent.getStringExtra("str_entry_by");
        str_entry_date = cIntent.getStringExtra("str_entry_date");

        pID = cIntent.getIntExtra("pID", 20);
        /** For Liberia Member Designe test
         * */

        // no need The Liberia Containnner
        lLayoutLiberiaContainer.setVisibility(View.GONE);

        if (!is_edit) {
            if (str_hhID.length() > 5) {
                setMemID(str_c_code, layR1ListCode, layR2ListCode, str_unionCode, str_villageCode, str_hhID.substring(8));
            } else {
                setMemID(str_c_code, layR1ListCode, layR2ListCode, str_unionCode, str_villageCode, str_hhID);
            }


        } else {
            // When Edit
            btngotToAssigne.setEnabled(true);
            // btnSaveMember.setText("UPDATE");
            mID = cIntent.getIntExtra("mId", 20);

            //str_hhID = cIntent.getStringExtra("str_hhID");
            MemID = cIntent.getStringExtra("MemID");
            MemberName = cIntent.getStringExtra("MemberName");


            gender = cIntent.getStringExtra("gender");
            idRelation = cIntent.getStringExtra("relation");
            str_relation = sqlH.getRelationString(idRelation);

            lmp_date = cIntent.getStringExtra("LMPDate");
            child_dob = cIntent.getStringExtra("ChildDOB");
            str_elderly = "";// cIntent.getStringExtra("Elderly");
            str_disabled = "";// cIntent.getStringExtra("Disabled");
            age = cIntent.getStringExtra("age");
            tvMemID.setText(MemID);
            txtMemName.setText(MemberName);
            // lmpDate.setText(lmp_date);
            //  childDOB.setText(child_dob);
            txtAge.setText(age);
        }
        if (redirect != null) {
        /*    if (redirect.equals("Sub_Assign")) {
                //  strAward = cIntent.getStringExtra(KEY.AWARD_NAME);
                idAward = cIntent.getStringExtra(KEY.AWARD_CODE);
                idProgram = cIntent.getStringExtra(KEY.PROGRAM_CODE);
                strProgram = cIntent.getStringExtra(KEY.PROGRAM_NAME);
                idDonor = cIntent.getStringExtra("DONOR_CODE");
                idCriteria = cIntent.getStringExtra("CRITERIA_CODE");
                strCriteria = cIntent.getStringExtra("CRITERIA_STR");
            }*/
        }

        tvHHID.setText(str_hhID);
        tvHHName.setText(str_hhName);

//        loadAward(str_c_code);
        loadGender();
        loadRelation();


        tvHHID.setEnabled(false);
        tvHHName.setEnabled(false);
        tvMemID.setEnabled(false);
        // Set focus to Member Name
        txtMemName.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        imm.showSoftInput(txtMemName, InputMethodManager.SHOW_IMPLICIT);


    }

    private void gotoHouseholdDetailsPage() {
        String temHH;
        if (str_hhID.length() > 5) {
            temHH = str_hhID.substring(8);
        } else {
            temHH = str_hhID;
        }

        ListDataModel data = sqlH.getSingleRegisteredData(str_c_code, layR1ListCode, layR2ListCode, str_unionCode, str_villageCode, temHH);

        Intent dIntent = new Intent(RegisterMember.this, ViewRecordDetail.class);

        String temp_country_name = data.getCountryName();
        String district = data.getDistrict();
        String upazilla = data.getUpazilla();
        String unit = data.getUnitName();
        String village = data.getVillage();

        String country_code = data.getCountryCode();
        String districtCode = data.getDistrictCode();
        String upazillaCode = data.getUpazillaCode();
        String unitCode = data.getUnitNameCode();
        String villageCode = data.getVillageCode();


        String regID = districtCode + upazillaCode + unitCode + villageCode + data.getRegID();
        String regDate = data.getRegDate();
        String personName = data.getName();
        String sex = data.getSex();
        String hhSize = data.getHhSize();
        String latitude = data.getLatitude();
        String longitude = data.getLongitude();
        String agLand = data.getAgLand();
        String vstatus = data.getvStatus();
        String mstatus = data.getmStatus();
        String entryBy = data.getEntryBy();
        String entryDate = data.getEntryDate();
        String vsla_group = data.getVSLAGroup();

        int pId = data.getID();


        dIntent.putExtra(KEY.COUNTRY_NAME, temp_country_name);
        dIntent.putExtra(KEY.DISTRICT, district);
        dIntent.putExtra(KEY.UPAZILLA, upazilla);
        dIntent.putExtra(KEY.UNIT, unit);
        dIntent.putExtra(KEY.VILLAGE_NAME, village);
        dIntent.putExtra(KEY.COUNTRY_CODE, country_code);
        dIntent.putExtra(KEY.DISTRICT_CODE, districtCode);
        dIntent.putExtra(KEY.UPAZILLA_CODE, upazillaCode);
        dIntent.putExtra(KEY.UNIT_CODE, unitCode);
        dIntent.putExtra(KEY.VILLAGE_CODE, villageCode);
        dIntent.putExtra(KEY.REG_ID, regID);
        dIntent.putExtra(KEY.REG_DATE, regDate);
        dIntent.putExtra(KEY.PERSON_NAME, personName);
        dIntent.putExtra(KEY.SEX, sex);
        dIntent.putExtra(KEY.HH_SIZE, hhSize);
        dIntent.putExtra(KEY.LATITUDE, latitude);
        dIntent.putExtra(KEY.LONGITUDE, longitude);
        dIntent.putExtra(KEY.AG_LAND, agLand);
        dIntent.putExtra(KEY.VSTATUS, vstatus);
        dIntent.putExtra(KEY.MSTATUS, mstatus);
        dIntent.putExtra(KEY.ENTRY_BY, entryBy);
        dIntent.putExtra(KEY.ENTRY_DATE, entryDate);
        dIntent.putExtra(KEY.VSLA_GROUP, vsla_group);
        dIntent.putExtra(KEY.P_ID, pId);
        finish();

        startActivity(dIntent);
    }

    private void clearField() {

        tvMemID.setText("");
        str_hhMemID = "";

        txtMemName.setText("");
        str_MemName = "";
        txtAge.setText("");
        str_age = "";
        idRelation = "";
        str_relation = "";

        setMemID(str_c_code, layR1ListCode, layR2ListCode, str_unionCode, str_villageCode, tvHHID.getText().toString().substring(8));


        loadGender();
        loadRelation();
        //  btnSaveMember.setText("Save");
    }


    private void goToAssigneNewMehod() {


        Intent intent = new Intent(RegisterMember.this, AssignActivity.class);
        finish();
        String tmpMemberCode = tvHHID.getText().toString() + tvMemID.getText().toString();
//        Log.d("MOR", "tmpMemberCode:" + tmpMemberCode + "str_c_code:" + str_c_code);
        intent.putExtra(KEY.COUNTRY_ID, str_c_code);
        intent.putExtra(KEY.MEMBER_ID, tmpMemberCode);
        startActivity(intent);

    }


    private void saveMemberData() {
        str_hhMemID = tvMemID.getText().toString();
        str_MemName = txtMemName.getText().toString();
        str_age = txtAge.getText().toString();


        String lmp_date = "";// lmpDate.getText().toString();
        String child_dob = "";// childDOB.getText().toString();

        //  boolean invalid = false;


        // TODO :: Need to check valid date range collect from online

        if (str_hhMemID.equals("")) {

            Toast.makeText(getApplicationContext(), "Enter any ID", Toast.LENGTH_SHORT).show();
        } else if (str_MemName.equals("")) {

            dialog.showErrorDialog(mContext, "Missing Name. Enter Person Name");

        } else if (str_age.equals("")) {

            dialog.showErrorDialog(mContext, "Missing Age. Please select a Age");

        } else if (Integer.valueOf(str_age) > 99) {

            dialog.showErrorDialog(mContext, "Age exceeds allowable range.");

        } else if (str_relation.equals("Select Relation")) {

            dialog.showErrorDialog(mContext, "Missing relation. Please select a Relation");

        } else {
            String temHH;
            if (str_hhID.length() > 5) {
                temHH = str_hhID.substring(8);
            } else {
                temHH = str_hhID;
            }
            if (is_edit) {


                String memAgeTypeFlag = sqlH.getMemberAgeTypeFlag(str_c_code, layR1ListCode, layR2ListCode, str_unionCode, str_villageCode, temHH, str_hhMemID);

//                Log.e("shuvoTest",memAgeFlg);

                sqlH.editMalawiMemberData(str_c_code, layR1ListCode, layR2ListCode, str_unionCode, str_villageCode, temHH, str_hhMemID, str_MemName, str_gender, idRelation, lmp_date, child_dob, str_elderly, str_disabled, str_age, pID, memAgeTypeFlag);

                // Update Member data
                Toast.makeText(mContext, "The member has been uploaded  ", Toast.LENGTH_SHORT).show();
                setIsMemberSaved(true);
            } else {
                /**
                 * Insert procedure
                 * */
                String temId;
                if (tvHHID.getText().toString().length() > 5)
                    temId = tvHHID.getText().toString().substring(8);
                else
                    temId = tvHHID.getText().toString();

                ListDataModel data = sqlH.getSingleRegisteredData(str_c_code, layR1ListCode, layR2ListCode, str_unionCode, str_villageCode, temId);
                String memAgeFlag = sqlH.getMemberAgeTypeFlag(str_c_code, layR1ListCode, layR2ListCode, str_unionCode, str_villageCode, temId, str_hhMemID);

                sqlH.addMemberDataForMalawi(str_c_code, layR1ListCode, layR2ListCode, str_unionCode, str_villageCode, temId, str_hhMemID, str_MemName, str_gender, idRelation, str_entry_by, str_entry_date, lmp_date, child_dob, str_elderly, str_disabled, str_age, data.getRegDate(), pID, memAgeFlag);


//                Log.e("shuvoTest",memAgeFlag);

                Toast.makeText(getApplicationContext(), "save successfully", Toast.LENGTH_LONG).show();
                setIsMemberSaved(true);

            }
        }
        /**
         * When the member is saved than the Assign controller will appears */
        /**
         * todo: enable asssigne Button
         *
         */
        btngotToAssigne.setEnabled(true);

    }

    private void setMemID(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String hhID) {
        String next_id = sqlH.getMemberID(str_c_code, str_district, str_upazilla, str_union, str_village, hhID);
        tvMemID.setText(next_id);
        Log.d(TAG, "Member ID is = " + next_id);
    }

    /**
     * LOAD :: Relation
     */
    private void loadRelation() {

        // Spinner Drop down elements for District
        List<SpinnerHelper> listRelation = sqlH.getListAndID(sqlH.RELATION_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listRelation);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spRelation.setAdapter(dataAdapter);

        if (idRelation != null) {
            for (int i = 0; i < spRelation.getCount(); i++) {
                String relation = spRelation.getItemAtPosition(i).toString();
                if (relation.equals(str_relation)) {
                    position = i;
                }
            }
            spRelation.setSelection(position);
        }


        spRelation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                str_relation = ((SpinnerHelper) spRelation.getSelectedItem()).getValue();
                idRelation = ((SpinnerHelper) spRelation.getSelectedItem()).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * LOAD :: GENDER
     */
    private void loadGender() {

        spGender = (Spinner) findViewById(R.id.spGender);
        ArrayAdapter<CharSequence> adtGender = ArrayAdapter.createFromResource(
                this, R.array.spGenderItem, R.layout.spinner_layout);

        adtGender.setDropDownViewResource(R.layout.spinner_layout);
        spGender.setAdapter(adtGender);

        if (gender == null || !gender.equals("M"))
            gender = "Female";
        else
            gender = "Male";


        spGender.setSelection(getSpinnerIndex(spGender, gender));

        spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                str_gender = parent.getItemAtPosition(position).toString();

                if (str_gender.equals("Male"))
                    str_gender = "M";
                else
                    str_gender = "F";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


//    public void updateDateLMB() {
//        /*lmpDate.setText(format.format( calendar.getTime()));*/
//    }

//    public void setDateLMB() {
//        new DatePickerDialog(RegisterMember.this, dpLMB, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
//    }

//    DatePickerDialog.OnDateSetListener dpLMB = new DatePickerDialog.OnDateSetListener() {
//
//        @Override
//        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//            calendar.set(Calendar.YEAR, year);
//            calendar.set(Calendar.MONTH, monthOfYear);
//            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//            updateDateLMB();
//        }
//    };


/*    public void updateChildDOBDate() {
        //childDOB.setText(format.format( calendarChildDOB.getTime()));
    }*/

//    public void setDateChildDOB() {
//        new DatePickerDialog(RegisterMember.this, dpChildDOB, calendarChildDOB.get(Calendar.YEAR), calendarChildDOB.get(Calendar.MONTH), calendarChildDOB.get(Calendar.DAY_OF_MONTH)).show();
//    }

//    DatePickerDialog.OnDateSetListener dpChildDOB = new DatePickerDialog.OnDateSetListener() {
//
//        @Override
//        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//            calendarChildDOB.set(Calendar.YEAR, year);
//            calendarChildDOB.set(Calendar.MONTH, monthOfYear);
//            calendarChildDOB.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//            updateChildDOBDate();
//        }
//    };


    private void viewReference() {
        tvHHID = (TextView) findViewById(R.id.tv_Mreg_HH_id);
        tvHHName = (TextView) findViewById(R.id.tv_reg_mem_hh_name);
        tvMemID = (TextView) findViewById(R.id.mem_id);   // Member ID
        txtMemName = (EditText) findViewById(R.id.mem_name);
        spGender = (Spinner) findViewById(R.id.spGender);
        txtAge = (EditText) findViewById(R.id.mem_age);
        spRelation = (Spinner) findViewById(R.id.spRelation);
        lLayoutLiberiaContainer = (LinearLayout) findViewById(R.id.lLayout_LiberiaViews); // to control layout
        lLayoutMalawiContainer = (LinearLayout) findViewById(R.id.lLayout_malawiViews); // to control layout

        btnSaveMember = (Button) findViewById(R.id.btnSaveMember);
        btngotToAssigne = (Button) findViewById(R.id.btn_mem_gotoAssign);
        btn_mem_gotoHhDetails = (Button) findViewById(R.id.btn_mem_gotoHouseholdDetails);
        btn_Clear = (Button) findViewById(R.id.btnClearMember);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnReg = (Button) findViewById(R.id.btnRegisterFooter);

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

        setHomeButtonIcon();
        setHhDetailsIcon();
        setSaveButtonIcon();
        setButtonClearIcon();
        setRegNHouseHoldIcon();
        setAssignIcon();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setHhDetailsIcon() {
        btn_mem_gotoHhDetails.setText("");
        Drawable imageHHDetails = getResources().getDrawable(R.drawable.hh_details);
        btn_mem_gotoHhDetails.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHHDetails, null, null, null);
        setPaddingButton(mContext, imageHHDetails, btn_mem_gotoHhDetails);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setHomeButtonIcon() {
        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, btnHome);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setSaveButtonIcon() {
        btnSaveMember.setText("");
        Drawable imageSave = getResources().getDrawable(R.drawable.save_b);
        btnSaveMember.setCompoundDrawablesRelativeWithIntrinsicBounds(imageSave, null, null, null);
        setPaddingButton(mContext, imageSave, btnSaveMember);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setButtonClearIcon() {
        btn_Clear.setText("");
        Drawable imageClear = getResources().getDrawable(R.drawable.clear_b);
        btn_Clear.setCompoundDrawablesRelativeWithIntrinsicBounds(imageClear, null, null, null);
        setPaddingButton(mContext, imageClear, btn_Clear);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setRegNHouseHoldIcon() {
        btnReg.setText("");
        Drawable addMemberIcon = getResources().getDrawable(R.drawable.registration);
        btnReg.setCompoundDrawablesRelativeWithIntrinsicBounds(addMemberIcon, null, null, null);
        setPaddingButton(mContext, addMemberIcon, btnReg);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setAssignIcon() {
        btngotToAssigne.setText("");
        Drawable assignIcon = getResources().getDrawable(R.drawable.assign);
        btngotToAssigne.setCompoundDrawablesRelativeWithIntrinsicBounds(assignIcon, null, null, null);
        setPaddingButton(mContext, assignIcon, btngotToAssigne);
    }


}
