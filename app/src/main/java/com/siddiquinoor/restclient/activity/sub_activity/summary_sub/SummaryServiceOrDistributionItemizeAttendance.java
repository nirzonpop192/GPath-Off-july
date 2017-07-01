package com.siddiquinoor.restclient.activity.sub_activity.summary_sub;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.SummaryServiceListAdapter;
import com.siddiquinoor.restclient.views.adapters.SummaryServiceListModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.util.ArrayList;
import java.util.List;

public class SummaryServiceOrDistributionItemizeAttendance extends BaseActivity {

    private final String TAG = "SummaryServiceItemizeAttendance";
    private SQLiteHandler sqlH;
    // private  int position;
    private Spinner spItemName;// spinner Criteria SumRegLay4TotalHHRecords
    private String idItemSpec;
    private String strItemSpec;
    private boolean isComeThroughIntent;
    private ListView lv_summaryList;
    private SummaryItemizeAttendance adapter;
    private ArrayList<SummaryServiceListModel> srvListArray = new ArrayList<SummaryServiceListModel>();
    private Button btn_home;
    private Button btn_summaryMenu;
    private final Context CONTEXT = SummaryServiceOrDistributionItemizeAttendance.this;
    private String idCountry;
    private String idOpMonth_Code;
    private String holderMonth_str;

    private String idDonor;
    private String idAward, strAward, strProgram, idProgram;
    private String flag;

    private ADNotificationManager dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_service_itemize_attendance);
        sqlH = new SQLiteHandler(this);
        dialog = new ADNotificationManager();
        viewReference();


        Intent intent = getIntent();

        idCountry = intent.getStringExtra(KEY.COUNTRY_ID);

        String dir = intent.getStringExtra(KEY.DIR_CLASS_NAME_KEY);

        if (dir.equals("SummaryServiceItemize")) {


            idDonor = intent.getStringExtra(KEY.DONOR_CODE);
            idAward = intent.getStringExtra(KEY.AWARD_CODE);
            strAward = intent.getStringExtra(KEY.AWARD_NAME);
            idProgram = intent.getStringExtra(KEY.PROGRAM_CODE);
            strProgram = intent.getStringExtra(KEY.PROGRAM_NAME);
            idItemSpec = intent.getStringExtra(KEY.ITEM_SPEC_CODE);
            strItemSpec = intent.getStringExtra(KEY.ITEM_SPEC_NAME);
            idOpMonth_Code = intent.getStringExtra(KEY.SERVICE_MONTH_CODE);
            holderMonth_str = intent.getStringExtra(KEY.SERVICE_MONTH_NAME);
            flag = intent.getStringExtra(KEY.FLAG);
            log();


            loadItemName(idCountry, idDonor, idAward, idOpMonth_Code, idProgram, flag);


        } else if (dir.equals("ServiceSummaryMenu")) {

            idDonor = intent.getStringExtra(KEY.DONOR_CODE);
            idAward = intent.getStringExtra(KEY.AWARD_CODE);
            strAward = intent.getStringExtra(KEY.AWARD_NAME);
            idProgram = intent.getStringExtra(KEY.PROGRAM_CODE);
            strProgram = intent.getStringExtra(KEY.PROGRAM_NAME);
//            idItemSpec = intent.getStringExtra(KEY.ITEM_SPEC_CODE);
//            strItemSpec = intent.getStringExtra(KEY.ITEM_SPEC_NAME);
            idOpMonth_Code = intent.getStringExtra(KEY.SERVICE_MONTH_CODE);
            holderMonth_str = intent.getStringExtra(KEY.SERVICE_MONTH_NAME);
            flag = intent.getStringExtra(KEY.FLAG);
            log();


            loadItemName(idCountry, idDonor, idAward, idOpMonth_Code, idProgram, flag);

        } else {
        }
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity((Activity) CONTEXT);
            }
        });
        btn_summaryMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(CONTEXT, SummaryServiceOrDistributionItemize.class);
                i.putExtra(KEY.DIR_CLASS_NAME_KEY, "SummaryServiceItemizeAttendance");
                i.putExtra(KEY.COUNTRY_ID, idCountry);
                i.putExtra(KEY.DONOR_CODE, idDonor);
                i.putExtra(KEY.AWARD_CODE, idAward);
                i.putExtra(KEY.AWARD_NAME, strAward);
                i.putExtra(KEY.PROGRAM_CODE, idProgram);
                i.putExtra(KEY.PROGRAM_NAME, strProgram);
                i.putExtra(KEY.SERVICE_MONTH_CODE, idOpMonth_Code);
                i.putExtra(KEY.FLAG, flag);
                i.putExtra(KEY.FLAG, flag);
                startActivity(i);
            }
        });
    }

    private void log() {
        Log.d("Itemize Attendance2",
                "idCountry : " + idCountry + " idDonor :" + idDonor
                        + "idAward : " + idAward + " strAward :" + strAward
                        + " idProgram :" + idProgram
                        + " strProgram :" + strProgram
                        + " idItemSpec :" + idItemSpec
                        + " strItemSpec :" + strItemSpec
                        + " idOpMonth_Code :" + idOpMonth_Code
                        + " holderMonth_str :" + holderMonth_str
                        + " flag :" + flag
        );
    }

    private void viewReference() {
        spItemName = (Spinner) findViewById(R.id.sp_itemName);
        lv_summaryList = (ListView) findViewById(R.id.lv_ServItemizeAttendance);
        btn_home = (Button) findViewById(R.id.btnHomeFooter);
        btn_summaryMenu = (Button) findViewById(R.id.btnRegisterFooter);
        setUpGoBackButton();
        setUpHomeButton();

    }

    private void setUpGoBackButton() {
        btn_summaryMenu.setText("");
        Drawable imageGoto = getResources().getDrawable(R.drawable.goto_back);
        btn_summaryMenu.setCompoundDrawablesRelativeWithIntrinsicBounds(imageGoto, null, null, null);
        btn_summaryMenu.setPadding(180, 10, 180, 10);
    }

    private void setUpHomeButton() {

        btn_home.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btn_home.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        btn_home.setPadding(180, 10, 180, 10);
    }

    /**
     * LOAD :: item name
     */
    private void loadItemName(final String cCode, final String donorCode, final String awardCode, final String opMCode, final String programCode, final String srvDistflag) {

        int position = 0; // here the select the position of spinner

       /**/

        String criteria;
        if (srvDistflag.equals(KEY.DIST_FLAG)) {
            criteria = SQLiteQuery.getServExtentedItemName(cCode, donorCode, awardCode, opMCode, programCode);


        } else {
            criteria = SQLiteQuery.getDistExtentedItemName(cCode, donorCode, awardCode, opMCode, programCode);
        }


        List<SpinnerHelper> listSCriteria = sqlH.getListAndID(SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE
                , criteria, null, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listSCriteria);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spItemName.setAdapter(dataAdapter);


        if (idItemSpec != null) {


            for (int i = 0; i < spItemName.getCount(); i++) {
                String award = spItemName.getItemAtPosition(i).toString();
                if (award.equals(strItemSpec)) {
                    position = i;
                }
            }
            spItemName.setSelection(position);

        }
        spItemName.setSelection(position);


        spItemName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strItemSpec = ((SpinnerHelper) spItemName.getSelectedItem()).getValue();
                idItemSpec = ((SpinnerHelper) spItemName.getSelectedItem()).getId();
                if (idItemSpec.length() > 2) {



                    loadServiceItemizeAttendanceList(cCode, donorCode, awardCode, opMCode, programCode, idItemSpec, flag);
                }

                Log.d("Itemize Attendance", "idItemSpec : " + idItemSpec + " strItemSpec :" + strItemSpec);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    public void loadServiceItemizeAttendanceList(String countryCode, String donorCode, String awaradCode, String opMonthCode, String programCode, String itemSpecCode, String srvDistFlag) {
        Log.d("Itemize Attendance", "In load service List ");
        // use veriable to like operation
        List<SummaryServiceListModel> srvList = sqlH.getTotalSerDistItemizeAttendanceSummary(countryCode, donorCode, awaradCode, opMonthCode, programCode, itemSpecCode, srvDistFlag);//SQHandler 783:Line
        if (srvList.size() != 0) {
            srvListArray.clear();
            for (SummaryServiceListModel data : srvList) {
                // add contacts data in arrayList
                srvListArray.add(data);
            }
            adapter = new SummaryItemizeAttendance(this, srvListArray);
            adapter.notifyDataSetChanged();
            lv_summaryList.setAdapter(adapter);

            lv_summaryList.setFocusableInTouchMode(true);

        } else {
            dialog.showInfromDialog(CONTEXT, "No Data", "No Data found");
        }
    }


    public class SummaryItemizeAttendance extends BaseAdapter {
        private final String TAG = SummaryServiceListAdapter.class.getName();
        Activity activity;
        private LayoutInflater inflater;
        private ArrayList<SummaryServiceListModel> srvListData = new ArrayList<SummaryServiceListModel>();
        ViewHolderS holder;

        public SummaryItemizeAttendance(Activity activity, ArrayList<SummaryServiceListModel> srvListData) {
            this.activity = activity;
            this.srvListData = srvListData;
        }

        @Override
        public int getCount() {
            return srvListData.size();
        }

        @Override
        public Object getItem(int position) {
            return srvListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;


            if (inflater == null)
                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                row = inflater.inflate(R.layout.list_row_summary_itemize, null);
                holder = new ViewHolderS();
                holder.tv_hh_id = (TextView) row.findViewById(R.id.tv_id_distAId);
                //  holder.tv_hh_mm_id= (TextView) row.findViewById(R.id.tv_hh_mm_id_ss);
                holder.tv_srvCount = (TextView) row.findViewById(R.id.tv_Status_distASummary);
                // holder.tv_srv_date= (TextView) row.findViewById(R.id.tv_date_ss);
                holder.tv_cost = (TextView) row.findViewById(R.id.row_tv_summ_itemize_cost);


                row.setTag(holder);
            } else {
                holder = (ViewHolderS) row.getTag();
            }
            SummaryServiceListModel srvS = new SummaryServiceListModel();
            srvS = srvListData.get(position);

            holder.tv_hh_id.setText(srvS.getCustomId());
            // holder.tv_hh_mm_id.setText(srvS.getMemberId());
            holder.tv_srvCount.setText(srvS.getServiceCount());
            double totalCost = Double.parseDouble(srvS.getServiceCount()) * Double.parseDouble(srvS.getPer_unit_cost());
            holder.tv_cost.setText(String.valueOf(totalCost));

            return row;
        }

        class ViewHolderS {
            TextView tv_hh_id;
            TextView tv_hh_mm_id;
            TextView tv_srvCount;
            TextView tv_cost;
            //  TextView tv_srv_date;
        }
    }

}
