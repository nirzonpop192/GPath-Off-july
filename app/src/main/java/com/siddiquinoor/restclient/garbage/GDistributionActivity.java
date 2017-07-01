package com.siddiquinoor.restclient.garbage;

/**
 * Created by USER on 6/2/2016.
 * hje
 */
public class GDistributionActivity {
/** pervous Distribu tion activity */
/*    package com.siddiquinoor.restclient.activity;

    import android.app.Activity;
    import android.app.AlertDialog;
    import android.app.ProgressDialog;
    import android.content.Context;
    import android.content.DialogInterface;
    import android.content.Intent;
    import android.graphics.Color;
    import android.os.AsyncTask;
    import android.os.Bundle;
    import android.text.Editable;
    import android.text.TextWatcher;
    import android.util.Log;
    import android.util.SparseBooleanArray;
    import android.view.ContextThemeWrapper;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.view.Window;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.BaseAdapter;
    import android.widget.Button;
    import android.widget.CheckBox;
    import android.widget.CompoundButton;
    import android.widget.EditText;
    import android.widget.ImageButton;
    import android.widget.LinearLayout;
    import android.widget.ListView;
    import android.widget.Spinner;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.google.zxing.client.android.Intents;
    import com.google.zxing.integration.android.IntentIntegrator;
    import com.siddiquinoor.restclient.R;
    import com.siddiquinoor.restclient.activity.sub_activity.dist_sub.DistrubutionVoucherActivity;
    import com.siddiquinoor.restclient.fragments.BaseActivity;
    import com.siddiquinoor.restclient.manager.SQLiteHandler;
    import com.siddiquinoor.restclient.utils.KEY;
    import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
    import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
    import com.siddiquinoor.restclient.utils.UtilClass;
    import com.siddiquinoor.restclient.data_model.adapters.DistributionGridDataModel;
    import com.siddiquinoor.restclient.data_model.adapters.DistributionSaveDataModel;
    import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
    import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

    import java.text.ParseException;
    import java.util.ArrayList;
    import java.util.List;

    public class DistributionActivity extends BaseActivity implements View.OnClickListener {
        AlertDialog goToDialog;
        Intent intent;

        private static final String TAG = DistributionActivity.class.getSimpleName();
        private static final String FOOD_TYPE = "FoodFlag";
        private static final String NON_FOOD_TYPE = "NFoodFlag";
        private static final String CASH_TYPE = "CashFlag";
        private static final String VOUCHER_TYPE = "VOFlag";
        private String idCountry, strCounty;
        private final Context MCONTEXT = DistributionActivity.this;
        ;
        private SQLiteHandler sqlH;
        private Spinner spAward, spProgram, spDistributionType, spServiceMonth, spDistributionMonth, spUpazilla, spFDP;
        private String strAward, strProgram, strServiceMonth, strDistMonth, strUpazilla, strFDP, strDistType;
        private String idDonor, idAward, idProgram, idDistributionType, idServiceMonth, idDisMonth, idUP, idFDP;

        private TextView tvLayR2Label;
        private ArrayList<DistributionGridDataModel> distributedArray = new ArrayList<DistributionGridDataModel>();
        private Button btn_home, btnSummary, btnDistSave, btnSearch;
        private ListView mListView;
        private static boolean isNotAdded = true;
        private CheckBox checkBox_header;
        private static int count = 0;
        public static ProgressDialog pDialog;
        *//**
         * To save checked items, and re-add while scrolling.
         *//*
        SparseBooleanArray mChecked = new SparseBooleanArray();
        private DistributionDataListAdapter adapter;
        private EditText edtMemSearch;
        private ADNotificationManager dialog;
        private Button btnDistQR;
        private TextView titleLayerSet;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_distribution);

            sqlH = new SQLiteHandler(this);
            dialog = new ADNotificationManager();

            viewReference();

            Intent intent = getIntent();
            String dir = intent.getStringExtra(KEY.DIR_CLASS_NAME_KEY);
            if (dir.equals("DistrubutionVoucherActivity")) {
                idCountry = intent.getStringExtra(KEY.COUNTRY_ID);
                idAward = intent.getStringExtra(KEY.AWARD_CODE);
                strAward = intent.getStringExtra(KEY.AWARD_NAME);
                idDonor = intent.getStringExtra(KEY.DONOR_CODE);
                idProgram = intent.getStringExtra(KEY.PROGRAM_CODE);
                strProgram = intent.getStringExtra(KEY.PROGRAM_NAME);
                idDistributionType = intent.getStringExtra(KEY.DISTRIBUTION_TYPE_CODE);
                strDistType = intent.getStringExtra(KEY.DISTRIBUTION_TYPE_NAME);
                idServiceMonth = intent.getStringExtra(KEY.SERVICE_MONTH_CODE);
                strServiceMonth = intent.getStringExtra(KEY.SERVICE_MONTH_NAME);
                idDisMonth = intent.getStringExtra(KEY.DISTRIBUTION_MONTH_CODE);
                strDistMonth = intent.getStringExtra(KEY.DISTRIBUTION_MONTH_NAME);
                idUP = intent.getStringExtra(KEY.LAYER_2_CODE);
                strUpazilla = intent.getStringExtra(KEY.LAYER_2_NAME);
                idFDP = intent.getStringExtra(KEY.FDP_CODE);
                strFDP = intent.getStringExtra(KEY.FDP_NAME);


                loadAward(idCountry);
                Log.d(TAG, "From the Distribution  Dir page idCountry:" + idCountry
                        + " idAward : " + idAward + " strAward: " + strAward
                        + " idDonor : " + idDonor + " idProgram: " + idProgram
                        + " strProgram : " + strProgram + " idDistributionType: " + idDistributionType
                        + " strDistType : " + strDistType + " idServiceMonth: " + idServiceMonth
                        + " strServiceMonth : " + strServiceMonth + " idDisMonth: " + idDisMonth
                        + " strDistMonth : " + strDistMonth + " idUP: " + idUP
                        + " strUpazilla : " + strUpazilla + " idFDP: " + idFDP
                        + " strFDP : " + strFDP + " idFDP: " + idFDP

                );

       *//*     loadDistributionListView(String cCode, String donorCode, String awardCode,
                    String srvOpMCode, String fdpCode, String distOpMCode, String mem)*//*

            } else {

                idCountry = intent.getStringExtra(KEY.COUNTRY_ID);
                strCounty = intent.getStringExtra(KEY.STR_COUNTRY);
                loadAward(idCountry);
            }
            Log.d(TAG, " in ON Create method");
            tvLayR2Label.setText(UtilClass.getLayR2LabelName(MCONTEXT, idCountry));

            setListener();

//        masterCheckBoxController();

          *//*
         * To avoid adding multiple times
         *//*
//        if (isNotAdded) {

            *//*
             * mListView >> (ListView) //DO NOT ADD `NULL` here.
             *//*

            final View headerView = getLayoutInflater().inflate(R.layout.title_distribution_listview_header,
                    mListView, false);

            checkBox_header = (CheckBox) headerView.findViewById(R.id.cb_DistributationCheckedAll);
            titleLayerSet = (TextView) headerView.findViewById(R.id.tv_dist_tittle_rec);
            Log.d(TAG, " in ON Create method Check Box View Reference ");

            *//*
             * Select All / None DO NOT USE "setOnCheckedChangeListener" here.
             *//*
            checkBox_header.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    *//*
                     * Set all the checkbox to True/False
                     *//*
                    for (int i = 0; i < count; i++) {
                        mChecked.put(i, checkBox_header.isChecked());
                    }

                    *//*
                     * Update View
                     *//*
                    adapter.notifyDataSetChanged();

                }
            });

            *//*
             * Add Header to ListView
             *//*
            mListView.addHeaderView(headerView);
//
//            isNotAdded = false;
//        }


        }


        private void setListener() {
            btn_home.setOnClickListener(this);
            btnSummary.setOnClickListener(this);
            btnDistSave.setOnClickListener(this);
            btnSearch.setOnClickListener(this);
            btnDistQR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentIntegrator integrator = new IntentIntegrator(DistributionActivity.this);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                    // QR Code Capture Activity Orientation Set : degree unit
                    integrator.setOrientation(90);
                    // Scan View finder size : pixel unit
                    integrator.addExtra(Intents.Scan.HEIGHT, 300);
                    integrator.addExtra(Intents.Scan.WIDTH, 300);
                    // Capture View Start
                    integrator.initiateScan();
                }
            });


            edtMemSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
// edtMemSearch.setText("");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (edtMemSearch.getText().toString() != null && !edtMemSearch.getText().toString().equals("")) {
                        Log.d(TAG, "In Search button : idCountry :" + idCountry + ", idDonor :" + idDonor + " idAward:" + idAward + " idServiceMonth:" + idServiceMonth + " idFDP:" + idFDP + " idDisMonth:" + idDisMonth);
//                    loadDistributionListView(idCountry, idDonor, idAward, idServiceMonth, idFDP, idDisMonth, edtMemSearch.getText().toString());
                        LoadingList ld=new LoadingList(idCountry, idDonor, idAward, idServiceMonth, idFDP, idDisMonth, edtMemSearch.getText().toString()) ;
                        ld.execute();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

        }

        private void viewReference() {
            spAward = (Spinner) findViewById(R.id.sp_dist_awardList);
            spProgram = (Spinner) findViewById(R.id.sp_dist_Program);
            spFDP = (Spinner) findViewById(R.id.sp_dis_FDPoint);
            spDistributionType = (Spinner) findViewById(R.id.sp_dist_Type);
            spServiceMonth = (Spinner) findViewById(R.id.sp_dis_ServiceMonth);
            spDistributionMonth = (Spinner) findViewById(R.id.sp_dis_DistributionMonth);
            spUpazilla = (Spinner) findViewById(R.id.sp_dis_upzellla);

            btn_home = (Button) findViewById(R.id.btnHomeFooter);
            btn_home.setText("Go To ");
            btnSummary = (Button) findViewById(R.id.btnRegisterFooter);


            btnSummary.setText("SumRegLay4TotalHHRecords");
            mListView = (ListView) findViewById(R.id.lv_dist);
            btnDistSave = (Button) findViewById(R.id.btn_dist_save);
            btnDistQR = (Button) findViewById(R.id.btn_dist_qr);
            btnSearch = (Button) findViewById(R.id.btn_distribution_search);
            edtMemSearch = (EditText) findViewById(R.id.edt_dist_memberSearch);

            tvLayR2Label = (TextView) findViewById(R.id.tv_layR2Label);


        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case IntentIntegrator.REQUEST_CODE:
                    if (resultCode == Activity.RESULT_OK) {
                        String qr_content = data.getStringExtra(Intents.Scan.RESULT);
                        //qr_reader_result_textView.setText(qr_content);
                        edtMemSearch.setText(qr_content);
                    } else {
                        Log.d("TAG", "Result Not Ok");
                    }
                    break;
                default:
                    Log.d("TAG", "No Result Code");
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnHomeFooter:

                    //goToMainActivity((Activity) MCONTEXT);
                    goToAlert();
                    break;
                case R.id.btnRegisterFooter:

                    goToSummaryActivity((Activity) MCONTEXT, idCountry);
                    break;
                case R.id.btn_dist_save:
                    saveDistributionData();
                    break;
                case R.id.btn_distribution_search:
                    if (edtMemSearch.getText().toString() != null && !edtMemSearch.getText().toString().equals("")) {
                        Log.d(TAG, "In Search button : idCountry :" + idCountry + ", idDonor :" + idDonor + " idAward:" + idAward + " idServiceMonth:" + idServiceMonth + " idFDP:" + idFDP + " idDisMonth:" + idDisMonth);
//                    loadDistributionListView(idCountry, idDonor, idAward, idServiceMonth, idFDP, idDisMonth, edtMemSearch.getText().toString());
                        LoadingList ld=new LoadingList(idCountry, idDonor, idAward, idServiceMonth, idFDP, idDisMonth, edtMemSearch.getText().toString()) ;
                        ld.execute();

                    }
                    break;
            }
        }

        *//**
         * LOAD :: Award
         *//*
        private void loadAward(final String idCountry) {

            int position = 0;
            String criteria = " WHERE " + SQLiteHandler.ADM_COUNTRY_AWARD_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + idCountry + "'";
            // Spinner Drop down elements for District
            List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.ADM_COUNTRY_AWARD_TABLE, criteria, null, false);

            // Creating adapter for spinner
            ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listAward);
            // Drop down layout style
            dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
            // attaching data adapter to spinner
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
                    idAward = ((SpinnerHelper) spAward.getSelectedItem()).getId();



                    if (idAward.length() > 2) {
                        idDonor = idAward.substring(0, 2);
                        idAward = idAward.substring(2);
                        loadDistributionType();
                    }
                    //loadProgram(idAward.substring(2), idDonor, idCountry);
                    Log.d(TAG, "idAward : " + idAward + " donor id :" + idAward.substring(0, 2));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } // end Load Award Spinner

        *//**
         * LOAD :: Program
         *//*
        private void loadProgram(final String idcCode, final String donorCode, final String awardCode, String distributionType) {

            int position = 0;
            String criteria = "";
            switch (distributionType) {
                case FOOD_TYPE:
                    criteria = SQLiteQuery.getDistProgramsNames_WHERE_Condition(awardCode, donorCode, SQLiteHandler.FOOD_FLAG);
                    break;
                case NON_FOOD_TYPE:
                    criteria = SQLiteQuery.getDistProgramsNames_WHERE_Condition(awardCode, donorCode, SQLiteHandler.NON_FOOD_FLAG);
                    break;
                case CASH_TYPE:
                    criteria = SQLiteQuery.getDistProgramsNames_WHERE_Condition(awardCode, donorCode, SQLiteHandler.CASH_FLAG);
                    break;
                case VOUCHER_TYPE:
                    criteria = SQLiteQuery.getDistProgramsNames_WHERE_Condition(awardCode, donorCode, SQLiteHandler.VOUCHER_FLAG);
                    break;


            }

            // Spinner Drop down elements for District
            List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.COUNTRY_PROGRAM_TABLE, criteria, null, false);

            // Creating adapter for spinner
            ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listProgram);
            // Drop down layout style
            dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
            // attaching data adapter to spinner
            spProgram.setAdapter(dataAdapter);


            if (idProgram != null) {
                for (int i = 0; i < spProgram.getCount(); i++) {
                    String prog = spProgram.getItemAtPosition(i).toString();
                    if (prog.equals(strProgram)) {
                        position = i;
                    }
                }
                spProgram.setSelection(position);
            }


            spProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    strProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getValue();
                    idProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getId();
                    if (idProgram.length() > 2) {
                        loadServiceMonth(idCountry);


                        Log.d(TAG, "load Prog data " + idProgram);

                    }
                    //  loadProgram(idAward);
                    // loadCriteria(idAward, donorId, idProgram, idcCode);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } // end Load Spinner

        *//**
         * LOAD :: Criteria
         *//*
        private void loadFDP(final String cCode, final String layR2Code) {

            int position = 0;

            String criteria = SQLiteQuery.getFDPNames_Where_Condition(cCode, layR2Code);

            List<SpinnerHelper> fdpList = sqlH.getListAndID(SQLiteHandler.STAFF_FDP_ACCESS_TABLE, criteria, null, false);


            ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, fdpList);

            dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

            spFDP.setAdapter(dataAdapter);


            if (idFDP != null) {
                for (int i = 0; i < spFDP.getCount(); i++) {
                    String criteriaN = spFDP.getItemAtPosition(i).toString();
                    if (criteriaN.equals(strFDP)) {
                        position = i;
                    }
                }
                spFDP.setSelection(position);
            }


            spFDP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    strFDP = ((SpinnerHelper) spFDP.getSelectedItem()).getValue();
                    idFDP = ((SpinnerHelper) spFDP.getSelectedItem()).getId();


                    if (Integer.parseInt(idFDP) > 0) {
                        // loadVillage(cCode, idAward, donorId, idProgram, idCriteria);// idService=idCriteria



//                    loadDistributionListView(idCountry, idDonor, idAward, idServiceMonth, idFDP, idDisMonth, "");
                        LoadingList ld=new LoadingList(idCountry, idDonor, idAward, idServiceMonth, idFDP, idDisMonth, "") ;
                        ld.execute();


                    }

                    // if(idCriteria.length()>2){
                    Log.d(TAG, "load Food Distribution data " + idFDP + " Critrei a name " + strFDP);
                    // loadDistributionListView(idcCode,donorId,idAward,idCriteria.substring(0,3),idCriteria.substring(3));


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } // end Load Spinner

        *//**
         * ** LOAD:: DistributionType
         *//*
        private void loadDistributionType() {
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


      *//*  if (idDistributionType != null) {

            spDistributionType.setSelection(Integer.parseInt(idDistributionType));
        }*//*
            *//*** Experiments*//*

            spDistributionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    String type = parent.getItemAtPosition(position).toString();

                    if (type.equals("Food")) {
                        strDistType = "Food";
                        idDistributionType = FOOD_TYPE;
                    } else if (type.equals("Non Food")) {
                        strDistType = "Non Food";
                        idDistributionType = NON_FOOD_TYPE;
                    } else if (type.equals("Cash")) {
                        strDistType = "Cash";
                        idDistributionType = CASH_TYPE;
                    } else {
                        strDistType = "Voucher";
                        idDistributionType = VOUCHER_TYPE;
                    }

                    loadProgram(idCountry, idDonor, idAward, idDistributionType);


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        private void loadServiceMonth(String countryCode) {

            int position = 0;
            String criteria = SQLiteQuery.getServiceMonths_WHERE_Service_Close_Condition(countryCode);

            List<SpinnerHelper> listMonth = sqlH.getListAndID(SQLiteHandler.OP_MONTH_TABLE, criteria, null, false);
            ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listMonth);

            dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

            spServiceMonth.setAdapter(dataAdapter);


            if (idServiceMonth != null) {
                for (int i = 0; i < spServiceMonth.getCount(); i++) {
                    String month = spServiceMonth.getItemAtPosition(i).toString();
                    if (month.equals(strServiceMonth)) {
                        position = i;
                        Log.d("NIR", "Service month where close : id :" + idServiceMonth);
                    }
                }
                spServiceMonth.setSelection(position);
            }


            spServiceMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    strServiceMonth = ((SpinnerHelper) spServiceMonth.getSelectedItem()).getValue();
                    idServiceMonth = ((SpinnerHelper) spServiceMonth.getSelectedItem()).getId();
                    if (idServiceMonth.length() > 2) {
                        idServiceMonth = idServiceMonth.substring(8);

                        loadDistributionMonth(idCountry);


                        Log.d(TAG, "in if condition");
                        //loadServiceSummaryCriteriaList(idCountry, donorId, awardId, opMonthId);
                    }

                    Log.d(TAG, "idServiceMonth : " + idServiceMonth + " strServiceMonth :" + strServiceMonth);
                    //Log.d(TAG, "ID is: " + idDist);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } // end Load Spinner


        private void loadDistributionMonth(String countryCode) {

            int position = 0;
            String criteria = SQLiteQuery.getDistributionMonths_WHERE_Condition(countryCode);

            List<SpinnerHelper> listMonth = sqlH.getListAndID(SQLiteHandler.OP_MONTH_TABLE, criteria, null, false);
            ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listMonth);

            dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

            spDistributionMonth.setAdapter(dataAdapter);


            if (idDisMonth != null) {
                for (int i = 0; i < spDistributionMonth.getCount(); i++) {
                    String month = spDistributionMonth.getItemAtPosition(i).toString();
                    if (month.equals(strDistMonth)) {
                        position = i;
                    }
                }
                spDistributionMonth.setSelection(position);
            }


            spDistributionMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    strDistMonth = ((SpinnerHelper) spDistributionMonth.getSelectedItem()).getValue();
                    idDisMonth = ((SpinnerHelper) spDistributionMonth.getSelectedItem()).getId();
                    if (idDisMonth.length() > 2) {

                        idDisMonth = idDisMonth.substring(8);
                        Log.d(TAG, "Distributration Month Code :" + idDisMonth);
                        loadLayR2List(idCountry);
                        //loadServiceSummaryCriteriaList(idCountry, donorId, awardId, opMonthId);
                    }

                    Log.d(TAG, "idServiceMonth : " + idServiceMonth + " strServiceMonth :" + strDistMonth);
                    //Log.d(TAG, "ID is: " + idDist);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } // end Load Spinner


        *//**
         * LOAD :: UPAZILLA
         *
         *//*
        private void loadLayR2List(String cCode) {
            int position = 0;
            String criteria = " WHERE " + SQLiteHandler.STAFF_FDP_ACCESS_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + idCountry + "'"
                    + " AND " + SQLiteHandler.STAFF_FDP_ACCESS_TABLE + "." + SQLiteHandler.STAFF_CODE + " = '" + getStaffID() + "'"
                    + " AND " + SQLiteHandler.STAFF_FDP_ACCESS_TABLE + "." + SQLiteHandler.BTN_NEW_COL + " = '1'"
                    + " ORDER BY  Upazilla.UpazillaCode ";


            List<SpinnerHelper> listUpazilla = sqlH.getListAndID(SQLiteHandler.FDP_LAY_R2, criteria, cCode, false);
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

                    //strUpazilla = parent.getItemAtPosition(position).toString();
                    strUpazilla = ((SpinnerHelper) spUpazilla.getSelectedItem()).getValue();
                    idUP = ((SpinnerHelper) spUpazilla.getSelectedItem()).getId();
//                tvLayR2Label.setText(strUpazilla);
                    //loadUnion(idCountry);
                    if (idUP.length() > 2) {
                        loadFDP(idCountry, idUP);
                    }
                    //Log.d(TAG, "Upazilla selected: " + strUpazilla);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        *//**
         * While load data to the adapter
         *//*
        private class LoadingList extends AsyncTask<Void, Integer, String> {


            private String country, donor, award, serviceMonth, fDP, disMonth, memSearch;

            public LoadingList(String country, String donor, String award, String serviceMonth, String fDP, String disMonth, String memSearch) {
                this.country = country;
                this.donor = donor;
                this.award = award;
                this.serviceMonth = serviceMonth;
                this.fDP = fDP;
                this.disMonth = disMonth;
                this.memSearch = memSearch;
            }

            @Override
            protected String doInBackground(Void... params) {
                try {


                    loadDistributionListView(country, donor, award, serviceMonth, fDP, disMonth, memSearch);
                } catch (Exception e) {

                    return "UNKNOWN";
                }
                return "sucess";


                //return "";
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                startProgressBar("Data is Loading");
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                startProgressBar("Data is Loading.");
                // startProgressBar("Please wait");
            }


            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                pDialog.dismiss();


                // if the selected program is  for voucher tha this view will gone
                if (sqlH.checkAdmCountryProgramsVoucherFlag(idCountry, idDonor, idAward, idProgram)) {
                    btnDistSave.setVisibility(View.GONE);
                } else
                    btnDistSave.setVisibility(View.VISIBLE);

           *//* if(adapter.getCount()==0){

                *//**//** clean the list view *//**//*
                distributedArray.clear();
//                adapter = new DistributionDataListAdapter(this, distributedArray, disMonth, fdpCode, idProgram, idServiceMonth);
                loadDistributionListView(country, donor, award, serviceMonth, fDP, disMonth, memSearch);
                adapter.notifyDataSetChanged();
                mListView.setAdapter(adapter);

                dialog.showInfromDialog(MCONTEXT, "No Data", "No Data found");

            }
*//*

                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                    mListView.setAdapter(adapter);

                *//*mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
                mListView.setFocusableInTouchMode(true);*//*

                    if (adapter.getCount()==0){
                        dialog.showInfromDialog(MCONTEXT, "No Data", "No Data found");
                    }
                }
                else{
                    dialog.showInfromDialog(MCONTEXT, "No Data", "No Data found");
                }

            }
        }

        private void startProgressBar(String msg) {

            pDialog= new ProgressDialog(this);
            pDialog.setMessage(msg);
            pDialog.setCancelable(false);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            pDialog.show();
        }

   *//* public void loadDistributionListView(String cCode, String donorCode, String awardCode, String srvOpMCode, String fdpCode, String distOpMCode, String mem) {
        Log.d(TAG, "In load Distributration list ");
        // use veriable to like operation
        List<DistributionGridDataModel> distributedListData = sqlH.getDistributionDataList(cCode, donorCode, awardCode, srvOpMCode, fdpCode, mem);

        *//**//** @date: 2015-10-13
         * @description: Make a provision where if there is not data for the selected criteria then grid will be empty.*//**//*
        if (distributedListData.isEmpty() || distributedListData == null) {
//            Toast.makeText(MCONTEXT, " there is not data for the selected criteria ", Toast.LENGTH_SHORT).show();


            *//**//** clean the list view *//**//*
            distributedArray.clear();
            adapter = new DistributionDataListAdapter(this, distributedArray, distOpMCode, fdpCode, idProgram, idServiceMonth);
            adapter.notifyDataSetChanged();
            mListView.setAdapter(adapter);

            dialog.showInfromDialog(MCONTEXT, "No Data", "No Data found");
        }

        if (distributedListData.size() != 0) {
            distributedArray.clear();
            for (DistributionGridDataModel data : distributedListData) {
                // add contacts data in arrayList
                distributedArray.add(data);
            }
            Log.d(TAG, " size :" + distributedListData.size());
            adapter = new DistributionDataListAdapter(this, distributedArray, distOpMCode, fdpCode, idProgram, idServiceMonth);
            adapter.notifyDataSetChanged();
            mListView.setAdapter(adapter);

        }
        // if the selected program is  for voucher tha this view will gone
        if (sqlH.checkAdmCountryProgramsVoucherFlag(idCountry, idDonor, idAward, idProgram)) {
            btnDistSave.setVisibility(View.GONE);
        } else
            btnDistSave.setVisibility(View.VISIBLE);

    }
*//*


        public void loadDistributionListView(String cCode, String donorCode, String awardCode, String srvOpMCode, String fdpCode, String distOpMCode, String mem) {
            Log.d(TAG, "In load Distributration list ");
            // use veriable to like operation
            List<DistributionGridDataModel> distributedListData = sqlH.getDistributionDataList(cCode, donorCode, awardCode, srvOpMCode, fdpCode, mem);

            *//** @date: 2015-10-13
             * @description: Make a provision where if there is not data for the selected criteria then grid will be empty.*//*
     *//*   if (distributedListData.isEmpty() || distributedListData == null) {
//            Toast.makeText(MCONTEXT, " there is not data for the selected criteria ", Toast.LENGTH_SHORT).show();


            *//**//** clean the list view *//**//*
            distributedArray.clear();
            adapter = new DistributionDataListAdapter(this, distributedArray, distOpMCode, fdpCode, idProgram, idServiceMonth);
            adapter.notifyDataSetChanged();
            mListView.setAdapter(adapter);

            dialog.showInfromDialog(MCONTEXT, "No Data", "No Data found");
        }*//*

            if (distributedListData.size() != 0) {
                distributedArray.clear();
                for (DistributionGridDataModel data : distributedListData) {
                    // add contacts data in arrayList
                    distributedArray.add(data);
                }
                Log.d(TAG, " size :" + distributedListData.size());
                adapter = new DistributionDataListAdapter(this, distributedArray, distOpMCode, fdpCode, idProgram, idServiceMonth);
           *//* adapter.notifyDataSetChanged();
            mListView.setAdapter(adapter);*//*

            }

            else {
                *//** clean the list view *//*
                distributedArray.clear();
                adapter = new DistributionDataListAdapter(this, distributedArray, distOpMCode, fdpCode, idProgram, idServiceMonth);



            }
       *//* // if the selected program is  for voucher tha this view will gone
        if (sqlH.checkAdmCountryProgramsVoucherFlag(idCountry, idDonor, idAward, idProgram)) {
            btnDistSave.setVisibility(View.GONE);
        } else
            btnDistSave.setVisibility(View.VISIBLE);
*//*
        }



        private void saveDistributionData() {

            boolean invalid = false;

            if (invalid) {
                invalid = true;
                dialog.showErrorDialog(MCONTEXT, "Please select a Date");
            } else if (adapter == null) {
                dialog.showErrorDialog(MCONTEXT, "Invalid Attempt to save");
            } else {

                ArrayList<DistributionSaveDataModel> alist = new ArrayList<DistributionSaveDataModel>();
                alist = adapter.getArrayList();

                try {
                    String EntryBy = getStaffID();

                    String EntryDate = getDateTime();

                    for (DistributionSaveDataModel distDataP : alist) {

                        distDataP.setEntryBy(EntryBy);
                        distDataP.setEntryDate(EntryDate);
                        distDataP.setSrvOpMonthCode(idServiceMonth);
                        distDataP.setDistFlag(idDistributionType);

                        long id = sqlH.addInDistributionTable(distDataP);

                        SQLServerSyntaxGenerator distributedData = new SQLServerSyntaxGenerator();
                        distributedData.setAdmCountryCode(distDataP.getCountryCode());
                        distributedData.setAdmDonorCode(distDataP.getAdmDonorCode());
                        distributedData.setAdmAwardCode(distDataP.getAdmAwardCode());
                        distributedData.setLayR1ListCode(distDataP.getDistrictCode());
                        distributedData.setLayR2ListCode(distDataP.getUpCode());
                        distributedData.setLayR3ListCode(distDataP.getUniteCode());
                        distributedData.setLayR4ListCode(distDataP.getVillageCode());
                        distributedData.setProgCode(distDataP.getProgCode());
                        distributedData.setSrvCode(distDataP.getSrvCode());
                        distributedData.setOpMonthCode(distDataP.getOpMonthCode());
                        distributedData.setFDPCode(distDataP.getFDPCode());
                        distributedData.setID(distDataP.getID());
                        distributedData.setDistStatus(distDataP.getDistStatus());

                        distributedData.setSrvOpMonthCode(distDataP.getSrvOpMonthCode());
                        distributedData.setDistFlag(distDataP.getDistFlag());


                        distributedData.setEntryBy(EntryBy);
                        distributedData.setEntryDate(EntryDate);
                        sqlH.insertIntoUploadTable(distributedData.insertIntoDistributionTable());



                    }

                    Toast.makeText(getApplicationContext(), "Submitted Successfully...", Toast.LENGTH_LONG).show();
                    loadDistributionListView(idCountry, idDonor, idAward, idServiceMonth, idFDP, idDisMonth, "");

                *//*LoadingList ld=new LoadingList(idCountry, idDonor, idAward, idServiceMonth, idFDP, idDisMonth, "") ;
                ld.execute();*//*
                    // end of for loop
                    // LoadingList loading = new LoadingList();
                    // idMemberSearch = "";
                    // loading.execute();
//                loadDistributionListView(idCountry, idDonor, idAward, idProgram, idService, "", strOpMonthLabel, idOp, idOpMonth);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //}
            }// end of else


        }// end of save method


        *//**
         * Set Adapter class here
         *//*
        public class DistributionDataListAdapter extends BaseAdapter {
            private Activity activity;
            private ArrayList<DistributionGridDataModel> distributData;
            private LayoutInflater inflater;
            private String distOpMonthCode;
            private String fdpCode;
            private String progCode;
            private SQLiteHandler sqLiteHandler;
            private String serviceMonthCode;
            // when the check box is checked that partiqular id will save in this ArrayList
            private ArrayList<DistributionSaveDataModel> distListOFWant2Save = new ArrayList<DistributionSaveDataModel>();

            viewHolder holder;

            public DistributionDataListAdapter(Activity activity, ArrayList<DistributionGridDataModel> distributData, String distOpMonthCode, String fdpCode, String progCode, String serviceMonthCode) {
                this.distributData = distributData;
                this.activity = activity;
                this.distOpMonthCode = distOpMonthCode;
                this.fdpCode = fdpCode;
                this.progCode = progCode;
                sqLiteHandler = new SQLiteHandler(activity);
                this.serviceMonthCode = serviceMonthCode;
            }

            @Override
            public int getCount() {
                count = distributData.size();
                return count;
            }

            @Override
            public Object getItem(int position) {
                return distributData.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            DistributionGridDataModel getDistributedPerson(int position) {
                return (DistributionGridDataModel) getItem(position);
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                final DistributionGridDataModel disPeople = distributData.get(position);

                View row = convertView;

                holder = null;


                if (inflater == null)
                    inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


                if (convertView == null) {
                    // reference the view to xml id
                    row = inflater.inflate(R.layout.list_row_distribution, null);
                    holder = new viewHolder();
                    holder.hhNewId = (TextView) row.findViewById(R.id.tv_dist_row_newId);
                    holder.hhName = (TextView) row.findViewById(R.id.txt_dis_row_name);
                    holder.hhSrvShortName = (TextView) row.findViewById(R.id.tv_dis_row_criteriaShortName);
                    holder.hhDistStatus = (TextView) row.findViewById(R.id.dis_row_tv_DistStatus);
                    holder.memNewID = (TextView) row.findViewById(R.id.tv_dist_mem_row_newId);
                    holder.memName = (TextView) row.findViewById(R.id.txt_dis_mem_row_name);
                    holder.memSrvShortName = (TextView) row.findViewById(R.id.tv_dis_row_mem_criteriaShortName);
                    holder.memDistStatus = (TextView) row.findViewById(R.id.dis_mem_row_tv_DistStatus);
                    holder.llHouseHold = (LinearLayout) row.findViewById(R.id.ll_hh);
                    holder.imgVoucher = (ImageButton) row.findViewById(R.id.ibtn_voucher_dist_holder);
                    holder.imgHidleIcon = (ImageButton) row.findViewById(R.id.ibtn_voucher_dist_holder_hid);

                    row.setTag(holder);

                } else {
                    holder = (viewHolder) row.getTag();
                }

                holder.hhNewId.setText(disPeople.getHh_id());
                holder.hhName.setText(disPeople.getHh_name());
                holder.hhSrvShortName.setText("HHR");

                holder.memNewID.setText(disPeople.getMem_id());
                holder.memName.setText(disPeople.getMem_name());
                holder.memSrvShortName.setText(disPeople.getServiceShortName());

                String hhDistStatus = sqLiteHandler.getDistributionStatusFromDistributionTable(disPeople.getC_code(), disPeople.getDonorCode(), disPeople.getAwardCode(), disPeople.getDistrictCode(), disPeople.getUpazillaCode(), disPeople.getUnitCode(), disPeople.getVillageCode(), disPeople.getProgram_code(), disPeople.getService_code(), distOpMonthCode, fdpCode, disPeople.getHh_id());
                Log.d("In Adapter Dist", " hh dist status :" + hhDistStatus);
                holder.hhDistStatus.setText(hhDistStatus);
                String memDistStatus = sqLiteHandler.getDistributionStatusFromDistributionTable(disPeople.getC_code(), disPeople.getDonorCode(), disPeople.getAwardCode(), disPeople.getDistrictCode(), disPeople.getUpazillaCode(), disPeople.getUnitCode(), disPeople.getVillageCode(), disPeople.getProgram_code(), disPeople.getService_code(), distOpMonthCode, fdpCode, disPeople.getMem_id());
                holder.memDistStatus.setText(memDistStatus);
                Log.d("In Adapter Dist", " mem dist status :" + memDistStatus);
                //  the hh check box reference is define here
                final CheckBox cbHouseHolId_holder = (CheckBox) row.findViewById(R.id.cb_dis_id_holde);
                // set checked change listener
                cbHouseHolId_holder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                   *//* Saving Checked Position                            *//*
                        mChecked.put(position, isChecked);

                                *//*
                                 * Find if all the check boxes are true
                                 *//*
                        if (isAllValuesChecked()) {

                                    *//*
                                     * set HeaderCheck box to true
                                     *//*
                            checkBox_header.setChecked(isChecked);
                        }

                        int getPosition = (Integer) buttonView.getTag(R.id.dist_hh_cb);
                        // get the object of sepecific row & set the value of the
                        // save the chekbox of that patricula state
                        getDistributedPerson(getPosition).setCheckBoxHouseHold(isChecked);

                        addHouseHoldDataToArrayList(getDistributedPerson(getPosition),
                                getDistributedPerson(getPosition).isCheckBoxHouseHold());
                        // addHouseHoldDataToArrayList(personToBeServiced,isChecked);
                        Log.d(TAG, "P:" + (Integer) buttonView.getTag(R.id.dist_hh_cb) + " check box: " + isChecked);
                    }
                });
                // set the sate of particular positioned check box
                cbHouseHolId_holder.setTag(R.id.dist_hh_cb, position);
                if (checkBox_header.isChecked()) {
                    cbHouseHolId_holder.setChecked((mChecked.get(position)));
                } else {
                    cbHouseHolId_holder.setChecked(getDistributedPerson((Integer) cbHouseHolId_holder.getTag(R.id.dist_hh_cb)).isCheckBoxHouseHold());
                }


                //   Log.d(TAG, " position " + position + " the check box House hold   " + cbHouseHolId_holder.isChecked());

                *//** For 2nd Check Box *//*


                //  the hh check box reference is define here
                final CheckBox cbMemberId_holder = (CheckBox) row.findViewById(R.id.cb_dis_mem_id_holde);
                // set checked change listener
                cbMemberId_holder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                   *//* Saving Checked Position                            *//*
                        mChecked.put(position, isChecked);


                        // Find if all the check boxes are true

                        if (isAllValuesChecked()) {


                            // set HeaderCheck box to true

                            checkBox_header.setChecked(isChecked);
                        }

                        int getPosition = (Integer) buttonView.getTag(R.id.distmem_cb);

                        // get the object of sepecific row & set the value of the
                        // save the chekbox of that patricula state
                        getDistributedPerson(getPosition).setCheckBoxMember(isChecked);

                        addMemberDataToArrayList(getDistributedPerson(getPosition),
                                getDistributedPerson(getPosition).isCheckBoxMember());
                        // addHouseHoldDataToArrayList(personToBeServiced,isChecked);
                        Log.d(TAG, "member:" + (Integer) buttonView.getTag(R.id.distmem_cb) + " check box: " + isChecked);

                    }
                });
                // set the sate of particular positioned check box
                cbMemberId_holder.setTag(R.id.distmem_cb, position);
                // if master check box is clicked than all the check box of member will be selected
                // other wise it will checked
                if (checkBox_header.isChecked()) {
                    cbMemberId_holder.setChecked((mChecked.get(position)));
                } else {
                    cbMemberId_holder.setChecked(getDistributedPerson((Integer) cbMemberId_holder.getTag(R.id.distmem_cb)).isCheckBoxMember());
                }


                Log.d(TAG, " position " + position + " the check box Member  " + cbMemberId_holder.isChecked());
                if (progCode.equals("002") || disPeople.getService_code().equals("01")) {

                    //   android.widget.LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, .36f);
                    //  titleLayerSet.setLayoutParams(params);

                    holder.llHouseHold.setVisibility(View.VISIBLE);
             *//*  Old trick
               if (position % 2 == 0) {
                    row.setBackgroundColor(activity.getResources().getColor(R.color.default_bg));
                    changeTextColor(activity.getResources().getColor(R.color.black));
                }*//*

                    if (position % 2 == 0) {
                        row.setBackgroundColor(Color.WHITE);
                        changeTextColor(activity.getResources().getColor(R.color.blue));
                    } else {
                        row.setBackgroundColor(activity.getResources().getColor(R.color.list_divider));
                        changeTextColor(activity.getResources().getColor(R.color.black));
                    }
                }
                else {
                    holder.llHouseHold.setVisibility(View.GONE);
                    if (position % 2 == 0) {
                        row.setBackgroundColor(Color.WHITE);
                        changeTextColor(activity.getResources().getColor(R.color.blue));
                    } else {
                        row.setBackgroundColor(activity.getResources().getColor(R.color.list_divider));
                        changeTextColor(activity.getResources().getColor(R.color.black));
                    }
                }



                holder.imgHidleIcon.setVisibility(View.GONE);

                // if the selected program is not for voucher tha this view will gone
                if (!sqlH.checkAdmCountryProgramsVoucherFlag(idCountry, idDonor, idAward, idProgram)) {
                    holder.imgVoucher.setVisibility(View.GONE);
                }

                holder.imgVoucher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(activity, DistrubutionVoucherActivity.class);
                        disPeople.setSrvMonthCode(serviceMonthCode);
                        disPeople.setDistOpMonthCode(distOpMonthCode);
                        disPeople.setFdpCode(idFDP);
                        disPeople.setTempAwardString(strAward);
                        disPeople.setTempProgString(strProgram);
                        disPeople.setTempDistTypeId(idDistributionType);
                        disPeople.setTempDistTypeString(strDistType);
                        disPeople.setTempsrvMonthName(strServiceMonth);
                        disPeople.setTempDistMonthName(strDistMonth);
                        disPeople.setTempUpazillaName(strUpazilla);
                        disPeople.setTempFDPName(strFDP);

                        disPeople.setDistFlag(idDistributionType);
                        disPeople.setSrvOpMonthCode(idServiceMonth);


                  *//*  personToBeServiced.setServiceCenterCode(serviceCenterCode);
                    personToBeServiced.setServiceDTCode(serviceDate);*//*
                        // send the Service data object to the ServiceVoucherDetails
                        intent.putExtra(KEY.DISTRIBUTION_DATA_OBJECT_KEY, disPeople);
                        activity.startActivity(intent);
                    }
                });

                return row;
            } // end of get view

            private void changeTextColor(int color) {
                holder.memNewID.setTextColor(color);
                holder.memName.setTextColor(color);
                holder.memSrvShortName.setTextColor(color);
                holder.memDistStatus.setTextColor(color);

            }


            // by this method add only hh entry to the array list
            private void addHouseHoldDataToArrayList(DistributionGridDataModel gridData, boolean chackBoxStatus) {
                DistributionSaveDataModel distributedPeople = new DistributionSaveDataModel();

                distributedPeople = convertGridDataIntoSaveDataModel(gridData, gridData.getHh_id());

                Log.d(TAG, "getC_code: " + gridData.getC_code() + " getDonorCode: " + gridData.getDonorCode() + "getAwardCode: " + gridData.getAwardCode() + " getDistrictCode: " + gridData.getDistrictCode() + " getUpazillaCode: " + gridData.getUpazillaCode() + " getUnitCode:" + gridData.getUnitCode() + " getVillageCode: " + gridData.getVillageCode() + " getProgram_code:" + gridData.getProgram_code() + " getService_code:" + gridData.getService_code() + " distOpMonthCode: " + distOpMonthCode + " fdpCode: " + fdpCode + " id : " + gridData.getHh_id());

                // if the the check box is checked
                if (chackBoxStatus) {
                    distListOFWant2Save.add(distributedPeople);
                } else {
                    // first check the data is exits ing or not
                    if (distListOFWant2Save.contains(distributedPeople)) {
                        distListOFWant2Save.remove(distributedPeople);
                    }

                }

            }// end of addHouseHoldDataToArrayList()


            // by this method add only member entry to the array list
            private void addMemberDataToArrayList(DistributionGridDataModel gridData, boolean chackBoxStatus) {
                DistributionSaveDataModel distributedPeople = new DistributionSaveDataModel();
                // set up the Dist Data model to save Dist data table
                distributedPeople = convertGridDataIntoSaveDataModel(gridData, gridData.getMem_id());

                Log.d(TAG, "getC_code: " + gridData.getC_code() + " getDonorCode: " + gridData.getDonorCode() + "getAwardCode: " + gridData.getAwardCode() + " getDistrictCode: " + gridData.getDistrictCode() + " getUpazillaCode: " + gridData.getUpazillaCode() + " getUnitCode:" + gridData.getUnitCode() + " getVillageCode: " + gridData.getVillageCode() + " getProgram_code:" + gridData.getProgram_code() + " getService_code:" + gridData.getService_code() + " distOpMonthCode: " + distOpMonthCode + " fdpCode: " + fdpCode + " id : " + gridData.getMem_id());

                // if the the check box is checked
                if (chackBoxStatus) {
                    distListOFWant2Save.add(distributedPeople);
                } else {
                    // first check the data is exits ing or not
                    if (distListOFWant2Save.contains(distributedPeople)) {
                        distListOFWant2Save.remove(distributedPeople);
                    }

                }

            }// end of addHouseHoldDataToArrayList()

            *//**
             * @param gridData
             * @param id
             *//*
            private DistributionSaveDataModel convertGridDataIntoSaveDataModel(DistributionGridDataModel gridData, String id) {
                DistributionSaveDataModel dPeople = new DistributionSaveDataModel();
                dPeople.setCountryCode(gridData.getC_code());
                dPeople.setAdmDonorCode(gridData.getDonorCode());
                dPeople.setAdmAwardCode(gridData.getAwardCode());
                dPeople.setDistrictCode(gridData.getDistrictCode());
                dPeople.setUpCode(gridData.getUpazillaCode());
                dPeople.setUniteCode(gridData.getUnitCode());
                dPeople.setVillageCode(gridData.getVillageCode());
                dPeople.setProgCode(gridData.getProgram_code());
                dPeople.setSrvCode(gridData.getService_code());
                dPeople.setOpMonthCode(distOpMonthCode);// send Distre butaion of monthe Code
                dPeople.setFDPCode(fdpCode);
                dPeople.setID(id);
                dPeople.setDistStatus("R");
                return dPeople;
            }

            public ArrayList<DistributionSaveDataModel> getArrayList() {
                return distListOFWant2Save;
            }

            *//*
                 * Find if all values are checked.
                 *//*
            protected boolean isAllValuesChecked() {

                for (int i = 0; i < count; i++) {
                    if (!mChecked.get(i)) {
                        return false;
                    }
                }

                return true;
            }

            class viewHolder {
                TextView hhNewId;
                TextView hhName;
                TextView hhSrvShortName;
                TextView hhDistStatus;

                TextView memNewID;
                TextView memName;
                TextView memSrvShortName;
                TextView memDistStatus;
                LinearLayout llHouseHold;
                ImageButton imgVoucher;
                ImageButton imgHidleIcon;


            }
        }// end of adpater class

        public void goToAlert() {
            final CharSequence[] items = getResources().getStringArray(R.array.distribution_goto_array);

            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(DistributionActivity.this, android.R.style.Theme_Holo_Light_Dialog));

            builder.setTitle("GO TO:");


            builder.setIcon(R.drawable.navigation_icon);
            builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    switch (item) {
                        case 0:
                            finish();
                            intent = new Intent(DistributionActivity.this, MainActivity.class);
                            startActivity(intent);
                            break;
                        case 1:

                            finish();
                            intent = new Intent(DistributionActivity.this, SummaryMenuActivity.class);
                            intent.putExtra(KEY.COUNTRY_ID, sqlH.selectCountryCode());
                            startActivity(intent);

                       *//* finish();
                        intent = new Intent(DistributionActivity.this, AssignActivity.class);
                        intent.putExtra(KEY.COUNTRY_ID, idCountry);

                        intent.putExtra(AssignActivity.SUB_ASSIGN_DIR, false);
                        intent.putExtra(KEY.COUNTRY_ID, sqlH.selectCountryCode());
                        startActivity(intent);*//*
                            break;
                        case 2:
                            finish();
                            intent = new Intent(DistributionActivity.this, ServiceActivity.class);
                            intent.putExtra(KEY.DIR_CLASS_NAME_KEY, "RegisterMemberLiberia");
                            intent.putExtra(KEY.COUNTRY_ID, sqlH.selectCountryCode());
                            startActivity(intent);
                            break;
                        case 3:
                            finish();
                            intent = new Intent(DistributionActivity.this, SummaryMenuActivity.class);
                            intent.putExtra(KEY.COUNTRY_ID, sqlH.selectCountryCode());
                            startActivity(intent);
                            break;
                    *//*case 4:
                        finish();
                        intent = new Intent(DistributionActivity.this, RegisterMemberLiberia.class);
                        startActivity(intent);
                        break;*//*
                        case 4:
                            finish();
                            intent = new Intent(DistributionActivity.this, RegisterLiberia.class);
                            intent.putExtra("country_code",sqlH.selectCountryCode());
                            startActivity(intent);
                            break;
                    }
                    goToDialog.dismiss();
                }
            });
            goToDialog = builder.create();
            goToDialog.show();
            int titleDividerId = goToDialog.getContext().getResources().getIdentifier("titleDivider", "id", "android");//("android:id/titleDivider",null,null);
            //   View titleDivider = activityDialog.findViewById(titleDividerId);
            View titleDivider = goToDialog.getWindow().getDecorView().findViewById(titleDividerId);
            if (titleDivider != null)
                titleDivider.setBackgroundColor(getResources().getColor(R.color.blue));
// setAlertDevider();

        }

    }*/

}
