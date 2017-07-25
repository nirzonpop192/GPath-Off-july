package com.siddiquinoor.restclient.manager.sqlsyntax;

import android.util.Log;

import com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseRecordingActivity;
import com.siddiquinoor.restclient.data_model.DTSurveyTableDataModel;
import com.siddiquinoor.restclient.data_model.RegNAssgProgSrv;
import com.siddiquinoor.restclient.data_model.adapters.AssignDataModel;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.data_model.adapters.GraduationGridDataModel;
import com.siddiquinoor.restclient.views.adapters.ServiceDataModel;
import com.siddiquinoor.restclient.activity.sub_activity.gps_sub.PointAttributes;

import static com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseRecordingActivity.COMMUNITY_GROUP;
import static com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseRecordingActivity.COMMUNITY_GROUP_IG;
import static com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseRecordingActivity.COMMUNITY_GROUP_LG;
import static com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseRecordingActivity.COMMUNITY_GROUP_MG;
import static com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseRecordingActivity.COMMUNITY_GROUP_PG;
import static com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseRecordingActivity.COMMUNITY_GROUP_WE;
import static com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseRecordingActivity.DISTRIBUTION_POINT;
import static com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseRecordingActivity.GEO_LAYER_1;
import static com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseRecordingActivity.GEO_LAYER_2;
import static com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseRecordingActivity.GEO_LAYER_3;
import static com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseRecordingActivity.GEO_LAYER_4;
import static com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseRecordingActivity.GEO_LAYER_ADDRESS;
import static com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseRecordingActivity.LOOKUP_LIST;
import static com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseRecordingActivity.ORGANIZATION_LIST;
import static com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseRecordingActivity.SERVICE_SITE;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.*;

import static com.siddiquinoor.restclient.manager.SQLiteHandler.ADM_DONOR_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.FUND_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.FUND_SOURCE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.LAY_R1_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.IRRI_SYS_CODE_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.IRRI_SYS_NAME_COL;
import static com.siddiquinoor.restclient.manager.SQLiteHandler.LAY_R_LIST_CODE_COL;

import com.siddiquinoor.restclient.activity.sub_activity.gps_sub.MapActivity;

/**
 * @author FAISAL MOHAMMAD on 1/17/2016.
 *         <p>
 *         This class provided  all the local queries
 *         </p>
 */
public class SQLiteQuery {

    private static final String YES = "Y";
    private static final String TAG = SQLiteQuery.class.getSimpleName();

    public static String getUpzillaJoinQuery(String countryCode, String layR1Code) {
        return " JOIN " + SELECTED_VILLAGE_TABLE +
                " ON " + GEO_LAY_R2_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".CountryCode " +
                " AND " + GEO_LAY_R2_LIST_TABLE + "." + LAY_R1_LIST_CODE + " = " + SELECTED_VILLAGE_TABLE + ".DistrictCode " +
                " AND " + GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".UpazillaCode " +
                " WHERE " + GEO_LAY_R2_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + "= '" + countryCode + "' " +
                " AND " + GEO_LAY_R2_LIST_TABLE + "." + LAY_R1_LIST_CODE + "= '" + layR1Code + "' " +
                " GROUP BY " + GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL +
                ", " + GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_NAME_COL;
    }

    public static String getLayR3List_sql(String countryCode, String layR1Code, String layR2Code) {
        return " JOIN " + SELECTED_VILLAGE_TABLE +
                " ON " + GEO_LAY_R3_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".CountryCode " +
                " AND " + GEO_LAY_R3_LIST_TABLE + "." + LAY_R1_LIST_CODE + " = " + SELECTED_VILLAGE_TABLE + ".DistrictCode " +
                " AND " + GEO_LAY_R3_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".UpazillaCode " +
                " AND " + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".UnitCode " +
                " WHERE " + GEO_LAY_R3_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + "='" + countryCode + "' AND "
                + GEO_LAY_R3_LIST_TABLE + "." + LAY_R1_LIST_CODE + "='" + layR1Code + "' AND " +
                GEO_LAY_R3_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL + "='" + layR2Code + "' " +
                " GROUP BY " + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL + ", " + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_NAME;
    }

    public static String getVillageJoinQuery(String countryCode, String layR1Code, String layR2Code, String layR3Code) {
        return " JOIN " + SELECTED_VILLAGE_TABLE +
                " ON " + GEO_LAY_R4_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".CountryCode " +
                " AND " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R1_LIST_CODE + " = " + SELECTED_VILLAGE_TABLE + ".DistrictCode " +
                " AND " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".UpazillaCode " +
                " AND " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".UnitCode " +
                " AND " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".VillageCode " +

                " WHERE " + GEO_LAY_R4_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + "='" + countryCode + "' AND "
                + GEO_LAY_R4_LIST_TABLE + "." + LAY_R1_LIST_CODE + "='" + layR1Code + "' AND " +
                GEO_LAY_R4_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL + "='" + layR2Code + "' AND " +
                GEO_LAY_R4_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL + "='" + layR3Code + "' " +
                " GROUP BY " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_CODE_COL + ", " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_NAME_COL;
    }

    public static String getDistrictJoinQuery(String countryCode) {
        return " JOIN " + SELECTED_VILLAGE_TABLE +
                " ON " + GEO_LAY_R1_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".CountryCode " +
                " AND " + GEO_LAY_R1_LIST_TABLE + "." + LAY_R_LIST_CODE_COL + " = " + SELECTED_VILLAGE_TABLE + ".DistrictCode " +
                " WHERE " + GEO_LAY_R1_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + "='" + countryCode + "' GROUP BY " + SQLiteHandler.GEO_LAY_R1_LIST_TABLE + "." + LAY_R_LIST_CODE_COL + ", " + SQLiteHandler.GEO_LAY_R1_LIST_TABLE + "." + SQLiteHandler.LAY_R_LIST_NAME_COL;

    }


    public static String getLUP_TAParticipantCategories_sql(String cCode, String taGroup) {
        return "SELECT " + ADM_COUNTRY_CODE_COL
                + " , " + TA_GROUP_COL
                + " , " + PART_CAT_CODE_COL
                + " , " + PART_CAT_TITLE_COL
                + " FROM " + LUP_TA_PATICIPANT_CAT_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + TA_GROUP_COL + " = '" + taGroup + "' ";
    }


    public static String getTaOrganization_sql(String cCode) {
        return "SELECT " + ADM_COUNTRY_CODE_COL
                + " , " + PART_ORG_N_CODE_COL
                + " , " + PART_ORG_N_NAME_COL
                + " , " + SRC_BEN_COL
                + " FROM " + TA_PART_ORG_N_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' ";
    }

    public static String ifExistsInTaParticipantsListTable_sql(String cCode, String eventCode, String partId, String atdnDate) {
        return "SELECT * FROM " + TA_PARTICIPANTS_LIST_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + EVENT_CODE_COL + " = '" + eventCode + "'" +
                " AND " + PART_ID_COL + " = '" + partId + "' " +
                " AND " + ATDN_DATE_COL + " = '" + atdnDate + "' ";
    }


    public static String editTaParticipantsListTable_sql(String cCode, String eventCode, String partId, String atdnDate) {
        return ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + EVENT_CODE_COL + " = '" + eventCode + "'" +
                " AND " + PART_ID_COL + " = '" + partId + "' " +
                " AND " + ATDN_DATE_COL + " = '" + atdnDate + "' ";
    }


    public static String loadTaSummaryOrganization_sql(final String cCode, final String eventCode) {
        return " select  " + TA_PART_ORG_N_TABLE + "." + PART_ORG_N_CODE_COL + " AS code "
                + " , " + TA_PART_ORG_N_TABLE + "." + PART_ORG_N_NAME_COL + " AS title " +
                "    , " + "   (Select   count(  " + TA_PARTICIPANTS_LIST_TABLE + "." + PART_ORG_N_CODE_COL + ")  " +
                " from " + TA_PARTICIPANTS_LIST_TABLE
                + " where " + TA_PARTICIPANTS_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + "='" + cCode + "' " +
                "    and  " + TA_PARTICIPANTS_LIST_TABLE + "." + EVENT_CODE_COL + " = '" + eventCode + "'  " +
                "   and " + TA_PARTICIPANTS_LIST_TABLE + "." + PART_ORG_N_CODE_COL + " = " + TA_PART_ORG_N_TABLE + "." + PART_ORG_N_CODE_COL +

                " )  AS count " +
                "    from " + TA_PARTICIPANTS_LIST_TABLE + " " +
                "    inner join " + TA_PART_ORG_N_TABLE + "  ON " +
                "    " + TA_PART_ORG_N_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + TA_PARTICIPANTS_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + " " +
                "    and " + TA_PART_ORG_N_TABLE + "." + PART_ORG_N_CODE_COL + " = " + TA_PARTICIPANTS_LIST_TABLE + "." + PART_ORG_N_CODE_COL +
                "    where " + TA_PARTICIPANTS_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                "    and  " + TA_PARTICIPANTS_LIST_TABLE + "." + EVENT_CODE_COL + " = '" + eventCode + "' " +
                "    group by  " + TA_PART_ORG_N_TABLE + "." + PART_ORG_N_CODE_COL
                + " , " + TA_PART_ORG_N_TABLE + "." + PART_ORG_N_NAME_COL;
    }

    public static String loadTaSummaryCategory_sql(final String cCode, final String eventCode) {
        return " select " + TA_CATEGORY_TABLE + "." + TA_CAT_CODE_COL + " AS code "
                + " , " + TA_CATEGORY_TABLE + "." + TA_CAT_NAME_COL + " AS title " +
                "    , " + "   (Select count(  " + TA_PARTICIPANTS_LIST_TABLE + "." + ID_CATEGORY_COL + " )  " +
                "from " + TA_PARTICIPANTS_LIST_TABLE + " where " + TA_PARTICIPANTS_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + "='" + cCode + "' " +
                "    and  " + TA_PARTICIPANTS_LIST_TABLE + "." + EVENT_CODE_COL + " = '" + eventCode + "'  " +
                "   and " + TA_PARTICIPANTS_LIST_TABLE + "." + ID_CATEGORY_COL + " = " + TA_CATEGORY_TABLE + "." + TA_CAT_CODE_COL + " )  AS count " +
                "    from " + TA_PARTICIPANTS_LIST_TABLE + " " +
                "    inner join " + TA_CATEGORY_TABLE + "  ON " +
                "    " + TA_CATEGORY_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + TA_PARTICIPANTS_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + " " +
                "    and " + TA_CATEGORY_TABLE + "." + TA_CAT_CODE_COL + " = " + TA_PARTICIPANTS_LIST_TABLE + "." + ID_CATEGORY_COL +
                "    where " + TA_PARTICIPANTS_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                "    and  " + TA_PARTICIPANTS_LIST_TABLE + "." + EVENT_CODE_COL + " = '" + eventCode + "' " +
                "    group by  code , title";
//        Log.d(TAG," sql:"+sql);
//        return sql;
    }

    public static String loadTaSummaryPosition_sql(final String cCode, final String eventCode) {
        return " select " + TA_POS_PARTICIPANTS_TABLE + "." + POS_CODE_COL + " AS code "
                + " , " + TA_POS_PARTICIPANTS_TABLE + "." + POS_TITLE_COL + " AS title " +
                "    , " + "   (Select count(  " + TA_PARTICIPANTS_LIST_TABLE + "." + POS_CODE_COL + " )  from " + TA_PARTICIPANTS_LIST_TABLE + " where " + TA_PARTICIPANTS_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + "='" + cCode + "' " +
                "    and  " + TA_PARTICIPANTS_LIST_TABLE + "." + EVENT_CODE_COL + " = '" + eventCode + "'  " +
                "   and " + TA_PARTICIPANTS_LIST_TABLE + "." + POS_CODE_COL + " = " + TA_POS_PARTICIPANTS_TABLE + "." + POS_CODE_COL + " )  AS count " +
                "    from " + TA_PARTICIPANTS_LIST_TABLE + " " +
                "    inner join " + TA_POS_PARTICIPANTS_TABLE + "  ON " +
                "    " + TA_POS_PARTICIPANTS_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + TA_PARTICIPANTS_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + " " +
                "    and " + TA_POS_PARTICIPANTS_TABLE + "." + POS_CODE_COL + " = " + TA_PARTICIPANTS_LIST_TABLE + "." + POS_CODE_COL +
                "    where " + TA_PARTICIPANTS_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                "    and  " + TA_PARTICIPANTS_LIST_TABLE + "." + EVENT_CODE_COL + " = '" + eventCode + "' " +
                "    group by  code , title";
    }


    public static String loadTaSummarySession_sql(final String cCode, final String eventCode) {
//        SELECT AMSession
//        ,case when AMSession='1' then 'AM' else 'PM' end as title
//                , COUNT  (AMSession) FROM TAParticipantsList where
//        TAParticipantsList.CountryCode = '0002'     and  TAParticipantsList.EventCode = '00000002'
//                -- and title= case when 'AM' then AMSession='1' else PMSession='0' end
//        group by title

        return " select " + AM_SESSION_COL + " AS code "
                + " ,  CASE WHEN " + AM_SESSION_COL + " = '1' THEN 'AM' ELSE 'PM' END  AS title " +
                "    , " + "  count(*) AS count " +
                "    from " + TA_PARTICIPANTS_LIST_TABLE + " " +
                "    where " + TA_PARTICIPANTS_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                "    and  " + TA_PARTICIPANTS_LIST_TABLE + "." + EVENT_CODE_COL + " = '" + eventCode + "' " +
                //     " and " + SEX_COL + " =  case when "+SEX_COL+" = 'M' then 'M' else 'F' end " +
                "    group by  title ";
    }


    public static String loadTaSummarySex_sql(final String cCode, final String eventCode) {


        return " select " + TA_PARTICIPANTS_LIST_TABLE_SEX_COL + " AS code "
                + " ,  CASE WHEN " + TA_PARTICIPANTS_LIST_TABLE_SEX_COL + " = 'F' THEN 'Female' ELSE 'Male' END  AS title " +
                "    , " + "  count(  " + TA_PARTICIPANTS_LIST_TABLE_SEX_COL + " ) AS count " +
                "    from " + TA_PARTICIPANTS_LIST_TABLE + " " +
                "    where " + TA_PARTICIPANTS_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                "    and  " + TA_PARTICIPANTS_LIST_TABLE + "." + EVENT_CODE_COL + " = '" + eventCode + "' " +
                " and " + TA_PARTICIPANTS_LIST_TABLE_SEX_COL + " =  case when " + TA_PARTICIPANTS_LIST_TABLE_SEX_COL + " = 'M' then 'M' else 'F' end " +
                "    group by  code ";
    }


    /**
     * <p>This method return the sql String of Assign </p>
     *
     * @param cCode     Country Code
     * @param dstCode   District Code =Layer1Code
     * @param upCode    Upzella Code=Layer2Code
     * @param unCode    Union Code= Layer3Code
     * @param vCode     Village Code =Layer4Code
     * @param donorCode Donor Code
     * @param awardCode Award Code
     * @param prgCode   program Code
     * @param srvCode   service Code
     * @return sql String
     */

    public static String getSingleMemberForAssign_sql(String cCode, String dstCode, String upCode, String unCode, String vCode, String hhid, String memberId, String donorCode, String awardCode, String prgCode, String srvCode) {
        String getMemName;
        /**
         * 0004= Liberia's Country Code
         */
        if (cCode.equals("0004")) {
            getMemName = REGISTRATION_MEMBER_TABLE + "." + MEM_NAME_FIRST_COL + " || ' ' || " +
                    REGISTRATION_MEMBER_TABLE + "." + MEM_NAME_MIDDLE_COL + " || ' ' || " +
                    REGISTRATION_MEMBER_TABLE + "." + MEM_NAME_LAST_COL;

        } else {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;

        }

        return "SELECT " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + ", " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                + ", " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " AS newId "

                + ", " + getMemName + " As memName "
                + ", " + REGISTRATION_MEMBER_TABLE + "." + MEM_AGE
                + ", " + REGISTRATION_MEMBER_TABLE + "." + SEX_COL
                + ", " + LUP_REG_NHH_RELATION_TABLE + "." + RELATION_NAME + " AS HHRelation, " +
                " CASE WHEN LENGTH ( " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + ") > 0 " +
                " THEN 'Y' ELSE 'N' END  AS Assign "
                + ", " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL
                + ", " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + ", " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + ", " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + ", " + REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + ", " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_HH_HEAD_NAME
                + ", " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_MASTER_SERVICE_NAME_COL + "  AS AssignCriteria "

                + " , " + " regMemGrp." + GROUP_CODE_COL + " AS grpCode "
                + " , " + " grp." + GROUP_NAME_COL + " AS grpName "
                + " , regMemGrp." + ACTIVE_COL + " AS activeCode "


                + " , grpCat." + GROUP_CAT_CODE_COL + " AS catCode "
                + " , grpCat." + GROUP_CAT_NAME_COL + " AS catName "

                //", " +
               /* " ( SELECT " + SQLiteHandler.SERVICE_NAME_COL + " FROM " + SQLiteHandler.SERVICE_MASTER_TABLE + " WHERE " + SQLiteHandler.ADM_PROG_CODE_COL + " = '" + program + "' AND " +
                SQLiteHandler.ADM_SRV_CODE_COL + " = '" + service + "' GROUP BY " + SQLiteHandler.ADM_SRV_CODE_COL + " ) AS AssignCriteria " + // EXTRA FOR CRITERIA
*/

                + "FROM " + REG_N_HH_TABLE +
                " JOIN " + SQLiteHandler.REGISTRATION_MEMBER_TABLE +
                " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL +
                " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL +
                " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL +
                " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_VILLAGE_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL +
                " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_HHID + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " " +
                " LEFT JOIN " + SQLiteHandler.LUP_REG_NHH_RELATION_TABLE + " ON " + SQLiteHandler.LUP_REG_NHH_RELATION_TABLE + "." + SQLiteHandler.RELATION_CODE + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.RELATION_COL +
                " JOIN " + SQLiteHandler.SERVICE_MASTER_TABLE + " ON " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " = '" + prgCode + "' " +
                " AND " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " = '" + srvCode + "' " +
                " LEFT JOIN " + REG_N_ASSIGN_PROG_SRV_TABLE + " ON " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R1_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_UPZILLA_CODE_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_VILLAGE_CODE_COL +

                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID +

                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + PROG_CODE_COL + " = '" + prgCode + "' " +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + SRV_CODE_COL + " = '" + srvCode + "' "
                + " LEFT JOIN " + REG_N_MEM_PROG_GRP_TABLE + " AS regMemGrp ON "

                + " regMemGrp." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND  regMemGrp." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND  regMemGrp." + LAY_R2_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND  regMemGrp." + LAY_R3_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND  regMemGrp." + LAY_R4_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " AND  regMemGrp." + ADM_DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND  regMemGrp." + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND  regMemGrp." + HHID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " AND  regMemGrp." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                + " AND  regMemGrp." + PROG_CODE_COL + " = '" + prgCode + "'"
                + " AND  regMemGrp." + SRV_CODE_COL + " = '" + srvCode + "'"


                + " LEFT JOIN " + COMMUNITY_GROUP_TABLE + " AS grp "
                + " ON "
                + " grp." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND grp." + ADM_DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND grp." + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND grp." + ADM_PROG_CODE_COL + " = '" + prgCode + "'"
                + " AND grp. " + GROUP_CODE_COL + " = " + " regMemGrp." + GROUP_CODE_COL

                + " LEFT JOIN " + COMMUNITY_GROUP_CATEGORY_TABLE + " AS grpCat "
                + " ON "
                + " grpCat." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND grpCat." + ADM_DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND grpCat." + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND grpCat." + ADM_PROG_CODE_COL + " = '" + prgCode + "'"
                + " AND grpCat." + GROUP_CAT_CODE_COL + " = " + " grp." + GROUP_CAT_CODE_COL //+" regMemGrp." + SQLiteHandler.GROUP_CAT_CODE_COL
                + " WHERE " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL + " =  '" + cCode + "'" +
                " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL + " =  '" + dstCode + "'" +
                " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL + " =  '" + upCode + "' "
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL + " =  '" + unCode + "' "
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL + " =  '" + vCode + "' "
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " =  '" + hhid + "' "
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " =  '" + memberId + "' "
                + " GROUP BY newId ";


        //return sql;




/*
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.COMMUNITY_GROUP_CATEGORY_TABLE + " ("


                + " grp. " + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = "

                + " grp. " + SQLiteHandler.ADM_DONOR_CODE_COL + " = "
                + " grp. " + SQLiteHandler.ADM_AWARD_CODE_COL + " = "
                + " grp. " + SQLiteHandler.ADM_PROG_CODE_COL + " = "
                + " grpCat." + SQLiteHandler.GROUP_CAT_CODE_COL + " = "
                + " grpCat." + SQLiteHandler.GROUP_CAT_NAME_COL + "  "
                + " , " + SQLiteHandler.GROUP_CAT_SHORT_NAME_COL + "  "


        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.COMMUNITY_GROUP_TABLE + " ("


                + " grp." + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = "
                + " grp." + SQLiteHandler.ADM_DONOR_CODE_COL + " = "
                + " grp." + SQLiteHandler.ADM_AWARD_CODE_COL + " = "
                + " grp." + SQLiteHandler.ADM_PROG_CODE_COL + " = "
                + " grp. " + SQLiteHandler.GROUP_CODE_COL + " = "
                + " grp. " + SQLiteHandler.GROUP_NAME_COL + " VARCHAR(100) "
                + " grp. " + SQLiteHandler.GROUP_CAT_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LAY_R_LIST_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.SERVICE_CENTER_CODE_COL + " VARCHAR(4) "
                + " )";*/


        /** very good thinking
         * User can search by House hold & member Id also*/
        /*        SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.LAY_R_LIST_NAME_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.LAY_R2_LIST_NAME_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.LAY_R3_LIST_NAME + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.LAY_R4_LIST_NAME_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID + " LIKE '%" + memberSearchId + "%' " +
                // +" GROUP BY " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL;*//*+
                " ORDER BY " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + " DESC "*;*/

    }

    /**
     * <p>This method return the sql String of Assign </p>
     *
     * @param cCode          Country Code
     * @param dstCode        District Code =Layer1Code
     * @param upCode         Upzella Code=Layer2Code
     * @param unCode         Union Code= Layer3Code
     * @param vCode          Village Code =Layer4Code
     * @param donorCode      Donor Code
     * @param awardCode      Award Code
     * @param prgCode        program Code
     * @param srvCode        service Code
     * @param memberSearchId search by member Id
     * @return sql String
     */

    public static String getAssignListViewSelectQuery(String cCode, String dstCode, String upCode, String unCode, String vCode, String donorCode, String awardCode, String prgCode, String srvCode, String memberSearchId) {
        String getMemName;

        // 0004= Liberia's Country Code

        if (cCode.equals("0004")) {

            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;

        }

        return "SELECT " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + ", " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                + ", " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " AS newId "

                + ", " + getMemName + " As memName "
                + ", " + REGISTRATION_MEMBER_TABLE + "." + MEM_AGE
                + ", " + REGISTRATION_MEMBER_TABLE + "." + SEX_COL
                + ", " + LUP_REG_NHH_RELATION_TABLE + "." + RELATION_NAME + " AS HHRelation, " +
                " CASE WHEN LENGTH ( " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + ") > 0 " +
                " THEN 'Y' ELSE 'N' END  AS Assign "
                + ", " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL
                + ", " + REGISTRATION_MEMBER_TABLE + "." + LAY_R_LIST_NAME_COL
                + ", " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_NAME_COL
                + ", " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_NAME
                + ", " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_NAME_COL
                + ", " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HH_HEAD_NAME
                + ", " +

                SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_MASTER_SERVICE_NAME_COL + "  AS AssignCriteria " +
                //", " +
               /* " ( SELECT " + SQLiteHandler.SERVICE_NAME_COL + " FROM " + SQLiteHandler.SERVICE_MASTER_TABLE + " WHERE " + SQLiteHandler.ADM_PROG_CODE_COL + " = '" + program + "' AND " +
                SQLiteHandler.ADM_SRV_CODE_COL + " = '" + service + "' GROUP BY " + SQLiteHandler.ADM_SRV_CODE_COL + " ) AS AssignCriteria " + // EXTRA FOR CRITERIA
*/

                "FROM " + REG_N_HH_TABLE +
                " JOIN " + SQLiteHandler.REGISTRATION_MEMBER_TABLE +
                " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL +
                " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL +
                " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL +
                " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL +
                " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HHID + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " " +
                " LEFT JOIN " + LUP_REG_NHH_RELATION_TABLE + " ON " + LUP_REG_NHH_RELATION_TABLE + "." + RELATION_CODE + " = " + REGISTRATION_MEMBER_TABLE + "." + RELATION_COL +
                " JOIN " + SERVICE_MASTER_TABLE + " ON " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " = '" + prgCode + "' " +
                " AND " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " = '" + srvCode + "' " +
                " LEFT JOIN " + REG_N_ASSIGN_PROG_SRV_TABLE + " ON " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_UPZILLA_CODE_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_VILLAGE_CODE_COL +

                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID +

                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + PROG_CODE_COL + " = '" + prgCode + "' " +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + SRV_CODE_COL + " = '" + srvCode + "' " +


                " WHERE " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL + " =  '" + cCode + "'" +
                " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL + " =  '" + dstCode + "'" +
                " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL + " =  '" + upCode + "' "
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL + " =  '" + unCode + "' "
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL + " =  '" + vCode + "' "
                + " AND " +
                /** very good thinking
                 * User can search by House hold & member Id also*/
                REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL + " || '' || "
                + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL + " || '' || "
                + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL + " || '' || "
                + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL + " || '' || "
                + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL + " || '' || "
                + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " || \"\" || "
                + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " LIKE '%" + memberSearchId + "%' " +
                // +" GROUP BY " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL;/*+
                " ORDER BY " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " DESC ";


    }

    /**
     * @param cCode   Country Code
     * @param dstCode District Code =Layer1Code
     * @param upCode  Upzella Code=Layer2Code
     * @param unCode  Union Code= Layer3Code
     * @param vCode   Village Code =Layer4Code
     * @return sql String
     */

    public static String getMemberListView_searchBy_ID_sql(final String cCode, final String dstCode, final String upCode, final String unCode, final String vCode, final String memberSearchId, int number) {
        String getMemName;
        /**
         * 0004= Liberia's Country Code
         */
        if (cCode.equals("0004")) {
            getMemName = REGISTRATION_MEMBER_TABLE + "." + MEM_NAME_FIRST_COL + " || ' ' || " +
                    REGISTRATION_MEMBER_TABLE + "." + MEM_NAME_MIDDLE_COL + " || ' ' || " +
                    REGISTRATION_MEMBER_TABLE + "." + MEM_NAME_LAST_COL;

        } else {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;

        }

        String limit = "";
        if (memberSearchId.length() > 0 && !memberSearchId.equals(""))
            limit = "";
        else
            limit = " LIMIT 20 OFFSET " + number;

        return "SELECT " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + ", " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                + ", " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " AS newId "

                + ", " + getMemName + " As memName "
                + ", " + REGISTRATION_MEMBER_TABLE + "." + MEM_AGE
                + ", " + REGISTRATION_MEMBER_TABLE + "." + SEX_COL
                + ", " + LUP_REG_NHH_RELATION_TABLE + "." + RELATION_NAME + " AS HHRelation "

                + ", " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL
                + ", " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + ", " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + ", " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + ", " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + ", " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HH_HEAD_NAME
                + " , " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_NAME_COL + " AS layR4Name "
                + " , " + LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + REGN_ADDRESS_LOOKUP_NAME_COL + " As address "

//
                + " FROM " + REG_N_HH_TABLE
                + " JOIN " + REGISTRATION_MEMBER_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HHID + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " "
                + " LEFT JOIN " + LUP_REG_NHH_RELATION_TABLE
                + " ON " + LUP_REG_NHH_RELATION_TABLE + "." + RELATION_CODE + " = " + REGISTRATION_MEMBER_TABLE + "." + RELATION_COL
                + " left JOIN " + GEO_LAY_R4_LIST_TABLE + " ON "

                + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + " = " + GEO_LAY_R4_LIST_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_CODE_COL

                + " LEFT JOIN " + LUP_REGN_ADDRESS_LOOKUP_TABLE
                + " ON " + LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL

                + " AND " + LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE
                + " AND  " + LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_UPZILLA_CODE_COL
                + " AND  " + LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL
                + " AND  " + LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_VILLAGE_CODE_COL

                + " AND " + LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + REGN_ADDRESS_LOOKUP_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL


                + " WHERE " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL + " =  '" + cCode + "'"
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL + " =  '" + dstCode + "'"
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL + " =  '" + upCode + "' "
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL + " =  '" + unCode + "' "
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL + " =  '" + vCode + "' "
                + " AND "
                /** very good thinking
                 * User can search by House hold & member Id also*/
                + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL + " || ''  || "
                + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL + " || ''  || "
                + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL + " || ''  || "
                + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL + " || ''  || "
                + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " || '' || "
                + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " LIKE '%" + memberSearchId + "' "

                // group by
                + " GROUP BY " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL + " , "
                + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL + " , "
                + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL + " , "
                + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL + " , "
                + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " , "
                + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " "


                + " ORDER BY " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " DESC "
                + limit
                ;


    }


    /**
     * @param cCode   Country Code
     * @param dstCode District Code =Layer1Code
     * @param upCode  Upzella Code=Layer2Code
     * @param unCode  Union Code= Layer3Code
     * @param vCode   Village Code =Layer4Code
     * @return sql String
     */

    public static String getMemberListView_searchBy_Name_sql(final String cCode, final String dstCode, final String upCode, final String unCode, final String vCode, final String memberSearchName, int number) {
        String getMemName;
        /**
         * 0004= Liberia's Country Code
         */
        if (cCode.equals("0004")) {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;

        }

        return "SELECT " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " , " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                + " , " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " AS newId "

                + " , " + getMemName + " As memName "
                + " , " + REGISTRATION_MEMBER_TABLE + "." + MEM_AGE
                + " , " + REGISTRATION_MEMBER_TABLE + "." + SEX_COL
                + " , " + LUP_REG_NHH_RELATION_TABLE + "." + RELATION_NAME + " AS HHRelation "

                + " , " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " , " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " , " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " , " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " , " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " , " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HH_HEAD_NAME
                + " , " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_NAME_COL + " AS layR4Name "
                + " , " + LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + REGN_ADDRESS_LOOKUP_NAME_COL + " As address "

//
                + " FROM " + REG_N_HH_TABLE
                + " JOIN " + REGISTRATION_MEMBER_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HHID + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " "
                + " LEFT JOIN " + LUP_REG_NHH_RELATION_TABLE
                + " ON " + LUP_REG_NHH_RELATION_TABLE + "." + RELATION_CODE + " = " + REGISTRATION_MEMBER_TABLE + "." + RELATION_COL
                + " left JOIN " + GEO_LAY_R4_LIST_TABLE + " ON "

                + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + " = " + GEO_LAY_R4_LIST_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_CODE_COL

                + " LEFT JOIN " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE
                + " ON " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL

                + " AND " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE
                + " AND  " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_UPZILLA_CODE_COL
                + " AND  " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL
                + " AND  " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_VILLAGE_CODE_COL

                + " AND " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL


                + " WHERE " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL + " =  '" + cCode + "'"
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL + " =  '" + dstCode + "'"
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL + " =  '" + upCode + "' "
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL + " =  '" + unCode + "' "
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL + " =  '" + vCode + "' "
                + " AND "
                /** very good thinking
                 * User can search by House hold & member Name also*/
                + getMemName + " LIKE '%" + memberSearchName + "%' "

                + " ORDER BY " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " DESC "
                + " LIMIT 20 OFFSET " + number
                ;


    }


    public static String getMemberCount_sql(final String cCode, final String dstCode, final String upCode, final String unCode, final String vCode, final String memberSearchName, int number) {


        return "SELECT COUNT("
                + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " || '' || " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + ") AS cont "


//
                + " FROM " + REG_N_HH_TABLE
                + " JOIN " + REGISTRATION_MEMBER_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HHID + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " "
                + " LEFT JOIN " + LUP_REG_NHH_RELATION_TABLE
                + " ON " + LUP_REG_NHH_RELATION_TABLE + "." + RELATION_CODE + " = " + REGISTRATION_MEMBER_TABLE + "." + RELATION_COL
                + " left JOIN " + GEO_LAY_R4_LIST_TABLE + " ON "

                + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + " = " + GEO_LAY_R4_LIST_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_CODE_COL

                + " LEFT JOIN " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE
                + " ON " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL

                + " AND " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE
                + " AND  " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_UPZILLA_CODE_COL
                + " AND  " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL
                + " AND  " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_VILLAGE_CODE_COL

                + " AND " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL


                + " WHERE " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL + " =  '" + cCode + "'"
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL + " =  '" + dstCode + "'"
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL + " =  '" + upCode + "' "
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL + " =  '" + unCode + "' "
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL + " =  '" + vCode + "' ";


    }


    public static String getMemberGraduationStatusList_sql(String cCode, String donorCode, String awardCode, String programCode, String srvCode, String memberId) {
        String getMemberName;
        if (cCode.equals("0004")) {
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;


        return "SELECT " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL +
                " , " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID +

                " , " + getMemberName + " AS memName " +
                " , " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " , " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R1_LIST_CODE_COL +
                " , " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL +
                " , " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL +
                " , " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL +
                " , " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + PROG_CODE_COL +
                " , " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + SRV_CODE_COL +
                " , " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.GRD_DATE_COL +
                " , " + GEO_LAY_R4_LIST_TABLE + "." + SQLiteHandler.LAY_R4_LIST_NAME_COL +
                " , ( SELECT " + SQLiteHandler.GRD_TITLE_COL + " FROM " + SQLiteHandler.REG_N_LUP_GRADUATION_TABLE +
                " WHERE " + ADM_PROG_CODE_COL + " = '" + programCode + "'"
                + " AND " + ADM_SRV_CODE_COL + " = '" + srvCode + "'"
                + " AND " + SQLiteHandler.GRD_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.GRD_CODE_COL
                + " ) AS GRDTitle "
                + " , " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " AS nMemId "
//                "
                + " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE
                + " INNER JOIN " + REGISTRATION_MEMBER_TABLE + " ON "
                + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R1_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                + " INNER JOIN " + GEO_LAY_R4_LIST_TABLE + " ON "
                + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R1_LIST_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " WHERE " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + PROG_CODE_COL + " = '" + programCode + "'"
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + SRV_CODE_COL + " = '" + srvCode + "'"
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " LIKE  '%" + memberId + "%' "
                + " GROUP BY " + REGISTRATION_MEMBER_TABLE + "." + MEM_NAME_COL
                + " ORDER BY  " + REGISTRATION_MEMBER_TABLE + "." + MEM_NAME_COL;
    }

    public static String getSingleLiberiaMemberDataQuery(String country, String district, String upazilla, String union, String village, String household, String member) {

        return "SELECT  "
                + SQLiteHandler.MEM_NAME_COL
                + " , " + SQLiteHandler.ENTRY_BY
                + " , " + SQLiteHandler.ENTRY_DATE

                //  + REG_DATE_COL + " , " if it need latter
                + " , " + BIRTH_YEAR_COL
                + " , " + MARITAL_STATUS_COL
                + " , " + CONTACT_NO_COL
                + " , " + MEMBER_OTHER_ID_COL
                + " , " + MEM_NAME_FIRST_COL
                + " , " + MEM_NAME_MIDDLE_COL
                + " , " + MEM_NAME_LAST_COL
                + " , " + PHOTO_COL
                + " , " + TYPE_ID_COL
                + " , " + ID_NO_COL
                + " , " + V_BSC_MEM_1_NAME_FIRST_COL
                + " , " + V_BSC_MEM_1_NAME_MIDDLE_COL
                + " , " + V_BSC_MEM_1_NAME_LAST_COL
                + " , " + V_BSC_MEM_1_TITLE_COL
                + " , " + V_BSC_MEM_2_NAME_FIRST_COL
                + " , " + V_BSC_MEM_2_NAME_MIDDLE_COL
                + " , " + V_BSC_MEM_2_NAME_LAST_COL
                + " , " + V_BSC_MEM_2_TITLE_COL
                + " , " + PROXY_DESIGNATION_COL
                + " , " + PROXY_NAME_FIRST_COL
                + " , " + PROXY_NAME_MIDDLE_COL
                + " , " + PROXY_NAME_LAST_COL
                + " , " + PROXY_BIRTH_YEAR_COL
                + " , " + PROXY_PHOTO_COL
                + " , " + PROXY_TYPE_ID_COL
                + " , " + PROXY_ID_NO_COL
                + " , " + PROXY_BSC_MEM_1_NAME_FIRST_COL
                + " , " + PROXY_BSC_MEM_1_NAME_MIDDLE_COL
                + " , " + PROXY_BSC_MEM_1_NAME_LAST_COL
                + " , " + PROXY_BSC_MEM_1_TITLE_COL
                + " , " + PROXY_BSC_MEM_2_NAME_FIRST_COL
                + " , " + PROXY_BSC_MEM_2_NAME_MIDDLE_COL
                + " , " + PROXY_BSC_MEM_2_NAME_LAST_COL
                + " , " + PROXY_BSC_MEM_2_TITLE_COL
                +

                "   FROM "
                + REGISTRATION_MEMBER_TABLE + "  " +
                " WHERE " + ADM_COUNTRY_CODE_COL + "='" + country + "' " +
                " AND " + LAY_R1_LIST_CODE_COL + "='" + district + "' " +
                " AND " + LAY_R1_LIST_CODE_COL + "='" + upazilla + "' " +
                " AND " + LAY_R1_LIST_CODE_COL + "='" + union + "' " +
                " AND " + LAY_R1_LIST_CODE_COL + "='" + village + "' " +
                " AND " + HHID_COL + "='" + household + "' " +
                " AND " + HH_MEM_ID + "='" + member + "' ";
    }

    public static String getDeletedHouseHoldIDQuery(String personId) {
        return "SELECT "
                + ADM_COUNTRY_CODE_COL + " , "
                + LAY_R1_LIST_CODE + " , "
                + REGISTRATION_TABLE_UPZILLA_CODE_COL + " , "
                + REGISTRATION_TABLE_UNION_CODE_COL + " , "
                + REGISTRATION_TABLE_VILLAGE_CODE_COL + " , "
                + REGISTRATION_TABLE_HHID + "  "
                + " FROM " + REG_N_HH_TABLE
                + " WHERE " + REGISTRATION_TABLE_HHID + " = '" + personId + "' ";
    }

    public static String getDeletedMemberIDQuery(int memberId) {
        return "SELECT "
                + ADM_COUNTRY_CODE_COL + " , "
                + LAY_R1_LIST_CODE_COL + " , "
                + LAY_R2_LIST_CODE_COL + " , "
                + LAY_R3_LIST_CODE_COL + " , "
                + LAY_R4_LIST_CODE_COL + " , "
                + HHID_COL + " , "
                + HH_MEM_ID + "  "
                + " FROM " + SQLiteHandler.REGISTRATION_MEMBER_TABLE
                + " WHERE " + SQLiteHandler.ID_COL + " = '" + memberId + "' ";
    }

    public static String getSavePermissionSelectQuery(String tableName, String columnName, String country, String district, String upzella, String unit, String village) {
        return " SELECT " + columnName +
                // " SELECT "+SQLiteHandler.BTN_SAVE_COL+
                //" FROM "+SQLiteHandler.STAFF_GEO_INFO_ACCESS_TABLE+
                " FROM " + tableName +
                " WHERE " +
                ADM_COUNTRY_CODE_COL + " = '" + country + "' " +
                " AND " + LAY_R_LIST_CODE_COL + " = '" + district + "' " +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + upzella + "' " +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + unit + "' " +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + village + "' ";
    }

    public static String getFFAMemberListForServiceSelectQuery(String country, String donor,
                                                               String award, String program, String service,
                                                               String opCode, String opMonthCode,
                                                               String memberId, String groupCode, String distFlag) {
        String getMemberName;
        if (country.equals("0004")) {
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;


        return "SELECT  " +
                SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + "," + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_HH_HEAD_NAME
                + "," + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                + ", " + getMemberName + " AS memName "
                + " ," + REGISTRATION_MEMBER_TABLE + "." + SEX_COL
                + "," + REGISTRATION_MEMBER_TABLE + "." + MEM_AGE
                + "," + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL
                + "," + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_DONOR_CODE_COL
                + "," + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_AWARD_CODE_COL
                + "," + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R_LIST_CODE_COL
                + "," + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL
                + "," + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL
                + "," + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL
                + "," + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL
                + "," + REG_N_ASSIGN_PROG_SRV_TABLE + "." + PROG_CODE_COL
                + "," + REG_N_ASSIGN_PROG_SRV_TABLE + "." + SRV_CODE_COL
                + "," +

                "( SELECT  COUNT (*) " +
                " FROM " + SQLiteHandler.SERVICE_TABLE
                + " WHERE "
                + ADM_COUNTRY_CODE_COL + " = " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + ADM_DONOR_CODE_COL + " = " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + ADM_AWARD_CODE_COL + " = " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_AWARD_CODE_COL
                + " AND " + HHID_COL + " = " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL
                + " AND " + MEM_ID_COL + " = " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID
                + " AND " + PROG_CODE_COL + " = " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + PROG_CODE_COL
                + " AND " + SRV_CODE_COL + " = " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + SRV_CODE_COL
                + " AND " + OPERATION_CODE_COL + " = '2'"
                + " AND " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' "
                + " AND " + SQLiteHandler.DIST_FLAG_COL + " = '" + distFlag + "' "
                + " ) AS SrvRecieved "

                + " , " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " || \"\" || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " || \"\" || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " || \"\" || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " || \"\" || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL
                + " || \"\" || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " AS NewID "

                /** Add new string  id show ..*/


                + " , IFNULL(" + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.WORK_DAY_COL + ",'0') AS WD"

                + " FROM " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE +
                " INNER JOIN " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + " ON " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL

                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + MEM_ID_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID

                + " INNER JOIN " +
                REG_N_HH_TABLE
                + " ON " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R1_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_UPZILLA_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_VILLAGE_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL + " = " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_HHID

                + " INNER JOIN " +
                SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + " ON " +

                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + HHID_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HH_MEM_ID + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_DONOR_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_AWARD_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + ADM_AWARD_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + PROG_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + PROG_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + SRV_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SRV_CODE_COL
                + " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + GROUP_CODE_COL + " = '" + groupCode + "'"
                + " LEFT JOIN "
                + SERVICE_TABLE + " ON "
                + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_AWARD_CODE_COL
                + " AND " + SERVICE_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R_LIST_CODE_COL
                + " AND " + SERVICE_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + SERVICE_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + SERVICE_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " AND " + SERVICE_TABLE + "." + HHID_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL
                + " AND " + SERVICE_TABLE + "." + MEM_ID_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                + " AND " + SERVICE_TABLE + "." + PROG_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + PROG_CODE_COL
                + " AND " + SERVICE_TABLE + "." + SRV_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SRV_CODE_COL
                + " AND " + SERVICE_TABLE + "." + OPERATION_CODE_COL + " = '2'"
                + " AND " + SERVICE_TABLE + "." + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' "
                + " WHERE " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + country + "'" +
                "  AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donor + "'" +
                "  AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + award + "' " +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + PROG_CODE_COL + " = '" + program + "' " +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + SRV_CODE_COL + " = '" + service + "'" +
                " AND " +
                /**  id Searching  with 15 digit*/
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R_LIST_CODE_COL + " || \"\" || " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " || \"\" || " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " || \"\" || " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + " || \"\" || " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL + " || \"\" || " +
                REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                + " LIKE '%" + memberId + "%' " +
                " GROUP BY " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + HHID_COL +

                "  ORDER BY " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " DESC ";//, RegMembers.RegisterID DESC";


    }

    private static String dbo_Get_SrvMemCount(String opMonthCode, String distFlag, String condTable) {
        return "SELECT  COUNT (*) " +
                " FROM " + SERVICE_TABLE +
                " WHERE "
                + ADM_COUNTRY_CODE_COL + " = " + condTable + "." + ADM_COUNTRY_CODE_COL
                + " AND " + ADM_DONOR_CODE_COL + " = " + condTable + "." + ADM_DONOR_CODE_COL
                + " AND " + ADM_AWARD_CODE_COL + " = " + condTable + "." + ADM_AWARD_CODE_COL
                + " AND " + HHID_COL + " = " + condTable + "." + HHID_COL
                + " AND " + MEM_ID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                + " AND " + PROG_CODE_COL + " = " + condTable + "." + PROG_CODE_COL
                + " AND " + SRV_CODE_COL + " = " + condTable + "." + SRV_CODE_COL
                + " AND " + OPERATION_CODE_COL + " = '2'"
                + " AND " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' "
                + " AND " + DIST_FLAG_COL + " = '" + distFlag + "' ";
    }

    private static String dbo_Get_OpMonthEndDate(String cCode, String donorCode, String awardCode, String opCode, String opMonthCode) {
        return " Select date(substr(" + END_DATE_COL + ",1,4)||'-'|| substr(" + END_DATE_COL + ",6,2)||'-'|| substr(" + END_DATE_COL + ",9,2))" +
                "from " + OP_MONTH_TABLE + " where "
                + ADM_COUNTRY_CODE_COL + "= '" + cCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + "= '" + donorCode + "'" +
                " AND " + ADM_AWARD_CODE_COL + "= '" + awardCode + "' " +
                " AND " + OPERATION_CODE_COL + "='" + opCode + "'" +
                " AND " + OP_MONTH_CODE_COL + "='" + opMonthCode + "'";
    }


    private static String dbo_Get_OpMonthStartDate(String cCode, String donorCode, String awardCode, String opCode, String opMonthCode) {
        return " Select date(substr(" + START_DATE_COL + ",1,4)||'-'|| substr(" + START_DATE_COL + ",6,2)||'-'|| substr(" + START_DATE_COL + ",9,2))" +
                "from " + OP_MONTH_TABLE + " where "
                + ADM_COUNTRY_CODE_COL + "= '" + cCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + "= '" + donorCode + "'" +
                " AND " + ADM_AWARD_CODE_COL + "= '" + awardCode + "' " +
                " AND " + OPERATION_CODE_COL + "='" + opCode + "'" +
                " AND " + OP_MONTH_CODE_COL + "='" + opMonthCode + "'";
    }

    public static String dbo_Get_MemCU2DOB(String cCode, String layR1Code, String layR2Code, String layR3Code, String layR4Code, String hhId, String memId) {
        return "SELECT " + CU2DOB_DATE_COL + " FROM " + REG_N_CU2_TABLE
                + " WHERE    " + ADM_COUNTRY_CODE_COL + " = " + cCode
                + " AND " + LAY_R1_LIST_CODE_COL + " = " + layR1Code
                + " AND " + LAY_R2_LIST_CODE_COL + " = " + layR2Code
                + " AND " + LAY_R3_LIST_CODE_COL + " = " + layR3Code
                + " AND " + LAY_R4_LIST_CODE_COL + " = " + layR4Code
                + " AND " + HHID_COL + " = " + hhId
                + " AND " + MEM_ID_COL + " = " + memId + "  ";
    }

    public static String dbo_Get_dayDifference(String cCode, String donorCode, String awardCode, String opMonthCode, String tableName, String columnName) {
        return "CAST ( ( (" +
                "SELECT julianday(date(substr(" + END_DATE_COL + ", 1, 4) || '-' || substr(" + END_DATE_COL + ", 6, 2) || '-' || substr(" + END_DATE_COL + ", 9, 2) ) ) AS d " +
                "        FROM " + OP_MONTH_TABLE +
                "        WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + "" + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + "" + ADM_AWARD_CODE_COL + " = '" + awardCode + "'" +
                " AND " + "" + OPERATION_CODE_COL + " = '2' " +
                " AND " + "" + OP_MONTH_CODE_COL + " = '" + opMonthCode + "'" +
                "  )" +
                "   - (" +
                "    SELECT julianday(date(substr(" + columnName + ", 1, 4) || '-' || substr(" + columnName + ", 6, 2) || '-' || substr(" + columnName + ", 9, 2) ) ) AS dd " +
                "  FROM " + tableName + " " +
                "  WHERE " + ADM_COUNTRY_CODE_COL + " = regAss." + ADM_COUNTRY_CODE_COL + " AND" +
                " " + LAY_R1_LIST_CODE_COL + " = regAss." + LAY_R1_LIST_CODE_COL + " AND " +
                " " + LAY_R2_LIST_CODE_COL + " = regAss." + LAY_R2_LIST_CODE_COL + " AND " +
                " " + LAY_R3_LIST_CODE_COL + " = regAss." + LAY_R3_LIST_CODE_COL + " AND" +
                " " + LAY_R4_LIST_CODE_COL + " = regAss." + LAY_R4_LIST_CODE_COL + " AND" +
                " " + HHID_COL + " = regAss." + HHID_COL + " AND" +
                " " + MEM_ID_COL + " = regAss." + MEM_ID_COL + "" +
                "  )" +
                "  ) AS INTEGER) AS daydiffernce";
    }


    public static String getRptMemberServiceList_cu2_sql(String country, String donor, String award, String program, String srvCode, String opCode, String opMonthCode, String memId, String grpCode, String distFlag,
                                                         String grpLayR1Code, String grpLayR2Code, String grpLayR3Code
    ) {

        String cu2_sql = "SELECT  " +
                dbo_Get_dayDifference(country, donor, award, opMonthCode, REG_N_CU2_TABLE, CU2DOB_DATE_COL) +

                ", " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + "," +
                REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HH_HEAD_NAME + "," +
                REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + ", " +
                REGISTRATION_MEMBER_TABLE + "." + MEM_NAME_COL + " AS memName ," +
                REGISTRATION_MEMBER_TABLE + "." + SEX_COL + "," +
                REGISTRATION_MEMBER_TABLE + "." + MEM_AGE + "," +
                " regAss." + ADM_COUNTRY_CODE_COL + "," +
                " regAss." + ADM_DONOR_CODE_COL + "," +
                " regAss." + ADM_AWARD_CODE_COL + "," +
                " regAss." + LAY_R1_LIST_CODE_COL + "," +
                " regAss." + LAY_R2_LIST_CODE_COL + "," +
                " regAss." + LAY_R3_LIST_CODE_COL + "," +
                " regAss." + LAY_R4_LIST_CODE_COL + "," +
                " regAss." + HHID_COL + "," +
                " regAss." + PROG_CODE_COL + "," +
                " regAss." + SRV_CODE_COL + "," +

                "( " + dbo_Get_SrvMemCount(opMonthCode, distFlag, " regAss") + " ) AS SrvRecieved ," +
                " regAss." + LAY_R1_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R2_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R3_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R4_LIST_CODE_COL + " || \"\" || " +
                " regAss." + HHID_COL + " || \"\" || " +
                REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " AS NewID " +
                /** Add new string  id show ..*/

                "FROM " + REG_N_ASSIGN_PROG_SRV_TABLE + " AS regAss" +
                " INNER JOIN " + REGISTRATION_MEMBER_TABLE + " ON "
                + " regAss." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND  regAss." + LAY_R1_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND  regAss." + LAY_R2_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND  regAss." + LAY_R3_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND  regAss." + LAY_R4_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " AND  regAss." + HHID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " AND  regAss." + MEM_ID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                +

                " INNER JOIN " +
                REG_N_HH_TABLE + " ON "
                + " regAss." + ADM_COUNTRY_CODE_COL + " = " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND  regAss." + LAY_R1_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE
                + " AND  regAss." + LAY_R2_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL
                + " AND  regAss." + LAY_R3_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL
                + " AND  regAss." + LAY_R4_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL
                + " AND  regAss." + HHID_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HHID


// for test
                + " INNER JOIN " +
                REG_N_MEM_PROG_GRP_TABLE + " ON "

                + " regAss." + ADM_COUNTRY_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND  regAss." + LAY_R1_LIST_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND  regAss." + LAY_R2_LIST_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND  regAss." + LAY_R3_LIST_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND  regAss." + HHID_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + HHID_COL
                + " AND  regAss." + MEM_ID_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID
                + " AND  regAss." + ADM_DONOR_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND  regAss." + ADM_AWARD_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + ADM_AWARD_CODE_COL
                + " AND  regAss." + PROG_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + PROG_CODE_COL
                + " AND  regAss." + SRV_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + SRV_CODE_COL


                + " WHERE " +
                " regAss." + ADM_COUNTRY_CODE_COL + " = '" + country + "'" +
                "  AND " + " regAss." + ADM_DONOR_CODE_COL + " = '" + donor + "'" +
                "  AND " + " regAss." + ADM_AWARD_CODE_COL + " = '" + award + "'" +
                "  AND " + " regAss." + PROG_CODE_COL + " = '" + program + "'" +
                "  AND " + " regAss." + SRV_CODE_COL + " = '" + srvCode + "'" +

                " AND date(substr(regAss." + REG_N_DAT_COL + ", 1, 4) || " +
                "'-' || substr(regAss." + REG_N_DAT_COL + ", 6, 2) || '-' || substr(regAss." + REG_N_DAT_COL + ", 9, 2)) <="
                + "(" + dbo_Get_OpMonthEndDate(country, donor, award, "2", opMonthCode) + ")" +
                " AND date(substr(regAss." + GRD_DATE_COL + ", 1, 4) || '-' || substr(regAss." + GRD_DATE_COL + ", 6, 2) || '-' || substr(regAss." + GRD_DATE_COL + ", 9, 2)) >="
                + "(" + dbo_Get_OpMonthStartDate(country, donor, award, "2", opMonthCode) + ")" +


                " AND "
                + " daydiffernce <= 720 " +

                " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + GROUP_CODE_COL + " = '" + grpCode + "'" +
                " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R1_LIST_CODE_COL + " = '" + grpLayR1Code + "'" +
                " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R2_LIST_CODE_COL + " = '" + grpLayR2Code + "'" +
                " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R3_LIST_CODE_COL + " = '" + grpLayR3Code + "'" +
                " AND " +
                " regAss." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R2_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R3_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R4_LIST_CODE_COL + " || \"\" || " +
                " regAss." + HHID_COL + " || \"\" || " +
                REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                + " LIKE '%" + memId + "%' " +
                " GROUP BY " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL +

                "  ORDER BY " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " DESC ";

        Log.d(TAG, cu2_sql);
        return cu2_sql;
        //  " , "+ REGISTRATION_MEMBER_TABLE+"."+HH_MEM_ID+ "  ORDER BY "+ REGISTRATION_MEMBER_TABLE+"."+HHID_COL+" DESC, RegMembers.RegisterID DESC";

      /*  SELECT CAST (((
                SELECT
        julianday(date(substr(usaEndDate, 7, 4) || '-' || substr(usaEndDate, 1, 2) || '-' || substr(usaEndDate, 4, 2)))
        AS d
        FROM OpMonthTable
        WHERE CountryCode = '0002' AND
                DonorCode = '01' AND
                AwardCode = '01' AND
                OpCode = '2' AND
                OpMonthCode = '15'
        )
        -(
                SELECT
        julianday(date(substr(CU2Dob, 7, 4) || '-' || substr(CU2Dob, 1, 2) || '-' || substr(CU2Dob, 4, 2)))
        AS dd
        FROM RegN_CU2
        WHERE CountryCode = regAss.CountryCode AND
                DistrictCode = regAss.DistrictCode AND
                UpazillaCode = regAss.UpazillaCode AND
                UnitCode = regAss.UnitCode AND
                VillageCode = regAss.VillageCode AND
                RegisterID = regAss.RegisterID AND
                MemberID = regAss.MemberID
        )
        )AS INTEGER)AS daydiffernce,
        RegMembers.RegisterID,
                Registration.PersonName,
                RegMembers.MemberID,
                RegMembers.MemberName AS memName,
        RegMembers.Sex,
                RegMembers.MemAge,
                regAss.CountryCode,
                regAss.DonorCode,
                regAss.AwardCode,
                regAss.DistrictCode,
                regAss.UpazillaCode,
                regAss.UnitCode,
                regAss.VillageCode,
                regAss.RegisterID,
                regAss.ProgramCode,
                regAss.ServiceCode,
                (
                        SELECT COUNT( *)
        FROM Service
        WHERE CountryCode = regAss.CountryCode AND
                DonorCode = regAss.DonorCode AND
                AwardCode = regAss.AwardCode AND
                RegisterID = regAss.RegisterID AND
                MemberID = RegMembers.MemberID AND
                ProgramCode = regAss.ProgramCode AND
                ServiceCode = regAss.ServiceCode AND
                OpCode = '2' AND
                OpMonthCode = '15' AND
                DistFLAG = 'FoodFlag'
        )
        AS SrvRecieved,
        regAss.DistrictCode || "" || regAss.UpazillaCode || "" || regAss.UnitCode || "" || regAss.VillageCode || "" || regAss.RegisterID || "" || RegMembers.MemberID
        AS NewID
        FROM RegNAssignProgService AS regAss
        INNER JOIN
        RegMembers ON regAss.CountryCode = RegMembers.CountryCode AND
        regAss.DistrictCode = RegMembers.DistrictName AND
        regAss.UpazillaCode = RegMembers.UpazillaName AND
        regAss.UnitCode = RegMembers.UnitName AND
        regAss.VillageCode = RegMembers.VillageName AND
        regAss.RegisterID = RegMembers.RegisterID AND
        regAss.MemberID = RegMembers.MemberID
        INNER JOIN
        Registration ON regAss.CountryCode = Registration.CountryCode AND
        regAss.DistrictCode = Registration.DistrictName AND
        regAss.UpazillaCode = Registration.UpazillaName AND
        regAss.UnitCode = Registration.UnitName AND
        regAss.VillageCode = Registration.VillageName AND
        regAss.RegisterID = Registration.RegistrationID
        WHERE regAss.CountryCode = '0002' AND
        regAss.DonorCode = '01' AND
        regAss.AwardCode = '01' AND
        regAss.ProgramCode = '001' AND
        regAss.ServiceCode = '03' AND
        date(substr(regAss.RegNDate, 7, 4) || '-' || substr(regAss.RegNDate, 1, 2) || '-' || substr(regAss.RegNDate, 4, 2)) <= (
                SELECT
        date(substr(usaEndDate, 7, 4) || '-' || substr(usaEndDate, 1, 2) || '-' || substr(usaEndDate, 4, 2))
        FROM OpMonthTable
        WHERE CountryCode = '0002' AND
                DonorCode = '01' AND
                AwardCode = '01' AND
                OpCode = '2' AND
                OpMonthCode = '15'
        )
        AND
        date(substr(regAss.GRDDate, 7, 4) || '-' || substr(regAss.GRDDate, 1, 2) || '-' || substr(regAss.GRDDate, 4, 2)) >= (
                SELECT
        date(substr(usaStartDate, 7, 4) || '-' || substr(usaStartDate, 1, 2) || '-' || substr(usaStartDate, 4, 2))
        FROM OpMonthTable
        WHERE CountryCode = '0002' AND
                DonorCode = '01' AND
                AwardCode = '01' AND
                OpCode = '2' AND
                OpMonthCode = '15'
        )
        AND
        daydiffernce <= 720 AND
        regAss.DistrictCode || "" || regAss.UpazillaCode || "" || regAss.UnitCode || "" || regAss.VillageCode || "" || regAss.RegisterID || "" || RegMembers.MemberID
        LIKE '%%'
        GROUP BY RegMembers.RegisterID
        ORDER BY RegMembers.RegisterID DESC,
        RegMembers.RegisterID DESC;*/

    }


    public static String getRptMemberServiceList_ca2_sql(String country, String donor, String award, String program, String srvCode, String opCode, String opMonthCode, String memId, String grpCode, String distFlag, String grpLayR1Code, String grpLayR2Code, String grpLayR3Code) {


        return "SELECT  " +
                dbo_Get_dayDifference(country, donor, award, opMonthCode, REG_N_CA2_TABLE, CA2DOB_DATE_COL) +

                ", " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + "," +
                REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HH_HEAD_NAME + "," +
                REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + ", " +
                REGISTRATION_MEMBER_TABLE + "." + MEM_NAME_COL + " AS memName ," +
                REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.SEX_COL + "," +
                REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_AGE + "," +
                " regAss." + ADM_COUNTRY_CODE_COL + "," +
                " regAss." + ADM_DONOR_CODE_COL + "," +
                " regAss." + ADM_AWARD_CODE_COL + "," +
                " regAss." + LAY_R1_LIST_CODE_COL + "," +
                " regAss." + LAY_R2_LIST_CODE_COL + "," +
                " regAss." + LAY_R3_LIST_CODE_COL + "," +
                " regAss." + LAY_R4_LIST_CODE_COL + "," +
                " regAss." + HHID_COL + "," +
                " regAss." + PROG_CODE_COL + "," +
                " regAss." + SRV_CODE_COL + "," +

                "( " + dbo_Get_SrvMemCount(opMonthCode, distFlag, " regAss") + " ) AS SrvRecieved ," +
                " regAss." + LAY_R1_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R2_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R3_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R4_LIST_CODE_COL + " || \"\" || " +
                " regAss." + HHID_COL + " || \"\" || " +
                REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " AS NewID " +
                /** Add new string  id show ..*/

                "FROM " + REG_N_ASSIGN_PROG_SRV_TABLE + " AS regAss" +
                " INNER JOIN " + REGISTRATION_MEMBER_TABLE + " ON "
                + " regAss." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND  regAss." + LAY_R1_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND  regAss." + LAY_R2_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND  regAss." + LAY_R3_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND  regAss." + LAY_R4_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " AND  regAss." + HHID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " AND  regAss." + MEM_ID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                +

                " INNER JOIN " +
                REG_N_HH_TABLE + " ON "
                + " regAss." + ADM_COUNTRY_CODE_COL + " = " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND  regAss." + LAY_R1_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE
                + " AND  regAss." + LAY_R2_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL
                + " AND  regAss." + LAY_R3_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL
                + " AND  regAss." + LAY_R4_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL
                + " AND  regAss." + HHID_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HHID


// for test
                + " INNER JOIN " +
                REG_N_MEM_PROG_GRP_TABLE + " ON "

                + " regAss." + ADM_COUNTRY_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND  regAss." + LAY_R1_LIST_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND  regAss." + LAY_R2_LIST_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND  regAss." + LAY_R3_LIST_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND  regAss." + HHID_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + HHID_COL
                + " AND  regAss." + MEM_ID_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID
                + " AND  regAss." + ADM_DONOR_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND  regAss." + ADM_AWARD_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + ADM_AWARD_CODE_COL
                + " AND  regAss." + PROG_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + PROG_CODE_COL
                + " AND  regAss." + SRV_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + SRV_CODE_COL


                + " WHERE " +
                " regAss." + ADM_COUNTRY_CODE_COL + " = '" + country + "'" +
                "  AND " + " regAss." + ADM_DONOR_CODE_COL + " = '" + donor + "'" +
                "  AND " + " regAss." + ADM_AWARD_CODE_COL + " = '" + award + "'" +
                "  AND " + " regAss." + PROG_CODE_COL + " = '" + program + "'" +
                "  AND " + " regAss." + SRV_CODE_COL + " = '" + srvCode + "'" +

                " AND date(substr(regAss." + REG_N_DAT_COL + ", 1, 4) || '-' || substr(regAss." + REG_N_DAT_COL + ", 6, 2) || '-' || substr(regAss." + REG_N_DAT_COL + ", 9, 2)) <=" + "(" + dbo_Get_OpMonthEndDate(country, donor, award, "2", opMonthCode) + ")" +
                " AND date(substr(regAss." + GRD_DATE_COL + ", 1, 4) || '-' || substr(regAss." + GRD_DATE_COL + ", 6, 2) || '-' || substr(regAss." + GRD_DATE_COL + ", 9, 2)) >=" + "(" + dbo_Get_OpMonthStartDate(country, donor, award, "2", opMonthCode) + ")" +

                /**  id Searching  with 15 digit*/
                " AND "
                + " daydiffernce <= 1800 " +

                " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + GROUP_CODE_COL + " = '" + grpCode + "'" +
                " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R1_LIST_CODE_COL + " = '" + grpLayR1Code + "'" +
                " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R2_LIST_CODE_COL + " = '" + grpLayR2Code + "'" +
                " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R3_LIST_CODE_COL + " = '" + grpLayR3Code + "'" +
                " AND " +
                " regAss." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R2_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R3_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R4_LIST_CODE_COL + " || \"\" || " +
                " regAss." + HHID_COL + " || \"\" || " +
                REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                + " LIKE '%" + memId + "%' " +
                " GROUP BY " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL +

                "  ORDER BY " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " DESC, " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " DESC";


    }


    public static String getRptMemberServiceList_lm_sql(String country, String donor, String award, String program, String srvCode, String opCode, String opMonthCode, String memId, String grpCode, String distFlag, String grpLayR1Code, String grpLayR2Code, String grpLayR3Code) {

        String MorbiTuiLm = "SELECT  " +
                dbo_Get_dayDifference(country, donor, award, opMonthCode, REG_N_LM_TABLE, LM_DATE_COL) +

                ", " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + "," +
                REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HH_HEAD_NAME + "," +
                REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + ", " +
                REGISTRATION_MEMBER_TABLE + "." + MEM_NAME_COL + " AS memName ," +
                REGISTRATION_MEMBER_TABLE + "." + SEX_COL + "," +
                REGISTRATION_MEMBER_TABLE + "." + MEM_AGE + "," +
                " regAss." + ADM_COUNTRY_CODE_COL + "," +
                " regAss." + ADM_DONOR_CODE_COL + "," +
                " regAss." + ADM_AWARD_CODE_COL + "," +
                " regAss." + LAY_R1_LIST_CODE_COL + "," +
                " regAss." + LAY_R2_LIST_CODE_COL + "," +
                " regAss." + LAY_R3_LIST_CODE_COL + "," +
                " regAss." + LAY_R4_LIST_CODE_COL + "," +
                " regAss." + HHID_COL + "," +
                " regAss." + PROG_CODE_COL + "," +
                " regAss." + SRV_CODE_COL + "," +

                "( " + dbo_Get_SrvMemCount(opMonthCode, distFlag, " regAss") + " ) AS SrvRecieved ," +
                " regAss." + LAY_R1_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R2_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R3_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R4_LIST_CODE_COL + " || \"\" || " +
                " regAss." + HHID_COL + " || \"\" || " +
                REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " AS NewID " +
                /** Add new string  id show ..*/

                "FROM " + REG_N_ASSIGN_PROG_SRV_TABLE + " AS regAss" +
                " INNER JOIN " + REGISTRATION_MEMBER_TABLE + " ON "
                + " regAss." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND  regAss." + LAY_R1_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND  regAss." + LAY_R2_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND  regAss." + LAY_R3_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND  regAss." + LAY_R4_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " AND  regAss." + HHID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " AND  regAss." + MEM_ID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                +

                " INNER JOIN " +
                REG_N_HH_TABLE + " ON "
                + " regAss." + ADM_COUNTRY_CODE_COL + " = " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND  regAss." + LAY_R1_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE
                + " AND  regAss." + LAY_R2_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL
                + " AND  regAss." + LAY_R3_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL
                + " AND  regAss." + LAY_R4_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL
                + " AND  regAss." + HHID_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HHID


// for test
                + " INNER JOIN " +
                REG_N_MEM_PROG_GRP_TABLE + " ON "

                + " regAss." + ADM_COUNTRY_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND  regAss." + LAY_R1_LIST_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND  regAss." + LAY_R2_LIST_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND  regAss." + LAY_R3_LIST_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND  regAss." + HHID_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + HHID_COL
                + " AND  regAss." + MEM_ID_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID
                + " AND  regAss." + ADM_DONOR_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND  regAss." + ADM_AWARD_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + ADM_AWARD_CODE_COL
                + " AND  regAss." + PROG_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + PROG_CODE_COL
                + " AND  regAss." + SRV_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + SRV_CODE_COL


                + " WHERE " +
                " regAss." + ADM_COUNTRY_CODE_COL + " = '" + country + "'" +
                "  AND " + " regAss." + ADM_DONOR_CODE_COL + " = '" + donor + "'" +
                "  AND " + " regAss." + ADM_AWARD_CODE_COL + " = '" + award + "'" +
                "  AND " + " regAss." + PROG_CODE_COL + " = '" + program + "'" +
                "  AND " + " regAss." + SRV_CODE_COL + " = '" + srvCode + "'" +

                " AND date(substr(regAss." + REG_N_DAT_COL + ", 1, 4) || '-' || substr(regAss." + REG_N_DAT_COL + ", 6, 2) || '-' || substr(regAss." + REG_N_DAT_COL + ", 9, 2)) <=" + "(" + dbo_Get_OpMonthEndDate(country, donor, award, "2", opMonthCode) + ")" +
                " AND date(substr(regAss." + GRD_DATE_COL + ", 1, 4) || '-' || substr(regAss." + GRD_DATE_COL + ", 6, 2) || '-' || substr(regAss." + GRD_DATE_COL + ", 9, 2)) >=" + "(" + dbo_Get_OpMonthStartDate(country, donor, award, "2", opMonthCode) + ")" +

                /**  id Searching  with 15 digit*/
                " AND "
                + " daydiffernce <= 180 " +

                " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + GROUP_CODE_COL + " = '" + grpCode + "'" +
                " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R1_LIST_CODE_COL + " = '" + grpLayR1Code + "'" +
                " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R2_LIST_CODE_COL + " = '" + grpLayR2Code + "'" +
                " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R3_LIST_CODE_COL + " = '" + grpLayR3Code + "'" +
                " AND " +
                " regAss." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R2_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R3_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R4_LIST_CODE_COL + " || \"\" || " +
                " regAss." + HHID_COL + " || \"\" || " +
                REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                + " LIKE '%" + memId + "%' " +
                " GROUP BY " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL +
                //  " , "+ REGISTRATION_MEMBER_TABLE+"."+HH_MEM_ID+
                "  ORDER BY " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " DESC";
        Log.e("MorSala", MorbiTuiLm);
        return MorbiTuiLm;


    }


    public static String getRptMemberServiceList_pw_sql(String country, String donor, String award, String program, String srvCode, String opCode, String opMonthCode, String memId, String grpCode, String distFlag, String grpLayR1Code, String grpLayR2Code, String grpLayR3Code) {


        return "SELECT  " +
                dbo_Get_dayDifference(country, donor, award, opMonthCode, REG_N_PW_TABLE, LMP_DATE_COL) +

                ", " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + "," +
                REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HH_HEAD_NAME + "," +
                REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + ", " +
                REGISTRATION_MEMBER_TABLE + "." + MEM_NAME_COL + " AS memName ," +
                REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.SEX_COL + "," +
                REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_AGE + "," +
                " regAss." + ADM_COUNTRY_CODE_COL + "," +
                " regAss." + ADM_DONOR_CODE_COL + "," +
                " regAss." + ADM_AWARD_CODE_COL + "," +
                " regAss." + LAY_R1_LIST_CODE_COL + "," +
                " regAss." + LAY_R2_LIST_CODE_COL + "," +
                " regAss." + LAY_R3_LIST_CODE_COL + "," +
                " regAss." + LAY_R4_LIST_CODE_COL + "," +
                " regAss." + HHID_COL + "," +
                " regAss." + PROG_CODE_COL + "," +
                " regAss." + SRV_CODE_COL + "," +

                "( " + dbo_Get_SrvMemCount(opMonthCode, distFlag, " regAss") + " ) AS SrvRecieved ," +
                " regAss." + LAY_R1_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R2_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R3_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R4_LIST_CODE_COL + " || \"\" || " +
                " regAss." + HHID_COL + " || \"\" || " +
                REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " AS NewID " +
                /** Add new string  id show ..*/

                "FROM " + REG_N_ASSIGN_PROG_SRV_TABLE + " AS regAss" +
                " INNER JOIN " + REGISTRATION_MEMBER_TABLE + " ON "
                + " regAss." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND  regAss." + LAY_R1_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND  regAss." + LAY_R2_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND  regAss." + LAY_R3_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND  regAss." + LAY_R4_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " AND  regAss." + HHID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " AND  regAss." + MEM_ID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                +

                " INNER JOIN " +
                REG_N_HH_TABLE + " ON "
                + " regAss." + ADM_COUNTRY_CODE_COL + " = " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND  regAss." + LAY_R1_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE
                + " AND  regAss." + LAY_R2_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL
                + " AND  regAss." + LAY_R3_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL
                + " AND  regAss." + LAY_R4_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL
                + " AND  regAss." + HHID_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HHID


// for test
                + " INNER JOIN " +
                REG_N_MEM_PROG_GRP_TABLE + " ON "

                + " regAss." + ADM_COUNTRY_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND  regAss." + LAY_R1_LIST_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND  regAss." + LAY_R2_LIST_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND  regAss." + LAY_R3_LIST_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND  regAss." + HHID_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + HHID_COL
                + " AND  regAss." + MEM_ID_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID
                + " AND  regAss." + ADM_DONOR_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND  regAss." + ADM_AWARD_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + ADM_AWARD_CODE_COL
                + " AND  regAss." + PROG_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + PROG_CODE_COL
                + " AND  regAss." + SRV_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + SRV_CODE_COL


                + " WHERE " +
                " regAss." + ADM_COUNTRY_CODE_COL + " = '" + country + "'" +
                "  AND " + " regAss." + ADM_DONOR_CODE_COL + " = '" + donor + "'" +
                "  AND " + " regAss." + ADM_AWARD_CODE_COL + " = '" + award + "'" +
                "  AND " + " regAss." + PROG_CODE_COL + " = '" + program + "'" +
                "  AND " + " regAss." + SRV_CODE_COL + " = '" + srvCode + "'" +

                " AND date(substr(regAss." + REG_N_DAT_COL + ", 1, 4) || '-' || substr(regAss." + REG_N_DAT_COL + ", 6, 2) || '-' || substr(regAss." + REG_N_DAT_COL + ", 9, 2)) <=" + "(" + dbo_Get_OpMonthEndDate(country, donor, award, "2", opMonthCode) + ")" +
                " AND date(substr(regAss." + GRD_DATE_COL + ", 1, 4) || '-' || substr(regAss." + GRD_DATE_COL + ", 6, 2) || '-' || substr(regAss." + GRD_DATE_COL + ", 9, 2)) >=" + "(" + dbo_Get_OpMonthStartDate(country, donor, award, "2", opMonthCode) + ")" +

                /**  id Searching  with 15 digit*/
                " AND "
                + " daydiffernce <= 277 " +

                " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + GROUP_CODE_COL + " = '" + grpCode + "'" +
                " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R1_LIST_CODE_COL + " = '" + grpLayR1Code + "'" +
                " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R2_LIST_CODE_COL + " = '" + grpLayR2Code + "'" +
                " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R3_LIST_CODE_COL + " = '" + grpLayR3Code + "'" +
                " AND " +
                " regAss." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R2_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R3_LIST_CODE_COL + " || \"\" || " +
                " regAss." + LAY_R4_LIST_CODE_COL + " || \"\" || " +
                " regAss." + HHID_COL + " || \"\" || " +
                REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                + " LIKE '%" + memId + "%' " +
                " GROUP BY " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL +
                //  " , "+ REGISTRATION_MEMBER_TABLE+"."+HH_MEM_ID+
                "  ORDER BY " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " DESC"; // RegMembers.RegisterID DESC";
        //  " , "+ REGISTRATION_MEMBER_TABLE+"."+HH_MEM_ID+ "  ORDER BY "+ REGISTRATION_MEMBER_TABLE+"."+HHID_COL+" DESC, RegMembers.RegisterID DESC";


    }

    /**
     * this too much wrong
     *
     * @param country     Country Code
     * @param donor       donor Code
     * @param award       award Code
     * @param program     program Code
     * @param srvCode     Service Code
     * @param opCode      operation Code
     * @param opMonthCode operation Month Code
     * @param memId       member Id
     * @param grpCode     Group Code
     * @return Sql String of member list for service
     */


    public static String getRptMemberServiceList_sql(String country, String donor, String award, String program, String srvCode,
                                                     String opCode, String opMonthCode, String memId, String grpCode, String distFlag) {
        String getMemberName;
        if (country.equals("0004")) {
            getMemberName = REGISTRATION_MEMBER_TABLE + "." + MEM_NAME_FIRST_COL +
                    "|| ' ' || " + REGISTRATION_MEMBER_TABLE + "." + MEM_NAME_MIDDLE_COL +
                    "|| ' ' || " + REGISTRATION_MEMBER_TABLE + "." + MEM_NAME_LAST_COL;

        } else
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;


        return "SELECT  " +
                REGISTRATION_MEMBER_TABLE + "." + HHID_COL + "," +
                REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HH_HEAD_NAME + "," +
                REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + ", " +
                getMemberName + " AS memName ," +
                REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.SEX_COL + "," +
                REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_AGE + "," +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + "," +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_DONOR_CODE_COL + "," +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_AWARD_CODE_COL + "," +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R1_LIST_CODE_COL + "," +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + "," +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + "," +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + "," +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL + "," +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + PROG_CODE_COL + "," +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + SRV_CODE_COL + "," +


                "( " + dbo_Get_SrvMemCount(opMonthCode, distFlag, REG_N_ASSIGN_PROG_SRV_TABLE) + " ) AS SrvRecieved ," +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R1_LIST_CODE_COL + " || \"\" || " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " || \"\" || " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " || \"\" || " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + " || \"\" || " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL + " || \"\" || " +
                REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " AS NewID " +
                /** Add new string  id show ..*/

                "FROM " +
                REG_N_ASSIGN_PROG_SRV_TABLE +
                " INNER JOIN " + REGISTRATION_MEMBER_TABLE + " ON " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R1_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + MEM_ID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                +//" AND "+
                    /*
                    REG_N_ASSIGN_PROG_SRV_TABLE+"."+LAY_R4_LIST_CODE_COL+" = "+REGISTRATION_MEMBER_TABLE+"."+LAY_R4_LIST_NAME_COL+" AND "+
                    REG_N_ASSIGN_PROG_SRV_TABLE+"."+HHID_COL+" = "+REGISTRATION_MEMBER_TABLE+"."+HHID_COL+" AND "+
                    REG_N_ASSIGN_PROG_SRV_TABLE+"."+HH_MEM_ID+" = "+REGISTRATION_MEMBER_TABLE+"."+HH_MEM_ID+*/

                " INNER JOIN " +
                REG_N_HH_TABLE + " ON " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R1_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HHID
               /*     REGISTRATION_MEMBER_TABLE+"."+LAY_R4_LIST_NAME_COL+" = "+REG_N_HH_TABLE+"."+LAY_R4_LIST_NAME_COL+" AND "+
                    REGISTRATION_MEMBER_TABLE+"."+HHID_COL+" = "+REG_N_HH_TABLE+"."+PID_COL +*/

             /* for test */ + " INNER JOIN " +
                REG_N_MEM_PROG_GRP_TABLE + " ON " +

                REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R1_LIST_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + HHID_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + MEM_ID_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_DONOR_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_AWARD_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + ADM_AWARD_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + PROG_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + PROG_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + SRV_CODE_COL + " = " + REG_N_MEM_PROG_GRP_TABLE + "." + SRV_CODE_COL
                + " AND " + REG_N_MEM_PROG_GRP_TABLE + "." + GROUP_CODE_COL + " = '" + grpCode + "'"

                + " WHERE " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + country + "'  AND " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donor + "'  AND " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + award + "'  AND " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + PROG_CODE_COL + " = '" + program + "'  AND " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + SRV_CODE_COL + " = '" + srvCode + "' AND " +
                /**  id Searching  with 15 digit*/
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || \"\" || " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " || \"\" || " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " || \"\" || " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + " || \"\" || " +
                REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL + " || \"\" || " +
                REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                + " LIKE '%" + memId + "%' " +
                " GROUP BY " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL +
                //  " , "+ REGISTRATION_MEMBER_TABLE+"."+HH_MEM_ID+
                "  ORDER BY " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " DESC";//, RegMembers.RegisterID DESC";
        //  " , "+ REGISTRATION_MEMBER_TABLE+"."+HH_MEM_ID+ "  ORDER BY "+ REGISTRATION_MEMBER_TABLE+"."+HHID_COL+" DESC, RegMembers.RegisterID DESC";


    }

    public static String getAddressQuery(String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode) {
        return " WHERE "
                + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' AND "
                + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' AND "
                + LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' AND "
                + LAY_R3_LIST_CODE_COL + " = '" + unitCode + "' AND "
                + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "'";

        /**
         * " SELECT "+SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL+" , "+ SQLiteHandler.REGN_ADDRESS_LOOKUP_NAME_COL
         +" FROM "+SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE
         */
    }

    /**
     * To show distribution table
     */

    public static String getDistributionGridShowData(String countryCode, String donorCode, String awardCode, String progCode, String serviceOpMonthCode, String fdpCode, String memberId) {
        String sql = "SELECT "
                + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " AS country , "
                + SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " AS donor , "
                + SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " AS award , "
                + SERVICE_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " AS district , "
                + SERVICE_TABLE + "." + LAY_R2_LIST_CODE_COL + " AS upzella , "
                + SERVICE_TABLE + "." + LAY_R3_LIST_CODE_COL + " AS unite, "
                + SERVICE_TABLE + "." + LAY_R4_LIST_CODE_COL + " AS village, "
                + SERVICE_TABLE + "." + PROG_CODE_COL + " AS program , "
                + SERVICE_TABLE + "." + SRV_CODE_COL + " AS service , "
                + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS srvName , "

                + SERVICE_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || "
                + SERVICE_TABLE + "." + LAY_R2_LIST_CODE_COL + " || "
                + SERVICE_TABLE + "." + LAY_R3_LIST_CODE_COL + " || "
                + SERVICE_TABLE + "." + LAY_R4_LIST_CODE_COL + " || "
                + SERVICE_TABLE + "." + HHID_COL + " AS HHID , "

                + SERVICE_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || "
                + SERVICE_TABLE + "." + LAY_R2_LIST_CODE_COL + " || "
                + SERVICE_TABLE + "." + LAY_R3_LIST_CODE_COL + " || "
                + SERVICE_TABLE + "." + LAY_R4_LIST_CODE_COL + " || "
                + SERVICE_TABLE + "." + HHID_COL + " || "
                + SERVICE_TABLE + "." + MEM_ID_COL + " AS MEMBERID , "
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HH_HEAD_NAME + " AS HhName , "
                + REGISTRATION_MEMBER_TABLE + "." + MEM_NAME_COL + " AS MemName , "
                + SERVICE_TABLE + "." + OP_MONTH_CODE_COL + " , "
                + SERVICE_TABLE + "." + SERVICE_CENTER_CODE_COL + " ,  "
                + SERVICE_TABLE + "." + WORK_DAY_COL + " AS wd  "


                + " FROM " + SERVICE_TABLE + "  INNER JOIN " + COUNTRY_PROGRAM_TABLE + " ON  "
                + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL
                + " AND " + SERVICE_TABLE + "." + PROG_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + SERVICE_TABLE + "." + SRV_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL
                + " INNER JOIN " + REG_N_HH_TABLE
                + " ON " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + SERVICE_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND " + SERVICE_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + SERVICE_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + SERVICE_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + REG_N_HH_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " AND " + SERVICE_TABLE + "." + HHID_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HHID


                + " INNER JOIN " + REGISTRATION_MEMBER_TABLE + " ON "
                + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + SERVICE_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND " + SERVICE_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + SERVICE_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + SERVICE_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " AND " + SERVICE_TABLE + "." + HHID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " AND " + SERVICE_TABLE + "." + MEM_ID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID
                + " INNER JOIN " + SERVICE_MASTER_TABLE
                + " ON " + SERVICE_TABLE + "." + PROG_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + SERVICE_TABLE + "." + SRV_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL

                + " WHERE (" + COUNTRY_PROGRAM_TABLE + "." + FOOD_FLAG + " = '1'" +
                " OR " + COUNTRY_PROGRAM_TABLE + "." + NON_FOOD_FLAG + " = '1'" +
                " OR " + COUNTRY_PROGRAM_TABLE + "." + CASH_FLAG + " = '1'" +
                " OR " + COUNTRY_PROGRAM_TABLE + "." + VOUCHER_FLAG + " = '1'" + " ) "
                + " AND " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SERVICE_TABLE + "." + PROG_CODE_COL + " = '" + progCode + "' "
                + " AND " + SERVICE_TABLE + "." + OP_MONTH_CODE_COL + " = '" + serviceOpMonthCode + "' "

                + " AND ( " + SERVICE_TABLE + "." + DISTRIBUTION_STATUS_COL + " IN ( 'S', 'P' )) "
                + " AND  ( " + SERVICE_TABLE + "." + DIST_DT_COL + " = 'null' "
                + " OR   " + SERVICE_TABLE + "." + DIST_DT_COL + " is null )"

                + " AND (" + SERVICE_TABLE + "." + SERVICE_CENTER_CODE_COL + " IN ( SELECT "
                + SRV_CENTER_TABLE + "." + SERVICE_CENTER_CODE_COL + " FROM "
                + SRV_CENTER_TABLE
                + " WHERE " + SRV_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + SRV_CENTER_TABLE + "." + FDP_CODE_COL + " = '" + fdpCode + "' ))"

                + " AND " + SERVICE_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " ||  " + SERVICE_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " ||  " + SERVICE_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " || " + SERVICE_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " || " + SERVICE_TABLE + "." + HHID_COL
                + " ||  " + SERVICE_TABLE + "." + MEM_ID_COL + " like '%" + memberId + "%' ";


        Log.d("FAISAL", sql);
        return sql;

    }

    public static String getDistributionStatusFromDistributionTableQuery(String countryCode, String donorCode, String awardCode, String districtCode, String upzillaCode, String uniteCode, String villageCode, String programCode, String srviceCode, String distMonthCode, String fdpCode, final String distFlag, String id) {
        String sql = "SELECT CASE WHEN " + SQLiteHandler.DISTRIBUTION_STATUS_COL + " IS NULL THEN '-' ELSE " + SQLiteHandler.DISTRIBUTION_STATUS_COL + " END   AS " + SQLiteHandler.DISTRIBUTION_STATUS_COL
                + " FROM " + DISTRIBUTION_TABLE
                + " WHERE "
                + DISTRIBUTION_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + LAY_R2_LIST_CODE_COL + " = '" + upzillaCode + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + LAY_R3_LIST_CODE_COL + " = '" + uniteCode + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + PROG_CODE_COL + " = '" + programCode + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + SRV_CODE_COL + " = '" + srviceCode + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + OP_MONTH_CODE_COL + " = '" + distMonthCode + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + FDP_CODE_COL + " = '" + fdpCode + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + DIST_FLAG_COL + " = '" + distFlag + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + MEM_ID_15_D_COL + " = '" + id + "' ";
        Log.d("All_1", "sql:" + sql);
        return sql;
    }

    public static String getSingleHouseHoldDataForLiberiaQuery(String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode, String houseHoldId) {
        return " SELECT " +

                REG_DATE_COL + " , " +
                REGISTRATION_TABLE_HH_HEAD_NAME + " , " +
                REGISTRATION_TABLE_HOUSE_HOLD_TYPE_CODE_COL + " , " +                                                  // also retrives House hold catagories string
                LUP_REGNH_HEAD_CATEGORY_TABLE + "." + CATEGORY_NAME_COL + " AS HHType, " +              // also retrives House hold catagories string
                LT2YRS_M_COL + " , " +
                LT2YRS_F_COL + " , " +
                M_2TO5YRS_COL + " , " +
                F_2TO5YRS_COL + " , " +
                M_6TO12YRS_COL + " , " +
                F_6TO12YRS_COL + " , " +
                M_13TO17YRS_COL + " , " +
                F_13TO17YRS_COL + " , " +
                ORPHN_LT18YRS_M_COL + " , " +
                ORPHN_LT18YRS_F_COL + " , " +
                ADLT_18TO59_M_COL + " , " +
                ADLT_18TO59_F_COL + " , " +
                ELD_60P_M_COL + " , " +
                ELD_60P_F_COL + " , " +
                PLW_NO_COL + " , " +
                CHRO_ILL_NO_COL + " , " +

                DECEASED_CONTRACT_EBOLA_COL + " , " +
                EXTRA_CHILD_CAUSE_EBOLA_COL + " , " +
                EXTRA_ELDERLY_CAUSE_EBOLA_COL + " , " +
                EXTRA_CHRONICALLY_ILL_CAUSE_EBOLA_COL + " , " +


                BRF_COUNT_CATTLE_COL + " , " +
                BRF_VALUE_CATTLE_COL + " , " +
                AFT_COUNT_CATTLE_COL + " , " +
                AFT_VALUE_CATTLE_COL + " , " +
                BRF_COUNT_SGOATS_COL + " , " +
                BRF_VALUE_SGOATS_COL + " , " +
                AFT_COUNT_SGOATS_COL + " , " +
                AFT_VALUE_SGOATS_COL + " , " +
                BRF_COUNT_POULTRY_COL + " , " +
                BRF_VALUE_POULTRY_COL + " , " +
                AFT_COUNT_POULTRY_COL + " , " +
                AFT_VALUE_POULTRY_COL + " , " +
                BRF_COUNT_OTHER_COL + " , " +
                BRF_VALUE_OTHER_COL + " , " +
                AFT_COUNT_OTHER_COL + " , " +
                AFT_VALUE_OTHER_COL + " , " +
                BRF_ACRE_CULTIVABLE_COL + " , " +
                BRF_VALUE_CULTIVABLE_COL + " , " +
                AFT_ACRE_CULTIVABLE_COL + " , " +
                AFT_VALUE_CULTIVABLE_COL + " , " +
                BRF_ACRE_NON_CULTIVABLE_COL + " , " +
                BRF_VAL_NON_CULTIVABLE_COL + " , " +
                AFT_ACRE_NON_CULTIVABLE + " , " +
                AFT_VAL_NON_CULTIVABLE + " , " +
                BRF_ACRE_ORCHARDS + " , " +
                BRF_VAL_ORCHARDS + " , " +
                AFT_ACRE_ORCHARDS + " , " +
                AFT_VAL_ORCHARDS + " , " +
                BRF_VAL_CROP + " , " +
                AFT_VAL_CROP + " , " +
                BRF_VAL_LIVESTOCK + " , " +
                AFT_VAL_LIVESTOCK + " , " +
                BRF_VAL_SMALL_BUSINESS + " , " +
                AFT_VAL_SMALL_BUSINESS + " , " +
                BRF_VAL_EMPLOYMENT + " , " +
                AFT_VAL_EMPLOYMENT + " , " +
                BRF_VAL_REMITTANCES + " , " +
                AFT_VAL_REMITTANCES + " , " +
                BRF_CNT_WAGEENR + " , " +
                AFT_CNT_WAGEENR +

//                + ADM_COUNTRY_CODE_COL + " VARCHAR(10), "
//                + LAY_R1_LIST_CODE + " VARCHAR(10), "
//                + REGISTRATION_TABLE_UPZILLA_CODE_COL + " VARCHAR(10), "
//                + REGISTRATION_TABLE_UNION_CODE_COL + " VARCHAR(10), "
//                + REGISTRATION_TABLE_VILLAGE_CODE_COL + " VARCHAR(10), "


                " FROM  " + REG_N_HH_TABLE +
                " LEFT JOIN " + LUP_REGNH_HEAD_CATEGORY_TABLE
                + " ON " + LUP_REGNH_HEAD_CATEGORY_TABLE + "." + CATEGORY_CODE_COL + " = " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HOUSE_HOLD_TYPE_CODE_COL + " " +
                " WHERE " + REG_N_HH_TABLE + "." +
                ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND " + LAY_R1_LIST_CODE + " = '" + districtCode + "' " +
                " AND " + REGISTRATION_TABLE_UPZILLA_CODE_COL + " = '" + upzellaCode + "' " +
                " AND " + REGISTRATION_TABLE_UNION_CODE_COL + " = '" + unitCode + "'" +
                " AND " + REGISTRATION_TABLE_VILLAGE_CODE_COL + " = '" + villageCode + "' " +
                " AND " + REGISTRATION_TABLE_HHID + " = '" + houseHoldId + "' ";
    }

    public static String getVillageNameWHERECondition(String countryCode, String districtCode, String upazillaCode, String unitCode, String villageCode) {
        return " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' AND "
                + LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' AND "
                + LAY_R2_LIST_CODE_COL + " = '" + upazillaCode + "' AND "
                + LAY_R3_LIST_CODE_COL + " = '" + unitCode + "' AND "
                + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "'  ";
    }

    public static String getProgramsNames_WHERE_Condition(String awardCode, String donorCode) {
        return " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + "='" + donorCode + "'";

    }

    /**
     * getDistProgramsNames_WHERE_Condition() is For Distribution table
     */
    public static String getDistProgramsNames_WHERE_Condition(String awardCode, String donorCode, String columnName) {
        return " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + "='" + donorCode + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + columnName + "='" + 1 + "'";

    }

    public static String getServiceMonths_WHERE_Service_Open_Condition(String countryCode) {
        return " WHERE " +
                ADM_COUNTRY_CODE_COL + " = '" + countryCode + "'"
                + " AND " + STATUS + " = '" + "A" + "' "
                + " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '2' ";
    }

    public static String getServiceMonths_WHERE_Service_Close_Condition(String countryCode) {
        return " WHERE " +
                ADM_COUNTRY_CODE_COL + " = '" + countryCode + "'"
                + " AND " + STATUS + " = '" + "C" + "' "
                + " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '2' "
                + " ORDER BY OpMonthID   DESC   LIMIT 1 "
                ;


    }


    public static String getDistributionMonths_WHERE_Condition(String countryCode) {
        return " WHERE " +
                ADM_COUNTRY_CODE_COL + " = '" + countryCode + "'"
                + " AND " + STATUS + " = '" + "A" + "' "
                + " AND " + OPERATION_CODE_COL + " = '3' "
                + " ORDER BY OpMonthID   DESC  LIMIT 1 "
                + "        ";
    }

    public static String getCriteriaNames_WHERE_Condition(String awardCode, String donorCode, String programCode) {
        return " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + "='" + donorCode + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + "='" + programCode + "'";

    }

    public static String getFDPNames_Where_Condition(String idCountry, String laryR2) {
        return " WHERE " + SQLiteHandler.STAFF_FDP_ACCESS_TABLE + "." + STAFF_FDP_ACCESS_COUNTRY_CODE + " = '" + idCountry + "'"
                + " AND " + SQLiteHandler.FDP_MASTER_TABLE + "." + FDP_MASTER_LAY_R1_LIST_CODE_COL + " || " +
                SQLiteHandler.FDP_MASTER_TABLE + "." + FDP_MASTER_LAY_R2_LIST_CODE_COL + " = '" + laryR2 + "' "
                + " AND " + SQLiteHandler.STAFF_FDP_ACCESS_TABLE + "." + SQLiteHandler.BTN_NEW_COL + " = '1' "
                + " AND " + SQLiteHandler.STAFF_FDP_ACCESS_TABLE + "." + SQLiteHandler.BTN_SAVE_COL + " = '1' "
                + " AND " + SQLiteHandler.STAFF_FDP_ACCESS_TABLE + "." + SQLiteHandler.BTN_DEL_COL + " = '1' "
                + "  GROUP BY " + SQLiteHandler.FDP_MASTER_TABLE + "." + SQLiteHandler.FDP_NAME_COL
                + "  ORDER BY " + SQLiteHandler.FDP_MASTER_TABLE + "." + SQLiteHandler.FDP_NAME_COL;
    }

    public static String checkDataExitsQueryInCT_TableAssignForLiberia(String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode, String houseHoldId, String memberID) {
        return "SELECT * FROM " + SQLiteHandler.REG_N_CT_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + LAY_R_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + unitCode + "'"
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                + " AND " + HHID_COL + " = '" + houseHoldId + "' "
                + " AND " + HH_MEM_ID + " = '" + memberID + "' ";

    }
   /* // havet use it

    public static String checkAssignedCriteriaQueryInCT_TableForLiberia(String columnName, String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode, String houseHoldId, String memberID) {
        return "SELECT " + columnName + " FROM " + SQLiteHandler.REG_N_CT_TABLE
                + " WHERE " + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + SQLiteHandler.LAY_R_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unitCode + "'"
                + " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                + " AND " + SQLiteHandler.HHID_COL + " = '" + houseHoldId + "' "
                + " AND " + SQLiteHandler.HH_MEM_ID + " = '" + memberID + "' ";

    }*/


    public static String getLastServiceDateQuery(ServiceDataModel service) {
        return " SELECT " + SERVICE_TABLE_SERVICE_DT_COL
                + " FROM " + SERVICE_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + service.getC_code() + "'"
                + " AND " + ADM_DONOR_CODE_COL + " = '" + service.getDonor_code() + "'"
                + " AND " + ADM_AWARD_CODE_COL + " = '" + service.getAward_code() + "'"
                + " AND " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + service.getDistrictCode() + "'"
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + service.getUpazillaCode() + "'"
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + service.getUnitCode() + "'"
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + service.getVillageCode() + "'"
                + " AND " + HHID_COL + " = '" + service.getHHID() + "'"
                + " AND " + MEM_ID_COL + " = '" + service.getMemberId() + "'"
                + " AND " + PROG_CODE_COL + " = '" + service.getProgram_code() + "'"
                + " AND " + SRV_CODE_COL + " = '" + service.getService_code() + "'"
                + " AND " + OPERATION_CODE_COL + " = '" + service.getOpCode() + "'"
                + " AND " + OP_MONTH_CODE_COL + " = '" + service.getOpMontheCode() + "'" +
                "ORDER BY " + SERVICE_TABLE_SERVICE_SL_COL + " desc limit 1";
    }


    public static String getAssignCriteriaListSelectQuery(String countryCode, String donorCode, String awardCode, String programCode, String districtCode, String upzellaCode, String unitCode, String villageCode) {
        return "SELECT " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS Criteria , "
                + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " || '' || " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " AS IdCriteria ,  "
                + "( SELECT COUNT (*) FROM " + REG_N_ASSIGN_PROG_SRV_TABLE
                + " WHERE "
                + REG_N_ASSIGN_PROG_SRV_TABLE + "." + PROG_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + SRV_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + PROG_CODE_COL + " = '" + programCode + "' "
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " = '" + unitCode + "' "
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' ) AS AssignCount "
                + " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE
                //" JOIN "+SRV_CENTER_TABLE +" ON "+REG_N_ASSIGN_PROG_SRV_TABLE+"."+ADM_COUNTRY_CODE_COL +" = "+ SRV_CENTER_TABLE+"."+ADM_COUNTRY_CODE_COL+
                + " JOIN " + SERVICE_MASTER_TABLE + " ON "
                + REG_N_ASSIGN_PROG_SRV_TABLE + "." + PROG_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + SRV_CODE_COL + " = " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + SRV_CODE_COL
                + " WHERE " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "'"
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " = '" + unitCode + "' "
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "'"
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + PROG_CODE_COL + " = '" + programCode + "'"
                + " GROUP BY " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL;

    }

    public static String doesMemberAssignedInDifferentServiceQuery(RegNAssgProgSrv regData) {
        return "SELECT " + HH_MEM_ID
                + " FROM " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + regData.getCountryCode() + "'"
                + " AND " + LAY_R_LIST_CODE_COL + " = '" + regData.getDistrictCode() + "'"
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + regData.getUpazillaCode() + "'"
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + regData.getUnitCode() + "'"
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + regData.getVillageCode() + "'"
                + " AND " + ADM_DONOR_CODE_COL + " = '" + regData.getDonorCode() + "'"
                + " AND " + ADM_AWARD_CODE_COL + " = '" + regData.getAwardCode() + "'"
                + " AND " + HHID_COL + " = '" + regData.getHhId() + "'"
                + " AND " + HH_MEM_ID + " = '" + regData.getMemberId() + "'"
                + " AND " + ADM_PROG_CODE_COL + " = '" + regData.getProgramCode() + "'"
                + " AND " + ADM_SRV_CODE_COL + " != '" + regData.getServiceCode() + "'"
                + " AND (" + SQLiteHandler.GRD_CODE_COL + " = ( " + getGraduationDefaultActiveReason_Select_Query(regData.getProgramCode(), regData.getServiceCode()) + " ) )";
    }

    public static String getGraduationDefaultActiveReason_Select_Query(String prrogramCode, String serviceCode) {
        return "SELECT " + SQLiteHandler.GRD_CODE_COL + " FROM " + SQLiteHandler.REG_N_LUP_GRADUATION_TABLE +
                " WHERE  " + ADM_PROG_CODE_COL + " = '" + prrogramCode + "'"
                + " AND " + ADM_SRV_CODE_COL + " = '" + serviceCode + "'"
                + " AND " + SQLiteHandler.DEFAULT_CAT_ACTIVE_COL + " = '" + YES + "'  ";
    }

    public static String getMemberDataFrom_RegNAssProgSrv_Query(GraduationGridDataModel member) {
        return "SELECT * FROM " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE +
                " WHERE  " + ADM_COUNTRY_CODE_COL + " = '" + member.getCountryCode() + "' "
                + " AND " + LAY_R_LIST_CODE_COL + " = '" + member.getDistrictCode() + "' "
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + member.getUpazillaCode() + "' "
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + member.getUnitCode() + "' "
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + member.getVillageCode() + "' "
                + " AND " + HHID_COL + " = '" + member.getHh_id() + "' "
                + " AND " + HH_MEM_ID + " = '" + member.getMember_Id() + "' "
                + " AND " + ADM_DONOR_CODE_COL + " = '" + member.getDonor_code() + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + member.getAward_code() + "' "
                + " AND " + ADM_PROG_CODE_COL + " = '" + member.getProgram_code() + "' "
                + " AND " + ADM_SRV_CODE_COL + " = '" + member.getService_code() + "'  ";
    }

    /**
     * AGR Table Check
     */
   /* public static String checkDataExitsQueryInAGR_TableAssignForMalwai(String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode, String houseHoldId, String memberID) {
        return "SELECT * FROM " + SQLiteHandler.REG_N_AGR_TABLE
                + " WHERE " + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + SQLiteHandler.LAY_R_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unitCode + "'"
                + " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                + " AND " + SQLiteHandler.HHID_COL + " = '" + houseHoldId + "' "
                + " AND " + SQLiteHandler.HH_MEM_ID + " = '" + memberID + "' ";
    }*/
    public static String checkDataExitsQueryInRegN_ARG_TableSQL(String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode, String houseHoldId, String memberID) {
        return "SELECT * FROM " + SQLiteHandler.REG_N_AGR_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + unitCode + "'"
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                + " AND " + HHID_COL + " = '" + houseHoldId + "' "
                + " AND " + MEM_ID_COL + " = '" + memberID + "' ";
    }


    public static String checkDataExitsQueryInRegN_FFA_TableSQL(String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode, String houseHoldId, String memberID) {
        return "SELECT * FROM " + SQLiteHandler.REG_N_FFA_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + unitCode + "'"
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                + " AND " + HHID_COL + " = '" + houseHoldId + "' "
                + " AND " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + memberID + "' ";
    }

    public static String checkDataExitsQueryInRegN__TableSQL(String tableName, String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode, String houseHoldId, String memberID) {

        if (tableName.equals(REG_N_AGR_TABLE)) {
            return "SELECT * FROM " + tableName
                    + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' "
                    + " AND " + LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                    + " AND " + LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                    + " AND " + LAY_R3_LIST_CODE_COL + " = '" + unitCode + "'"
                    + " AND " + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                    + " AND " + HHID_COL + " = '" + houseHoldId + "' "
                    + " AND " + MEM_ID_COL + " = '" + memberID + "' ";
        } else {
            return "SELECT * FROM " + tableName
                    + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' "
                    + " AND " + LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                    + " AND " + LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                    + " AND " + LAY_R3_LIST_CODE_COL + " = '" + unitCode + "'"
                    + " AND " + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                    + " AND " + HHID_COL + " = '" + houseHoldId + "' "
                    + " AND " + MEM_ID_COL + " = '" + memberID + "' ";
        }

    }
// havet use it


    public static String getAssignDataIfExitsInRegNFFA_table_sql(String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode, String houseHoldId, String memberID) {
        return " SELECT " + SQLiteHandler.CHILD_HEADED_COL
                + " , " + SQLiteHandler.ELDERLY_HEADED_COL
                + " , " + SQLiteHandler.CHRONICALLY_ILL_COL
                + " , " + SQLiteHandler.FEMALE_HEADED_COL
                + " , " + SQLiteHandler.CROP_FAILURE_COL
                + " , " + SQLiteHandler.CHILDREN_REC_SUPP_FEED_N_COL
                + " , " + SQLiteHandler.WILLINGNESS_COL + "  "

                + " FROM " + SQLiteHandler.REG_N_FFA_TABLE

                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + unitCode + "'"
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                + " AND " + HHID_COL + " = '" + houseHoldId + "' "
                + " AND " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + memberID + "' ";
    }

    /**
     * AGR Table Check
     */
    public static String checkDataExitsQueryInAGR_TableAssignForMalwai(String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode, String houseHoldId, String memberID, boolean impelmetedinMain) {
        return " SELECT " + ELDERLY_YN_COL + " , "
                + LAND_SIZE_COL + " , "
                + DEPEND_ON_GANYU_COL + " , "
                + WILLINGNESS_COL + " , "
                + WINTER_CULTIVATION_COL + " , "
                + VULNERABLE_HH_COL + " , "
                + PLANTING_VALUE_CHAIN_CROP_COL + " , "
                + REG_N_DAT_COL + " , "
                + LUP_SRV_OPTION_LIST_TABLE + "." + LUP_OPTION_NAME_COL + " AS vcCropStr ,"
                + AG_INVC_COL + " , "
                + AG_NASFAM_COL + " , "
                + AG_CU_COL + ", "
                + AG_OTHER_COL + " , "
                + AG_L_S_GOAT_COL + " , "
                + AG_L_S_CHICKEN_COL + " , "
                + AG_L_S_PIGION_COL + " , "
                + AG_L_S_OTHER_COL + "  "

                + " FROM " + REG_N_AGR_TABLE
                + " LEFT JOIN " + LUP_SRV_OPTION_LIST_TABLE
                + " ON " + REG_N_AGR_TABLE + " . " + ADM_COUNTRY_CODE_COL + " = " + LUP_SRV_OPTION_LIST_TABLE + " . " + ADM_COUNTRY_CODE_COL +
                " AND " + PLANTING_VALUE_CHAIN_CROP_COL + " = " + LUP_OPTION_CODE_COL
                + " WHERE " + REG_N_AGR_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + unitCode + "'"
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                + " AND " + HHID_COL + " = '" + houseHoldId + "' "
                + " AND " + MEM_ID_COL + " = '" + memberID + "' ";


    }

    public static String get_RegNAssProgSrvRegistrationDateRangeSelectQuery(String cCode) {
        return "SELECT " + USA_START_DATE_COL + " , " + USA_END_DATE_COL + " FROM " + OP_MONTH_TABLE +
                " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + OPERATION_CODE_COL + " = '" + REGISTRATION_OP_CODE + "' " +
                " AND " + STATUS + " = '" + ACTIVE + "' ";

    }

/*    public static String getTotalListOfMemberRAssignedSummary_sql(String countryCode, String districtCode, String upzellaCode, String unionCode, String villageCode, String donorCode, String awardCord, String programCode, String srvCode) {


        String getMemberName;
        if (countryCode.equals("0004")) {
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;
        return "SELECT " + "rgAss." + LAY_R_LIST_CODE_COL
                + " || '' || " + "rgAss." + LAY_R2_LIST_CODE_COL
                + " || '' || " + "rgAss." + LAY_R3_LIST_CODE_COL
                + " || '' || " + "rgAss." + LAY_R4_LIST_CODE_COL
                + " || '' || " + "rgAss." + HHID_COL
                + " || '' || " + "rgAss." + HH_MEM_ID + " AS NewID "
                + ", " + getMemberName + "  AS memberName"
                + " , " + "rgAss." + REG_N_DAT_COL + " AS regDate "
                // + " || '' || " + "memGrp." + GROUP_CODE_COL
                + " , memGrp." + GROUP_CODE_COL
                + " , CASE WHEN cg." + GROUP_NAME_COL+" IS NULL  THEN '' ELSE cg." + GROUP_NAME_COL+ " END AS grpName "
                + " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE + " AS rgAss "
                + " INNER JOIN " + REGISTRATION_MEMBER_TABLE
                + " ON " + "rgAss." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " AND " + "rgAss." + LAY_R_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R_LIST_NAME_COL +
                " AND " + "rgAss." + LAY_R2_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_NAME_COL +
                " AND " + "rgAss." + LAY_R3_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_NAME +
                " AND " + "rgAss." + LAY_R4_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_NAME_COL +
                " AND " + "rgAss." + HHID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL +
                " AND " + "rgAss." + HH_MEM_ID + " = " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " " +
                      //   " JOIN "+SERVICE_MASTER_TABLE+" ON "+REG_N_ASSIGN_PROG_SRV_TABLE+"."+ADM_PROG_CODE_COL+" = "+SERVICE_MASTER_TABLE+"."+ADM_PROG_CODE_COL+
                 //" AND " +REG_N_ASSIGN_PROG_SRV_TABLE+"."+ADM_SRV_CODE_COL+" = "+SERVICE_MASTER_TABLE+"."+ADM_SRV_CODE_COL+

                " LEFT JOIN " + REG_N_MEM_PROG_GRP_TABLE + " AS memGrp "
                + " ON  memGrp." + ADM_COUNTRY_CODE_COL + " = " + " rgAss." + ADM_COUNTRY_CODE_COL
                + " AND memGrp." + LAY_R_LIST_CODE_COL + " = " + " rgAss." + LAY_R_LIST_CODE_COL
                + " AND memGrp." + LAY_R2_LIST_CODE_COL + " = " + " rgAss." + LAY_R2_LIST_CODE_COL
                + " AND memGrp." + LAY_R3_LIST_CODE_COL + " = " + " rgAss." + LAY_R3_LIST_CODE_COL
                + " AND memGrp." + LAY_R4_LIST_CODE_COL + " = " + " rgAss." + LAY_R4_LIST_CODE_COL
                + " AND memGrp." + ADM_DONOR_CODE_COL + " = " + " rgAss." + ADM_DONOR_CODE_COL
                + " AND memGrp." + ADM_AWARD_CODE_COL + " = " + " rgAss." + ADM_AWARD_CODE_COL
                + " AND memGrp." + HHID_COL + " = " + " rgAss." + HHID_COL
                + " AND memGrp." + HH_MEM_ID + " = " + " rgAss." + HH_MEM_ID
                + " AND memGrp." + ADM_PROG_CODE_COL + " = " + " rgAss." + ADM_PROG_CODE_COL
                + " AND memGrp." + ADM_SRV_CODE_COL + " = " + " rgAss." + ADM_SRV_CODE_COL +

                " LEFT JOIN " + COMMUNITY_GROUP_TABLE + " AS cg "
                + " ON  cg." + ADM_COUNTRY_CODE_COL + " = " + " rgAss." + ADM_COUNTRY_CODE_COL
                + " AND cg." + ADM_AWARD_CODE_COL + " = " + " rgAss." + ADM_AWARD_CODE_COL
                + " AND cg." + ADM_DONOR_CODE_COL + " = " + " rgAss." + ADM_DONOR_CODE_COL
                + " AND cg." + ADM_PROG_CODE_COL + " = " + " rgAss." + ADM_PROG_CODE_COL
                + " AND cg." + GROUP_CODE_COL + " = " + " memGrp." + GROUP_CODE_COL+

            //    + " AND cg." + LAY_R1_CODE_COL + " = " + " memGrp." + LAY_R1_CODE_COL
              //  + " AND cg." + GRP_LAY_R2_LIST_CODE_COL + " = " + " memGrp." + GRP_LAY_R2_LIST_CODE_COL
            //    + " AND cg." + GRP_LAY_R3_LIST_CODE_COL + " = " + " memGrp." + GRP_LAY_R3_LIST_CODE_COL +


                " WHERE " + "rgAss." + ADM_COUNTRY_CODE_COL + "= '" + countryCode + "'" +
                " AND " + "rgAss." + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + "rgAss." + ADM_AWARD_CODE_COL + " = '" + awardCord + "'" +
                " AND " + "rgAss." + ADM_PROG_CODE_COL + " = '" + programCode + "'" +
                " AND " + "rgAss." + ADM_SRV_CODE_COL + " = '" + srvCode + "'" +
                " AND " + "rgAss." + LAY_R_LIST_CODE_COL + " = '" + districtCode + "'" +
                " AND " + "rgAss." + LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "'" +
                " AND " + "rgAss." + LAY_R3_LIST_CODE_COL + " = '" + unionCode + "'" +
                " AND " + "rgAss." + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "'" +


                //  +
                " GROUP BY NewID" +//+ REG_N_ASSIGN_PROG_SRV_TABLE+"."+HHID_COL+
                " ORDER BY " + "rgAss." + HHID_COL +
                " , " + "rgAss." + HH_MEM_ID + "  ASC ";

        *//*return "SELECT " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R_LIST_CODE_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL
                + " || '' || " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HH_MEM_ID + " AS NewID "
                + ", " + getMemberName + "  AS memberName"
                + " , " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + REG_N_DAT_COL + " AS regDate"
                + " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE + " JOIN " + SQLiteHandler.REGISTRATION_MEMBER_TABLE
                + " ON " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R_LIST_NAME_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_NAME_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_NAME +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_NAME_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HH_MEM_ID + " = " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " " +
                      //   " JOIN "+SERVICE_MASTER_TABLE+" ON "+REG_N_ASSIGN_PROG_SRV_TABLE+"."+ADM_PROG_CODE_COL+" = "+SERVICE_MASTER_TABLE+"."+ADM_PROG_CODE_COL+
                 //" AND " +REG_N_ASSIGN_PROG_SRV_TABLE+"."+ADM_SRV_CODE_COL+" = "+SERVICE_MASTER_TABLE+"."+ADM_SRV_CODE_COL+
                " WHERE " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_COUNTRY_CODE_COL + "= '" + countryCode + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCord + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_PROG_CODE_COL + " = '" + programCode + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + ADM_SRV_CODE_COL + " = '" + srvCode + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R_LIST_CODE_COL + " = '" + districtCode + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R3_LIST_CODE_COL + " = '" + unionCode + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "'" +
                //  +
                " GROUP BY NewID" +//+ REG_N_ASSIGN_PROG_SRV_TABLE+"."+HHID_COL+
                " ORDER BY " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HHID_COL +
                " , " + REG_N_ASSIGN_PROG_SRV_TABLE + "." + HH_MEM_ID + "  ASC ";*//*


    }*/

    public static String getTotalListOfMemberRAssignedSummary_sql(String countryCode, String districtCode, String upzellaCode, String unionCode, String villageCode, String donorCode, String awardCord, String programCode, String srvCode) {


        String getMemberName;
        if (countryCode.equals("0004")) {
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;


        String sql = "SELECT " + "rgAss." + LAY_R1_LIST_CODE_COL
                + " || '' || " + "rgAss." + LAY_R2_LIST_CODE_COL
                + " || '' || " + "rgAss." + LAY_R3_LIST_CODE_COL
                + " || '' || " + "rgAss." + LAY_R4_LIST_CODE_COL
                + " || '' || " + "rgAss." + HHID_COL
                + " || '' || " + "rgAss." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " AS NewID "
                + ", " + getMemberName + "  AS memberName"
                + " , " + "rgAss." + REG_N_DAT_COL + " AS regDate "
                // + " || '' || " + "memGrp." + GROUP_CODE_COL
                + " , memGrp." + GROUP_CODE_COL
                + " , CASE WHEN cg." + GROUP_NAME_COL + " IS NULL  THEN '' ELSE cg." + GROUP_NAME_COL + " END AS grpName "
                + " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE + " AS rgAss "
                + " INNER JOIN " + REGISTRATION_MEMBER_TABLE
                + " ON " + "rgAss." + ADM_COUNTRY_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " AND " + "rgAss." + LAY_R1_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL +
                " AND " + "rgAss." + LAY_R2_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL +
                " AND " + "rgAss." + LAY_R3_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL +
                " AND " + "rgAss." + LAY_R4_LIST_CODE_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL +
                " AND " + "rgAss." + HHID_COL + " = " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL +
                " AND " + "rgAss." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " " +
                      /*   " JOIN "+SERVICE_MASTER_TABLE+" ON "+REG_N_ASSIGN_PROG_SRV_TABLE+"."+ADM_PROG_CODE_COL+" = "+SERVICE_MASTER_TABLE+"."+ADM_PROG_CODE_COL+
                 " AND " +REG_N_ASSIGN_PROG_SRV_TABLE+"."+ADM_SRV_CODE_COL+" = "+SERVICE_MASTER_TABLE+"."+ADM_SRV_CODE_COL+*/

                " LEFT JOIN " + REG_N_MEM_PROG_GRP_TABLE + " AS memGrp "
                + " ON  memGrp." + ADM_COUNTRY_CODE_COL + " = " + " rgAss." + ADM_COUNTRY_CODE_COL
                + " AND memGrp." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = " + " rgAss." + LAY_R1_LIST_CODE_COL
                + " AND memGrp." + LAY_R2_LIST_CODE_COL + " = " + " rgAss." + LAY_R2_LIST_CODE_COL
                + " AND memGrp." + LAY_R3_LIST_CODE_COL + " = " + " rgAss." + LAY_R3_LIST_CODE_COL
                + " AND memGrp." + LAY_R4_LIST_CODE_COL + " = " + " rgAss." + LAY_R4_LIST_CODE_COL
                + " AND memGrp." + ADM_DONOR_CODE_COL + " = " + " rgAss." + ADM_DONOR_CODE_COL
                + " AND memGrp." + ADM_AWARD_CODE_COL + " = " + " rgAss." + ADM_AWARD_CODE_COL
                + " AND memGrp." + HHID_COL + " = " + " rgAss." + HHID_COL
                + " AND memGrp." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = " + " rgAss." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID
                + " AND memGrp." + PROG_CODE_COL + " = " + " rgAss." + PROG_CODE_COL
                + " AND memGrp." + SRV_CODE_COL + " = " + " rgAss." + SRV_CODE_COL +

                " LEFT JOIN " + COMMUNITY_GROUP_TABLE + " AS cg "
                + " ON  cg." + ADM_COUNTRY_CODE_COL + " = " + " rgAss." + ADM_COUNTRY_CODE_COL
                + " AND cg." + ADM_AWARD_CODE_COL + " = " + " rgAss." + ADM_AWARD_CODE_COL
                + " AND cg." + ADM_DONOR_CODE_COL + " = " + " rgAss." + ADM_DONOR_CODE_COL
                + " AND cg." + ADM_PROG_CODE_COL + " = " + " rgAss." + PROG_CODE_COL
                + " AND cg." + GROUP_CODE_COL + " = " + " memGrp." + GROUP_CODE_COL +
                " AND cg." + LAY_R1_CODE_COL + " = " + " memGrp." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R1_LIST_CODE_COL
                + " AND cg." + GRP_LAY_R2_LIST_CODE_COL + " = " + " memGrp." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R2_LIST_CODE_COL
                + " AND cg." + GRP_LAY_R3_LIST_CODE_COL + " = " + " memGrp." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R3_LIST_CODE_COL +

                " WHERE " + "rgAss." + ADM_COUNTRY_CODE_COL + "= '" + countryCode + "'" +
                " AND " + "rgAss." + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + "rgAss." + ADM_AWARD_CODE_COL + " = '" + awardCord + "'" +
                " AND " + "rgAss." + PROG_CODE_COL + " = '" + programCode + "'" +
                " AND " + "rgAss." + SRV_CODE_COL + " = '" + srvCode + "'" +
                " AND " + "rgAss." + LAY_R1_LIST_CODE_COL + " = '" + districtCode + "'" +
                " AND " + "rgAss." + LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "'" +
                " AND " + "rgAss." + LAY_R3_LIST_CODE_COL + " = '" + unionCode + "'" +
                " AND " + "rgAss." + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "'" +


                //  +
                " GROUP BY NewID" +//+ REG_N_ASSIGN_PROG_SRV_TABLE+"."+HHID_COL+
                " ORDER BY " + "rgAss." + HHID_COL +
                " , " + "rgAss." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + "  ASC ";

        //  Log.e("ShuvoTest",hello);

        return sql;


    }

    public static String get_ProgramMultipleSrv_SelectQuery(String donorCode, String awardCode, String programCode) {

        return "SELECT " + MULTIPLE_SERVICE_FLAG_COL + " FROM " + ADM_PROGRAM_MASTER_TABLE
                + " WHERE " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + programCode + "' ";
    }

    public static String get_SrvCriteriaList_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String programCode, String distFlag) {
        return " SELECT " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS Criteria , " +
                SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " || '' || " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " AS IdCriteria ,  " +
                "( SELECT COUNT (*) FROM " + SERVICE_TABLE +
                " WHERE " + SERVICE_TABLE + "." + PROG_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL +
                " AND " + SERVICE_TABLE + "." + SRV_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL
                + " AND " + SERVICE_TABLE + "." + OP_MONTH_CODE_COL + " = " + OP_MONTH_TABLE + "." + OP_MONTH_CODE_COL
                + " AND " + SERVICE_TABLE + "." + SRV_STATUS_COL + " = 'O'"

                + " ) AS Count " +

                " FROM " + SERVICE_TABLE + " JOIN " + SRV_CENTER_TABLE
                + " ON " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SRV_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL +

                " JOIN " + OP_MONTH_TABLE + " ON " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + SERVICE_TABLE + "." + OPERATION_CODE_COL + " = " + OP_MONTH_TABLE + "." + OPERATION_CODE_COL
                + " AND " + SERVICE_TABLE + "." + OP_MONTH_CODE_COL + " = " + OP_MONTH_TABLE + "." + OP_MONTH_CODE_COL
                + " AND " + SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_AWARD_CODE_COL + " " +

                " JOIN " + SERVICE_MASTER_TABLE + " ON " + SERVICE_TABLE + "." + PROG_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " AND " +

                SERVICE_TABLE + "." + SRV_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL +

                " WHERE  " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCord + "'"
                + " AND " + SERVICE_TABLE + "." + OP_MONTH_CODE_COL + " = '" + opMCode + "'  "
                + " AND " + SERVICE_TABLE + "." + PROG_CODE_COL + " = '" + programCode + "' "
                + " AND " + SERVICE_TABLE + "." + DIST_FLAG_COL + " = '" + distFlag + "' "
                + " GROUP BY " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL;
    }


    public static String get_DistCriteriaList_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String programCode, String distFlag) {


        return " SELECT " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS Criteria , " +
                SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " || '' || " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " AS IdCriteria"


                + " ,  ( SELECT COUNT (*) FROM  " + SERVICE_TABLE
                + " WHERE " + SERVICE_TABLE + "." + PROG_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + SERVICE_TABLE + "." + SRV_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL

                + " AND " + SERVICE_TABLE + "." + SRV_STATUS_COL + " = 'C' "
                + " AND " + SERVICE_TABLE + "." + DISTRIBUTION_STATUS_COL + " IN ('S','P') ) AS plan "


                + " ,  ( SELECT COUNT (*) FROM " + DISTRIBUTION_TABLE
                + " WHERE " + DISTRIBUTION_TABLE + "." + PROG_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + DISTRIBUTION_TABLE + "." + SRV_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL
                + " AND " + DISTRIBUTION_TABLE + "." + OP_MONTH_CODE_COL + " = " + OP_MONTH_TABLE + "." + OP_MONTH_CODE_COL
                + " AND " + DISTRIBUTION_TABLE + "." + DISTRIBUTION_STATUS_COL + " = 'R'"

                + " ) AS receive " +

                " FROM " + DISTRIBUTION_TABLE

                + " JOIN " + OP_MONTH_TABLE
                + " ON " + DISTRIBUTION_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + DISTRIBUTION_TABLE + "." + ADM_DONOR_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + DISTRIBUTION_TABLE + "." + ADM_AWARD_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_AWARD_CODE_COL + " "
                + " AND " + OP_MONTH_TABLE + "." + OPERATION_CODE_COL + " = '3' "
                + " AND " + DISTRIBUTION_TABLE + "." + OP_MONTH_CODE_COL + " = " + OP_MONTH_TABLE + "." + OP_MONTH_CODE_COL

                + " JOIN " + SERVICE_MASTER_TABLE
                + " ON " + DISTRIBUTION_TABLE + "." + PROG_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + DISTRIBUTION_TABLE + "." + SRV_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL +

                " WHERE  " + DISTRIBUTION_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + DISTRIBUTION_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + DISTRIBUTION_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCord + "'" +
                " AND " + DISTRIBUTION_TABLE + "." + OP_MONTH_CODE_COL + " = '" + opMCode + "'  " +
                " AND " + DISTRIBUTION_TABLE + "." + PROG_CODE_COL + " = '" + programCode + "'  " +
                " AND " + DISTRIBUTION_TABLE + "." + DIST_FLAG_COL + " = '" + distFlag + "'  " +
                " GROUP BY " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL;
    }


    public static String getSrvExtendedItemSummaryList_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String programCode) {


        return " SELECT " + VOUCHER_ITEM_TABLE + "." + ITEM_NAME_COL

                + " || '-' || " + VOUCHER_ITEM__MEAS_TABLE + "." + UNITE_MEAS_COL
                + " ||' '||  " + VOUCHER_ITEM__MEAS_TABLE + "." + MEASE_TITLE_COL + " AS item , "
                + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " AS voucherID ,  " +
                " sum( " + SERVICE_EXTENDED_TABLE + "." + VOUCHER_UNIT_COL + ") AS unitCount " +
                " FROM " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE +
                "  INNER JOIN " + VOUCHER_ITEM_TABLE
                + " ON " + VOUCHER_ITEM_TABLE + "." + VOUCHER_ITEM_CATEGORY_CODE_COL + " || " + VOUCHER_ITEM_TABLE + "." + ITEM_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",0,8) " +
                "  INNER JOIN " + VOUCHER_ITEM__MEAS_TABLE
                + " ON " + VOUCHER_ITEM__MEAS_TABLE + "." + MEAS_R_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",8) " +
                "  INNER JOIN " + SERVICE_EXTENDED_TABLE
                + " ON " + SERVICE_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + ADM_DONOR_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + ADM_AWARD_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_AWARD_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + PROG_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + SRV_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_SRV_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL


                + " WHERE  " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCord + "'"
                + " AND " + SERVICE_EXTENDED_TABLE + "." + OP_MONTH_CODE_COL + " = '" + opMCode + "'  "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_PROG_CODE_COL + " = '" + programCode + "'  "
                + " GROUP BY " + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL;

    }

    public static String getDistExtendedItemSummaryList_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String programCode) {
        return " SELECT " + VOUCHER_ITEM_TABLE + "." + ITEM_NAME_COL

                + " || '-' || " + VOUCHER_ITEM__MEAS_TABLE + "." + UNITE_MEAS_COL
                + " ||' '||  " + VOUCHER_ITEM__MEAS_TABLE + "." + MEASE_TITLE_COL + " AS item , "
                + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " AS voucherID ,  " +
                " sum( " + DISTRIBUTION_EXTENDED_TABLE + "." + VOUCHER_UNIT_COL + ") AS unitCount " +
                " FROM " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE +
                "  INNER JOIN " + VOUCHER_ITEM_TABLE
                + " ON " + VOUCHER_ITEM_TABLE + "." + VOUCHER_ITEM_CATEGORY_CODE_COL + " || " + VOUCHER_ITEM_TABLE + "." + ITEM_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",0,8) " +
                "  INNER JOIN " + VOUCHER_ITEM__MEAS_TABLE
                + " ON " + VOUCHER_ITEM__MEAS_TABLE + "." + MEAS_R_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",8) " +
                "  INNER JOIN " + DISTRIBUTION_EXTENDED_TABLE
                + " ON " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_DONOR_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_AWARD_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_AWARD_CODE_COL
                + " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + PROG_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + SRV_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_SRV_CODE_COL
                + " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL


                + " WHERE  " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCord + "'"
                + " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + OP_MONTH_CODE_COL + " = '" + opMCode + "'  "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_PROG_CODE_COL + " = '" + programCode + "'  "
                + " GROUP BY "
                + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_DONOR_CODE_COL
                + " , " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_AWARD_CODE_COL
                + " , " + DISTRIBUTION_EXTENDED_TABLE + "." + PROG_CODE_COL
                + " , " + DISTRIBUTION_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL;


    }

    public static String getTotalServiceAttendanceSummary_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String prgCode, String srvCode, String distFlag) {
        String getMemName;
        if (cCode.equals("0004")) {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;

        }


        return " SELECT " + SERVICE_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || '' || " +
                SERVICE_TABLE + "." + LAY_R2_LIST_CODE_COL + " || '' || " +
                SERVICE_TABLE + "." + LAY_R3_LIST_CODE_COL + " || '' || " +
                SERVICE_TABLE + "." + LAY_R4_LIST_CODE_COL + " || '' || " +
                SERVICE_TABLE + "." + HHID_COL + " || '' || " +
                SERVICE_TABLE + "." + MEM_ID_COL + " AS NewID , " +
                /** HERE COUNT THE SERVICE */


                SERVICE_TABLE + "." + SERVICE_TABLE_SERVICE_SL_COL
                + " , " + getMemName + " AS memberName "
                + " , " + SERVICE_TABLE + "." + SRV_STATUS_COL + " AS status " +

                " FROM " + SERVICE_TABLE +
                " JOIN " + SRV_CENTER_TABLE + " ON " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SRV_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL +

                " JOIN " + OP_MONTH_TABLE + " ON " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " AND " + SERVICE_TABLE + "." + OPERATION_CODE_COL + " = " + OP_MONTH_TABLE + "." + OPERATION_CODE_COL +
                " AND " + SERVICE_TABLE + "." + OP_MONTH_CODE_COL + " = " + OP_MONTH_TABLE + "." + OP_MONTH_CODE_COL +
                " AND " + SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_DONOR_CODE_COL +
                " AND " + SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_AWARD_CODE_COL + "  " +
                " JOIN " + SERVICE_MASTER_TABLE
                + " ON " + SERVICE_TABLE + "." + PROG_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL +
                " AND " + SERVICE_TABLE + "." + SRV_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + "  " +
                " INNER JOIN " + REGISTRATION_MEMBER_TABLE +
                " ON " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL + " = " + SERVICE_TABLE + "." + LAY_R1_LIST_CODE_COL +
                " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + SERVICE_TABLE + "." + LAY_R2_LIST_CODE_COL +
                " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + SERVICE_TABLE + "." + LAY_R3_LIST_CODE_COL +
                " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + SERVICE_TABLE + "." + LAY_R4_LIST_CODE_COL +


                " AND " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL + " = " + SERVICE_TABLE + "." + HHID_COL +

                " AND " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " = " + SERVICE_TABLE + "." + MEM_ID_COL +

                " WHERE " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCord + "'" +
                " AND " + SERVICE_TABLE + "." + PROG_CODE_COL + " = '" + prgCode + "'" +
                " AND " + SERVICE_TABLE + "." + SRV_CODE_COL + " = '" + srvCode + "'" +
                " AND " + SERVICE_TABLE + "." + OP_MONTH_CODE_COL + " = '" + opMCode + "'  " +
                " AND " + SERVICE_TABLE + "." + DIST_FLAG_COL + " = '" + distFlag + "'  " +
                " AND " + SERVICE_TABLE + "." + SRV_STATUS_COL + " = 'O'  " +
                " GROUP BY " + SERVICE_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " , " +
                SERVICE_TABLE + "." + LAY_R2_LIST_CODE_COL + " , " +
                SERVICE_TABLE + "." + LAY_R3_LIST_CODE_COL + " , " +
                SERVICE_TABLE + "." + LAY_R4_LIST_CODE_COL + " , " +
                SERVICE_TABLE + "." + HHID_COL + " , " +
                SERVICE_TABLE + "." + MEM_ID_COL + " , " +
                SERVICE_TABLE + "." + SERVICE_TABLE_SERVICE_SL_COL + "  ";

    }

    public static String getTotalDistributionAttendanceSummary_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String prgCode, String srvCode, String distFlag) {
        String getMemName;
        if (cCode.equals("0004")) {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else {
            getMemName = REGISTRATION_MEMBER_TABLE + "." + MEM_NAME_COL;

        }
/**
 * todo: check status
 */

        return " SELECT " + DISTRIBUTION_TABLE + "." + MEM_ID_15_D_COL + " AS NewID "
                + " , " + DISTRIBUTION_TABLE + "." + DISTRIBUTION_STATUS_COL + " AS status "
                + " , CASE   WHEN length(" + DISTRIBUTION_TABLE + "." + MEM_ID_15_D_COL + ") >=15 "
                + " THEN " + getMemName
                + " ELSE " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HH_HEAD_NAME + " END AS rptName "

                + " FROM " + DISTRIBUTION_TABLE
                + " JOIN " + OP_MONTH_TABLE
                + " ON " + DISTRIBUTION_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + OP_MONTH_TABLE + "." + OPERATION_CODE_COL + " = '3' "
                + " AND " + DISTRIBUTION_TABLE + "." + OP_MONTH_CODE_COL + " = " + OP_MONTH_TABLE + "." + OP_MONTH_CODE_COL
                + " AND " + DISTRIBUTION_TABLE + "." + ADM_DONOR_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + DISTRIBUTION_TABLE + "." + ADM_AWARD_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_AWARD_CODE_COL
                + " LEFT JOIN " + REGISTRATION_MEMBER_TABLE
                + " ON " + REGISTRATION_MEMBER_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + DISTRIBUTION_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REGISTRATION_MEMBER_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " || " + REGISTRATION_MEMBER_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " || " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL
                + " || " + REGISTRATION_MEMBER_TABLE + "." + HH_MEM_ID + " = " + DISTRIBUTION_TABLE + "." + MEM_ID_15_D_COL


                + " LEFT JOIN " + REG_N_HH_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + DISTRIBUTION_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " || " + REG_N_HH_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " || " + REG_N_HH_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " || " + REG_N_HH_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " || " + REG_N_HH_TABLE + "." + HHID_COL
                + " = " + DISTRIBUTION_TABLE + "." + MEM_ID_15_D_COL

                + " WHERE " + DISTRIBUTION_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCord + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + PROG_CODE_COL + " = '" + prgCode + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + SRV_CODE_COL + " = '" + srvCode + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + OP_MONTH_CODE_COL + " = '" + opMCode + "' "
                + " AND " + DISTRIBUTION_TABLE + "." + DIST_FLAG_COL + " = '" + distFlag + "' "
                ;
    }

    public static String getTotal_Service_Itemize_AttendanceSummary_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String prgCode/*, String srvCode*/, String vouItSpec) {
        return " SELECT " + SERVICE_EXTENDED_TABLE + "." + FDP_MASTER_LAY_R1_LIST_CODE_COL + " || '' || "
                + SERVICE_EXTENDED_TABLE + "." + LAY_R2_LIST_CODE_COL + " || '' || "
                + SERVICE_EXTENDED_TABLE + "." + LAY_R3_LIST_CODE_COL + " || '' || "
                + SERVICE_EXTENDED_TABLE + "." + LAY_R4_LIST_CODE_COL + " || '' || "
                + SERVICE_EXTENDED_TABLE + "." + HHID_COL + " || '' || "
                + SERVICE_EXTENDED_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " AS NewID , "
                +
                /** HERE COUNT THE SERVICE */
                SERVICE_EXTENDED_TABLE + "." + VOUCHER_UNIT_COL + "  "
                + " ," + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.UNITE_COST_COL + " AS cost "
                + " FROM " + SERVICE_EXTENDED_TABLE +
                " JOIN " + SQLiteHandler.SRV_CENTER_TABLE + " ON " + SERVICE_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SQLiteHandler.SRV_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " JOIN " + SQLiteHandler.OP_MONTH_TABLE + " ON " + SERVICE_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " AND " + SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.OPERATION_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.OPERATION_CODE_COL +
                " AND " + SERVICE_EXTENDED_TABLE + "." + OP_MONTH_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + OP_MONTH_CODE_COL +
                " AND " + SERVICE_EXTENDED_TABLE + "." + ADM_DONOR_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + ADM_DONOR_CODE_COL +
                " AND " + SERVICE_EXTENDED_TABLE + "." + ADM_AWARD_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + ADM_AWARD_CODE_COL + "  " +
                " JOIN " + SQLiteHandler.SERVICE_MASTER_TABLE + " ON " + SERVICE_EXTENDED_TABLE + "." + PROG_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL +
                " AND " + SERVICE_EXTENDED_TABLE + "." + SRV_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SRV_CODE_COL + "  " +
                " INNER JOIN " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE +
                " ON " + SERVICE_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " AND  " + SERVICE_EXTENDED_TABLE + "." + ADM_DONOR_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_DONOR_CODE_COL +
                " AND  " + SERVICE_EXTENDED_TABLE + "." + ADM_AWARD_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_AWARD_CODE_COL +
                " AND  " + SERVICE_EXTENDED_TABLE + "." + PROG_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_PROG_CODE_COL +
                " AND  " + SERVICE_EXTENDED_TABLE + "." + SRV_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_SRV_CODE_COL +
                " AND  " + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL +
                " WHERE " + SERVICE_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + SERVICE_EXTENDED_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + SERVICE_EXTENDED_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCord + "'" +
                " AND " + SERVICE_EXTENDED_TABLE + "." + PROG_CODE_COL + " = '" + prgCode + "'" +
                //** use it latter    " AND " + SERVICE_EXTENDED_TABLE + "." + ADM_SRV_CODE_COL + " = '" + srvCode + "'" +
                " AND " + SERVICE_EXTENDED_TABLE + "." + OP_MONTH_CODE_COL + " = '" + opMCode + "'  " +
                " AND " + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " = '" + vouItSpec + "'  " +
                " GROUP BY " + SERVICE_EXTENDED_TABLE + "." + FDP_MASTER_LAY_R1_LIST_CODE_COL + " , " +
                SERVICE_EXTENDED_TABLE + "." + LAY_R2_LIST_CODE_COL + " , " +
                SERVICE_EXTENDED_TABLE + "." + LAY_R3_LIST_CODE_COL + " , " +
                SERVICE_EXTENDED_TABLE + "." + LAY_R4_LIST_CODE_COL + " , " +
                SERVICE_EXTENDED_TABLE + "." + HHID_COL + " , " +
                SERVICE_EXTENDED_TABLE + "." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID;
//                + " , " +               SERVICE_EXTENDED_TABLE + "." + SERVICE_SL_COL + "  ";

    }

    public static String getTotal_Distribution_Itemize_AttendanceSummary_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String prgCode/*, String srvCode*/, String vouItSpec) {
        return " SELECT "
                + DISTRIBUTION_EXTENDED_TABLE + "." + MEM_ID_15_D_COL + " AS NewID , " +
                /** HERE Unit  */
                DISTRIBUTION_EXTENDED_TABLE + "." + VOUCHER_UNIT_COL + "  " +
                " ," + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.UNITE_COST_COL + " AS cost " +
                " FROM " + DISTRIBUTION_EXTENDED_TABLE +
                " JOIN " + SQLiteHandler.SRV_CENTER_TABLE + " ON " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SQLiteHandler.SRV_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " JOIN " + SQLiteHandler.OP_MONTH_TABLE + " ON " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + ADM_COUNTRY_CODE_COL +
                //" AND " + SERVICE_EXTENDED_TABLE + "." + OPERATION_CODE_COL + " = " + OP_MONTH_TABLE + "." + OPERATION_CODE_COL +
                " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + OP_MONTH_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + OP_MONTH_CODE_COL +
                " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_DONOR_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + ADM_DONOR_CODE_COL +
                " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_AWARD_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + ADM_AWARD_CODE_COL + "  " +
                " JOIN " + SQLiteHandler.SERVICE_MASTER_TABLE + " ON " + DISTRIBUTION_EXTENDED_TABLE + "." + PROG_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL +
                " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + SRV_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + "  " +
                " INNER JOIN " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE +
                " ON " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_COUNTRY_CODE_COL +
                " AND  " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_DONOR_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_DONOR_CODE_COL +
                " AND  " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_AWARD_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_AWARD_CODE_COL +
                " AND  " + DISTRIBUTION_EXTENDED_TABLE + "." + PROG_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_PROG_CODE_COL +
                " AND  " + DISTRIBUTION_EXTENDED_TABLE + "." + SRV_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_SRV_CODE_COL +
                " AND  " + DISTRIBUTION_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL +
                " WHERE " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCord + "'" +
                " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + PROG_CODE_COL + " = '" + prgCode + "'" +
                //** use it latter    " AND " + SERVICE_EXTENDED_TABLE + "." + SRV_CODE_COL + " = '" + srvCode + "'" +
                " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + OP_MONTH_CODE_COL + " = '" + opMCode + "'  " +
                " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " = '" + vouItSpec + "'  " +
                " GROUP BY " + DISTRIBUTION_EXTENDED_TABLE + "." + MEM_ID_15_D_COL;
//


    }

    public static String getServiceDelete_WhereCondition(String cCode, String donorCode, String awardCode, String distId, String upId, String unId, String villId, String hhId, String memId, String progCode, String srvCode, String opMonthCode, String slNo) {
        return ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + LAY_R_LIST_CODE_COL + " = '" + distId + "' " +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + upId + "' " +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + unId + "' " +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + villId + "' " +
                " AND " + HHID_COL + " = '" + hhId + "' " +
                " AND " + HH_MEM_ID + " = '" + memId + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + progCode + "' " +
                " AND " + ADM_SRV_CODE_COL + " = '" + srvCode + "' " +
                " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '2' " +
                " AND " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' " +
                " AND " + SQLiteHandler.SERVICE_SL_COL + " = '" + slNo + "' ";
    }

    public static String getRegisteredData_ifVillageExt_SelectQuery(String ext_village, String hhId) {


        String sql = "SELECT "

                + REG_N_HH_TABLE + "." + REG_DATE_COL + ", "
                + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + ", "
                + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + " AS R_District, "
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + " AS R_Upazilla, "
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + " AS R_Union, "
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL + " AS R_Village, "
                + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_NAME + ", "
                + GEO_LAY_R1_LIST_TABLE + "." + LAY_R_LIST_NAME_COL + ", "
                + GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_NAME_COL + ","
                + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_NAME + ", "
                + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_NAME_COL + ","
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HHID + ","
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HH_HEAD_NAME + ","
                + "(" + " CASE WHEN " + REGISTRATION_TABLE_HH_HEAD_SEX + "==" + "'F'" + " THEN " + "'Female'" + " ELSE " + "'Male'" + " END " + ")  AS Sex" + ","
                + REG_N_HH_TABLE + "." + HH_SIZE + ","
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_GPS_LAT + ","
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_GPS_LONG + ","
                + REG_N_HH_TABLE + "." + AG_LAND + ","
                + "(" + " CASE WHEN " + V_STATUS + "==" + "'Y'" + " THEN " + "'Yes'" + " ELSE " + "'No'" + " END " + ") AS VStatus" + ","
                + " " + SQLiteHandler.M_STATUS + " AS MStatus " + ","
                + REG_N_HH_TABLE + "." + ENTRY_BY + ","
                + REG_N_HH_TABLE + "." + ENTRY_DATE + " , "
                + REG_N_HH_TABLE + "." + VSLA_GROUP + " , "
                + LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + REGN_ADDRESS_LOOKUP_NAME_COL + " as addname , "//25
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_REGN_ADDRESS_LOOKUP_CODE_COL + " as addcode ,"//26
                + REG_N_HH_TABLE + "." + W_RANK_COL + " as wRank ,"//26
                + REG_N_HH_TABLE + "." + M_STATUS + " as status "//26
                + " FROM " + REG_N_HH_TABLE
                + " LEFT JOIN " + COUNTRY_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_CODE
                + " LEFT JOIN " + GEO_LAY_R1_LIST_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + GEO_LAY_R1_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + "=" + GEO_LAY_R1_LIST_TABLE + "." + LAY_R_LIST_CODE_COL
                + " LEFT JOIN " + GEO_LAY_R2_LIST_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + GEO_LAY_R2_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + "=" + GEO_LAY_R2_LIST_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + "=" + SQLiteHandler.GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " LEFT JOIN " + GEO_LAY_R3_LIST_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + GEO_LAY_R3_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + "=" + GEO_LAY_R3_LIST_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + "=" + GEO_LAY_R3_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + "=" + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " LEFT JOIN " + GEO_LAY_R4_LIST_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + GEO_LAY_R4_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + "=" + GEO_LAY_R4_LIST_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + "=" + GEO_LAY_R4_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + "=" + GEO_LAY_R4_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL + "=" + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_CODE_COL


                + " LEFT JOIN " + LUP_REGN_ADDRESS_LOOKUP_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_UPZILLA_CODE_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_VILLAGE_CODE_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_REGN_ADDRESS_LOOKUP_CODE_COL + " = " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL

                + " WHERE " +
                REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + " || '' || " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + " || '' || "
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + " || '' || " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + " || '' || "
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL + " = '" + ext_village + "' "
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HHID + " LIKE '%" + hhId + "%' "
                + " GROUP BY " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HHID                      /// this GROUP BY PREVENT TO SHOW DUPLICATED VALUES // Faisal Mohammad  @email:nirzon192@gmail.com @email:nirzon192@gmail.com @email:nirzon192@gmail.com @email:nirzon192@gmail.commodify
                + " ORDER BY " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HHID + " DESC";
        Log.d("MAR", sql);
        return sql;

    }


    public static String getSingleRegisteredData_sql(String cCode, String layR1Code, String layR2Code, String layR3Code, String layR4Code, final String hhId) {


        return "SELECT "
                //  + REG_N_HH_TABLE + "." + SQLiteHandler.ID_COL + ", "
                + REG_N_HH_TABLE + "." + SQLiteHandler.REG_DATE_COL + ", "
                + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + ", "
                + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE + " AS R_District, "
                + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_UPZILLA_CODE_COL + " AS R_Upazilla, "
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + " AS R_Union, "
                + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_VILLAGE_CODE_COL + " AS R_Village, "
                + SQLiteHandler.COUNTRY_TABLE + "." + SQLiteHandler.COUNTRY_COUNTRY_NAME + ", "
                + SQLiteHandler.GEO_LAY_R1_LIST_TABLE + "." + SQLiteHandler.LAY_R_LIST_NAME_COL + ", "
                + SQLiteHandler.GEO_LAY_R2_LIST_TABLE + "." + SQLiteHandler.LAY_R2_LIST_NAME_COL + ","
                + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_NAME + ", "
                + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + "." + SQLiteHandler.LAY_R4_LIST_NAME_COL + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_HHID + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_HH_HEAD_NAME + ","
                + "(" + " CASE WHEN " + SQLiteHandler.REGISTRATION_TABLE_HH_HEAD_SEX + "==" + "'F'" + " THEN " + "'Female'" + " ELSE " + "'Male'" + " END " + ")  AS Sex" + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.HH_SIZE + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_GPS_LAT + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_GPS_LONG + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.AG_LAND + ","
                + "(" + " CASE WHEN " + SQLiteHandler.V_STATUS + "==" + "'Y'" + " THEN " + "'Yes'" + " ELSE " + "'No'" + " END " + ") AS VStatus" + ","
                + "(" + " CASE WHEN " + SQLiteHandler.M_STATUS + "==" + "'Y'" + " THEN " + "'Yes'" + " ELSE " + "'No'" + " END " + ") AS MStatus" + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.ENTRY_BY + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.ENTRY_DATE + " , "
                + REG_N_HH_TABLE + "." + SQLiteHandler.VSLA_GROUP + " , "
                + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_NAME_COL + " as addname , "//25
                + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_REGN_ADDRESS_LOOKUP_CODE_COL + " as addcode "//26
                + " FROM " + REG_N_HH_TABLE
                + " LEFT JOIN " + SQLiteHandler.COUNTRY_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + SQLiteHandler.COUNTRY_TABLE + "." + COUNTRY_COUNTRY_CODE
                + " LEFT JOIN " + SQLiteHandler.GEO_LAY_R1_LIST_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + SQLiteHandler.GEO_LAY_R1_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE + "=" + SQLiteHandler.GEO_LAY_R1_LIST_TABLE + "." + LAY_R_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.GEO_LAY_R2_LIST_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + SQLiteHandler.GEO_LAY_R2_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE + "=" + SQLiteHandler.GEO_LAY_R2_LIST_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_UPZILLA_CODE_COL + "=" + SQLiteHandler.GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " LEFT JOIN " + GEO_LAY_R3_LIST_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + GEO_LAY_R3_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + "=" + GEO_LAY_R3_LIST_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R2_LIST_CODE_COL + "=" + GEO_LAY_R3_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R3_LIST_CODE_COL + "=" + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.GEO_LAY_R4_LIST_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE + "=" + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_UPZILLA_CODE_COL + "=" + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + "=" + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_VILLAGE_CODE_COL + "=" + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_UPZILLA_CODE_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_VILLAGE_CODE_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " = " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL

                + " WHERE " +
                REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' AND "
                + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE + " = '" + layR1Code + "' AND "
                + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_UPZILLA_CODE_COL + " = '" + layR2Code + "' AND "
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + " = '" + layR3Code + "' AND "
                + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_VILLAGE_CODE_COL + " = '" + layR4Code + "'   "
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_HHID + " = '" + hhId + "' ";
        // + " GROUP BY " + SQLiteHandler.REG_N_HH_TABLE + "." + SQLiteHandler.ID_COL /// this GROUP BY PREVENT TO SHOW DUPLICATED VALUES // Faisal Mohammad  @email:nirzon192@gmail.com @email:nirzon192@gmail.com @email:nirzon192@gmail.com @email:nirzon192@gmail.commodify
        //   + " ORDER BY " + SQLiteHandler.REG_N_HH_TABLE + "." + SQLiteHandler.ID_COL + " DESC";
        //   Log.d("MAR",sql);
        // return sql;

    }


    public static String getRegisteredData_ifVillageExt_SearchByName_sql(String ext_village, String hhName) {


        return "SELECT "

                + REG_N_HH_TABLE + "." + SQLiteHandler.REG_DATE_COL + ", "
                + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + ", "
                + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + " AS R_District, "
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + " AS R_Upazilla, "
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + " AS R_Union, "
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL + " AS R_Village, "
                + COUNTRY_TABLE + "." + SQLiteHandler.COUNTRY_COUNTRY_NAME + ", "
                + GEO_LAY_R1_LIST_TABLE + "." + SQLiteHandler.LAY_R_LIST_NAME_COL + ", "
                + GEO_LAY_R2_LIST_TABLE + "." + SQLiteHandler.LAY_R2_LIST_NAME_COL + ","
                + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_NAME + ", "
                + GEO_LAY_R4_LIST_TABLE + "." + SQLiteHandler.LAY_R4_LIST_NAME_COL + ","
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HHID + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_HH_HEAD_NAME + ","
                + "(" + " CASE WHEN " + SQLiteHandler.REGISTRATION_TABLE_HH_HEAD_SEX + "==" + "'F'" + " THEN " + "'Female'" + " ELSE " + "'Male'" + " END " + ")  AS Sex" + ","
                + REG_N_HH_TABLE + "." + HH_SIZE + ","
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_GPS_LAT + ","
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_GPS_LONG + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.AG_LAND + ","
                + "(" + " CASE WHEN " + SQLiteHandler.V_STATUS + "==" + "'Y'" + " THEN " + "'Yes'" + " ELSE " + "'No'" + " END " + ") AS VStatus" + ","
                + " " + SQLiteHandler.M_STATUS + " AS MStatus" + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.ENTRY_BY + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.ENTRY_DATE + " , "
                + REG_N_HH_TABLE + "." + SQLiteHandler.VSLA_GROUP + " , "
                + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_NAME_COL + " as addname , "//25
                + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_REGN_ADDRESS_LOOKUP_CODE_COL + " as addcode ,"//26
                + REG_N_HH_TABLE + "." + SQLiteHandler.W_RANK_COL + " as wRank ,"//26
                + REG_N_HH_TABLE + "." + SQLiteHandler.M_STATUS + " as status "//26

                + " FROM " + REG_N_HH_TABLE
                + " LEFT JOIN " + SQLiteHandler.COUNTRY_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_CODE
                + " LEFT JOIN " + GEO_LAY_R1_LIST_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + GEO_LAY_R1_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + "=" + GEO_LAY_R1_LIST_TABLE + "." + LAY_R_LIST_CODE_COL
                + " LEFT JOIN " + GEO_LAY_R2_LIST_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + GEO_LAY_R2_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + "=" + GEO_LAY_R2_LIST_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + "=" + GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " LEFT JOIN " + GEO_LAY_R3_LIST_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + GEO_LAY_R3_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + "=" + GEO_LAY_R3_LIST_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + "=" + GEO_LAY_R3_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + "=" + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " LEFT JOIN " + GEO_LAY_R4_LIST_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + "=" + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + "= " + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + " = " + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL + " =" + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " LEFT JOIN " + LUP_REGN_ADDRESS_LOOKUP_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + "=" + LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + " = " + LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + " = " + LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL + " = " + LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_REGN_ADDRESS_LOOKUP_CODE_COL + " = " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL

                + " WHERE " +// + LAY_R4_LIST_CODE_COL + "='" + ext_village +" AND "+
                REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + " || '' || "
                + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + " || '' || "
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + " || '' || "
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + " || '' || "
                + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_VILLAGE_CODE_COL + " = '" + ext_village + "' "
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_HH_HEAD_NAME + " LIKE '%" + hhName + "%' "
                + " GROUP BY " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HHID                     /// this GROUP BY PREVENT TO SHOW DUPLICATED VALUES // Faisal Mohammad  @email:nirzon192@gmail.com @email:nirzon192@gmail.com @email:nirzon192@gmail.com @email:nirzon192@gmail.commodify
                + " ORDER BY " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HHID + " DESC";
        //   Log.d("MAR",sql);
        // return sql;

    }


    public static String getRegisteredData_ifVillage_NOT_Ext_SelectQuery() {
        String query = "SELECT "
                + REG_N_HH_TABLE + "." + SQLiteHandler.ID_COL + ", "
                + REG_N_HH_TABLE + "." + SQLiteHandler.REG_DATE_COL + ", "
                + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + ", "
                + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE + " AS R_District, "
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UPZILLA_CODE_COL + " AS R_Upazilla, "
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + " AS R_Union, "
                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_VILLAGE_CODE_COL + " AS R_Village, "

                + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_NAME + ", "
                + GEO_LAY_R1_LIST_TABLE + "." + LAY_R_LIST_NAME_COL + ", "
                + GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_NAME_COL + ","
                + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_NAME + ", "
                + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_NAME_COL + ","

                + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_HHID + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_HH_HEAD_NAME + ","
                + "(" + " CASE WHEN " + SQLiteHandler.REGISTRATION_TABLE_HH_HEAD_SEX + "==" + "'F'" + " THEN " + "'Female'" + " ELSE " + "'Male'" + " END " + ") AS Sex" + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.HH_SIZE + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_GPS_LAT + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_GPS_LONG + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.AG_LAND + ","
                + "(" + " CASE WHEN " + SQLiteHandler.V_STATUS + "==" + "'Y'" + " THEN " + "'Yes'" + " ELSE " + "'No'" + " END " + ") AS VStatus" + ","
                + "  " + SQLiteHandler.M_STATUS + " AS MStatus " + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.ENTRY_BY + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.ENTRY_DATE + ","
                + REG_N_HH_TABLE + "." + SQLiteHandler.VSLA_GROUP + " , "
                + " , "
                + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_NAME_COL + " as addname , "//25
                + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_REGN_ADDRESS_LOOKUP_CODE_COL + " as addcode ,"//26
                + REG_N_HH_TABLE + "." + SQLiteHandler.W_RANK_COL + " as wRank ,"//26
                + REG_N_HH_TABLE + "." + SQLiteHandler.M_STATUS + " as status "//26

                + " FROM " + REG_N_HH_TABLE
                + " LEFT JOIN " + SQLiteHandler.COUNTRY_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + SQLiteHandler.COUNTRY_TABLE + "." + COUNTRY_COUNTRY_CODE
                + " LEFT JOIN " + SQLiteHandler.GEO_LAY_R1_LIST_TABLE

                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + SQLiteHandler.GEO_LAY_R1_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE + "=" + SQLiteHandler.GEO_LAY_R1_LIST_TABLE + "." + LAY_R_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.GEO_LAY_R2_LIST_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + SQLiteHandler.GEO_LAY_R2_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE + "=" + SQLiteHandler.GEO_LAY_R2_LIST_TABLE + "." + LAY_R_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_UPZILLA_CODE_COL + "=" + SQLiteHandler.GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " LEFT JOIN " + GEO_LAY_R3_LIST_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + GEO_LAY_R3_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + LAY_R1_LIST_CODE + "=" + GEO_LAY_R3_LIST_TABLE + "." + LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_UPZILLA_CODE_COL + "=" + GEO_LAY_R3_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + "=" + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.GEO_LAY_R4_LIST_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE + "=" + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_UPZILLA_CODE_COL + "=" + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + "=" + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_VILLAGE_CODE_COL + "=" + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE
                + " ON " + REG_N_HH_TABLE + "." + ADM_COUNTRY_CODE_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R_LIST_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.LAY_R2_LIST_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R2_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_UNION_CODE_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R3_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_VILLAGE_CODE_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + LAY_R4_LIST_CODE_COL
                + " AND " + REG_N_HH_TABLE + "." + SQLiteHandler.REGISTRATION_TABLE_REGN_ADDRESS_LOOKUP_CODE_COL + " = " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL

                + " GROUP BY " + REG_N_HH_TABLE + "." + SQLiteHandler.ID_COL  /// this GROUP BY PREVENT TO SHOW DUPLICATED VALUES // Faisal Mohammad  @email:nirzon192@gmail.com @email:nirzon192@gmail.com @email:nirzon192@gmail.com @email:nirzon192@gmail.commodify
                + " ORDER BY " + REG_N_HH_TABLE + "." + SQLiteHandler.ID_COL + " DESC";

        Log.e("ShuvoQuery", query);
        return query;

    }


    public static String getSrvSpecificByMemberId_SelectQuery(String cCode, String donorCode, String awardCode
            , String programCode, String srvCode, String opCode, String opMonthCode, String srvCenterCode
            , String fdpCode, String mem15Id) {
        return "SELECT "
                + SQLiteHandler.BABY_STATUS_COL
                + " , " + GMP_ATTENDACE_COL
                + " , " + WEIGHT_STATUS_COL
                + " , " + NUT_ATTENDANCE_COL
                + " , " + VITA_UNDER5_COL
                + " , " + EXCLUSIVE_CURRENTLYBF_COL
                + " , " + DATE_COMPFEEDING_COL
                + " , " + CMAMREF_COL
                + " , " + CMAMADD_COL
                + " , " + ANCVISIT_COL
                + " , " + PNCVISIT_2D_COL
                + " , " + PNCVISIT_1W_COL
                + " , " + PNCVISIT_6W_COL
                + " , " + DELIVERY_STAFF_1_COL
                + " , " + DELIVERY_STAFF_2_COL
                + " , " + DELIVERY_STAFF_3_COL
                + " , " + HOME_SUPPORT24H_1D_COL
                + " , " + HOME_SUPPORT24H_2D_COL
                + " , " + HOME_SUPPORT24H_3D_COL
                + " , " + HOME_SUPPORT24H_8D_COL
                + " , " + HOME_SUPPORT24H_14D_COL
                + " , " + HOME_SUPPORT24H_21D_COL
                + " , " + HOME_SUPPORT24H_30D_COL
                + " , " + HOME_SUPPORT24H_60D_COL
                + " , " + HOME_SUPPORT24H_90D_COL

                + " , " + HOME_SUPPORT48H_1D_COL
                + " , " + HOME_SUPPORT48H_3D_COL
                + " , " + HOME_SUPPORT48H_8D_COL
                + " , " + HOME_SUPPORT48H_30D_COL
                + " , " + HOME_SUPPORT48H_60D_COL
                + " , " + HOME_SUPPORT48H_90D_COL

                + " , " + MATERNAL_BLEEDING_COL
                + " , " + MATERNAL_SEIZURE_COL
                + " , " + MATERNAL_INFECTION_COL
                + " , " + MATERNAL_PROLONGEDLABOR_COL
                + " , " + MATERNAL_OBSTRUCTEDLABOR_COL
                + " , " + MATERNAL_PPRM_COL
                + " , " + NBORN_ASPHYXIA_COL
                + " , " + NBORN_SEPSIS_COL
                + " , " + NBORN_HYPOTHERMIA_COL
                + " , " + NBORN_HYPERTHERMIA_COL
                + " , " + NBORN_NOSUCKLING_COL
                + " , " + NBORN_JAUNDICE_COL
                + " , " + CHILD_DIARRHEA_COL
                + " , " + CHILD_PNEUMONIA_COL
                + " , " + CHILD_FEVER_COL
                + " , " + CHILD_CEREBRALPALSY_COL
                + " , " + IMMU_POLIO_COL
                + " , " + IMMU_BCG_COL
                + " , " + IMMU_MEASLES_COL
                + " , " + IMMU_DPT_HIB_COL
                + " , " + IMMU_LOTTA_COL
                + " , " + IMMU_OTHER_COL
                + " , " + FPCOUNSEL_MALECONDOM_COL
                + " , " + FPCOUNSEL_FEMALECONDOM_COL
                + " , " + FPCOUNSEL_PILL_COL
                + " , " + FPCOUNSEL_DEPO_COL
                + " , " + FPCOUNSEL_LONGPARMANENT_COL
                + " , " + FPCOUNSEL_NOMETHOD_COL

                + " , " + SQLiteHandler.CROP_CODE_COL
                + " , " + SQLiteHandler.LOAN_SOURCE_COL
                + " , " + SQLiteHandler.LOAN_AMT_COL
                + " , " + SQLiteHandler.ANIMAL_CODE_COL
                + " , " + SQLiteHandler.LEAD_CODE_COL

                + " FROM " + SQLiteHandler.SERVICE_SPECIFIC_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND  " + ADM_DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND  " + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND  " + PROG_CODE_COL + " = '" + programCode + "'"
                + " AND  " + SRV_CODE_COL + " = '" + srvCode + "'"
                + " AND  " + SQLiteHandler.OPERATION_CODE_COL + " = '" + opCode + "'"
                + " AND  " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "'"
                + " AND  " + SQLiteHandler.SERVICE_CENTER_CODE_COL + " = '" + srvCenterCode + "'"
                + " AND  " + FDP_CODE_COL + " = '" + fdpCode + "'"
                + " AND  " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL
                + " || '' || " + LAY_R4_LIST_CODE_COL + " || '' || " + HHID_COL + " || '' || " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + mem15Id + "'";

    }

    public static String getServiceDateRange_selectQuery(String cCode, String srvOpMonthCode) {
        return "SELECT  " + SQLiteHandler.OPERATION_CODE_COL
                + " , " + OP_MONTH_CODE_COL
                + " , " + START_DATE_COL
                + " , " + END_DATE_COL
                + " , " + MONTH_LABEL_COL
                + " FROM " + OP_MONTH_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + STATUS + "= 'A'"
                + " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '2'"
                + " AND " + OP_MONTH_CODE_COL + " = '" + srvOpMonthCode + "'";
    }


    public static String getProgramGraduationDateOfMember_sql(String cCode, String disCode, String upCode, String unCode, String vCode, String hhID, String memID, String donorCode, String awardCode, String progCode) {
        return "SELECT  MAX( " + SQLiteHandler.GRD_DATE_COL + " )  AS grdDate"
                + " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + LAY_R_LIST_CODE_COL + " = '" + disCode + "' "
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "' "
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "' "
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "' "
                + " AND " + HHID_COL + " = '" + hhID + "' "
                + " AND " + HH_MEM_ID + " = '" + memID + "' "
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + ADM_PROG_CODE_COL + " = '" + progCode + "' ";
    }

    public static String checkAdmCountryProgramsVoucherFlag_sql(String cCode, String donorCode, String awardCode, String progCode) {
        return "SELECT " + SQLiteHandler.VOUCHER_FLAG + " FROM " + SQLiteHandler.COUNTRY_PROGRAM_TABLE +
                " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "'" +
                " AND " + ADM_PROG_CODE_COL + " = '" + progCode + "'";
    }


    public static String checkAdmCountryProgramsNoneFoodFlag_sql(String cCode, String donorCode, String awardCode, String progCode, String srvCode) {
        return "SELECT " + SQLiteHandler.NON_FOOD_FLAG + " FROM " + SQLiteHandler.COUNTRY_PROGRAM_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND " + ADM_PROG_CODE_COL + " = '" + progCode + "'"
                + " AND " + ADM_SRV_CODE_COL + " = '" + srvCode + "'"
                ;
    }

    public static String get_VOItmUnitMeas(String measRCode) {
        return " SELECT " + VOUCHER_ITEM__MEAS_TABLE + "." + UNITE_MEAS_COL
                + " FROM " + VOUCHER_ITEM__MEAS_TABLE
                + " WHERE " + VOUCHER_ITEM__MEAS_TABLE + "." + MEAS_R_CODE_COL + " = " + measRCode;

    }


    public static String getServExtentedItemName(final String cCode, final String donorCode, final String awardCode, final String opMCode, final String programCode) {
        return " SELECT " +
                SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " AS voucherID" +

                " , " + VOUCHER_ITEM_TABLE + "." + ITEM_NAME_COL

                + " || '-' || " + VOUCHER_ITEM__MEAS_TABLE + "." + UNITE_MEAS_COL
                + " ||' '||  " + VOUCHER_ITEM__MEAS_TABLE + "." + MEASE_TITLE_COL + " AS item" +

                " FROM " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE +
                "  INNER JOIN " + VOUCHER_ITEM_TABLE
                + " ON " + VOUCHER_ITEM_TABLE + "." + VOUCHER_ITEM_CATEGORY_CODE_COL + " || " + VOUCHER_ITEM_TABLE + "." + ITEM_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",0,8) " +
                "  INNER JOIN " + VOUCHER_ITEM__MEAS_TABLE
                + " ON " + VOUCHER_ITEM__MEAS_TABLE + "." + MEAS_R_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",8) " +
                "  INNER JOIN " + DISTRIBUTION_EXTENDED_TABLE
                + " ON " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_DONOR_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_AWARD_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_AWARD_CODE_COL
                + " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + PROG_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + SRV_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_SRV_CODE_COL
                + " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL


                + " WHERE  " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND " + DISTRIBUTION_EXTENDED_TABLE + "." + OP_MONTH_CODE_COL + " = '" + opMCode + "'  "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_PROG_CODE_COL + " = '" + programCode + "'  "
                + " GROUP BY "
                + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_DONOR_CODE_COL
                + " , " + DISTRIBUTION_EXTENDED_TABLE + "." + ADM_AWARD_CODE_COL
                + " , " + DISTRIBUTION_EXTENDED_TABLE + "." + PROG_CODE_COL
                + " , " + DISTRIBUTION_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL;

    }


    public static String getDistExtentedItemName(final String cCode, final String donorCode, final String awardCode, final String opMCode, final String programCode) {
        return " SELECT " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " AS voucherID "
                + " , " + VOUCHER_ITEM_TABLE + "." + ITEM_NAME_COL
                + " || '-' || " + VOUCHER_ITEM__MEAS_TABLE + "." + UNITE_MEAS_COL
                + " ||' '||  " + VOUCHER_ITEM__MEAS_TABLE + "." + MEASE_TITLE_COL + " AS item "
                + " FROM " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE
                + "  INNER JOIN " + VOUCHER_ITEM_TABLE
                + " ON " + VOUCHER_ITEM_TABLE + "." + VOUCHER_ITEM_CATEGORY_CODE_COL + " || " + VOUCHER_ITEM_TABLE + "." + ITEM_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",0,8) "
                + "  INNER JOIN " + VOUCHER_ITEM__MEAS_TABLE
                + " ON " + VOUCHER_ITEM__MEAS_TABLE + "." + MEAS_R_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",8) " +
                "  INNER JOIN " + SERVICE_EXTENDED_TABLE
                + " ON " + SERVICE_EXTENDED_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + ADM_DONOR_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + ADM_AWARD_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_AWARD_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + PROG_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + SRV_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_SRV_CODE_COL
                + " AND " + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL +
                " WHERE  " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND " + SERVICE_EXTENDED_TABLE + "." + OP_MONTH_CODE_COL + " = '" + opMCode + "'  "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + ADM_PROG_CODE_COL + " = '" + programCode + "'  "
                + " GROUP BY " + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL;
    }

    public static String getGroupSummaryList_sql(final String cCode) {
        return "SELECT " +

                "   cg." + ADM_COUNTRY_CODE_COL + " " +
                " , cg." + ADM_DONOR_CODE_COL +
                " , cg." + ADM_AWARD_CODE_COL +
                " , cg." + ADM_PROG_CODE_COL +
                " , cgc." + GROUP_CAT_CODE_COL +
                " , cgc." + GROUP_CAT_SHORT_NAME_COL +
                " , cg." + GROUP_CODE_COL +
                " , cg." + GROUP_NAME_COL +
                " , cg." + GRP_LAY_R3_LIST_CODE_COL +
                " , ut." + LAY_R3_LIST_NAME +
                " , srv." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL +

                " , ( select Count(*) from " + REG_N_MEM_PROG_GRP_TABLE + " AS regNgrp "
                + " WHERE  regNgrp." + ADM_COUNTRY_CODE_COL + " = cg." + ADM_COUNTRY_CODE_COL
                + " AND  " + "regNgrp." + ADM_DONOR_CODE_COL + " = cg." + ADM_DONOR_CODE_COL
                + " AND  " + "regNgrp." + ADM_AWARD_CODE_COL + " = cg." + ADM_AWARD_CODE_COL
                + " AND regNgrp." + PROG_CODE_COL + " = cg." + ADM_PROG_CODE_COL
                + " AND  " + "regNgrp." + GROUP_CODE_COL + " = cg." + GROUP_CODE_COL


                + " )  AS c " +

                " FROM " + COMMUNITY_GROUP_TABLE + "  AS cg " +

                " LEFT JOIN " + REG_N_MEM_PROG_GRP_TABLE + " AS regG " +
                " ON regG." + ADM_COUNTRY_CODE_COL + " = cg." + ADM_COUNTRY_CODE_COL + " " +
                " AND regG." + ADM_DONOR_CODE_COL + " = cg." + ADM_DONOR_CODE_COL +
                " AND regG." + ADM_AWARD_CODE_COL + " = cg." + ADM_AWARD_CODE_COL +
                " AND regG." + PROG_CODE_COL + " = cg." + ADM_PROG_CODE_COL +
                " AND regG." + GROUP_CODE_COL + " = cg." + GROUP_CODE_COL +

                " LEFT JOIN " + COMMUNITY_GROUP_CATEGORY_TABLE + " AS cgc " +

                " ON cg." + ADM_COUNTRY_CODE_COL + " = cgc." + ADM_COUNTRY_CODE_COL +
                " AND cg." + ADM_DONOR_CODE_COL + " = cgc." + ADM_DONOR_CODE_COL +
                " AND cg." + ADM_AWARD_CODE_COL + " = cgc." + ADM_AWARD_CODE_COL +
                " AND cg." + ADM_PROG_CODE_COL + " = cgc." + ADM_PROG_CODE_COL +
                " AND cg." + GROUP_CAT_CODE_COL + " = cgc." + GROUP_CAT_CODE_COL +
                " LEFT JOIN " + SERVICE_MASTER_TABLE + " AS srv " +
                " ON cg." + ADM_PROG_CODE_COL + " = srv. " + ADM_PROG_CODE_COL +
                " INNER JOIN " + SELECTED_VILLAGE_TABLE + " AS sv " +
                " ON cg." + LAY_R1_CODE_COL + " = sv.DistrictCode " +
                " AND cg." + GRP_LAY_R2_LIST_CODE_COL + " = sv.UpazillaCode " +
                " AND cg." + GRP_LAY_R3_LIST_CODE_COL + " = sv.UnitCode " +
                " AND regG." + SRV_CODE_COL + " = srv." + ADM_SRV_CODE_COL +
                " INNER JOIN " + GEO_LAY_R3_LIST_TABLE + " AS ut " +
                " ON ut." + ADM_COUNTRY_CODE_COL + " = cg." + ADM_COUNTRY_CODE_COL +
                " AND ut." + LAY_R1_LIST_CODE_COL + " = cg." + LAY_R1_CODE_COL +
                " AND  ut." + LAY_R2_LIST_CODE_COL + " = cg." + GRP_LAY_R2_LIST_CODE_COL +
                " AND  ut." + LAY_R3_LIST_CODE_COL + " = cg." + GRP_LAY_R2_LIST_CODE_COL +


                " WHERE cg." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " GROUP BY cgc." + GROUP_CAT_CODE_COL + ", cg." + GROUP_CODE_COL;

        // OLD QUERY CONFIRM THE NEW ONE THEN DELETE OLD ONE (BELLOW)
       /* return "SELECT " +

                "   cg." + ADM_COUNTRY_CODE_COL + " " +
                " , cg." + ADM_DONOR_CODE_COL +
                " , cg." + ADM_AWARD_CODE_COL +
                " , cg." + ADM_PROG_CODE_COL +
                " , cgc." + GROUP_CAT_CODE_COL +
                " , cgc." + GROUP_CAT_SHORT_NAME_COL +
                " , cg." + GROUP_CODE_COL +
                " , cg." + GROUP_NAME_COL +
                " , srv." + SQLiteHandler.SERVICE_SHORT_NAME_COL +

                " , ( select Count(*) from " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + " AS regNgrp "
                + " WHERE  regNgrp." + ADM_COUNTRY_CODE_COL + " = cg." + ADM_COUNTRY_CODE_COL
                + " AND  " + "regNgrp." + ADM_DONOR_CODE_COL + " = cg." + ADM_DONOR_CODE_COL
                + " AND  " + "regNgrp." + ADM_AWARD_CODE_COL + " = cg." + ADM_AWARD_CODE_COL
                + " AND regNgrp." + ADM_PROG_CODE_COL + " = cg." + ADM_PROG_CODE_COL
                + " AND  " + "regNgrp." + GROUP_CODE_COL + " = cg." + GROUP_CODE_COL


                + " )  AS c " +

                " FROM " + COMMUNITY_GROUP_TABLE + "  AS cg " +

                " LEFT JOIN " + REG_N_MEM_PROG_GRP_TABLE + " AS regG " +
                " ON regG." + ADM_COUNTRY_CODE_COL + " = cg." + ADM_COUNTRY_CODE_COL + " " +
                " AND regG." + ADM_DONOR_CODE_COL + " = cg." + ADM_DONOR_CODE_COL +
                " AND regG." + ADM_AWARD_CODE_COL + " = cg." + ADM_AWARD_CODE_COL +
                " AND regG." + ADM_PROG_CODE_COL + " = cg." + ADM_PROG_CODE_COL +
                " AND regG." + GROUP_CODE_COL + " = cg." + GROUP_CODE_COL +
                " LEFT JOIN " + COMMUNITY_GROUP_CATEGORY_TABLE + " AS cgc " +
                " ON cg." + ADM_COUNTRY_CODE_COL + " = cgc." + ADM_COUNTRY_CODE_COL +
                " AND cg." + ADM_DONOR_CODE_COL + " = cgc." + ADM_DONOR_CODE_COL +
                " AND cg." + ADM_AWARD_CODE_COL + " = cgc." + ADM_AWARD_CODE_COL +
                " AND cg." + ADM_PROG_CODE_COL + " = cgc." + ADM_PROG_CODE_COL +
                " AND cg." + GROUP_CAT_CODE_COL + " = cgc." + GROUP_CAT_CODE_COL +
                " LEFT JOIN " + SERVICE_MASTER_TABLE + " AS srv " +
                " ON cg." + ADM_PROG_CODE_COL + " = srv. " + ADM_PROG_CODE_COL +
                " AND regG." + ADM_SRV_CODE_COL + " = srv." + ADM_SRV_CODE_COL +
                " WHERE cg." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " GROUP BY cgc." + GROUP_CAT_CODE_COL + ", cg." + GROUP_CODE_COL;*/
    }

    public static String loadVillageInAssignSummary_sql(String cCode) {
        return "SELECT " + " v." + ADM_COUNTRY_CODE_COL + " || '' ||  v." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || '' || v." + LAY_R2_LIST_CODE_COL + " || '' || v." +
                LAY_R3_LIST_CODE_COL + " || '' || v." + LAY_R4_LIST_CODE_COL + " AS v_code," +
                " v." + SQLiteHandler.LAY_R4_LIST_NAME_COL + " AS Vill_Name " +
                     /*   " COUNT("+PID_COL+") AS records"*/" FROM " + SQLiteHandler.GEO_LAY_R4_LIST_TABLE + " AS v" +
                " LEFT JOIN " + REG_N_HH_TABLE + " AS r" +
                " ON r." + ADM_COUNTRY_CODE_COL + "= v." + ADM_COUNTRY_CODE_COL
                + " AND " + "r." + LAY_R1_LIST_CODE + "= v." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND " + "r." + REGISTRATION_TABLE_UPZILLA_CODE_COL + "= v." + LAY_R2_LIST_CODE_COL
                + " AND " + "r." + REGISTRATION_TABLE_UNION_CODE_COL + "= v." + LAY_R3_LIST_CODE_COL
                + " AND " + "r." + REGISTRATION_TABLE_VILLAGE_CODE_COL + "= v." + LAY_R4_LIST_CODE_COL +
                " Inner join " + SELECTED_VILLAGE_TABLE + " AS s"
                + " on " + " s.CountryCode " + " = v." + ADM_COUNTRY_CODE_COL
                + " AND " + "s.DistrictCode " + " = v." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " AND " + "s.UpazillaCode " + " = v." + LAY_R2_LIST_CODE_COL
                + " AND " + "s.UnitCode " + " = v." + LAY_R3_LIST_CODE_COL
                + " AND " + "s.VillageCode " + " = v." + LAY_R4_LIST_CODE_COL +

                " WHERE v." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'" + /** send the no of village for selected country added by Faisal Mohammad*/
                "  GROUP BY v." + ADM_COUNTRY_CODE_COL + ",v." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + ",v." + LAY_R2_LIST_CODE_COL + ",v." + LAY_R3_LIST_CODE_COL + ",v." + LAY_R4_LIST_CODE_COL;

    }


    public static String getIdListInGroupInGroupSummary_sql(String cCode, String donorCode, String awardCode, String prgCode, String grpCode) {


        String getMemberName;
        if (cCode.equals("0004")) {
            getMemberName = " regMem." + SQLiteHandler.MEM_NAME_FIRST_COL +
                    "|| ' ' || " + " regMem." + SQLiteHandler.MEM_NAME_MIDDLE_COL +
                    "|| ' ' || " + " regMem." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else
            getMemberName = " regMem." + SQLiteHandler.MEM_NAME_COL;
        return "SELECT " +


                "  regG." + LAY_R1_LIST_CODE_COL +
                " || '' || regG." + LAY_R2_LIST_CODE_COL +
                " || '' || regG." + LAY_R3_LIST_CODE_COL +
                " || '' || regG." + LAY_R4_LIST_CODE_COL +
                " || '' || regG." + HHID_COL +
                " || '' || regG." + MEM_ID_COL + " AS idMem " +

                " , srv." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL +
                " , " + getMemberName + " AS memName " +
                " , cg." + GROUP_NAME_COL +
                " FROM " + REG_N_MEM_PROG_GRP_TABLE + " AS regG " +


                " LEFT JOIN " + SERVICE_MASTER_TABLE + " AS srv " +
                " ON regG." + PROG_CODE_COL + " = srv. " + ADM_PROG_CODE_COL +
                " AND regG." + SRV_CODE_COL + " = srv." + ADM_SRV_CODE_COL +

                " LEFT JOIN " + REGISTRATION_MEMBER_TABLE + " AS regMem " +

                " ON " + " regG." + ADM_COUNTRY_CODE_COL + " = " + " regMem." + ADM_COUNTRY_CODE_COL +
                " AND " + " regG." + LAY_R1_LIST_CODE_COL + " = " + " regMem." + LAY_R1_LIST_CODE_COL +
                " AND " + " regG." + LAY_R2_LIST_CODE_COL + " = " + " regMem." + LAY_R2_LIST_CODE_COL +
                " AND " + " regG." + LAY_R3_LIST_CODE_COL + " = " + " regMem." + LAY_R3_LIST_CODE_COL +
                " AND " + " regG." + LAY_R4_LIST_CODE_COL + " = " + " regMem." + LAY_R4_LIST_CODE_COL +
                " AND " + " regG." + HHID_COL + " = " + " regMem." + HHID_COL +
                " AND " + " regG." + MEM_ID_COL + " = " + " regMem." + HH_MEM_ID + " " +

                " INNER JOIN " + COMMUNITY_GROUP_TABLE + " AS cg "
                + " ON  cg." + ADM_COUNTRY_CODE_COL + " = " + " regG." + ADM_COUNTRY_CODE_COL
                + " AND cg." + ADM_AWARD_CODE_COL + " = " + " regG." + ADM_AWARD_CODE_COL
                + " AND cg." + ADM_DONOR_CODE_COL + " = " + " regG." + ADM_DONOR_CODE_COL
                + " AND cg." + ADM_PROG_CODE_COL + " = " + " regG." + PROG_CODE_COL
                + " AND cg." + GROUP_CODE_COL + " = " + " regG." + GROUP_CODE_COL

                + " AND cg." + LAY_R1_CODE_COL + " = " + " regG." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R1_LIST_CODE_COL
                + " AND cg." + GRP_LAY_R2_LIST_CODE_COL + " = " + " regG." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R2_LIST_CODE_COL
                + " AND cg." + GRP_LAY_R3_LIST_CODE_COL + " = " + " regG." + REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R3_LIST_CODE_COL +


                " WHERE regG." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND regG." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND regG." + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND regG." + PROG_CODE_COL + " = '" + prgCode + "' " +
                " AND regG." + GROUP_CODE_COL + " = '" + grpCode + "' " +
                " GROUP BY idMem "
                ;

    }

    public static String loadProgramWhereMemberAreAssigned_sql(final String idcCode, final String donorCode, final String awardCode, final String memId) {
        return "SELECT " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL
                + " , " + ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL
                + " FROM " + ADM_PROGRAM_MASTER_TABLE
                + " INNER JOIN " + ADM_COUNTRY_AWARD_TABLE
                + " ON " + ADM_COUNTRY_AWARD_TABLE + "." + ADM_DONOR_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + ADM_COUNTRY_AWARD_TABLE + "." + ADM_AWARD_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_AWARD_CODE_COL
                + " INNER JOIN " + REG_N_ASSIGN_PROG_SRV_TABLE + " AS regAss "
                + " ON regAss." + PROG_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL
                + " WHERE " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_DONOR_CODE_COL + "='" + donorCode + "'"
                + " AND regAss." + LAY_R1_LIST_CODE_COL
                + " || '' || regAss." + LAY_R2_LIST_CODE_COL
                + " || '' || regAss." + LAY_R3_LIST_CODE_COL
                + " || '' || regAss." + LAY_R4_LIST_CODE_COL
                + " || '' || regAss." + HHID_COL
                + " || '' || regAss." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + memId + "'"
                + " GROUP BY " + ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL;

    }

    public static String loadOrganization_sql(final String cCode, final String donorCode, final String awardCode) {
        return "SELECT progOR." + ORG_N_CODE_COL +
                " ,  pOrg." + ORGANIZATION_NAME + " " +
                "                                FROM " + PROGRAM_ORGANIZATION_ROLE_TABLE + " AS progOR "
                + "                               INNER JOIN " +
                "                                " + PROGRAM_ORGANIZATION_NAME_TABLE + " AS pOrg " +
                "                               ON progOR." + PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL + " = pOrg." + PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL + "  " +
                "                                WHERE (progOR." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "')" +
                "                                AND (progOR." + ADM_DONOR_CODE_COL + " = '" + donorCode + "') " +
                "                                AND (progOR." + ADM_AWARD_CODE_COL + " = '" + awardCode + "') " +
                "                                AND (progOR." + IMP_Y_N_COL + " = 'Y')" +
                " GROUP BY progOR." + PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL +
                "                                ORDER BY pOrg." + ORGANIZATION_NAME;

    }

    public static String editMemberIn_RegNmemProgGroup_where_sql(String cCode, String donorCode, String awardCode, String disttCode, String upCode, String unCode, String vCode, String hhID, String memID
            , String progCode, String srvCode) {
        return ADM_COUNTRY_CODE_COL + "= '" + cCode + "' "
                + " AND " + ADM_DONOR_CODE_COL + "= '" + donorCode + "'"
                + " AND " + ADM_AWARD_CODE_COL + "= '" + awardCode + "'"
                + " AND " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + "= '" + disttCode + "'"
                + " AND " + LAY_R2_LIST_CODE_COL + "= '" + upCode + "'"
                + " AND " + LAY_R3_LIST_CODE_COL + "= '" + unCode + "'"
                + " AND " + LAY_R4_LIST_CODE_COL + "= '" + vCode + "'"
                + " AND " + HHID_COL + "= '" + hhID + "'"
                + " AND " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + "=  '" + memID + "'"
                + " AND " + PROG_CODE_COL + "=  '" + progCode + "'"
                + " AND " + SRV_CODE_COL + "=  '" + srvCode + "'";
    }

    public static String getCommunityGroupList_sql(final String cCode, final String donorCode, final String awardCode, final String progCode, final String groupName) {
        return "SELECT " +
                " cg." + GROUP_NAME_COL

                + " , cg." + GROUP_CODE_COL
                //  + " , cg." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " , un." + LAY_R3_LIST_NAME
                + " , un." + LAY_R3_LIST_CODE_COL
                + " , un." + LAY_R2_LIST_CODE_COL
                + " , un." + LAY_R1_LIST_CODE_COL

                + " , " + " cgc." + GROUP_CAT_CODE_COL
                + " , " + " cgc." + GROUP_CAT_NAME_COL
                + " , " + " cgc." + GROUP_CAT_SHORT_NAME_COL
                + " , " + " pm." + PROGRAM_SHORT_NAME_COL
                + " , " + " pm." + ADM_PROG_CODE_COL
                + " , " + " pm." + ADM_PROGRAM_MASTER_PROGRAM_NAME_COL
                + " , " + " don." + DONOR_NAME_COL + "|| '-' || awd." + AWARD_SHORT_NAME_COL + " AS awardName "
                + " , " + " cgc." + ADM_AWARD_CODE_COL

                + " , " + " grpDetail." + ORG_CODE_COL
                + " , " + " org." + ORGANIZATION_NAME
                + " , " + " grpDetail." + STAFF_CODE_COL
                + " , " + " staff." + STAFF_NAME_COL

                + " , " + " grpDetail." + LAND_SIZE_UNDER_IRRIGATION_COL
                + " , " + " grpDetail." + IRRIGATION_SYSTEM_USED_COL
                + " , " + " grpDetail." + FUND_SUPPORT_COL
                + " , " + " grpDetail." + ACTIVE_STATUS_COL
                + " , " + " grpDetail." + REP_NAME_COL
                + " , " + " grpDetail." + REP_PHONE_NUMBER_COL
                + " , " + " grpDetail." + FORMATION_DATE_COL
                + " , " + " grpDetail." + TYPE_OF_GROUP
                + " , " + " grpDetail." + STATUS
                + " , " + " grpDetail." + PROJECT_NO_COL
                + " , " + " grpDetail." + PROJECT_TITLE

                + " FROM " + COMMUNITY_GROUP_CATEGORY_TABLE + " AS cgc "

                + " INNER JOIN "
                + COMMUNITY_GROUP_TABLE + " AS cg "
                + " ON cgc." + ADM_COUNTRY_CODE_COL + " =     cg." + ADM_COUNTRY_CODE_COL
                + " AND cgc." + ADM_DONOR_CODE_COL + " =      cg." + ADM_DONOR_CODE_COL
                + " AND cgc." + ADM_AWARD_CODE_COL + " =      cg." + ADM_AWARD_CODE_COL
                + " AND cgc." + ADM_PROG_CODE_COL + " =    cg." + ADM_PROG_CODE_COL
                + " AND cgc." + GROUP_CAT_CODE_COL + " =  cg." + GROUP_CAT_CODE_COL

                + " INNER JOIN " +
                ADM_PROGRAM_MASTER_TABLE + " AS pm "
                + " ON cgc." + ADM_DONOR_CODE_COL + " = pm." + ADM_DONOR_CODE_COL
                + " AND cgc." + ADM_AWARD_CODE_COL + " = pm." + ADM_AWARD_CODE_COL
                + " AND cgc." + ADM_PROG_CODE_COL + " = pm." + ADM_PROG_CODE_COL
                + " LEFT JOIN " +
                GEO_LAY_R3_LIST_TABLE + " AS un"
                + " ON un." + ADM_COUNTRY_CODE_COL + " = cgc." + ADM_COUNTRY_CODE_COL
                + " AND un." + LAY_R1_LIST_CODE_COL + " = cg." + LAY_R1_CODE_COL
                + " AND un." + LAY_R2_LIST_CODE_COL + " = cg." + GRP_LAY_R2_LIST_CODE_COL
                + " AND un." + LAY_R3_LIST_CODE_COL + " = cg." + GRP_LAY_R3_LIST_CODE_COL
                + " INNER JOIN " +
                ADM_COUNTRY_AWARD_TABLE + " AS awd "
                + " ON awd." + ADM_COUNTRY_CODE_COL + " = cgc." + ADM_COUNTRY_CODE_COL
                + " AND awd." + ADM_DONOR_CODE_COL + " = cgc." + ADM_DONOR_CODE_COL
                + " AND awd." + ADM_AWARD_CODE_COL + " = cgc." + ADM_AWARD_CODE_COL

                + " INNER JOIN "
                + ADM_DONOR_TABLE + " AS don "
                + " ON " + "  don." + ADM_DONOR_CODE_COL + " = cgc." + ADM_DONOR_CODE_COL
                + " LEFT JOIN " + COMMUNITY_GRP_DETAIL_TABLE + " AS grpDetail "
                + " ON "
                + " grpDetail." + ADM_COUNTRY_CODE_COL + " = cgc." + ADM_COUNTRY_CODE_COL
                + " AND grpDetail." + ADM_DONOR_CODE_COL + " = cgc." + ADM_DONOR_CODE_COL
                + " AND grpDetail." + ADM_AWARD_CODE_COL + " = cgc." + ADM_AWARD_CODE_COL
                + " AND grpDetail." + ADM_PROG_CODE_COL + " = pm." + ADM_PROG_CODE_COL
                + " AND grpDetail." + GROUP_CODE_COL + " = cg." + GROUP_CODE_COL
                + " AND grpDetail." + LAY_R1_CODE_COL + " = cg." + LAY_R1_CODE_COL
                + " AND grpDetail." + GRP_LAY_R2_LIST_CODE_COL + " = cg." + GRP_LAY_R2_LIST_CODE_COL
                + " AND grpDetail." + GRP_LAY_R3_LIST_CODE_COL + " = cg." + GRP_LAY_R3_LIST_CODE_COL


                + " LEFT JOIN " + PROGRAM_ORGANIZATION_NAME_TABLE + " AS org "
                + " ON org." + PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL + " = grpDetail." + ORG_CODE_COL

                + " LEFT JOIN " + STAFF_MASTER_TABLE + " AS staff "
                + " ON staff." + STAFF_ID_COL + " = " + " grpDetail." + STAFF_CODE_COL
                + " AND staff." + STAFF_COUNTRY_CODE + " = " + " cgc." + ADM_COUNTRY_CODE_COL
                + " AND staff." + ORG_N_CODE_COL + " = " + " grpDetail." + ORG_CODE_COL

                + " INNER JOIN " + SELECTED_VILLAGE_TABLE + " AS selV "
                + " ON cg." + LAY_R1_CODE_COL + " = selV.DistrictCode "
                + " AND cg." + GRP_LAY_R2_LIST_CODE_COL + " = selV.UpazillaCode "
                + " AND cg." + GRP_LAY_R3_LIST_CODE_COL + " = selV.UnitCode "
                + "   WHERE cgc." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND cgc." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND cgc." + ADM_AWARD_CODE_COL + " ='" + awardCode + "' "
                + " AND cgc." + ADM_PROG_CODE_COL + " = '" + progCode + "' "
                + " AND cg." + GROUP_NAME_COL + " LIKE '%" + groupName + "%' "
                + " GROUP BY "
                + " cg." + ADM_COUNTRY_CODE_COL
                + ", cg." + ADM_DONOR_CODE_COL
                + ", cg." + ADM_AWARD_CODE_COL
                + ", cg." + ADM_PROG_CODE_COL
                + ", cg." + GROUP_CAT_CODE_COL
                + ",  cg." + GROUP_CODE_COL
                + ", cg." + LAY_R1_CODE_COL
                + ", cg." + GRP_LAY_R2_LIST_CODE_COL
                + ", cg." + GRP_LAY_R3_LIST_CODE_COL;

    }

    public static String getDistributionExtedVoucherSummaryDataList_sql(String cCode, String discode, String upCode, String unCode, String vCode, String memId, String donorCode, String awardCode, String programCode, String serviceCode, String opMonthCode, String fdpCode) {
        return "SELECT " + ADM_COUNTRY_CODE_COL
                + "  , " + ADM_DONOR_CODE_COL + " , " + ADM_AWARD_CODE_COL +
                " , " + LAY_R_LIST_CODE_COL + " , " + LAY_R2_LIST_CODE_COL +
                " , " + LAY_R3_LIST_CODE_COL + " , " + LAY_R4_LIST_CODE_COL +
                " , " + ADM_PROG_CODE_COL + " , " + ADM_SRV_CODE_COL +
                " , " + OP_MONTH_CODE_COL +
                " , (Select " + ITEM_NAME_COL + " from " + VOUCHER_ITEM_TABLE +
                " where " + VOUCHER_ITEM_CATEGORY_CODE_COL + " || " + ITEM_CODE_COL + " = substr(" + VOUCHER_ITEM_SPEC_COL + ",0,8)) AS ItemName " +
                " , (Select " + UNITE_MEAS_COL + " ||' '|| " + MEASE_TITLE_COL + " from " + VOUCHER_ITEM__MEAS_TABLE +
                " where " + MEAS_R_CODE_COL + " = substr(" + VOUCHER_ITEM_SPEC_COL + ",8,3) ) as measerment " +
                " , " + VOUCHER_REFERENCE_NUMBER_COL +
                " , " + VOUCHER_UNIT_COL +
                " , " + VOUCHER_ITEM_SPEC_COL +
                " FROM  " + DISTRIBUTION_EXTENDED_TABLE +
                " WHERE  " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND  " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND  " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND  " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + discode + "' " +
                " AND  " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "' " +
                " AND  " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "' " +
                " AND  " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "' " +
                " AND  " + PROG_CODE_COL + " = '" + programCode + "' " +
                " AND  " + SRV_CODE_COL + " = '" + serviceCode + "' " +
                " AND  " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' " +
                " AND  " + MEM_ID_15_D_COL + " = '" + memId + "' " +
                " AND  " + FDP_CODE_COL + " = '" + fdpCode + "' ";
    }

    public static String getServiceExtedVoucherSummaryDataList_sql(String cCode, String discode, String upCode, String unCode, String vCode, String memId, String donorCode, String awardCode, String programCode, String serviceCode, String opMonthCode) {
        return "SELECT " + ADM_COUNTRY_CODE_COL
                + "  , " + ADM_DONOR_CODE_COL + " , " + ADM_AWARD_CODE_COL
                + " , " + LAY_R_LIST_CODE_COL
                + " , " + LAY_R2_LIST_CODE_COL
                + " , " + LAY_R3_LIST_CODE_COL
                + " , " + LAY_R4_LIST_CODE_COL
                + " , " + ADM_PROG_CODE_COL
                + " , " + ADM_SRV_CODE_COL
                + " , " + OP_MONTH_CODE_COL
                + " , " + VOUCHER_ITEM_TABLE + "." + ITEM_NAME_COL + " AS ItemName "
                + " , " + VOUCHER_ITEM__MEAS_TABLE + "." + UNITE_MEAS_COL + " ||' '|| " + MEASE_TITLE_COL + " AS measerment " +
                " , " + VOUCHER_REFERENCE_NUMBER_COL
                + " , " + VOUCHER_UNIT_COL
                + " , " + VOUCHER_ITEM_SPEC_COL
                + " FROM  " + SERVICE_EXTENDED_TABLE
                + " INNER JOIN " + VOUCHER_ITEM_TABLE
                + " ON " + VOUCHER_ITEM_TABLE + "." + VOUCHER_ITEM_CATEGORY_CODE_COL + " || " + VOUCHER_ITEM_TABLE + "." + ITEM_CODE_COL + " = substr(" + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",0,8)"
                + " INNER JOIN " + VOUCHER_ITEM__MEAS_TABLE
                + " ON " + VOUCHER_ITEM__MEAS_TABLE + "." + MEAS_R_CODE_COL + " = substr(" + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",8,3)  "
                + " WHERE  " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND  " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND  " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND  " + FDP_MASTER_LAY_R1_LIST_CODE_COL + " = '" + discode + "' " +
                " AND  " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "' " +
                " AND  " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "' " +
                " AND  " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "' " +
                " AND  " + PROG_CODE_COL + " = '" + programCode + "' " +
                " AND  " + SRV_CODE_COL + " = '" + serviceCode + "' " +
                " AND  " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' " +
                " AND  " + FDP_MASTER_LAY_R1_LIST_CODE_COL + " || " + LAY_R2_LIST_CODE_COL + " || " + LAY_R3_LIST_CODE_COL + " || " + LAY_R4_LIST_CODE_COL + " || " + HHID_COL + " || " + HH_MEM_ID + " = '" + memId + "' ";
    }

    /**
     * @param cCode             country
     * @param donorCode         donor
     * @param awardCode         award
     * @param foodFlagTypeQuery an and condition which will be dynamically setted
     * @return Them method only return a query that return program name and service name  concretely and  their code
     */
    public static String loadServiceRecodeCriteria(String cCode, String donorCode, String awardCode, String foodFlagTypeQuery) {
        return " SELECT "
                + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + " ||  '' || " + COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL + " AS criteriaId "
                + ", " + ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL + " || '-' ||  " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS Criteria"
                + " FROM " + COUNTRY_PROGRAM_TABLE
                + " INNER JOIN " + ADM_PROGRAM_MASTER_TABLE
                + " ON " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_DONOR_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_AWARD_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL
                + " INNER JOIN " + SERVICE_MASTER_TABLE + " ON "
                + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL

                + " WHERE " + COUNTRY_PROGRAM_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + foodFlagTypeQuery
                + " ORDER BY Criteria ";
    }

    public static String layR4ListServicePage_sql() {
        return " JOIN " + STAFF_GEO_INFO_ACCESS_TABLE + " AS geoAccess " +
                " ON " + GEO_LAY_R4_LIST_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + "geoAccess." + ADM_COUNTRY_CODE_COL +
                " AND " + GEO_LAY_R4_LIST_TABLE + "." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = " + "geoAccess." + LAY_R_LIST_CODE_COL +
                " AND " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL + " = " + "geoAccess." + LAY_R2_LIST_CODE_COL +
                " AND " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL + " = " + "geoAccess." + LAY_R3_LIST_CODE_COL +
                " AND " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_CODE_COL + " = " + "geoAccess." + LAY_R4_LIST_CODE_COL +
                " GROUP BY " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_CODE_COL + ", " + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_NAME_COL;
    }

//    public static String getHouseHoldData_sql(String hhID, String c_code, String layR1Code, String layR2Code, String layR3Code, String layR4Code) {
//        return "SELECT "
//                + REG_N_HH_TABLE + "." + ID_COL + ", "
//                + REG_N_HH_TABLE + "." + REG_DATE_COL + ", "
//
//                + REG_N_HH_TABLE + "." + COUNTRY_CODE + ", "
//                + REG_N_HH_TABLE + "." + LAY_R_LIST_NAME_COL + " AS R_District, "
//                + REG_N_HH_TABLE + "." + LAY_R2_LIST_NAME_COL + " AS R_Upazilla, "
//                + REG_N_HH_TABLE + "." + LAY_R3_LIST_NAME + " AS R_Union, "
//                + REG_N_HH_TABLE + "." + LAY_R4_LIST_NAME_COL + " AS R_Village, "
//
//                + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_NAME + " AS country_name, "
//                + GEO_LAY_R1_LIST_TABLE + "." + LAY_R_LIST_NAME_COL + ", "
//                + GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_NAME_COL + ","
//                + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_NAME + ", "
//                + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_NAME_COL + ","
//
//                + REG_N_HH_TABLE + "." + PID_COL + ","
//                + REG_N_HH_TABLE + "." + REGISTRATION_TABLE_HH_HEAD_NAME + ","
//                + "(" + " CASE WHEN " + SEX_COL + "==" + "'F'" + " THEN " + "'Female'" + " ELSE " + "'Male'" + " END " + ")  AS Sex" + ","
//                + REG_N_HH_TABLE + "." + HH_SIZE + ","
//                + REG_N_HH_TABLE + "." + LATITUDE_COL + ","
//                + REG_N_HH_TABLE + "." + LONGITUDE_COL + ","
//                + REG_N_HH_TABLE + "." + AG_LAND + ","
//                + "(" + " CASE WHEN " + V_STATUS + "==" + "'Y'" + " THEN " + "'Yes'" + " ELSE " + "'No'" + " END " + ") AS VStatus" + ","
//                + "(" + " CASE WHEN " + M_STATUS + "==" + "'Y'" + " THEN " + "'Yes'" + " ELSE " + "'No'" + " END " + ") AS MStatus" + ","
//                + REG_N_HH_TABLE + "." + ENTRY_BY + ","
//                + REG_N_HH_TABLE + "." + ENTRY_DATE
//                + " FROM " + REG_N_HH_TABLE
//                + " LEFT JOIN " + GEO_LAY_R1_LIST_TABLE
//                + " ON " + REG_N_HH_TABLE + "." + LAY_R_LIST_NAME_COL + "=" + GEO_LAY_R1_LIST_TABLE + "." + LAY_R_LIST_CODE_COL
//                + " LEFT JOIN " + GEO_LAY_R2_LIST_TABLE + " ON " + REG_N_HH_TABLE + "." + LAY_R2_LIST_NAME_COL + "=" + GEO_LAY_R2_LIST_TABLE + "." + LAY_R2_LIST_CODE_COL
//                + " LEFT JOIN " + GEO_LAY_R3_LIST_TABLE + " ON " + REG_N_HH_TABLE + "." + LAY_R3_LIST_NAME + "=" + GEO_LAY_R3_LIST_TABLE + "." + LAY_R3_LIST_CODE_COL
//                + " LEFT JOIN " + GEO_LAY_R4_LIST_TABLE + " ON " + REG_N_HH_TABLE + "." + LAY_R4_LIST_NAME_COL + "=" + GEO_LAY_R4_LIST_TABLE + "." + LAY_R4_LIST_CODE_COL
//                + " LEFT JOIN " + COUNTRY_TABLE + " ON " + REG_N_HH_TABLE + "." + COUNTRY_CODE + "=" + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_CODE
//
//                + " WHERE " + PID_COL + "='" + hhID
//                + "' AND " + REG_N_HH_TABLE + "." + COUNTRY_CODE + "='" + c_code
//                + "' AND R_District='" + layR1Code + "'"
//                + " AND R_Upazilla='" + layR2Code + "'"
//                + " AND R_Union='" + layR3Code + "'"
//                + " AND R_Village='" + layR4Code
//
//                + "' ORDER BY " + REG_N_HH_TABLE + "." + ID_COL + " DESC";
//    }

    /***
     * @return and condition query
     */
    public static String srvTable_And_sql(String cCode, String donorCode, String awardCode, String layR1Code, String lay2Code, String layR3Code, String lay4Code, String hhid, String memid, String progCode, String srvCode, String opCode, String opMonthCode, String srvDate, String srvSerial, String distFlag) {
        return ADM_COUNTRY_CODE_COL + "= '" + cCode + "' "
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + LAY_R1_LIST_CODE_COL + " = '" + layR1Code + "' "
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + lay2Code + "' "
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + layR3Code + "' "
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + lay4Code + "' "
                + " AND " + HHID_COL + " = '" + hhid + "' "
                + " AND " + MEM_ID_COL + " = '" + memid + "' "
                + " AND " + PROG_CODE_COL + " = '" + progCode + "' "
                + " AND " + SRV_CODE_COL + " = '" + srvCode + "' "
                + " AND " + OPERATION_CODE_COL + " = '" + opCode + "' "
                + " AND " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' "
                + " AND " + SERVICE_TABLE_SERVICE_DT_COL + " = '" + srvDate + "' "
                + " AND " + SERVICE_TABLE_SERVICE_SL_COL + " = '" + srvSerial + "' "
                + " AND " + DIST_FLAG_COL + " = '" + distFlag + "' ";
    }

    public static String selectSrvTable_sql(String cCode, String donorCode, String awardCode, String layR1Code, String lay2Code, String layR3Code, String lay4Code, String hhid, String memid, String progCode, String srvCode, String opCode, String opMonthCode, String srvDate, String srvSerial, String distFlag) {
        return "SELECT * FROM " + SERVICE_TABLE + " WHERE " + srvTable_And_sql(cCode, donorCode, awardCode, layR1Code, lay2Code, layR3Code, lay4Code, hhid, memid, progCode, srvCode, opCode, opMonthCode, srvDate, srvSerial, distFlag);
    }

    /**
     * Only fore the Service recording Activity to Specifies the Service Center
     *
     * @param opMode operation Mode of Device
     * @return query
     */
    public static String loadServiceCenter_sql(int opMode) {
        String criteria = "";
        switch (opMode) {
            case UtilClass.SERVICE_OPERATION_MODE:
                criteria = "SELECT  CASE " + FDP_CODE_COL + " WHEN  'null'  THEN '000'  ELSE " + FDP_CODE_COL + " END " + " || '' || " + SERVICE_CENTER_CODE_COL + " , " +
                        SERVICE_CENTER_NAME_COL + " FROM " + SRV_CENTER_TABLE
                        + " WHERE " + SRV_CENTER_TABLE + "." + SERVICE_CENTER_CODE_COL + " || '' || "
                        + SRV_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL
                        + " IN ( SELECT "
                        + SELECTED_SERVICE_CENTER_TABLE + "." + SERVICE_CENTER_CODE_COL + " || '' || "
                        + SELECTED_SERVICE_CENTER_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + " from " + SELECTED_SERVICE_CENTER_TABLE + ")"
                        + " AND " + FDP_CODE_COL + " != 'null' "
                        + " GROUP BY " + SRV_CENTER_TABLE + "." + SERVICE_CENTER_CODE_COL;


                break;
            default:
                criteria = "SELECT  CASE " + FDP_CODE_COL + " WHEN  'null'  THEN '000'  ELSE " + FDP_CODE_COL + " END " + " || '' || " + SERVICE_CENTER_CODE_COL + " , " +
                        SERVICE_CENTER_NAME_COL + " FROM " + SRV_CENTER_TABLE;
                break;
        }
        return criteria;
    }

    /**
     * this method only use for dynamic response
     *
     * @param cCode         Country Code
     * @param resLupText    response look up text
     * @param lup_TableName look up table Name
     * @return dynamic query
     */
    public static String loadDynamicSpinnerListLoader_sql(String cCode, String resLupText, String lup_TableName, AssignDataModel.DynamicDataIndexDataModel dyBasic) {
        String udf = "";

        switch (resLupText) {

            case GEO_LAYER_3:

                udf = "SELECT " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL
                        + ", " + LAY_R3_LIST_NAME
                        + " FROM " + GEO_LAY_R3_LIST_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'";


                break;
            case GEO_LAYER_2:
                udf = "SELECT " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL
                        + ", " + LAY_R2_LIST_NAME_COL
                        + " FROM " + GEO_LAY_R2_LIST_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + "= '" + cCode + "'";


                break;

            case GEO_LAYER_1:

                udf = "SELECT " + LAY_R_LIST_CODE_COL
                        + ", " + LAY_R_LIST_NAME_COL
                        + " FROM " + GEO_LAY_R1_LIST_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'";


                break;

            case GEO_LAYER_4:

                udf = "SELECT " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL + " || '' || " + LAY_R4_LIST_CODE_COL
                        + ", " + LAY_R4_LIST_NAME_COL
                        + " FROM " + GEO_LAY_R4_LIST_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'";


                break;

            case GEO_LAYER_ADDRESS:

                udf = "SELECT " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL + " || '' || " + LAY_R4_LIST_CODE_COL + " || '' || " + REGN_ADDRESS_LOOKUP_CODE_COL
                        + ", " + REGN_ADDRESS_LOOKUP_NAME_COL
                        + " FROM " + LUP_REGN_ADDRESS_LOOKUP_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + "='" + cCode + "'";


                break;

            case SERVICE_SITE:

                udf = "SELECT " + SRV_CENTER_TABLE + "." + SERVICE_CENTER_CODE_COL
                        + ", " + SRV_CENTER_TABLE + "." + SERVICE_CENTER_NAME_COL
                        + " FROM " + SRV_CENTER_TABLE
                        + " WHERE " + SRV_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'";


                break;

            case DISTRIBUTION_POINT:

                udf = "SELECT " + FDP_MASTER_TABLE + "." + FDP_CODE_COL
                        + ", " + FDP_MASTER_TABLE + "." + FDP_NAME_COL
                        + " FROM " + FDP_MASTER_TABLE
                        + " WHERE " + FDP_MASTER_TABLE + "." + FDP_MASTER_COUNTRY_CODE + "='" + cCode + "'";


                break;


            case LOOKUP_LIST:

                udf = "SELECT " + DT_LUP_TABLE + "." + LIST_CODE_COL
                        + ", " + DT_LUP_TABLE + "." + LIST_NAME_COL
                        + " FROM " + DT_LUP_TABLE
                        + " WHERE " + DT_LUP_TABLE + "." + ADM_COUNTRY_CODE_COL + "= '" + cCode + "' "
                        + " AND " + DT_LUP_TABLE + "." + TABLE_NAME_COL + "= '" + lup_TableName + "'";


                break;


            case COMMUNITY_GROUP:
                udf = loadCommunityGroup(" INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                        + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                        + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'");

                break;


            case COMMUNITY_GROUP_PG:


                udf = loadCommunityGroup(" INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                        + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                        + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                        + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '001' " // Producer group= 001
                        + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001
                );
                break;
            case COMMUNITY_GROUP_IG:
                udf = loadCommunityGroup(
                        " INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                                + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                                + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                                + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '003' " // Irrigation Group= 003
                                + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001
                );
                break;

            case COMMUNITY_GROUP_MG:
                udf = loadCommunityGroup(
                        " INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                                + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                                + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                                + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '002' " // Marketing Group= 002
                                + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001
                );
                break;

            case COMMUNITY_GROUP_WE:
                udf = loadCommunityGroup(" INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                        + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                        + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                        + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '004' " // WeVSL= 004
                        + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001
                );
                break;

            case COMMUNITY_GROUP_LG:
                udf = loadCommunityGroup(" INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                        + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                        + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                        + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '005' " // Livestock Group= 005
                        + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001
                );
                break;

            case ORGANIZATION_LIST:

                udf = "SELECT  progOR." + ORG_N_CODE_COL
                        + ", pOrg." + ORGANIZATION_NAME + " " +
                        "                                FROM " + PROGRAM_ORGANIZATION_ROLE_TABLE + " AS progOR "
                        + "                               INNER JOIN " +
                        "                                " + PROGRAM_ORGANIZATION_NAME_TABLE + " AS pOrg " +
                        "                               ON progOR." + ORG_N_CODE_COL + " = pOrg." + PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL + "  " +
                        "                                WHERE (progOR." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "')" +
                        " GROUP BY pOrg." + ORGANIZATION_NAME;

                break;

            case DTResponseRecordingActivity.COMMNITY_ANIMAL:

                udf = "SELECT " + ANIMAL_CODE_COL +
                        " , " + ANIMAL_TYPE_COL
                        + " FROM " + LUP_COMMUNITY_ANIMAL_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + dyBasic.getcCode() + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dyBasic.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dyBasic.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dyBasic.getProgramActivityCode() + "' ";


                break;

            case "Community Lead Position":

                udf = "SELECT " + LEAD_CODE_COL +
                        " , " + LEAD_POSITION_COL
                        + " FROM " + LUP_COMMUNITY_LEAD_POSITION_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + dyBasic.getcCode() + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dyBasic.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dyBasic.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dyBasic.getProgramActivityCode() + "' ";


                break;

            case "Commnity Loan Source":

                udf = "SELECT " + LOAN_CODE_COL +
                        " , " + LOAN_SOURCE_COL
                        + " FROM " + LUP_COMMUNITY_LOAN_SOURCE_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + dyBasic.getcCode() + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dyBasic.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dyBasic.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dyBasic.getProgramActivityCode() + "' ";


                break;


            case "Commnity Fund Source":

                udf = "SELECT " + FUND_CODE_COL +
                        " , " + FUND_SOURCE_COL
                        + " FROM " + LUP_COMMUNITY_FUND_SOURCE_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + dyBasic.getcCode() + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dyBasic.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dyBasic.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dyBasic.getProgramActivityCode() + "' ";


                break;
            case "Community Irrigation System":
                udf = "SELECT " + IRRI_SYS_CODE_COL +
                        " , " + IRRI_SYS_NAME_COL
                        + " FROM " + LUP_COMMUNITY_IRRIGATION_SYSTEM_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + dyBasic.getcCode() + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dyBasic.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dyBasic.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dyBasic.getProgramActivityCode() + "' ";


                break;


            case "Prog Group Crop List":
                udf = "SELECT " + CROP_CODE_COL +
                        " , " + CROP_NAME_COL
                        + " FROM " + LUP_PROG_GROUP_CROP_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + dyBasic.getcCode() + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dyBasic.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dyBasic.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dyBasic.getProgramActivityCode() + "' ";


                break;


        }


        return udf;

    }


    public static String loadDynamicSpinnerValueInReport_sql(String cCode, String resLupText, String value, DTSurveyTableDataModel dataModel) {
        String udf = "";

        switch (resLupText) {

            case GEO_LAYER_3:

                udf = "SELECT " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL
                        + ", " + LAY_R3_LIST_NAME
                        + " FROM " + GEO_LAY_R3_LIST_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                        + " AND " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL + " = '" + value + "'"

                ;


                break;
            case GEO_LAYER_2:
                udf = "SELECT " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL
                        + ", " + LAY_R2_LIST_NAME_COL
                        + " FROM " + GEO_LAY_R2_LIST_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + "= '" + cCode + "'"
                        + " AND " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + "= '" + value + "'";


                break;

            case GEO_LAYER_1:

                udf = "SELECT " + LAY_R_LIST_CODE_COL
                        + ", " + LAY_R_LIST_NAME_COL
                        + " FROM " + GEO_LAY_R1_LIST_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                        + " AND " + LAY_R_LIST_CODE_COL + " = '" + value + "'";


                break;

            case GEO_LAYER_4:

                udf = "SELECT " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL + " || '' || " + LAY_R4_LIST_CODE_COL
                        + ", " + LAY_R4_LIST_NAME_COL
                        + " FROM " + GEO_LAY_R4_LIST_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                        + " AND " + LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL + " || '' || " + LAY_R4_LIST_CODE_COL + " = '" + value + "'";


                break;

            case GEO_LAYER_ADDRESS:

                udf = "SELECT " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL + " || '' || " + LAY_R4_LIST_CODE_COL + " || '' || " + REGN_ADDRESS_LOOKUP_CODE_COL
                        + ", " + REGN_ADDRESS_LOOKUP_NAME_COL
                        + " FROM " + LUP_REGN_ADDRESS_LOOKUP_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                        + " AND " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL + " || '' || " + LAY_R4_LIST_CODE_COL + " || '' || " + REGN_ADDRESS_LOOKUP_CODE_COL + "='" + value + "'";


                break;

            case COMMUNITY_GROUP:
                udf = loadCommunityGroup(" INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                        + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                        + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"

                        + " AND " + " commGrp." + ADM_DONOR_CODE_COL + " || '' || "
                        + " commGrp." + ADM_AWARD_CODE_COL + " || '' ||"
                        + " commGrp." + ADM_PROG_CODE_COL + " || '' ||"
                        + " commGrp." + GROUP_CODE_COL + " || '' || "
                        + " commGrp." + LAY_R1_CODE_COL + " || '' || "
                        + " commGrp." + GRP_LAY_R2_LIST_CODE_COL + " || '' || "
                        + " commGrp." + GRP_LAY_R3_LIST_CODE_COL + " = '" + value + "'"

                );

                break;


            case COMMUNITY_GROUP_PG:


                udf = loadCommunityGroup(" INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                        + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                        + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                        + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '001' " // Producer group= 001
                        + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001

                        + " AND " + " commGrp." + ADM_DONOR_CODE_COL + " || '' || "
                        + " commGrp." + ADM_AWARD_CODE_COL + " || '' ||"
                        + " commGrp." + ADM_PROG_CODE_COL + " || '' ||"
                        + " commGrp." + GROUP_CODE_COL + " || '' || "
                        + " commGrp." + LAY_R1_CODE_COL + " || '' || "
                        + " commGrp." + GRP_LAY_R2_LIST_CODE_COL + " || '' || "
                        + " commGrp." + GRP_LAY_R3_LIST_CODE_COL + " = '" + value + "'"
                );
                break;
            case COMMUNITY_GROUP_IG:
                udf = loadCommunityGroup(
                        " INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                                + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                                + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                                + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '003' " // Irrigation Group= 003
                                + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001

                                + " AND " + " commGrp." + ADM_DONOR_CODE_COL + " || '' || "
                                + " commGrp." + ADM_AWARD_CODE_COL + " || '' ||"
                                + " commGrp." + ADM_PROG_CODE_COL + " || '' ||"
                                + " commGrp." + GROUP_CODE_COL + " || '' || "
                                + " commGrp." + LAY_R1_CODE_COL + " || '' || "
                                + " commGrp." + GRP_LAY_R2_LIST_CODE_COL + " || '' || "
                                + " commGrp." + GRP_LAY_R3_LIST_CODE_COL + " = '" + value + "'"
                );
                break;

            case COMMUNITY_GROUP_MG:
                udf = loadCommunityGroup(
                        " INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                                + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                                + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                                + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '002' " // Marketing Group= 002
                                + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001

                                + " AND " + " commGrp." + ADM_DONOR_CODE_COL + " || '' || "
                                + " commGrp." + ADM_AWARD_CODE_COL + " || '' ||"
                                + " commGrp." + ADM_PROG_CODE_COL + " || '' ||"
                                + " commGrp." + GROUP_CODE_COL + " || '' || "
                                + " commGrp." + LAY_R1_CODE_COL + " || '' || "
                                + " commGrp." + GRP_LAY_R2_LIST_CODE_COL + " || '' || "
                                + " commGrp." + GRP_LAY_R3_LIST_CODE_COL + " = '" + value + "'"
                );
                break;

            case COMMUNITY_GROUP_WE:
                udf = loadCommunityGroup(" INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                        + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                        + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                        + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '004' " // WeVSL= 004
                        + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001

                        + " AND " + " commGrp." + ADM_DONOR_CODE_COL + " || '' || "
                        + " commGrp." + ADM_AWARD_CODE_COL + " || '' ||"
                        + " commGrp." + ADM_PROG_CODE_COL + " || '' ||"
                        + " commGrp." + GROUP_CODE_COL + " || '' || "
                        + " commGrp." + LAY_R1_CODE_COL + " || '' || "
                        + " commGrp." + GRP_LAY_R2_LIST_CODE_COL + " || '' || "
                        + " commGrp." + GRP_LAY_R3_LIST_CODE_COL + " = '" + value + "'"
                );
                break;

            case COMMUNITY_GROUP_LG:
                udf = loadCommunityGroup(" INNER JOIN " + STAFF_SRV_CENTER_ACCESS_TABLE + " AS srvAcc "
                        + " ON commGrp." + SERVICE_CENTER_CODE_COL + " =  srvAcc." + SERVICE_CENTER_CODE_COL
                        + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                        + " AND  " + "commGrp." + GROUP_CAT_CODE_COL + "= '005' " // Livestock Group= 005
                        + " AND  " + "commGrp." + ADM_PROG_CODE_COL + "= '003' " // agr= 001

                        + " AND " + " commGrp." + ADM_DONOR_CODE_COL + " || '' || "
                        + " commGrp." + ADM_AWARD_CODE_COL + " || '' ||"
                        + " commGrp." + ADM_PROG_CODE_COL + " || '' ||"
                        + " commGrp." + GROUP_CODE_COL + " || '' || "
                        + " commGrp." + LAY_R1_CODE_COL + " || '' || "
                        + " commGrp." + GRP_LAY_R2_LIST_CODE_COL + " || '' || "
                        + " commGrp." + GRP_LAY_R3_LIST_CODE_COL + " = '" + value + "'"
                );
                break;


            case SERVICE_SITE:

                udf = "SELECT " + SRV_CENTER_TABLE + "." + SERVICE_CENTER_CODE_COL
                        + ", " + SRV_CENTER_TABLE + "." + SERVICE_CENTER_NAME_COL
                        + " FROM " + SRV_CENTER_TABLE
                        + " WHERE " + SRV_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL + "= '" + cCode + "'"
                        + " AND " + SRV_CENTER_TABLE + "." + SERVICE_CENTER_CODE_COL + "= '" + value + "'";


                break;

            case DISTRIBUTION_POINT:

                udf = "SELECT " + FDP_MASTER_TABLE + "." + FDP_CODE_COL
                        + ", " + FDP_MASTER_TABLE + "." + FDP_NAME_COL
                        + " FROM " + FDP_MASTER_TABLE
                        + " WHERE " + FDP_MASTER_TABLE + "." + FDP_MASTER_COUNTRY_CODE + "= '" + cCode + "'"
                        + " AND " + FDP_MASTER_TABLE + "." + FDP_CODE_COL + "= '" + value + "'";


                break;


            case LOOKUP_LIST:

                udf = "SELECT " + DT_LUP_TABLE + "." + LIST_CODE_COL
                        + ", " + DT_LUP_TABLE + "." + LIST_NAME_COL
                        + " FROM " + DT_LUP_TABLE
                        + " WHERE " + DT_LUP_TABLE + "." + ADM_COUNTRY_CODE_COL + "= '" + cCode + "' "
                        + " AND " + DT_LUP_TABLE + "." + LIST_CODE_COL + "= '" + value + "'";


                break;


            case ORGANIZATION_LIST:

                udf = "SELECT  progOR." + ORG_N_CODE_COL
                        + ", pOrg." + ORGANIZATION_NAME + " "
                        + " FROM " + PROGRAM_ORGANIZATION_ROLE_TABLE + " AS progOR "
                        + " INNER JOIN " + " " + PROGRAM_ORGANIZATION_NAME_TABLE + " AS pOrg "
                        + " ON progOR." + ORG_N_CODE_COL + " = pOrg." + PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL
                        + " WHERE progOR." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND progOR." + ORG_N_CODE_COL + " = '" + value + "' "
                        + " GROUP BY pOrg." + ORGANIZATION_NAME;

                break;

            case DTResponseRecordingActivity.COMMNITY_ANIMAL:

                udf = "SELECT " + ANIMAL_CODE_COL +
                        " , " + ANIMAL_TYPE_COL
                        + " FROM " + LUP_COMMUNITY_ANIMAL_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dataModel.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dataModel.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dataModel.getProgramCode() + "' "
                        + " AND " + ANIMAL_CODE_COL + " = '" + value + "' ";


                break;

            case DTResponseRecordingActivity.COMMNITY_LEAD_POSITION:

                udf = "SELECT " + LEAD_CODE_COL +
                        " , " + LEAD_POSITION_COL
                        + " FROM " + LUP_COMMUNITY_LEAD_POSITION_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dataModel.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dataModel.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dataModel.getProgramCode() + "' "
                        + " AND " + LEAD_CODE_COL + " = '" + value + "' ";


                break;

            case "Commnity Loan Source":

                udf = "SELECT " + LOAN_CODE_COL +
                        " , " + LOAN_SOURCE_COL
                        + " FROM " + LUP_COMMUNITY_LOAN_SOURCE_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dataModel.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dataModel.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dataModel.getProgramCode() + "' "
                        + " AND " + LOAN_CODE_COL + " = '" + value + "' ";


                break;


            case "Commnity Fund Source":

                udf = "SELECT " + FUND_CODE_COL +
                        " , " + FUND_SOURCE_COL
                        + " FROM " + LUP_COMMUNITY_FUND_SOURCE_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dataModel.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dataModel.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dataModel.getProgramCode() + "' "
                        + " AND " + FUND_CODE_COL + " = '" + value + "' ";


                break;
            case "Community Irrigation System":
                udf = "SELECT " + IRRI_SYS_CODE_COL +
                        " , " + IRRI_SYS_NAME_COL
                        + " FROM " + LUP_COMMUNITY_IRRIGATION_SYSTEM_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dataModel.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dataModel.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dataModel.getProgramCode() + "' "
                        + " AND " + IRRI_SYS_CODE_COL + " = '" + value + "' ";


                break;


            case "Prog Group Crop List":
                udf = "SELECT " + CROP_CODE_COL +
                        " , " + CROP_NAME_COL
                        + " FROM " + LUP_PROG_GROUP_CROP_TABLE
                        + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND " + ADM_DONOR_CODE_COL + " = '" + dataModel.getDonorCode() + "' "
                        + " AND " + ADM_AWARD_CODE_COL + " = '" + dataModel.getAwardCode() + "' "
                        + " AND " + ADM_PROG_CODE_COL + " = '" + dataModel.getProgramCode() + "' "
                        + " AND " + CROP_CODE_COL + " = '" + value + "' ";


                break;


        }


        return udf;

    }

    private static String loadCommunityGroup(String whereCondition) {
        return " SELECT DISTINCT "

                + " commGrp." + ADM_DONOR_CODE_COL + " || '' || "
                + " commGrp." + ADM_AWARD_CODE_COL + " || '' ||"
                + " commGrp." + ADM_PROG_CODE_COL + " || '' ||"
                + " commGrp." + GROUP_CODE_COL + " || '' || "
                + " commGrp." + LAY_R1_CODE_COL + " || '' || "
                + " commGrp." + GRP_LAY_R2_LIST_CODE_COL + " || '' || "
                + " commGrp." + GRP_LAY_R3_LIST_CODE_COL

                + " , award." + AWARD_SHORT_COL + " || '-' ||"
                + " counAward." + AWARD_SHORT_NAME_COL + " || '-' ||"
                + " admProg." + PROGRAM_SHORT_NAME_COL + " || '-' ||"
                + " commGrp." + GROUP_NAME_COL
                + " FROM " + COMMUNITY_GROUP_TABLE + " AS commGrp "

                + " LEFT JOIN " + ADM_AWARD_TABLE + " AS award "
                + " ON award." + ADM_DONOR_CODE_COL + " = commGrp." + ADM_DONOR_CODE_COL
                + " AND award." + AWARD_CODE_COL + " = commGrp." + ADM_AWARD_CODE_COL

                + " INNER JOIN " + ADM_COUNTRY_AWARD_TABLE + " AS counAward "

                + " ON counAward." + ADM_DONOR_CODE_COL + " = commGrp." + ADM_DONOR_CODE_COL
                + " AND counAward." + ADM_AWARD_CODE_COL + " = commGrp." + ADM_AWARD_CODE_COL

                + " INNER JOIN " + ADM_PROGRAM_MASTER_TABLE + " AS admProg "

                + " ON admProg." + ADM_DONOR_CODE_COL + " = commGrp." + ADM_DONOR_CODE_COL
                + " AND admProg." + ADM_AWARD_CODE_COL + " = commGrp." + ADM_AWARD_CODE_COL
                + " AND admProg." + ADM_PROG_CODE_COL + " = commGrp." + ADM_PROG_CODE_COL

                // where condition would be dy namic
                //     + " WHERE " + "commGrp." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'"
                + whereCondition
                + " GROUP BY commGrp." + ADM_DONOR_CODE_COL
                + " , commGrp." + ADM_AWARD_CODE_COL
                + " , commGrp." + ADM_PROG_CODE_COL
                + " , commGrp." + GROUP_CODE_COL
                + " , commGrp." + LAY_R1_CODE_COL
                + " , commGrp." + GRP_LAY_R2_LIST_CODE_COL
                + " , commGrp." + GRP_LAY_R3_LIST_CODE_COL
                + " ORDER BY award." + AWARD_SHORT_COL + " || '-' ||"
                + " counAward." + AWARD_SHORT_NAME_COL + " || '-' ||"
                + " admProg." + PROGRAM_SHORT_NAME_COL + " || '-' ||"
                + " commGrp." + GROUP_NAME_COL;
    }

    public static String loadDtMonth_sql(String cCode, String opCode, String opMonthCode) {

        String criteria = "";
        if (opMonthCode.length() > 1)
            criteria = " AND " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' ";

        return "SELECT " + OP_MONTH_CODE_COL + " AS OpMonthID, "
                + MONTH_LABEL_COL + " FROM " + OP_MONTH_TABLE
                + " WHERE " +
                ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + STATUS + " = '" + "A" + "' "
                + " AND " + OPERATION_CODE_COL + " = '5' "
                + criteria
                + " ORDER BY OpMonthID   DESC ";
    }


    public static String loadCountry_sql(int appOpMode, boolean multipleCountryAccessUserFlag) {
        String sql = "";
        switch (appOpMode) {
            case UtilClass.REGISTRATION_OPERATION_MODE:
            case UtilClass.TRANING_n_ACTIVITY_OPERATION_MODE:
                sql = " INNER JOIN " + SQLiteHandler.SELECTED_VILLAGE_TABLE + " ON "
                        + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_CODE + " = "
                        + SELECTED_VILLAGE_TABLE + ".CountryCode";


                break;
            case UtilClass.DISTRIBUTION_OPERATION_MODE:
                sql = " INNER JOIN " + SELECTED_FDP_TABLE + " ON "
                        + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_CODE + " = "
                        + SELECTED_FDP_TABLE + "." + ADM_COUNTRY_CODE_COL;

                break;
            case UtilClass.SERVICE_OPERATION_MODE:
                sql = " INNER JOIN " + SELECTED_SERVICE_CENTER_TABLE + " ON "
                        + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_CODE + " = "
                        + SELECTED_SERVICE_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL;


                break;

            case UtilClass.OTHER_OPERATION_MODE:
                /**                 * check  user  has access in multiple countries                 */
                if (multipleCountryAccessUserFlag) {
                    sql = " INNER JOIN " + SELECTED_COUNTRY_TABLE + " ON "
                            + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_CODE + " = "
                            + SELECTED_COUNTRY_TABLE + ".CountryCode";


                } else {


                    sql = " INNER JOIN " + SQLiteHandler.STAFF_GEO_INFO_ACCESS_TABLE + " AS staffAcces ON "
                            + SQLiteHandler.COUNTRY_TABLE + "." + SQLiteHandler.COUNTRY_COUNTRY_CODE + " = " + "staffAcces." + SQLiteHandler.ADM_COUNTRY_CODE_COL
                            + " WHERE (" + SQLiteHandler.BTN_NEW_COL + " = '1' " +
                            " OR " + SQLiteHandler.BTN_SAVE_COL + " = 1" +
                            " OR " + SQLiteHandler.BTN_DEL_COL + " = 1 ) GROUP BY " + " staffAcces." + SQLiteHandler.ADM_COUNTRY_CODE_COL;


                }

                break;
        }
        return sql;
    }

    public static String showNearBy_gpsSubGroup_sql(String cCode) {
        return "SELECT DISTINCT gpsSubGrp." + GROUP_CODE_COL + " || '' || gpsSubGrp." + SUB_GROUP_CODE_COL
                + " , gpsGrp." + GROUP_NAME_COL + "|| '-' || gpsSubGrp." + SUB_GROUP_NAME_COL
                + " FROM " + GPS_LOCATION_TABLE + " AS gpsLoc " + " INNER JOIN " + GPS_SUB_GROUP_TABLE + " AS gpsSubGrp "
                + " ON gpsLoc." + GROUP_CODE_COL + " = gpsSubGrp." + GROUP_CODE_COL
                + " AND  gpsLoc." + SUB_GROUP_CODE_COL + " = gpsSubGrp." + SUB_GROUP_CODE_COL
                + " INNER JOIN " + GPS_GROUP_TABLE + " AS gpsGrp "
                + " ON gpsLoc." + GROUP_CODE_COL + " = gpsGrp." + GROUP_CODE_COL
                + " WHERE gpsLoc." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " ORDER BY gpsSubGrp." + SUB_GROUP_NAME_COL;


    }

    public static String getDynamicTableIndexList_sql(String cCode, String dtTitleSearch, String staffId) {
        return "SELECT dtB." + DT_TITLE_COL + "  " +
                " , dtCPgr." + DT_BASIC_COL + " AS dtBasicCode  " +
                " , donor." + DONOR_NAME_COL + " || '-' || award." + AWARD_SHORT_NAME_COL + " AS awardName  " +
                " , dtCPgr." + ADM_DONOR_CODE_COL + " || '' || dtCPgr." + ADM_AWARD_CODE_COL + " AS awardCode  " +
                " , prg." + PROGRAM_SHORT_NAME_COL + "  " +
                " , dtCPgr." + ADM_PROG_CODE_COL + "  " +
                " , dtCPgr." + PROG_ACTIVITY_TITLE_COL +
                " , dtCPgr." + ADM_COUNTRY_CODE_COL +
                " , dtB." + DT_OP_MODE_COL +

                " , dtCPgr." + ADM_DONOR_CODE_COL +
                " , dtCPgr." + PROG_ACTIVITY_CODE_COL +
                " , dtB." + DT_SHORT_NAME_COL
                + "  FROM " +
                DT_COUNTRY_PROGRAM_TABLE + " AS dtCPgr  " +
                " LEFT JOIN " + DT_BASIC_TABLE + "  AS dtB  " +
                " ON dtB." + DT_BASIC_COL + " = dtCpgr." + DT_BASIC_COL + "   " +
                " LEFT JOIN " +
                ADM_COUNTRY_AWARD_TABLE + " as award  ON " +
                "  award." + ADM_COUNTRY_CODE_COL + " = dtCpgr." + ADM_COUNTRY_CODE_COL + "  " +
                " AND  award." + ADM_DONOR_CODE_COL + " = dtCpgr." + ADM_DONOR_CODE_COL + "  " +
                " AND award." + ADM_AWARD_CODE_COL + "= dtCpgr." + ADM_AWARD_CODE_COL + "  " +
                " LEFT JOIN " +
                ADM_DONOR_TABLE + " AS donor  " +
                " ON donor." + ADM_DONOR_CODE_COL + " = dtCpgr." + ADM_DONOR_CODE_COL + "  " +
                " LEFT JOIN " +
                ADM_PROGRAM_MASTER_TABLE + " AS prg  " +
                " ON prg." + ADM_DONOR_CODE_COL + " = dtCpgr." + ADM_DONOR_CODE_COL + "  " +
                " AND prg." + ADM_AWARD_CODE_COL + " = dtCpgr." + ADM_AWARD_CODE_COL + "  " +
                " AND prg." + ADM_PROG_CODE_COL + " = dtCpgr." + ADM_PROG_CODE_COL + "  " +
                "  INNER JOIN " + DT_ENU_TABLE + " AS dtEnu ON " +
                " dtEnu." + DT_BASIC_COL + " = dtCPgr." + DT_BASIC_COL +
                " WHERE dtCPgr." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND dtEnu." + DT_STF_CODE_COL + " = '" + staffId + "' "
                + " AND dtB." + DT_TITLE_COL + " LIKE '%" + dtTitleSearch + "%'";
    }

    /**
     * {@link PointAttributes#loadLocation(String, String, String)} method use this query
     *
     * @param cCode      country Code
     * @param grpCode    group Code
     * @param subGrpCode Sub group Code
     * @return location name
     */
    public static String loadLocationLoader_sql(String cCode, String grpCode, String subGrpCode) {
        return "SELECT " + LOCATION_CODE_COL + "," + LOCATION_NAME_COL + " FROM " +
                GPS_LOCATION_TABLE +
                " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + GROUP_CODE_COL + "='" + grpCode + "' " +
                " AND " + SUB_GROUP_CODE_COL + "='" + subGrpCode + "'";
    }

    /**
     * the sql used only Map Activity
     * {@link MapActivity#loadLocation(String)} method used this query
     *
     * @param cCode country code
     * @return location name, groupCode + subGroupCode + locationCode
     */
    public static String loadLocationLoader_sql(String cCode) {
        return "SELECT " + GROUP_CODE_COL + " || '' ||" + SUB_GROUP_CODE_COL + " || '' ||" + LOCATION_CODE_COL
                + "," + LOCATION_NAME_COL
                + " FROM " + GPS_LOCATION_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " ORDER BY " + LOCATION_NAME_COL + " ASC ";
    }

    /**
     * this method sql to load Service Name and code to Criteria spinner in
     * {@link com.siddiquinoor.restclient.activity.AssignActivity#loadCriteria(String, String, String, String)}
     *
     * @param awardCode   award Code
     * @param donorCode   donor Code
     * @param programCode program Code
     * @return srvCode & srvName
     */
    public static String loadCriteria_sql(String awardCode, String donorCode, String programCode) {
        return "SELECT " + COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL + " AS criteriaId" + " , " +
                SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_NAME_COL + " || '-' ||  " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS Criteria" +
                " FROM " + COUNTRY_PROGRAM_TABLE + " JOIN " + SERVICE_MASTER_TABLE +
                " ON " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL + " " +
                " WHERE " + COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + "='" + donorCode + "'"
                + " AND " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + "='" + programCode + "'"
                + " AND " + COUNTRY_PROGRAM_TABLE + "." + PROG_FLAG + "= '1'"
                + " GROUP BY " + COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL;

    }

    /**
     * @return layRlist Code and name
     */
    public static String loadLayR4List_sql() {
        return "SELECT  v." +
                ADM_COUNTRY_CODE_COL + " || '' || v." +
                MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || '' || v." + LAY_R2_LIST_CODE_COL + " || '' || v." + LAY_R3_LIST_CODE_COL + " || '' || v." + LAY_R4_LIST_CODE_COL
                + ", v." + LAY_R4_LIST_NAME_COL + " FROM " + GEO_LAY_R4_LIST_TABLE + " AS v " +
                "  INNER JOIN " + SELECTED_VILLAGE_TABLE + " as S "
                + " ON S.CountryCode "

                + " ||''|| S.DistrictCode "
                + " ||''|| S.UpazillaCode "
                + " ||''|| S.UnitCode "
                + " ||''|| S.VillageCode "

                + "  = v." + ADM_COUNTRY_CODE_COL
                + " ||''|| v." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " ||''|| v." + LAY_R2_LIST_CODE_COL
                + " ||''|| v." + LAY_R3_LIST_CODE_COL
                + " ||''|| v." + LAY_R4_LIST_CODE_COL + " " +
                " GROUP BY  v." + ADM_COUNTRY_CODE_COL
                + " ||''|| v." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + " ||''|| v." + LAY_R2_LIST_CODE_COL
                + " ||''|| v." + LAY_R3_LIST_CODE_COL
                + " ||''|| v." + LAY_R4_LIST_CODE_COL + " ";
    }

    /**
     * this query check is specific member exits in RefNAssProgSrv for specific program an Service
     *
     * @param cCode     country Code
     * @param layR1Code layR1Code
     * @param layR2Code layR1Code
     * @param layR3Code layR1Code
     * @param lay4Code  layR1Code
     * @param hh        house hold id
     * @param memID     member id
     * @param progCode  program Code
     * @param srvCode   service Code
     * @return
     */
    public static String ifExistsInRegNAssProgSrv_sql(String cCode, String layR1Code, String layR2Code, String layR3Code, String lay4Code, String hh, String memID, String progCode, String srvCode) {
        return "SELECT * FROM " + REG_N_ASSIGN_PROG_SRV_TABLE
                + " WHERE    " + ADM_COUNTRY_CODE_COL + " = '" + cCode
                + "' AND " + LAY_R1_LIST_CODE_COL + " = '" + layR1Code + "'" +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + layR2Code + "'" +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + layR3Code + "'" +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + lay4Code + "'" +
                " AND " + HHID_COL + " = '" + hh + "'"
                + " AND " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + memID + "'  "
                + " AND " + PROG_CODE_COL + " = '" + progCode + "'  "
                + " AND " + SRV_CODE_COL + " = '" + srvCode + "'  ";
    }

    public static String getSelectedVillageList_sql() {
        return "SELECT " + GEO_LAY_R_CODE_COL
                + " , " + VILLAGE_NAME_COL + " FROM " + SELECTED_VILLAGE_TABLE
                + " GROUP BY " + GEO_LAY_R_CODE_COL;
    }

    public static String getSelectedCountryCodeFromSelectedVillage_sql() {
        return "SELECT CountryCode "
                + " FROM " + SELECTED_VILLAGE_TABLE
                + " GROUP BY  CountryCode ";
    }


    public static String getSelectedCountryCodeFromSelectedFDP_sql() {
        return "SELECT " + ADM_COUNTRY_CODE_COL
                + " FROM " + SELECTED_FDP_TABLE
                + " GROUP BY   " + ADM_COUNTRY_CODE_COL;
    }


    public static String getSelectedCountryCodeFromSelectedCenter_sql() {
        return "SELECT " + ADM_COUNTRY_CODE_COL
                + " FROM " + SELECTED_SERVICE_CENTER_TABLE
                + " GROUP BY   " + ADM_COUNTRY_CODE_COL;
    }

    public static String loadGroupLoader_sql(String cCode, String donorCode, String awardCode, String progCode, String grpCateCode) {
        return " SELECT  " + GROUP_CODE_COL + " , " + GROUP_NAME_COL
                + " FROM " + COMMUNITY_GROUP_TABLE
                + " INNER JOIN " + SELECTED_VILLAGE_TABLE
                + " ON " + LAY_R1_CODE_COL + " = DistrictCode "
                + " AND " + GRP_LAY_R2_LIST_CODE_COL + " = UpazillaCode "
                + " AND " + GRP_LAY_R3_LIST_CODE_COL + " = UnitCode "
                + " WHERE " + COMMUNITY_GROUP_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + ADM_PROG_CODE_COL + " = '" + progCode + "' "
                + " AND " + GROUP_CAT_CODE_COL + " = '" + grpCateCode + "' "
                + " GROUP BY  " + GROUP_CODE_COL
                + " ORDER BY  " + GROUP_NAME_COL;
    }

    public static String loadProgram_sql(String cCode) {
        return "SELECT " +
                ADM_PROGRAM_MASTER_TABLE + "." + ADM_DONOR_CODE_COL + " || '' || "
                + ADM_PROGRAM_MASTER_TABLE + "." + ADM_AWARD_CODE_COL + " || '' || "
                + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " AS criteriaId" + " , " +
                ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL + " AS Criteria" +
                " FROM " + ADM_PROGRAM_MASTER_TABLE

                + " INNER JOIN " + COUNTRY_PROGRAM_TABLE
                + " ON " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_DONOR_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_AWARD_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL
                + " AND " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " =  " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL

                + " WHERE " + COUNTRY_PROGRAM_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "

                + " GROUP BY " + ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL
                + " ORDER BY Criteria ";
    }


    public static String loadProgram_sql(String awardCode, String donorCode) {
        return "SELECT " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + " , " +
                ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL +
                " FROM " + COUNTRY_PROGRAM_TABLE + " JOIN " + ADM_PROGRAM_MASTER_TABLE + " ON " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL +
                " AND " + COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_AWARD_CODE_COL +
                " AND " + COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_DONOR_CODE_COL + " " +

                " WHERE " + COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + "= '" + awardCode + "'"
                + " AND " + COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + "= '" + donorCode + "'"

                + " GROUP BY " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL;
    }

    public static String getLocationList_sql(String cCode, String searchLocName) {
        return "SELECT " + GPS_LOCATION_TABLE + "." + GROUP_CODE_COL + " || '' || " + GPS_LOCATION_TABLE + "." + SUB_GROUP_CODE_COL + " || '' || " + GPS_LOCATION_TABLE + "." + SQLiteHandler.LOCATION_CODE_COL
                + " , " + GPS_LOCATION_TABLE + "." + LOCATION_NAME_COL

                + ", CASE WHEN  ifnull(length(" + GPS_LOCATION_TABLE + "." + LATITUDE_COL + "), 0) = 0  THEN 'N' ELSE 'Y' END AS dataExit "
                + " , " + GPS_GROUP_TABLE + "." + GROUP_NAME_COL
                + " FROM " + GPS_LOCATION_TABLE
                + " LEFT JOIN " + GPS_GROUP_TABLE
                + " ON " + GPS_GROUP_TABLE + "." + GROUP_CODE_COL + " = " + GPS_LOCATION_TABLE + "." + GROUP_CODE_COL
                + " WHERE " + GPS_LOCATION_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + GPS_LOCATION_TABLE + "." + LOCATION_NAME_COL + " LIKE '%" + searchLocName + "%' "
                + " ORDER BY " + GPS_LOCATION_TABLE + "." + LOCATION_NAME_COL + " ASC ";
    }

    public static String getSingleDynamicQuestion_sql(String dtBasicCode, int index) {
        // this query didn't maintain the sequence
       /*  "SELECT * FROM " + DTQ_TABLE +
                " WHERE " + DT_BASIC_COL + "= '" + dtBasicCode + "'" +
                " LIMIT 1 OFFSET " + String.valueOf(index);*/

        return "SELECT * FROM " + DTQ_TABLE +
                " WHERE " + DT_BASIC_COL + "= '" + dtBasicCode + "'" +
                " AND " + QSEQ_SCOL + "= " + index;/*+
                " LIMIT 1 OFFSET " + String.valueOf(index);*/

    }

    public static String dtSurveyTableDataModels_sql(int surveyNum, String dtBasic) {
        return "SELECT dtSrv." + DT_BASIC_COL +
                " , dtSrv." + "CountryCode" +
                " , dtSrv." + "DonorCode" +
                " , dtSrv." + "AwardCode" +
                " , dtSrv." + "ProgramCode" +
                " , dtSrv." + DT_ENU_ID_COL +
                " , dtSrv." + DTQ_CODE_COL +
                " , dtSrv." + DTA_CODE_COL +
                " , dtSrv." + DT_R_SEQ_COL +
                " , dtSrv." + DTA_VALUE_COL +
                " , dtSrv." + PROG_ACTIVITY_CODE_COL +
                " , dtSrv." + DTTIME_STRING_COL +
                " , dtSrv." + OP_MODE_COL +
                " , dtSrv." + OP_MONTH_CODE_COL +
                " , dtSrv." + DATA_TYPE_COL +
                " , dtSrv." + DTQ_TEXT_COL +
                " , dtSrv." + DT_SURVEY_NUM +
                " , dtSrv." + U_FILE_COL +
                " , dtSrv." + RESPONSE_VALUE_CONTROL_COL +
                " , dtSrv." + QRES_LUP_TEXT_COL +
                " , dtSrv." + DTA_LABEL_COL +
                " , dtan." + DTA_LABEL_COL +

                " FROM " + DT_SURVEY_TABLE + " AS dtSrv " +
                " left join " + DT_A_TABLE + " AS dtan ON " +
                " dtSrv." + DT_BASIC_COL + " =  dtan." + DT_BASIC_COL +
                " AND  dtSrv." + DTQ_CODE_COL + " =  dtan." + DTQ_CODE_COL +
                " AND  dtSrv." + DTA_CODE_COL + " =  dtan." + DTA_CODE_COL +
                " AND  dtSrv." + DTA_VALUE_COL + " =  dtan." + DTA_VALUE_COL +

                " WHERE dtSrv." + DT_SURVEY_NUM + " = " + surveyNum +
                " AND dtSrv." + DT_BASIC_COL + " = '" + dtBasic + "' ";
    }

    // TODO: 10/17/2016  use theSQLite Column  Constant
    public static String getMemberData_sql(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String hhID) {
        return "SELECT DISTINCT rm.*, r.HHHeadName, r." + REGISTRATION_TABLE_HHID + " AS PID" +
                " , rel." + RELATION_NAME + " FROM "
                + REGISTRATION_MEMBER_TABLE + " AS rm " +
                " LEFT JOIN " + REG_N_HH_TABLE + " AS r " +
                " ON r.AdmCountryCode=rm.AdmCountryCode " +
                " AND r.LayR1ListCode=rm.LayR1ListCode " +
                " AND r.LayR2ListCode=rm.LayR2ListCode " +
                " AND r.LayR3ListCode=rm.LayR3ListCode " +
                " AND r.LayR4ListCode=rm.LayR4ListCode " +
                " AND r.HHID=rm.HHID " +
                " LEFT JOIN " + LUP_REG_NHH_RELATION_TABLE + " AS rel " +
                " ON rm." + RELATION_COL + "=rel." + RELATION_CODE +
                " WHERE rm." + ADM_COUNTRY_CODE_COL + "='" + str_c_code + "' " +
                " AND rm." + LAY_R1_LIST_CODE_COL + "='" + str_district + "' " +
                " AND rm." + LAY_R2_LIST_CODE_COL + "='" + str_upazilla + "' " +
                " AND rm." + LAY_R3_LIST_CODE_COL + "='" + str_union + "' " +
                " AND rm." + LAY_R4_LIST_CODE_COL + "='" + str_village + "' " +
                " AND rm." + HHID_COL + "='" + hhID + "' " +
                " ORDER BY rm." + HHID_COL + " DESC";
    }

    public static String getDTA_Table_sql(String dtBasic, String dtQCode) {
        return "SELECT * FROM " + DT_A_TABLE +
                " WHERE " + DT_BASIC_COL + "= '" + dtBasic + "'" +
                " AND " + DTQ_CODE_COL + "= '" + dtQCode + "'";
    }

    public static String getTrainingActivityIndexList(final String cCode, final String eventTitleSearch) {


        //  + ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
        //     + " , " + EVENT_CODE_COL + " VARCHAR(8) "
        //      + " , " + EVENT_NAME_COL + " VARCHAR(100) "
        //  + " , " + ADM_DONOR_CODE_COL + " VARCHAR(2) "
        //     + " , " + ADM_AWARD_CODE_COL + " VARCHAR(2) "
        //   + " , " + TA_GROUP_COL + " VARCHAR(3) "
        //    + " , " + TA_SUB_GROUP_COL + " VARCHAR(3) "
        //     + " , " + PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL + " VARCHAR(10) "
        //    + " , " + START_DATE_COL + " VARCHAR(10) "
        //         + " , " + END_DATE_COL + " VARCHAR(10) "
////                + " , " + VENUE_NAME_COL + " VARCHAR(100) "
////                + " , " + VENUE_ADDRESS_COL + " VARCHAR(200) "
        //    + " , " + ACTIVE_COL + " VARCHAR(10) "
        //      + " , " + TOTAL_DAYS_COL + " VARCHAR(10) "
        //       + " , " + HOURS_PER_DAY_COL + " VARCHAR(10) "

        //  TA_MASTER_TABLE
        return " SELECT " + ADM_COUNTRY_CODE_COL
                + " , " + EVENT_CODE_COL
                + " , " + EVENT_NAME_COL
                + " , " + ADM_DONOR_CODE_COL
                + " , " + ADM_AWARD_CODE_COL
                + " , " + TA_GROUP_COL
                + " , " + TA_SUB_GROUP_COL
                + " , " + PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL
                + " , " + START_DATE_COL
                + " , " + END_DATE_COL
                + " , " + VENUE_NAME_COL
                + " , " + VENUE_ADDRESS_COL
                + " , " + ACTIVE_COL
                + " , " + TOTAL_DAYS_COL
                + " , " + HOURS_PER_DAY_COL
                + " FROM " + TA_MASTER_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + ACTIVE_COL + " = 'Y' "
                + " AND " + EVENT_NAME_COL + " LIKE '%" + eventTitleSearch + "%' ";
    }

    public static String getEligibleTrainingAcitMemList_sql(String cCode, String layR1Code, String layR2Code, String layR3Code, String layR4Code, String searchID_orName, int number) {
        String getMemName;

        // 0004= Liberia's Country Code

        if (cCode.equals("0004")) {

            getMemName = " regMem." + MEM_NAME_FIRST_COL + " || ' ' || " +
                    " regMem." + MEM_NAME_MIDDLE_COL + " || ' ' || " +
                    " regMem." + MEM_NAME_LAST_COL;

        } else {
            getMemName = " regMem." + MEM_NAME_COL;

        }

        if (searchID_orName.matches("[a-zA-Z]+")) {                                                     // which'll return true cause it contains only alphabet.

            searchID_orName = " AND " + " memName " + " LIKE '%" + searchID_orName + "%' ";
        } else {

            searchID_orName = " AND " + " regAss." + ADM_COUNTRY_CODE_COL + " || \"\" || "
                    + " regAss." + LAY_R1_LIST_CODE_COL + " || \"\" || "
                    + " regAss." + LAY_R2_LIST_CODE_COL + " || \"\" || "
                    + " regAss." + LAY_R3_LIST_CODE_COL + " || \"\" || "
                    + " regAss." + LAY_R4_LIST_CODE_COL + " || \"\" || "
                    + " regAss." + HHID_COL + " || \"\" || "
                    + " regAss." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " LIKE '" + searchID_orName + "%' ";
        }

        String limit = "";
        if (searchID_orName.length() > 0 && !searchID_orName.equals(""))
            limit = "";
        else
            limit = " LIMIT 50 OFFSET " + number;

        String Mor = "SELECT " + " regMem." + HHID_COL
                + " , " + " regMem." + HH_MEM_ID
                + " , " + " regMem." + LAY_R1_LIST_CODE_COL
                + " || '' || " + " regMem." + LAY_R2_LIST_CODE_COL
                + " || '' || " + " regMem." + LAY_R3_LIST_CODE_COL
                + " || '' || " + " regMem." + LAY_R4_LIST_CODE_COL
                + " || '' || " + " regMem." + HHID_COL
                + " || '' || " + " regMem." + HH_MEM_ID + " AS newId "

                + " , " + getMemName + " As memName "
                + " , " + " regMem." + MEM_AGE
                + " , " + " regMem." + SEX_COL +

                // " , CASE WHEN LENGTH ( " + " regAss." + HH_MEM_ID + ") > 0 " + " THEN 'Y' ELSE 'N' END  AS Assign "                +
                " , " + " regMem." + ADM_COUNTRY_CODE_COL
                + " , " + " regMem." + LAY_R1_LIST_CODE_COL
                + " , " + " regMem." + LAY_R2_LIST_CODE_COL
                + " , " + " regMem." + LAY_R3_LIST_CODE_COL
                + " , " + " regMem." + LAY_R4_LIST_CODE_COL
                + " , " + " regHH." + REGISTRATION_TABLE_HH_HEAD_NAME

                + " , " + " vill." + LAY_R4_LIST_NAME_COL + " AS lay4Name "
                + " " +


                " FROM " + REG_N_HH_TABLE + " AS regHH " +
                " INNER JOIN " + REGISTRATION_MEMBER_TABLE + " AS regMem " +
                " ON " + " regHH." + ADM_COUNTRY_CODE_COL + " = " + " regMem." + ADM_COUNTRY_CODE_COL +
                " AND " + " regHH." + LAY_R1_LIST_CODE + " = " + " regMem." + LAY_R1_LIST_CODE_COL +
                " AND " + " regHH." + REGISTRATION_TABLE_UPZILLA_CODE_COL + " = " + " regMem." + LAY_R2_LIST_CODE_COL +
                " AND " + " regHH." + REGISTRATION_TABLE_UNION_CODE_COL + " = " + " regMem." + LAY_R3_LIST_CODE_COL +
                " AND " + " regHH." + REGISTRATION_TABLE_VILLAGE_CODE_COL + " = " + " regMem." + LAY_R4_LIST_CODE_COL +
                " AND " + " regHH." + REGISTRATION_TABLE_HHID + " = " + " regMem." + HHID_COL + " " +


                " INNER JOIN " + REG_N_ASSIGN_PROG_SRV_TABLE + " AS regAss ON " +
                " regAss." + ADM_COUNTRY_CODE_COL + " = " + " regHH." + ADM_COUNTRY_CODE_COL +
                " AND " + " regAss." + LAY_R1_LIST_CODE_COL + " = " + " regHH." + LAY_R1_LIST_CODE +
                " AND " + " regAss." + LAY_R2_LIST_CODE_COL + " = " + " regHH." + REGISTRATION_TABLE_UPZILLA_CODE_COL +
                " AND " + " regAss." + LAY_R3_LIST_CODE_COL + " = " + " regHH." + REGISTRATION_TABLE_UNION_CODE_COL +
                " AND " + " regAss." + LAY_R4_LIST_CODE_COL + " = " + " regHH." + REGISTRATION_TABLE_VILLAGE_CODE_COL +

                " AND " + " regAss." + HHID_COL + " = " + " regMem." + HHID_COL +
                " AND " + " regAss." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = " + " regMem." + HH_MEM_ID +


/*                " INNER JOIN " + SELECTED_VILLAGE_TABLE + " AS sv " +
                " ON regHH." + ADM_COUNTRY_CODE_COL + " = " + " sv.CountryCode " +
                " AND regHH." + LAY_R1_LIST_CODE + " = " + " sv.DistrictCode " +
                " AND regHH." + LAY_R2_LIST_CODE_COL + " = " + " sv.UpazillaCode " +
                " AND regHH." + LAY_R3_LIST_CODE_COL + " = " + " sv.UnitCode " +
                " AND regHH." + LAY_R4_LIST_CODE_COL + " = " + " sv.VillageCode " +*/


                " INNER JOIN " + GEO_LAY_R4_LIST_TABLE + " AS vill " +
                " ON regHH." + ADM_COUNTRY_CODE_COL + " = " + " vill." + ADM_COUNTRY_CODE_COL +
                " AND regHH." + LAY_R1_LIST_CODE + " = " + " vill." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL +
                " AND regHH." + LAY_R2_LIST_CODE_COL + " = " + " vill." + LAY_R2_LIST_CODE_COL +
                " AND regHH." + LAY_R3_LIST_CODE_COL + " = " + " vill." + LAY_R3_LIST_CODE_COL +
                " AND regHH." + LAY_R4_LIST_CODE_COL + " = " + " vill." + LAY_R4_LIST_CODE_COL +

/*
                " AND " + " sv.CountryCode " + " = " + " vill." + ADM_COUNTRY_CODE_COL +
                " AND " + " sv.DistrictCode " + " = " + " vill." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL +
                " AND " + " sv.UpazillaCode " + " = " + " vill." + LAY_R2_LIST_CODE_COL +
                " AND " + " sv.UnitCode " + " = " + " vill." + LAY_R3_LIST_CODE_COL +
                " AND " + " sv.VillageCode " + " = " + " vill." + LAY_R4_LIST_CODE_COL +*/


                " WHERE " + " regMem." + ADM_COUNTRY_CODE_COL + " =  '" + cCode + "' " +

                " AND regHH." + LAY_R1_LIST_CODE + " = '" + layR1Code + "' " +
                " AND regHH." + LAY_R2_LIST_CODE_COL + " = '" + layR2Code + "' " +
                " AND regHH." + LAY_R3_LIST_CODE_COL + " = '" + layR3Code + "' " +
                " AND regHH." + LAY_R4_LIST_CODE_COL + " = '" + layR4Code + "' "
                + searchID_orName +
                " GROUP BY  newId , memName " +


                " ORDER BY " + " regMem." + HHID_COL + " DESC "
                + limit;

        Log.e("ShuvoMor", Mor);

        return Mor;
    }

    public static String loadCriteria_sql(final String cCode, final String donorCode, final String awardCode, final String programCode, final String memId) {
        return "SELECT " + COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL + " AS criteriaId" + " , " +
                SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_NAME_COL + " || '-' ||  " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS Criteria" +
                " FROM " + COUNTRY_PROGRAM_TABLE + " JOIN " + SERVICE_MASTER_TABLE +
                " ON " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + " AND " +
                SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL + " " +

                " INNER JOIN " + REG_N_ASSIGN_PROG_SRV_TABLE + " AS regAss "
                + " ON regAss." + PROG_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL
                + " AND regAss." + SRV_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL
                +
                " WHERE " + COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + "='" + donorCode + "'"
                + " AND " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + "='" + programCode + "'"
                + " AND regAss." + LAY_R1_LIST_CODE_COL
                + " || '' || regAss." + LAY_R2_LIST_CODE_COL
                + " || '' || regAss." + LAY_R3_LIST_CODE_COL
                + " || '' || regAss." + LAY_R4_LIST_CODE_COL
                + " || '' || regAss." + HHID_COL
                + " || '' || regAss." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + memId + "'"
                + " GROUP BY " + COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL;
    }

    public static String loadLayR4CodeForRegisterRecordView_sql(String cCode) {
        return "SELECT " + " v." + ADM_COUNTRY_CODE_COL + " || '' ||  v." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || '' || v." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " || '' || v." +
                LAY_R3_LIST_CODE_COL + " || '' || v." + LAY_R4_LIST_CODE_COL + " AS v_code," +
                " v." + LAY_R4_LIST_NAME_COL + " AS Vill_Name " +
                     /*   " COUNT("+PID_COL+") AS records"*/" FROM " + GEO_LAY_R4_LIST_TABLE + " AS v" +
                " LEFT JOIN " + REG_N_HH_TABLE + " AS r" +
                " ON r." + ADM_COUNTRY_CODE_COL + "= v." + ADM_COUNTRY_CODE_COL + " AND " +
                "r." + LAY_R1_LIST_CODE + "= v." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " AND " +
                "r." + REGISTRATION_TABLE_UPZILLA_CODE_COL + "= v." + LAY_R2_LIST_CODE_COL + " AND " +
                "r." + REGISTRATION_TABLE_UNION_CODE_COL + "= v." + LAY_R3_LIST_CODE_COL + " AND " +
                "r." + REGISTRATION_TABLE_VILLAGE_CODE_COL + "= v." + LAY_R4_LIST_CODE_COL +
                " Inner join " + SELECTED_VILLAGE_TABLE + " AS s"
                + " on " + " s.CountryCode " + " = v." + ADM_COUNTRY_CODE_COL + " AND " +
                "s.DistrictCode " + " = v." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " AND " +
                "s.UpazillaCode " + " = v." + LAY_R2_LIST_CODE_COL + " AND " +
                "s.UnitCode " + " = v." + LAY_R3_LIST_CODE_COL + " AND " +
                "s.VillageCode " + " = v." + LAY_R4_LIST_CODE_COL +

                " WHERE v." + ADM_COUNTRY_CODE_COL + "= '" + cCode + "'" + /** send the no of village for selected country added by Faisal Mohammad*/
                "  GROUP BY v." + ADM_COUNTRY_CODE_COL + "," +
                " v." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL
                + ",v." + LAY_R2_LIST_CODE_COL + ", " +
                " v." + LAY_R3_LIST_CODE_COL + "," +
                " v." + LAY_R4_LIST_CODE_COL;
    }

    public static String loadCountry_sql(String operationModeName) {
        String criteria = "";

        switch (operationModeName) {
            case UtilClass.REGISTRATION_OPERATION_MODE_NAME:
                criteria = " INNER JOIN " + SELECTED_VILLAGE_TABLE + " ON "
                        + COUNTRY_TABLE + "." + ADM_COUNTRY_CODE_COL + " = "
                        + SELECTED_VILLAGE_TABLE + ".CountryCode";


                break;
            case UtilClass.DISTRIBUTION_OPERATION_MODE_NAME:
                criteria = " INNER JOIN " + SELECTED_FDP_TABLE + " ON "
                        + COUNTRY_TABLE + "." + ADM_COUNTRY_CODE_COL + " = "
                        + SELECTED_FDP_TABLE + "." + ADM_COUNTRY_CODE_COL;

                break;
            case UtilClass.SERVICE_OPERATION_MODE_NAME:
                criteria = " INNER JOIN " + SELECTED_SERVICE_CENTER_TABLE + " ON "
                        + COUNTRY_TABLE + "." + ADM_COUNTRY_CODE_COL + " = "
                        + SELECTED_SERVICE_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL;


                break;
        }

        return criteria;
    }

    public static String loadLayR3List_sql(String cCode) {
        return "SELECT  ut." + LAY_R1_LIST_CODE_COL + " || '' || " + "ut." + LAY_R2_LIST_CODE_COL + " || '' || " + " ut." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " AS layCode "
                + ", " + "ut." + LAY_R3_LIST_NAME + " AS layName "
                + " FROM " + GEO_LAY_R3_LIST_TABLE + " AS ut"
                + " INNER JOIN " + SELECTED_VILLAGE_TABLE + " AS geo "
                + " ON geo.CountryCode " + " = " + "ut." + ADM_COUNTRY_CODE_COL
                + " AND geo. DistrictCode" + " = " + "ut." + LAY_R1_LIST_CODE_COL
                + " AND   geo. UpazillaCode" + " = ut." + LAY_R2_LIST_CODE_COL
                + " AND  geo. UnitCode" + " = ut." + LAY_R3_LIST_CODE_COL
                + " WHERE " + "ut." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"

                + " GROUP BY layCode, layName";
    }

    public static String getNextMemberID_sql(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String hhID) {
        return "SELECT " + HH_MEM_ID + " AS max_rec FROM " + REGISTRATION_MEMBER_TABLE + " WHERE "
                + ADM_COUNTRY_CODE_COL + "= '" + str_c_code + "' AND "
                + LAY_R1_LIST_CODE_COL + "= '" + str_district + "' AND "
                + LAY_R2_LIST_CODE_COL + "= '" + str_upazilla + "' AND "
                + LAY_R3_LIST_CODE_COL + "= '" + str_union + "' AND "
                + LAY_R4_LIST_CODE_COL + "= '" + str_village + "' AND "
                + HHID_COL + "='" + hhID + "'" +
                " ORDER BY " + HH_MEM_ID + " DESC LIMIT 1";
    }

    public static String editLiberiaMemberData_sql(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String hhID, String str_hhMemID) {
        return ADM_COUNTRY_CODE_COL + " = '" + str_c_code + "' "
                + " AND " + LAY_R1_LIST_CODE_COL + " = '" + str_district + "' "
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + str_upazilla + "' "
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + str_union + "' "
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + str_village + "' "
                + " AND " + HHID_COL + " = '" + hhID + "' "
                + " AND " + HH_MEM_ID + " = '" + str_hhMemID + "' ";
    }

    public static String loadCriteriaForDistributionSummary(String cCode, String donorCode, String awardCord, String progCode) {
        return "SELECT " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " || '' || " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " AS IdCriteria ,  " +
                SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS Criteria  " +

                " FROM " + SERVICE_TABLE + " JOIN " + SRV_CENTER_TABLE
                + " ON " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SRV_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL +

                " JOIN " + OP_MONTH_TABLE
                + " ON " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_COUNTRY_CODE_COL + " AND " +
                SERVICE_TABLE + "." + OPERATION_CODE_COL + " = " + OP_MONTH_TABLE + "." + OPERATION_CODE_COL
                + " AND " + SERVICE_TABLE + "." + OP_MONTH_CODE_COL + " = " + OP_MONTH_TABLE + "." + OP_MONTH_CODE_COL
                + " AND " + SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_DONOR_CODE_COL
                + " AND " + SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_AWARD_CODE_COL + " " +
                " JOIN " + SERVICE_MASTER_TABLE + " ON " + SERVICE_TABLE + "." + PROG_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL
                + " AND " + SERVICE_TABLE + "." + SRV_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL +
                " WHERE  " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCord + "' " +
                " AND " + SERVICE_TABLE + "." + PROG_CODE_COL + " = '" + progCode + "' " +
                " GROUP BY " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL;
    }

    public static String edtAssignAgerIn_Elderley_sql(AssignDataModel data) {
        return ADM_COUNTRY_CODE_COL + " = '" + data.getCountryCode() + "'" +
                " AND " + LAY_R1_LIST_CODE_COL + " = '" + data.getDistrictCode() + "'" +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + data.getUpazillaCode() + "'" +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + data.getUnitCode() + "'" +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + data.getVillageCode() + "'" +
                " AND " + HHID_COL + " = '" + data.getHh_id() + "'" +
                " AND " + MEM_ID_COL + " = '" + data.getMemId() + "'  ";
    }

    public static String getServiceDetailsForMember_sql(String cCode, String donorCode, String awardCord,
                                                        String districCode, String upCode, String unCode,
                                                        String vCode, String hhId, String mmId, String opCode,
                                                        String opMCode, String prgCode, String srvCode) {
        return " SELECT " + SERVICE_TABLE_SERVICE_SL_COL
                + " , " + SERVICE_TABLE_SERVICE_DT_COL

                + " , " + SRV_STATUS_COL +
                " FROM  " + SERVICE_TABLE +
                " WHERE  " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " And   " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " And   " + ADM_AWARD_CODE_COL + " = '" + awardCord + "' " +
                " And  " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + districCode + "' " +
                " And   " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "' " +
                " And   " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "' " +
                " And   " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "' " +
                "  And  " + HHID_COL + " = '" + hhId + "' " +
                "  And  " + MEM_ID_COL + " = '" + mmId + "' " +
                " And   " + PROG_CODE_COL + " ='" + prgCode + "' " +
                "  And  " + SRV_CODE_COL + " ='" + srvCode + "' " +
                "  And  " + OPERATION_CODE_COL + " = '2' " +
                "  And  " + OP_MONTH_CODE_COL + "   ='" + opMCode + "' ";
    }

    public static String updateSrvMaxDate_sql(String cCode, String donorCode, String awardCode,
                                              String distCode, String upCode, String unCode, String vCode,
                                              String hhId, String memId, String progCode, String srvCode,
                                              String srvMaxDate) {
        return SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND  " + LAY_R1_LIST_CODE_COL + " = '" + distCode + "'"
                + " AND  " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "'"
                + " AND  " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "'"
                + " AND  " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "'"
                + " AND  " + HHID_COL + " = '" + hhId + "'"
                + " AND  " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + memId + "'"
                + " AND  " + ADM_DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND  " + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND  " + PROG_CODE_COL + " = '" + progCode + "'"
                + " AND  " + SRV_CODE_COL + " = '" + srvCode + "'";
    }

    public static String getLocationSpecificLatLong_sql(String cCode, String groupCode, String subGroupCode, String locationCode) {
        return " SELECT ( CASE WHEN " + LATITUDE_COL + "='ISNULL' Then "
                + LATITUDE_COL + "='' ElSE " + LATITUDE_COL + " END ) AS '" + LATITUDE_COL + "' ," +
                "(CASE WHEN " + LONGITUDE_COL + "='ISNULL'Then " + LONGITUDE_COL + " = '' ElSE " + LONGITUDE_COL + " END ) AS '" + LONGITUDE_COL + "'  " +
                " FROM " + GPS_LOCATION_TABLE +
                " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + GROUP_CODE_COL + " ='" + groupCode + "' "
                + " AND " + SUB_GROUP_CODE_COL + " ='" + subGroupCode + "' "
                + " AND " + LOCATION_CODE_COL + " ='" + locationCode + "'";
    }


    public static String editMalawiMemberData_sql(String cCode, String layR1Code, String layR2Code,
                                                  String layR3Code, String layR4Code, String hhID,
                                                  String memId) {
        return ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " and " + LAY_R1_LIST_CODE_COL + " = '" + layR1Code + "'" +
                " and " + LAY_R2_LIST_CODE_COL + " = '" + layR2Code + "'" +
                " and " + LAY_R3_LIST_CODE_COL + " = '" + layR3Code + "'" +
                " and " + LAY_R4_LIST_CODE_COL + " = '" + layR4Code + "'" +
                " and " + HHID_COL + " = '" + hhID + "'" +
                " and " + HH_MEM_ID + " = '" + memId + "'";
    }


    public static String getHouseHoldRegistrationIsChecked_sql(String columnName, String c_code,
                                                               String dname, String upname,
                                                               String uname, String vname, String pid) {
        return "SELECT " + columnName + " FROM " + REG_N_HH_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + c_code + "'"                          // country name
                + " AND " + LAY_R1_LIST_CODE + " = '" + dname + "'"                                 // district name
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + upname + "'"                            // upazilla name
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + uname + "'"                             // Unit name
                + " AND " + REGISTRATION_TABLE_VILLAGE_CODE_COL + " = '" + vname + "'"              // Unit name
                + " AND " + REGISTRATION_TABLE_HHID + " = '" + pid + "'";                           // Personal code
    }

    public static String getSubGroupSpecificLatLongCoordinates_sql(String cCode, String grpCode,
                                                                   String subGrpCode) {
        return " SELECT "
                + LOCATION_CODE_COL
                + " , " + LOCATION_NAME_COL
                + " , " + LATITUDE_COL

                + " , " + LONGITUDE_COL
                + " FROM " + GPS_LOCATION_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + GROUP_CODE_COL + " ='" + grpCode + "' "
                + " AND " + SUB_GROUP_CODE_COL + " ='" + subGrpCode + "' "
                + " AND (" + LATITUDE_COL + " != '' " + " OR  " + LONGITUDE_COL + " != '' )";
    }
}//end of class
