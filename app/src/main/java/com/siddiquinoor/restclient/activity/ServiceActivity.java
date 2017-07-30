package com.siddiquinoor.restclient.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;

import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;
import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.sub_activity.service_sub.ServiceRecordDetails;
import com.siddiquinoor.restclient.activity.sub_activity.service_sub.ServiceSpecification;
import com.siddiquinoor.restclient.activity.sub_activity.service_sub.ServiceVoucherDetails;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.views.adapters.ServiceDataModel;
import com.siddiquinoor.restclient.views.adapters.VouItemServiceExtDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;
import com.siddiquinoor.restclient.views.spinner.SpinnerLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static com.siddiquinoor.restclient.manager.SQLiteHandler.ADM_AWARD_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.GROUP_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.GROUP_NAME_COL;


public class ServiceActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String FFA = "FFA";
    private static final String C1 = "C1";
    private static final String C2 = "C2";
    private static final String C3 = "C3";
    private static final String DRR = "DRR";
    private static final String UCT = "UCT";
    private static final String CFWS = "CFWS";
    private static final String CFWU = "CFWU";
    public static final String PW = "PW";
    public static final String IG = "IG";
    public static final String MG = "MG";
    public static final String LM = "LM";
    public static final String CU2 = "CU2";
    public static final String CA2 = "CA2";
    public static final String HHR = "HHR";
    public static final String CFWE = "CFWE";
    public static final String AGR = "AGR";
    public static final String MCHN = "MCHN";
    // for log  tag
    private final String TAG = ServiceActivity.class.getName();

    AlertDialog goToDialog;
    Intent intent;

    private Spinner spAward, spCriteria, spServiceCenter, spServiceMonth;

    private String strServiceCenter;
    private SQLiteHandler sqlH;
    private String strAward, strCriteria, strSrvMonth, strGroupCat, strGroup, strVLayR4List;
    private String idCountry, idAward, idDonor, idProgram, idCriteria, idService, idOpCode, idOpMonthCode,
            idSrvCenter, idFdpCode, idMemberSearch, idServiceMonth, idGroupCat, idGroup, idLayR4List;
    // private String serviceMonthCode;
    private String strOpMonthLabel = null;
    private TextView tv_srvDate;
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private Button btnHome, btnSummary;

    private Button btnSave;
    private ServiceDataListAdapter adapter;
    private ArrayList<ServiceDataModel> serviceArray = new ArrayList<ServiceDataModel>();
    ListView mListView;
    private Button btn_search;
    private EditText edt_srvMMSerach;
    private String opMonthLable;
    HashMap<String, String> dateRange;
    private Context mContext;
    private ADNotificationManager erroDialog = new ADNotificationManager();
    public static ProgressDialog pDialog;
    private CheckBox checkBox_header;
    // private static boolean isNotAdded = true;

    /**
     * To save checked items, and re-add while scrolling.
     */
    SparseBooleanArray mChecked = new SparseBooleanArray();

    private static int count = 0;

    private Spinner spGroupCategories, spGroup, spLayR4List;

    private TextView tv_srvTitleCount, tv_GrpCatLabel, tv_GrpLabel, tv_LayR4Label;
    private boolean fromQR = false;
    private Spinner spDistributionType;
    private String strDistType;
    private String idDistributionType;
    private String idGrpLayR1Code;
    private String idGrpLayR2Code;
    private String idGrpLayR3Code;
    /**
     * member details layer
     */
    private LinearLayout lay_MemberDetails;
    private TextView tv_MemberDetails_HHID, tv_MemberDetails_MemID, tv_MemberDetails_MemName;
    private EditText edt_MemberDetails_working_days;
    private String wd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        initialize();


        setAllListener();


        Intent intent = getIntent();
        String countryId;
        String dir = intent.getStringExtra(KEY.DIR_CLASS_NAME_KEY);
        if (dir.equals("ServiceRecordDetails")) {


            countryId = intent.getStringExtra(KEY.COUNTRY_ID);

            idAward = intent.getStringExtra(KEY.AWARD_CODE);
            strAward = intent.getStringExtra(KEY.AWARD_NAME);

            idDonor = intent.getStringExtra(KEY.DONOR_CODE);
            strCriteria = intent.getStringExtra(KEY.CRITERIA_NAME);
            idCriteria = intent.getStringExtra(KEY.CRITERIA_CODE);
            idSrvCenter = intent.getStringExtra(KEY.SERVICE_CENTER_CODE);
            strServiceCenter = intent.getStringExtra(KEY.SERVICE_CENTER_NAME);
            String srDate = intent.getStringExtra(KEY.SERVICE_DATE);
            opMonthLable = intent.getStringExtra(KEY.OP_MONTH_LABLE);
            idOpMonthCode = intent.getStringExtra(KEY.OP_MONTH_CODE);
            idOpCode = intent.getStringExtra(KEY.OP_CODE);

            idServiceMonth = intent.getStringExtra(KEY.SERVICE_MONTH_CODE);
            strSrvMonth = intent.getStringExtra(KEY.SERVICE_MONTH_NAME);

            tv_srvDate.setText(srDate);

            idProgram = idCriteria.substring(0, 3);
            idService = idCriteria.substring(3);

            idGroup = intent.getStringExtra(KEY.GROUP_CODE);
            strGroup = intent.getStringExtra(KEY.GROUP_NAME);
            idGroupCat = intent.getStringExtra(KEY.GROUP_CATEGORY_CODE);
            strGroupCat = intent.getStringExtra(KEY.GROUP_CATEGORY_NAME);

            //   Log.d("NIR0", "idGroupCat :" + idGroupCat + "strGroupCat: " + strGroupCat + " idGroup :" + idGroup + " strGroup" + strGroup);

            loadindingLog(countryId, srDate);


            String memSearchId = "";


        } else if (dir.equals("ServiceVoucherDetails")) {

            countryId = intent.getStringExtra(KEY.COUNTRY_ID);

            idAward = intent.getStringExtra(KEY.AWARD_CODE);
            strAward = intent.getStringExtra(KEY.AWARD_NAME);

            idDonor = intent.getStringExtra(KEY.DONOR_CODE);
            strCriteria = intent.getStringExtra(KEY.CRITERIA_NAME);
            idCriteria = intent.getStringExtra(KEY.CRITERIA_CODE);
            idSrvCenter = intent.getStringExtra(KEY.SERVICE_CENTER_CODE);
            strServiceCenter = intent.getStringExtra(KEY.SERVICE_CENTER_NAME);
            String srDate = intent.getStringExtra(KEY.SERVICE_DATE);
            opMonthLable = intent.getStringExtra(KEY.OP_MONTH_LABLE);
            idOpMonthCode = intent.getStringExtra(KEY.OP_MONTH_CODE);
            idOpCode = intent.getStringExtra(KEY.OP_CODE);

            idServiceMonth = intent.getStringExtra(KEY.SERVICE_MONTH_CODE);
            strSrvMonth = intent.getStringExtra(KEY.SERVICE_MONTH_NAME);

            tv_srvDate.setText(srDate);

            idProgram = idCriteria.substring(0, 3);
            idService = idCriteria.substring(3);

            idGroup = intent.getStringExtra(KEY.GROUP_CODE);
            strGroup = intent.getStringExtra(KEY.GROUP_NAME);
            idGroupCat = intent.getStringExtra(KEY.GROUP_CATEGORY_CODE);
            strGroupCat = intent.getStringExtra(KEY.GROUP_CATEGORY_NAME);

            Log.d("NIR0", "idGroupCat :" + idGroupCat + "strGroupCat: " + strGroupCat + " idGroup :" + idGroup + " strGroup" + strGroup);

            //loadAward(countryId);
            loadindingLog(countryId, srDate);

            String memSearchId = "";


        } else if (dir.equals("ServiceSpecification")) {

            countryId = intent.getStringExtra(KEY.COUNTRY_ID);
            idAward = intent.getStringExtra(KEY.AWARD_CODE);
            strAward = intent.getStringExtra(KEY.AWARD_NAME);

            idDonor = intent.getStringExtra(KEY.DONOR_CODE);
            strCriteria = intent.getStringExtra(KEY.CRITERIA_NAME);
            idCriteria = intent.getStringExtra(KEY.CRITERIA_CODE);
            idSrvCenter = intent.getStringExtra(KEY.SERVICE_CENTER_CODE);
            strServiceCenter = intent.getStringExtra(KEY.SERVICE_CENTER_NAME);
            String srDate = intent.getStringExtra(KEY.SERVICE_DATE);
            idServiceMonth = intent.getStringExtra(KEY.SERVICE_MONTH_CODE);
            strSrvMonth = intent.getStringExtra(KEY.SERVICE_MONTH_NAME);
            tv_srvDate.setText(srDate);

            idProgram = idCriteria.substring(0, 3);
            idService = idCriteria.substring(3);


            idGroup = intent.getStringExtra(KEY.GROUP_CODE);
            strGroup = intent.getStringExtra(KEY.GROUP_NAME);
            idGroupCat = intent.getStringExtra(KEY.GROUP_CATEGORY_CODE);
            strGroupCat = intent.getStringExtra(KEY.GROUP_CATEGORY_NAME);

            Log.d("NIR0", "idGroupCat :" + idGroupCat + "strGroupCat: " + strGroupCat + " idGroup :" + idGroup + " strGroup" + strGroup);

            /// loadAward(countryId);

            testLogD(countryId, srDate, "ServiceSpecification");
        } else {


            countryId = intent.getStringExtra(KEY.COUNTRY_ID);
            // String strCountry = intent.getStringExtra("STR_COUNTRY");


        }


        loadServiceCenter(countryId);

        /**             * Select All / None DO NOT USE "setOnCheckedChangeListener" here.             */
        checkBox_header.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /**                     * Set all the checkbox to True/False                     */
                for (int i = 0; i < count; i++) {
                    mChecked.put(i, checkBox_header.isChecked());
                }

                /**                     * Update View                     */
                adapter.notifyDataSetChanged();

            }
        });

            /*
             * Add Header to ListView
             */

        fromQR = false;
        Button btn_service_qr_reader = (Button) findViewById(R.id.btn_service_qr);
        btn_service_qr_reader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(ServiceActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                // QR Code Capture Activity Orientation Set : degree unit
                integrator.setOrientation(90);
                // Scan View finder size : pixel unit
                integrator.addExtra(Intents.Scan.HEIGHT, 300);
                integrator.addExtra(Intents.Scan.WIDTH, 300);
                // Capture View Start
                integrator.initiateScan();
                fromQR = true;
            }
        });

        edt_srvMMSerach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (fromQR) {
                    String temId = edt_srvMMSerach.getText().toString().trim();
                    String serviceDate = tv_srvDate.getText().toString();

                    LoadingList loadlist = new LoadingList(idCountry, idDonor, idAward, idProgram, idService, temId, idOpMonthCode, strOpMonthLabel, idOpMonthCode, serviceDate, idSrvCenter, idGroup);
                    loadlist.execute();
                    // for test purpose
//                    loadServiceListView(idCountry, idDonor, idAward, idProgram, idService, temId, idOpMonthCode, strOpMonthLabel, idOpMonthCode, serviceDate, idSrvCenter, idGroup);
                    fromQR = false;
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void loadindingLog(String countryId, String srDate) {
        Log.d("NIR2", "From redrirect from service Record\n"
                + "countryId : " + countryId + " idAward : " + idAward
                + " strAward :" + strAward + " idDonor :" + idDonor
                + " strCriteria: " + strCriteria + " idCriteria : " + idCriteria
                + " idSrvCenter: " + idSrvCenter + " strServiceCenter : " + strServiceCenter
                + " serviceDate: " + srDate + " opMonthLable : " + opMonthLable
                + " serviceDate: " + idOpMonthCode + " idOpCode : " + idOpCode
                + " idServiceMonth: " + idServiceMonth + " strSrvMonth : " + strSrvMonth
                + " idGroup: " + idGroup + " strGroup : " + strGroup
                + " idGroupCat: " + idGroupCat + " strGroupCat : " + strGroupCat
        );
    }

    private void testLogD(String countryId, String srDate, String pageName) {
        Log.d("NIR1", "From redir from " + pageName + "\n"
                + "countryId : " + countryId + " idAward : " + idAward
                + " strAward :" + strAward + " idDonor :" + idDonor
                + " strCriteria: " + strCriteria + " idCriteria : " + idCriteria
                + " idSrvCenter: " + idSrvCenter + " strServiceCenter : " + strServiceCenter
                + " serviceDate: " + srDate + " idServiceMonth : " + idServiceMonth
                + " strSrvMonth: " + strSrvMonth
        );
    }

    private void initialize() {
        sqlH = new SQLiteHandler(this);
        mContext = ServiceActivity.this;
        viewReference();
        pDialog = new ProgressDialog(mContext);
        tv_LayR4Label.setText(UtilClass.getLayR4LabelName(mContext, idCountry));

        hidMemberDetailsLayer();
        showNHideGroupNCat(View.GONE);
        showNHideLayRList(View.GONE);
    }

    private void hidMemberDetailsLayer() {
        lay_MemberDetails.setVisibility(View.GONE);
    }

    private void showMemberDetailsLayer() {
        lay_MemberDetails.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IntentIntegrator.REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    String qr_content = data.getStringExtra(Intents.Scan.RESULT);
//qr_reader_result_textView.setText(qr_content);
                    edt_srvMMSerach.setText(qr_content);
                } else {
                    Log.d("TAG", "Result Not Ok");
                }
                break;
            default:
                Log.d("TAG", "No Result Code");
                break;
        }
    }

    private void setAllListener() {

        btnHome.setOnClickListener(this);
        tv_srvDate.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMemberData();
            }
        });
        btnSummary.setOnClickListener(this);

    }

    private void searchMemberData() {

        String temId = edt_srvMMSerach.getText().toString().trim();

        String serviceDate = tv_srvDate.getText().toString();
        if (temId.isEmpty() || temId.equals("") || temId.length() > 0) {

            idMemberSearch = temId;
            if (idDonor.equals("00") || idAward.equals("")) {
                erroDialog.showErrorDialog(mContext, "Select Award");
            } else if (idSrvCenter == null || idSrvCenter.equals("00")) {
                erroDialog.showErrorDialog(mContext, "Select Service Center");
            } else if (idServiceMonth == null || idServiceMonth.equals("00")) {
                erroDialog.showErrorDialog(mContext, "Select Service Month");
            } else if (serviceDate.equals("") || serviceDate.equals("yyyy-mm-dd") || serviceDate.equals("Date")) {

                erroDialog.showErrorDialog(mContext, "Select a Date");
            } else if (idProgram == null || idService == null) {
                erroDialog.showErrorDialog(mContext, "Select Criteria");
            } else {

                try {
                    dateRange = sqlH.getDateRangeForService(idCountry, idOpMonthCode);
                    String start_date = dateRange.get("sdate");
                    String end_date = dateRange.get("edate");


                    idOpCode = "2";
                    idOpMonthCode = idServiceMonth.substring(8);
                    strOpMonthLabel = strSrvMonth;


                    if (serviceDate != null && start_date != null && end_date != null) {
                        if (!getValidDateRangeUSAFormat(serviceDate, start_date, end_date)) {

                            erroDialog.showErrorDialog(mContext, "Service date is not within the valid range.");

                        } else {

                            LoadingList loadlist = new LoadingList(idCountry, idDonor, idAward,
                                    idProgram, idService, idMemberSearch, idOpMonthCode,
                                    strOpMonthLabel, idOpMonthCode, serviceDate, idSrvCenter, idGroup);
                            loadlist.execute();
                            /// for test purpose don't delete below code
//                            loadServiceListView(idCountry, idDonor, idAward, idProgram, idService, idMemberSearch, idOpMonthCode, strOpMonthLabel, idOpMonthCode, serviceDate, idSrvCenter, idGroup);
                        }

                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }


        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_srvDate:
                setDate();
                break;
            case R.id.btn_service_save:

                saveData();

                break;

            case R.id.btnHomeFooter:

                //    goToAlert();
                goToMainActivity((Activity) mContext);


                break;
            case R.id.btnRegisterFooter:
                finish();
                Intent iSummary = new Intent(mContext, SummaryMenuActivity.class);
                iSummary.putExtra("ID_COUNTRY", idCountry);
                startActivity(iSummary);
                break;
        }
    }

    /**
     * Save  data
     */

    private void saveData() {

        String wd = null;
        String serviceDate = tv_srvDate.getText().toString();

        /** *  Check the is service date  valid date or not  */
        if (serviceDate.equals("") || serviceDate.equals("yyyy-mm-dd") || serviceDate.equals("Date"))
            erroDialog.showErrorDialog(mContext, "Please select a Date ");
        else if (idSrvCenter.equals("00"))
            erroDialog.showErrorDialog(mContext, "Please select Service Center ");
        else try {

                dateRange = sqlH.getDateRangeForService(idCountry, idOpMonthCode);
                String start_date = dateRange.get("sdate");
                String end_date = dateRange.get("edate");
                idOpCode = dateRange.get("opCode");
                strOpMonthLabel = dateRange.get("opMonthLable");


                if (start_date != null && end_date != null) {
                    if (!getValidDateRange(serviceDate, start_date, end_date, false)) {
                        erroDialog.showErrorDialog(mContext, "Service date is not within the valid range. Save attempt denied");

                    } else if (adapter.isArrayListNull()) {

                        erroDialog.showErrorDialog(mContext, "No records selected to save.");

                    } else {

                        ArrayList<ServiceDataModel> alist = new ArrayList<ServiceDataModel>();
                        alist = adapter.getArrayList();

                        String srvName;
                        String progName;
                        /***
                         * get program and service  name
                         */
                        srvName = sqlH.getServiceShortName(idProgram, idService);
                        progName = sqlH.getProgramShortName(idAward, idDonor, idProgram);


/**
 * must implement below code
 */
                        switch (srvName) {

                            case FFA:
                            case C1:
                            case C2:
                            case C3:
                            case MG:
                            case IG:
                                switch (progName) {

                                    case DRR:
                                    case AGR:
                                    case CFWS:
                                    case CFWU:


                                        if ((!srvName.equals(MG) && !srvName.equals(IG))) {

                                            if (checkMultipleCheckedOnGrid(idCountry, idDonor, idAward, idProgram)) {

                                                erroDialog.showErrorDialog(mContext, "Sure to apply default days for selected IDs?");

                                                defaulftWDIntial();
                                                break;
                                            }
                                            if (!checkOtherParameterIsGiven(srvName, progName, idCountry, idDonor, idAward, idProgram)) {

                                                erroDialog.showErrorDialog(mContext, "Invalid Attempt");

                                                return;
                                            }


                                        }


                                        break;

                                }
                                break;
                        }


                        wd = this.wd;
                        if (wd == null || wd.equals("0")) {
                            wd = sqlH.get_ProgSrvDefaultDays(idCountry, idDonor, idAward, idProgram, idService, idDistributionType);
                        }


                        Log.d("MOR11", "wd:" + wd + "\n idDistributionType:" + idDistributionType + " idService: " + idService);

                        try {
                            String EntryBy = getStaffID();
                            String EntryDate = getDateTime();


                            for (ServiceDataModel srvMemData : alist) {
                                srvMemData.setOpCode(idOpCode);
                                srvMemData.setOpMontheCode(idOpMonthCode);
                                srvMemData.setWorkingDay(wd);
//                            Log.d("SAVE", "Working  Daya setWorkingDay:" + srvMemData.getWorkingDay());


                                srvMemData.setServiceSLCode(srvMemData.getServiceSLCode());
                                srvMemData.setServiceDTCode(serviceDate);
                                srvMemData.setServiceStatusCode("O");
                                srvMemData.setServiceCenterCode(idSrvCenter);
                                srvMemData.setFPDCode(idFdpCode);

                                srvMemData.setDistFlag(idDistributionType);


                                /**
                                 * get last Serviced Date
                                 */
                                String lastServicedDate = sqlH.getLastServiceDate(srvMemData);

                                long dayDifference = 0;
                                SimpleDateFormat myFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
                                SQLServerSyntaxGenerator sqlServerSyntax = new SQLServerSyntaxGenerator();

                                sqlServerSyntax.setAdmCountryCode(srvMemData.getC_code());
                                sqlServerSyntax.setAdmDonorCode(srvMemData.getDonor_code());
                                sqlServerSyntax.setAdmAwardCode(srvMemData.getAward_code());
                                sqlServerSyntax.setLayR1ListCode(srvMemData.getDistrictCode());
                                sqlServerSyntax.setLayR2ListCode(srvMemData.getUpazillaCode());
                                sqlServerSyntax.setLayR3ListCode(srvMemData.getUnitCode());
                                sqlServerSyntax.setLayR4ListCode(srvMemData.getVillageCode());
                                sqlServerSyntax.setHHID(srvMemData.getHHID());
                                sqlServerSyntax.setMemID(srvMemData.getMemberId());
                                sqlServerSyntax.setProgCode(srvMemData.getProgram_code());
                                sqlServerSyntax.setSrvCode(srvMemData.getService_code());
                                sqlServerSyntax.setOpCode(srvMemData.getOpCode());
                                sqlServerSyntax.setOpMonthCode(srvMemData.getOpMontheCode());
                                sqlServerSyntax.setSrvSL(srvMemData.getServiceSLCode());
                                sqlServerSyntax.setSrvCenterCode(srvMemData.getServiceCenterCode());
                                sqlServerSyntax.setSrvDT(srvMemData.getServiceDTCode());
                                sqlServerSyntax.setSrvStatus(srvMemData.getServiceStatusCode());
                                sqlServerSyntax.setFDPCode(idFdpCode);
                                sqlServerSyntax.setWD(srvMemData.getWorkingDay());
                                sqlServerSyntax.setEntryBy(EntryBy);
                                sqlServerSyntax.setEntryDate(EntryDate);
                                sqlServerSyntax.setDistFlag(idDistributionType);
                                /**
                                 * if the man get service more than one time
                                 */
                                if (!lastServicedDate.equals("")) {
                                    try {
                                        Date date1 = myFormat.parse(serviceDate);
                                        Date date2 = myFormat.parse(lastServicedDate);
                                        dayDifference = date2.getTime() - date1.getTime();

                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    /**
                                     * if the last serviced Date & present Service date are not Same
                                     * than the data will be inserted
                                     * A man cannot get 2 service in the same day
                                     */
                                    if (dayDifference != 0) {

                                        /** check the data exit for Service                        */
                                        if (sqlH.isMemberExitsSrvTable(srvMemData)) {

                                            sqlH.updateMemberIntoServiceTable(srvMemData, EntryBy, EntryDate); /** update for local device */

                                            sqlH.insertIntoUploadTable(sqlServerSyntax.updateInToSrvTable()); /** update Syntax for upload in Sync process */
                                        } else {

                                            sqlH.addMemberIntoServiceTable(srvMemData, EntryBy, EntryDate);     /** insert for local device */

                                            sqlH.insertIntoUploadTable(sqlServerSyntax.insertInToSrvTable());  /** insert for upload in Sync process */
                                        }


                                        /**                                         * min Srv Date                                         */
                                        saveServiceMinumDate(srvMemData, serviceDate, sqlServerSyntax, sqlH);

                                        /**                                         * max date                                         */
                                        saveServiceMaxDate(srvMemData, serviceDate, sqlServerSyntax, sqlH);


                                        /**  if it is none food than save automatically Service Extended table fgf*/


                                        /**                                         * none food flag                                         */
                                        saveNoneFoodProgram(srvMemData, sqlServerSyntax, EntryBy, EntryDate);
                                    } else {
                                        /***
                                         * try to edit section
                                         */

                                        sqlH.updateMemberIntoServiceTable(srvMemData, EntryBy, EntryDate);  /** update for local device */


                                        sqlH.insertIntoUploadTable(sqlServerSyntax.updateInToSrvTable()); /** update Syntax for upload in Sync process */
                                    }
                                } /** if the man get service for first time */
                                else {
                                    /** check the data exit for Service                                     *                                          */
                                    if (sqlH.isMemberExitsSrvTable(srvMemData)) {

                                        sqlH.updateMemberIntoServiceTable(srvMemData, EntryBy, EntryDate);  /** update for local device */


                                        sqlH.insertIntoUploadTable(sqlServerSyntax.updateInToSrvTable());   /** update Syntax for upload in Sync process */
                                    } else {

                                        sqlH.addMemberIntoServiceTable(srvMemData, EntryBy, EntryDate);   /** insert for local device */

                                        sqlH.insertIntoUploadTable(sqlServerSyntax.insertInToSrvTable());   /** insert for upload in Sync process */
                                    }
                                    /**
                                     * get SrvMinDate
                                     */
                                    saveServiceMinumDate(srvMemData, serviceDate, sqlServerSyntax, sqlH);

                                    /**
                                     * get  max date
                                     */
                                    saveServiceMaxDate(srvMemData, serviceDate, sqlServerSyntax, sqlH);


                                    /**
                                     * none food flag
                                     */
                                    saveNoneFoodProgram(srvMemData, sqlServerSyntax, EntryBy, EntryDate);


                                }                                                                   // end of the else


                            }// end of for

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        idMemberSearch = "";
                        Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();

                        LoadingList loadList = new LoadingList(idCountry, idDonor, idAward, idProgram, idService, idMemberSearch, idOpMonthCode, strOpMonthLabel, idOpMonthCode, idSrvCenter, serviceDate, idGroup);
                        loadList.execute();
                        // for test putpose don't delete below code
                        //    loadServiceListView(idCountry, idDonor, idAward, idProgram, idService, idMemberSearch, idOpMonthCode, strOpMonthLabel, idOpMonthCode, serviceDate, idSrvCenter, idGroup);
                    }

                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

    }

    private void defaulftWDIntial() {
        this.wd = sqlH.get_ProgSrvDefaultDays(idCountry, idDonor, idAward, idProgram, idService, idDistributionType);
    }


    private boolean checkOtherParameterIsGiven(String srvName, String progName, String cCode, String donorCode, String awardCode, String progCode) {

        boolean flag = true;


        switch (srvName) {
            case C1:
            case C2:
            case C3:
            case FFA:
                switch (progName) {
                    case CFWS:
                    case CFWU:
                    case DRR:
                    case UCT:

                        flag = lay_MemberDetails.getVisibility() == View.VISIBLE;// lay_MemberDetails.getVisibility()==View.VISIBLE ? true : false;
                        this.wd = edt_MemberDetails_working_days.getText().toString();
                        flag = (!this.wd.equals("0") && !wd.isEmpty() && this.wd != null);

                        break;
                }
                break;
            case MG:
                flag = lay_MemberDetails.getVisibility() == View.VISIBLE;  //divMemberDetails.Visible ? true : false;
                break;
            case IG:
                flag = lay_MemberDetails.getVisibility() == View.VISIBLE;
                break;
        }
        if (idDistributionType.equals("VoFlag") && !sqlH.checkAdmCountryProgramsVoucherFlag(cCode, donorCode, awardCode, progCode)) {
            flag = lay_MemberDetails.getVisibility() == View.VISIBLE;
        }
        if (!flag) {
            hidMemberDetailsLayer();
        }
        return flag;
    }

    private boolean checkMultipleCheckedOnGrid(String cCode, String donorCode, String awardCode, String progCode) {


        int count = 0;
        boolean flag = true;
        if (sqlH.checkAdmCountryProgramsVoucherFlag(cCode, donorCode, awardCode, progCode)) {
            /***
             * for list view
             */
            for (int i = 0; i < adapter.getCount(); i++) {

                /***
                 * check Itm Selector.
                 */
                if (mChecked.get(i)) {
                    count++;

                    Log.d("AAA", "pos: " + i + " mChecked.get(i)" + mChecked.get(i));
                }
                if (count < 2) {
                    Log.d("AAA", "false: ");
                    flag = false;
                    break;
                }
            }

        } else {
            /***
             *  todo: for  grdExtendedSrvMemberList think about it later
             *  do not delete
             */

            for (int i = 0; i < adapter.getCount(); i++) {

                /***
                 * check Itm Selector.
                 */
                if (mChecked.get(i)) {
                    count++;

                    Log.d("AAA", "pos: " + i + " mChecked.get(i)" + mChecked.get(i) + "count:");
                }
                if (count < 2) {
                    Log.d("AAA", "false: ");
                    flag = false;
                    break;
                }
            }

        }

        /***
         *  details page related
         */
    /*    if (!flag)
        {
            HidMemDetailsGrid();
        }*/
        return flag;
//        return false;
    }

    private void saveNoneFoodProgram(ServiceDataModel srv_memData, SQLServerSyntaxGenerator syntax, String entryBy, String entryDate) {

        if (sqlH.checkAdmCountryProgramsNoneFoodFlag(idCountry, idDonor, idAward, idProgram, idService)) {

            Log.d("NIr", "In none food flag");
            List<VouItemServiceExtDataModel> listVou = sqlH.getDefaultVoucherItemRespectToProgram(idCountry, idDonor, idAward, idProgram, idService);

            for (VouItemServiceExtDataModel data : listVou) {
                // add contacts data in arrayList


                String voRefNo = "";
                Log.d("NIr", "data.getVoItmSpec(" + data.getVoItmSpec());
                sqlH.addServiceExtendedTable(srv_memData.getC_code(), srv_memData.getDistrictCode(), srv_memData.getUpazillaCode(), srv_memData.getUnitCode(),
                        srv_memData.getVillageCode(), srv_memData.getHHID(), srv_memData.getMemberId(), srv_memData.getDonor_code(), srv_memData.getAward_code(),
                        srv_memData.getProgram_code(), srv_memData.getService_code(), srv_memData.getOpCode(), srv_memData.getOpMontheCode(),
                        data.getVoItmSpec() /*vOItmSpec*/, data.getVoItmUnit()/* Unite Code */, voRefNo, data.getVoItemCost(), idDistributionType, entryBy, entryDate, "0");
                /** set the variable than insert  upload Table*/


                syntax.setvOItmUnit(data.getVoItmUnit());
                syntax.setvOItmSpec(data.getVoItmSpec());
                syntax.setvOItmCost(data.getVoItemCost());

                sqlH.insertIntoUploadTable(syntax.insertIntoSrvExtendedTable());


            }

        }
    }

    public static void saveServiceMinumDate(ServiceDataModel data, String serviceDate, SQLServerSyntaxGenerator sqlServerSyntax, SQLiteHandler sqlH) {
        String srvMinimumDate = sqlH.get_MemberMinSrvDate(data.getC_code(), data.getDonor_code(), data.getAward_code(), data.getDistrictCode(), data.getUpazillaCode(), data.getUnitCode(),
                data.getVillageCode(), data.getHHID(), data.getMemberId(), data.getProgram_code(), data.getService_code());
        if (srvMinimumDate.length() > 0) {

            SimpleDateFormat myFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
            Date date = null;
            Date mini = null;
            try {
                date = myFormat.parse(serviceDate);
                mini = myFormat.parse(srvMinimumDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (mini != null && date != null) {
                if (mini.getTime() > date.getTime()) {
                    sqlH.updateSrvMinDate(data.getC_code(), data.getDonor_code(), data.getAward_code(), data.getDistrictCode(), data.getUpazillaCode(), data.getUnitCode(),
                            data.getVillageCode(), data.getHHID(), data.getMemberId(), data.getProgram_code(), data.getService_code(), serviceDate);
                    sqlH.insertIntoUploadTable(sqlServerSyntax.update_SrvMinDate());
                }
            }

        } else {
            sqlH.updateSrvMinDate(data.getC_code(), data.getDonor_code(), data.getAward_code(), data.getDistrictCode(), data.getUpazillaCode(), data.getUnitCode(),
                    data.getVillageCode(), data.getHHID(), data.getMemberId(), data.getProgram_code(), data.getService_code(), serviceDate);
            sqlH.insertIntoUploadTable(sqlServerSyntax.update_SrvMinDate());
        }
    }


    public static void saveServiceMaxDate(ServiceDataModel data, String serviceDate, SQLServerSyntaxGenerator sqlServerSyntax, SQLiteHandler sqlH) {
        String srvMaximumDate = sqlH.get_MemberMaxSrvDate(data.getC_code(), data.getDonor_code(), data.getAward_code(), data.getDistrictCode(), data.getUpazillaCode(), data.getUnitCode(),
                data.getVillageCode(), data.getHHID(), data.getMemberId(), data.getProgram_code(), data.getService_code());
        if (srvMaximumDate.length() > 0) {

            SimpleDateFormat myFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
            Date date = null;
            Date max = null;
            try {
                date = myFormat.parse(serviceDate);
                max = myFormat.parse(srvMaximumDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (max != null && date != null) {
                if (max.getTime() < date.getTime()) {
                    sqlH.updateSrvMaxDate(data.getC_code(), data.getDonor_code(), data.getAward_code(), data.getDistrictCode(), data.getUpazillaCode(), data.getUnitCode(),
                            data.getVillageCode(), data.getHHID(), data.getMemberId(), data.getProgram_code(), data.getService_code(), serviceDate);
                    sqlH.insertIntoUploadTable(sqlServerSyntax.update_SrvMaxDate());
                }
            }

        } else {
            sqlH.updateSrvMinDate(data.getC_code(), data.getDonor_code(), data.getAward_code(), data.getDistrictCode(), data.getUpazillaCode(), data.getUnitCode(),
                    data.getVillageCode(), data.getHHID(), data.getMemberId(), data.getProgram_code(), data.getService_code(), serviceDate);
            sqlH.insertIntoUploadTable(sqlServerSyntax.update_SrvMinDate());
        }
    }


    /**
     * This method Convert SlNo from String to integer Than Increments by 1
     * again convert into String to save
     *
     * @param sl Service Serial No
     * @return next Service Serial No
     */

    private String padding(String sl) {

        int tem = Integer.parseInt(sl);
//        if(tem>1)
        tem = tem + 1;

        String convertedValue = String.valueOf(tem);
        if (convertedValue.length() < 2)
            convertedValue = "0" + convertedValue;
        //  Log.d(TAG,"Service SL :"+convertedValue);
        return convertedValue;

    }

    public void setDate() {
        new DatePickerDialog(ServiceActivity.this, datepickerD, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener datepickerD = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDate();
        }
    };

    public void updateDate() {
        tv_srvDate.setText(format.format(calendar.getTime()));
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
    private void loadGroup(final String cCode, final String donorCode, final String awardCode
            , final String progCode, final String grpCateCode, final String strSrvDate, final String srvCenterCode) {

        int position = 0;


        String criteria = "SELECT  " + SQLiteHandler.LAY_R1_CODE_COL + "||"
                + SQLiteHandler.GRP_LAY_R2_LIST_CODE_COL + "||" +
                SQLiteHandler.GRP_LAY_R3_LIST_CODE_COL + "||"

                + GROUP_CODE_COL

                + " , " + GROUP_NAME_COL + " FROM " + SQLiteHandler.COMMUNITY_GROUP_TABLE + " WHERE " + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.ADM_PROG_CODE_COL + " = '" + progCode + "' "
                + " AND " + SQLiteHandler.GROUP_CAT_CODE_COL + " = '" + grpCateCode + "' "
                + " AND " + SQLiteHandler.SERVICE_CENTER_CODE_COL + " = '" + srvCenterCode + "' "
                + " AND " + SQLiteHandler.GRP_LAY_R3_LIST_CODE_COL + " != '-' "
                + " GROUP BY " + SQLiteHandler.GROUP_NAME_COL + " ";


        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, null, false);
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
        }


        spGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strGroup = ((SpinnerHelper) spGroup.getSelectedItem()).getValue();
                String groupCodeWithlayer = ((SpinnerHelper) spGroup.getSelectedItem()).getId();


// for test purpose
                //  loadServiceListView(idCountry, idDonor, idAward, idProgram, idService, idMemberSearch, idOpMonthCode, strOpMonthLabel, idOpMonthCode, strSrvDate, idSrvCenter, idGroup);
                if (groupCodeWithlayer.length() > 2) {

                    idGrpLayR1Code = groupCodeWithlayer.substring(0, 2);
                    idGrpLayR2Code = groupCodeWithlayer.substring(2, 4);
                    idGrpLayR3Code = groupCodeWithlayer.substring(4, 6);
                    idGroup = groupCodeWithlayer.substring(6);


                    /**   working*/
//                    LoadingList loadlist = new LoadingList(idCountry, idDonor, idAward, idProgram, idService, idMemberSearch, idOpMonthCode, strOpMonthLabel, idOpMonthCode, strSrvDate, idSrvCenter, idGroup);
//                    loadlist.execute();

                    //  for test query
                    testLoadServiceListView(idCountry, idDonor, idAward, idProgram, idService,
                            idMemberSearch, idOpMonthCode, strOpMonthLabel, idOpMonthCode,
                            strSrvDate, idSrvCenter, idGroup);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    /**
     * LOAD :: Village :loadLayR4List
     */
    private void loadLayR4List(String cCode) {

        int position = 0;
        List<SpinnerHelper> listVillage = sqlH.getListAndID(SQLiteHandler.GEO_LAY_R4_LIST_TABLE, SQLiteQuery.layR4ListServicePage_sql(), cCode, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listVillage);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spLayR4List.setAdapter(dataAdapter);

        if (idLayR4List != null) {
            for (int i = 0; i < spLayR4List.getCount(); i++) {
                String village = spLayR4List.getItemAtPosition(i).toString();
                if (village.equals(strVLayR4List)) {
                    position = i;
                }
            }
            spLayR4List.setSelection(position);
        }


        spLayR4List.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strVLayR4List = ((SpinnerHelper) spLayR4List.getSelectedItem()).getValue();
                idLayR4List = ((SpinnerHelper) spLayR4List.getSelectedItem()).getId();
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
    private void loadGroupCategory(final String cCode, String donorCode, String awardCode,
                                   final String progCode, final String strSrvDate, final String srvCenterCode) {


        SpinnerLoader.loadGroupCatLoader(mContext, sqlH, spGroupCategories, cCode, donorCode, awardCode, progCode, idGroupCat, strGroupCat);


        spGroupCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strGroupCat = ((SpinnerHelper) spGroupCategories.getSelectedItem()).getValue();
                idGroupCat = ((SpinnerHelper) spGroupCategories.getSelectedItem()).getId();

                if (idGroupCat.length() > 2)
                    loadGroup(idCountry, idDonor, idAward, progCode, idGroupCat, strSrvDate, srvCenterCode);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    /**
     * LOAD :: Award
     *
     * @param cCode Country Code
     */
    private void loadAward(final String cCode, final String SrvCenterCode, final String fdpCode, final String opMonthCode) {

        SpinnerLoader.loadAwardLoader(mContext, sqlH, spAward, cCode, idAward, strAward);


        spAward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strAward = ((SpinnerHelper) spAward.getSelectedItem()).getValue();
                String awardID = ((SpinnerHelper) spAward.getSelectedItem()).getId();

                if (awardID.length() > 2) {
                    idAward = awardID.substring(2);
                    idDonor = awardID.substring(0, 2);
                    idCountry = cCode;

                    loadDistributionType(cCode, idDonor, idAward, SrvCenterCode, fdpCode, opMonthCode);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * LOAD :: Service Center
     */
    private void loadServiceCenter(final String cCode) {

        SpinnerLoader.loadServiceCenterLoader(mContext, sqlH, spServiceCenter, idSrvCenter, strServiceCenter);

        spServiceCenter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strServiceCenter = ((SpinnerHelper) spServiceCenter.getSelectedItem()).getValue();
                String fdpWithSrvCenterCode = ((SpinnerHelper) spServiceCenter.getSelectedItem()).getId();

                if (fdpWithSrvCenterCode.length() > 2) {
                    idFdpCode = fdpWithSrvCenterCode.substring(0, 3);
                    idSrvCenter = fdpWithSrvCenterCode.substring(3);
                    loadServiceMonth(cCode, idSrvCenter, idFdpCode);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    /**
     * LOAD :: load Service Month
     */
    private void loadServiceMonth(final String cCode, final String SrvCenterCode, final String fdpCode) {

        int position = 0;
        String criteria;

        criteria = SQLiteQuery.getServiceMonths_WHERE_Service_Open_Condition(cCode);
        List<SpinnerHelper> listMonth = sqlH.getListAndID(SQLiteHandler.OP_MONTH_TABLE, criteria, null, false);
        listMonth.remove(0);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listMonth);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spServiceMonth.setAdapter(dataAdapter);


        if (idServiceMonth != null) {

            for (int i = 0; i < spServiceMonth.getCount(); i++) {
                String month = spServiceMonth.getItemAtPosition(i).toString();
                if (month.equals(strSrvMonth)) {
                    position = i;
                    spServiceMonth.setSelection(position);


                }
            }

        }


        spServiceMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strSrvMonth = ((SpinnerHelper) spServiceMonth.getSelectedItem()).getValue();
                idServiceMonth = ((SpinnerHelper) spServiceMonth.getSelectedItem()).getId();
                if (idServiceMonth.length() > 2) {

                    /** I don't want change the query Code that whys use sub string
                     *                   idCountry = idServiceMonth.substring(0, 4);
                     *                   donorId = idServiceMonth.substring(4, 6);
                     *                    awardId = idServiceMonth.substring(6, 8);
                     * */
                    idOpMonthCode = idServiceMonth.substring(8);

                    loadAward(cCode, SrvCenterCode, fdpCode, idServiceMonth);


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    /**
     * ** LOAD:: DistributionType
     */
    private void loadDistributionType(final String cCode, final String donorCode, final String awardCode, final String srvCenterCode, final String fdpCode, final String srvMonthCode) {
        int position = 0;

        ArrayAdapter<CharSequence> adptMartial = ArrayAdapter.createFromResource(
                this, R.array.arrDistributionType, R.layout.spinner_layout);

        adptMartial.setDropDownViewResource(R.layout.spinner_layout);
        spDistributionType.setAdapter(adptMartial);


        if (idDistributionType != null) {
            for (int i = 0; i < spDistributionType.getCount(); i++) {
                String type = spDistributionType.getItemAtPosition(i).toString();
                if (type.equals(strDistType)) {
                    position = i;
                }
            }
            spDistributionType.setSelection(position);
        }


        /*** Experiments*/

        spDistributionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String type = parent.getItemAtPosition(position).toString();
                String foodFlagQuary = "";

                switch (type) {
                    case "None":
                        strDistType = "None";
                        idDistributionType = DistributionActivity.NONE;
                        foodFlagQuary = " AND " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.FOOD_FLAG + "= '0' "
                                + " AND " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.NON_FOOD_FLAG + "= '0' "
                                + " AND " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.CASH_FLAG + "= '0' "
                                + " AND " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.VOUCHER_FLAG + "= '0' ";
                        break;
                    case "Food":
                        strDistType = "Food";
                        idDistributionType = DistributionActivity.FOOD_TYPE;
                        foodFlagQuary = " AND " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.FOOD_FLAG + "= '1' ";
                        break;
                    case "Non Food":

                        strDistType = "Non Food";
                        idDistributionType = DistributionActivity.NON_FOOD_TYPE;
                        foodFlagQuary = " AND " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.NON_FOOD_FLAG + "= '1' ";
                        break;

                    case "Cash":
                        strDistType = "Cash";
                        idDistributionType = DistributionActivity.CASH_TYPE;
                        foodFlagQuary = " AND " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.CASH_FLAG + "= '1' ";
                        break;
                    case "Voucher":
                        strDistType = "Voucher";
                        idDistributionType = DistributionActivity.VOUCHER_TYPE;
                        foodFlagQuary = " AND " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.VOUCHER_FLAG + "= '1' ";
                        break;
                }

                loadServiceRecodeCriteria(cCode, donorCode, awardCode, srvCenterCode, idFdpCode, idOpMonthCode, foodFlagQuary);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean checkDateInValidRang(String cCode, String idOpMonthCode, String date) {

        boolean validDate = true;
        HashMap<String, String> serviceDateRange;


        /**         * if the the voucher program  than service date & service center code must needed*/

        if (date.equals("") || date.equals("yyyy-mm-dd") || date.equals("Date")) {
            validDate = false;
            erroDialog.showErrorDialog(mContext, "Please select a Date ");

        } else {
            try {
                serviceDateRange = sqlH.getDateRangeForService(cCode, idOpMonthCode);
                String start_date = serviceDateRange.get("sdate");
                String end_date = serviceDateRange.get("edate");


                idOpCode = "2";                                                                     // the op cod eof the service is 2
                /**
                 *  KENO  AMI  strOpMonthLabel BOSALAM ETAR KARON BER KORTE HOBE
                 */
                strOpMonthLabel = serviceDateRange.get("opMonthLable");


                if (date.length() > 0 && start_date != null && end_date != null) {
                    if (!getValidDateRange(date, start_date, end_date, false)) {
                        validDate = false;
                        erroDialog.showErrorDialog(mContext, "Service date is not within the valid range. Save attempt denied");

                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return validDate;
    }

    /***
     * This Method set group Code
     */
    private void loadGroupsOrLayR4List(String cCode, String donorCode, String awardCode, String criteriaCode, String srvCenterCode) {
        idCountry = cCode;
        idAward = awardCode;
        idDonor = donorCode;
        idProgram = criteriaCode.substring(0, 3);
        idService = criteriaCode.substring(3);
        idMemberSearch = "";

        String serviceDate = tv_srvDate.getText().toString();

        if (checkDateInValidRang(idCountry, idOpMonthCode, serviceDate)) {

            /**   *  get program Name  Service name                   */
            String srvName = sqlH.getServiceShortName(idProgram, idService);
            String progName = sqlH.getProgramShortName(awardCode, donorCode, idProgram);


            switch (srvName) {
                case C1:
                case C2:
                case C3:
                case IG:
                case MG:
                case FFA:
                case PW:
                case LM:
                case CU2:
                case CA2:
                case HHR:
                    switch (progName) {
                        case CFWS:
                        case CFWU:
                        case CFWE:
                        case DRR:
                        case AGR:
                        case MCHN:
                            showNHideGroupNCat(View.VISIBLE);
                            showNHideLayRList(View.GONE);
                            loadGroupCategory(idCountry, idDonor, idAward, idProgram, serviceDate, srvCenterCode);
                            break;
                        default:
                            showNHideGroupNCat(View.GONE);
                            showNHideLayRList(View.VISIBLE);
                            loadLayR4List(idCountry);
                            //  get village list  getList4(_CountryCode, Class.SessionManager.LoginUserId);
                            break;

                    }
                    break;
                default:
                    //  get village list
                    showNHideGroupNCat(View.GONE);
                    showNHideLayRList(View.VISIBLE);
                    loadLayR4List(idCountry);
                    break;
            }


        }
    }


    /**
     * LOAD :: Criteria program name and service name
     * @param cCode             Country Code
     * @param donorCode         Donor Code
     * @param awardCode         award Code
     * @param srvCenterCode     Service Center Code
     * @param fdpCode           food distribution point
     * @param srvMonthCode      service month Code
     * @param foodFlagTypeQuery sqlite query An "and condition" will  dynamically added  to load criteria on spinner .Assume program MCHN has
     *                          four service PW,LM,CA2,CU2. CA2 & CU2 have Food type service but PW and LM
     *                          have Cash type service .if user select food type then pw and lm will not appeared.
     *                          criteria Code (programCode + service Code)
     */
    private void loadServiceRecodeCriteria(final String cCode, final String donorCode, final String awardCode, final String srvCenterCode, final String fdpCode, final String srvMonthCode, final String foodFlagTypeQuery) {

        String criteria = SQLiteQuery.loadServiceRecodeCriteria(cCode, donorCode, awardCode, foodFlagTypeQuery);
        SpinnerLoader.loadCriteriaLoader(mContext, sqlH, spCriteria, idCriteria, strCriteria, criteria);

        spCriteria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                 @Override
                                                 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                     strCriteria = ((SpinnerHelper) spCriteria.getSelectedItem()).getValue();
                                                     idCriteria = ((SpinnerHelper) spCriteria.getSelectedItem()).getId();
                                                     if (idCriteria.length() > 2)
                                                         loadGroupsOrLayR4List(cCode, donorCode, awardCode, idCriteria, srvCenterCode);


                                                 }

                                                 @Override
                                                 public void onNothingSelected(AdapterView<?> parent) {

                                                 }
                                             }

        );

    } // end Load Spinner

    /**
     * @param cCode         country Code
     * @param donorCode     Donor Code
     * @param awardCode     award Code
     * @param prgCode       Program Code
     * @param srvCode       Service Code
     * @param memSearchId   member Id for Search Version
     * @param opMonthLable  Service Operation Month Code
     * @param opCode        operation code
     * @param opMCode       operation month code
     * @param srvDate       service date
     * @param srvCenterCode Service Center Code
     * @param grpCode       GroupCod e
     *                      <p> this method </p>
     *                      todo:09-08-2016 implements graduatio date
     */


    public void loadServiceListView(final String cCode, String donorCode, String awardCode, String prgCode, String srvCode, String memSearchId, String opMonthLable, String opCode, String opMCode, String srvDate, String srvCenterCode, String grpCode) {

        List<ServiceDataModel> srvMemberList = null;

        /**
         *  get program Name  Service name
         */
        String srvName = sqlH.getServiceShortName(prgCode, srvCode);
        String progName = sqlH.getProgramShortName(awardCode, donorCode, prgCode);


        switch (srvName) {
            case C1:
            case C2:
            case C3:
            case FFA:
                switch (progName) {
                    case CFWS:
                    case CFWU:
                    case DRR:
                        tv_srvTitleCount.setText(R.string.wd);
                        srvMemberList = sqlH.getFFAMemberListForService(cCode, donorCode, awardCode, prgCode, srvCode, memSearchId, opCode, opMCode, grpCode, idDistributionType);
                        break;

                }
                break;
            default:
                // use variable to like operation
                if (idGrpLayR1Code != null && idGrpLayR1Code.length() > 0 && idGrpLayR2Code != null && idGrpLayR2Code.length() > 0 && idGrpLayR3Code != null && idGrpLayR3Code.length() > 0)
                    srvMemberList = sqlH.getRptMemberServiceList(cCode, donorCode, awardCode, prgCode, srvCode, memSearchId, opCode, opMCode, grpCode, idDistributionType, idGrpLayR1Code, idGrpLayR2Code, idGrpLayR3Code);

                break;
        }

        if (srvMemberList != null) {
            Log.d(TAG, "srvMemberList size : " + srvMemberList.size());
            if (srvMemberList.size() != 0) {
                serviceArray.clear();
                for (ServiceDataModel data : srvMemberList) {

                    serviceArray.add(data);
                }
//                Log.d(TAG, "serviceArray size : " + serviceArray.size() + "");
                adapter = new ServiceDataListAdapter(this, serviceArray, strAward, idCriteria, strCriteria, opMonthLable, opCode, opMCode, srvDate, srvCenterCode, progName, srvName);

            } else {
                // this statements clear the list view
                serviceArray.clear();
                adapter = new ServiceDataListAdapter(this, serviceArray, strAward, idCriteria, strCriteria, opMonthLable, opCode, opMCode, srvDate, srvCenterCode, progName, srvName);

            }
        } else {
            serviceArray.clear();
            adapter = new ServiceDataListAdapter(this, serviceArray, strAward, idCriteria, strCriteria, opMonthLable, opCode, opMCode, srvDate, srvCenterCode, progName, srvName);

        }


    }



    public void testLoadServiceListView(final String cCode, String donorCode, String awardCode, String prgCode, String srvCode, String memSearchId, String opMonthLable, String opCode, String opMCode, String srvDate, String srvCenterCode, String grpCode) {

        List<ServiceDataModel> srvMemberList = null;

        /**
         *  get program Name  Service name
         */
        String srvName = sqlH.getServiceShortName(prgCode, srvCode);
        String progName = sqlH.getProgramShortName(awardCode, donorCode, prgCode);


        switch (srvName) {
            case C1:
            case C2:
            case C3:
            case FFA:
                switch (progName) {
                    case CFWS:
                    case CFWU:
                    case DRR:
                        tv_srvTitleCount.setText(R.string.wd);
                        srvMemberList = sqlH.getFFAMemberListForService(cCode, donorCode, awardCode, prgCode, srvCode, memSearchId, opCode, opMCode, grpCode, idDistributionType);
                        break;

                }
                break;
            default:
                // use variable to like operation
                if (idGrpLayR1Code != null && idGrpLayR1Code.length() > 0 && idGrpLayR2Code != null && idGrpLayR2Code.length() > 0 && idGrpLayR3Code != null && idGrpLayR3Code.length() > 0)
                    srvMemberList = sqlH.getRptMemberServiceList(cCode, donorCode, awardCode, prgCode, srvCode, memSearchId, opCode, opMCode, grpCode, idDistributionType, idGrpLayR1Code, idGrpLayR2Code, idGrpLayR3Code);

                break;
        }

        if (srvMemberList != null) {
            Log.d(TAG, "srvMemberList size : " + srvMemberList.size());
            if (srvMemberList.size() != 0) {
                serviceArray.clear();
                for (ServiceDataModel data : srvMemberList) {

                    serviceArray.add(data);
                }
//                Log.d(TAG, "serviceArray size : " + serviceArray.size() + "");
                adapter = new ServiceDataListAdapter(this, serviceArray, strAward, idCriteria, strCriteria, opMonthLable, opCode, opMCode, srvDate, srvCenterCode, progName, srvName);

            } else {
                // this statements clear the list view
                serviceArray.clear();
                adapter = new ServiceDataListAdapter(this, serviceArray, strAward, idCriteria, strCriteria, opMonthLable, opCode, opMCode, srvDate, srvCenterCode, progName, srvName);

            }
        } else {
            serviceArray.clear();
            adapter = new ServiceDataListAdapter(this, serviceArray, strAward, idCriteria, strCriteria, opMonthLable, opCode, opMCode, srvDate, srvCenterCode, progName, srvName);

        }

/**
 *  set adpater
 */

       if (!adapter.isEmpty()) {
            adapter.notifyDataSetChanged();
            mListView.setAdapter(adapter);
           /**
         * Notify the use no data available
         */
            if (adapter.getCount() == 0) {
                erroDialog.showInfromDialog(mContext, "No Data Found", "No Data found");
            }


        } else {
            erroDialog.showInfromDialog(mContext, "No Data Found", "No Data found");
        }


    }

    /**
     * This is a Back Ground Thread Class
     */
    private class LoadingList extends AsyncTask<Void, Integer, String> {
        private String countryCode, donorCode, awardCode, programCode,
                serviceCode, searchID, opMonthLabel, opCode, opMonthCode, srvCenterCode, tserviceDate, groupCode;

        public LoadingList(String countryCode, String donorCode, String awardCode,
                           String programCode, String serviceCode, String searchID, String opMonthLabel,
                           String opCode, String opMonthCode, String srvCenterCode, String tserviceDate, String groupCode) {
            this.countryCode = countryCode;
            this.donorCode = donorCode;
            this.awardCode = awardCode;
            this.programCode = programCode;
            this.serviceCode = serviceCode;
            this.searchID = searchID;
            this.opMonthLabel = opMonthLabel;
            this.opCode = opCode;
            this.opMonthCode = opMonthCode;
            this.srvCenterCode = srvCenterCode;
            this.tserviceDate = tserviceDate;
            this.groupCode = groupCode;


        }

        @Override
        protected String doInBackground(Void... params) {
            try {


                loadServiceListView(countryCode, donorCode, awardCode, programCode, serviceCode, searchID, idOpMonthCode, opMonthLabel, opMonthCode
                        , tserviceDate, srvCenterCode, groupCode);

            } catch (Exception e) {
                pDialog.dismiss();
                return "UNKNOWN";
            }
            return "sucess";

        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            startProgressBar(mContext.getResources().getString(R.string.loading_msg));

        }


        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            pDialog.dismiss();

            // if the selected program is  for voucher tha this view will gone
            if (sqlH.checkAdmCountryProgramsVoucherFlag(idCountry, idDonor, idAward, idProgram)) {
                btnSave.setVisibility(View.GONE);
            } else {
                btnSave.setVisibility(View.VISIBLE);
            }

            //   mListView.setFocusableInTouchMode(true);
            if (adapter != null) {
                adapter.notifyDataSetChanged();
                mListView.setAdapter(adapter);
                /**
                 * Notify the use no data available
                 */
                if (adapter.getCount() == 0) {
                    erroDialog.showInfromDialog(mContext, "No Data Found", "No Data found");
                }


            } else {
                erroDialog.showInfromDialog(mContext, "No Data Found", "No Data found");
            }


        }
    }

    private void startProgressBar(String msg) {
        pDialog.setMessage(msg);
        pDialog.setCancelable(false);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.show();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * Here  is the sub class  Adapter class
     */

    public class ServiceDataListAdapter extends BaseAdapter {
        private Activity activity;
        /**
         * inflater Convert XmL into Java Object
         */

        private LayoutInflater inflater;
        ArrayList<ServiceDataModel> servicedData;// = new ArrayList<ServiceDataModel>();
        private SQLiteHandler sqlH = null;
        private final String TAG = "ServiceDataListAdapter";

        private String awardName;
        private String villageName; // todo:remove the Village  Name
        private String CriteriaName;
        private String criteriaId;
        private String opMonthStr;
        private HashMap<String, String> dateRange;

        private String opCode;
        private String opMonthCode;
        private String srvCenterCode;
        private String srvDate;
        /**
         * To Control the rows of List need the Program Short name
         */
        private String progShortName;
        private String srvShortName;

        /**
         * @param activity      The Parent Activity
         * @param servicedData  Service Date
         * @param awardName     Awaer Name
         * @param criteriaId    Criteria Id (Program Code+ Service Code )
         * @param CriteriaName  Criteria Name (Program Name+ Service Name )
         * @param opMonthStr    Service Operation Month String
         * @param opCode        operation Code
         * @param opMonthCode   Service Operation Mode
         * @param srvDate       Service Date
         * @param srvCenterCode Service Center Code
         */


        public ServiceDataListAdapter(Activity activity, ArrayList<ServiceDataModel> servicedData, String awardName, String criteriaId,
                                      String CriteriaName, String opMonthStr, String opCode, String opMonthCode, String srvDate, String srvCenterCode, String progShortName, String srvShortName) {
            this.activity = activity;
            this.servicedData = servicedData;
            sqlH = new SQLiteHandler(activity);
            dateRange = sqlH.getDateRangeForService(idCountry, idServiceMonth);
            this.awardName = awardName;
            this.CriteriaName = CriteriaName;
            this.criteriaId = criteriaId;
            this.opMonthStr = opMonthStr;
            this.opCode = opCode;
            this.opMonthCode = opMonthCode;
            this.srvDate = srvDate;
            this.srvCenterCode = srvCenterCode;
            this.progShortName = progShortName;
            this.srvShortName = srvShortName;


        }


        @Override
        public int getCount() {

            count = servicedData.size();
            return count;

        }

        /**
         * @param position the Index of the row in list
         * @return the Service Object
         */

        @Override
        public Object getItem(int position) {
            return servicedData.get(position);
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        viewHolder holder;

        /**
         * @param position    index
         * @param convertView dynamic row view
         * @param parent
         * @return the Custom View of row
         */

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            final ServiceDataModel personToBeServiced = servicedData.get(position);

            View row = convertView;


            if (inflater == null)
                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            if (convertView == null) {
                row = inflater.inflate(R.layout.list_row_service, null);
                holder = new viewHolder();
                holder.tv_newMemId = (TextView) row.findViewById(R.id.srv_row_newId);
                holder.tv_memName = (TextView) row.findViewById(R.id.srv_row_member_name);
                holder.tv_countORwd = (TextView) row.findViewById(R.id.srv_row_tv_criteriaView);
                holder.imgEdit = (ImageButton) row.findViewById(R.id.ibtn_edit_service_holder);
                holder.imgVoucher = (ImageButton) row.findViewById(R.id.ibtn_voucher_service_holder);
                holder.imgSrvSpecific = (ImageButton) row.findViewById(R.id.ibtn_service_spacific_holder);

                row.setTag(holder);

            } else {
                holder = (viewHolder) row.getTag();
            }


            String criteria = SQLiteQuery.getVillageNameWHERECondition(personToBeServiced.getC_code(), personToBeServiced.getDistrictCode(), personToBeServiced.getUpazillaCode(), personToBeServiced.getUnitCode(), personToBeServiced.getVillageCode());


            villageName = sqlH.getVillageName(criteria);
            personToBeServiced.setVillageName(villageName);
            personToBeServiced.setAwardName(awardName);
            personToBeServiced.setCriteriaName(CriteriaName);
            personToBeServiced.setOpMonthStr(opMonthStr);
            personToBeServiced.setServiceSLCode(padding(personToBeServiced.getGetSrvMemCount()));

            personToBeServiced.setOpCode("2"); // set opCode to service
            personToBeServiced.setOpMontheCode(opMonthCode);
            personToBeServiced.setCriteriaId(criteriaId);
            personToBeServiced.setFPDCode(idFdpCode);


            /* **************ADDING CONTENTS**************** */

            holder.tv_newMemId.setText(personToBeServiced.getNewID());  // Registration ID or Holding ID
            //  holder.mmID.setText(personToBeServiced.getMemberId());
            holder.tv_memName.setText(personToBeServiced.getHh_mm_name());
            switch (srvShortName) {
                case C1:
                case C2:
                case C3:
                case FFA:
                    switch (progShortName) {
                        case CFWS:
                        case "CFWU":
                        case DRR:
                            holder.tv_countORwd.setText(personToBeServiced.getWorkingDay());
                            break;

                    }
                    break;
                default:
                    int c = Integer.parseInt(personToBeServiced.getGetSrvMemCount());
                    holder.tv_countORwd.setText(String.valueOf(c));
                    break;
            }


            // check box reference is define here
            CheckBox cbId_holder = (CheckBox) row.findViewById(R.id.cb_srv_id_holde);
            // set checked change listener
            cbId_holder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    /**
                     *  Saving Checked Position
                     */
                    mChecked.put(position, isChecked);

                    /**
                     * Find if all the check boxes are true
                     */
                    if (isAllValuesChecked()) {

                                    /*
                                     * set HeaderCheck box to true
                                     */
                        checkBox_header.setChecked(isChecked);
                    }

                    /** get the object of sepecific row & set the value of the
                     * save the chekbox of that particular state
                     */
                    getServicedPerson((Integer) buttonView.getTag()).setCheckBox(isChecked);
                    /** if the selected program is  for voucher tha staff will not save the
                     *  benificary service normally by check box
                     *
                     */

                    /** old state*/
                    addDataToArrayList(getServicedPerson((Integer) buttonView.getTag()),
                            getServicedPerson((Integer) buttonView.getTag()).isCheckBox());


                }
            });
            // set the sate of particular positioned check box
            cbId_holder.setTag(position);
            // than set the checked sate

            /**
             * Set CheckBox "TRUE" or "FALSE" if mChecked == true
             */
            cbId_holder.setChecked((mChecked.get(position)));

            /**
             * necessary setups
             */
            personToBeServiced.setServiceCenterCode(srvCenterCode);

            personToBeServiced.setServiceDTCode(srvDate);
            personToBeServiced.setTemServiceDate(srvDate);
            personToBeServiced.setTemServiceCenterName(strServiceCenter);
            personToBeServiced.setServiceCenterCode(idSrvCenter);
            personToBeServiced.setTemServiceDate(tv_srvDate.getText().toString());
            personToBeServiced.setOpMonthStr(strOpMonthLabel);
            personToBeServiced.setTemIdServiceMonth(idOpMonthCode);
            personToBeServiced.setTemStrSrvMonth(strSrvMonth);
            personToBeServiced.setTemIdGroup(idGroup);
            personToBeServiced.setTemStrGroup(strGroup);
            personToBeServiced.setTemIdGroupCat(idGroupCat);
            personToBeServiced.setTemStrGroupCat(strGroupCat);
            personToBeServiced.setDistFlag(idDistributionType);

/**
 * This Button bring To Service Record Page
 */
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String srvName = sqlH.getServiceShortName(idProgram, idService);
                    String progName = sqlH.getProgramShortName(idAward, idDonor, idProgram);
                    switch (srvName) {

                        case FFA:
                        case C1:
                        case C2:
                        case C3:
                        case MG:
                        case IG:
                            switch (progName) {

                                case DRR:
                                case AGR:
                                case CFWS:
                                case CFWU:
                                    /**
                                     * if member details layer visible  then user may got to record details page
                                     */
                                    if (lay_MemberDetails.getVisibility() == View.VISIBLE)
                                        gotoServiceEditPage(personToBeServiced);

                                    showMemberDetailsLayer();

                                    tv_MemberDetails_HHID.setText(personToBeServiced.getHHID());
                                    tv_MemberDetails_MemID.setText(personToBeServiced.getMemberId());
                                    tv_MemberDetails_MemName.setText(personToBeServiced.getHh_mm_name());

                                    edt_MemberDetails_working_days.setText(personToBeServiced.getWorkingDay());

                                    if (!edt_MemberDetails_working_days.getText().toString().equals("0"))
                                        edt_MemberDetails_working_days.setFocusable(false);


                                    break;

                            }
                            break;
                        default:
                            gotoServiceEditPage(personToBeServiced);
                            break;
                    }


                }
            });


            // if the selected program is not for voucher tha this view will gone
            // todo
            if (!sqlH.checkAdmCountryProgramsVoucherFlag(idCountry, idDonor, idAward, idProgram)) {
                holder.imgVoucher.setVisibility(View.GONE);
            }

            if (!sqlH.checkCriteriaServiceSpecificFlag(idCountry, idDonor, idAward, idProgram, idService)) {
                holder.imgSrvSpecific.setVisibility(View.GONE);
            }

            holder.imgVoucher.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(activity, ServiceVoucherDetails.class);

                    // send the Service data object to the ServiceVoucherDetails
                    intent.putExtra(KEY.SERVICE_DATA_OBJECT_KEY, personToBeServiced);
                    activity.finish();
                    activity.startActivity(intent);
                }
            });


            holder.imgSrvSpecific.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent iSrvSpec = new Intent(activity, ServiceSpecification.class);


                    iSrvSpec.putExtra(KEY.SERVICE_DATA_OBJECT_KEY, personToBeServiced);

                    activity.finish();
                    activity.startActivity(iSrvSpec);
                }
            });


            if (position % 2 == 0) {
                row.setBackgroundColor(Color.WHITE);
                changeTextColor(activity.getResources().getColor(R.color.blue));
            } else {
                row.setBackgroundColor(activity.getResources().getColor(R.color.list_divider));
                changeTextColor(activity.getResources().getColor(R.color.black));
            }

            return row;
        }

        /**
         * this method let the user got to Service recorded Details page .
         * where user can see the when the selected member get service and can delete the false entry
         *
         * @param srvData service recorded data
         */
        private void gotoServiceEditPage(final ServiceDataModel srvData) {
            activity.finish();
            Intent intent = new Intent(activity, ServiceRecordDetails.class);
            srvData.setTemServiceCenterName(strServiceCenter);
            srvData.setServiceCenterCode(idSrvCenter);
            srvData.setTemServiceDate(tv_srvDate.getText().toString());
            srvData.setOpMonthStr(strOpMonthLabel);
            intent.putExtra(KEY.SERVICE_DATA_OBJECT_KEY, srvData);
            activity.startActivity(intent);
        }

        private void changeTextColor(int color) {

            holder.tv_newMemId.setTextColor(color);
            holder.tv_memName.setTextColor(color);
            holder.tv_countORwd.setTextColor(color);
        }


        ServiceDataModel getServicedPerson(int position) {
            return (ServiceDataModel) getItem(position);
        }


        protected boolean isArrayListNull() {
            return listOFWant2Save.isEmpty();


        }

        private ArrayList<ServiceDataModel> listOFWant2Save = new ArrayList<ServiceDataModel>();

        private void addDataToArrayList(ServiceDataModel s, boolean chackBoxStatus) {
            if (chackBoxStatus) {
                listOFWant2Save.add(s);
            } else {
                if (listOFWant2Save.contains(s)) {// first check the data is exits ing or not
                    listOFWant2Save.remove(s);
                }

            }

        }

        public ArrayList<ServiceDataModel> getArrayList() {
            return listOFWant2Save;
        }


        class viewHolder {
            TextView tv_newMemId;
            TextView tv_memName;
            /**
             * tv_countORwd will show the no of Service that Member Get And no of Working Days
             * depend upon Program
             */
            TextView tv_countORwd;
            ImageButton imgEdit;
            ImageButton imgVoucher;
            ImageButton imgSrvSpecific;

        }

        /*
         * Find if all values are checked.
         */
        protected boolean isAllValuesChecked() {

            for (int i = 0; i < count; i++) {
                if (!mChecked.get(i)) {
                    return false;
                }
            }

            return true;
        }


    }// end of  adapter

    /**
     * calling getWidth() and getHeight() too early:
     * When  the UI has not been sized and laid out on the screen yet..
     *
     * @param hasFocus the value will be true when UI is focus
     */

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        setUpSummaryButton();
        addIconHomeButton();
        setUpSaveButton();
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpSummaryButton() {
        btnSummary.setText("");
        Drawable summeryImage = getResources().getDrawable(R.drawable.summession_b);
        btnSummary.setCompoundDrawablesRelativeWithIntrinsicBounds(summeryImage, null, null, null);
        setPaddingButton(mContext, summeryImage, btnSummary);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addIconHomeButton() {
        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, btnHome);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpSaveButton() {
        btnSave.setText("");
        Drawable saveImage = getResources().getDrawable(R.drawable.save_b);
        btnSave.setCompoundDrawablesRelativeWithIntrinsicBounds(saveImage, null, null, null);
        setPaddingButton(mContext, saveImage, btnSave);
    }

    /**
     * this method will convert XML view into the Java View Object  .
     */

    private void viewReference() {
        spAward = (Spinner) findViewById(R.id.sp_awardList);
        spCriteria = (Spinner) findViewById(R.id.spCriteria);
        spServiceCenter = (Spinner) findViewById(R.id.spServiceCenter);
        spServiceMonth = (Spinner) findViewById(R.id.spServiceMonth);
        spGroupCategories = (Spinner) findViewById(R.id.sp_srvGroupCategories);
        spGroup = (Spinner) findViewById(R.id.sp_srvGroup);
        spLayR4List = (Spinner) findViewById(R.id.sp_srvLayR4List);
        tv_srvDate = (TextView) findViewById(R.id.tv_srvDate);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnSave = (Button) findViewById(R.id.btn_service_save);
        btnSummary = (Button) findViewById(R.id.btnRegisterFooter);
        mListView = (ListView) findViewById(R.id.lv_ServiceRecording);
        btn_search = (Button) findViewById(R.id.btn_service_search);
        edt_srvMMSerach = (EditText) findViewById(R.id.edt_service_memberSearch);
        spDistributionType = (Spinner) findViewById(R.id.sp_srv_dist_Type);

        /**         * Header Check Box         */
        final View headerView = getLayoutInflater().inflate(R.layout.title_service_listview_header, mListView, false);
        checkBox_header = (CheckBox) headerView.findViewById(R.id.cb_ServiceCheckedAll);
        mListView.addHeaderView(headerView);

        tv_srvTitleCount = (TextView) findViewById(R.id.tv_srvTitleCount);
        tv_GrpCatLabel = (TextView) findViewById(R.id.tv_service_GrpCatLabel);
        tv_GrpLabel = (TextView) findViewById(R.id.tv_service_GrpLabel);
        tv_LayR4Label = (TextView) findViewById(R.id.tv_service_LayR4Label);

        lay_MemberDetails = (LinearLayout) findViewById(R.id.lay_MemberDetails);
        tv_MemberDetails_HHID = (TextView) findViewById(R.id.srv_tv_details_HHID);
        tv_MemberDetails_MemID = (TextView) findViewById(R.id.srv_tv_details_MemID);
        tv_MemberDetails_MemName = (TextView) findViewById(R.id.srv_tv_details_MemName);
        edt_MemberDetails_working_days = (EditText) findViewById(R.id.srv_edt_total_working_days);


    }

    private void showNHideGroupNCat(int visibility) {
        tv_GrpCatLabel.setVisibility(visibility);
        tv_GrpLabel.setVisibility(visibility);
        spGroupCategories.setVisibility(visibility);
        spGroup.setVisibility(visibility);
    }

    private void showNHideLayRList(int visibility) {
        tv_LayR4Label.setVisibility(visibility);
        spLayR4List.setVisibility(visibility);

    }

}
