package com.siddiquinoor.restclient.manager.sqlsyntax;

import com.siddiquinoor.restclient.manager.SQLiteHandler;

import static com.siddiquinoor.restclient.manager.SQLiteHandler.*;

import static com.siddiquinoor.restclient.manager.SQLiteHandler.FUND_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.FUND_SOURCE_COL;

/**
 * Created by Faisal on 2/18/2016.
 * This Class Contain The Schema
 */
public class Schema {

    private static final String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS ";
    private static final String PRIMARY_KEY = "PRIMARY KEY";


    /**
     * The patten of Table & serial
     * 1> userLogin
     * 2> Country
     * 3> Layer Label
     * 4> District
     */
    // userLogin
    public static String sqlCreateUserLoginTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LOGIN_TABLE + "("
                + SQLiteHandler.USER_ID + " TEXT,"
                + SQLiteHandler.COUNTRY_CODE + " TEXT,"
                + SQLiteHandler.USER_LOGIN_NAME + " TEXT,"
                + SQLiteHandler.USER_LOGIN_PW + " TEXT,"
                + SQLiteHandler.USER_FIRST_NAME + " TEXT,"
                + SQLiteHandler.USER_LAST_NAME + " TEXT,"
                + SQLiteHandler.USER_EMAIL + " TEXT,"
                + SQLiteHandler.USER_EMAIL_VERIFICATION + " TEXT,"
                + SQLiteHandler.USER_STATUS + " TEXT,"
                + ENTRY_BY + " TEXT," + ENTRY_DATE + " DATE" + ")";
    }


    public static String sqlCreateStaffMasterTable() {


        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.STAFF_MASTER_TABLE + "("
                + SQLiteHandler.STAFF_ID_COL + " TEXT ,"
                + SQLiteHandler.STAFF_COUNTRY_CODE + " TEXT,"
                + SQLiteHandler.STAFF_NAME_COL + " TEXT,"
                + SQLiteHandler.ORG_N_CODE_COL + " TEXT,"
                + SQLiteHandler.ORG_N_DESG_N_CODE_COL + " TEXT,"
                + SQLiteHandler.STAFF_STATUS_COL + " TEXT,"
                + SQLiteHandler.STAFF_CATEGORY_COL + " TEXT,"
                + SQLiteHandler.USER_LOGIN_NAME + " TEXT,"
                + SQLiteHandler.USER_LOGIN_PW + " TEXT,"
                + SQLiteHandler.STAFF_ADMIN_ROLE_COL + " TEXT "
                + ")";


    }

    // Country schema
    public static String sqlCreateCountry() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.COUNTRY_TABLE +
                "(" + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_COUNTRY_CODE + " VARCHAR(5), " + SQLiteHandler.COUNTRY_COUNTRY_NAME + " VARCHAR(50))";
    }

    // Layer Label schema
    public static String sqlCreateLayerLabel() {
        return CREATE_TABLE_IF_NOT_EXISTS + GEO_LAY_R_MASTER_TABLE + "("
                + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + LAYER_LAVLE_COUNTRY_CODE + " VARCHAR(4), "
                + GEO_LAY_R_CODE_COL + " VARCHAR(1), "
                + GEO_LAY_R_NAME_COL + " VARCHAR(50))";
    }

    // District
    public static String sqlCreateDistrict() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.GEO_LAY_R1_LIST_TABLE + "("
                + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(5), "
                + GEO_LAY_R_CODE_COL + " VARCHAR(2),"
                + LAY_R_LIST_CODE_COL + " VARCHAR(5), "
                + LAY_R_LIST_NAME_COL + " VARCHAR(50))";
    }

    // upazilla
    public static String sqlCreateUpazilla() {
        return CREATE_TABLE_IF_NOT_EXISTS + GEO_LAY_R2_LIST_TABLE + "("
                + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(50), "
                + GEO_LAY_R_CODE_COL + " VARCHAR(2),"
                + LAY_R1_LIST_CODE_COL + " VARCHAR(50), "
                + LAY_R2_LIST_CODE_COL + " VARCHAR(50), "
                + LAY_R2_LIST_NAME_COL + " VARCHAR(50))";
    }

    public static String sqlCreateUnit() {
        return "CREATE TABLE IF NOT EXISTS " + GEO_LAY_R3_LIST_TABLE + "("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", " + ADM_COUNTRY_CODE_COL + " VARCHAR(4)" +
                ", " + GEO_LAY_R_CODE_COL + " VARCHAR(2)" +
                ", " + LAY_R1_LIST_CODE_COL + " VARCHAR(2)" +
                ", " + LAY_R2_LIST_CODE_COL + " VARCHAR(2)" +
                ", " + LAY_R3_LIST_CODE_COL + " VARCHAR(2)" +
                ", " + LAY_R3_LIST_NAME + " VARCHAR(50))";
    }


    // RegNHHMemschema schema
    public static String sqlCreateRegMember() {
        return CREATE_TABLE_IF_NOT_EXISTS + REGISTRATION_MEMBER_TABLE + "("
//                + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT "
//                + " , " +
                + ADM_COUNTRY_CODE_COL + " VARCHAR(5) "
                + " , " + LAY_R1_LIST_CODE_COL + " VARCHAR(5) "
                + " , " + LAY_R2_LIST_CODE_COL + " VARCHAR(5) "
                + " , " + LAY_R3_LIST_CODE_COL + " VARCHAR(5) "
                + " , " + LAY_R4_LIST_CODE_COL + " VARCHAR(5) "
                + " , " + HHID_COL + " VARCHAR(7) "
                + " , " + HH_MEM_ID + " VARCHAR(5) "
                + " , " + MEM_NAME_COL + " VARCHAR(50) "
                + " , " + SEX_COL + " VARCHAR(8) "
                + " , " + RELATION_COL + " VARCHAR(50) "
                + " , " + ENTRY_BY + " VARCHAR(10) "
                + " , " + ENTRY_DATE + " VARCHAR(20) "
                + " , " + LMP_DATE + " DATE "
                + " , " + CHILD_DOB + " DATE "
                + " , " + ELDERLY + " VARCHAR(1) "
                + " , " + DISABLE + " VARCHAR(1) "
                + " , " + MEM_AGE + " VARCHAR(5) "
                + " , " + REG_DATE_COL + " VARCHAR(20) "
                + " , " + BIRTH_YEAR_COL + " VARCHAR(20) "
                + " , " + MARITAL_STATUS_COL + " VARCHAR(20) "
                + " , " + CONTACT_NO_COL + " VARCHAR(50) "
                + " , " + MEMBER_OTHER_ID_COL + " VARCHAR(100) "
                + " , " + MEM_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + MEM_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + MEM_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + PHOTO_COL + " BLOB "
                + " , " + TYPE_ID_COL + " VARCHAR(50) "
                + " , " + ID_NO_COL + " VARCHAR(50) "
                + " , " + V_BSC_MEM_1_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + V_BSC_MEM_1_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + V_BSC_MEM_1_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + V_BSC_MEM_1_TITLE_COL + " VARCHAR(50) "
                + " , " + V_BSC_MEM_2_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + V_BSC_MEM_2_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + V_BSC_MEM_2_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + V_BSC_MEM_2_TITLE_COL + " VARCHAR(50) "
                + " , " + PROXY_DESIGNATION_COL + " VARCHAR(50) "
                + " , " + PROXY_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + PROXY_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + PROXY_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + PROXY_BIRTH_YEAR_COL + " VARCHAR(20) "
                + " , " + PROXY_PHOTO_COL + " BLOB "
                + " , " + PROXY_TYPE_ID_COL + " VARCHAR(50) "
                + " , " + PROXY_ID_NO_COL + " VARCHAR(50) "
                + " , " + PROXY_BSC_MEM_1_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + PROXY_BSC_MEM_1_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + PROXY_BSC_MEM_1_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + PROXY_BSC_MEM_1_TITLE_COL + " VARCHAR(50) "
                + " , " + PROXY_BSC_MEM_2_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + PROXY_BSC_MEM_2_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + PROXY_BSC_MEM_2_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + PROXY_BSC_MEM_2_TITLE_COL + " VARCHAR(50) "
                + " , " + MEM_TYPE_FLAG + " VARCHAR(2) "
//                + " , " + GROUP_CODE_COL + " VARCHAR(5) "

                + " )";
    }



    /*  public static String sqlCreateRegMember() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT "
                + " , " + ADM_COUNTRY_CODE_COL + " VARCHAR(5) "
                + " , " + SQLiteHandler.LAY_R_LIST_NAME_COL + " VARCHAR(5) "
                + " , " + SQLiteHandler.LAY_R2_LIST_NAME_COL + " VARCHAR(5) "
                + " , " + LAY_R3_LIST_NAME + " VARCHAR(5) "
                + " , " + LAY_R4_LIST_NAME_COL + " VARCHAR(5) "
                + " , " + HHID_COL + " VARCHAR(7) "
                + " , " + HH_MEM_ID + " VARCHAR(5) "
                + " , " + SQLiteHandler.MEM_NAME_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.SEX_COL + " VARCHAR(8) "
                + " , " + SQLiteHandler.RELATION_COL + " VARCHAR(50) "
                + " , " + ENTRY_BY + " VARCHAR(10) "
                + " , " + ENTRY_DATE + " VARCHAR(20) "
                + " , " + SQLiteHandler.LMP_DATE + " DATE "
                + " , " + SQLiteHandler.CHILD_DOB + " DATE "
                + " , " + SQLiteHandler.ELDERLY + " VARCHAR(1) "
                + " , " + SQLiteHandler.DISABLE + " VARCHAR(1) "
                + " , " + SQLiteHandler.MEM_AGE + " VARCHAR(5) "
                + " , " + SQLiteHandler.REG_DATE_COL + " VARCHAR(20) "
                + " , " + SQLiteHandler.BIRTH_YEAR_COL + " VARCHAR(20) "
                + " , " + SQLiteHandler.MARITAL_STATUS_COL + " VARCHAR(20) "
                + " , " + SQLiteHandler.CONTACT_NO_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.MEMBER_OTHER_ID_COL + " VARCHAR(100) "
                + " , " + SQLiteHandler.MEM_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.MEM_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.MEM_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PHOTO_COL + " BLOB "
                + " , " + SQLiteHandler.TYPE_ID_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.ID_NO_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.V_BSC_MEM_1_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.V_BSC_MEM_1_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.V_BSC_MEM_1_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.V_BSC_MEM_1_TITLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.V_BSC_MEM_2_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.V_BSC_MEM_2_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.V_BSC_MEM_2_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.V_BSC_MEM_2_TITLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_DESIGNATION_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BIRTH_YEAR_COL + " VARCHAR(20) "
                + " , " + SQLiteHandler.PROXY_PHOTO_COL + " BLOB "
                + " , " + SQLiteHandler.PROXY_TYPE_ID_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_ID_NO_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BSC_MEM_1_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BSC_MEM_1_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BSC_MEM_1_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BSC_MEM_1_TITLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BSC_MEM_2_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BSC_MEM_2_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BSC_MEM_2_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BSC_MEM_2_TITLE_COL + " VARCHAR(50) "
                + " , " + GROUP_CODE_COL + " VARCHAR(5) "

                + " )";
    }*/


    public static String sqlCreateRegMemberCardPrintTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.MEMBER_CARD_PRINT_TABLE + " ( "
                + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(10), "
                + ADM_DONOR_CODE_COL + " VARCHAR(10), "
                + ADM_AWARD_CODE_COL + " VARCHAR(10), "
                + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R2_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R3_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R4_LIST_CODE_COL + " VARCHAR(10), "
                + HHID_COL + " VARCHAR(10), "
                + MEM_ID_COL + " VARCHAR(10), "
                + SQLiteHandler.REPORT_GROUP_COL + " VARCHAR(10), "
                + SQLiteHandler.REPORT_CODE_COL + " VARCHAR(50), "
                + SQLiteHandler.CARD_REQUEST_SL_COL + " VARCHAR(10), "
                + SQLiteHandler.CARD_PRINT_REASON_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.REQUEST_DATE_COL + " VARCHAR(50) , "
                + SQLiteHandler.PRINT_DATE_COL + " VARCHAR(50) , "
                + SQLiteHandler.PRINT_BY_COL + " VARCHAR(10), "
                + SQLiteHandler.DELIVERY_DATE_COL + " VARCHAR(50), "
                + SQLiteHandler.DELIVERY_BY_COL + " VARCHAR(10), "
                + SQLiteHandler.DELIVERY_STATUS_COL + " VARCHAR(10), "
                + ENTRY_BY + " VARCHAR(10), "
                + ENTRY_DATE + " VARCHAR(50), "

                + SYNC_COL + " BOOLEAN DEFAULT 0 )";
    }

    // Valid Date
    public static String sqlCreateDateRange() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.VALID_DATE_RANGE
                + "(" + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + SQLiteHandler.DATE_RANGE_COUNTRY_CODE + " VARCHAR(4), "
                + SQLiteHandler.DATE_START + " DATE, "
                + SQLiteHandler.DATE_END + " DATE)";
    }


    /**
     *
     */
    public static String sqlCreateCardPrintReasonTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LUP_REGN_CARD_PRINT_REASON_TABLE + "("
                + SQLiteHandler.CARD_PRINT_REASON_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.CARD_PRINT_REASON_TITLE_COL + " VARCHAR(50) )";
    }

    /**
     *
     */
    public static String sqlCreateCardTypeTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REPORT_TEMPLATE_TABLE + "("
                + ADM_COUNTRY_CODE_COL + " VARCHAR(6), "
                + SQLiteHandler.REPORT_LABLE_COL + " VARCHAR(50), "
                + SQLiteHandler.REPORT_CODE_COL + " VARCHAR(10) )";

    }

    /**
     *
     */

    public static String sqlCreateVillage() {
        return CREATE_TABLE_IF_NOT_EXISTS + GEO_LAY_R4_LIST_TABLE + "("
                + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(50), "
                + GEO_LAY_R_CODE_COL + " VARCHAR(2),"
                + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R2_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R3_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R4_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R4_LIST_NAME_COL + " VARCHAR(50), "
                + HOUSE_HOLD_TARGET + " VARCHAR(50))"; // new
    }

    // added @2015-09-29
    public static String sqlCreateGraduationTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REG_N_LUP_GRADUATION_TABLE + "("
                + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + ADM_PROG_CODE_COL + " VARCHAR(20), "
                + ADM_SRV_CODE_COL + " VARCHAR(50), "
                + GRD_CODE_COL + " VARCHAR(50), "
                + GRD_TITLE_COL + " VARCHAR(50), "
                + DEFAULT_CAT_ACTIVE_COL + " VARCHAR(50), "
                + DEFAULT_CAT_EXIT_COL + " VARCHAR(50) ) ";


    }


    public static String sqlCreateHouseHoldCategoryTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LUP_REGNH_HEAD_CATEGORY_TABLE + " ( "
                + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(20), "
                + CATEGORY_CODE_COL + " VARCHAR(20), "
                + CATEGORY_NAME_COL + " VARCHAR(120) ) "

                ;
    }


    public static String sqlCreateStaffGeoInfoAccessTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + STAFF_GEO_INFO_ACCESS_TABLE + " ( "
                + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + STAFF_CODE + " VARCHAR(6), "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(5), "
                + ADM_DONOR_CODE_COL + " VARCHAR(2), "
                + ADM_AWARD_CODE_COL + " VARCHAR(2), "/*
                + LAYR_LIST_CODE_COL + " VARCHAR(20), "*/
                + LAY_R_LIST_CODE_COL + " VARCHAR(5), "
//                + LAY_R2_LIST_CODE_COL + " VARCHAR(5), "
//                + LAY_R3_LIST_CODE_COL + " VARCHAR(5), "
//                + LAY_R4_LIST_CODE_COL + " VARCHAR(5) , "
                + BTN_NEW_COL + " VARCHAR(5), "
                + BTN_SAVE_COL + " VARCHAR(5), "
                + BTN_DEL_COL + " VARCHAR(5), "
                + BTN_PEPR_COL + " VARCHAR(5), "
                + BTN_APRV_COL + " VARCHAR(5), "
                + BTN_REVW_COL + " VARCHAR(5), "
                + BTN_VRFY_COL + " VARCHAR(5) , " // grad code colm
                + BTN_DTRAN_COL + " VARCHAR(5)" +
                " )";
    }

    public static String sqlCreateStaffSrvCenterAccessTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + STAFF_SRV_CENTER_ACCESS_TABLE + " ( "
                + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + STAFF_CODE + " VARCHAR(6), "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(5), "
                + SERVICE_CENTER_CODE_COL + " VARCHAR(7), "
                + BTN_NEW_COL + " VARCHAR(5), "
                + BTN_SAVE_COL + " VARCHAR(5), "
                + BTN_DEL_COL + " VARCHAR(5) " +
                " )";
    }


    public static String sqlCreateRegNCA2Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + REG_N_CA2_TABLE + " ( "
                //   + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(10), "
                + LAY_R1_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R2_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R3_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R4_LIST_CODE_COL + " VARCHAR(10), "
                + HHID_COL + " VARCHAR(10), "
                + MEM_ID_COL + " VARCHAR(10), "
                + REG_N_DAT_COL + " VARCHAR(50), "
                + CA2DOB_DATE_COL + " VARCHAR(50), "
                + ADM_PROG_CODE_COL + " VARCHAR(10), "
                + ADM_SRV_CODE_COL + " VARCHAR(10), "
                + GRDCODE_COL + " VARCHAR(10) , " // grad code colm
                + CA2_GRD_DATE_COL + " VARCHAR(50) , "
                + CHILD_NAME_COL + " VARCHAR(50) , "
                + CHILD_SEX_COL + " VARCHAR(2) , "
                + ENTRY_BY + " VARCHAR(50) DEFAULT '0', "
                + ENTRY_DATE + " VARCHAR(50) DEFAULT '0'" +
//                " , "
//                + SYNC_COL + " BOOLEAN DEFAULT 0 " +
                ")";
    }


    public static String sqlCreateRegNCU2Table() {
        return CREATE_TABLE_IF_NOT_EXISTS + REG_N_CU2_TABLE + " ( "

                + ADM_COUNTRY_CODE_COL + " VARCHAR(4), "
                + LAY_R1_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R2_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R3_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R4_LIST_CODE_COL + " VARCHAR(2), "
                + HHID_COL + " VARCHAR(6), "
                + MEM_ID_COL + " VARCHAR(2), "
                + REG_N_DAT_COL + " VARCHAR(50), "
                + CU2DOB_DATE_COL + " VARCHAR(50), "
                + ADM_PROG_CODE_COL + " VARCHAR(3), "
                + ADM_SRV_CODE_COL + " VARCHAR(2), "
                + GRDCODE_COL + " VARCHAR(2) , " // grad code colm
                + CU2_GRD_DATE_COL + " VARCHAR(50) , "
                + CHILD_NAME_COL + " VARCHAR(50) , "
                + CHILD_SEX_COL + " VARCHAR(2) , "
                + ENTRY_BY + " VARCHAR(50) , "
                + ENTRY_DATE + " VARCHAR(50) " +
                //+
                //  " , "
              /*  + SYNC_COL + "  BOOLEAN DEFAULT 0 "+*/
                " )";
    }

    public static String sqlCreateRegNPWTable() {


        return CREATE_TABLE_IF_NOT_EXISTS + REG_N_PW_TABLE + " ( "
//                + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(10), "
                + LAY_R1_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R2_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R3_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R4_LIST_CODE_COL + " VARCHAR(10), "
                + HHID_COL + " VARCHAR(10), "
                + MEM_ID_COL + " VARCHAR(10), "
                + REG_N_DAT_COL + " VARCHAR(50), "
                + LMP_DATE_COL + " VARCHAR(50), "
                + ADM_PROG_CODE_COL + " VARCHAR(10), "
                + ADM_SRV_CODE_COL + " VARCHAR(10), "
                + GRDCODE_COL + " VARCHAR(10) , "
                + PW_GRD_DATE_COL + " VARCHAR(50) , "
                + ENTRY_BY + " VARCHAR(50) DEFAULT '0', "
                + ENTRY_DATE + " VARCHAR(50) DEFAULT '0'" +
//                " , " + SYNC_COL + "  BOOLEAN DEFAULT 0" +
                " )";
    }

    public static String sqlCreateServiceCenterTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SRV_CENTER_TABLE + " ( "
                + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(20), "
                + SERVICE_CENTER_CODE_COL + " VARCHAR(20), "
                + SERVICE_CENTER_NAME_COL + " VARCHAR(100), "
                + FDP_CODE_COL + " VARCHAR(5)"
                + " )";

    }


    public static String sqlCreateCountryAwardTable() {

        return CREATE_TABLE_IF_NOT_EXISTS + ADM_COUNTRY_AWARD_TABLE + "("

                + ADM_COUNTRY_CODE_COL + " VARCHAR(20), "
                + ADM_DONOR_CODE_COL + " VARCHAR(20), "
                + ADM_AWARD_CODE_COL + " VARCHAR(20), "
                + AWARD_REF_N_COL + " VARCHAR(50), "
                + AWARD_START_DATE_COL + " VARCHAR(100), "
                + AWARD_END_DATE_COL + " VARCHAR(100), "
                + AWARD_SHORT_NAME_COL + " VARCHAR(50), "
                + AWARD_STATUS_COL + " VARCHAR(50) "

                + " , " + PRIMARY_KEY + " (" + ADM_COUNTRY_CODE_COL + ", " + ADM_DONOR_CODE_COL + ", " + ADM_AWARD_CODE_COL + " ) "
                + " ) ";
    }

    public static String sqlCreateAdmAwardTable() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.ADM_AWARD_TABLE + "("
                + AWARD_CODE_COL + " VARCHAR(20), "
                + ADM_DONOR_CODE_COL + " VARCHAR(20), "
                + AWARD_NAME_COL + " VARCHAR(100), "
                + AWARD_SHORT_COL + " VARCHAR(50) "
                + " ) ";
    }

    /**
     * creating Sub Assigne Table
     **/

    public static String sqlCreateRegN_AGR_Table() {
        return CREATE_TABLE_IF_NOT_EXISTS + REG_N_AGR_TABLE + " ("

//                + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "

                + ADM_COUNTRY_CODE_COL + " VARCHAR(4), "
                + LAY_R1_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R2_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R3_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R4_LIST_CODE_COL + " VARCHAR(2), "
                + HHID_COL + " VARCHAR(5), "
                + MEM_ID_COL + " VARCHAR(20), "
                + REG_N_DAT_COL + " VARCHAR(20), "
                + ELDERLY_YN_COL + " VARCHAR(1), "
                + LAND_SIZE_COL + " VARCHAR(21), "
                + DEPEND_ON_GANYU_COL + " VARCHAR(1), "
                + WILLINGNESS_COL + " VARCHAR(1), "
                + WINTER_CULTIVATION_COL + " VARCHAR(1), "
                + VULNERABLE_HH_COL + " VARCHAR(1), "
                + PLANTING_VALUE_CHAIN_CROP_COL + " VARCHAR(3), "
                + AG_INVC_COL + " VARCHAR(1) DEFAULT 'N', "
                + AG_NASFAM_COL + " VARCHAR(1) DEFAULT 'N', "
                + AG_CU_COL + " VARCHAR(1) DEFAULT 'N', "
                + AG_OTHER_COL + " VARCHAR(1) DEFAULT 'N', "
                + AG_L_S_GOAT_COL + " INT DEFAULT 0, "
                + AG_L_S_CHICKEN_COL + " INT DEFAULT 0, "
                + AG_L_S_PIGION_COL + " INT DEFAULT 0, "
                + AG_L_S_OTHER_COL + " INT DEFAULT 0, "
                + ENTRY_BY + " VARCHAR(50) DEFAULT '0', "
                + ENTRY_DATE + " VARCHAR(50) DEFAULT '0' "
                + " )";


    }

    public static String sqlCreateDistributionTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + DISTRIBUTION_TABLE + " ( "

                + ADM_COUNTRY_CODE_COL + " VARCHAR(10), "
                + ADM_DONOR_CODE_COL + " VARCHAR(5), "
                + ADM_AWARD_CODE_COL + " VARCHAR(5), "
                + LAY_R1_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R2_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R3_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R4_LIST_CODE_COL + " VARCHAR(10), "
                + ADM_PROG_CODE_COL + " VARCHAR(10), "
                + ADM_SRV_CODE_COL + " VARCHAR(10), "
                + OP_MONTH_CODE_COL + " VARCHAR(10), "
                + FDP_CODE_COL + " VARCHAR(10), "
                + MEM_ID_15_D_COL + " VARCHAR(25), "
                + DISTRIBUTION_STATUS_COL + " VARCHAR(2), "
                + SRV_OP_MONTH_CODE_COL + " VARCHAR(2), "
                + DIST_FLAG_COL + " VARCHAR(100), "
                + WORK_DAY_COL + " VARCHAR(10), "
                + ENTRY_BY + " VARCHAR(20), "
                + ENTRY_DATE + " VARCHAR(22) "
                + " )";
    }

    public static String sqlCreateStaffFDPAccessTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + STAFF_FDP_ACCESS_TABLE + " ( "
                + STAFF_CODE + " VARCHAR(5) , "
                + STAFF_FDP_ACCESS_COUNTRY_CODE + " VARCHAR(5) , "
                + FDP_CODE_COL + " VARCHAR(5) , "
                + BTN_NEW_COL + " VARCHAR(1) , "
                + BTN_SAVE_COL + " VARCHAR(1) , "
                + BTN_DEL_COL + " VARCHAR(1)  "
                + " ) ";
    }

    public static String sqlCreateFDP_Master_Table() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.FDP_MASTER_TABLE + " ( "
                + SQLiteHandler.FDP_MASTER_COUNTRY_CODE + " VARCHAR(5) , "
                + FDP_CODE_COL + " VARCHAR(6) , "
                + SQLiteHandler.FDP_NAME_COL + " VARCHAR(100) , "
                + SQLiteHandler.FDA_CATEGORIES_CODE_COL + " VARCHAR(10) , "
                + SQLiteHandler.WH_CODE_COL + " VARCHAR(3)  ,"
                + FDP_MASTER_LAY_R1_LIST_CODE_COL + " VARCHAR(6)  ,"
                + FDP_MASTER_LAY_R2_LIST_CODE_COL + " VARCHAR(6)  "
                + " ) ";
    }


    public static String sqlCreate_RegN_CT_Table() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REG_N_CT_TABLE + " ( "
                + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(10), "
                + LAY_R_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R2_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R3_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R4_LIST_CODE_COL + " VARCHAR(10), "
                + HHID_COL + " VARCHAR(10), "
                + HH_MEM_ID + " VARCHAR(10), "
                + SQLiteHandler.C11_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C21_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C31_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C32_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C33_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C34_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C35_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C36_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C37_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C38_CT_PR + " VARCHAR(1) , "
                + ENTRY_BY + " VARCHAR(10), "
                + ENTRY_DATE + " VARCHAR(20) "
                + " )";
    }

    /**
     * In this table SQL Server will be Inserted
     */

    public static String sqlCreateUploadTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + UPLOAD_SYNTAX_TABLE + " ( "
                + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + SQL_QUERY_SYNTAX + " BLOB , "
                + SYNC_COL + " BOOLEAN DEFAULT 0 )";
    }

    public static String sqlCreateUploadPhysicalTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + UPLOAD_PHYSICAL_TABLE_SYNTAX_TABLE + " ( "
                + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT " +
                " , " + SQL_QUERY_SYNTAX + " BLOB" +
                " , " + SYNC_COL + " BOOLEAN DEFAULT 0 " +
                " , " + DT_R_SEQ_COL + " INTEGER " +
                " , " + PORTABLE_DEVICE_ID_COL + " TEXT " +
                ")";
    }


    public static String sqlCreateRegNLMTable() {


        return CREATE_TABLE_IF_NOT_EXISTS + REG_N_LM_TABLE + " ( "
                //    + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(5), "
                + LAY_R1_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R2_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R3_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R4_LIST_CODE_COL + " VARCHAR(2), "
                + HHID_COL + " VARCHAR(6), "
                + MEM_ID_COL + " VARCHAR(3), "
                + REG_N_DAT_COL + " VARCHAR(50), "
                + LM_DATE_COL + " VARCHAR(50), "
                + ADM_PROG_CODE_COL + " VARCHAR(3), "
                + ADM_SRV_CODE_COL + " VARCHAR(2), "
                + GRDCODE_COL + " VARCHAR(2) , "
                + LMGRDDATE_COL + " VARCHAR(50) , "
                + CHILD_NAME_COL + " VARCHAR(50) , "
                + CHILD_SEX_COL + " VARCHAR(2) , "

                + ENTRY_BY + " VARCHAR(5) , "
                + ENTRY_DATE + " VARCHAR(50) DEFAULT '0' " //+
//                " , " + SYNC_COL + "  BOOLEAN DEFAULT 0"
                + " )";

    }

    public static String sqlCreateADM_CountryProgram() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.COUNTRY_PROGRAM_TABLE + " ( "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(5)"
                + " , " + ADM_DONOR_CODE_COL + " VARCHAR(5)"
                + " , " + ADM_AWARD_CODE_COL + " VARCHAR(5)"
                + " , " + ADM_PROG_CODE_COL + " VARCHAR(5)"
                + " , " + ADM_SRV_CODE_COL + " VARCHAR(5)"
                + " , " + PROG_FLAG + " VARCHAR(1)"
                + " , " + FOOD_FLAG + " VARCHAR(1)"
                + " , " + NON_FOOD_FLAG + " VARCHAR(1)"
                + " , " + CASH_FLAG + " VARCHAR(1)"
                + " , " + VOUCHER_FLAG + " VARCHAR(1)"
                + " , " + DEFAULT_FOOD_DAYS_COL + " VARCHAR(4)"
                + " , " + DEFAULT_NO_FOOD_DAYS_COL + " VARCHAR(4)"
                + " , " + DEFAULT_CASH_DAYS_COL + " VARCHAR(4)"
                + " , " + DEFAULT_VOUCHAR_DAYS_COL + " VARCHAR(4)"
                + " , " + SERVICE_SPECIFIC_FLAG_COL + " VARCHAR(4)"
                + " , " + PRIMARY_KEY + " (" + ADM_COUNTRY_CODE_COL + ", " + ADM_DONOR_CODE_COL + ", " + ADM_AWARD_CODE_COL + ", " + ADM_PROG_CODE_COL + ", " + ADM_SRV_CODE_COL + " )"
                + " )";
    }

    public static String sqlCreateOpMonthTable() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.OP_MONTH_TABLE + " ( "

                + ADM_COUNTRY_CODE_COL + " VARCHAR(20) "
                + " , " + ADM_DONOR_CODE_COL + " VARCHAR(20) "
                + " , " + ADM_AWARD_CODE_COL + " VARCHAR(20) "
                + " , " + OPERATION_CODE_COL + " VARCHAR(20) "
                + " , " + OP_MONTH_CODE_COL + " VARCHAR(50) "
                + " , " + MONTH_LABEL_COL + " VARCHAR(50) "
                + " , " + START_DATE_COL + " VARCHAR(20) "
                + " , " + END_DATE_COL + " VARCHAR(20) "
                // // TODO: 5/5/2017  remove the usa fomate
                + " , " + USA_START_DATE_COL + " VARCHAR(20) "
                + " , " + USA_END_DATE_COL + " VARCHAR(20) "
                + " , " + STATUS + " VARCHAR(20) "
                + " , " + PRIMARY_KEY + " (" + ADM_COUNTRY_CODE_COL + ", " + ADM_DONOR_CODE_COL + ", " + ADM_AWARD_CODE_COL + ", " + OPERATION_CODE_COL + ", " + OP_MONTH_CODE_COL + " ) "
                + " ) ";
    }

    /**
     * added by Faisal Mohammad
     * create  LOCATION TABLE table
     * AdmGpsLocation_Schema schema
     * remarks-
     */
    public static String sqlCreateGpsLocationTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.GPS_LOCATION_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(5), "
                + GROUP_CODE_COL + " VARCHAR(3), "
                + SQLiteHandler.SUB_GROUP_CODE_COL + " VARCHAR(3), "
                + SQLiteHandler.LOCATION_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.LOCATION_NAME_COL + " VARCHAR(100), "
                + SQLiteHandler.LATITUDE_COL + " VARCHAR(20), "
                + SQLiteHandler.LONGITUDE_COL + " VARCHAR(20) , "
                + ENTRY_BY + " VARCHAR(20) , "
                + ENTRY_DATE + " VARCHAR(20) ) ";
    }

    /**
     * added by Faisal Mohammad
     * create GPS_SUB_GROUP_TABLE table
     * AdmServiceMaster_Schema schema
     * remarks-
     */

    public static String sqlCreateGpsSubGroupTable() {

        return CREATE_TABLE_IF_NOT_EXISTS + GPS_SUB_GROUP_TABLE + "("
                //    + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + GROUP_CODE_COL + " VARCHAR(20), "
                + SUB_GROUP_CODE_COL + " VARCHAR(20), "
                + SUB_GROUP_NAME_COL + " VARCHAR(50), "
                + DESCRIPTION_COL + " VARCHAR(100) ) ";
    }

    /**
     * added by Faisal Mohammad
     * create GPS_GROUP_TABLE table
     * <p>
     * remarks-
     */
    public static String sqlCreateGpsGroupTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.GPS_GROUP_TABLE + "("
                //   + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + GROUP_CODE_COL + " VARCHAR(20), "
                + GROUP_NAME_COL + " VARCHAR(20), "
                + SQLiteHandler.DESCRIPTION_COL + " VARCHAR(100) ) ";
    }

    /**
     * added by Faisal Mohammad
     * create Registration Assign Program Srv table
     * <p>
     * remarks-
     */
    public static String sqlCreateRegNAssignPrgSrvTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "("
                + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(10), "
                + LAY_R1_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R2_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R3_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R4_LIST_CODE_COL + " VARCHAR(10), "
                + ADM_DONOR_CODE_COL + " VARCHAR(10), "
                + ADM_AWARD_CODE_COL + " VARCHAR(10), "
                + HHID_COL + " VARCHAR(10), "
                + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " VARCHAR(10), "
                + PROG_CODE_COL + " VARCHAR(10), "
                + SRV_CODE_COL + " VARCHAR(10), "
                + REG_N_DAT_COL + " VARCHAR(50)  , "
                + GRD_CODE_COL + " VARCHAR(10)  , "
                + GRD_DATE_COL + " VARCHAR(50)  , "
                + REG_N_STATUS_COL + " VARCHAR(20) , "
                + GRD_STATUS_COL + " VARCHAR(20)  , "
                + SRV_MIN_DATE_COL + " VARCHAR(50)  , "
                + SRV_MAX_DATE_COL + " VARCHAR(50)  , "
                + ENTRY_BY + " VARCHAR(5) , "
                + ENTRY_DATE + " VARCHAR(20) " +
//                " , "+
                // + SYNC_COL + " DEFAULT 0 " +
                " ) ";


    }


    /**
     * added by Faisal Mohammad
     * create Service Master table
     * AdmServiceMaster_Schema schema
     * remarks-
     */
    public static String sqlCreateServiceMasterTable() {

        return CREATE_TABLE_IF_NOT_EXISTS + SERVICE_MASTER_TABLE + "("
                + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + ADM_PROG_CODE_COL + " VARCHAR(20), "
                + ADM_SRV_CODE_COL + " VARCHAR(20), "
                + SERVICE_MASTER_SERVICE_NAME_COL + " VARCHAR(100), "
                + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " VARCHAR(50)  ) ";

    }

    /**
     * added by Faisal Mohammad
     * create Program Master table
     * AdmProgramMaster_Schema schema
     */
    public static String sqlCreateProgramMasterTable() {

        return CREATE_TABLE_IF_NOT_EXISTS + ADM_PROGRAM_MASTER_TABLE + "("

                + ADM_DONOR_CODE_COL + " VARCHAR(2), "
                + ADM_AWARD_CODE_COL + " VARCHAR(2), "
                + ADM_PROG_CODE_COL + " VARCHAR(5), "
                + ADM_PROGRAM_MASTER_PROGRAM_NAME_COL + " VARCHAR(100), "
                + PROGRAM_SHORT_NAME_COL + " VARCHAR(5), "
                + MULTIPLE_SERVICE_FLAG_COL + " VARCHAR(2) "
                + " , " + PRIMARY_KEY + " ( " + ADM_PROG_CODE_COL + ", " + ADM_AWARD_CODE_COL + ", " + ADM_DONOR_CODE_COL + " ) "
                + " ) ";
    }

    /**
     * AdmDonorSchema schema
     * remarks - ok
     */
    public static String sqlCreateDonorTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.ADM_DONOR_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + ADM_DONOR_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.DONOR_NAME_COL + " VARCHAR(100) ) ";
    }


    public static String sqlCreateRegistration() {
        return CREATE_TABLE_IF_NOT_EXISTS + REG_N_HH_TABLE + "("
//                + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(10), "
                + LAY_R1_LIST_CODE + " VARCHAR(10), "
                + LAY_R2_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R3_LIST_CODE_COL + " VARCHAR(10), "
                + LAY_R4_LIST_CODE_COL + " VARCHAR(10), "
                + REGISTRATION_TABLE_REGN_ADDRESS_LOOKUP_CODE_COL + " VARCHAR(3), "
                + HHID_COL + " VARCHAR(10), " // pid
                + REG_DATE_COL + " DATE, "
                + REGISTRATION_TABLE_HH_HEAD_NAME + " VARCHAR(50), "                                /// pname
                + REGISTRATION_TABLE_HH_HEAD_SEX + " VARCHAR(8), "
                + HH_SIZE + " VARCHAR(10), "
                + REGISTRATION_TABLE_GPS_LAT + " VARCHAR(20), "
                + REGISTRATION_TABLE_GPS_LONG + " VARCHAR(20), "
                + AG_LAND + " VARCHAR(20), "
                + V_STATUS + " VARCHAR(10), "
                + M_STATUS + " VARCHAR(10), "
                + ENTRY_BY + " VARCHAR(20), "
                + ENTRY_DATE + " DATE, "
                + VSLA_GROUP + " VARCHAR(10), "
                + GPS_LONG_SWAP + " VARCHAR(100), "
                + REGISTRATION_TABLE_HOUSE_HOLD_TYPE_CODE_COL + " VARCHAR(10), "
                + LT2YRS_M_COL + " VARCHAR(10), "
                + LT2YRS_F_COL + " VARCHAR(10), "
                + M_2TO5YRS_COL + " VARCHAR(10), "
                + F_2TO5YRS_COL + " VARCHAR(10), "
                + M_6TO12YRS_COL + " VARCHAR(10), "
                + F_6TO12YRS_COL + " VARCHAR(10), "
                + M_13TO17YRS_COL + " VARCHAR(10), "
                + F_13TO17YRS_COL + " VARCHAR(10), "
                + ORPHN_LT18YRS_M_COL + " VARCHAR(10), "
                + ORPHN_LT18YRS_F_COL + " VARCHAR(10), "
                + ADLT_18TO59_M_COL + " VARCHAR(10), "
                + ADLT_18TO59_F_COL + " VARCHAR(10), "
                + ELD_60P_M_COL + " VARCHAR(10), "
                + ELD_60P_F_COL + " VARCHAR(10), "
                + PLW_NO_COL + " VARCHAR(10), "
                + CHRO_ILL_NO_COL + " VARCHAR(10), "
                + DECEASED_CONTRACT_EBOLA_COL + " VARCHAR(10), "
                + EXTRA_CHILD_CAUSE_EBOLA_COL + " VARCHAR(10), "
                + EXTRA_ELDERLY_CAUSE_EBOLA_COL + " VARCHAR(10), "
                + EXTRA_CHRONICALLY_ILL_CAUSE_EBOLA_COL + " VARCHAR(10), "
                + BRF_COUNT_CATTLE_COL + " VARCHAR(10), "
                + BRF_VALUE_CATTLE_COL + " VARCHAR(10), "
                + AFT_COUNT_CATTLE_COL + " VARCHAR(10), "
                + AFT_VALUE_CATTLE_COL + " VARCHAR(10), "
                + BRF_COUNT_SGOATS_COL + " VARCHAR(10), "
                + BRF_VALUE_SGOATS_COL + " VARCHAR(10), "
                + AFT_COUNT_SGOATS_COL + " VARCHAR(10), "
                + AFT_VALUE_SGOATS_COL + " VARCHAR(10), "
                + BRF_COUNT_POULTRY_COL + " VARCHAR(10), "
                + BRF_VALUE_POULTRY_COL + " VARCHAR(10), "
                + AFT_COUNT_POULTRY_COL + " VARCHAR(10), "
                + AFT_VALUE_POULTRY_COL + " VARCHAR(10), "
                + BRF_COUNT_OTHER_COL + " VARCHAR(10), "
                + BRF_VALUE_OTHER_COL + " VARCHAR(10), "
                + AFT_COUNT_OTHER_COL + " VARCHAR(10), "
                + AFT_VALUE_OTHER_COL + " VARCHAR(10), "
                + BRF_ACRE_CULTIVABLE_COL + " VARCHAR(10), "
                + BRF_VALUE_CULTIVABLE_COL + " VARCHAR(10), "
                + AFT_ACRE_CULTIVABLE_COL + " VARCHAR(10), "
                + AFT_VALUE_CULTIVABLE_COL + " VARCHAR(10), "
                + BRF_ACRE_NON_CULTIVABLE_COL + " VARCHAR(10), "
                + BRF_VAL_NON_CULTIVABLE_COL + " VARCHAR(10), "
                + AFT_ACRE_NON_CULTIVABLE + " VARCHAR(10), "
                + AFT_VAL_NON_CULTIVABLE + " VARCHAR(10), "
                + BRF_ACRE_ORCHARDS + " VARCHAR(10), "
                + BRF_VAL_ORCHARDS + " VARCHAR(10), "
                + AFT_ACRE_ORCHARDS + " VARCHAR(10), "
                + AFT_VAL_ORCHARDS + " VARCHAR(10), "
                + BRF_VAL_CROP + " VARCHAR(10), "
                + AFT_VAL_CROP + " VARCHAR(10), "
                + BRF_VAL_LIVESTOCK + " VARCHAR(10), "
                + AFT_VAL_LIVESTOCK + " VARCHAR(10), "
                + BRF_VAL_SMALL_BUSINESS + " VARCHAR(10), "
                + AFT_VAL_SMALL_BUSINESS + " VARCHAR(10), "
                + BRF_VAL_EMPLOYMENT + " VARCHAR(10), "
                + AFT_VAL_EMPLOYMENT + " VARCHAR(10), "
                + BRF_VAL_REMITTANCES + " VARCHAR(10), "
                + AFT_VAL_REMITTANCES + " VARCHAR(10), "
                + BRF_CNT_WAGEENR + " VARCHAR(10), "
                + AFT_CNT_WAGEENR + " VARCHAR(10), "
                + W_RANK_COL + " VARCHAR(10), "
                + LTP_2_HECTRES_COL + " VARCHAR(2) , "
                + LT_3_FOOD_STOCK_COL + " VARCHAR(2) , "
                + NO_MAJOR_COMMON_LIVE_STOCK_COL + " VARCHAR(2), "
                + RECEIVE_NO_FORMAL_WAGES_COL + " VARCHAR(2), "
                + NO_IGA_COL + " VARCHAR(2) , "
                + RELY_PICE_EORK_COL + " VARCHAR(2) "//+","

//                + SYNC_COL + " BOOLEAN DEFAULT 0" +
                +" )";
    }

    /**
     * Service Table Schema
     */

    public static String sqlCreateServiceTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.SERVICE_TABLE + "("
                //   + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(4), "
                + ADM_DONOR_CODE_COL + " VARCHAR(2), "
                + ADM_AWARD_CODE_COL + " VARCHAR(2), "
                + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R2_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R3_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R4_LIST_CODE_COL + " VARCHAR(2), "
                + HHID_COL + " VARCHAR(5), "
                + MEM_ID_COL + " VARCHAR(2), "
                + PROG_CODE_COL + " VARCHAR(3), "
                + SRV_CODE_COL + " VARCHAR(2), "
                + OPERATION_CODE_COL + " VARCHAR(1), "
                + OP_MONTH_CODE_COL + " VARCHAR(2), "
                + SERVICE_TABLE_SERVICE_SL_COL + " VARCHAR(2)  , "
                + SERVICE_CENTER_CODE_COL + " VARCHAR(3) , "
                + SERVICE_TABLE_SERVICE_DT_COL + " VARCHAR(20) DEFAULT '00' , "
                + SRV_STATUS_COL + " VARCHAR(2)  , "
                + DISTRIBUTION_STATUS_COL + " VARCHAR(1)  , "
                + DIST_DT_COL + " VARCHAR(20) DEFAULT '00' , "
                + FDP_CODE_COL + " VARCHAR(4) , "
                + WORK_DAY_COL + " VARCHAR(6) , "
                + DIST_FLAG_COL + " VARCHAR(100) , "

                /**  this column may not be necessary in future delete id no use now */
//                + GROUP_CODE_COL + " VARCHAR(4)  , "
                // for total synch summary report need entry by & entry date
                + ENTRY_BY + " VARCHAR(4) DEFAULT '00' , "
                + ENTRY_DATE + " VARCHAR(20) DEFAULT '00' " +
//                ", " + SYNC_COL + "  BOOLEAN DEFAULT 0 " +
                ") "
                ;// delete last comma ,

    }


    // Creating RegNHHMember Schema
    public static String sqlCreateRegRelation() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LUP_REG_NHH_RELATION_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + SQLiteHandler.RELATION_CODE + " VARCHAR(10), "
                + SQLiteHandler.RELATION_NAME + " VARCHAR(100)"
                + " )";
    }

    // Creating LUP_SrvOptionList Schema
    public static String sqlCreateLUP_SrvOptionList() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LUP_SRV_OPTION_LIST_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(5), "
                + PROG_CODE_COL + " VARCHAR(4), "
                + SRV_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.LUP_OPTION_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.LUP_OPTION_NAME_COL + " VARCHAR(4) "
                + " )";
    }


    // create Vul table schema
    public static String sqlCreateRegNVUL_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REG_N_VUL_TABLE + " ("

                + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "

                + ADM_COUNTRY_CODE_COL + " VARCHAR(4), "
                + LAY_R1_LIST_CODE + " VARCHAR(2), "
                + LAY_R2_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R3_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R4_LIST_CODE_COL + " VARCHAR(2), "
                + HHID_COL + " VARCHAR(5), "
                + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " VARCHAR(20), "
                + REG_N_DAT_COL + " VARCHAR(20), "
                + SQLiteHandler.Disabled_YN_COL + " VARCHAR(1), "
                + ENTRY_BY + " VARCHAR(50) DEFAULT '0', "
                + ENTRY_DATE + " VARCHAR(50) DEFAULT '0' "
                + " )";

    }

    // create Vul table schema
    public static String sqlCreateVoucherItem_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.VOUCHER_ITEM_TABLE + " ("

                + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "

                + SQLiteHandler.VOUCHER_ITEM_CATEGORY_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.ITEM_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.ITEM_NAME_COL + " VARCHAR(100) "

                + " )";

    }

    public static String sqlCreateVoucherItemMeas_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE + " ("

                + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + SQLiteHandler.MEAS_R_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.UNITE_MEAS_COL + " VARCHAR(5), "
                + SQLiteHandler.MEASE_TITLE_COL + " VARCHAR(100) "

                + " )";

    }

    public static String sqlCreateVoucherCountryProgItem_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + " ("

                + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "

                + ADM_COUNTRY_CODE_COL + " VARCHAR(4), "
                + ADM_DONOR_CODE_COL + " VARCHAR(5), "
                + ADM_AWARD_CODE_COL + " VARCHAR(5), "
                + ADM_PROG_CODE_COL + " VARCHAR(5), "
                + ADM_SRV_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.VOUCHER_ITEM_CATEGORY_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.ITEM_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.MEAS_R_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " VARCHAR(15), "
                + SQLiteHandler.UNITE_COST_COL + " VARCHAR(25), "
                + SQLiteHandler.ACTIVE_STATUS_COL + " VARCHAR(5), "
                + SQLiteHandler.CURRENCY_COL + " VARCHAR(5), "
                + SYNC_COL + " VARCHAR(5) DEFAULT '0' "
                + " )";

    }


    public static String sqlCreateServiceExtended_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.SERVICE_EXTENDED_TABLE + " ("

                + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "

                + ADM_COUNTRY_CODE_COL + " VARCHAR(4), "
                + ADM_DONOR_CODE_COL + " VARCHAR(2), "
                + ADM_AWARD_CODE_COL + " VARCHAR(2), "
                + LAY_R1_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R2_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R3_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R4_LIST_CODE_COL + " VARCHAR(2), "
                + HHID_COL + " VARCHAR(5), "
                + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " VARCHAR(2), "
                + PROG_CODE_COL + " VARCHAR(3), "
                + SRV_CODE_COL + " VARCHAR(2), "
                + OPERATION_CODE_COL + " VARCHAR(1), "
                + OP_MONTH_CODE_COL + " VARCHAR(2), "

                + VOUCHER_ITEM_SPEC_COL + " VARCHAR(15), "
                + VOUCHER_UNIT_COL + " VARCHAR(25), "
                + VOUCHER_REFERENCE_NUMBER_COL + " VARCHAR(18), "
                + VOUCHER_ITEM_COST_COL + " VARCHAR(5), "
                + DIST_FLAG_COL + " VARCHAR(100), "
                + ENTRY_BY + " VARCHAR(5), "
                + ENTRY_DATE + " VARCHAR(20), "
                + SYNC_COL + " VARCHAR(5) DEFAULT '0' "
                + " )";


    }


    public static String sqlCreateDistributionExtended_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + " ("

                // + SQLiteHandler.ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "

                + ADM_COUNTRY_CODE_COL + " VARCHAR(4), "
                + ADM_DONOR_CODE_COL + " VARCHAR(2), "
                + ADM_AWARD_CODE_COL + " VARCHAR(2), "
                + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R2_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R3_LIST_CODE_COL + " VARCHAR(2), "
                + LAY_R4_LIST_CODE_COL + " VARCHAR(2), "
                + PROG_CODE_COL + " VARCHAR(3), "
                + SRV_CODE_COL + " VARCHAR(2), "
                + OP_MONTH_CODE_COL + " VARCHAR(2), "
                + FDP_CODE_COL + " VARCHAR(10), "
                + MEM_ID_15_D_COL + " VARCHAR(25), "
                + VOUCHER_ITEM_SPEC_COL + " VARCHAR(15), "
                + VOUCHER_UNIT_COL + " VARCHAR(25), "
                + VOUCHER_REFERENCE_NUMBER_COL + " VARCHAR(18), "

                + SRV_OP_MONTH_CODE_COL + " VARCHAR(2), "
                + DIST_FLAG_COL + " VARCHAR(100), "

                + ENTRY_BY + " VARCHAR(5), "
                + ENTRY_DATE + " VARCHAR(20), "
                + SYNC_COL + " VARCHAR(5) DEFAULT '0' "
                + " )";


    }


    public static String sqlCreateSelectedVillage_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SELECTED_VILLAGE_TABLE + " ("

                + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "

                + COUNTRY_CODE_COL + "  VARCHAR(4), "
                + DISTRICT_CODE_COL + " VARCHAR(2), "
                + UPAZILLA_CODE_COL + " VARCHAR(2), "
                + UNIT_CODE_COL + " VARCHAR(2), "
                + VILLAGE_CODE_COL + " VARCHAR(2), "
                + GEO_LAY_R_CODE_COL + " VARCHAR(2), "
                + VILLAGE_NAME_COL + " VARCHAR(100), "
                + REGN_ADDRESS_LOOKUP_CODE_COL + " VARCHAR(4) "

                + " )";


    }


    public static String sqlCreateSelectedFDP_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SELECTED_FDP_TABLE + " ("


                + ADM_COUNTRY_CODE_COL + " VARCHAR(4), "
                + FDP_CODE_COL + " VARCHAR(4), "
                + FDP_NAME_COL + " VARCHAR(100) "
                + " )";


    }

    public static String sqlCreateSelectedServiceCenter_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.SELECTED_SERVICE_CENTER_TABLE + " ("
                + ADM_COUNTRY_CODE_COL + " VARCHAR(4), "
                + SERVICE_CENTER_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.SERVICE_CENTER_NAME_COL + " VARCHAR(100) "
                + " )";


    }


    public static String sqlCreateCommunityGroup_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + COMMUNITY_GROUP_TABLE + " ("

                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_DONOR_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_AWARD_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_PROG_CODE_COL + " VARCHAR(4) "
                + " , " + GROUP_CODE_COL + " VARCHAR(4) "
                + " , " + LAY_R1_CODE_COL + " VARCHAR(4) DEFAULT '00' "
                + " , " + GRP_LAY_R2_LIST_CODE_COL + " VARCHAR(4) DEFAULT '00' "
                + " , " + GRP_LAY_R3_LIST_CODE_COL + " VARCHAR(4) DEFAULT '00' "
                + " , " + GROUP_NAME_COL + " VARCHAR(100) "
                + " , " + GROUP_CAT_CODE_COL + " VARCHAR(4) "
                + " , " + SERVICE_CENTER_CODE_COL + " VARCHAR(4) "
                + " , " + ENTRY_BY + " VARCHAR(4) "
                + " , " + ENTRY_DATE + " VARCHAR(25) "
/**
 * get Exception
 * android.database.sqlite.SQLiteConstraintException: UNIQUE constraint failed:
 * when use conjunctive primary keys
 */
                //  + " ,  " + PRIMARY_KEY + "(" + SQLiteHandler.ADM_COUNTRY_CODE_COL + "," + SQLiteHandler.ADM_DONOR_CODE_COL + "," + SQLiteHandler.ADM_AWARD_CODE_COL + "," + SQLiteHandler.ADM_PROG_CODE_COL + "," + SQLiteHandler.GROUP_CODE_COL + "," + SQLiteHandler.LAY_R_LIST_CODE_COL + "," + SQLiteHandler.LAY_R2_LIST_CODE_COL + " , " + SQLiteHandler.LAY_R3_LIST_CODE_COL + ")   "
                + " )";


    }


    public static String createTableCommunityGrpDetail() {


        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.COMMUNITY_GRP_DETAIL_TABLE
                + " ( "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_DONOR_CODE_COL + " VARCHAR(4)"
                + " , " + ADM_AWARD_CODE_COL + " VARCHAR(4)"
                + " , " + ADM_PROG_CODE_COL + " VARCHAR(4) "
                + " , " + GROUP_CODE_COL + " VARCHAR(4) "
                + " , " + LAY_R1_CODE_COL + " VARCHAR(4) DEFAULT '-' "
                + " , " + GRP_LAY_R2_LIST_CODE_COL + " VARCHAR(4) DEFAULT '-' "
                + " , " + GRP_LAY_R3_LIST_CODE_COL + " VARCHAR(4) DEFAULT '-' "
                + " , " + SQLiteHandler.ORG_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.STAFF_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LAND_SIZE_UNDER_IRRIGATION_COL + " VARCHAR(20) "
                + " , " + SQLiteHandler.IRRIGATION_SYSTEM_USED_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.FUND_SUPPORT_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.ACTIVE_STATUS_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.REP_NAME_COL + " VARCHAR(100) "
                + " , " + SQLiteHandler.REP_PHONE_NUMBER_COL + " VARCHAR(100) "
                + " , " + SQLiteHandler.FORMATION_DATE_COL + " VARCHAR(100) "
                + " , " + SQLiteHandler.TYPE_OF_GROUP + " VARCHAR(4) "
                + " , " + SQLiteHandler.STATUS + " VARCHAR(100) "
                + " , " + SQLiteHandler.PROJECT_NO_COL + " VARCHAR(100) "
                + " , " + SQLiteHandler.PROJECT_TITLE + " VARCHAR(200) "
                + " , " + ENTRY_BY + " VARCHAR(4) "
                + " , " + ENTRY_DATE + " VARCHAR(50) "

/**
 * get Exception
 * android.database.sqlite.SQLiteConstraintException: UNIQUE constraint failed:
 * when use conjunctive primary keys
 */

//                + " ,  " + PRIMARY_KEY + "(" + SQLiteHandler.ADM_COUNTRY_CODE_COL + "," + SQLiteHandler.ADM_DONOR_CODE_COL + "," + SQLiteHandler.ADM_AWARD_CODE_COL + "," + SQLiteHandler.ADM_PROG_CODE_COL + "," + SQLiteHandler.GROUP_CODE_COL + "," + SQLiteHandler.LAY_R_LIST_CODE_COL + "," + SQLiteHandler.LAY_R2_LIST_CODE_COL + " , " + SQLiteHandler.LAY_R3_LIST_CODE_COL + ")   "
                + " ) ";
    }


    public static String sqlCreateGPSSubGroupAttributes_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.GPS_SUB_GROUP_ATTRIBUTES_TABLE + " ("


                + GROUP_CODE_COL + " VARCHAR(4) "
                + " , " + SUB_GROUP_CODE_COL + " VARCHAR(4) "
                + " , " + ATTRIBUTE_CODE_COL + " VARCHAR(4) "
                + " , " + ATTRIBUTE_TITLE_COL + " VARCHAR(4) "
                + " , " + DATA_TYPE_COL + " VARCHAR(4) "
                + " , " + GPS_SUB_GROUP_ATTRIBUTES_TABLE_LOOK_UP_CODE_COL + " VARCHAR(4) "
                + " )";


    }

    public static String sqlCreateLUP_GPS_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LUP_GPS_TABLE + " ("


                + GROUP_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.SUB_GROUP_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.ATTRIBUTE_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LUP_GPS_TABLE_LOOK_UP_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.GPS_SUB_GROUP_ATTRIBUTES_TABLE_LOOK_UP_NAME_COL + " VARCHAR(4) "
                + " )";


    }

    public static String sqlCreateGPSLocationAttributes_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.GPS_LOCATION_ATTRIBUTES_TABLE + " ("


                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + GROUP_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.SUB_GROUP_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LOCATION_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.ATTRIBUTE_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.ATTRIBUTE_VALUE_COL + " VARCHAR(100) "
                + " , " + SQLiteHandler.ATTRIBUTE_PHOTO_COL + " TEXT "
                + " , " + ENTRY_BY + " VARCHAR(4) "
                + " , " + ENTRY_DATE + " VARCHAR(20) "
                + " )";


    }

    // Creating LUP_SrvOptionList Schema
    public static String sqlCreateLUP_GpsList() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LUP_GPS_LIST_TABLE + "("

                + GROUP_CODE_COL + " VARCHAR(5)  , "
                + SQLiteHandler.SUB_GROUP_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.ATTRIBUTE_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.LUP_VALUE_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.LUP_VALUE_TEXT_COL + " VARCHAR(100) "
                + " )";
    }


    public static String sqlCreateServiceSpecification_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.SERVICE_SPECIFIC_TABLE + " ("


                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_DONOR_CODE_COL + " VARCHAR(2) "
                + " , " + ADM_AWARD_CODE_COL + " VARCHAR(2) "
                + " , " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + LAY_R2_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + LAY_R3_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + LAY_R4_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + HHID_COL + " VARCHAR(5) "
                + " , " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " VARCHAR(2) "
                + " , " + PROG_CODE_COL + " VARCHAR(3) "
                + " , " + SRV_CODE_COL + " VARCHAR(2) "
                + " , " + OPERATION_CODE_COL + " VARCHAR(2) "
                + " , " + OP_MONTH_CODE_COL + " VARCHAR(2) "
                + " , " + SERVICE_CENTER_CODE_COL + " VARCHAR(5) "
                + " , " + FDP_CODE_COL + " VARCHAR(5) "
                + " , " + SRV_STATUS_COL + " VARCHAR(4) "

                + " , " + SQLiteHandler.BABY_STATUS_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.GMP_ATTENDACE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.WEIGHT_STATUS_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.NUT_ATTENDANCE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.VITA_UNDER5_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.EXCLUSIVE_CURRENTLYBF_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.DATE_COMPFEEDING_COL + " VARCHAR(20) "
                + " , " + SQLiteHandler.CMAMREF_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.CMAMADD_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.ANCVISIT_COL + " VARCHAR(20) "
                + " , " + SQLiteHandler.PNCVISIT_2D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.PNCVISIT_1W_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.PNCVISIT_6W_COL + " VARCHAR(2) "

                + " , " + SQLiteHandler.DELIVERY_STAFF_1_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.DELIVERY_STAFF_2_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.DELIVERY_STAFF_3_COL + " VARCHAR(2) "

                + " , " + SQLiteHandler.HOME_SUPPORT24H_1D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT24H_2D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT24H_3D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT24H_8D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT24H_14D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT24H_21D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT24H_30D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT24H_60D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT24H_90D_COL + " VARCHAR(2) "

                + " , " + SQLiteHandler.HOME_SUPPORT48H_1D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT48H_3D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT48H_8D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT48H_30D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT48H_60D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT48H_90D_COL + " VARCHAR(2) "

                + " , " + SQLiteHandler.MATERNAL_BLEEDING_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.MATERNAL_SEIZURE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.MATERNAL_INFECTION_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.MATERNAL_PROLONGEDLABOR_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.MATERNAL_OBSTRUCTEDLABOR_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.MATERNAL_PPRM_COL + " VARCHAR(2) "


                + " , " + SQLiteHandler.NBORN_ASPHYXIA_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.NBORN_SEPSIS_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.NBORN_HYPOTHERMIA_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.NBORN_HYPERTHERMIA_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.NBORN_NOSUCKLING_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.NBORN_JAUNDICE_COL + " VARCHAR(2) "

                + " , " + SQLiteHandler.CHILD_DIARRHEA_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.CHILD_PNEUMONIA_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.CHILD_FEVER_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.CHILD_CEREBRALPALSY_COL + " VARCHAR(2) "


                + " , " + SQLiteHandler.IMMU_POLIO_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.IMMU_BCG_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.IMMU_MEASLES_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.IMMU_DPT_HIB_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.IMMU_LOTTA_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.IMMU_OTHER_COL + " VARCHAR(2) "

                + " , " + SQLiteHandler.FPCOUNSEL_MALECONDOM_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.FPCOUNSEL_FEMALECONDOM_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.FPCOUNSEL_PILL_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.FPCOUNSEL_DEPO_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.FPCOUNSEL_LONGPARMANENT_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.FPCOUNSEL_NOMETHOD_COL + " VARCHAR(2) "

                + " , " + SQLiteHandler.CROP_CODE_COL + " VARCHAR(3) "
                + " , " + SQLiteHandler.LOAN_SOURCE_COL + " VARCHAR(3) "
                + " , " + SQLiteHandler.LOAN_AMT_COL + " VARCHAR(3) "
                + " , " + SQLiteHandler.ANIMAL_CODE_COL + " VARCHAR(3) "
                + " , " + SQLiteHandler.LEAD_CODE_COL + " VARCHAR(3) "


                + " , " + ENTRY_BY + " VARCHAR(4) "
                + " , " + ENTRY_DATE + " VARCHAR(20) "
                + " )";


    }


    public static String sqlCreateLUP_CommunityAnimalList_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LUP_COMMUNITY_ANIMAL_TABLE + " ("


                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_DONOR_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_AWARD_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_PROG_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.ANIMAL_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.ANIMAL_TYPE_COL + " VARCHAR(100) "

                + " )";


    }


    public static String sqlCreateLUP_ProgramGroupCrop_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LUP_PROG_GROUP_CROP_TABLE + " ("


                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_DONOR_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_AWARD_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_PROG_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.CROP_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.CROP_NAME_COL + " VARCHAR(100) "
                + " , " + SQLiteHandler.CROP_CAT_COL + " VARCHAR(10) "

                + " )";


    }

    public static String sqlCreateLUP_CommunityLoanSource_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LUP_COMMUNITY_LOAN_SOURCE_TABLE + " ("


                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_DONOR_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_AWARD_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_PROG_CODE_COL + " VARCHAR(4) "
                + " , " + LOAN_CODE_COL + " VARCHAR(4) "
                + " , " + LOAN_SOURCE_COL + " VARCHAR(100) "


                + " )";


    }


    public static String sqlCreateLUP_CommunityLeadPosition_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + LUP_COMMUNITY_LEAD_POSITION_TABLE + " ("


                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_DONOR_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_AWARD_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_PROG_CODE_COL + " VARCHAR(4) "
                + " , " + LEAD_CODE_COL + " VARCHAR(4) "
                + " , " + LEAD_POSITION_COL + " VARCHAR(100) "


                + " )";
    }

    public static String sqlCreateRegNmemProgGrp_Table() {


        return CREATE_TABLE_IF_NOT_EXISTS + REG_N_MEM_PROG_GRP_TABLE + " ("


                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + LAY_R1_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + LAY_R2_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + LAY_R3_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + LAY_R4_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + ADM_DONOR_CODE_COL + " VARCHAR(2) "
                + " , " + ADM_AWARD_CODE_COL + " VARCHAR(2) "
                + " , " + HHID_COL + " VARCHAR(6) "
                + " , " + MEM_ID_COL + " VARCHAR(2) "
                + " , " + PROG_CODE_COL + " VARCHAR(3) "
                + " , " + SRV_CODE_COL + " VARCHAR(2) "
                + " , " + GROUP_CODE_COL + " VARCHAR(5) "
                + " , " + GROUP_NAME_COL + " VARCHAR(100) "
                + " , " + ACTIVE_COL + " VARCHAR(5) "
                + " , " + ENTRY_BY + " VARCHAR(4) "
                + " , " + ENTRY_DATE + " VARCHAR(20) "
                + " , " + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R1_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R2_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R3_LIST_CODE_COL + " VARCHAR(2) "

                + " )";
    }


    public static String sqlCreateCommunityGroupCategoryes_Table() {


        return CREATE_TABLE_IF_NOT_EXISTS + COMMUNITY_GROUP_CATEGORY_TABLE + " ("

                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_DONOR_CODE_COL + " VARCHAR(2) "
                + " , " + ADM_AWARD_CODE_COL + " VARCHAR(2) "
                + " , " + ADM_PROG_CODE_COL + " VARCHAR(3) "
                + " , " + GROUP_CAT_CODE_COL + " VARCHAR(4) "
                + " , " + GROUP_CAT_NAME_COL + " VARCHAR(100) "
                + " , " + GROUP_CAT_SHORT_NAME_COL + " VARCHAR(5) "


                + " )";
    }


    public static String sqlCreate_Gps_Location_Content_Table() {
        return CREATE_TABLE_IF_NOT_EXISTS + GPS_LOCATION_CONTENT_TABLE + " ("
                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + GROUP_CODE_COL + " VARCHAR(5)"
                + " , " + SUB_GROUP_CODE_COL + " VARCHAR(5)"
                + " , " + LOCATION_CODE_COL + " VARCHAR(5)"
                + " , " + CONTENT_CODE_COL + " VARCHAR(5)"
                + " , " + IMAGE_FILE_COL + " BLOB "
                + " , " + REMARKES_COL + " VARCHAR(10)"
                + " , " + ENTRY_BY + " VARCHAR(4) "
                + " , " + ENTRY_DATE + " VARCHAR(20) "
                + " ) ";
    }

    public static String createTableLastSyncTime() {
        return CREATE_TABLE_IF_NOT_EXISTS + LAST_SYNC_TYRACE_TABLE
                + " ( "
                + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + USER_ID + " TEXT , "
                + USER_LOGIN_NAME + " TEXT ,"
                + LAST_SYNC_TIME_COL + " TEXT "
                + " ) ";
    }


    public static String sqlCreateDistNPlanBasic() {
        return CREATE_TABLE_IF_NOT_EXISTS + DIST_N_PLAN_BASIC_TABLE + "("

                + ADM_COUNTRY_CODE_COL + " VARCHAR(10), "
                + ADM_DONOR_CODE_COL + " VARCHAR(10), "
                + ADM_AWARD_CODE_COL + " VARCHAR(10), "
                + PROG_CODE_COL + " VARCHAR(10), "
                + OPERATION_CODE_COL + " VARCHAR(10), "
                + SRV_OP_MONTH_CODE_COL + " VARCHAR(10), "
                + DIST_OP_MONTH_CODE_COL + " VARCHAR(10), "
                + FDP_CODE_COL + " VARCHAR(10), "
                + DIST_FLAG_COL + " VARCHAR(10), "
                + ORG_CODE_COL + " VARCHAR(10), "
                + DISTRIBUTOR_COL + " VARCHAR(10), "
                + DISTRIBUTION_DATE_COL + " VARCHAR(50)  , "
                + DELIVERY_DATE_COL + " VARCHAR(50)  , "
                + STATUS + " VARCHAR(50)  , "
                + PREPARED_BY_COL + " VARCHAR(20) , "
                + VERIFIED_BY_COL + " VARCHAR(20) , "
                + APPROVED_BY_COL + " VARCHAR(20)  "


                + " ) ";


    }

    public static String createTableLUP_RegNAddLookup() {
        return CREATE_TABLE_IF_NOT_EXISTS + LUP_REGN_ADDRESS_LOOKUP_TABLE
                + " ( "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + REGN_ADDRESS_LOOKUP_CODE_COL + " VARCHAR(3)"
                + " , " + REGN_ADDRESS_LOOKUP_NAME_COL + " VARCHAR(100)"
                + " , " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " VARCHAR(4) "
                + " , " + LAY_R2_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + LAY_R3_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + LAY_R4_LIST_CODE_COL + " VARCHAR(2) "
                + " ) ";
    }


    public static String createTableProgOrgNRole() {
        return CREATE_TABLE_IF_NOT_EXISTS + PROGRAM_ORGANIZATION_ROLE_TABLE
                + " ( "
                + ADM_COUNTRY_CODE_COL + " varchar (4) "
                + " , " + ADM_DONOR_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_AWARD_CODE_COL + " VARCHAR(4) "
                + " , " + ORG_N_CODE_COL + " VARCHAR(3) "
                + " , " + PRIME_Y_N_COL + " VARCHAR (1) "
                + " , " + SUB_Y_N_COL + " VARCHAR (1) "
                + " , " + TECH_Y_N_COL + " VARCHAR (1) "
                + " , " + LONG_Y_N_COL + " VARCHAR (1) "
                + " , " + OTH_Y_N_COL + " VARCHAR (1) "
                + " , " + IMP_Y_N_COL + " VARCHAR (1) "

                + "  ,  " + PRIMARY_KEY + " (" + ADM_COUNTRY_CODE_COL + "," + ADM_DONOR_CODE_COL + "," + ADM_AWARD_CODE_COL + "," + ORG_N_CODE_COL + ")   "

                + " ) ";
    }

    public static String createTableProgOrgN() {
        return CREATE_TABLE_IF_NOT_EXISTS + PROGRAM_ORGANIZATION_NAME_TABLE + " ( "
                + PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL + " VARCHAR(3), "
                + ORGANIZATION_NAME + " VARCHAR(100) "
                + " , " + ORGANIZATION_SHORT_NAME + " VARCHAR (25) "
                //+ " , " + PRIMARY_KEY + "(" +  ORG_CODE_COL + ") "
                + " ) ";
    }

    /**
     * SQLite tools from mustafiz
     */
    public static String createTableDTATable() {
        return CREATE_TABLE_IF_NOT_EXISTS + "  " + DT_A_TABLE + "  (   " +
                "   " + DT_BASIC_COL + "  TEXT NOT NULL,   " +
                "   " + DTQ_CODE_COL + "  TEXT NOT NULL,   " +
                "   " + DTA_CODE_COL + "  TEXT NOT NULL,   " +
                "   " + DTA_LABEL_COL + "  TEXT,   " +
                "   " + DTA_VALUE_COL + "  TEXT,   " +
                "   " + DT_SEQ_COL + "  INTEGER,   " +
                "   " + DTA_SHORT_COL + "  TEXT,   " +
                "   " + DT_SCORE_CODE_COL + "  TEXT,   " +
                "   " + DTSKIP_DTQ_CODE_COL + "    TEXT,   " +
                "   " + DTA_COMPARE_CODE_COL + "    TEXT,   " +
                "   " + SHOW_HIDE_COL + "    TEXT,   " +
                "   " + MAX_VALUE_COL + "    TEXT,   " +
                "   " + MIN_VALUE_COL + "    TEXT,   " +
                "   " + DATA_TYPE_COL + "    TEXT,   " +
                "   " + MARK_ON_GRID_COL + "    TEXT,   " +
                "   " + ENTRY_BY + "    TEXT,   " +
                "   " + ENTRY_DATE + "    TEXT" +
                //  ",   " +
                //  "  " + PRIMARY_KEY + "(" +  DT_BASIC_COL + "," +  DTQ_CODE_COL + "," +  DTA_CODE_COL + ")   " +
                ")";
    }

    public static String createTableDTBasic() {
        return CREATE_TABLE_IF_NOT_EXISTS + "   " + DT_BASIC_TABLE + "  (   " +
                "   " + DT_BASIC_COL + "   TEXT NOT NULL,   " +
                "   " + DT_TITLE_COL + "   TEXT,   " +
                "   " + DT_SUB_TITLE_COL + "   TEXT,   " +
                "   " + DT_DESCRIPTION_COL + "   TEXT,   " +
                "   " + DT_AUTO_SCROLL_COL + "   TEXT,   " +
                "   " + DTAUTO_SCROLL_TEXT + "   TEXT,   " +
                "   " + DT_ACTIVE_COL + "   TEXT,   " +
                "   " + DT_CATEGORY_COL + "   TEXT,   " +
                "   " + DT_GEO_LIST_LEVEL_COL + "   TEXT,   " +
                "   " + DT_OP_MODE_COL + "   TEXT,   " +
                "   " + DT_SHORT_NAME_COL + "   TEXT" +
                // ",   " + "   " +  ENTRY_BY + "    TEXT,   " +
                //"  " +  ENTRY_DATE + "    TEXT" +
                //  ",   " +
                // "  " + PRIMARY_KEY + "(" +  DT_BASIC_COL + ")   " +
                ")";
    }

    public static String createTableDTCategory() {
        return CREATE_TABLE_IF_NOT_EXISTS + "   " + DT_CATEGORY_TABLE + " " + "   (    " +
                "    " + DT_CATEGORY_COL + "    TEXT NOT NULL,    " +
                "    " + DT_CATEGORY_TABLE_CATEGORY_NAME_COL + "    TEXT,    " +
                "    " + FREQUENCY_COL + "    TEXT,    " +
                "   " + ENTRY_BY + "    TEXT,    " +
                "   " + ENTRY_DATE + "    TEXT" +
                //   ",    " +
                //   "  " + PRIMARY_KEY + "(" +  DT_CATEGORY_COL + ")    " +
                ")";
    }

    public static String createTableDTCountryProgram() {
        return CREATE_TABLE_IF_NOT_EXISTS + "   " + DT_COUNTRY_PROGRAM_TABLE + "   (  " +
                "    " + ADM_COUNTRY_CODE_COL + "    TEXT NOT NULL,  " +
                "    " + ADM_DONOR_CODE_COL + "    TEXT NOT NULL,  " +
                "    " + ADM_AWARD_CODE_COL + "    TEXT NOT NULL,  " +
                "    " + ADM_PROG_CODE_COL + "    TEXT NOT NULL,  " +
                "    " + PROG_ACTIVITY_CODE_COL + "    TEXT NOT NULL,  " +
                "    " + PROG_ACTIVITY_TITLE_COL + "    TEXT,  " +
                "    " + DT_BASIC_COL + "    TEXT NOT NULL,  " +
                "    " + REF_IDENTIFIER_COL + "    TEXT,  " +
                "    " + STATUS + "    TEXT,  " +
                "    " + RPT_FREQUENCY_COL + "    TEXT,  " +
                "   " + ENTRY_BY + "    TEXT,  " +
                "   " + ENTRY_DATE + "    TEXT" +
                //  " ,  " +                "  " + PRIMARY_KEY + "(" +  ADM_COUNTRY_CODE_COL + ","
                // +  ADM_DONOR_CODE_COL + " ," +  ADM_AWARD_CODE_COL + " ," +  ADM_PROG_CODE_COL + " ," +  PROG_ACTIVITY_CODE_COL + ")  " +
                ")";
    }

    public static String createTableDTGeoListLevel() {
        return CREATE_TABLE_IF_NOT_EXISTS + "   " + DTGEO_LIST_LEVEL_TABLE + " " + "    (   " +
                "      " + GEO_LEVEL_COL + "      TEXT NOT NULL,   " +
                "      " + GEO_LEVEL_NAME_COL + "      TEXT,   " +
                "      " + LIST_UDF_NAME_COL + "      TEXT,   " +
                "     " + ENTRY_BY + "      TEXT,   " +
                "     " + ENTRY_DATE + "      TEXT,   " +
                "   " + PRIMARY_KEY + "( " + GEO_LEVEL_COL + ")   " +
                ")";
    }

    public static String createTableDTQResMode() {
        return CREATE_TABLE_IF_NOT_EXISTS + "   " + DTQRES_MODE_TABLE + "   (   " +
                "      " + QRES_MODE_COL + "      TEXT NOT NULL,   " +
                "      " + QRES_LUP_TEXT_COL + "      TEXT,   " +
                "      " + DATA_TYPE_COL + "      TEXT,   " +
                "      " + LOOK_UP_UDF_NAME_COL + "      TEXT,   " +
                "      " + RESPONSE_VALUE_CONTROL_COL + "      TEXT" +
                // ",   " + "   " + PRIMARY_KEY + "(" +  QRES_MODE_COL + ")   " +
                ")";
    }

    public static String createTableDTQTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + "   " + DTQ_TABLE + "    (   " +
                "      " + DT_BASIC_COL + "      TEXT NOT NULL,   " +
                "      " + DTQ_CODE_COL + "      TEXT NOT NULL,   " +
                "      " + QTEXT_COL + "      TEXT,   " +
                "      " + QRES_MODE_COL + "      TEXT,   " +
                "      " + QSEQ_SCOL + "      INTEGER,   " +
                "      " + ALLOW_NULL_COL + "      TEXT,   " +
                "      " + LUP_TABLE_NAME + "      TEXT" +
                //",   " +
                //    "   " + PRIMARY_KEY + "(" +  DT_BASIC_COL + " ," +  DTQ_CODE_COL + " )   " +
                ")";
    }

    public static String createTableDTResponseTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + "   " + DT_RESPONSE_TABLE + "    (   " +
                "      " + DT_BASIC_COL + "      TEXT NOT NULL" +
                " ,   " + "      " + ADM_COUNTRY_CODE_COL + "      TEXT NOT NULL" +
                " ,   " + "      " + ADM_DONOR_CODE_COL + "      TEXT NOT NULL" +
                " ,   " + "      " + ADM_AWARD_CODE_COL + "      TEXT NOT NULL" +
                " ,   " + "      " + ADM_PROG_CODE_COL + "      TEXT NOT NULL" +
                " ,   " + "      " + DT_ENU_ID_COL + "      TEXT NOT NULL" +
                " ,   " + "      " + DTQ_CODE_COL + "      TEXT NOT NULL" +
                " ,   " + "     " + DTA_CODE_COL + "       TEXT NOT NULL" +
                " ,   " + "     " + DT_R_SEQ_COL + "      INTEGER NOT NULL" +
                " ,   " + "      " + DTA_VALUE_COL + "      BLOB" +
                " ,   " + "      " + PROG_ACTIVITY_CODE_COL + "      TEXT" +
                " ,   " + "      " + DTTIME_STRING_COL + "      TEXT" +
                " ,   " + "      " + OP_MODE_COL + "      TEXT" +
                " ,   " + "     " + OP_MONTH_CODE_COL + "       TEXT" +
                " ,   " + "     " + DATA_TYPE_COL + "       TEXT" +
                " ,   " + "     " + U_FILE_COL + "       BLOB   " +
                " ,   " + "     " + COMPLETENESS_COL + "       VARCHAR(1)   " +
                //  "  , " + PRIMARY_KEY + "(" +  DT_BASIC_COL + "," +  ADM_COUNTRY_CODE_COL + ", " +  ADM_DONOR_CODE_COL + " ," +  ADM_AWARD_CODE_COL + " ," +  ADM_PROG_CODE_COL
                //+ "," +  DT_ENU_ID_COL + "," +  DTQ_CODE_COL + "," +  DTA_CODE_COL + " ," +  DT_R_SEQ_COL + ")   " +
                ")";
    }

    public static String createTableDTSurveyTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + "   " + DT_SURVEY_TABLE + "    (   " +
                "      " + DT_BASIC_COL + "      TEXT NOT NULL,   " +
                "      " + COUNTRY_CODE_COL + "      TEXT NOT NULL,   " +
                "      " + DONOR_CODE_COL + "      TEXT NOT NULL,   " +
                "      " + AWARD_CODE_COL + "      TEXT NOT NULL,   " +
                "      " + PROGRAM_CODE_COL + "      TEXT NOT NULL,   " +
                "      " + DT_ENU_ID_COL + "      TEXT NOT NULL,   " +
                "      " + DTQ_CODE_COL + "      TEXT NOT NULL,   " +
                "     " + DTA_CODE_COL + "       TEXT NOT NULL,   " +
                "     " + DT_R_SEQ_COL + "      INTEGER NOT NULL,   " +
                "      " + DTA_VALUE_COL + "      BLOB,   " +
                "      " + PROG_ACTIVITY_CODE_COL + "      TEXT,   " +
                "      " + DTTIME_STRING_COL + "      TEXT,   " +
                "      " + OP_MODE_COL + "      TEXT,   " +
                "     " + OP_MONTH_CODE_COL + "       TEXT,   " +
                "     " + DATA_TYPE_COL + "       TEXT,   " +
                "     " + DTQ_TEXT_COL + "       TEXT,   " +
                "     " + DT_SURVEY_NUM + "       INTEGER NOT NULL   " +
                "     , " + U_FILE_COL + "       BLOB   " +
                "     , " + RESPONSE_VALUE_CONTROL_COL + "       TEXT   " +
                "     , " + QRES_LUP_TEXT_COL + "       TEXT   " +
                "     , " + DTA_LABEL_COL + "       TEXT   " +
                "     , " + COMPLETENESS_COL + "       TEXT   " +
                "     , " + ID_COL + "       INTEGER   " +

                "  , " + PRIMARY_KEY + "(" + DT_BASIC_COL + "," + COUNTRY_CODE_COL + ", " + DONOR_CODE_COL + " ," + AWARD_CODE_COL + " ," + PROGRAM_CODE_COL
                + "," + DT_ENU_ID_COL + "," + DTQ_CODE_COL + "," + DTA_CODE_COL + " ," + DT_R_SEQ_COL + ")   " +
                ")";
    }

    public static String createDTEnuTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + "   " + DT_ENU_TABLE + "    (   " +
                "      " + DT_STF_CODE_COL + "      TEXT NOT NULL ,   " +
                "      " + DT_ADM_COUNTRY_CODE_COL + "      TEXT ,   " +
                "      " + DT_BASIC_COL + "      TEXT ,   " +
                "      " + DT_BTN_SAVE_COL + "      TEXT ,   " +
                "      " + DT_ENTRY_BY_COL + "      TEXT ,   " +
                "     " + DT_USA_ENTRY_DATE_COL + "       TEXT   " +
                //  "  , " + PRIMARY_KEY + "(" +  DT_BASIC_COL + "," +  ADM_COUNTRY_CODE_COL + ", " +  ADM_DONOR_CODE_COL + " ," +  ADM_AWARD_CODE_COL + " ," +  ADM_PROG_CODE_COL
                //+ "," +  DT_ENU_ID_COL + "," +  DTQ_CODE_COL + "," +  DTA_CODE_COL + " ," +  DT_R_SEQ_COL + ")   " +
                ")";
    }

    public static String createTableDTTableDefinition() {
        return CREATE_TABLE_IF_NOT_EXISTS + "   " + DT_TABLE_DEFINITION_TABLE + "    (   " +
                "      " + TABLE_NAME_COL + "      TEXT NOT NULL,   " +
                "      " + FIELD_NAME_COL + "      TEXT NOT NULL,   " +
                "      " + FIELD_DEFINITION_COL + "      TEXT,   " +
                "      " + FIELD_SHORT_NAME_COL + "      TEXT,   " +
                "      " + VALUE_UDF_COL + "      TEXT,   " +
                "      " + LUPTABLE_NAME_COL + "      TEXT,   " +
                "      " + ADMIN_ONLY_COL + "      TEXT,   " +
                "      " + ENTRY_BY + "      TEXT,   " +
                "      " + ENTRY_DATE + "      TEXT" +
                " ,   " + "   " + PRIMARY_KEY + "(" + TABLE_NAME_COL + "," + FIELD_NAME_COL + ")   " +
                ")";
    }

    public static String createTaleDTTableListCategory() {
        return CREATE_TABLE_IF_NOT_EXISTS + "   " + DTTABLE_LIST_CATEGORY_TABLE + "    (   " +
                "      " + TABLE_NAME_COL + "      TEXT NOT NULL,   " +
                "      " + TABLE_GROUP_CODE_COL + "      TEXT,   " +
                "      " + USE_ADMIN_ONLY_COL + "      TEXT,   " +
                "      " + USE_REPORT_COL + "      TEXT,   " +
                "      " + USE_TRANSACTION_COL + "      TEXT,   " +
                "      " + USE_LUP_COL + "      TEXT" +
                "      " + USE_REGISTRATION_COL + "      TEXT" +
                "      " + USE_SERVICE_COL + "      TEXT" +
                "      " + USE_DISTRIBUTION_COL + "      TEXT" +
                "      " + USE_INVENTORY_COL + "      TEXT" +
                "      " + USE_GIS_COL + "      TEXT" +
                "      " + TD_SP_NAME_COL + "      TEXT" +
                "      " + T_CONTENTS_COL + "      TEXT" +
                ",   " + "   " + PRIMARY_KEY + "(" + TABLE_NAME_COL + ")   " +
                ")";
    }

    public static String createTaleDT_LUP_Table() {
        return CREATE_TABLE_IF_NOT_EXISTS + "   " + DT_LUP_TABLE + "    (   " +
                "      " + ADM_COUNTRY_CODE_COL + "      TEXT NOT NULL,   " +
                "      " + TABLE_NAME_COL + "      TEXT NOT NULL,   " +
                "      " + LIST_CODE_COL + "      TEXT NOT NULL,   " +
                "      " + LIST_NAME_COL + "      TEXT" +
                " , " + "   " + PRIMARY_KEY + " (" + ADM_COUNTRY_CODE_COL + "," + TABLE_NAME_COL + "," + LIST_CODE_COL + ")   " +
                ")";
    }

    /**
     * This temporary Table only use for Service Mode  And Service Center
     *
     * @return table Schema
     */

    public static String sqlCreateTemporary_CountryProgram() {
        return CREATE_TABLE_IF_NOT_EXISTS + TEMPORARY_COUNTRY_PROGRAM_TABLE + " ( "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(5)"
                + " , " + ADM_DONOR_CODE_COL + " VARCHAR(5)"
                + " , " + ADM_AWARD_CODE_COL + " VARCHAR(5)"
                + " , " + ADM_PROG_CODE_COL + " VARCHAR(5)"
                + " , " + PROGRAM_NAME_COL + " VARCHAR(100) "
                + " , " + PROGRAM_SHORT_NAME_COL + " VARCHAR(5) "
                + " , " + IS_SELECTED_FLAG_COL + " VARCHAR(1) DEFAULT '0' "

                + " , " + PRIMARY_KEY + " (" + ADM_COUNTRY_CODE_COL + ", " + ADM_DONOR_CODE_COL + ", " + ADM_AWARD_CODE_COL + ", " + ADM_PROG_CODE_COL + " )"
                + " )";
    }


    public static String sqlCreateTemporary_OpMonthTable() {

        return CREATE_TABLE_IF_NOT_EXISTS + TEMPORARY_OP_MONTH_TABLE + " ( "

                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_DONOR_CODE_COL + " VARCHAR(2) "
                + " , " + ADM_AWARD_CODE_COL + " VARCHAR(2) "
                + " , " + OPERATION_CODE_COL + " VARCHAR(20) "
                + " , " + OP_MONTH_CODE_COL + " VARCHAR(2) "
                + " , " + MONTH_LABEL_COL + " VARCHAR(50) "
                + " , " + USA_START_DATE_COL + " VARCHAR(20) "
                + " , " + USA_END_DATE_COL + " VARCHAR(20) "
                + " , " + STATUS + " VARCHAR(20) "
                + " , " + IS_SELECTED_FLAG_COL + " VARCHAR(1) DEFAULT '0' "
                + " , " + PRIMARY_KEY + " (" + ADM_COUNTRY_CODE_COL + ", " + ADM_DONOR_CODE_COL + ", " + ADM_AWARD_CODE_COL + ", " + OPERATION_CODE_COL + ", " + OP_MONTH_CODE_COL + " ) "
                + " ) ";
    }


    public static String sqlCreateSelectedCountry() {
        return CREATE_TABLE_IF_NOT_EXISTS + SELECTED_COUNTRY_TABLE +
                "(" + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT, "
                + COUNTRY_CODE + " VARCHAR(5), " + COUNTRY_COUNTRY_NAME + " VARCHAR(50))";
    }

    public static String sqlCreateOperationModeTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SELECTED_OPERATION_MODE_TABLE +
                "(" + ID_COL + " INTEGER " + PRIMARY_KEY + " AUTOINCREMENT" +
                " , " + SELECTED_OPERATION_MODE_CODE_COL + " INTEGER " +
                ", " + SELECTED_OPERATION_MODE_NAME_COL + " VARCHAR(50)" +
                ", " + ENTRY_BY + " VARCHAR(50)" +
                " , " + ENTRY_DATE + " VARCHAR(30)" +
                ")";
    }


    public static String createTableRegN_FFA() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REG_N_FFA_TABLE
                + " ( "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " VARCHAR(4) "
                + " , " + LAY_R2_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + LAY_R3_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + LAY_R4_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + HHID_COL + " VARCHAR(5) "
                + " , " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " VARCHAR(2) "
                + " , " + ORPHAN_CHILDREN_COL + " VARCHAR(1) "
                + " , " + CHILD_HEADED_COL + " VARCHAR(1) "
                + " , " + ELDERLY_HEADED_COL + " VARCHAR(1) "
                + " , " + CHRONICALLY_ILL_COL + " VARCHAR(1) "
                + " , " + FEMALE_HEADED_COL + " VARCHAR(1) "
                + " , " + CROP_FAILURE_COL + " VARCHAR(1) "
                + "  , " + CHILDREN_REC_SUPP_FEED_N_COL + " VARCHAR(1) "
                + "  , " + WILLINGNESS_COL + " VARCHAR(1) "
                + " , " + ENTRY_BY + " VARCHAR(4) "
                + " , " + ENTRY_DATE + " VARCHAR(20) "
                + " ) ";
    }

    public static String createTableRegN_WE() {
        return CREATE_TABLE_IF_NOT_EXISTS + REG_N_WE_TABLE
                + " ( "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " VARCHAR(4) "
                + " , " + LAY_R2_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + LAY_R3_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + LAY_R4_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + HHID_COL + " VARCHAR(5) "
                + " , " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " VARCHAR(2) "
                + " , " + REG_DATE_COL + " VARCHAR(20) "
                + " , " + WEALTH_RANKING_COL + " VARCHAR(1) "
                + " , " + MEMBER_EXT_GROUP_COL + " VARCHAR(1) "
                + " , " + ENTRY_BY + " VARCHAR(4) "
                + " , " + ENTRY_DATE + " VARCHAR(20) "
                + " ) ";


    }

    public static String createTableDTASkipTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + DTA_SKIP_TABLE
                + " ( "
                + DT_BASIC_COL + " VARCHAR(8) "
                + " , " + DTQ_CODE_COL + " VARCHAR(10) "
                + " , " + SKIP_CODE_COL + " VARCHAR(10) "
                + " , " + DTA_CODE_COMB_N_COL + " TEXT "
                + " , " + DTSKIP_DTQ_CODE_COL + " VARCHAR(10) "
                + " , " + PRIMARY_KEY + " (" + DT_BASIC_COL + ", " + DTQ_CODE_COL + ", " + SKIP_CODE_COL + " ) "
                + " ) ";

    }


    public static String createTAMasterTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + TA_MASTER_TABLE
                + " ( "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + EVENT_CODE_COL + " VARCHAR(8) "
                + " , " + EVENT_NAME_COL + " VARCHAR(100) "
                + " , " + ADM_DONOR_CODE_COL + " VARCHAR(2) "
                + " , " + ADM_AWARD_CODE_COL + " VARCHAR(2) "
                + " , " + TA_GROUP_COL + " VARCHAR(3) "
                + " , " + TA_SUB_GROUP_COL + " VARCHAR(3) "
                + " , " + PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL + " VARCHAR(10) "
                + " , " + START_DATE_COL + " VARCHAR(10) "
                + " , " + END_DATE_COL + " VARCHAR(10) "
                + " , " + VENUE_NAME_COL + " VARCHAR(100) "
                + " , " + VENUE_ADDRESS_COL + " VARCHAR(200) "
                + " , " + ACTIVE_COL + " VARCHAR(10) "
                + " , " + TOTAL_DAYS_COL + " VARCHAR(10) "
                + " , " + HOURS_PER_DAY_COL + " VARCHAR(10) "
                + " , " + PRIMARY_KEY + " (" + ADM_COUNTRY_CODE_COL + ", " + EVENT_CODE_COL + " ) "
                + " ) ";
    }


    public static String createTACategoryTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + TA_CATEGORY_TABLE
                + " ( "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + TA_CAT_CODE_COL + " VARCHAR(3) "
                + " , " + TA_CAT_NAME_COL + " VARCHAR(50) "
                + " , " + SRC_BEN_COL + " VARCHAR(3) "
                + " , " + PRIMARY_KEY + " (" + ADM_COUNTRY_CODE_COL + ", " + TA_CAT_CODE_COL + " ) "
                + " ) ";
    }

    public static String createTAEventTopicTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + TA_EVENT_TOPIC_TABLE
                + " ( "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + EVENT_CODE_COL + " VARCHAR(3) "
                + " , " + TOPIC_MASTER_CODE_COL + " VARCHAR(50) "
                + " , " + TOPIC_CHILD_CODE_COL + " VARCHAR(3) "
                + " , " + PRIMARY_KEY + " (" + ADM_COUNTRY_CODE_COL + ", " + EVENT_CODE_COL + ", " + TOPIC_MASTER_CODE_COL + ", " + TOPIC_CHILD_CODE_COL + " ) "
                + " ) ";
    }

    public static String createTAGroupTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + TA_GROUP_TABLE
                + " ( "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + TOPIC_GROUP_COL + " VARCHAR(3) "
                + " , " + TOPIC_GROUP_TITLE_COL + " VARCHAR(200) "
                + " , " + PRIMARY_KEY + " (" + ADM_COUNTRY_CODE_COL + ", " + TOPIC_GROUP_COL + " ) "
                + " ) ";
    }


    public static String createTAParticipantsTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + TA_PARTICIPANTS_LIST_TABLE
                + " ( "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + EVENT_CODE_COL + " VARCHAR(8) "
                + " , " + PART_ID_COL + " VARCHAR(50) "
                + " , " + ID_CATEGORY_COL + " VARCHAR(3) "
                + " , " + PART_NAME_COL + " VARCHAR(100) "
                + " , " + PART_ORG_N_CODE_COL + " VARCHAR(3) "
                + " , " + TA_PARTICIPANTS_LIST_TABLE_SEX_COL + " VARCHAR(1) "
                + " , " + PART_CAT_CODE_COL + " VARCHAR(3) "
                + " , " + POS_CODE_COL + " VARCHAR(3) "
                + " , " + AM_SESSION_COL + " VARCHAR(1) "
                + " , " + PM_SESSION_COL + " VARCHAR(1) "
                + " , " + ATDN_DATE_COL + " VARCHAR(30) "
                + " , " + TA_GROUP_COL + " VARCHAR(3) "
                + " , " + ENTRY_BY + " VARCHAR(4) "
                + " , " + ENTRY_DATE + " VARCHAR(20) "
                + " , " + PRIMARY_KEY + " (" + ADM_COUNTRY_CODE_COL + ", " + EVENT_CODE_COL + ", " + PART_ID_COL + ", " + ATDN_DATE_COL + " ) "
                + " ) ";
    }


    public static String createTAPartOrgNTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + TA_PART_ORG_N_TABLE
                + " ( "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + PART_ORG_N_CODE_COL + " VARCHAR(3) "
                + " , " + PART_ORG_N_NAME_COL + " VARCHAR(200) "
                + " , " + SRC_BEN_COL + " VARCHAR(1) "
                + " , " + PRIMARY_KEY + " (" + ADM_COUNTRY_CODE_COL + ", " + PART_ORG_N_CODE_COL + " ) "
                + " ) ";
    }

    public static String createTAPosParticipantsTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + TA_POS_PARTICIPANTS_TABLE
                + " ( "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + POS_CODE_COL + " VARCHAR(3) "
                + " , " + POS_TITLE_COL + " VARCHAR(100) "

                + " , " + PRIMARY_KEY + " (" + ADM_COUNTRY_CODE_COL + ", " + POS_CODE_COL + " ) "
                + " ) ";
    }


    public static String createTASubGroupTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + TA_SUB_GROUP_TABLE
                + " ( "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + TA_GROUP_COL + " VARCHAR(3) "
                + " , " + TA_SUB_GROUP_COL + " VARCHAR(3) "
                + " , " + TA_SUB_TITLE_COL + " VARCHAR(200) "
                + " , " + PRIMARY_KEY + " (" + ADM_COUNTRY_CODE_COL + ", " + TA_GROUP_COL + ", " + TA_SUB_GROUP_COL + " ) "
                + " ) ";
    }

    public static String createTATopicChildTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + TA_TOPIC_CHILD_TABLE
                + " ( "
                + TOPIC_MASTER_CODE_COL + " VARCHAR(4) "
                + " , " + TOPIC_CHILD_CODE_COL + " VARCHAR(3) "
                + " , " + TOPIC_SUB_TITLE_COL + " VARCHAR(200) "

                + " , " + PRIMARY_KEY + " (" + TOPIC_MASTER_CODE_COL + ", " + TOPIC_CHILD_CODE_COL + " ) "
                + " ) ";
    }

    public static String createTATopicMasterTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + TA_TOPIC_MASTER_TABLE
                + " ( "
                + TOPIC_MASTER_CODE_COL + " VARCHAR(4) "
                + " , " + TOPIC_TITLE_COL + " VARCHAR(200) "

                + " , " + PRIMARY_KEY + " (" + TOPIC_MASTER_CODE_COL + " ) "
                + " ) ";
    }


    public static String crateLUP_TAParticipantCat() {
        return CREATE_TABLE_IF_NOT_EXISTS + LUP_TA_PATICIPANT_CAT_TABLE
                + " ( "
                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + TA_GROUP_COL + " VARCHAR(3) "
                + " , " + PART_CAT_CODE_COL + " VARCHAR(3) "
                + " , " + PART_CAT_TITLE_COL + " VARCHAR(3) "
                + " , " + PRIMARY_KEY + " (" + ADM_COUNTRY_CODE_COL + " , " + TA_GROUP_COL + " , " + PART_CAT_CODE_COL + " ) "
                + " ) ";
    }


    public static String sqlCreateLUP_CommunityFundSource_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + LUP_COMMUNITY_FUND_SOURCE_TABLE + " ("


                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_DONOR_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_AWARD_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_PROG_CODE_COL + " VARCHAR(4) "
                + " , " + FUND_CODE_COL + " VARCHAR(4) "
                + " , " + FUND_SOURCE_COL + " VARCHAR(100) "
                + " )";


    }

    public static String sqlCreateLUP_CommunityIrrigationSystem_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + LUP_COMMUNITY_IRRIGATION_SYSTEM_TABLE + " ("


                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_DONOR_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_AWARD_CODE_COL + " VARCHAR(4) "
                + " , " + ADM_PROG_CODE_COL + " VARCHAR(4) "
                + " , " + IRRI_SYS_CODE_COL + " VARCHAR(3) "
                + " , " + IRRI_SYS_NAME_COL + " VARCHAR(100) "
                + " )";


    }


    public static String sqlCreateAdmMachineRegistry_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + ADM_MACHINE_REGISTRY_TABLE + " ("


                + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + DEVICE_TYPE_ID_COL + " VARCHAR(4) "
                + " , " + M_CODE_COL + " VARCHAR(50) "
                + " , " + M_LABLE_COL + " VARCHAR(50) "
                + " , " + M_ID_COL + " VARCHAR(50) "
                + " , " + DEVICE_ROLE_ID_COL + " VARCHAR(50) "
                + " , " + OFF_MODE_COL + " VARCHAR(50) "
                + " , " + ON_MODE_COL + " VARCHAR(50) "
                + " , " + STATUS + " VARCHAR(50) "
                + " , " + OPERATION_MODE_COL + " VARCHAR(50) "
                + " , " + ENTRY_BY + " VARCHAR(4) "
                + " , " + ENTRY_DATE + " VARCHAR(50) "

                + " , " + PRIMARY_KEY + " (" + M_CODE_COL + " , " + DEVICE_ROLE_ID_COL + " ) "
                + " )";


    }


    public static String sqlCreateAdmMachinePublisher_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + ADM_MACHINE_PUBLISHER_TABLE + " ("


                + PUBLISHER_ID_COL + " VARCHAR(50) "
                + " , " + PUBLISHER_NAME_COL + " VARCHAR(50) "
                + " , " + M_CODE_COL + " VARCHAR(50) "
                + " , " + SUBSCRIBER_M_CODE_COL + " VARCHAR(50) "
                + " , " + ENTRY_BY + " VARCHAR(50) "
                + " , " + ENTRY_DATE + " VARCHAR(50) "

                + " , " + PRIMARY_KEY + " (" + PUBLISHER_ID_COL + " ) "
                + " )";


    }


}
