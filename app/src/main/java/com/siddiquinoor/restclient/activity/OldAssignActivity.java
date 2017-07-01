package com.siddiquinoor.restclient.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
//import com.siddiquinoor.restclient.custom.AsyncDelegate;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.data_model.adapters.AssignDataModel;
import com.siddiquinoor.restclient.views.adapters.AssignDataModelAdapter;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class OldAssignActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private static final String TAG = OldAssignActivity.class.getName();
    AlertDialog goToDialog;
    Intent intent;
    public static final String ID_COUNTRY = "ID_COUNTRY";
    public static final String ASSIGN_AWARD_CODE = "ASSIGN_AWARD_CODE";
    public static final String ASSIGN_AWARD_STR = "ASSIGN_AWARD_STR";
    public static final String ASSIGN_DONOR_CODE = "ASSIGN_DONOR_CODE";
    public static final String ASSIGN_PROGRAM_CODE = "ASSIGN_PROGRAM_CODE";
    public static final String ASSIGN_PROGRAM_STR = "ASSIGN_PROGRAM_STR";
    public static final String ASSIGN_CRITERIA_CODE = "ASSIGN_CRITERIA_CODE";
    public static final String ASSIGN_CRITERIA_STR = "ASSIGN_CRITERIA_STR";
    public static final String ASSIGN_VILLAGE_CODE = "ASSIGN_VILLAGE_CODE";
    public static final String ASSIGN_VILLAGE_STR = "ASSIGN_VILLAGE_STR";
    public static final String ASSIGN_DISTRICT_CODE = "ASSIGN_DISTRICT_CODE";
    public static final String ASSIGN_UPZELA_CODE = "ASSIGN_UPZELA_CODE";
    public static final String ASSIGN_UNIT_CODE = "ASSIGN_UNIT_CODE";
    public static final String SUB_ASSIGN_DIR = "Sub_Ass_dir";
    private SQLiteHandler sqlH;
    private Context mcontext;

    private String idCountry;
    private String strAward;
    private String idAward;
    private Spinner spAward;
    private Spinner spProgram;
    private String idProgram;
    private String strProgram;

    private Spinner spCriteria;
    private String idCriteria;
    private String strCriteria;
    private Spinner spVillage;
    private String idVillage;
    private String strVillage;
    private int positionVillage;
    //  private Button btnHome;
    private Button btnSearch;
    private EditText edt_mmSearch;
    String entryBy;
    String entryDate;


    private String idCountryC;
    private String idDistrictC;
    private String idUpazilaC;
    private String idUnitC;
    private String idVillageC;
    private String idService;
    private String idDonor;
    private ArrayList<AssignDataModel> assignedArray = new ArrayList<AssignDataModel>();
    ListView listViewAss;
    private AssignDataModelAdapter adapter;

    private ProgressDialog pDialog;
    ADNotificationManager dialog;

    /**
     * fotter button important
     */
    private Button btnGoTo;
    private Button btnSummary;

    private boolean mredirection;
    private TextView tvLayR4Lable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_assign);
        mcontext = OldAssignActivity.this;
        dialog = new ADNotificationManager();

        viewReference();

        btnSummary.setOnClickListener(this);

        sqlH = new SQLiteHandler(this);

        pDialog = new ProgressDialog(this);
        btnSearch.setOnClickListener(this);
        btnSearch.setEnabled(false);
        edt_mmSearch.setOnClickListener(this);

        btnGoTo.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "in on Start method ");

        // in onCreate() it save Instance

        Intent intent = getIntent();
        boolean redirection;

        redirection = intent.getBooleanExtra("Ass_DIR", false);
        mredirection = redirection;
        // if it Comes from Sub page Of
        if (redirection) {

            getDataFromIntent(intent);

            loadAward(idCountry);
            tvLayR4Lable.setText(UtilClass.getLayR4LabelName(mcontext, idCountry));


        } else {
            idCountry = intent.getStringExtra(KEY.COUNTRY_ID);
            loadAward(idCountry);
            String id=intent.getStringExtra(KEY.MEMBER_ID);
            edt_mmSearch.setText(id);
            String temId = edt_mmSearch.getText().toString().trim();
         /*   if (!temId.equals("") && temId.length() > 0) {

                LoadListView loader = new LoadListView(idCountryC, idDistrictC, idUpazilaC, idUnitC, idVillageC, idDonor, idAward, idProgram, idService, temId);
                loader.execute();
            }*/
        }
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void getDataFromIntent(Intent mIntent) {

        strAward = mIntent.getStringExtra(ASSIGN_AWARD_STR);
        strProgram = mIntent.getStringExtra(ASSIGN_PROGRAM_STR);
        strCriteria = mIntent.getStringExtra(ASSIGN_CRITERIA_STR);
        strVillage = mIntent.getStringExtra(ASSIGN_VILLAGE_STR);
        idCountry = mIntent.getStringExtra(ID_COUNTRY);
        idAward = mIntent.getStringExtra(ASSIGN_AWARD_CODE);
        idDonor = mIntent.getStringExtra(ASSIGN_DONOR_CODE);
        idProgram = mIntent.getStringExtra(ASSIGN_PROGRAM_CODE);
        idCriteria = mIntent.getStringExtra(ASSIGN_CRITERIA_CODE);
        idDistrictC = mIntent.getStringExtra(ASSIGN_DISTRICT_CODE);
        idUpazilaC = mIntent.getStringExtra(ASSIGN_UPZELA_CODE);
        idUnitC = mIntent.getStringExtra(ASSIGN_UNIT_CODE);
        idVillage = mIntent.getStringExtra(ASSIGN_VILLAGE_CODE);


    }

    private void viewReference() {
        spAward = (Spinner) findViewById(R.id.sp_assine_awardList);
        spProgram = (Spinner) findViewById(R.id.spProgram);
        spCriteria = (Spinner) findViewById(R.id.spCriteriaA);
        spVillage = (Spinner) findViewById(R.id.spVillageA);
        tvLayR4Lable = (TextView) findViewById(R.id.tv_assign_ac_layR4Label);

        listViewAss = (ListView) findViewById(R.id.lv_assign);
        btnSearch = (Button) findViewById(R.id.btn_assign_search);
        edt_mmSearch = (EditText) findViewById(R.id.edt_assign_memberSearch);

        btnGoTo = (Button) findViewById(R.id.btnHomeFooter);

        btnGoTo.setOnClickListener(this);
        btnSummary = (Button) findViewById(R.id.btnRegisterFooter);
//        setUpSummaryButton();
//        setUpGotoButton();

    }

/*
    private void setUpSummaryButton() {
        btnSummary.setText("");
        Drawable summeryImage = getResources().getDrawable(R.drawable.summession_b);
        btnSummary.setCompoundDrawablesRelativeWithIntrinsicBounds(summeryImage, null, null, null);
        btnSummary.setPadding(180, 10, 180, 10);
    }

    *//**
     * Icon set by the method
     *//*
    private void setUpGotoButton() {
        btnGoTo.setText("");
        Drawable imageGoto = getResources().getDrawable(R.drawable.goto_forward);
        btnGoTo.setCompoundDrawablesRelativeWithIntrinsicBounds(imageGoto, null, null, null);
        btnGoTo.setPadding(180, 10, 180, 10);
    }*/


    /**
     * change the  button color on  the resume mode
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (!btnSearch.isEnabled()) {
            //  btnSearch.setTextColor(getResources().getColor(R.color.gray));
        }


        Intent intent = getIntent();
        boolean tem = intent.getBooleanExtra(SUB_ASSIGN_DIR, false);
        boolean redirection = getIntent().getExtras().getBoolean(SUB_ASSIGN_DIR);
        mredirection = redirection;
        // if it comes from sub page of Assigne
        if (redirection) {
            getDataFromIntent(getIntent());

            loadAward(idCountry);
            LoadListView load = new LoadListView(idCountry, idDistrictC, idUpazilaC, idUnitC, idVillage, idDonor, idAward, idProgram, idCriteria, "");
            load.execute();

        }


       /* else
            btnSearch.setTextColor(getResources().getColor(R.color.green));*/
    }

    /**
     * Button to click
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_assign_search:
                //Toast.makeText(AssignActivity.this,"button is pressed ",Toast.LENGTH_SHORT).show();

                //  set the condition for user must select all the spinner field
                String temId = edt_mmSearch.getText().toString().trim();
                if (!temId.equals("") && temId.length() > 0) {

                    LoadListView loader = new LoadListView(idCountryC, idDistrictC, idUpazilaC, idUnitC, idVillageC, idDonor, idAward, idProgram, idService, temId);
                    loader.execute();
                }
                break;
            case R.id.btnHomeFooter:
                //Intent iHome = new Intent(mcontext, MainActivity.class);
                // startActivity(iHome);
                goToAlert();
                break;
            case R.id.btnRegisterFooter:
                Intent iSummary = new Intent(mcontext, SummaryMenuActivity.class);
                iSummary.putExtra(KEY.COUNTRY_ID, idCountry);
                startActivity(iSummary);
                break;
            case R.id.edt_assign_memberSearch:
                showSoftKeyboard(edt_mmSearch);
                // Toast.makeText(mcontext," edit text click ",Toast.LENGTH_SHORT).show();
                break;

        }

    }

    /**
     * LOAD :: Award
     */
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

                // String donorId=idAward.substring(0,1)
                //     if (!mredirection){
                String idDonor = idAward.substring(0, 2);
                loadProgram(idCountry, idAward.substring(2), idDonor);
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
    private void loadProgram(final String cCode, final String awardCode, final String donorCode) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.ADM_AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.ADM_DONOR_CODE_COL + "='" + donorCode + "'";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.COUNTRY_PROGRAM_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listProgram);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

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

                Log.d(TAG, "idProgram: " + idProgram + "strProgram: " + strProgram);

                loadCriteria(awardCode, donorCode, idProgram, cCode);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    /**
     * LOAD :: Criteria
     */
    private void loadCriteria(final String idAward, final String donorId, final String idProgram, final String cCode) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.ADM_AWARD_CODE_COL + "='" + idAward + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.ADM_DONOR_CODE_COL + "='" + donorId + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.ADM_PROG_CODE_COL + "='" + idProgram + "'";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listCriteria = sqlH.getListAndID(SQLiteHandler.SERVICE_MASTER_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listCriteria);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spCriteria.setAdapter(dataAdapter);


        if (idCriteria != null) {
            for (int i = 0; i < spCriteria.getCount(); i++) {
                String criteriaN = spCriteria.getItemAtPosition(i).toString();
                if (criteriaN.equals(strCriteria)) {
                    position = i;
                }
            }
            spCriteria.setSelection(position);
        }


        spCriteria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCriteria = ((SpinnerHelper) spCriteria.getSelectedItem()).getValue();
                idCriteria = ((SpinnerHelper) spCriteria.getSelectedItem()).getId();

                /**
                 * @confuse : WTF what I have done */
                if (Integer.parseInt(idCriteria) > 0) {
                    loadLayRList(cCode, idAward, donorId, idProgram, idCriteria);// idService=idCriteria


                }


                Log.d(TAG, "load idCriteria id: " + idCriteria + " Critrei a name: " + strCriteria);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    private void loadLayRList(final String cCode, final String awardCode, final String donorCode, final String programCode, final String serviceCode) {
        int position = 0;
        //  String criteria=" WHERE "+SQLiteHandler.ADM_COUNTRY_CODE_COL +" = '"+idcCode+"' ";
        String criteria = " AS v   INNER JOIN " + SQLiteHandler.SELECTED_VILLAGE_TABLE + " as S "
                + " ON S." + SQLiteHandler.LAYER_CODE_COL + "  = v." + SQLiteHandler.ADM_COUNTRY_CODE_COL
                + " ||''|| v." + SQLiteHandler.LAY_R_LIST_CODE_COL
                + " ||''|| v." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " ||''|| v." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " ||''|| v." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " ";

        List<SpinnerHelper> listVillage = sqlH.getListAndID(SQLiteHandler.VILLAGE_TABLE_FOR_ASSIGN, criteria, idCountry, false);

        // Creating adapter for spinner
        final ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listVillage);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spVillage.setAdapter(dataAdapter);
        //dataAdapter.notifyDataSetChanged();
        if (idVillage != null) {
            for (int i = 0; i < spVillage.getCount(); i++) {
                String village = spVillage.getItemAtPosition(i).toString();
                if (village.equals(strVillage)) {
                    position = i;
                }
            }
            spVillage.setSelection(position);
        }

        spVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strVillage = ((SpinnerHelper) spVillage.getSelectedItem()).getValue();
                idVillage = ((SpinnerHelper) spVillage.getSelectedItem()).getId();
                Log.d(TAG, "village id :" + idVillage);
                if (Integer.parseInt(idVillage) > 0) {
                    // after the village is loaded the search button is enable
                    btnSearch.setEnabled(true);
                    //  btnSearch.setTextColor(getResources().getColor(R.color.green));

                    idCountryC = idVillage.substring(0, 4);
                    idDistrictC = idVillage.substring(4, 6);
                    idUpazilaC = idVillage.substring(6, 8);
                    idUnitC = idVillage.substring(8, 10);
                    idVillageC = idVillage.substring(10);
                    idDonor = donorCode;
                    idService = serviceCode;
                    idCountry = cCode;
                    idAward = awardCode;
                    idProgram = programCode;
                    LoadListView loading = new LoadListView(idCountryC, idDistrictC, idUpazilaC, idUnitC, idVillageC, idDonor, idAward, idProgram, idService, "");
                    loading.execute();


                } else {
                    adapter = new AssignDataModelAdapter();
                    adapter.notifyDataSetChanged();
                    listViewAss.setAdapter(adapter);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadAssignedListData(String cCode, String dCode, String upCode, String uCode, String vCode, String donorCode, String awardCode, String progCode, String srvCode, String memSId) { // mwmSId = memeber searchin variable
        //listViewAss.setAdapter(null);
        List<AssignDataModel> assDatalist = sqlH.getListForAssign(cCode, dCode, upCode, uCode, vCode, donorCode, awardCode, progCode, srvCode, memSId);
        // listViewAss.setAdapter(null);
        if (assDatalist.size() != 0) {
            assignedArray.clear();
            for (AssignDataModel asdata : assDatalist) {
                // add contacts data in arrayList

                assignedArray.add(asdata);
            }


            try {
                entryBy = getStaffID();
                entryDate = getDateTime();
            } catch (ParseException pe) {
                pe.printStackTrace();
            }


            adapter = new AssignDataModelAdapter((Activity) OldAssignActivity.this,
                    assignedArray, awardCode, strAward, progCode, strProgram,
                    srvCode, idDonor, strCriteria, idCriteria,
                    strCriteria,
                    strVillage,
                    entryBy, entryDate);

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     *  Delete the below fromating Code
     */
    private class LoadListView extends AsyncTask<Void, Integer, String> {

        private String mMemberIdS;// for member id search
        private String mICountry;
        private String mIDistrictC;
        private String mIUpazilaC;
        private String mIUnitC;
        private String mIVillageC;
        private String mIDonor;
        private String mIAward;
        private String mIProgram;
        private String mIService;


        public LoadListView(final String mICountry, final String mIDistrictC, final String mIUpazilaC, final String mIUnitC, final String mIVillageC,
                            final String mIDonor, final String mIAward, final String mIProgram, final String mIService, final String mMemberIdS) {
            this.mMemberIdS = mMemberIdS;
            this.mICountry = mICountry;
            this.mIDistrictC = mIDistrictC;
            this.mIUpazilaC = mIUpazilaC;
            this.mIUnitC = mIUnitC;
            this.mIVillageC = mIVillageC;
            this.mIDonor = mIDonor;
            this.mIAward = mIAward;
            this.mIProgram = mIProgram;
            this.mIService = mIService;
        }

        @Override
        protected String doInBackground(Void... params) {


            loadAssignedListData(mICountry, mIDistrictC, mIUpazilaC, mIUnitC, mIVillageC, mIDonor, mIAward, mIProgram, mIService, mMemberIdS);


            return "successes";


        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            startProgressBar("Data is Loading");

        }


        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (adapter != null) {

                adapter.notifyDataSetChanged();
                listViewAss.setAdapter(adapter);

                listViewAss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
                listViewAss.setFocusableInTouchMode(true);
                pDialog.dismiss();
            } else {
                Log.d(TAG, "Adapter Is Empety ");
                pDialog.dismiss();
            }

        }
    }

    private void startProgressBar(String msg) {


        pDialog.setMessage(msg);
        pDialog.setCancelable(false);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        pDialog.show();
    }

    public void goToAlert() {
        final CharSequence[] items = getResources().getStringArray(R.array.assign_got_array);

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(OldAssignActivity.this, android.R.style.Theme_Holo_Light_Dialog));

        builder.setTitle("GO TO:");


        builder.setIcon(R.drawable.navigation_icon);
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        finish();
                        intent = new Intent(OldAssignActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                   /* case 1:
//                        finish();
//                        intent = new Intent(AssignActivity.this,RegisterMemberLiberia.class);
//
//                        startActivity(intent);
                        break;*/
                    case 1:
                        finish();
                        intent = new Intent(OldAssignActivity.this, DistributionActivity.class);
                        intent.putExtra(KEY.DIR_CLASS_NAME_KEY, "AssignActivity");
                        intent.putExtra(KEY.COUNTRY_ID, sqlH.selectCountryCode());
                        startActivity(intent);
                        break;
                    case 2:
                        finish();
                        intent = new Intent(OldAssignActivity.this, ServiceActivity.class);
                        intent.putExtra(KEY.DIR_CLASS_NAME_KEY, "AssignActivity");
                        intent.putExtra(KEY.COUNTRY_ID, sqlH.selectCountryCode());
                        startActivity(intent);
                        break;
                    case 3:
                        finish();
                        intent = new Intent(OldAssignActivity.this, SummaryMenuActivity.class);
                        intent.putExtra(KEY.COUNTRY_ID, sqlH.selectCountryCode());
                        startActivity(intent);
                        break;
                    case 4:
                        finish();
                        intent = new Intent(OldAssignActivity.this, RegisterLiberia.class);
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

}
