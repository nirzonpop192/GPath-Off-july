package com.siddiquinoor.restclient.activity.sub_activity.summary_sub;
/**
 * Created by Faisal on 9/5/2016.
 * This Activity show the Group SumRegLay4TotalHHRecords List number of people Assigned in  Group
 * Group Name	Category (Short Name)	Short Name (SrvCode)	Count
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.SummaryMenuActivity;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.SummaryGroupListAdapter;
import com.siddiquinoor.restclient.views.adapters.SummaryGroupListDataModel;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.util.ArrayList;
import java.util.List;

public class GroupSummary extends BaseActivity /*implements AdapterView.OnItemClickListener*/ {
    /**
     * buttons of the page.
     * btnBack is used to back to {@link SummaryMenuActivity} page
     * btnHome  is used to  goto {@link com.siddiquinoor.restclient.activity.MainActivity} page
     */
    private Button btnBack, btnHome;

    /**
     * mListView is UI view which will show the data to user (group)
     */
    private ListView mListView;

    /**
     * layR3 label
     */
    private TextView tv_lay3Title;

    /**
     * mContext is GroupSummary class Context class
     */
    private final Context mContext = GroupSummary.this;

    /**
     * idCountry is global country code
     */
    private String idCountry;

    /**
     * data base instance
     */
    private SQLiteHandler sqlH;

    /**
     * mAdapter is custom made list view adapter.
     * {@link SummaryGroupListAdapter} class object
     */
    private SummaryGroupListAdapter mAdapter;

    /**
     * mGroupListA is Custom List Array of {@link SummaryGroupListDataModel} class
     */
    private ArrayList<SummaryGroupListDataModel> mGroupListA = new ArrayList<SummaryGroupListDataModel>();

    /**
     * mDialog is Custom Dialog manager
     */
    private ADNotificationManager mDialog;

    /**
     * pDialog is progress bar dialog . which is only used in {@link LoadListView} class .
     * it's works as loader
     */
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_summary);
        initial();

        setAllListener();

        // anonymous class object
        new LoadListView(idCountry).execute();
// for test purpose
//        loadGroupList(idCountry);

    }

    private void initial() {
        viewReference();

        // initiate the db instance
        sqlH = new SQLiteHandler(mContext);

        // initiate the Dialog Manager
        mDialog = new ADNotificationManager();

        // get country Code from db
        idCountry = sqlH.getSelectedCountryCode();

        // get the layR3 Label  name from db
        tv_lay3Title.setText(sqlH.getLayerLabel(idCountry, "3"));
    }

    /**
     *  button listener
     */
    private void setAllListener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSummaryActivity((Activity) mContext, idCountry);

            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity((Activity) mContext);

            }
        });
    }

    /**
     * refer the non java object into java object
     */
    private void viewReference() {
        btnBack = (Button) findViewById(R.id.btnRegisterFooter);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        mListView = (ListView) findViewById(R.id.list_group_records);
        tv_lay3Title = (TextView) findViewById(R.id.list_title_LayR3Name);
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
        setUpHomeButton();
        setUpBackPressButton();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpBackPressButton() {
        btnBack.setText("");
        Drawable backImage = getResources().getDrawable(R.drawable.goto_back);
        btnBack.setCompoundDrawablesRelativeWithIntrinsicBounds(backImage, null, null, null);
        setPaddingButton(mContext, backImage, btnBack);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpHomeButton() {
        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, btnHome);
    }


    /**
     * this method get the list of group and assigned member total number from db .
     * set into the {@link #mAdapter} to load into the list view .
     * @param cCode country code
     * @since 2016-10-17
     */
    public void loadGroupList(String cCode) {


        // use variable to like operation
        List<SummaryGroupListDataModel> assignList = sqlH.getGroupSummaryList(cCode);
        if (assignList.size() != 0) {
//            mGroupListA.clear();
//            for (SummaryGroupListDataModel data : assignList) {
//                // add contacts data in arrayList
//                mGroupListA.add(data);
//
//            }

           mAdapter = new SummaryGroupListAdapter((Activity) mContext, assignList);
//            // leave it for test purpose
//            mAdapter.notifyDataSetChanged();
//            mListView.setAdapter(mAdapter);
//        //    mListView.setOnItemClickListener(this);
//            mListView.setFocusableInTouchMode(true);

        }
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//    }

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
     * AsyncTask enables proper and easy use of the UI thread. This class allows you to perform
     * ( get the group Array list from db  )
     * background operations and publish results on the UI thread  without having to manipulate threads and/or handlers.
     */
    private class LoadListView extends AsyncTask<Void, Integer, String> {
        String temCountryCode;

        /**
         *  constructor
         * @param temCountryCode country Code
         */
        private LoadListView(String temCountryCode) {
            this.temCountryCode = temCountryCode;
        }

        @Override
        protected String doInBackground(Void... params) {
            loadGroupList(temCountryCode);
            return "success";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            startProgressBar(mContext.getResources().getString(R.string.loading_msg));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (pDialog.isShowing())
                pDialog.dismiss();


            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
                mListView.setAdapter(mAdapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SummaryGroupListDataModel data = (SummaryGroupListDataModel) mAdapter.getItem(position);
                        Intent intent = new Intent(mContext, GroupMemberSummary.class);
                        intent.putExtra(KEY.COMMUNITY_GRP_DATA_OBJECT_KEY, data);
                        finish();
                        startActivity(intent);
                    }
                });
                mListView.setFocusableInTouchMode(true);

            } else {
                mDialog.showInfromDialog(mContext, "No Data", "");
                // Log.d("MAL", "Adapter Is Empty ");

            }
        }
    }
}
