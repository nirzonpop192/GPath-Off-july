package com.siddiquinoor.restclient.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;

/**
 * Created by pop on 2/13/2017.
 *
 * @author Faisal Mohammad
 */

public class CameraUtils {

    public static final int CAMERA_REQUEST_1 = 10;
    public static final int CAMERA_REQUEST_2 = 20;
    public static final int CAMERA_REQUEST_3 = 30;
    public static final int CAMERA_REQUEST_4 = 40;
    public static final int CAMERA_REQUEST_5 = 50;

    public static final int CAPTURED_IMAGE = 0;
    public static final int DELETE_IMAGE = 1;
    public static final int CANCEL = 2;

    /**
     * @param context invoking context
     * @return info about does device support Camera
     */
    public static boolean isDeviceSupportCamera(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);

    }

    /**
     * if this button is clicked, close current activity
     *
     * @param requestCode request Code for image
     */
    public void captureImageAlert(final Activity activity, final int requestCode) {
        //Intent cameraIntent;
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(cameraIntent, requestCode);


    }
}
