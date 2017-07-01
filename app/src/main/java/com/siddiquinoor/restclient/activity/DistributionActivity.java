package com.siddiquinoor.restclient.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.utils.CalculationPadding;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.data_model.adapters.DistributionGridDataModel;
import com.siddiquinoor.restclient.data_model.adapters.DistributionSaveDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;
import com.siddiquinoor.restclient.views.spinner.SpinnerLoader;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.siddiquinoor.restclient.manager.SQLiteHandler.ADM_COUNTRY_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.FDP_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.FDP_MASTER_COUNTRY_CODE;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.FDP_MASTER_LAY_R1_LIST_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.FDP_MASTER_LAY_R2_LIST_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.FDP_MASTER_TABLE;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.LAY_R1_LIST_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.LAY_R2_LIST_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.SELECTED_FDP_TABLE;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.STAFF_FDP_ACCESS_COUNTRY_CODE;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.STAFF_FDP_ACCESS_TABLE;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.UPAZILLA_TABLE;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.UPZILLA_NAME_COL;

public class DistributionActivity extends BaseActivity {
    private static final int ONCE = 1;
    public static final String NONE = "NoneFlag";
    //    AlertDialog goToDialog;
    Intent intent;

    private static final String TAG = DistributionActivity.class.getSimpleName();
    public static final String FOOD_TYPE = "FoodFlag";
    public static final String NON_FOOD_TYPE = "NFoodFlag";
    public static final String CASH_TYPE = "CashFlag";
    public static final String VOUCHER_TYPE = "VOFlag";
    private String idCountry, strCounty;
    private final Context mContext = DistributionActivity.this;

    private SQLiteHandler sqlH;
    private Spinner spAward, spProgram, spDistributionType, spServiceMonth, spDistributionMonth, spUpazilla, spFDP;
    private String strAward, strProgram, strServiceMonth, strDistMonth, strUpazilla, strFDP, strDistType;
    private String idDonor, idAward, idProgram, idDistributionType, idServiceMonth, idDisMonth, idUP, idFDP;

    private TextView tvLayR2Label;
    private ArrayList<DistributionGridDataModel> distributedArray = new ArrayList<DistributionGridDataModel>();
    private Button btnHome, btnSummary, btnSave, btnSearch;
    private ListView mListView;
    private static boolean isNotAdded = true;
    private CheckBox checkBox_header;
    private static int count = 0;
    public static ProgressDialog pDialog;
    /**
     * To save checked items, and re-add while scrolling.
     */
    SparseBooleanArray mChecked = new SparseBooleanArray();
    private DistributionDataListAdapter adapter;
    private EditText edtMemSearch;

    /**
     * mDialog is Custom Dialog manager
     */
    private ADNotificationManager mDialog;
    private Button btnDistQR;
    private TextView titleLayerSet;
    private boolean fromQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_distribution);

        intil();


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
     /*       *//**
             * FOR TEST PURPOSE
             *//*
            Log.d(TAG, "From the Distribution  Dir page idCountry:" + idCountry
                    + " idAward : " + idAward + " strAward: " + strAward
                    + " idDonor : " + idDonor + " idProgram: " + idProgram
                    + " strProgram : " + strProgram + " idDistributionType: " + idDistributionType
                    + " strDistType : " + strDistType + " idServiceMonth: " + idServiceMonth
                    + " strServiceMonth : " + strServiceMonth + " idDisMonth: " + idDisMonth
                    + " strDistMonth : " + strDistMonth + " idUP: " + idUP
                    + " strUpazilla : " + strUpazilla + " idFDP: " + idFDP
                    + " strFDP : " + strFDP + " idFDP: " + idFDP  );*/

       /*     loadDistributionListView(String cCode, String donorCode, String awardCode,
                    String srvOpMCode, String fdpCode, String distOpMCode, String mem)*/

        } else {

            idCountry = intent.getStringExtra(KEY.COUNTRY_ID);
            strCounty = intent.getStringExtra(KEY.STR_COUNTRY);
            loadAward(idCountry);
        }
        Log.d(TAG, " in ON Create method");
        tvLayR2Label.setText(UtilClass.getLayR2LabelName(mContext, idCountry));

        setListener();

            /*
             * mListView >> (ListView) //DO NOT ADD `NULL` here.
             */

        final View headerView = getLayoutInflater().inflate(R.layout.title_distribution_listview_header,
                mListView, false);

        checkBox_header = (CheckBox) headerView.findViewById(R.id.cb_DistributationCheckedAll);
        titleLayerSet = (TextView) headerView.findViewById(R.id.tv_dist_tittle_rec);
        Log.d(TAG, " in ON Create method Check Box View Reference ");


        checkBox_header.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                /**
                 * Set all the checkbox to True/False
                 */
                for (int i = 0; i < count; i++) {
                    mChecked.put(i, checkBox_header.isChecked());
                    /**
                     * if master check box is then the toast will appears
                     * to notify the user to scroll down the last row
                     * just for once
                     */
                    if (checkBox_header.isChecked() && i < ONCE) {
                        Toast.makeText(DistributionActivity.this, "Scroll to the last row to ensure all row  is selected.", Toast.LENGTH_LONG).show();
                    }

                }


                /**
                 * Update View
                 */
                adapter.notifyDataSetChanged();

            }
        });

        /**
         * Add Header to ListView
         */
        mListView.addHeaderView(headerView);

        fromQR = false;
    }

    private void intil() {
        sqlH = new SQLiteHandler(this);
        mDialog = new ADNotificationManager();
        viewReference();
    }


    private void setListener() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomePage();
            }
        });
        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSummaryActivity((Activity) mContext, idCountry);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDistributionData();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtMemSearch.getText().toString() != null && !edtMemSearch.getText().toString().equals("")) {
                    Log.d(TAG, "In Search button : idCountry :" + idCountry + ", idDonor :" + idDonor + " idAward:" + idAward + " idServiceMonth:" + idServiceMonth + " idFDP:" + idFDP + " idDisMonth:" + idDisMonth);

                    LoadingList ld = new LoadingList(idCountry, idDonor, idAward, idProgram, idServiceMonth, idFDP, idDisMonth, edtMemSearch.getText().toString());
                    ld.execute();

                }
            }
        });


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
                fromQR = true;
            }
        });


        edtMemSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
// edtMemSearch.setText("");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (fromQR) {
                    if (edtMemSearch.getText().toString() != null && !edtMemSearch.getText().toString().equals("")) {

                        LoadingList ld = new LoadingList(idCountry, idDonor, idAward, idProgram, idServiceMonth, idFDP, idDisMonth, edtMemSearch.getText().toString());
                        ld.execute();
                        fromQR = false;
                    }
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
        btnHome = (Button) findViewById(R.id.btnHomeFooter);

        btnSummary = (Button) findViewById(R.id.btnRegisterFooter);

        mListView = (ListView) findViewById(R.id.lv_dist);
        btnSave = (Button) findViewById(R.id.btn_dist_save);
        btnDistQR = (Button) findViewById(R.id.btn_dist_qr);
        btnSearch = (Button) findViewById(R.id.btn_distribution_search);
        edtMemSearch = (EditText) findViewById(R.id.edt_dist_memberSearch);
        tvLayR2Label = (TextView) findViewById(R.id.tv_layR2Label);



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
        setUpGpsButton();
        setUpSummaryButton();
        setUpSaveButton();
    }

    private void setUpSaveButton() {
        btnSave.setText("");
        Drawable saveImage = getResources().getDrawable(R.drawable.save_b);
        btnSave.setCompoundDrawablesRelativeWithIntrinsicBounds(saveImage, null, null, null);
        btnSave.setPadding(380, 30, 380, 30);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)

    private void setUpSummaryButton() {
        btnSummary.setText("");
        Drawable summeryImage = getResources().getDrawable(R.drawable.summession_b);
        btnSummary.setCompoundDrawablesRelativeWithIntrinsicBounds(summeryImage, null, null, null);

        /**
         * calculate padding in pixel
         */
        int leftPadd, rightPadd, topPadd, bottomPadd;
        CalculationPadding calPadd = new CalculationPadding();

        leftPadd = rightPadd = calPadd.calculateViewPadding(mContext, summeryImage, btnSummary);
        /**
         * set the value in resource
         */
        topPadd = bottomPadd = getResources().getInteger(R.integer.top_bottom_icon_pad_int_5);

        btnSummary.setPadding(leftPadd, topPadd, rightPadd, bottomPadd);
    }

    /**
     * Icon set by the method
     */

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpGpsButton() {

        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        /**
         * calculate padding in pixel
         */
        int leftPadd, rightPadd, topPadd, bottomPadd;
        CalculationPadding calPadd = new CalculationPadding();

        leftPadd = rightPadd = calPadd.calculateViewPadding(mContext, imageHome, btnHome);
        /**
         * set the value in resource
         */
        topPadd = bottomPadd = getResources().getInteger(R.integer.top_bottom_icon_pad_int_5);

        btnHome.setPadding(leftPadd, topPadd, rightPadd, bottomPadd);

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


    private void goToHomePage() {
        finish();
        intent = new Intent(DistributionActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * LOAD :: Award
     */
    private void loadAward(final String idCountry) {


        SpinnerLoader.loadAwardLoader(mContext, sqlH, spAward, idCountry, idAward, strAward);
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

                Log.d(TAG, "idAward : " + idAward + " donor id :" + idAward.substring(0, 2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Award Spinner

    /**
     * LOAD :: Program
     */
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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * LOAD :: Criteria
     */
    private void loadFDP(final String cCode, final String layR2Code) {

        int position = 0;

        String criteria = SQLiteQuery.getFDPNames_Where_Condition(cCode, layR2Code);
        List<SpinnerHelper> fdpList = sqlH.getListAndID(STAFF_FDP_ACCESS_TABLE, criteria, null, false);
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

                         // for loading time
/*                    LoadingList ld = new LoadingList(idCountry, idDonor, idAward, idProgram, idServiceMonth, idFDP, idDisMonth, "");
                    ld.execute();*/

                    /**                     * For test*/


                 /*   loadDistributionListView(idCountry, idDonor, idAward, idProgram, idServiceMonth, idFDP, idDisMonth, "");*/
                }
                Log.d(TAG, "FDPCode :" + idFDP + " FDPName :" + strFDP);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * LOAD:: DistributionType
     */
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


        /*** Experiments*/

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

    /**
     * LOAD:: ServiceMonth
     */

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


                }

                Log.d(TAG, "idServiceMonth : " + idServiceMonth + " strServiceMonth :" + strServiceMonth);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * ** LOAD:: DistributionMonth
     */

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



    private void loadLayR2List(String cCode) {
        int position = 0;
        String criteria =
                " Select DISTINCT  " + UPAZILLA_TABLE + "." + LAY_R1_LIST_CODE_COL + " || " + UPAZILLA_TABLE + "." + LAY_R2_LIST_CODE_COL + " AS code "
                        + " , " + UPAZILLA_TABLE + " ." + UPZILLA_NAME_COL + " AS Name "
                        + " FROM  " + STAFF_FDP_ACCESS_TABLE
                        + "  INNER JOIN         " + FDP_MASTER_TABLE
                        + "   ON         " + STAFF_FDP_ACCESS_TABLE + "." + STAFF_FDP_ACCESS_COUNTRY_CODE + " = " + FDP_MASTER_TABLE + "." + ADM_COUNTRY_CODE_COL
                        + "   AND         " + STAFF_FDP_ACCESS_TABLE + "." + FDP_CODE_COL + " = " + FDP_MASTER_TABLE + "." + FDP_CODE_COL
                        + "   INNER JOIN    " + UPAZILLA_TABLE
                        + "   ON    " + STAFF_FDP_ACCESS_TABLE + "." + STAFF_FDP_ACCESS_COUNTRY_CODE + " = " + UPAZILLA_TABLE + "." + ADM_COUNTRY_CODE_COL
                        + "   AND   " + FDP_MASTER_TABLE + "." + FDP_MASTER_COUNTRY_CODE + " = " + UPAZILLA_TABLE + "." + ADM_COUNTRY_CODE_COL
                        + "   AND   " + FDP_MASTER_TABLE + "." + FDP_MASTER_LAY_R1_LIST_CODE_COL + " = " + UPAZILLA_TABLE + "." + LAY_R1_LIST_CODE_COL
                        + "   AND   " + FDP_MASTER_TABLE + "." + FDP_MASTER_LAY_R2_LIST_CODE_COL + " = " + UPAZILLA_TABLE + "." + LAY_R2_LIST_CODE_COL

                        + " INNER JOIN " + SELECTED_FDP_TABLE + " ON "
                        + STAFF_FDP_ACCESS_TABLE + "." + STAFF_FDP_ACCESS_COUNTRY_CODE + " = " + SELECTED_FDP_TABLE + "." + ADM_COUNTRY_CODE_COL
                        + " AND " + STAFF_FDP_ACCESS_TABLE + "." + FDP_CODE_COL + " = " + SELECTED_FDP_TABLE + "." + FDP_CODE_COL+

        " WHERE " + STAFF_FDP_ACCESS_TABLE + "." + STAFF_FDP_ACCESS_COUNTRY_CODE + " = '" + idCountry + "'"
                + " AND " + STAFF_FDP_ACCESS_TABLE + "." + SQLiteHandler.STAFF_CODE + " = '" + getStaffID() + "'"
                + " AND " + STAFF_FDP_ACCESS_TABLE + "." + SQLiteHandler.BTN_NEW_COL + " = '1'"
                + " ORDER BY  "+UPAZILLA_TABLE + "." + LAY_R2_LIST_CODE_COL ;


        List<SpinnerHelper> listUpazilla = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, cCode, false);
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


                if (idUP.length() > 2) {
                    loadFDP(idCountry, idUP);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * LOAD :: GRID THREAD
     * <p/>
     * While load data to the adapter
     */
    private class LoadingList extends AsyncTask<Void, Integer, String> {


        private String country, donor, award, program, serviceMonth, fDP, disMonth, memSearch;

        public LoadingList(String country, String donor, String award, String program, String serviceMonth, String fDP, String disMonth, String memSearch) {
            this.country = country;
            this.donor = donor;
            this.award = award;
            this.serviceMonth = serviceMonth;
            this.fDP = fDP;
            this.disMonth = disMonth;
            this.memSearch = memSearch;
            this.program = program;
        }

        @Override
        protected String doInBackground(Void... params) {


                loadDistributionListView(country, donor, award, program, serviceMonth, fDP, disMonth, memSearch);

            return "sucess";


            //return "";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            startProgressBar(mContext.getResources().getString(R.string.loading_msg));
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
            } else
                btnSave.setVisibility(View.VISIBLE);


            if (adapter != null) {
                adapter.notifyDataSetChanged();
                mListView.setAdapter(adapter);


                if (adapter.getCount() == 0) {
                    mDialog.showInfromDialog(mContext, "No Data", "No Data found");
                }
            } else {
                mDialog.showInfromDialog(mContext, "No Data", "No Data found");
            }

        }
    }

    private void startProgressBar(String msg) {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage(msg);
        pDialog.setCancelable(false);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        pDialog.show();
    }

    /**
     * LOAD :: Distribution List
     */
    public void loadDistributionListView(String cCode, String donorCode, String awardCode, String progCode, String srvOpMCode, String fdpCode, String distOpMCode, String mem) {

        // use veriable to like operation
        List<DistributionGridDataModel> distributedListData = sqlH.getDistributionDataList(cCode, donorCode, awardCode, progCode, srvOpMCode, fdpCode, mem);


      /*  if (distributedListData.size() != 0) {*/
           /* distributedArray.clear();
            for (DistributionGridDataModel data : distributedListData) {

                distributedArray.add(data);
            }*/

            adapter = new DistributionDataListAdapter(this, distributedListData, distOpMCode, fdpCode, idProgram, idServiceMonth);

   /*     } else {
            *//** clean the list view *//*
            distributedArray.clear();
            adapter = new DistributionDataListAdapter(this, distributedArray, distOpMCode, fdpCode, idProgram, idServiceMonth);


        }*/
//        mListView.setAdapter(adapter);
//
    }


    private void saveDistributionData() {

        boolean invalid = false;

        if (invalid) {
            invalid = true;
            mDialog.showErrorDialog(mContext, "Please select a Date");
        } else if (adapter == null) {
            mDialog.showErrorDialog(mContext, "Invalid Attempt to save");
        } else {

            List<DistributionGridDataModel> alist = new ArrayList<DistributionGridDataModel>();
            alist = adapter.distributData;
            Log.d(TAG, "distributData size " + alist.size());
            /** set  if condition */

            if (adapter.distributData.isEmpty()) {

                Toast.makeText(getApplicationContext(), "No date is selected  a valid date! Please select data!!", Toast.LENGTH_LONG).show();
            } else {
                int count = 0;

                try {
                    String EntryBy = getStaffID();

                    String EntryDate = getDateTime();


                    for (int i = 0; i < mListView.getAdapter().getCount(); i++) {
                        if (mChecked.get(i)) {
                            DistributionGridDataModel temd = alist.get(i);
                            DistributionSaveDataModel distDataP = convertGridDataIntoSaveDataModel(temd);

                            distDataP.setEntryBy(EntryBy);
                            distDataP.setEntryDate(EntryDate);
                            distDataP.setSrvOpMonthCode(idServiceMonth);
                            distDataP.setDistFlag(idDistributionType);

                            long id = sqlH.addInDistributionTable(distDataP);


                            /** upload syntax*/
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
                            distributedData.setWD(distDataP.getWd());
                            distributedData.setEntryBy(EntryBy);
                            distributedData.setEntryDate(EntryDate);
                            sqlH.insertIntoUploadTable(distributedData.insertIntoDistributionTable());


                            count++;
                        }
                    }

                    Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();


                    LoadingList ld = new LoadingList(idCountry, idDonor, idAward, idProgram, idServiceMonth, idFDP, idDisMonth, "");
                    ld.execute();
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
            Log.d(TAG, "Count all  : " + count);
            //}
        }// end of else


    }// end of save method


    private DistributionSaveDataModel convertGridDataIntoSaveDataModel(DistributionGridDataModel gridData) {
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
        dPeople.setOpMonthCode(gridData.getDistOpMonthCode());// send Distre butaion of monthe Code
        dPeople.setFDPCode(gridData.getFdpCode());
        dPeople.setID(gridData.getnMId());
        dPeople.setDistStatus(gridData.getStatus());
        dPeople.setWd(gridData.getWd());
        return dPeople;
    }


    /**
     * Set Adapter class here
     */
    public class DistributionDataListAdapter extends BaseAdapter {
        private Activity activity;
        public List<DistributionGridDataModel> distributData;
        private LayoutInflater inflater;
        private String distOpMonthCode;
        private String fdpCode;
        private String progCode;
        private SQLiteHandler sqH;
        private String serviceMonthCode;
        // when the check box is checked that particular id will save in this ArrayList
        private ArrayList<DistributionSaveDataModel> distListOFWant2Save = new ArrayList<DistributionSaveDataModel>();

        viewHolder holder;

        public DistributionDataListAdapter(Activity activity, List<DistributionGridDataModel> distributData, String distOpMonthCode, String fdpCode, String progCode, String serviceMonthCode) {
            this.distributData = distributData;
            this.activity = activity;
            this.distOpMonthCode = distOpMonthCode;
            this.fdpCode = fdpCode;
            this.progCode = progCode;
            sqH = new SQLiteHandler(activity);
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

                holder.rptID = (TextView) row.findViewById(R.id.tv_dist_mem_row_newId);
                holder.rptName = (TextView) row.findViewById(R.id.txt_dis_mem_row_name);
                holder.memSrvShortName = (TextView) row.findViewById(R.id.tv_dis_row_mem_criteriaShortName);
                holder.memDistStatus = (TextView) row.findViewById(R.id.dis_mem_row_tv_DistStatus);
                holder.imgVoucher = (ImageButton) row.findViewById(R.id.ibtn_voucher_dist_holder);
                holder.imgDelete = (ImageButton) row.findViewById(R.id.delete_distribution_rpt);
                row.setTag(holder);

            } else {
                holder = (viewHolder) row.getTag();
            }


            holder.rptID.setText(disPeople.getRpt_id());
            holder.rptName.setText(disPeople.getRpt_name());
            holder.memSrvShortName.setText(disPeople.getServiceShortName());


            String memDistStatus = sqH.getDistributionStatusFromDistributionTable(disPeople.getC_code(), disPeople.getDonorCode(), disPeople.getAwardCode(), disPeople.getDistrictCode(), disPeople.getUpazillaCode(), disPeople.getUnitCode(), disPeople.getVillageCode(), disPeople.getProgram_code(), disPeople.getService_code(), distOpMonthCode, fdpCode, idDistributionType, disPeople.getRpt_id());
        /*    Log.d("All", disPeople.getRpt_id() + " Name :" + disPeople.getRpt_name() +
                    "\n Service Code:" + disPeople.getService_code() + "\n memDistStatus:" + memDistStatus
            );*/


            holder.memDistStatus.setText(memDistStatus);
         /*   Log.d(TAG, "mem dist status :" + memDistStatus + " position : " + position);*/
            /** For 2nd Check Box */


            //  the hh check box reference is define here
            final CheckBox cbMemberId_holder = (CheckBox) row.findViewById(R.id.cb_dis_mem_id_holde);
            // set checked change listener
            cbMemberId_holder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                   /* Saving Checked Position                            */
                    mChecked.put(position, isChecked);


                    // Find if all the check boxes are true

                    if (isAllValuesChecked()) {


                        /**
                         *  set HeaderCheck box to true
                         */

                        checkBox_header.setChecked(isChecked);

                    }


                    int getPosition = (Integer) buttonView.getTag();

                    /**
                     * Get the object of specific row & set the value of the
                     * save the checkbox of that particular state .
                     * */
                    getDistributedPerson(getPosition).setCheckBoxMember(isChecked);


                }
            });
            // set the sate of particular positioned check box

            cbMemberId_holder.setTag(position);
            // if master check box is clicked than all the check box of member will be selected
            // other wise it will checked
            if (checkBox_header.isChecked()) {
                cbMemberId_holder.setChecked((mChecked.get(position)));
            } else {

                cbMemberId_holder.setChecked(false);
            }

            cbMemberId_holder.setChecked((mChecked.get(position)));
            // Log.d(TAG, " position " + position + " the check box Member  " + cbMemberId_holder.isChecked());
          /*  if (progCode.equals("002") || disPeople.getService_code().equals("05")) {

                holder.rptID.setText(disPeople.getRpt_id());
                holder.rptName.setText(disPeople.getRpt_name());
                holder.memSrvShortName.setText("HHR");

                String hhDistStatus = sqH.getDistributionStatusFromDistributionTable(disPeople.getC_code(), disPeople.getDonorCode(), disPeople.getAwardCode(), disPeople.getDistrictCode(), disPeople.getUpazillaCode(), disPeople.getUnitCode(), disPeople.getVillageCode(), disPeople.getProgram_code(), disPeople.getService_code(), distOpMonthCode, fdpCode, disPeople.getRpt_id());
                Log.d("In Adapter Dist", " hh dist status :" + hhDistStatus);
                holder.memDistStatus.setText(hhDistStatus);
                disPeople.setnMId(disPeople.getRpt_id()); /// if criteria HHR

            } else {
                disPeople.setnMId(disPeople.getRpt_id());
            }*/


            disPeople.setnMId(disPeople.getRpt_id());
            disPeople.setDistOpMonthCode(distOpMonthCode);// send Distrebutaion of monthe Code
            disPeople.setFdpCode(fdpCode);

            disPeople.setStatus("R");


            if (position % 2 == 0) {
                row.setBackgroundColor(Color.WHITE);
                changeTextColor(activity.getResources().getColor(R.color.blue));
            } else {
                row.setBackgroundColor(activity.getResources().getColor(R.color.list_divider));
                changeTextColor(activity.getResources().getColor(R.color.black));
            }


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


                  /*  personToBeServiced.setServiceCenterCode(serviceCenterCode);
                    personToBeServiced.setServiceDTCode(serviceDate);*/
                    // send the Service data object to the ServiceVoucherDetails
                    intent.putExtra(KEY.DISTRIBUTION_DATA_OBJECT_KEY, disPeople);
                    activity.startActivity(intent);
                }
            });


            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
                    alertDialog.setTitle("Confirm Delete...");
                    alertDialog.setMessage("Are you sure to delete this Member?");
                    alertDialog.setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int selectedID) {

                                    if (sqlH.getDistIsprepare(disPeople.getC_code(), disPeople.getDonorCode(),
                                            disPeople.getAwardCode(), disPeople.getProgram_code(), "3", idServiceMonth, idDisMonth, idFDP)) {
                                        Toast.makeText(activity, "ERROR: Distribution Plan already prepared. Delete attempt denied.", Toast.LENGTH_LONG).show();
                                    } else {
                                        DistributionSaveDataModel dsM = new DistributionSaveDataModel();


                                        holder.memDistStatus.setText("-");
                                        notifyDataSetChanged();
                                        dsM.setCountryCode(disPeople.getC_code());
                                        dsM.setAdmAwardCode(disPeople.getAwardCode());
                                        dsM.setAdmDonorCode(disPeople.getDonorCode());
                                        dsM.setDistrictCode(disPeople.getDistrictCode());
                                        dsM.setUpCode(disPeople.getUpazillaCode());
                                        dsM.setUniteCode(disPeople.getUnitCode());
                                        dsM.setVillageCode(disPeople.getVillageCode());
                                        dsM.setProgCode(disPeople.getProgram_code());
                                        dsM.setSrvCode(disPeople.getService_code());

                                        dsM.setID(disPeople.getnMId());

                                        dsM.setOpMonthCode(disPeople.getDistOpMonthCode());
                                        /***  delete for of line  */


                                        Log.d("NIR", " for  Distribution Delete data :" +
                                                " dsM.getCountryCode() :" + dsM.getCountryCode() +
                                                " dsM.getAdmAwardCode() :" + dsM.getAdmAwardCode() +
                                                " dsM.getAdmDonorCode() ;" + dsM.getAdmDonorCode() +
                                                " dsM.getDistrictCode() ;" + dsM.getDistrictCode() +
                                                " dsM.getUpCode() :" + dsM.getUpCode() +
                                                " dsM.getUniteCode() :" + dsM.getUniteCode() +
                                                " dsM.getVillageCode() :" + dsM.getVillageCode() +
                                                " dsM.getProgCode() :" + dsM.getProgCode() +
                                                " dsM.getSrvCode() :" + dsM.getSrvCode() +
                                                " dsM.disPeople.setnMId() :" + disPeople.getnMId());

                                        sqH.deleteDistribution(dsM);

                                        SQLServerSyntaxGenerator deleteRPT = new SQLServerSyntaxGenerator();


                                        deleteRPT.setAdmCountryCode(disPeople.getC_code());
                                        deleteRPT.setAdmAwardCode(disPeople.getAwardCode());
                                        deleteRPT.setAdmDonorCode(disPeople.getDonorCode());
                                        deleteRPT.setLayR1ListCode(disPeople.getDistrictCode());
                                        deleteRPT.setLayR2ListCode(disPeople.getUpazillaCode());
                                        deleteRPT.setLayR3ListCode(disPeople.getUnitCode());
                                        deleteRPT.setLayR4ListCode(disPeople.getVillageCode());
                                        deleteRPT.setProgCode(disPeople.getProgram_code());
                                        deleteRPT.setSrvCode(disPeople.getService_code());
                                        deleteRPT.setID(disPeople.getnMId());
                                        deleteRPT.setOpMonthCode(disPeople.getDistOpMonthCode());
                                        sqH.insertIntoUploadTable(deleteRPT.deleteMemberFromDistTable());
                                        disPeople.setStatus("-");
                                        holder.memDistStatus.setText("-");
                                        // distributData.remove(position);
                                        Log.d("MOR", deleteRPT.deleteMemberFromDistTable());
                                        Toast.makeText(activity, "Deleted successfully!", Toast.LENGTH_LONG).show();
                                        notifyDataSetChanged();


                                    }


                                }
                            });

                    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();


//                    Toast.makeText(activity, "delete", Toast.LENGTH_SHORT).show();

                }
            });

            return row;
        } // end of get view

        private void changeTextColor(int color) {
            holder.rptID.setTextColor(color);
            holder.rptName.setTextColor(color);
            holder.memSrvShortName.setTextColor(color);
            holder.memDistStatus.setTextColor(color);

        }


        public ArrayList<DistributionSaveDataModel> getArrayList() {
            return distListOFWant2Save;
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

        class viewHolder {


            TextView rptID;
            TextView rptName;
            TextView memSrvShortName;
            TextView memDistStatus;

            ImageButton imgVoucher;
            ImageButton imgDelete;


        }
    }// end of adapter class


}
