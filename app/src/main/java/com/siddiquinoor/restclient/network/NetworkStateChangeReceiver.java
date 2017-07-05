package com.siddiquinoor.restclient.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.siddiquinoor.restclient.controller.AppConfig;
import com.siddiquinoor.restclient.controller.AppController;
import com.siddiquinoor.restclient.utils.VersionUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by TD-Faisal
 * on 7/5/2017.
 */
public class NetworkStateChangeReceiver extends BroadcastReceiver {

    private static final String TAG = NetworkStateChangeReceiver.class.getSimpleName();

    @Override
    public void onReceive(final Context context, Intent intent) {


        ConnectionDetector detector = new ConnectionDetector(context);
        if (detector.isConnectingToInternet()) {
//            Toast.makeText(context, "net", Toast.LENGTH_SHORT).show();
            checkVersionName(new VolleyCallback() {
                @Override
                public void onSuccess(String result) {
                    try {
                        JSONObject jObj = new JSONObject(result);

                        if (!jObj.isNull("apk_version")) {


                            String AppSerial;
                            String Version = "";
                            JSONArray village = jObj.getJSONArray("apk_version");

                            int size = village.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject vil = village.getJSONObject(i);

                                AppSerial = vil.getString("AppSerial");
                                Version = vil.getString("Version");

                                if (Version.length() > 0)
                                    break;
                            }

                            Log.d("POP_192", "Version " + Version);
                            if (!Version.equals("")) {
                                if (!VersionUtils.getVersionName(context).equals(Version)){
                                    // do something




                                }

                            }


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }


    public static void checkVersionName(final VolleyCallback callback) {
        // Tag used to cancel the request
        String tag_string_req = "enu";


        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.API_LINK_VER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                AppController.getInstance().getRequestQueue().getCache().clear();  // clear catch


                /**
                 *  DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY
                 */

                String errorResult = response.substring(9, 14);


                boolean error = !errorResult.equals("false");                                       //If Json String  get False than it return false

                if (!error) {

//                    VersionUtils.getVersionName().equals()

                    callback.onSuccess(response);

                } else {
                    // Error in login. Invalid UserName or Password
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);


                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());


            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    public interface VolleyCallback {
        void onSuccess(String result);
    }
}
