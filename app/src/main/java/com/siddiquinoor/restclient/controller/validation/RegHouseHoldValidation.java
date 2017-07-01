package com.siddiquinoor.restclient.controller.validation;

import android.content.Context;

import com.siddiquinoor.restclient.manager.SQLiteHandler;

/**
 * Created by USER on 2/28/2016.
 */
public class RegHouseHoldValidation extends Validation {

    public static final int NO_REGISTRATION_ID = 1;
    public static final int NO_HOUSE_HOLD_NAME = 2;
    public static final int INVALID_NAME_FORMATE = 3;
    public static final int SAME_HOUSE_HOLD_NAME_EXIST = 4;
    public static final int HOUSE_HOLD_SIZE_ZERO = 5;
    public static final int NO_REGISTRATION_DATE_SELECTED = 6;
    public static final int SAME_HOUSE_HOLD_ID_EXIST = 7;
    public static final int VALIDATION_OK = 10;

    public int malawiHouseHoldValidation(Context context,String cCode,String distCode,String upCode,String unCode,String vCode, String hhId, String HHname, String hhSize, String regDate, boolean EditingMode) {
        sqlH = new SQLiteHandler(context);

        if (hhId.equals("")) {
            return NO_REGISTRATION_ID;
        } else if (HHname.equals("")) {
            return NO_HOUSE_HOLD_NAME;
        } else if (countNoWhitSpace(HHname) > countNoWord(HHname)) {
            return INVALID_NAME_FORMATE;
        } else if (isHousHoldExits(HHname) && !EditingMode) {
            return SAME_HOUSE_HOLD_NAME_EXIST;
        } else if (sqlH.ifThisHHIDExitsInRegHHTable(cCode, distCode, upCode, unCode, vCode, hhId)) {
            return SAME_HOUSE_HOLD_ID_EXIST;
        } else if (hhSize.equals("")) {
            return HOUSE_HOLD_SIZE_ZERO;
        } else if (regDate.equals("")) {
            return NO_REGISTRATION_DATE_SELECTED;
        } else
            return VALIDATION_OK;

    }

    private boolean isHousHoldExits(String name) {

        return sqlH.ifThisNameExitsInRegHHTable(name);
    }

    private int countNoWord(String sname) {
        int wordNo = sname.isEmpty() ? 0 : sname.split(" \\s+").length;
        return wordNo;
    }

    private int countNoWhitSpace(String sname) {
        int countSpaces = sname == null ? 0 : sname.length() - sname.replace(" ", "").length();
        return countSpaces;
    }


}
