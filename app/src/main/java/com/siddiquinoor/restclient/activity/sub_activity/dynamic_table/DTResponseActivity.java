package com.siddiquinoor.restclient.activity.sub_activity.dynamic_table;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.DynamicTable;
import com.siddiquinoor.restclient.data_model.adapters.AssignDataModel;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.data_model.adapters.DTQTableDataModel;
import com.siddiquinoor.restclient.views.adapters.DynamicTableQusDataModelAdapter;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.spinner.SpinnerLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class DTResponseActivity extends BaseActivity {

    private Spinner spTableName, spDtMonth;
    private Spinner spAward;
    private Spinner spProgram;
    private TextView tvActivityTitle;
    private TextView tvDate;
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private SQLiteHandler sqlH;
    private Context mContext = DTResponseActivity.this;
    private String idAward;
    private String strAward;
    private String idDonor;
    private String idTable;
    private String strTable;
    private String idProgram;
    private String strProgram;
    // private ListView lv_DT_QList;
    private Button btn_goToQustion, btn_BackToQustion;
    private AssignDataModel.DynamicDataIndexDataModel dyIndex;
    DynamicTableQusDataModelAdapter adapter = null;
    private String idMonth;
    private String strMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dt_response);
        inti();
        Intent intent = getIntent();
        dyIndex = intent.getParcelableExtra(KEY.DYNAMIC_INDEX_DATA_OBJECT_KEY);
        idAward = dyIndex.getAwardCode();
        strAward = dyIndex.getAwardName();
        idTable = dyIndex.getDtBasicCode();
        strTable = dyIndex.getDtTittle();
        idProgram = dyIndex.getProgramCode();
        strProgram = dyIndex.getProgramName();
        tvActivityTitle.setText(dyIndex.getPrgActivityTitle());

        loadTable(dyIndex.getcCode());
        /**
         * get Current Date by System
         * and set Into tv
         */
        getCurrentDate();
        setListener();
    }

    private void inti() {
        viewReference();
        sqlH = new SQLiteHandler(mContext);
    }

    private void setListener() {
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });
        btn_goToQustion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!idMonth.equals("00")) {
                    String regDate = tvDate.getText().toString();
                    HashMap<String, String> dateRange = sqlH.getDynamicTableDateRange(dyIndex.getcCode(), idMonth);
                    String start_date = dateRange.get("sdate");
                    String end_date = dateRange.get("edate");

                    if (regDate.length() > 0 && start_date != null && end_date != null) {
                        try {
                            if (!getValidDateRange(regDate, start_date, end_date,false)) {

//                            dialog.showErrorDialog(mContext, "Registration date is not within the valid range. Save attempt denied.");
                                Toast.makeText(mContext, "date is not within the valid range", Toast.LENGTH_SHORT).show();
                            } else {
                                GoToDT_QuestionPage();
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Toast.makeText(mContext, "Select Month", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_BackToQustion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DynamicTable.class);
                intent.putExtra(KEY.COUNTRY_ID, dyIndex.getcCode());
                finish();
                startActivity(intent);
            }
        });

    }

    private void GoToDT_QuestionPage() {
        /**
         * resetting Adm Award Code
         */
        dyIndex.setAwardCode(idAward);

        /**
         * set opMonth Code & op month Date
         */
        dyIndex.setOpMonthCode(idMonth);
        dyIndex.setOpMonthDate(tvDate.getText().toString());
        if (adapter != null) {
            Intent intent = new Intent(mContext, DTResponseRecordingActivity.class);
            intent.putExtra(KEY.DYNAMIC_INDEX_DATA_OBJECT_KEY, dyIndex);
            intent.putExtra(KEY.DYNAMIC_T_QUES_SIZE, adapter.getCount());

            startActivity(intent);
        }
    }

    /**
     * Refer the java view Object
     */

    private void viewReference() {

        spTableName = (Spinner) findViewById(R.id.sp_dtResponse_table_name);
        spAward = (Spinner) findViewById(R.id.sp_dtResponse_award);
        spProgram = (Spinner) findViewById(R.id.sp_dtResponse_program);
        spDtMonth = (Spinner) findViewById(R.id.sp_dtMonth);
        tvActivityTitle = (TextView) findViewById(R.id.tv_dtResponse_activity_title);
        tvDate = (TextView) findViewById(R.id.txt_dtResponse_date);
//        lv_DT_QList = (ListView) findViewById(R.id.lv_DTQList);
        btn_goToQustion = (Button) findViewById(R.id.btnHomeFooter);
        btn_BackToQustion = (Button) findViewById(R.id.btnRegisterFooter);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addIconGoToQustionButton() {


        btn_goToQustion.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.goto_forward);
        btn_goToQustion.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, btn_goToQustion);


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addIconBackButton() {


        btn_BackToQustion.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.goto_back);
        btn_BackToQustion.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, btn_BackToQustion);


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
        addIconGoToQustionButton();
        addIconBackButton();
    }


    private void loadTable(final String cCode) {

        int position = 0;
        String sql = "SELECT " + "dtCPgr." + SQLiteHandler.DT_BASIC_COL + " AS dtBasicCode  " +
                " , " + "dtB." + SQLiteHandler.DT_TITLE_COL + "  " +
                "  FROM " + SQLiteHandler.DT_COUNTRY_PROGRAM_TABLE + " AS dtCPgr  " +
                " LEFT JOIN " + SQLiteHandler.DT_BASIC_TABLE + "  as dtB  " +
                " ON dtB." + SQLiteHandler.DT_BASIC_COL + " = dtCpgr." + SQLiteHandler.DT_BASIC_COL + "   " +
                " WHERE dtCPgr." + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "' ";


        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, sql, null, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listProgram);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spTableName.setAdapter(dataAdapter);


        if (idTable != null) {
            for (int i = 0; i < spTableName.getCount(); i++) {
                String table = spTableName.getItemAtPosition(i).toString();
                if (table.equals(strTable)) {
                    position = i;
                }
            }

            spTableName.setSelection(position);
            /**
             * disable spinner
             */
            spTableName.setEnabled(false);
        }


        spTableName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strTable = ((SpinnerHelper) spTableName.getSelectedItem()).getValue();
                idTable = ((SpinnerHelper) spTableName.getSelectedItem()).getId();
                loadAward(cCode);
                /**
                 * Do not delete # loadDT_questionView method
                 */
                loadDT_questionView(idTable);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    /**
     * LOAD :: Award
     */
    private void loadAward(final String cCode) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.ADM_COUNTRY_AWARD_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "'";

        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.ADM_COUNTRY_AWARD_TABLE, criteria, null, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listAward);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spAward.setAdapter(dataAdapter);


        if (idAward != null) {
            for (int i = 0; i < spAward.getCount(); i++) {
                String award = spAward.getItemAtPosition(i).toString();
                if (award.equals(strAward)) {
                    position = i;
                }
            }
            spAward.setSelection(position);
            /**
             * disable spinner
             */
            spAward.setEnabled(false);
        }


        spAward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strAward = ((SpinnerHelper) spAward.getSelectedItem()).getValue();
                String awardCode = ((SpinnerHelper) spAward.getSelectedItem()).getId();


                if (awardCode.length() > 2) {
                    idDonor = awardCode.substring(0, 2);
                    idAward = awardCode.substring(2);

                    loadProgram(cCode, idDonor, idAward);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Award Spinner

    /**
     * LOAD :: Program
     * todo: change the sql
     */
    private void loadProgram(final String cCode, final String donorCode, final String awardCode) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.ADM_AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.ADM_DONOR_CODE_COL + "='" + donorCode + "'";

        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE, criteria, null, false);
/**
 *  replace the Select Program by Cross Cutting
 */
        listProgram.remove(0);
        listProgram.add(0, new SpinnerHelper(0, "000", "Cross Cutting"));

        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listProgram);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spProgram.setAdapter(dataAdapter);


        if (idProgram != null) {
            for (int i = 0; i < spProgram.getCount(); i++) {
                String prog = spProgram.getItemAtPosition(i).toString();
                if (prog.equals(strProgram)) {
                    position = i;
                }
            }
            spProgram.setSelection(position);
            /**
             * disable spinner
             */
            spProgram.setEnabled(false);
        }


        spProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getValue();
                idProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getId();

                loadDtMonth(cCode, dyIndex.getOpMode());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    private void loadDtMonth(final String cCode, String opCode) {
        SpinnerLoader.loadDtMonthLoader(mContext,sqlH,spDtMonth, cCode,opCode,idMonth,strMonth);


        spDtMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strMonth = ((SpinnerHelper) spDtMonth.getSelectedItem()).getValue();
                idMonth = ((SpinnerHelper) spDtMonth.getSelectedItem()).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    /**
     * Load :: list View
     * the list is not needed
     *
     * @param dtBasicCode Dynamic Basic Code
     */

    public void loadDT_questionView(String dtBasicCode) {

        List<DTQTableDataModel> dataList = sqlH.getDynamicQuestionList(dtBasicCode);


        ArrayList<DTQTableDataModel> dataArray = new ArrayList<DTQTableDataModel>();
        if (dataList.size() != 0) {

            dataArray.clear();

            for (DTQTableDataModel data : dataList) {
                /**
                 * add contacts data in arrayList
                 * */

                dataArray.add(data);
            }
            /**
             * Assign the Adapter in list
             * */

            adapter = new DynamicTableQusDataModelAdapter((Activity) mContext, dataArray);
        }

        if (adapter != null) {
            if (adapter.getCount() != 0) {
                adapter.notifyDataSetChanged();
//   /** hide the list View */    lv_DT_QList.setAdapter(adapter);
            } /*else {
                new ADNotificationManager().showInfromDialog(mContext, "NO Data", "No data Found");

            }*/

        }

    }

    /**
     * Date & time Session
     */
    public void getCurrentDate() {
        Calendar calendar = Calendar.getInstance();

        String strDate = mFormat.format(calendar.getTime());
        displayDate(strDate);
    }

    private void displayDate(String strDate) {
        tvDate.setText(strDate);
    }

    public void setDate() {
        new DatePickerDialog(mContext, datepickerD, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    /**
     * date Time picker Listener
     */

    DatePickerDialog.OnDateSetListener datepickerD = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDate();
        }
    };

    public void updateDate() {
        displayDate(mFormat.format(calendar.getTime()));

    }


}
