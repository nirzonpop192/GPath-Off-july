package com.siddiquinoor.restclient.activity.sub_activity.summary_sub;

/**
 * Activity for presenting the list of all entry in a list view
 * with image and details
 *
 * @author
 * @desc TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.3.0
 * @since 1.0
 */

import android.annotation.TargetApi;
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
import android.widget.ListView;
import android.widget.Spinner;

import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.Register;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.HouseHoldPersonListAdapter;
import com.siddiquinoor.restclient.views.adapters.ListDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.spinner.SpinnerLoader;

import java.util.ArrayList;
import java.util.List;

import static com.siddiquinoor.restclient.utils.KEY.VILLAGE_CODE_1;
import static com.siddiquinoor.restclient.utils.KEY.VILLAGE_NAME_1;

public class RegisterRecordView extends BaseActivity {

    private static final String TAG = RegisterRecordView.class.getSimpleName();

    private Button btnReg, btnHome, btn_hhSearch;

    private Spinner spVillage;
    private EditText edt_hhSearch;


    private Context mContext;

    private ProgressDialog pDialog;
    private ArrayList<ListDataModel> personArray = new ArrayList<ListDataModel>();

    private ListView listViewRecord;
    private HouseHoldPersonListAdapter adapter;


    private SQLiteHandler sqlH;
    private String ext_village = null;
    private String ext_village_name = null;
    private String idVill = null;

    int position = 0;
    private String criteria = "";
    private String idCountry;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);
        initl();

        setListener();


        Intent intent = getIntent();
        ext_village = intent.getStringExtra(VILLAGE_CODE_1);
        ext_village_name = intent.getStringExtra(VILLAGE_NAME_1);

        idCountry = ext_village.substring(0, 4);


        loadLayR4CodeForRegisterRecordView(idCountry);

        if (ext_village != null) {

//            LoadingListView load = new LoadingListView(ext_village, "");
//            load.execute();
            loadListData(ext_village, "");
            for (int i = 0; i < spVillage.getCount(); i++) {
                String village = spVillage.getItemAtPosition(i).toString();
                if (village.equals(ext_village_name)) {
                    position = i;
                }
            }
            spVillage.setSelection(position);
        }
    }

    private void initl() {
        mContext = this;
        pDialog = new ProgressDialog(this);

        sqlH = new SQLiteHandler(getApplicationContext());
        viewReference();
    }

    private void setListener() {
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iReg = new Intent(getApplicationContext(), Register.class);
                iReg.putExtra("country_code", idCountry);
                startActivity(iReg);
                finish();
            }
        });


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHome);
                finish();
            }
        });

        btn_hhSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hhSearch = edt_hhSearch.getText().toString();
                if (hhSearch.length() > 0) {
                    if (ext_village != null) {
                        // idVill = ((SpinnerHelper) spVillage.getSelectedItem()).getId();
//                        LoadingListView lv = new LoadingListView(ext_village, hhSearch);
//                        lv.execute();

                        loadListData(ext_village, hhSearch);
                    }
                }
            }
        });
    }


    private void viewReference() {
        btnReg = (Button) findViewById(R.id.btnRegisterFooter);
        edt_hhSearch = (EditText) findViewById(R.id.edt_hhSearch);
        btn_hhSearch = (Button) findViewById(R.id.btn_hhSearch);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        spVillage = (Spinner) findViewById(R.id.spVillagerecord);
        listViewRecord = (ListView) findViewById(R.id.list_registered_user); // in activity_view_records.xmlml


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

        addIconRegistrationButton();

    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addIconRegistrationButton() {

        btnReg.setText("");
        Drawable registration = getResources().getDrawable(R.drawable.registration);
        btnReg.setCompoundDrawablesRelativeWithIntrinsicBounds(null, registration, null, null);
        btnReg.setPadding(-1, 10, -1, 10);
    }


    private void loadLayR4CodeForRegisterRecordView(String cCode) {


        SpinnerLoader.loadLayR4CodeForRegisterRecordViewLoader(mContext, sqlH, spVillage, cCode);

        spVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                personArray.clear();

                if (ext_village == null) {
                    idVill = ((SpinnerHelper) spVillage.getSelectedItem()).getId();

//                    LoadingListView load = new LoadingListView(idVill, "");
//                    load.execute();

                    loadListData(idVill, "");
                } else {

                    Log.d(TAG, "village code: " + ext_village);

//                    LoadingListView load = new LoadingListView(ext_village, "");
//                    load.execute();

                    loadListData(ext_village, "");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void loadListData(String village_code, String houseHoldId) {
        List<ListDataModel> listData = sqlH.getRegisteredData(village_code, houseHoldId);
        personArray.clear(); ///here cearing array list for uddate

        for (ListDataModel data : listData) {
            // add contacts data in arrayList
            personArray.add(data);
        }

        adapter = new HouseHoldPersonListAdapter(this, personArray);
        adapter.notifyDataSetChanged();
         listViewRecord.setAdapter(adapter);


    }


    private class LoadingListView extends AsyncTask<Void, Integer, String> {

        private String villageCode;
        private String temHhId;

        public LoadingListView(String villageCode, String temHhId) {
            this.villageCode = villageCode;
            this.temHhId = temHhId;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
//                loadListData(villageCode, temHhId);
            } catch (Exception e) {
                pDialog.dismiss();
                return "UNKNOWN";
            }
            return "success";
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            startProgressBar(mContext.getResources().getString(R.string.loading_msg));

        }


        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            adapter.notifyDataSetChanged();
            listViewRecord.setAdapter(adapter);

            listViewRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
            listViewRecord.setFocusableInTouchMode(true);
            pDialog.dismiss();

        }
    }


    private void startProgressBar(String msg) {
        pDialog.setMessage(msg);
        pDialog.setCancelable(false);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.show();
    }

}
