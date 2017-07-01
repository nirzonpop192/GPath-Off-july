package com.siddiquinoor.restclient.activity.sub_activity.training;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.TrainingActivity;
import com.siddiquinoor.restclient.data_model.LupTaParticipentCategories;
import com.siddiquinoor.restclient.data_model.TAPosParticipants;
import com.siddiquinoor.restclient.data_model.TA_ParticipantsListDataModel;
import com.siddiquinoor.restclient.data_model.TaPartOrgN;
import com.siddiquinoor.restclient.data_model.adapters.TA_ParticipientAttendenceDateTimeDataModel;
import com.siddiquinoor.restclient.data_model.adapters.TrainigActivBeneficiaryDataModel;
import com.siddiquinoor.restclient.data_model.adapters.TrainingNActivityIndexDataModel;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.views.adapters.TA_ParticipientAttendenceDateTimeAdapter;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddTaParticipaintActivity extends BaseActivity {
    private TextView tv_taTitle, tv_startNEndDate, tv_venue, tv_Address;
    private Context mContext;
    private ADNotificationManager mDialog;
    private SQLiteHandler sqlH;
    private TrainingNActivityIndexDataModel mTAMasterData;
    private TrainigActivBeneficiaryDataModel mEligiableBenef = null;
    private Button btnHome, btnPreview, btnSave, btnTrainActivity;
    private List<TA_ParticipientAttendenceDateTimeDataModel> mDatesString = new ArrayList<TA_ParticipientAttendenceDateTimeDataModel>();  // Set initial capacity to `days`.
    private EditText edt_participant_Id;
    private EditText edt_participant_Name;
    private ListView listView;
    private List<LupTaParticipentCategories> mLupParticipantCategoriesList;
    private List<TAPosParticipants> mTAPositionParticipantList;
    private List<TaPartOrgN> mTaPartOrgaNizationList;

    private Spinner spGender;

    private TA_ParticipientAttendenceDateTimeAdapter adapter;
    private String gender, str_gender;
    private RadioGroup radioGrp_organization, radioGrp_category, radioGrp_position;

    /**
     * List for Dynamic
     */

    //private List<RadioButton> mOrganizationRadioButton_List = new ArrayList<RadioButton>();
    // private List<RadioButton> mCategoriesRadioButton_List = new ArrayList<RadioButton>();
    //private List<RadioButton> mPositionRadioButton_List = new ArrayList<RadioButton>();
    private String mIdCategorisCode;


    private void viewReference() {

        tv_taTitle = (TextView) findViewById(R.id.ta_index_row_tv_taTitle);
        tv_startNEndDate = (TextView) findViewById(R.id.ta_index_row_tv_StartEndDate);
        tv_venue = (TextView) findViewById(R.id.ta_index_row_tv_Venue);
        tv_Address = (TextView) findViewById(R.id.ta_index_row_tv_Address);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnPreview = (Button) findViewById(R.id.btn_dt_preview);
        btnSave = (Button) findViewById(R.id.btn_dt_next);
        radioGrp_organization = (RadioGroup) findViewById(R.id.ta_add_pati_rg_organization);
        radioGrp_category = (RadioGroup) findViewById(R.id.ta_add_pati_rg_category);
        radioGrp_position = (RadioGroup) findViewById(R.id.ta_add_pati_rg_position);
        edt_participant_Id = (EditText) findViewById(R.id.edt_add_participant_ID);
        edt_participant_Name = (EditText) findViewById(R.id.edt_add_participant_Name);
        Button button = (Button) findViewById(R.id.btnRegisterFooter);
        button.setVisibility(View.GONE);
        listView = (ListView) findViewById(R.id.lv_time_schedule);

        btnTrainActivity = (Button) findViewById(R.id.btn_GoToTAPage);
    }


    public void loadPositionRadioButtons() {

        mTAPositionParticipantList = sqlH.getTAPosParticipants(mTAMasterData.getcCode());
        if (radioGrp_position.getChildCount() > 0) {

            radioGrp_position.removeAllViews();
        }


        for (int i = 0; i < mTAPositionParticipantList.size(); i++) {
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(i);
            rdbtn.setTextSize(18);                                                                  // set text size
            rdbtn.setPadding(0, 10, 0, 10);                                                         // set padding

            rdbtn.setText(mTAPositionParticipantList.get(i).getPosTitle());                                // set label

            radioGrp_position.addView(rdbtn);


        }                                                                                           // end of for loop
    }

    public void loadCategoriesRadioButtons() {

        mLupParticipantCategoriesList = sqlH.getLUP_TAParticipantCategories(mTAMasterData.getcCode(), mTAMasterData.getTaGroupCode());
        if (radioGrp_category.getChildCount() > 0) {

            radioGrp_category.removeAllViews();
        }


        for (int i = 0; i < mLupParticipantCategoriesList.size(); i++) {
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(i);
            rdbtn.setTextSize(18);                                                                  // set text size
            rdbtn.setPadding(0, 10, 0, 10);                                                         // set padding

            rdbtn.setText(mLupParticipantCategoriesList.get(i).getParticipentCategoriesName());                            // set label

            radioGrp_category.addView(rdbtn);


        }                                                                                           // end of for loop
    }

    public void loadOrdinationRadioButtons() {

        mTaPartOrgaNizationList = sqlH.getTaOrganization(mTAMasterData.getcCode());
        if (radioGrp_organization.getChildCount() > 0) {

            radioGrp_organization.removeAllViews();
        }


        for (int i = 0; i < mTaPartOrgaNizationList.size(); i++) {
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(i);
            rdbtn.setTextSize(18);                                                                  // set text size
            rdbtn.setPadding(0, 10, 0, 10);                                                         // set padding

            rdbtn.setText(mTaPartOrgaNizationList.get(i).getPartOrgNName());                            // set label

            radioGrp_organization.addView(rdbtn);


        }                                                                                           // end of for loop
    }

    private void initial() {
        mContext = AddTaParticipaintActivity.this;
        mDialog = new ADNotificationManager();
        sqlH = new SQLiteHandler(mContext);

        Intent intent = getIntent();
        mTAMasterData = intent.getParcelableExtra(KEY.EVENT_INDEX_DATA_OBJECT_KEY);
        mIdCategorisCode = intent.getStringExtra(KEY.IDCATEGORY_OBJECT_KEY);
        mEligiableBenef = intent.getParcelableExtra(KEY.T_A_BENEFICIARY_DATA_OBJECT_KEY);
        viewReference();
    }

    private void setText() {
        tv_taTitle.setText(mTAMasterData.getEventTittle());
        tv_startNEndDate.setText(mTAMasterData.getStartDate() + "  to  " + mTAMasterData.getEndDate());

        tv_venue.setText("" + "Venue     : " + mTAMasterData.getVenueName().trim());
        tv_Address.setText("" + "Address : " + mTAMasterData.getAddressName().trim());
        if (mEligiableBenef != null) {

            edt_participant_Id.setText(mEligiableBenef.getNewId());
            edt_participant_Name.setText(mEligiableBenef.getHh_mm_name());
            edt_participant_Id.setEnabled(false);
            edt_participant_Id.setFocusable(false);

            edt_participant_Name.setEnabled(false);
            edt_participant_Name.setFocusable(false);


            gender = mEligiableBenef.getMember_sex();


        }
    }

    private void restoreThePreviousSavedValue() {
        TA_ParticipantsListDataModel dataModel = sqlH.getTaParticipantsListTable(mTAMasterData.getcCode(), mTAMasterData.getEventCode(), mEligiableBenef.getNewId(), mIdCategorisCode);
        if (dataModel != null) {

            for (int i = 0; i < mTaPartOrgaNizationList.size(); i++) {
                if (mTaPartOrgaNizationList.get(i).getPartOrgNCode().equals(dataModel.getPartOrgNCode())) {
                    RadioButton radioButton = (RadioButton) radioGrp_organization.getChildAt(i);
                    radioButton.setChecked(true);
                }

            }

            for (int i = 0; i < mLupParticipantCategoriesList.size(); i++) {
                if (mLupParticipantCategoriesList.get(i).getParticipentCategoriesCode().equals(dataModel.getPartCatCode())) {
                    RadioButton radioButton = (RadioButton) radioGrp_category.getChildAt(i);
                    radioButton.setChecked(true);
                }

            }

            for (int i = 0; i < mTAPositionParticipantList.size(); i++) {
                if (mTAPositionParticipantList.get(i).getPosCode().equals(dataModel.getPosCode())) {
                    RadioButton radioButton = (RadioButton) radioGrp_position.getChildAt(i);
                    radioButton.setChecked(true);
                }

            }
        }
    }

    private void goToIdTypeSelectionPage() {
        finish();
        Intent intent = new Intent(mContext, IdTypeSelection.class);
        intent.putExtra(KEY.EVENT_INDEX_DATA_OBJECT_KEY, mTAMasterData);
        startActivity(intent);
    }


    private void setListener() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity((Activity) mContext);
            }
        });
        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToIdTypeSelectionPage();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProcessData();
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

    private void saveProcessData() {
        if (edt_participant_Id.getText().length() <= 0) {
            mDialog.showErrorDialog(mContext, "Participant Id Missing");
        } else if (edt_participant_Name.getText().length() <= 0) {
            mDialog.showErrorDialog(mContext, "Participant Name Missing");
        } else if (radioGrp_organization.getCheckedRadioButtonId() == -1) {
            mDialog.showErrorDialog(mContext, "Participant's Organization Missing");
        } else if (radioGrp_category.getCheckedRadioButtonId() == -1) {
            mDialog.showErrorDialog(mContext, "Participant's Category Missing");
        } else if (radioGrp_position.getCheckedRadioButtonId() == -1) {
            mDialog.showErrorDialog(mContext, "Participant's Position Missing");
        } else if (adapter.getmSelectedDateTime().size() == 0) {
            mDialog.showErrorDialog(mContext, "select session");
        } else {
            saveData();
        }


    }

    private void saveData() {

        String cCode = mTAMasterData.getcCode();
        String eventCode = mTAMasterData.getEventCode();
        String partcipantID = edt_participant_Id.getText().toString();

        String partCatCode = mLupParticipantCategoriesList.get(radioGrp_category.getCheckedRadioButtonId()).getParticipentCategoriesCode();

        String positionCode = mTAPositionParticipantList.get(radioGrp_position.getCheckedRadioButtonId()).getPosCode();

        String organationCode = mTaPartOrgaNizationList.get(radioGrp_organization.getCheckedRadioButtonId()).getPartOrgNCode();

        String partcipantName = edt_participant_Name.getText().toString();

        String entryBy = getStaffID();
        String entryDate = "";
        try {
            entryDate = getDateTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        for (TA_ParticipientAttendenceDateTimeDataModel dataModel : adapter.getmSelectedDateTime()) {

            String amSession = dataModel.isAmSession() ? "1" : "0";
            String pmSession = dataModel.isPmSession() ? "1" : "0";
            if (sqlH.ifExistsInTaParticipantsListTable(cCode, eventCode, partcipantID, dataModel.getDate())) {
                sqlH.editTaParticipantsListTable(cCode, eventCode, partcipantID, mIdCategorisCode, partcipantName, organationCode, str_gender, partCatCode, positionCode, amSession, pmSession, dataModel.getDate(), mTAMasterData.getTaGroupCode(), entryBy, entryDate);
            } else {
                sqlH.addTaParticipantsListTable(cCode, eventCode, partcipantID, mIdCategorisCode, partcipantName, organationCode, str_gender, partCatCode, positionCode, amSession, pmSession, dataModel.getDate(), mTAMasterData.getTaGroupCode(), entryBy, entryDate);
            }

        }

        Toast.makeText(mContext, "Save Successfully ", Toast.LENGTH_SHORT).show();

        continueDialog();
    }


    private void loadGender() {

        spGender = (Spinner) findViewById(R.id.spGender);
        ArrayAdapter<CharSequence> adtGender = ArrayAdapter.createFromResource(
                this, R.array.spGenderItem, R.layout.spinner_layout);

        adtGender.setDropDownViewResource(R.layout.spinner_layout);
        spGender.setAdapter(adtGender);

        if (gender == null || !gender.equals("M"))
            gender = "Female";
        else
            gender = "Male";


        spGender.setSelection(getSpinnerIndex(spGender, gender));

        if (mEligiableBenef != null)
            spGender.setEnabled(false);
        else
            spGender.setEnabled(true);

        spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                str_gender = parent.getItemAtPosition(position).toString();

                if (str_gender.equals("Male"))
                    str_gender = "M";
                else
                    str_gender = "F";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // I suggest to use Joda-Time for that. Add one day at a time until reaching the end date.
    private void dateGenerator() {


        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyyy", Locale.ENGLISH);
        try {

            SimpleDateFormat inputFormat;
            SharedPreferences settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
            boolean syncMode = settings.getBoolean(UtilClass.SYNC_MODE_KEY, true);

            if (syncMode){
                inputFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
            }else {
                inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
            }



            Calendar calendar = Calendar.getInstance();
            calendar.setTime(inputFormat.parse(mTAMasterData.getStartDate()));
            String startTimestr = dateFormat.format(calendar.getTime());


            calendar.setTime(inputFormat.parse(mTAMasterData.getEndDate()));
            String endTimestr = dateFormat.format(calendar.getTime());


            DateTime startDate = new DateTime(dateFormat.parse(startTimestr));
            DateTime endDate = new DateTime(dateFormat.parse(endTimestr));
            int days = Days.daysBetween(startDate, endDate).getDays();


            for (int i = 0; i <= days; i++) {

                TA_ParticipientAttendenceDateTimeDataModel data = new TA_ParticipientAttendenceDateTimeDataModel();
                DateTime d = startDate.withFieldAdded(DurationFieldType.days(), i);

                DateTimeFormatter fmt = DateTimeFormat.forPattern("MM-dd-yyyy");


                data.setDate(fmt.print(d));
                mDatesString.add(data);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (mEligiableBenef != null) {
            List<TA_ParticipantsListDataModel> dataModels = sqlH.getsingleTaParticipantsListTableRecordsAttendance(mTAMasterData.getcCode(), mTAMasterData.getEventCode(), mEligiableBenef.getNewId(), mIdCategorisCode);


            if (dataModels != null && dataModels.size() > 0) {
                for (int i = 0; i < dataModels.size(); i++) {

                    for (int j = 0; j < mDatesString.size(); j++) {

                        if (dataModels.get(i).getAtdnDate().equals(mDatesString.get(j).getDate())) {
                            mDatesString.get(j).setChecked(true);
                            mDatesString.get(j).setAmSession(dataModels.get(i).getAmSession().equals("1"));
                            mDatesString.get(j).setPmSession(dataModels.get(i).getPmSession().equals("1"));
                        }

                    }

                }

            }
        }

        adapter = new TA_ParticipientAttendenceDateTimeAdapter((Activity) AddTaParticipaintActivity.this, mDatesString);

        //  use below code to debug

        if (adapter.getCount() > 0) {
            if (adapter.getCount() != 0) {
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
            } else {
                new ADNotificationManager().showInfromDialog(mContext, "NO Data", "No data Found");
            }
        }

    }


    /**
     * This method show a dialog to ask user to collect the survey data again.
     * If user press yes then  this method  will invoke the
     * to start to collect response again.
     * For continuing to a new question series, no need to show the previous image. So I just make the global variable  null
     */
    private void continueDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle("Continue !!");

        // On pressing Settings button
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                continueProcess();


            }
        });

        // Setting Dialog Message
        alertDialog.setMessage("Do you want to continue ?");

        // on pressing cancel button
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(mContext, TrainingActivity.class);
                finish();
                startActivity(intent);
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    private void continueProcess() {

        Intent intent = new Intent(mContext, IdTypeSelection.class);
        intent.putExtra(KEY.EVENT_INDEX_DATA_OBJECT_KEY, mTAMasterData);
        finish();
        startActivity(intent);


    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ta_participaint);
        initial();

        dateGenerator();

        setText();
        loadGender();

        setListener();
        loadOrdinationRadioButtons();
        loadCategoriesRadioButtons();
        loadPositionRadioButtons();

        if (mEligiableBenef != null)
            restoreThePreviousSavedValue();
    }
}
