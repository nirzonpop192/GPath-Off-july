package com.siddiquinoor.restclient.activity.sub_activity.training;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.TrainingActivity;
import com.siddiquinoor.restclient.data_model.TaCategoriesDataModel;
import com.siddiquinoor.restclient.data_model.adapters.TrainingNActivityIndexDataModel;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.util.List;

public class IdTypeSelection extends BaseActivity {

    public static final int NO_RADIO_BUTTON_SELECTED = -1;
    private TextView tv_taTitle, tv_startNEndDate, tv_venue, tv_Address;

    private TrainingNActivityIndexDataModel mTAMasterData;
    private Button btnHome, btnNextPage, btnPreview, btnTrainActivity;
    private Context mContext;
    //    private RadioButton rbtnBeneficiary_card, rbtnNational_id_card, rbtnEmail_address, rbtnCell_phone, rbtnLicence;
    private RadioGroup radioGrp_Categories;
    private ADNotificationManager mDialog;
    private List<TaCategoriesDataModel> mTaCategoriesList;
    private SQLiteHandler sqlH;
    //private RadioGroup radioGrp_Categories;

    public void loadOrdinationRadioButtons() {

        mTaCategoriesList = sqlH.getTaCategories(mTAMasterData.getcCode());
        if (radioGrp_Categories.getChildCount() > 0) {

            radioGrp_Categories.removeAllViews();
        }


        for (int i = 0; i < mTaCategoriesList.size(); i++) {
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(i);
            rdbtn.setTextSize(18);                                                                  // set text size
            rdbtn.setPadding(0, 10, 0, 10);                                                         // set padding

            rdbtn.setText(mTaCategoriesList.get(i).getTaCatName());                            // set label

            radioGrp_Categories.addView(rdbtn);


        }                                                                                           // end of for loop
    }

    private void viewReference() {
        radioGrp_Categories = (RadioGroup) findViewById(R.id.rdGrp_categories);

        tv_taTitle = (TextView) findViewById(R.id.ta_index_row_tv_taTitle);
        tv_startNEndDate = (TextView) findViewById(R.id.ta_index_row_tv_StartEndDate);
        tv_venue = (TextView) findViewById(R.id.ta_index_row_tv_Venue);
        tv_Address = (TextView) findViewById(R.id.ta_index_row_tv_Address);
        btnPreview = (Button) findViewById(R.id.btn_dt_preview);
        btnNextPage = (Button) findViewById(R.id.btn_dt_next);
        btnTrainActivity = (Button) findViewById(R.id.btn_GoToTAPage);
        //radioGrp_Categories = (RadioGroup) findViewById(R.id.rdGrp_categories);

        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        Button button = (Button) findViewById(R.id.btnRegisterFooter);
        button.setVisibility(View.GONE);
        btnPreview.setVisibility(View.INVISIBLE);
    }

    private void initial() {
        mContext = IdTypeSelection.this;
        mDialog = new ADNotificationManager();
        sqlH= new SQLiteHandler(mContext);
        Intent intent = getIntent();
        mTAMasterData = intent.getParcelableExtra(KEY.EVENT_INDEX_DATA_OBJECT_KEY);
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

        btnTrainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(mContext, TrainingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void goToAddParticipants() {

        if (radioGrp_Categories.getCheckedRadioButtonId() == NO_RADIO_BUTTON_SELECTED) {
            mDialog.showErrorDialog(mContext, "Select ID type");
        } else {




            String categoryName= mTaCategoriesList.get(radioGrp_Categories.getCheckedRadioButtonId()).getTaCatName();
            switch (categoryName) {
                case "Beneficiary Card":
                    Intent intent = new Intent(mContext, TABeneficiaryCardListActivity.class);
                    intent.putExtra(KEY.EVENT_INDEX_DATA_OBJECT_KEY, mTAMasterData);
                    intent.putExtra(KEY.IDCATEGORY_OBJECT_KEY, mTaCategoriesList.get(radioGrp_Categories.getCheckedRadioButtonId()).getTaCatCode());

                    startActivity(intent);
                    break;
                case "National ID Card":
                case "License":
                case "Cell Phone":
                case "Email":

                    Intent iAddPati = new Intent(mContext, AddTaParticipaintActivity.class);
                    iAddPati.putExtra(KEY.EVENT_INDEX_DATA_OBJECT_KEY, mTAMasterData);
                    iAddPati.putExtra(KEY.IDCATEGORY_OBJECT_KEY, mTaCategoriesList.get(radioGrp_Categories.getCheckedRadioButtonId()).getTaCatCode());
                    startActivity(iAddPati);
                    break;

            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_type_selection);

        initial();
        setText();
        loadOrdinationRadioButtons();
        setListener();
    }



}
