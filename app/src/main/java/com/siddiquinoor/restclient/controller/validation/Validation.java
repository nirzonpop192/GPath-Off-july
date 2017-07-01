package com.siddiquinoor.restclient.controller.validation;

import android.content.Context;

import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;

import java.text.ParseException;
import java.util.HashMap;

/**
 * Created by Faisal on 2/24/2016.
 */
public class Validation extends BaseActivity {

    protected SQLiteHandler sqlH;

    protected boolean testRegistrationDateValidity(Context context, String registrationDate, String countryCode) {
        sqlH = new SQLiteHandler(context);
        HashMap<String, String> dateRange = sqlH.getDateRange(countryCode);
        String start_date = dateRange.get("sdate");
        String end_date = dateRange.get("edate");
        if (registrationDate != null && start_date != null && end_date != null) {
            try {
                if (!getValidDateRange(registrationDate, start_date, end_date)) {
                    return true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
