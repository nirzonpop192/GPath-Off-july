package com.siddiquinoor.restclient.fragments;

/**
 * This activity is the Base Activity of all application activity
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.3.0
 * @since 1.0
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.siddiquinoor.restclient.activity.SummaryMenuActivity;
import com.siddiquinoor.restclient.activity.LoginActivity;
import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.controller.AppConfig;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.controller.SessionManager;
import com.siddiquinoor.restclient.utils.CalculationPadding;
import com.siddiquinoor.restclient.utils.KEY;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public abstract class BaseActivity extends Activity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    // Session manager
    public SessionManager session;
    protected static Context app_context;

    private ProgressDialog pDialog;

    private Intent intent;
    private AppConfig ac;

    private SQLiteHandler sqlH;
    // save the value is the app data download
    public static final String APP_PREFERENCES = "APP_Prefs";
    // key of saved data
    public static final String IS_APP_FIRST_RUN = "IsAppFirstRun";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app_context = getApplicationContext();

//        session = new SessionManager(app_context);

        // Session manager
        session = new SessionManager(app_context);
        //ac = new AppConfig();

        sqlH = new SQLiteHandler(app_context);

        /**
         * Check if user is already logged in or not
         * Ignore while in Development Environment
         */


        if (!ac.DEV_ENVIRONMENT) {
            if (!session.isLoggedIn()) {
                intent = new Intent(app_context, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);

            }
        }

    }

    protected void setLogin(boolean status) {
        session.setLogin(status);
    }

    protected void logoutUser() {

        setLogin(false);


        Intent intent = new Intent(app_context, LoginActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        finish();
    }

    protected void setUserName(String name) {
        session.setUserName(name);
    }

    protected String getUserName() {
        return session.getUserName();
    }

    //Set Staff ID
    protected void setStaffID(String staffID) {
        session.setStaffID(staffID);
    }

    // Get Staff ID
    protected String getStaffID() {
        return session.getStaffId();
    }

    //Set Username
    protected void setUserID(String userID) {
        session.setUserID(userID);
    }

    // Get Username
    protected String getUserID() {
        return session.getUserID();
    }


    //Set User Password
    protected void setUserPassword(String pass) {
        session.setUserPassword(pass);
    }

    // Get User Password
    protected String getUserPassword() {
        return session.getUserPassword();
    }


    protected void setUserCountryCode(String C_Code) {
        session.setUserCountryCode(C_Code);
    }

    protected String getCountryCode() {
        return session.getCountryCode();
    }


    protected int getSpinnerIndex(Spinner spinner, String myString) {

        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(myString)) {
                index = i;
            }
        }
        return index;
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_common, menu);

        // Get the SearchView and set the searchable configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();


        if (itemId == R.id.menuActionLogout) {
            logoutUser();
        }

        return super.onOptionsItemSelected(item);

        //return false;
    }

    protected void showProgress(String msg) {
        if (pDialog != null && pDialog.isShowing())
            dismissProgress();

        pDialog = ProgressDialog.show(this, getResources().getString(R.string.app_name), msg);
    }

    protected void dismissProgress() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    protected void showAlert(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.app_name))
                .setMessage(msg)
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
    }

    protected String getDateTime() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM-dd-yyyy HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date).toString();
    }

    protected String getDateTime(boolean isForExport) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd-HHmm", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date).toString();
    }

    protected String getDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM-dd-yyyyHH-MM", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date).toString();
    }

    protected boolean isTheDateValidFormat(String testingDate) {


        if (testingDate == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS", Locale.ENGLISH);
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(testingDate);
            System.out.println(date);

        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }


    protected boolean getValidDateRange(String test_date, String sdate, String edate, boolean flag) throws ParseException {

        String curr_date;


        curr_date = test_date + " 00:00:00";


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);

        Date newDate = format.parse(curr_date);
        Date startDate = format.parse(sdate);
        Date endDate = format.parse(edate);

        return newDate.getTime() >= startDate.getTime() && newDate.getTime() <= endDate.getTime();

    }


    protected boolean getValidDateRange(String test_date, String sdate, String edate) throws ParseException {

        String curr_date;
        if (isTheDateValidFormat(test_date)) {
            curr_date = test_date;
        } else {
            curr_date = test_date + " 00:00:00.000";
        }

        if (sdate.length() == 19) {
            sdate = sdate + ".000";

        }
        if (edate.length() == 19) {
            edate = edate + ".000";
        }

        //SimpleDateFormat nformat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS", Locale.ENGLISH);

        Date newDate = format.parse(curr_date);
        Date startDate = format.parse(sdate);
        Date endDate = format.parse(edate);

        return newDate.getTime() >= startDate.getTime() && newDate.getTime() <= endDate.getTime();

    }


    protected boolean isTheDateValidUSAFormat(String testingDate) {


        if (testingDate == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(testingDate);
            System.out.println(date);

        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }


    protected boolean getValidDateRangeUSAFormat(String test_date, String sdate, String edate) throws ParseException {

        String curr_date;
        if (isTheDateValidUSAFormat(test_date)) {
            curr_date = test_date;
        } else {
            curr_date = test_date;

        }

        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);

        Date newDate = format.parse(curr_date);
        Date startDate = format.parse(sdate);
        Date endDate = format.parse(edate);

        return newDate.getTime() >= startDate.getTime() && newDate.getTime() <= endDate.getTime();

    }


    //dynamically file will create Read from it
    protected String readDataFromFile(String mfile_name) {
        String retrieveString = "";

        try {
            InputStream inputStream = openFileInput("pci_" + mfile_name + ".txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    //stringBuilder.append(receiveString);
                    stringBuilder.append((CharSequence) receiveString);
                }

                inputStream.close();
                retrieveString = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e(TAG, "Can not read file: " + e.toString());
        }
        //Log.i("REFAT---->", ret);
        Log.i(TAG, String.valueOf(retrieveString.length()));
        return retrieveString;
    }


    /**
     * dynamically file will be created. Write bytes on it as string operation
     *
     * @param stringResponse json String
     * @param file_name      file Name
     */
    protected void writeJSONToTextFile(String stringResponse, String file_name) {

        String fileName = "pci_" + file_name + ".txt";

        //  Log.d(TAG, "String Response write into : " + mfile_name);
        FileOutputStream outputStream;


        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(stringResponse.getBytes());

            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  /*  protected static void  showSoftKeyboard(View view){

    }*/

    /**
     * @param activity it move Main page
     */
    protected void goToMainActivity(Activity activity) {
        activity.finish();
        Intent iMainActivity = new Intent(activity, MainActivity.class);
        startActivity(iMainActivity);

    }

    /**
     * @param activity it move Main page
     */
    protected void goToSummaryActivity(Activity activity, String countryCode) {
        activity.finish();
        Intent iSummary = new Intent(activity, SummaryMenuActivity.class);
        iSummary.putExtra(KEY.COUNTRY_ID, countryCode);

        startActivity(iSummary);

    }

    /**
     * @param context invoking class name
     * @param image   Drawable image
     * @param btn     button view
     * @deprecated
     */
    protected void setPaddingButton(Context context, Drawable image, Button btn) {
        int leftPadd, rightPadd, topPadd, bottomPadd;
        CalculationPadding calPadd = new CalculationPadding();

        leftPadd = rightPadd = calPadd.calculateViewPadding(context, image, btn);
        /**
         * get the value  from resource
         */
        topPadd = bottomPadd = getResources().getInteger(R.integer.top_bottom_icon_pad_int_5);

        btn.setPadding(leftPadd, topPadd, rightPadd, bottomPadd);
    }
}
