package com.siddiquinoor.restclient.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
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
import com.siddiquinoor.restclient.activity.sub_activity.register_sub.RegisterMemberLiberia;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.data_model.RegN_HH_libDataModel;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
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


public class RegisterLiberia extends BaseActivity implements View.OnClickListener {


    public static final String YES = "Y";
    //   ----------Spinner ---------
    private Spinner spDistrict;
    private Spinner spUpazilla;
    private Spinner spUnion;
    private Spinner spVillage;
    private Spinner spHHType; // houes hold type


    //-------------Spinner's Values hhName--------
    private String strCountry;
    private String strDistrict;
    private String strUnion;
    private String strUpazilla;
    private String strVillage;
    private String strHHType;

//-------------Spinner's Values ID--------

    private String idCountry;
    private String idDist;
    private String idUP;
    private String idUnion;
    private String idVill;
    private String idHHType;
    //----------Button------------
    private Button btnSave;
    private Button btnClear;
    private Button btnHome;
    private Button btnAddMember;
    //-------------SQLITE--------
    private SQLiteHandler sqlH;

    //--------------Context-------
    private final Context mContext = this;

    //--------------Globel variable -------
    int position = 0;
    // -------------TextView------
    private TextView tv_regDate;
    // -------------EditText------
    private EditText edt_regId;
    private EditText edt_regName;
    // private EditText edt_regDate;

    //-----------Age----------
    private EditText edt_LT2yrsM;
    private EditText edt_LT2yrsF;
    private EditText edt_M2to5yrs;
    private EditText edt_F2to5yrs;
    private EditText edt_M6to12yrs;
    private EditText edt_F6to12yrs;
    private EditText edt_M13to17yrs;
    private EditText edt_F13to17yrs;
    private EditText edt_Orphn_LT18yrsM;
    private EditText edt_Orphn_LT18yrsF;
    private EditText edt_Adlt_18to59M;
    private EditText edt_Adlt_18to59F;
    private EditText edt_Eld_60pM;
    private EditText edt_Eld_60pF;
    private EditText edt_PLW_no;
    private EditText edt_Chro_ill_no;
    // Cattle Count & Value
    // Before Ebolla
    private EditText edt_BrfCountCattle;
    private EditText edt_BrfValueCattle;
    // After Ebolla
    private EditText edt_AftCountCattle;
    private EditText edt_AftValueCattle;

    // Sheep Goats Count & Value
    // Before Ebolla
    private EditText edt_BRFCountSGoats;
    private EditText edt_BrfValueSGoats;
    // After Ebolla
    private EditText edt_AftCountSGoats;
    private EditText edt_AftValueSGoats;

    // Poultry Count & Value
    // Before Ebolla
    private EditText edt_BRFCountPoultry;
    private EditText edt_BrfValuePoultry;
    // After Ebolla
    private EditText edt_AftCountPoultry;
    private EditText edt_AftValuePoultry;

    // Other Count & Value
    // Before Ebolla
    private EditText edt_BRFCountOther;
    private EditText edt_BrfValueOther;
    // After Ebolla
    private EditText edt_AftCountOther;
    private EditText edt_AftValueOther;

    // Cultivable Acre & Value
    // Before Ebolla
    private EditText edt_BrfAcreCultivable;
    private EditText edt_BrfValueCultivable;
    // After Ebolla
    private EditText edt_AftAcreCultivable;
    private EditText edt_AftValueCultivable;

    // NonCultivable Acre & Value
    // Before Ebolla
    private EditText edt_BrfAcreNonCultivable;
    private EditText edt_BrfValueNonCultivable;
    // After Ebolla
    private EditText edt_AftAcreNonCultivable;
    private EditText edt_AftValueNonCultivable;


    private EditText edt_BrfAcreOrchards; // Orchards Acre & Value
    private EditText edt_BrfValueOrchards;
    private EditText edt_AftAcreOrchards;
    private EditText edt_AftValueOrchards;

    private EditText edt_BRFValCrop;          // Corp Value
    private EditText edt_lb_AFTValCrop;
    private EditText edt_BRFValLivestock; // Live Stock Value
    private EditText edt_AFTValLivestock;
    private EditText edt_BRFValSmallBusiness; // SmallBusiness Value
    private EditText edt_AFTValSmallBusiness;

    // Employment Value
    // Before Ebolla
    private EditText edt_BRFValEmployment;
    // After Ebolla
    private EditText edt_AFTValEmployment;


    private EditText edt_BRFValRemittances; // Remittances Value
    private EditText edt_AFTValRemittances;

    // BRFCntWageEnr Value
    // Before Ebolla
    private EditText edt_BRFValBRFCntWageEnr;
    // After Ebolla
    private EditText edt_AFTValBRFCntWageEnr;


    // Check Box
    private CheckBox cb_contractEbola; //Did someone in this HH (living or deceased) contract Ebola? (LivingDeceasedContractEbola)
    private CheckBox cb_ExChBcaseEboloa;//Has this HH taken in an extra child because of Ebola? (ExtraChildBecauseEbola)
    private CheckBox cb_hasTakeEForEbola; //Has this HH taken in an extra elderly person because of Ebola?
    private CheckBox cb_hasTkOldIllForEbola;//Has this HH taken in an extra chronically ill or disabled person because of Ebola? (ExtraChronicallyIllDisabledPersonBecauseEbola)


    //---------- String ------
    private String strName;
    private String strRegDate;
    private String strRegID;
//---------- utill -------

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private Calendar calendar = Calendar.getInstance();

    private ADNotificationManager dialog;
    private String EntryBy;
    private String EntryDate;
    private boolean is_edit = false;
    private String TAG = RegisterLiberia.class.getSimpleName();
    private int pID;
    // private boolean savePermission;

    private TextView tv_LayR1Label;
    private TextView tv_LayR2Label;
    private TextView tv_LayR3Label;
    private TextView tv_LayR4Label;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register_liberia);
        viewReference();
        objectDeclaration();
        Intent intent = getIntent();
        /** GET THE EDITED VALUE FROM HERE */

        idCountry = intent.getStringExtra(KEY.COUNTRY_CODE);
        setLayRLable();
        loadLayR1List(idCountry);
        loadHouseHoldCategory(idCountry);

        edt_regId.setEnabled(false);
        edt_regName.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edt_regName, InputMethodManager.SHOW_IMPLICIT);

        setListener();
        // When Edit is passed
        Intent intnt = getIntent();


        is_edit = intnt.getBooleanExtra(KEY.IS_EDIT, false);

        strCountry = intnt.getStringExtra(KEY.COUNTRY_NAME);
        strDistrict = intnt.getStringExtra(KEY.DISTRICT);
        strUpazilla = intnt.getStringExtra("upazilla");
        strUnion = intnt.getStringExtra("unit");
        strVillage = intnt.getStringExtra("village");


        idCountry = intnt.getStringExtra("country_code");
        idDist = intnt.getStringExtra(KEY.DISTRICT_CODE);
        idUP = intnt.getStringExtra("upazillaCode");
        idUnion = intnt.getStringExtra("unitCode");
        idVill = intnt.getStringExtra("villageCode");


        edt_regId.setText(intnt.getStringExtra("regID"));
        String tem = intnt.getStringExtra("regID");
        //   reDOB.setText(intnt.getStringExtra("regDate"));
        //   String regDate=intnt.getStringExtra("regDate");


        if (is_edit) {
            pID = intnt.getIntExtra("pID", -1);
            edt_regName.setText(intnt.getStringExtra(KEY.PERSON_NAME));
            getHouseHoldDataFromDataBase(idCountry, idDist, idUP, idUnion, idVill, edt_regId.getText().toString());
            loadLayR1List(idCountry);
            btnSave.setText("Update");
        }


    }


    private void setListener() {
        tv_regDate.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnHome.setOnClickListener(this);
        btnAddMember.setOnClickListener(this);
    }

    private void setLayRLable() {

        tv_LayR1Label.setText( UtilClass.getLayR1LabelName(mContext,idCountry));
        tv_LayR2Label.setText( UtilClass.getLayR2LabelName(mContext,idCountry));
        tv_LayR3Label.setText( UtilClass.getLayR3LabelName(mContext,idCountry));
        tv_LayR4Label.setText( UtilClass.getLayR4LabelName(mContext,idCountry));

    }

    private void objectDeclaration() {
        sqlH = new SQLiteHandler(mContext);
        dialog = new ADNotificationManager();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHomeFooter:
                finish();
                Intent iHome = new Intent(mContext, MainActivity.class);
                startActivity(iHome);
                break;
            case R.id.tv_LB_hh_reg_date:
                setDate();
                break;
            case R.id.btn_lb_rgSaveData:
                if (sqlH.getSavePermissionForHHEntries(idCountry, idDist, idUP, idUnion, idVill))
                    saveRegistrationHouseHold();
                else
                    dialog.showInfromDialog(mContext, "Save Denied", "You don't have Save Permission .Pls contact with Admin");
                //  Log.d(TAG, "save button");


                break;
            case R.id.btnRegisterFooter:

                gotoMemberRegistrationPage();

                break;

        }
    }

    private void gotoMemberRegistrationPage() {
        finish();

        EntryBy = getStaffID();
        try {
            EntryDate = getDateTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        String registeredId = edt_regId.getText().toString();
        String name = edt_regName.getText().toString();

        Intent dIntent = new Intent(RegisterLiberia.this, RegisterMemberLiberia.class);

        dIntent.putExtra("redirect", "");
        dIntent.putExtra("page_from", "RegisterLiberia");
        dIntent.putExtra("str_country", strCountry);
        dIntent.putExtra("str_district", strDistrict);
        dIntent.putExtra("str_upazilla", strUpazilla);
        dIntent.putExtra("str_union", strUnion);
        dIntent.putExtra("str_village", strVillage);
        dIntent.putExtra(KEY.STR_COUNTRY_CODE, idCountry);
        dIntent.putExtra(KEY.STR_DISTRICT_CODE, idDist);
        dIntent.putExtra(KEY.STR_UPAZILLA_CODE, idUP);
        dIntent.putExtra(KEY.STR_UNION_CODE, idUnion);
        dIntent.putExtra(KEY.STR_VILLAGE_CODE, idVill);
        dIntent.putExtra(KEY.ENTRY_BY, EntryBy);
        dIntent.putExtra(KEY.ENTRY_DATE, EntryDate);
        dIntent.putExtra(KEY.HOUSE_HOLD_ID, registeredId);
        dIntent.putExtra("str_hhName", name);
        dIntent.putExtra(KEY.REGESTRATION_DATE, tv_regDate.getText());

        startActivity(dIntent);
    }


    private void getHouseHoldDataFromDataBase(String str_c_code, String str_districtCode, String str_upazillaCode, String str_unionCode, String str_villageCode, String str_hhID) {
        RegN_HH_libDataModel houseHold = sqlH.getSingleLiberiaHouseHoldData(str_c_code, str_districtCode, str_upazillaCode, str_unionCode, str_villageCode, str_hhID);


        setTextToEditText(houseHold);

    }


    private void saveRegistrationHouseHold() {

        SQLServerSyntaxGenerator libReg = new SQLServerSyntaxGenerator();


        strRegID = edt_regId.getText().toString();
        strName = edt_regName.getText().toString();
        strRegDate = tv_regDate.getText().toString();

        boolean invalid = false;


        if (strRegID.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Enter any ID", Toast.LENGTH_SHORT).show();
        } else if (strName.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing HH Head hhName. Save attempt denied");
            // Toast.makeText(getApplicationContext(), "Enter Person hhName", Toast.LENGTH_SHORT).show();
        }
               /* else if(isHousHoldExits(name)){
                    mdialog.showErrorDialog(mContext, "HH Head hhName already exists. Save attempt denied.");

                }*/

        else if (strRegDate.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Please select a Date");
            // Toast.makeText(getApplicationContext(), "Please select a Date", Toast.LENGTH_SHORT).show();
        } else try {

            HashMap<String, String> dateRange = sqlH.getDateRange(idCountry);
            String start_date = dateRange.get("sdate");
            String end_date = dateRange.get("edate");

            if (strRegDate != null && start_date != null && end_date != null) {
                if (!getValidDateRange(strRegDate, start_date, end_date)) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Registration date is not a valid date! Please select a valid date within the range!!", Toast.LENGTH_LONG).show();
                } else if (invalid == false) {


                    // todo : set different class object to data save SQlite

                    EntryBy = getStaffID();
                    EntryDate = getDateTime();

                    RegN_HH_libDataModel hhData = new RegN_HH_libDataModel();
                    hhData.setCountryCode(idCountry);
                    hhData.setDistrictCode(idDist);
                    hhData.setUpazillaCode(idUP);
                    hhData.setUnitCode(idUnion);
                    hhData.setVillageCode(idVill);
                    hhData.setHhId(strRegID);
                    hhData.setRegNDate(strRegDate);
                    hhData.setHHHeadName(strName);
                    // todo: set house hold catagories
                    hhData.setHHTypeCode(idHHType);
                    // todo : get check box values
                    hhData.setLivingDeceasedContractEbola(cb_contractEbola.isChecked() ? "Y" : "N");
                    hhData.setExtraChildBecauseEbola(cb_ExChBcaseEboloa.isChecked() ? "Y" : "N");
                    hhData.setExtraelderlyPersonBecauseEbola(cb_hasTakeEForEbola.isChecked() ? "Y" : "N");
                    hhData.setExtraChronicallyIllDisabledPersonBecauseEbola(cb_hasTkOldIllForEbola.isChecked() ? "Y" : "N");


                    hhData.setPLW(edt_PLW_no.getText().toString());
                    hhData.setChronicallyIll(edt_Chro_ill_no.getText().toString());

                    hhData.setLT2yrsM(edt_LT2yrsM.getText().toString());
                    hhData.setLT2yrsF(edt_LT2yrsF.getText().toString());
                    hhData.setM2to5yers(edt_M2to5yrs.getText().toString());
                    hhData.setF2to5yrs(edt_F2to5yrs.getText().toString());

                    hhData.setM6to12yrs(edt_M6to12yrs.getText().toString());
                    hhData.setF6to12yrs(edt_F6to12yrs.getText().toString());
                    hhData.setM13to17yrs(edt_M13to17yrs.getText().toString());
                    hhData.setF13to17yrs(edt_F13to17yrs.getText().toString());
                    hhData.setOrphn_LT18yrsM(edt_Orphn_LT18yrsM.getText().toString());
                    hhData.setOrphn_LT18yrsF(edt_Orphn_LT18yrsF.getText().toString());
                    hhData.setAdlt_18to59M(edt_Adlt_18to59M.getText().toString());
                    hhData.setAdlt_18to59F(edt_Adlt_18to59F.getText().toString());
                    hhData.setEld_60pM(edt_Eld_60pM.getText().toString());
                    hhData.setEld_60pF(edt_Eld_60pF.getText().toString());
                    hhData.setPLW(edt_PLW_no.getText().toString());
                    hhData.setChronicallyIll(edt_Chro_ill_no.getText().toString());
                    hhData.setBrfCntCattle(edt_BrfCountCattle.getText().toString());
                    hhData.setBrfValCattle(edt_BrfValueCattle.getText().toString());
                    hhData.setAftCntCattle(edt_AftCountCattle.getText().toString());
                    hhData.setAftValCattle(edt_AftValueCattle.getText().toString());
                    hhData.setBrfCntSheepGoats(edt_BRFCountSGoats.getText().toString());
                    hhData.setBrfValSheepGoats(edt_BrfValueSGoats.getText().toString());
                    hhData.setAftCntSheepGoats(edt_AftCountSGoats.getText().toString());
                    hhData.setAftValSheepGoats(edt_AftValueSGoats.getText().toString());
                    hhData.setBrfCntPoultry(edt_BRFCountPoultry.getText().toString());
                    hhData.setBrfValPoultry(edt_BrfValuePoultry.getText().toString());
                    hhData.setAftCntPoultry(edt_AftCountPoultry.getText().toString());
                    hhData.setAftValPoultry(edt_AftValuePoultry.getText().toString());
                    hhData.setBrfCntOther(edt_BRFCountOther.getText().toString());
                    hhData.setBrfValOther(edt_BrfValueOther.getText().toString());
                    hhData.setAftCntOther(edt_AftCountOther.getText().toString());
                    hhData.setAftValOther(edt_AftValueOther.getText().toString());
                    hhData.setBrfAcreCultivable(edt_BrfAcreCultivable.getText().toString());
                    hhData.setBrfValCultivable(edt_BrfValueCultivable.getText().toString());
                    hhData.setAftAcreCultivable(edt_AftAcreCultivable.getText().toString());
                    hhData.setAftValCultivable(edt_AftValueCultivable.getText().toString());
                    hhData.setBrfAcreNonCultivable(edt_BrfAcreNonCultivable.getText().toString());
                    hhData.setBrfValNonCultivable(edt_BrfValueNonCultivable.getText().toString());
                    hhData.setAftAcreNonCultivable(edt_AftAcreNonCultivable.getText().toString());
                    hhData.setAftValNonCultivable(edt_AftValueNonCultivable.getText().toString());
                    hhData.setBrfAcreOrchards(edt_BrfAcreOrchards.getText().toString());
                    hhData.setBrfValOrchards(edt_BrfValueOrchards.getText().toString());
                    hhData.setAftAcreOrchards(edt_AftAcreOrchards.getText().toString());
                    hhData.setAftValOrchards(edt_AftValueOrchards.getText().toString());
                    hhData.setBrfValCrop(edt_BRFValCrop.getText().toString());
                    hhData.setAftValCrop(edt_lb_AFTValCrop.getText().toString());
                    hhData.setBrfValLivestock(edt_BRFValLivestock.getText().toString());
                    hhData.setAftValLivestock(edt_AFTValLivestock.getText().toString());
                    hhData.setBrfValSmallBusiness(edt_BRFValSmallBusiness.getText().toString());
                    hhData.setAftValSmallBusiness(edt_AFTValSmallBusiness.getText().toString());
                    hhData.setBrfValEmployment(edt_BRFValEmployment.getText().toString());
                    hhData.setAftValEmployment(edt_AFTValEmployment.getText().toString());
                    hhData.setBrfValRemittances(edt_BRFValRemittances.getText().toString());
                    hhData.setAftValRemittances(edt_AFTValRemittances.getText().toString());
                    hhData.setBrfCntWageEnr(edt_BRFValBRFCntWageEnr.getText().toString());
                    hhData.setAftCntWageEnr(edt_AFTValBRFCntWageEnr.getText().toString());
                    hhData.setEntryBy(EntryBy);
                    hhData.setEntryDate(EntryDate);
                    libReg = setterLib_RegForGobleVariable(hhData);


                    if (is_edit) {

                        // todo : write edit code  Registration
                        // to get previous  district ,upzella, union,village
                        // if user atteme to insert one hh to differente district or village  by mistake then
                        // he want to change the correect village or union  after synch  than he can do it
                        RegN_HH_libDataModel previousLayRList = sqlH.getPreviousLayeRListforHouseHold(pID);
                        libReg.setPreviousLayR1ListCode(previousLayRList.getDistrictCode());
                        libReg.setPreviousLayR2ListCode(previousLayRList.getUpazillaCode());
                        libReg.setPreviousLayR3ListCode(previousLayRList.getUnitCode());
                        libReg.setPreviousLayR4ListCode(previousLayRList.getVillageCode());

                        sqlH.editRegistrationRecordForLiberia(pID, hhData);
                        sqlH.insertIntoUploadTable(libReg.updateRegNHHtableForLiberia());
                        // todo write Upload Code For
                        Toast.makeText(RegisterLiberia.this, " update successfully pID: " + pID, Toast.LENGTH_SHORT).show();
                                    /*finish();
                                    String tem=idCountry+idDist+idUP+idUnion+idVill;
                                    Intent vIntent = new Intent(RegisterLiberia.this, MW_RegisterViewRecord.class);
                                    vIntent.putExtra("village_code", tem);
                                    vIntent.putExtra("village", strVillage);

                                    startActivity(vIntent);*/
                    } else    // Insert data as New Entry
                    {
                        /**
                         * INSERT FOR LOCAL DATABASE
                         * */
                        long id = sqlH.addHHRegForLiberia(hhData);
                        /**
                         * INSERT FOR UPLOAD INTO AZURE
                         * */
                        //  Log.d(TAG, " Liberia Data :" + libReg.insertIntoRegNHHtableForLiberia());
                        sqlH.insertIntoUploadTable(libReg.insertIntoRegNHHtableForLiberia());

                        Toast.makeText(getApplicationContext(), "Data submitted Successfully...", Toast.LENGTH_LONG).show();

                    }
                    // until the house hold save the member btutton will not active
                    btnAddMember.setTextColor(getResources().getColor(R.color.green));
                    btnAddMember.setEnabled(true);
                } // everything gone well
            } else {
                Toast.makeText(getApplicationContext(), "No valid date range found!", Toast.LENGTH_SHORT).show();
            }
        } catch (ParseException pe) {
            pe.printStackTrace();
            Toast.makeText(getApplicationContext(), "Wrong Date Format, parse error!", Toast.LENGTH_SHORT).show();
        }


    }


    /**
     * DatePicker code Start
     */
    public void updateDate() {
        tv_regDate.setText(format.format(calendar.getTime()));
    }


    public void setDate() {
        new DatePickerDialog(mContext, d, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
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

    /**
     * LOAD :: HOUSEHOLD CATEGORY
     */
    private void loadHouseHoldCategory(String cCode) {

        SpinnerLoader.loadHouseHoldCategoryLoader(mContext,sqlH,spHHType,cCode,idHHType,strHHType);


        spHHType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                strHHType = ((SpinnerHelper) spHHType.getSelectedItem()).getValue();
                idHHType = ((SpinnerHelper) spHHType.getSelectedItem()).getId();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner District

    /**
     * @author :
     * @date: 2015-09-20
     * Remarks ::
     * purpose: Disable back press button
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /** Do nothing */
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLayR1List(idCountry);
    }

    /**
     * LOAD :: DISTRICT: loadLayR1List
     */
    private void loadLayR1List(String cCode) {
        position = 0;

        String criteria = SQLiteQuery.getDistrictJoinQuery(idCountry);
        // Spinner Drop down elements for District
        List<SpinnerHelper> listDistrict = sqlH.getListAndID(sqlH.DISTRICT_TABLE, criteria, cCode, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listDistrict);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
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
                //strDistrict =  ( (SpinnerHelper) spDistrict.getSelectedItem () ).getId();
                strDistrict = ((SpinnerHelper) spDistrict.getSelectedItem()).getValue();
                idDist = ((SpinnerHelper) spDistrict.getSelectedItem()).getId();

                loadLayR2List(idCountry,idDist);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner District

    /**
     * LOAD :: UPAZILLA
     */
    private void loadLayR2List(final String cCode, final String disCode) {

        String criteria = SQLiteQuery.getUpzillaJoinQuery(cCode, disCode);


        // Spinner Drop down elements for District
        List<SpinnerHelper> listUpazilla = sqlH.getListAndID(sqlH.UPAZILLA_TABLE, criteria, cCode, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listUpazilla);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
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

                //strUpazilla = parent.getItemAtPosition(position).toString();
                strUpazilla = ((SpinnerHelper) spUpazilla.getSelectedItem()).getValue();
                idUP = ((SpinnerHelper) spUpazilla.getSelectedItem()).getId();

                loadLayR3List(cCode,disCode,idUP);

                //Log.d(TAG, "Upazilla selected: " + strUpazilla);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * LOAD :: Union
     */
    private void loadLayR3List(final String cCode, final String distCode, final String upCode) {
        position = 0;

        String criteria = SQLiteQuery.getLayR3List_sql(cCode, distCode, upCode);


        // Spinner Drop down elements for District
        List<SpinnerHelper> listUnion = sqlH.getListAndID(sqlH.GEO_LAY_R3_LIST_TABLE, criteria, cCode, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listUnion);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spUnion.setAdapter(dataAdapter);


        if (idUnion != null) {
            for (int i = 0; i < spUnion.getCount(); i++) {
                String union = spUnion.getItemAtPosition(i).toString();
                if (union.equals(strUnion)) {
                    position = i;
                }
            }
            spUnion.setSelection(position);
        }




        spUnion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //strUpazilla = parent.getItemAtPosition(position).toString();
                strUnion = ((SpinnerHelper) spUnion.getSelectedItem()).getValue();
                idUnion = ((SpinnerHelper) spUnion.getSelectedItem()).getId();

                loadLayR4List(cCode,distCode,upCode,idUnion);

                //Log.d(TAG, "Upazilla selected: " + strUpazilla);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * LOAD :: village
     */
    private void loadLayR4List(String cCode,String distCode,String upCode,String unitCode) {
        position = 0;

        Log.d("Nir_1","cCode : "+cCode+" distCode : "+ distCode+" upCode: "+upCode+" unitCode: "+unitCode);

        String criteria = SQLiteQuery.getVillageJoinQuery(cCode, distCode, upCode, unitCode);

        // Spinner Drop down elements for District
        List<SpinnerHelper> listVillage = sqlH.getListAndID(sqlH.VILLAGE_TABLE, criteria, cCode, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listVillage);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spVillage.setAdapter(dataAdapter);

        if (idVill != null) {
            for (int i = 0; i < spVillage.getCount(); i++) {
                String village = spVillage.getItemAtPosition(i).toString();
                if (village.equals(strVillage)) {
                    position = i;
                }
            }
            spVillage.setSelection(position);
        }
       /* if (idVill != null) {
            spVillage.setSelection(Integer.parseInt(idVill));
        }*/


        spVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //strVillage = parent.getItemAtPosition(position).toString();
                strVillage = ((SpinnerHelper) spVillage.getSelectedItem()).getValue();
                idVill = ((SpinnerHelper) spVillage.getSelectedItem()).getId();

                if (!is_edit) {
                    String next_id = sqlH.getHHIDForLiberia(idCountry, idDist, idUP, idUnion, idVill);
                    edt_regId.setText(next_id);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private boolean funcCheckBoxValue(String d) {
        return d.equals(YES);
    }

    private void setTextToEditText(RegN_HH_libDataModel dataModel) {

        cb_contractEbola.setChecked(funcCheckBoxValue(dataModel.getLivingDeceasedContractEbola()));


        cb_ExChBcaseEboloa.setChecked(funcCheckBoxValue(dataModel.getExtraChildBecauseEbola()));
        cb_hasTakeEForEbola.setChecked(funcCheckBoxValue(dataModel.getExtraelderlyPersonBecauseEbola()));
        cb_hasTkOldIllForEbola.setChecked(funcCheckBoxValue(dataModel.getExtraChronicallyIllDisabledPersonBecauseEbola()));

        tv_regDate.setText(dataModel.getRegNDate());
        //edt_regId.setText(houseHoldId);
        edt_regName.setText(dataModel.getHHHeadName());
        edt_LT2yrsM.setText(dataModel.getLT2yrsM());
        edt_LT2yrsF.setText(dataModel.getLT2yrsF());
        edt_M2to5yrs.setText(dataModel.getM2to5yers());
        edt_F2to5yrs.setText(dataModel.getF2to5yrs());
        edt_M6to12yrs.setText(dataModel.getM6to12yrs());
        edt_F6to12yrs.setText(dataModel.getF6to12yrs());
        edt_M13to17yrs.setText(dataModel.getM13to17yrs());
        edt_F13to17yrs.setText(dataModel.getF13to17yrs());
        edt_Orphn_LT18yrsM.setText(dataModel.getOrphn_LT18yrsM());
        edt_Orphn_LT18yrsF.setText(dataModel.getOrphn_LT18yrsF());
        edt_Adlt_18to59M.setText(dataModel.getAdlt_18to59M());
        edt_Adlt_18to59F.setText(dataModel.getAdlt_18to59F());
        edt_Eld_60pM.setText(dataModel.getEld_60pM());
        edt_Eld_60pF.setText(dataModel.getEld_60pF());
        edt_PLW_no.setText(dataModel.getPLW());
        edt_Chro_ill_no.setText(dataModel.getChronicallyIll());
        edt_BrfCountCattle.setText(dataModel.getBrfCntCattle());
        edt_BrfValueCattle.setText(dataModel.getBrfValCattle());
        edt_AftCountCattle.setText(dataModel.getAftCntCattle());
        edt_AftValueCattle.setText(dataModel.getAftValCattle());
        edt_BRFCountSGoats.setText(dataModel.getBrfCntSheepGoats());
        edt_BrfValueSGoats.setText(dataModel.getBrfValSheepGoats());
        edt_AftCountSGoats.setText(dataModel.getAftCntSheepGoats());
        edt_AftValueSGoats.setText(dataModel.getAftValSheepGoats());
        edt_BRFCountPoultry.setText(dataModel.getBrfCntPoultry());
        edt_BrfValuePoultry.setText(dataModel.getBrfValPoultry());
        edt_AftCountPoultry.setText(dataModel.getAftCntPoultry());
        edt_AftValuePoultry.setText(dataModel.getAftValPoultry());
        edt_BRFCountOther.setText(dataModel.getBrfCntOther());
        edt_BrfValueOther.setText(dataModel.getBrfValOther());
        edt_AftCountOther.setText(dataModel.getAftCntOther());
        edt_AftValueOther.setText(dataModel.getAftValOther());
        edt_BrfAcreCultivable.setText(dataModel.getBrfAcreCultivable());
        edt_BrfValueCultivable.setText(dataModel.getBrfValCultivable());
        edt_AftAcreCultivable.setText(dataModel.getAftAcreCultivable());
        edt_AftValueCultivable.setText(dataModel.getAftValCultivable());
        edt_BrfAcreNonCultivable.setText(dataModel.getBrfAcreNonCultivable());
        edt_BrfValueNonCultivable.setText(dataModel.getBrfValNonCultivable());
        edt_AftAcreNonCultivable.setText(dataModel.getAftAcreNonCultivable());
        edt_AftValueNonCultivable.setText(dataModel.getAftValNonCultivable());
        edt_BrfAcreOrchards.setText(dataModel.getBrfAcreOrchards());
        edt_BrfValueOrchards.setText(dataModel.getBrfValOrchards());
        edt_AftAcreOrchards.setText(dataModel.getAftAcreOrchards());
        edt_AftValueOrchards.setText(dataModel.getAftValOrchards());
        edt_BRFValCrop.setText(dataModel.getBrfValCrop());
        edt_lb_AFTValCrop.setText(dataModel.getAftValCrop());
        edt_BRFValLivestock.setText(dataModel.getBrfValLivestock());
        edt_AFTValLivestock.setText(dataModel.getAftValLivestock());
        edt_BRFValSmallBusiness.setText(dataModel.getBrfValSmallBusiness());
        edt_AFTValSmallBusiness.setText(dataModel.getAftValSmallBusiness());
        edt_BRFValEmployment.setText(dataModel.getBrfValEmployment());
        edt_AFTValEmployment.setText(dataModel.getAftValEmployment());
        edt_BRFValRemittances.setText(dataModel.getBrfValRemittances());
        edt_AFTValRemittances.setText(dataModel.getAftValRemittances());
        edt_BRFValBRFCntWageEnr.setText(dataModel.getBrfCntWageEnr());
        edt_AFTValBRFCntWageEnr.setText(dataModel.getAftCntWageEnr());
    }

    private void viewReference() {
        spDistrict = (Spinner) findViewById(R.id.sp_lb_District);
        spUpazilla = (Spinner) findViewById(R.id.sp_lb_Upazilla);
        spUnion = (Spinner) findViewById(R.id.sp_LB_Union);
        spVillage = (Spinner) findViewById(R.id.sp_LB_Village);
        spHHType = (Spinner) findViewById(R.id.sp_LB_hh_type);
        tv_regDate = (TextView) findViewById(R.id.tv_LB_hh_reg_date);
        edt_regId = (EditText) findViewById(R.id.edt_LB_reg_id);
        edt_regName = (EditText) findViewById(R.id.edt_LB_reg_name);
        edt_LT2yrsM = (EditText) findViewById(R.id.edt_lb_reg_LT2yrsM);
        edt_LT2yrsF = (EditText) findViewById(R.id.edt_lb_reg_LT2yrsF);
        edt_M2to5yrs = (EditText) findViewById(R.id.edt_lb_reg_M2to5yrs);
        edt_F2to5yrs = (EditText) findViewById(R.id.edt_lb_reg_F2to5yrs);
        edt_M6to12yrs = (EditText) findViewById(R.id.edt_lb_reg_M6to12yrs);
        edt_F6to12yrs = (EditText) findViewById(R.id.edt_lb_reg_F6to12yrs);
        edt_M13to17yrs = (EditText) findViewById(R.id.edt_lb_reg_M13to17yrs);
        edt_F13to17yrs = (EditText) findViewById(R.id.edt_lb_reg_F13to17yrs);
        edt_Orphn_LT18yrsM = (EditText) findViewById(R.id.edt_lb_reg_Orphn_LT18yrsM);
        edt_Orphn_LT18yrsF = (EditText) findViewById(R.id.edt_lb_reg_Orphn_LT18yrsF);
        edt_Adlt_18to59M = (EditText) findViewById(R.id.edt_lb_reg_Adlt_18to59M);
        edt_Adlt_18to59F = (EditText) findViewById(R.id.edt_lb_reg_Adlt_18to59F);
        edt_Eld_60pM = (EditText) findViewById(R.id.edt_lb_reg_Eld_60pM);
        edt_Eld_60pF = (EditText) findViewById(R.id.edt_lb_reg_Eld_60pF);

        //@2015-09-22
        // again
        edt_PLW_no = (EditText) findViewById(R.id.edt_lb_reg_PLW_no);
        edt_Chro_ill_no = (EditText) findViewById(R.id.edt_lb_reg_Chro_ill_no);
        edt_BrfCountCattle = (EditText) findViewById(R.id.edt_lb_BRFCntCattle);
        edt_BrfValueCattle = (EditText) findViewById(R.id.edt_lb_BRFValCattle);
        edt_AftCountCattle = (EditText) findViewById(R.id.edt_lb_AFTCntCattle);
        edt_AftValueCattle = (EditText) findViewById(R.id.edt_lb_AFTValCattle);
        edt_BRFCountSGoats = (EditText) findViewById(R.id.edt_lb_BRFCntSheepGoats);
        edt_BrfValueSGoats = (EditText) findViewById(R.id.edt_lb_BRFValSheepGoat);
        edt_AftCountSGoats = (EditText) findViewById(R.id.edt_lb_AFTCntSheepGoats);
        edt_AftValueSGoats = (EditText) findViewById(R.id.edt_lb_AFTValSheepGoats);
        edt_BRFCountPoultry = (EditText) findViewById(R.id.edt_lb_BrfCntPoultry);
        edt_BrfValuePoultry = (EditText) findViewById(R.id.edt_lb_BRFValPoultry);
        edt_AftCountPoultry = (EditText) findViewById(R.id.edt_lb_AFTCntPoultry);
        edt_AftValuePoultry = (EditText) findViewById(R.id.edt_lb_AFTValPoultry);
        edt_BRFCountOther = (EditText) findViewById(R.id.edt_lb_BRFCntOrther);
        edt_BrfValueOther = (EditText) findViewById(R.id.edt_lb_BRFValOrther);
        edt_AftCountOther = (EditText) findViewById(R.id.edt_lb_AFTCntOrther);
        edt_AftValueOther = (EditText) findViewById(R.id.edt_lb_AFTValOrther);
        edt_BrfAcreCultivable = (EditText) findViewById(R.id.edt_lb_BRFAcreCultivable);
        edt_BrfValueCultivable = (EditText) findViewById(R.id.edt_lb_BRFValCultivable);
        edt_AftAcreCultivable = (EditText) findViewById(R.id.edt_lb_AFTAcreCultivable);
        edt_AftValueCultivable = (EditText) findViewById(R.id.edt_lb_AFTValCultivable);
        edt_BrfAcreNonCultivable = (EditText) findViewById(R.id.edt_lb_BRFAcreNonCultivable);
        edt_BrfValueNonCultivable = (EditText) findViewById(R.id.edt_lb_BRFValNonCultivable);
        edt_AftAcreNonCultivable = (EditText) findViewById(R.id.edt_lb_AFTAcreNonCultivable);
        edt_AftValueNonCultivable = (EditText) findViewById(R.id.edt_lb_AFTValNonCultivable);
        edt_BrfAcreOrchards = (EditText) findViewById(R.id.edt_lb_BRFAcreOrchards);
        edt_BrfValueOrchards = (EditText) findViewById(R.id.edt_lb_BRFValOrchards);
        edt_AftAcreOrchards = (EditText) findViewById(R.id.edt_lb_AFTAcreOrchards);
        edt_AftValueOrchards = (EditText) findViewById(R.id.edt_lb_AFTValOrchards);
        edt_BRFValCrop = (EditText) findViewById(R.id.edt_lb_BRFValCrop);
        edt_lb_AFTValCrop = (EditText) findViewById(R.id.edt_lb_AFTValCrop);
        edt_BRFValLivestock = (EditText) findViewById(R.id.edt_lb_BRFValLivestock);
        edt_AFTValLivestock = (EditText) findViewById(R.id.edt_lb_AFTValLivestock);
        edt_BRFValSmallBusiness = (EditText) findViewById(R.id.edt_lb_BRFValSmallBusiness);
        edt_AFTValSmallBusiness = (EditText) findViewById(R.id.edt_lb_AFTValSmallBusiness);
        edt_BRFValEmployment = (EditText) findViewById(R.id.edt_lb_BRFValEmployment);
        edt_AFTValEmployment = (EditText) findViewById(R.id.edt_lb_AFTValEmployment);
        edt_BRFValRemittances = (EditText) findViewById(R.id.edt_lb_BRFValRemittances);
        edt_AFTValRemittances = (EditText) findViewById(R.id.edt_lb_AFTValRemittances);
        edt_BRFValBRFCntWageEnr = (EditText) findViewById(R.id.edt_lb_BRFCntWageEnr);
        edt_AFTValBRFCntWageEnr = (EditText) findViewById(R.id.edt_lb_AFTCntWageEnr);


        cb_contractEbola = (CheckBox) findViewById(R.id.cb_lb_hh_contractEbola);
        cb_ExChBcaseEboloa = (CheckBox) findViewById(R.id.cb_LB_ExChBcaseEboloa);
        cb_hasTakeEForEbola = (CheckBox) findViewById(R.id.cb_LB_hasTakeEForEbola);
        cb_hasTkOldIllForEbola = (CheckBox) findViewById(R.id.cb_LB_hasTkOldIllForEbola);


        btnSave = (Button) findViewById(R.id.btn_lb_rgSaveData);
        btnClear = (Button) findViewById(R.id.btn_lb_rgClearData);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnAddMember = (Button) findViewById(R.id.btnRegisterFooter);
        tv_LayR1Label = (TextView) findViewById(R.id.tv_hh_lb_LayR1Lable);
        tv_LayR2Label = (TextView) findViewById(R.id.tv_hh_lb_LayR2Lable);
        tv_LayR3Label = (TextView) findViewById(R.id.tv_hh_lb_LayR3Lable);
        tv_LayR4Label = (TextView) findViewById(R.id.tv_hh_lb_LayR4Lable);
        btnAddMember.setText("Add Member");
        btnAddMember.setTextColor(getResources().getColor(R.color.gray));
        btnAddMember.setEnabled(false);

    }

    /**
     * To reduce the code in Save method use this method
     */

    private SQLServerSyntaxGenerator setterLib_RegForGobleVariable(RegN_HH_libDataModel data) {
        SQLServerSyntaxGenerator globalData = new SQLServerSyntaxGenerator();

        globalData.setAdmCountryCode(data.getCountryCode());
        globalData.setLayR1ListCode(data.getDistrictCode());
        globalData.setLayR2ListCode(data.getUpazillaCode());
        globalData.setLayR3ListCode(data.getUnitCode());
        globalData.setLayR4ListCode(data.getVillageCode());
        globalData.setHHID(data.getHhId());
        globalData.setHhRegNDate(data.getRegNDate());
        globalData.setHhHHHeadName(data.getHHHeadName());
        globalData.setHhPLW(data.getPLW());


        globalData.setHhChronicallyIll(data.getChronicallyIll());
        globalData.setHhLivingDeceasedContractEbola(cb_contractEbola.isChecked() ? "1" : "0");
        globalData.setHhExtraChildBecauseEbola(cb_ExChBcaseEboloa.isChecked() ? "1" : "0");
        globalData.setHhExtraElderlyPersonBecauseEbola(cb_hasTakeEForEbola.isChecked() ? "1" : "0");
        globalData.setHhExtraChronicallyIllDisabledPersonBecauseEbola(cb_hasTkOldIllForEbola.isChecked() ? "1" : "0");
        globalData.setHhLT2yrsM(data.getLT2yrsM());
        globalData.setHhLT2yrsF(data.getLT2yrsF());
        globalData.setHhM2to5yers(data.getM2to5yers());
        globalData.setHhF2to5yrs(data.getF2to5yrs());
        globalData.setHhM6to12yrs(data.getM6to12yrs());
        globalData.setHhF6to12yrs(data.getF6to12yrs());
        globalData.setHhM13to17yrs(data.getM13to17yrs());
        globalData.setHhF13to17yrs(data.getF13to17yrs());
        globalData.setHhOrphn_LT18yrsM(data.getOrphn_LT18yrsM());
        globalData.setHhOrphn_LT18yrsF(data.getOrphn_LT18yrsF());
        globalData.setHhAdlt_18to59M(data.getAdlt_18to59M());
        globalData.setHhAdlt_18to59F(data.getAdlt_18to59F());
        globalData.setHhEld_60pM(data.getEld_60pM());
        globalData.setHhEld_60pF(data.getEld_60pF());
        globalData.setHhPLW(data.getPLW());
        globalData.setHhChronicallyIll(data.getChronicallyIll());
        globalData.setHhBRFCntCattle(data.getBrfCntCattle());
        globalData.setHhBRFValCattle(data.getBrfValCattle());
        globalData.setHhAFTCntCattle(data.getAftCntCattle());
        globalData.setHhAFTValCattle(data.getAftValCattle());
        globalData.setHhBRFCntSheepGoats(data.getBrfCntSheepGoats());
        globalData.setHhBRFValSheepGoats(data.getBrfValSheepGoats());
        globalData.setHhAFTCntSheepGoats(data.getAftCntSheepGoats());
        globalData.setHhAFTValSheepGoats(data.getAftValSheepGoats());
        globalData.setHhBRFCntPoultry(data.getBrfCntPoultry());
        globalData.setHhBRFValPoultry(data.getBrfValPoultry());
        globalData.setHhAFTCntPoultry(data.getAftCntPoultry());
        globalData.setHhAFTValPoultry(data.getAftValPoultry());
        globalData.setHhBRFCntOther(data.getBrfCntOther());
        globalData.setHhBRFValOther(data.getBrfValOther());
        globalData.setHhAFTCntOther(data.getAftCntOther());
        globalData.setHhAFTValOther(data.getAftValOther());
        globalData.setHhBRFAcreCultivable(data.getBrfAcreCultivable());
        globalData.setHhBRFValCultivable(data.getBrfValCultivable());
        globalData.setHhAFTAcreCultivable(data.getAftAcreCultivable());
        globalData.setHhAFTValCultivable(data.getAftValCultivable());
        globalData.setHhBRFAcreNonCultivable(data.getBrfAcreNonCultivable());
        globalData.setHhBRFValNonCultivable(data.getBrfValNonCultivable());
        globalData.setHhAFTAcreNonCultivable(data.getAftAcreNonCultivable());
        globalData.setHhAFTValNonCultivable(data.getAftValNonCultivable());
        globalData.setHhBRFAcreOrchards(data.getBrfAcreOrchards());
        globalData.setHhBRFValOrchards(data.getBrfValOrchards());
        globalData.setHhAFTAcreOrchards(data.getAftAcreOrchards());
        globalData.setHhAFTValOrchards(data.getAftValOrchards());
        globalData.setHhBRFValCrop(data.getBrfValCrop());
        globalData.setHhAFTValCrop(data.getAftValCrop());
        globalData.setHhBRFValLivestock(data.getBrfValLivestock());
        globalData.setHhAFTValLivestock(data.getAftValLivestock());
        globalData.setHhBRFValSmallBusiness(data.getBrfValSmallBusiness());
        globalData.setHhAFTValSmallBusiness(data.getAftValSmallBusiness());
        globalData.setHhBRFValEmployment(data.getBrfValEmployment());
        globalData.setHhAFTValEmployment(data.getAftValEmployment());
        globalData.setHhBRFValRemittances(data.getBrfValRemittances());
        globalData.setHhAFTValRemittances(data.getAftValRemittances());
        globalData.setHhBRFCntWageEnr(data.getBrfCntWageEnr());
        globalData.setHhAFTCntWageEnr(data.getAftCntWageEnr());


        globalData.setEntryDate(data.getEntryDate());
        globalData.setEntryBy(data.getEntryBy());

        return globalData;
    }

}
