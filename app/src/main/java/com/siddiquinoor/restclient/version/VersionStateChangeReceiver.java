package com.siddiquinoor.restclient.version;


import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.controller.AppConfig;
import com.siddiquinoor.restclient.controller.AppController;
import com.siddiquinoor.restclient.network.ConnectionDetector;
import com.siddiquinoor.restclient.utils.VersionUtils;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;
import com.siddiquinoor.restclient.views.notifications.CustomToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;


/**
 * Created by TD-Faisal
 * Note  no Dialog can be set in Broadcast Receiver
 * on 7/5/2017.
 */
public class VersionStateChangeReceiver extends BroadcastReceiver {

    private static final String TAG = VersionStateChangeReceiver.class.getSimpleName();

    @Override
    public void onReceive(final Context context, Intent intent) {


        ConnectionDetector detector = new ConnectionDetector(context);
        if (detector.isConnectingToInternet()) {

            checkVersionName(new VolleyCallback() {

                @Override
                public void onSuccess(String result) {
                    try {
                        JSONObject jObj = new JSONObject(result);

                        if (!jObj.isNull("apk_version")) {


                            String appSerial;
                            String version = "";
                            JSONArray village = jObj.getJSONArray("apk_version");

                            int size = village.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject vil = village.getJSONObject(i);

                                appSerial = vil.getString("AppSerial");
                                version = vil.getString("Version");

                                if (version.length() > 0)
                                    break;
                            }

//                            Log.d("POP_192", "Version " + version);
                            if (!version.equals("")) {
                                if (!VersionUtils.getVersionName(context).equals(version)) {


                                    ADNotificationManager dialog = new ADNotificationManager();
                                    String msg = "New Apk version available for Download.You will get less than 5 minutes.\nInstall the apk by FileExplorer or FileManager apps in Download Folder";

                                    CustomToast.show(context, msg);
                                    CustomToast.show(context, msg);



                                    //get destination to update file and set Uri
                                    //TODO: First I wanted to store my update .apk file on internal storage for my app but apparently android does not allow you to open and install
                                    //aplication with existing package from there. So for me, alternative solution is Download directory in external storage. If there is better
                                    //solution, please inform us in comment
                                    String destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
                                    String fileName = AppConfig.DOWNLOADED_APK_NAME;

                                    final Uri uri_apk_view = Uri.parse("file://" + destination);
                                    destination += fileName;
                                    final Uri uri = Uri.parse("file://" + destination);

                                    //Delete update file if exists
                                    File file = new File(destination);
                                    if (file.exists())
                                        file.delete();

                                    //get url of app on server
                                    String url = AppConfig.NEW_APK_DOWNLOAD_LINK;

                                    //set downloadmanager
                                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                                    request.setDescription(context.getString(R.string.notification_description));
                                    request.setTitle(context.getString(R.string.app_name));

                                    //set destination
                                    request.setDestinationUri(uri);

                                    // get download service and enqueue file
                                    final DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                                    final long downloadId = manager.enqueue(request);
//set BroadcastReceiver to install app when .apk is downloaded
                                    BroadcastReceiver onComplete = new BroadcastReceiver() {
                                        public void onReceive(Context ctxt, Intent intent) {



                                            Intent install = new Intent(Intent.ACTION_GET_CONTENT);
                                            Uri uri = Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/");
                                            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            install.setDataAndType(uri, "resource/folder");

//                                            context.startActivity(Intent.createChooser(install, "Open folder"));

                                            Intent chooserIntent = Intent.createChooser(install, "Open With");
                                            chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            context.startActivity(chooserIntent);


//                                Intent install = new Intent(Intent.ACTION_GET_CONTENT);
//                                            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                            install.setDataAndType(Uri.fromFile(new File(uri.toString())),
//                                                    manager.getMimeTypeForDownloadedFile(downloadId));
//
//                                            install.setPackage(context.getPackageName());
//                                            context.startActivity(install);
//
//                                            context.unregisterReceiver(this);
//                                           // context.finish();

                                        }
                                    };
                                    //register receiver for when .apk download is compete

                                    context.getApplicationContext().registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

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
        String tag_string_req = "version_tag";


        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.API_LINK_VER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                AppController.getInstance().getRequestQueue().getCache().clear();                   // clear catch memory from heap

                String errorResult = response.substring(9, 14);
                boolean error = !errorResult.equals("false");                                       //If Json String  get False than it return false

                if (!error) {                                  //
                    callback.onSuccess(response);

                } else {
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);       // Error in login. Invalid UserName or Password
                    Log.e(TAG,errorMsg);
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());

            }
        });


        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);                      // Adding request to request queue

    }

    /**
     * custom Interface for call back method
     */
    public interface VolleyCallback {
        void onSuccess(String result);
    }
}
