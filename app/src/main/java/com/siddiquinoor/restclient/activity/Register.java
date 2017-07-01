package com.siddiquinoor.restclient.activity;

/**
 * Activity for data entry, the main business of the application
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.3.0
 * @since 1.0
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.activity.sub_activity.register_sub.RegisterMember;
import com.siddiquinoor.restclient.activity.sub_activity.summary_sub.RegisterRecordView;
import com.siddiquinoor.restclient.network.GPSTracker;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;
import com.siddiquinoor.restclient.views.spinner.SpinnerLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class Register extends BaseActivity {

    private static final String TAG = Register.class.getSimpleName();

    // GPSTracker class
    GPSTracker gps;
    private double latitude = 0;
    private double longitude = 0;

    private Button btnAddMember;
    private Button btnHome;
    private Button btnSaveData;
    private Button btnRegistrationRecode;
    private Button btnClear;
    private Button btnRegisterFooter;
    private CheckBox chkBxHhCat1, chkBxHhCat2, chkBxHhCat3, chkBxHhCat4, chkBxHhCat5, chkBxHhCat6;

    // Spinner
    private Spinner spCountry;
    private Spinner spDistrict, spUpazilla, spUnion, spVillage, spGender, spVStatus, spMStatus, spAddress;

    Spinner spWRank;
    private String strCountry;
    private String strDistrict;
    private String strUnion;
    private String strUpazilla;
    private String strVillage;
    private String strGender;
    private String strVStatus;
    private String strMStatus;
    private String strAddress;
    private String lTp2Hectres, lT3mFoodStock, noMajorCommonLiveStock, receiveNoFormalWages, noIGA, relyPiecework;

    private String idCountry;
    private String idDist;
    private String idUP;
    private String idUnion;
    private String idVill;
    private String idAddress;

    private EditText regId;
    private EditText regName;
    private EditText hhSize;
    private EditText txtLatitude;
    private EditText txtLongitude;
    //private EditText agLand;
    private EditText VSLA_group;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private Calendar calendar = Calendar.getInstance();
    private TextView reDOB;

    long temPIdStore;


    private SQLiteHandler sqlH;


    private String criteria;
    private String strRegDate;
    private String strWRank;
    private String idWRank;

    public String getStrRegDate() {
        return strRegDate;
    }

    public void setStrRegDate(String strRegDate) {
        this.strRegDate = strRegDate;
    }

    private boolean is_edit = false;
    private int pID = 0;

    int position = 0;
    boolean is_valid_date = false;

    String EntryBy;
    String EntryDate;
    String registeredId;
    String name;
    private ADNotificationManager dialog;
    private Context mContext;


    private TextView tv_LayR1Label;
    private TextView tv_LayR2Label;
    private TextView tv_LayR3Label;
    private TextView tv_LayR4Label;

    private SimpleDateFormat formatUSA = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.register);
        dialog = new ADNotificationManager();
        mContext = this;

        // SqLite database handler
        sqlH = new SQLiteHandler(getApplicationContext());
        gps = new GPSTracker(getApplicationContext());

        viewReference();


        // Getting GPS data
        // check if GPS enabled
        if (gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            txtLatitude.setText(String.valueOf(latitude));
            txtLongitude.setText(String.valueOf(longitude));

            // \n is for new line

        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            // TODO :: If needed then enable the alert to go to settings.
            //gps.showSettingsAlert();
        }

        setListener();

        regId.setEnabled(false);

        // Set focus to Registered name
        regName.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        imm.showSoftInput(regName, InputMethodManager.SHOW_IMPLICIT);

        // Country Code
        //idCountry = getCountryCode();

        // When Edit is passed
        Intent intnt = getIntent();

        is_edit = intnt.getBooleanExtra(KEY.IS_EDIT, false);
        strCountry = intnt.getStringExtra(KEY.COUNTRY_NAME);
        strDistrict = intnt.getStringExtra(KEY.DISTRICT);
        strUpazilla = intnt.getStringExtra(KEY.UPAZILLA);
        strUnion = intnt.getStringExtra(KEY.UNIT);
        strVillage = intnt.getStringExtra(KEY.VILLAGE_NAME);
        idCountry = intnt.getStringExtra(KEY.COUNTRY_CODE);
        idDist = intnt.getStringExtra(KEY.DISTRICT_CODE);
        idUP = intnt.getStringExtra(KEY.UPAZILLA_CODE);
        idUnion = intnt.getStringExtra(KEY.UNIT_CODE);
        idVill = intnt.getStringExtra(KEY.VILLAGE_CODE);
        regId.setText(intnt.getStringExtra(KEY.REG_ID));
        reDOB.setText(intnt.getStringExtra(KEY.REG_DATE));
        String regDate = intnt.getStringExtra(KEY.REG_DATE);
        regName.setText(intnt.getStringExtra(KEY.PERSON_NAME));
        strGender = intnt.getStringExtra(KEY.SEX);
        hhSize.setText(intnt.getStringExtra(KEY.HH_SIZE));
        txtLatitude.setText(intnt.getStringExtra(KEY.LATITUDE));
        txtLongitude.setText(intnt.getStringExtra(KEY.LONGITUDE));
        //agLand.setText(intnt.getStringExtra("agLand"));
        VSLA_group.setText(intnt.getStringExtra(KEY.VSLA_GROUP));

        strVStatus = intnt.getStringExtra(KEY.VSTATUS);
        strMStatus = intnt.getStringExtra(KEY.MSTATUS);
        idAddress = intnt.getStringExtra(KEY.ADDRESS_CODE);
        strAddress = intnt.getStringExtra(KEY.ADDRESS_NAME);

        idWRank = intnt.getStringExtra(KEY.WALTH_RANK);

        criteria = "";


        if (is_edit) {
            pID = intnt.getIntExtra("pID", -1);
            loadLayR1List(idCountry);
            loadLayR2List(idCountry);
            loadLayR3List(idCountry);
            loadLayR4List(idCountry, idDist, idUP, idUnion);


            chkBxHhCat1.setChecked(sqlH.getHouseHoldRegistrationIsChecked(SQLiteHandler.LTP_2_HECTRES_COL, idCountry, idDist, idUP, idUnion, idVill, intnt.getStringExtra(KEY.REG_ID)));
            chkBxHhCat2.setChecked(sqlH.getHouseHoldRegistrationIsChecked(SQLiteHandler.LT_3_FOOD_STOCK_COL, idCountry, idDist, idUP, idUnion, idVill, intnt.getStringExtra(KEY.REG_ID)));
            chkBxHhCat3.setChecked(sqlH.getHouseHoldRegistrationIsChecked(SQLiteHandler.NO_MAJOR_COMMON_LIVE_STOCK_COL, idCountry, idDist, idUP, idUnion, idVill, intnt.getStringExtra(KEY.REG_ID)));
            chkBxHhCat4.setChecked(sqlH.getHouseHoldRegistrationIsChecked(SQLiteHandler.RECEIVE_NO_FORMAL_WAGES_COL, idCountry, idDist, idUP, idUnion, idVill, intnt.getStringExtra(KEY.REG_ID)));
            chkBxHhCat5.setChecked(sqlH.getHouseHoldRegistrationIsChecked(SQLiteHandler.NO_IGA_COL, idCountry, idDist, idUP, idUnion, idVill, intnt.getStringExtra(KEY.REG_ID)));
            chkBxHhCat6.setChecked(sqlH.getHouseHoldRegistrationIsChecked(SQLiteHandler.RELY_PICE_EORK_COL, idCountry, idDist, idUP, idUnion, idVill, intnt.getStringExtra(KEY.REG_ID)));

        }

        if (!is_edit) {
            updateDate();
        }

        loadGender();
        loadVStatus();
        loadMStatus();
        loadWRank();

        btnAddMember.setEnabled(false);
        // btnAddMember.setTextColor(getResources().getColor(R.color.input_label_hint));


    }

    private void setListener() {

        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (idDist.equals("00"))
                    dialog.showErrorDialog(mContext, "Select " + tv_LayR1Label.getText());
                else if (idUP.equals("00"))
                    dialog.showErrorDialog(mContext, "Select " + tv_LayR2Label.getText());
                else if (idUnion.equals("00"))
                    dialog.showErrorDialog(mContext, "Select " + tv_LayR3Label.getText());
                else if (idVill.equals("00"))
                    dialog.showErrorDialog(mContext, "Select " + tv_LayR4Label.getText());

                else if (idAddress.equals("00")) {
                    dialog.showErrorDialog(mContext, "Select Address please");
                } else {
                    saveData();
                }

            }
        });
        btnRegistrationRecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idVill.equals("00")) {
                    Intent intent = new Intent(Register.this, RegisterRecordView.class);
                    intent.putExtra("village_code", idCountry + idDist + idUP + idUnion + idVill);
                    intent.putExtra("village", strVillage);
                    finish();
                    startActivity(intent);
                }


            }
        });

        //   btnAddMember.setText("ADD MEMBER");
        btnAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoTheMemberRegistrationPage();
            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });

        reDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity((Activity) mContext);
            }
        });


    }


    private void viewReference() {
        // spCountry = (Spinner) findViewById(R.id.spCountry);
        spDistrict = (Spinner) findViewById(R.id.spDistrict);
        spUpazilla = (Spinner) findViewById(R.id.spUpazilla);
        spUnion = (Spinner) findViewById(R.id.spUnion);
        spVillage = (Spinner) findViewById(R.id.spVillage);
        spGender = (Spinner) findViewById(R.id.spGender);
        regId = (EditText) findViewById(R.id.reg_id);
        regName = (EditText) findViewById(R.id.reg_name);
        reDOB = (TextView) findViewById(R.id.hh_reg_date);
        hhSize = (EditText) findViewById(R.id.hh_size);
        txtLatitude = (EditText) findViewById(R.id.latitude);
        txtLongitude = (EditText) findViewById(R.id.longitude);
        // agLand = (EditText)findViewById(R.id.ag_land);
        VSLA_group = (EditText) findViewById(R.id.VSLA_group);
        tv_LayR1Label = (TextView) findViewById(R.id.tv_hh_LayR1Label);
        tv_LayR2Label = (TextView) findViewById(R.id.tv_hh_LayR2Label);
        tv_LayR3Label = (TextView) findViewById(R.id.tv_hh_LayR3Label);
        tv_LayR4Label = (TextView) findViewById(R.id.tv_hh_LayR4Label);
        btnSaveData = (Button) findViewById(R.id.btnSaveData);
        btnRegistrationRecode = (Button) findViewById(R.id.btnRegistrationRecode);
        btnAddMember = (Button) findViewById(R.id.btnRegisterFooter);
        btnClear = (Button) findViewById(R.id.btnClearData);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        spWRank = (Spinner) findViewById(R.id.spWRank);


        chkBxHhCat1 = (CheckBox) findViewById(R.id.cb_hh_type_1);
        chkBxHhCat2 = (CheckBox) findViewById(R.id.cb_hh_type_2);
        chkBxHhCat3 = (CheckBox) findViewById(R.id.cb_hh_type_3);
        chkBxHhCat4 = (CheckBox) findViewById(R.id.cb_hh_type_4);
        chkBxHhCat5 = (CheckBox) findViewById(R.id.cb_hh_type_5);
        chkBxHhCat6 = (CheckBox) findViewById(R.id.cb_hh_type_6);


    }

    private void setLayRLable() {

        tv_LayR1Label.setText(UtilClass.getLayR1LabelName(mContext, idCountry));
        tv_LayR2Label.setText(UtilClass.getLayR2LabelName(mContext, idCountry));
        tv_LayR3Label.setText(UtilClass.getLayR3LabelName(mContext, idCountry));
        tv_LayR4Label.setText(UtilClass.getLayR4LabelName(mContext, idCountry));

    }

    // on the resume mode the district spinner will load other wise it didn't work
    @Override
    protected void onResume() {
        super.onResume();
        loadLayR1List(idCountry);
        setLayRLable();
    }


    private void gotoTheMemberRegistrationPage() {
        finish();

        EntryBy = getStaffID();
        try {
            EntryDate = getDateTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        registeredId = regId.getText().toString();

        name = regName.getText().toString();

        Intent dIntent = new Intent(Register.this, RegisterMember.class);

        dIntent.putExtra("redirect", "");
        dIntent.putExtra("page_from", "Register");
        dIntent.putExtra("str_country", strCountry);
        dIntent.putExtra("str_district", strDistrict);
        dIntent.putExtra("str_upazilla", strUpazilla);
        dIntent.putExtra("str_union", strUnion);
        dIntent.putExtra("str_village", strVillage);
        dIntent.putExtra("str_c_code", idCountry);
        dIntent.putExtra("str_districtCode", idDist);
        dIntent.putExtra("str_upazillaCode", idUP);
        dIntent.putExtra("str_unionCode", idUnion);
        dIntent.putExtra("str_villageCode", idVill);
        dIntent.putExtra("str_entry_by", EntryBy);
        dIntent.putExtra("str_entry_date", EntryDate);
        dIntent.putExtra("str_hhID", idDist + idUP + idUnion + idVill + registeredId);
        dIntent.putExtra("str_hhName", name);

        startActivity(dIntent);
    }

    private void clearData() {
        txtLatitude.setText(String.valueOf(latitude));
        txtLongitude.setText(String.valueOf(longitude));
        // regId.setText("");
        regName.setText("");
        reDOB.setText("");
        hhSize.setText("");
        txtLatitude.setText("");
        txtLongitude.setText("");
        VSLA_group.setText("");
        loadMStatus();
        loadVStatus();
        loadWRank();

        String next_id = sqlH.getRegistrationID(idCountry, idDist, idUP, idUnion, idVill);
        regId.setText(next_id);

        btnAddMember.setEnabled(false);
        //  btnAddMember.setTextColor(getResources().getColor(R.color.input_label_hint));


        chkBxHhCat1.setChecked(false);
        chkBxHhCat2.setChecked(false);
        chkBxHhCat3.setChecked(false);
        chkBxHhCat4.setChecked(false);
        chkBxHhCat5.setChecked(false);
        chkBxHhCat6.setChecked(false);

            /*    Intent intent = getIntent();
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);*/
    }

    private void saveData() {

        lTp2Hectres = lT3mFoodStock = noMajorCommonLiveStock = receiveNoFormalWages = noIGA = relyPiecework = "N";


        if (chkBxHhCat1.isChecked()) {
            lTp2Hectres = "Y";

        }

        if (chkBxHhCat2.isChecked()) {
            lT3mFoodStock = "Y";

        }

        if (chkBxHhCat3.isChecked()) {
            noMajorCommonLiveStock = "Y";

        }

        if (chkBxHhCat4.isChecked()) {
            receiveNoFormalWages = "Y";
        }

        if (chkBxHhCat5.isChecked()) {
            noIGA = "Y";
        }
        if (chkBxHhCat6.isChecked()) {
            relyPiecework = "Y";
        }


        registeredId = regId.getText().toString();
        name = regName.getText().toString().trim();
        String regDate = reDOB.getText().toString();

        String HHSize = hhSize.getText().toString();


        EntryBy = getStaffID();
        try {
            EntryDate = getDateTime();
        } catch (Exception e) {
            e.printStackTrace();
        }


        String AGLand = "";// agLand.getText().toString();
        String v_group = VSLA_group.getText().toString();

        String strLatitude = txtLatitude.getText().toString();
        String strLongitude = txtLongitude.getText().toString();

        SQLServerSyntaxGenerator malaiwTable = new SQLServerSyntaxGenerator();
        malaiwTable.setAdmCountryCode(idCountry);
        malaiwTable.setLayR1ListCode(idDist);
        malaiwTable.setLayR2ListCode(idUP);
        malaiwTable.setLayR3ListCode(idUnion);
        malaiwTable.setLayR4ListCode(idVill);

        malaiwTable.setLayR4ListCode(idVill);

        malaiwTable.setHHID(registeredId);
        malaiwTable.setHhRegNDate(regDate);
        malaiwTable.setHhHHHeadName(name);
        malaiwTable.setHhHHHeadSex(strGender);
        malaiwTable.setHhHHSize(HHSize);
        malaiwTable.setHhGPSLate(strLatitude);
        malaiwTable.setHhGPSLong(strLongitude);
        malaiwTable.setHhAGLand(AGLand);
        malaiwTable.setHhVStatus(strVStatus);
        malaiwTable.setHhMStatus(strMStatus);
        malaiwTable.setEntryBy(EntryBy);
        malaiwTable.setEntryDate(EntryDate);
        malaiwTable.setHhVSLAGroup(v_group);
        malaiwTable.setRegNAddLookupCode(idAddress);
        malaiwTable.setWRank(idWRank);
        malaiwTable.setLTp2Hectres(lTp2Hectres);
        malaiwTable.setLT3mFoodStock(lT3mFoodStock);
        malaiwTable.setNoMajorCommonLiveStock(noMajorCommonLiveStock);
        malaiwTable.setReceiveNoFormalWages(receiveNoFormalWages);
        malaiwTable.setNoIGA(noIGA);
        malaiwTable.setRelyPiecework(relyPiecework);

        if (!strLatitude.matches("")) {
            latitude = Double.parseDouble(strLatitude);
            strLatitude = String.valueOf(latitude);
        }
        if (!strLongitude.matches("")) {
            longitude = Double.parseDouble(strLongitude);
            strLongitude = String.valueOf(longitude);
        }


        // UPDATE Postion
        if (is_edit) {
            String v_status = sqlH.getHH_VerifiedStatus(idCountry, idDist, idUP, idUnion, idVill, registeredId);
            if (v_status.equals("N")) {
                // uload for locat device
                sqlH.updateRegistrationRecord(pID, idDist, idUP, idUnion, idVill, idAddress, registeredId, regDate, name, strGender, HHSize, strLatitude, strLongitude, AGLand, strVStatus, strMStatus, EntryBy, EntryDate, v_group, idWRank,
                        lTp2Hectres, lT3mFoodStock, noMajorCommonLiveStock, receiveNoFormalWages, noIGA, relyPiecework);


                sqlH.insertIntoUploadTable(malaiwTable.updateRegNHHtableForMalawi());
                Toast.makeText(Register.this, " update successfully ", Toast.LENGTH_SHORT).show();
                finish();
                String tem = idCountry + idDist + idUP + idUnion + idVill;
                Intent vIntent = new Intent(Register.this, RegisterRecordView.class);
                vIntent.putExtra("village_code", tem);
                vIntent.putExtra("village", strVillage);

                startActivity(vIntent);
            } else {
                dialog.showErrorDialog(mContext, "HH record has been Verified. Save attempt denied.");
            }


        }
        //Insert postion
        else    // Add data
        {

            if (registeredId.equals("")) {

                Toast.makeText(getApplicationContext(), "Enter any ID", Toast.LENGTH_SHORT).show();
            } else if (name.equals("")) {

                dialog.showErrorDialog(mContext, "Missing HH Head hhName. Save attempt denied");

            } else if (sqlH.ifThisHHIDExitsInRegHHTable(idCountry, idDist, idUP, idUnion, idVill, registeredId)) {
                dialog.showErrorDialog(mContext, "HH Id already exists. Save attempt denied. Press clear Button For new Entry");
            } else if (countNoWhitSpace(name) > countNoWord(name)) {
                dialog.showErrorDialog(mContext, " Invalid format. Save attempt denied");
            } else if (isHousHoldExits(name) && !is_edit) {
                dialog.showErrorDialog(mContext, "HH Head hhName already exists. Save attempt denied.");
            } // same id Exits in db than it will not insert

            else if (regDate.equals("")) {
                //    invalid = true;
                Toast.makeText(getApplicationContext(), "Select a Date", Toast.LENGTH_SHORT).show();
            } else if (HHSize.equals("")) {
                dialog.showErrorDialog(mContext, " Invalid HH Size. Save attempt denied.");
            } else if (spGender.equals("")) {
                //   invalid = true;
                Toast.makeText(getApplicationContext(), "Select a Gender", Toast.LENGTH_SHORT).show();
            } else try {

                HashMap<String, String> dateRange = sqlH.getDateRange(idCountry);
                String start_date = dateRange.get("sdate");
                String end_date = dateRange.get("edate");

                if (regDate != null && start_date != null && end_date != null) {
                    if (!getValidDateRange(regDate, start_date, end_date)) {
                        //  invalid = true;
                        dialog.showErrorDialog(mContext, "Registration date is not within the valid range. Save attempt denied.");


                    } else {

                        long id = sqlH.addRegistrationForMalawi(idCountry, idDist, idUP, idUnion, idVill, idAddress, registeredId, regDate, name, strGender, HHSize, strLatitude, strLongitude, AGLand, strVStatus, strMStatus, EntryBy, EntryDate, v_group, idWRank, lTp2Hectres, lT3mFoodStock, noMajorCommonLiveStock, receiveNoFormalWages, noIGA, relyPiecework);





                        sqlH.insertIntoUploadTable(malaiwTable.insertIntoRegNHHtableForMalawi());
                        Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No valid date range found!", Toast.LENGTH_SHORT).show();
                }
            } catch (ParseException pe) {
                pe.printStackTrace();
                Toast.makeText(getApplicationContext(), "Wrong Date Format, parse error!", Toast.LENGTH_SHORT).show();
            }
            // until the house hold save the member bttton will not active
            // btnAddMember.setTextColor(getResources().getColor(R.color.green));
            btnAddMember.setEnabled(true);
        } // everything gone well
    }


    private int countNoWord(String sname) {
        int wordNo = sname.isEmpty() ? 0 : sname.split(" \\s+").length;
        return wordNo;
    }

    private int countNoWhitSpace(String sname) {
        int countSpaces = sname == null ? 0 : sname.length() - sname.replace(" ", "").length();
        return countSpaces;
    }

    private boolean isHousHoldExits(String name) {

        return sqlH.ifThisNameExitsInRegHHTable(name);
    }


    /**
     * LOAD :: DISTRICT: loadLayR1List
     */
    private void loadLayR1List(String cCode) {

        criteria = SQLiteQuery.getDistrictJoinQuery(idCountry);

        List<SpinnerHelper> listDistrict = sqlH.getListAndID(SQLiteHandler.DISTRICT_TABLE, criteria, cCode, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listDistrict);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spDistrict.setAdapter(dataAdapter);


        if (idDist != null) {
            for (int i = 0; i < spDistrict.getCount(); i++) {
                String district = spDistrict.getItemAtPosition(i).toString();
                if (district.equals(strDistrict)) {
                    position = i;
                }
            }
            spDistrict.setSelection(position);
        }


        spDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                strDistrict = ((SpinnerHelper) spDistrict.getSelectedItem()).getValue();
                idDist = ((SpinnerHelper) spDistrict.getSelectedItem()).getId();
                loadLayR2List(idCountry);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner District


    /**
     * LOAD :: UPAZILLA=loadLayR2List
     */
    private void loadLayR2List(String cCode) {

        criteria = SQLiteQuery.getUpzillaJoinQuery(idCountry, idDist);


        List<SpinnerHelper> listUpazilla = sqlH.getListAndID(SQLiteHandler.UPAZILLA_TABLE, criteria, cCode, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listUpazilla);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spUpazilla.setAdapter(dataAdapter);


        if (idUP != null) {
            for (int i = 0; i < spUpazilla.getCount(); i++) {
                String upazilla = spUpazilla.getItemAtPosition(i).toString();
                if (upazilla.equals(strUpazilla)) {
                    position = i;
                }
            }
            spUpazilla.setSelection(position);
        }


        spUpazilla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                strUpazilla = ((SpinnerHelper) spUpazilla.getSelectedItem()).getValue();
                idUP = ((SpinnerHelper) spUpazilla.getSelectedItem()).getId();

                loadLayR3List(idCountry);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * LOAD :: Union
     */
    private void loadLayR3List(String cCode) {


        SpinnerLoader.loadLayR3ListLoader(mContext, sqlH, spUnion, idUnion, strUnion, idCountry, idDist, idUP);
        spUnion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                strUnion = ((SpinnerHelper) spUnion.getSelectedItem()).getValue();
                idUnion = ((SpinnerHelper) spUnion.getSelectedItem()).getId();

                loadLayR4List(idCountry, idDist, idUP, idUnion);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * LOAD :: Village :loadLayR4List
     */
    private void loadLayR4List(String cCode, String layR1Code, String layR2Code, String layR3Code) {


        SpinnerLoader.loadLayR4ListLoader(mContext, sqlH, spVillage, idVill, strVillage, cCode, layR1Code, layR2Code, layR3Code);
        spVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                strVillage = ((SpinnerHelper) spVillage.getSelectedItem()).getValue();
                idVill = ((SpinnerHelper) spVillage.getSelectedItem()).getId();

                loadAddress(idCountry, idDist, idUP, idUnion, idVill);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * LOAD :: GENDER
     */
    private void loadGender() {

        spGender = (Spinner) findViewById(R.id.spGender);
        ArrayAdapter<CharSequence> adtGender = ArrayAdapter.createFromResource(
                this, R.array.spGenderItem, R.layout.spinner_layout);

        adtGender.setDropDownViewResource(R.layout.spinner_layout);
        spGender.setAdapter(adtGender);

        if (strGender != null) {
            spGender.setSelection(getSpinnerIndex(spGender, strGender));
        }

        spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                strGender = parent.getItemAtPosition(position).toString();

                if (strGender.equals("Male"))
                    strGender = "M";
                else
                    strGender = "F";
                //Log.d(TAG, "Gender selected: " + strGender);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * LOAD :: GENDER
     */
    private void loadWRank() {


        ArrayAdapter<CharSequence> adtGender = ArrayAdapter.createFromResource(
                this, R.array.spWRankItem, R.layout.spinner_layout);

        adtGender.setDropDownViewResource(R.layout.spinner_layout);
        spWRank.setAdapter(adtGender);
//        Log.d("MOR","idWRank : "+idWRank);

        if (idWRank != null && idWRank.length() > 0) {

            spWRank.setSelection(Integer.parseInt(idWRank) - 1);

        }


        spWRank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                strWRank = parent.getItemAtPosition(position).toString();


                if (strWRank.equals("Very Poor"))
                    idWRank = "1";
                else if (strWRank.equals("Poor"))
                    idWRank = "2";
                else
                    idWRank = "3";
                //Log.d(TAG, "Gender selected: " + strGender);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * LOAD :: VStatus
     */
    private void loadVStatus() {

        spVStatus = (Spinner) findViewById(R.id.spVStatus);
        ArrayAdapter<CharSequence> adtVStatus = ArrayAdapter.createFromResource(
                this, R.array.arrVStatus, R.layout.spinner_layout);

        adtVStatus.setDropDownViewResource(R.layout.spinner_layout);
        spVStatus.setAdapter(adtVStatus);


        if (strVStatus != null) {
            spVStatus.setSelection(getSpinnerIndex(spVStatus, strVStatus));
        }

        spVStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                strVStatus = parent.getItemAtPosition(position).toString();

                if (strVStatus.equals("Yes"))
                    strVStatus = "Y";
                else
                    strVStatus = "N";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadAddress(String countryCode, String distCode, String uPCode, String uCode, String vCode) {
        spAddress = (Spinner) findViewById(R.id.spAddress);

        criteria = SQLiteQuery.getAddressQuery(countryCode, distCode, uPCode, uCode, vCode);
        List<SpinnerHelper> listAddress = sqlH.getListAndID(SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE, criteria, countryCode, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listAddress);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spAddress.setAdapter(dataAdapter);

        if (idAddress != null) {

            for (int i = 0; i < spAddress.getCount(); i++) {
                String addressName = spAddress.getItemAtPosition(i).toString();
                if (addressName.equals(strAddress)) {
                    position = i;
                }
            }
            spAddress.setSelection(position);
        }

        spAddress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                strAddress = ((SpinnerHelper) spAddress.getSelectedItem()).getValue();
                idAddress = ((SpinnerHelper) spAddress.getSelectedItem()).getId();

                if (!is_edit) {
                    String next_id = sqlH.getRegistrationID(idCountry, idDist, idUP, idUnion, idVill);
                    regId.setText(next_id);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * LOAD :: MStatus
     */
    private void loadMStatus() {
        int pos = 0;

        Log.d("MORA", "strMStatus : " + strMStatus);

        spMStatus = (Spinner) findViewById(R.id.spMStatus);
        ArrayAdapter<CharSequence> adtMStatus = ArrayAdapter.createFromResource(
                this, R.array.arrMStatus, R.layout.spinner_layout);

        adtMStatus.setDropDownViewResource(R.layout.spinner_layout);
        spMStatus.setAdapter(adtMStatus);

        if (strMStatus != null) {
            if (strMStatus.equals("R"))
                pos = 0;
            else if (strMStatus.equals("M"))
                pos = 1;
            else
                pos = 2;

            spMStatus.setSelection(pos);

        }
        spMStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                strMStatus = parent.getItemAtPosition(position).toString();
                if (strMStatus.equals("Resident"))
                    strMStatus = "R";
                else if (strMStatus.equals("Migrated"))
                    strMStatus = "M";
                else
                    strMStatus = "O";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * DatePicker code Start
     **/
    public void updateDate() {
        reDOB.setText(format.format(calendar.getTime()));
    }

    public void setDate() {
        new DatePickerDialog(Register.this, d, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDate();
        }
    };

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setHomeButtonIcon() {
        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, btnHome);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setRegistrationRecodeIcon() {
        btnRegistrationRecode.setText("");
        Drawable search = getResources().getDrawable(R.drawable.search_20_20);
        btnRegistrationRecode.setCompoundDrawablesRelativeWithIntrinsicBounds(search, null, null, null);
        setPaddingButton(mContext, search, btnRegistrationRecode);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setSaveButtonIcon() {
        btnSaveData.setText("");
        Drawable imageSave = getResources().getDrawable(R.drawable.save_b);
        btnSaveData.setCompoundDrawablesRelativeWithIntrinsicBounds(imageSave, null, null, null);
        setPaddingButton(mContext, imageSave, btnSaveData);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setButtonClare() {
        btnClear.setText("");
        Drawable imageClear = getResources().getDrawable(R.drawable.clear_b);
        btnClear.setCompoundDrawablesRelativeWithIntrinsicBounds(imageClear, null, null, null);
        setPaddingButton(mContext, imageClear, btnClear);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setAddMember() {
        btnAddMember.setText("");
        Drawable addMemberIcon = getResources().getDrawable(R.drawable.add_member);
        btnAddMember.setCompoundDrawablesRelativeWithIntrinsicBounds(addMemberIcon, null, null, null);
        setPaddingButton(mContext, addMemberIcon, btnAddMember);
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
        setSaveButtonIcon();
        setRegistrationRecodeIcon();
        setHomeButtonIcon();
        setButtonClare();
        setAddMember();
    }

}
