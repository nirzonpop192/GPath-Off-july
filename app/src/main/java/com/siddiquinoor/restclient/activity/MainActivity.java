package com.siddiquinoor.restclient.activity;

/**
 * Activity for presenting the Dashboard of the application
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.3.0
 * @since 1.0
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.controller.AppController;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.SyncDatabase;
import com.siddiquinoor.restclient.network.ConnectionDetector;
import com.siddiquinoor.restclient.version.VersionStateChangeReceiver;
import com.siddiquinoor.restclient.parse.Parser;
import com.siddiquinoor.restclient.utils.FileUtils;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.data_model.adapters.DistributionSaveDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;
import com.siddiquinoor.restclient.views.notifications.AlertDialogManager;
import com.siddiquinoor.restclient.views.notifications.CustomToast;
import com.siddiquinoor.restclient.views.spinner.SpinnerLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    public static final String LIBERIA_COUNTRY_CODE = "0004";
    public static final int TOTAL_NO_OF_TABLE = 88;
    private static ProgressDialog pDialog;

    private final String TAG = MainActivity.class.getSimpleName();


    private SQLiteHandler db;
    // Connection detector class
    ConnectionDetector cd;
    // flag for Internet connection status
    Boolean isInternetPresent = false;

    public static Activity main_activity;
    private View my_view;

    AlertDialogManager alert;


    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;
    private Button btnDynamicData;

    private Button btnNewReg;
    private Button btnSummaryRep;
    ///private Button btnViewRec;
    private Button btnSyncRec;
    private SQLiteHandler sqlH;
    private Spinner spCountry;
    private String idCountry;
    private String strCountry;
    private static final String Y = "YES";
    private static final String N = "NO";
    // button declear here
    private Button btnService, btnGPS, btnAssign, btnGraduation, btnCardRequest, btnDistribution, btnGroup, btnTrainActivity;

    private ProgressDialog progressDialog;

    private TextView tvGeoData, tvLastSync, tvSyncRequired, tvOperationMode, tvDeviceId;
    private Context mContext;

    private int progressIncremental;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_activity = this;
        mContext = this;
        spCountry = (Spinner) findViewById(R.id.spDCountry);// add the spinner
        sqlH = new SQLiteHandler(this); //  it should be other wise it will show null point Exception


        db = new SQLiteHandler(getApplicationContext());                                            // SqLite database handler


        cd = new ConnectionDetector(getApplicationContext());                                        // connection manager

        viewReference();                                                                            // find View by ID for all Views


        SharedPreferences settings;

        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        boolean isFirstRun = settings.getBoolean(IS_APP_FIRST_RUN, false);

        showOperationModelLabel(settings);
        txtName.setText(getUserName());
        tvLastSync.setText(db.getLastSyncStatus());


        String deviceId = UtilClass.getDeviceId(mContext);                                      // get mac address

        tvDeviceId.setText(deviceId);



     /*   btnViewRec = (Button) findViewById(R.id.btnViewRecord);
        btnViewRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iView = new Intent(getApplicationContext(), MW_RegisterViewRecord.class);
                startActivity(iView);
                //main_activity.finish();
            }
        });*/


        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });


        buttonSetListener();
        // when the MainActivity run for first time  The JSon Data inject to the
        // SQLite database from text file
        if (isFirstRun) {
            SharedPreferences.Editor editor;
            settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
            editor = settings.edit();
            new Inject_All_DataIntoSQLite().execute();
            editor.putBoolean(IS_APP_FIRST_RUN, false);
            editor.apply();
        }
        loadCountry();
        setAllButtonDisabled();
        viewAccessController();
        //showOperationModelLabel(settings);
/**
 * back up db
 */
        final Button restorDb = (Button) findViewById(R.id.btnRestoreDB);

        // for test disable it
//        restorDb.setVisibility(View.GONE);                                                          //no dded this button any more
        restorDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restoreDataBase();


            }
        });

        Button btnExportDataBase = (Button) findViewById(R.id.btnEXTDB);


        boolean syncMode = settings.getBoolean(UtilClass.SYNC_MODE_KEY, true);
        if (syncMode) {
            btnExportDataBase.setVisibility(View.GONE);
        } else
            btnExportDataBase.setVisibility(View.VISIBLE);

        btnExportDataBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String root = Environment.getExternalStorageDirectory().toString();

                File sd = new File(root + "/GpathOffline/");                                             // get the internal root directories root/GpathOffline path

                deleteRecursive(sd);

                // Check this link also how to delete folder from internal storage in android?.


                SQLiteHandler sqLiteHandler = new SQLiteHandler(MainActivity.this, 1);

                sqLiteHandler.insertIntoExportDataBase(MainActivity.this);


                String currentDBPath = "/data/data/" + getPackageName() + "/databases/"
                        + SQLiteHandler.EXTERNAL_DATABASE_NAME;


                // file exporting naming conversation
                String subNpubId = sqlH.getSubscriberNPublisherID(UtilClass.getDeviceId(mContext));

                if (!subNpubId.equals("") && subNpubId.length() != 0) {
                    String endDate = "";
                    try {
                        endDate = getDateTime(true);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    SharedPreferences settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                    String startDate = settings.getString(UtilClass.IMPORT_DATE_TIME_KEY, "");

//                String backupdbName = FileUtils.EXPORT_GOF_FILE;                                  // don't delete the code  eta o lagte pare
                    String backupdbName = subNpubId + "_" + getStaffID() + "_" + startDate + "_" + endDate + ".gof";
                    FileUtils.dataBaseCopyFromPackageToInternalRoot(mContext, currentDBPath, backupdbName, "Export Successful! ");

//                db.clearUploadSyntaxTable();                                                      // ei method tha na delete kora valo kokhon er projon pore buja jaitese na
                    logoutUser();

                } else {
                    ADNotificationManager dialog = new ADNotificationManager();
                    dialog.showInvalidDialog(mContext, "Invalid Attempt ", "This device is not registered to export !");

                }


            }
        });

        /**
         * import db
         */
        Button btnImportDatabase = (Button) findViewById(R.id.btnImport);

        btnImportDatabase.setVisibility(View.GONE);
        String path = Environment.getExternalStorageDirectory().getPath() + "/" + SQLiteHandler.DATABASE_NAME;
        File newDb = new File(path);
        if (newDb.exists()) {
            btnImportDatabase.setVisibility(View.VISIBLE);
        }
        btnImportDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new ImportDbAsycnTask().execute();
            }
        });

//       String versionName= VersionUtils.getVersionName(getApplicationContext());                  delete it

        callBroadCastReceiverToCheck();


    }                                                                                               // end of onCreate


    public static void whenDComplete() {

    }


    private void callBroadCastReceiverToCheck() {
        Intent registerBroadcast = new Intent(this, VersionStateChangeReceiver.class);
        sendBroadcast(registerBroadcast);
    }

    void deleteRecursive(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();

    }

    private void restoreDataBase() {
        String dbBy = getStaffID();
        String dbByName = getUserName();
        String backupDate = null;
        try {
            backupDate = getDateTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String destinationDbPath = "G_path_" + dbBy + "-" + dbByName + "_" + backupDate + ".db";

        String sourceDbPath = "/data/data/" + getPackageName() + "/databases/" + SQLiteHandler.DATABASE_NAME;

        FileUtils.dataBaseCopyFromPackageToInternalRoot(mContext, sourceDbPath, destinationDbPath, "Import Successful! " + destinationDbPath);

    }


    private boolean importDataBase() {
        boolean flag = false;

        try {
            String path = Environment.getExternalStorageDirectory().getPath() + "/" + SQLiteHandler.DATABASE_NAME;


            File newDb = new File(path);
            if (newDb.exists()) {
                flag = sqlH.importDatabase(path, MainActivity.this);
                File file = new File(path);                                                         // delete
                file.delete();

                sqlH.reCreateSurveyTable();
            } else flag = false;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }


    private class ImportDbAsycnTask extends AsyncTask<Void, Integer, String> {

        private boolean importFlag;


        private ImportDbAsycnTask() {
            importFlag = false;
        }

        @Override
        protected String doInBackground(Void... params) {
            importFlag = importDataBase();


            return "successes";
        }

        /**
         * Initiate the dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            startProgressBar("Data Importing");

        }


        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            hideProgressBar();
            String ddf = importFlag ? "Imported " : " is not imported ";
            CustomToast.show(mContext, "DataBase : " + ddf);


            logoutUser();

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

    /**
     * this method bring the database front Internal memory
     */
    public void newBackupDBFromApp() {
        try {
            File sd = Environment.getExternalStorageDirectory();
            //   File data = Environment.getDataDirectory();

            String dbBy = getStaffID();
            String dbByName = getUserName();
            String backupDate = getDateTime();
            String backupdbName = "G_path_" + dbBy + "-" + dbByName + "_" + backupDate + ".db";

            if (sd.canWrite()) {
                String currentDBPath = "/data/data/" + getPackageName() + "/databases/pci";
                String backupDBPath = backupdbName;
                File currentDB = new File(currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                    Toast.makeText(getApplicationContext(), "Import Successful! " + backupDB.getAbsolutePath(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Backup Failed!", Toast.LENGTH_SHORT)
                    .show();

        }
    }


    /**
     * <p>
     * This method control the button view with respect to operation
     * </p>
     */

    private void viewAccessController() {
        // get operation mode from shared and preference
//        int operationMode = settings.getInt(UtilClass.OPERATION_MODE, 0);

        String operationModeName = sqlH.getDeviceOperationModeName();
        switch (operationModeName) {
            case UtilClass.REGISTRATION_OPERATION_MODE_NAME:
                btnNewReg.setEnabled(true);
                btnAssign.setEnabled(true);
                btnGraduation.setEnabled(true);
                btnGroup.setEnabled(true);


                break;
            case UtilClass.DISTRIBUTION_OPERATION_MODE_NAME:
                btnDistribution.setEnabled(true);

                break;
            case UtilClass.SERVICE_OPERATION_MODE_NAME:
                btnService.setEnabled(true);

                break;

            case UtilClass.OTHER_OPERATION_MODE_NAME:
                btnDynamicData.setEnabled(true);
                btnGPS.setEnabled(true);
                btnSummaryRep.setEnabled(false);
                break;

            case UtilClass.TRANING_N_ACTIVITY_OPERATION_MODE_NAME:
                btnTrainActivity.setEnabled(true);
                btnSummaryRep.setEnabled(false);
                break;
        }
    }


    private void buttonSetListener() {
        btnNewReg.setOnClickListener(this);
        btnSummaryRep.setOnClickListener(this);
        btnCardRequest.setOnClickListener(this);
        btnSyncRec.setOnClickListener(this);
        btnDistribution.setOnClickListener(this);
        btnService.setOnClickListener(this);
        btnGPS.setOnClickListener(this);
        btnGraduation.setOnClickListener(this);
        btnAssign.setOnClickListener(this);
        btnGroup.setOnClickListener(this);
        btnDynamicData.setOnClickListener(this);
        btnTrainActivity.setOnClickListener(this);

    }

    /**
     * <p>
     * set all button disable first expect syn & summary report button
     * </p>
     */
    private void setAllButtonDisabled() {
        btnNewReg.setEnabled(false);
        btnCardRequest.setEnabled(false);
        btnDistribution.setEnabled(false);
        btnService.setEnabled(false);
        btnGPS.setEnabled(false);
        btnGraduation.setEnabled(false);
        btnAssign.setEnabled(false);
        btnGroup.setEnabled(false);
        btnDynamicData.setEnabled(false);
        btnTrainActivity.setEnabled(false);

    }

    private void viewReference() {


        txtName = (TextView) findViewById(R.id.user_name);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        tvGeoData = (TextView) findViewById(R.id.tv_geo_data_1);
        tvGeoData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvGeoData.setMovementMethod(new ScrollingMovementMethod());
            }
        });
        //
        tvLastSync = (TextView) findViewById(R.id.tv_last_sync);
        tvSyncRequired = (TextView) findViewById(R.id.tv_sync_required);
        btnNewReg = (Button) findViewById(R.id.btnNewReg);
        btnSummaryRep = (Button) findViewById(R.id.btnSummaryReport);
        btnCardRequest = (Button) findViewById(R.id.btnCardRequest);
        btnGPS = (Button) findViewById(R.id.btnGPS);
        btnSyncRec = (Button) findViewById(R.id.btnSyncRecord);
        btnGraduation = (Button) findViewById(R.id.btnGraduation);
        btnAssign = (Button) findViewById(R.id.btnAssinge);
        btnService = (Button) findViewById(R.id.btnService);
        btnDistribution = (Button) findViewById(R.id.btnDistribution);
        tvLastSync = (TextView) findViewById(R.id.tv_last_sync);
        btnGroup = (Button) findViewById(R.id.btnGroup);
        btnTrainActivity = (Button) findViewById(R.id.btnTrainingActivity);
        btnDynamicData = (Button) findViewById(R.id.btnDynamicData);
        tvOperationMode = (TextView) findViewById(R.id.tv_operation_mode);
        tvGeoData = (TextView) findViewById(R.id.tv_geo_data_1);
        tvDeviceId = (TextView) findViewById(R.id.tv_deviceId);


        if (db.selectUploadSyntextRowCount() > 0) {
            tvSyncRequired.setText(Y);
        } else {
            tvSyncRequired.setText(N);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGroup:

                Intent iGroupSear = new Intent(getApplicationContext(), GroupSearchPage.class);

                iGroupSear.putExtra(KEY.COUNTRY_ID, idCountry);
                iGroupSear.putExtra(KEY.STR_COUNTRY, strCountry);
                startActivity(iGroupSear);

                break;
            case R.id.btnNewReg:
                Intent iReg;
                if (idCountry.equals(LIBERIA_COUNTRY_CODE)) {
                    iReg = new Intent(getApplicationContext(), RegisterLiberia.class);

                    iReg.putExtra("country_code", idCountry);
                    iReg.putExtra("country_name", strCountry);
                    startActivity(iReg);

                } else {
                    iReg = new Intent(getApplicationContext(), Register.class);

                    iReg.putExtra("country_code", idCountry);
                    iReg.putExtra("country_name", strCountry);
                    startActivity(iReg);
                }
                break;
            case R.id.btnSummaryReport:
                Intent iSumm = new Intent(getApplicationContext(), SummaryMenuActivity.class);

                iSumm.putExtra(KEY.COUNTRY_ID, idCountry);
                iSumm.putExtra(KEY.STR_COUNTRY, strCountry);
                startActivity(iSumm);
                //main_activity.finish();
                break;
            case R.id.btnSyncRecord:


                SharedPreferences settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                boolean syncProcessOnGoing = settings.getBoolean(UtilClass.PROCESS_ON_GOING_KEY, false);
                if (!syncProcessOnGoing)
                    synchronizationProcess(v);

                break;

            case R.id.btnCardRequest:
                finish();
                Intent iCardR = new Intent(getApplicationContext(), CardRequestActivity.class);

                iCardR.putExtra("ID_COUNTRY", idCountry);
                iCardR.putExtra("STR_COUNTRY", strCountry);
                startActivity(iCardR);
                break;
            case R.id.btnDistribution:
                finish();
                Intent iDist = new Intent(getApplicationContext(), DistributionActivity.class);

                iDist.putExtra(KEY.COUNTRY_ID, idCountry);
                iDist.putExtra(KEY.STR_COUNTRY, strCountry);
                iDist.putExtra(KEY.DIR_CLASS_NAME_KEY, "MainActivity");
                startActivity(iDist);
                break;

            case R.id.btnService:
                finish();
                Intent iSer = new Intent(getApplicationContext(), ServiceActivity.class);

                iSer.putExtra(KEY.COUNTRY_ID, idCountry);
                iSer.putExtra(KEY.STR_COUNTRY, strCountry);
                iSer.putExtra(KEY.DIR_CLASS_NAME_KEY, "MainActivity");
                startActivity(iSer);
                break;
            case R.id.btnGPS:

                finish();
                Intent iMap = new Intent(getApplicationContext(), GPSLocationSearchPage.class);

                iMap.putExtra(KEY.COUNTRY_ID, idCountry);
                iMap.putExtra(KEY.STR_COUNTRY, strCountry);
                iMap.putExtra(KEY.DIR_CLASS_NAME_KEY, "MainActivity");
                startActivity(iMap);
                break;

            case R.id.btnGraduation:
                finish();
                /**
                 *  when user press the assign page it will take to the member page
                 */

                Intent iMemSearchPage_1 = new Intent(getApplicationContext(), MemberSearchPage.class);
                // // TODO: 4/4/2017  check intent  then remove the object parameter
                iMemSearchPage_1.putExtra(KEY.COUNTRY_ID, idCountry);
                iMemSearchPage_1.putExtra(KEY.DIR_CLASS_NAME_KEY, "MainActivity");

                startActivity(iMemSearchPage_1);

                break;

            case R.id.btnAssinge:
                finish();
                /**
                 *  when user press the assign page it will take to the member page
                 */


                Intent iMemSearchPage = new Intent(getApplicationContext(), MemberSearchPage.class);
                iMemSearchPage.putExtra(KEY.COUNTRY_ID, idCountry);
                iMemSearchPage.putExtra(KEY.STR_COUNTRY, strCountry);
                iMemSearchPage.putExtra(KEY.DIR_CLASS_NAME_KEY, "MainActivity");


                startActivity(iMemSearchPage);
                break;
            case R.id.btnDynamicData:
                finish();
                Intent iDynamicData = new Intent(getApplicationContext(), DynamicTable.class);
                iDynamicData.putExtra(KEY.COUNTRY_ID, idCountry);
                startActivity(iDynamicData);
                break;
            case R.id.btnTrainingActivity:
                finish();
                Intent iTrainingActivity = new Intent(getApplicationContext(), TrainingActivity.class);
                startActivity(iTrainingActivity);
                break;
        }

    }

    private void synchronizeData(View v) {
        final AppController globalVariable = (AppController) getApplicationContext();
        globalVariable.setMainViewContext(v);
        MainTask main_task = new MainTask();
        main_task.execute();
    }

    private void synchronizationProcess(View v) {
        /**
         * check the on line mode is on or not
         */
        SharedPreferences settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        boolean syncMode = settings.getBoolean(UtilClass.SYNC_MODE_KEY, true);
        if (syncMode) {
            my_view = v;
            isInternetPresent = cd.isConnectingToInternet();

            if (isInternetPresent) {
                synchronizeData(my_view);

                /**
                 * save the synchronize time
                 */
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
                Date now = new Date();
                String SyncDate = date.format(now);
                db.insertIntoLastSyncTraceStatus(getUserID(), getUserName(), SyncDate);

                tvSyncRequired.setText(N);
                if (db.getLastSyncStatus().equals("")) {
                    tvLastSync.setText("N/A");
                } else {
                    tvLastSync.setText(db.getLastSyncStatus());
                }

            } else {

                showAlert("Check your internet connectivity!!");
            }

        } else
            showAlert("Device is not configure for internet connectivity !!");
    }

    private class MainTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                //doSomeTasks();
            } catch (Exception e) {
                Log.e("Async task", "Task Failed " + e);
            }
            return "Success";
        }

        @Override
        protected void onPreExecute() {
            SyncDatabase sync = new SyncDatabase(getApplicationContext(), main_activity);
            sync.startTask();
        }


        @Override
        protected void onPostExecute(String result) {
            //finalizeSync();
            Log.d(TAG, "Task Finish");
            //SyncDatabase.pDialog.dismiss();
        }


    } // end Asynchronous class MainTask


    /**
     * LOAD :: COUNTRY
     */
    private void loadCountry() {

        int operationMode = sqlH.getDeviceOperationModeCode();


        SpinnerLoader.loadCountryLoader(mContext, sqlH, spCountry, operationMode, idCountry, strCountry);
        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCountry = ((SpinnerHelper) spCountry.getSelectedItem()).getValue();
                idCountry = ((SpinnerHelper) spCountry.getSelectedItem()).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private class Inject_RegNAssProgSrvDataIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new Inject_DynamicTableIntoSQLite().execute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... params) {

            String retrieveData = readDataFromFile("reg_ass_prog_srv_data");
            try {


                JSONObject jObj = new JSONObject(retrieveData);

                if (!jObj.isNull(Parser.REG_M_ASSIGN_PROG_SRV_JSON_A)) {
                    Parser.regNAssignProgSrvParser(jObj.getJSONArray(Parser.REG_M_ASSIGN_PROG_SRV_JSON_A), db);

                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.REGN_PW_JSON_A)) {
                    Parser.regNPWParser(jObj.getJSONArray(Parser.REGN_PW_JSON_A), db);

                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.REGN_LM_JSON_A)) {
                    Parser.regNLMParser(jObj.getJSONArray(Parser.REGN_LM_JSON_A), db);

                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.REGN_CU_2_JSON_A)) {
                    Parser.regNCU2Parser(jObj.getJSONArray(Parser.REGN_CU_2_JSON_A), db);

                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.REGN_CA_2)) {
                    Parser.RegN_CA2Parser(jObj.getJSONArray(Parser.REGN_CA_2), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.REG_N_AGR_JSON_A)) {
                    Parser.RegN_AGRParser(jObj.getJSONArray(Parser.REG_N_AGR_JSON_A), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.REG_N_CT_JSON_A)) {
                    Parser.RegN_CTParser(jObj.getJSONArray(Parser.REG_N_CT_JSON_A), db);
                }


                if (!jObj.isNull("reg_n_ffa")) {
                    Parser.reg_N_FFAParser(jObj.getJSONArray("reg_n_ffa"), db);
                }

                if (!jObj.isNull("reg_n_we")) {
                    Parser.reg_N_WEParser(jObj.getJSONArray("reg_n_we"), db);
                }


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
            return null;

        }
    }


    private class Inject_DynamicTableIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new Inject_TrainingActivityIntoSQLite().execute();

        }

        @Override
        protected Void doInBackground(Void... params) {


            /**
             * Read JSON DATA  from the text file
             * */
            String retrieveData = readDataFromFile(LoginActivity.DYNAMIC_TABLE);

            try {

                /**                 * The total string Convert into JSON object                 * */

                JSONObject jObj = new JSONObject(retrieveData);

                publishProgress(++progressIncremental);

                if (!jObj.isNull("D_T_answer")) {
                    Parser.DTA_Parser(jObj.getJSONArray("D_T_answer"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_basic")) {
                    Parser.DTBasicParser(jObj.getJSONArray("D_T_basic"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_category")) {
                    Parser.DTCategoryParser(jObj.getJSONArray("D_T_category"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_CountryProgram")) {
                    Parser.DTCountryProgramParser(jObj.getJSONArray("D_T_CountryProgram"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_GeoListLevel")) {
                    Parser.DTGeoListLevelParser(jObj.getJSONArray("D_T_GeoListLevel"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_QTable")) {
                    Parser.DTQTableParser(jObj.getJSONArray("D_T_QTable"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_QResMode")) {
                    Parser.DTQResModeParser(jObj.getJSONArray("D_T_QResMode"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_ResponseTable")) {
                    Parser.DTResponseTableParser(jObj.getJSONArray("D_T_ResponseTable"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_TableDefinition")) {
                    Parser.DTTableDefinitionParser(jObj.getJSONArray("D_T_TableDefinition"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_TableListCategory")) {
                    Parser.DTTableListCategoryParser(jObj.getJSONArray("D_T_TableListCategory"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("D_T_LUP")) {
                    Parser.DTLUPParser(jObj.getJSONArray("D_T_LUP"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("DTA_Skip_Table")) {
                    Parser.DTA_Skip_TableParser(jObj.getJSONArray("DTA_Skip_Table"), db);
                }


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }
    }

    private class Inject_TrainingActivityIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new Inject_EnuTableIntoSQLite().execute();

        }

        @Override
        protected Void doInBackground(Void... params) {


            /**
             * Read JSON DATA  from the text file
             * */
            String retrieveData = readDataFromFile(LoginActivity.TRAINING_N_ACTIVITY);

            try {

                /**                 * The total string Convert into JSON object                 * */

                JSONObject jObj = new JSONObject(retrieveData);

                publishProgress(++progressIncremental);

                if (!jObj.isNull("T_A_master")) {
                    Parser.TA_Master_Parser(jObj.getJSONArray("T_A_master"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("T_A_category")) {
                    Parser.T_A_category_Parser(jObj.getJSONArray("T_A_category"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("T_A_eventTopic")) {
                    Parser.T_A_eventTopic_Parser(jObj.getJSONArray("T_A_eventTopic"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("T_A_group")) {
                    Parser.T_A_group_Parser(jObj.getJSONArray("T_A_group"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("T_A_partOrgN")) {
                    Parser.T_A_partOrgN_Parser(jObj.getJSONArray("T_A_partOrgN"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("T_A_posParticipants")) {
                    Parser.T_A_posParticipants_Parser(jObj.getJSONArray("T_A_posParticipants"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("T_A_subGroup")) {
                    Parser.T_A_subGroup_Parser(jObj.getJSONArray("T_A_subGroup"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("T_A_topicChild")) {
                    Parser.T_A_topicChild_Parser(jObj.getJSONArray("T_A_topicChild"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("T_A_topicMaster")) {
                    Parser.T_A_topicMaster_Parser(jObj.getJSONArray("T_A_topicMaster"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("LUP_TAParticipantCat")) {
                    Parser.LUP_TAParticipantCat_Parser(jObj.getJSONArray("LUP_TAParticipantCat"), db);
                }


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }
    }

    private class Inject_EnuTableIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            hideDialog();
            loadCountry();

            // set the user name
            txtName.setText(getUserName());
        }

        @Override
        protected Void doInBackground(Void... params) {


            /**             * Read JSON DATA  from the text file             **/
            String retrieveData = readDataFromFile(LoginActivity.ENU_TABLE);

            try {


                /**                 * The total string Convert into JSON object                 * */

                JSONObject jObj = new JSONObject(retrieveData);
                publishProgress(++progressIncremental);

                if (!jObj.isNull("enu_table")) {
                    Parser.DTEnu_Parser(jObj.getJSONArray("enu_table"), db);
                }

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }
    }


    private class Inject_Reg_HouseH_DataIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            new Inject_Service_DataIntoSQLite().execute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... params) {

            String receiveData = readDataFromFile(LoginActivity.REG_HOUSE_HOLD_DATA);
            /**             * the parsing held by other class             */

            Parser.RegistrationNHHParser(receiveData, db);


            return null;
        }
    }

    private class Inject_Service_DataIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // insert assigne data
            new Inject_Reg_Member_DataIntoSQLite().execute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... params) {

            String retreiveData = readDataFromFile(LoginActivity.SERVICE_DATA);


            /**
             * The total string Convert into JSON object
             * */

            try {
                JSONObject jObj = new JSONObject(retreiveData);

                if (!jObj.isNull(Parser.SERVICE_TABLE_JSON_A)) {
                    JSONArray services_data = jObj.getJSONArray(Parser.SERVICE_TABLE_JSON_A);
                    publishProgress(++progressIncremental);
                    Parser.SrvTableParser(services_data, db);
                }


                if (!jObj.isNull("service_exe_table")) {// this is not servie
                    JSONArray services_exe_table = jObj.getJSONArray("service_exe_table");

                    publishProgress(++progressIncremental);
                    Parser.SrvExtTableParser(services_exe_table, db);
                }
            } catch (Exception e) {
                Log.d(TAG, "Exception : " + e);
                e.printStackTrace();
            }


            return null;
        }
    }

    /**
     * inject Reg member data
     */

    private class Inject_Reg_Member_DataIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            new Inject_Reg_Member_Prog_Grp_DataIntoSQLite().execute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... params) {

            String retreiveData = readDataFromFile(LoginActivity.REG_MEMBER_DATA);
            Parser.RegNMemberParser(retreiveData, db);
            publishProgress(++progressIncremental);

            return null;
        }
    }


    /**
     * inject Reg member Program  Group data
     */

    private class Inject_Reg_Member_Prog_Grp_DataIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            new Inject_RegNAssProgSrvDataIntoSQLite().execute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... params) {

            String retrieveData = readDataFromFile(LoginActivity.REG_MEMBER_PROG_GROUP_DATA);
            // todo change the  structure
            Parser.RegNMemProGrpParser(retrieveData, db);
            publishProgress(++progressIncremental);

            Parser.GpsLocationContentParser(retrieveData, db);
            publishProgress(++progressIncremental);

            Parser.SrvSpecificTableParser(retrieveData, db);
            publishProgress(++progressIncremental);


            return null;
        }
    }


    private class Inject_All_DataIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            /**
             * Read JSON DATA  from the text file
             * */
            String retrieveData = readDataFromFile(LoginActivity.ALL_DATA);

            try {
                int size;
                /**
                 * The total string Convert into JSON object
                 * */

                JSONObject jObj = new JSONObject(retrieveData);

                String user_id = jObj.getString(Parser.USR_ID);
                JSONObject user = jObj.getJSONObject(Parser.USER_JSON_A);
                String country_code = user.getString(Parser.ADM_COUNTRY_CODE);
                String login_name = user.getString(Parser.USR_LOG_IN_NAME);
                String login_pw = user.getString(Parser.USR_LOG_IN_PW);
                String first_name = user.getString(Parser.USR_FIRST_NAME);
                String last_name = user.getString(Parser.USR_LAST_NAME);
                String email = user.getString(Parser.USR_EMAIL);
                String email_verification = user.getString(Parser.USR_EMAIL_VERIFICATION);
                String user_status = user.getString(Parser.USR_STATUS);
                String entry_by = user.getString(Parser.ENTRY_BY);
                String entry_date = user.getString(Parser.ENTRY_DATE);

                setUserName(first_name); // Setting User hhName into session
                setStaffID(user_id); // Setting Staff ID to use when sync data


                setUserCountryCode(country_code); // Setting Country code


                db.addUser(user_id, country_code, login_name, login_pw, first_name, last_name, email, email_verification, user_status, entry_by, entry_date);
//                Log.d("MOR_12",user_id +  country_code +  login_name +  login_pw +  first_name +  last_name +  email +  email_verification +  user_status +  entry_by +  entry_date);


                publishProgress(++progressIncremental);


                if (!jObj.isNull(Parser.COUNTRIES_JSON_A)) {

                    Parser.AdmCountryParser(jObj.getJSONArray(Parser.COUNTRIES_JSON_A), db);
                }


                publishProgress(++progressIncremental);


                if (!jObj.isNull(Parser.VALID_DATES_JSON_A)) {
                    JSONArray valid_dates = jObj.getJSONArray(Parser.VALID_DATES_JSON_A);
                    size = valid_dates.length();

                    for (int i = 0; i < size; i++) {
                        JSONObject valid_date = valid_dates.getJSONObject(i);
                        String AdmCountryCode = valid_date.getString(Parser.ADM_COUNTRY_CODE);
                        String StartDate = valid_date.getString(Parser.START_DATE);
                        String EndDate = valid_date.getString(Parser.END_DATE);

                        db.addValidDateRange(AdmCountryCode, StartDate, EndDate);


                    }
                }
                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.GPS_GROUP_JSON_A)) {
                    Parser.gpsGroupParser(jObj.getJSONArray(Parser.GPS_GROUP_JSON_A), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.GPS_SUBGROUP_JSON_A)) {
                    Parser.gpsSubGroupParser(jObj.getJSONArray(Parser.GPS_SUBGROUP_JSON_A), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.GPS_LOCATION_JSON_A)) {
                    Parser.gpsLocationParse(jObj.getJSONArray(Parser.GPS_LOCATION_JSON_A), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.ADM_COUNTRY_AWARD_JSON_A)) {
                    Parser.admCountryAwardParser(jObj.getJSONArray(Parser.ADM_COUNTRY_AWARD_JSON_A), db);
                }
                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.ADM_AWARD_JSON_A)) {
                    Parser.admAwardParser(jObj.getJSONArray(Parser.ADM_AWARD_JSON_A), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.ADM_DONOR_JSON_A)) {
                    Parser.admDonorParser(jObj.getJSONArray(Parser.ADM_DONOR_JSON_A), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.ADM_PROGRAM_MASTER_JSON_A)) {
                    Parser.admProgramMasterParser(jObj.getJSONArray(Parser.ADM_PROGRAM_MASTER_JSON_A), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.ADM_SERVICE_MASTER_JSON_A)) {
                    Parser.admServiceMasterParser(jObj.getJSONArray(Parser.ADM_SERVICE_MASTER_JSON_A), db);

                }


                //adm_op_month
                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.ADM_OP_MONTH_JSON_A)) {
                    Parser.admOpMonthParser(jObj.getJSONArray(Parser.ADM_OP_MONTH_JSON_A), db);

                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.ADM_COUNTRY_PROGRAM_JSON_A)) {
                    Parser.admCountryProgramParser(jObj.getJSONArray(Parser.ADM_COUNTRY_PROGRAM_JSON_A), db);

                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.DOB_SERVICE_CENTER_JSON_A)) {
                    Parser.srvCenterParser(jObj.getJSONArray(Parser.DOB_SERVICE_CENTER_JSON_A), db);

                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.STAFF_ACCESS_INFO_JSON_A)) {
                    Parser.staff_access_infoParser(jObj.getJSONArray(Parser.STAFF_ACCESS_INFO_JSON_A), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.LB_REG_HH_CATEGORY_JSON_A)) {
                    Parser.lupRegnHHCategoryParser(jObj.getJSONArray(Parser.LB_REG_HH_CATEGORY_JSON_A), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.REG_LUP_GRADUATION_JSON_A)) {
                    Parser.regNLupGraduationParser(jObj.getJSONArray(Parser.REG_LUP_GRADUATION_JSON_A), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.LAYER_LABELS_JSON_A)) {
                    Parser.geoLayRMasterParser(jObj.getJSONArray(Parser.LAYER_LABELS_JSON_A), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.DISTRICT_JSON_A)) {
                    Parser.geoLayR1ListParser(jObj.getJSONArray(Parser.DISTRICT_JSON_A), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.UPAZILLA_JSON_A)) {
                    Parser.geoLayR2ListParser(jObj.getJSONArray(Parser.UPAZILLA_JSON_A), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.UNIT_JSON_A)) {
                    Parser.geoLayR3ListParser(jObj.getJSONArray(Parser.UNIT_JSON_A), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.VILLAGE_JSON_A)) {
                    Parser.geoLayR4ListParser(jObj.getJSONArray(Parser.VILLAGE_JSON_A), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.RELATION_JSON_A)) {
                    Parser.lupRegNHHRelationParser(jObj.getJSONArray(Parser.RELATION_JSON_A), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.REPORT_TEMPLATE_JSON_A)) {
                    Parser.rptTemplateParser(jObj.getJSONArray(Parser.REPORT_TEMPLATE_JSON_A), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.CARD_PRINT_REASON_JSON_A)) {
                    Parser.lupRegNCardPrintReasonParser(jObj.getJSONArray(Parser.CARD_PRINT_REASON_JSON_A), db);
                }


                publishProgress(++progressIncremental);


                if (!jObj.isNull(Parser.REG_MEM_CARD_REQUEST_JSON_A)) {
                    JSONArray reg_mem_card_requests = jObj.getJSONArray(Parser.REG_MEM_CARD_REQUEST_JSON_A);
                    size = reg_mem_card_requests.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject reg_mem_card_request = reg_mem_card_requests.getJSONObject(i);

                        String AdmCountryCode = reg_mem_card_request.getString(Parser.ADM_COUNTRY_CODE);
                        String AdmDonorCode = reg_mem_card_request.getString(Parser.ADM_DONOR_CODE);
                        String AdmAwardCode = reg_mem_card_request.getString(Parser.ADM_AWARD_CODE);
                        String LayR1ListCode = reg_mem_card_request.getString(Parser.LAY_R_1_LIST_CODE);
                        String LayR2ListCode = reg_mem_card_request.getString(Parser.LAY_R_2_LIST_CODE);
                        String LayR3ListCode = reg_mem_card_request.getString(Parser.LAY_R_3_LIST_CODE);
                        String LayR4ListCode = reg_mem_card_request.getString(Parser.LAY_R_4_LIST_CODE);
                        String HHID = reg_mem_card_request.getString(Parser.HHID);
                        String MemID = reg_mem_card_request.getString(Parser.MEM_ID);
                        String RptGroup = reg_mem_card_request.getString(Parser.RPT_GROUP);
                        String RptCode = reg_mem_card_request.getString(Parser.RPT_CODE);
                        String RequestSL = reg_mem_card_request.getString(Parser.REQUEST_SL);
                        String ReasonCode = reg_mem_card_request.getString(Parser.REASON_CODE);
                        String RequestDate = reg_mem_card_request.getString(Parser.REQUEST_DATE);
                        String PrintDate = reg_mem_card_request.getString(Parser.PRINT_DATE);
                        String PrintBy = reg_mem_card_request.getString(Parser.PRINT_BY);
                        String DeliveryDate = reg_mem_card_request.getString(Parser.DELIVERY_DATE);
                        String DeliveredBy = reg_mem_card_request.getString(Parser.DELIVERED_BY);
                        String DelStatus = reg_mem_card_request.getString(Parser.DEL_STATUS);
                        String EntryBy = reg_mem_card_request.getString(Parser.ENTRY_BY);
                        String EntryDate = reg_mem_card_request.getString(Parser.ENTRY_DATE);

                        db.addCardRequestDataFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode,
                                HHID, MemID, RptGroup, RptCode, RequestSL, ReasonCode, RequestDate,
                                PrintDate, PrintBy, DeliveryDate, DeliveredBy, DelStatus, EntryBy, EntryDate);

                        //Log.d(TAG, "In Reg Mem Card Request Table: AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : " + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode + " HHID : " + HHID);
                    }
                }


                publishProgress(++progressIncremental);


                if (!jObj.isNull(Parser.STAFF_FDP_ACCESS_JSON_A)) {
                    JSONArray staff_fdp_accesses = jObj.getJSONArray(Parser.STAFF_FDP_ACCESS_JSON_A);
                    size = staff_fdp_accesses.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject staff_fdp_access = staff_fdp_accesses.getJSONObject(i);

                        String StfCode = staff_fdp_access.getString(Parser.STF_CODE);
                        String AdmCountryCode = staff_fdp_access.getString(Parser.ADM_COUNTRY_CODE);
                        String FDPCode = staff_fdp_access.getString(Parser.FDP_CODE);
                        String btnNew = staff_fdp_access.getString(Parser.BTN_NEW);
                        String btnSave = staff_fdp_access.getString(Parser.BTN_SAVE);
                        String btnDel = staff_fdp_access.getString(Parser.BTN_DEL);


                        db.addStaffFDPAccess(StfCode, AdmCountryCode, FDPCode, btnNew, btnSave, btnDel);

                        //  Log.d(TAG, "In Reg Mem Card Request Table: AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : "
                        //        + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode+ " HHID : " + HHID);
                    }
                }


                publishProgress(++progressIncremental);

                if (!jObj.isNull(Parser.FDP_MASTER_JSON_A)) {
                    JSONArray fdp_masters = jObj.getJSONArray(Parser.FDP_MASTER_JSON_A);
                    size = fdp_masters.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject fdp_master = fdp_masters.getJSONObject(i);

                        String AdmCountryCode = fdp_master.getString(Parser.ADM_COUNTRY_CODE);
                        String FDPCode = fdp_master.getString(Parser.FDP_CODE);
                        String FDPName = fdp_master.getString(Parser.FDP_NAME);
                        String FDPCatCode = fdp_master.getString(Parser.FDP_CAT_CODE);
                        String WHCode = fdp_master.getString(Parser.WH_CODE);
                        String LayR1Code = fdp_master.getString(Parser.LAY_R_1_CODE);
                        String LayR2Code = fdp_master.getString(Parser.LAY_R_2_CODE);


                        db.addFDPMaster(AdmCountryCode, FDPCode, FDPName, FDPCatCode, WHCode, LayR1Code, LayR2Code);

                        //  Log.d(TAG, "In Reg Mem Card Request Table: AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : "
                        //        + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode+ " HHID : " + HHID);
                    }
                }


                publishProgress(++progressIncremental);


                if (!jObj.isNull(Parser.DISTRIBUTION_TABLE_JSON_A)) {
                    JSONArray distribution_tableDatas = jObj.getJSONArray(Parser.DISTRIBUTION_TABLE_JSON_A);
                    size = distribution_tableDatas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject distribution_tableData = distribution_tableDatas.getJSONObject(i);
                        DistributionSaveDataModel data = new DistributionSaveDataModel();
                        data.setCountryCode(distribution_tableData.getString(Parser.ADM_COUNTRY_CODE));
                        data.setAdmDonorCode(distribution_tableData.getString(Parser.ADM_DONOR_CODE));
                        data.setAdmAwardCode(distribution_tableData.getString(Parser.ADM_AWARD_CODE));
                        data.setDistrictCode(distribution_tableData.getString(Parser.LAY_R_1_LIST_CODE));
                        data.setUpCode(distribution_tableData.getString(Parser.LAY_R_2_LIST_CODE));
                        data.setUniteCode(distribution_tableData.getString(Parser.LAY_R_3_LIST_CODE));
                        data.setVillageCode(distribution_tableData.getString(Parser.LAY_R_4_LIST_CODE));
                        data.setProgCode(distribution_tableData.getString(Parser.PROG_CODE));
                        data.setSrvCode(distribution_tableData.getString(Parser.SRV_CODE));
                        data.setOpMonthCode(distribution_tableData.getString(Parser.OP_MONTH_CODE));
                        data.setFDPCode(distribution_tableData.getString(Parser.FDP_CODE));
                        data.setID(distribution_tableData.getString(Parser.ID));
                        data.setDistStatus(distribution_tableData.getString(Parser.DIST_STATUS));
                        data.setSrvOpMonthCode(distribution_tableData.getString(Parser.SRV_OP_MONTH_CODE));
                        data.setDistFlag(distribution_tableData.getString(Parser.DIST_FLAG));
                        data.setWd(distribution_tableData.getString("WD"));

                        db.addInDistributionTableFormOnLine(data);

                        //  Log.d(TAG, "In Reg Mem Card Request Table: AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : "
                        //        + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode+ " HHID : " + HHID);
                    }
                }


                publishProgress(++progressIncremental);


                if (!jObj.isNull(Parser.DISTRIBUTION_EXT_TABLE_JSON_A)) {
                    JSONArray distribution_ext_tableDatas = jObj.getJSONArray(Parser.DISTRIBUTION_EXT_TABLE_JSON_A);
                    size = distribution_ext_tableDatas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject distribution_ext_tableData = distribution_ext_tableDatas.getJSONObject(i);
                        // DistributionSaveDataModel data = new DistributionSaveDataModel();

                        String AdmCountryCode = distribution_ext_tableData.getString(Parser.ADM_COUNTRY_CODE);
                        String AdmDonorCode = distribution_ext_tableData.getString(Parser.ADM_DONOR_CODE);
                        String AdmAwardCode = distribution_ext_tableData.getString(Parser.ADM_AWARD_CODE);
                        String LayR1ListCode = distribution_ext_tableData.getString(Parser.LAY_R_1_LIST_CODE);
                        String LayR2ListCode = distribution_ext_tableData.getString(Parser.LAY_R_2_LIST_CODE);
                        String LayR3ListCode = distribution_ext_tableData.getString(Parser.LAY_R_3_LIST_CODE);
                        String LayR4ListCode = distribution_ext_tableData.getString(Parser.LAY_R_4_LIST_CODE);
                        String ProgCode = distribution_ext_tableData.getString(Parser.PROG_CODE);
                        String SrvCode = distribution_ext_tableData.getString(Parser.SRV_CODE);
                        String OpMonthCode = distribution_ext_tableData.getString(Parser.OP_MONTH_CODE);
                        String FDPCode = distribution_ext_tableData.getString(Parser.FDP_CODE);
                        String ID = distribution_ext_tableData.getString(Parser.ID);
                        String VOItmSpec = distribution_ext_tableData.getString(Parser.VO_ITM_SPEC);
                        String VOItmUnit = distribution_ext_tableData.getString(Parser.VO_ITM_UNIT);
                        String VORefNumber = distribution_ext_tableData.getString(Parser.VO_REF_NUMBER);
                        String SrvOpMonthCode = distribution_ext_tableData.getString(Parser.SRV_OP_MONTH_CODE);
                        String DistFlag = distribution_ext_tableData.getString(Parser.DIST_FLAG);


                        //data.setDistStatus(distribution_ext_tableData.getString(DIST_STATUS);
                        // // TODO: 9/25/2016  create Method for download online

                        db.addInDistributionExtendedTable(AdmCountryCode, AdmDonorCode, AdmAwardCode,
                                LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode, ProgCode,
                                SrvCode, OpMonthCode, FDPCode, ID, VOItmSpec, VOItmUnit,
                                VORefNumber, SrvOpMonthCode, DistFlag,

                                "", "", "1");

                        //  Log.d(TAG, "In Reg Mem Card Request Table: AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : "
                        //        + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode+ " HHID : " + HHID);
                    }
                }


                publishProgress(++progressIncremental);

                if (!jObj.isNull("distbasic_table")) {
                    JSONArray distbasic_table = jObj.getJSONArray("distbasic_table");
                    size = distbasic_table.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject distbasic = distbasic_table.getJSONObject(i);
                        // DistributionSaveDataModel data = new DistributionSaveDataModel();

                        String AdmCountryCode = distbasic.getString(Parser.ADM_COUNTRY_CODE);
                        String AdmDonorCode = distbasic.getString(Parser.ADM_DONOR_CODE);
                        String AdmAwardCode = distbasic.getString(Parser.ADM_AWARD_CODE);
                        String ProgCode = distbasic.getString(Parser.PROG_CODE);
                        String OpCode = distbasic.getString("OpCode");
                        String SrvOpMonthCode = distbasic.getString("SrvOpMonthCode");
                        String DisOpMonthCode = distbasic.getString("DisOpMonthCode");
                        String FDPCode = distbasic.getString(Parser.FDP_CODE);
                        String DistFlag = distbasic.getString("DistFlag");
                        ///   String FoodFlag = distbasic.getString("FoodFlag");
                        String OrgCode = distbasic.getString("OrgCode");
                        String Distributor = distbasic.getString("Distributor");
                        String DistributionDate = distbasic.getString("DistributionDate");
                        String DeliveryDate = distbasic.getString("DeliveryDate");
                        String Status = distbasic.getString("Status");
                        String PreparedBy = distbasic.getString("PreparedBy");
                        String VerifiedBy = distbasic.getString("VerifiedBy");
                        String ApproveBy = distbasic.getString("ApproveBy");


                        //data.setDistStatus(distribution_ext_tableData.getString(DIST_STATUS);

                        db.addInDistributionNPlaneTable(AdmCountryCode, AdmDonorCode, AdmAwardCode, ProgCode,
                                OpCode, SrvOpMonthCode, DisOpMonthCode, FDPCode, DistFlag, OrgCode, Distributor,
                                DistributionDate, DeliveryDate, Status, PreparedBy, VerifiedBy, ApproveBy);


                    }
                }


                publishProgress(++progressIncremental);


                if (!jObj.isNull(Parser.LUP_SRV_OPTION_LIST)) {
                    JSONArray lup_srv_option_listDatas = jObj.getJSONArray(Parser.LUP_SRV_OPTION_LIST);
                    size = lup_srv_option_listDatas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject lup_srv_option_listData = lup_srv_option_listDatas.getJSONObject(i);

                        String countryCode = lup_srv_option_listData.getString(Parser.ADM_COUNTRY_CODE);

                        String programCode = lup_srv_option_listData.getString(Parser.PROG_CODE);
                        String serviceCode = lup_srv_option_listData.getString(Parser.SRV_CODE);
                        String LUPOptionCode = lup_srv_option_listData.getString(Parser.LUP_OPTION_CODE);
                        String LUPOptionName = lup_srv_option_listData.getString(Parser.LUP_OPTION_NAME);


                        db.addInLupSrvOptionListFromOnline(countryCode, programCode, serviceCode, LUPOptionCode, LUPOptionName);

                        //  Log.d(TAG, "In Reg Mem Card Request Table: AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : "
                        //        + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode+ " HHID : " + HHID);
                    }
                }

                publishProgress(++progressIncremental);


                if (!jObj.isNull("vo_itm_table")) {
                    JSONArray vo_itm_tableDatas = jObj.getJSONArray("vo_itm_table");
                    size = vo_itm_tableDatas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject vo_itm_tableData = vo_itm_tableDatas.getJSONObject(i);
                        //AGR_DataModel data = new AGR_DataModel();
                        String CatCode = vo_itm_tableData.getString("CatCode");
                        String ItmCode = vo_itm_tableData.getString("ItmCode");
                        String ItmName = vo_itm_tableData.getString("ItmName");


                        db.addVoucherItemTableFromOnline(CatCode, ItmCode, ItmName);

//                        Log.d(TAG, "In Voucher item table : CatCode : " + CatCode + " ItmCode : " + ItmCode + " ItmName : " + ItmName);

                    }
                }


                publishProgress(++progressIncremental);

                if (!jObj.isNull(Parser.VO_ITM_MEAS_TABLE_JSON_A)) {
                    JSONArray vo_itm_meas_tableDatas = jObj.getJSONArray(Parser.VO_ITM_MEAS_TABLE_JSON_A);
                    size = vo_itm_meas_tableDatas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject vo_itm_meas_tableData = vo_itm_meas_tableDatas.getJSONObject(i);
                        //AGR_DataModel data = new AGR_DataModel();
                        String MeasRCode = vo_itm_meas_tableData.getString("MeasRCode");
                        String UnitMeas = vo_itm_meas_tableData.getString("UnitMeas");
                        String MeasTitle = vo_itm_meas_tableData.getString("MeasTitle");


                        db.addVoucherItemMeasFromOnline(MeasRCode, UnitMeas, MeasTitle);

//                        Log.d(TAG, "In Voucher item table : MeasRCode : " + MeasRCode + " UnitMeas : " + UnitMeas + " MeasTitle : " + MeasTitle);

                    }
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("vo_country_prog_itm")) {
                    JSONArray vo_country_prog_itmDatas = jObj.getJSONArray("vo_country_prog_itm");
                    size = vo_country_prog_itmDatas.length();


                    String AdmCountryCode;
                    String AdmDonorCode;
                    String AdmAwardCode;
                    String AdmProgCode;
                    String AdmSrvCode;
                    String CatCode;
                    String ItmCode;
                    String MeasRCode;
                    String VOItmSpec;
                    String UnitCost;
                    String Active;
                    String Currency;
                    for (int i = 0; i < size; i++) {
                        JSONObject vo_country_prog_itmData = vo_country_prog_itmDatas.getJSONObject(i);
                        //AGR_DataModel data = new AGR_DataModel();
                        AdmCountryCode = vo_country_prog_itmData.getString("AdmCountryCode");
                        AdmDonorCode = vo_country_prog_itmData.getString("AdmDonorCode");
                        AdmAwardCode = vo_country_prog_itmData.getString("AdmAwardCode");
                        AdmProgCode = vo_country_prog_itmData.getString("AdmProgCode");
                        AdmSrvCode = vo_country_prog_itmData.getString("AdmSrvCode");
                        CatCode = vo_country_prog_itmData.getString("CatCode");
                        ItmCode = vo_country_prog_itmData.getString("ItmCode");
                        MeasRCode = vo_country_prog_itmData.getString("MeasRCode");
                        VOItmSpec = vo_country_prog_itmData.getString(Parser.VO_ITM_SPEC);
                        UnitCost = vo_country_prog_itmData.getString("UnitCost");
                        Active = vo_country_prog_itmData.getString("Active");
                        Currency = vo_country_prog_itmData.getString("Currency");


                        db.addVoucherCountryProgItemFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, AdmSrvCode, CatCode, ItmCode, MeasRCode, VOItmSpec, UnitCost, Active, Currency);

                        //                        Log.d(TAG, "In Voucher Country  Prog Item  table : AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " AdmAwardCode : " + AdmAwardCode);

                    }
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.LUP_GPS_TABLE_JSON_A)) {
                    JSONArray lup_gps_table_Datas = jObj.getJSONArray(Parser.LUP_GPS_TABLE_JSON_A);
                    size = lup_gps_table_Datas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject lup_gps_tableData = lup_gps_table_Datas.getJSONObject(i);

                        String GrpCode = lup_gps_tableData.getString("GrpCode");
                        String SubGrpCode = lup_gps_tableData.getString("SubGrpCode");
                        String AttributeCode = lup_gps_tableData.getString("AttributeCode");
                        String LookUpCode = lup_gps_tableData.getString("LookUpCode");
                        String LookUpName = lup_gps_tableData.getString("LookUpName");


                        db.addLUP_GPS_TableFromOnline(GrpCode, SubGrpCode, AttributeCode, LookUpCode, LookUpName);
                       /* Log.d("NIR2", "addLUP_GPS_TableFromOnline : GrpCode : " + GrpCode + " SubGrpCode : "
                                + SubGrpCode + " AttributeCode : " + AttributeCode
                                + " LookUpCode : " + LookUpCode + " LookUpName : " + LookUpName
                        );*/

                    }
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.GPS_SUB_GROUP_ATTRIBUTES_JSON_A)) {
                    JSONArray gps_sub_group_attributes_Datas = jObj.getJSONArray(Parser.GPS_SUB_GROUP_ATTRIBUTES_JSON_A);
                    size = gps_sub_group_attributes_Datas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject gps_sub_group_attributes_Data = gps_sub_group_attributes_Datas.getJSONObject(i);


                        String GrpCode = gps_sub_group_attributes_Data.getString("GrpCode");
                        String SubGrpCode = gps_sub_group_attributes_Data.getString("SubGrpCode");
                        String AttributeCode = gps_sub_group_attributes_Data.getString("AttributeCode");
                        String AttributeTitle = gps_sub_group_attributes_Data.getString("AttributeTitle");
                        String DataType = gps_sub_group_attributes_Data.getString("DataType");
                        String LookUpCode = gps_sub_group_attributes_Data.getString("LookUpCode");


                        db.addGPS_SubGroupAttributesFromOnline(GrpCode, SubGrpCode, AttributeCode, AttributeTitle, DataType, LookUpCode);
                        /*Log.d("NIR2", "addGPS_SubGroupAttributesFromOnline : GrpCode : " + GrpCode
                                + " SubGrpCode : " + SubGrpCode + " AttributeCode : " + AttributeCode
                                + " AttributeTitle : " + AttributeTitle + " DataType : " + DataType
                                + " LookUpCode : " + LookUpCode);*/

                    }
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull(Parser.GPS_LOCATION_ATTRIBUTES_JSON_A)) {
                    JSONArray gps_location_attributes_Datas = jObj.getJSONArray(Parser.GPS_LOCATION_ATTRIBUTES_JSON_A);
                    size = gps_location_attributes_Datas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject gps_location_attributes_Data = gps_location_attributes_Datas.getJSONObject(i);


                        String AdmCountryCode = gps_location_attributes_Data.getString("AdmCountryCode");
                        String GrpCode = gps_location_attributes_Data.getString("GrpCode");
                        String SubGrpCode = gps_location_attributes_Data.getString("SubGrpCode");
                        String LocationCode = gps_location_attributes_Data.getString("LocationCode");
                        String AttributeCode = gps_location_attributes_Data.getString("AttributeCode");
                        String AttributeValue = gps_location_attributes_Data.getString("AttributeValue");
                        String AttPhoto = gps_location_attributes_Data.getString("AttPhoto");

                        db.addGPSLocationAttributesFromOnline(AdmCountryCode, GrpCode, SubGrpCode, LocationCode, AttributeCode, AttributeValue, AttPhoto);
                     /*   Log.d("NIR2", "addGPS_SubGroupAttributesFromOnline : AdmCountryCode : " + AdmCountryCode
                                + " GrpCode : " + GrpCode + " SubGrpCode : " + SubGrpCode
                                + " LocationCode : " + LocationCode + " AttributeCode : " + AttributeCode

                                + " AttributeValue : " + AttributeValue
                        );*/

                    }
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("community_group")) {

                    Parser.CommunityGroupParser(jObj.getJSONArray("community_group"), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull("lup_community_animal")) {

                    Parser.lupCommunityAnimalParser(jObj.getJSONArray("lup_community_animal"), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull("lup_prog_group_crop")) {
                    Parser.lupProgramGroupCropParser(jObj.getJSONArray("lup_prog_group_crop"), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull("lup_community_loan_source")) {
                    Parser.lupCommunityLoanSourceParser(jObj.getJSONArray("lup_community_loan_source"), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull("lup_community_fund_source")) {
                    Parser.lupCommunityFundSourceParser(jObj.getJSONArray("lup_community_fund_source"), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull("lup_community_irrigation_system")) {
                    Parser.lupCommunityIrrigationSystemParser(jObj.getJSONArray("lup_community_irrigation_system"), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull("lup_community__lead_position")) {
                    Parser.lupCommunityLeadPositionParser(jObj.getJSONArray("lup_community__lead_position"), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull("community_group_category")) {
                    Parser.lupCommunityGroupCategoryParser(jObj.getJSONArray("community_group_category"), db);
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull("lup_reg_n_add_lookup")) {
                    JSONArray lup_reg_n_add_lookup = jObj.getJSONArray("lup_reg_n_add_lookup");
                    size = lup_reg_n_add_lookup.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject Lup_reg_n_add_lookup = lup_reg_n_add_lookup.getJSONObject(i);


                        String AdmCountryCode = Lup_reg_n_add_lookup.getString("AdmCountryCode");
                        String RegNAddLookupCode = Lup_reg_n_add_lookup.getString("RegNAddLookupCode");
                        String RegNAddLookup = Lup_reg_n_add_lookup.getString("RegNAddLookup");
                        String LayR1ListCode = Lup_reg_n_add_lookup.getString("LayR1ListCode");
                        String LayR2ListCode = Lup_reg_n_add_lookup.getString("LayR2ListCode");
                        String LayR3ListCode = Lup_reg_n_add_lookup.getString("LayR3ListCode");
                        String LayR4ListCode = Lup_reg_n_add_lookup.getString("LayR4ListCode");

/*
                        Log.d("InTest", "AdmCountryCode:" + AdmCountryCode + "RegNAddLookupCode" + RegNAddLookupCode
                                + "RegNAddLookup: " + RegNAddLookup + "LayR1ListCode : " + LayR1ListCode + "LayR2ListCode:" + LayR2ListCode
                                + " LayR3ListCode: " + LayR3ListCode + "LayR4ListCode:" + LayR4ListCode);*/
                        db.addLUP_RegNAddLookup(AdmCountryCode, RegNAddLookupCode, RegNAddLookup, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode);

                    }
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("prog_org_n_role")) {
                    JSONArray prog_org_n_role_Datas = jObj.getJSONArray("prog_org_n_role");
                    size = prog_org_n_role_Datas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject prog_org_n_role_Data = prog_org_n_role_Datas.getJSONObject(i);
                        String AdmCountryCode = prog_org_n_role_Data.getString("AdmCountryCode");
                        String AdmDonorCode = prog_org_n_role_Data.getString("AdmDonorCode");
                        String AdmAwardCode = prog_org_n_role_Data.getString("AdmAwardCode");
                        String OrgNCode = prog_org_n_role_Data.getString("OrgNCode");
                        String PrimeYN = prog_org_n_role_Data.getString("PrimeYN");
                        String SubYN = prog_org_n_role_Data.getString("SubYN");
                        String TechYN = prog_org_n_role_Data.getString("TechYN");
                        String LogYN = prog_org_n_role_Data.getString("LogYN");
                        String ImpYN = prog_org_n_role_Data.getString("ImpYN");
                        String OthYN = prog_org_n_role_Data.getString("OthYN");

                        db.insertIntoProgOrgNRole(AdmCountryCode, AdmDonorCode, AdmAwardCode, OrgNCode, PrimeYN, SubYN, TechYN, ImpYN, LogYN, OthYN);
                    }
                }
                publishProgress(++progressIncremental);
                if (!jObj.isNull("org_n_code")) {
                    JSONArray org_n_code_Datas = jObj.getJSONArray("org_n_code");
                    size = org_n_code_Datas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject org_n_code_Data = org_n_code_Datas.getJSONObject(i);
                        String OrgNCode = org_n_code_Data.getString("OrgNCode");
                        String OrgNName = org_n_code_Data.getString("OrgNName");
                        String OrgNShortName = org_n_code_Data.getString("OrgNShortName");
//                        Log.d(TAG, "OrgNName:" + OrgNName + "OrgNShortName:" + OrgNShortName);
                        db.insertIntoProgOrgN(OrgNCode, OrgNName, OrgNShortName);
                    }
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull("community_grp_detail")) {

                    Parser.CommunityGroupDetailsParser(jObj.getJSONArray("community_grp_detail"), db);
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("staff_master")) {
                    JSONArray staff_master_Datas = jObj.getJSONArray("staff_master");
                    size = staff_master_Datas.length();

                    String StfCode;
                    String OrigAdmCountryCode;
                    String StfName;
                    String OrgNCode;
                    String OrgNDesgNCode;
                    String StfStatus;
                    String StfCategory;
                    String UsrLogInName;
                    String UsrLogInPW;
                    String StfAdminRole;
                    for (int i = 0; i < size; i++) {
                        JSONObject staff_master_Data = staff_master_Datas.getJSONObject(i);


                        StfCode = staff_master_Data.getString("StfCode");
                        OrigAdmCountryCode = staff_master_Data.getString("OrigAdmCountryCode");
                        StfName = staff_master_Data.getString("StfName");
                        OrgNCode = staff_master_Data.getString("OrgNCode");
                        OrgNDesgNCode = staff_master_Data.getString("OrgNDesgNCode");
                        StfStatus = staff_master_Data.getString("StfStatus");
                        StfCategory = staff_master_Data.getString("StfCategory");
                        UsrLogInName = staff_master_Data.getString("UsrLogInName");
                        UsrLogInPW = staff_master_Data.getString("UsrLogInPW");
                        StfAdminRole = staff_master_Data.getString("StfAdminRole");


                        db.insertIntoStaffMasterTable(StfCode, OrigAdmCountryCode, StfName, OrgNCode, OrgNDesgNCode, StfStatus, StfCategory, UsrLogInName, UsrLogInPW, StfAdminRole);

                    }
                }

                publishProgress(++progressIncremental);
                if (!jObj.isNull("gps_lup_list")) {
                    JSONArray gps_lup_list_data = jObj.getJSONArray("gps_lup_list");
                    size = gps_lup_list_data.length();

                    String GrpCode;
                    String SubGrpCode;
                    String AttributeCode;
                    String LupValueCode;
                    String LupValueText;

                    for (int i = 0; i < size; i++) {
                        JSONObject gps_lup_list = gps_lup_list_data.getJSONObject(i);


                        GrpCode = gps_lup_list.getString("GrpCode");
                        SubGrpCode = gps_lup_list.getString("SubGrpCode");
                        AttributeCode = gps_lup_list.getString("AttributeCode");
                        LupValueCode = gps_lup_list.getString("LupValueCode");
                        LupValueText = gps_lup_list.getString("LupValueText");


                        db.insertIntoLupGpsList(GrpCode, SubGrpCode, AttributeCode, LupValueCode, LupValueText);

                    }
                }


                publishProgress(++progressIncremental);
                if (!jObj.isNull("staff_srv_center_access")) {
                    Parser.staff_srv_center_accessParser(jObj.getJSONArray("staff_srv_center_access"), db);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            showProgressDialog();
            progressIncremental = 0;
        }

        @Override
        protected void onPostExecute(Void string) {

            new Inject_Reg_HouseH_DataIntoSQLite().execute();
        }
    }

    private void hideDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * Read JSON DATA Insert into SQLite
     */
    private void showProgressDialog() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(TOTAL_NO_OF_TABLE);
        progressDialog.setMessage("Retrieving...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    private void showOperationModelLabel(SharedPreferences settings) {
//        int operationMode = settings.getInt(UtilClass.OPERATION_MODE, 0);
        String operationModeName = sqlH.getDeviceOperationModeName();
        switch (operationModeName) {
            case UtilClass.REGISTRATION_OPERATION_MODE_NAME:

                tvOperationMode.setText("REGISTRATION");
                List<String> list;
                list = db.selectGeoDataVillage();
                String villageName = "";
                for (int i = 0; i < list.size(); i++) {
                    villageName += list.get(i) + "\n";
                }
                tvGeoData.setText(villageName);

                break;

            case UtilClass.TRANING_N_ACTIVITY_OPERATION_MODE_NAME:

                tvOperationMode.setText("TRAINING N ACTIVITY");

                list = db.selectGeoDataVillage();
                villageName = "";
                for (int i = 0; i < list.size(); i++) {
                    villageName += list.get(i) + "\n";
                }
                tvGeoData.setText(villageName);

                break;
            case UtilClass.DISTRIBUTION_OPERATION_MODE_NAME:

                tvOperationMode.setText("DISTRIBUTION");
                list = db.selectGeoDataFDP();
                String fdPName = "";
                for (int i = 0; i < list.size(); i++) {
                    fdPName += list.get(i) + "\n";
                }
                tvGeoData.setText(fdPName);
                break;
            case UtilClass.SERVICE_OPERATION_MODE_NAME:

                tvOperationMode.setText("SERVICE");
                list = db.selectGeoDataCenter();
                String centerName = "";
                for (int i = 0; i < list.size(); i++) {
                    centerName += list.get(i) + "\n";
                }
                tvGeoData.setText(centerName);

                break;


            case UtilClass.OTHER_OPERATION_MODE_NAME:
                tvOperationMode.setText("ORTHER");
                tvGeoData.setText("NOT APPLICABLE");

                break;
        }
    }
}
