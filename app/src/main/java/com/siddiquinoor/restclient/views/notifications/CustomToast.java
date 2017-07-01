package com.siddiquinoor.restclient.views.notifications;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;

/**
 * Created by pop on 3/18/2017.
 * this class
 */

public class CustomToast {

    // class variable
//    private Context mContext;
//
//    public CustomToast(Context mContext) {
//        this.mContext = mContext;
//    }

    public static void show(Context mContext,String msg) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View customToastRoot = inflater.inflate(R.layout.custom_toast_layout, null);
        TextView text = (TextView) customToastRoot.findViewById(R.id.tv_toast_msg);
        text.setText(msg);
        Toast customToast = new Toast(mContext);

        customToast.setView(customToastRoot);
        customToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        customToast.setDuration(Toast.LENGTH_LONG);
        customToast.show();
    }

    public void   showSaveMassage(){}


}
