package com.siddiquinoor.restclient.setting;

import android.content.Context;
import android.content.SharedPreferences;

import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.utils.UtilClass;

/**
 * Created by TD-Android on 7/16/2017.
 */

public class Preference {

    public static boolean getSyncMode(Context context) {
        SharedPreferences settings = context.getSharedPreferences(BaseActivity.APP_PREFERENCES, Context.MODE_PRIVATE);
        boolean syncMode = settings.getBoolean(UtilClass.SYNC_MODE_KEY, true);

        return syncMode;
    }
}
