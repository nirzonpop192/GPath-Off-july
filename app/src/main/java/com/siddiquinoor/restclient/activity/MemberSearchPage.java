package com.siddiquinoor.restclient.activity;
/**
 * @autor : Faisal Mohammad
 * This activity is used to navigate the member to Assign an graduation module
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.data_model.adapters.AssignDataModel;
import com.siddiquinoor.restclient.views.adapters.MemberSearchAdapter;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;
import com.siddiquinoor.restclient.views.spinner.SpinnerLoader;


import java.util.ArrayList;
import java.util.List;

public class MemberSearchPage extends BaseActivity {
    private static final String TAG = "MemberSearchPage";
    private SQLiteHandler sqlH;
    private Spinner spVillage;
    private String idVillage;
    private String strVillage;
    private String idCountry;
    private static ProgressDialog pDialog;
    private ListView listOfMember;

    /**
     * mAdapter is Custom Adapter
     */
    private MemberSearchAdapter mAdapter;

    private String idLayR1Code;
    private String idLayR2Code;
    private String idLayR3Code;
    private String idLayR4Code;

    /**
     * Button to go home
     */
    private Button btnHome;

    /**
     * Button to Search Member
     */
    private Button btn_searMem;
    private Button btn_next;
    private Button btn_prev;

    /**
     * edt_memberId is Edit Text which is used to search Id
     */
    private EditText edt_memberId;
    private final Context mContext = MemberSearchPage.this;

    /**
     * mDialog is Custom Dialog manager
     */
    private ADNotificationManager mDialog;

    private TextView tv_title;

    private int NUM_ITEMS_PAGE = 20;
    public int TOTAL_LIST_ITEMS;

    private int pageCount;

    /**
     * Using this increment value we can move the listview items
     */
    private int increment = 0;
    private LinearLayout loutListContoller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_search_page);
        initialize();
        setListener();
    }

    private void setListener() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity((Activity) mContext);
            }
        });

        btn_searMem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMember();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                increment++;
                new LoadListView(idCountry, idLayR1Code, idLayR2Code, idLayR3Code, idLayR4Code, "", strVillage, increment).execute();
                CheckEnable();
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                increment--;
                new LoadListView(idCountry, idLayR1Code, idLayR2Code, idLayR3Code, idLayR4Code, "", strVillage, increment).execute();
                CheckEnable();
            }
        });

    }


    /**
     * Method for enabling and disabling Buttons
     */
    private void CheckEnable() {
        if (increment + 1 == pageCount) {
            btn_next.setEnabled(false);
        } else if (increment == 0) {
            btn_prev.setEnabled(false);
        } else {
            btn_prev.setEnabled(true);
            btn_next.setEnabled(true);
        }
    }

    /**
     * this method Execute  LoadListView anonymous object's thread
     */
    private void searchMember() {
        String idMember = edt_memberId.getText().toString();

        // jodi Edit Text kisu na thake o search korte parbe tokhon sob list asb
        if (idMember.length() == 0) {

            // anonymous object
            new LoadListView(idCountry, idLayR1Code, idLayR2Code, idLayR3Code, idLayR4Code, "", strVillage, increment).execute();
            loutListContoller.setVisibility(View.VISIBLE);
            tv_title.setVisibility(View.VISIBLE);
        } else {
            // search with member id
            // anonymous object
            new LoadListView(idCountry, idLayR1Code, idLayR2Code, idLayR3Code, idLayR4Code, idMember, strVillage, increment).execute();
            loutListContoller.setVisibility(View.GONE);
            tv_title.setVisibility(View.GONE);
        }
    }


    /**
     * initialize the global variable
     */

    private void initialize() {
        sqlH = new SQLiteHandler(MemberSearchPage.this);
        viewReference();

        increment = 0;

        getNumberOfPages();


        mDialog = new ADNotificationManager();

        // get data from  intent data
        Intent intent = getIntent();
        String dir = intent.getStringExtra(KEY.DIR_CLASS_NAME_KEY);
        if (dir.equals("MainActivity")) {
            idCountry = sqlH.getSelectedCountryCode();
            loadLayR4List();
        } else {

            idCountry = intent.getStringExtra(KEY.COUNTRY_ID);
            idVillage = intent.getStringExtra(KEY.VILLAGE_CODE);
            strVillage = intent.getStringExtra(KEY.VILLAGE_NAME);
            loadLayR4List();
        }
    }

    /**
     * this block is for checking the number of pages
     * ====================================================
     */
    private void getNumberOfPages() {
        TOTAL_LIST_ITEMS = sqlH.getMemberCount(idCountry, idLayR1Code, idLayR2Code, idLayR3Code, idLayR4Code, "", increment);
        int val = TOTAL_LIST_ITEMS % NUM_ITEMS_PAGE;
        val = val == 0 ? 0 : 1;
        pageCount = TOTAL_LIST_ITEMS / NUM_ITEMS_PAGE + val;

    }

    /**
     * Refer the XML views with java object
     */
    private void viewReference() {
        spVillage = (Spinner) findViewById(R.id.search_mem_spVillage);
        listOfMember = (ListView) findViewById(R.id.lv_mem_search);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        Button btnSummary = (Button) findViewById(R.id.btnRegisterFooter);
        btnSummary.setVisibility(View.GONE);
        btn_searMem = (Button) findViewById(R.id.btn_memberSearch);
        edt_memberId = (EditText) findViewById(R.id.edt_memberSearch);

        loutListContoller = (LinearLayout) findViewById(R.id.listViewController);
        btn_next = (Button) findViewById(R.id.next);
        btn_prev = (Button) findViewById(R.id.prev);

        tv_title = (TextView) findViewById(R.id.tv_page_title);


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addIconHomeButton() {
        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(null, imageHome, null, null);
//        setPaddingButton(mContext, imageHome, btnHome);
        btnHome.setPadding(-1, 5, -1, 5);
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
        addIconHomeButton();
    }

    /**
     *
     */
    private void loadLayR4List() {

        SpinnerLoader.loadLayR4ListLoader(mContext, sqlH, spVillage, idVillage, strVillage, SQLiteQuery.loadLayR4List_sql());


        spVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strVillage = ((SpinnerHelper) spVillage.getSelectedItem()).getValue();
                idVillage = ((SpinnerHelper) spVillage.getSelectedItem()).getId();
                Log.d(TAG, "village id :" + idVillage);
                if (Integer.parseInt(idVillage) > 5) {
                    // after the village is loaded the search button is enable

                    String countryCode = idVillage.substring(0, 4);
                    idLayR1Code = idVillage.substring(4, 6);
                    idLayR2Code = idVillage.substring(6, 8);
                    idLayR3Code = idVillage.substring(8, 10);
                    idLayR4Code = idVillage.substring(10);

                    getNumberOfPages();
                    LoadListView loading = new LoadListView(countryCode, idLayR1Code, idLayR2Code, idLayR3Code, idLayR4Code, "", strVillage, increment);
                    loading.execute();
                    if (loutListContoller.getVisibility() == View.GONE) {
                        loutListContoller.setVisibility(View.VISIBLE);
                        tv_title.setVisibility(View.VISIBLE);
                    }

                } else {
                    mAdapter = new MemberSearchAdapter();
                    mAdapter.notifyDataSetChanged();
                    listOfMember.setAdapter(mAdapter);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }// end of the village spinner

    /**
     * This Class i used For  Loading List in thread while data loading into view it will show a loader
     * dialog
     */

    private class LoadListView extends AsyncTask<Void, Integer, String> {

        private String temCCode;
        private String temDistCode;
        private String temUpazilaCode;
        private String temUnitCode;
        private String temVillageCode;
        private String memId;
        private String temVillageName;
        private int temNumber;


        private LoadListView(final String temCCode, final String temDistCode, final String temUpazilaCode, final String temUnitCode, final String temVillageCode, final String memId, final String temVillageName, int temNumber) {

            this.temCCode = temCCode;
            this.temDistCode = temDistCode;
            this.temUpazilaCode = temUpazilaCode;
            this.temUnitCode = temUnitCode;
            this.temVillageCode = temVillageCode;
            this.memId = memId;
            this.temVillageName = temVillageName;
            this.temNumber = temNumber;

        }

        @Override
        protected String doInBackground(Void... params) {
            loadAssignedListData(temCCode, temDistCode, temUpazilaCode, temUnitCode, temVillageCode, memId, temVillageName, temNumber);
            return "successes";
        }

        /**
         * Initiate the dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            startProgressBar(mContext.getResources().getString(R.string.loading_msg));

        }


        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            hideProgressBar();

            tv_title.setText("Page " + (temNumber + 1) + " of " + pageCount);
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
                listOfMember.setAdapter(mAdapter);
                listOfMember.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
                listOfMember.setFocusableInTouchMode(true);

            } else {
                Log.d(TAG, "Adapter Is Empty ");
                mDialog.showInfromDialog(mContext, "No Data", "No Data found");
            }

        }
    }

    private void hideProgressBar() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    /**
     * @param msg text massage
     */
    private void startProgressBar(String msg) {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage(msg);
        pDialog.setCancelable(true);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.show();
    }

    /**
     * @param cCode  Country Code
     * @param dCode  LayR1 code
     * @param upCode LayR2 code
     * @param uCode  LayR3 code
     * @param vCode  LayR4 code
     * @param memId  15 digit member id
     */

    private void loadAssignedListData(final String cCode, final String dCode, final String upCode, final String uCode, final String vCode, final String memId, final String vName, int number) {


        int start = number * NUM_ITEMS_PAGE;
        List<AssignDataModel> memberList = sqlH.getMemberList(cCode, dCode, upCode, uCode, vCode, memId, start);


        if (memberList != null && memberList.size() != 0) {

            //Assign the Adapter in list
            mAdapter = new MemberSearchAdapter((Activity) MemberSearchPage.this, memberList, vCode, vName);
        }
    }

    /**
     * to off the back press button
     */
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
