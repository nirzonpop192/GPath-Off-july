package com.siddiquinoor.restclient.activity.sub_activity.summary_sub;

/**
 * Activity for presenting the list of all entry in a list view
 * with image and details
 *
 * @author Faisal Mohammad, Refat Hassan
 * @desc TechnoDhaka.
 * @link
 * @version 1.3.0
 * @since 1.0
 * Created on 3/5/2015
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
import android.widget.ListView;
import android.widget.TextView;

import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.Register;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.SummaryListAdapter;
import com.siddiquinoor.restclient.views.adapters.SummaryModel;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.util.ArrayList;
import java.util.List;

import static com.siddiquinoor.restclient.utils.KEY.VILLAGE_CODE_1;
import static com.siddiquinoor.restclient.utils.KEY.VILLAGE_NAME_1;


public class SumRegLay4TotalHHRecords extends BaseActivity {

    private Button btnReg;
    private Button btnHome;
    private TextView title;
    private SQLiteHandler sqlH;

    private ArrayList<SummaryModel> summaryArr = new ArrayList<SummaryModel>();

    private SummaryListAdapter adapter;
    private ListView listView;
    private String village;
    private String village_code;
    private String TAG = SumRegLay4TotalHHRecords.class.getName();
    private String idCountry;
    private Context mContext;
    private ProgressDialog pDialog;
    /**
     * mDialog is Custom Dialog manager
     */
    private ADNotificationManager mDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set View to register.xml
        setContentView(R.layout.activity_summary);

        mContext = this;
        mDialog = new ADNotificationManager();
        viewReference();
        Intent intent = getIntent();
        idCountry = intent.getStringExtra(KEY.COUNTRY_ID);

        //  Log.d(TAG, " id country : " + idCountry);

        sqlH = new SQLiteHandler(getApplicationContext());

        setListener();


        String layer_title = sqlH.getLayerLabel(getCountryCode(), "4");
        //   Log.d(TAG, " layer_title : " + layer_title);


        title.setText(layer_title);

        LoadListView loadListView = new LoadListView(idCountry);
        loadListView.execute();

//
//        List<SummaryModel> listData = sqlH.getTotalRecords(idCountry);
//
//        for (SummaryModel data : listData) {
//            // add contacts data in arrayList
//            summaryArr.add(data);
//        }
//
//        adapter = new SummaryListAdapter(this, summaryArr);


    }                                                                                               // end onCreate


    /**
     * This Class i used For  Loading List in thread while data loading into view it will show a loader
     * dialog
     */

    private class LoadListView extends AsyncTask<Void, Integer, String> {

        private String temCCode;


        private LoadListView(final String temCCode) {

            this.temCCode=temCCode;
        }

        @Override
        protected String doInBackground(Void... params) {

            List<SummaryModel> listData = sqlH.getTotalRecords(temCCode);

            for (SummaryModel data : listData) {
                // add contacts data in arrayList
                summaryArr.add(data);
            }

            adapter = new SummaryListAdapter((Activity) mContext, summaryArr);
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


            if (adapter != null) {
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        village_code = summaryArr.get(position).getVillCode();
                        village = summaryArr.get(position).getVillName();

                        //  Log.d(TAG, "village :" + village + " , village_code : " + village_code);

                        Intent dIntent = new Intent(SumRegLay4TotalHHRecords.this, RegisterRecordView.class);

                        dIntent.putExtra(VILLAGE_CODE_1, village_code);
                        dIntent.putExtra(VILLAGE_NAME_1, village);

                        finish();
                        startActivity(dIntent);


                    }
                });
                listView.setFocusableInTouchMode(true);

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


    private void setListener() {

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent iReg = new Intent(getApplicationContext(), Register.class);
                iReg.putExtra("country_code", idCountry);
                //   iReg.putExtra("country_name", strCountry);
                // startActivity(iReg);
                finish();
                startActivity(iReg);


            }
        });


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intentHome);

            }
        });
    }

    private void viewReference() {
        btnHome = (Button) findViewById(R.id.btnRegisterFooter);
        btnReg = (Button) findViewById(R.id.btnHomeFooter);
        title = (TextView) findViewById(R.id.hh_s_text_table);
        listView = (ListView) findViewById(R.id.list_village_records);                              // in activity_summary.xml

    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addIconRegistrationButton() {

        btnReg.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.registration);
        btnReg.setCompoundDrawablesRelativeWithIntrinsicBounds(null, imageHome, null, null);
        btnHome.setPadding(-1, 10, -1, 10);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpHomeButton() {

        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(null, imageHome, null, null);
        btnHome.setPadding(-1, 10, -1, 10);
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
        addIconRegistrationButton();
    }


}
