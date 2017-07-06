package com.siddiquinoor.restclient.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;


/**
 * Created by Faisal
 * on 7/5/2017.
 * this class work withe app's version type task
 */
public class VersionUtils {

    private static final String TAG = VersionUtils.class.getSimpleName();

    /**
     *
     * @param context the context of the  Application
     * @return the version name of the application
     */

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
