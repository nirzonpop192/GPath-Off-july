package com.siddiquinoor.restclient.activity.sub_activity.training;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.TrainingActivity;
import com.siddiquinoor.restclient.data_model.adapters.TaSummary;
import com.siddiquinoor.restclient.data_model.adapters.TrainingNActivityIndexDataModel;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.TA_SummaryAdapter;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.util.List;

public class TaSummaryPage extends BaseActivity {
    private TextView tv_taTitle, tv_startNEndDate, tv_venue, tv_Address;
    private Context mContext;
    private ADNotificationManager mDialog;
    private SQLiteHandler sqlH;
    private TrainingNActivityIndexDataModel mTAMasterData;
    private ListView lvOrganazetion, lvCategory, lvPosition, lvSex, lvSession;
    private TA_SummaryAdapter adapterOrganization, adapterCategory, adapterPosition, adapterSex, adapterSession;
    Button btnHome,btnTrainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ta_summary_page);
        initial();

        loadTaSummaryOrganization(mTAMasterData.getcCode(), mTAMasterData.getEventCode());
        loadTaSummaryCategory(mTAMasterData.getcCode(), mTAMasterData.getEventCode());
        loadTaSummaryPosition(mTAMasterData.getcCode(), mTAMasterData.getEventCode());
        loadTaSummarySex(mTAMasterData.getcCode(), mTAMasterData.getEventCode());
        loadTaSummarySession(mTAMasterData.getcCode(), mTAMasterData.getEventCode());
        setListener();
    }

    private void setListener() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity((Activity) mContext);
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
    }

    private void initial() {
        mContext = TaSummaryPage.this;
        mDialog = new ADNotificationManager();
        sqlH = new SQLiteHandler(mContext);

        Intent intent = getIntent();
        mTAMasterData = intent.getParcelableExtra(KEY.EVENT_INDEX_DATA_OBJECT_KEY);
        viewReference();
        setText();
    }

    private void setText() {
        tv_taTitle.setText(mTAMasterData.getEventTittle());
        tv_startNEndDate.setText(mTAMasterData.getStartDate() + "  to  " + mTAMasterData.getEndDate());

        tv_venue.setText("" + "Venue     : " + mTAMasterData.getVenueName().trim());
        tv_Address.setText("" + "Address : " + mTAMasterData.getAddressName().trim());
    }

    private void viewReference() {
        tv_taTitle = (TextView) findViewById(R.id.ta_index_row_tv_taTitle);
        tv_startNEndDate = (TextView) findViewById(R.id.ta_index_row_tv_StartEndDate);
        tv_venue = (TextView) findViewById(R.id.ta_index_row_tv_Venue);
        tv_Address = (TextView) findViewById(R.id.ta_index_row_tv_Address);
        lvOrganazetion = (ListView) findViewById(R.id.lv_sum_ta_orz);
        lvCategory = (ListView) findViewById(R.id.lv_sum_ta_cat);
        lvPosition = (ListView) findViewById(R.id.lv_sum_ta_pos);
        lvSex = (ListView) findViewById(R.id.lv_sum_ta_sex);
        lvSession = (ListView) findViewById(R.id.lv_sum_ta_session);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        Button button = (Button) findViewById(R.id.btnRegisterFooter);
        button.setVisibility(View.GONE);

        Button btnPreview = (Button) findViewById(R.id.btn_dt_preview);
        btnPreview.setVisibility(View.INVISIBLE);
        Button btnSave = (Button) findViewById(R.id.btn_dt_next);
        btnSave.setVisibility(View.INVISIBLE);
        btnTrainActivity = (Button) findViewById(R.id.btn_GoToTAPage);
    }


    private void loadTaSummaryOrganization(final String cCode, final String eventCode) {

        List<TaSummary> assDatalist = sqlH.getTaSummary(SQLiteQuery.loadTaSummaryOrganization_sql(cCode, eventCode));


        adapterOrganization = new TA_SummaryAdapter((Activity) mContext, assDatalist);


        if (adapterOrganization.getCount() > 0) {
            if (adapterOrganization.getCount() != 0) {
                adapterOrganization.notifyDataSetChanged();
                lvOrganazetion.setAdapter(adapterOrganization);
            } else {
                new ADNotificationManager().showInfromDialog(mContext, "NO Data", "No data Found");
            }
        }


    }

    private void loadTaSummaryCategory(final String cCode, final String eventCode) {

        List<TaSummary> assDatalist = sqlH.getTaSummary(SQLiteQuery.loadTaSummaryCategory_sql(cCode, eventCode));

        adapterCategory = new TA_SummaryAdapter((Activity) mContext, assDatalist);

        if (adapterCategory.getCount() > 0) {
            if (adapterCategory.getCount() != 0) {
                adapterCategory.notifyDataSetChanged();
                lvCategory.setAdapter(adapterCategory);
            } else {
                new ADNotificationManager().showInfromDialog(mContext, "NO Data", "No data Found");
            }
        }


    }

    private void loadTaSummaryPosition(final String cCode, final String eventCode) {

        List<TaSummary> assDatalist = sqlH.getTaSummary(SQLiteQuery.loadTaSummaryPosition_sql(cCode, eventCode));

        adapterPosition = new TA_SummaryAdapter((Activity) mContext, assDatalist);

        if (adapterPosition.getCount() > 0) {
            if (adapterPosition.getCount() != 0) {
                adapterPosition.notifyDataSetChanged();
                lvPosition.setAdapter(adapterPosition);
            } else {
                new ADNotificationManager().showInfromDialog(mContext, "NO Data", "No data Found");
            }
        }


    }

    private void loadTaSummarySex(final String cCode, final String eventCode) {

        List<TaSummary> assDatalist = sqlH.getTaSummary(SQLiteQuery.loadTaSummarySex_sql(cCode, eventCode));

        adapterSex = new TA_SummaryAdapter((Activity) mContext, assDatalist);

        if (adapterSex.getCount() > 0) {
            if (adapterSex.getCount() != 0) {
                adapterSex.notifyDataSetChanged();
                lvSex.setAdapter(adapterSex);
            } else {
                new ADNotificationManager().showInfromDialog(mContext, "NO Data", "No data Found");
            }
        }


    }

    private void loadTaSummarySession(final String cCode, final String eventCode) {

        List<TaSummary> assDatalist = sqlH.getTaSummary(SQLiteQuery.loadTaSummarySession_sql(cCode, eventCode));

        adapterSession = new TA_SummaryAdapter((Activity) mContext, assDatalist);

        if (adapterSession.getCount() > 0) {
            if (adapterSession.getCount() != 0) {
                adapterSession.notifyDataSetChanged();
                lvSession.setAdapter(adapterSession);
            } else {
                new ADNotificationManager().showInfromDialog(mContext, "NO Data", "No data Found");
            }
        }


    }


}
