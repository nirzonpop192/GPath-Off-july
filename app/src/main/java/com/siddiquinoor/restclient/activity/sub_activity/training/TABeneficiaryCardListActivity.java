package com.siddiquinoor.restclient.activity.sub_activity.training;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.siddiquinoor.restclient.activity.TrainingActivity;
import com.siddiquinoor.restclient.data_model.adapters.TrainigActivBeneficiaryDataModel;
import com.siddiquinoor.restclient.data_model.adapters.TrainingNActivityIndexDataModel;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.TrainingNActivityBeneficiaryAdapter;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;
import com.siddiquinoor.restclient.views.spinner.SpinnerLoader;

import java.util.ArrayList;
import java.util.List;

import static com.siddiquinoor.restclient.R.id.spVillage;

public class TABeneficiaryCardListActivity extends BaseActivity {
    private TextView tv_taTitle, tv_startNEndDate, tv_venue, tv_Address;

    private TrainingNActivityIndexDataModel mTAMasterData;
    private Button btnHome, btnNextPage, btnPreview, btn_TABeneficiarySearch, btnTrainActivity;
    private Context mContext;

    private ADNotificationManager mDialog;
    String idCountry;
    private String idLayR1Code, idLayR2Code, idLayR3Code, idLayR4Code;
    private Spinner spVillage;
    private String idVillage;
    private String strVillage;

    private ListView listView;
    private static ProgressDialog pDialog;
    private EditText edtTABeneficiaryNameORID;
    private SQLiteHandler sqlH;
    private TrainingNActivityBeneficiaryAdapter adapter;
    private String mIdCategories;
    private final String TAG = TABeneficiaryCardListActivity.class.getSimpleName();

    private Button btn_next;
    private Button btn_prev;


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
        setContentView(R.layout.activity_tabeneficiary_card_list);
        initial();
        setText();
        setListener();

        // to debug the below code
//        loadEligibleTrainingAcitMemList(mTAMasterData.getcCode(), "");
/*        LoadListView loadList = new LoadListView(mTAMasterData.getcCode(), "");
        loadList.execute();*/

        loadLayR4List();
    }

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

                    idCountry = idVillage.substring(0, 4);
                    idLayR1Code = idVillage.substring(4, 6);
                    idLayR2Code = idVillage.substring(6, 8);
                    idLayR3Code = idVillage.substring(8, 10);
                    idLayR4Code = idVillage.substring(10);

                    getNumberOfPages();

                    new LoadListView(mTAMasterData.getcCode(), idLayR1Code, idLayR2Code, idLayR3Code, idLayR4Code, "", increment).execute();

                    if (loutListContoller.getVisibility() == View.GONE) {
                        loutListContoller.setVisibility(View.VISIBLE);
                        tv_title.setVisibility(View.VISIBLE);
                    }

                } /*else {
                    mAdapter = new MemberSearchAdapter();
                    mAdapter.notifyDataSetChanged();
                    listOfMember.setAdapter(mAdapter);

                }*/


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }// end of the village spinner

    private void viewReference() {

        tv_taTitle = (TextView) findViewById(R.id.ta_index_row_tv_taTitle);
        tv_startNEndDate = (TextView) findViewById(R.id.ta_index_row_tv_StartEndDate);
        tv_venue = (TextView) findViewById(R.id.ta_index_row_tv_Venue);
        tv_Address = (TextView) findViewById(R.id.ta_index_row_tv_Address);
        btnNextPage = (Button) findViewById(R.id.btn_dt_next);
        btnPreview = (Button) findViewById(R.id.btn_dt_preview);
        btn_TABeneficiarySearch = (Button) findViewById(R.id.btn_TASearch);
        btnTrainActivity = (Button) findViewById(R.id.btn_GoToTAPage);
        edtTABeneficiaryNameORID = (EditText) findViewById(R.id.edt_TASearch);

        listView = (ListView) findViewById(R.id.lv_eligibleBeni);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        Button button = (Button) findViewById(R.id.btnRegisterFooter);
        button.setVisibility(View.GONE);

        spVillage = (Spinner) findViewById(R.id.search_mem_spVillage);

        loutListContoller = (LinearLayout) findViewById(R.id.listViewController);
        btn_next = (Button) findViewById(R.id.next);
        btn_prev = (Button) findViewById(R.id.prev);

        tv_title = (TextView) findViewById(R.id.tv_page_title);
    }

    private void initial() {
        mContext = TABeneficiaryCardListActivity.this;
        mDialog = new ADNotificationManager();
        sqlH = new SQLiteHandler(mContext);

        Intent intent = getIntent();
        mTAMasterData = intent.getParcelableExtra(KEY.EVENT_INDEX_DATA_OBJECT_KEY);
        mIdCategories = intent.getStringExtra(KEY.IDCATEGORY_OBJECT_KEY);
        viewReference();
    }


    private void setText() {
        tv_taTitle.setText(mTAMasterData.getEventTittle());
        tv_startNEndDate.setText(mTAMasterData.getStartDate() + "  to  " + mTAMasterData.getEndDate());

        tv_venue.setText("" + "Venue     : " + mTAMasterData.getVenueName().trim());
        tv_Address.setText("" + "Address : " + mTAMasterData.getAddressName().trim());
    }

    private void setListener() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity((Activity) mContext);
            }
        });
        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddParticipants();
            }
        });
        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToIdTypeSelectionPage();
            }
        });

        btn_TABeneficiarySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memNameORID = edtTABeneficiaryNameORID.getText().toString();

                if (memNameORID.length() == 0) {                                                  //Search button should filter all when there is nothing written in the text box.
                    new LoadListView(idCountry, idLayR1Code, idLayR2Code, idLayR3Code, idLayR4Code, "", increment).execute();
                    if (loutListContoller.getVisibility() == View.GONE) {
                        loutListContoller.setVisibility(View.VISIBLE);
                        tv_title.setVisibility(View.VISIBLE);
                    }


                } else {

                    new LoadListView(mTAMasterData.getcCode(), idLayR1Code, idLayR2Code, idLayR3Code, idLayR4Code, memNameORID, increment).execute();
                    loutListContoller.setVisibility(View.GONE);
                    tv_title.setVisibility(View.GONE);
                }

            }
        });
        btnTrainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(mContext, TrainingActivity.class);
                startActivity(intent);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                increment++;
                new LoadListView(idCountry, idLayR1Code, idLayR2Code, idLayR3Code, idLayR4Code, "", increment).execute();
                CheckEnable();
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                increment--;
                new LoadListView(idCountry, idLayR1Code, idLayR2Code, idLayR3Code, idLayR4Code, "", increment).execute();
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
     * this block is for checking the number of pages
     * ====================================================
     */
    private void getNumberOfPages() {
        TOTAL_LIST_ITEMS = sqlH.getMemberCount(idCountry, idLayR1Code, idLayR2Code, idLayR3Code, idLayR4Code, "", increment);
        int val = TOTAL_LIST_ITEMS % NUM_ITEMS_PAGE;
        val = val == 0 ? 0 : 1;
        pageCount = TOTAL_LIST_ITEMS / NUM_ITEMS_PAGE + val;

    }

    private void goToIdTypeSelectionPage() {
        finish();
        Intent intent = new Intent(mContext, IdTypeSelection.class);
        intent.putExtra(KEY.EVENT_INDEX_DATA_OBJECT_KEY, mTAMasterData);
        startActivity(intent);
    }

    private void goToAddParticipants() {
        int size = adapter.getSelectedEligibleBeneficiaryList().size();

        if (size == 0) {
            mDialog.showErrorDialog(mContext, "Select A Participant ");

        } else if (size == 1) {
//            Toast.makeText(mContext, " ID :"+adapter.getSelectedEligibleBeneficiaryList().get(0).getNewId(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(mContext, AddTaParticipaintActivity.class);
            intent.putExtra(KEY.EVENT_INDEX_DATA_OBJECT_KEY, mTAMasterData);
            intent.putExtra(KEY.IDCATEGORY_OBJECT_KEY, mIdCategories);
            intent.putExtra(KEY.T_A_BENEFICIARY_DATA_OBJECT_KEY, adapter.getSelectedEligibleBeneficiaryList().get(0));
            startActivity(intent);

        } else {
            mDialog.showInfromDialog(mContext, "Multiple Selection", "Multiple Selection is not allowed !");
        }
    }


    private void loadEligibleTrainingAcitMemList(final String cCode, String layR1Code, String layR2Code, String layR3Code, String layR4Code, final String memberString, int number) {

        int start = number * NUM_ITEMS_PAGE;
        List<TrainigActivBeneficiaryDataModel> memberList = sqlH.getMemberList(cCode, layR1Code, layR2Code, layR3Code, layR4Code, memberString, start,true);

        if (memberList.size() != 0) {
            adapter = new TrainingNActivityBeneficiaryAdapter((Activity) TABeneficiaryCardListActivity.this,memberList);
        }
    }
//    private void loadEligibleTrainingAcitMemList(final String cCode, String layR1Code, String layR2Code, String layR3Code, String layR4Code, final String memberString, int start) {
//
//        ArrayList<TrainigActivBeneficiaryDataModel> dataArray = new ArrayList<>();
//
//
//        List<TrainigActivBeneficiaryDataModel> assDatalist = sqlH.getEligibleTrainingAcitMemList(cCode, layR1Code, layR2Code, layR3Code, layR4Code, memberString, start);
//
//        if (assDatalist.size() != 0) {
////            dataArray.clear();
////            for (TrainigActivBeneficiaryDataModel data : assDatalist) {
////                // add contacts data in arrayList
////
////                dataArray.add(data);
////            }
//
//
//            adapter = new TrainingNActivityBeneficiaryAdapter((Activity) TABeneficiaryCardListActivity.this,
//                    assDatalist);
//
//            //  use below code to debug
//
////            if (adapter.getCount() > 0) {
////                if (adapter.getCount() != 0) {
////                    adapter.notifyDataSetChanged();
////                    listView.setAdapter(adapter);
////                } else {
////                    new ADNotificationManager().showInfromDialog(mContext, "NO Data", "No data Found");
////                }
////            }
//
//        }
//    }

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
        private String temlayR1Code, temlayR2Code, temlayR3Code, temlayR4Code;
        private String memberID;
        private int offsetNumber;


        LoadListView(String temCCode, String temlayR1Code, String temlayR2Code, String temlayR3Code, String temlayR4Code, String memberID, int offset) {
            this.memberID = memberID;
            this.temlayR1Code = temlayR1Code;
            this.temlayR2Code = temlayR2Code;
            this.temlayR3Code = temlayR3Code;
            this.temlayR4Code = temlayR4Code;

            this.temCCode = temCCode;
            this.offsetNumber = offset;
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
            tv_title.setText("Page " + (offsetNumber + 1) + " of " + pageCount);
            if (adapter != null && adapter.getCount() > 0) {
                if (adapter.getCount() != 0) {
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                } else {
                    mDialog.showInfromDialog(mContext, "NO Data", "No data Found");
                }
            }
        }

        @Override
        protected String doInBackground(Void... params) {
            loadEligibleTrainingAcitMemList(temCCode, temlayR1Code, temlayR2Code, temlayR3Code, temlayR4Code, memberID, offsetNumber);
            return "success";
        }
    }


}
