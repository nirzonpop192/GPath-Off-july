package com.siddiquinoor.restclient.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.controller.AppConfig;
import com.siddiquinoor.restclient.controller.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Faisal
 * on 7/5/2017.
 * this class work withe app's version type task
 */
public class VersionUtils {

    private static final String TAG = VersionUtils.class.getSimpleName();

    public static String getVersionName(Context context) {

        PackageInfo pInfo = null;

        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = "";

        if (pInfo != null)                                                                          //safety block
            version = pInfo.versionName;

        Log.d(TAG, "version Name :" + version);
        return version;
    }



}
