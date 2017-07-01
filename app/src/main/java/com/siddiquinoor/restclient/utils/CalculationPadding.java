package com.siddiquinoor.restclient.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

import android.view.View;
import android.widget.Button;

/**
 * Created by pop
 *
 * @version 0.0.0
 *          project: Device Measurement
 * @since 8/12/2016.
 */
public class CalculationPadding {
    private static final String TAG = CalculationPadding.class.getSimpleName();


    public int calculateSidePaddingSingleButton(Context context, Drawable drawable) {
        DeviceSize deviceSize = new DeviceSize(context);
        int deviceWidth = (int) deviceSize.getDpWidth();

        DrawableImageSize imageSize = new DrawableImageSize(drawable);
        int imageWidth = imageSize.getmWidth();
        imageWidth = imageWidth / 2;
        int p = (int) (deviceWidth / 2) - imageWidth;

        return p;
    }

    /**
     * @param context  Calling class context
     * @param drawable Dwable Image
     * @param view     Button
     * @return the padding amount in pixel
     */
    public int calculateViewPadding(Context context, Drawable drawable, Button view) {

        DisplayMetrics disMetrics = context.getResources().getDisplayMetrics();

        float viewWidth = view.getWidth();

        DrawableImageSize imageSize = new DrawableImageSize(drawable);

        /**
         * get the image width in pixel
         */
        float imageWidth = imageSize.getmWidth() * disMetrics.density;

        /**
         * get one side value
         */
        imageWidth /= 2;
        viewWidth /= 2;

        int p = (int) (viewWidth - imageWidth);

        /**
         * Calibration 20 pixel Added
         */
        p += 20;



        return p;
    }


}
