package com.siddiquinoor.restclient.activity.sub_activity.commu_group_sub;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.GroupSearchPage;
import com.siddiquinoor.restclient.activity.MemberSearchPage;
import com.siddiquinoor.restclient.data_model.LayRCodes;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.data_model.adapters.CommunityGroupDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.siddiquinoor.restclient.manager.sqlsyntax.Schema;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;
import com.siddiquinoor.restclient.views.spinner.SpinnerLoader;

public class CommunityGroupNDetailsRecodes extends BaseActivity {
    // TODO: 10/18/2016  change the Schema  of Communninity Group & details table (done )
    // TODO: 10/17/2016 1.> LayRCode2 Code  (solved)
    // TODO: 10/17/2016  2.> Add EntryBy and Entry Date Column  in Community table (solved )
    // TODO: 10/17/2016  3.>  Add layR3List instead of the  layR2 List (solved )
    // TODO: 10/17/2016  4.> Lay1 lay2 lay3 as  primary key (solved )
    // TODO: 10/17/2016  5.> Use the Column Constant (done )
    // TODO: 10/17/2016  6.> Save once Validation
    // TODO: 10/17/2016  Use the Column Constant (Done)
    // TODO: 10/18/2016  8.>set validation to prevent duplicate values (solved )

    private static final String TAG = CommunityGroupNDetailsRecodes.class.getSimpleName();
    private Context mContext = CommunityGroupNDetailsRecodes.this;
    private SimpleDateFormat formatUSA = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
    private Calendar calendar = Calendar.getInstance();
    private Spinner spCountry, spAward, spProgram/*, spOrganization*/, spStatus, spActive;
    private Spinner spStaffName;
    private TextView tvFormation;
    private Spinner spLayR3List, spGroup, spGroupCategories;
    private EditText edtRepresentative, edtContactNo;
    private Button btnSave, btnDelete, btnBackward, btnHome;
    //private Intent intent;

    SQLiteHandler sqlH;
    private String idCountry, strCountry, idAward, strAward, idDonor, idProgram, strProgram, idActive, idStatus, strContactNo, strRepresentative;
    // private String strDate;
    private String entryBy;
    private String entryDate;
    private String strFormationDate;
    private String idLayR1Code;
    private String idLayR2Code;
    private String idLayR3Code;

    private String oldLayR1Code;
    private String oldLayR2Code;
    private String oldLayR3Code;

    private String strLayR3Name;
    private String idGroup;
    private String strGroup;
    private String idGroupCat;
    private String strGroupCat;

    private CommunityGroupDataModel grpData;
    private String idOrg;
    private String strOrg;

    private String idStaff;
    private String strStaff;
    private Spinner spOrg;
    private EditText edtGroupName;
    private boolean addNewFlag = false;


    private boolean savedOnce;
    private TextView layR2lable;
    private ADNotificationManager dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_grp_details);

        init();
        Intent intent = getIntent();


        addNewFlag = intent.getBooleanExtra(KEY.ADD_FLAG_KEY, false);

        if (!addNewFlag) {
            edtGroupName.setVisibility(View.GONE);
            grpData = intent.getParcelableExtra(KEY.COMMUNITY_GRP_DATA_OBJECT_KEY);
            Log.d("SAL", "getCommuCategoriesName: " + grpData.getCommuCategoriesName()
                    + "getCommunityGroupName: " + grpData.getCommunityGroupName()
                    + "getCommunityGroupCode: " + grpData.getCommunityGroupCode()
                    + "getAwardCode: " + grpData.getAwardCode()
                    + "getAwardName: " + grpData.getAwardName()
                    + "getAwardName: " + grpData.getProgramName()
                    + "getProgramCode: " + grpData.getProgramCode()
                    + "\n getLayr1Code: " + grpData.getLayr1Code()
                    + "\n getLayr2Code: " + grpData.getLayr2Code()
                    + "\n getLayr3Code: " + grpData.getLayr3Code()
                    + "\n getLayr3Name: " + grpData.getLayr3Name()
                    + "\ngetCommuCategoriesCode: " + grpData.getCommuCategoriesCode()
                    + "\ngetOrgonizationCode: " + grpData.getOrgonizationCode()
                    + "\ngetOrgonizationName: " + grpData.getOrgonizationName()
                    + "\ngetStaffCode: " + grpData.getStaffCode()
                    + "\ngetStaffName: " + grpData.getStaffName()
                    + "\ngetRepName: " + grpData.getRepName()
                    + "\ngetRepPhoneNo: " + grpData.getRepPhoneNo()
                    + "\ngetFormation: " + grpData.getFormation()
                    + "\ngetStatus: " + grpData.getStatus()
                    + "\ngetActive: " + grpData.getActive()
            );

            idAward = grpData.getAwardCode();
            strAward = grpData.getAwardName();
            idProgram = grpData.getProgramCode();
            strProgram = grpData.getProgramShortName();
            // TODO: 10/19/2016  get layr 1 2 3 with contrict
            idLayR1Code = grpData.getLayr1Code();
            idLayR2Code = grpData.getLayr2Code();
            idLayR3Code = grpData.getLayr3Code();
            strLayR3Name = grpData.getLayr3Name();
            idGroup = grpData.getCommunityGroupCode();
            strGroup = grpData.getCommunityGroupName();
            idGroupCat = grpData.getCommuCategoriesCode();
            strGroupCat = grpData.getCommuCategoriesName();
            idOrg = grpData.getOrgonizationCode();
            strOrg = grpData.getOrgonizationName();
            idStaff = grpData.getStaffCode();
            strStaff = grpData.getStaffName();
            edtRepresentative.setText(grpData.getRepName());

            edtContactNo.setText(grpData.getRepPhoneNo());
            tvFormation.setText(grpData.getFormation());

            idStatus = grpData.getStatus();
            idActive = grpData.getActive();
        } else {
            spGroup.setVisibility(View.GONE);
            idDonor = intent.getStringExtra(KEY.DONOR_CODE);
            idAward = intent.getStringExtra(KEY.AWARD_CODE);
            idProgram = intent.getStringExtra(KEY.PROGRAM_CODE);
            strProgram = sqlH.getProgramShortName(idAward, idDonor, idProgram);

        }


        loadCountry();
        //loadAward(idCountry);
        // staffName();
        loadActiveStatus();
        loadStatus();
        buttonActionListener();
        tvFormation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFormationDate();
            }
        });

        getEntryCredentials();
    }

    private void init() {
        sqlH = new SQLiteHandler(this);
        dialog = new ADNotificationManager();
        viewReferences();
        savedOnce = false;


    }

    private void viewReferences() {
        spCountry = (Spinner) findViewById(R.id.sp_grp_details_country);
        spAward = (Spinner) findViewById(R.id.sp_grp_details_award);
        spProgram = (Spinner) findViewById(R.id.sp_grp_details_program);
        // spOrganization = (Spinner) findViewById(R.id.sp_grp_details_organization);
        spStatus = (Spinner) findViewById(R.id.sp_grp_details_status);
        spActive = (Spinner) findViewById(R.id.sp_grp_details_active);
        spLayR3List = (Spinner) findViewById(R.id.sp_grp_details_ta);
        spGroup = (Spinner) findViewById(R.id.sp_grp_details_group);
        spGroupCategories = (Spinner) findViewById(R.id.sp_grp_details_category);
        spStaffName = (Spinner) findViewById(R.id.sp_grp_details_staff_name);
        tvFormation = (TextView) findViewById(R.id.tv_grp_details_formation);
        edtRepresentative = (EditText) findViewById(R.id.edt_grp_details_representative);
        edtContactNo = (EditText) findViewById(R.id.edt_grp_details_contact_no);
        btnSave = (Button) findViewById(R.id.btn_grp_details_save);
        btnDelete = (Button) findViewById(R.id.btn_grp_details_delete);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnBackward = (Button) findViewById(R.id.btnRegisterFooter);

        spOrg = (Spinner) findViewById(R.id.sp_grp_details_organization);
        edtGroupName = (EditText) findViewById(R.id.edt_communityName);

        layR2lable = (TextView) findViewById(R.id.tv_grp_details_layR3lable);


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
        setHomeButton();
        setSaveButton();
        setBackwardButton();
        setDeleteButton();
    }

// TODO: 10/17/2016 set  padding dynamicly  method

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setHomeButton() {
        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);

        setPaddingButton(mContext, imageHome, btnHome);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setSaveButton() {
        btnSave.setText("");
        Drawable saveImage = getResources().getDrawable(R.drawable.save_b);
        btnSave.setCompoundDrawablesRelativeWithIntrinsicBounds(saveImage, null, null, null);
        setPaddingButton(mContext, saveImage, btnSave);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setBackwardButton() {
        btnBackward.setText("");
        Drawable backwardImage = getResources().getDrawable(R.drawable.goto_back);
        btnBackward.setCompoundDrawablesRelativeWithIntrinsicBounds(backwardImage, null, null, null);
        setPaddingButton(mContext, backwardImage, btnBackward);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setDeleteButton() {
        btnDelete.setText("");
        Drawable deleteImage = getResources().getDrawable(R.drawable.assign);
        btnDelete.setCompoundDrawablesRelativeWithIntrinsicBounds(deleteImage, null, null, null);
        setPaddingButton(mContext, deleteImage, btnDelete);

    }

    /**
     * This method load the County Name to the spinner
     */

    private void loadCountry() {

        String operationModeName = sqlH.getDeviceOperationModeName();


        SpinnerLoader.loadCountryLoader(mContext, sqlH, spCountry, idCountry, strCountry, SQLiteQuery.loadCountry_sql(operationModeName));


        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCountry = ((SpinnerHelper) spCountry.getSelectedItem()).getValue();
                idCountry = ((SpinnerHelper) spCountry.getSelectedItem()).getId();

                if (idCountry.length() > 2) {
                    loadAward(idCountry);
                    layR2lable.setText(sqlH.getLayerLabel(idCountry, "3"));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    /**
     * LOAD :: UNIT: loadLayR3List
     */
    private void loadLayR3List(String cCode) {
//        int position = 0;
//        String criteria =SQLiteQuery.loadLayR3List_sql(cCode) ;
//
//
//        List<SpinnerHelper> listLayR2Code = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, cCode, false);
//        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listLayR2Code);
//        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
//        spLayR3List.setAdapter(dataAdapter);
//
//
//        if (idLayR3Code != null) {
//
//            for (int i = 0; i < spLayR3List.getCount(); i++) {
//                String LayR2Name = spLayR3List.getItemAtPosition(i).toString();
//                if (LayR2Name.equals(strLayR3Name)) {
//                    position = i;
//                }
//            }
//            spLayR3List.setSelection(position);
//
//            if (!addNewFlag) {
//                spLayR3List.setEnabled(false);
//            }
//
//        }

        boolean disableFlag = false;
        if (!addNewFlag) {
            disableFlag = true;
        }

        SpinnerLoader.loadLayR3ListLoader(mContext, sqlH,spLayR3List,idLayR3Code,strLayR3Name,cCode,SQLiteQuery.loadLayR3List_sql(cCode),true,disableFlag);
        spLayR3List.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                strLayR3Name = ((SpinnerHelper) spLayR3List.getSelectedItem()).getValue();
                String layR2Code = ((SpinnerHelper) spLayR3List.getSelectedItem()).getId();

                if (layR2Code.length() > 5) {
                    idLayR1Code = layR2Code.substring(0, 2);
                    /**                     * the culprit is here                     */
                    idLayR2Code = layR2Code.substring(2, 4);
                    /**                     * implement the layR 3                     */
                    idLayR3Code = layR2Code.substring(4);
                    loadGroupCategory(idCountry, idDonor, idAward, idProgram);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void loadStaffName(String cCode, String orgCode) {
        int position = 0;


        String criteria = "SELECT " + SQLiteHandler.STAFF_ID_COL
                + ", " + SQLiteHandler.STAFF_NAME_COL
                + " FROM " + SQLiteHandler.STAFF_MASTER_TABLE
                + " WHERE " + SQLiteHandler.STAFF_COUNTRY_CODE + " ='" + cCode + "'"
                + " AND " + SQLiteHandler.ORG_N_CODE_COL + " ='" + orgCode + "'";


        List<SpinnerHelper> listStaff = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, cCode, false);


        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listStaff);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spStaffName.setAdapter(dataAdapter);


        if (idStaff != null) {

            for (int i = 0; i < spStaffName.getCount(); i++) {
                String staffName = spStaffName.getItemAtPosition(i).toString();
                if (staffName.equals(strStaff)) {
                    position = i;
                }
            }
            spStaffName.setSelection(position);
        }


        spStaffName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                strStaff = ((SpinnerHelper) spStaffName.getSelectedItem()).getValue();
                idStaff = ((SpinnerHelper) spStaffName.getSelectedItem()).getId();
// todo: recheck this position
//                if (idStaff.length() > 0) {

//                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * @param cCode     County code
     * @param donorCode donorCode
     * @param awardCode awardCode
     * @see Schema#createTableProgOrgNRole() &
     * @see Schema#createTableProgOrgN()
     * @see SQLiteQuery#loadOrganization_sql(String, String, String)
     */
    private void loadOrganization(final String cCode, final String donorCode, final String awardCode) {
        SpinnerLoader.loadOrganizationLoader(mContext, sqlH, spOrg, cCode, idOrg, strOrg, SQLiteQuery.loadOrganization_sql(cCode, donorCode, awardCode));


        spOrg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strOrg = ((SpinnerHelper) spOrg.getSelectedItem()).getValue();
                idOrg = ((SpinnerHelper) spOrg.getSelectedItem()).getId();
                if (idOrg.length() > 0)
                    loadStaffName(idCountry, idOrg);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void loadActiveStatus() {
        int pos;

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

    private void loadStatus() {
        int pos = 0;

        ArrayAdapter<CharSequence> adptMartial = ArrayAdapter.createFromResource(
                this, R.array.arrStatus, R.layout.spinner_layout);

        adptMartial.setDropDownViewResource(R.layout.spinner_layout);
        spStatus.setAdapter(adptMartial);
        if (idStatus != null) {
            if (idStatus.equals("N"))
                pos = 0;
            else
                pos = 1;

            spStatus.setSelection(pos);
        }


        spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String strStatus = parent.getItemAtPosition(position).toString();

                if (strStatus.equals("NEW"))
                    idStatus = "N";

                else
                    idStatus = "E";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void buttonActionListener() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity((Activity) mContext);
            /*   Intent iHome = new Intent(mContext, MainActivity.class);
                startActivity(iHome);*/
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveProcess();


            }
        });
        btnBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGroupSear = new Intent(CommunityGroupNDetailsRecodes.this, GroupSearchPage.class);
                iGroupSear.putExtra(KEY.COUNTRY_ID, idCountry);
                iGroupSear.putExtra(KEY.STR_COUNTRY, strCountry);
                finish();
                startActivity(iGroupSear);

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunityGroupNDetailsRecodes.this, MemberSearchPage.class);
                intent.putExtra(KEY.COUNTRY_ID, idCountry);
                intent.putExtra(KEY.DIR_CLASS_NAME_KEY, "MainActivity");
                finish();
                startActivity(intent);
            }
        });
    }

    /**
     * this method save the Community Basic and Community details Table
     *
     * @see Schema#sqlCreateCommunityGroup_Table()
     * @see Schema#createTableCommunityGrpDetail()
     */

    private void saveProcess() {
        String GrpName = "";
        getText();

        /**
         * Set validation for GrpName

         */
        if (!addNewFlag) {
            GrpName = strGroup;
        } else {
            GrpName = edtGroupName.getText().toString();
        }


        if (idProgram.equals("00"))
            dialog.showErrorDialog(mContext, "Select Program");
        else if (idLayR3Code == null)
            dialog.showErrorDialog(mContext, "Select " + layR2lable.getText());
        else if (idGroupCat.equals("00"))
            dialog.showErrorDialog(mContext, "Select Category");
        else if (GrpName.length() == 0)
            dialog.showErrorDialog(mContext, "Enter Group Name");
        else if (idOrg.equals("00"))
            dialog.showErrorDialog(mContext, "Select Organization ");
        else if (idStaff.equals("00"))
            dialog.showErrorDialog(mContext, "Select Staff ");
        else if (strRepresentative.length() == 0 || strRepresentative.equals("null"))
            dialog.showErrorDialog(mContext, "Enter Representative ");
        else if (strFormationDate.equals("Date"))
            dialog.showErrorDialog(mContext, "Enter Formation Date ");
        else {
            saveData();
        }


    }

    private void saveData() {
        String GrpName;
        String entryBy = getStaffID();

        SQLServerSyntaxGenerator syntax = new SQLServerSyntaxGenerator();
        syntax.setAdmCountryCode(idCountry);
        syntax.setAdmDonorCode(idDonor);
        syntax.setAdmAwardCode(idAward);
        syntax.setProgCode(idProgram);
        syntax.setGrpCode(idGroup);
        syntax.setOrganizationCode(idOrg);
        syntax.setActive(idActive);
        //syntax.setIrrigationSystemUsed(null);
        //syntax.setLandSizeUnderIrrigation(null);
        //  syntax.setFundSupport(null);
        syntax.setRepresentativeName(strRepresentative);
        syntax.setRepresentativePhoneNumber(strContactNo);
        syntax.setFormationDate(strFormationDate);
        syntax.setStaffCode(idStaff);
        syntax.setEntryBy(getStaffID());
        syntax.setEntryDate(entryDate);

        String SrvCenterCode = "";

        /**
         * set Validation for GrpName
         */

        /**
         * if it edit mode the globel veriable strGroup provides the group name from 
         * the spinner 
         */
        if (!addNewFlag) {
            GrpName = strGroup;
        } else {
            GrpName = edtGroupName.getText().toString();
        }


        syntax.setGrpName(GrpName);
        syntax.setGrpCatCode(idGroupCat);
        syntax.setLayR1ListCode(idLayR1Code);
        syntax.setLayR2ListCode(idLayR2Code);
        syntax.setLayR3ListCode(idLayR3Code);


        if (!addNewFlag) {
            syntax.setGrpCode(idGroup);
/**
 * if lay exits
 */
            LayRCodes layRCodes = sqlH.getLayRListFromGroupDetails(idCountry, idDonor, idAward, idProgram, idGroup);
            Log.d("jiba", "getLayR1Code : " + layRCodes.getLayR1Code() + " getLayR2Code:" + layRCodes.getLayR2Code() + " getLayR2Code:" + layRCodes.getLayR3Code());

            sqlH.editIntoGroupDetails(idCountry, idDonor, idAward, idProgram, idGroup, idOrg, idStaff, null, null, null, idActive, strRepresentative, strContactNo, strFormationDate, null, idStatus, entryBy, entryDate, null, null, idLayR1Code, idLayR2Code, idLayR3Code, layRCodes);
            if (layRCodes.getLayR3Code().equals("-"))
                sqlH.insertIntoUploadTable(syntax.updateIntoCommunityGrpDetailIfLayR3CodeNotExit(layRCodes));
            else
                sqlH.insertIntoUploadTable(syntax.updateIntoCommunityGrpDetail(layRCodes));
        } else {


            /**
             * insert method
             */
            if (!savedOnce) {
// TODO: 10/19/2016 wht to do about next id

                idGroup = sqlH.getNextGroupId(idCountry, idDonor, idAward, idProgram, idLayR1Code, idLayR2Code, idLayR3Code);
                syntax.setGrpCode(idGroup);

                /**
                 *  basic Community Group
                 */
                sqlH.addCommunityGroup(idCountry, idDonor, idAward, idProgram, idGroup, GrpName, idGroupCat, idLayR1Code, idLayR2Code, idLayR3Code, SrvCenterCode, entryBy, entryDate);
                sqlH.insertIntoUploadTable(syntax.insertIntoCommunityGroupTable());
                /**
                 * Community Group details
                 */
                sqlH.addIntoGroupDetails(idCountry, idDonor, idAward, idProgram, idGroup, idOrg, idStaff, null, null, null, idActive, strRepresentative, strContactNo, strFormationDate, null, idStatus, entryBy, entryDate, null, null, idLayR1Code, idLayR2Code, idLayR3Code);
                sqlH.insertIntoUploadTable(syntax.insertIntoCommunityGrpDetail());

                oldLayR1Code = idLayR1Code;
                oldLayR2Code = idLayR2Code;
                oldLayR3Code = idLayR3Code;
                /**
                 * the data is saved now ...
                 */
                savedOnce = true;
            }
            /**
             * new Entry but edit the field update method
             */
            else {


                /**
                 *  basic Community Group
                 */
                sqlH.editCommunityGroup(idCountry, idDonor, idAward, idProgram, idGroup, GrpName, idGroupCat, idLayR1Code, idLayR2Code, idLayR3Code, entryBy, entryDate, oldLayR1Code, oldLayR2Code, oldLayR3Code);
                LayRCodes layRCodes_group = sqlH.getLayRListFromCommunityGroup(idCountry, idDonor, idAward, idProgram, idGroup, strGroup);

                sqlH.insertIntoUploadTable(syntax.updateIntoCommunityGroupTable(layRCodes_group));

                LayRCodes layRCodes_details = sqlH.getLayRListFromGroupDetails(idCountry, idDonor, idAward, idProgram, idGroup);

                /**
                 * Community Group details
                 */
                sqlH.editIntoGroupDetails(idCountry, idDonor, idAward, idProgram, idGroup, idOrg, idStaff, null, null, null, idActive, strRepresentative, strContactNo, strFormationDate, null, idStatus, entryBy, entryDate, null, null, idLayR1Code, idLayR2Code, idLayR3Code, layRCodes_details);
                sqlH.insertIntoUploadTable(syntax.updateIntoCommunityGrpDetail(layRCodes_details));

            }


        }


        Toast.makeText(CommunityGroupNDetailsRecodes.this, "Save Successfully", Toast.LENGTH_SHORT).show();
    }


    private void getText() {
        strContactNo = edtContactNo.getText().toString();
        strRepresentative = edtRepresentative.getText().toString();
        strFormationDate = tvFormation.getText().toString();


        if (strContactNo.length() > 0) {
            //TODO: DO SOME WORK;
        } else {
            //TODO: Nothing To DO;
        }

        if (strRepresentative.length() > 0) {
            //TODO:: DO SOME WORK
        } else {
            //TODO:: NOthing To Do;
        }
        if (tvFormation.length() > 0) {
            //TODO:: DO SOME STAFF
        }
    }


    //TODO: DATE SETTINGS
    public void setFormationDate() {
        new DatePickerDialog(mContext, d, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void updateFormationDate() {
        tvFormation.setText(formatUSA.format(calendar.getTime()));
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateFormationDate();
        }
    };


    private void getEntryCredentials() {
        try {
            entryBy = getStaffID();
            entryDate = getDateTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * LOAD :: GroupCategory
     *
     * @param cCode     Adm Country Code
     * @param donorCode Adm Donor Code
     * @param awardCode Adm Award Code
     * @param progCode  Adm Program Code
     */
    private void loadGroupCategory(final String cCode, final String donorCode, final String awardCode, final String progCode) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.ADM_PROG_CODE_COL + " = '" + progCode + "' "
                + " GROUP BY " + SQLiteHandler.GROUP_CAT_CODE_COL + " , " + SQLiteHandler.GROUP_CAT_NAME_COL;


        // Spinner Drop down elements for District
        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.COMMUNITY_GROUP_CATEGORY_TABLE, criteria, null, false);


        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listAward);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spGroupCategories.setAdapter(dataAdapter);


        if (idGroupCat != null) {
            for (int i = 0; i < spGroupCategories.getCount(); i++) {
                String groupCategory = spGroupCategories.getItemAtPosition(i).toString();
                if (groupCategory.equals(strGroupCat)) {
                    position = i;
                }
            }
            spGroupCategories.setSelection(position);

            /**
             * disable selection of the spinner
             */
            if (!addNewFlag) {
                spGroupCategories.setEnabled(false);
            }


        }


        spGroupCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strGroupCat = ((SpinnerHelper) spGroupCategories.getSelectedItem()).getValue();
                idGroupCat = ((SpinnerHelper) spGroupCategories.getSelectedItem()).getId();

                if (idGroupCat.length() > 2)
                    loadGroup(cCode, donorCode, awardCode, progCode, idGroupCat);

                Log.d(TAG, "Group Category ,idGroupCat:" + idGroupCat + " strGroupCat : " + strGroupCat);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    private void loadAward(final String idCountry) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.ADM_COUNTRY_AWARD_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + idCountry + "'";

        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.ADM_COUNTRY_AWARD_TABLE, criteria, null, false);

        listAward.remove(0);

        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listAward);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spAward.setAdapter(dataAdapter);
        if (idAward != null) {
            for (int i = 0; i < spAward.getCount(); i++) {
                String award = spAward.getItemAtPosition(i).toString();
                if (award.equals(strAward)) {
                    position = i;
                }
            }
            spAward.setSelection(position);

        }
        spAward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strAward = ((SpinnerHelper) spAward.getSelectedItem()).getValue();
                String awardCode = ((SpinnerHelper) spAward.getSelectedItem()).getId();
                if (awardCode.length() > 2) {
                    idDonor = awardCode.substring(0, 2);
                    idAward = awardCode.substring(2);
                    if (addNewFlag) {
                        loadProgram(idCountry, idAward, idDonor);
                        loadOrganization(idCountry, idAward, idDonor);
                    } else {
                        loadProgram(idCountry, idAward, idDonor);
                    }


                    // Log.d(TAG, "idAward : " + idAward + " donor id :" + idAward.substring(0, 2));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }


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

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.ADM_PROG_CODE_COL + " = '" + progCode + "' "
                + " AND " + SQLiteHandler.GROUP_CAT_CODE_COL + " = '" + grpCateCode + "' "
                //    + " AND " + SQLiteHandler.SERVICE_CENTER_CODE_COL + " = '" + idSrvCenter + "' "
                ;


        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.COMMUNITY_GROUP_TABLE, criteria, null, false);


        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listAward);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spGroup.setAdapter(dataAdapter);


        if (idGroup != null) {
            for (int i = 0; i < spGroup.getCount(); i++) {
                String groupCategory = spGroup.getItemAtPosition(i).toString();
                if (groupCategory.equals(strGroup)) {
                    position = i;
                }
            }
            spGroup.setSelection(position);
            /**
             * disable selection of the spinner
             */
            if (!addNewFlag) {
                spGroup.setEnabled(false);
            }
        }


        spGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strGroup = ((SpinnerHelper) spGroup.getSelectedItem()).getValue();
                idGroup = ((SpinnerHelper) spGroup.getSelectedItem()).getId();

                if (idGroup.length() > 2) {
                    loadOrganization(idCountry, idDonor, idAward);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    private void loadProgram(final String cCode, final String awardCode, final String donorCode) {
        boolean disableFlag = false;
        if (!addNewFlag) {
            disableFlag = true;
        }
        SpinnerLoader.loadProgramLoader(mContext, sqlH, spProgram, idProgram, strProgram, SQLiteQuery.loadProgram_sql(awardCode, donorCode), true, disableFlag);

        spProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getValue();
                idProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getId();
                if (idProgram.length() > 2) {
//                    Log.d("DIM"," strLayR3Name : "+strLayR3Name+"idLayR3Code:"+idLayR3Code);
                    loadLayR3List(idCountry);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
