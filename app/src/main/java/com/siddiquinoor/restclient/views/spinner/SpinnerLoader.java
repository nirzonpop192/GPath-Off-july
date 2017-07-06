package com.siddiquinoor.restclient.views.spinner;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.MemberSearchPage;
import com.siddiquinoor.restclient.activity.sub_activity.gps_sub.MapActivity;
import com.siddiquinoor.restclient.data_model.adapters.AssignDataModel;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.data_model.adapters.DTQTableDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;

import java.util.ArrayList;
import java.util.List;

import static com.siddiquinoor.restclient.manager.SQLiteHandler.ADM_AWARD_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.ADM_COUNTRY_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.ADM_DONOR_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.GROUP_CAT_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.GROUP_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.ADM_PROG_CODE_COL;

/**
 * Created by pop
 *
 * @since 1/11/2017.
 * This class is use for Load values in Spinner .
 * if  data exist in data base get the value set to the spinner value
 */

public class SpinnerLoader {
    private static String TAG = SpinnerLoader.class.getSimpleName();

    /***
     * @param context      refer to the activity which will invoke this method.
     * @param sqlH         database reference
     * @param spGroupCat   spinner view
     * @param cCode        country code
     * @param donorCode    donor code
     * @param awardCode    award code
     * @param progCode     program code
     * @param groupCatCode Group categories code
     * @param strGroupCat  Group categories name(values)
     *                     <p>
     *                     This methods load the Group Categories Code and Name to the Spinner From data base
     *                     following to the Country Code , Donor Code , Award Code , Program Code.
     *                     If data exits for certain member , Assume that member 'xyz' has group categories code 03
     *                     and it value I mean group categories name 'Producer Group Categories ' the spinner will be selected the
     *                     'Producer Group' and it's value otherwise the default value would be select .</p>
     */

    public static void loadGroupCatLoader(Context context, SQLiteHandler sqlH, Spinner spGroupCat, String cCode, String donorCode, String awardCode, String progCode, String groupCatCode, String strGroupCat) {
        int position = 0;
        String criteria = " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + ADM_PROG_CODE_COL + " = '" + progCode + "' "
                + " GROUP BY  " + GROUP_CAT_CODE_COL;


        List<com.siddiquinoor.restclient.views.helper.SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.COMMUNITY_GROUP_CATEGORY_TABLE, criteria, null, false);
        ArrayAdapter<com.siddiquinoor.restclient.views.helper.SpinnerHelper> dataAdapter = new ArrayAdapter<com.siddiquinoor.restclient.views.helper.SpinnerHelper>(context, R.layout.spinner_layout, listAward);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spGroupCat.setAdapter(dataAdapter);


        if (groupCatCode != null) {
            for (int i = 0; i < spGroupCat.getCount(); i++) {
                String groupCategory = spGroupCat.getItemAtPosition(i).toString();
                if (groupCategory.equals(strGroupCat)) {
                    position = i;
                }
            }
            spGroupCat.setSelection(position);
        }
    }

    /**
     * @param context  refer to the activity which will invoke this method.
     * @param spActive spinner view
     * @param idActive Active Code
     */
    public static void loadActiveStatusLoader(Context context, Spinner spActive, String idActive) {
        int pos = 0;

        ArrayAdapter<CharSequence> adptMartial = ArrayAdapter.createFromResource(context, R.array.arrActive, R.layout.spinner_layout);
        adptMartial.setDropDownViewResource(R.layout.spinner_layout);
        spActive.setAdapter(adptMartial);

        if (idActive != null) {
            if (idActive.equals("Y"))
                pos = 0;
            else
                pos = 1;
            spActive.setSelection(pos);
        }
    }

    /**
     * semi universal method
     *
     * @param context     refer to the activity which will invoke this method.
     * @param sqlH        database reference
     * @param spGroup     spinner view
     * @param cCode       country code
     * @param donorCode   donor code
     * @param awardCode   award code
     * @param progCode    program code
     * @param grpCateCode Group categories code
     * @param groupCode   group Code
     * @param strGroup    group name
     *                    <p>
     *                    This methods load the Group  Code and Name to the Spinner From data base
     *                    following to the Country Code , Donor Code , Award Code , Program Code & Group Categories Code .
     *                    If data exits for certain member , Assume that member 'xyz' has group  code 0003
     *                    and it value I mean group categories name 'Tamana Group, Balaka ' the spinner will be selected the
     *                    'Tamana Group Balaka' and it's value otherwise the default value would be select .</p>
     */
    public static void loadGroupLoader(Context context, SQLiteHandler sqlH, Spinner spGroup, String cCode, String donorCode, String awardCode, String progCode, String grpCateCode, String groupCode, String strGroup) {
        int position = 0;
        String criteria = SQLiteQuery.loadGroupLoader_sql(cCode, donorCode, awardCode, progCode, grpCateCode);


        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, null, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listAward);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spGroup.setAdapter(dataAdapter);


        if (groupCode != null) {
            for (int i = 0; i < spGroup.getCount(); i++) {
                String groupCategory = spGroup.getItemAtPosition(i).toString();
                if (groupCategory.equals(strGroup)) {
                    position = i;
                }
            }
            spGroup.setSelection(position);
        }
    }

    /**
     * universal method
     *
     * @param context   refer to the activity which will invoke this method.
     * @param sqlH      database reference
     * @param spAward   spinner view
     * @param cCode     country Code
     * @param awardCode award code
     * @param strAward  award Name
     *                  This method load the Award Name .
     */
    public static void loadAwardLoader(Context context, SQLiteHandler sqlH, Spinner spAward, String cCode, String awardCode, String strAward) {
        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.ADM_COUNTRY_AWARD_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + "='" + cCode + "'";

        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.ADM_COUNTRY_AWARD_TABLE, criteria, null, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listAward);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spAward.setAdapter(dataAdapter);


        if (awardCode != null) {
            for (int i = 0; i < spAward.getCount(); i++) {
                String award = spAward.getItemAtPosition(i).toString();
                if (award.equals(strAward)) {
                    position = i;
                }
            }
            spAward.setSelection(position);
        }

    }



    public static void loadServiceCenterLoader(Context context, SQLiteHandler sqlH, Spinner spServiceCenter, String idSrvCenter, String strServiceCenter) {
        int position = 0;


        int operationMode = UtilClass.getAppOperationMode((Activity) context);
        String criteria = SQLiteQuery.loadServiceCenter_sql(operationMode);


        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, null, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listAward);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spServiceCenter.setAdapter(dataAdapter);

        if (idSrvCenter != null) {
            for (int i = 0; i < spServiceCenter.getCount(); i++) {
                String serviceCenterName = spServiceCenter.getItemAtPosition(i).toString();

                if (serviceCenterName.equals(strServiceCenter)) {
                    position = i;
                }
            }
            spServiceCenter.setSelection(position);
        }

    }

    /**
     * todo: describe it
     *
     * @param context refer to the activity which will invoke this method.
     * @param sqlH    database reference
     * @param spOrg   spinner view
     * @param cCode   country code
     * @param idOrg   organization Code
     * @param strOrg  organization Code
     * @param query   load spinner query
     */
    public static void loadOrganizationLoader(Context context, SQLiteHandler sqlH, Spinner spOrg, String cCode, String idOrg, String strOrg, String query) {

        int position = 0;

        List<SpinnerHelper> listOrganization = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, query, cCode, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listOrganization);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spOrg.setAdapter(dataAdapter);

        if (idOrg != null) {

            for (int i = 0; i < spOrg.getCount(); i++) {
                String organization = spOrg.getItemAtPosition(i).toString();
                if (organization.equals(strOrg)) {
                    position = i;
                }
            }
            spOrg.setSelection(position);
        }
    }

    /***
     * the dynamic spinner loader in Dynamic Response
     *
     * @param context    refer to the activity which will invoke this method.
     * @param sqlH       database reference
     * @param dt_spinner spinner view
     * @param cCode      country code
     * @param resLupText response Look up text
     * @param strSpinner spinner T7ext
     * @param mDTQ       Dynamic Table Question
     */
    public static void loadDynamicSpinnerListLoader(Context context, SQLiteHandler sqlH, Spinner dt_spinner, String cCode, String resLupText, String strSpinner, DTQTableDataModel mDTQ, AssignDataModel.DynamicDataIndexDataModel dyBasic) {

        int position = 0;
        List<SpinnerHelper> list = new ArrayList<SpinnerHelper>();
        String udf = SQLiteQuery.loadDynamicSpinnerListLoader_sql(cCode, resLupText, mDTQ.getLup_TableName(), dyBasic);


        list.clear();
        list = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, udf, cCode, false);

        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, list);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        dt_spinner.setAdapter(dataAdapter);

        /**      Retrieving Code for previous button         */
        if (strSpinner != null) {
            for (int i = 0; i < dt_spinner.getCount(); i++) {
//                String union = dt_spinner.getItemAtPosition(i).toString();
                if (list.get(i).getId().equals(strSpinner)) {
                    position = i;
                }
            }
            dt_spinner.setSelection(position);
        }

    }


    /**
     * this method load dynamic survey  active month.
     *
     * @param context   refer to the activity which will invoke this method.
     * @param sqlH      database reference
     * @param spDtMonth spinner view
     * @param cCode     country code
     * @param opCode    operation code for dynamic table is 5
     * @param idMonth   op month code
     * @param strMonth  op month name
     */
    public static void loadDtMonthLoader(Context context, SQLiteHandler sqlH, Spinner spDtMonth, String cCode, String opCode, String idMonth, String strMonth) {
        int position = 0;
        String criteria = SQLiteQuery.loadDtMonth_sql(cCode, opCode, "");
        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, null, false);
        /**         *  remove select value         */
        listProgram.remove(0);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listProgram);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spDtMonth.setAdapter(dataAdapter);


        if (idMonth != null) {
            for (int i = 0; i < spDtMonth.getCount(); i++) {
                String monthName = spDtMonth.getItemAtPosition(i).toString();
                if (monthName.equals(strMonth)) {
                    position = i;
                }
            }
            spDtMonth.setSelection(position);

        }

    }

    public static void loadCountryLoader(Context context, SQLiteHandler sqlH, Spinner spCountry, int operationMode, String cCode, String strCountry) {

        int position = 0;
        String criteria = "";
        Log.d(TAG, "operation mode : " + operationMode);
        criteria = SQLiteQuery.loadCountry_sql(operationMode, sqlH.isMultipleCountryAccessUser());

        List<SpinnerHelper> listCountry = sqlH.getListAndID(SQLiteHandler.COUNTRY_TABLE, criteria, null, true);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listCountry);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spCountry.setAdapter(dataAdapter);


        if (cCode != null) {
            for (int i = 0; i < spCountry.getCount(); i++) {
                String district = spCountry.getItemAtPosition(i).toString();
                if (district.equals(strCountry)) {
                    position = i;
                }
            }
            spCountry.setSelection(position);
        }
    }

    /**
     * this spinner only use in Liberia registration page
     *
     * @param context   refer to the activity which will invoke this method.
     * @param sqlH      database reference
     * @param spHHType  spinner view
     * @param cCode     country code
     * @param idHHType  house hold categories  type in code
     * @param strHHType house hold categories  type values / name
     */
    public static void loadHouseHoldCategoryLoader(Context context, SQLiteHandler sqlH, Spinner spHHType, String cCode, String idHHType, String strHHType) {
        int position = 0;


        String criteria = " WHERE " + SQLiteHandler.LUP_REGNH_HEAD_CATEGORY_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + "='" + cCode + "' ";
        //GROUP BY "+sqlH.DISTRICT_TABLE+"."+sqlH.LAY_R_LIST_CODE_COL+", "+sqlH.DISTRICT_TABLE+"."+sqlH.DISTRICT_NAME_COL;

        List<SpinnerHelper> listHHCategory = sqlH.getListAndID(SQLiteHandler.LUP_REGNH_HEAD_CATEGORY_TABLE, criteria, cCode, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listHHCategory);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spHHType.setAdapter(dataAdapter);


        if (idHHType != null) {
            for (int i = 0; i < spHHType.getCount(); i++) {
                String district = spHHType.getItemAtPosition(i).toString();
                if (district.equals(strHHType)) {
                    position = i;
                }
            }
            spHHType.setSelection(position);
        }


    }

    // // TODO: 2/13/2017   implement it on SumRegLay4TotalHHRecords page
    public static void loadLayR4CodeForRegisterRecordViewLoader(Context context, SQLiteHandler sqlH, Spinner spVillage, String cCode) {


        String criteria = SQLiteQuery.loadLayR4CodeForRegisterRecordView_sql(cCode);


//        Log.d(TAG,"criteria: "+criteria);
        List<SpinnerHelper> listVillage = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, cCode, false);


        // Creating adapter for spinner
        final ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listVillage);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spVillage.setAdapter(dataAdapter);
        //dataAdapter.notifyDataSetChanged();
//        String criteria = "SELECT " + " v." + SQLiteHandler.ADM_COUNTRY_CODE_COL + " || '' ||  v." + LAY_R_LIST_CODE_COL + " || '' || v." + LAY_R2_LIST_CODE_COL + " || '' || v." +
//                LAY_R3_LIST_CODE_COL + " || '' || v." + LAY_R4_LIST_CODE_COL + " AS v_code," +
//                " v." + LAY_R4_LIST_NAME_COL + " AS Vill_Name " +
//                     /*   " COUNT("+PID_COL+") AS records"*/" FROM " + VILLAGE_TABLE + " AS v" +
//                " LEFT JOIN " + SQLiteHandler.REG_N_HH_TABLE + " AS r" +
//                " ON r." + SQLiteHandler.ADM_COUNTRY_CODE_COL + "= v." + SQLiteHandler.ADM_COUNTRY_CODE_COL
//                + " AND " +
//                "r." + SQLiteHandler.DISTRICT_NAME_COL + "= v." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
//                + " AND " +
//                "r." + SQLiteHandler.UPZILLA_NAME_COL + "= v." + LAY_R2_LIST_CODE_COL
//                + " AND " +
//                "r." + SQLiteHandler.LAY_R3_LIST_NAME + "= v." + LAY_R3_LIST_CODE_COL
//                + " AND " +
//                "r." + LAY_R4_LIST_NAME_COL + "= v." + LAY_R4_LIST_CODE_COL +
//                " Inner join " + SELECTED_VILLAGE_TABLE + " AS s"
//                + " on " + " s." + SQLiteHandler.ADM_COUNTRY_CODE_COL + "= v." + SQLiteHandler.ADM_COUNTRY_CODE_COL + " AND " +
//                "s." + LAY_R_LIST_CODE_COL + "= v." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " AND " +
//                "s." + LAY_R2_LIST_CODE_COL + "= v." + LAY_R2_LIST_CODE_COL + " AND " +
//                "s." + LAY_R3_LIST_CODE_COL + "= v." + LAY_R3_LIST_CODE_COL + " AND " +
//                "s." + LAY_R4_LIST_CODE_COL + "= v." + LAY_R4_LIST_CODE_COL +
//
//                " WHERE v." + SQLiteHandler.ADM_COUNTRY_CODE_COL + "='" + cCode + "'" + /** send the no of village for selected country added by Faisal Mohammad*/
//                "  GROUP BY v." + SQLiteHandler.ADM_COUNTRY_CODE_COL + ",v." + LAY_R_LIST_CODE_COL + ",v." + LAY_R2_LIST_CODE_COL + ",v." + LAY_R3_LIST_CODE_COL + ",v." + LAY_R4_LIST_CODE_COL;
//
//        List<SpinnerHelper> listVillage = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, cCode, false);
//
//
//        // Creating adapter for spinner
//        final ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listVillage);
//        // Drop down layout style
//        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
//        // attaching data adapter to spinner
//        spVillage.setAdapter(dataAdapter);
//        //dataAdapter.notifyDataSetChanged();
    }


    /**
     * load location Name And it's Code in spinner . and restore the previous state if data exits .
     * use in
     * {@link MapActivity#loadLocation(String)}
     * {@link com.siddiquinoor.restclient.activity.sub_activity.gps_sub.PointAttributes#loadLocation(String, String, String)}
     *
     * @param context     refer to the activity which will invoke this method.
     * @param sqlH        database reference.
     * @param spLocation  spinner view.
     * @param idLocation  location Code
     * @param strLocation location Name
     * @param criteria    sql
     */

    public static void loadLocationLoader(Context context, SQLiteHandler sqlH, Spinner spLocation, String idLocation, String strLocation, String criteria) {

        int position = 0;
        List<SpinnerHelper> listLocation = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, null, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listLocation);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spLocation.setAdapter(dataAdapter);

        if (idLocation != null) {
            for (int i = 0; i < spLocation.getCount(); i++) {
                String locationName = spLocation.getItemAtPosition(i).toString();
                if (locationName.equals(strLocation)) {
                    position = i;
                }
            }
            spLocation.setSelection(position);
        }
    }

    /**
     * load Gps Group Name and it's code
     *
     * @param context  refer to the activity which will invoke this method.
     * @param sqlH     database reference.
     * @param spGroup  spinner view.
     * @param idGroup  Group Code
     * @param strGroup Group Name
     */
    public static void loadGpsGroupLoader(Context context, SQLiteHandler sqlH, Spinner spGroup, String idGroup, String strGroup) {
        int position = 0;

        String criteria = "";

        List<SpinnerHelper> listGroup = sqlH.getListAndID(SQLiteHandler.GPS_GROUP_TABLE, criteria, null, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listGroup);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spGroup.setAdapter(dataAdapter);


        if (idGroup != null) {
            for (int i = 1; i < spGroup.getCount(); i++) {
                String group = spGroup.getItemAtPosition(i).toString();
                if (group.equals(strGroup)) {
                    position = i;
                }
            }
            spGroup.setSelection(position);
        }

    }

    public static void loadGpsSubGroupLoader(Context context, SQLiteHandler sqlH, Spinner spSubGroup, String idGroup, String idSubGroup, String strSubGroup) {
        int position = 0;
        String criteria = " WHERE " + GROUP_CODE_COL + "='" + idGroup + "'";
        List<SpinnerHelper> listSubGroup = sqlH.getListAndID(SQLiteHandler.GPS_SUB_GROUP_TABLE, criteria, null, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listSubGroup);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spSubGroup.setAdapter(dataAdapter);

        // Set the string to the position
        if (idSubGroup != null) {
            for (int i = 0; i < spSubGroup.getCount(); i++) {
                String subGroup = spSubGroup.getItemAtPosition(i).toString();
                if (subGroup.equals(strSubGroup)) {
                    position = i;
                }
            }
            spSubGroup.setSelection(position);
        }
    }

    /**
     * this method load value Criteria / service  in  spinner
     * this method use in      *
     * {@link com.siddiquinoor.restclient.activity.AssignActivity#loadCriteria(String, String, String, String)}
     *
     * @param context     refer to the activity which will invoke this method.
     * @param sqlH        database reference.
     * @param spCriteria  spinner view.
     * @param idCriteria  Criteria Code
     * @param strCriteria Criteria Name
     * @param sql_query   query
     */
    public static void loadCriteriaLoader(Context context, SQLiteHandler sqlH, Spinner spCriteria, String idCriteria, String strCriteria, String sql_query) {
        int position = 0;
        List<SpinnerHelper> listCriteria = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, sql_query, null, false);


        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listCriteria);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spCriteria.setAdapter(dataAdapter);


        if (idCriteria != null) {
            for (int i = 0; i < spCriteria.getCount(); i++) {
                String criteriaN = spCriteria.getItemAtPosition(i).toString();
                if (criteriaN.equals(strCriteria)) {
                    position = i;
                }
            }
            spCriteria.setSelection(position);
        }

    }

    /**
     * @param context           refer to the activity which will invoke this method.
     * @param sqlH              database reference
     * @param spCriteria        spinner view
     * @param cCode             country code
     * @param donorCode         donor code
     * @param awardCode         award code
     * @param foodFlagTypeQuery sqlite query An "and condition" will  dynamically added  to load criteria on spinner .Assume program MCHN has
     *                          four service PW,LM,CA2,CU2. CA2 & CU2 have Food type service but PW and LM
     *                          have Cash type service .if user select food type then pw and lm will not appeared
     * @param criteriaCode      criteria Code (programCode + service Code)
     * @param strCriteria       criteria Name (programShortName + serviceShortName )
     *                          <p>
     *                          This methods load the Criteria Code (Program Code+ Service Code ) and Name (Program Name + service Name ) to the Spinner From data base
     *                          following to the Country Code , Donor Code , Award Code , foodFlagTypeQuery .</p>
     */
    public static void loadServiceRecodeCriteriaLoader(Context context, SQLiteHandler sqlH, Spinner spCriteria, String cCode, String donorCode, String awardCode, String foodFlagTypeQuery, String criteriaCode, String strCriteria) {

        int position = 0;
        String criteria = SQLiteQuery.loadServiceRecodeCriteria(cCode, donorCode, awardCode, foodFlagTypeQuery);

        List<SpinnerHelper> listCriteria = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, null, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listCriteria);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spCriteria.setAdapter(dataAdapter);


        if (criteriaCode != null) {
            for (int i = 0; i < spCriteria.getCount(); i++) {
                String award = spCriteria.getItemAtPosition(i).toString();
                if (award.equals(strCriteria)) {
                    position = i;
                }
            }
            spCriteria.setSelection(position);
        }

    }

    /**
     * {@link MemberSearchPage#loadLayR4List()}
     * {@link com.siddiquinoor.restclient.activity.sub_activity.summary_sub.SummaryAssignCriteria#loadLayR4List(String)}
     *
     * @param context    refer to the activity which will invoke this method.
     * @param sqlH       database reference.
     * @param spVillage  spinner view.
     * @param idVillage  layR4Code Code
     * @param strVillage layR4Code Name
     * @param sql        sql query
     */
    public static void loadLayR4ListLoader(Context context, SQLiteHandler sqlH, Spinner spVillage, String idVillage, String strVillage, String sql) {
        int position = 0;

        List<SpinnerHelper> listVillage = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, sql, null, false);
        final ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listVillage);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spVillage.setAdapter(dataAdapter);

        if (idVillage != null) {
            for (int i = 0; i < spVillage.getCount(); i++) {
                String village = spVillage.getItemAtPosition(i).toString();
                if (village.equals(strVillage)) {
                    position = i;
                }
            }
            spVillage.setSelection(position);
        }

    }

    /**
     * this is dedicated layer 4 list loader . only used in Registration
     *
     * @param context    refer to the activity which will invoke this method.
     * @param sqlH       database reference.
     * @param spVillage  spinner view.
     * @param idVillage  layR4Code Code
     * @param strVillage layR4Code Name
     * @param cCode      country Code
     * @param idDist     layR1 code
     * @param idUP       layR2 code
     * @param idUnion    layR3 code
     */
    public static void loadLayR4ListLoader(Context context, SQLiteHandler sqlH, Spinner spVillage, String idVillage, String strVillage, String cCode, String idDist, String idUP, String idUnion) {
        int position = 0;

        // joining query for test purpose .
//        String criteria = SQLiteQuery.getVillageJoinQuery(cCode, idDist, idUP, idUnion);

        List<SpinnerHelper> listVillage = sqlH.getListAndID(SQLiteHandler.VILLAGE_TABLE, SQLiteQuery.getVillageJoinQuery(cCode, idDist, idUP, idUnion), cCode, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listVillage);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spVillage.setAdapter(dataAdapter);

        if (idVillage != null) {
            for (int i = 0; i < spVillage.getCount(); i++) {
                String village = spVillage.getItemAtPosition(i).toString();
                if (village.equals(strVillage)) {
                    position = i;
                }
            }
            spVillage.setSelection(position);
        }
    }

    /**
     * @param context  refer to the activity which will invoke this method.
     * @param sqlH     database reference.
     * @param spUnion  spinner view.
     * @param idUnion  layR4Code Code
     * @param strUnion layR4Code Name
     * @param cCode    country code
     * @param idDist   LayR1 code
     * @param idUP     LayR2 code
     */
    public static void loadLayR3ListLoader(Context context, SQLiteHandler sqlH, Spinner spUnion, String idUnion, String strUnion, String cCode, String idDist, String idUP) {
        int position = 0;


        String criteria = SQLiteQuery.getLayR3List_sql(cCode, idDist, idUP);
        List<SpinnerHelper> listUnion = sqlH.getListAndID(SQLiteHandler.GEO_LAY_R3_LIST_TABLE, criteria, cCode, false);

        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listUnion);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spUnion.setAdapter(dataAdapter);


        if (idUnion != null) {
            for (int i = 0; i < spUnion.getCount(); i++) {
                String union = spUnion.getItemAtPosition(i).toString();
                if (union.equals(strUnion)) {
                    position = i;
                }
            }
            spUnion.setSelection(position);
        }
    }



    public static void loadLayR3ListLoader(Context context, SQLiteHandler sqlH, Spinner spLayR3List, String idLayR3Code, String strLayR3Name, String cCode,String criteria , boolean disableActivation,boolean disableFlag) {
        int position = 0;
//        String criteria =SQLiteQuery.loadLayR3List_sql(cCode) ;


        List<SpinnerHelper> listLayR2Code = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, cCode, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listLayR2Code);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spLayR3List.setAdapter(dataAdapter);


        if (idLayR3Code != null) {

            for (int i = 0; i < spLayR3List.getCount(); i++) {
                String LayR2Name = spLayR3List.getItemAtPosition(i).toString();
                if (LayR2Name.equals(strLayR3Name)) {
                    position = i;
                }
            }
            spLayR3List.setSelection(position);



            if (disableActivation) {
                /**
                 *  block the  control selection of
                 */
                if (disableFlag) {
                    spLayR3List.setEnabled(false);
                }
            }

        }

    }

    /**
     * used  in {@link com.siddiquinoor.restclient.activity.GroupSearchPage#loadProgram(String)}
     * used  in {@link com.siddiquinoor.restclient.activity.sub_activity.commu_group_sub.CommunityGroupNDetailsRecodes#loadProgram(String, String, String)}
     * This method  load the program name to the spinner
     *
     * @param context   refer to the activity which will invoke this method.
     * @param sqlH      database reference.
     * @param spProgram spinner view.
     * @param progCode  program Code
     * @param progName  Program Name
     * @param sql       sql Query
     */

    public static void loadProgramLoader(Context context, SQLiteHandler sqlH, Spinner spProgram, String progCode, String progName, String sql, boolean disableActivation, boolean disableFlag) {
        int position = 0;


        List<SpinnerHelper> listCriteria = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, sql, null, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listCriteria);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spProgram.setAdapter(dataAdapter);


        if (progCode != null) {
            for (int i = 0; i < spProgram.getCount(); i++) {
                String programName = spProgram.getItemAtPosition(i).toString();
                if (progName.equals(programName)) {
                    position = i;
                }
            }
            spProgram.setSelection(position);

            if (disableActivation) {
                /**
                 *  block the  control selection of
                 */
                if (disableFlag) {
                    spProgram.setEnabled(false);
                }
            }

        }


    }


    public static void loadCountryLoader(Context context, SQLiteHandler sqlH, Spinner spCountry, String idCountry, String strCountry, String criteria) {
        int position = 0;


        List<SpinnerHelper> listCountry = sqlH.getListAndID(SQLiteHandler.COUNTRY_TABLE, criteria, null, true);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(context, R.layout.spinner_layout, listCountry);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spCountry.setAdapter(dataAdapter);


        if (idCountry != null) {
            for (int i = 0; i < spCountry.getCount(); i++) {
                String district = spCountry.getItemAtPosition(i).toString();
                if (district.equals(strCountry)) {
                    position = i;
                }
            }
            spCountry.setSelection(position);
        }


    }
}                                                                                                   // end of the class
