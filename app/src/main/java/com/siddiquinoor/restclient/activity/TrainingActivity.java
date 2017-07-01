package com.siddiquinoor.restclient.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.data_model.adapters.TrainingNActivityIndexDataModel;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.views.adapters.TrainingNActivityIndexAdapter;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.util.ArrayList;
import java.util.List;

public class TrainingActivity extends BaseActivity {
    private SQLiteHandler sqlH;
    private Context mContext;
    private String idCountry;
    private Button btnHome, btnDTSearch;
    private TrainingNActivityIndexAdapter adapter;
    private ListView listView;

    private static ProgressDialog pDialog;
    private EditText edtEventName;


    private void loadTrainingActivityEventIndex(final String cCode, final String eventNameSearch) {
        List<TrainingNActivityIndexDataModel> dataList = sqlH.getTrainingActivityIndexList(cCode, eventNameSearch);
        ArrayList<TrainingNActivityIndexDataModel> dataArray = new ArrayList<>();

        if (dataList.size() != 0) {
            dataArray.clear();

            for (TrainingNActivityIndexDataModel data : dataList) {
                dataArray.add(data);                                                                 //add contacts data in arrayList
            }
            adapter = new TrainingNActivityIndexAdapter((Activity) mContext, dataArray);                  //Assign the Adapter in list
        }

//
//               for tes purpose
//        if (adapter != null && adapter.getCount() > 0) {
//            if (adapter.getCount() != 0) {
//                adapter.notifyDataSetChanged();
//                listView.setAdapter(adapter);
//            } else {
//                new ADNotificationManager().showInfromDialog(mContext, "NO Data", "No data Found");
//            }
//        }
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

    private class LoadListView extends AsyncTask<Void, Integer, String> {
        private String temCCode;
        private String eventName;


        LoadListView(String temCCode, String eventName) {
            this.eventName = eventName;
            this.temCCode = temCCode;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            startProgressBar(mContext.getResources().getString(R.string.loading_msg));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            hideProgressBar();

            if (adapter != null && adapter.getCount() > 0) {
                if (adapter.getCount() != 0) {
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                } else {
                    new ADNotificationManager().showInfromDialog(mContext, "NO Data", "No data Found");
                }
            }
        }

        @Override
        protected String doInBackground(Void... params) {
            loadTrainingActivityEventIndex(temCCode,eventName);
            return "success";
        }
    }

    private void initial() {
        mContext = TrainingActivity.this;
        sqlH = new SQLiteHandler(mContext);
        idCountry = sqlH.getSelectedCountryCode();
        viewReference();
//        edtEventName.setFocusable(false);
    }

    private void viewReference() {
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        Button button = (Button) findViewById(R.id.btnRegisterFooter);
        button.setVisibility(View.GONE);                                                             // button er Gaiyeb korra jonno
        listView = (ListView) findViewById(R.id.lvTrainingActivityIndex);
        btnDTSearch = (Button) findViewById(R.id.btn_TASearch);
        edtEventName = (EditText) findViewById(R.id.edt_TASearch);

    }


    private void setListener() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity((Activity) mContext);
            }
        });

        btnDTSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventNameSearch();
            }
        });


    }

    private void eventNameSearch() {

        if (edtEventName.getText().toString().length() == 0) {                                       //Search button should filter all when there is nothing written in the text box.
            new LoadListView(idCountry, "").execute();

        } else {
            new LoadListView(idCountry, edtEventName.getText().toString()).execute();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        initial();
        setListener();

/**
 *  Data load stooped for taking more time than expected
 */
        LoadListView loadListView= new LoadListView(idCountry,"");                                  //
        loadListView.execute();

        // for test purpose
//        loadTrainingActivityEventIndex(idCountry,"");
    }

}
