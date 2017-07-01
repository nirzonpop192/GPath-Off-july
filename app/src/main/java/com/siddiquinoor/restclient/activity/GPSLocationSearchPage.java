package com.siddiquinoor.restclient.activity;

import android.annotation.TargetApi;
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

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.sub_activity.gps_sub.MapActivity;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.SearchLocationAdapter;
import com.siddiquinoor.restclient.views.helper.LocationHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.util.List;

public class GPSLocationSearchPage extends BaseActivity {

    private static final String TAG = GPSLocationSearchPage.class.getSimpleName();

    /**
     * Adm Country Code
     */
    private String idCountry;

    /**
     * Button Search Location
     */
    private Button btn_search_loc;

    /**
     * Database Helper Object
     */
    private SQLiteHandler sqlH;

    /**
     * Location ListView
     */
    private ListView listView;

    /**
     * edt_seLocName Edit Text . where user input their designer location
     * edt_seLocName =searching Location Name
     */
    private EditText edt_seLocName;

    /**
     *  button of class
     */
    private Button btnHome, btnGps;

    /**
     *  context
     */
    private Context mContext = GPSLocationSearchPage.this;

    /**
     *
     */
    private ProgressDialog pDialog;

    /**
     * mAdapter is custom Adapter of {@link SearchLocationAdapter}
     */
    private SearchLocationAdapter mAdapter;
    /**
     * mDialog is Custom Dialog manager to provide information to user
     */
    private ADNotificationManager mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_loaction_search_page);

        initialize();

        Intent intent = getIntent();

        // find out the reason why i did this ??
        String dir = intent.getStringExtra(KEY.DIR_CLASS_NAME_KEY);

        // get the Country id from intent don't use db to get db to get Country Code
        idCountry = intent.getStringExtra(KEY.COUNTRY_ID);

        // anonymous object
        new LoadListView(idCountry, "").execute();

        setListener();

        // for test purpose
        //   loadLocation(idCountry, "");
    }

    /**
     * this method get the specific location want to search .
     *
     */
    private void searchLocation() {
        String locationName = edt_seLocName.getText().toString().trim();
        if (locationName.length() == 0) {

            //anonymous object
            new LoadListView(idCountry, "").execute();

            // for test purpose
            //loadLocation(idCountry, locationName);
        } else {
            //anonymous object
            new LoadListView(idCountry, locationName).execute();

            // for test purpose
            //loadLocation(idCountry, locationName);
        }
    }

    /**
     * allocated all the variable here
     */
    private void initialize() {
        viewReference();

        //initiate db
        sqlH = new SQLiteHandler(GPSLocationSearchPage.this);

        mDialog = new ADNotificationManager();
    }

    /**
     * Refer the non java  object with java object
     */
    private void viewReference() {
        btn_search_loc = (Button) findViewById(R.id.btn_location_search);
        listView = (ListView) findViewById(R.id.lv_location);
        edt_seLocName = (EditText) findViewById(R.id.edt_searchLocation);
        btnHome = (Button) findViewById(R.id.btnRegisterFooter);
        btnGps = (Button) findViewById(R.id.btnHomeFooter);
    }


    /**
     * <p>This method Load the Location onto the spinner</p>
     *
     * @param cCode         Country Code
     * @param searchLocName Location Name
     */

    private void loadLocation(final String cCode, final String searchLocName) {
        List<LocationHelper> listOfLocation = sqlH.getLocationList(cCode, searchLocName);

        mAdapter = new SearchLocationAdapter(GPSLocationSearchPage.this, listOfLocation, idCountry);

        // for test purpose don't delete  bellow code

/*        mAdapter.notifyDataSetChanged();
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/


    }

    private void setListener() {

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(GPSLocationSearchPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(GPSLocationSearchPage.this, MapActivity.class);
                intent.putExtra(KEY.COUNTRY_ID, idCountry);
                intent.putExtra(KEY.DIR_CLASS_NAME_KEY, TAG);

                startActivity(intent);
            }
        });

        btn_search_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchLocation();
            }
        });
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
        addIconAddGPSButton();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addIconHomeButton() {
        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, btnHome);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addIconAddGPSButton() {
        btnGps.setText("");
        Drawable addImage = getResources().getDrawable(R.drawable.add);
        btnGps.setCompoundDrawablesRelativeWithIntrinsicBounds(addImage, null, null, null);
        setPaddingButton(mContext, addImage, btnGps);
    }

    private void hideProgressBar() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    /**
     *
     * @param msg dialog msg
     */
    private void startProgressBar(String msg) {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage(msg);
        pDialog.setCancelable(true);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.show();
    }

    private class LoadListView extends AsyncTask<Void, Integer, String> {

        private String tem_cCode;
        private String tem_LocationName;

        public LoadListView(String tem_cCode, String tem_LocationName) {
            this.tem_cCode = tem_cCode;
            this.tem_LocationName = tem_LocationName;
        }

        @Override
        protected String doInBackground(Void... params) {
            loadLocation(tem_cCode, tem_LocationName);
            return "success";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            startProgressBar("Data is Loading");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            hideProgressBar();
            if (mAdapter != null && mAdapter.getCount() > 0) {

                mAdapter.notifyDataSetChanged();
                listView.setAdapter(mAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
            } else {
                mDialog.showInfromDialog(mContext, "No Data", "No Data found");
            }

        }
    }
}
