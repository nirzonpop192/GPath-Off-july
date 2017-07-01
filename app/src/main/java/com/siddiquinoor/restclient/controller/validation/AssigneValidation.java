package com.siddiquinoor.restclient.controller.validation;

import android.content.Context;
import android.util.Log;

import com.siddiquinoor.restclient.data_model.RegNAssgProgSrv;

/**
 * Created by USER on 2/18/2016.
 */
public class AssigneValidation extends Validation {

    public static final int INVALID_DATE_FORMAT = 1;
    public static final int INVALID_DATE = 2;
    public static final int MEM_ALREADY_ASSIGNED_D_S = 3;// member already assigned in differet service
    public static final int VALIDATION_OK = 10;
    public static final String TAG = AssigneValidation.class.getSimpleName();
    private static final int DATE_IS_NOT_SELECTED_YET =4 ;


    public AssigneValidation() {

    }


    public int assigne_PEER_awardValidation(String rDate, Context context, RegNAssgProgSrv regData) {
        // first it the date is selected or not
        Log.d(TAG, "RegDate :" + rDate);
        //if(rDate!=null) {
        if (rDate == null) {
            Log.d(TAG,"the Date is null");
            return DATE_IS_NOT_SELECTED_YET;
        } else if (rDate.equals("null") || rDate.equals("Registration Date")) {
            return INVALID_DATE_FORMAT;

        }
        //a safety check block
        /*else if (!rDate.isEmpty() && !rDate.equals("")) {
            // check the the regDate is in valid date range
           *//* if (testRegistrationDateValidity(context, rDate, regData.getCountryCode())) {

                return INVALID_DATE;
            }
            *//*
            // check Does member already assign in different service
           *//*  if (sqlH.doesMemberAssignedInDifferentService(regData)) {
                Log.d(TAG, " Data Exits Indifferent Criteria ");
                return MEM_ALREADY_ASSIGNED_D_S;
            }*//*
        }
        // }*/

        // if no condition satisfied  than it is a valid data
        else
        return VALIDATION_OK;

    }
}// end of class
