package com.siddiquinoor.restclient.activity;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.sub_activity.commu_group_sub.CommunityGroupNDetailsRecodes;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.CommunityGroupAdapter;
import com.siddiquinoor.restclient.data_model.adapters.CommunityGroupDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;
import com.siddiquinoor.restclient.views.spinner.SpinnerLoader;

import java.util.ArrayList;
import java.util.List;

public class GroupSearchPage extends BaseActivity {
    // TODO: 10/18/2016  page reloading problem
    private static final String TAG = GroupSearchPage.class.getSimpleName();
    private SQLiteHandler sqlH;
    private static ProgressDialog pDialog;
    private Button btnAddGroup, btnHome, btn_searchGroup;

    private EditText edt_groupSearch;
    private ListView listView;
    private CommunityGroupAdapter adapter;
    private String idCountry;
    private Spinner spProgram;
    private String strCriteria;
    private String idCriteria;
    private String idAward, idDonor, idProgram;
    private Context mContext;

    /**
     * mDialog is Custom Dialog manager to provide information to user
     */
    private ADNotificationManager mDialog;

    //   private String idService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_search_page);
        initialize();

        idCountry = getIntent().getStringExtra(KEY.COUNTRY_ID);
        loadProgram(idCountry);
        setListener();

    }

    private void setListener() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent iHome = new Intent(GroupSearchPage.this, MainActivity.class);
                startActivity(iHome);
            }
        });

        btn_searchGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchGroupName();
            }
        });

        btnAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupSearchPage.this, CommunityGroupNDetailsRecodes.class);

                intent.putExtra(KEY.ADD_FLAG_KEY, true);
                intent.putExtra(KEY.DONOR_CODE, idDonor);
                intent.putExtra(KEY.AWARD_CODE, idAward);
                intent.putExtra(KEY.PROGRAM_CODE, idProgram);


                startActivity(intent);
            }
        });
    }

    /**
     * this method Execute  LoadListView anonymous object's thread
     */
    private void searchGroupName() {
        String grpName = edt_groupSearch.getText().toString();
        if (grpName.length() == 0) {

            //safety block
            if (idCriteria.length() > 2) {

                // anonymous object
                new LoadListView(idCountry, idDonor, idAward, idProgram, "").execute();
            }
        } else {

            //safety block
            if (idCriteria.length() > 2) {

                // search with group name
                // anonymous object
                new LoadListView(idCountry, idDonor, idAward, idProgram, grpName).execute();
            }
        }
    }

    private void initialize() {
        mContext = GroupSearchPage.this;
        sqlH = new SQLiteHandler(mContext);
        mDialog = new ADNotificationManager();
        viewReference();


    }

    /**
     * LOAD :: Criteria
     *
     * @param cCode Country Code
     */
    private void loadProgram(final String cCode) {

        SpinnerLoader.loadProgramLoader(mContext, sqlH, spProgram, idCriteria, strCriteria, SQLiteQuery.loadProgram_sql(cCode),false,false);


        spProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCriteria = ((SpinnerHelper) spProgram.getSelectedItem()).getValue();
                idCriteria = ((SpinnerHelper) spProgram.getSelectedItem()).getId();
                if (idCriteria.length() > 2) {

                    if (idCriteria.length() > 2) {
                        idDonor = idCriteria.substring(0, 2);
                        idAward = idCriteria.substring(2, 4);
                        idProgram = idCriteria.substring(4, 7);
                        // idService = idCriteria.substring(7);
                        /**                         * for test purpose (to check error )   {@link GroupSearchPage#loadCommunityGroupListData(String, String, String, String, String) method}                   */
                        // loadCommunityGroupListData(idCountry, idDonor, idAward, idProgram, "");
                        LoadListView loading = new LoadListView(idCountry, idDonor, idAward, idProgram, "");
                        loading.execute();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    /**
     * Refer the XML views with java object
     */
    private void viewReference() {
        listView = (ListView) findViewById(R.id.lv_group);
        btnHome = (Button) findViewById(R.id.btnRegisterFooter);
        btnAddGroup = (Button) findViewById(R.id.btnHomeFooter);
        spProgram = (Spinner) findViewById(R.id.search_Group_spCriteria);
        btn_searchGroup = (Button) findViewById(R.id.btn_groupSearch);
        edt_groupSearch = (EditText) findViewById(R.id.edt_groupSearch);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addIconHomeButton() {
        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, btnHome);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addIconAddGroupButton() {
        btnAddGroup.setText("");
        Drawable addImage = getResources().getDrawable(R.drawable.add);
        btnAddGroup.setCompoundDrawablesRelativeWithIntrinsicBounds(addImage, null, null, null);
        setPaddingButton(mContext, addImage, btnAddGroup);
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
        addIconAddGroupButton();
    }

    private class LoadListView extends AsyncTask<Void, Integer, String> {


        private String temCCode;
        private String temDonorCode;
        private String temAwardCode;
        private String temProgCode;
        private String groupName;


        private LoadListView(final String temCCode, final String temDonorCode, final String temAwardCode, final String temProgCode, final String groupName) {

            this.temCCode = temCCode;
            this.temDonorCode = temDonorCode;
            this.temAwardCode = temAwardCode;
            this.temProgCode = temProgCode;
            this.groupName = groupName;
        }

        @Override
        protected String doInBackground(Void... params) {
            loadCommunityGroupListData(temCCode, temDonorCode, temAwardCode, temProgCode, groupName);
            return "successes";
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            startProgressBar(mContext.getResources().getString(R.string.loading_msg));

        }


        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            hideProgressBar();


            if (adapter != null) {

                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
                listView.setFocusableInTouchMode(true);

            } else {
//                Log.d(TAG, "Adapter Is Empety ");
                mDialog.showInfromDialog(mContext, "No Data", "No Data found");
            }

        }
    }

    private void hideProgressBar() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void startProgressBar(String msg) {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage(msg);
        pDialog.setCancelable(true);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.show();
    }

    private void loadCommunityGroupListData(final String cCode, final String donorCode, final String awardCode, final String progCode, final String groupName) { // mwmSId = memeber searchin variable

        List<CommunityGroupDataModel> groupList = sqlH.getCommunityGroupList(cCode, donorCode, awardCode, progCode, groupName);


        ArrayList<CommunityGroupDataModel> groupArray = new ArrayList<CommunityGroupDataModel>();
        if (groupList.size() != 0) {
            groupArray.clear();
            for (CommunityGroupDataModel asdata : groupList) {

                groupArray.add(asdata);
            }
            adapter = new CommunityGroupAdapter((Activity) GroupSearchPage.this, groupArray);
        }
    }


}
