package com.siddiquinoor.restclient.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.data_model.AdmCountryDataModel;
import com.siddiquinoor.restclient.data_model.FDPItem;
import com.siddiquinoor.restclient.data_model.ServiceCenterItem;
import com.siddiquinoor.restclient.data_model.VillageItem;
import com.siddiquinoor.restclient.manager.SQLiteHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>This class is use for Convert Selected Village(LayR4Code) or FDp or Service Center into JSON.</p>
 *
 * @author FAISAL on 2/23/2016.
 */
public class UtilClass {



    /**
     * Registration Mode
     */
    public static final int REGISTRATION_OPERATION_MODE = 1;
    public static final String REGISTRATION_OPERATION_MODE_NAME = "Registration";
    /**
     * Distribution Mode
     */
    public static final int DISTRIBUTION_OPERATION_MODE = 2;
    public static final String DISTRIBUTION_OPERATION_MODE_NAME = "Distribution";
    /**
     * Service Mode
     */
    public static final int SERVICE_OPERATION_MODE = 3;
    public static final String SERVICE_OPERATION_MODE_NAME = "Service";


    /**
     * OTHER Mode or dynamic  mode
     */
    public static final int OTHER_OPERATION_MODE = 4;
    public static final String OTHER_OPERATION_MODE_NAME = "Others";

    /**
     * OTHER Mode or dynamic  mode
     */
    public static final int TRANING_n_ACTIVITY_OPERATION_MODE = 5;
    public static final String TRANING_N_ACTIVITY_OPERATION_MODE_NAME = "Training & Activity";

    /**
     * Class Tag for Debug
     */
    private static final String TAG = "UtilClass";
    /**
     * Key of Operation Mode to store Shared preference
     */
    public static final String OPERATION_MODE = "OPERATION_MODE";

    public static final String SYNC_MODE_KEY = "SYNC_MODE";

    public static final String PROCESS_ON_GOING_KEY = "PROCESS_ON_GOING";
    public static final String IMPORT_DATE_TIME_KEY = "IMPORT_DATETIME";

    public String setDateFormatFromTV(TextView tv) {
        String temp = "";
        if (!tv.getText().toString().equals("") && tv.getText().toString().equals("null")) {
            temp = tv.getText().toString();
            // to save the date format YYYY-MM-DD
            // to prevent
            if (temp.length() > 10) {
                temp = temp.substring(6) + "-" + temp.substring(0, 5);
                //setStrRegDate(temp);
            }
        }
        return temp;
    }

    /**
     * @param context Invoking  Context Class
     * @param cCode   Country Code
     * @return Layer_1 Label
     */
    public static String getLayR1LabelName(Context context, String cCode) {
        SQLiteHandler sql = new SQLiteHandler(context);
        return sql.getLayerLabel(cCode, "1");
        //   Log.d("UtillClass","layR1Label : "+layR1Label);

    }

    public static String getLayR2LabelName(Context context, String cCode) {
        SQLiteHandler sql = new SQLiteHandler(context);
        return sql.getLayerLabel(cCode, "2");
        //   Log.d("UtillClass","layR1Label : "+layR1Label);

    }

    public static String getLayR3LabelName(Context context, String cCode) {
        SQLiteHandler sql = new SQLiteHandler(context);
        return sql.getLayerLabel(cCode, "3");
        //   Log.d("UtillClass","layR1Label : "+layR1Label);

    }

    public static String getLayR4LabelName(Context context, String cCode) {
        SQLiteHandler sql = new SQLiteHandler(context);
        return sql.getLayerLabel(cCode, "4");
        //   Log.d("UtillClass","layR1Label : "+layR1Label);

    }


    /**
     * <p>this method convert  selected village data into json array</p>
     *
     * @param className         ClassName is String Variable. That track down the invoking Class.
     *                          <p>If Calling Class is LogIn Than selected Village will be inserted into db</p>
     * @param selectVillageList Selected (max)2 village Code(layR4List Code)
     * @param sqlH              Database Helper
     * @return The json Data of selected village code(LayR4Code)
     */
    public static JSONArray layR4CodeJSONConverter(String className, ArrayList<VillageItem> selectVillageList, SQLiteHandler sqlH) {

        JSONArray selectedVillageJson = new JSONArray();

        for (int j = 0; j < selectVillageList.size(); j++) {

            JSONObject mData = new JSONObject();


            try {
                mData.put("selectedLayR4Code", selectVillageList.get(j).getLayRCode());
                if (className.equals("LoginActivity")) {
                    /** insert the into the data base */
                    /**
                     * if there is no address code pute in db
                     */
                    if (selectVillageList.get(j).getLayRCode().length() > 12) {

                        String countryCode = selectVillageList.get(j).getLayRCode().substring(0, 4);
                        String layR1Code = selectVillageList.get(j).getLayRCode().substring(4, 6);
                        String layR2Code = selectVillageList.get(j).getLayRCode().substring(6, 8);
                        String layR3Code = selectVillageList.get(j).getLayRCode().substring(8, 10);
                        String layR4Code = selectVillageList.get(j).getLayRCode().substring(10, 12);
                        String addressCode = selectVillageList.get(j).getLayRCode().substring(12);
                        String layRCode = selectVillageList.get(j).getLayRCode();
                        String layR4Name = selectVillageList.get(j).getLayR4ListName();


                        sqlH.addSelectedVillage(countryCode, layR1Code, layR2Code, layR3Code, layR4Code, layRCode, layR4Name, addressCode);


                    } else {


                        String countryCode = selectVillageList.get(j).getLayRCode().substring(0, 4);
                        String layR1Code = selectVillageList.get(j).getLayRCode().substring(4, 6);
                        String layR2Code = selectVillageList.get(j).getLayRCode().substring(6, 8);
                        String layR3Code = selectVillageList.get(j).getLayRCode().substring(8, 10);
                        String layR4Code = selectVillageList.get(j).getLayRCode().substring(10);
                        String layRCode = selectVillageList.get(j).getLayRCode();
                        String layR4Name = selectVillageList.get(j).getLayR4ListName();


                        String addressCode = "";

                        sqlH.addSelectedVillage(countryCode, layR1Code, layR2Code, layR3Code, layR4Code, layRCode, layR4Name, addressCode);


                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            selectedVillageJson.put(mData);
        }


        Log.d("RefatJson", selectedVillageJson.toString());
        return selectedVillageJson;

    }


    public static JSONArray jsonConverter(String string) {

        JSONArray jsonArray = new JSONArray();

        JSONObject mData = new JSONObject();

        try {
            mData.put("selectedLayR4Code", string);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        jsonArray.put(mData);



        Log.d(TAG, jsonArray.toString());
        return jsonArray;

    }

    /**
     * <p>This method convert  selected FDP data into json array</p>
     *
     * @param className     ClassName is String Variable. That track down the invoking Class.
     *                      <p>If Calling Class is LogIn Than selected Village will be inserted into db</p>
     * @param selectFdpList Selected (max)2 Fdp Code(Food distribution point)
     * @param sqlH          Database Helper
     * @return The json Data of selected fdp Code
     */
    public static JSONArray fdpCodeJSONConverter(String className, ArrayList<FDPItem> selectFdpList, SQLiteHandler sqlH) {

        JSONArray selectedFdpJson = new JSONArray();

        for (int j = 0; j < selectFdpList.size(); j++) {

            JSONObject mData = new JSONObject();


            try {

                mData.put("selectedLayR4Code", selectFdpList.get(j).getAdmCountryCode() + selectFdpList.get(j).getFDPCode()); /// use same key for gynerick
                if (className.equals("LoginActivity")) {
                    /** insert the into the data base */
                    String countryCode = selectFdpList.get(j).getAdmCountryCode();
                    String FdpCode = selectFdpList.get(j).getFDPCode();
                    String FdpName = selectFdpList.get(j).getFDPName();

                    Log.d(TAG, " countryCode : " + countryCode +
                            " FdpCode   : " + FdpCode + " FdpName   : " + FdpName);
                    sqlH.addSelectedFDP(countryCode, FdpCode, FdpName);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            selectedFdpJson.put(mData);
        }


        Log.d(TAG, selectedFdpJson.toString());
        return selectedFdpJson;

    }


    /**
     * @discription:
     */
    /**
     * <p>This method convert  selected Service Center Code into json array</p>
     *
     * @param className             ClassName is String Variable. That track down the invoking Class.
     *                              <p>If Calling Class is LogIn Than selected Village will be inserted into db</p>
     * @param selectedSrvCenterList Selected (max)2 Service Center Code
     * @param sqlH                  Database Helper
     * @return The json Data of selected Service Center Code Code
     */
    public static JSONArray srvCenterCodeJSONConverter(String className, ArrayList<ServiceCenterItem> selectedSrvCenterList, SQLiteHandler sqlH) {

        JSONArray selectedFdpJson = new JSONArray();

        for (int j = 0; j < selectedSrvCenterList.size(); j++) {

            JSONObject mData = new JSONObject();


            try {

                mData.put("selectedLayR4Code", selectedSrvCenterList.get(j).getAdmCountryCode() + selectedSrvCenterList.get(j).getServiceCenterCode()); /// use same key for gynerick
                if (className.equals("LoginActivity")) {
                    /** insert the into the data base */
                    String countryCode = selectedSrvCenterList.get(j).getAdmCountryCode();
                    String ServiceCenterCode = selectedSrvCenterList.get(j).getServiceCenterCode();
                    String ServiceCenterName = selectedSrvCenterList.get(j).getServiceCenterName();
                    Log.d(TAG, " countryCode : " + countryCode + " ServiceCenterCode   : " + ServiceCenterCode + " ServiceCenterName   : " + ServiceCenterName);
                    sqlH.addSelectedServiceCenter(countryCode, ServiceCenterCode, ServiceCenterName);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            selectedFdpJson.put(mData);
        }


        Log.d(TAG, selectedFdpJson.toString());
        return selectedFdpJson;

    }


    /**
     * this method will provided the mode of operation
     *
     * @param activity Activity
     * @return Operation Mode which could be Registration , Service , Distribution
     */

    public static int getAppOperationMode(Activity activity) {
        SharedPreferences settings;
        settings = activity.getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE); //1
        return settings.getInt(UtilClass.OPERATION_MODE, 0);

    }


    /**
     * @since : 2016-09-18
     * <p/>
     * calculate Graduation date for all program and service
     * todo:      pw,lm,cu2,ca2,agr,ddr, uat
     */
    public static String calculateGRDDate(String cCode, String donorCode, String awardCode, SQLiteHandler sqLiteHandler) {
        return sqLiteHandler.getAwardGraduation(cCode, donorCode, awardCode);
    }

    public static int getMaxNumberFromList(Integer[] surveyNumbers) {
        Arrays.sort(surveyNumbers);
        int surveyNumber = surveyNumbers[surveyNumbers.length - 1] + 1;
        return surveyNumber;
    }

    public static String getIMEINumber(Context context) {
        TelephonyManager teMg = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return teMg.getDeviceId();
    }


    /**
     *
     * @param context invoking context class
     * @return mac address of device
     */
    public static String getDeviceId(Context context) {
        String deviceId;
       String macAddress=getMacAddress(context);
       String androidId=getAndroidId(context);
        if (androidId.length()>13)
            deviceId=macAddress+":"+androidId.substring(androidId.length()-6,androidId.length()-1);
           else
            deviceId=macAddress;

        return deviceId;

    }

    public static String getMacAddress(Context context) {
        WifiManager m_wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return m_wm.getConnectionInfo().getMacAddress();
    }

    public static String getAndroidId(Context context) {
        Activity activity= (Activity) context;
        return Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);

    }

}
