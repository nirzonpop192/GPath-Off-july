package com.siddiquinoor.restclient.manager;

/**
 * This class is the Base Handler of all SQL operation
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.3.0
 * @since 1.0
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Log;
import android.widget.ImageView;

import com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseRecordingActivity;
import com.siddiquinoor.restclient.activity.GPSLocationSearchPage;
import com.siddiquinoor.restclient.data_model.AGR_DataModel;
import com.siddiquinoor.restclient.data_model.AssignDDR_FFA_DataModel;
import com.siddiquinoor.restclient.data_model.CTDataModel;
import com.siddiquinoor.restclient.data_model.DTQResModeDataModel;
import com.siddiquinoor.restclient.data_model.DTResponseTableDataModel;
import com.siddiquinoor.restclient.data_model.DTSurveyTableDataModel;
import com.siddiquinoor.restclient.data_model.DT_ATableDataModel;
import com.siddiquinoor.restclient.data_model.FDPItem;
import com.siddiquinoor.restclient.data_model.GPS_LocationAttributeDataModel;
import com.siddiquinoor.restclient.data_model.GPS_LocationDataModel;
import com.siddiquinoor.restclient.data_model.GPS_SubGroupAttributeDataModel;
import com.siddiquinoor.restclient.data_model.LayRCodes;
import com.siddiquinoor.restclient.data_model.LupTaParticipentCategories;
import com.siddiquinoor.restclient.data_model.Lup_gpsListDataModel;
import com.siddiquinoor.restclient.data_model.ProgramMasterDM;
import com.siddiquinoor.restclient.data_model.RegNAssgProgSrv;
import com.siddiquinoor.restclient.data_model.RegN_HH_libDataModel;
import com.siddiquinoor.restclient.data_model.RegN_MM_libDataModel;
import com.siddiquinoor.restclient.data_model.ServiceCenterItem;
import com.siddiquinoor.restclient.data_model.ServiceSpecificDataModel;
import com.siddiquinoor.restclient.data_model.TAPosParticipants;
import com.siddiquinoor.restclient.data_model.TA_ParticipantsListDataModel;
import com.siddiquinoor.restclient.data_model.TaCategoriesDataModel;
import com.siddiquinoor.restclient.data_model.TaPartOrgN;
import com.siddiquinoor.restclient.data_model.TemOpMonth;
import com.siddiquinoor.restclient.data_model.VillageItem;
import com.siddiquinoor.restclient.data_model.adapters.TaSummary;
import com.siddiquinoor.restclient.data_model.adapters.TrainigActivBeneficiaryDataModel;
import com.siddiquinoor.restclient.data_model.adapters.TrainingNActivityIndexDataModel;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.manager.sqlsyntax.Schema;
import com.siddiquinoor.restclient.utils.FileUtils;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.data_model.adapters.AssignDataModel;
import com.siddiquinoor.restclient.data_model.adapters.CommunityGroupDataModel;
import com.siddiquinoor.restclient.data_model.adapters.DistributionGridDataModel;
import com.siddiquinoor.restclient.data_model.adapters.DistributionSaveDataModel;
import com.siddiquinoor.restclient.data_model.adapters.DTQTableDataModel;
import com.siddiquinoor.restclient.views.adapters.GPSLocationLatLong;
import com.siddiquinoor.restclient.data_model.adapters.GraduationGridDataModel;
import com.siddiquinoor.restclient.views.adapters.ListDataModel;
import com.siddiquinoor.restclient.views.adapters.MemberModel;
import com.siddiquinoor.restclient.views.adapters.ServiceDataModel;
import com.siddiquinoor.restclient.views.adapters.ServiceSlDataModle;
import com.siddiquinoor.restclient.views.adapters.SummaryOfMemberAssignedListModel;
import com.siddiquinoor.restclient.views.adapters.SummaryCriteriaModel;
import com.siddiquinoor.restclient.views.adapters.SummaryGroupListDataModel;
import com.siddiquinoor.restclient.views.adapters.SummaryIdListInGroupDataModel;
import com.siddiquinoor.restclient.views.adapters.SummaryModel;
import com.siddiquinoor.restclient.views.adapters.SummaryServiceListModel;
import com.siddiquinoor.restclient.views.adapters.VouItemServiceExtDataModel;
import com.siddiquinoor.restclient.views.adapters.raf_data_model.GraduationDateCode;
import com.siddiquinoor.restclient.views.helper.LocationHelper;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;

import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.siddiquinoor.restclient.activity.AssignActivity.CA2;
import static com.siddiquinoor.restclient.activity.AssignActivity.CU2;
import static com.siddiquinoor.restclient.activity.AssignActivity.LM;
import static com.siddiquinoor.restclient.activity.AssignActivity.MCHN;
import static com.siddiquinoor.restclient.activity.AssignActivity.PW;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables

    // Database Version
    private static final int DATABASE_VERSION = 21;
    // Database Name
    public static final String DATABASE_NAME = "PCI.db";

    public static final String EXTERNAL_DATABASE_NAME = "PCI_ex";
    // Android meta data table
    public static final String SQLITE_SEQUENCE = "SQLITE_SEQUENCE";
    public static final String TABLE_NAME = "NAME";
    public static final String SQL_QUERY_SYNTAX = "SqlQuery";
    public static final String UPLOAD_SYNTAX_TABLE = "UploadSyntax";
    public static final String UPLOAD_PHYSICAL_TABLE_SYNTAX_TABLE = "UploadPhysicalTableSyntax";
    public static final String FOOD_FLAG = "FoodFlag";
    public static final String PROG_FLAG = "ProgFlag";
    public static final String NON_FOOD_FLAG = "NFoodFlag";
    public static final String CASH_FLAG = "CashFlag";
    public static final String VOUCHER_FLAG = "VOFlag";
    public static final String IS_SELECTED_FLAG_COL = "IsSelectedFlag";
    public static final String ACTIVE = "A";
    public static final int REGISTRATION_OP_CODE = 1;
    public static final String ASSIGN_SUMMARY_PROGRAM_DETAILS = "assignSummaryProgramDetails";
    public static final String FDP_LAY_R2 = "FdpLayR2";
    private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";


    private String next_id = "";
    private static final int ID_LENGTH = 5;

    // Login table name
    // todo: rename table Name
    public static final String LOGIN_TABLE = "UsrLogIn";
    public static final String STAFF_MASTER_TABLE = "StaffMaster";
    public static final String STAFF_ID_COL = "StfCode";
    public static final String STAFF_NAME_COL = "StfName";
    public static final String STAFF_STATUS_COL = "StfStatus";
    public static final String STAFF_CATEGORY_COL = "StfCategory";
    public static final String STAFF_ADMIN_ROLE_COL = "StfAdminRole";


    public static final String COUNTRY_TABLE = "AdmCountry";
    public static final String VALID_DATE_RANGE = "ValidDateRange";
    public static final String LAYER_LABEL_TABLE = "GeoLayRMaster";
    public static final String DISTRICT_TABLE = "GeoLayR1List";
    public static final String UPAZILLA_TABLE = "GeoLayR2List";
    public static final String GEO_LAY_R3_LIST_TABLE = "GeoLayR3List";
    public static final String VILLAGE_TABLE = "GeoLayR4List";
    public static final String VILLAGE_TABLE_FOR_ASSIGN = "VillageForQuery"; // THIS KEY USE FOR QUERY IN ASSIGN
    public static final String REG_N_HH_TABLE = "RegNHHTable";
    public static final String REGISTRATION_MEMBER_TABLE = "RegNHHMem";
    public static final String RELATION_TABLE = "LUP_RegNHHRelation";


    public static final String SERVICE_TABLE = "SrvTable";
    public static final String ADM_COUNTRY_AWARD_TABLE = "AdmCountryAward";
    public static final String ADM_AWARD_TABLE = "AdmAward";
    public static final String ADM_DONOR_TABLE = "AdmDonor";
    public static final String ADM_PROGRAM_MASTER_TABLE = "AdmProgramMaster";
    public static final String SERVICE_MASTER_TABLE = "AdmServiceMaster";
    public static final String REG_N_ASSIGN_PROG_SRV_TABLE = "RegNAssignProgSrv";
    public static final String GPS_GROUP_TABLE = "GPSGroupTable";
    public static final String GPS_SUB_GROUP_TABLE = "GPSSubGroupTable";
    public static final String GPS_LOCATION_TABLE = "GPSLocationTable";
    public static final String OP_MONTH_TABLE = "AdmOpMonthTable";
    public static final String COUNTRY_PROGRAM_TABLE = "AdmCountryProgram";
    /**
     * temporary only use for Service  mode
     */
    public static final String TEMPORARY_COUNTRY_PROGRAM_TABLE = "TemCountryProgram";
    public static final String TEMPORARY_OP_MONTH_TABLE = "TemOpMonthTable";

    public static final String SERVICE_CENTER_TABLE = "SrvCenterTable";

    public static final String STAFF_GEO_INFO_ACCESS_TABLE = "StaffGeoInfoAccess";
    public static final String STAFF_SRV_CENTER_ACCESS_TABLE = "StaffSrvCenterAccess";

    public static final String LUP_REGNH_HEAD_CATEGORY_TABLE = "LUP_RegNHHHeadCategory";
    // public static final String LIBERIA_REGISTRATION_TABLE = "Liberia_Registration";

    public static final String REG_N_LUP_GRADUATION_TABLE = "RegNLUP_Graduation";

    public static final String REPORT_TEMPLATE_TABLE = "RptTemplateTable";
    public static final String CARD_PRINT_REASON_TABLE = "LUP_RegNCardPrintReason";
    public static final String MEMBER_CARD_PRINT_TABLE = "RegNMemCardPrintTable";

    public static final String VOUCHER_ITEM_TABLE = "VOItmTable";
    public static final String VOUCHER_ITEM__MEAS_TABLE = "VOItmMeas";
    public static final String VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE = "VOCountryProgItm";
    public static final String SERVICE_EXTENDED_TABLE = "SrvExtendedTable";
    public static final String DISTRIBUTION_EXTENDED_TABLE = "DistExtendedTable";
    public static final String SELECTED_VILLAGE_TABLE = "SelectedVillage";
    public static final String SELECTED_COUNTRY_TABLE = "SelectedCountry";
    public static final String SELECTED_FDP_TABLE = "SelectedFDP";
    public static final String SELECTED_SERVICE_CENTER_TABLE = "SelectedCenter";
    public static final String COMMUNITY_GROUP_TABLE = "CommunityGroup";
    public static final String GPS_SUB_GROUP_ATTRIBUTES_TABLE = "GPSSubGroupAttributes";
    public static final String LUP_GPS_TABLE = "GPSLUPList";
    public static final String LUP_COMMUNITY_ANIMAL_TABLE = "LUP_CommnityAnimalList";
    public static final String LUP_PROG_GROUP_CROP_TABLE = "LUP_ProgGroupCropList";
    public static final String LUP_COMMUNITY_LOAN_SOURCE_TABLE = "LUP_CommnityLoanSource";
    public static final String LUP_COMMUNITY_FUND_SOURCE_TABLE = "LUP_CommnityFundSource";
    public static final String LUP_COMMUNITY_IRRIGATION_SYSTEM_TABLE = "LUP_CommunityIrrigationSystem";
    public static final String ADM_MACHINE_REGISTRY_TABLE = "AdmMachineRegistry";
    public static final String ADM_MACHINE_PUBLISHER_TABLE = "AdmMachinePublisher";

    public static final String REG_N_MEM_PROG_GRP_TABLE = "RegNMemProgGrp";
    public static final String COMMUNITY_GROUP_CATEGORY_TABLE = "CommunityGroupCategory";
    public static final String DTQRES_MODE_TABLE = "DTQResMode";
    public static final String GPS_LOCATION_CONTENT_TABLE = "GPSLocationContent";


    public static final String LUP_COMMUNITY_LEAD_POSITION_TABLE = "LUP_CommunityLeadPosition";

    public static final String GPS_LOCATION_ATTRIBUTES_TABLE = "GPSLocationAttributes";
    public static final String SERVICE_SPECIFIC_TABLE = "SrvSpecific";

    public static final String LAST_SYNC_TYRACE_TABLE = "LastSyncTrace";
    public static final String REG_N_FFA_TABLE = "RegN_FFA";
    public static final String REG_N_WE_TABLE = "RegN_WE";
    public static final String DTA_SKIP_TABLE = "DTASkipTable";
    public static final String TA_MASTER_TABLE = "TAMaster";
    public static final String TA_CATEGORY_TABLE = "TACategory";
    public static final String TA_EVENT_TOPIC_TABLE = "TAEventTopic";
    public static final String TA_GROUP_TABLE = "TAGroup";
    public static final String TA_SUB_GROUP_TABLE = "TASubGroup";
    public static final String TA_PARTICIPANTS_LIST_TABLE = "TAParticipantsList";
    public static final String TA_PART_ORG_N_TABLE = "TAPartOrgN";
    public static final String TA_POS_PARTICIPANTS_TABLE = "TAPosParticipants";
    public static final String TA_TOPIC_CHILD_TABLE = "TATopicChild";
    public static final String TA_TOPIC_MASTER_TABLE = "TATopicMaster";
    public static final String LUP_TA_PATICIPANT_CAT_TABLE = "LUP_TAParticipantCat";

    public static final String DIST_N_PLAN_BASIC_TABLE = "DistNPlanBasic";
    public static final String LUP_REGN_ADDRESS_LOOKUP_TABLE = "LUP_RegNAddLookup";
    public static final String COMMUNITY_GRP_DETAIL_TABLE = "CommunityGrpDetail";
    public static final String DTGEO_LIST_LEVEL_TABLE = "DTGeoListLevel";
    public static final String PROGRAM_ORGANIZATION_ROLE_TABLE = "ProgOrgNRole";
    public static final String PROGRAM_ORGANIZATION_NAME_TABLE = "ProgOrgN";

    public static final String DT_RESPONSE_TABLE = "DTResponseTable";
    public static final String DT_SURVEY_TABLE = "DTSurveyTable";
    public static final String DT_CATEGORY_TABLE = "DTCategory";
    public static final String DT_COUNTRY_PROGRAM_TABLE = "DTCountryProgram";
    public static final String DT_ENU_TABLE = "DTEnuTable";
    public static final String DTGEO_LIST_LEVEL_COL = "DTGeoListLevel";
    public static final String DTQRES_MODE_COL = "DTQResMode";
    public static final String DTQ_TABLE = "DTQTable";
    public static final String DT_TABLE_DEFINITION_TABLE = "DTTableDefinition";
    public static final String DTTABLE_LIST_CATEGORY_TABLE = "DTTableListCategory";

    public static final String LIST_CODE_COL = "ListCode";
    public static final String LIST_NAME_COL = "ListName";
    public static final String DT_LUP_TABLE = "DTLUP";


    public static final String DT_A_TABLE = "DTATable";
    public static final String DT_BASIC_TABLE = "DTBasic";
    public static final String DT_BASIC_COL = "DTBasic";
    public static final String COUNTRY_CODE_COL = "CountryCode";
    public static final String PROGRAM_CODE_COL = "ProgramCode";
    public static final String DTQ_CODE_COL = "DTQCode";
    public static final String DTQ_TEXT_COL = "DTQText";
    public static final String DTA_CODE_COL = "DTACode";
    public static final String DTA_LABEL_COL = "DTALabel";
    public static final String SKIP_CODE_COL = "SkipCode";
    public static final String DTA_CODE_COMB_N_COL = "DTACodeCombN";

    public static final String DT_SURVEY_NUM = "DTSurveyNumber";

    public static final String DT_SEQ_COL = "DTSeq";
    public static final String DTA_SHORT_COL = "DTAShort";
    public static final String DT_SCORE_CODE_COL = "DTScoreCode";
    public static final String DTSKIP_DTQ_CODE_COL = "DTSkipDTQCode";


    public static final String DTA_COMPARE_CODE_COL = "DTACompareCode";
    public static final String DT_TITLE_COL = "DTTitle";
    public static final String DT_SUB_TITLE_COL = "DTSubTitle";
    public static final String DT_DESCRIPTION_COL = "DTDescription";
    public static final String DT_AUTO_SCROLL_COL = "DTAutoScroll";
    public static final String DTAUTO_SCROLL_TEXT = "DTAutoScrollText";
    public static final String DT_ACTIVE_COL = "DTActive";
    public static final String DT_CATEGORY_COL = "DTCategory";
    public static final String DT_GEO_LIST_LEVEL_COL = "DTGeoListLevel";
    public static final String DT_OP_MODE_COL = "DTOPMode";
    public static final String DT_SHORT_NAME_COL = "DTShortName";

    public static final String DT_STF_CODE_COL = "StfCode";
    public static final String DT_ADM_COUNTRY_CODE_COL = "AdmCountryCode";
    //    public static final String DT_BASIC_COL = "DTBasic";
    public static final String DT_BTN_SAVE_COL = "btnSave";
    public static final String DT_ENTRY_BY_COL = "EntryBy";
    public static final String DT_USA_ENTRY_DATE_COL = "EntryDate";
    //  training Activity
    public static final String EVENT_CODE_COL = "EventCode";
    public static final String EVENT_NAME_COL = "EventName";
    public static final String TA_GROUP_COL = "TAGroup";
    public static final String TA_SUB_GROUP_COL = "TASubGroup";
    public static final String TA_SUB_TITLE_COL = "TASubTitle";
    public static final String VENUE_NAME_COL = "VenueName";
    public static final String VENUE_ADDRESS_COL = "VenueAddress";
    public static final String TOTAL_DAYS_COL = "TotalDays";
    public static final String HOURS_PER_DAY_COL = "HoursPerDay";
    public static final String TA_CAT_CODE_COL = "TACatCode";
    public static final String TA_CAT_NAME_COL = "TACatName";
    public static final String PART_CAT_TITLE_COL = "PartCatTitle";
    public static final String SRC_BEN_COL = "SrcBen";
    public static final String TOPIC_MASTER_CODE_COL = "TopicMasterCode";
    public static final String TOPIC_TITLE_COL = "TopicTitle";
    public static final String TOPIC_CHILD_CODE_COL = "TopicChildCode";
    public static final String TOPIC_SUB_TITLE_COL = "TopicSubTitle";
    public static final String TOPIC_GROUP_COL = "TAGroup";
    public static final String TOPIC_GROUP_TITLE_COL = "TAGroupTitle";
    public static final String PART_ID_COL = "PartID";
    public static final String ID_CATEGORY_COL = "IDCategory";
    public static final String PART_NAME_COL = "PartName";
    public static final String PART_ORG_N_CODE_COL = "PartOrgNCode";
    public static final String PART_ORG_N_NAME_COL = "PartOrgNName";
    public static final String PART_CAT_CODE_COL = "PartCatCode";
    public static final String POS_CODE_COL = "PosCode";
    public static final String AM_SESSION_COL = "AMSession";
    public static final String PM_SESSION_COL = "PMSession";
    public static final String ATDN_DATE_COL = "AtdnDate";
    public static final String POS_TITLE_COL = "PosTitle";

    public static final String FREQUENCY_COL = "Frequency";
    public static final String PROG_ACTIVITY_CODE_COL = "ProgActivityCode";
    public static final String PROG_ACTIVITY_TITLE_COL = "ProgActivityTitle";
    public static final String REF_IDENTIFIER_COL = "RefIdentifier";
    public static final String RPT_FREQUENCY_COL = "RptFrequency";
    public static final String GEO_LEVEL_COL = "GeoLevel";
    public static final String GEO_LEVEL_NAME_COL = "GeoLevelName";
    public static final String LIST_UDF_NAME_COL = "ListUDFName";
    public static final String QRES_MODE_COL = "QResMode";
    public static final String QRES_LUP_TEXT_COL = "QResLupText";
    public static final String LOOK_UP_UDF_NAME_COL = "LookUpUDFName";
    public static final String RESPONSE_VALUE_CONTROL_COL = "ResponseValueControl";
    public static final String QTEXT_COL = "QText";
    public static final String ALLOW_NULL_COL = "AllowNull";
    public static final String LUP_TABLE_NAME = "LUPTableName";

    public static final String PORTABLE_DEVICE_ID_COL = "PortableDeviceID";

    public static final String QSEQ_SCOL = "QSeq";
    public static final String DT_ENU_ID_COL = "DTEnuID";
    public static final String OP_MODE_COL = "OpMode";
    public static final String DTTIME_STRING_COL = "DTTimeString";
    public static final String DT_R_SEQ_COL = "DTRSeq";
    public static final String DTA_VALUE_COL = "DTAValue";
    public static final String TABLE_NAME_COL = "TableName";
    public static final String FIELD_NAME_COL = "FieldName";
    public static final String FIELD_DEFINITION_COL = "FieldDefinition";
    public static final String FIELD_SHORT_NAME_COL = "FieldShortName";
    public static final String VALUE_UDF_COL = "ValueUDF";
    public static final String LUPTABLE_NAME_COL = "LUPTableName";
    public static final String ADMIN_ONLY_COL = "AdminOnly";
    public static final String TABLE_GROUP_CODE_COL = "TableGroupCode";
    public static final String USE_ADMIN_ONLY_COL = "UseAdminOnly";
    public static final String USE_REPORT_COL = "UseReport";
    public static final String USE_TRANSACTION_COL = "UseTransaction";
    public static final String USE_LUP_COL = "UseLUP";
    public static final String USE_REGISTRATION_COL = "UseRegistration";
    public static final String USE_SERVICE_COL = "UseService";
    public static final String USE_DISTRIBUTION_COL = "UseDistribution";
    public static final String USE_INVENTORY_COL = "UseInventory";
    public static final String USE_GIS_COL = "UseGIS";
    public static final String TD_SP_NAME_COL = "TDspName";
    public static final String T_CONTENTS_COL = "TContents";
    public static final String SHOW_HIDE_COL = "ShowHide";
    public static final String MAX_VALUE_COL = "MaxValue";
    public static final String MIN_VALUE_COL = "MinValue";
    public static final String MARK_ON_GRID_COL = "MarkOnGrid";
    public static final String LAND_SIZE_UNDER_IRRIGATION_COL = "LandSizeUnderIrrigation";
    public static final String IRRIGATION_SYSTEM_USED_COL = "IrrigationSystemUsed";
    public static final String FUND_SUPPORT_COL = "FundSupport";
    public static final String REP_NAME_COL = "RepName";
    public static final String REP_PHONE_NUMBER_COL = "RepPhoneNumber";
    public static final String FORMATION_DATE_COL = "FormationDate";
    public static final String PROJECT_NO_COL = "ProjectNo";
    public static final String PROJECT_TITLE = "ProjectTitle";


    public static final String UPZELLA_TABLE_CUSTOM_QUERY = "UpazellaTableCustomQuery";

    // Specific Assigne Table
    public static final String REG_N_LM_TABLE = "RegN_LM";
    public static final String REG_N_PW_TABLE = "RegN_PW";
    public static final String REG_N_CU2_TABLE = "RegN_CU2";
    public static final String REG_N_CA2_TABLE = "RegN_CA2";
    public static final String REG_N_AGR_TABLE = "RegN_ARG";
    public static final String REG_N_CT_TABLE = "RegN_CT";
    public static final String REG_N_VUL_TABLE = "RegN_VUL";

    public static final String LUP_SRV_OPTION_LIST_TABLE = "LUP_SrvOptionList";
    public static final String LUP_GPS_LIST_TABLE = "GPSLUPList";

    public static final String LUP_VALUE_CODE_COL = "LupValueCode";
    public static final String LUP_VALUE_TEXT_COL = "LupValueText";


    /**
     * *************************************************************************
     * COLUMN NAME DEFINE FROM HERE
     * **************************************************************************
     */

    // Login Table Columns names
    public static final String USER_ID = "UsrID";
    public static final String COUNTRY_CODE = "CountryCode";
    public static final String FDP_MASTER_COUNTRY_CODE = "AdmCountryCode";
    public static final String STAFF_FDP_ACCESS_COUNTRY_CODE = "AdmCountryCode";
    public static final String DATE_RANGE_COUNTRY_CODE = "CountryCode";
    public static final String LAYER_LAVLE_COUNTRY_CODE = "AdmCountryCode";
    public static final String COUNTRY_COUNTRY_CODE = "AdmCountryCode";
    public static final String STAFF_COUNTRY_CODE = "OrigAdmCountryCode";
    public static final String STAFF_CODE = "StfCode";
    public static final String USER_LOGIN_NAME = "UsrLogInName";
    public static final String USER_LOGIN_PW = "UsrLogInPW";
    public static final String USER_FIRST_NAME = "UsrFirstName";
    public static final String USER_LAST_NAME = "UsrLastName";
    public static final String USER_EMAIL = "UsrEmail";
    public static final String USER_EMAIL_VERIFICATION = "UsrEmailVerification";
    public static final String USER_STATUS = "UsrStatus";

    public static final String LTP_2_HECTRES_COL = "LTp2Hectres";
    public static final String LT_3_FOOD_STOCK_COL = "LT3mFoodStock";
    public static final String NO_MAJOR_COMMON_LIVE_STOCK_COL = "NoMajorCommonLiveStock";
    public static final String RECEIVE_NO_FORMAL_WAGES_COL = "ReceiveNoFormalWages";
    public static final String NO_IGA_COL = "NoIGA";
    public static final String RELY_PICE_EORK_COL = "RelyPiecework";


    // COUNTRY
    public static final String ID_COL = "ID";
    public static final String COUNTRY_COUNTRY_NAME = "AdmCountryName";

    // Valid Date Range
    public static final String LAYER_CODE_COL = "GeoLayRCode";
    public static final String LAYER_NAME_COL = "GeoLayRName";

    // Layer Label
    public static final String DATE_START = "StartDate";
    public static final String DATE_END = "EndDate";

    // DISTRICT
    public static final String MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL = "LayR1ListCode";
    public static final String FDP_MASTER_LAY_R1_LIST_CODE_COL = "LayR1Code";
    public static final String LAY_R_LIST_CODE_COL = "LayRListCode";
    public static final String DISTRICT_NAME_COL = "LayRListName";

    // Registration by Shuvo
    public static final String LAY_R1_LIST_CODE = "LayR1ListCode";
    public static final String REGISTRATION_TABLE_UPZILLA_CODE_COL = "LayR2ListCode";
    public static final String REGISTRATION_TABLE_UNION_CODE_COL = "LayR3ListCode";
    public static final String REGISTRATION_TABLE_VILLAGE_CODE_COL = "LayR4ListCode";
    public static final String REGISTRATION_TABLE_HHID = "HHID";   ///// pId
    public static final String REGISTRATION_TABLE_HH_HEAD_NAME = "HHHeadName";
    public static final String REGISTRATION_TABLE_HH_HEAD_SEX = "HHHeadSex";
    public static final String REGISTRATION_TABLE_GPS_LAT = "GPSLat";
    public static final String REGISTRATION_TABLE_GPS_LONG = "GPSLong";
    public static final String REGISTRATION_TABLE_REGN_ADDRESS_LOOKUP_CODE_COL = "RegNAddLookupCode ";
    public static final String REGISTRATION_TABLE_HOUSE_HOLD_TYPE_CODE_COL = "HHCat";

    // Upazilla
    public static final String FDP_MASTER_LAY_R2_LIST_CODE_COL = "LayR2Code";
    public static final String LAY_R1_LIST_CODE_COL = "LayR1ListCode";
    public static final String LAY_R2_LIST_CODE_COL = "LayR2ListCode";
    public static final String UPZILLA_NAME_COL = "LayR2ListName";

    // UNIT
    public static final String LAY_R3_LIST_CODE_COL = "LayR3ListCode";
    public static final String LAY_R3_LIST_NAME = "LayR3ListName";

    // Village
    public static final String LAY_R4_LIST_CODE_COL = "LayR4ListCode";
    public static final String LAY_R4_LIST_NAME_COL = "LayR4ListName";
    public static final String HOUSE_HOLD_TARGET = "HHCount";


    //-------------- REGISTRATION-------
    public static final String ADM_COUNTRY_CODE_COL = "AdmCountryCode";
    public static final String PID_COL = "RegistrationID";
    // public static final String PNAME_COL = "PersonName";
    public static final String SEX_COL = "MemSex";
    public static final String TA_PARTICIPANTS_LIST_TABLE_SEX_COL = "Sex";
    public static final String REG_DATE_COL = "RegNDate";
    public static final String VSLA_GROUP = "VSLAGroup";
    public static final String REGN_ADDRESS_LOOKUP_CODE_COL = "RegNAddLookupCode";
    public static final String REGN_ADDRESS_LOOKUP_NAME_COL = "RegNAddLookup";

    //public static final String PHOTO_COL            = "Photo";

    public static final String LATITUDE_COL = "Latd";
    public static final String LONGITUDE_COL = "Long";
    public static final String HH_SIZE = "HHSize";
    public static final String AG_LAND = "AGLand";
    public static final String V_STATUS = "VStatus";
    public static final String M_STATUS = "MStatus";
    public static final String ENTRY_BY = "EntryBy";
    public static final String ENTRY_DATE = "EntryDate";
    public static final String RELATION_CODE = "HHRelationCode";
    public static final String RELATION_NAME = "RelationName";
    public static final String HOUSE_HOLD_TYPE_CODE_COL = "HHHeadCat";
    public static final String LT2YRS_M_COL = "LT2yrsM";
    public static final String LT2YRS_F_COL = "LT2yrsF";
    public static final String M_2TO5YRS_COL = "M2to5yrs";
    public static final String F_2TO5YRS_COL = "F2to5yrs";
    public static final String M_6TO12YRS_COL = "M6to12yrs";
    public static final String F_6TO12YRS_COL = "F6to12yrs";
    public static final String M_13TO17YRS_COL = "M13to17yrs";
    public static final String F_13TO17YRS_COL = "F13to17yrs";
    public static final String ORPHN_LT18YRS_M_COL = "Orphn_LT18yrsM";
    public static final String ORPHN_LT18YRS_F_COL = "Orphn_LT18yrsF";
    public static final String ADLT_18TO59_M_COL = "Adlt_18to59M";
    public static final String ADLT_18TO59_F_COL = "Adlt_18to59F";
    public static final String ELD_60P_M_COL = "Eld_60pM";
    public static final String ELD_60P_F_COL = "Eld_60pF";
    public static final String PLW_NO_COL = "PLW";
    public static final String CHRO_ILL_NO_COL = "ChronicallyIll";
    public static final String DECEASED_CONTRACT_EBOLA_COL = "LivingDeceasedContractEbola";
    public static final String EXTRA_CHILD_CAUSE_EBOLA_COL = "ExtraChildBecauseEbola";
    public static final String EXTRA_ELDERLY_CAUSE_EBOLA_COL = "ExtraElderlyPersonBecauseEbola";
    public static final String EXTRA_CHRONICALLY_ILL_CAUSE_EBOLA_COL = "ExtraChronicallyIllDisabledPersonBecauseEbola";
    public static final String BRF_COUNT_CATTLE_COL = "BRFCntCattle";
    public static final String BRF_VALUE_CATTLE_COL = "BRFValCattle";
    public static final String AFT_COUNT_CATTLE_COL = "AFTCntCattle";
    public static final String AFT_VALUE_CATTLE_COL = "AFTValCattle";
    public static final String BRF_COUNT_SGOATS_COL = "BRFCntSheepGoats";
    public static final String BRF_VALUE_SGOATS_COL = "BRFValSheepGoats";
    public static final String AFT_COUNT_SGOATS_COL = "AFTCntSheepGoats";
    public static final String AFT_VALUE_SGOATS_COL = "AFTValSheepGoats";
    public static final String BRF_COUNT_POULTRY_COL = "BRFCntPoultry";
    public static final String BRF_VALUE_POULTRY_COL = "BRFValPoultry";
    public static final String AFT_COUNT_POULTRY_COL = "AFTCntPoultry";
    public static final String AFT_VALUE_POULTRY_COL = "AFTValPoultry";
    public static final String BRF_COUNT_OTHER_COL = "BRFCntOther";
    public static final String BRF_VALUE_OTHER_COL = "BRFValOther";
    public static final String AFT_COUNT_OTHER_COL = "AFTCntOther";
    public static final String AFT_VALUE_OTHER_COL = "AFTValOther";
    public static final String BRF_ACRE_CULTIVABLE_COL = "BRFAcreCultivable";
    public static final String BRF_VALUE_CULTIVABLE_COL = "BRFValCultivable";
    public static final String AFT_ACRE_CULTIVABLE_COL = "AFTAcreCultivable";
    public static final String AFT_VALUE_CULTIVABLE_COL = "AFTValCultivable";
    public static final String BRF_ACRE_NON_CULTIVABLE_COL = "BRFAcreNonCultivable";
    public static final String BRF_VAL_NON_CULTIVABLE_COL = "BRFValNonCultivable";
    public static final String AFT_ACRE_NON_CULTIVABLE = "AFTAcreNonCultivable";
    public static final String AFT_VAL_NON_CULTIVABLE = "AFTValNonCultivable";
    public static final String BRF_ACRE_ORCHARDS = "BRFAcreOrchards";
    public static final String BRF_VAL_ORCHARDS = "BRFValOrchards";
    public static final String AFT_ACRE_ORCHARDS = "AFTAcreOrchards";
    public static final String AFT_VAL_ORCHARDS = "AFTValOrchards";
    public static final String BRF_VAL_CROP = "BRFValCrop";
    public static final String AFT_VAL_CROP = "AFTValCrop";
    public static final String BRF_VAL_LIVESTOCK = "BRFValLivestock";
    public static final String AFT_VAL_LIVESTOCK = "AFTValLivestock";
    public static final String BRF_VAL_SMALL_BUSINESS = "BRFValSmallBusiness";
    public static final String AFT_VAL_SMALL_BUSINESS = "AFTValSmallBusiness";
    public static final String BRF_VAL_EMPLOYMENT = "BRFValEmployment";
    public static final String AFT_VAL_EMPLOYMENT = "AFTValEmployment";
    public static final String BRF_VAL_REMITTANCES = "BRFValRemittances";
    public static final String AFT_VAL_REMITTANCES = "AFTValRemittances";
    public static final String BRF_CNT_WAGEENR = "BRFCntWageEnr";
    public static final String AFT_CNT_WAGEENR = "AFTCntWageEnr";
    public static final String GPS_LONG_SWAP = "GPSLongSwap";
    public static final String SYNC_COL = "SyncStatus";
    public static final String W_RANK_COL = "WRank";


    public static final String LAST_SYNC_TIME_COL = "LastSyncTime";


    // Registration Member

    public static final String HHID_COL = "HHID";
    public static final String MEM_ID_COL = "MemID";
    public static final String REG_N_ASSIGN_PROG_SRV_HH_MEM_ID = "MemID";
    public static final String HH_MEM_ID = "HHMemID";
    public static final String MEM_NAME_COL = "MemName";
    public static final String RELATION_COL = "HHRelation";
    public static final String LMP_DATE = "LMPDate";
    public static final String CHILD_DOB = "ChildDOB";
    public static final String ELDERLY = "Elderly";
    public static final String DISABLE = "Disabled";
    public static final String MEM_AGE = "MemAge";
    public static final String BIRTH_YEAR_COL = "BirthYear";
    public static final String MARITAL_STATUS_COL = "MaritalStatus";
    public static final String CONTACT_NO_COL = "ContactNo";
    public static final String MEMBER_OTHER_ID_COL = "MemOtherID";
    public static final String MEM_NAME_FIRST_COL = "MemName_First";
    public static final String MEM_NAME_MIDDLE_COL = "MemName_Middle";
    public static final String MEM_NAME_LAST_COL = "MemName_Last";
    public static final String PHOTO_COL = "Photo";
    public static final String TYPE_ID_COL = "Type_ID";
    public static final String ID_NO_COL = "ID_NO";
    public static final String V_BSC_MEM_1_NAME_FIRST_COL = "V_BSCMemName1_First";
    public static final String V_BSC_MEM_1_NAME_MIDDLE_COL = "V_BSCMemName1_Middle";
    public static final String V_BSC_MEM_1_NAME_LAST_COL = "V_BSCMemName1_Last";
    public static final String V_BSC_MEM_1_TITLE_COL = "V_BSCMem1_TitlePosition";

    public static final String V_BSC_MEM_2_NAME_FIRST_COL = "V_BSCMemName2_First";
    public static final String V_BSC_MEM_2_NAME_MIDDLE_COL = "V_BSCMemName2_Middle";
    public static final String V_BSC_MEM_2_NAME_LAST_COL = "V_BSCMemName2_Last";
    public static final String V_BSC_MEM_2_TITLE_COL = "V_BSCMem2_TitlePosition";

    public static final String PROXY_DESIGNATION_COL = "Proxy_Designation";
    public static final String PROXY_NAME_FIRST_COL = "Proxy_Name_First";
    public static final String PROXY_NAME_MIDDLE_COL = "Proxy_Name_Middle";
    public static final String PROXY_NAME_LAST_COL = "Proxy_Name_Last";

    public static final String PROXY_BIRTH_YEAR_COL = "Proxy_BirthYear";
    public static final String PROXY_PHOTO_COL = "Proxy_Photo";
    public static final String PROXY_TYPE_ID_COL = "Proxy_Type_ID";
    public static final String PROXY_ID_NO_COL = "Proxy_ID_NO";
    public static final String PROXY_BSC_MEM_1_NAME_FIRST_COL = "P_BSCMemName1_First";
    public static final String PROXY_BSC_MEM_1_NAME_MIDDLE_COL = "P_BSCMemName1_Middle";
    public static final String PROXY_BSC_MEM_1_NAME_LAST_COL = "P_BSCMemName1_Last";
    public static final String PROXY_BSC_MEM_1_TITLE_COL = "P_BSCMem1_TitlePosition";

    public static final String PROXY_BSC_MEM_2_NAME_FIRST_COL = "P_BSCMemName2_First";
    public static final String PROXY_BSC_MEM_2_NAME_MIDDLE_COL = "P_BSCMemName2_Middle";
    public static final String PROXY_BSC_MEM_2_NAME_LAST_COL = "P_BSCMemName2_Last";
    public static final String PROXY_BSC_MEM_2_TITLE_COL = "P_BSCMem2_TitlePosition";
    public static final String MEM_TYPE_FLAG = "MemTypeFlag";


    /**
     * ADDED BY POP COLUMN FOR SERVICE TABLE
     */

    public static final String PUBLISHER_ID_COL = "PublisherID";
    public static final String PUBLISHER_NAME_COL = "PublisherName";
    public static final String SUBSCRIBER_M_CODE_COL = "SubscriberMCode";
    public static final String DEVICE_TYPE_ID_COL = "DeviceTypeID";
    public static final String M_CODE_COL = "MCode";
    public static final String M_LABLE_COL = "MLabel";
    public static final String M_ID_COL = "MID";
    public static final String DEVICE_ROLE_ID_COL = "DeviceRoleID";
    public static final String OFF_MODE_COL = "OFFMode";
    public static final String ON_MODE_COL = "ONMode";
    public static final String OPERATION_MODE_COL = "OperationMode";

    public static final String ADM_DONOR_CODE_COL = "AdmDonorCode";
    public static final String DONOR_CODE_COL = "DonorCode";
    public static final String ADM_AWARD_CODE_COL = "AdmAwardCode";
    public static final String AWARD_CODE_COL = "AwardCode";
    public static final String ADM_PROG_CODE_COL = "AdmProgCode";
    public static final String PROG_CODE_COL = "ProgCode";
    public static final String ADM_SRV_CODE_COL = "AdmSrvCode";
    public static final String SRV_CODE_COL = "SrvCode";
    public static final String OPERATION_CODE_COL = "OpCode";
    public static final String OP_MONTH_CODE_COL = "OpMonthCode";
    public static final String SERVICE_SL_COL = "ServiceSL";
    public static final String SERVICE_TABLE_SERVICE_SL_COL = "SrvSL";
    public static final String SERVICE_DT_COL = "ServiceDT";
    public static final String SERVICE_TABLE_SERVICE_DT_COL = "SrvDT";
    //    public static final String SERVICE_STATUS_COL = "ServiceStatus";
    public static final String SRV_STATUS_COL = "SrvStatus";
    public static final String WORK_DAY_COL = "WD";
    /**
     * DisNplan table
     */
    public static final String SRV_OP_MONTH_CODE_COL = "SrvOpMonthCode";

    // ADDED BY POP COLUMN FOR COUNTRY_AWARD TABLE
    /**
     * {@link #ADM_COUNTRY_AWARD_TABLE 's column }
     */

    public static final String AWARD_REF_N_COL = "AwardRefNumber";
    public static final String AWARD_START_DATE_COL = "AwardStartDate";
    public static final String AWARD_END_DATE_COL = "AwardEndDate";
    public static final String AWARD_SHORT_COL = "AwardShort";
    public static final String AWARD_SHORT_NAME_COL = "AwardShortName";
    public static final String AWARD_NAME_COL = "AwardName";
    public static final String AWARD_STATUS_COL = "AwardStatus";

    // ADDED BY POP COLUMN FOR DONOR TABLE
    public static final String DONOR_NAME_COL = "AdmDonorName";

    // ADDED BY POP COLUMN FOR PROGRAM TABLE

    public static final String PROGRAM_NAME_COL = "ProgramName";
    public static final String ADM_PROGRAM_MASTER_PROGRAM_NAME_COL = "ProgName";
    public static final String PROGRAM_SHORT_NAME_COL = "ProgShortName";
    public static final String MULTIPLE_SERVICE_FLAG_COL = "MultipleSrv";
    public static final String DEFAULT_FOOD_DAYS_COL = "DefaultFoodDays";
    public static final String DEFAULT_NO_FOOD_DAYS_COL = "DefaultNFoodDays";
    public static final String DEFAULT_CASH_DAYS_COL = "DefaultCashDays";
    public static final String DEFAULT_VOUCHAR_DAYS_COL = "DefaultVODays";
    public static final String SERVICE_SPECIFIC_FLAG_COL = "SrvSpecific";

    // ADDED BY POP COLUMN FOR PROGRAM TABLE

    public static final String SERVICE_NAME_COL = "ServiceName";
    public static final String SERVICE_MASTER_SERVICE_NAME_COL = "AdmSrvName";
    public static final String SERVICE_SHORT_NAME_COL = "ServiceShortName";
    public static final String SERVICE_MASTER_SERVICE_SHORT_NAME_COL = "AdmSrvShortName";

    // ADDED BY POP COLUMN FOR REG_N_ASSIGN_PROG_SRV_TABLE  TABLE

    public static final String REG_N_DAT_COL = "RegNDate";
    public static final String GRD_CODE_COL = "GRDCode";
    public static final String GRD_DATE_COL = "GRDDate";
    public static final String SRV_MIN_DATE_COL = "SrvMin";
    public static final String SRV_MAX_DATE_COL = "SrvMax";

    public static final String REG_N_STATUS_COL = "RegNStatus";
    public static final String GRD_STATUS_COL = "GRDStatus";


    // ADDED BY POP COLUMN FOR GRADUATION  TABLE

    public static final String GRD_TITLE_COL = "GRDTitle";
    public static final String DEFAULT_CAT_ACTIVE_COL = "DefaultCatActive";
    public static final String DEFAULT_CAT_EXIT_COL = "DefaultCatExit";


    // ADDED BY POP COLUMN FOR GPS GROUP TABLE

    public static final String GROUP_CODE_COL = "GrpCode";
    public static final String GROUP_NAME_COL = "GrpName";
    public static final String DESCRIPTION_COL = "Description";
    public static final String LAY_R1_CODE_COL = "LayR1Code";
    public static final String GRP_LAY_R2_LIST_CODE_COL = "LayR2Code";
    public static final String GRP_LAY_R3_LIST_CODE_COL = "LayR3Code";
    public static final String REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R1_LIST_CODE_COL = "GrpLayR1ListCode";
    public static final String REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R2_LIST_CODE_COL = "GrpLayR2ListCode";
    public static final String REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R3_LIST_CODE_COL = "GrpLayR3ListCode";


    public static final String PRIME_Y_N_COL = "PrimeYN";
    public static final String SUB_Y_N_COL = "SubYN";
    public static final String TECH_Y_N_COL = "TechYN";
    public static final String IMP_Y_N_COL = "ImpYN";
    public static final String LONG_Y_N_COL = "LogYN";
    public static final String OTH_Y_N_COL = "OthYN";
    public static final String ORGANIZATION_NAME = "OrgNName";
    public static final String ORGANIZATION_SHORT_NAME = "OrgNShortName";
    // for device information

    public static final String SELECTED_OPERATION_MODE_TABLE = "SelectedOperationMode";
    public static final String SELECTED_OPERATION_MODE_CODE_COL = "OperationModeCode";
    public static final String SELECTED_OPERATION_MODE_NAME_COL = "OperationModeName";


    // ADDED BY POP COLUMN FOR GPS SUB GROUP TABLE

    public static final String SUB_GROUP_CODE_COL = "SubGrpCode";
    public static final String SUB_GROUP_NAME_COL = "SubGrpName";

    // ADDED BY POP COLUMN FOR GPS LOCATION  TABLE

    public static final String LOCATION_CODE_COL = "LocationCode";
    public static final String LOCATION_NAME_COL = "LocationName";

    // ADDED BY POP COLUMN FOR GPS LOCATION  TABLE


    public static final String MONTH_LABEL_COL = "MonthLabel";
    public static final String START_DATE_COL = "StartDate";
    public static final String END_DATE_COL = "EndDate";

    public static final String USA_START_DATE_COL = "usaStartDate";
    public static final String USA_END_DATE_COL = "usaEndDate";
    public static final String STATUS = "Status";

    /**
     * {@link #REG_N_LM_TABLE 's column }
     */
    public static final String LM_DATE_COL = "LMDOB";
    public static final String GRDCODE_COL = "GRDCode";
    public static final String LMGRDDATE_COL = "LMGRDDate";
    public static final String CHILD_NAME_COL = "ChildName";
    public static final String CHILD_SEX_COL = "ChildSex";


    // ADDED BY POP COLUMN FOR SERVICE CENTER  TABLE


    public static final String SERVICE_CENTER_CODE_COL = "SrvCenterCode";
    public static final String SERVICE_CENTER_NAME_COL = "SrvCenterName";
    public static final String SERVICE_CENTER_ADDRESS_COL = "SrvCenterAddress";
    public static final String SERVICE_CENTER_CAT_COL = "SrvCenterCatCode";
    //  public static final String FDP_CODE_COL = "FDPcode";
    //public static final String SERVICE_CENTER_ADDRESS_COL ="SrvCenterAddress";

    // special condition constant
    public static final String SERVICE_SUMMARY_CRITERIA_QUERY = "serviceSummaryCriteria";
    public static final String GRADUATION_PROGRAM_QUERY = "graduationProgramQuery";

    // / ADDED BY POP COLUMN FOR REG_N_PW  TABLE


    public static final String PW_GRD_DATE_COL = "PWGRDDate";
    public static final String LMP_DATE_COL = "LMPDate";

    // ADDED BY POP COLUMN FOR REG_N_CA2  TABLE

    public static final String CA2_GRD_DATE_COL = "CA2GRDDate";
    public static final String CA2DOB_DATE_COL = "CA2DOB";

    // ADDED BY POP COLUMN FOR REG_N_CU2  TABLE


    public static final String CU2_GRD_DATE_COL = "CU2GRDDate";
    public static final String CU2DOB_DATE_COL = "CU2DOB";


    // ADDED BY POP COLUMN FOR STAFF_GEO_INFO_ACCESS_TABLE


    public static final String STAFF_CODE_COL = "StfCode";
    public static final String LAYR_LIST_CODE_COL = "LayRListCode";
    public static final String BTN_NEW_COL = "btnNew";
    public static final String BTN_SAVE_COL = "btnSave";
    public static final String BTN_DEL_COL = "btnDel";
    public static final String BTN_PEPR_COL = "btnPepr";

    public static final String BTN_APRV_COL = "btnAprv";
    public static final String BTN_REVW_COL = "btnRevw";
    public static final String BTN_VRFY_COL = "btnVrfy";
    public static final String BTN_DTRAN_COL = "btnDTran";


    // ADDED BY POP COLUMN FOR HOUSE_HOLD_CATEGORIES


    public static final String CATEGORY_CODE_COL = "HHHeadCatCode";
    public static final String VOUCHER_ITEM_CATEGORY_CODE_COL = "CatCode";
    public static final String CATEGORY_NAME_COL = "CatName";
    public static final String DT_CATEGORY_TABLE_CATEGORY_NAME_COL = "CategoryName";


    // ADDED BY POP COLUMN FOR HOUSE_HOLD_CATEGORIES


    public static final String REPORT_LABLE_COL = "RptLabel";
    public static final String REPORT_CODE_COL = "RptCode";

    // ADDED BY POP COLUMN FOR CARD_PRINT_REASON_TABLE

    public static final String CARD_PRINT_REASON_TITLE_COL = "ReasonTitle";
    public static final String CARD_PRINT_REASON_CODE_COL = "ReasonCode";

    // ADDED BY POP COLUMN FOR MEMBER_CARD_PRINT_TABLE

    public static final String REPORT_GROUP_COL = "RptGroup";
    public static final String CARD_REQUEST_SL_COL = "RequestSL";
    public static final String REQUEST_DATE_COL = "RequestDate";
    public static final String PRINT_DATE_COL = "PrintDate";
    public static final String PRINT_BY_COL = "PrintBy";
    public static final String DELIVERY_DATE_COL = "DeliveryDate";
    public static final String DELIVERY_BY_COL = "DeliveredBy";
    public static final String DELIVERY_STATUS_COL = "DelStatus";

    // COLOUMN FOR REGN_CT TABLE


    public static final String C11_CT_PR = "C11_CT_PR";
    public static final String C21_CT_PR = "C21_CT_PR";
    public static final String C31_CT_PR = "C31_CT_PR";
    public static final String C32_CT_PR = "C32_CT_PR";
    public static final String C33_CT_PR = "C33_CT_PR";
    public static final String C34_CT_PR = "C34_CT_PR";
    public static final String C35_CT_PR = "C35_CT_PR";
    public static final String C36_CT_PR = "C36_CT_PR";
    public static final String C37_CT_PR = "C37_CT_PR";
    public static final String C38_CT_PR = "C38_CT_PR";


    public static final String STAFF_FDP_ACCESS_TABLE = "StaffFDPAccess";
    public static final String FDP_MASTER_TABLE = "FDPMaster";
    public static final String FDP_CODE_COL = "FDPCode";
    public static final String FDP_NAME_COL = "FDPName";
    public static final String FDA_CATEGORIES_CODE_COL = "FDPCatCode";
    public static final String WH_CODE_COL = "WHCode";

    // RegN_ARG Column
    public static final String ELDERLY_YN_COL = "ElderlyYN";
    public static final String LAND_SIZE_COL = "LandSize";
    public static final String DEPEND_ON_GANYU_COL = "DependOnGanyu";
    public static final String WILLINGNESS_COL = "Willingness";
    public static final String WINTER_CULTIVATION_COL = "WinterCultivation";
    public static final String VULNERABLE_HH_COL = "VulnerableHH";
    public static final String PLANTING_VALUE_CHAIN_CROP_COL = "PlantingValueChainCrop";
    // RegN_vul Column
    public static final String Disabled_YN_COL = "DisabledYN";

    // LUP_SrvOptionList TABLE COLUMN
    public static final String LUP_OPTION_CODE_COL = "LUPOptionCode";
    public static final String LUP_OPTION_NAME_COL = "LUPOptionName";

    ////////////////SPECIAL QURY----------
//    public static final String VILLAGE_TABLE_QUERY_FROM_REG = "villageQuery";
    public static final String VILLAGE_TABLE_QUERY_FOR_RECORDS = "villageQueryForRecords";


    public static final String DISTRIBUTION_TABLE = "DistTable";
    public static final String DISTRIBUTION_STATUS_COL = "DistStatus";
    public static final String MEM_ID_15_D_COL = "ID";
    public static final String DIST_DATE_COL = "DistDate";
    public static final String DIST_DT_COL = "DistDT";

    public static final String DIST_FLAG_COL = "DistFlag";

    public static final String DIST_OP_MONTH_CODE_COL = "DisOpMonthCode";
    public static final String ORG_CODE_COL = "OrgCode";
    public static final String PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL = "OrgNCode";
    public static final String ORG_N_CODE_COL = "OrgNCode";
    public static final String ORG_N_DESG_N_CODE_COL = "OrgNDesgNCode";
    public static final String DISTRIBUTOR_COL = "Distributor";
    public static final String DISTRIBUTION_DATE_COL = "DistributionDate";
    public static final String PREPARED_BY_COL = "PreparedBy";
    public static final String VERIFIED_BY_COL = "VerifiedBy";
    public static final String APPROVED_BY_COL = "ApproveBy";


    // Voucher item table column
    public static final String ITEM_CODE_COL = "ItmCode";
    public static final String ITEM_NAME_COL = "ItmName";


    // Voucher item  meas table column
    public static final String MEAS_R_CODE_COL = "MeasRCode";
    public static final String UNITE_MEAS_COL = "UnitMeas";
    public static final String MEASE_TITLE_COL = "MeasTitle";


    public static final String VOUCHER_ITEM_SPEC_COL = "VOItmSpec";
    public static final String UNITE_COST_COL = "UnitCost";
    public static final String ACTIVE_STATUS_COL = "ActiveStatus";
    public static final String ACTIVE_COL = "Active";
    public static final String CURRENCY_COL = "Currency";
    public static final String VOUCHER_UNIT_COL = "VOItmUnit";
    public static final String VOUCHER_REFERENCE_NUMBER_COL = "VORefNumber";
    public static final String VOUCHER_ITEM_COST_COL = "VOItmCost";

    public static final String ATTRIBUTE_CODE_COL = "AttributeCode";
    public static final String ATTRIBUTE_TITLE_COL = "AttributeTitle";
    public static final String ATTRIBUTE_VALUE_COL = "AttributeValue";
    public static final String ATTRIBUTE_PHOTO_COL = "AttPhoto";
    public static final String DATA_TYPE_COL = "DataType";
    public static final String U_FILE_COL = "UFILE";
    //  public static final String LOOKUP_TABLE_NAME_COL = "LookUpCode";
    public static final String LOOK_UP_CODE_COL = "LookUpCode";
    public static final String LUP_GPS_TABLE_LOOK_UP_CODE_COL = "LupValueCode";
    public static final String GPS_SUB_GROUP_ATTRIBUTES_TABLE_LOOK_UP_CODE_COL = "LookUp";
    public static final String GPS_SUB_GROUP_ATTRIBUTES_TABLE_LOOK_UP_NAME_COL = "LupValueText";
    public static final String LOOK_UP_NAME_COL = "LookUpName";
    /**
     * For getListAndID() methods when the table name already use
     */
    public static final String CUSTOM_QUERY = "CustomQuery";
    /**
     * for Service Specification Coloumn
     */
    public static final String BABY_STATUS_COL = "BabyStatus";
    public static final String GMP_ATTENDACE_COL = "GMPAttendace";
    public static final String WEIGHT_STATUS_COL = "WeightStatus";
    public static final String NUT_ATTENDANCE_COL = "NutAttendance";
    public static final String VITA_UNDER5_COL = "VitA_Under5";
    public static final String EXCLUSIVE_CURRENTLYBF_COL = "Exclusive_CurrentlyBF";
    public static final String DATE_COMPFEEDING_COL = "DateCompFeeding";
    public static final String CMAMREF_COL = "CMAMRef";
    public static final String CMAMADD_COL = "CMAMAdd";
    public static final String ANCVISIT_COL = "ANCVisit";
    public static final String PNCVISIT_2D_COL = "PNCVisit_2D";
    public static final String PNCVISIT_1W_COL = "PNCVisit_1W";
    public static final String PNCVISIT_6W_COL = "PNCVisit_6W";
    public static final String DELIVERY_STAFF_1_COL = "DeliveryStaff_1";
    public static final String DELIVERY_STAFF_2_COL = "DeliveryStaff_2";
    public static final String DELIVERY_STAFF_3_COL = "DeliveryStaff_3";
    public static final String HOME_SUPPORT24H_1D_COL = "HomeSupport24H_1d";
    public static final String HOME_SUPPORT24H_2D_COL = "HomeSupport24H_2d";
    public static final String HOME_SUPPORT24H_3D_COL = "HomeSupport24H_3d";
    public static final String HOME_SUPPORT24H_8D_COL = "HomeSupport24H_8d";
    public static final String HOME_SUPPORT24H_14D_COL = "HomeSupport24H_14d";
    public static final String HOME_SUPPORT24H_21D_COL = "HomeSupport24H_21d";
    public static final String HOME_SUPPORT24H_30D_COL = "HomeSupport24H_30d";
    public static final String HOME_SUPPORT24H_60D_COL = "HomeSupport24H_60d";
    public static final String HOME_SUPPORT24H_90D_COL = "HomeSupport24H_90d";
    public static final String HOME_SUPPORT48H_1D_COL = "HomeSupport48H_1d";
    public static final String HOME_SUPPORT48H_3D_COL = "HomeSupport48H_3d";
    public static final String HOME_SUPPORT48H_8D_COL = "HomeSupport48H_8d";
    public static final String HOME_SUPPORT48H_30D_COL = "HomeSupport48H_30d";
    public static final String HOME_SUPPORT48H_60D_COL = "HomeSupport48H_60d";
    public static final String HOME_SUPPORT48H_90D_COL = "HomeSupport48H_90d";
    public static final String MATERNAL_BLEEDING_COL = "Maternal_Bleeding";
    public static final String MATERNAL_SEIZURE_COL = "Maternal_Seizure";
    public static final String MATERNAL_INFECTION_COL = "Maternal_Infection";
    public static final String MATERNAL_PROLONGEDLABOR_COL = "Maternal_ProlongedLabor";
    public static final String MATERNAL_OBSTRUCTEDLABOR_COL = "Maternal_ObstructedLabor";
    public static final String MATERNAL_PPRM_COL = "Maternal_PPRM";
    public static final String NBORN_ASPHYXIA_COL = "NBorn_Asphyxia";
    public static final String NBORN_SEPSIS_COL = "NBorn_Sepsis";
    public static final String NBORN_HYPOTHERMIA_COL = "NBorn_Hypothermia";
    public static final String NBORN_HYPERTHERMIA_COL = "NBorn_Hyperthermia";
    public static final String NBORN_NOSUCKLING_COL = "NBorn_NoSuckling";
    public static final String NBORN_JAUNDICE_COL = "NBorn_Jaundice";
    public static final String CHILD_DIARRHEA_COL = "Child_Diarrhea";
    public static final String CHILD_PNEUMONIA_COL = "Child_Pneumonia";
    public static final String CHILD_FEVER_COL = "Child_Fever";
    public static final String CHILD_CEREBRALPALSY_COL = "Child_CerebralPalsy";
    public static final String IMMU_POLIO_COL = "Immu_Polio";
    public static final String IMMU_BCG_COL = "Immu_BCG";
    public static final String IMMU_MEASLES_COL = "Immu_Measles";
    public static final String IMMU_DPT_HIB_COL = "Immu_DPT_HIB";
    public static final String IMMU_LOTTA_COL = "Immu_Lotta";
    public static final String IMMU_OTHER_COL = "Immu_Other";
    public static final String FPCOUNSEL_MALECONDOM_COL = "FPCounsel_MaleCondom";
    public static final String FPCOUNSEL_FEMALECONDOM_COL = "FPCounsel_FemaleCondom";
    public static final String FPCOUNSEL_PILL_COL = "FPCounsel_Pill";
    public static final String FPCOUNSEL_DEPO_COL = "FPCounsel_Depo";
    public static final String FPCOUNSEL_LONGPARMANENT_COL = "FPCounsel_LongParmanent";
    public static final String FPCOUNSEL_NOMETHOD_COL = "FPCounsel_NoMethod";
    public static final String CROP_CODE_COL = "CropCode";
    public static final String LOAN_SOURCE_COL = "LoanSource";
    public static final String LOAN_AMT_COL = "LoanAMT";
    public static final String ANIMAL_CODE_COL = "AnimalCode";
    public static final String LEAD_CODE_COL = "LeadCode";
    /**
     * For Group Community table
     */
    public static final String GROUP_CAT_CODE_COL = "GrpCatCode";
    /**
     * For Group Community Categories table
     */
    public static final String GROUP_CAT_NAME_COL = "GrpCatName";
    public static final String GROUP_CAT_SHORT_NAME_COL = "GrpCatShortName";


    /**
     * LUP_PROG_GROUP_CROP_TABLE FOR COLMN
     */
    public static final String CROP_NAME_COL = "CropList";
    public static final String CROP_CAT_COL = "CropCatCode";


    /**
     * LUP_COMMUNITY_ANIMAL_TABLE FOR COLMN
     */
    public static final String ANIMAL_TYPE_COL = "AnimalType";

    /**
     * LUP_COMMUNITY_LOAN_SOURCE_TABLE FOR COLMN
     */
    public static final String LOAN_CODE_COL = "LoanCode";

    /**
     * {@link #LUP_COMMUNITY_LEAD_POSITION_TABLE 's column }
     */
    public static final String LEAD_POSITION_COL = "LeadPosition";

    /**
     * {@link #LUP_COMMUNITY_FUND_SOURCE_TABLE 's column }
     */
    public static final String FUND_CODE_COL = "FundCode";
    public static final String FUND_SOURCE_COL = "FundSource";


    /**
     * {@link #LUP_COMMUNITY_IRRIGATION_SYSTEM_TABLE 's column }
     */
    public static final String IRRI_SYS_CODE_COL = "IrriSysCode";
    public static final String IRRI_SYS_NAME_COL = "IrriSysName";

    public static final String CONTENT_CODE_COL = "ContentCode";
    public static final String IMAGE_FILE_COL = "ImageFile";
    public static final String REMARKES_COL = "Remarks";


    public static final String WEALTH_RANKING_COL = "WealthRanking";
    public static final String MEMBER_EXT_GROUP_COL = "MemberExtGroup";
    public static final String ORPHAN_CHILDREN_COL = "OrphanedChildren";
    public static final String CHILD_HEADED_COL = "ChildHeaded";
    public static final String ELDERLY_HEADED_COL = "ElderlyHeaded";
    public static final String CHRONICALLY_ILL_COL = "ChronicallyIll";
    public static final String CROP_FAILURE_COL = "CropFailure";
    public static final String FEMALE_HEADED_COL = "FemaleHeaded";
    public static final String CHILDREN_REC_SUPP_FEED_N_COL = "ChildrenRecSuppFeedN";


    public static final String AG_INVC_COL = "AGOINVC";
    public static final String AG_NASFAM_COL = "AGONASFAM";
    public static final String AG_CU_COL = "AGOCU";
    public static final String AG_OTHER_COL = "AGOOther";
    public static final String AG_L_S_GOAT_COL = "LSGoat";
    public static final String AG_L_S_CHICKEN_COL = "LSChicken";
    public static final String AG_L_S_PIGION_COL = "LSPigeon";
    public static final String AG_L_S_OTHER_COL = "LSOther";


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db, int version) {
        dropTableFromExportedDatabase(db);
        db.execSQL(Schema.sqlCreateUploadTable());
        db.execSQL(Schema.sqlCreateUploadPhysicalTable());
    }

    /**
     * this method is for only Exported data base
     *
     * @param context the invoking activity or MainActivity
     * @param version by default ertar value 1 thakbe
     */
    public SQLiteHandler(Context context, int version) {
        super(context, EXTERNAL_DATABASE_NAME, null, version);
        SQLiteDatabase extranal = this.getWritableDatabase();
        onCreate(extranal, version);

    }

    public String getSubscriberNPublisherID(String macID) {
        String fileName = "";
        String sourceID = "";
        String distinationID = "";
        String opCode = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = " SELECT " + M_CODE_COL + " AS SubscriberID "
                + " , " + DEVICE_ROLE_ID_COL
                + " FROM " + ADM_MACHINE_REGISTRY_TABLE
                + " WHERE " + M_ID_COL + " = '" + macID + "' ";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null && cursor.moveToFirst()) {
            sourceID = cursor.getString(cursor.getColumnIndex("SubscriberID"));
            opCode = cursor.getString(cursor.getColumnIndex(DEVICE_ROLE_ID_COL));
        }
        if (cursor != null)
            cursor.close();

        String sql_1 = " SELECT " + M_CODE_COL + " AS PublisherID "
                + " FROM " + ADM_MACHINE_PUBLISHER_TABLE
                + " WHERE " + SUBSCRIBER_M_CODE_COL + " = '" + sourceID + "' ";
        Cursor cursor_2 = db.rawQuery(sql_1, null);

        if (cursor_2 != null && cursor_2.moveToFirst()) {
            distinationID = cursor_2.getString(cursor_2.getColumnIndex("PublisherID"));
        }
        if (cursor_2 != null)
            cursor_2.close();


        fileName = sourceID + "_" + distinationID + "_" + opCode;
        if (sourceID.length() == 0 || distinationID.length() == 0 || opCode.length() == 0)
            fileName = "";
        db.close();
        return fileName;
    }

    /**
     * ei method orginal data base theke data niye exported data base Upload syntax gulo insert korabe
     *
     * @param context the invoking activity or MainActivity
     */
    public void insertIntoExportDataBase(Context context) {
        List<dataUploadDB> list = new ArrayList<>();

        String path = "/data/data/" + context.getPackageName() + "/databases/";

        SQLiteDatabase extralDatabase, originalDatabase;
        extralDatabase = SQLiteDatabase.openDatabase(path + EXTERNAL_DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        originalDatabase = SQLiteDatabase.openDatabase(path + DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);


        extralDatabase.delete(UPLOAD_SYNTAX_TABLE, null, null);

        String sql = "SELECT  * FROM " + UPLOAD_SYNTAX_TABLE +// " WHERE " + SYNC_COL + "=0 "
                " ORDER BY " + ID_COL + " ASC ";
        Cursor cursor = originalDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                dataUploadDB data = new dataUploadDB();
                data._id = cursor.getString(cursor.getColumnIndex(ID_COL));
                data._syntax = cursor.getString(cursor.getColumnIndex(SQL_QUERY_SYNTAX));
                list.add(data);

            } while (cursor.moveToNext());
            cursor.close();
        }

        //  originalDatabase.close();


        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ContentValues values = new ContentValues();
                values.put(SQL_QUERY_SYNTAX, list.get(i)._syntax);
                values.put(SYNC_COL, "0");


                extralDatabase.insert(UPLOAD_SYNTAX_TABLE, null, values);

            }
        }


        List<dataUploadDB> list_1 = new ArrayList<>();

        extralDatabase.delete(UPLOAD_PHYSICAL_TABLE_SYNTAX_TABLE, null, null);

        String sql_1 = "SELECT  " + SQL_QUERY_SYNTAX
                + " , " + DT_R_SEQ_COL
                + " FROM " + UPLOAD_PHYSICAL_TABLE_SYNTAX_TABLE;

        Cursor cursor_1 = originalDatabase.rawQuery(sql_1, null);
        if (cursor_1.moveToFirst()) {
            do {
                dataUploadDB data = new dataUploadDB();
//                data._id = cursor.getString(cursor.getColumnIndex(ID_COL));
                data._syntax = cursor_1.getString(0);
                data._sqn = cursor_1.getInt(1);
                list_1.add(data);

            } while (cursor_1.moveToNext());
            cursor_1.close();
        }

        originalDatabase.close();


        if (list_1.size() > 0) {
            for (int i = 0; i < list_1.size(); i++) {
                ContentValues values = new ContentValues();
                values.put(SQL_QUERY_SYNTAX, list_1.get(i)._syntax);
                values.put(DT_R_SEQ_COL, list_1.get(i)._sqn);
                values.put(SYNC_COL, "0");
                values.put(PORTABLE_DEVICE_ID_COL, UtilClass.getDeviceId(context));


                extralDatabase.insert(UPLOAD_PHYSICAL_TABLE_SYNTAX_TABLE, null, values);

            }
        }


        extralDatabase.close();

    }

    /**
     * Copies the database file at the specified location over the current
     * internal application database.
     */
    public boolean importDatabase(String scrDBPath, Context context) throws IOException {

        // Close the SQLiteOpenHelper so it will commit the created empty
        // database to internal storage.
        close();
        File newDb = new File(scrDBPath);
        File packageDb = new File("/data/data/" + context.getPackageName() + "/databases/"
                + DATABASE_NAME);

//        File oldDbpath = new File("/data/data/" + context.getPackageName() + "/databases/");


        if (newDb.exists()) {
            // // TODO: 6/29/2017  create directories  for old directories
            if (!packageDb.exists()) {
                new SQLiteHandler(context);
            }


            FileUtils.copyFile(new FileInputStream(newDb), new FileOutputStream(packageDb));             // Access the copied database so SQLiteHelper will cache it and mark


            getWritableDatabase().close();                                                          // it as created.
            return true;
        }
        return false;
    }

    public void reCreateSurveyTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_SURVEY_TABLE);
        db.execSQL(Schema.createTableDTSurveyTable());
        db.close();
    }


    private void dropTableFromExportedDatabase(SQLiteDatabase db) {

        db.execSQL(DROP_TABLE_IF_EXISTS + LOGIN_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + COUNTRY_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + VALID_DATE_RANGE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DISTRICT_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + UPAZILLA_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + GEO_LAY_R3_LIST_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + VILLAGE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_HH_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + REGISTRATION_MEMBER_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + RELATION_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + ADM_COUNTRY_AWARD_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + ADM_DONOR_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + ADM_PROGRAM_MASTER_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_MASTER_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_ASSIGN_PROG_SRV_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + GPS_GROUP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + GPS_SUB_GROUP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + GPS_LOCATION_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + OP_MONTH_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + COUNTRY_PROGRAM_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_LM_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_PW_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_CU2_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_CA2_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_GEO_INFO_ACCESS_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_REGNH_HEAD_CATEGORY_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_LUP_GRADUATION_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + REPORT_TEMPLATE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + CARD_PRINT_REASON_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + MEMBER_CARD_PRINT_TABLE);
        // db.execSQL(DROP_TABLE_IF_EXISTS + UPLOAD_SYNTAX_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + FDP_MASTER_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_FDP_ACCESS_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_CT_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DISTRIBUTION_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_AGR_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_VUL_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_CENTER_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LAYER_LABEL_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + VOUCHER_ITEM_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + VOUCHER_ITEM__MEAS_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_EXTENDED_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DISTRIBUTION_EXTENDED_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + SELECTED_VILLAGE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + GPS_SUB_GROUP_ATTRIBUTES_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_GPS_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + GPS_LOCATION_ATTRIBUTES_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_SPECIFIC_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + COMMUNITY_GROUP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_ANIMAL_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_PROG_GROUP_CROP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_LOAN_SOURCE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_LEAD_POSITION_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_MEM_PROG_GRP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + COMMUNITY_GROUP_CATEGORY_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + GPS_LOCATION_CONTENT_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_FFA_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_WE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DIST_N_PLAN_BASIC_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + COMMUNITY_GRP_DETAIL_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + PROGRAM_ORGANIZATION_NAME_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + PROGRAM_ORGANIZATION_ROLE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_MASTER_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_MASTER_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_GPS_LIST_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_A_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_BASIC_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_CATEGORY_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_COUNTRY_PROGRAM_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DTGEO_LIST_LEVEL_COL);
        db.execSQL(DROP_TABLE_IF_EXISTS + DTGEO_LIST_LEVEL_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DTQRES_MODE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DTQ_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_RESPONSE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_SURVEY_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_TABLE_DEFINITION_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DTTABLE_LIST_CATEGORY_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_ENU_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DT_LUP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + DTA_SKIP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + TEMPORARY_COUNTRY_PROGRAM_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + TEMPORARY_OP_MONTH_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + SELECTED_COUNTRY_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + ADM_AWARD_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + SELECTED_OPERATION_MODE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_SRV_CENTER_ACCESS_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + TA_MASTER_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + TA_CATEGORY_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + TA_EVENT_TOPIC_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + TA_GROUP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + TA_PARTICIPANTS_LIST_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + TA_PART_ORG_N_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + TA_POS_PARTICIPANTS_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + TA_SUB_GROUP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + TA_TOPIC_CHILD_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + TA_TOPIC_MASTER_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_TA_PATICIPANT_CAT_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + SELECTED_FDP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + SELECTED_SERVICE_CENTER_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LAST_SYNC_TYRACE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_SRV_OPTION_LIST_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_REGN_ADDRESS_LOOKUP_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_FUND_SOURCE_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_IRRIGATION_SYSTEM_TABLE);

    }

    // Creating Tables

    /**
     * this method create tables in the db
     *
     * @param db sqlite data base reference
     *           invoked by  {@link #refreshDatabase(SQLiteDatabase)}
     *           {@link #deleteReferenceTable()}
     *           {@link #deleteUsers(SQLiteDatabase)}
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Schema.sqlCreateUploadTable());
        db.execSQL(Schema.sqlCreateUploadPhysicalTable());
        db.execSQL(Schema.sqlCreateUserLoginTable());
        db.execSQL(Schema.sqlCreateStaffMasterTable());
        db.execSQL(Schema.sqlCreateCountry());
        db.execSQL(Schema.sqlCreateLayerLabel());
        db.execSQL(Schema.sqlCreateDistrict());
        db.execSQL(Schema.sqlCreateUpazilla());
        db.execSQL(Schema.sqlCreateUnit());
        db.execSQL(Schema.sqlCreateVillage());
        db.execSQL(Schema.sqlCreateDateRange());
        db.execSQL(Schema.sqlCreateRegistration());
        db.execSQL(Schema.sqlCreateRegRelation());
        db.execSQL(Schema.sqlCreateRegMember());
        db.execSQL(Schema.sqlCreateServiceTable());
        db.execSQL(Schema.sqlCreateCountryAwardTable());
        db.execSQL(Schema.sqlCreateAdmAwardTable());
        db.execSQL(Schema.sqlCreateDonorTable());
        db.execSQL(Schema.sqlCreateProgramMasterTable());
        db.execSQL(Schema.sqlCreateServiceMasterTable());
        db.execSQL(Schema.sqlCreateRegNAssignPrgSrvTable());
        db.execSQL(Schema.sqlCreateGpsGroupTable());
        db.execSQL(Schema.sqlCreateGpsSubGroupTable());
        db.execSQL(Schema.sqlCreateGpsLocationTable());
        db.execSQL(Schema.sqlCreateOpMonthTable());
        db.execSQL(Schema.sqlCreateADM_CountryProgram());
        db.execSQL(Schema.sqlCreateRegNLMTable());
        db.execSQL(Schema.sqlCreateServiceCenterTable());
        db.execSQL(Schema.sqlCreateRegNPWTable());
        db.execSQL(Schema.sqlCreateRegNCU2Table());
        db.execSQL(Schema.sqlCreateRegNCA2Table());
        db.execSQL(Schema.sqlCreateStaffGeoInfoAccessTable());
        db.execSQL(Schema.sqlCreateStaffSrvCenterAccessTable());
        db.execSQL(Schema.sqlCreateHouseHoldCategoryTable());
        db.execSQL(Schema.sqlCreateGraduationTable());
        db.execSQL(Schema.sqlCreateCardTypeTable());
        db.execSQL(Schema.sqlCreateCardPrintReasonTable());
        db.execSQL(Schema.sqlCreateRegMemberCardPrintTable());
        db.execSQL(Schema.sqlCreate_RegN_CT_Table());
        db.execSQL(Schema.sqlCreateStaffFDPAccessTable());
        db.execSQL(Schema.sqlCreateFDP_Master_Table());
        db.execSQL(Schema.sqlCreateDistributionTable());
        db.execSQL(Schema.sqlCreateRegN_AGR_Table());
        db.execSQL(Schema.sqlCreateLUP_SrvOptionList());
        db.execSQL(Schema.sqlCreateRegNVUL_Table());
        db.execSQL(Schema.sqlCreateVoucherItem_Table());
        db.execSQL(Schema.sqlCreateVoucherItemMeas_Table());
        db.execSQL(Schema.sqlCreateVoucherCountryProgItem_Table());
        db.execSQL(Schema.sqlCreateServiceExtended_Table());
        db.execSQL(Schema.sqlCreateDistributionExtended_Table());
        db.execSQL(Schema.sqlCreateSelectedFDP_Table());
        db.execSQL(Schema.sqlCreateSelectedServiceCenter_Table());
        db.execSQL(Schema.sqlCreateCommunityGroup_Table());
        db.execSQL(Schema.sqlCreateGPSSubGroupAttributes_Table());
        db.execSQL(Schema.sqlCreateLUP_GPS_Table());
        db.execSQL(Schema.sqlCreateGPSLocationAttributes_Table());
        db.execSQL(Schema.sqlCreateServiceSpecification_Table());
        db.execSQL(Schema.sqlCreateLUP_CommunityAnimalList_Table());
        db.execSQL(Schema.sqlCreateLUP_ProgramGroupCrop_Table());
        db.execSQL(Schema.sqlCreateLUP_CommunityLoanSource_Table());
        db.execSQL(Schema.sqlCreateLUP_CommunityLeadPosition_Table());
        db.execSQL(Schema.sqlCreateRegNmemProgGrp_Table());
        db.execSQL(Schema.sqlCreateCommunityGroupCategoryes_Table());
        db.execSQL(Schema.sqlCreate_Gps_Location_Content_Table());
        // TODO: 11/13/2016  redesign  last sync time
        db.execSQL(Schema.createTableLastSyncTime());
        db.execSQL(Schema.createTableRegN_FFA());
        db.execSQL(Schema.createTableRegN_WE());
        db.execSQL(Schema.sqlCreateDistNPlanBasic());
        db.execSQL(Schema.createTableLUP_RegNAddLookup());
        db.execSQL(Schema.createTableCommunityGrpDetail());
        db.execSQL(Schema.createTableProgOrgNRole());
        db.execSQL(Schema.createTableProgOrgN());
        db.execSQL(Schema.sqlCreateLUP_GpsList());
        db.execSQL(Schema.createTableDTATable()); /**         * for Dynamic Module         */
        db.execSQL(Schema.createTableDTBasic());
        db.execSQL(Schema.createTableDTCategory());
        db.execSQL(Schema.createTableDTCountryProgram());
        db.execSQL(Schema.createTableDTGeoListLevel());
        db.execSQL(Schema.createTableDTQResMode());
        db.execSQL(Schema.createTableDTQTable());
        db.execSQL(Schema.createTableDTResponseTable());
        db.execSQL(Schema.createTableDTSurveyTable());
        db.execSQL(Schema.createDTEnuTable());
        db.execSQL(Schema.createTableDTTableDefinition());
        db.execSQL(Schema.createTaleDTTableListCategory());
        db.execSQL(Schema.createTaleDT_LUP_Table());
        db.execSQL(Schema.createTableDTASkipTable());
        /**  training & activity */
        db.execSQL(Schema.createTAMasterTable());
        db.execSQL(Schema.createTACategoryTable());
        db.execSQL(Schema.createTAEventTopicTable());
        db.execSQL(Schema.createTAGroupTable());
        db.execSQL(Schema.createTAParticipantsTable());
        db.execSQL(Schema.createTAPartOrgNTable());
        db.execSQL(Schema.createTAPosParticipantsTable());
        db.execSQL(Schema.createTASubGroupTable());
        db.execSQL(Schema.createTATopicChildTable());
        db.execSQL(Schema.createTATopicMasterTable());
        db.execSQL(Schema.crateLUP_TAParticipantCat());

        db.execSQL(Schema.sqlCreateLUP_CommunityFundSource_Table());
        db.execSQL(Schema.sqlCreateLUP_CommunityIrrigationSystem_Table());

        /** * temporary  table */
        db.execSQL(Schema.sqlCreateTemporary_CountryProgram());
        db.execSQL(Schema.sqlCreateTemporary_OpMonthTable());
        db.execSQL(Schema.sqlCreateSelectedVillage_Table());
        db.execSQL(Schema.sqlCreateSelectedCountry());
        db.execSQL(Schema.sqlCreateOperationModeTable());                                           // device information

        db.execSQL(Schema.sqlCreateAdmMachineRegistry_Table());                                     //  FOR IMPORT EXPORT device information
        db.execSQL(Schema.sqlCreateAdmMachinePublisher_Table());                                    //  FOR IMPORT EXPORT device information


        Log.d(TAG, "  Create All Table ");


    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        refreshDatabase(db);
    }

    public void clearUploadSyntaxTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(UPLOAD_SYNTAX_TABLE, null, null);
    }

    /**
     * Drop All the Table to alter the Any table column in table.
     * after droping all the table it called {@link #onCreate(SQLiteDatabase)} method to create tables
     *
     * @param db database
     */
    private void refreshDatabase(SQLiteDatabase db) {

        //SQLiteDatabase db = this.getWritableDatabase();

//        Log.d(TAG, "Dropping all table..");

        // Drop older table if existed
        try {

            db.execSQL(DROP_TABLE_IF_EXISTS + VOUCHER_ITEM_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + VOUCHER_ITEM__MEAS_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LOGIN_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + COUNTRY_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + VALID_DATE_RANGE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DISTRICT_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + UPAZILLA_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GEO_LAY_R3_LIST_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + VILLAGE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_HH_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REGISTRATION_MEMBER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + RELATION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + ADM_COUNTRY_AWARD_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + ADM_DONOR_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + ADM_PROGRAM_MASTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_MASTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_ASSIGN_PROG_SRV_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_GROUP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_SUB_GROUP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_LOCATION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + OP_MONTH_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + COUNTRY_PROGRAM_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_LM_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_PW_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_CU2_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_CA2_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_GEO_INFO_ACCESS_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_REGNH_HEAD_CATEGORY_TABLE);
            //db.execSQL(DROP_TABLE_IF_EXISTS + LIBERIA_REGISTRATION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_LUP_GRADUATION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REPORT_TEMPLATE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + CARD_PRINT_REASON_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + MEMBER_CARD_PRINT_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + UPLOAD_SYNTAX_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + FDP_MASTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_FDP_ACCESS_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_CT_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DISTRIBUTION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_AGR_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_VUL_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_CENTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LAYER_LABEL_TABLE);

            db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_EXTENDED_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DISTRIBUTION_EXTENDED_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + SELECTED_VILLAGE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_SUB_GROUP_ATTRIBUTES_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_GPS_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_LOCATION_ATTRIBUTES_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_SPECIFIC_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + COMMUNITY_GROUP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_ANIMAL_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_PROG_GROUP_CROP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_LOAN_SOURCE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_LEAD_POSITION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_MEM_PROG_GRP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + COMMUNITY_GROUP_CATEGORY_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_LOCATION_CONTENT_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_FFA_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_WE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DIST_N_PLAN_BASIC_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + COMMUNITY_GRP_DETAIL_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + PROGRAM_ORGANIZATION_NAME_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + PROGRAM_ORGANIZATION_ROLE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_MASTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_MASTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_GPS_LIST_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_A_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_BASIC_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_CATEGORY_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_COUNTRY_PROGRAM_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DTGEO_LIST_LEVEL_COL);
            db.execSQL(DROP_TABLE_IF_EXISTS + DTGEO_LIST_LEVEL_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DTQRES_MODE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DTQ_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_RESPONSE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_SURVEY_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_TABLE_DEFINITION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DTTABLE_LIST_CATEGORY_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_ENU_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_LUP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DTA_SKIP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + TEMPORARY_COUNTRY_PROGRAM_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + TEMPORARY_OP_MONTH_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + SELECTED_COUNTRY_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + ADM_AWARD_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + SELECTED_OPERATION_MODE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_SRV_CENTER_ACCESS_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + TA_MASTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + TA_CATEGORY_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + TA_EVENT_TOPIC_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + TA_GROUP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + TA_PARTICIPANTS_LIST_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + TA_PART_ORG_N_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + TA_POS_PARTICIPANTS_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + TA_SUB_GROUP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + TA_TOPIC_CHILD_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + TA_TOPIC_MASTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_TA_PATICIPANT_CAT_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_FUND_SOURCE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_IRRIGATION_SYSTEM_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + ADM_MACHINE_REGISTRY_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + ADM_MACHINE_PUBLISHER_TABLE);


//            Log.d(TAG, "All table Dropped.");
        } catch (Exception e) {
//            Log.d(TAG, "Error: " + e.getMessage());
        }


        // Create tables again
        onCreate(db);
    }


    /**
     * Re crate database Delete all reference tables and create them again     *
     * invoked by the {@link SyncDatabase#checkLoginAndDowenReftData(String, String, JSONArray, String)}
     */
    public void deleteReferenceTable() {

        SQLiteDatabase db = this.getWritableDatabase();


        try {

//            db.delete(COUNTRY_TABLE, null, null);
//            db.delete(VALID_DATE_RANGE, null, null);
//
//            db.delete(RELATION_TABLE, null, null);
//            /**
//             * todo do not delete AWARd Table program table Service Table
//             */
//
//            db.delete(ADM_DONOR_TABLE, null, null);
//            db.delete(ADM_PROGRAM_MASTER_TABLE, null, null);
//            db.delete(SERVICE_MASTER_TABLE, null, null);
//
//            db.delete(OP_MONTH_TABLE, null, null);
//
//            db.delete(LUP_REGNH_HEAD_CATEGORY_TABLE, null, null);
//            db.delete(REG_N_LUP_GRADUATION_TABLE, null, null);
//            db.delete(LAYER_LABEL_TABLE, null, null);
//            db.delete(REPORT_TEMPLATE_TABLE, null, null);
//            db.delete(CARD_PRINT_REASON_TABLE, null, null);
//            db.delete(FDP_MASTER_TABLE, null, null);
//            db.delete(STAFF_FDP_ACCESS_TABLE, null, null);
//            db.delete(LUP_SRV_OPTION_LIST_TABLE, null, null);
//            db.delete(SERVICE_TABLE, null, null);
//            db.delete(SERVICE_EXTENDED_TABLE, null, null);


//            Log.d(TAG, "All Reference data Deleted.");
        } catch (Exception e) {
//            Log.d(TAG, "Error: " + e.getMessage());
        }

        //db.close();

        onCreate(db);
    }

    /**
     * Delete selected Village TABLE,selected FDp TABLE,selected Service TABLE,selected Country TABLE
     * and invoking {@link #deleteUsers(SQLiteDatabase)} method
     */
    public void deleteUsersWithSelected_LayR4_FDP_Srv_Country() {
        SQLiteDatabase db = this.getWritableDatabase();
        deleteUsers(db);


        db.delete(SELECTED_VILLAGE_TABLE, null, null);

        db.delete(SELECTED_FDP_TABLE, null, null);

        db.delete(SELECTED_SERVICE_CENTER_TABLE, null, null);

        db.delete(SELECTED_COUNTRY_TABLE, null, null);

        db.close();

    }

    /**
     * Re crate database Delete all tables and create them again
     * todo optimize code
     */
    public void deleteUsers(SQLiteDatabase db) {

        // SQLiteDatabase db = this.getWritableDatabase();

//        Log.d(TAG, "Deleting all user data..");

        try {
            // Delete All Rows
            db.delete(DT_A_TABLE, null, null);
            db.delete(DT_BASIC_TABLE, null, null);
            db.delete(DT_CATEGORY_TABLE, null, null);
            db.delete(DT_COUNTRY_PROGRAM_TABLE, null, null);
            db.delete(DTGEO_LIST_LEVEL_TABLE, null, null);
            db.delete(DTQRES_MODE_TABLE, null, null);
            db.delete(DTQ_TABLE, null, null);
            db.delete(DT_RESPONSE_TABLE, null, null);
            db.delete(DT_SURVEY_TABLE, null, null);
            db.delete(DT_TABLE_DEFINITION_TABLE, null, null);
            db.delete(DTTABLE_LIST_CATEGORY_TABLE, null, null);
            db.delete(DT_LUP_TABLE, null, null);
            db.delete(DT_ENU_TABLE, null, null);
            db.delete(LOGIN_TABLE, null, null);
            db.delete(COUNTRY_TABLE, null, null);
            db.delete(VALID_DATE_RANGE, null, null);
            db.delete(DISTRICT_TABLE, null, null);
            db.delete(UPAZILLA_TABLE, null, null);
            db.delete(GEO_LAY_R3_LIST_TABLE, null, null);
            db.delete(VILLAGE_TABLE, null, null);
            db.delete(REG_N_HH_TABLE, null, null);
            db.delete(REGISTRATION_MEMBER_TABLE, null, null);
            db.delete(RELATION_TABLE, null, null);
            db.delete(SERVICE_TABLE, null, null);
            db.delete(ADM_COUNTRY_AWARD_TABLE, null, null);
            db.delete(ADM_DONOR_TABLE, null, null);
            db.delete(ADM_PROGRAM_MASTER_TABLE, null, null);
            db.delete(SERVICE_MASTER_TABLE, null, null);
            db.delete(REG_N_ASSIGN_PROG_SRV_TABLE, null, null);
            db.delete(GPS_GROUP_TABLE, null, null);
            db.delete(GPS_SUB_GROUP_TABLE, null, null);
            db.delete(GPS_LOCATION_TABLE, null, null);
            db.delete(OP_MONTH_TABLE, null, null);
            db.delete(COUNTRY_PROGRAM_TABLE, null, null);
            db.delete(REG_N_LM_TABLE, null, null);
            db.delete(REG_N_PW_TABLE, null, null);
            db.delete(REG_N_CU2_TABLE, null, null);
            db.delete(REG_N_CA2_TABLE, null, null);
            db.delete(STAFF_GEO_INFO_ACCESS_TABLE, null, null);
            db.delete(STAFF_SRV_CENTER_ACCESS_TABLE, null, null);
            db.delete(LUP_REGNH_HEAD_CATEGORY_TABLE, null, null);

            db.delete(REG_N_LUP_GRADUATION_TABLE, null, null);
            db.delete(REPORT_TEMPLATE_TABLE, null, null);
            db.delete(CARD_PRINT_REASON_TABLE, null, null);
            db.delete(MEMBER_CARD_PRINT_TABLE, null, null);
            db.delete(UPLOAD_SYNTAX_TABLE, null, null);
            db.delete(FDP_MASTER_TABLE, null, null);
            db.delete(STAFF_FDP_ACCESS_TABLE, null, null);
            db.delete(REG_N_CT_TABLE, null, null);
            db.delete(DISTRIBUTION_TABLE, null, null);
            db.delete(REG_N_AGR_TABLE, null, null);
            db.delete(REG_N_VUL_TABLE, null, null);
            db.delete(SERVICE_CENTER_TABLE, null, null);
            db.delete(LAYER_LABEL_TABLE, null, null);
            db.delete(VOUCHER_ITEM_TABLE, null, null);
            db.delete(VOUCHER_ITEM__MEAS_TABLE, null, null);
            db.delete(VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE, null, null);
            db.delete(SERVICE_EXTENDED_TABLE, null, null);
            db.delete(DISTRIBUTION_EXTENDED_TABLE, null, null);
            db.delete(GPS_SUB_GROUP_ATTRIBUTES_TABLE, null, null);
            db.delete(LUP_GPS_TABLE, null, null);
            db.delete(GPS_LOCATION_ATTRIBUTES_TABLE, null, null);
            db.delete(SERVICE_SPECIFIC_TABLE, null, null);
            db.delete(COMMUNITY_GROUP_TABLE, null, null);

            db.delete(LUP_COMMUNITY_ANIMAL_TABLE, null, null);
            db.delete(LUP_PROG_GROUP_CROP_TABLE, null, null);
            db.delete(LUP_COMMUNITY_LOAN_SOURCE_TABLE, null, null);
            db.delete(LUP_COMMUNITY_LEAD_POSITION_TABLE, null, null);
            db.delete(LUP_COMMUNITY_FUND_SOURCE_TABLE, null, null);
            db.delete(LUP_COMMUNITY_IRRIGATION_SYSTEM_TABLE, null, null);

            db.delete(REG_N_MEM_PROG_GRP_TABLE, null, null);
            db.delete(COMMUNITY_GROUP_CATEGORY_TABLE, null, null);
            db.delete(GPS_LOCATION_CONTENT_TABLE, null, null);
            db.delete(REG_N_FFA_TABLE, null, null);
            db.delete(REG_N_WE_TABLE, null, null);
            db.delete(DIST_N_PLAN_BASIC_TABLE, null, null);
            db.delete(COMMUNITY_GRP_DETAIL_TABLE, null, null);
            db.delete(PROGRAM_ORGANIZATION_NAME_TABLE, null, null);
            db.delete(PROGRAM_ORGANIZATION_ROLE_TABLE, null, null);
            db.delete(STAFF_MASTER_TABLE, null, null);
            db.delete(LUP_GPS_LIST_TABLE, null, null);
            db.delete(ADM_AWARD_TABLE, null, null);
            db.delete(SELECTED_OPERATION_MODE_TABLE, null, null);
            db.delete(TA_MASTER_TABLE, null, null);
            db.delete(TA_CATEGORY_TABLE, null, null);
            db.delete(TA_EVENT_TOPIC_TABLE, null, null);
            db.delete(TA_GROUP_TABLE, null, null);
            db.delete(TA_PARTICIPANTS_LIST_TABLE, null, null);
            db.delete(TA_PART_ORG_N_TABLE, null, null);
            db.delete(TA_POS_PARTICIPANTS_TABLE, null, null);
            db.delete(TA_SUB_GROUP_TABLE, null, null);
            db.delete(TA_TOPIC_CHILD_TABLE, null, null);
            db.delete(TA_TOPIC_MASTER_TABLE, null, null);
            db.delete(LUP_TA_PATICIPANT_CAT_TABLE, null, null);
            db.delete(ADM_MACHINE_REGISTRY_TABLE, null, null);
            db.delete(ADM_MACHINE_PUBLISHER_TABLE, null, null);


//            Log.d(TAG, "All User data Deleted.");
        } catch (Exception e) {
//            Log.d(TAG, "Error: " + e.getMessage());
        }

        //db.close();

        onCreate(db);
    }


    public List<TaCategoriesDataModel> getTaCategories(String cCode) {
        List<TaCategoriesDataModel> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();


        String sql = "SELECT " + ADM_COUNTRY_CODE_COL
                + " , " + TA_CAT_CODE_COL
                + " , " + TA_CAT_NAME_COL
                + " , " + SRC_BEN_COL
                + " FROM " + TA_CATEGORY_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' ";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                TaCategoriesDataModel data = new TaCategoriesDataModel();
                data.setcCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                data.setTaCatCode(cursor.getString(cursor.getColumnIndex(TA_CAT_CODE_COL)));
                data.setTaCatName(cursor.getString(cursor.getColumnIndex(TA_CAT_NAME_COL)));
                data.setSrcBen(cursor.getString(cursor.getColumnIndex(SRC_BEN_COL)));


                list.add(data);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return list;
    }

    public void addTATopicMasterTable(String topicMasterCode, String topicTitle) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TOPIC_MASTER_CODE_COL, topicMasterCode);
        values.put(TOPIC_TITLE_COL, topicTitle);


        db.insert(TA_TOPIC_MASTER_TABLE, null, values);
        db.close();
    }


    public void addTATopicChildTable(String topicMasterCode, String topicChildCode, String topicSubTitle) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TOPIC_MASTER_CODE_COL, topicMasterCode);
        values.put(TOPIC_CHILD_CODE_COL, topicChildCode);
        values.put(TOPIC_SUB_TITLE_COL, topicSubTitle);


        db.insert(TA_TOPIC_CHILD_TABLE, null, values);
        db.close();
    }


    public List<TA_ParticipantsListDataModel> getsingleTaParticipantsListTableRecordsAttendance(String cCode, String eventCode, String partId, String idCategory) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<TA_ParticipantsListDataModel> list = new ArrayList<>();

        String sql = "SELECT * FROM " + TA_PARTICIPANTS_LIST_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + EVENT_CODE_COL + " = '" + eventCode + "'" +
                " AND " + PART_ID_COL + " = '" + partId + "' " +
                " AND " + ID_CATEGORY_COL + " = '" + idCategory + "' ";


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                TA_ParticipantsListDataModel data = new TA_ParticipantsListDataModel();
                data.setcCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                data.setEventCode(cursor.getString(cursor.getColumnIndex(EVENT_CODE_COL)));

                data.setPartId(cursor.getString(cursor.getColumnIndex(PART_ID_COL)));
                data.setIdCatCode(cursor.getString(cursor.getColumnIndex(ID_CATEGORY_COL)));
                data.setPartName(cursor.getString(cursor.getColumnIndex(PART_NAME_COL)));
                data.setPartOrgNCode(cursor.getString(cursor.getColumnIndex(PART_ORG_N_CODE_COL)));
                data.setSex(cursor.getString(cursor.getColumnIndex(TA_PARTICIPANTS_LIST_TABLE_SEX_COL)));
                data.setPartCatCode(cursor.getString(cursor.getColumnIndex(PART_CAT_CODE_COL)));
                data.setPosCode(cursor.getString(cursor.getColumnIndex(POS_CODE_COL)));
                data.setAmSession(cursor.getString(cursor.getColumnIndex(AM_SESSION_COL)));
                data.setPmSession(cursor.getString(cursor.getColumnIndex(PM_SESSION_COL)));
                data.setTaGroup(cursor.getString(cursor.getColumnIndex(TA_GROUP_COL)));
                data.setAtdnDate(cursor.getString(cursor.getColumnIndex(ATDN_DATE_COL)));

                list.add(data);

            } while (cursor.moveToNext());

            cursor.close();
        }


        db.close();
        return list;

    }


    public void editTaParticipantsListTable(String cCode, String eventCode, String partId, String idCatCode,
                                            String partName, String partOrgNCode, String sex,
                                            String partCatCode, String posCode, String amSession,
                                            String pmSession, String atdnDate, String taGroup, String entryBy, String entryDate) {


        String where = SQLiteQuery.editTaParticipantsListTable_sql(cCode, eventCode, partId, atdnDate);


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(ID_CATEGORY_COL, idCatCode);
        values.put(PART_NAME_COL, partName);
        values.put(PART_ORG_N_CODE_COL, partOrgNCode);
        values.put(TA_PARTICIPANTS_LIST_TABLE_SEX_COL, sex);
        values.put(PART_CAT_CODE_COL, partCatCode);
        values.put(POS_CODE_COL, posCode);
        values.put(AM_SESSION_COL, amSession);
        values.put(PM_SESSION_COL, pmSession);
        values.put(TA_GROUP_COL, taGroup);
        values.put(ATDN_DATE_COL, atdnDate);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        db.update(TA_PARTICIPANTS_LIST_TABLE, values, where, null);

        db.close();

        SQLServerSyntaxGenerator syntaxGenerator = new SQLServerSyntaxGenerator();

        syntaxGenerator.setAdmCountryCode(cCode);
        syntaxGenerator.setEventCode(eventCode);
        syntaxGenerator.setPartID(partId);
        syntaxGenerator.setIDCategory(idCatCode);
        syntaxGenerator.setPartName(partName);
        syntaxGenerator.setPartOrgNCode(partOrgNCode);
        syntaxGenerator.setSex(sex);
        syntaxGenerator.setPartCatCode(partCatCode);
        syntaxGenerator.setPosCode(posCode);
        syntaxGenerator.setAMSession(amSession);
        syntaxGenerator.setPMSession(pmSession);
        syntaxGenerator.setAtdnDate(atdnDate);
        syntaxGenerator.setTAGroup(taGroup);
        syntaxGenerator.setEntryBy(entryBy);
        syntaxGenerator.setEntryDate(entryDate);
        insertIntoUploadTable(syntaxGenerator.updateTAParticipantsList());


    }

    public boolean ifExistsInTaParticipantsListTable(String cCode, String eventCode, String partId, String atdnDate) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean flag;
        String sql = SQLiteQuery.ifExistsInTaParticipantsListTable_sql(cCode, eventCode, partId, atdnDate);
        Cursor cursor = db.rawQuery(sql, null);

        flag = (cursor.getCount() > 0);

        if (cursor != null)
            cursor.close();

        db.close();
        return flag;
    }

    public TA_ParticipantsListDataModel getTaParticipantsListTable(String cCode, String eventCode, String partId, String idCategory) {
        SQLiteDatabase db = this.getReadableDatabase();

        TA_ParticipantsListDataModel data = new TA_ParticipantsListDataModel();
        String sql = "SELECT * FROM " + TA_PARTICIPANTS_LIST_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + EVENT_CODE_COL + " = '" + eventCode + "'" +
                " AND " + PART_ID_COL + " = '" + partId + "' " +
                " AND " + ID_CATEGORY_COL + " = '" + idCategory + "' " +
                " GROUP BY " + PART_ORG_N_CODE_COL + " , " + PART_CAT_CODE_COL + " ," + POS_CODE_COL;

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            data.setcCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
            data.setEventCode(cursor.getString(cursor.getColumnIndex(EVENT_CODE_COL)));

            data.setPartId(cursor.getString(cursor.getColumnIndex(PART_ID_COL)));
            data.setIdCatCode(cursor.getString(cursor.getColumnIndex(ID_CATEGORY_COL)));
            data.setPartName(cursor.getString(cursor.getColumnIndex(PART_NAME_COL)));
            data.setPartOrgNCode(cursor.getString(cursor.getColumnIndex(PART_ORG_N_CODE_COL)));
            data.setSex(cursor.getString(cursor.getColumnIndex(TA_PARTICIPANTS_LIST_TABLE_SEX_COL)));
            data.setPartCatCode(cursor.getString(cursor.getColumnIndex(PART_CAT_CODE_COL)));
            data.setPosCode(cursor.getString(cursor.getColumnIndex(POS_CODE_COL)));
            data.setAmSession(cursor.getString(cursor.getColumnIndex(AM_SESSION_COL)));
            data.setPmSession(cursor.getString(cursor.getColumnIndex(PM_SESSION_COL)));
            data.setTaGroup(cursor.getString(cursor.getColumnIndex(TA_GROUP_COL)));
            data.setAtdnDate(cursor.getString(cursor.getColumnIndex(ATDN_DATE_COL)));


        }
        if (cursor != null)
            cursor.close();

        db.close();
        return data;

    }

    public List<TaPartOrgN> getTaOrganization(String cCode) {
        List<TaPartOrgN> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = SQLiteQuery.getTaOrganization_sql(cCode);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                TaPartOrgN data = new TaPartOrgN();
                data.setcCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                data.setPartOrgNCode(cursor.getString(cursor.getColumnIndex(PART_ORG_N_CODE_COL)));
                data.setPartOrgNName(cursor.getString(cursor.getColumnIndex(PART_ORG_N_NAME_COL)));
                data.setSrcBen(cursor.getString(cursor.getColumnIndex(SRC_BEN_COL)));


                list.add(data);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return list;
    }

    public List<LupTaParticipentCategories> getLUP_TAParticipantCategories(String cCode, String taGroup) {
        List<LupTaParticipentCategories> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = SQLiteQuery.getLUP_TAParticipantCategories_sql(cCode, taGroup);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                LupTaParticipentCategories data = new LupTaParticipentCategories();
                data.setcCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                data.setTaGroup(cursor.getString(cursor.getColumnIndex(TA_GROUP_COL)));
                data.setParticipentCategoriesCode(cursor.getString(cursor.getColumnIndex(PART_CAT_CODE_COL)));
                data.setParticipentCategoriesName(cursor.getString(cursor.getColumnIndex(PART_CAT_TITLE_COL)));


                list.add(data);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return list;
    }

    public List<TAPosParticipants> getTAPosParticipants(String cCode) {
        List<TAPosParticipants> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT " + ADM_COUNTRY_CODE_COL
                + " , " + POS_CODE_COL
                + " , " + POS_TITLE_COL
                + " FROM " + TA_POS_PARTICIPANTS_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' ";


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                TAPosParticipants data = new TAPosParticipants();
                data.setcCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                data.setPosCode(cursor.getString(cursor.getColumnIndex(POS_CODE_COL)));
                data.setPosTitle(cursor.getString(cursor.getColumnIndex(POS_TITLE_COL)));


                list.add(data);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return list;
    }

    public void addTASubGroupTable(String cCode, String taGrouPCode, String taSubGrouCode, String taSubGrouTitle) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(TA_GROUP_COL, taGrouPCode);
        values.put(TA_SUB_GROUP_COL, taSubGrouCode);
        values.put(TA_SUB_TITLE_COL, taSubGrouTitle);

        db.insert(TA_SUB_GROUP_TABLE, null, values);
        db.close();
    }

    public void addTAPosParticipantsTable(String cCode, String posCode, String posTitle) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(POS_CODE_COL, posCode);
        values.put(POS_TITLE_COL, posTitle);

        db.insert(TA_POS_PARTICIPANTS_TABLE, null, values);
        db.close();
    }


    public void addTAPartOrgTable(String cCode, String partOrgNCode, String partOrgNName, String srcBen) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(PART_ORG_N_CODE_COL, partOrgNCode);
        values.put(PART_ORG_N_NAME_COL, partOrgNName);
        values.put(SRC_BEN_COL, srcBen);

        db.insert(TA_PART_ORG_N_TABLE, null, values);
        db.close();
    }


    public void addTaParticipantsListTable(String cCode, String eventCode, String partId, String idCatCode,
                                           String partName, String partOrgNCode, String sex,
                                           String partCatCode, String posCode, String amSession,
                                           String pmSession, String atdnDate, String taGroup, String entryBy, String entryDate) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(EVENT_CODE_COL, eventCode);
        values.put(PART_ID_COL, partId);
        values.put(ID_CATEGORY_COL, idCatCode);
        values.put(PART_NAME_COL, partName);
        values.put(PART_ORG_N_CODE_COL, partOrgNCode);
        values.put(TA_PARTICIPANTS_LIST_TABLE_SEX_COL, sex);
        values.put(PART_CAT_CODE_COL, partCatCode);
        values.put(POS_CODE_COL, posCode);
        values.put(AM_SESSION_COL, amSession);
        values.put(PM_SESSION_COL, pmSession);
        values.put(TA_GROUP_COL, taGroup);
        values.put(ATDN_DATE_COL, atdnDate);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        db.insert(TA_PARTICIPANTS_LIST_TABLE, null, values);
        db.close();

        SQLServerSyntaxGenerator syntaxGenerator = new SQLServerSyntaxGenerator();

        syntaxGenerator.setAdmCountryCode(cCode);
        syntaxGenerator.setEventCode(eventCode);
        syntaxGenerator.setPartID(partId);
        syntaxGenerator.setIDCategory(idCatCode);
        syntaxGenerator.setPartName(partName);
        syntaxGenerator.setPartOrgNCode(partOrgNCode);
        syntaxGenerator.setSex(sex);
        syntaxGenerator.setPartCatCode(partCatCode);
        syntaxGenerator.setPosCode(posCode);
        syntaxGenerator.setAMSession(amSession);
        syntaxGenerator.setPMSession(pmSession);
        syntaxGenerator.setAtdnDate(atdnDate);
        syntaxGenerator.setTAGroup(taGroup);
        syntaxGenerator.setEntryBy(entryBy);
        syntaxGenerator.setEntryDate(entryDate);
        insertIntoUploadTable(syntaxGenerator.insertIntoTAParticipantsList());
    }

    public void addTAGroupTable(String cCode, String topicGroupCode, String topicGroupTitle) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(TOPIC_GROUP_COL, topicGroupCode);
        values.put(TOPIC_GROUP_TITLE_COL, topicGroupTitle);

        db.insert(TA_GROUP_TABLE, null, values);
        db.close();
    }

    public void addTAEventTopicTable(String cCode, String eventCode, String topicMasterCode, String topicChildCode) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(EVENT_CODE_COL, eventCode);
        values.put(TOPIC_MASTER_CODE_COL, topicMasterCode);
        values.put(TOPIC_CHILD_CODE_COL, topicChildCode);

        db.insert(TA_EVENT_TOPIC_TABLE, null, values);
        db.close();
    }

    public void addTAcategoryTable(String cCode, String taCatCode, String taCatName, String srcBen) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(TA_CAT_CODE_COL, taCatCode);
        values.put(TA_CAT_NAME_COL, taCatName);
        values.put(SRC_BEN_COL, srcBen);


        db.insert(TA_CATEGORY_TABLE, null, values);
        db.close();
    }

    public void addTaMasterTable(String cCode, String eventCode, String eventName, String donorCode, String awardCode, String taGroup, String taSubGroup, String orgCode, String startDate, String endDate, String vanueName, String vanueAddress, String active, String totalDays, String hourPerDay
    ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);

        values.put(EVENT_CODE_COL, eventCode);
        values.put(EVENT_NAME_COL, eventName);
        values.put(ADM_DONOR_CODE_COL, donorCode);

        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(TA_GROUP_COL, taGroup);
        values.put(TA_SUB_GROUP_COL, taSubGroup);
        values.put(PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL, orgCode);
        values.put(START_DATE_COL, startDate);
        values.put(END_DATE_COL, endDate);
        values.put(VENUE_NAME_COL, vanueName);
        values.put(VENUE_ADDRESS_COL, vanueAddress);
        values.put(ACTIVE_COL, active);
        values.put(TOTAL_DAYS_COL, totalDays);
        values.put(HOURS_PER_DAY_COL, hourPerDay);

        db.insert(TA_MASTER_TABLE, null, values);
        db.close();

    }

    /**
     * @param cCode       Country Code
     * @param grpCode     Group Code
     * @param subGrpCode  Sub Group Code
     * @param locCode     Location  Code
     * @param contentCode content Code
     * @param imageFile   imge in byte array
     * @param remarks     remarks
     * @param entryBy     entryBy
     * @param entryDate   entryDate
     */


    public void insertIntoGPSLocationContentTable(String cCode, String grpCode, String subGrpCode, String locCode, String contentCode, byte[] imageFile, String remarks, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(GROUP_CODE_COL, grpCode);
        values.put(SUB_GROUP_CODE_COL, subGrpCode);
        values.put(LOCATION_CODE_COL, locCode);
        values.put(CONTENT_CODE_COL, contentCode);
        values.put(IMAGE_FILE_COL, imageFile);
        values.put(REMARKES_COL, remarks);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        db.insert(GPS_LOCATION_CONTENT_TABLE, null, values);
        db.close();

    }

    public void insertIntoLupGpsList(String grpCode, String subGrpCode, String attbuteCode, String lup_valueCode, String lup_value_text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(GROUP_CODE_COL, grpCode);
        values.put(SUB_GROUP_CODE_COL, subGrpCode);
        values.put(ATTRIBUTE_CODE_COL, attbuteCode);
        values.put(LUP_VALUE_CODE_COL, lup_valueCode);
        values.put(LUP_VALUE_TEXT_COL, lup_value_text);


        db.insert(LUP_GPS_LIST_TABLE, null, values);
        db.close();


    }


    public void insertIntoStaffMasterTable(String staffId, String cCode, String staffName, String orgCode, String orgNDesgCode
            , String staffStatus, String staffCat, String userName, String userPass, String staffAdimRole) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(STAFF_ID_COL, staffId);
        values.put(STAFF_COUNTRY_CODE, cCode);
        values.put(STAFF_NAME_COL, staffName);
        values.put(ORG_N_CODE_COL, orgCode);
        values.put(ORG_N_DESG_N_CODE_COL, orgNDesgCode);
        values.put(STAFF_STATUS_COL, staffStatus);
        values.put(STAFF_CATEGORY_COL, staffCat);
        values.put(USER_LOGIN_NAME, userName);
        values.put(USER_LOGIN_PW, userPass);
        values.put(STAFF_ADMIN_ROLE_COL, staffAdimRole);

        db.insert(STAFF_MASTER_TABLE, null, values);
        db.close();

    }

    public static final String TYPE_OF_GROUP = "TypeOfGroup";


    /**
     * @param cCode             Country Code
     * @param donorCode         Donor Code
     * @param awardCode         Award Code
     * @param progCode          Program Code
     * @param groupCatCode      group Categories Code
     * @param groupCatName      group Categories Name
     * @param groupCatShortName group Categories Short Name
     */

    public void addCommunityGroupCategoryFromOnline(String cCode, String donorCode, String awardCode, String progCode, String groupCatCode, String groupCatName
            , String groupCatShortName) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(GROUP_CAT_CODE_COL, groupCatCode);
        values.put(GROUP_CAT_NAME_COL, groupCatName);
        values.put(GROUP_CAT_SHORT_NAME_COL, groupCatShortName);


        db.insert(COMMUNITY_GROUP_CATEGORY_TABLE, null, values);
        db.close();
    }


    public void addRegNmemProgGroupFromOnline(String cCode, String donorCode, String awardCode, String layR1Code, String layR2Code, String layR3Code, String layR4Code, String hhID, String memID, String progCode, String srvCode, String grpCode, String active, String grpLayR1Code, String grpLayR2Code, String grpLayR3Code) {

        /**
         * To trace the user's input (Group Name )  I am using grpName  variable  no need to download from onLine
         * */
        String grpName = "";
        addRegNmemProgGroup(cCode, donorCode, awardCode, layR1Code, layR2Code, layR3Code, layR4Code, hhID, memID, progCode, srvCode, grpCode, grpName, active, "", "", grpLayR1Code, grpLayR2Code, grpLayR3Code);
    }


    /**
     * this method will need ed in Assign Main Page also
     *
     * @param cCode     Country Code
     * @param donorCode Donor Code
     * @param awardCode Award Code
     * @param layR1Code LayR1 Code
     * @param layR2Code LayR2 Code
     * @param layR3Code LayR3 Code
     * @param layR4Code LayR4 Code
     * @param hhID      House hold Id
     * @param memID     member Id
     * @param progCode  program Code
     * @param srvCode   service Code
     * @param grpCode   group code
     * @param active    active code
     * @param entryBy   user id
     * @param entryDate endtry Date
     */

    public void addRegNmemProgGroup(String cCode, String donorCode, String awardCode, String layR1Code, String layR2Code, String layR3Code, String layR4Code, String hhID, String memID, String progCode, String srvCode, String grpCode, String grpName, String active, String entryBy, String entryDate, String grpLayR1Code, String grpLayR2Code, String grpLayR3Code) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL, layR1Code);
        values.put(LAY_R2_LIST_CODE_COL, layR2Code);
        values.put(LAY_R3_LIST_CODE_COL, layR3Code);
        values.put(LAY_R4_LIST_CODE_COL, layR4Code);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(HHID_COL, hhID);
        values.put(REG_N_ASSIGN_PROG_SRV_HH_MEM_ID, memID);
        values.put(PROG_CODE_COL, progCode);
        values.put(SRV_CODE_COL, srvCode);
        values.put(GROUP_CODE_COL, grpCode);/*
        values.put(GROUP_NAME_COL, grpName);*/
        values.put(ACTIVE_COL, active);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R1_LIST_CODE_COL, grpLayR1Code);
        values.put(REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R2_LIST_CODE_COL, grpLayR2Code);
        values.put(REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R3_LIST_CODE_COL, grpLayR3Code);


        db.insert(REG_N_MEM_PROG_GRP_TABLE, null, values);
        db.close();

    }


    public void addLUP_AnimalTypeFromOnline(String cCode, String donorCode, String awardCode, String progCode, String animalCode, String animalType) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(ANIMAL_CODE_COL, animalCode);
        values.put(ANIMAL_TYPE_COL, animalType);

        // long id =
        db.insert(LUP_COMMUNITY_ANIMAL_TABLE, null, values);
        db.close();


    }


    /**
     * lupProgGrpCrop data insert from only online
     *
     * @param cCode       Country Code
     * @param donorCode   Donor Code
     * @param awardCode   Award Code
     * @param progCode    program code
     * @param cropCode    crop code
     * @param corpName    crop Name
     * @param cropCatCode crop Categories  Code
     */

    public void addLUP_ProgramGroupCrop(String cCode, String donorCode, String awardCode, String progCode, String cropCode, String corpName, String cropCatCode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(CROP_CODE_COL, cropCode);
        values.put(CROP_NAME_COL, corpName);
        values.put(CROP_CAT_COL, cropCatCode);


        //  long id =
        db.insert(LUP_PROG_GROUP_CROP_TABLE, null, values);
        db.close();


    }


    public void addLUP_CommunityLoanSource(String cCode, String donorCode, String awardCode,
                                           String progCode, String loanCode, String loanSource) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(LOAN_CODE_COL, loanCode);
        values.put(LOAN_SOURCE_COL, loanSource);

        db.insert(LUP_COMMUNITY_LOAN_SOURCE_TABLE, null, values);
        db.close();


    }

    public void addLUP_CommunityFundSource(String cCode, String donorCode, String awardCode,
                                           String progCode, String fundCode, String fundSource) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(FUND_CODE_COL, fundCode);
        values.put(FUND_SOURCE_COL, fundSource);

        db.insert(LUP_COMMUNITY_FUND_SOURCE_TABLE, null, values);
        db.close();


    }


    public void addLUP_CommunityIrrigationSystem(String cCode, String donorCode, String awardCode,
                                                 String progCode, String irriSysCode, String irriSystemName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(IRRI_SYS_CODE_COL, irriSysCode);
        values.put(IRRI_SYS_NAME_COL, irriSystemName);

        db.insert(LUP_COMMUNITY_IRRIGATION_SYSTEM_TABLE, null, values);
        db.close();


    }

    // add LUP_CommunityLoanSource list

    public void addLUP_CommunityLeadPostition(String cCode, String donorCode, String awardCode,
                                              String progCode, String leadCode, String leadPosition) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(LEAD_CODE_COL, leadCode);
        values.put(LEAD_POSITION_COL, leadPosition);


        db.insert(LUP_COMMUNITY_LEAD_POSITION_TABLE, null, values);
        db.close();


    }


    // add service Center

    public void addServiceCenter(String cCode, String srvCenCode, String srvCenName, String fdpCode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(SERVICE_CENTER_CODE_COL, srvCenCode);
        values.put(SERVICE_CENTER_NAME_COL, srvCenName);
        values.put(FDP_CODE_COL, fdpCode);

        db.insert(SERVICE_CENTER_TABLE, null, values);
        db.close();


    }

    public void insertAdmCountryProgram(String cCode, String donorCode, String awardCode, String programCode, String servCode, String progFlag, String food, String nonFood, String cash, String voucher,
                                        String defaultFoodDays, String defaultNoFoodDays, String defaultCashDays, String defaultVoucharDays, String srvSpecific) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, programCode);
        values.put(ADM_SRV_CODE_COL, servCode);
        values.put(PROG_FLAG, progFlag);
        values.put(FOOD_FLAG, food);
        values.put(NON_FOOD_FLAG, nonFood);
        values.put(CASH_FLAG, cash);
        values.put(VOUCHER_FLAG, voucher);
        values.put(DEFAULT_FOOD_DAYS_COL, defaultFoodDays);
        values.put(DEFAULT_NO_FOOD_DAYS_COL, defaultNoFoodDays);
        values.put(DEFAULT_CASH_DAYS_COL, defaultCashDays);
        values.put(DEFAULT_VOUCHAR_DAYS_COL, defaultVoucharDays);
        values.put(SERVICE_SPECIFIC_FLAG_COL, srvSpecific);
        // Inserting Row
        db.insert(COUNTRY_PROGRAM_TABLE, null, values);
        db.close(); // Closing database connection


    }

    /**
     * **********************************************************************
     * INSERT OPERATION FROM HERE
     * ***********************************************************************
     */
     /*
    * @date : 2015-09-30*/
    public void addGraduation(String programCode, String serviceCode, String grdCode, String grdTitle,
                              String defaultCatActive, String defaultCatExit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ADM_PROG_CODE_COL, programCode);
        values.put(ADM_SRV_CODE_COL, serviceCode);
        values.put(GRD_CODE_COL, grdCode);
        values.put(GRD_TITLE_COL, grdTitle);
        values.put(DEFAULT_CAT_ACTIVE_COL, defaultCatActive);
        values.put(DEFAULT_CAT_EXIT_COL, defaultCatExit);

        // many mort ot insert
        db.insert(REG_N_LUP_GRADUATION_TABLE, null, values);
        db.close();


    }


    public RegN_HH_libDataModel getPreviousLayeRListforHouseHold(int pID) {
        SQLiteDatabase db = this.getReadableDatabase();
        RegN_HH_libDataModel LayRList = new RegN_HH_libDataModel();

        String selectQuery = "SELECT " + ADM_COUNTRY_CODE_COL + " , "
                + LAY_R1_LIST_CODE + " , "
                + REGISTRATION_TABLE_UPZILLA_CODE_COL + " , "
                + REGISTRATION_TABLE_UNION_CODE_COL + " , "
                + REGISTRATION_TABLE_VILLAGE_CODE_COL +
                //  " , "      +PID_COL+
                "  FROM " + REG_N_HH_TABLE +
                " WHERE " + ID_COL + " = " + pID;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                LayRList.setCountryCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                LayRList.setDistrictCode(cursor.getString(cursor.getColumnIndex(LAY_R1_LIST_CODE)));
                LayRList.setUpazillaCode(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_UPZILLA_CODE_COL)));
                LayRList.setUnitCode(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_UNION_CODE_COL)));
                LayRList.setVillageCode(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_VILLAGE_CODE_COL)));
                // LayRList.setVillageCode(cursor.getString(cursor.getColumnIndex(PID_COL)));
            }
            cursor.close();
        }
        db.close();
        return LayRList;
    }


    public void editRegistrationRecordForLiberia(int pID, RegN_HH_libDataModel data) {

        String where = ID_COL + "=" + pID;


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DISTRICT_NAME_COL, data.getDistrictCode());
        values.put(UPZILLA_NAME_COL, data.getUpazillaCode());
        values.put(LAY_R3_LIST_NAME, data.getUnitCode());
        values.put(LAY_R4_LIST_NAME_COL, data.getVillageCode());
        values.put(PID_COL, data.getHhId());
        values.put(REG_DATE_COL, data.getRegNDate());
        values.put(REGISTRATION_TABLE_HH_HEAD_NAME, data.getHHHeadName());
        values.put(HOUSE_HOLD_TYPE_CODE_COL, data.getHHTypeCode());
        values.put(LT2YRS_M_COL, data.getLT2yrsM());
        values.put(LT2YRS_F_COL, data.getLT2yrsF());
        values.put(M_2TO5YRS_COL, data.getM2to5yers());
        values.put(F_2TO5YRS_COL, data.getF2to5yrs());
        values.put(M_6TO12YRS_COL, data.getM6to12yrs());
        values.put(F_6TO12YRS_COL, data.getF6to12yrs());
        values.put(M_13TO17YRS_COL, data.getM13to17yrs());
        values.put(F_13TO17YRS_COL, data.getF13to17yrs());
        values.put(ORPHN_LT18YRS_M_COL, data.getOrphn_LT18yrsM());
        values.put(ORPHN_LT18YRS_F_COL, data.getOrphn_LT18yrsF());
        values.put(ADLT_18TO59_M_COL, data.getAdlt_18to59M());
        values.put(ADLT_18TO59_F_COL, data.getAdlt_18to59F());
        values.put(ELD_60P_M_COL, data.getEld_60pM());
        values.put(ELD_60P_F_COL, data.getEld_60pF());
        values.put(PLW_NO_COL, data.getPLW());
        values.put(CHRO_ILL_NO_COL, data.getChronicallyIll());
        values.put(DECEASED_CONTRACT_EBOLA_COL, data.getLivingDeceasedContractEbola());
        values.put(EXTRA_CHILD_CAUSE_EBOLA_COL, data.getExtraChildBecauseEbola());
        values.put(EXTRA_ELDERLY_CAUSE_EBOLA_COL, data.getExtraelderlyPersonBecauseEbola());
        values.put(EXTRA_CHRONICALLY_ILL_CAUSE_EBOLA_COL, data.getExtraChronicallyIllDisabledPersonBecauseEbola());
        values.put(BRF_COUNT_CATTLE_COL, data.getBrfCntCattle());
        values.put(BRF_VALUE_CATTLE_COL, data.getBrfValCattle());
        values.put(AFT_COUNT_CATTLE_COL, data.getAftCntCattle());
        values.put(AFT_VALUE_CATTLE_COL, data.getAftValCattle());
        values.put(BRF_COUNT_SGOATS_COL, data.getBrfCntSheepGoats());
        values.put(BRF_VALUE_SGOATS_COL, data.getBrfValSheepGoats());
        values.put(AFT_COUNT_SGOATS_COL, data.getAftCntSheepGoats());
        values.put(AFT_VALUE_SGOATS_COL, data.getAftValSheepGoats());
        values.put(BRF_COUNT_POULTRY_COL, data.getBrfCntPoultry());
        values.put(BRF_VALUE_POULTRY_COL, data.getBrfValPoultry());
        values.put(AFT_COUNT_POULTRY_COL, data.getAftCntPoultry());
        values.put(AFT_VALUE_POULTRY_COL, data.getAftValPoultry());
        values.put(BRF_COUNT_OTHER_COL, data.getBrfCntOther());
        values.put(BRF_VALUE_OTHER_COL, data.getBrfValOther());
        values.put(AFT_COUNT_OTHER_COL, data.getAftCntOther());
        values.put(AFT_VALUE_OTHER_COL, data.getAftValOther());
        values.put(BRF_ACRE_CULTIVABLE_COL, data.getBrfAcreCultivable());
        values.put(BRF_VALUE_CULTIVABLE_COL, data.getBrfValCultivable());
        values.put(AFT_ACRE_CULTIVABLE_COL, data.getAftAcreCultivable());
        values.put(AFT_VALUE_CULTIVABLE_COL, data.getAftValCultivable());
        values.put(BRF_ACRE_NON_CULTIVABLE_COL, data.getBrfAcreNonCultivable());
        values.put(BRF_VAL_NON_CULTIVABLE_COL, data.getBrfValNonCultivable());
        values.put(AFT_ACRE_NON_CULTIVABLE, data.getAftAcreNonCultivable());
        values.put(AFT_VAL_NON_CULTIVABLE, data.getAftValNonCultivable());
        values.put(BRF_ACRE_ORCHARDS, data.getBrfAcreOrchards());
        values.put(BRF_VAL_ORCHARDS, data.getBrfValOrchards());
        values.put(AFT_ACRE_ORCHARDS, data.getAftAcreOrchards());
        values.put(AFT_VAL_ORCHARDS, data.getAftValOrchards());
        values.put(BRF_VAL_CROP, data.getBrfValCrop());
        values.put(AFT_VAL_CROP, data.getAftValCrop());
        values.put(BRF_VAL_LIVESTOCK, data.getBrfValLivestock());
        values.put(AFT_VAL_LIVESTOCK, data.getAftValLivestock());
        values.put(BRF_VAL_SMALL_BUSINESS, data.getBrfValSmallBusiness());
        values.put(AFT_VAL_SMALL_BUSINESS, data.getAftValSmallBusiness());
        values.put(BRF_VAL_EMPLOYMENT, data.getBrfValEmployment());
        values.put(AFT_VAL_EMPLOYMENT, data.getAftValEmployment());
        values.put(BRF_VAL_REMITTANCES, data.getBrfValRemittances());
        values.put(AFT_VAL_REMITTANCES, data.getAftValRemittances());
        values.put(BRF_CNT_WAGEENR, data.getBrfCntWageEnr());
        values.put(AFT_CNT_WAGEENR, data.getAftCntWageEnr());
        values.put(ENTRY_BY, data.getEntryDate());
        values.put(ENTRY_DATE, data.getEntryBy());

        // Inserting Row into local database
        db.update(REG_N_HH_TABLE, values, where, null);

//        updateRegistrationStatus("" + pID, 0);    // Setting Update status to false

        db.close(); // Closing database connection

//        Log.d(TAG, "Registration data edited for: " + pID);
    }


    public long addHHRegForLiberia(RegN_HH_libDataModel data) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(ADM_COUNTRY_CODE_COL, data.getCountryCode());
        values.put(LAY_R1_LIST_CODE, data.getDistrictCode());
        values.put(REGISTRATION_TABLE_UPZILLA_CODE_COL, data.getUpazillaCode());
        values.put(REGISTRATION_TABLE_UNION_CODE_COL, data.getUnitCode());
        values.put(REGISTRATION_TABLE_VILLAGE_CODE_COL, data.getVillageCode());
        values.put(REGISTRATION_TABLE_HHID, data.getHhId());
        values.put(REG_DATE_COL, data.getRegNDate());
        values.put(REGISTRATION_TABLE_HH_HEAD_NAME, data.getHHHeadName());
        values.put(REGISTRATION_TABLE_HOUSE_HOLD_TYPE_CODE_COL, data.getHHTypeCode());

        values.put(LT2YRS_M_COL, data.getLT2yrsM());
        values.put(LT2YRS_F_COL, data.getLT2yrsF());
        values.put(M_2TO5YRS_COL, data.getM2to5yers());
        values.put(F_2TO5YRS_COL, data.getF2to5yrs());
        values.put(M_6TO12YRS_COL, data.getM6to12yrs());
        values.put(F_6TO12YRS_COL, data.getF6to12yrs());
        values.put(M_13TO17YRS_COL, data.getM13to17yrs());
        values.put(F_13TO17YRS_COL, data.getF13to17yrs());
        values.put(ORPHN_LT18YRS_M_COL, data.getOrphn_LT18yrsM());
        values.put(ORPHN_LT18YRS_F_COL, data.getOrphn_LT18yrsF());
        values.put(ADLT_18TO59_M_COL, data.getAdlt_18to59M());
        values.put(ADLT_18TO59_F_COL, data.getAdlt_18to59F());
        values.put(ELD_60P_M_COL, data.getEld_60pM());
        values.put(ELD_60P_F_COL, data.getEld_60pF());
        values.put(PLW_NO_COL, data.getPLW());
        values.put(CHRO_ILL_NO_COL, data.getChronicallyIll());

        values.put(DECEASED_CONTRACT_EBOLA_COL, data.getLivingDeceasedContractEbola());
        values.put(EXTRA_CHILD_CAUSE_EBOLA_COL, data.getExtraChildBecauseEbola());
        values.put(EXTRA_ELDERLY_CAUSE_EBOLA_COL, data.getExtraelderlyPersonBecauseEbola());
        values.put(EXTRA_CHRONICALLY_ILL_CAUSE_EBOLA_COL, data.getExtraChronicallyIllDisabledPersonBecauseEbola());


        values.put(BRF_COUNT_CATTLE_COL, data.getBrfCntCattle());
        values.put(BRF_VALUE_CATTLE_COL, data.getBrfValCattle());
        values.put(AFT_COUNT_CATTLE_COL, data.getAftCntCattle());
        values.put(AFT_VALUE_CATTLE_COL, data.getAftValCattle());
        values.put(BRF_COUNT_SGOATS_COL, data.getBrfCntSheepGoats());
        values.put(BRF_VALUE_SGOATS_COL, data.getBrfValSheepGoats());
        values.put(AFT_COUNT_SGOATS_COL, data.getAftCntSheepGoats());
        values.put(AFT_VALUE_SGOATS_COL, data.getAftValSheepGoats());
        values.put(BRF_COUNT_POULTRY_COL, data.getBrfCntPoultry());
        values.put(BRF_VALUE_POULTRY_COL, data.getBrfValPoultry());
        values.put(AFT_COUNT_POULTRY_COL, data.getAftCntPoultry());
        values.put(AFT_VALUE_POULTRY_COL, data.getAftValPoultry());
        values.put(BRF_COUNT_OTHER_COL, data.getBrfCntOther());
        values.put(BRF_VALUE_OTHER_COL, data.getBrfValOther());
        values.put(AFT_COUNT_OTHER_COL, data.getAftCntOther());
        values.put(AFT_VALUE_OTHER_COL, data.getAftValOther());
        values.put(BRF_ACRE_CULTIVABLE_COL, data.getBrfAcreCultivable());
        values.put(BRF_VALUE_CULTIVABLE_COL, data.getBrfValCultivable());
        values.put(AFT_ACRE_CULTIVABLE_COL, data.getAftAcreCultivable());
        values.put(AFT_VALUE_CULTIVABLE_COL, data.getAftValCultivable());
        values.put(BRF_ACRE_NON_CULTIVABLE_COL, data.getBrfAcreNonCultivable());
        values.put(BRF_VAL_NON_CULTIVABLE_COL, data.getBrfValNonCultivable());
        values.put(AFT_ACRE_NON_CULTIVABLE, data.getAftAcreNonCultivable());
        values.put(AFT_VAL_NON_CULTIVABLE, data.getAftValNonCultivable());
        values.put(BRF_ACRE_ORCHARDS, data.getBrfAcreOrchards());
        values.put(BRF_VAL_ORCHARDS, data.getBrfValOrchards());
        values.put(AFT_ACRE_ORCHARDS, data.getAftAcreOrchards());
        values.put(AFT_VAL_ORCHARDS, data.getAftValOrchards());
        values.put(BRF_VAL_CROP, data.getBrfValCrop());
        values.put(AFT_VAL_CROP, data.getAftValCrop());
        values.put(BRF_VAL_LIVESTOCK, data.getBrfValLivestock());
        values.put(AFT_VAL_LIVESTOCK, data.getAftValLivestock());
        values.put(BRF_VAL_SMALL_BUSINESS, data.getBrfValSmallBusiness());
        values.put(AFT_VAL_SMALL_BUSINESS, data.getAftValSmallBusiness());
        values.put(BRF_VAL_EMPLOYMENT, data.getBrfValEmployment());
        values.put(AFT_VAL_EMPLOYMENT, data.getAftValEmployment());
        values.put(BRF_VAL_REMITTANCES, data.getBrfValRemittances());
        values.put(AFT_VAL_REMITTANCES, data.getAftValRemittances());
        values.put(BRF_CNT_WAGEENR, data.getBrfCntWageEnr());
        values.put(AFT_CNT_WAGEENR, data.getAftCntWageEnr());


        values.put(ENTRY_BY, data.getEntryBy());
        values.put(ENTRY_DATE, data.getEntryDate());
        // many mort ot insert
        // insert
        long id = db.insert(REG_N_HH_TABLE, null, values);
        db.close();
//        Log.d(TAG, "New LIBERIA_REGISTRATION_TABLE  added: " + id);
        return id;
    }


    public void addServiceSpecificTableFromOnline(String cCode, String donorCode, String awardCode
            , String distCode, String upCode, String unCode, String vCode, String hhId, String memId, String programCode
            , String srvCode, String opCode, String opMonthCode, String srvCenterCode
            , String fdpCode, String srvStatus
            , String babyStatus
            , String gmpAttendence, String weightStatus, String nutAttendance, String vitaUnder5, String exclCurrentLybf
            , String dateComFeed, String camRef, String camAdd, String dateAncVisit
            , String pncVisit2D, String pncVisit1W, String pncVisit6W
            , String deliveryStaff_1, String deliveryStaff_2, String deliveryStaff_3
            , String homeSupport24H_1D, String homeSupport24H_2D, String homeSupport24H_3D
            , String homeSupport24H_8D, String homeSupport24H_14D, String homeSupport24H_21D
            , String homeSupport24H_30D, String homeSupport24H_60D, String homeSupport24H_90D
            , String homeSupport48H_1D, String homeSupport48H_3D, String homeSupport48H_8D
            , String homeSupport48H_30D, String homeSupport48H_60D, String homeSupport48H_90D
            , String maternal_bleeed, String maternal_seizure, String maternal_infection
            , String maternal_proLongedLabor, String maternal_obstructedLabor, String maternal_pprm
            , String nBorn_Aspyxia, String nBorn_Sepsis, String nBorn_HypoThermai
            , String nBorn_HyperThermai, String nBorn_noSuckling, String nBorn_Jaundices
            , String child_Diarrhea, String child_Pneumonia, String child_Fever, String child_CerebralPalsy
            , String immu_Polio, String immu_BCG, String immu_Measles
            , String immu_DPT_HIB, String immu_Lotta, String immU_Other
            , String fpCounsel_MaleCondom, String fpCounsel_FemaleCondom
            , String fpCounsel_Pill, String fpCounsel_Depo
            , String fpCounsel_LongParmanent, String fpCounsel_NoMethod
            , String cropCode, String loanSource
            , String loanAMT, String animalCode
            , String leadCode


    ) {

        String entryBy = "";
        String entryDate = "";


        addServiceSpecificTable(cCode, donorCode, awardCode, distCode, upCode, unCode, vCode, hhId, memId, programCode
                , srvCode, opCode, opMonthCode, srvCenterCode, fdpCode, srvStatus
                , babyStatus, gmpAttendence, weightStatus, nutAttendance, vitaUnder5, exclCurrentLybf
                , dateComFeed, camRef, camAdd, dateAncVisit, pncVisit2D, pncVisit1W, pncVisit6W
                , deliveryStaff_1, deliveryStaff_2, deliveryStaff_3, homeSupport24H_1D, homeSupport24H_2D, homeSupport24H_3D
                , homeSupport24H_8D, homeSupport24H_14D, homeSupport24H_21D, homeSupport24H_30D, homeSupport24H_60D, homeSupport24H_90D
                , homeSupport48H_1D, homeSupport48H_3D, homeSupport48H_8D, homeSupport48H_30D, homeSupport48H_60D, homeSupport48H_90D
                , maternal_bleeed, maternal_seizure, maternal_infection, maternal_proLongedLabor, maternal_obstructedLabor, maternal_pprm
                , nBorn_Aspyxia, nBorn_Sepsis, nBorn_HypoThermai, nBorn_HyperThermai, nBorn_noSuckling, nBorn_Jaundices
                , child_Diarrhea, child_Pneumonia, child_Fever, child_CerebralPalsy, immu_Polio, immu_BCG, immu_Measles
                , immu_DPT_HIB, immu_Lotta, immU_Other, fpCounsel_MaleCondom, fpCounsel_FemaleCondom
                , fpCounsel_Pill, fpCounsel_Depo, fpCounsel_LongParmanent, fpCounsel_NoMethod, cropCode, loanSource, loanAMT, animalCode, leadCode, entryBy, entryDate);
    }


    public long addServiceSpecificTable(String cCode, String donorCode, String awardCode
            , String distCode, String upCode, String unCode, String vCode, String hhId, String memId, String programCode
            , String srvCode, String opCode, String opMonthCode, String srvCenterCode
            , String fdpCode, String srvStatus
            , String babyStatus
            , String gmpAttendence, String weightStatus, String nutAttendance, String vitaUnder5, String exclCurrentLybf
            , String dateComFeed, String camRef, String camAdd, String dateAncVisit
            , String pncVisit2D, String pncVisit1W, String pncVisit6W
            , String deliveryStaff_1, String deliveryStaff_2, String deliveryStaff_3
            , String homeSupport24H_1D, String homeSupport24H_2D, String homeSupport24H_3D
            , String homeSupport24H_8D, String homeSupport24H_14D, String homeSupport24H_21D
            , String homeSupport24H_30D, String homeSupport24H_60D, String homeSupport24H_90D
            , String homeSupport48H_1D, String homeSupport48H_3D, String homeSupport48H_8D
            , String homeSupport48H_30D, String homeSupport48H_60D, String homeSupport48H_90D
            , String maternal_bleeed, String maternal_seizure, String maternal_infection
            , String maternal_proLongedLabor, String maternal_obstructedLabor, String maternal_pprm
            , String nBorn_Aspyxia, String nBorn_Sepsis, String nBorn_HypoThermai
            , String nBorn_HyperThermai, String nBorn_noSuckling, String nBorn_Jaundices
            , String child_Diarrhea, String child_Pneumonia, String child_Fever, String child_CerebralPalsy
            , String immu_Polio, String immu_BCG, String immu_Measles
            , String immu_DPT_HIB, String immu_Lotta, String immU_Other
            , String fpCounsel_MaleCondom, String fpCounsel_FemaleCondom
            , String fpCounsel_Pill, String fpCounsel_Depo
            , String fpCounsel_LongParmanent, String fpCounsel_NoMethod
            , String cropCode, String loanSource
            , String loanAMT, String animalCode
            , String leadCode
            , String entryBy
            , String entryDate


    ) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL, distCode);
        values.put(LAY_R2_LIST_CODE_COL, upCode);
        values.put(LAY_R3_LIST_CODE_COL, unCode);
        values.put(LAY_R4_LIST_CODE_COL, vCode);
        values.put(HHID_COL, hhId);
        values.put(REG_N_ASSIGN_PROG_SRV_HH_MEM_ID, memId);
        values.put(PROG_CODE_COL, programCode);
        values.put(SRV_CODE_COL, srvCode);
        values.put(OPERATION_CODE_COL, opCode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(SERVICE_CENTER_CODE_COL, srvCenterCode);
        values.put(FDP_CODE_COL, fdpCode);
        values.put(SRV_STATUS_COL, srvStatus);

        values.put(BABY_STATUS_COL, babyStatus);
        values.put(GMP_ATTENDACE_COL, gmpAttendence);
        values.put(WEIGHT_STATUS_COL, weightStatus);
        values.put(NUT_ATTENDANCE_COL, nutAttendance);
        values.put(VITA_UNDER5_COL, vitaUnder5);
        values.put(EXCLUSIVE_CURRENTLYBF_COL, exclCurrentLybf);
        values.put(DATE_COMPFEEDING_COL, dateComFeed);
        values.put(CMAMREF_COL, camRef);
        values.put(CMAMADD_COL, camAdd);
        values.put(ANCVISIT_COL, dateAncVisit);

        values.put(PNCVISIT_2D_COL, pncVisit2D);
        values.put(PNCVISIT_1W_COL, pncVisit1W);
        values.put(PNCVISIT_6W_COL, pncVisit6W);

        values.put(DELIVERY_STAFF_1_COL, deliveryStaff_1);
        values.put(DELIVERY_STAFF_2_COL, deliveryStaff_2);
        values.put(DELIVERY_STAFF_3_COL, deliveryStaff_3);

        values.put(HOME_SUPPORT24H_1D_COL, homeSupport24H_1D);
        values.put(HOME_SUPPORT24H_2D_COL, homeSupport24H_2D);
        values.put(HOME_SUPPORT24H_3D_COL, homeSupport24H_3D);
        values.put(HOME_SUPPORT24H_8D_COL, homeSupport24H_8D);
        values.put(HOME_SUPPORT24H_14D_COL, homeSupport24H_14D);
        values.put(HOME_SUPPORT24H_21D_COL, homeSupport24H_21D);
        values.put(HOME_SUPPORT24H_30D_COL, homeSupport24H_30D);
        values.put(HOME_SUPPORT24H_60D_COL, homeSupport24H_60D);
        values.put(HOME_SUPPORT24H_90D_COL, homeSupport24H_90D);

        values.put(HOME_SUPPORT48H_1D_COL, homeSupport48H_1D);
        values.put(HOME_SUPPORT48H_3D_COL, homeSupport48H_3D);
        values.put(HOME_SUPPORT48H_8D_COL, homeSupport48H_8D);
        values.put(HOME_SUPPORT48H_30D_COL, homeSupport48H_30D);
        values.put(HOME_SUPPORT48H_60D_COL, homeSupport48H_60D);
        values.put(HOME_SUPPORT48H_90D_COL, homeSupport48H_90D);

        values.put(MATERNAL_BLEEDING_COL, maternal_bleeed);
        values.put(MATERNAL_SEIZURE_COL, maternal_seizure);
        values.put(MATERNAL_INFECTION_COL, maternal_infection);
        values.put(MATERNAL_PROLONGEDLABOR_COL, maternal_proLongedLabor);
        values.put(MATERNAL_OBSTRUCTEDLABOR_COL, maternal_obstructedLabor);
        values.put(MATERNAL_PPRM_COL, maternal_pprm);

        values.put(NBORN_ASPHYXIA_COL, nBorn_Aspyxia);
        values.put(NBORN_SEPSIS_COL, nBorn_Sepsis);
        values.put(NBORN_HYPOTHERMIA_COL, nBorn_HypoThermai);
        values.put(NBORN_HYPERTHERMIA_COL, nBorn_HyperThermai);
        values.put(NBORN_NOSUCKLING_COL, nBorn_noSuckling);
        values.put(NBORN_JAUNDICE_COL, nBorn_Jaundices);

        values.put(CHILD_DIARRHEA_COL, child_Diarrhea);
        values.put(CHILD_PNEUMONIA_COL, child_Pneumonia);
        values.put(CHILD_FEVER_COL, child_Fever);
        values.put(CHILD_CEREBRALPALSY_COL, child_CerebralPalsy);

        values.put(IMMU_POLIO_COL, immu_Polio);
        values.put(IMMU_BCG_COL, immu_BCG);
        values.put(IMMU_MEASLES_COL, immu_Measles);
        values.put(IMMU_DPT_HIB_COL, immu_DPT_HIB);
        values.put(IMMU_LOTTA_COL, immu_Lotta);
        values.put(IMMU_OTHER_COL, immU_Other);

        values.put(FPCOUNSEL_MALECONDOM_COL, fpCounsel_MaleCondom);
        values.put(FPCOUNSEL_FEMALECONDOM_COL, fpCounsel_FemaleCondom);
        values.put(FPCOUNSEL_PILL_COL, fpCounsel_Pill);
        values.put(FPCOUNSEL_DEPO_COL, fpCounsel_Depo);
        values.put(FPCOUNSEL_LONGPARMANENT_COL, fpCounsel_LongParmanent);
        values.put(FPCOUNSEL_NOMETHOD_COL, fpCounsel_NoMethod);

        values.put(CROP_CODE_COL, cropCode);
        values.put(LOAN_SOURCE_COL, loanSource);
        values.put(LOAN_AMT_COL, loanAMT);
        values.put(ANIMAL_CODE_COL, animalCode);
        values.put(LEAD_CODE_COL, leadCode);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        // insert
        long id = db.insert(SERVICE_SPECIFIC_TABLE, null, values);
        db.close();

        return id;
    }


    public int uploadIntoServiceSpecificTable(String cCode, String donorCode, String awardCode
            , String distCode, String upCode, String unCode, String vCode, String hhId, String memId, String programCode
            , String srvCode, String opCode, String opMonthCode, String srvCenterCode
            , String fdpCode
            , String babyStatus
            , String gmpAttendence, String weightStatus, String nutAttendance, String vitaUnder5, String exclCurrentLybf
            , String dateComFeed, String camRef, String camAdd, String dateAncVisit
            , String pncVisit2D, String pncVisit1W, String pncVisit6W
            , String deliveryStaff_1, String deliveryStaff_2, String deliveryStaff_3
            , String homeSupport24H_1D, String homeSupport24H_2D, String homeSupport24H_3D
            , String homeSupport24H_8D, String homeSupport24H_14D, String homeSupport24H_21D
            , String homeSupport24H_30D, String homeSupport24H_60D, String homeSupport24H_90D
            , String homeSupport48H_1D, String homeSupport48H_3D, String homeSupport48H_8D
            , String homeSupport48H_30D, String homeSupport48H_60D, String homeSupport48H_90D
            , String maternal_bleeed, String maternal_seizure, String maternal_infection
            , String maternal_proLongedLabor, String maternal_obstructedLabor, String maternal_pprm
            , String nBorn_Aspyxia, String nBorn_Sepsis, String nBorn_HypoThermai
            , String nBorn_HyperThermai, String nBorn_noSuckling, String nBorn_Jaundices
            , String child_Diarrhea, String child_Pneumonia, String child_Fever, String child_CerebralPalsy
            , String immu_Polio, String immu_BCG, String immu_Measles
            , String immu_DPT_HIB, String immu_Lotta, String immU_Other
            , String fpCounsel_MaleCondom, String fpCounsel_FemaleCondom
            , String fpCounsel_Pill, String fpCounsel_Depo
            , String fpCounsel_LongParmanent, String fpCounsel_NoMethod
            , String cropCode, String loanSource
            , String loanAMT, String animalCode
            , String leadCode
            , String entryBy
            , String entryDate


    ) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String where = ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + distCode + "' "
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "' "
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "' "
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "' "
                + " AND " + HHID_COL + " = '" + hhId + "' "
                + " AND " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + memId + "' "
                + " AND " + PROG_CODE_COL + " = '" + programCode + "' "
                + " AND " + SRV_CODE_COL + " = '" + srvCode + "' "
                + " AND " + OPERATION_CODE_COL + " = '" + opCode + "' " //OPCODE 2 IS SERVICE MODE
                + " AND " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' "
                + " AND " + SERVICE_CENTER_CODE_COL + " = '" + srvCenterCode + "' "
                + " AND " + FDP_CODE_COL + " = '" + fdpCode + "' ";


        values.put(BABY_STATUS_COL, babyStatus);
        values.put(GMP_ATTENDACE_COL, gmpAttendence);
        values.put(WEIGHT_STATUS_COL, weightStatus);
        values.put(NUT_ATTENDANCE_COL, nutAttendance);
        values.put(VITA_UNDER5_COL, vitaUnder5);
        values.put(EXCLUSIVE_CURRENTLYBF_COL, exclCurrentLybf);
        values.put(DATE_COMPFEEDING_COL, dateComFeed);
        values.put(CMAMREF_COL, camRef);
        values.put(CMAMADD_COL, camAdd);
        values.put(ANCVISIT_COL, dateAncVisit);

        values.put(PNCVISIT_2D_COL, pncVisit2D);
        values.put(PNCVISIT_1W_COL, pncVisit1W);
        values.put(PNCVISIT_6W_COL, pncVisit6W);

        values.put(DELIVERY_STAFF_1_COL, deliveryStaff_1);
        values.put(DELIVERY_STAFF_2_COL, deliveryStaff_2);
        values.put(DELIVERY_STAFF_3_COL, deliveryStaff_3);

        values.put(HOME_SUPPORT24H_1D_COL, homeSupport24H_1D);
        values.put(HOME_SUPPORT24H_2D_COL, homeSupport24H_2D);
        values.put(HOME_SUPPORT24H_3D_COL, homeSupport24H_3D);
        values.put(HOME_SUPPORT24H_8D_COL, homeSupport24H_8D);
        values.put(HOME_SUPPORT24H_14D_COL, homeSupport24H_14D);
        values.put(HOME_SUPPORT24H_21D_COL, homeSupport24H_21D);
        values.put(HOME_SUPPORT24H_30D_COL, homeSupport24H_30D);
        values.put(HOME_SUPPORT24H_60D_COL, homeSupport24H_60D);
        values.put(HOME_SUPPORT24H_90D_COL, homeSupport24H_90D);

        values.put(HOME_SUPPORT48H_1D_COL, homeSupport48H_1D);
        values.put(HOME_SUPPORT48H_3D_COL, homeSupport48H_3D);
        values.put(HOME_SUPPORT48H_8D_COL, homeSupport48H_8D);
        values.put(HOME_SUPPORT48H_30D_COL, homeSupport48H_30D);
        values.put(HOME_SUPPORT48H_60D_COL, homeSupport48H_60D);
        values.put(HOME_SUPPORT48H_90D_COL, homeSupport48H_90D);

        values.put(MATERNAL_BLEEDING_COL, maternal_bleeed);
        values.put(MATERNAL_SEIZURE_COL, maternal_seizure);
        values.put(MATERNAL_INFECTION_COL, maternal_infection);
        values.put(MATERNAL_PROLONGEDLABOR_COL, maternal_proLongedLabor);
        values.put(MATERNAL_OBSTRUCTEDLABOR_COL, maternal_obstructedLabor);
        values.put(MATERNAL_PPRM_COL, maternal_pprm);

        values.put(NBORN_ASPHYXIA_COL, nBorn_Aspyxia);
        values.put(NBORN_SEPSIS_COL, nBorn_Sepsis);
        values.put(NBORN_HYPOTHERMIA_COL, nBorn_HypoThermai);
        values.put(NBORN_HYPERTHERMIA_COL, nBorn_HyperThermai);
        values.put(NBORN_NOSUCKLING_COL, nBorn_noSuckling);
        values.put(NBORN_JAUNDICE_COL, nBorn_Jaundices);

        values.put(CHILD_DIARRHEA_COL, child_Diarrhea);
        values.put(CHILD_PNEUMONIA_COL, child_Pneumonia);
        values.put(CHILD_FEVER_COL, child_Fever);
        values.put(CHILD_CEREBRALPALSY_COL, child_CerebralPalsy);

        values.put(IMMU_POLIO_COL, immu_Polio);
        values.put(IMMU_BCG_COL, immu_BCG);
        values.put(IMMU_MEASLES_COL, immu_Measles);
        values.put(IMMU_DPT_HIB_COL, immu_DPT_HIB);
        values.put(IMMU_LOTTA_COL, immu_Lotta);
        values.put(IMMU_OTHER_COL, immU_Other);

        values.put(FPCOUNSEL_MALECONDOM_COL, fpCounsel_MaleCondom);
        values.put(FPCOUNSEL_FEMALECONDOM_COL, fpCounsel_FemaleCondom);
        values.put(FPCOUNSEL_PILL_COL, fpCounsel_Pill);
        values.put(FPCOUNSEL_DEPO_COL, fpCounsel_Depo);
        values.put(FPCOUNSEL_LONGPARMANENT_COL, fpCounsel_LongParmanent);
        values.put(FPCOUNSEL_NOMETHOD_COL, fpCounsel_NoMethod);

        values.put(CROP_CODE_COL, cropCode);
        values.put(LOAN_SOURCE_COL, loanSource);
        values.put(LOAN_AMT_COL, loanAMT);
        values.put(ANIMAL_CODE_COL, animalCode);
        values.put(LEAD_CODE_COL, leadCode);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        // insert
        int affectedRow = db.update(SERVICE_SPECIFIC_TABLE, values, where, null);
        db.close();
//        Log.d(TAG, "No of Row affected  " + affectedRow + " in " + SERVICE_SPECIFIC_TABLE);
        return affectedRow;
    }


    public void deleteFromServSpecificTable(String cCode, String donorCode, String awardCode
            , String distCode, String upCode, String unCode, String vCode, String hhId, String memId, String programCode
            , String srvCode, String opCode, String opMonthCode) {


        SQLiteDatabase db = this.getWritableDatabase();

        String where = ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + distCode + "' "
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "' "
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "' "
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "' "
                + " AND " + HHID_COL + " = '" + hhId + "' "
                + " AND " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + memId + "' "
                + " AND " + PROG_CODE_COL + " = '" + programCode + "' "
                + " AND " + SRV_CODE_COL + " = '" + srvCode + "' "
                + " AND " + OPERATION_CODE_COL + " = '" + opCode + "' " //OPCODE 2 IS SERVICE MODE
                + " AND " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' ";


        // delete
        int noOfDeletedRow = db.delete(SERVICE_SPECIFIC_TABLE, where, null);
        db.close();
//        Log.d(TAG, "No of Row deleted  " + noOfDeletedRow + " in " + SERVICE_SPECIFIC_TABLE);

    }


    public void addInDistributionTableFormOnLine(DistributionSaveDataModel dist_data) {
        dist_data.setEntryBy("-");
        dist_data.setEntryDate("-");
        addInDistributionTable(dist_data);

    }

    public long addInDistributionTable(DistributionSaveDataModel distData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, distData.getCountryCode());
        values.put(ADM_DONOR_CODE_COL, distData.getAdmDonorCode());
        values.put(ADM_AWARD_CODE_COL, distData.getAdmAwardCode());
        values.put(LAY_R1_LIST_CODE_COL, distData.getDistrictCode());
        values.put(LAY_R2_LIST_CODE_COL, distData.getUpCode());
        values.put(LAY_R3_LIST_CODE_COL, distData.getUniteCode());
        values.put(LAY_R4_LIST_CODE_COL, distData.getVillageCode());
        values.put(ADM_PROG_CODE_COL, distData.getProgCode());
        values.put(ADM_SRV_CODE_COL, distData.getSrvCode());
        values.put(OP_MONTH_CODE_COL, distData.getOpMonthCode());
        values.put(FDP_CODE_COL, distData.getFDPCode());
        values.put(MEM_ID_15_D_COL, distData.getID());
        values.put(DISTRIBUTION_STATUS_COL, distData.getDistStatus());
        values.put(SRV_OP_MONTH_CODE_COL, distData.getSrvOpMonthCode());
        values.put(DIST_FLAG_COL, distData.getDistFlag());
        values.put(WORK_DAY_COL, distData.getWd());


        values.put(ENTRY_BY, distData.getEntryBy());
        values.put(ENTRY_DATE, distData.getEntryDate());
        long id = db.insert(DISTRIBUTION_TABLE, null, values);
        db.close();


        return id;
    }


    public long addInDistributionExtendedTable(String c_code, String donorCode, String awardCode, String districtCode,
                                               String upzellaCode, String uname, String vname, String program, String service, String opMonth,
                                               String fdp, String memID,
                                               String voItmSpec, String voItmUnit, String voRefNumber, String srvOpMonthCode, String distFlag, String entryBy, String entryDate, String is_online) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(ADM_COUNTRY_CODE_COL, c_code);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL, districtCode);
        values.put(LAY_R2_LIST_CODE_COL, upzellaCode);
        values.put(LAY_R3_LIST_CODE_COL, uname);
        values.put(LAY_R4_LIST_CODE_COL, vname);
        values.put(PROG_CODE_COL, program);
        values.put(SRV_CODE_COL, service);
        values.put(OP_MONTH_CODE_COL, opMonth);
        values.put(FDP_CODE_COL, fdp);
        values.put(MEM_ID_15_D_COL, memID);
        values.put(VOUCHER_ITEM_SPEC_COL, voItmSpec);
        values.put(VOUCHER_UNIT_COL, voItmUnit);
        values.put(VOUCHER_REFERENCE_NUMBER_COL, voRefNumber);

        values.put(SRV_OP_MONTH_CODE_COL, srvOpMonthCode);
        values.put(DIST_FLAG_COL, distFlag);

        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(SYNC_COL, is_online);
        long id = db.insert(DISTRIBUTION_EXTENDED_TABLE, null, values);
        db.close();
//        Log.d(TAG, "Distribution Extended table   added: " + id);

        return id;
    }


    public boolean getDistIsprepare(String AdmCountryCode, String AdmDonorCode, String AdmAwardCode, String ProgCode,
                                    String OpCode, String SrvOpMonthCode, String DisOpMonthCode, String FDPCode) {


        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT *  FROM " + DIST_N_PLAN_BASIC_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + AdmCountryCode + "' "
                + " AND " + ADM_DONOR_CODE_COL + " = '" + AdmDonorCode + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + AdmAwardCode + "' "
                + " AND " + PROG_CODE_COL + " = '" + ProgCode + "' "
                + " AND " + OPERATION_CODE_COL + " = '" + OpCode + "' "
                + " AND " + SRV_OP_MONTH_CODE_COL + " = '" + SrvOpMonthCode + "' "
                + " AND " + DIST_OP_MONTH_CODE_COL + " = '" + DisOpMonthCode + "' "
                + " AND " + FDP_CODE_COL + " = '" + FDPCode + "' ";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        } else
            return false;
    }

    public void addInDistributionNPlaneTable(String AdmCountryCode, String AdmDonorCode, String AdmAwardCode, String ProgCode,
                                             String OpCode, String SrvOpMonthCode, String DisOpMonthCode, String FDPCode
            , String DistFlag, String OrgCode, String Distributor, String DistributionDate, String DeliveryDate, String Status, String PreparedBy, String VerifiedBy, String ApproveBy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(ADM_COUNTRY_CODE_COL, AdmCountryCode);
        values.put(ADM_DONOR_CODE_COL, AdmDonorCode);
        values.put(ADM_AWARD_CODE_COL, AdmAwardCode);
        values.put(PROG_CODE_COL, ProgCode);
        values.put(OPERATION_CODE_COL, OpCode);
        values.put(SRV_OP_MONTH_CODE_COL, SrvOpMonthCode);
        values.put(DIST_OP_MONTH_CODE_COL, DisOpMonthCode);
        values.put(FDP_CODE_COL, FDPCode);
        values.put(DIST_FLAG_COL, DistFlag);
        values.put(ORG_CODE_COL, OrgCode);
        values.put(DISTRIBUTOR_COL, Distributor);
        values.put(DISTRIBUTION_DATE_COL, DistributionDate);
        values.put(DELIVERY_DATE_COL, DeliveryDate);
        values.put(STATUS, Status);
        values.put(PREPARED_BY_COL, PreparedBy);
        values.put(VERIFIED_BY_COL, VerifiedBy);
        values.put(APPROVED_BY_COL, ApproveBy);

        long id = db.insert(DIST_N_PLAN_BASIC_TABLE, null, values);
        db.close();
//        Log.d(TAG, "Distribution Extended table   added: " + id);


    }


    public String getMemberRegNDate(GraduationGridDataModel dtata) {
        AssignDataModel assignedMem = new AssignDataModel();
        assignedMem.setC_code(dtata.getCountryCode());
        assignedMem.setDistrictCode(dtata.getDistrictCode());
        assignedMem.setUpazillaCode(dtata.getUpazillaCode());
        assignedMem.setUnitCode(dtata.getUnitCode());
        assignedMem.setVillageCode(dtata.getVillageCode());
        assignedMem.setHh_id(dtata.getHh_id());
        assignedMem.setMemId(dtata.getMember_Id());
        assignedMem.setProgram_code(dtata.getProgram_code());
        assignedMem.setService_code(dtata.getService_code());
        return getMemberRegNDate(assignedMem);
    }


    /**
     * @since 2015-11-07
     */
    public String getMemberRegNDate(AssignDataModel assignedMem) {
        String regDate = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT   " + REG_DATE_COL + "  " +
                " FROM " + REGISTRATION_MEMBER_TABLE + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + assignedMem.getCountryCode() + "' " +
                " AND " + LAY_R1_LIST_CODE_COL + " = '" + assignedMem.getDistrictCode() + "' " +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + assignedMem.getUpazillaCode() + "' " +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + assignedMem.getUnitCode() + "' " +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + assignedMem.getVillageCode() + "' " +
                " AND " + HHID_COL + " = '" + assignedMem.getHh_id() + "' " +
                " AND " + HH_MEM_ID + " = '" + assignedMem.getMemId() + "' ";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    regDate = cursor.getString(cursor.getColumnIndex(REG_DATE_COL));
                }

            }
        } catch (NullPointerException e) {
//            Log.e(TAG, "in getMemberName() get Exception " + e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }


        db.close();

        return regDate;
    }


    /**
     * @since : 2015-09-18
     */
    public void addHHCategory(String cCode, String hhCatCode, String hhCategoryName) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(CATEGORY_CODE_COL, hhCatCode);
        values.put(CATEGORY_NAME_COL, hhCategoryName);
        // Insert
        long id = db.insert(LUP_REGNH_HEAD_CATEGORY_TABLE, null, values);
        db.close();
//        Log.d(TAG, "New House Hold Category  added: " + id);


    }

    public String getUserId() {
        String userId = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQurey = "SELECT " + USER_ID + " FROM " + LOGIN_TABLE;
        Cursor cursor;
        cursor = db.rawQuery(selectQurey, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                userId = cursor.getString(cursor.getColumnIndex(USER_ID));
            }
            cursor.close();
        }

        db.close();
        return userId;
    }

    public boolean ifExistsInCU2Table(AssignDataModel asPeople) {
        boolean flag = false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + REG_N_CU2_TABLE + " WHERE    " + ADM_COUNTRY_CODE_COL + "=? AND " + LAY_R1_LIST_CODE_COL + "=? AND "
                        + LAY_R2_LIST_CODE_COL + "=? AND " + LAY_R3_LIST_CODE_COL + "=? AND " + LAY_R4_LIST_CODE_COL + "=? AND " + HHID_COL + "=? AND " + MEM_ID_COL + "=?  ",
                new String[]{asPeople.getCountryCode(), asPeople.getDistrictCode(), asPeople.getUpazillaCode(), asPeople.getUnitCode(), asPeople.getVillageCode(),
                        asPeople.getHh_id(), asPeople.getMemId()});//*keyValue,keyvalue1*/});

        flag = (cursor.getCount() > 0) ? true : false;

        if (cursor != null)
            cursor.close();
        db.close();

        return flag;
    }

    public int editMemberDataIn_CU2(AssignDataModel assMem, String dob) {


        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ADM_PROG_CODE_COL, assMem.getProgram_code());
        values.put(ADM_SRV_CODE_COL, assMem.getService_code());
        values.put(REG_N_DAT_COL, assMem.getRegNDate());
        values.put(CU2DOB_DATE_COL, dob); // GDR Date
        values.put(GRD_CODE_COL, assMem.getGrdCode());
        values.put(CU2_GRD_DATE_COL, assMem.getGrdDate()); // GDR_Date
        values.put(ENTRY_BY, assMem.getEntryBy());
        values.put(ENTRY_DATE, assMem.getEntryDate());
        values.put(SYNC_COL, "0");


        String query = COUNTRY_CODE + " = '" + assMem.getCountryCode() + "' AND " +
                LAY_R_LIST_CODE_COL + " = '" + assMem.getDistrictCode() + "' AND " +
                LAY_R2_LIST_CODE_COL + " = '" + assMem.getUpazillaCode() + "' AND " +
                LAY_R3_LIST_CODE_COL + " = '" + assMem.getUnitCode() + "' AND " +
                LAY_R4_LIST_CODE_COL + " = '" + assMem.getVillageCode() + "' AND " +
                HHID_COL + " = '" + assMem.getHh_id() + "' AND " +
                HH_MEM_ID + " = '" + assMem.getMemId() + "'  ";


        int id = db.update(REG_N_CU2_TABLE, values, query, null);

        db.close();
        return id;
    }

    /*public long addMemberInto_CU2(AssignDataModel assingPerson, String dob) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COUNTRY_CODE, assingPerson.getCountryCode()); // country name

        values.put(LAY_R_LIST_CODE_COL, assingPerson.getDistrictCode()); // district name
        values.put(LAY_R2_LIST_CODE_COL, assingPerson.getUpazillaCode()); // upazilla name
        values.put(LAY_R3_LIST_CODE_COL, assingPerson.getUnitCode()); // Unit name
        values.put(LAY_R4_LIST_CODE_COL, assingPerson.getVillageCode()); // village  name


        values.put(HHID_COL, assingPerson.getHh_id()); // Hh id
        values.put(HH_MEM_ID, assingPerson.getMemId()); // member id
        values.put(ADM_PROG_CODE_COL, assingPerson.getProgram_code()); // program Code
        values.put(ADM_SRV_CODE_COL, assingPerson.getService_code()); // service Code

        values.put(REG_N_DAT_COL, assingPerson.getRegNDate()); //
        values.put(CU2DOB_DATE_COL, dob);
        values.put(GRD_CODE_COL, assingPerson.getGrdCode());
        values.put(CU2_GRD_DATE_COL, assingPerson.getGrdDate()); // GDR_Date

        values.put(ENTRY_BY, assingPerson.getEntryBy());
        values.put(ENTRY_DATE, assingPerson.getEntryDate());

// values.put(GRD_CODE_COL,"00"); // GDR_CODE
        //  values.put(GRD_DATE_COL,"00"); // GDR_Date


        // Inserting Row
        long id = db.insert(REG_N_CU2_TABLE, null, values);
        db.close(); // Closing database connection
        // updateRegNLMFStatus(assingPerson, 0);
        Log.d(TAG, "add member to the CU2 table: " + id);
        return id;
    }*/


    /**
     * Todo : Save permission
     *
     * @param cCode
     * @param districtCode
     * @param unitCode
     * @param upzellaCode
     * @param vCode
     */

    public boolean getSavePermissionForHHEntries(String cCode, String districtCode, String upzellaCode, String unitCode, String vCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        String savePermission = "";
        String selectQuery = SQLiteQuery.getSavePermissionSelectQuery(STAFF_GEO_INFO_ACCESS_TABLE, BTN_SAVE_COL, cCode, districtCode, upzellaCode, unitCode, vCode);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                savePermission = cursor.getString(cursor.getColumnIndex(BTN_SAVE_COL));
            }
        }
        if (cursor != null)
            cursor.close();
        db.close();


        // simplefli version
        return savePermission.equals("1");
    }


    public boolean getDeletePermissionForHHEntries(String cCode, String districtCode, String upzellaCode, String unitCode, String vCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        String savePermission = "";
        String selectQuery = SQLiteQuery.getSavePermissionSelectQuery(STAFF_GEO_INFO_ACCESS_TABLE, BTN_DEL_COL, cCode, districtCode, upzellaCode, unitCode, vCode);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                savePermission = cursor.getString(cursor.getColumnIndex(BTN_DEL_COL));
            }
        }
        if (cursor != null)
            cursor.close();
        db.close();

      /*  if (savePermission.equals("1")) return true;
        else
            return  false;
            */
        // simplefli version
        return savePermission.equals("1");
    }


    public void addStaffGeoAccessInfo(String staffCode, String cCode, String donorCode, String awardCode, String layrListCode/*, String districtCode, String upzellaCode, String unitCode, String vCode*/, String btnNew, String btnSave, String btnDel, String btnpepr, String btnAprv, String btnRevw, String btnVrfy, String btnDtrain) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STAFF_CODE, staffCode);
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);

        values.put(LAY_R_LIST_CODE_COL, layrListCode);
//        values.put(LAY_R2_LIST_CODE_COL, upzellaCode);
//        values.put(LAY_R3_LIST_CODE_COL, unitCode);
//        values.put(LAY_R4_LIST_CODE_COL, vCode);

        // the permission of user action
        values.put(BTN_NEW_COL, btnNew);
        values.put(BTN_SAVE_COL, btnSave);
        values.put(BTN_DEL_COL, btnDel);
        values.put(BTN_PEPR_COL, btnpepr);
        values.put(BTN_APRV_COL, btnAprv);
        values.put(BTN_REVW_COL, btnRevw);
        values.put(BTN_VRFY_COL, btnVrfy);
        values.put(BTN_DTRAN_COL, btnDtrain);


        // Inserting Row
        db.insert(STAFF_GEO_INFO_ACCESS_TABLE, null, values);
        db.close(); // Closing database connection


    }


    public void addStaffSrvCenterAccess(String staffCode, String cCode, String srvCenterCode, String btnNew, String btnSave, String btnDel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STAFF_CODE, staffCode);
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(SERVICE_CENTER_CODE_COL, srvCenterCode);

        values.put(BTN_NEW_COL, btnNew);
        values.put(BTN_SAVE_COL, btnSave);
        values.put(BTN_DEL_COL, btnDel);

        // Inserting Row
        db.insert(STAFF_SRV_CENTER_ACCESS_TABLE, null, values);
        db.close(); // Closing database connection
    }

    public void addOpMonthFromOnline(String cCode, String donorCode, String awardCode, String opCode, String opMonthCode, String mLable, String sDate, String eDate, String usasDate, String usaeDate, String status) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(OPERATION_CODE_COL, opCode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(MONTH_LABEL_COL, mLable);
        values.put(START_DATE_COL, sDate);
        values.put(END_DATE_COL, eDate);
        values.put(USA_START_DATE_COL, usasDate);
        values.put(USA_END_DATE_COL, usaeDate);
        values.put(STATUS, status);


        db.insert(OP_MONTH_TABLE, null, values);                                                     // Inserting Row
        db.close();                                                                                 // Closing database connection


    }

    // add location

    public void addGpsLocation(String cCode, String grpCode, String subGrpCode, String localCode, String localName, String lat, String lng) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(GROUP_CODE_COL, grpCode);
        values.put(SUB_GROUP_CODE_COL, subGrpCode);
        values.put(LOCATION_CODE_COL, localCode);
        values.put(LOCATION_NAME_COL, localName);
        values.put(LATITUDE_COL, lat);
        values.put(LONGITUDE_COL, lng);


        // Inserting Row
        db.insert(GPS_LOCATION_TABLE, null, values);
        db.close();                                                                                 // Closing database connection


    }

    // add gps sub group
    public void addGpsSubGroup(String grpCode, String subGrpCode, String subGrpName, String description) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GROUP_CODE_COL, grpCode);
        values.put(SUB_GROUP_CODE_COL, subGrpCode);
        values.put(SUB_GROUP_NAME_COL, subGrpName);
        values.put(DESCRIPTION_COL, description);


        // Inserting Row
//        long id =
        db.insert(GPS_SUB_GROUP_TABLE, null, values);
        db.close(); // Closing database connection

        //  Log.d(TAG, "New Group inserted into GPS_SUB_GROUP_TABLE: " + id);

    }

    public String getGroupNameFromDb(String groupCode) {
        String groupName = "";

        String selectQuery = "SELECT  " + GROUP_NAME_COL + " FROM " + GPS_GROUP_TABLE +
                " WHERE " + GROUP_CODE_COL + " = '" + groupCode + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                groupName = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }
        return groupName;
    }

    public String getSubGroupNameFromDb(String groupCode, String subGroupCode) {
        String sub_groupName = "";

        String selectQuery = "SELECT  " + SUB_GROUP_NAME_COL + " FROM " + GPS_SUB_GROUP_TABLE
                + " WHERE " + GROUP_CODE_COL + " = '" + groupCode + "'"
                + " AND " + SUB_GROUP_CODE_COL + " = '" + subGroupCode + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                sub_groupName = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }
        return sub_groupName;
    }

    // add gps Fdp
    public void addStaffFDPAccess(String staffCode, String countryCode, String fdpCode, String btnNew, String btnSave, String btnDel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STAFF_CODE, staffCode);
        values.put(STAFF_FDP_ACCESS_COUNTRY_CODE, countryCode);
        values.put(FDP_CODE_COL, fdpCode);
        values.put(BTN_NEW_COL, btnNew);
        values.put(BTN_SAVE_COL, btnSave);
        values.put(BTN_DEL_COL, btnDel);


        // Inserting Row
        long id = db.insert(STAFF_FDP_ACCESS_TABLE, null, values);
        db.close(); // Closing database connection

        //Log.d(TAG, "New Group inserted into " + STAFF_FDP_ACCESS_TABLE + ": " + id);

    }

    // add gps Fdp
    public void addFDPMaster(String countryCode, String fdpCode, String fdpName, String fdpCat, String whCode, String layer1, String layer2) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FDP_MASTER_COUNTRY_CODE, countryCode);
        values.put(FDP_CODE_COL, fdpCode);

        values.put(FDP_NAME_COL, fdpName);
        values.put(FDA_CATEGORIES_CODE_COL, fdpName);
        values.put(WH_CODE_COL, whCode);
        values.put(FDP_MASTER_LAY_R1_LIST_CODE_COL, layer1);
        values.put(FDP_MASTER_LAY_R2_LIST_CODE_COL, layer2);


        // Inserting Row
        long id = db.insert(FDP_MASTER_TABLE, null, values);
        db.close(); // Closing database connection


    }


    public ArrayList<GraduationGridDataModel> getMemberGraduationStatusList(String cCode, String donorCode, String awardCode, String programCode, String srvCode, String memCode) {

        ArrayList<GraduationGridDataModel> graduationGridList = new ArrayList<GraduationGridDataModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.getMemberGraduationStatusList_sql(cCode, donorCode, awardCode, programCode, srvCode, memCode);

        Cursor cursor = db.rawQuery(selectQuery, null);
        String dateformat;
        if (cursor.moveToFirst()) {
            do {


//                "
                GraduationGridDataModel data = new GraduationGridDataModel();

                data.setHh_id(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                data.setMember_Id(cursor.getString(cursor.getColumnIndex(REG_N_ASSIGN_PROG_SRV_HH_MEM_ID)));
                data.setMember_name(cursor.getString(cursor.getColumnIndex("memName")));

                data.setCountryCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                data.setDistrictCode(cursor.getString(cursor.getColumnIndex(LAY_R1_LIST_CODE_COL)));
                data.setUpazillaCode(cursor.getString(cursor.getColumnIndex(LAY_R2_LIST_CODE_COL)));
                data.setUnitCode(cursor.getString(cursor.getColumnIndex(LAY_R3_LIST_CODE_COL)));
                data.setVillageCode(cursor.getString(cursor.getColumnIndex(LAY_R4_LIST_CODE_COL)));
                data.setProgram_code(cursor.getString(cursor.getColumnIndex(PROG_CODE_COL)));
                data.setService_code(cursor.getString(cursor.getColumnIndex(SRV_CODE_COL)));

//                graduation.setGraduationDate(removeTimestamp(cursor.getString(cursor.getColumnIndex(GRD_DATE_COL))));
                data.setGraduationDate(cursor.getString(cursor.getColumnIndex(GRD_DATE_COL)));

                data.setVillageName(cursor.getString(cursor.getColumnIndex(LAY_R4_LIST_NAME_COL)));
                data.setGraduationTitle(cursor.getString(cursor.getColumnIndex("GRDTitle")));
                data.setnMemId(cursor.getString(cursor.getColumnIndex("nMemId")));

                //  Log.d(TAG, DatabaseUtils.dumpCursorToString(cursor));

                graduationGridList.add(data);

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return graduationGridList;


    }

    public String getHHIDForLiberia(String c_code, String distID, String upID, String unit, String villID) {

        String registrationID = "";

        String next_id = getNextHHidForLiberia(c_code, distID, upID, unit, villID);

        if (!next_id.isEmpty()) {
            Integer temp_id = Integer.parseInt(next_id);
            temp_id++;
            next_id = temp_id.toString();
        } else {
            next_id = "1";
        }

        int id_len = next_id.length();


        registrationID = getPadding(id_len, next_id);

        return registrationID;
    }


    public String getNextHHidForLiberia(String c_code, String distID, String upID, String unit, String villID) {

//        String query = "SELECT " + PID_COL + " AS max_rec FROM " + REG_N_HH_TABLE + " WHERE "
//                + ADM_COUNTRY_CODE_COL + "='" + c_code + "' AND "
//                + DISTRICT_NAME_COL + "='" + distID + "' AND "
//                + UPZILLA_NAME_COL + "='" + upID + "' AND "
//                + LAY_R3_LIST_NAME + "='" + unit + "' AND "
//                + LAY_R4_LIST_NAME_COL + "='" + villID + "'" +
//                " ORDER BY " + PID_COL + " DESC LIMIT 1";

        String query = "SELECT " + REGISTRATION_TABLE_HHID + " AS max_rec FROM " + REG_N_HH_TABLE + " WHERE "
                + ADM_COUNTRY_CODE_COL + "= '" + c_code + "' AND "
                + LAY_R1_LIST_CODE + "= '" + distID + "' AND "
                + REGISTRATION_TABLE_UPZILLA_CODE_COL + "= '" + upID + "' AND "
                + REGISTRATION_TABLE_UNION_CODE_COL + "= '" + unit + "' AND "
                + REGISTRATION_TABLE_VILLAGE_CODE_COL + "= '" + villID + "'" +
                " ORDER BY " + REGISTRATION_TABLE_HHID + " DESC LIMIT 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // EDITED BY POP
        if (cursor.moveToFirst()) {

            next_id = cursor.getString(cursor.getColumnIndex("max_rec"));

        }

        cursor.close();
        db.close();

        return next_id;
    }

    /**
     * @return specific data exit or not
     * @see #checkDataExistInTable(String, String)
     */

    public boolean ifDataExistIn_RegN_AGR(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID, String mmId) {
        return checkDataExistInTable(SQLiteQuery.checkDataExitsQueryInRegN__TableSQL(REG_N_AGR_TABLE, cCode, distCode, upCode, unCode, vCode, hhID, mmId), REG_N_AGR_TABLE);
    }

    public boolean ifDataExistIn_RegN_FFA(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID, String mmId) {
        return checkDataExistInTable(SQLiteQuery.checkDataExitsQueryInRegN__TableSQL(REG_N_FFA_TABLE, cCode, distCode, upCode, unCode, vCode, hhID, mmId), REG_N_FFA_TABLE);
    }

    public boolean ifDataExistIn_RegN_WE(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID, String mmId) {
        return checkDataExistInTable(SQLiteQuery.checkDataExitsQueryInRegN__TableSQL(REG_N_WE_TABLE, cCode, distCode, upCode, unCode, vCode, hhID, mmId), REG_N_WE_TABLE);
    }


    public ArrayList<ServiceSlDataModle> getServiceDetailsForMember(String cCode, String donorCode, String awardCord,
                                                                    String districCode, String upCode, String unCode,
                                                                    String vCode, String hhId, String mmId, String opCode,
                                                                    String opMCode, String prgCode, String srvCode) {
        ArrayList<ServiceSlDataModle> srvSlList = new ArrayList<ServiceSlDataModle>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.getServiceDetailsForMember_sql(cCode, donorCode, awardCord,
                districCode, upCode, unCode,
                vCode, hhId, mmId, opCode,
                opMCode, prgCode, srvCode);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ServiceSlDataModle srvDetails = new ServiceSlDataModle();

                srvDetails.setSrvSerial(cursor.getString(cursor.getColumnIndex(SERVICE_TABLE_SERVICE_SL_COL)));

                /** @tips: For Removing timestamp 2015-06-06 00:00:00.00 to 2015-06-06
                 * use mm-- dd--YYYY*/
                String temp = cursor.getString(cursor.getColumnIndex(SERVICE_TABLE_SERVICE_DT_COL));

                /**   MM--YYYY--DD*/

                srvDetails.setServiceDate(cursor.getString(cursor.getColumnIndex(SERVICE_TABLE_SERVICE_DT_COL)));


                srvDetails.setServiceStatus(cursor.getString(cursor.getColumnIndex(SRV_STATUS_COL)));


                srvSlList.add(srvDetails);

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return srvSlList;

    }


    public String getMemCU2DOB(AssignDataModel asPeople) {
        String dobDate = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.dbo_Get_MemCU2DOB("'" + asPeople.getCountryCode() + "'", "'" + asPeople.getDistrictCode() + "'", "'" + asPeople.getUpazillaCode() + "'", "'" + asPeople.getUnitCode() + "'", "'" + asPeople.getVillageCode() + "'", "'" + asPeople.getHh_id() + "'", "'" + asPeople.getMemId() + "'");

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            dobDate = cursor.getString(cursor.getColumnIndex(CU2DOB_DATE_COL));

        }
        if (cursor != null)
            cursor.close();

        db.close();


        return dobDate;

    }

    public String getDOBDate_CA2(AssignDataModel asPeople) {
        String dobDate = "";
        SQLiteDatabase db = this.getReadableDatabase();


        String selectQuery = "SELECT " + CA2DOB_DATE_COL + " FROM " + REG_N_CA2_TABLE + "" +
                " WHERE    " + ADM_COUNTRY_CODE_COL + " = '" + asPeople.getCountryCode() + "'" +
                " AND " + LAY_R1_LIST_CODE_COL + " = '" + asPeople.getDistrictCode() + "'" +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + asPeople.getUpazillaCode() + "'" +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + asPeople.getUnitCode() + "'" +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + asPeople.getVillageCode() + "'" +
                " AND " + HHID_COL + " = '" + asPeople.getHh_id() + "' " +
                " AND " + MEM_ID_COL + " = '" + asPeople.getMemId() + "'  ";


        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {

            dobDate = cursor.getString(cursor.getColumnIndex(CA2DOB_DATE_COL));

        }
        if (cursor != null)
            cursor.close();

        db.close();
        // mCursor.close();
        if (dobDate == null) {
            dobDate = "";
        }

        return dobDate;

    }

    /**
     * get LMP date for pregnant women  from PW
     */
    public String getLMPDate_PW(AssignDataModel asPeople) {
        String lmpDate = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + LMP_DATE_COL + " FROM " + REG_N_PW_TABLE + " WHERE    " + ADM_COUNTRY_CODE_COL + " = '" + asPeople.getCountryCode() + "' AND " +
                LAY_R1_LIST_CODE_COL + " = '" + asPeople.getDistrictCode() + "' AND " +
                LAY_R2_LIST_CODE_COL + " = '" + asPeople.getUpazillaCode() + "' AND " +
                LAY_R3_LIST_CODE_COL + " = '" + asPeople.getUnitCode() + "' AND " +
                LAY_R4_LIST_CODE_COL + " = '" + asPeople.getVillageCode() + "' AND " +
                HHID_COL + " = '" + asPeople.getHh_id() + "' AND " +
                MEM_ID_COL + " = '" + asPeople.getMemId() + "'  ";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {

//            lmpDate = removeTimestamp(cursor.getString(cursor.getColumnIndex(LMP_DATE_COL)));
            lmpDate = cursor.getString(cursor.getColumnIndex(LMP_DATE_COL));

//            Log.d(TAG, "Lmp Date " + cursor.getString(0));
        }
        if (cursor != null)
            cursor.close();

        db.close();
        // mCursor.close();

        return lmpDate;

    }


    public String getLMDate_LM(AssignDataModel asPeople) {
        String lmDate = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + LM_DATE_COL + " FROM " + REG_N_LM_TABLE + " WHERE    " + ADM_COUNTRY_CODE_COL + " = '" + asPeople.getCountryCode() + "' AND " +
                LAY_R1_LIST_CODE_COL + " = '" + asPeople.getDistrictCode() + "' AND " +
                LAY_R2_LIST_CODE_COL + " = '" + asPeople.getUpazillaCode() + "' AND " +
                LAY_R3_LIST_CODE_COL + " = '" + asPeople.getUnitCode() + "' AND " +
                LAY_R4_LIST_CODE_COL + " = '" + asPeople.getVillageCode() + "' AND " +
                HHID_COL + " = '" + asPeople.getHh_id() + "' AND " +
                MEM_ID_COL + " = '" + asPeople.getMemId() + "'  ";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {


            lmDate = cursor.getString(cursor.getColumnIndex(LM_DATE_COL));

        }
        if (cursor != null)
            cursor.close();

        db.close();


        return lmDate;

    }


    public String getRegDate_RegNAsgProgSrv(AssignDataModel asPeople) {
        String regDate = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + REG_N_DAT_COL + " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE + " WHERE    " + ADM_COUNTRY_CODE_COL + " = '" + asPeople.getCountryCode() + "' AND " +
                LAY_R1_LIST_CODE_COL + " = '" + asPeople.getDistrictCode() + "' AND " +
                LAY_R2_LIST_CODE_COL + " = '" + asPeople.getUpazillaCode() + "' AND " +
                LAY_R3_LIST_CODE_COL + " = '" + asPeople.getUnitCode() + "' AND " +
                LAY_R4_LIST_CODE_COL + " = '" + asPeople.getVillageCode() + "' AND " +
                HHID_COL + " = '" + asPeople.getHh_id() + "' AND " +
                REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + asPeople.getMemId() + "'  ";

        Cursor mCursor = db.rawQuery(selectQuery, null);
        if (mCursor != null && mCursor.moveToFirst()) {
//            regDate = removeTimestamp(mCursor.getString(mCursor.getColumnIndex(REG_N_DAT_COL)));
            regDate = mCursor.getString(mCursor.getColumnIndex(REG_N_DAT_COL));
//            Log.d(TAG, "Reg Date:" + mCursor.getString(0));
        }
        if (mCursor != null) {
            mCursor.close();
        }

        db.close();

        return regDate;

    }


    public ArrayList<GPS_SubGroupAttributeDataModel> getGpsSubGroupAttributes(String groupCode, String subGroupCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<GPS_SubGroupAttributeDataModel> alist = new ArrayList<GPS_SubGroupAttributeDataModel>();

        String selectQuery = "SELECT "
                + ATTRIBUTE_CODE_COL
                + " , " + ATTRIBUTE_TITLE_COL
                + " , " + DATA_TYPE_COL
                + " , " + GPS_SUB_GROUP_ATTRIBUTES_TABLE_LOOK_UP_CODE_COL
                + " FROM " + GPS_SUB_GROUP_ATTRIBUTES_TABLE
                + " WHERE " + GROUP_CODE_COL + " = '" + groupCode + "' "
                + " AND " + SUB_GROUP_CODE_COL + " = '" + subGroupCode + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                GPS_SubGroupAttributeDataModel data = new GPS_SubGroupAttributeDataModel();
                data.setGroupCode(groupCode);
                data.setSubGroupCode(subGroupCode);
                data.setAttributeCode(cursor.getString(0));
                data.setAttributeTitle(cursor.getString(1));
                data.setDataType(cursor.getString(2));
                data.setLookUpCode(cursor.getString(3));

                alist.add(data);

            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }
        return alist;
    }


    public ArrayList<Lup_gpsListDataModel> getLupGPSList(String groupCode, String subGroupCode, String attCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Lup_gpsListDataModel> list = new ArrayList<Lup_gpsListDataModel>();

        String selectQuery = "SELECT * "

                + " FROM " + LUP_GPS_LIST_TABLE
                + " WHERE " + GROUP_CODE_COL + " = '" + groupCode + "' "
                + " AND " + SUB_GROUP_CODE_COL + " = '" + subGroupCode + "' "
                + " AND " + ATTRIBUTE_CODE_COL + " = '" + attCode + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Lup_gpsListDataModel data = new Lup_gpsListDataModel();
                data.setGroupCode(cursor.getString(0));
                data.setSubGroupCode(cursor.getString(1));
                data.setAttributeCode(cursor.getString(2));
                data.setLupValueCode(cursor.getString(3));
                data.setLupValueText(cursor.getString(4));


                list.add(data);

            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }
        return list;
    }


    public ServiceSpecificDataModel getSrvSpecificByMemberId(String cCode, String donorCode, String awardCode
            , String programCode, String srvCode, String opCode, String opMonthCode, String srvCenterCode
            , String fdpCode, String mem15Id) {


        SQLiteDatabase db = this.getReadableDatabase();
        ServiceSpecificDataModel data = new ServiceSpecificDataModel();

        String selectQuery = SQLiteQuery.getSrvSpecificByMemberId_SelectQuery(cCode, donorCode, awardCode
                , programCode, srvCode, opCode, opMonthCode, srvCenterCode, fdpCode, mem15Id);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                data.setBabyStatus(cursor.getString(cursor.getColumnIndex(BABY_STATUS_COL)));
                data.setGmpAttendence(cursor.getString(cursor.getColumnIndex(GMP_ATTENDACE_COL)));
                data.setWeightStatus(cursor.getString(cursor.getColumnIndex(WEIGHT_STATUS_COL)));
                data.setNutAttendance(cursor.getString(cursor.getColumnIndex(NUT_ATTENDANCE_COL)));
                data.setVitaUnder5(cursor.getString(cursor.getColumnIndex(VITA_UNDER5_COL)));
                data.setExclCurrentLybf(cursor.getString(cursor.getColumnIndex(EXCLUSIVE_CURRENTLYBF_COL)));
                data.setDateComFeed(cursor.getString(cursor.getColumnIndex(DATE_COMPFEEDING_COL)));
                data.setCamRef(cursor.getString(cursor.getColumnIndex(CMAMREF_COL)));
                data.setCamAdd(cursor.getString(cursor.getColumnIndex(CMAMADD_COL)));
                data.setDateAncVisit(cursor.getString(cursor.getColumnIndex(ANCVISIT_COL)));
                data.setPncVisit2D(cursor.getString(cursor.getColumnIndex(PNCVISIT_2D_COL)));
                data.setPncVisit1W(cursor.getString(cursor.getColumnIndex(PNCVISIT_1W_COL)));
                data.setPncVisit6W(cursor.getString(cursor.getColumnIndex(PNCVISIT_6W_COL)));
                data.setDeliveryStaff_1(cursor.getString(cursor.getColumnIndex(DELIVERY_STAFF_1_COL)));
                data.setDeliveryStaff_2(cursor.getString(cursor.getColumnIndex(DELIVERY_STAFF_2_COL)));
                data.setDeliveryStaff_3(cursor.getString(cursor.getColumnIndex(DELIVERY_STAFF_3_COL)));
                data.setHomeSupport24H_1D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_1D_COL)));
                data.setHomeSupport24H_2D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_2D_COL)));
                data.setHomeSupport24H_3D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_3D_COL)));
                data.setHomeSupport24H_8D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_8D_COL)));
                data.setHomeSupport24H_14D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_14D_COL)));
                data.setHomeSupport24H_21D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_21D_COL)));
                data.setHomeSupport24H_30D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_30D_COL)));
                data.setHomeSupport24H_60D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_60D_COL)));
                data.setHomeSupport24H_90D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_90D_COL)));
                data.setHomeSupport48H_1D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT48H_1D_COL)));
                data.setHomeSupport48H_3D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT48H_3D_COL)));
                data.setHomeSupport48H_8D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT48H_8D_COL)));
                data.setHomeSupport48H_30D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT48H_30D_COL)));
                data.setHomeSupport48H_60D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT48H_60D_COL)));
                data.setHomeSupport48H_90D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT48H_90D_COL)));
                data.setMaternal_bleeed(cursor.getString(cursor.getColumnIndex(MATERNAL_BLEEDING_COL)));
                data.setMaternal_seizure(cursor.getString(cursor.getColumnIndex(MATERNAL_SEIZURE_COL)));
                data.setMaternal_infection(cursor.getString(cursor.getColumnIndex(MATERNAL_INFECTION_COL)));
                data.setMaternal_proLongedLabor(cursor.getString(cursor.getColumnIndex(MATERNAL_PROLONGEDLABOR_COL)));
                data.setMaternal_obstructedLabor(cursor.getString(cursor.getColumnIndex(MATERNAL_OBSTRUCTEDLABOR_COL)));
                data.setMaternal_pprm(cursor.getString(cursor.getColumnIndex(MATERNAL_PPRM_COL)));
                data.setnBorn_Aspyxia(cursor.getString(cursor.getColumnIndex(NBORN_ASPHYXIA_COL)));
                data.setnBorn_Sepsis(cursor.getString(cursor.getColumnIndex(NBORN_SEPSIS_COL)));
                data.setnBorn_HypoThermai(cursor.getString(cursor.getColumnIndex(NBORN_HYPOTHERMIA_COL)));
                data.setnBorn_HyperThermai(cursor.getString(cursor.getColumnIndex(NBORN_HYPERTHERMIA_COL)));
                data.setnBorn_noSuckling(cursor.getString(cursor.getColumnIndex(NBORN_NOSUCKLING_COL)));
                data.setnBorn_Jaundices(cursor.getString(cursor.getColumnIndex(NBORN_JAUNDICE_COL)));
                data.setChild_Diarrhea(cursor.getString(cursor.getColumnIndex(CHILD_DIARRHEA_COL)));
                data.setChild_Pneumonia(cursor.getString(cursor.getColumnIndex(CHILD_PNEUMONIA_COL)));
                data.setChild_Fever(cursor.getString(cursor.getColumnIndex(CHILD_FEVER_COL)));
                data.setChild_CerebralPalsy(cursor.getString(cursor.getColumnIndex(CHILD_CEREBRALPALSY_COL)));
                data.setImmu_Polio(cursor.getString(cursor.getColumnIndex(IMMU_POLIO_COL)));
                data.setImmu_BCG(cursor.getString(cursor.getColumnIndex(IMMU_BCG_COL)));
                data.setImmu_Measles(cursor.getString(cursor.getColumnIndex(IMMU_MEASLES_COL)));
                data.setImmu_DPT_HIB(cursor.getString(cursor.getColumnIndex(IMMU_DPT_HIB_COL)));
                data.setImmu_Lotta(cursor.getString(cursor.getColumnIndex(IMMU_LOTTA_COL)));
                data.setImmU_Other(cursor.getString(cursor.getColumnIndex(IMMU_OTHER_COL)));
                data.setFpCounsel_MaleCondom(cursor.getString(cursor.getColumnIndex(FPCOUNSEL_MALECONDOM_COL)));
                data.setFpCounsel_FemaleCondom(cursor.getString(cursor.getColumnIndex(FPCOUNSEL_FEMALECONDOM_COL)));
                data.setFpCounsel_Pill(cursor.getString(cursor.getColumnIndex(FPCOUNSEL_PILL_COL)));
                data.setFpCounsel_Depo(cursor.getString(cursor.getColumnIndex(FPCOUNSEL_DEPO_COL)));
                data.setFpCounsel_LongParmanent(cursor.getString(cursor.getColumnIndex(FPCOUNSEL_LONGPARMANENT_COL)));
                data.setFpCounsel_NoMethod(cursor.getString(cursor.getColumnIndex(FPCOUNSEL_NOMETHOD_COL)));
                data.setCropCode(cursor.getString(cursor.getColumnIndex(CROP_CODE_COL)));
                data.setLoanSource(cursor.getString(cursor.getColumnIndex(LOAN_SOURCE_COL)));
                data.setLoanAMT(cursor.getString(cursor.getColumnIndex(LOAN_AMT_COL)));
                data.setAnimalCode(cursor.getString(cursor.getColumnIndex(ANIMAL_CODE_COL)));
                data.setLeadCode(cursor.getString(cursor.getColumnIndex(LEAD_CODE_COL)));


            }


            cursor.close();
            db.close();
        }


        return data;


    }


    public boolean isDataExitsInServiceSpecificTable(String cCode, String donorCode, String awardCode
            , String programCode
            , String srvCode, String opCode, String opMonthCode, String srvCenterCode
            , String fdpCode, String mem15Id) {
        SQLiteDatabase db = this.getReadableDatabase();

        boolean dataExits = false;

        String selectQuery = "SELECT * FROM " + SERVICE_SPECIFIC_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND  " + ADM_DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND  " + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND  " + PROG_CODE_COL + " = '" + programCode + "'"
                + " AND  " + SRV_CODE_COL + " = '" + srvCode + "'"
                + " AND  " + OPERATION_CODE_COL + " = '" + opCode + "'"
                + " AND  " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "'"
                + " AND  " + SERVICE_CENTER_CODE_COL + " = '" + srvCenterCode + "'"
                + " AND  " + FDP_CODE_COL + " = '" + fdpCode + "'"
                + " AND  " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || '' || " + LAY_R2_LIST_CODE_COL + " || '' || " + LAY_R3_LIST_CODE_COL
                + " || '' || " + LAY_R4_LIST_CODE_COL + " || '' || " + HHID_COL + " || '' || " + HH_MEM_ID + " = '" + mem15Id + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0) {
            dataExits = true;
        }
        cursor.close();
        db.close();


        return dataExits;
    }


    public boolean isDataExitsInGpsLocationAttributesTable(String cCode, String groupCode, String subGroupCode, String locationCode, String attributeCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean dataExits = false;

        String selectQuery = "SELECT  * "
                + " FROM " + GPS_LOCATION_ATTRIBUTES_TABLE
                + " WHERE "
                + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + GROUP_CODE_COL + " = '" + groupCode + "' "
                + " AND " + SUB_GROUP_CODE_COL + " = '" + subGroupCode + "' "
                + " AND " + LOCATION_CODE_COL + " = '" + locationCode + "' "
                + " AND " + ATTRIBUTE_CODE_COL + " = '" + attributeCode + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.getCount() > 0) {
            dataExits = true;
        }
        cursor.close();
        db.close();


        return dataExits;

    }


    public String getAttributeValuesGpsLocationAttributesTable(String cCode, String groupCode, String subGroupCode, String locationCode, String attributeCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        String attValues = "";

        String selectQuery = "SELECT  " + ATTRIBUTE_VALUE_COL
                + " FROM " + GPS_LOCATION_ATTRIBUTES_TABLE
                + " WHERE "
                + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + GROUP_CODE_COL + " = '" + groupCode + "' "
                + " AND " + SUB_GROUP_CODE_COL + " = '" + subGroupCode + "' "
                + " AND " + LOCATION_CODE_COL + " = '" + locationCode + "' "
                + " AND " + ATTRIBUTE_CODE_COL + " = '" + attributeCode + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                attValues = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }


        return attValues;

    }


    /**
     * @param query sql syntax
     * @return insert id
     */
    public long insertIntoUploadTable(String query) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQL_QUERY_SYNTAX, query);
        values.put(SYNC_COL, "0");
        long id = db.insert(UPLOAD_SYNTAX_TABLE, null, values);

        db.close();
        return id;

    }


    /**
     * @param query sp for dtresponse table
     * @return
     */
    public long insertIntoUploadPhysicalTable(String query, int sequence) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQL_QUERY_SYNTAX, query);
        values.put(DT_R_SEQ_COL, sequence);
        values.put(SYNC_COL, "0");
        long id = db.insert(UPLOAD_PHYSICAL_TABLE_SYNTAX_TABLE, null, values);

        db.close();
        return id;

    }


    public int uploadStatusFlagOfUploadTable(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SYNC_COL, 1);
        int updateId = db.update(UPLOAD_SYNTAX_TABLE, values, ID_COL + " = ? ", new String[]{String.valueOf(id)});
//        Log.d(TAG, "inserted into Upload Table id:" + updateId);
        return updateId;

    }

    /**
     * Used in Synchronize data in MainActivity
     * <p/>
     * for sql Query data
     */
    public ArrayList<dataUploadDB> getUploadSyntaxData() {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<dataUploadDB> allData = new ArrayList<dataUploadDB>();


        String selectQuery = "SELECT  * FROM " + UPLOAD_SYNTAX_TABLE + " WHERE " + SYNC_COL + "=0 "
                + " ORDER BY " + ID_COL + " ASC ";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            do {
                dataUploadDB data = new dataUploadDB();
                data._id = cursor.getString(cursor.getColumnIndex(ID_COL));
                data._syntax = cursor.getString(cursor.getColumnIndex(SQL_QUERY_SYNTAX));
                allData.add(data);

            } while (cursor.moveToNext());
        }
        db.close();
        return allData;
    }

    /**
     * date : 2015-10-17
     * <p/>
     * Faisal Mohammad
     * <p/>
     * SummaryAssignBaseCriteria.class
     * <p/>
     * description : base on the criteria this method will list of member which are assigned in particular Criteria or Service
     */

    public List<SummaryOfMemberAssignedListModel> getTotalListOfMemberRAssignedSummary(String cCode, String distCode, String upCode, String unCode, String vCode,
                                                                                       String donorCode, String awardCord, String prgCode, String srvCode) {

        List<SummaryOfMemberAssignedListModel> assignList = new ArrayList<SummaryOfMemberAssignedListModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.getTotalListOfMemberRAssignedSummary_sql(cCode, distCode, upCode, unCode, vCode, donorCode, awardCord, prgCode, srvCode);


        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SummaryOfMemberAssignedListModel assignedPeople = new SummaryOfMemberAssignedListModel();
                assignedPeople.setCustomId(cursor.getString(cursor.getColumnIndex("NewID")));
                assignedPeople.setMemberName(cursor.getString(cursor.getColumnIndex("memberName")));
                assignedPeople.setRegDate(cursor.getString(cursor.getColumnIndex("regDate")));
                assignedPeople.setGroupName(cursor.getString(cursor.getColumnIndex("grpName")));

                assignList.add(assignedPeople);
                //   Log.d(TAG, " Assigne summary List : " + cursor.getString(0) + " : " + cursor.getString(1) + " : " + cursor.getString(2) + " : " + cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return assignList;

    }

    /**
     * :
     * 2015-11-07
     * <p/>
     * :
     */
    public String getMemberName(String cCode, String disCode,
                                String upCode, String unCode,
                                String vCode, String hhID, String memID
    ) {
        String memberName = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT   " + MEM_NAME_COL + "  " +
                " FROM " + REGISTRATION_MEMBER_TABLE + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + LAY_R1_LIST_CODE_COL + " = '" + disCode + "' " +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "' " +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "' " +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "' " +
                " AND " + HHID_COL + " = '" + hhID + "' " +
                " AND " + HH_MEM_ID + " = '" + memID + "' ";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    memberName = cursor.getString(cursor.getColumnIndex(MEM_NAME_COL));
                }

            }
        } catch (NullPointerException e) {
//            Log.e(TAG, "in getMemberName() get Exception " + e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }


        db.close();

        return memberName;
    }


    public List<SummaryGroupListDataModel> getGroupSummaryList(String cCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<SummaryGroupListDataModel> list = new ArrayList<SummaryGroupListDataModel>();

        String sql = SQLiteQuery.getGroupSummaryList_sql(cCode);

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                SummaryGroupListDataModel data = new SummaryGroupListDataModel();
                data.setcCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                data.setDonorCode(cursor.getString(cursor.getColumnIndex(ADM_DONOR_CODE_COL)));
                data.setAwardCode(cursor.getString(cursor.getColumnIndex(ADM_AWARD_CODE_COL)));
                data.setProgramCode(cursor.getString(cursor.getColumnIndex(ADM_PROG_CODE_COL)));
                data.setGroupCatCode(cursor.getString(cursor.getColumnIndex(GROUP_CAT_CODE_COL)));
                data.setGroupCatShortName(cursor.getString(cursor.getColumnIndex(GROUP_CAT_SHORT_NAME_COL)));
                data.setGroupCode(cursor.getString(cursor.getColumnIndex(GROUP_CODE_COL)));

                data.setGroupName(cursor.getString(cursor.getColumnIndex(GROUP_NAME_COL)));
                data.setSrvShortName(cursor.getString(cursor.getColumnIndex(SERVICE_MASTER_SERVICE_SHORT_NAME_COL)));
                data.setCount(cursor.getString(cursor.getColumnIndex("c")));
                data.setLayR3Name(cursor.getString(cursor.getColumnIndex(LAY_R3_LIST_NAME)));

                list.add(data);

            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }

        return list;
    }


    public List<SummaryIdListInGroupDataModel> getIdListInGroupInGroupSummary(String cCode, String donorCode, String awardCode, String prgCode, String grpCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<SummaryIdListInGroupDataModel> list = new ArrayList<SummaryIdListInGroupDataModel>();


        String sql = SQLiteQuery.getIdListInGroupInGroupSummary_sql(cCode, donorCode, awardCode, prgCode, grpCode);


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                SummaryIdListInGroupDataModel data = new SummaryIdListInGroupDataModel();
                data.setnMemId(cursor.getString(cursor.getColumnIndex("idMem")));
                data.setMemName(cursor.getString(cursor.getColumnIndex("memName")));
                data.setSrvName(cursor.getString(cursor.getColumnIndex(SERVICE_MASTER_SERVICE_SHORT_NAME_COL)));
                data.setGrpName(cursor.getString(cursor.getColumnIndex(GROUP_NAME_COL)));


                list.add(data);

            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return list;
    }


    public String getAwardGraduation(String cCode, String donorCode, String awardCode) {
        String grdDate = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + AWARD_END_DATE_COL +
                " FROM " + ADM_COUNTRY_AWARD_TABLE +
                " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' ";


        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                grdDate = cursor.getString(0);
            }

            cursor.close();
            db.close();
        }


        return grdDate;
    }


    public String getProgramGraduationDateOfMember(String cCode, String disCode, String upCode, String unCode, String vCode, String hhID, String memID, String donorCode, String awardCode, String progCode) {
        String grdDate = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.getProgramGraduationDateOfMember_sql(cCode, disCode, upCode, unCode, vCode, hhID, memID, donorCode, awardCode, progCode);

        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                cursor.moveToFirst();
                grdDate = cursor.getString(cursor.getColumnIndex("grdDate"));
                /** @tips: For Removing timestamp 2015-06-06 00:00:00.00 to 2015-06-06
                 * use mm-- dd--YYYY*/
                String dateformat = "";
                if (grdDate.length() > 5) {
                    grdDate = grdDate.substring(0, 10);
                    dateformat = "";
                    dateformat = dateformat + grdDate.substring(5, 7) + "-" + grdDate.substring(8, 10) + "-" + grdDate.substring(0, 4);
                    grdDate = dateformat;
                }

            }
        } catch (NullPointerException e) {
//            Log.e(TAG, "in getProgramGraduationDateOfMember get Exception " + e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }


        db.close();


        return grdDate;
    }


    public int edtAssignAgerIn_Elderley(AssignDataModel assignDataModel, String elderleyNy, String regDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(REG_N_DAT_COL, regDate);
        values.put(ELDERLY_YN_COL, elderleyNy);
        String query = SQLiteQuery.edtAssignAgerIn_Elderley_sql(assignDataModel);
        int id = db.update(REG_N_AGR_TABLE, values, query, null);
        db.close();
        return id;
    }

    /**
     * @param asPeople AssignDataModel
     * @param lmpDate  lmpDate
     * @return no of affected rows
     */

    public int editMemberIn_PW(AssignDataModel asPeople, String lmpDate) {
        SQLiteDatabase db = this.getWritableDatabase();


        String sql = ADM_COUNTRY_CODE_COL + " = '" + asPeople.getCountryCode() + "' "
                + " AND " + LAY_R_LIST_CODE_COL + " = '" + asPeople.getDistrictCode() + "' "
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + asPeople.getUpazillaCode() + "' "
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + asPeople.getUnitCode() + "' "
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + asPeople.getVillageCode() + "' "
                + " AND " + HHID_COL + " = '" + asPeople.getHh_id() + "'"
                + " AND " + HH_MEM_ID + " = '" + asPeople.getMemId() + "'  ";

        ContentValues values = new ContentValues();
        values.put(REG_N_DAT_COL, asPeople.getRegNDate());
        values.put(LMP_DATE_COL, lmpDate);
        values.put(GRD_CODE_COL, asPeople.getGrdCode());
        values.put(PW_GRD_DATE_COL, asPeople.getGrdDate());
        values.put(ENTRY_DATE, asPeople.getEntryDate());
        values.put(ENTRY_BY, asPeople.getEntryBy());
        values.put(SYNC_COL, "0");


        // updating row
        int id = db.update(REG_N_PW_TABLE, values, sql, null);
        db.close();
        return id;
    }


    /**

     */

    public void editMemberIn_RegNmemProgGroup(String cCode, String donorCode, String awardCode, String disttCode, String upCode, String unCode, String vCode, String hhID, String memID
            , String progCode, String srvCode, String grpCode, String active, String entryBy, String entryDate, String grpLayR1Code, String grpLayR2Code, String grpLayR3Code) {
        SQLiteDatabase db = this.getWritableDatabase();


        String sql = SQLiteQuery.editMemberIn_RegNmemProgGroup_where_sql(cCode, donorCode, awardCode, disttCode, upCode, unCode, vCode, hhID, memID
                , progCode, srvCode);


        ContentValues values = new ContentValues();
        values.put(GROUP_CODE_COL, grpCode);
        values.put(ACTIVE_COL, active);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R1_LIST_CODE_COL, grpLayR1Code);
        values.put(REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R2_LIST_CODE_COL, grpLayR2Code);
        values.put(REG_N_MEM_PROG_GRP_TABLE_GRP_LAY_R3_LIST_CODE_COL, grpLayR3Code);
        values.put(ENTRY_DATE, entryDate);


        // updating row
        db.update(REG_N_MEM_PROG_GRP_TABLE, values, sql, null);

        db.close();

    }
// TODO: 10/17/2016 Rename the Column  name


    public int edtAssignAgerIn_PG(AssignDataModel assignDataModel, String landSize, String willingness, String dependOnGruney, String regDate, String invc, String nasfm, String cu, String other, int goat, int chicken, int pigion, int other_sp) {
        SQLiteDatabase db = this.getWritableDatabase();


        String where = SQLiteQuery.edtAssignAgerIn_Elderley_sql(assignDataModel);
//        ADM_COUNTRY_CODE_COL + " = '" + assignDataModel.getCountryCode() + "' AND " +
//                LAY_R_LIST_CODE_COL + " = '" + assignDataModel.getDistrictCode() + "' AND " +
//                LAY_R2_LIST_CODE_COL + " = '" + assignDataModel.getUpazillaCode() + "' AND " +
//                LAY_R3_LIST_CODE_COL + " = '" + assignDataModel.getUnitCode() + "' AND " +
//                LAY_R4_LIST_CODE_COL + " = '" + assignDataModel.getVillageCode() + "' AND " +
//                HHID_COL + " = '" + assignDataModel.getHh_id() + "' AND " +
//                HH_MEM_ID + " = '" + assignDataModel.getMemId() + "'  ";

        ContentValues values = new ContentValues();
        values.put(REG_N_DAT_COL, regDate);
        values.put(LAND_SIZE_COL, landSize);
        values.put(DEPEND_ON_GANYU_COL, dependOnGruney);
        values.put(WILLINGNESS_COL, willingness);
        values.put(AG_INVC_COL, invc);
        values.put(AG_NASFAM_COL, nasfm);
        values.put(AG_CU_COL, cu);
        values.put(AG_OTHER_COL, other);

        values.put(AG_L_S_GOAT_COL, goat);
        values.put(AG_L_S_CHICKEN_COL, chicken);
        values.put(AG_L_S_PIGION_COL, pigion);
        values.put(AG_L_S_OTHER_COL, other_sp);

        int id = db.update(REG_N_AGR_TABLE, values, where, null);
        return id;
    }


    public int edtAssignAgerIn_IG(AssignDataModel assignDataModel, String landSize, String willingness, String winterCultivation, String regDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = SQLiteQuery.edtAssignAgerIn_Elderley_sql(assignDataModel);
        ContentValues values = new ContentValues();
        values.put(REG_N_DAT_COL, regDate);
        values.put(LAND_SIZE_COL, landSize);
        values.put(WINTER_CULTIVATION_COL, winterCultivation);
        values.put(WILLINGNESS_COL, willingness);

        int id = db.update(REG_N_AGR_TABLE, values, query, null);
        return id;
    }


    public int edtAssignAgerIn_LG(AssignDataModel assignDataModel, String landSize, String willingness, String vurnarableHH, String regDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = SQLiteQuery.edtAssignAgerIn_Elderley_sql(assignDataModel);
        ContentValues values = new ContentValues();
        values.put(REG_N_DAT_COL, regDate);
        values.put(LAND_SIZE_COL, landSize);
        values.put(VULNERABLE_HH_COL, vurnarableHH);
        values.put(WILLINGNESS_COL, willingness);

        int id = db.update(REG_N_AGR_TABLE, values, query, null);
        return id;
    }


    public int edtAssignAgerIn_MG(AssignDataModel assignDataModel, String landSize, String willingness, String plantingVC, String regDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = SQLiteQuery.edtAssignAgerIn_Elderley_sql(assignDataModel);
        ContentValues values = new ContentValues();
        values.put(REG_N_DAT_COL, regDate);
        values.put(LAND_SIZE_COL, landSize);
        values.put(PLANTING_VALUE_CHAIN_CROP_COL, plantingVC);
        values.put(WILLINGNESS_COL, willingness);

        int id = db.update(REG_N_AGR_TABLE, values, query, null);
        return id;
    }


    public String getMaxCardRequesSl(String cCode, String donorCode,
                                     String awardCode, String disCode,
                                     String upCode, String unCode,
                                     String vCode, String hhID,
                                     String memID, String rptGroup,
                                     String reportCode) {
        String lasSl = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT CASE WHEN MAX( " + CARD_REQUEST_SL_COL + " ) IS NULL THEN '0' ELSE " + CARD_REQUEST_SL_COL + " END AS SL" +
                " FROM " + MEMBER_CARD_PRINT_TABLE + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + disCode + "' " +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "' " +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "' " +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "' " +
                " AND " + HHID_COL + " = '" + hhID + "' " +
                " AND " + MEM_ID_COL + " = '" + memID + "' " +
                " AND " + REPORT_GROUP_COL + " = '" + rptGroup + "' " +
                " AND " + REPORT_CODE_COL + " = '" + reportCode + "' ";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    lasSl = cursor.getString(cursor.getColumnIndex("SL"));
                }

            }
        } catch (NullPointerException e) {
//            Log.e(TAG, "In getMaxCardRequesSl() method Exeption " + e);
        } finally {
            if (cursor != null)
                cursor.close();
        }


        db.close();

        if (lasSl.length() > 0) {
            int padd = 3 - lasSl.length();
            for (int i = 0; i < padd; i++) {
                lasSl = "0" + lasSl;
            }
        } else {
            lasSl = "000";
        }


        return lasSl;
    }

    /**
     * @param cCode    String
     * @param distCode String
     * @param upCode   String
     * @param unCode   String
     * @param vCode    String
     * @param hhID     String
     * @param mmId     String
     * @return AGR_DataModel
     * Data Check for AGR Table
     */
    public AGR_DataModel checkAssignCriteriaInAGR_TableForMalwai(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID, String mmId, boolean impelmetedinMain) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.checkDataExitsQueryInAGR_TableAssignForMalwai(cCode, distCode, upCode, unCode, vCode, hhID, mmId, impelmetedinMain);
        Cursor cursor = db.rawQuery(selectQuery, null);
        AGR_DataModel agr_dataModel = new AGR_DataModel();
// default value
        agr_dataModel.setElderleyYN("N");
        agr_dataModel.setLandSize("00");
        agr_dataModel.setDepenonGanyu("N");
        agr_dataModel.setWillingness("N");
        agr_dataModel.setWinterCultivation("N");
        agr_dataModel.setVulnerableHh("N");
        agr_dataModel.setPlantingVcrop(null);
        //    Log.d(TAG, "In check AssignCriteria In AGR _ Table For Malwai");
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                agr_dataModel.setElderleyYN(cursor.getString(cursor.getColumnIndex(ELDERLY_YN_COL)));

                agr_dataModel.setLandSize(cursor.getString(cursor.getColumnIndex(LAND_SIZE_COL)));

                agr_dataModel.setDepenonGanyu(cursor.getString(cursor.getColumnIndex(DEPEND_ON_GANYU_COL)));
                agr_dataModel.setWillingness(cursor.getString(cursor.getColumnIndex(WILLINGNESS_COL)));

                agr_dataModel.setWinterCultivation(cursor.getString(cursor.getColumnIndex(WINTER_CULTIVATION_COL)));
                agr_dataModel.setVulnerableHh(cursor.getString(cursor.getColumnIndex(VULNERABLE_HH_COL)));
                agr_dataModel.setPlantingVcrop(cursor.getString(cursor.getColumnIndex(PLANTING_VALUE_CHAIN_CROP_COL)));
                agr_dataModel.setRegnDate(cursor.getString(cursor.getColumnIndex(REG_N_DAT_COL)));


                agr_dataModel.setAgInvc(cursor.getString(cursor.getColumnIndex(AG_INVC_COL)));
                agr_dataModel.setAgNasfam(cursor.getString(cursor.getColumnIndex(AG_NASFAM_COL)));
                agr_dataModel.setAgCu(cursor.getString(cursor.getColumnIndex(AG_CU_COL)));
                agr_dataModel.setAgOrther(cursor.getString(cursor.getColumnIndex(AG_OTHER_COL)));
                agr_dataModel.setIntGoat(cursor.getInt(cursor.getColumnIndex(AG_L_S_GOAT_COL)));
                agr_dataModel.setIntChicken(cursor.getInt(cursor.getColumnIndex(AG_L_S_CHICKEN_COL)));
                agr_dataModel.setIntPegion(cursor.getInt(cursor.getColumnIndex(AG_L_S_PIGION_COL)));
                agr_dataModel.setIntOther(cursor.getInt(cursor.getColumnIndex(AG_L_S_OTHER_COL)));


            }
            cursor.close();
        }
        db.close();
        return agr_dataModel;
    }


    public AGR_DataModel checkAssignCriteriaInRegN_WE(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID, String mmId) {


        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = " SELECT "
                + REG_DATE_COL
                + " , " + WEALTH_RANKING_COL
                + " , " + MEMBER_EXT_GROUP_COL
                + " FROM " + REG_N_WE_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + distCode + "' "
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "' "
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "'"
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "' "
                + " AND " + HHID_COL + " = '" + hhID + "' "
                + " AND " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + mmId + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        AGR_DataModel agr_dataModel = new AGR_DataModel();
// default value
        agr_dataModel.setRegNDate(" ");
        agr_dataModel.setWealthRank("N");
        agr_dataModel.setMemExitGrp("N");

//        Log.d(TAG, "In check AssignCriteria In WE _ Table For Malwai");
        if (cursor != null && cursor.moveToFirst()) {


            agr_dataModel.setRegNDate(cursor.getString(cursor.getColumnIndex(REG_DATE_COL)));
            agr_dataModel.setWealthRank(cursor.getString(cursor.getColumnIndex(WEALTH_RANKING_COL)));
            agr_dataModel.setMemExitGrp(cursor.getString(cursor.getColumnIndex(MEMBER_EXT_GROUP_COL)));

            cursor.close();
        }
        db.close();
        return agr_dataModel;
    }

    /**
     * @since :2015-11-09
     * <p/>
     * <p/>
     * <p/>
     * get DalivaryStatus no from MemberCardRequestTable
     */
    public String getCardDeliveryStatus(String cCode, String donorCode, String awardCode, String disCode, String upCode, String unCode, String vCode, String hhID, String memID, String rptGroup, String reportCode, String requestSl) {
        String cardDelivaryStatus = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  CASE WHEN " + DELIVERY_STATUS_COL + " " +
                " IS NULL THEN 'N' ELSE " + DELIVERY_STATUS_COL + " END AS deliveryStatus " +
                " FROM " + MEMBER_CARD_PRINT_TABLE + " WHERE " + CARD_REQUEST_SL_COL + " = '" + requestSl + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + disCode + "' " +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "' " +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "' " +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "' " +
                " AND " + HHID_COL + " = '" + hhID + "' " +
                " AND " + MEM_ID_COL + " = '" + memID + "' " +
                " AND " + REPORT_GROUP_COL + " = '" + rptGroup + "' " +
                " AND " + REPORT_CODE_COL + " = '" + reportCode + "' ";
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    cardDelivaryStatus = cursor.getString(cursor.getColumnIndex("deliveryStatus"));

                }

            }
        } catch (NullPointerException e) {
//            Log.e(TAG, "in getCardDeliveryStatus() get Exception " + e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }

        }
        db.close();

        return cardDelivaryStatus;
    }


    public String getCardDeliveryDate(String cCode, String donorCode,
                                      String awardCode, String disCode,
                                      String upCode, String unCode,
                                      String vCode, String hhID,
                                      String memID, String rptGroup,
                                      String reportCode, String requestSl) {
        String cardPrintDate = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  CASE WHEN MAX(" + DELIVERY_DATE_COL + ")" +
                " IS NULL THEN '' ELSE " + DELIVERY_DATE_COL + " END AS deliveryDate " +
                " FROM " + MEMBER_CARD_PRINT_TABLE + " WHERE " + CARD_REQUEST_SL_COL + " = '" + requestSl + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + disCode + "' " +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "' " +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "' " +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "' " +
                " AND " + HHID_COL + " = '" + hhID + "' " +
                " AND " + MEM_ID_COL + " = '" + memID + "' " +
                " AND " + REPORT_GROUP_COL + " = '" + rptGroup + "' " +
                " AND " + REPORT_CODE_COL + " = '" + reportCode + "' ";
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    cardPrintDate = cursor.getString(cursor.getColumnIndex("deliveryDate"));
                    /** @tips: For Removing timestamp 2015-06-06 00:00:00.00 to 2015-06-06
                     * use mm-- dd--YYYY*/
                    String dateformat = "";
                    if (cardPrintDate.length() > 5 && !cardPrintDate.equals("No Data found")) {
                        cardPrintDate = cardPrintDate.substring(0, 10);
                        dateformat = "";
                        dateformat = dateformat + cardPrintDate.substring(5, 7) + "-" + cardPrintDate.substring(8, 10) + "-" + cardPrintDate.substring(0, 4);
                        cardPrintDate = dateformat;
                    }
                }

            }
        } catch (NullPointerException e) {
//            Log.e(TAG, "in getCardRequestDate() get Exception " + e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }

        }
        db.close();

        return cardPrintDate;
    }


    /**
     * date :2015-11-07
     * modified:
     * author : Faisal mohamad
     * status
     * description : get Serial no from MemberCardRequestTable
     */
    public String getCardPrintDate(String cCode, String donorCode, String awardCode, String disCode, String upCode, String unCode, String vCode, String hhID, String memID, String rptGroup,
                                   String reportCode, String requestSl) {
        String cardPrintDate = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  CASE WHEN MAX(" + PRINT_DATE_COL + ")" +
                " IS NULL THEN '' ELSE " + PRINT_DATE_COL + " END AS printDate " +
                " FROM " + MEMBER_CARD_PRINT_TABLE + " WHERE " + CARD_REQUEST_SL_COL + " = '" + requestSl + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + disCode + "' " +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "' " +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "' " +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "' " +
                " AND " + HHID_COL + " = '" + hhID + "' " +
                " AND " + MEM_ID_COL + " = '" + memID + "' " +
                " AND " + REPORT_GROUP_COL + " = '" + rptGroup + "' " +
                " AND " + REPORT_CODE_COL + " = '" + reportCode + "' ";
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    cardPrintDate = cursor.getString(cursor.getColumnIndex("printDate"));
                    /** @tips: For Removing timestamp 2015-06-06 00:00:00.00 to 2015-06-06
                     * use mm-- dd--YYYY*/
                   /* String dateformat = "";
                    if (cardPrintDate.length() > 5 && !cardPrintDate.equals("No Data found")) {
                        cardPrintDate = cardPrintDate.substring(0, 10);
                        dateformat = "";
                        dateformat = dateformat + cardPrintDate.substring(5, 7) + "-" + cardPrintDate.substring(8, 10) + "-" + cardPrintDate.substring(0, 4);
                        cardPrintDate = dateformat;
                    }*/
                }

            }
        } catch (NullPointerException e) {
//            Log.e(TAG, "in getCardRequestDate() get Exception " + e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }

        }
        db.close();

        return cardPrintDate;
    }


    public long addCardDelivaryDate(String c_code, String donorCode, String awardCode, String disCode, String upCode, String unCode, String vCode, String hhid, String memid, String reportGroupCode, String reportCode, String cRequestSl, String cpReasonCode,
                                    String deliveryDate, String deliveryBy) {


        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();

        values.put(DELIVERY_DATE_COL, deliveryDate);
        values.put(DELIVERY_BY_COL, deliveryBy);
        values.put(DELIVERY_STATUS_COL, "Y");
        values.put(SYNC_COL, "0");


        String query = COUNTRY_CODE + " = '" + c_code + "' AND " +
                ADM_DONOR_CODE_COL + " = '" + donorCode + "' AND " +
                ADM_AWARD_CODE_COL + " = '" + awardCode + "' AND " +
                LAY_R_LIST_CODE_COL + " = '" + disCode + "' AND " +
                LAY_R2_LIST_CODE_COL + " = '" + upCode + "' AND " +
                LAY_R3_LIST_CODE_COL + " = '" + unCode + "' AND " +
                LAY_R4_LIST_CODE_COL + " = '" + vCode + "' AND " +
                HHID_COL + " = '" + hhid + "' AND " +
                HH_MEM_ID + " = '" + memid + "'AND  " +
                REPORT_GROUP_COL + " = '" + reportGroupCode + "'AND  " +
                REPORT_CODE_COL + " = '" + reportCode + "'AND  " +
                CARD_REQUEST_SL_COL + " = '" + cRequestSl + "'AND  " +
                CARD_PRINT_REASON_CODE_COL + " = '" + cpReasonCode + "' ";


        // updating row
        int id = db.update(MEMBER_CARD_PRINT_TABLE, values, query, null);
//        Log.d(TAG, "update Card Member Card Print's DelevaryDate  of MemberCardPrint Table: " + id);


        return id;


    }


    public long addCardRequestDataFromOnline(String c_code, String donorCode, String awardCode, String disCode, String upCode, String unCode, String vCode, String hhid, String memid, String reportGroupCode, String reportCode, String cRequestSl, String cpReasonCode,
                                             String requestDate, String printDate, String printBy, String deliveryDate, String deliveryBy, String deliveryStatus, String entryBy, String entryDate) {


        // TODO :: photo
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, c_code);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);

        values.put(MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL, disCode);
        values.put(LAY_R2_LIST_CODE_COL, upCode);
        values.put(LAY_R3_LIST_CODE_COL, unCode);
        values.put(LAY_R4_LIST_CODE_COL, vCode);

        values.put(HHID_COL, hhid); // Registration id
        values.put(MEM_ID_COL, memid); // member id


        values.put(REPORT_GROUP_COL, reportGroupCode);
        values.put(REPORT_CODE_COL, reportCode);
        values.put(CARD_REQUEST_SL_COL, cRequestSl);
        values.put(CARD_PRINT_REASON_CODE_COL, cpReasonCode);
        values.put(REQUEST_DATE_COL, requestDate);
        values.put(PRINT_DATE_COL, printDate);
        values.put(PRINT_BY_COL, printBy);
        values.put(DELIVERY_DATE_COL, deliveryDate);
        values.put(DELIVERY_BY_COL, deliveryBy);

        values.put(DELIVERY_STATUS_COL, deliveryStatus);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(SYNC_COL, "1");

        //  values.put(GRD_DATE_COL,"00"); // GDR_Date


        // Inserting Row
        long id = db.insert(MEMBER_CARD_PRINT_TABLE, null, values);
        db.close(); // Closing database connection
        // updateRegNLMFStatus(assingPerson, 0);
//        Log.d(TAG, "New Member Card Print data added into MemberCardPrint Table: " + id);
        return id;
    }


    public long addCardRequestDate(String c_code, String donorCode, String awardCode,
                                   String disCode, String upCode, String unCode,
                                   String vCode, String hhid, String memid,
                                   String reportGroupCode, String reportCode, String cRequestSl, String cpReasonCode,
                                   String requestDate, String entryBy,
                                   String entryDate) {


        // TODO :: photo
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE, c_code);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);

        values.put(MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL, disCode);
        values.put(LAY_R2_LIST_CODE_COL, upCode);
        values.put(LAY_R3_LIST_CODE_COL, unCode);
        values.put(LAY_R4_LIST_CODE_COL, vCode);

        values.put(HHID_COL, hhid); // Registration id
        values.put(MEM_ID_COL, memid); // member id


        values.put(REPORT_GROUP_COL, reportGroupCode);
        values.put(REPORT_CODE_COL, reportCode);
        values.put(CARD_REQUEST_SL_COL, cRequestSl);
        values.put(CARD_PRINT_REASON_CODE_COL, cpReasonCode);
        values.put(REQUEST_DATE_COL, requestDate);

        values.put(DELIVERY_STATUS_COL, "N");
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(SYNC_COL, "0");


        //  values.put(GRD_DATE_COL,"00"); // GDR_Date


        // Inserting Row
        long id = db.insert(MEMBER_CARD_PRINT_TABLE, null, values);
        db.close(); // Closing database connection
        // updateRegNLMFStatus(assingPerson, 0);
//        Log.d(TAG, "New Member Card Print data added into MemberCardPrint Table: " + id);
        return id;
    }


    /**
     * date :2015-11-07
     * modified: 2015-11-07
     * <p/>
     * <p/>
     * get Serial no from MemberCardRequestTable
     */
    public String getCardRequestDate(String cCode, String donorCode, String awardCode, String disCode, String upCode, String unCode, String vCode, String hhID,
                                     String memID, String rptGroup, String reportCode, String requestSl) {
        String cardRequestDate = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  CASE WHEN MAX(" + REQUEST_DATE_COL + ")" +
                " IS NULL THEN '' ELSE " + REQUEST_DATE_COL + " END AS requestDate " +
                " FROM " + MEMBER_CARD_PRINT_TABLE + " WHERE " + CARD_REQUEST_SL_COL + " = '" + requestSl + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + disCode + "' " +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "' " +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "' " +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "' " +
                " AND " + HHID_COL + " = '" + hhID + "' " +
                " AND " + MEM_ID_COL + " = '" + memID + "' " +
                " AND " + REPORT_GROUP_COL + " = '" + rptGroup + "' " +
                " AND " + REPORT_CODE_COL + " = '" + reportCode + "' ";
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    cardRequestDate = cursor.getString(cursor.getColumnIndex("requestDate"));
                    /** @tips: For Removing timestamp 2015-06-06 00:00:00.00 to 2015-06-06
                     * use mm-- dd--YYYY*/
                    String dateformat = "";
                    if (cardRequestDate.length() > 5 && !cardRequestDate.equals("No Data found")) {
                        cardRequestDate = cardRequestDate.substring(0, 10);
                        dateformat = "";
                        dateformat = dateformat + cardRequestDate.substring(5, 7) + "-" + cardRequestDate.substring(8, 10) + "-" + cardRequestDate.substring(0, 4);
                        cardRequestDate = dateformat;
                    }
                }

            }
        } catch (NullPointerException e) {
//            Log.e(TAG, "in getCardRequestDate() get Exception " + e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }

        }
        db.close();

        return cardRequestDate;
    }


    /**
     * LIST OF ID THAT GET SERVICE  OF VOUCHER PROGRAM
     */

    public List<SummaryServiceListModel> getTotalSerDistItemizeAttendanceSummary(String cCode, String donorCode, String awardCord, String opMCode, String prgCode/*, String srvCode*/, String vouItSpec, String srvDistFlag) {
        // final String OP_CODE_FOR_SERVICE = "2";
        List<SummaryServiceListModel> srvRecvList = new ArrayList<SummaryServiceListModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        // String selectQuery = SQLiteQuery.getTotalServiceAttendanceSummary_SelectQuery(cCode, donorCode, awardCord, opMCode, prgCode, srvCode);
        String selectQuery;

        if (srvDistFlag.equals(KEY.SRV_FLAG)) {

            selectQuery = SQLiteQuery.getTotal_Service_Itemize_AttendanceSummary_SelectQuery(cCode, donorCode, awardCord, opMCode, prgCode/*, String srvCode*/, vouItSpec);

        } else {


            selectQuery = SQLiteQuery.getTotal_Distribution_Itemize_AttendanceSummary_SelectQuery(cCode, donorCode, awardCord, opMCode, prgCode/*, String srvCode*/, vouItSpec);
        }

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SummaryServiceListModel srvL = new SummaryServiceListModel();

                srvL.setCustomId(cursor.getString(0));
                srvL.setServiceCount(cursor.getString(1));
                srvL.setPer_unit_cost(cursor.getString(2));

                srvRecvList.add(srvL);
                //    Log.d(TAG, " Service summary List : " + cursor.getString(0) + " : " + cursor.getString(1) + " : " + cursor.getString(2) + " : " + cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return srvRecvList;

    }


    /**
     * LIST OF ID THAT GET SERVICE
     */

    public List<SummaryServiceListModel> getTotalServiceNDistributionAttendanceSummary(String cCode, String donorCode, String awardCord, String opMCode, String prgCode, String srvCode, String distFlag, String srvORDistFlag) {
        final String OP_CODE_FOR_SERVICE = "2";
        List<SummaryServiceListModel> srvRecvList = new ArrayList<SummaryServiceListModel>();
        SQLiteDatabase db = this.getReadableDatabase();


        String selectQuery;
        if (srvORDistFlag.equals(KEY.DIST_FLAG)) {
            selectQuery = SQLiteQuery.getTotalDistributionAttendanceSummary_SelectQuery(cCode, donorCode, awardCord, opMCode, prgCode, srvCode, distFlag);
        } else {
            selectQuery = SQLiteQuery.getTotalServiceAttendanceSummary_SelectQuery(cCode, donorCode, awardCord, opMCode, prgCode, srvCode, distFlag);
        }

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SummaryServiceListModel srvL = new SummaryServiceListModel();

                srvL.setCustomId(cursor.getString(cursor.getColumnIndex("NewID")));

                srvL.setServiceCount(cursor.getString(1));
                srvL.setMemberName(cursor.getString(2)); // member  name
                srvL.setStatus(cursor.getString(cursor.getColumnIndex("status"))); // member  name

                srvRecvList.add(srvL);
                //    Log.d(TAG, " Service summary List : " + cursor.getString(0) + " : " + cursor.getString(1) + " : " + cursor.getString(2) + " : " + cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return srvRecvList;

    }


    /**
     * @since : 2015-10-15 m:2015-10-19
     * <p/>
     * <p/>
     * this method load Assign  Criteria for Assigne SumRegLay4TotalHHRecords Criteria
     */

    public List<SummaryCriteriaModel> getAssignCriteriaList(String cCode, String distCode, String upCode, String unCode, String vCode, String donorCode, String awardCode, String progCode) {//, String opMCode) {
        List<SummaryCriteriaModel> criteriaList = new ArrayList<SummaryCriteriaModel>();//List<SummaryCriteriaModel>();
        SQLiteDatabase db = this.getReadableDatabase();//Database();
        String selectQuery = SQLiteQuery.getAssignCriteriaListSelectQuery(cCode, donorCode, awardCode, progCode, distCode, upCode, unCode, vCode);

        Cursor cursor = db.rawQuery(selectQuery, null);
        //  SummaryCriteriaModel crite = new SummaryCriteriaModel();
        if (cursor.moveToFirst()) {
            do {
                SummaryCriteriaModel crite = new SummaryCriteriaModel();
                crite.setCriteria_name(cursor.getString(cursor.getColumnIndex("Criteria")));//cursor
                crite.setCriteria_id(cursor.getString(cursor.getColumnIndex("IdCriteria")));
                crite.setRecord(Integer.parseInt(cursor.getString(cursor.getColumnIndex("AssignCount"))));
                criteriaList.add(crite);
//                Log.d(TAG, " Service summary Criteria : " + cursor.getString(0) + ",IdCriteria : " + cursor.getString(1) + ", AssignCount : " + cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return criteriaList;
    }

    /*// use in AGR Module
    public void insertDataInto_RegNAgrTable(String c_code, String d_code, String upname, String uname, String vname, String hhiD,
                                           String hhMemId, String regnDate, String elderleyYN, String landSize, String depenonGanyu,
                                           String willingness, String winterCultivation, String vulnerableHh, String plantingVcrop, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COUNTRY_CODE, c_code);
        values.put(LAY_R_LIST_CODE_COL, d_code);
        values.put(LAY_R2_LIST_CODE_COL, upname);
        values.put(LAY_R3_LIST_CODE_COL, uname);
        values.put(LAY_R4_LIST_CODE_COL, vname);
        values.put(HHID_COL, hhiD);
        values.put(HH_MEM_ID, hhMemId);
        values.put(REG_N_DAT_COL, regnDate);
        values.put(ELDERLY_YN_COL, elderleyYN);
        values.put(LAND_SIZE_COL, landSize);
        values.put(DEPEND_ON_GANYU_COL, depenonGanyu);
        values.put(WILLINGNESS_COL, willingness);
        values.put(WINTER_CULTIVATION_COL, winterCultivation);
        values.put(VULNERABLE_HH_COL, vulnerableHh);
        values.put(PLANTING_VALUE_CHAIN_CROP_COL, plantingVcrop);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);


        db.insert(REG_N_AGR_TABLE, null, values);

        db.close();
    }*//*REG_N_AGR_TABLE*/
    public void addInLupSrvOptionListFromOnline(String countryCode, String progCode, String srvCode, String optionCode, String optionName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, countryCode);
        values.put(PROG_CODE_COL, progCode);
        values.put(SRV_CODE_COL, srvCode);
        values.put(LUP_OPTION_CODE_COL, optionCode);
        values.put(LUP_OPTION_NAME_COL, optionName);

        db.insert(LUP_SRV_OPTION_LIST_TABLE, null, values);

        db.close();

    }

    public void addVoucherItemTableFromOnline(String catCode, String itemCode, String itemName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(VOUCHER_ITEM_CATEGORY_CODE_COL, catCode);
        values.put(ITEM_CODE_COL, itemCode);
        values.put(ITEM_NAME_COL, itemName);


        db.insert(VOUCHER_ITEM_TABLE, null, values);

        db.close();

    }

    public void addVoucherItemMeasFromOnline(String measRCode, String uniteMeas, String measeTitle) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MEAS_R_CODE_COL, measRCode);
        values.put(UNITE_MEAS_COL, uniteMeas);
        values.put(MEASE_TITLE_COL, measeTitle);


        db.insert(VOUCHER_ITEM__MEAS_TABLE, null, values);

        db.close();

    }


    public void addVoucherCountryProgItemFromOnline(String countryCode, String donorCode, String awardCode,
                                                    String progCode, String srvCode, String catCode,
                                                    String itemCode, String mearCode, String itemSpec,
                                                    String uniteCost, String active, String currency
    ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, countryCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(ADM_SRV_CODE_COL, srvCode);
        values.put(VOUCHER_ITEM_CATEGORY_CODE_COL, catCode);
        values.put(ITEM_CODE_COL, itemCode);
        values.put(MEAS_R_CODE_COL, mearCode);
        values.put(VOUCHER_ITEM_SPEC_COL, itemSpec);
        values.put(UNITE_COST_COL, uniteCost);
        values.put(ACTIVE_STATUS_COL, active);
        values.put(CURRENCY_COL, currency);


        db.insert(VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE, null, values);

        db.close();

    }


    public void addGPS_SubGroupAttributesFromOnline(String groupCode, String subGroupCode, String attributeCode, String attributeTitle, String dataType, String lupTableName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GROUP_CODE_COL, groupCode);
        values.put(SUB_GROUP_CODE_COL, subGroupCode);
        values.put(ATTRIBUTE_CODE_COL, attributeCode);
        values.put(ATTRIBUTE_TITLE_COL, attributeTitle);
        values.put(DATA_TYPE_COL, dataType);
        values.put(GPS_SUB_GROUP_ATTRIBUTES_TABLE_LOOK_UP_CODE_COL, lupTableName);

        db.insert(GPS_SUB_GROUP_ATTRIBUTES_TABLE, null, values);

        db.close();

    }


    public void addLUP_GPS_TableFromOnline(String groupCode, String subGroupCode, String attributeCode, String lookUpCode, String lookUpName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GROUP_CODE_COL, groupCode);
        values.put(SUB_GROUP_CODE_COL, subGroupCode);
        values.put(ATTRIBUTE_CODE_COL, attributeCode);
        values.put(LUP_GPS_TABLE_LOOK_UP_CODE_COL, lookUpCode);
        values.put(GPS_SUB_GROUP_ATTRIBUTES_TABLE_LOOK_UP_NAME_COL, lookUpName);

        db.insert(LUP_GPS_TABLE, null, values);

        db.close();

    }


    public void addGPSLocationAttributesFromOnline(String cCode, String groupCode, String subGroupCode, String locationCode, String attributeCode, String attributeValue, String photo) {
        String entryBy = "";
        String entryDate = "";
        addGPSLocationAttributes(cCode, groupCode, subGroupCode, locationCode, attributeCode, attributeValue, photo, entryBy, entryDate);
    }


    public void addGPSLocationAttributes(String cCode, String groupCode, String subGroupCode, String locationCode, String attributeCode, String attributeValue, String photo, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(GROUP_CODE_COL, groupCode);
        values.put(SUB_GROUP_CODE_COL, subGroupCode);
        values.put(LOCATION_CODE_COL, locationCode);
        values.put(ATTRIBUTE_CODE_COL, attributeCode);
        values.put(ATTRIBUTE_VALUE_COL, attributeValue);
        values.put(ATTRIBUTE_PHOTO_COL, photo);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        db.insert(GPS_LOCATION_ATTRIBUTES_TABLE, null, values);

        db.close();

    }

    public boolean isDataExistsInGpsLocationAttributesTable(String cCode, String groupCode, String subGroupCode, String locationCode, String attributeCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + GPS_LOCATION_ATTRIBUTES_TABLE + " WHERE " +

                ADM_COUNTRY_CODE_COL + " = '" + cCode + "' AND " +
                GROUP_CODE_COL + " = '" + groupCode + "' AND " +
                SUB_GROUP_CODE_COL + " = '" + subGroupCode + "' AND " +
                LOCATION_CODE_COL + " = '" + locationCode + "' AND " +
                ATTRIBUTE_CODE_COL + " = '" + attributeCode + "' ";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        } else
            return false;


    }


    public GPS_LocationAttributeDataModel getDataFromInGpsLocationAttributesTable(String cCode, String groupCode, String subGroupCode, String locationCode, String attributeCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        GPS_LocationAttributeDataModel data = new GPS_LocationAttributeDataModel();
        String sql = "SELECT * FROM " + GPS_LOCATION_ATTRIBUTES_TABLE + " WHERE " +

                ADM_COUNTRY_CODE_COL + " = '" + cCode + "' AND " +
                GROUP_CODE_COL + " = '" + groupCode + "' AND " +
                SUB_GROUP_CODE_COL + " = '" + subGroupCode + "' AND " +
                LOCATION_CODE_COL + " = '" + locationCode + "' AND " +
                ATTRIBUTE_CODE_COL + " = '" + attributeCode + "' ";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                data.setCountryCode(cursor.getString(0));
                data.setGroupCode(cursor.getString(1));
                data.setSubGroupCode(cursor.getString(2));
                data.setLocationCode(cursor.getString(3));
                data.setAttributeCode(cursor.getString(4));
                data.setAttributeValue(cursor.getString(5));


            }
            cursor.close();
            db.close();
        }
        return data;

    }

    // todo : check the it frist

    /*public void isDataExitInCommunityGroup(String cCode, String donorCode, String awardCode, String progCode, String groupCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql= "SELECT * FROM "+COMMUNITY_GROUP_TABLE+
                +" WHERE "+

        ADM_COUNTRY_CODE_COL    , cCode
        ADM_DONOR_CODE_COL      , donorCode
        ADM_AWARD_CODE_COL      , awardCode
        ADM_PROG_CODE_COL     , progCode
        GROUP_CODE_COL, groupCode
    }*/


    /**
     * table Schema
     *
     * @param cCode         Country  Code
     * @param donorCode     donor Code
     * @param awardCode     award Code
     * @param progCode      program Code
     * @param grpCode       group Code
     * @param grpName       group Name
     * @param grpCatCode    group Categories Code
     * @param layR1Code     LayR1 Code
     * @param layR2Code     LayR2 Code
     * @param srvCenterCode Service Center Code
     * @param entryBy       Service Center Code
     * @param entryDate     Service Center Code
     * @see Schema#sqlCreateCommunityGroup_Table()
     */
    public void addCommunityGroup(String cCode, String donorCode, String awardCode, String progCode, String grpCode,
                                  String grpName, String grpCatCode, String layR1Code, String layR2Code, String layR3Code, String srvCenterCode
            , String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(GROUP_CODE_COL, grpCode);
        values.put(GROUP_NAME_COL, grpName);
        values.put(GROUP_CAT_CODE_COL, grpCatCode);
        values.put(LAY_R1_CODE_COL, layR1Code);
        values.put(GRP_LAY_R2_LIST_CODE_COL, layR2Code);
        values.put(GRP_LAY_R3_LIST_CODE_COL, layR3Code);
        values.put(SERVICE_CENTER_CODE_COL, srvCenterCode);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        long i = db.insert(COMMUNITY_GROUP_TABLE, null, values);

        db.close();

    }

    /**
     * table Schema
     *
     * @param cCode      Country  Code
     * @param donorCode  donor Code
     * @param awardCode  award Code
     * @param progCode   program Code
     * @param grpCode    group Code
     * @param grpName    group Name
     * @param grpCatCode group Categories Code
     * @param layR1Code  LayR1 Code
     * @param layR2Code  LayR2 Code
     * @param layR3Code  LayR3 Code
     * @param entryBy    id of staff that modifies the data
     * @param entryDate  date time of modified the data
     * @see Schema#sqlCreateCommunityGroup_Table()
     */
    public void editCommunityGroup(String cCode, String donorCode, String awardCode, String progCode, String grpCode,
                                   String grpName, String grpCatCode, String layR1Code, String layR2Code, String layR3Code
            , String entryBy, String entryDate, String oldLayR1Code, String oldLayR2Code, String oldLayR3Code) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        String where = ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND " + ADM_PROG_CODE_COL + " = '" + progCode + "'"
                + " AND " + GROUP_CODE_COL + " = '" + grpCode + "'";
        /**
         * do not delete the below the program
         */


        values.put(GROUP_NAME_COL, grpName);
        values.put(GROUP_CAT_CODE_COL, grpCatCode);

        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(LAY_R1_CODE_COL, layR1Code);
        values.put(GRP_LAY_R2_LIST_CODE_COL, layR2Code);
        values.put(GRP_LAY_R3_LIST_CODE_COL, layR3Code);

        db.update(COMMUNITY_GROUP_TABLE, values, where, null);

        db.close();

    }


    /**
     * Group Details
     *
     * @param cCode              Country Code
     * @param donorCode          donor Code
     * @param awardCode          award Code
     * @param progCode           program Code
     * @param grpCode            Group Code
     * @param ogrCode            organization Code
     * @param staffCode          staff Code
     * @param landSizeUnder      land Size Under
     * @param irrigationSaysUsed iirigration SysUsed
     * @param fundSupport        fund Support
     * @param active             active
     * @param reapName           reap Name
     * @param reapPhone          reap Phone
     * @param formation          formation
     * @param typeOfGrp          type of Grp
     * @param status             status
     * @param entryBy            entry By
     * @param entryDate          entry Date
     * @param projecftNo         project No
     * @param projectTitle       ProjectTitle
     * @param layR1Code          grouplayR1ListCode
     * @param layR2Code          grouplayR2ListCode
     * @param layR3Code          grouplayR3ListCode
     * @see Schema#createTableCommunityGrpDetail() table Schema
     */

    public void addIntoGroupDetails(String cCode, String donorCode, String awardCode, String progCode, String grpCode, String ogrCode, String staffCode, String landSizeUnder, String irrigationSaysUsed, String fundSupport, String active, String reapName, String reapPhone, String formation, String typeOfGrp, String status, String entryBy, String entryDate, String projecftNo, String projectTitle, String layR1Code, String layR2Code, String layR3Code) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(GROUP_CODE_COL, grpCode);
        values.put(ORG_CODE_COL, ogrCode);
        values.put(LAY_R1_CODE_COL, layR1Code);
        values.put(GRP_LAY_R2_LIST_CODE_COL, layR2Code);
        values.put(GRP_LAY_R3_LIST_CODE_COL, layR3Code);
        values.put(STAFF_CODE_COL, staffCode);
        values.put(LAND_SIZE_UNDER_IRRIGATION_COL, landSizeUnder);
        values.put(IRRIGATION_SYSTEM_USED_COL, irrigationSaysUsed);
        values.put(FUND_SUPPORT_COL, fundSupport);
        values.put(ACTIVE_STATUS_COL, active);
        values.put(REP_NAME_COL, reapName);
        values.put(REP_PHONE_NUMBER_COL, reapPhone);
        values.put(FORMATION_DATE_COL, formation);
        values.put(TYPE_OF_GROUP, typeOfGrp);
        values.put(STATUS, status);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(PROJECT_NO_COL, projecftNo);
        values.put(PROJECT_TITLE, projectTitle);
        db.insert(COMMUNITY_GRP_DETAIL_TABLE, null, values);

        db.close();

    }

    public LayRCodes getLayRListFromCommunityORGroupDetails(String cCode, String donorCode, String awardCode, String progCode, String grpCode, String grpName, String tableName) {

        LayRCodes layRCodes = new LayRCodes();
        SQLiteDatabase db = this.getReadableDatabase();
        String criteria = "";
        /**
         * JODI COMMUNITY GROUP TABLE THEKE QUERY HOY TAHOLE
         * GROUP NAME MUST DITE HOBE
         * CODITION TA HOLO JODI GROUP NAME THAKE TAHOLE AND CONDITION ADD HOBE
         * ACCQURATE LAYRLIST ANAR JONNO
         */
        if (grpName.length() > 0)
            criteria = " AND " + GROUP_NAME_COL + " = '" + grpName + "' ";

        String sql = "Select "
                + "  " + LAY_R1_CODE_COL
                + " , " + GRP_LAY_R2_LIST_CODE_COL
                + " , " + GRP_LAY_R3_LIST_CODE_COL
                + " FROM " + tableName
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + ADM_PROG_CODE_COL + " = '" + progCode + "' "
                + " AND " + GROUP_CODE_COL + " = '" + grpCode + "' "
                + criteria;


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                layRCodes.setLayR1Code(cursor.getString(0));
                layRCodes.setLayR2Code(cursor.getString(1));
                layRCodes.setLayR3Code(cursor.getString(2));
            }
            cursor.close();
            db.close();
        }
        return layRCodes;


    }


    public LayRCodes getLayRListFromGroupDetails(String AdmCountryCode, String donorCode, String awardCode, String progCode, String grpCode) {

        return getLayRListFromCommunityORGroupDetails(AdmCountryCode, donorCode, awardCode, progCode, grpCode, "", COMMUNITY_GRP_DETAIL_TABLE);


    }

    /**
     * this method get the list of the community previous lay r 1 2 3 code if exits
     *
     * @param cCode     country Code
     * @param donorCode donor Code
     * @param awardCode award Code
     * @param progCode  Program Code
     * @param grpCode   Group Code
     * @param grpName   Group Name
     * @return Group Layer List
     */
    public LayRCodes getLayRListFromCommunityGroup(String cCode, String donorCode, String awardCode, String progCode, String grpCode, String grpName) {

        return getLayRListFromCommunityORGroupDetails(cCode, donorCode, awardCode, progCode, grpCode, grpName, COMMUNITY_GROUP_TABLE);


    }

    public void editIntoGroupDetails(String AdmCountryCode, String donorCode
            , String awardCode, String progCode, String grpCode
            , String orgCode, String staffCode, String landSizeUnder, String iirigrationSysUsed, String fundSuppot
            , String active, String reapName, String reapPhone, String formation, String typeOfGrp
            , String status, String entryBy, String entryDate, String projecftNo, String projectTitle
            , String layR1Code, String layR2Code, String layR3Code, LayRCodes oldLayR
    ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String extraCondition;
        if (oldLayR.getLayR1Code().length() > 0 && oldLayR.getLayR2Code().length() > 0 && oldLayR.getLayR3Code().length() > 0) {
            if (oldLayR.getLayR3Code().equals("-")) {

                extraCondition = " AND " + LAY_R1_CODE_COL + " = '" + oldLayR.getLayR1Code() + "'"
                        + " AND " + GRP_LAY_R2_LIST_CODE_COL + " = '" + oldLayR.getLayR2Code() + "'";
            } else {
                extraCondition = " AND " + LAY_R1_CODE_COL + " = '" + oldLayR.getLayR1Code() + "'"
                        + " AND " + GRP_LAY_R2_LIST_CODE_COL + " = '" + oldLayR.getLayR2Code() + "'"
                        + " AND " + GRP_LAY_R3_LIST_CODE_COL + " = '" + oldLayR.getLayR3Code() + "'";
            }

        } else {
            extraCondition = "";
        }
        String where = ADM_COUNTRY_CODE_COL + " = '" + AdmCountryCode + "' "
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + ADM_PROG_CODE_COL + " = '" + progCode + "' "
                + " AND " + GROUP_CODE_COL + " = '" + grpCode + "' "
                + extraCondition;

        values.put(ORG_CODE_COL, orgCode);
        values.put(STAFF_CODE_COL, staffCode);
        values.put(LAND_SIZE_UNDER_IRRIGATION_COL, landSizeUnder);
        values.put(IRRIGATION_SYSTEM_USED_COL, iirigrationSysUsed);
        values.put(FUND_SUPPORT_COL, fundSuppot);
        values.put(ACTIVE_STATUS_COL, active);
        values.put(REP_NAME_COL, reapName);
        values.put(REP_PHONE_NUMBER_COL, reapPhone);
        values.put(FORMATION_DATE_COL, formation);
        values.put(TYPE_OF_GROUP, typeOfGrp);
        values.put(STATUS, status);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(PROJECT_NO_COL, projecftNo);
        values.put(PROJECT_TITLE, projectTitle);
        values.put(LAY_R1_CODE_COL, layR1Code);
        values.put(GRP_LAY_R2_LIST_CODE_COL, layR2Code);
        values.put(GRP_LAY_R3_LIST_CODE_COL, layR3Code);


        int i = db.update(COMMUNITY_GRP_DETAIL_TABLE, values, where, null);
//        Log.d("Update", "update " + i + " no of row of community detaidls ");
    }


    public void addInRegNAgrTableFromOnline(AGR_DataModel onlineData, String invc, String nasfm, String cu, String other, int goat, int chicken, int pegion, int other_sp) {
        onlineData.setEntryBy("");
        onlineData.setEntryDate("");
        //TODO: FIX IT LATTER
        insertDataInto_RegNAgrTable(onlineData, invc, nasfm, cu, other, goat, chicken, pegion, other_sp);

    }


    public void insertDataInto_RegNAgrTable(AGR_DataModel data, String invc, String nasfm, String cu, String other, int goat, int chicken, int pegion, int other_sp) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(ADM_COUNTRY_CODE_COL, data.getCountryCode());
        values.put(LAY_R1_LIST_CODE_COL, data.getDistrictCode());
        values.put(LAY_R2_LIST_CODE_COL, data.getUpazillaCode());
        values.put(LAY_R3_LIST_CODE_COL, data.getUnitCode());
        values.put(LAY_R4_LIST_CODE_COL, data.getVillageCode());
        values.put(HHID_COL, data.getHhId());
        values.put(MEM_ID_COL, data.getHhMemId());
        values.put(REG_N_DAT_COL, data.getRegnDate());
        values.put(ELDERLY_YN_COL, data.getElderleyYN());
        values.put(LAND_SIZE_COL, data.getLandSize());
        values.put(DEPEND_ON_GANYU_COL, data.getDepenonGanyu());
        values.put(WILLINGNESS_COL, data.getWillingness());
        values.put(WINTER_CULTIVATION_COL, data.getWinterCultivation());
        values.put(VULNERABLE_HH_COL, data.getVulnerableHh());
        values.put(PLANTING_VALUE_CHAIN_CROP_COL, data.getPlantingVcrop());
        values.put(ENTRY_BY, data.getEntryBy());
        values.put(ENTRY_DATE, data.getEntryDate());
        values.put(AG_INVC_COL, invc);
        values.put(AG_NASFAM_COL, nasfm);
        values.put(AG_CU_COL, cu);
        values.put(AG_OTHER_COL, other);
        values.put(AG_L_S_GOAT_COL, goat);
        values.put(AG_L_S_CHICKEN_COL, chicken);
        values.put(AG_L_S_PIGION_COL, pegion);
        values.put(AG_L_S_OTHER_COL, other_sp);


        db.insert(REG_N_AGR_TABLE, null, values);

        db.close();
    }/*REG_N_AGR_TABLE*/

    /* this method load Service  Criteria for Service SumRegLay4TotalHHRecords Criteria  or Distribution */
    public List<SummaryCriteriaModel> getServiceSummaryCriteriaList(String cCode, String donorCode, String awardCord, String opMCode, String progCode, String distFlag) {
        List<SummaryCriteriaModel> criteriaList = new ArrayList<SummaryCriteriaModel>();//List<SummaryCriteriaModel>();

        SQLiteDatabase db = this.getReadableDatabase();//Database();
        String selectQuery;
        // get the dist count
   /*     if (srvORDstFlag.equals(KEY.DIST_FLAG)) {
            selectQuery = SQLiteQuery.get_DistCriteriaList_SelectQuery(cCode, donorCode, awardCord, opMCode, progCode);
        } else*/
        selectQuery = SQLiteQuery.get_SrvCriteriaList_SelectQuery(cCode, donorCode, awardCord, opMCode, progCode, distFlag);


        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SummaryCriteriaModel crite = new SummaryCriteriaModel();
                crite.setCriteria_name(cursor.getString(cursor.getColumnIndex("Criteria")));//cursor
                crite.setCriteria_id(cursor.getString(cursor.getColumnIndex("IdCriteria")));
                crite.setRecord(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Count"))));
                criteriaList.add(crite);
//                Log.d(TAG, " Service summary Criteria : " + cursor.getString(0) + ",IdCriteria : " + cursor.getString(1) + ", ServiceCount : " + cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return criteriaList;
    }


    public List<SummaryCriteriaModel> getDistributionSummaryCriteriaList(String cCode, String donorCode, String awardCord, String opMCode, String progCode, String distFlag) {
        List<SummaryCriteriaModel> criteriaList = new ArrayList<SummaryCriteriaModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery;

        selectQuery = SQLiteQuery.get_DistCriteriaList_SelectQuery(cCode, donorCode, awardCord, opMCode, progCode, distFlag);


        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SummaryCriteriaModel crite = new SummaryCriteriaModel();
                crite.setCriteria_name(cursor.getString(cursor.getColumnIndex("Criteria")));
                crite.setCriteria_id(cursor.getString(cursor.getColumnIndex("IdCriteria")));

                crite.setPlan(Integer.parseInt(cursor.getString(cursor.getColumnIndex("plan"))));
                crite.setRecord(Integer.parseInt(cursor.getString(cursor.getColumnIndex("receive"))));
                criteriaList.add(crite);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return criteriaList;
    }


    /* this method load Service Extented item summary   */
    public List<SummaryCriteriaModel> getSrvORDistExtendedItemSummaryList(String cCode, String donorCode, String awardCord, String opMCode, String progCode, String srvDistFlag) {
        List<SummaryCriteriaModel> criteriaList = new ArrayList<SummaryCriteriaModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery;
        if (srvDistFlag.equals(KEY.DIST_FLAG)) {
            selectQuery = SQLiteQuery.getDistExtendedItemSummaryList_SelectQuery(cCode, donorCode, awardCord, opMCode, progCode);
        } else {
            selectQuery = SQLiteQuery.getSrvExtendedItemSummaryList_SelectQuery(cCode, donorCode, awardCord, opMCode, progCode);
        }

        Cursor cursor = db.rawQuery(selectQuery, null);
        //  SummaryCriteriaModel crite = new SummaryCriteriaModel();
        if (cursor.moveToFirst()) {
            do {
                SummaryCriteriaModel crite = new SummaryCriteriaModel();
                crite.setCriteria_name(cursor.getString(cursor.getColumnIndex("item")));//cursor
                crite.setCriteria_id(cursor.getString(cursor.getColumnIndex("voucherID")));
                crite.setRecord(Integer.parseInt(cursor.getString(cursor.getColumnIndex("unitCount"))));
                criteriaList.add(crite);
//                Log.d(TAG, " Service Item summary item : " + cursor.getString(0) + ",voucherID : " + cursor.getString(1) + ", unitCount : " + cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return criteriaList;
    }

    /**
     * added by Faisal Mohammad
     *
     */
    /**
     * get data from registration & memeber & reg Nass srv table
     *
     * @param cCode     Country
     * @param disCode   District Code LayR1
     * @param upCode    upCode
     * @param unCode    Unite Code
     * @param vCode     Village Code
     * @param donorCode Donor Code
     * @param awardCode awardCode
     * @param progCode  program Code
     * @param servCode  service Code
     * @param memberId  Sarch id
     * @return AssignDataModel list
     */
    public ArrayList<AssignDataModel> getSingleMemberForAssign(String cCode, String disCode, String upCode, String unCode, String vCode, String hhid, String memberId, String donorCode, String awardCode, String progCode, String servCode) {

        ArrayList<AssignDataModel> listAsignPeople = new ArrayList<AssignDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = SQLiteQuery.getSingleMemberForAssign_sql(cCode, disCode, upCode, unCode, vCode, hhid, memberId, donorCode, awardCode, progCode, servCode);


        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                AssignDataModel assignPerson = new AssignDataModel();

                assignPerson.setHh_id(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                assignPerson.setMemId(cursor.getString(cursor.getColumnIndex(HH_MEM_ID)));
                assignPerson.setNewId(cursor.getString(cursor.getColumnIndex("newId")));
                assignPerson.setHh_mm_name(cursor.getString(cursor.getColumnIndex("memName")));
                assignPerson.setMember_age(cursor.getString(cursor.getColumnIndex(MEM_AGE)));
                assignPerson.setMember_sex(cursor.getString(cursor.getColumnIndex(SEX_COL)));
                assignPerson.setHh_relation(cursor.getString(cursor.getColumnIndex("HHRelation")));
                assignPerson.setAssignYN(cursor.getString(cursor.getColumnIndex("Assign")));

                assignPerson.setC_code(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                assignPerson.setDistrictCode(cursor.getString(cursor.getColumnIndex(LAY_R1_LIST_CODE_COL)));
                assignPerson.setUpazillaCode(cursor.getString(cursor.getColumnIndex(LAY_R2_LIST_CODE_COL)));
                assignPerson.setUnitCode(cursor.getString(cursor.getColumnIndex(LAY_R3_LIST_CODE_COL)));
                assignPerson.setVillageCode(cursor.getString(cursor.getColumnIndex(LAY_R4_LIST_CODE_COL)));
                assignPerson.setHh_name(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_HH_HEAD_NAME)));


                assignPerson.setAssign_criteria(cursor.getString(cursor.getColumnIndex("AssignCriteria")));
                assignPerson.setGroupCode(cursor.getString(cursor.getColumnIndex("grpCode")));
                assignPerson.setGroupName(cursor.getString(cursor.getColumnIndex("grpName")));
                assignPerson.setGroupCatCode(cursor.getString(cursor.getColumnIndex("catCode")));
                assignPerson.setGroupCatName(cursor.getString(cursor.getColumnIndex("catName")));
                assignPerson.setActiveCode(cursor.getString(cursor.getColumnIndex("activeCode")));

                //   Log.d(TAG, " " + cursor.getString(1) + " , " + cursor.getString(2) + " , " + cursor.getString(14) + " , " + cursor.getString(15));
                listAsignPeople.add(assignPerson);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listAsignPeople;

    }


    /**
     * added by Faisal Mohammad
     *
     */
    /**
     * get data from registration & memeber & reg Nass srv table
     *
     * @param cCode      Country
     * @param disCode    District Cod e
     * @param upCode     upCode
     * @param unCode     Unite Code
     * @param vCode      Village Code
     * @param donorCode  Donor Code
     * @param awardCode  awardCode
     * @param progCode   program Code
     * @param servCode   service Code
     * @param mmSearchId member Sarch id
     * @return AssignDataModel list
     */
    public ArrayList<AssignDataModel> getListForAssign(String cCode, String disCode, String upCode, String unCode, String vCode, String donorCode, String awardCode, String progCode, String servCode, String mmSearchId) {

        ArrayList<AssignDataModel> listAsignPeople = new ArrayList<AssignDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = SQLiteQuery.getAssignListViewSelectQuery(cCode, disCode, upCode, unCode, vCode, donorCode, awardCode, progCode, servCode, mmSearchId);


        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                AssignDataModel assignPerson = new AssignDataModel();

                assignPerson.setHh_id(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                assignPerson.setMemId(cursor.getString(cursor.getColumnIndex(HH_MEM_ID)));
                assignPerson.setNewId(cursor.getString(cursor.getColumnIndex("newId")));
                assignPerson.setHh_mm_name(cursor.getString(cursor.getColumnIndex("memName")));
                assignPerson.setMember_age(cursor.getString(cursor.getColumnIndex(MEM_AGE)));
                assignPerson.setMember_sex(cursor.getString(cursor.getColumnIndex(SEX_COL)));
                assignPerson.setHh_relation(cursor.getString(cursor.getColumnIndex("HHRelation")));
                assignPerson.setAssignYN(cursor.getString(cursor.getColumnIndex("Assign")));

                assignPerson.setC_code(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                assignPerson.setDistrictCode(cursor.getString(cursor.getColumnIndex(DISTRICT_NAME_COL)));
                assignPerson.setUpazillaCode(cursor.getString(cursor.getColumnIndex(UPZILLA_NAME_COL)));
                assignPerson.setUnitCode(cursor.getString(cursor.getColumnIndex(LAY_R3_LIST_NAME)));
                assignPerson.setVillageCode(cursor.getString(cursor.getColumnIndex(LAY_R4_LIST_NAME_COL)));
                assignPerson.setHh_name(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_HH_HEAD_NAME)));


                assignPerson.setAssign_criteria(cursor.getString(cursor.getColumnIndex("AssignCriteria")));

                //   Log.d(TAG, " " + cursor.getString(1) + " , " + cursor.getString(2) + " , " + cursor.getString(14) + " , " + cursor.getString(15));
                listAsignPeople.add(assignPerson);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listAsignPeople;


    }


    public ArrayList<TrainigActivBeneficiaryDataModel> getMemberList(final String cCode, final String disCode, final String upCode, final String unCode, final String vCode, final String memberIdOrName, int number, boolean traing) {


        ArrayList<TrainigActivBeneficiaryDataModel> listAsignPeople = new ArrayList<TrainigActivBeneficiaryDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "";
        if (isAlpha(memberIdOrName)) {
            sql = SQLiteQuery.getMemberListView_searchBy_Name_sql(cCode, disCode, upCode, unCode, vCode, memberIdOrName, number);

        } else {
            sql = SQLiteQuery.getMemberListView_searchBy_ID_sql(cCode, disCode, upCode, unCode, vCode, memberIdOrName, number);
        }


        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                TrainigActivBeneficiaryDataModel assignPerson = new TrainigActivBeneficiaryDataModel();

                assignPerson.setcCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                assignPerson.setLayR4Name(cursor.getString(cursor.getColumnIndex("layR4Name")));
                assignPerson.setAddressName(cursor.getString(cursor.getColumnIndex("address")));
                assignPerson.setNewId(cursor.getString(cursor.getColumnIndex("newId")));
                assignPerson.setHh_name(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_HH_HEAD_NAME)));/** house hold name */
                assignPerson.setHh_mm_name(cursor.getString(cursor.getColumnIndex("memName")));
                assignPerson.setMember_age(cursor.getString(cursor.getColumnIndex(MEM_AGE)));
                assignPerson.setMember_sex(cursor.getString(cursor.getColumnIndex(SEX_COL)));

                listAsignPeople.add(assignPerson);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listAsignPeople;


    }
//    public ArrayList<TrainigActivBeneficiaryDataModel> getEligibleTrainingAcitMemList(String cCode,String layR1Code,String layR2Code,String layR3Code,String layR4Code, String mmSearchId, int start) {
//
//        ArrayList<TrainigActivBeneficiaryDataModel> list = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String sql = SQLiteQuery.getEligibleTrainingAcitMemList_sql(cCode, layR1Code, layR2Code, layR3Code, layR4Code, mmSearchId,start);
//
//
//        Cursor cursor = db.rawQuery(sql, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                TrainigActivBeneficiaryDataModel dataModel = new TrainigActivBeneficiaryDataModel();
//
//
//                dataModel.setNewId(cursor.getString(cursor.getColumnIndex("newId")));
//                dataModel.setHh_mm_name(cursor.getString(cursor.getColumnIndex("memName")));
//                dataModel.setMember_age(cursor.getString(cursor.getColumnIndex(MEM_AGE)));
//                dataModel.setMember_sex(cursor.getString(cursor.getColumnIndex(SEX_COL)));
//
//                dataModel.setHh_name(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_HH_HEAD_NAME)));
//                dataModel.setLayR4Name(cursor.getString(cursor.getColumnIndex("lay4Name")));
//
//                list.add(dataModel);
//
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//
//        return list;
//
//
//    }

    private boolean isAlpha(String nameORid) {
        return nameORid.matches("[a-zA-Z]+");
    }

    /**
     * for Member Search Pag e
     *
     * @param cCode   Country Code
     * @param disCode District Code =Layer1Code
     * @param upCode  Upzella Code=Layer2Code
     * @param unCode  Union Code= Layer3Code
     * @param vCode   Village Code =Layer4Code
     * @return member list
     */

    public ArrayList<AssignDataModel> getMemberList(final String cCode, final String disCode, final String upCode, final String unCode, final String vCode, final String memberIdOrName, int number) {


        ArrayList<AssignDataModel> listAsignPeople = new ArrayList<AssignDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "";
        if (isAlpha(memberIdOrName)) {
            sql = SQLiteQuery.getMemberListView_searchBy_Name_sql(cCode, disCode, upCode, unCode, vCode, memberIdOrName, number);

        } else {
            sql = SQLiteQuery.getMemberListView_searchBy_ID_sql(cCode, disCode, upCode, unCode, vCode, memberIdOrName, number);
        }


        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                AssignDataModel assignPerson = new AssignDataModel();

                assignPerson.setHh_id(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                assignPerson.setMemId(cursor.getString(cursor.getColumnIndex(HH_MEM_ID)));
                assignPerson.setNewId(cursor.getString(cursor.getColumnIndex("newId")));
                assignPerson.setHh_mm_name(cursor.getString(cursor.getColumnIndex("memName")));
                assignPerson.setMember_age(cursor.getString(cursor.getColumnIndex(MEM_AGE)));

                assignPerson.setMember_sex(cursor.getString(cursor.getColumnIndex(SEX_COL)));
                assignPerson.setHh_relation(cursor.getString(cursor.getColumnIndex("HHRelation")));
                assignPerson.setVillageName(cursor.getString(cursor.getColumnIndex("layR4Name")));
                assignPerson.setAddressName(cursor.getString(cursor.getColumnIndex("address")));


                assignPerson.setC_code(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                assignPerson.setDistrictCode(cursor.getString(cursor.getColumnIndex(LAY_R1_LIST_CODE_COL)));
                assignPerson.setUpazillaCode(cursor.getString(cursor.getColumnIndex(LAY_R2_LIST_CODE_COL)));
                assignPerson.setUnitCode(cursor.getString(cursor.getColumnIndex(LAY_R3_LIST_CODE_COL)));
                assignPerson.setVillageCode(cursor.getString(cursor.getColumnIndex(LAY_R4_LIST_CODE_COL)));
                assignPerson.setHh_name(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_HH_HEAD_NAME)));/** house hold name */


                listAsignPeople.add(assignPerson);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listAsignPeople;


    }


    public int getMemberCount(final String cCode, final String disCode, final String upCode, final String unCode, final String vCode, final String memberIdOrName, int number) {

        int count = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "";
        sql = SQLiteQuery.getMemberCount_sql(cCode, disCode, upCode, unCode, vCode, memberIdOrName, number);


        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null && cursor.moveToFirst()) {
            count = Integer.parseInt(cursor.getString(cursor.getColumnIndex("cont")));
            cursor.close();
        }


        db.close();

        return count;


    }

    public int getNextDTResponseSequence(String dtBasic, String cCode, String donorCode, String awardCode, String prgCode, String entryBy) {
        int nextDTRSeq;
        SQLiteDatabase db = this.getReadableDatabase();
        int tem = -1;
        String sql = " Select " + DT_R_SEQ_COL + " from " + DT_RESPONSE_TABLE + " " +
                " WHERE " +
                " " + DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + prgCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + entryBy + "' " +
                " GROUP By " + DT_R_SEQ_COL + " " +
                " order by " + DT_R_SEQ_COL + " DESC " +
                " limit 1";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                tem = cursor.getInt(cursor.getColumnIndex(DT_R_SEQ_COL));
            }
            cursor.close();
            db.close();

        }

        if (tem > 0) {
            nextDTRSeq = tem + 1;
        } else
            nextDTRSeq = 1;
        return nextDTRSeq;

    }

    public int getSurveyNumber(String dtBasic, String cCode, String donorCode, String awardCode, String prgCode, String entryBy) {
        int surveyNumber;
        ArrayList<Integer> surveyList = getSurveyList(dtBasic, cCode, donorCode, awardCode, prgCode, entryBy);


        if (surveyList.size() > 0) {
            surveyNumber = UtilClass.getMaxNumberFromList(surveyList.toArray(new Integer[surveyList.size()]));
        } else
            surveyNumber = 1;
        return surveyNumber;

    }

    public ArrayList<Integer> getSurveyList(String dtBasic, String cCode, String donorCode, String awardCode, String prgCode, String entryBy) {
        SQLiteDatabase db = this.getReadableDatabase();
        int tem = -1;
        String sql = " Select DISTINCT " + DT_SURVEY_NUM + " from " + DT_SURVEY_TABLE + " " +
                " WHERE " + DT_BASIC_COL + " = '" + dtBasic + "' ";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Integer> surveyList = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    tem = cursor.getInt(cursor.getColumnIndex(DT_SURVEY_NUM));
                    if (tem >= 0) {
                        surveyList.add(tem);
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

        }
        return surveyList;

    }

    public void addIntoDtEnuTable(String dtStfCoe, String admCountryCode, String dtBasicCol, String dtBtnSave, String entryBy,
                                  String usaEntryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_STF_CODE_COL, dtStfCoe);
        values.put(DT_ADM_COUNTRY_CODE_COL, admCountryCode);
        values.put(DT_BASIC_COL, dtBasicCol);
        values.put(DT_BTN_SAVE_COL, dtBtnSave);
        values.put(DT_ENTRY_BY_COL, entryBy);
        values.put(DT_USA_ENTRY_DATE_COL, usaEntryDate);

        db.insert(DT_ENU_TABLE, null, values);
        db.close();
    }

    public boolean checkDTBasic(String dtBasic, String stfID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + DT_ENU_TABLE +
                " WHERE " + DT_STF_CODE_COL + "= '" + stfID + "'";


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String basicCol = cursor.getString(cursor.getColumnIndex(DT_BASIC_COL));
                    if (dtBasic.equalsIgnoreCase(basicCol)) {
                        return true;
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        }
        return false;
    }

    /**
     * this method get single  question  of Dynamic table  with respect to Dt Basic Code and
     * sequence index (ascending order ) one by one .
     *
     * @param dtBasicCode Dynamic basic Code
     * @param index       question index
     * @return {@link DTQTableDataModel }
     */
    public DTQTableDataModel getSingleDynamicQuestion(String dtBasicCode, int index) {
        DTQTableDataModel singleQus = new DTQTableDataModel();

        SQLiteDatabase db = this.getReadableDatabase();

        // this query didn't maintain the sequence
        String sql = SQLiteQuery.getSingleDynamicQuestion_sql(dtBasicCode, index);


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                singleQus.setDtBasicCode(cursor.getString(0));
                singleQus.setDtQCode(cursor.getString(1));
                singleQus.setqText(cursor.getString(2));
                singleQus.setqResModeCode(cursor.getString(3));
                singleQus.setqSeq(cursor.getString(4));
                singleQus.setAllowNullFlag(cursor.getString(5));
                singleQus.setLup_TableName(cursor.getString(6));
            }
            cursor.close();
            db.close();
        }
        return singleQus;
    }

    public DTQTableDataModel getSingleDynamicQuestion(String dtBasicCode, String dtqCode) {
        DTQTableDataModel singleQus = new DTQTableDataModel();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + DTQ_TABLE +
                " WHERE " + DT_BASIC_COL + "= '" + dtBasicCode + "'" +
                " AND " + DTQ_CODE_COL + "= '" + dtqCode + "'";


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                singleQus.setDtBasicCode(cursor.getString(0));
                singleQus.setDtQCode(cursor.getString(1));
                singleQus.setqText(cursor.getString(2));
                singleQus.setqResModeCode(cursor.getString(3));
                singleQus.setqSeq(cursor.getString(4));
                singleQus.setAllowNullFlag(cursor.getString(5));
                singleQus.setLup_TableName(cursor.getString(6));
            }
            cursor.close();
            db.close();
        }
        return singleQus;
    }


    /**
     * invoking
     *
     * @param qResMode question Response Mode  ques's ans type
     * @return ans repose mode
     * @see DTResponseRecordingActivity#loadDT_QResMode(String)
     */

    public DTQResModeDataModel getDT_QResMode(String qResMode) {
        DTQResModeDataModel responseMode = new DTQResModeDataModel();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + DTQRES_MODE_TABLE +
                " WHERE " + QRES_MODE_COL + "= '" + qResMode + "'";


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                responseMode.setDtQResMode(cursor.getString(0));
                responseMode.setDtQResLupText(cursor.getString(1));
                responseMode.setDtDataType(cursor.getString(2).trim());
                responseMode.setDtLookUpUDFName(cursor.getString(3));
                responseMode.setDtResponseValueControl(cursor.getString(4).trim());

            }
            cursor.close();
            db.close();
        }
/*        Log.d("responseTest", " ResMode:" + responseMode.getDtQResMode()
                + " \tResponseValueControl" + responseMode.getDtResponseValueControl()
                + "  setDtQResLupText:"
                + responseMode.getDtQResLupText());*/
        return responseMode;
    }

    /**
     * <p>The DTA Table store the Default Value of All Dynamic View's default value </p>
     * invoke in {@link DTResponseRecordingActivity#displayQuestion(DTQTableDataModel)}
     *
     * @param dtBasic Basic Code
     * @param dtQCode Question Code
     * @return list of the default View's answer
     */

    public List<DT_ATableDataModel> getDTA_Table(String dtBasic, String dtQCode) {

        List<DT_ATableDataModel> listDTA = new ArrayList<DT_ATableDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = SQLiteQuery.getDTA_Table_sql(dtBasic, dtQCode);


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                DT_ATableDataModel dta = new DT_ATableDataModel();

                dta.setDTBasic(cursor.getString(0));
                dta.setDt_QCode(cursor.getString(1));
                dta.setDt_ACode(cursor.getString(2));
                dta.setDt_ALabel(cursor.getString(3));
                dta.setDt_AValue(cursor.getString(4));
                dta.setDt_Seq(cursor.getString(5));
                dta.setDt_AShort(cursor.getString(6));
                dta.setDt_ScoreCode(cursor.getString(7));
                dta.setDt_SkipDTQCode(cursor.getString(8));
                dta.setDt_ACompareCode(cursor.getString(9));
                dta.setShowHide(cursor.getString(10));
                dta.setMaxValue(cursor.getString(11));
                dta.setMinValue(cursor.getString(12));
                dta.setDataType(cursor.getString(13));
                dta.setMarkOnGrid(cursor.getString(14));

                listDTA.add(dta);


            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }


        return listDTA;

    }

    public ArrayList<DTQTableDataModel> getDynamicQuestionList(String dtBasicCode) {
        ArrayList<DTQTableDataModel> list = new ArrayList<DTQTableDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + DTQ_TABLE +
                " WHERE " + DT_BASIC_COL + "= '" + dtBasicCode + "'";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                DTQTableDataModel data = new DTQTableDataModel();

                data.setDtBasicCode(cursor.getString(0));
                data.setDtQCode(cursor.getString(1));
                data.setqText(cursor.getString(2));
                data.setqResModeCode(cursor.getString(3));
                data.setqSeq(cursor.getString(4));
                data.setAllowNullFlag(cursor.getString(5));

                list.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;

    }

    /**
     * this method get the list of Dynamic Data or Survey Subject  index
     *
     * @param cCode         Country Code
     * @param dtTitleSearch Search Key
     * @return DT index list
     */

    public ArrayList<AssignDataModel.DynamicDataIndexDataModel> getDynamicTableIndexList(final String cCode, String dtTitleSearch, final String staffId) {

        ArrayList<AssignDataModel.DynamicDataIndexDataModel> list = new ArrayList<AssignDataModel.DynamicDataIndexDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = SQLiteQuery.getDynamicTableIndexList_sql(cCode, dtTitleSearch, staffId);


        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                AssignDataModel.DynamicDataIndexDataModel data = new AssignDataModel.DynamicDataIndexDataModel();
                data.setDtTittle(cursor.getString(0));
                data.setDtBasicCode(cursor.getString(1));
                data.setAwardName(cursor.getString(2));
                data.setAwardCode(cursor.getString(3));
                data.setProgramName(cursor.getString(4));
                data.setProgramCode(cursor.getString(5));

                data.setPrgActivityTitle(cursor.getString(6));
                data.setcCode(cursor.getString(7));
                data.setOpMode(cursor.getString(8));
                data.setDonorCode(cursor.getString(9));
                data.setProgramActivityCode(cursor.getString(10));
                data.setDtShortName(cursor.getString(11));


                list.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;


    }


    public ArrayList<TrainingNActivityIndexDataModel> getTrainingActivityIndexList(final String cCode, final String eventTitleSearch) {

        ArrayList<TrainingNActivityIndexDataModel> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = SQLiteQuery.getTrainingActivityIndexList(cCode, eventTitleSearch);

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                TrainingNActivityIndexDataModel data = new TrainingNActivityIndexDataModel();
                data.setcCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                data.setEventCode(cursor.getString(cursor.getColumnIndex(EVENT_CODE_COL)));
                data.setEventTittle(cursor.getString(cursor.getColumnIndex(EVENT_NAME_COL)));
                data.setDonorCode(cursor.getString(cursor.getColumnIndex(ADM_DONOR_CODE_COL)));
                data.setAwardCode(cursor.getString(cursor.getColumnIndex(ADM_AWARD_CODE_COL)));
                data.setTaGroupCode(cursor.getString(cursor.getColumnIndex(TA_GROUP_COL)));
                data.setTaSubGroupCode(cursor.getString(cursor.getColumnIndex(TA_SUB_GROUP_COL)));

                data.setTaOrgCode(cursor.getString(cursor.getColumnIndex(PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL)));
                data.setStartDate(cursor.getString(cursor.getColumnIndex(START_DATE_COL)));
                data.setEndDate(cursor.getString(cursor.getColumnIndex(END_DATE_COL)));
                data.setVenueName(cursor.getString(cursor.getColumnIndex(VENUE_NAME_COL)));
                data.setAddressName(cursor.getString(cursor.getColumnIndex(VENUE_ADDRESS_COL)));
                data.setActiveStatus(cursor.getString(cursor.getColumnIndex(ACTIVE_COL)));
                data.setTotalDays(cursor.getString(cursor.getColumnIndex(TOTAL_DAYS_COL)));
                data.setHourPerDay(cursor.getString(cursor.getColumnIndex(HOURS_PER_DAY_COL)));


                list.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;


    }

    public ArrayList<CommunityGroupDataModel> getCommunityGroupList(final String cCode, final String donorCode, final String awardCode, final String progCode, final String groupName) {

        ArrayList<CommunityGroupDataModel> groupList = new ArrayList<CommunityGroupDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = SQLiteQuery.getCommunityGroupList_sql(cCode, donorCode, awardCode, progCode, groupName);


        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                CommunityGroupDataModel data = new CommunityGroupDataModel();

                data.setCommunityGroupName(cursor.getString(cursor.getColumnIndex(GROUP_NAME_COL)));
                data.setCommuCategoriesCode(cursor.getString(cursor.getColumnIndex(GROUP_CAT_CODE_COL)));
                data.setCommuCategoriesShortName(cursor.getString(cursor.getColumnIndex(GROUP_CAT_SHORT_NAME_COL)));
                data.setProgramShortName(cursor.getString(cursor.getColumnIndex(PROGRAM_SHORT_NAME_COL)));
                data.setCommunityGroupCode(cursor.getString(cursor.getColumnIndex(GROUP_CODE_COL)));
                data.setCommuCategoriesName(cursor.getString(cursor.getColumnIndex(GROUP_CAT_NAME_COL)));
                data.setAwardName(cursor.getString(cursor.getColumnIndex("awardName")));
                data.setAwardCode(cursor.getString(cursor.getColumnIndex(ADM_AWARD_CODE_COL)));
                data.setProgramName(cursor.getString(cursor.getColumnIndex(ADM_PROGRAM_MASTER_PROGRAM_NAME_COL)));
                data.setProgramCode(cursor.getString(cursor.getColumnIndex(ADM_PROG_CODE_COL)));
                data.setLayr1Code(cursor.getString(cursor.getColumnIndex(LAY_R1_LIST_CODE_COL)));
                data.setLayr2Code(cursor.getString(cursor.getColumnIndex(LAY_R2_LIST_CODE_COL)));
                data.setLayr3Code(cursor.getString(cursor.getColumnIndex(LAY_R3_LIST_CODE_COL)));
                data.setLayr3Name(cursor.getString(cursor.getColumnIndex(LAY_R3_LIST_NAME)));
                data.setOrgonizationCode(cursor.getString(cursor.getColumnIndex(ORG_CODE_COL)));
                data.setOrgonizationName(cursor.getString(cursor.getColumnIndex(ORGANIZATION_NAME)));
                data.setStaffCode(cursor.getString(cursor.getColumnIndex(STAFF_CODE_COL)));
                data.setStaffName(cursor.getString(cursor.getColumnIndex(STAFF_NAME_COL)));
                data.setLandSizeUnderIrrigation(cursor.getString(cursor.getColumnIndex(LAND_SIZE_UNDER_IRRIGATION_COL)));
                data.setIrrigationSystemUsed(cursor.getString(cursor.getColumnIndex(IRRIGATION_SYSTEM_USED_COL)));
                data.setFundSupport(cursor.getString(cursor.getColumnIndex(FUND_SUPPORT_COL)));
                data.setActive(cursor.getString(cursor.getColumnIndex(ACTIVE_STATUS_COL)));
                data.setRepName(cursor.getString(cursor.getColumnIndex(REP_NAME_COL)));
                data.setRepPhoneNo(cursor.getString(cursor.getColumnIndex(REP_PHONE_NUMBER_COL)));
                data.setFormation(cursor.getString(cursor.getColumnIndex(FORMATION_DATE_COL)));
                data.setTypeOfGroup(cursor.getString(cursor.getColumnIndex(TYPE_OF_GROUP)));
                data.setStatus(cursor.getString(cursor.getColumnIndex(STATUS)));
                data.setProjectNo(cursor.getString(cursor.getColumnIndex(PROJECT_NO_COL)));
                data.setProjectTitle(cursor.getString(cursor.getColumnIndex(PROJECT_TITLE)));

                groupList.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return groupList;


    }

    /**
     * @param cCode     countryCode
     * @param donarCode donarCode
     * @param awardCode awardCode
     * @param orgCode   organizationCode
     * @param primeyn   primeyn
     * @param subyn     subyn
     * @param techyn    techyn
     * @param impyn     impyn
     * @param logyn     logyn
     * @param othyn     othyn
     * @see Schema#createTableProgOrgNRole()   the table Schema
     */
    public void insertIntoProgOrgNRole(String cCode, String donarCode, String awardCode, String orgCode, String primeyn, String subyn, String techyn, String impyn, String logyn, String othyn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donarCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ORG_N_CODE_COL, orgCode);
        values.put(PRIME_Y_N_COL, primeyn);
        values.put(SUB_Y_N_COL, subyn);
        values.put(TECH_Y_N_COL, techyn);
        values.put(IMP_Y_N_COL, impyn);
        values.put(LONG_Y_N_COL, logyn);
        values.put(OTH_Y_N_COL, othyn);
        db.insert(PROGRAM_ORGANIZATION_ROLE_TABLE, null, values);


    }


    public long insertIntoProgOrgN(String OrgNCode, String orgNName, String orgNShortName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROGRAM_ORGANIZATION_NAME_TABLE_ORG_CODE_COL, OrgNCode);
        values.put(ORGANIZATION_NAME, orgNName);
        values.put(ORGANIZATION_SHORT_NAME, orgNShortName);
        long id = db.insert(PROGRAM_ORGANIZATION_NAME_TABLE, null, values);
//        Log.d(TAG, "NEW Insert into " + PROGRAM_ORGANIZATION_NAME_TABLE + " Table: " + id);
        return id;
    }


    /**
     * @param donorCode donorCode
     * @param awardCode awardCode
     * @param progCode  ProgramCode
     * @return yes or no
     */

    public String get_ProgramMultipleSrv(String donorCode, String awardCode, String progCode) {
        String programMultipleSrvFlag = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String query = SQLiteQuery.get_ProgramMultipleSrv_SelectQuery(donorCode, awardCode, progCode);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                programMultipleSrvFlag = cursor.getString(cursor.getColumnIndex(MULTIPLE_SERVICE_FLAG_COL));

            }
            cursor.close();
            db.close();
        }
        return programMultipleSrvFlag;

    }


    // update gps location
    public void updateGpsLocation(String cCode, String groupCode, String subGroupCode, String locationCode, String lat, String lng, String entryBy, String entryDate) {

        SQLiteDatabase db = this.getWritableDatabase();
        String where = ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + GROUP_CODE_COL + " = '" + groupCode + "' " +
                " AND " + SUB_GROUP_CODE_COL + " = '" + subGroupCode + "' " +
                " AND " + LOCATION_CODE_COL + " = '" + locationCode + "'; ";
        ContentValues values = new ContentValues();
        values.put(LATITUDE_COL, lat);
        values.put(LONGITUDE_COL, lng);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);


        db.update(GPS_LOCATION_TABLE, values, where, null);

        db.close();
        SQLServerSyntaxGenerator sqlSyntax = new SQLServerSyntaxGenerator();
        sqlSyntax.setAdmCountryCode(cCode);
        sqlSyntax.setGrpCode(groupCode);
        sqlSyntax.setSubGrpCode(subGroupCode);
        sqlSyntax.setLocationCode(locationCode);
        sqlSyntax.setLatd(lat);
        sqlSyntax.setLong(lng);
        sqlSyntax.setEntryBy(entryBy);
        sqlSyntax.setEntryDate(entryDate);

        insertIntoUploadTable(sqlSyntax.updateGPS_GeoLocationTable());
    }

    /**
     * @param data AssignDataModel class's object
     * @
     */
    public boolean ifExistsInRegNAssProgSrv(AssignDataModel data) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean flag;
        String sql = SQLiteQuery.ifExistsInRegNAssProgSrv_sql(data.getCountryCode(), data.getDistrictCode(), data.getUpazillaCode(), data.getUnitCode(), data.getVillageCode(), data.getHh_id(), data.getMemId(), data.getProgram_code(), data.getService_code());


        Cursor cursor = db.rawQuery(sql, null);


        flag = (cursor.getCount() > 0);
        // for protection .if close cursor while it's value null , the close stametnt
        if (cursor != null)
            cursor.close();
        db.close();
        return flag;

    }

    public int checkAssignCriteriaInCT_TableForLiberia(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID, String mmId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.checkDataExitsQueryInCT_TableAssignForLiberia(cCode, distCode, upCode, unCode, vCode, hhID, mmId);
        Cursor cursor = db.rawQuery(selectQuery, null);
        String C11, C21, C31, C32, C33, C34, C35, C36, C37, C38;
        C11 = C21 = C31 = C32 = C33 = C34 = C35 = C36 = C37 = C38 = "";

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                C11 = cursor.getString(cursor.getColumnIndex(C11_CT_PR));
                C21 = cursor.getString(cursor.getColumnIndex(C21_CT_PR));
                C31 = cursor.getString(cursor.getColumnIndex(C31_CT_PR));
                C32 = cursor.getString(cursor.getColumnIndex(C32_CT_PR));
                C33 = cursor.getString(cursor.getColumnIndex(C33_CT_PR));
                C34 = cursor.getString(cursor.getColumnIndex(C34_CT_PR));
                C35 = cursor.getString(cursor.getColumnIndex(C35_CT_PR));
                C36 = cursor.getString(cursor.getColumnIndex(C36_CT_PR));
                C37 = cursor.getString(cursor.getColumnIndex(C37_CT_PR));
                C38 = cursor.getString(cursor.getColumnIndex(C38_CT_PR));


            }
            cursor.close();
        }
        db.close();
        int radioButtonCheckValue = 0;
        if (C11.equals("Y")) {
            radioButtonCheckValue = 1;
        } else {
            if (C21.equals("Y")) {
                radioButtonCheckValue = 2;
            } else {
                if (C31.equals("Y")) {
                    radioButtonCheckValue = 3;
                } else if (C32.equals("Y")) {
                    radioButtonCheckValue = 4;
                } else if (C33.equals("Y")) {
                    radioButtonCheckValue = 5;
                } else if (C34.equals("Y")) {
                    radioButtonCheckValue = 6;
                } else if (C35.equals("Y")) {
                    radioButtonCheckValue = 7;
                } else if (C36.equals("Y")) {
                    radioButtonCheckValue = 8;
                } else if (C37.equals("Y")) {
                    radioButtonCheckValue = 9;
                } else if (C38.equals("Y")) {
                    radioButtonCheckValue = 10;
                }
            }
        }
        return radioButtonCheckValue;
    }

    /****/

    public boolean ifDataExiteInRegNCT(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID, String mmId) {
        return checkDataExistInTable(SQLiteQuery.checkDataExitsQueryInCT_TableAssignForLiberia(cCode, distCode, upCode, unCode, vCode, hhID, mmId), REG_N_CT_TABLE);
    }

    /**
     * @date : 2016-02-07
     * check the data exists in Every Table
     */
    public boolean checkDataExistInTable(String query, String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        if (query == null)
            query = "";


        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
//            Log.d(TAG, "the data exists in Table :" + tableName);
            return true;
        }

        cursor.close();
        db.close();
//        Log.d(TAG, "the data  did not exists in Table :" + tableName);

        return false;

    }

    public void addMemIntoCT_Table(CTDataModel memData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put(ID_COL,idColoumn);
        values.put(ADM_COUNTRY_CODE_COL, memData.getCountryCode());
        values.put(LAY_R_LIST_CODE_COL, memData.getDistrictCode());
        values.put(LAY_R2_LIST_CODE_COL, memData.getUpazillaCode());
        values.put(LAY_R3_LIST_CODE_COL, memData.getUnitCode());
        values.put(LAY_R4_LIST_CODE_COL, memData.getVillageCode());
        values.put(HHID_COL, memData.getHhId());
        values.put(HH_MEM_ID, memData.getMemID());
        values.put(C11_CT_PR, memData.getC11CtPr());
        values.put(C21_CT_PR, memData.getC21CtPr());
        values.put(C31_CT_PR, memData.getC31CtPr());
        values.put(C32_CT_PR, memData.getC32CtPr());
        values.put(C33_CT_PR, memData.getC33CtPr());
        values.put(C34_CT_PR, memData.getC34CtPr());
        values.put(C35_CT_PR, memData.getC35CtPr());
        values.put(C36_CT_PR, memData.getC36CtPr());
        values.put(C37_CT_PR, memData.getC37CtPr());
        values.put(C38_CT_PR, memData.getC38CtPr());
        values.put(ENTRY_BY, memData.getEntryBy());
        values.put(ENTRY_DATE, memData.getEntryDate());
        long id = db.insert(REG_N_CT_TABLE, null, values);
//        Log.i(TAG, "Insert into " + REG_N_CT_TABLE + " id : " + String.valueOf(id));
        db.close();
    }


    public boolean doesMemberAssignedInDifferentService(RegNAssgProgSrv data) {
        SQLiteDatabase db = this.getReadableDatabase();
        String memId = "";
        String selectQuery = SQLiteQuery.doesMemberAssignedInDifferentServiceQuery(data);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                memId = cursor.getString(cursor.getColumnIndex(HH_MEM_ID));
            }
            cursor.close();
        }
//        Log.d(TAG, " does does Member Assigned In Different Service member id : " + memId);
        db.close();
        if (memId.length() == 2) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * : 2015-10-01
     * author: Faisal Mohammad
     * remark: use it generic in future ..
     * iscription: for graduation
     * todo : use checkDataExistInTable()
     */

    public boolean ifExistsInRegNAssProgSrv(GraduationGridDataModel grd) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = SQLiteQuery.getMemberDataFrom_RegNAssProgSrv_Query(grd);


        Cursor cursor = db.rawQuery(sql, null);


        if (cursor.getCount() > 0) {

            cursor.close();
            db.close();
//            Log.d(TAG, " This data exists In Reg N Assinge prog service table");
            return true;
            /* record exist */
        } else {

            cursor.close();
            db.close();
//            Log.d(TAG, " This data  didn't exists In eg N Assinge prog service table");
            return false;
             /* record not exist */
        }


    }


    public boolean ifExistsInCA2Table(AssignDataModel asPeople) {
        //  todo : use checkDataExistInTable()
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + REG_N_CA2_TABLE + " WHERE    " + ADM_COUNTRY_CODE_COL + "=? AND " + LAY_R1_LIST_CODE_COL + "=? AND "
                        + LAY_R2_LIST_CODE_COL + "=? AND " + LAY_R3_LIST_CODE_COL + "=? AND " + LAY_R4_LIST_CODE_COL + "=? AND " + HHID_COL + "=? AND " + MEM_ID_COL + "=?  ",
                new String[]{asPeople.getCountryCode(), asPeople.getDistrictCode(), asPeople.getUpazillaCode(), asPeople.getUnitCode(), asPeople.getVillageCode(),
                        asPeople.getHh_id(), asPeople.getMemId()});

        if (cursor.getCount() > 0) {

            cursor.close();
            db.close();
            return true;
/* record exist */
        } else {


            return false;
/* record not exist */
        }


    }

    /**
     * * todo : use checkDataExistInTable()
     */
    /**
     * This method return tru if data Exits in Pw Table
     *
     * @param asPeople ede
     * @return true
     */

    public boolean ifExistsInPWTable(AssignDataModel asPeople) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + REG_N_PW_TABLE + " WHERE    " + ADM_COUNTRY_CODE_COL + "=? AND " + LAY_R1_LIST_CODE_COL + "=? AND "
                + LAY_R2_LIST_CODE_COL + "=? AND " + LAY_R3_LIST_CODE_COL + "=? AND " + LAY_R4_LIST_CODE_COL + "=? AND " + HHID_COL + "=? AND " + MEM_ID_COL + "=?  ";
        Cursor cursor = db.rawQuery(sql,
                new String[]{asPeople.getCountryCode(), asPeople.getDistrictCode(), asPeople.getUpazillaCode(), asPeople.getUnitCode(), asPeople.getVillageCode(),
                        asPeople.getHh_id(), asPeople.getMemId()});

        if (cursor.getCount() > 0) {

            cursor.close();
            db.close();
            return true;
            /** record exist */
        } else {

            return false;
            /* record does not exist */
        }

    }

    /**
     * @param cCode     Country Code
     * @param donorCode donorCode
     * @param awardCode awardCode
     * @param disttCode Layer1 Code
     * @param upCode    Layer2 Code
     * @param unCode    Layer3 Code
     * @param vCode     Layer4 Code
     * @param hhID      houshole id
     * @param memID     member id
     * @param progCode  program id
     * @param srvCode   Service Id
     * @return true/flase
     */


    public boolean ifExistsInRegNmemProgGroup(String cCode, String donorCode, String awardCode, String disttCode, String upCode
            , String unCode, String vCode, String hhID, String memID
            , String progCode, String srvCode) {
        SQLiteDatabase db = this.getReadableDatabase();


        String sql = "SELECT * FROM " + REG_N_MEM_PROG_GRP_TABLE
                + " WHERE    " + ADM_COUNTRY_CODE_COL + "= '" + cCode + "' "
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

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
//            Log.d(TAG, " This data exists In " + REG_N_MEM_PROG_GRP_TABLE + " table");
            cursor.close();
            db.close();
            return true;
            /** record exist */
        } else {
//            Log.d(TAG, " This data  didn't exists In " + REG_N_MEM_PROG_GRP_TABLE + " table");
            return false;
            /* record does not exist */
        }

    }

    /**
     * Todo: use it in assgne  get_MemOthCriteriaLive
     *
     * @param cCode
     * @param distCode
     * @param upCode
     * @param unCode
     * @param vCode
     * @param hhId
     * @param mmId
     * @param donorCode
     * @param awardCode
     * @param progCode
     * @param srvCode
     * @return
     */

    public boolean get_MemOthCriteriaLive(String cCode, String distCode, String upCode, String unCode, String vCode, String hhId, String mmId, String donorCode, String awardCode, String progCode, String srvCode) {
        boolean memHave = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID +
                " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE +
                " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + LAY_R1_LIST_CODE_COL + " = '" + distCode + "' " +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "' " +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "' " +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + HHID_COL + " = '" + hhId + "' " +
                " AND " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + mmId + "' " +
                " AND " + PROG_CODE_COL + " <> '" + progCode + "' " +
                " AND " + SRV_CODE_COL + " = '" + srvCode + "' " +
                " AND " + GRD_CODE_COL + " = (" + SQLiteQuery.getGraduationDefaultActiveReason_Select_Query(progCode, srvCode) + ") ";

        //  Cursor cursor = db.rawQuery(sql, null);

        Cursor m = db.rawQuery(sql, null);


        if (m != null) {


            if (m.getCount() > 0) {

                memHave = true;

            } else {

                memHave = false;
            }
            m.close();
            db.close();

        }

        return memHave;

    }

    /**
     * * todo : use checkDataExistInTable()
     */

    public boolean ifExistsInLmdTable(AssignDataModel asPeople) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + REG_N_LM_TABLE + " WHERE    " + ADM_COUNTRY_CODE_COL + "=? AND " + LAY_R1_LIST_CODE_COL + "=? AND "
                        + LAY_R2_LIST_CODE_COL + "=? AND " + LAY_R3_LIST_CODE_COL + "=? AND " + LAY_R4_LIST_CODE_COL + "=? AND " + HHID_COL + "=? AND " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + "=?  ",
                new String[]{asPeople.getCountryCode(), asPeople.getDistrictCode(), asPeople.getUpazillaCode(), asPeople.getUnitCode(), asPeople.getVillageCode(),
                        asPeople.getHh_id(), asPeople.getMemId()});//*keyValue,keyvalue1*/});
        if (mCursor.getCount() > 0) {
//            Log.d(TAG, " This data exists In LMD table");
            return true;
        /* record exist */
        } else {
//            Log.d(TAG, " This data  didn't exists In LMD table");
            return false;
        /* record not exist */
        }

    }

    public String getGrdCodeForMember_RegNAssProgSrv(String cCode, String distCode, String upCode, String unCode, String vCode, String hhId, String mmId,
                                                     String progCode, String srvCode, String donorCode, String awardCode) {

        String selectQuery = "SELECT " + GRD_CODE_COL + " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE +
                " WHERE  " + ADM_COUNTRY_CODE_COL + " = '" + cCode
                + "' AND " + LAY_R1_LIST_CODE_COL + " = '" + distCode
                + "' AND " + LAY_R2_LIST_CODE_COL + " = '" + upCode
                + "' AND " + LAY_R3_LIST_CODE_COL + " = '" + unCode
                + "' AND " + LAY_R4_LIST_CODE_COL + " = '" + vCode
                + "' AND " + HHID_COL + " = '" + hhId
                + "' AND " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + mmId
                + "' AND " + ADM_DONOR_CODE_COL + " = '" + donorCode
                + "' AND " + ADM_AWARD_CODE_COL + " = '" + awardCode
                + "' AND " + PROG_CODE_COL + " = '" + progCode
                + "' AND " + SRV_CODE_COL + " = '" + srvCode + "'  ";

        SQLiteDatabase db = this.getReadableDatabase();
        String grdCode = "";
        Cursor mCursor = db.rawQuery(selectQuery, null);
        if (mCursor.moveToFirst()) {
            grdCode = mCursor.getString(mCursor.getColumnIndex(GRD_CODE_COL));
//            Log.d(TAG, "Member Saved  GRD Code : " + grdCode);
        }
        mCursor.close();
        db.close();
        return grdCode;
    }

    /**
     * @since 2015-10-02
     */
    public String getGRDDefaultExitReason(String progCode, String srvCode) {


        String selectQuery = "SELECT " + GRD_CODE_COL + " FROM " + REG_N_LUP_GRADUATION_TABLE +
                " WHERE  " + ADM_PROG_CODE_COL + " = '" + progCode
                + "' AND " + ADM_SRV_CODE_COL + " = '" + srvCode
                + "' AND " + DEFAULT_CAT_EXIT_COL + " = 'Y'  ";

        SQLiteDatabase db = this.getReadableDatabase();
        String grdCode = "";
        Cursor mCursor = db.rawQuery(selectQuery, null);
        if (mCursor.moveToFirst()) {
            grdCode = mCursor.getString(mCursor.getColumnIndex(GRD_CODE_COL));
        }
        mCursor.close();
        db.close();
        return grdCode;
    }


    /**
     * 2015-11-23
     * Faisal Mohammad
     * <p/>
     * get default Exit Reason for Graduation
     */
    public String getGRDDefaultActiveReason(String progCode, String srvCode) {


        String selectQuery = SQLiteQuery.getGraduationDefaultActiveReason_Select_Query(progCode, srvCode);
        /*"SELECT " + GRD_CODE_COL + " FROM " + REG_N_LUP_GRADUATION_TABLE +
                " WHERE  " + ADM_PROG_CODE_COL + " = '" + progCode
                + "' AND " + ADM_SRV_CODE_COL + " = '" + srvCode
                + "' AND " + DEFAULT_CAT_ACTIVE_COL + " = 'Y'  ";*/

        SQLiteDatabase db = this.getReadableDatabase();
        String grdCode = "";
        Cursor mCursor = db.rawQuery(selectQuery, null);
        if (mCursor.moveToFirst()) {
            grdCode = mCursor.getString(mCursor.getColumnIndex(GRD_CODE_COL));
        }
        mCursor.close();
        db.close();
        return grdCode;
    }


    // add gps group
    public void addGpsGroup(String grpCode, String grpName, String description) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GROUP_CODE_COL, grpCode); // GROUP code
        values.put(GROUP_NAME_COL, grpName); // GROUP name
        values.put(DESCRIPTION_COL, description); // GROUP description


        // Inserting Row
        long id = db.insert(GPS_GROUP_TABLE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "New Group inserted into GPS_GROUP_TABLE: " + id);

    }

    // add program master record
    public void addServiceMaster(String pCode, String serviceCode, String serviceN, String srvShortN) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_PROG_CODE_COL, pCode);
        values.put(ADM_SRV_CODE_COL, serviceCode);
        values.put(SERVICE_MASTER_SERVICE_NAME_COL, serviceN);
        values.put(SERVICE_MASTER_SERVICE_SHORT_NAME_COL, srvShortN);
        //values.put(PROGRAM_SHORT_NAME_COL, pShortN);


        // Inserting Row
        long id = db.insert(SERVICE_MASTER_TABLE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "New AWARD inserted into SERVICE_MASTER_TABLE TABLE: " + id);

    }

    // add program master record
    public void addAdmProgramMaster(String pCode, String awardCode, String donorCode, String pName, String pShortN, String multipleSrv) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_PROG_CODE_COL, pCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_PROGRAM_MASTER_PROGRAM_NAME_COL, pName);
        values.put(PROGRAM_SHORT_NAME_COL, pShortN);
        values.put(MULTIPLE_SERVICE_FLAG_COL, multipleSrv);


        // Inserting Row
        db.insert(ADM_PROGRAM_MASTER_TABLE, null, values);
        db.close(); // Closing database connection


    }


    public void insertIntoAdmAward(String donorCode, String awardCode, String awardName, String awardShortName) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);
        values.put(AWARD_NAME_COL, awardName);
        values.put(AWARD_SHORT_COL, awardShortName);


        // Inserting Row
        db.insert(ADM_AWARD_TABLE, null, values);
        db.close();


    }

    public void insertIntoAdmCountryAward(String cCode, String donorCode, String awardCode, String awardRef, String awardStartD, String awardEndD, String awardShortN, String awardStatus) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);                                                    // country code
        values.put(ADM_DONOR_CODE_COL, donorCode);                                                      // donor code
        values.put(ADM_AWARD_CODE_COL, awardCode);                                                      //  award code
        values.put(AWARD_REF_N_COL, awardRef);                                                      // award reference code
        values.put(AWARD_START_DATE_COL, awardStartD);                                              // awardStartDate
        values.put(AWARD_END_DATE_COL, awardEndD);                                                  // awardEndDate
        values.put(AWARD_SHORT_NAME_COL, awardShortN);                                              // AwardShort Name
        values.put(AWARD_STATUS_COL, awardStatus);                                                  // AwardStatus

        // Inserting Row
        db.insert(ADM_COUNTRY_AWARD_TABLE, null, values);
        db.close(); // Closing database connection

        //  Log.d(TAG, "New AWARD inserted into COUNTRY AWARD_TABLE: " + id);
    }

    // add donor name

    /**
     * @param donorCode
     * @param donorName
     */
    public void addDonorName(String donorCode, String donorName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_DONOR_CODE_COL, donorCode); // donor code
        values.put(DONOR_NAME_COL, donorName); // donor Name


        // Inserting Row
        long id = db.insert(ADM_DONOR_TABLE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "New DONOR name  inserted into DONOR_TABLE: " + id);

    }


    public GPSLocationLatLong getLocationSpecificLatLong(String cCode, String groupCode, String subGroupCode, String locationCode) {
        String selectQuery;


        selectQuery = SQLiteQuery.getLocationSpecificLatLong_sql(cCode, groupCode, subGroupCode, locationCode);


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        GPSLocationLatLong latLong = new GPSLocationLatLong();
        latLong.setLatitude("");                                                                    // initial to handel null Exception
        latLong.setLongitude("");

        if (cursor.moveToFirst()) {

            latLong.setLatitude(cursor.getString(cursor.getColumnIndex(LATITUDE_COL)));
            latLong.setLongitude(cursor.getString(cursor.getColumnIndex(LONGITUDE_COL)));

//            Log.d(TAG, " Location " + cursor.getColumnName(0) + " :" + cursor.getString(0));
            cursor.close();

        }


        db.close();
        return latLong;
    }

    /**
     * @param cCode        Country Code
     * @param groupCode    Group  Code
     * @param subGroupCode Sub Group Code
     *                     <p>This Method return the GPS Coordinates , it's title  and Location Code, which latitude  and lonitude are not null</p>
     * @return An array of GPS Location
     */

    public ArrayList<GPS_LocationDataModel> getSubGroupSpecificLatLongCoordinates(String cCode, String groupCode, String subGroupCode) {

        ArrayList<GPS_LocationDataModel> gpsList = new ArrayList<GPS_LocationDataModel>();


        String selectQuery = " SELECT "
                + LOCATION_CODE_COL
                + " , " + LOCATION_NAME_COL
                + " , " + LATITUDE_COL

                + " , " + LONGITUDE_COL
                + " FROM " + GPS_LOCATION_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + GROUP_CODE_COL + " ='" + groupCode + "' "
                + " AND " + SUB_GROUP_CODE_COL + " ='" + subGroupCode + "' "
                + " AND (" + LATITUDE_COL + " != '' " + " OR  " + LONGITUDE_COL + " != '' )";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {

            do {
                GPS_LocationDataModel data = new GPS_LocationDataModel();
                data.setAdmCountryCode(cCode);
                data.setGroupCode(groupCode);
                data.setSubGroupCode(subGroupCode);
                data.setLocationCode(cursor.getString(0));
                data.setLocationName(cursor.getString(1));
                data.setLat(cursor.getString(2));
                data.setLng(cursor.getString(3));
                gpsList.add(data);


            } while (cursor.moveToNext());

            cursor.close();
            db.close();


        }


        return gpsList;
    }

    public String get_ProgSrvDefaultDays(String cCode, String donorCode, String awardCode, String progCode, String srvCode, String flag) {
        String getDefaultDays = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "";
        switch (flag) {
            case "FoodFlag":
                sql = "SELECT " + DEFAULT_FOOD_DAYS_COL + " FROM " + COUNTRY_PROGRAM_TABLE
                        + " WHERE " + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND  " + SQLiteHandler.ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                        + " AND  " + SQLiteHandler.ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                        + " AND  " + SQLiteHandler.ADM_PROG_CODE_COL + " = '" + progCode + "' "
                        + " AND  " + SQLiteHandler.ADM_SRV_CODE_COL + " = '" + srvCode + "' ";
                break;
            case "NFoodFlag":
                sql = "SELECT " + DEFAULT_NO_FOOD_DAYS_COL + " FROM " + COUNTRY_PROGRAM_TABLE
                        + " WHERE " + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND  " + SQLiteHandler.ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                        + " AND  " + SQLiteHandler.ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                        + " AND  " + SQLiteHandler.ADM_PROG_CODE_COL + " = '" + progCode + "' "
                        + " AND  " + SQLiteHandler.ADM_SRV_CODE_COL + " = '" + srvCode + "' ";
                break;
            case "CashFlag":
                sql = "SELECT " + DEFAULT_CASH_DAYS_COL + " FROM " + COUNTRY_PROGRAM_TABLE
                        + " WHERE " + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND  " + SQLiteHandler.ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                        + " AND  " + SQLiteHandler.ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                        + " AND  " + SQLiteHandler.ADM_PROG_CODE_COL + " = '" + progCode + "' "
                        + " AND  " + SQLiteHandler.ADM_SRV_CODE_COL + " = '" + srvCode + "' ";
                break;
            case "VOFlag":
                sql = "SELECT " + DEFAULT_VOUCHAR_DAYS_COL + " FROM " + COUNTRY_PROGRAM_TABLE
                        + " WHERE " + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND  " + SQLiteHandler.ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                        + " AND  " + SQLiteHandler.ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                        + " AND  " + SQLiteHandler.ADM_PROG_CODE_COL + " = '" + progCode + "' "
                        + " AND  " + SQLiteHandler.ADM_SRV_CODE_COL + " = '" + srvCode + "' ";
                break;
        }


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                getDefaultDays = cursor.getString(0);

            }
            cursor.close();
            db.close();
        }
        return getDefaultDays;
    }


    public boolean ifThisHHIDExitsInRegHHTable(String countryCode, String distCode, String upCode, String unCode, String villCode, String hh_id) {
        boolean isExits;
        String selectQuery = "SELECT * FROM " + REG_N_HH_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + LAY_R1_LIST_CODE + " = '" + distCode + "' "
                + " AND " + REGISTRATION_TABLE_UPZILLA_CODE_COL + " = '" + upCode + "' "
                + " AND " + REGISTRATION_TABLE_UNION_CODE_COL + " = '" + unCode + "' "
                + " AND " + REGISTRATION_TABLE_VILLAGE_CODE_COL + " = '" + villCode + "' "
                + " AND " + REGISTRATION_TABLE_HHID + " = '" + hh_id + "' ";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;

    }

    /**
     * @param cCode    Country Code
     * @param dnorCode Donor Code
     * @param awdCode  award Code
     * @param progCode Program Code
     * @return VoucherFlag is exits than true or else  false
     */

    public boolean checkAdmCountryProgramsVoucherFlag(String cCode, String dnorCode, String awdCode, String progCode) {

        boolean voFlag = false;
        String tem = null;
        String selectQuery = SQLiteQuery.checkAdmCountryProgramsVoucherFlag_sql(cCode, dnorCode, awdCode, progCode);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                tem = cursor.getString(cursor.getColumnIndex(VOUCHER_FLAG));
            }

            cursor.close();
            db.close();

            voFlag = tem.equals("1") ? true : false;
        }
//        if (tem != null) {
//            if (tem.equals("1")) // if volFag found 1 than it will send the true
//                voFlag = true;
//        }
        return voFlag;

    }

    /**
     * @param cCode    Country Code
     * @param dnorCode Donor Code
     * @param awdCode  award Code
     * @param progCode Program Code
     * @param SrvCode  Service Code
     * @return nonFoodFlag is exits than true or else  false
     */

    public boolean checkAdmCountryProgramsNoneFoodFlag(String cCode, String dnorCode, String awdCode, String progCode, String SrvCode) {

        boolean nonFoodFlag = false;
        String tem = null;
        String selectQuery = SQLiteQuery.checkAdmCountryProgramsNoneFoodFlag_sql(cCode, dnorCode, awdCode, progCode, SrvCode);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                tem = cursor.getString(0);
            }

            cursor.close();
            db.close();
        }
        if (tem != null) {
            if (tem.equals("1")) // if nonFoodFlag found 1 than it will sed the true
                nonFoodFlag = true;
        }
        return nonFoodFlag;

    }


  /*  public long insertIntoDDR_RegN_FFATable(String countryCode, String districtCode, String upozillaCode,
                                            String unitCode, String villageCode, String houseHoldID,
                                            String houseHoldMemberId, String orphanChildren, String childHeaded,
                                            String elderlyHeaded, String chronicallyIll, String cropFailure,
                                            String childrenRecSuppFeedN, String willingness, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, countryCode);
        values.put(LAY_R_LIST_CODE_COL, districtCode);
        values.put(LAY_R2_LIST_CODE_COL, upozillaCode);
        values.put(LAY_R3_LIST_CODE_COL, unitCode);
        values.put(LAY_R4_LIST_CODE_COL, villageCode);
        values.put(HHID_COL, houseHoldID);
        values.put(HH_MEM_ID, houseHoldMemberId);
        values.put(ORPHAN_CHILDREN_COL, orphanChildren);
        values.put(CHILD_HEADED_COL, childHeaded);
        values.put(ELDERLY_HEADED_COL, elderlyHeaded);
        values.put(CHRONICALLY_ILL_COL, chronicallyIll);
        values.put(CROP_FAILURE_COL, cropFailure);
        values.put(CHILDREN_REC_SUPP_FEED_N_COL, childrenRecSuppFeedN);
        values.put(WILLINGNESS_COL, willingness);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        long id = db.insert(REG_N_FFA_TABLE, null, values);
        return id;


        + SQLiteHandler.ADM_COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LAY_R_LIST_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HHID_COL + " VARCHAR(5) "
                + " , " + SQLiteHandler.HH_MEM_ID+" VARCHAR(2) "
                + " , " + SQLiteHandler.ORPHAN_CHILDREN_COL+" VARCHAR(1) "
                + " , " + SQLiteHandler.CHILD_HEADED_COL+" VARCHAR(1) "
                + " , " + SQLiteHandler.ELDERLY_HEADED_COL+" VARCHAR(1) "
                + " , " + SQLiteHandler.CHRONICALLY_ILL_COL +" VARCHAR(1) "
                + " , " + SQLiteHandler.CROP_FAILURE_COL + " VARCHAR(1) "
                +"  , " + SQLiteHandler.CHILDREN_REC_SUPP_FEED_N_COL +" VARCHAR(1) "
                +"  , " + SQLiteHandler.WILLINGNESS_COL+ " VARCHAR(1) "
    }*/


    public void editIntoDDR_RegN_FFATable(String cCode, String districtCode, String upozillaCode,
                                          String unitCode, String villageCode, String houseHoldID,
                                          String houseHoldMemberId, String orphanChildren, String childHeaded,
                                          String elderlyHeaded, String chronicallyIll, String femaleHeaded, String cropFailure,
                                          String childrenRecSuppFeedN, String willingness, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String where = "" + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + upozillaCode + "' "
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + unitCode + "' "
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                + " AND " + HHID_COL + " = '" + houseHoldID + "' "
                + " AND " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + houseHoldMemberId + "' ";


        values.put(ORPHAN_CHILDREN_COL, orphanChildren);
        values.put(CHILD_HEADED_COL, childHeaded);
        values.put(ELDERLY_HEADED_COL, elderlyHeaded);
        values.put(CHRONICALLY_ILL_COL, chronicallyIll);
        values.put(FEMALE_HEADED_COL, femaleHeaded);
        values.put(CROP_FAILURE_COL, cropFailure);
        values.put(CHILDREN_REC_SUPP_FEED_N_COL, childrenRecSuppFeedN);
        values.put(WILLINGNESS_COL, willingness);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        db.update(REG_N_FFA_TABLE, values, where, null);

    }


    public void insertIntoDDR_RegN_FFATable(String cCode, String layR1Code, String layR2Code,
                                            String layR3Code, String layR4Code, String houseHoldID,
                                            String houseHoldMemberId, String orphanChildren, String childHeaded,
                                            String elderlyHeaded, String chronicallyIll, String femaleHeaded, String cropFailure,
                                            String childrenRecSuppFeedN, String willingness, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL, layR1Code);
        values.put(LAY_R2_LIST_CODE_COL, layR2Code);
        values.put(LAY_R3_LIST_CODE_COL, layR3Code);
        values.put(LAY_R4_LIST_CODE_COL, layR4Code);
        values.put(HHID_COL, houseHoldID);
        values.put(REG_N_ASSIGN_PROG_SRV_HH_MEM_ID, houseHoldMemberId);
        values.put(ORPHAN_CHILDREN_COL, orphanChildren);
        values.put(CHILD_HEADED_COL, childHeaded);
        values.put(ELDERLY_HEADED_COL, elderlyHeaded);
        values.put(CHRONICALLY_ILL_COL, chronicallyIll);
        values.put(FEMALE_HEADED_COL, femaleHeaded);
        values.put(CROP_FAILURE_COL, cropFailure);
        values.put(CHILDREN_REC_SUPP_FEED_N_COL, childrenRecSuppFeedN);
        values.put(WILLINGNESS_COL, willingness);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        db.insert(REG_N_FFA_TABLE, null, values);


    }


    public void insertInto_RegN_WETable(String cCode, String layR1Code, String layR2Code, String layR3Code, String layR4Code, String houseHoldID,
                                        String houseHoldMemberId, String regDate, String wealthRank, String memberExitGroup, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL, layR1Code);
        values.put(LAY_R2_LIST_CODE_COL, layR2Code);
        values.put(LAY_R3_LIST_CODE_COL, layR3Code);
        values.put(LAY_R4_LIST_CODE_COL, layR4Code);
        values.put(HHID_COL, houseHoldID);
        values.put(REG_N_ASSIGN_PROG_SRV_HH_MEM_ID, houseHoldMemberId);
        values.put(REG_DATE_COL, regDate);
        values.put(WEALTH_RANKING_COL, wealthRank);
        values.put(MEMBER_EXT_GROUP_COL, memberExitGroup);

        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        db.insert(REG_N_WE_TABLE, null, values);


    }

    public void editInto_RegN_WETable(String cCode, String layR1Code, String layR2Code, String layR3Code, String layR4Code, String houseHoldID,
                                      String houseHoldMemberId, String regDate, String wealthRank, String memberExitGroup, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String where = "" + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + layR1Code + "' "
                + " AND " + LAY_R2_LIST_CODE_COL + " = '" + layR2Code + "' "
                + " AND " + LAY_R3_LIST_CODE_COL + " = '" + layR3Code + "' "
                + " AND " + LAY_R4_LIST_CODE_COL + " = '" + layR4Code + "' "
                + " AND " + HHID_COL + " = '" + houseHoldID + "' "
                + " AND " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + houseHoldMemberId + "' ";


        values.put(REG_DATE_COL, regDate);
        values.put(WEALTH_RANKING_COL, wealthRank);
        values.put(MEMBER_EXT_GROUP_COL, memberExitGroup);

        db.update(REG_N_WE_TABLE, values, where, null);

    }


    public AssignDDR_FFA_DataModel getAssignDataIfExitsInRegNFFA_table(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID, String mmId) {
        SQLiteDatabase db = this.getReadableDatabase();


        String selectQuery = SQLiteQuery.getAssignDataIfExitsInRegNFFA_table_sql(cCode, distCode, upCode, unCode, vCode, hhID, mmId);


        Cursor cursor = db.rawQuery(selectQuery, null);
        AssignDDR_FFA_DataModel ffaData = new AssignDDR_FFA_DataModel();
// default value
        ffaData.setOrphanChildRb1("N");
        ffaData.setElderlyHeadedRb2("N");
        ffaData.setChronicallyIllRb3("N");
        ffaData.setFemaleHeadedRb4("N");
        ffaData.setCropFailureRb5("N");
        ffaData.setChildrenRecSuppFeedNRb6("N");
        ffaData.setWillingnessRb7("N");

        if (cursor != null) {
            if (cursor.moveToFirst()) {


                ffaData.setOrphanChildRb1(cursor.getString(cursor.getColumnIndex(CHILD_HEADED_COL)));
                ffaData.setElderlyHeadedRb2(cursor.getString(cursor.getColumnIndex(ELDERLY_HEADED_COL)));
                ffaData.setChronicallyIllRb3(cursor.getString(cursor.getColumnIndex(CHRONICALLY_ILL_COL)));
                ffaData.setFemaleHeadedRb4(cursor.getString(cursor.getColumnIndex(FEMALE_HEADED_COL)));
                ffaData.setCropFailureRb5(cursor.getString(cursor.getColumnIndex(CROP_FAILURE_COL)));
                ffaData.setChildrenRecSuppFeedNRb6(cursor.getString(cursor.getColumnIndex(CHILDREN_REC_SUPP_FEED_N_COL)));
                ffaData.setWillingnessRb7(cursor.getString(cursor.getColumnIndex(WILLINGNESS_COL)));

//                Log.d("FFA-SQL", "agr_dataModel.=" + ffaData.getOrphanChildRb1()
//                        + ffaData.getElderlyHeadedRb2() + ffaData.getChronicallyIllRb3()
//                        + ffaData.getFemaleHeadedRb4() + ffaData.getCropFailureRb5()
//                        + ffaData.getChildrenRecSuppFeedNRb6() + ffaData.getWillingnessRb7()
//                );


            }
            cursor.close();
        }
        db.close();
        return ffaData;
    }


    public long insertIntoDDR_RegN_VUL(String countryCode, String districtCode, String upozillaCode,
                                       String unitCode, String villageCode, String houseHoldID,
                                       String houseHoldMemberId, String regnDate, String disable,
                                       String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, countryCode);
        values.put(LAY_R1_LIST_CODE, districtCode);
        values.put(LAY_R2_LIST_CODE_COL, upozillaCode);
        values.put(LAY_R3_LIST_CODE_COL, unitCode);
        values.put(LAY_R4_LIST_CODE_COL, villageCode);
        values.put(HHID_COL, houseHoldID);
        values.put(REG_N_ASSIGN_PROG_SRV_HH_MEM_ID, houseHoldMemberId);
        values.put(REG_N_DAT_COL, regnDate);
        values.put(Disabled_YN_COL, disable);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        long id = db.insert(REG_N_VUL_TABLE, null, values);
        return id;
    }


    public boolean checkCriteriaServiceSpecificFlag(String countryCode, String donarCode, String awardCode, String progCode, String srvCode) {

        boolean srvSpeceficFlag = false;
        String tem = null;
        String selectQuery = "SELECT " + SERVICE_SPECIFIC_FLAG_COL + " FROM " + COUNTRY_PROGRAM_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "'"
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donarCode + "'"
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND " + ADM_PROG_CODE_COL + " = '" + progCode + "'"
                + " AND " + ADM_SRV_CODE_COL + " = '" + srvCode + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                tem = cursor.getString(0);
            }

            cursor.close();
            db.close();
        }
        if (tem != null) {
            if (tem.equals("1")) // if srvSpeceficFlag found 1 than it will sed the true
                srvSpeceficFlag = true;
        }
        return srvSpeceficFlag;

    }

    public String getMinDate(String cCode) {
        return getDate(cCode, "Min");
    }

    public String getMaxDate(String cCode) {
        return getDate(cCode, "Max");
    }

    public String getDate(String cCode, String MinOrMax) {
        String date = "";
        String sql = "";
        // query to get the start date of the registration process.
        if (MinOrMax.equals("Min")) {
            sql = "SELECT MIN " + "(" + START_DATE_COL + ")" + " FROM " + OP_MONTH_TABLE + " WHERE " + ADM_COUNTRY_CODE_COL
                    + " = '" + cCode + "'" + " AND " + STATUS + "= 'A'";
        } else {
            sql = "SELECT MAX " + "(" + END_DATE_COL + ")" + " FROM " + OP_MONTH_TABLE + " WHERE " + ADM_COUNTRY_CODE_COL
                    + " = '" + cCode + "'" + " AND " + STATUS + "= 'A'";
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                date = cursor.getString(0);     // Start Date
            }
            cursor.close();
            db.close();
        }


        return date;
    }


    public boolean ifThisNameExitsInRegHHTable(String hname) {
        boolean isExits;
        String selectQuery = "SELECT * FROM " + REG_N_HH_TABLE + " WHERE " + REGISTRATION_TABLE_HH_HEAD_NAME + " = '" + hname + "' ";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;

    }


    /**
     * @param str_c_code
     * @param layR1Code
     * @param layR2Code
     * @param layR3Code
     * @param layR4Code
     * @param hhID
     * @return #RegN_HH_libDataModel
     */


    public RegN_HH_libDataModel getSingleLiberiaHouseHoldData(String str_c_code, String layR1Code, String layR2Code, String layR3Code, String layR4Code, String hhID) {
        RegN_HH_libDataModel hhData = new RegN_HH_libDataModel();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = SQLiteQuery.getSingleHouseHoldDataForLiberiaQuery(str_c_code, layR1Code, layR2Code, layR3Code, layR4Code, hhID);

        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {


                hhData.setRegNDate(cursor.getString(cursor.getColumnIndex(REG_DATE_COL)));
                hhData.setHHHeadName(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_HH_HEAD_NAME)));
                hhData.setHHTypeCode(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_HOUSE_HOLD_TYPE_CODE_COL)));
                hhData.setHHTypeString(cursor.getString(cursor.getColumnIndex("HHType")));


                hhData.setLT2yrsM(cursor.getString(cursor.getColumnIndex(LT2YRS_M_COL)));
                hhData.setLT2yrsF(cursor.getString(cursor.getColumnIndex(LT2YRS_F_COL)));
                hhData.setM2to5yers(cursor.getString(cursor.getColumnIndex(M_2TO5YRS_COL)));
                hhData.setF2to5yrs(cursor.getString(cursor.getColumnIndex(F_2TO5YRS_COL)));
                hhData.setM6to12yrs(cursor.getString(cursor.getColumnIndex(M_6TO12YRS_COL)));
                hhData.setF6to12yrs(cursor.getString(cursor.getColumnIndex(F_6TO12YRS_COL)));
                hhData.setM13to17yrs(cursor.getString(cursor.getColumnIndex(M_13TO17YRS_COL)));
                hhData.setF13to17yrs(cursor.getString(cursor.getColumnIndex(F_13TO17YRS_COL)));
                hhData.setOrphn_LT18yrsM(cursor.getString(cursor.getColumnIndex(ORPHN_LT18YRS_M_COL)));
                hhData.setOrphn_LT18yrsF(cursor.getString(cursor.getColumnIndex(ORPHN_LT18YRS_F_COL)));
                hhData.setAdlt_18to59M(cursor.getString(cursor.getColumnIndex(ADLT_18TO59_M_COL)));
                hhData.setAdlt_18to59F(cursor.getString(cursor.getColumnIndex(ADLT_18TO59_F_COL)));
                hhData.setEld_60pM(cursor.getString(cursor.getColumnIndex(ELD_60P_M_COL)));
                hhData.setEld_60pF(cursor.getString(cursor.getColumnIndex(ELD_60P_F_COL)));
                hhData.setPLW(cursor.getString(cursor.getColumnIndex(PLW_NO_COL)));
                hhData.setChronicallyIll(cursor.getString(cursor.getColumnIndex(CHRO_ILL_NO_COL)));


                hhData.setLivingDeceasedContractEbola(cursor.getString(cursor.getColumnIndex(DECEASED_CONTRACT_EBOLA_COL)));
                hhData.setExtraChildBecauseEbola(cursor.getString(cursor.getColumnIndex(EXTRA_CHILD_CAUSE_EBOLA_COL)));
                hhData.setExtraelderlyPersonBecauseEbola(cursor.getString(cursor.getColumnIndex(EXTRA_ELDERLY_CAUSE_EBOLA_COL)));
                hhData.setExtraChronicallyIllDisabledPersonBecauseEbola(cursor.getString(cursor.getColumnIndex(EXTRA_CHRONICALLY_ILL_CAUSE_EBOLA_COL)));

                hhData.setBrfCntCattle(cursor.getString(cursor.getColumnIndex(BRF_COUNT_CATTLE_COL)));
                hhData.setBrfValCattle(cursor.getString(cursor.getColumnIndex(BRF_VALUE_CATTLE_COL)));
                hhData.setAftCntCattle(cursor.getString(cursor.getColumnIndex(AFT_COUNT_CATTLE_COL)));
                hhData.setAftValCattle(cursor.getString(cursor.getColumnIndex(AFT_VALUE_CATTLE_COL)));
                hhData.setBrfCntSheepGoats(cursor.getString(cursor.getColumnIndex(BRF_COUNT_SGOATS_COL)));
                hhData.setBrfValSheepGoats(cursor.getString(cursor.getColumnIndex(BRF_VALUE_SGOATS_COL)));
                hhData.setAftCntSheepGoats(cursor.getString(cursor.getColumnIndex(AFT_COUNT_SGOATS_COL)));
                hhData.setAftValSheepGoats(cursor.getString(cursor.getColumnIndex(AFT_VALUE_SGOATS_COL)));
                hhData.setBrfCntPoultry(cursor.getString(cursor.getColumnIndex(BRF_COUNT_POULTRY_COL)));
                hhData.setBrfValPoultry(cursor.getString(cursor.getColumnIndex(BRF_VALUE_POULTRY_COL)));
                hhData.setAftCntPoultry(cursor.getString(cursor.getColumnIndex(AFT_COUNT_POULTRY_COL)));
                hhData.setAftValPoultry(cursor.getString(cursor.getColumnIndex(AFT_VALUE_POULTRY_COL)));
                hhData.setBrfCntOther(cursor.getString(cursor.getColumnIndex(BRF_COUNT_OTHER_COL)));
                hhData.setBrfValOther(cursor.getString(cursor.getColumnIndex(BRF_VALUE_OTHER_COL)));
                hhData.setAftCntOther(cursor.getString(cursor.getColumnIndex(AFT_COUNT_OTHER_COL)));
                hhData.setAftValOther(cursor.getString(cursor.getColumnIndex(AFT_VALUE_OTHER_COL)));
                hhData.setBrfAcreCultivable(cursor.getString(cursor.getColumnIndex(BRF_ACRE_CULTIVABLE_COL)));
                hhData.setBrfValCultivable(cursor.getString(cursor.getColumnIndex(BRF_VALUE_CULTIVABLE_COL)));
                hhData.setAftAcreCultivable(cursor.getString(cursor.getColumnIndex(AFT_ACRE_CULTIVABLE_COL)));
                hhData.setAftValCultivable(cursor.getString(cursor.getColumnIndex(AFT_VALUE_CULTIVABLE_COL)));
                hhData.setBrfAcreNonCultivable(cursor.getString(cursor.getColumnIndex(BRF_ACRE_NON_CULTIVABLE_COL)));
                hhData.setBrfValNonCultivable(cursor.getString(cursor.getColumnIndex(BRF_VAL_NON_CULTIVABLE_COL)));
                hhData.setAftAcreNonCultivable(cursor.getString(cursor.getColumnIndex(AFT_ACRE_NON_CULTIVABLE)));
                hhData.setAftValNonCultivable(cursor.getString(cursor.getColumnIndex(AFT_VAL_NON_CULTIVABLE)));
                hhData.setBrfAcreOrchards(cursor.getString(cursor.getColumnIndex(BRF_ACRE_ORCHARDS)));
                hhData.setBrfValOrchards(cursor.getString(cursor.getColumnIndex(BRF_VAL_ORCHARDS)));
                hhData.setAftAcreOrchards(cursor.getString(cursor.getColumnIndex(AFT_ACRE_ORCHARDS)));
                hhData.setAftValOrchards(cursor.getString(cursor.getColumnIndex(AFT_VAL_ORCHARDS)));

                hhData.setBrfValCrop(cursor.getString(cursor.getColumnIndex(BRF_VAL_CROP)));
                hhData.setAftValCrop(cursor.getString(cursor.getColumnIndex(AFT_VAL_CROP)));
                hhData.setBrfValLivestock(cursor.getString(cursor.getColumnIndex(BRF_VAL_LIVESTOCK)));
                hhData.setAftValLivestock(cursor.getString(cursor.getColumnIndex(AFT_VAL_LIVESTOCK)));
                hhData.setBrfValSmallBusiness(cursor.getString(cursor.getColumnIndex(BRF_VAL_SMALL_BUSINESS)));
                hhData.setAftValSmallBusiness(cursor.getString(cursor.getColumnIndex(AFT_VAL_SMALL_BUSINESS)));
                hhData.setBrfValEmployment(cursor.getString(cursor.getColumnIndex(BRF_VAL_EMPLOYMENT)));
                hhData.setAftValEmployment(cursor.getString(cursor.getColumnIndex(AFT_VAL_EMPLOYMENT)));
                hhData.setBrfValRemittances(cursor.getString(cursor.getColumnIndex(BRF_VAL_REMITTANCES)));
                hhData.setAftValRemittances(cursor.getString(cursor.getColumnIndex(AFT_VAL_REMITTANCES)));
                hhData.setBrfCntWageEnr(cursor.getString(cursor.getColumnIndex(BRF_CNT_WAGEENR)));
                hhData.setAftCntWageEnr(cursor.getString(cursor.getColumnIndex(AFT_CNT_WAGEENR)));
                /**
                 hhData.setEntryBy(EntryBy);
                 hhData.setEntryDate(EntryDate);*/


            }
        }
        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return hhData;
    }


    // ToDo: get single member data


    public RegN_MM_libDataModel getSingleLiberiaMemberData(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String hhID, String memID) {
        RegN_MM_libDataModel memberData = new RegN_MM_libDataModel();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = SQLiteQuery.getSingleLiberiaMemberDataQuery(str_c_code, str_district, str_upazilla, str_union, str_village, hhID, memID);

        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {


                memberData.setStr_MemNameFirst(cursor.getString(cursor.getColumnIndex(MEM_NAME_COL)));
                memberData.setStr_entry_by(cursor.getString(cursor.getColumnIndex(ENTRY_BY)));
                memberData.setStr_entry_date(cursor.getString(cursor.getColumnIndex(ENTRY_DATE)));
                memberData.setStr_MemBirthYear(cursor.getString(cursor.getColumnIndex(BIRTH_YEAR_COL)));
                memberData.setCodeMartial(cursor.getString(cursor.getColumnIndex(MARITAL_STATUS_COL)));
                memberData.setStr_MemContact(cursor.getString(cursor.getColumnIndex(CONTACT_NO_COL)));
                memberData.setStr_memOtherID(cursor.getString(cursor.getColumnIndex(MEMBER_OTHER_ID_COL)));
                memberData.setStr_MemNameFirst(cursor.getString(cursor.getColumnIndex(MEM_NAME_FIRST_COL)));
                memberData.setStr_MemNameMiddle(cursor.getString(cursor.getColumnIndex(MEM_NAME_MIDDLE_COL)));
                memberData.setStr_MemNameLast(cursor.getString(cursor.getColumnIndex(MEM_NAME_LAST_COL)));
                memberData.setMemberEncodedImage(cursor.getBlob(cursor.getColumnIndex(PHOTO_COL)));
                memberData.setCodeIDType(cursor.getString(cursor.getColumnIndex(TYPE_ID_COL)));
                memberData.setStrTypeIDNo(cursor.getString(cursor.getColumnIndex(ID_NO_COL)));
                memberData.setStr_V_bsc_Mem_1_NameFirst(cursor.getString(cursor.getColumnIndex(V_BSC_MEM_1_NAME_FIRST_COL)));
                memberData.setStr_V_bsc_Mem_1_NameMiddle(cursor.getString(cursor.getColumnIndex(V_BSC_MEM_1_NAME_MIDDLE_COL)));
                memberData.setStr_V_bsc_Mem_1_NameLast(cursor.getString(cursor.getColumnIndex(V_BSC_MEM_1_NAME_LAST_COL)));

                // TODO :: Need to check relation in Member table and needs to save the Relation code
                // TODO :: Need to select relation according to code in Member Detail page  + V_BSC_MEM_1_TITLE_COL + " , "


                memberData.setCodeBscMem1Title(cursor.getString(cursor.getColumnIndex(V_BSC_MEM_1_TITLE_COL)));
                memberData.setStr_V_bsc_Mem_2_NameFirst(cursor.getString(cursor.getColumnIndex(V_BSC_MEM_2_NAME_FIRST_COL)));
                memberData.setStr_V_bsc_Mem_2_NameMiddle(cursor.getString(cursor.getColumnIndex(V_BSC_MEM_2_NAME_MIDDLE_COL)));
                memberData.setStr_V_bsc_Mem_2_NameLast(cursor.getString(cursor.getColumnIndex(V_BSC_MEM_2_NAME_LAST_COL)));
                memberData.setCodeBscMem2Title(cursor.getString(cursor.getColumnIndex(V_BSC_MEM_2_TITLE_COL)));
                memberData.setCodeDesignatedProxy(cursor.getString(cursor.getColumnIndex(PROXY_DESIGNATION_COL)));
                memberData.setStr_ProxyNameFirst(cursor.getString(cursor.getColumnIndex(PROXY_NAME_FIRST_COL)));
                memberData.setStr_ProxyNameMiddle(cursor.getString(cursor.getColumnIndex(PROXY_NAME_MIDDLE_COL)));
                memberData.setStr_ProxyNameLast(cursor.getString(cursor.getColumnIndex(PROXY_NAME_LAST_COL)));
                memberData.setStr_ProxyBirthYear(cursor.getString(cursor.getColumnIndex(PROXY_BIRTH_YEAR_COL)));
                memberData.setProxyEncodedImage(cursor.getBlob(cursor.getColumnIndex(PROXY_PHOTO_COL)));
                memberData.setStr_ProxyTypedIDNo(cursor.getString(cursor.getColumnIndex(PROXY_ID_NO_COL)));
                memberData.setCodeIDTypeForProxy(cursor.getString(cursor.getColumnIndex(PROXY_TYPE_ID_COL)));
                memberData.setStr_Proxy_bsc_Mem_1_NameFirst(cursor.getString(cursor.getColumnIndex(PROXY_BSC_MEM_1_NAME_FIRST_COL)));
                memberData.setStr_Proxy_bsc_Mem_1_NameMiddle(cursor.getString(cursor.getColumnIndex(PROXY_BSC_MEM_1_NAME_MIDDLE_COL)));
                memberData.setStr_Proxy_bsc_Mem_1_NameLast(cursor.getString(cursor.getColumnIndex(PROXY_BSC_MEM_1_NAME_LAST_COL)));
                memberData.setCodeProxyBscMem1Title(cursor.getString(cursor.getColumnIndex(PROXY_BSC_MEM_1_TITLE_COL)));
                memberData.setStr_Proxy_bsc_Mem_2_NameFirst(cursor.getString(cursor.getColumnIndex(PROXY_BSC_MEM_2_NAME_FIRST_COL)));
                memberData.setStr_Proxy_bsc_Mem_2_NameMiddle(cursor.getString(cursor.getColumnIndex(PROXY_BSC_MEM_2_NAME_MIDDLE_COL)));
                memberData.setStr_Proxy_bsc_Mem_2_NameLast(cursor.getString(cursor.getColumnIndex(PROXY_BSC_MEM_2_NAME_LAST_COL)));
                memberData.setCodeProxyBscMem2Title(cursor.getString(cursor.getColumnIndex(PROXY_BSC_MEM_2_TITLE_COL)));


            }
        }
        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return memberData;
    }


    // Getting All Contacts

    public List<MemberModel> getMemberData(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String hhID) {

        List<MemberModel> memberList = new ArrayList<MemberModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "";


        query = SQLiteQuery.getMemberData_sql(str_c_code, str_district, str_upazilla, str_union, str_village, hhID);


        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                MemberModel member = new MemberModel();

                //   member.setPID(Integer.parseInt(cursor.getString(cursor.getColumnIndex("PID"))));
                member.setPID(Integer.parseInt(cursor.getString(cursor.getColumnIndex("PID"))));/*
                member.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_COL))));*/
                member.setRegID(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                member.setName(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_HH_HEAD_NAME)));

                member.setDistrict(cursor.getString(cursor.getColumnIndex(LAY_R1_LIST_CODE_COL)));
                member.setUpazilla(cursor.getString(cursor.getColumnIndex(LAY_R2_LIST_CODE_COL)));
                member.setUnitName(cursor.getString(cursor.getColumnIndex(LAY_R3_LIST_CODE_COL)));
                member.setVillage(cursor.getString(cursor.getColumnIndex(LAY_R4_LIST_CODE_COL)));

                member.setCountryCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                member.setDistrictCode(cursor.getString(cursor.getColumnIndex(LAY_R1_LIST_CODE_COL)));
                member.setUpazillaCode(cursor.getString(cursor.getColumnIndex(LAY_R2_LIST_CODE_COL)));
                member.setUnitNameCode(cursor.getString(cursor.getColumnIndex(LAY_R3_LIST_CODE_COL)));
                member.setVillageCode(cursor.getString(cursor.getColumnIndex(LAY_R4_LIST_CODE_COL)));


                member.setMemID(cursor.getString(cursor.getColumnIndex(HH_MEM_ID)));
                member.setMemberName(cursor.getString(cursor.getColumnIndex(MEM_NAME_COL)));
                member.setGender(cursor.getString(cursor.getColumnIndex(SEX_COL)));

                // TODO :: Need to check relation in Member table and needs to save the Relation code
                // TODO :: Need to select relation according to code in Member Detail page

                member.setEntryBy(cursor.getString(cursor.getColumnIndex(ENTRY_BY)));
                member.setEntryDate(cursor.getString(cursor.getColumnIndex(ENTRY_DATE)));

                member.setRelation(cursor.getString(cursor.getColumnIndex(RELATION_COL)));
                member.setRelationCode(cursor.getString(cursor.getColumnIndex(RELATION_NAME)));


                member.setLMPDate(cursor.getString(cursor.getColumnIndex(LMP_DATE)));
                member.setChildDOB(cursor.getString(cursor.getColumnIndex(CHILD_DOB)));
                member.setElderly(cursor.getString(cursor.getColumnIndex(ELDERLY)));
                member.setDisabled(cursor.getString(cursor.getColumnIndex(DISABLE)));
                member.setMemberAge(cursor.getString(cursor.getColumnIndex(MEM_AGE)));


                // adding all into personList array
                memberList.add(member);

            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return contact list
        return memberList;
    }


    public ListDataModel getSingleRegisteredData(String cCode, String layR1Code, String layR2Code, String layR3Code, String layR4Code, final String hhId) {


        SQLiteDatabase db = this.getReadableDatabase();

        String query = "";


        query = SQLiteQuery.getSingleRegisteredData_sql(cCode, layR1Code, layR2Code, layR3Code, layR4Code, hhId);
        ListDataModel person = new ListDataModel();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {


//                person.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_COL))));


                person.setCountryName(cursor.getString(cursor.getColumnIndex(COUNTRY_COUNTRY_NAME)));
                person.setDistrict(cursor.getString(cursor.getColumnIndex(DISTRICT_NAME_COL)));
                person.setUpazilla(cursor.getString(cursor.getColumnIndex(UPZILLA_NAME_COL)));
                person.setUnitName(cursor.getString(cursor.getColumnIndex(LAY_R3_LIST_NAME)));
                person.setVillage(cursor.getString(cursor.getColumnIndex(LAY_R4_LIST_NAME_COL)));

                person.setCountryCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                person.setDistrictCode(cursor.getString(cursor.getColumnIndex("R_District")));
                person.setUpazillaCode(cursor.getString(cursor.getColumnIndex("R_Upazilla")));
                person.setUnitNameCode(cursor.getString(cursor.getColumnIndex("R_Union")));
                person.setVillageCode(cursor.getString(cursor.getColumnIndex("R_Village")));
                person.setRegID(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_HHID)));
                person.setRegDate(cursor.getString(cursor.getColumnIndex(REG_DATE_COL)));
                person.setName(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_HH_HEAD_NAME)));
                person.setSex(cursor.getString(cursor.getColumnIndex("Sex")));
                person.setHhSize(cursor.getString(cursor.getColumnIndex(HH_SIZE)));
                person.setLatitude(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_GPS_LAT)));
                person.setLongitude(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_GPS_LONG)));
                person.setAgLand(cursor.getString(cursor.getColumnIndex(AG_LAND)));
                person.setvStatus(cursor.getString(cursor.getColumnIndex(V_STATUS)));
                person.setmStatus(cursor.getString(cursor.getColumnIndex(M_STATUS)));
                person.setEntryBy(cursor.getString(cursor.getColumnIndex(ENTRY_BY)));
                person.setEntryDate(cursor.getString(cursor.getColumnIndex(ENTRY_DATE)));
                person.setVSLAGroup(cursor.getString(cursor.getColumnIndex(VSLA_GROUP)));

                person.setAddressCode(cursor.getString(cursor.getColumnIndex("addcode")));
                person.setAddressName(cursor.getString(cursor.getColumnIndex("addname")));


                // adding all into personList array


            }
            cursor.close();
            db.close();
        }


        // return contact list
        return person;
    }

    /**
     * Getting All Contacts
     * IT IS CALLED IN MW_RegisterViewRecord
     * IT SHOW ALL THE RECORD IN VIEW PAGE
     * todo: address code & address Name
     */
    public List<ListDataModel> getRegisteredData(String ext_village, final String hhIdOrHHName) {

        List<ListDataModel> personList = new ArrayList<ListDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "";

        if (ext_village != null) {

            if (!hhIdOrHHName.equals("")) {
                if (isAlpha(hhIdOrHHName)) {
                    query = SQLiteQuery.getRegisteredData_ifVillageExt_SearchByName_sql(ext_village, hhIdOrHHName);
                } else {
                    query = SQLiteQuery.getRegisteredData_ifVillageExt_SelectQuery(ext_village, hhIdOrHHName);
                }
            } else {
                query = SQLiteQuery.getRegisteredData_ifVillageExt_SelectQuery(ext_village, hhIdOrHHName);
            }


        } else {
            query = SQLiteQuery.getRegisteredData_ifVillage_NOT_Ext_SelectQuery();
        }

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ListDataModel person = new ListDataModel();

//                person.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_COL))));


                person.setCountryName(cursor.getString(cursor.getColumnIndex(COUNTRY_COUNTRY_NAME)));
                person.setDistrict(cursor.getString(cursor.getColumnIndex(DISTRICT_NAME_COL)));
                person.setUpazilla(cursor.getString(cursor.getColumnIndex(UPZILLA_NAME_COL)));
                person.setUnitName(cursor.getString(cursor.getColumnIndex(LAY_R3_LIST_NAME)));
                person.setVillage(cursor.getString(cursor.getColumnIndex(LAY_R4_LIST_NAME_COL)));

                person.setCountryCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                person.setDistrictCode(cursor.getString(cursor.getColumnIndex("R_District")));
                person.setUpazillaCode(cursor.getString(cursor.getColumnIndex("R_Upazilla")));
                person.setUnitNameCode(cursor.getString(cursor.getColumnIndex("R_Union")));
                person.setVillageCode(cursor.getString(cursor.getColumnIndex("R_Village")));
                person.setRegID(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_HHID)));
                person.setRegDate(cursor.getString(cursor.getColumnIndex(REG_DATE_COL)));
                person.setName(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_HH_HEAD_NAME)));
                person.setSex(cursor.getString(cursor.getColumnIndex("Sex")));
                person.setHhSize(cursor.getString(cursor.getColumnIndex(HH_SIZE)));
                person.setLatitude(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_GPS_LAT)));
                person.setLongitude(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_GPS_LONG)));
                person.setAgLand(cursor.getString(cursor.getColumnIndex(AG_LAND)));
                person.setvStatus(cursor.getString(cursor.getColumnIndex(V_STATUS)));
                person.setmStatus(cursor.getString(cursor.getColumnIndex(M_STATUS)));
                person.setEntryBy(cursor.getString(cursor.getColumnIndex(ENTRY_BY)));
                person.setEntryDate(cursor.getString(cursor.getColumnIndex(ENTRY_DATE)));
                person.setVSLAGroup(cursor.getString(cursor.getColumnIndex(VSLA_GROUP)));

                person.setAddressCode(cursor.getString(cursor.getColumnIndex("addcode")));
                person.setAddressName(cursor.getString(cursor.getColumnIndex("addname")));

                person.setRank(cursor.getString(cursor.getColumnIndex("wRank")));


                // adding all into personList array
                personList.add(person);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return contact list
        return personList;
    }

    public String getDistributionStatusFromDistributionTable(String cCode, String donorCode, String awardCode, String districtCode, String upCode, String unCode, String vilCode, String progCode, String srvCode, String distMonthCode, String fdpCode, String distFlag, String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "-";

        String status = "";
        selectQuery = SQLiteQuery.getDistributionStatusFromDistributionTableQuery(cCode, donorCode, awardCode, districtCode, upCode, unCode, vilCode, progCode, srvCode, distMonthCode, fdpCode, distFlag, id);

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                status = cursor.getString(cursor.getColumnIndex(DISTRIBUTION_STATUS_COL));
            }
            cursor.close();
            db.close();
        }
//        Log.d("All_4", "status:" + status + "\n length :" + status.length());
        if (!status.equals("null") && !(status.length() == 0)) {
            status = "R";
        } else {
            status = "-";
        }

        return status;
    }

    /**
     * Getting All Recodr from Service  table
     * IT IS CALLED IN MW_RegisterViewRecord
     * IT SHOW ALL THE RECORD IN Distribution Activity
     */
    public List<DistributionGridDataModel> getDistributionDataList(String cCode, String donorCode, String awardCode, String progCode, String srvOpMonthCode, String fdpCode, String searchMem) {

//        Log.d(TAG, "In get data Distribution ");

        List<DistributionGridDataModel> distributedList = new ArrayList<DistributionGridDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "";


        selectQuery = SQLiteQuery.getDistributionGridShowData(cCode, donorCode, awardCode, progCode, srvOpMonthCode, fdpCode, searchMem);

        Cursor c1 = db.rawQuery(selectQuery, null);

        if (c1 != null) {
            c1.moveToFirst();
        }

        int i = 0;

        if (c1 != null && c1.moveToFirst()) {
            do {
                DistributionGridDataModel distbutedPerson = new DistributionGridDataModel();

                distbutedPerson.setC_code(c1.getString(0));
                distbutedPerson.setDonorCode(c1.getString(c1.getColumnIndex("donor")));
                distbutedPerson.setAwardCode(c1.getString(c1.getColumnIndex("award")));
                distbutedPerson.setDistrictCode(c1.getString(c1.getColumnIndex("district")));
                distbutedPerson.setUpazillaCode(c1.getString(c1.getColumnIndex("upzella")));
                distbutedPerson.setUnitCode(c1.getString(c1.getColumnIndex("unite")));
                distbutedPerson.setVillageCode(c1.getString(c1.getColumnIndex("village")));

                if (donorCode.equals("01") && awardCode.equals("01") && progCode.equals("001")) {
                    if (i % 2 == 0) {
                        distbutedPerson.setRpt_id(c1.getString(c1.getColumnIndex("HHID")));
                        distbutedPerson.setRpt_name(c1.getString(c1.getColumnIndex("HhName")));
                        distbutedPerson.setServiceShortName("HHR");
                        distbutedPerson.setService_code("05");
                    } else {
                        distbutedPerson.setRpt_id(c1.getString(c1.getColumnIndex("MEMBERID")));
                        distbutedPerson.setRpt_name(c1.getString(c1.getColumnIndex("MemName")));
                        distbutedPerson.setServiceShortName(c1.getString(c1.getColumnIndex("srvName")));
                        distbutedPerson.setService_code(c1.getString(c1.getColumnIndex("service")));
                    }

                } else {


                    distbutedPerson.setRpt_id(c1.getString(c1.getColumnIndex("MEMBERID")));
                    distbutedPerson.setRpt_name(c1.getString(c1.getColumnIndex("MemName")));
                    distbutedPerson.setServiceShortName(c1.getString(c1.getColumnIndex("srvName")));
                    distbutedPerson.setService_code(c1.getString(c1.getColumnIndex("service")));
                }


                distbutedPerson.setProgram_code(c1.getString(c1.getColumnIndex("program")));
                distbutedPerson.setWd(c1.getString(c1.getColumnIndex("wd")));
                distributedList.add(distbutedPerson);
                i++;

            } while (c1.moveToNext());

            // Log.d("NIM", DatabaseUtils.dumpCursorToString(cursor));
            c1.close();
            db.close();

        }

        return distributedList;/// ther select per son to get service
    }

    public String getVoucherRefNoFromDistExted(String cCode, String discode, String upCode, String unCode, String vCode,
                                               String memId
            , String donorCode, String awardCode, String programCode, String serviceCode, String opMonthCode, String fdpCode) {

//        Log.d(TAG, "In get data Service ");

        String voiReference = "";
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "";


// Grid data will load from DistExtended table
        selectQuery = "SELECT " + VOUCHER_REFERENCE_NUMBER_COL +
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
                " AND  " + FDP_CODE_COL + " = '" + fdpCode + "' "
        ;


        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                voiReference = cursor.getString(cursor.getColumnIndex(VOUCHER_REFERENCE_NUMBER_COL));
            }
            cursor.close();
            db.close();
        }


        return voiReference;/// ther select per son to get service
    }


    /**
     * Getting All Recodr from Registration member And Registration And ProgramAssngService  table
     * IT IS CALLED IN MW_RegisterViewRecord
     * IT SHOW ALL THE RECORD IN SERVICE Activity
     */
    public List<VouItemServiceExtDataModel> getDistExtedVoucherDataList(String cCode, String discode, String upCode, String unCode, String vCode,
                                                                        String memId
            , String donorCode, String awardCode, String programCode, String serviceCode, String opMonthCode, String fdpCode, boolean dataExitstsInDistTable) {


        List<VouItemServiceExtDataModel> srvExtListItem = new ArrayList<VouItemServiceExtDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "";

        if (dataExitstsInDistTable) {
// todo : testing
// Grid data will load from DistExtended table
            selectQuery = SQLiteQuery.getDistributionExtedVoucherSummaryDataList_sql(cCode, discode, upCode, unCode, vCode, memId, donorCode, awardCode, programCode, serviceCode, opMonthCode, fdpCode);


        } else {
            // Grid data will load from SrvExtended table table


            selectQuery = SQLiteQuery.getServiceExtedVoucherSummaryDataList_sql(cCode, discode, upCode, unCode, vCode, memId, donorCode, awardCode, programCode, serviceCode, opMonthCode);


        }

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                VouItemServiceExtDataModel items = new VouItemServiceExtDataModel();

                items.setC_code(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                items.setDonor_code(cursor.getString(cursor.getColumnIndex(ADM_DONOR_CODE_COL)));
                items.setAward_code(cursor.getString(cursor.getColumnIndex(ADM_AWARD_CODE_COL)));
                items.setDistrictCode(cursor.getString(cursor.getColumnIndex(LAY_R_LIST_CODE_COL)));
                items.setUpazillaCode(cursor.getString(cursor.getColumnIndex(LAY_R2_LIST_CODE_COL)));
                items.setUnitCode(cursor.getString(cursor.getColumnIndex(LAY_R3_LIST_CODE_COL)));
                items.setVillageCode(cursor.getString(cursor.getColumnIndex(LAY_R4_LIST_CODE_COL)));
                items.setProgram_code(cursor.getString(cursor.getColumnIndex(ADM_PROG_CODE_COL)));
                items.setService_code(cursor.getString(cursor.getColumnIndex(ADM_SRV_CODE_COL)));
                items.setOpMontheCode(cursor.getString(cursor.getColumnIndex(OP_MONTH_CODE_COL)));
                items.setItemName(cursor.getString(cursor.getColumnIndex("ItemName")));
                items.setMeasurments(cursor.getString(cursor.getColumnIndex("measerment")));
//                items.setCheckBox(cursor.getString(cursor.getColumnIndex("checkedItem")).equals("False") ? false : true);
                items.setVoItmUnit(cursor.getString(cursor.getColumnIndex(VOUCHER_REFERENCE_NUMBER_COL)));
                items.setVoItmUnit(cursor.getString(cursor.getColumnIndex(VOUCHER_UNIT_COL)));
                items.setVoItmSpec(cursor.getString(cursor.getColumnIndex(VOUCHER_ITEM_SPEC_COL)));


//                Log.d(TAG, " Dist Extended  list data" + cursor.getString(1) + " , " + cursor.getString(2) + " , " + cursor.getString(3) + " , "
//                        + cursor.getString(4) + " , " + cursor.getString(5) + " , " +
//                        cursor.getString(6) + " , " + cursor.getString(7) + " , " + cursor.getString(8) + " , " + cursor.getString(9));
//

                srvExtListItem.add(items);

            } while (cursor.moveToNext());


            cursor.close();
            db.close();
        }


        return srvExtListItem;/// ther select per son to get service
    }


    /**
     * Getting All Recodr from Registration member And Registration And ProgramAssngService  table
     * IT IS CALLED IN MW_RegisterViewRecord
     * IT SHOW ALL THE RECORD IN SERVICE Activity
     */
    public List<VouItemServiceExtDataModel> getSrvExtedVoucherDataList(String cCode, String discode, String upCode, String unCode, String vCode,
                                                                       String hhId, String memId
            , String donorCode, String awardCode, String programCode, String serviceCode, String opMonthCode) {

        //  Log.d(TAG, "In get data Service ");

        List<VouItemServiceExtDataModel> itemList = new ArrayList<VouItemServiceExtDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "";


        selectQuery = "select (Select " + ITEM_NAME_COL + " from " + VOUCHER_ITEM_TABLE +

                " where " + CATEGORY_CODE_COL + " || " + ITEM_CODE_COL + " = substr(VOCPI." + VOUCHER_ITEM_SPEC_COL + ",0,8))" +
                " ||'-'|| (Select " + UNITE_MEAS_COL + " ||' '|| " + MEASE_TITLE_COL + " from " + VOUCHER_ITEM__MEAS_TABLE +

                " where " + MEAS_R_CODE_COL + " = VOCPI." + MEAS_R_CODE_COL + " ) as item " +
                " ,   (case when SrET." + VOUCHER_ITEM_SPEC_COL + " is null then 'False' else 'True' end ) as checkedItem " +
                " , SrET." + VOUCHER_UNIT_COL + " AS  " + VOUCHER_UNIT_COL +
                " , VOCPI." + VOUCHER_ITEM_SPEC_COL + " AS " + VOUCHER_ITEM_SPEC_COL +
                " from " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + " as VOCPI" +
                " left join  " + SERVICE_EXTENDED_TABLE + " as  SrET" +
                "       on " +
                " SrET." + ADM_COUNTRY_CODE_COL + " = VOCPI." + ADM_COUNTRY_CODE_COL +

                " and  SrET." + ADM_AWARD_CODE_COL + " = VOCPI." + ADM_AWARD_CODE_COL
                + " and        SrET." + ADM_DONOR_CODE_COL + " = VOCPI." + ADM_DONOR_CODE_COL +
                " and        SrET." + VOUCHER_ITEM_SPEC_COL + " = VOCPI." + VOUCHER_ITEM_SPEC_COL +
                " and        SrET." + FDP_MASTER_LAY_R1_LIST_CODE_COL + " = '" + discode + "'" +
                " and SrET." + LAY_R2_LIST_CODE_COL + " = '" + upCode + "'" +
                " and SrET." + LAY_R3_LIST_CODE_COL + " = '" + unCode + "'" +
                " and SrET." + LAY_R4_LIST_CODE_COL + " = '" + vCode + "'" +
                " and SrET." + HHID_COL + " = '" + hhId + "'" +
                " and SrET." + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + memId + "'" +
                " and SrET." + PROG_CODE_COL + " = '" + programCode + "'" +
                " and SrET." + SRV_CODE_COL + " = '" + serviceCode + "'" +
                " and SrET." + OPERATION_CODE_COL + " = '2'" +      // opcode 2 mean s service
                " and SrET." + OP_MONTH_CODE_COL + " = '" + opMonthCode + "'" +
                " where VOCPI." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " and VOCPI." + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                " and VOCPI." + ADM_AWARD_CODE_COL + " = '" + awardCode + "'" +
                " and VOCPI." + ADM_SRV_CODE_COL + " = '" + serviceCode + "'";


        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                VouItemServiceExtDataModel items = new VouItemServiceExtDataModel();

                items.setItemName(cursor.getString(cursor.getColumnIndex("item")));
                items.setCheckBox(cursor.getString(cursor.getColumnIndex("checkedItem")).equals("False") ? false : true);
                items.setVoItmUnit(cursor.getString(cursor.getColumnIndex(VOUCHER_UNIT_COL)));
                items.setVoItmSpec(cursor.getString(cursor.getColumnIndex(VOUCHER_ITEM_SPEC_COL)));


            /*    Log.d(TAG, " Voucher  list data" + cursor.getString(1) + " , " + cursor.getString(2) + " , " + cursor.getString(3) + " , "
                        + cursor.getString(4) + " , " + cursor.getString(5) + " , " +
                        cursor.getString(6) + " , " + cursor.getString(7) + " , " + cursor.getString(8) + " , " + cursor.getString(9));*/
                itemList.add(items);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return itemList;/// ther select per son to get service
    }

    public boolean isDataExitedDistExtendedTable(String cCode, String discode, String upCode, String unCode, String vCode,
                                                 String memId, String donorCode, String awardCode, String programCode, String serviceCode, String opMonthCode, String fdpCode) {

        boolean dataExits = false;

        String selectDelete = " Select * from " + DISTRIBUTION_EXTENDED_TABLE + " where " +
                SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.LAY_R_LIST_CODE_COL + " = '" + discode + "' "
                + " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upCode + "' "
                + " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unCode + "' "
                + " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + vCode + "' "
                + " AND " + SQLiteHandler.ADM_PROG_CODE_COL + " = '" + programCode + "' "
                + " AND " + SQLiteHandler.ADM_SRV_CODE_COL + " = '" + serviceCode + "' "
                + " AND " + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMonthCode + "' "
                + " AND " + SQLiteHandler.MEM_ID_15_D_COL + " = '" + memId + "' "
                + " AND " + SQLiteHandler.FDP_CODE_COL + " = '" + fdpCode + "' ";
        // + " AND " + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " = '" + voItmSpec + "' ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectDelete, null);

        if (cursor.getCount() > 0) {
            dataExits = true;
        }
        cursor.close();
        db.close();


        return dataExits;


    }


    public void deleteFromDistExtendedTable(String cCode, String discode, String upCode, String unCode, String vCode,
                                            String memId, String donorCode, String awardCode, String programCode, String serviceCode, String opMonthCode, String fdpCode) {


        String selectDelete = " Delete from " + DISTRIBUTION_EXTENDED_TABLE + " where " +
                SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " = '" + discode + "' "
                + " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upCode + "' "
                + " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unCode + "' "
                + " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + vCode + "' "
                // + " AND " + SQLiteHandler.HHID_COL + " = '" + hhId + "' "
                + " AND " + SQLiteHandler.MEM_ID_15_D_COL + " = '" + memId + "' "
                + " AND " + SQLiteHandler.PROG_CODE_COL + " = '" + programCode + "' "
                + " AND " + SQLiteHandler.SRV_CODE_COL + " = '" + serviceCode + "' "
                + " AND " + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMonthCode + "' "
                + " AND " + SQLiteHandler.FDP_CODE_COL + " = '" + fdpCode + "' ";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(selectDelete);

//        Log.d(TAG, " delete from Srv Extended table  row ");
        db.close();

    }

    public void deleteFromServiceExtendedTable(String cCode, String discode, String upCode, String unCode, String vCode,
                                               String hhId, String memId, String donorCode, String awardCode, String programCode, String serviceCode, String opMonthCode/*, String voItmSpec*/) {


        String selectDelete = " Delete from " + SERVICE_EXTENDED_TABLE + " where " +
                SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.FDP_MASTER_LAY_R1_LIST_CODE_COL + " = '" + discode + "' "
                + " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upCode + "' "
                + " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unCode + "' "
                + " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + vCode + "' "
                + " AND " + SQLiteHandler.HHID_COL + " = '" + hhId + "' "
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + memId + "' "
                + " AND " + SQLiteHandler.PROG_CODE_COL + " = '" + programCode + "' "
                + " AND " + SQLiteHandler.SRV_CODE_COL + " = '" + serviceCode + "' "
                + " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '2' "
                + " AND " + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMonthCode + "' ";
        //  + " AND " + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " = '" + voItmSpec + "' "
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(selectDelete);

//        Log.d(TAG, " delete from Srv Extended table  row ");
        db.close();

    }


    public boolean isDataExitedServiceExtendedTable(String cCode, String discode, String upCode, String unCode, String vCode,
                                                    String hhId, String memId, String donorCode, String awardCode, String programCode, String serviceCode, String opMonthCode/*, String voItmSpec*/) {

        boolean dataExits = false;

        String selectDelete = " Select * from " + SERVICE_EXTENDED_TABLE + " where " +
                SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.FDP_MASTER_LAY_R1_LIST_CODE_COL + " = '" + discode + "' "
                + " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upCode + "' "
                + " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unCode + "' "
                + " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + vCode + "' "
                + " AND " + SQLiteHandler.HHID_COL + " = '" + hhId + "' "
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + memId + "' "
                + " AND " + SQLiteHandler.PROG_CODE_COL + " = '" + programCode + "' "
                + " AND " + SQLiteHandler.SRV_CODE_COL + " = '" + serviceCode + "' "
                + " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '2' "
                + " AND " + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMonthCode + "' ";
        // + " AND " + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " = '" + voItmSpec + "' ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectDelete, null);

        if (cursor.getCount() > 0) {
            dataExits = true;
        }
        cursor.close();
        db.close();


        return dataExits;


    }

    public List<VouItemServiceExtDataModel> getDefaultVoucherItemRespectToProgram(String cCode, String donorCode, String awardCode, String progCode, String srvCode) {
        List<VouItemServiceExtDataModel> itemList = new ArrayList<VouItemServiceExtDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();


        String sql = "SELECT ( " + SQLiteQuery.get_VOItmUnitMeas(VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + MEAS_R_CODE_COL) + " ) AS Unit " +
                " , " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL
                + " , " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + UNITE_COST_COL
                + " FROM  " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + ADM_PROG_CODE_COL + " = '" + progCode + "' "
                + " AND " + ADM_SRV_CODE_COL + " = '" + srvCode + "' ";

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                VouItemServiceExtDataModel items = new VouItemServiceExtDataModel();

                items.setVoItmUnit(cursor.getString(0));
                items.setVoItmSpec(cursor.getString(1));
                items.setVoItemCost(cursor.getString(2));


//                Log.d("FAll", " Voucher  list data" + cursor.getString(0) + " , " + cursor.getString(1));
                /* + " , " + cursor.getString(3) + " , "
                        + cursor.getString(4) + " , " + cursor.getString(5) + " , " +
                        cursor.getString(6) + " , " + cursor.getString(7) + " , " + cursor.getString(8) + " , " + cursor.getString(9));*/
                itemList.add(items);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return itemList;/// ther select per son to get service

    }


    /* added by service */

    /**
     * @param cCode       country Code
     * @param discode     layer1 Code
     * @param upCode      layer2 Code
     * @param unCode      layer3 Code
     * @param vCode       layer4 Code
     * @param hhId        house hold id
     * @param memId       member id
     * @param donorCode   donor code
     * @param awardCode   awardCode
     * @param prgCode     programCode
     * @param srvCode     serviceCode
     * @param opCode      operation Code
     * @param opMonthCode operation Month Code
     * @param voItmSpec   voucher Item Specefic
     * @param voUnit      Voucher unite
     * @param voRefeNo    Voucher Reference No
     * @param voItmCost   Voucher Item Cost
     * @param entryBy     seasson manager
     * @param entryDate   seasson Date
     * @param is_online   is it come from online
     */
    public void addServiceExtendedTable(String cCode, String discode, String upCode, String unCode, String vCode, String hhId, String memId, String donorCode, String awardCode, String prgCode, String srvCode, String opCode, String opMonthCode, String voItmSpec, String voUnit, String voRefeNo, String voItmCost, String distFlag, String entryBy, String entryDate, String is_online) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(LAY_R1_LIST_CODE_COL, discode);
        values.put(LAY_R2_LIST_CODE_COL, upCode);
        values.put(LAY_R3_LIST_CODE_COL, unCode);
        values.put(LAY_R4_LIST_CODE_COL, vCode);
        values.put(HHID_COL, hhId);
        values.put(REG_N_ASSIGN_PROG_SRV_HH_MEM_ID, memId);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(PROG_CODE_COL, prgCode);
        values.put(SRV_CODE_COL, srvCode);
        values.put(OPERATION_CODE_COL, opCode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(VOUCHER_ITEM_SPEC_COL, voItmSpec);
        values.put(VOUCHER_UNIT_COL, voUnit);
        values.put(VOUCHER_REFERENCE_NUMBER_COL, voRefeNo);
        values.put(VOUCHER_ITEM_COST_COL, voItmCost);
        values.put(DIST_FLAG_COL, distFlag);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

//        values.put(SYNC_COL, is_online); // Sync Status


        // Inserting Row
        long id = db.insert(SERVICE_EXTENDED_TABLE, null, values);
        db.close(); // Closing database connection

    /*    Log.d(TAG, "New Service Extended  data added  Service Extended Table: " + id);*/
    }


    public String getComunityGroupNameFromServiceTable(String cCode, String donorCode, String awardCode,
                                                       String distCode, String upCode, String unCode, String vCode
            , String hhId, String memId, String progCode, String srvCode) {
        String groupName = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "Select " + COMMUNITY_GROUP_TABLE + "." + GROUP_NAME_COL + " From " + SERVICE_TABLE
                + " " + " inner join " + COMMUNITY_GROUP_TABLE
                + " on " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + COMMUNITY_GROUP_TABLE + "." + ADM_COUNTRY_CODE_COL
                + " and " + SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " = " + COMMUNITY_GROUP_TABLE + "." + ADM_AWARD_CODE_COL
                + " and " + SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " = " + COMMUNITY_GROUP_TABLE + "." + ADM_DONOR_CODE_COL
                + " and " + SERVICE_TABLE + "." + PROG_CODE_COL + " = " + COMMUNITY_GROUP_TABLE + "." + ADM_PROG_CODE_COL
                + " and  " + SERVICE_TABLE + "." + GROUP_CODE_COL + " = " + COMMUNITY_GROUP_TABLE + "." + GROUP_CODE_COL
                + " Where "
                + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " and " + SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " and " + SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " = '" + donorCode + "'"
                + " and " + SERVICE_TABLE + "." + LAY_R_LIST_CODE_COL + " = '" + distCode + "'"
                + " and " + SERVICE_TABLE + "." + LAY_R2_LIST_CODE_COL + " = '" + upCode + "'"
                + " and " + SERVICE_TABLE + "." + LAY_R3_LIST_CODE_COL + " = '" + unCode + "'"
                + " and " + SERVICE_TABLE + "." + LAY_R4_LIST_CODE_COL + " = '" + vCode + "'"
                + " and " + SERVICE_TABLE + "." + HHID_COL + " = '" + hhId + "'"
                + " and " + SERVICE_TABLE + "." + MEM_ID_COL + " = '" + memId + "'"
                + " and " + SERVICE_TABLE + "." + PROG_CODE_COL + " = '" + progCode + "'"
                + " and " + SERVICE_TABLE + "." + SRV_CODE_COL + " = '" + srvCode + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                groupName = cursor.getString(0);
                if (groupName.equals("null"))
                    groupName = "";
            }
        }

        return groupName;

    }

    /**
     * This method get the short name Of the Program <p>
     *
     * @param awardCode Award Code
     * @param donorCode Donor Code
     * @param ProgCode  Program Code
     * @return program Short name
     */

    public String getProgramShortName(String awardCode, String donorCode, String ProgCode) {

        String progSName = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + PROGRAM_SHORT_NAME_COL + " FROM " + ADM_PROGRAM_MASTER_TABLE
                + " WHERE " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND  " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND  " + ADM_PROG_CODE_COL + " = '" + ProgCode + "' ";

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                progSName = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }
        return progSName;

    }

    /**
     * @param progCode Program Code
     * @param srvCode  Service Cod e
     * @return Service  Short name
     */

    public String getServiceShortName(String progCode, String srvCode) {
        String srvSName = "";
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT " + SERVICE_MASTER_SERVICE_SHORT_NAME_COL
                + " FROM " + SERVICE_MASTER_TABLE
                + " WHERE  " + ADM_PROG_CODE_COL + " = '" + progCode + "' "
                + " AND " + ADM_SRV_CODE_COL + " = '" + srvCode + "' ";

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                srvSName = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }
        return srvSName;
    }

    public String get_MemberMinSrvDate(String cCode, String donorCode, String awardCode,
                                       String distCode, String upCode, String unCode, String vCode
            , String hhId, String memId, String progCode, String srvCode) {

        String srvMinDate = "";
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT " + SRV_MIN_DATE_COL + " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE
                + " WHERE " + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND  " + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + distCode + "'"
                + " AND  " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upCode + "'"
                + " AND  " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unCode + "'"
                + " AND  " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + vCode + "'"
                + " AND  " + SQLiteHandler.HHID_COL + " = '" + hhId + "'"
                + " AND  " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + memId + "'"
                + " AND  " + SQLiteHandler.ADM_DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND  " + SQLiteHandler.ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND  " + SQLiteHandler.PROG_CODE_COL + " = '" + progCode + "'"
                + " AND  " + SQLiteHandler.SRV_CODE_COL + " = '" + srvCode + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                srvMinDate = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }
        return srvMinDate;

    }

    public void updateSrvMinDate(String cCode, String donorCode, String awardCode, String distCode, String upCode, String unCode, String vCode
            , String hhId, String memId, String progCode, String srvCode, String srvMinDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND  " + SQLiteHandler.LAY_R_LIST_CODE_COL + " = '" + distCode + "'"
                + " AND  " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upCode + "'"
                + " AND  " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unCode + "'"
                + " AND  " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + vCode + "'"
                + " AND  " + SQLiteHandler.HHID_COL + " = '" + hhId + "'"
                + " AND  " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + memId + "'"
                + " AND  " + SQLiteHandler.ADM_DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND  " + SQLiteHandler.ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND  " + SQLiteHandler.PROG_CODE_COL + " = '" + progCode + "'"
                + " AND  " + SQLiteHandler.SRV_CODE_COL + " = '" + srvCode + "'";
        ContentValues values = new ContentValues();
        values.put(SRV_MIN_DATE_COL, srvMinDate);
        int id = db.update(REG_N_ASSIGN_PROG_SRV_TABLE, values, sql, null);
//        Log.d("NI2", "id affected:" + id);
    }


    public String get_MemberMaxSrvDate(String cCode, String donorCode, String awardCode,
                                       String distCode, String upCode, String unCode, String vCode
            , String hhId, String memId, String progCode, String srvCode) {

        String srvMaxDate = "";
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT " + SRV_MAX_DATE_COL + " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE
                + " WHERE " + SQLiteHandler.ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND  " + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + distCode + "'"
                + " AND  " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upCode + "'"
                + " AND  " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unCode + "'"
                + " AND  " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + vCode + "'"
                + " AND  " + SQLiteHandler.HHID_COL + " = '" + hhId + "'"
                + " AND  " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + memId + "'"
                + " AND  " + SQLiteHandler.ADM_DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND  " + SQLiteHandler.ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND  " + SQLiteHandler.PROG_CODE_COL + " = '" + progCode + "'"
                + " AND  " + SQLiteHandler.SRV_CODE_COL + " = '" + srvCode + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                srvMaxDate = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }
        return srvMaxDate;

    }

    public void updateSrvMaxDate(String cCode, String donorCode, String awardCode, String distCode, String upCode, String unCode, String vCode
            , String hhId, String memId, String progCode, String srvCode, String srvMaxDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = SQLiteQuery.updateSrvMaxDate_sql(cCode, donorCode, awardCode, distCode, upCode, unCode, vCode
                , hhId, memId, progCode, srvCode, srvMaxDate);
        ContentValues values = new ContentValues();
        values.put(SRV_MAX_DATE_COL, srvMaxDate);
        int id = db.update(REG_N_ASSIGN_PROG_SRV_TABLE, values, sql, null);
//        Log.d("NI2", "id affected:" + id);
    }


    // todo: impelements Graduatae Date to load data

    public List<ServiceDataModel> getFFAMemberListForService(String cCode, String donorCode, String awardCode, String programCode,
                                                             String serviceCode, String mm_SearchId, String opCode, String opMCode, String groupCode, String distFlag) {

//        Log.d(TAG, "In get data Service ");

        List<ServiceDataModel> sList = new ArrayList<ServiceDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "";


        selectQuery = SQLiteQuery.getFFAMemberListForServiceSelectQuery(cCode, donorCode, awardCode,
                programCode, serviceCode, opCode, opMCode, mm_SearchId, groupCode, distFlag);


        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ServiceDataModel sperson = new ServiceDataModel();

                sperson.setHHID(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                sperson.setHh_name(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_HH_HEAD_NAME)));
                sperson.setMemberId(cursor.getString(cursor.getColumnIndex(HH_MEM_ID)));
                sperson.setHh_mm_name(cursor.getString(cursor.getColumnIndex("memName")));

                sperson.setC_code(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                sperson.setDonor_code(cursor.getString(cursor.getColumnIndex(ADM_DONOR_CODE_COL)));
                sperson.setAward_code(cursor.getString(cursor.getColumnIndex(ADM_AWARD_CODE_COL)));
                sperson.setDistrictCode(cursor.getString(cursor.getColumnIndex(LAY_R_LIST_CODE_COL)));
                sperson.setUpazillaCode(cursor.getString(cursor.getColumnIndex(LAY_R2_LIST_CODE_COL)));
                sperson.setUnitCode(cursor.getString(cursor.getColumnIndex(LAY_R3_LIST_CODE_COL)));
                sperson.setVillageCode(cursor.getString(cursor.getColumnIndex(LAY_R4_LIST_CODE_COL)));
                sperson.setHHID(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                sperson.setProgram_code(cursor.getString(cursor.getColumnIndex(ADM_PROG_CODE_COL)));
                sperson.setService_code(cursor.getString(cursor.getColumnIndex(ADM_SRV_CODE_COL)));
                sperson.setGetSrvMemCount(cursor.getString(cursor.getColumnIndex("SrvRecieved")));
                sperson.setNewID(cursor.getString(cursor.getColumnIndex("NewID")));
                sperson.setWorkingDay(cursor.getString(cursor.getColumnIndex("WD")));


/*                Log.d(TAG, " Service list data" + cursor.getString(1) + " , " + cursor.getString(2) + " , " + cursor.getString(3) + " , "
                        + cursor.getString(4) + " , " + cursor.getString(5) + " , " +
                        cursor.getString(6) + " , " + cursor.getString(7) + " , " + cursor.getString(8) + " , " + cursor.getString(9));*/
                sList.add(sperson);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return sList;/// ther select per son to get service
    }


    /**
     * This method
     * Getting All Record from Registration member And Registration And ProgramAssngService  table
     * IT IS CALLED IN MW_RegisterViewRecord
     * IT SHOW ALL THE RECORD IN SERVICE Activity
     *
     * @param cCode       Country Code
     * @param donorCode   donor Code
     * @param awardCode   award Code
     * @param programCode programCode
     * @param srvCode     serviceCode
     * @param mm_SearchId mm_SearchId
     * @param opCode      Operation Code For Service The Op Code is 2
     * @param opMCode     OpmMonth Code For Service
     * @param groupCode   Community Group Code
     * @return member list
     */
    public List<ServiceDataModel> getRptMemberServiceList(String cCode, String donorCode, String awardCode, String programCode, String srvCode, String mm_SearchId, String opCode, String opMCode, String groupCode, String distFlag, String grpLayR1Code, String grpLayR2Code, String grpLayR3Code) {


        List<ServiceDataModel> sList = new ArrayList<ServiceDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "";
        switch (programCode) {
            case MCHN:
                switch (srvCode) {

                    case CU2:
                        selectQuery = SQLiteQuery.getRptMemberServiceList_cu2_sql(cCode, donorCode, awardCode, programCode, srvCode, opCode, opMCode, mm_SearchId, groupCode, distFlag, grpLayR1Code, grpLayR2Code, grpLayR3Code);
                        break;
                    case CA2:

                        selectQuery = SQLiteQuery.getRptMemberServiceList_ca2_sql(cCode, donorCode, awardCode, programCode, srvCode, opCode, opMCode, mm_SearchId, groupCode, distFlag, grpLayR1Code, grpLayR2Code, grpLayR3Code);

                        break;

                    case LM:
                        selectQuery = SQLiteQuery.getRptMemberServiceList_lm_sql(cCode, donorCode, awardCode, programCode, srvCode, opCode, opMCode, mm_SearchId, groupCode, distFlag, grpLayR1Code, grpLayR2Code, grpLayR3Code);

                        break;

                    case PW:
                        selectQuery = SQLiteQuery.getRptMemberServiceList_pw_sql(cCode, donorCode, awardCode, programCode, srvCode, opCode, opMCode, mm_SearchId, groupCode, distFlag, grpLayR1Code, grpLayR2Code, grpLayR3Code);

                        break;

                    default:
                        selectQuery = SQLiteQuery.getRptMemberServiceList_sql(cCode, donorCode, awardCode,
                                programCode, srvCode, opCode, opMCode, mm_SearchId, groupCode, distFlag);
                        break;
                }
                break;
            default:
                selectQuery = SQLiteQuery.getRptMemberServiceList_sql(cCode, donorCode, awardCode,
                        programCode, srvCode, opCode, opMCode, mm_SearchId, groupCode, distFlag);
                break;
        }
// for test
//        Log.d("ERROR192", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ServiceDataModel sperson = new ServiceDataModel();

                sperson.setHHID(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                sperson.setHh_name(cursor.getString(cursor.getColumnIndex(REGISTRATION_TABLE_HH_HEAD_NAME)));
                sperson.setMemberId(cursor.getString(cursor.getColumnIndex(HH_MEM_ID)));
                sperson.setHh_mm_name(cursor.getString(cursor.getColumnIndex("memName")));

                sperson.setC_code(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                sperson.setDonor_code(cursor.getString(cursor.getColumnIndex(ADM_DONOR_CODE_COL)));
                sperson.setAward_code(cursor.getString(cursor.getColumnIndex(ADM_AWARD_CODE_COL)));
                sperson.setDistrictCode(cursor.getString(cursor.getColumnIndex(LAY_R1_LIST_CODE_COL)));
                sperson.setUpazillaCode(cursor.getString(cursor.getColumnIndex(LAY_R2_LIST_CODE_COL)));
                sperson.setUnitCode(cursor.getString(cursor.getColumnIndex(LAY_R3_LIST_CODE_COL)));
                sperson.setVillageCode(cursor.getString(cursor.getColumnIndex(LAY_R4_LIST_CODE_COL)));
                sperson.setHHID(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                sperson.setProgram_code(cursor.getString(cursor.getColumnIndex(PROG_CODE_COL)));
                sperson.setService_code(cursor.getString(cursor.getColumnIndex(SRV_CODE_COL)));
                sperson.setGetSrvMemCount(cursor.getString(cursor.getColumnIndex("SrvRecieved")));
                sperson.setNewID(cursor.getString(cursor.getColumnIndex("NewID")));


                sList.add(sperson);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return sList;/// ther select per son to get service
    }

    /**
     * 2015-10-12
     * Faisal Mohammad
     * check Service date before insert the service
     */
    public String getLastServiceDate(ServiceDataModel si) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.getLastServiceDateQuery(si);

        Cursor cursor = db.rawQuery(selectQuery, null);
        String lastdate = "";
        if (cursor.moveToFirst()) {
            lastdate = cursor.getString(cursor.getColumnIndex(SERVICE_TABLE_SERVICE_DT_COL));
        }
        cursor.close();
        db.close();
        return lastdate;
    }


    public String get_VOUnitCost(String cCode, String donorCode, String awardCode, String progCode, String srvCode, String vOItmSpec) {
        SQLiteDatabase db = this.getReadableDatabase();
        String cost = "";
        String selectQuery = "Select " + UNITE_COST_COL + " from " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE +
                " where " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " and " + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                " and " + ADM_AWARD_CODE_COL + " = '" + awardCode + "'" +
                " and " + ADM_PROG_CODE_COL + " = '" + progCode + "'" +
                " and " + ADM_SRV_CODE_COL + " = '" + srvCode + "'" +
                " and " + VOUCHER_ITEM_SPEC_COL + " = '" + vOItmSpec + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                cost = cursor.getString(cursor.getColumnIndex(UNITE_COST_COL));
            }
            cursor.close();
            db.close();
        }
        return cost;

    }

    public List<SummaryModel> getTotalRecords(String CountryId) {

        List<SummaryModel> listSummary = new ArrayList<SummaryModel>();

        int records = 0;
        String vill_name = "";

        //String query = "SELECT " + LAY_R4_LIST_NAME_COL + " AS Vill_Name, COUNT(*) AS records FROM " + REG_N_HH_TABLE + " GROUP BY " + LAY_R4_LIST_NAME_COL;
/*
        *****NOR VAI QUEY
        String query = "SELECT v." + LAY_R4_LIST_CODE_COL +" AS v_code,"+
                " v."+ LAY_R4_LIST_NAME_COL +" AS Vill_Name, COUNT(*) AS records FROM " + REG_N_HH_TABLE + " AS r LEFT JOIN " + VILLAGE_TABLE + " AS v ON r."+ LAY_R4_LIST_NAME_COL +"=v."+ LAY_R4_LIST_CODE_COL +"  GROUP BY v."+ LAY_R4_LIST_NAME_COL;

*/
        // POP CODE
        String query = "SELECT " + " v." + ADM_COUNTRY_CODE_COL + " || '' ||  v." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || '' || v." + LAY_R2_LIST_CODE_COL + " || '' || v." +
                LAY_R3_LIST_CODE_COL + " || '' || v." + LAY_R4_LIST_CODE_COL + " AS v_code," +
                " v." + LAY_R4_LIST_NAME_COL + " AS Vill_Name," +
                " COUNT(" + REGISTRATION_TABLE_HHID + ") AS records FROM " + VILLAGE_TABLE + " AS v" +
                " LEFT JOIN " + REG_N_HH_TABLE + " AS r" +
                " ON r." + ADM_COUNTRY_CODE_COL + "=v." + ADM_COUNTRY_CODE_COL + " AND " +
                "r." + LAY_R1_LIST_CODE + "=v." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " AND " +

                "r." + REGISTRATION_TABLE_UPZILLA_CODE_COL + "=v." + LAY_R2_LIST_CODE_COL + " AND " +
                "r." + REGISTRATION_TABLE_UNION_CODE_COL + "=v." + LAY_R3_LIST_CODE_COL + " AND " +
                "r." + REGISTRATION_TABLE_VILLAGE_CODE_COL + "=v." + LAY_R4_LIST_CODE_COL +

                " WHERE v." + ADM_COUNTRY_CODE_COL + "='" + CountryId + "'" + /** send the no of village for selected country added by Faisal Mohammad*/
                "  GROUP BY v." + ADM_COUNTRY_CODE_COL + ",v." + MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + ",v." + LAY_R2_LIST_CODE_COL + ",v." + LAY_R3_LIST_CODE_COL + ",v." + LAY_R4_LIST_CODE_COL;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                SummaryModel summary = new SummaryModel();

                summary.setVillageCode(cursor.getString(cursor.getColumnIndex("v_code")));
                summary.setVillageName(cursor.getString(cursor.getColumnIndex("Vill_Name")));
                summary.setRecords(cursor.getInt(cursor.getColumnIndex("records")));

                //records = cursor.getInt(cursor.getColumnIndex("records"));
                String checkedId = cursor.getString(cursor.getColumnIndex("v_code"));
                int noOfRecords = cursor.getInt(cursor.getColumnIndex("records"));
                if ((checkedId != null) && (noOfRecords > 0)) { /// IF village RECORD IS NOT ZERRO THAN IT INSERT
                    listSummary.add(summary);
                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        return listSummary;
    }

    public String getNextMemberID(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String hhID) {

        String query = SQLiteQuery.getNextMemberID_sql(str_c_code, str_district, str_upazilla, str_union, str_village, hhID);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                next_id = cursor.getString(cursor.getColumnIndex("max_rec"));

            } while (cursor.moveToNext());
        }

        cursor.close();

        return next_id;
    }

    public String getNextHouseHoldID(String c_code, String distID, String upID, String unit, String villID) {

        //String query = "SELECT COUNT(*) AS max_rec FROM " + REG_N_HH_TABLE + " WHERE "
        String query = "SELECT " + REGISTRATION_TABLE_HHID + " AS max_rec FROM " + REG_N_HH_TABLE + " WHERE "
                + ADM_COUNTRY_CODE_COL + "='" + c_code + "' AND "
                + LAY_R1_LIST_CODE + "='" + distID + "' AND "
                + REGISTRATION_TABLE_UPZILLA_CODE_COL + "='" + upID + "' AND "
                + REGISTRATION_TABLE_UNION_CODE_COL + "='" + unit + "' AND "
                + REGISTRATION_TABLE_VILLAGE_CODE_COL + "='" + villID + "'" +
                " ORDER BY " + REGISTRATION_TABLE_HHID + " DESC LIMIT 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // EDITED BY POP
        if (cursor.moveToFirst()) {

            next_id = cursor.getString(cursor.getColumnIndex("max_rec"));

        }


        cursor.close();
        db.close();

        return next_id;
    }

    public String getAutoIncrementID(String tableName) {

        String query = "SELECT * FROM " + SQLITE_SEQUENCE + " WHERE " + TABLE_NAME + "='" + tableName + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                next_id = cursor.getString(cursor.getColumnIndex("seq"));

            } while (cursor.moveToNext());
        }

        cursor.close();

        return next_id;
    }

    public String getMemberID(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String hhID) {

        String memberID = "";

        String next_id = getNextMemberID(str_c_code, str_district, str_upazilla, str_union, str_village, hhID);

        if (!next_id.isEmpty()) { // if next id is not empty
            Integer temp_id = Integer.parseInt(next_id); // convert it to int
            temp_id += 1;
            next_id = temp_id.toString();
        } else {
            next_id = "01";
        }

        int id_len = next_id.length();

        if (id_len < 2)
            memberID = "0" + next_id;
        else
            memberID = next_id;

        return "" + memberID;
    }

    public String getRegistrationID(String c_code, String distID, String upID, String unit, String villID) {

        String registrationID = "";

        String next_id = getNextHouseHoldID(c_code, distID, upID, unit, villID);

        if (!next_id.isEmpty()) {
            Integer temp_id = Integer.parseInt(next_id);
            temp_id++;
            next_id = temp_id.toString();
        } else {
            next_id = "1";
        }

        int id_len = next_id.length();


        registrationID = getPadding(id_len, next_id);

        return registrationID;
    }

    private String getPadding(int id_len, String next_id) {

        String padded_id = "";

        if (id_len > 0) {
            int pad = ID_LENGTH - id_len;

            for (int i = 0; i < pad; i++) {
                padded_id += "0";
            }
            padded_id = padded_id + next_id;
        } else {
            padded_id = "000001";
        }

        return padded_id;
    }

    // todo Error in the db
    // // TODO: 10/23/2016  app Crash
    public String getNextGroupId(String cCode, String donorCode, String awardCode, String progCode, String layR1Code, String layR2Code, String layR3Code) {
        String grpCode = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT max(" + GROUP_CODE_COL + ") FROM " + COMMUNITY_GROUP_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND " + ADM_PROG_CODE_COL + " = '" + progCode + "'"
                + " AND " + LAY_R1_CODE_COL + " = '" + layR1Code + "'"
                + " AND " + GRP_LAY_R2_LIST_CODE_COL + " = '" + layR2Code + "'"
                + " AND " + GRP_LAY_R3_LIST_CODE_COL + " = '" + layR3Code + "'";
//        Log.d("CHA", sql);

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                grpCode = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }


        if (grpCode != null) {
            if (!grpCode.isEmpty()) {
                Integer temp_id = Integer.parseInt(grpCode);
                temp_id++;
                grpCode = temp_id.toString();
            }
        } else {
            grpCode = "1";
        }


        int grp_len = grpCode.length();
        String next_grp_id = grpCode;

        String padded_id = "";

        if (grp_len > 0) {
            int pad = 4 - grp_len;

            for (int i = 0; i < pad; i++) {
                padded_id += "0";
            }
            padded_id = padded_id + next_grp_id;
        } else {
            padded_id = "0001";
        }

        return padded_id;

    }


    public String getVillageName(String criteria) {

        String selectQuery = "SELECT " + LAY_R4_LIST_NAME_COL + " FROM " + VILLAGE_TABLE + criteria;
        //selectLabel += getLayerLabel(cCode, "4");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        String villageName = "";

        if (cursor.moveToFirst()) {

            villageName = cursor.getString(0);
            // listItem.add(cursor.getString(0));


        }

        // closing connection
        cursor.close();
        db.close();
        return villageName;
    }

    /**
     * Getting list of any table with ID - Value pair
     * returns list of labels
     */
    public List<SpinnerHelper> getListAndID(String table_name, String criteria, String cCode, boolean countryLoad) {

        List<SpinnerHelper> listItem = new ArrayList<SpinnerHelper>();

        String selectQuery = "";
        String selectLabel = "Select ";

        switch (table_name) {


            case COUNTRY_TABLE:
                selectQuery = "SELECT DISTINCT " + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_CODE + ", " + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_NAME + " FROM " + table_name + criteria;
                selectLabel = "Select Country";

                break;

            case DISTRICT_TABLE:
                selectQuery = "SELECT " + table_name + "." + LAY_R_LIST_CODE_COL + ", " + table_name + "." + DISTRICT_NAME_COL + " FROM " + table_name + criteria;
                //selectLabel += getLayerLabel(cCode, "1"); show select Country
                selectLabel = "Select " + getLayerLabel(cCode, "1");

                break;

            case UPAZILLA_TABLE:
                selectQuery = "SELECT " + table_name + "." + LAY_R2_LIST_CODE_COL + ", " + table_name + "." + UPZILLA_NAME_COL + " FROM " + table_name + criteria;
                selectLabel += getLayerLabel(cCode, "2");

                break;

            case GEO_LAY_R3_LIST_TABLE:
                selectQuery = "SELECT " + table_name + "." + LAY_R3_LIST_CODE_COL + ", " + table_name + "." + LAY_R3_LIST_NAME + " FROM " + table_name + criteria;
                selectLabel += getLayerLabel(cCode, "3");

                break;

            case VILLAGE_TABLE:
                selectQuery = "SELECT " + table_name + "." + LAY_R4_LIST_CODE_COL + ", " + table_name + "." + LAY_R4_LIST_NAME_COL + " FROM " + table_name + criteria;
                selectLabel += getLayerLabel(cCode, "4");

                break;

            case RELATION_TABLE:
                selectQuery = "SELECT " + RELATION_CODE + "," + RELATION_NAME + " FROM " + table_name + criteria;
                selectLabel = "Select Relation";
                //listItem.add("Select Village");
                break;
            case ADM_COUNTRY_AWARD_TABLE:
                selectQuery = "SELECT " + ADM_COUNTRY_AWARD_TABLE + "." + ADM_DONOR_CODE_COL + " || '' ||  " + ADM_COUNTRY_AWARD_TABLE + "." + ADM_AWARD_CODE_COL + " AS AwardCode" + " , " +
                        ADM_DONOR_TABLE + "." + DONOR_NAME_COL + " || '-' ||  " + ADM_COUNTRY_AWARD_TABLE + "." + AWARD_SHORT_NAME_COL + " AS AwardName" +
                        " FROM " + table_name + " JOIN " + ADM_DONOR_TABLE + " ON " + ADM_COUNTRY_AWARD_TABLE + "." + ADM_DONOR_CODE_COL + " = " + ADM_DONOR_TABLE + "." + ADM_DONOR_CODE_COL +
                        /* CHANGE*/    criteria + "GROUP BY " + ADM_DONOR_TABLE + "." + DONOR_NAME_COL + " || '-' ||  " + ADM_COUNTRY_AWARD_TABLE + "." + AWARD_SHORT_NAME_COL + " ORDER BY AwardName ";
                selectLabel = "Select Award";
                break;
            // Criteria for service
            case ADM_PROGRAM_MASTER_TABLE:
                selectQuery = "SELECT " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " || '' ||  " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " AS criteriaId" + " , " +
                        ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL + " || '-' ||  " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS Criteria" +
                        " FROM " + table_name + " JOIN " + SERVICE_MASTER_TABLE + " ON " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL +
                        criteria + " GROUP BY " + ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL + " || '-' ||  " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " ORDER BY Criteria ";
                selectLabel = "Select Criteria";
                break;
            case GPS_GROUP_TABLE:
                selectQuery = "SELECT DISTINCT " + GROUP_CODE_COL + "," + GROUP_NAME_COL + " FROM " + table_name + criteria;

                selectLabel = "Select Group";
                break;
            case GPS_SUB_GROUP_TABLE:
                selectQuery = "SELECT DISTINCT " + SUB_GROUP_CODE_COL + " , " + SUB_GROUP_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select sub Group";
                break;
            case GPS_LOCATION_TABLE:
                selectQuery = "SELECT " + LOCATION_CODE_COL + "," + LOCATION_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select location";
                break;
            // for Program spinner Assigne & gradution
            case COUNTRY_PROGRAM_TABLE:
                selectQuery = "SELECT " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + " , " +
                        ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL +
                        " FROM " + table_name + " JOIN " + ADM_PROGRAM_MASTER_TABLE + " ON " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL +
                        " AND " + COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_AWARD_CODE_COL +
                        " AND " + COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_DONOR_CODE_COL + " " +
                        criteria + " GROUP BY " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL;//+" GROUP BY "+ADM_PROGRAM_MASTER_TABLE +"."+PROGRAM_SHORT_NAME_COL +" || '-' ||  " +SERVICE_MASTER_TABLE+"."+SERVICE_SHORT_NAME_COL+" ORDER BY Criteria ";
                selectLabel = "Select Program";
                break;

            case SERVICE_MASTER_TABLE:
                selectQuery = "SELECT " + COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL + " AS criteriaId" + " , " +
                        SERVICE_MASTER_TABLE + "." + SERVICE_NAME_COL + " || '-' ||  " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS Criteria" +
                        " FROM " + COUNTRY_PROGRAM_TABLE + " JOIN " + table_name +
                        " ON " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + " AND " +
                        SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL + " " +
                        criteria + " GROUP BY " + COUNTRY_PROGRAM_TABLE + "." + ADM_SRV_CODE_COL;

                selectLabel = "Select Criteria";
                break;

            case VILLAGE_TABLE_FOR_ASSIGN:
                selectQuery = "SELECT  v." +
                        ADM_COUNTRY_CODE_COL + " || '' || v." +
                        MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL + " || '' || v." + LAY_R2_LIST_CODE_COL + " || '' || v." + LAY_R3_LIST_CODE_COL + " || '' || v." + LAY_R4_LIST_CODE_COL
                        + ", v." + LAY_R4_LIST_NAME_COL + " FROM " + VILLAGE_TABLE + criteria;
                selectLabel += getLayerLabel(cCode, "4");
                //listItem.add("Select Village");
                break;
            /** FOR SERVICE SUMMMARY OP_MONTH LOAD */

            case OP_MONTH_TABLE:
                selectQuery = "SELECT " + ADM_COUNTRY_CODE_COL + " || '' || " + ADM_DONOR_CODE_COL + " || '' || " + ADM_AWARD_CODE_COL + " || '' || " + OP_MONTH_CODE_COL + " AS OpMonthID, " + MONTH_LABEL_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Month";

                break;
            case SERVICE_SUMMARY_CRITERIA_QUERY:

                selectQuery = " SELECT " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " || '' || " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL + " AS IdCriteria ,  " +
                        SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL + " AS Criteria  " +

                        "FROM " + SERVICE_TABLE + " JOIN " + SERVICE_CENTER_TABLE
                        + " ON " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + SERVICE_CENTER_TABLE + "." + ADM_COUNTRY_CODE_COL +

                        " JOIN " + OP_MONTH_TABLE
                        + " ON " + SERVICE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_COUNTRY_CODE_COL + " AND " +
                        SERVICE_TABLE + "." + OPERATION_CODE_COL + " = " + OP_MONTH_TABLE + "." + OPERATION_CODE_COL + " AND " +
                        SERVICE_TABLE + "." + OP_MONTH_CODE_COL + " = " + OP_MONTH_TABLE + "." + OP_MONTH_CODE_COL + " AND " +
                        SERVICE_TABLE + "." + ADM_DONOR_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_DONOR_CODE_COL + " AND " +
                        SERVICE_TABLE + "." + ADM_AWARD_CODE_COL + " = " + OP_MONTH_TABLE + "." + ADM_AWARD_CODE_COL + " " +
                        " JOIN " + SERVICE_MASTER_TABLE + " ON " + SERVICE_TABLE + "." + ADM_PROG_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_PROG_CODE_COL + " AND " +       // "\t   INNER JOIN AdmServiceMaster ON SrvTable.ProgCode = AdmServiceMaster.AdmProgCode AND \n" +
                        SERVICE_TABLE + "." + ADM_SRV_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + ADM_SRV_CODE_COL +
                        criteria +
                        " GROUP BY " + SERVICE_MASTER_TABLE + "." + SERVICE_MASTER_SERVICE_SHORT_NAME_COL;
                selectLabel = "Select Criteria";

                break;

            case VILLAGE_TABLE_QUERY_FOR_RECORDS:
                selectQuery = "SELECT " + " v." + ADM_COUNTRY_CODE_COL + " || '' ||  v." + LAY_R_LIST_CODE_COL + " || '' || v." + LAY_R2_LIST_CODE_COL + " || '' || v." +
                        LAY_R3_LIST_CODE_COL + " || '' || v." + LAY_R4_LIST_CODE_COL + " AS v_code," +
                        " v." + LAY_R4_LIST_NAME_COL + " AS Vill_Name " +
                     /*   " COUNT("+PID_COL+") AS records"*/" FROM " + VILLAGE_TABLE + " AS v" +
                        " LEFT JOIN " + REG_N_HH_TABLE + " AS r" +
                        " ON r." + ADM_COUNTRY_CODE_COL + "=v." + ADM_COUNTRY_CODE_COL + " AND " +
                        "r." + DISTRICT_NAME_COL + "=v." + LAY_R_LIST_CODE_COL + " AND " +
                        "r." + UPZILLA_NAME_COL + "=v." + LAY_R2_LIST_CODE_COL + " AND " +
                        "r." + LAY_R3_LIST_NAME + "=v." + LAY_R3_LIST_CODE_COL + " AND " +
                        "r." + LAY_R4_LIST_NAME_COL + "=v." + LAY_R4_LIST_CODE_COL +

                        " WHERE v." + ADM_COUNTRY_CODE_COL + "='" + cCode + "'" + /** send the no of village for selected country added by Faisal Mohammad*/
                        "  GROUP BY v." + ADM_COUNTRY_CODE_COL + ",v." + LAY_R_LIST_CODE_COL + ",v." + LAY_R2_LIST_CODE_COL + ",v." + LAY_R3_LIST_CODE_COL + ",v." + LAY_R4_LIST_CODE_COL;

                selectLabel += getLayerLabel(cCode, "4");
                break;
            case LUP_REGNH_HEAD_CATEGORY_TABLE:
                selectQuery = "SELECT " + CATEGORY_CODE_COL + " , " + CATEGORY_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select House Hold Type";
                break;

            case REG_N_LUP_GRADUATION_TABLE:
                selectQuery = "SELECT " + GRD_CODE_COL + " , " + GRD_TITLE_COL + " FROM " + table_name + criteria;
                Log.d("nafiz", selectQuery);
                selectLabel = "Select Reason";
                break;
            case SERVICE_CENTER_TABLE:
                selectQuery = "SELECT " + SERVICE_CENTER_CODE_COL + " , " +
                        SERVICE_CENTER_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Service Center ";
                break;
            case REPORT_TEMPLATE_TABLE: //@date:2015-11-04
                selectQuery = "SELECT " + REPORT_CODE_COL + " , " +
                        REPORT_LABLE_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Card Type ";
                break;
            case CARD_PRINT_REASON_TABLE: //@date:2015-11-04
                selectQuery = "SELECT " + CARD_PRINT_REASON_CODE_COL + " , " +
                        CARD_PRINT_REASON_TITLE_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Reason";
                break;
            // to get Upzella Code & District Code
            case UPZELLA_TABLE_CUSTOM_QUERY:
                selectQuery = "SELECT " + UPAZILLA_TABLE + "." + LAY_R_LIST_CODE_COL + " || " + UPAZILLA_TABLE + "." + LAY_R2_LIST_CODE_COL + ", " + UPAZILLA_TABLE + "." + UPZILLA_NAME_COL + " FROM " + UPAZILLA_TABLE + criteria;
                selectLabel += getLayerLabel(cCode, "2");
                break;
            case STAFF_FDP_ACCESS_TABLE:
                selectQuery = "SELECT " + FDP_MASTER_TABLE + "." + FDP_CODE_COL + " AS " + FDP_CODE_COL + " , "
                        + FDP_MASTER_TABLE + "." + FDP_NAME_COL + " AS " + FDP_NAME_COL +
                        " FROM " + table_name + " INNER JOIN "
                        + FDP_MASTER_TABLE + " ON "
                        + STAFF_FDP_ACCESS_TABLE + "." + STAFF_FDP_ACCESS_COUNTRY_CODE + " = " + FDP_MASTER_TABLE + "." + FDP_MASTER_COUNTRY_CODE
                        + " AND " + STAFF_FDP_ACCESS_TABLE + "." + FDP_CODE_COL + " = " + FDP_MASTER_TABLE + "." + FDP_CODE_COL
                        + " INNER JOIN " + SELECTED_FDP_TABLE + " ON "
                        + STAFF_FDP_ACCESS_TABLE + "." + STAFF_FDP_ACCESS_COUNTRY_CODE + " = " + SELECTED_FDP_TABLE + "." + ADM_COUNTRY_CODE_COL
                        + " AND " + STAFF_FDP_ACCESS_TABLE + "." + FDP_CODE_COL + " = " + SELECTED_FDP_TABLE + "." + FDP_CODE_COL

                        + criteria;
                break;
            case LUP_SRV_OPTION_LIST_TABLE:
                selectQuery = " SELECT " + LUP_OPTION_CODE_COL + " , " + LUP_OPTION_NAME_COL
                        + " FROM " + LUP_SRV_OPTION_LIST_TABLE + " " + criteria;
                selectLabel = "Select Service";
                break;
            case ASSIGN_SUMMARY_PROGRAM_DETAILS:
                selectQuery = "SELECT " + COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL
                        + " || '' || " + COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL
                        + " || '' || " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL
                        + " , " +
                        ADM_PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL +
                        " FROM " + COUNTRY_PROGRAM_TABLE + " JOIN " + ADM_PROGRAM_MASTER_TABLE + " ON " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_PROG_CODE_COL +
                        " AND " + COUNTRY_PROGRAM_TABLE + "." + ADM_AWARD_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_AWARD_CODE_COL +
                        " AND " + COUNTRY_PROGRAM_TABLE + "." + ADM_DONOR_CODE_COL + " = " + ADM_PROGRAM_MASTER_TABLE + "." + ADM_DONOR_CODE_COL + " " +
                        criteria + " GROUP BY " + COUNTRY_PROGRAM_TABLE + "." + ADM_PROG_CODE_COL;//+" GROUP BY "+ADM_PROGRAM_MASTER_TABLE +"."+PROGRAM_SHORT_NAME_COL +" || '-' ||  " +SERVICE_MASTER_TABLE+"."+SERVICE_SHORT_NAME_COL+" ORDER BY Criteria ";
                selectLabel = "Select Program";
                break;
            case FDP_LAY_R2:
                selectQuery = " Select DISTINCT  " + UPAZILLA_TABLE + "." + LAY_R1_LIST_CODE_COL + " || " + UPAZILLA_TABLE + "." + LAY_R2_LIST_CODE_COL + " AS code "
                        + " , " + UPAZILLA_TABLE + " ." + UPZILLA_NAME_COL + " AS Name "
                        + " FROM  " + STAFF_FDP_ACCESS_TABLE
                        + "  INNER JOIN         " + FDP_MASTER_TABLE
                        + "   ON         " + STAFF_FDP_ACCESS_TABLE + "." + STAFF_FDP_ACCESS_COUNTRY_CODE + " = " + FDP_MASTER_TABLE + "." + ADM_COUNTRY_CODE_COL
                        + "   AND         " + STAFF_FDP_ACCESS_TABLE + "." + FDP_CODE_COL + " = " + FDP_MASTER_TABLE + "." + FDP_CODE_COL
                        + "   INNER JOIN    " + UPAZILLA_TABLE
                        + "   ON    " + STAFF_FDP_ACCESS_TABLE + "." + STAFF_FDP_ACCESS_COUNTRY_CODE + " = " + UPAZILLA_TABLE + "." + ADM_COUNTRY_CODE_COL
                        + "   AND   " + FDP_MASTER_TABLE + "." + FDP_MASTER_COUNTRY_CODE + " = " + UPAZILLA_TABLE + "." + ADM_COUNTRY_CODE_COL
                        + "   AND   " + FDP_MASTER_TABLE + "." + FDP_MASTER_LAY_R1_LIST_CODE_COL + " = " + UPAZILLA_TABLE + "." + LAY_R1_LIST_CODE_COL
                        + "   AND   " + FDP_MASTER_TABLE + "." + FDP_MASTER_LAY_R2_LIST_CODE_COL + " = " + UPAZILLA_TABLE + "." + LAY_R2_LIST_CODE_COL

                        + " INNER JOIN " + SELECTED_FDP_TABLE + " ON "
                        + STAFF_FDP_ACCESS_TABLE + "." + STAFF_FDP_ACCESS_COUNTRY_CODE + " = " + SELECTED_FDP_TABLE + "." + ADM_COUNTRY_CODE_COL
                        + " AND " + STAFF_FDP_ACCESS_TABLE + "." + FDP_CODE_COL + " = " + SELECTED_FDP_TABLE + "." + FDP_CODE_COL


                        + criteria
                ;

                selectLabel = "Select ";
                break;
            case VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE:
                selectQuery = criteria;
                selectLabel = "Select Item";
                break;

            case CUSTOM_QUERY:
                selectQuery = criteria;
                selectLabel = "Select ";
                break;
            case LUP_COMMUNITY_ANIMAL_TABLE:
                selectQuery = "SELECT  " + ANIMAL_CODE_COL + " , " + ANIMAL_TYPE_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Animal Type";

                break;
            case LUP_PROG_GROUP_CROP_TABLE:
                selectQuery = "SELECT  " + CROP_CODE_COL + " , " + CROP_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Crop Type";

                break;


            case LUP_COMMUNITY_LOAN_SOURCE_TABLE:
                selectQuery = "SELECT  " + LOAN_CODE_COL + " , " + LOAN_SOURCE_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Loan Source";

                break;

            case LUP_COMMUNITY_LEAD_POSITION_TABLE:
                selectQuery = "SELECT  " + LEAD_CODE_COL + " , " + LEAD_POSITION_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Loan Source";

                break;

            case COMMUNITY_GROUP_CATEGORY_TABLE:
                selectQuery = "SELECT  " + GROUP_CAT_CODE_COL + " , " + GROUP_CAT_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select ";

                break;

            case COMMUNITY_GROUP_TABLE:
                selectQuery = "SELECT  " + GROUP_CODE_COL + " , " + GROUP_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select";

                break;
            case LUP_REGN_ADDRESS_LOOKUP_TABLE:
                selectQuery = "SELECT " + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " , " + SQLiteHandler.REGN_ADDRESS_LOOKUP_NAME_COL
                        + " FROM " + table_name + criteria;
                selectLabel = " Select";
                break;


        }

        //selectQuery = "SELECT * FROM " + table_name ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (selectLabel == null) selectLabel = "Select..";
        int position = 0;

        if (!countryLoad) { // all spinner show the select except load country of Main Activity
            listItem.add(new SpinnerHelper(position, "00", selectLabel));
            position++;
        }


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                listItem.add(new SpinnerHelper(position, cursor.getString(0), cursor.getString(1)));
//                Log.d(TAG, " table name :" + table_name + " :- " + cursor.getColumnName(0) + " : " + cursor.getString(0) + "  " + cursor.getColumnName(1) + " : " + cursor.getString(1));
                position++;
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning list item
        return listItem;
    }

    /**
     * this method invoke in {@link GPSLocationSearchPage#loadLocation(String, String)}
     *
     * @param cCode         country Code
     * @param searchLocName Searching loaction Name
     * @return list of location
     */
    public List<LocationHelper> getLocationList(String cCode, String searchLocName) {
        int position = 0;
        List<LocationHelper> list = new ArrayList<LocationHelper>();
        String sql = SQLiteQuery.getLocationList_sql(cCode, searchLocName);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                // what the fuck . totally short cut.
                list.add(new LocationHelper(position, cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));

                position++;
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }

        return list;
    }

    public long addLUP_RegNAddLookup(String countryCode, String addressLookupCode, String addressLookup, String districtCode, String upozillaCode, String unitCode, String villageCode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ADM_COUNTRY_CODE_COL, countryCode);
        values.put(REGN_ADDRESS_LOOKUP_CODE_COL, addressLookupCode);
        values.put(REGN_ADDRESS_LOOKUP_NAME_COL, addressLookup);
        values.put(MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL, districtCode);
        values.put(LAY_R2_LIST_CODE_COL, upozillaCode);
        values.put(LAY_R3_LIST_CODE_COL, unitCode);
        values.put(LAY_R4_LIST_CODE_COL, villageCode);

        long id = db.insert(LUP_REGN_ADDRESS_LOOKUP_TABLE, null, values);
        db.close();
        return id;
    }


    public String getRelationString(String relationId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String relationName = "";
        String selectQuery = "SELECT " + RELATION_NAME + " FROM " + RELATION_TABLE + " WHERE " +
                RELATION_CODE + " = '" + relationId + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                relationName = cursor.getString(cursor.getColumnIndex(RELATION_NAME));
            }
        }
        if (cursor != null)
            cursor.close();

        db.close();
        return relationName;
    }

    public void updateCardRequestStatus(String update_id, int status) {

        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "UPDATE " + MEMBER_CARD_PRINT_TABLE + " SET " + SYNC_COL + "=" + status + " WHERE " + ID_COL + "=" + update_id;
        db.execSQL(selectQuery);
        db.close();
    }

//    public void updateRegistrationStatus(String update_id, int status) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        String selectQuery = "UPDATE " + REG_N_HH_TABLE + " SET " + SYNC_COL + "=" + status + " WHERE " + ID_COL + "=" + update_id;
//        db.execSQL(selectQuery);
//        db.close();
//    }

   /* public void updateLiberaiRegistrationStatus(String update_id, int status) {

        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "UPDATE " + LIBERIA_REGISTRATION_TABLE + " SET " + SYNC_COL + "=" + status + " WHERE " + ID_COL + "=" + update_id;
        db.execSQL(selectQuery);
        db.close();
    }*/


    /**
     * Storing Country info into database
     */

    public void addCountry(String code, String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_COUNTRY_CODE, code); // Country code
        values.put(COUNTRY_COUNTRY_NAME, name); // Country name


        db.insert(COUNTRY_TABLE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "New Country inserted: " + id);
    }

    public void insertSelectedCountry(String code, String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE, code);
        values.put(COUNTRY_COUNTRY_NAME, name);


        db.insert(SELECTED_COUNTRY_TABLE, null, values);
        db.close();


    }


    /**
     * Storing Country info into database
     */

    public void addValidDateRange(String code, String sdate, String edate) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DATE_RANGE_COUNTRY_CODE, code); // Country code
        values.put(DATE_START, sdate); // start date
        values.put(DATE_END, edate); // end date

        // Inserting Row
        long id = db.insert(VALID_DATE_RANGE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "New date range inserted: " + id);
    }


    /**
     * Storing Layer Label info into database
     */

    public void addLayerLabel(String c_code, String l_code, String l_name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LAYER_LAVLE_COUNTRY_CODE, c_code); // Country code
        values.put(LAYER_CODE_COL, l_code); // Layer code
        values.put(LAYER_NAME_COL, l_name); // Layer name

        // Inserting Row
        long id = db.insert(LAYER_LABEL_TABLE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "Layer Label data inserted: " + id);
    }


    /**
     * Storing District details into database
     */
    public void addDistrict(String country, String GeoLayRCode, String code, String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, country); // country code
        values.put(LAYER_CODE_COL, GeoLayRCode); // Layer code
        values.put(LAY_R_LIST_CODE_COL, code); // district code
        values.put(DISTRICT_NAME_COL, name); // district name

        // Inserting Row
        long id = db.insert(DISTRICT_TABLE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "New District inserted into District: " + id);
    }


    // Storing Card Reason  details into database
    // @date: 2015-11-05

    public void addCardPrintReason(String reason_code, String reason_title) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CARD_PRINT_REASON_CODE_COL, reason_code);
        values.put(CARD_PRINT_REASON_TITLE_COL, reason_title);

        // Inserting Row
        long id = db.insert(CARD_PRINT_REASON_TABLE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "New Card Reason inserted into Card Print Reason Table: " + id);
    }


    /**
     * Storing Upazilla details into database
     */
    public void addUpazilla(String country, String GeoLayRCode, String dcode, String upcode, String upname) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, country); // country code
        values.put(LAYER_CODE_COL, GeoLayRCode); // Layer code
        values.put(LAY_R1_LIST_CODE_COL, dcode); // district code
        values.put(LAY_R2_LIST_CODE_COL, upcode); // upazilla code
        values.put(UPZILLA_NAME_COL, upname); // upazilla name

        // Inserting Row
        long id = db.insert(UPAZILLA_TABLE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "New UPAZILLA_ inserted into Upazilla: " + id);
    }


    /**
     * Storing Unit details into database
     */
    public void addUnit(String country, String GeoLayRCode, String dcode, String upcode, String ucode, String uname) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, country);                                                  // country code
        values.put(LAYER_CODE_COL, GeoLayRCode);                                                    // Layer code
        values.put(LAY_R1_LIST_CODE_COL, dcode);                                                     //  district code
        values.put(LAY_R2_LIST_CODE_COL, upcode);                                                   // upazilla code
        values.put(LAY_R3_LIST_CODE_COL, ucode);                                                    // unit code
        values.put(LAY_R3_LIST_NAME, uname);                                                        // unit name


        db.insert(GEO_LAY_R3_LIST_TABLE, null, values);                                              // Inserting Row
        db.close();                                                                                 // Closing database connection


    }

    public void addSelectedVillage(String country, String dcode, String upcode, String ucode, String vcode, String layrCode, String vname, String addressCode) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put("CountryCode", country);
        values.put("DistrictCode", dcode);
        values.put("UpazillaCode", upcode);
        values.put("UnitCode", ucode);
        values.put("VillageCode", vcode);
        values.put(LAYER_CODE_COL, layrCode);
        values.put("VillageName", vname);
        values.put(REGN_ADDRESS_LOOKUP_CODE_COL, addressCode);

//                                                                                                   donn't delete below code previous satage
//        values.put(ADM_COUNTRY_CODE_COL, country);                                                // country code
//        values.put(LAY_R_LIST_CODE_COL, dcode);                                                                 //  district code
//        values.put(LAY_R2_LIST_CODE_COL, upcode); // upazilla code
//        values.put(LAY_R3_LIST_CODE_COL, ucode); // unit code
//        values.put(LAY_R4_LIST_CODE_COL, vcode); // Village code
//        values.put(LAYER_CODE_COL, layrCode); // whoe LaRCode code
//        values.put(LAY_R4_LIST_NAME_COL, vname); // Village name
//        values.put(REGN_ADDRESS_LOOKUP_CODE_COL, addressCode); // Village name
//


        db.insert(SELECTED_VILLAGE_TABLE, null, values);
        db.close();


    }


    public void addSelectedFDP(String country, String fdpCode, String fdpName) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, country);                                                  // country code
        values.put(FDP_CODE_COL, fdpCode);                                                          //  fdp code
        values.put(FDP_NAME_COL, fdpName);                                                                                                      // fdp name

        long id = db.insert(SELECTED_FDP_TABLE, null, values);                                      // Inserting Row
        db.close();

//        Log.d(TAG, "New Village inserted into VILLAGE_TABLE: " + id);
    }


    public void addSelectedServiceCenter(String country, String fdpCode, String fdpName) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, country); // country code
        values.put(SERVICE_CENTER_CODE_COL, fdpCode); //  fdp code
        values.put(SERVICE_CENTER_NAME_COL, fdpName); // fdp name
        // Inserting Row
        long id = db.insert(SELECTED_SERVICE_CENTER_TABLE, null, values);
        db.close();

//        Log.d(TAG, "New Village inserted into VILLAGE_TABLE: " + id);
    }

    /**
     * Storing Village details into database
     */
    public void addVillage(String country, String GeoLayRCode, String dcode, String upcode, String ucode, String vcode, String vname, String hhtarget) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, country); // country code
        values.put(LAYER_CODE_COL, GeoLayRCode); // Layer code
        values.put(MEM_CARD_PRINT_LAY_R1_LIST_CODE_COL, dcode); //  district code
        values.put(LAY_R2_LIST_CODE_COL, upcode); // upazilla code
        values.put(LAY_R3_LIST_CODE_COL, ucode); // unit code
        values.put(LAY_R4_LIST_CODE_COL, vcode); // Village code
        values.put(LAY_R4_LIST_NAME_COL, vname); // Village name
        values.put(HOUSE_HOLD_TARGET, hhtarget); // Village 's house hold targe

        // Inserting Row
        long id = db.insert(VILLAGE_TABLE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "New Village inserted into VILLAGE_TABLE: " + id);
    }

    // Storing Relation details into database

    public void addRelation(String rel_code, String rel_name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RELATION_CODE, rel_code); // m code
        values.put(RELATION_NAME, rel_name); // m name

        // Inserting Row
        long id = db.insert(RELATION_TABLE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "New Relation inserted into Relation Table: " + id);
    }


    public void addCardType(String country_code, String cardType_lable, String cardType_code) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(ADM_COUNTRY_CODE_COL, country_code);
        values.put(REPORT_LABLE_COL, cardType_lable);
        values.put(REPORT_CODE_COL, cardType_code);

        // Inserting Row
        long id = db.insert(REPORT_TEMPLATE_TABLE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "New Report Card Type inserted into Report Template Table: " + id);
    }


    public void addRegNPWFromOnLine(String c_code, String dname, String upname, String uname, String vname, String hhid, String memid, String program, String service, String regNdate, String grdCode,                                    //  String entryBy,
                                    //  String entryDate,
                                    String lmpDate, String pwGrdDate) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, c_code); // country name

        values.put(LAY_R1_LIST_CODE_COL, dname);                                                    // district code
        values.put(LAY_R2_LIST_CODE_COL, upname);                                                   // upazilla code
        values.put(LAY_R3_LIST_CODE_COL, uname);                                                    // Unit code
        values.put(LAY_R4_LIST_CODE_COL, vname);                                                    // village  code

        values.put(HHID_COL, hhid);                                                                 // House hold id
        values.put(MEM_ID_COL, memid);                                                              // member id
        values.put(REG_N_DAT_COL, regNdate); //
        values.put(LMP_DATE_COL, lmpDate);
        values.put(ADM_PROG_CODE_COL, program); //
        values.put(ADM_SRV_CODE_COL, service);
        values.put(GRDCODE_COL, grdCode);
        values.put(PW_GRD_DATE_COL, pwGrdDate);

        values.put(ENTRY_BY, "00");
        values.put(ENTRY_DATE, "00");


        db.insert(REG_N_PW_TABLE, null, values);                                                    // Inserting Row
        db.close();                                                                                 // Closing database connection


    }


    /**
     * this function insert member the into RegNAssProgSrv table
     *
     * @param data data
     * @return row id of inserted value
     * @since 2015-11-23
     */

    public long addMemberDataInto_RegNAsgProgSrv(AssignDataModel data) {

        return InsertInto_RegNAsgProgSrv(data.getCountryCode(), data.getDistrictCode()
                , data.getUpazillaCode(), data.getUnitCode()
                , data.getVillageCode(), data.getDonor_code()
                , data.getAward_code(), data.getHh_id()
                , data.getMemId(), data.getProgram_code()
                , data.getService_code(), data.getRegNDate()
                , data.getGrdCode(), data.getGrdDate()
                , data.getEntryBy(), data.getEntryDate(), "", "", "0");


    }

    /**
     * @param c_code     Country Code
     * @param dname      layer Code 1(District or County)
     * @param upname     layer Code 2
     * @param uname      layer Code 3
     * @param vname      layer Code 4 (Village or town)
     * @param donorCode  house hold id
     * @param awardCode  Member id
     * @param hhid       program code
     * @param memid      Service Code
     * @param progCode   Registration Date
     * @param srvCode    Graduation code
     * @param regNdate   regNdate
     * @param grdCode    grdCode
     * @param gdrDate    gdrDate
     * @param entryBy    entryBy
     * @param entryDate  entryDate
     * @param srvMinDate srvMinDate
     * @param srvMaxDate srvMaxDate
     * @param onLine     onLine
     * @return
     */


    private long InsertInto_RegNAsgProgSrv(String c_code, String dname, String upname, String uname, String vname, String donorCode, String awardCode, String hhid, String memid, String progCode, String srvCode, String regNdate, String grdCode, String gdrDate, String entryBy, String entryDate, String srvMinDate, String srvMaxDate, String onLine) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ADM_COUNTRY_CODE_COL, c_code);

        values.put(LAY_R1_LIST_CODE_COL, dname);
        values.put(LAY_R2_LIST_CODE_COL, upname);
        values.put(LAY_R3_LIST_CODE_COL, uname);
        values.put(LAY_R4_LIST_CODE_COL, vname);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(HHID_COL, hhid);
        values.put(REG_N_ASSIGN_PROG_SRV_HH_MEM_ID, memid);
        values.put(PROG_CODE_COL, progCode);
        values.put(SRV_CODE_COL, srvCode);
        values.put(REG_N_DAT_COL, regNdate);
        values.put(GRD_CODE_COL, grdCode);
        values.put(GRD_DATE_COL, gdrDate);
        values.put(SRV_MIN_DATE_COL, srvMinDate);
        values.put(SRV_MAX_DATE_COL, srvMaxDate);

        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        // values.put(SYNC_COL, onLine);


        // Inserting Row
        long id = db.insert(REG_N_ASSIGN_PROG_SRV_TABLE, null, values);
        db.close(); // Closing database connection


//        Log.d(TAG, "New " + REG_N_ASSIGN_PROG_SRV_TABLE + ": " + id);
        return id;
    }


    public void addRegNassignProgServiceFromOnline(String c_code, String dname, String upname, String uname, String vname, String donor, String award, String hhid, String memid, String program, String service, String regNdate, String grdCode, String gdrDate, String srvMinDate, String srvMaxDate) {

        InsertInto_RegNAsgProgSrv(c_code, dname, upname, uname, vname, donor, award, hhid, memid, program, service, regNdate, grdCode, gdrDate, "", "", srvMinDate, srvMaxDate, "1");


    }


    public long addMotherInto_PW(AssignDataModel assingPerson, String lmpDate) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ADM_COUNTRY_CODE_COL, assingPerson.getCountryCode()); // country name

        values.put(LAY_R1_LIST_CODE_COL, assingPerson.getDistrictCode()); // district name
        values.put(LAY_R2_LIST_CODE_COL, assingPerson.getUpazillaCode()); // upazilla name
        values.put(LAY_R3_LIST_CODE_COL, assingPerson.getUnitCode()); // Unit name
        values.put(LAY_R4_LIST_CODE_COL, assingPerson.getVillageCode()); // village  name

        values.put(HHID_COL, assingPerson.getHh_id()); // Personal name
        values.put(MEM_ID_COL, assingPerson.getMemId()); // Registration name
        values.put(ADM_PROG_CODE_COL, assingPerson.getProgram_code()); // Person name
        values.put(ADM_SRV_CODE_COL, assingPerson.getService_code()); // sex
        values.put(GRDCODE_COL, assingPerson.getGrdCode());
        values.put(PW_GRD_DATE_COL, assingPerson.getGrdDate()); //

        values.put(REG_N_DAT_COL, assingPerson.getRegNDate()); //
        values.put(LMP_DATE_COL, lmpDate);
        values.put(ENTRY_BY, assingPerson.getEntryBy());
        values.put(ENTRY_DATE, assingPerson.getEntryDate());
        // values.put(SYNC_COL, "0");


        // Inserting Row
        long id = db.insert(REG_N_PW_TABLE, null, values);
        db.close(); // Closing database connection
        // updateRegNLMFStatus(assingPerson, 0);
        Log.d(TAG, "New RegN assign Program Service  data added from online into Service Table: " + id);
        return id;
    }


    /**
     * @param c_code    Country Code
     * @param dname     layer Code 1(District or County)
     * @param upname    layer Code 2
     * @param uname     layer Code 3
     * @param vname     layer Code 4 (Village or town)
     * @param hhid      house hold id
     * @param memId     Member id
     * @param program   program code
     * @param service   Service Code
     * @param regNdate  Registration Date
     * @param grdCode   Graduation code
     * @param lmDate    lactating Mother starting date
     * @param lmGrdDate lactating Mother Graduation Date
     * @param childName childName
     * @param childSex  childSex
     */
    public void addRegNLMFromOnLine(String c_code, String dname, String upname, String uname, String vname, String hhid,
                                    String memId, String program, String service, String regNdate, String grdCode, String lmDate, String lmGrdDate, String childName, String childSex) {

        addMemIntoRegN_LM(c_code, dname, upname, uname, vname, hhid, memId, program, service, regNdate, grdCode, lmDate, lmGrdDate, childName, childSex, "", "", "1");

    }

    /**
     * @param cCode     Country Code
     * @param distCode  layer Code 1(District or County)
     * @param upCode    layer Code 2
     * @param unCode    layer Code 3
     * @param vCode     layer Code 4 (Village or town)
     * @param hhID      house hold id
     * @param memId     Member id
     * @param progCode  program code
     * @param servCode  Service Code
     * @param regNdate  Registration Date
     * @param grdCode   Graduation code
     * @param lmDate    lactating Mother starting date
     * @param lmGrdDate lactating Mother Graduation Date
     * @param childName childName
     * @param childSex  childSex
     * @param entryBy   entry by
     * @param entryDate entryDate
     * @return row of inserted data
     */

    public long addMemIntoRegN_LM(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID,
                                  String memId, String progCode, String servCode, String regNdate, String grdCode,
                                  String lmDate, String lmGrdDate, String childName, String childSex,
                                  String entryBy, String entryDate, String syn) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(LAY_R1_LIST_CODE_COL, distCode);
        values.put(LAY_R2_LIST_CODE_COL, upCode);
        values.put(LAY_R3_LIST_CODE_COL, unCode);
        values.put(LAY_R4_LIST_CODE_COL, vCode);
        values.put(HHID_COL, hhID);
        values.put(MEM_ID_COL, memId);

        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(ADM_SRV_CODE_COL, servCode);

        values.put(GRD_CODE_COL, grdCode);
        values.put(LMGRDDATE_COL, lmGrdDate);
        values.put(REG_N_DAT_COL, regNdate);
        values.put(LM_DATE_COL, lmDate);

        values.put(CHILD_NAME_COL, childName);
        values.put(CHILD_SEX_COL, childSex);

        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
//        values.put(SYNC_COL, syn);


        // Inserting Row
        long id = db.insert(REG_N_LM_TABLE, null, values);
        db.close();                                                                                 // Closing database connection

        //  Log.d(TAG, "New" + REG_N_LM_TABLE + " " + id);
        return id;
    }


    /**
     * @param cCode      Country Code
     * @param distCode   layer Code 1(District or County)
     * @param upCode     layer Code 2
     * @param unCode     layer Code 3
     * @param vCode      layer Code 4 (Village or town)
     * @param hhID       house hold id
     * @param memId      Member id
     * @param progCode   program code
     * @param servCode   Service Code
     * @param regNdate   Registration Date
     * @param grdCode    Graduation code
     * @param dob        date of Birth
     * @param cu2GrdDate Cu Graduation Date
     * @param childName  childName
     * @param childSex   childSex
     * @param entryBy    entry by
     * @param entryDate  entryDate
     * @return row of inserted data
     */

    public long addMemIntoRegN_CU2(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID,
                                   String memId, String progCode, String servCode, String regNdate, String grdCode,
                                   String dob, String cu2GrdDate, String childName, String childSex,
                                   String entryBy, String entryDate, String syn) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(LAY_R1_LIST_CODE_COL, distCode);
        values.put(LAY_R2_LIST_CODE_COL, upCode);
        values.put(LAY_R3_LIST_CODE_COL, unCode);
        values.put(LAY_R4_LIST_CODE_COL, vCode);
        values.put(HHID_COL, hhID);
        values.put(MEM_ID_COL, memId);

        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(ADM_SRV_CODE_COL, servCode);

        values.put(REG_N_DAT_COL, regNdate);

        values.put(CU2DOB_DATE_COL, dob);


        values.put(GRD_CODE_COL, grdCode);
        values.put(CU2_GRD_DATE_COL, cu2GrdDate);


        values.put(CHILD_NAME_COL, childName);
        values.put(CHILD_SEX_COL, childSex);

        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        // values.put(SYNC_COL, syn);


        // Inserting Row
        long id = db.insert(REG_N_CU2_TABLE, null, values);
        db.close();                                                                                 // Closing database connection


        return id;
    }

    public void addRegNCU2_FromOnLine(String c_code, String dname, String upname, String uname, String vname, String hhid, String memid, String program, String service, String regNdate, String grdCode,
                                      String cu2DOB, String cu2GrdDate, String childName, String childSex) {

        addMemIntoRegN_CU2(c_code, dname, upname, uname, vname, hhid, memid, program, service, regNdate, grdCode, cu2DOB, cu2GrdDate, childName, childSex, "", "", "1");

    }


    /**
     * @param c_code     Country Code
     * @param dname      layer Code 1(District or County)
     * @param upname     layer Code 2
     * @param uname      layer Code 3
     * @param vname      layer Code 4 (Village or town)
     * @param hhid       house hold id
     * @param memid      Member id
     * @param program    program code
     * @param service    Service Code
     * @param regNdate   Registration Date
     * @param grdCode    Graduation code
     * @param ca2DOB     date of Birth
     * @param ca2GrdDate Cu Graduation Date
     * @param childName  childName
     * @param childSex   childSex
     */


    public void addRegNCA2_FromOnLine(String c_code, String dname, String upname, String uname, String vname, String hhid, String memid, String program, String service, String regNdate, String grdCode,

                                      String ca2DOB, String ca2GrdDate, String childName, String childSex) {

        addMemIntoRegN_CA2(c_code, dname, upname, uname, vname, hhid, memid, program, service, regNdate, grdCode, ca2DOB, ca2GrdDate, childName, childSex, "", "");

    }


    /**
     * @param cCode      Country Code
     * @param distCode   layer Code 1(District or County)
     * @param upCode     layer Code 2
     * @param unCode     layer Code 3
     * @param vCode      layer Code 4 (Village or town)
     * @param hhID       house hold id
     * @param memId      Member id
     * @param progCode   program code
     * @param servCode   Service Code
     * @param regNdate   Registration Date
     * @param grdCode    Graduation code
     * @param dob        date of Birth
     * @param ca2GrdDate Cu Graduation Date
     * @param childName  childName
     * @param childSex   childSex
     * @param entryBy    entry by
     * @param entryDate  entryDate
     * @return row of inserted data
     */

    public long addMemIntoRegN_CA2(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID,
                                   String memId, String progCode, String servCode, String regNdate, String grdCode,
                                   String dob, String ca2GrdDate, String childName, String childSex,
                                   String entryBy, String entryDate) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(LAY_R1_LIST_CODE_COL, distCode);
        values.put(LAY_R2_LIST_CODE_COL, upCode);
        values.put(LAY_R3_LIST_CODE_COL, unCode);
        values.put(LAY_R4_LIST_CODE_COL, vCode);
        values.put(HHID_COL, hhID);
        values.put(MEM_ID_COL, memId);
        values.put(ADM_PROG_CODE_COL, progCode);
        values.put(ADM_SRV_CODE_COL, servCode);
        values.put(REG_N_DAT_COL, regNdate);
        values.put(CA2DOB_DATE_COL, dob);
        values.put(GRD_CODE_COL, grdCode);
        values.put(CA2_GRD_DATE_COL, ca2GrdDate);
        values.put(CHILD_NAME_COL, childName);
        values.put(CHILD_SEX_COL, childSex);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        //  values.put(SYNC_COL, syn);


        // Inserting Row
        long id = db.insert(REG_N_CA2_TABLE, null, values);
        db.close();                                                                 // Closing database connection


        return id;
    }


    public int editMemberDataIn_CA2(AssignDataModel asPeople) {


        SQLiteDatabase db = this.getWritableDatabase();

        String where = COUNTRY_CODE + " = '" + asPeople.getCountryCode() + "'" +
                " AND " + LAY_R1_LIST_CODE_COL + " = '" + asPeople.getDistrictCode() + "'" +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + asPeople.getUpazillaCode() + "'" +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + asPeople.getUnitCode() + "'" +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + asPeople.getVillageCode() + "'" +
                " AND " + HHID_COL + " = '" + asPeople.getHh_id() + "'" +
                " AND " + MEM_ID_COL + " = '" + asPeople.getMemId() + "'  ";


        ContentValues values = new ContentValues();


        values.put(ADM_PROG_CODE_COL, asPeople.getProgram_code());
        values.put(ADM_SRV_CODE_COL, asPeople.getService_code());

        values.put(REG_N_DAT_COL, asPeople.getRegNDate());
        values.put(CA2DOB_DATE_COL, asPeople.getDobDate());
        values.put(GRDCODE_COL, asPeople.getGrdCode());
        values.put(CA2_GRD_DATE_COL, asPeople.getDobDate());
        values.put(ENTRY_BY, asPeople.getEntryBy());
        values.put(ENTRY_DATE, asPeople.getEntryDate());
//        values.put(SYNC_COL, "0");

        // Log.d(TAG, "asPeople.getLmpDate() :" + asPeople.getLmpDate());


        // updating row
        int id = db.update(REG_N_CA2_TABLE, values, where, null);
       /* int id= db.update( REG_N_PW_TABLE, values, HH_MEM_ID + " = ? AND ",
                new String[]{asPeople.getMemberId()});*/

        //updateRegNCA2Status(asPeople, 0);

        return id;
    }


    public int upDateRegNLM(AssignDataModel asPeople, String lmDate) {


        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();

        values.put(REG_N_DAT_COL, asPeople.getRegNDate()); // sex
        values.put(LM_DATE_COL, lmDate); // GDR_CODE
        values.put(ENTRY_BY, asPeople.getEntryBy());
        values.put(ENTRY_DATE, asPeople.getEntryDate());


//        Log.d(TAG, "asPeople.getLmpDate() :" + asPeople.getLmpDate());

        String query = ADM_COUNTRY_CODE_COL + " = '" + asPeople.getCountryCode() + "' AND " +
                LAY_R1_LIST_CODE_COL + " = '" + asPeople.getDistrictCode() + "' AND " +
                LAY_R2_LIST_CODE_COL + " = '" + asPeople.getUpazillaCode() + "' AND " +
                LAY_R3_LIST_CODE_COL + " = '" + asPeople.getUnitCode() + "' AND " +
                LAY_R4_LIST_CODE_COL + " = '" + asPeople.getVillageCode() + "' AND " +
                HHID_COL + " = '" + asPeople.getHh_id() + "' AND " +
                MEM_ID_COL + " = '" + asPeople.getMemId() + "'  ";

        // updating row
        int id = db.update(REG_N_LM_TABLE, values, query, null);

        updateRegNLMFStatus(asPeople, 0);

        return id;
    }


    public GraduationDateCode getGRDPeopleDetial(
            String cCode, String distCode, String upCode, String unCode,
            String vCode, String hhId, String mmId,
            String progCode, String srvCode, String donorCode, String awardCode) {


        SQLiteDatabase db = this.getReadableDatabase();

        GraduationDateCode data = new GraduationDateCode();


        String selectQuery = " SELECT " + GRD_CODE_COL + " , " + GRD_DATE_COL +
                " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE +
                " WHERE " + COUNTRY_CODE + " = '" + cCode + "'" +
                " AND " + LAY_R1_LIST_CODE_COL + " = '" + distCode + "'" +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "'" +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "'" +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "'" +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "'" +
                " AND " + HHID_COL + " = '" + hhId + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + mmId + "'" +
                " AND  " + PROG_CODE_COL + " = '" + progCode + "'" +
                " AND  " + SRV_CODE_COL + " = '" + srvCode + "'   ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                data.setGrdCode(cursor.getString(cursor.getColumnIndex(GRD_CODE_COL)));
                data.setGrdDate(cursor.getString(cursor.getColumnIndex(GRD_DATE_COL)));
            }
        }
        if (cursor != null)
            cursor.close();
        db.close();

        return data;
    }


    public String getRegDateFromRegNAssignProgSrv(String cCode, String distCode, String upCode, String unCode,
                                                  String vCode, String hhId, String mmId, String donorCode, String awardCode, String progCode, String srvCode) {


        SQLiteDatabase db = this.getReadableDatabase();

        String regDate = "";


        String selectQuery = " SELECT " + REG_N_DAT_COL +
                " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE +
                " WHERE " + COUNTRY_CODE + " = '" + cCode + "' AND " +
                LAY_R1_LIST_CODE_COL + " = '" + distCode + "' AND " +
                LAY_R2_LIST_CODE_COL + " = '" + upCode + "' AND " +
                LAY_R3_LIST_CODE_COL + " = '" + unCode + "' AND " +
                LAY_R4_LIST_CODE_COL + " = '" + vCode + "' AND " +
                ADM_DONOR_CODE_COL + " = '" + donorCode + "' AND " +
                ADM_AWARD_CODE_COL + " = '" + awardCode + "' AND " +
                HHID_COL + " = '" + hhId + "' AND " +
                REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + mmId + "' AND  " +
                ADM_PROG_CODE_COL + " = '" + progCode + "' AND  " +
                ADM_SRV_CODE_COL + " = '" + srvCode + "'   ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                regDate = cursor.getString(cursor.getColumnIndex(REG_N_DAT_COL));

            }
        }
        if (cursor != null)
            cursor.close();
        db.close();

        return regDate;
    }


    public int editMemberDataIn_RegNAsgProgSrv(String grdDate, String grdCode, String entryBy, String entryDate, String cCode, String distCode, String upCode, String unCode, String vCode, String hhId, String mmId, String progCode, String srvCode, String donorCode, String awardCode) {


        SQLiteDatabase db = this.getWritableDatabase();

        String criteria = " " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " +
                LAY_R1_LIST_CODE_COL + " = '" + distCode + "'" +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + upCode + "'" +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + unCode + "'" +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + vCode + "'" +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "'" +
                " AND " + HHID_COL + " = '" + hhId + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + mmId + "'" +
                " AND  " + PROG_CODE_COL + " = '" + progCode + "'" +
                " AND  " + SRV_CODE_COL + " = '" + srvCode + "'   ";


        ContentValues values = new ContentValues();

        values.put(GRD_CODE_COL, grdCode); // GRD_CODE= Graduation Title Code
        values.put(GRD_DATE_COL, grdDate); // GDR_Date= Graduation Date Code
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
//        values.put(SYNC_COL, 0);

//        Log.d(TAG, "update Graduation Date :" + grdDate);


        // updating row
        int id = db.update(REG_N_ASSIGN_PROG_SRV_TABLE, values, criteria, null);

        db.close();

        return id;
    }


    public int editMemberDataIn_RegNAsgProgSrv(AssignDataModel asPeople) {


        SQLiteDatabase db = this.getWritableDatabase();
        String whereQuery = " " + ADM_COUNTRY_CODE_COL + " = '" + asPeople.getCountryCode() + "' AND " +
                LAY_R1_LIST_CODE_COL + " = '" + asPeople.getDistrictCode() + "'" +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + asPeople.getUpazillaCode() + "'" +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + asPeople.getUnitCode() + "'" +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + asPeople.getVillageCode() + "'" +
                " AND " + ADM_DONOR_CODE_COL + " = '" + asPeople.getDonor_code() + "'" +
                " AND " + ADM_AWARD_CODE_COL + " = '" + asPeople.getAward_code() + "'" +
                " AND " + HHID_COL + " = '" + asPeople.getHh_id() + "'" +
                " AND " + REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + " = '" + asPeople.getMemId() + "'  ";

        ContentValues values = new ContentValues();
        values.put(REG_N_DAT_COL, asPeople.getRegNDate());

        values.put(GRD_DATE_COL, asPeople.getGrdDate());
        //  values.put(GRD_CODE_COL, asPeople.getGrdCode());
        //omite the code
//        values.put(SYNC_COL, "0");

        values.put(ENTRY_BY, asPeople.getEntryBy());
        values.put(ENTRY_DATE, asPeople.getEntryDate());

//        Log.d(TAG, "asPeople.getRegNDate() :" + asPeople.getRegNDate());


        // updating row
        int id = db.update(REG_N_ASSIGN_PROG_SRV_TABLE, values, whereQuery, null);
//        Log.d(TAG, "id:" + id);

        db.close();
        return id;
    }


    /**
     * update regn Ass n ser prg tabel upload status
     */
    public void updateRegNLMFStatus(AssignDataModel aPeople, int status) {

        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "UPDATE " + REG_N_LM_TABLE + " SET " + SYNC_COL + "=" + status + " WHERE " +
                ADM_COUNTRY_CODE_COL + "=" + aPeople.getCountryCode() + " AND " + LAY_R1_LIST_CODE_COL + "=" + aPeople.getDistrictCode() + " AND " +
                LAY_R2_LIST_CODE_COL + "=" + aPeople.getUpazillaCode() + " AND " +
                LAY_R3_LIST_CODE_COL + "=" + aPeople.getUnitCode() + " AND " +
                LAY_R4_LIST_CODE_COL + "=" + aPeople.getVillageCode() + " AND " + HHID_COL + "=" + aPeople.getHh_id() + " AND " +
                HH_MEM_ID + "=" + aPeople.getMemId();

        //String selectQuery = "UPDATE " + REG_N_HH_TABLE + " SET " + SYNC_COL + "=" + status +" WHERE " + ID_COL + "=" + update_id;
        db.execSQL(selectQuery);
        db.close();
    }


    /**
     * update regn Ass n ser prg tabel upload status
     */
    public void updateRegNAsgProgSrvStatus(AssignDataModel aPeople, int status) {

        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "UPDATE " + REG_N_ASSIGN_PROG_SRV_TABLE + " SET " + SYNC_COL + "=" + status + " WHERE " +
                ADM_COUNTRY_CODE_COL + "=" + aPeople.getCountryCode() + " AND " + LAY_R1_LIST_CODE_COL + "=" + aPeople.getDistrictCode() + " AND " +
                LAY_R2_LIST_CODE_COL + "=" + aPeople.getUpazillaCode() + " AND " +
                LAY_R3_LIST_CODE_COL + "=" + aPeople.getUnitCode() + " AND " +
                LAY_R4_LIST_CODE_COL + "=" + aPeople.getVillageCode() + " AND " + HHID_COL + "=" + aPeople.getHh_id() + " AND " +
                REG_N_ASSIGN_PROG_SRV_HH_MEM_ID + "=" + aPeople.getMemId();

        //String selectQuery = "UPDATE " + REG_N_HH_TABLE + " SET " + SYNC_COL + "=" + status +" WHERE " + ID_COL + "=" + update_id;
        db.execSQL(selectQuery);
        db.close();
    }


    public void addServiceFromOnline(String c_code, String donorCode, String awardCode, String districtCode, String upzellaCode, String uname, String vname, String hhid, String memid, String program, String service, String opCode, String opMonth, String serviceSl, String srvCenterCode,
                                     String serviceDt, String SrvStatus, String distStatus, String distDate, String fdpCode, String wd, String distFlag, String groupCode, String is_online) {


        String entryBy = "";
        String entryDate = "";
        insertIntoSrvTable(c_code, donorCode, awardCode, districtCode, upzellaCode, uname, vname, hhid, memid, program, service, opCode, opMonth, serviceSl, srvCenterCode, serviceDt, SrvStatus, distStatus, distDate, fdpCode, wd, distFlag, groupCode, is_online, entryBy, entryDate);


    }

    /* added by service */

    public boolean isMemberExitsSrvTable(ServiceDataModel srvData) {

        boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();


        String sql = SQLiteQuery.selectSrvTable_sql(srvData.getC_code(), srvData.getDonor_code(), srvData.getAward_code(), srvData.getDistrictCode(), srvData.getUpazillaCode(), srvData.getUnitCode(), srvData.getVillageCode(), srvData.getHHID(), srvData.getMemberId(), srvData.getProgram_code(), srvData.getService_code(), srvData.getOpCode(), srvData.getOpMontheCode(), srvData.getServiceDTCode(), srvData.getServiceSLCode(), srvData.getDistFlag());


        Cursor cursor = db.rawQuery(sql, null);

        flag = (cursor.getCount() > 0) ? true : false;

        cursor.close();
        db.close();
        return flag;
    }

    public void updateMemberIntoServiceTable(ServiceDataModel srvData, String entryBy, String entryDate) {


        SQLiteDatabase db = this.getWritableDatabase();


        String sql = SQLiteQuery.srvTable_And_sql(srvData.getC_code(), srvData.getDonor_code(), srvData.getAward_code(), srvData.getDistrictCode(), srvData.getUpazillaCode(), srvData.getUnitCode(), srvData.getVillageCode(), srvData.getHHID(), srvData.getMemberId(), srvData.getProgram_code(), srvData.getService_code(), srvData.getOpCode(), srvData.getOpMontheCode(), srvData.getServiceDTCode(), srvData.getServiceSLCode(), srvData.getDistFlag());


        ContentValues values = new ContentValues();

        values.put(WORK_DAY_COL, srvData.getWorkingDay());


        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        db.update(SERVICE_TABLE, values, sql, null);


        db.close();
    }


    /**
     * todo: Implements Service Center Code & wd
     * The
     *
     * @param srvData   Service Data Model
     * @param entryBy   entryBy Indicant who insert the data
     * @param entryDate EntryDate
     */
    public void addMemberIntoServiceTable(ServiceDataModel srvData, String entryBy, String entryDate) {
        String SrvStatus = "O";
        String distStatus = "";
        String distDate = "";

        String fdpCode = srvData.getFPDCode();
        String wd = srvData.getWorkingDay();

        /**
         * there is no use of group code just for test
         */
        String distFlag = srvData.getDistFlag();
        String groupCode = "";
        String srvCenterCode = srvData.getServiceCenterCode();
        String is_online = "0";
        insertIntoSrvTable(srvData.getC_code(), srvData.getDonor_code(), srvData.getAward_code(), srvData.getDistrictCode(), srvData.getUpazillaCode()
                , srvData.getUnitCode(), srvData.getVillageCode(), srvData.getHHID(),
                srvData.getMemberId(), srvData.getProgram_code(), srvData.getService_code(), srvData.getOpCode(), srvData.getOpMontheCode(), srvData.getServiceSLCode()
                , srvCenterCode, srvData.getServiceDTCode(), SrvStatus, distStatus, distDate, fdpCode, wd, distFlag, groupCode, is_online, entryBy, entryDate);


    }


    /**
     * This method save the Service Data
     *
     * @param c_code             Country Code
     * @param donorCode          donor code
     * @param awardCode          award code
     * @param layR1Code          layR1 Code
     * @param layR2Code          layR2 Code
     * @param layR3Code          layR3 Code
     * @param layR4Code          layR4 Code
     * @param hhid               house hold id
     * @param memId              member id
     * @param progCode           program code
     * @param srvCode            service Code
     * @param opCode             op code
     * @param opMonthCode        op month Code
     * @param srvSl              service serial
     * @param srvCenterCode      service center code
     * @param srvDate            date of getting  service
     * @param srvStatus          service status indecate that either  either service eligible for member
     * @param distributionStatus it is part of distribution mode
     * @param distributionDate   it is part Distribution  mode
     * @param fdpCode            Food Distribution Code
     * @param wd                 working Day
     * @param groupCode          only for trace  no need to intregrated
     * @param is_online          sync Status
     * @param entryBy            who entry the data
     * @param entryDate          entryDate
     */

    public void insertIntoSrvTable(String c_code, String donorCode, String awardCode, String layR1Code, String layR2Code, String layR3Code, String layR4Code, String hhid, String memId, String progCode, String srvCode, String opCode, String opMonthCode, String srvSl, String srvCenterCode,
                                   String srvDate, String srvStatus, String distributionStatus, String distributionDate, String fdpCode, String wd, String distFlag, String groupCode, String is_online, String entryBy, String entryDate) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(ADM_COUNTRY_CODE_COL, c_code);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);

        values.put(LAY_R1_LIST_CODE_COL, layR1Code);
        values.put(LAY_R2_LIST_CODE_COL, layR2Code);
        values.put(LAY_R3_LIST_CODE_COL, layR3Code);
        values.put(LAY_R4_LIST_CODE_COL, layR4Code);
        values.put(HHID_COL, hhid);
        values.put(MEM_ID_COL, memId);

        values.put(PROG_CODE_COL, progCode);
        values.put(SRV_CODE_COL, srvCode);

        values.put(OPERATION_CODE_COL, opCode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);

        values.put(SERVICE_TABLE_SERVICE_SL_COL, srvSl);
        values.put(SERVICE_CENTER_CODE_COL, srvCenterCode);

        values.put(SERVICE_TABLE_SERVICE_DT_COL, srvDate);
        values.put(SRV_STATUS_COL, srvStatus);

        values.put(DISTRIBUTION_STATUS_COL, distributionStatus);
        values.put(DIST_DT_COL, distributionDate);
        values.put(FDP_CODE_COL, fdpCode);

        values.put(WORK_DAY_COL, wd);
        values.put(DIST_FLAG_COL, distFlag);
        /**
         * Group Code is not necessary to insert by Mobile Device
         */
        values.put(GROUP_CODE_COL, groupCode);

        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

//        values.put(SYNC_COL, is_online); // Sync Status


        // Inserting Row
        db.insert(SERVICE_TABLE, null, values);
        db.close(); // Closing database connection


    }


    public void addServiceExtendedFromOnline(String c_code, String donorCode, String awardCode, String districtCode,
                                             String upzellaCode, String unCode, String vCode, String hhid,
                                             String memid, String program, String service, String opCode, String opMonthCode,
                                             String voItmSpec, String voItmUnit, String voRefNumber, String voItmCost, String is_online) {

        addServiceExtendedTable(c_code, districtCode, upzellaCode, unCode, vCode, hhid, memid, donorCode, awardCode, program,
                service, opCode, opMonthCode, voItmSpec, voItmUnit, voRefNumber,
                voItmCost, "", "", "", is_online);


    }


    /** HERE REGISTRATION CRUDE OPERATION*/

    /**
     * Storing Registration Data into database
     */


    public void addRegistrationFromOnline(String c_code, String dname, String upname, String uname, String vname, String pid, String r_date, String pname, String sex, String HHSize, String latitude, String longitude, String AGLand, String VStatus, String MStatus, String EntryBy, String EntryDate, String VSLAGroup, String GpsLongSwap, String regNAddLookupCode,
                                          String HHCategories, String lessT2Ymale, String lessT2YFemale, String m2to5, String f2to5, String m6to12, String f6to12, String m13to17, String f13to17, String orpLT18m, String orpLT18f, String m18to59, String f18to59, String eld60m, String eld60f, String pwsNo, String chroIllNo, String livingDecasedContractE, String extraChildBecauseE,
                                          String extraElderlyBecauseE, String extraIllBecauseEbola, String BCntCattle, String BValCattle, String ACntCattle, String AValCattle, String BCntSGoats, String BValSGoats, String ACntSGoats, String AValSGoats, String BCntPoultry, String BValPoultry, String ACntPoultry, String AfValPoultry, String BCntOther, String BValOther, String ACntOther, String AValOther,
                                          String BAcreCultivable, String BValCultivable, String AAcreCultivable, String AValueCultivable, String BAcreNonCultivable, String BValNonCultivable, String AAcreNonCultivable, String AValNonCultivable, String BAcreOrchards, String BValOrchards, String AAcreOrchards, String AValOrchards, String BValCrop, String AValCrop, String BValLivestock, String AValLivestock,
                                          String BValSmallBusiness, String AValSmallBusiness, String BValEmployment, String AValEmployment, String BValRemittances, String AValRemittances, String BCntWageEnr, String ATCntWageEnr, String is_online, String wRank
            , String LTp2Hectres, String LT3mFoodStock, String NoMajorCommonLiveStock, String ReceiveNoFormalWages, String NoIGA, String RelyPiecework) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ADM_COUNTRY_CODE_COL, c_code); // country name
        values.put(LAY_R1_LIST_CODE, dname); // district name
        values.put(REGISTRATION_TABLE_UPZILLA_CODE_COL, upname); // upazilla name
        values.put(REGISTRATION_TABLE_UNION_CODE_COL, uname); // Unit name
        values.put(REGISTRATION_TABLE_VILLAGE_CODE_COL, vname); // Unit name
        values.put(REGISTRATION_TABLE_HHID, pid); // Personal name
        values.put(REG_DATE_COL, r_date);                           // Registration Date
        values.put(REGISTRATION_TABLE_HH_HEAD_NAME, pname); // Person name
        values.put(REGISTRATION_TABLE_HH_HEAD_SEX, sex); // sex
        values.put(HH_SIZE, HHSize); // House hold Size
        values.put(REGISTRATION_TABLE_GPS_LAT, latitude); // Latitude
        values.put(REGISTRATION_TABLE_GPS_LONG, longitude); // Longitude
        values.put(AG_LAND, AGLand); // Agriculture land
        values.put(V_STATUS, VStatus); // verify Status
        values.put(M_STATUS, MStatus); // Marriage  Status
        values.put(GPS_LONG_SWAP, GpsLongSwap); //.............................................................
        values.put(REGISTRATION_TABLE_REGN_ADDRESS_LOOKUP_CODE_COL, regNAddLookupCode); //
        values.put(REGISTRATION_TABLE_HOUSE_HOLD_TYPE_CODE_COL, HHCategories); // House Hold Categories
        values.put(LT2YRS_M_COL, lessT2Ymale); // Less than 2 Year Male
        values.put(LT2YRS_F_COL, lessT2YFemale); // Less than 2 Year Female
        values.put(M_2TO5YRS_COL, m2to5);
        values.put(F_2TO5YRS_COL, f2to5);
        values.put(M_6TO12YRS_COL, m6to12);
        values.put(F_6TO12YRS_COL, f6to12);
        values.put(M_13TO17YRS_COL, m13to17);
        values.put(F_13TO17YRS_COL, f13to17);
        values.put(ORPHN_LT18YRS_M_COL, orpLT18m);
        values.put(ORPHN_LT18YRS_F_COL, orpLT18f);
        values.put(ADLT_18TO59_M_COL, m18to59);
        values.put(ADLT_18TO59_F_COL, f18to59);
        values.put(ELD_60P_M_COL, eld60m);
        values.put(ELD_60P_F_COL, eld60f);
        values.put(PLW_NO_COL, pwsNo);
        values.put(CHRO_ILL_NO_COL, chroIllNo);
        values.put(DECEASED_CONTRACT_EBOLA_COL, livingDecasedContractE);
        values.put(EXTRA_CHILD_CAUSE_EBOLA_COL, extraChildBecauseE);
        values.put(EXTRA_ELDERLY_CAUSE_EBOLA_COL, extraElderlyBecauseE);
        values.put(EXTRA_CHRONICALLY_ILL_CAUSE_EBOLA_COL, extraIllBecauseEbola);//extraChronicallyIllBecauseEbola
        values.put(BRF_COUNT_CATTLE_COL, BCntCattle);
        values.put(BRF_VALUE_CATTLE_COL, BValCattle);
        values.put(AFT_COUNT_CATTLE_COL, ACntCattle);
        values.put(AFT_VALUE_CATTLE_COL, AValCattle);
        values.put(BRF_COUNT_SGOATS_COL, BCntSGoats);
        values.put(BRF_VALUE_SGOATS_COL, BValSGoats);
        values.put(AFT_COUNT_SGOATS_COL, ACntSGoats);
        values.put(AFT_VALUE_SGOATS_COL, AValSGoats);
        values.put(BRF_COUNT_POULTRY_COL, BCntPoultry);
        values.put(BRF_VALUE_POULTRY_COL, BValPoultry);
        values.put(AFT_COUNT_POULTRY_COL, ACntPoultry);
        values.put(AFT_VALUE_POULTRY_COL, AfValPoultry);
        values.put(BRF_COUNT_OTHER_COL, BCntOther);
        values.put(BRF_VALUE_OTHER_COL, BValOther);
        values.put(AFT_COUNT_OTHER_COL, ACntOther);
        values.put(AFT_VALUE_OTHER_COL, AValOther);
        values.put(BRF_ACRE_CULTIVABLE_COL, BAcreCultivable);
        values.put(BRF_VALUE_CULTIVABLE_COL, BValCultivable);
        values.put(AFT_ACRE_CULTIVABLE_COL, AAcreCultivable);
        values.put(AFT_VALUE_CULTIVABLE_COL, AValueCultivable);
        values.put(BRF_ACRE_NON_CULTIVABLE_COL, BAcreNonCultivable);
        values.put(BRF_VAL_NON_CULTIVABLE_COL, BValNonCultivable);
        values.put(AFT_ACRE_NON_CULTIVABLE, AAcreNonCultivable);
        values.put(AFT_VAL_NON_CULTIVABLE, AValNonCultivable);
        values.put(BRF_ACRE_ORCHARDS, BAcreOrchards);
        values.put(BRF_VAL_ORCHARDS, BValOrchards);
        values.put(AFT_ACRE_ORCHARDS, AAcreOrchards);
        values.put(AFT_VAL_ORCHARDS, AValOrchards);
        values.put(BRF_VAL_CROP, BValCrop);
        values.put(AFT_VAL_CROP, AValCrop);
        values.put(BRF_VAL_LIVESTOCK, BValLivestock);
        values.put(AFT_VAL_LIVESTOCK, AValLivestock);
        values.put(BRF_VAL_SMALL_BUSINESS, BValSmallBusiness);
        values.put(AFT_VAL_SMALL_BUSINESS, AValSmallBusiness);
        values.put(BRF_VAL_EMPLOYMENT, BValEmployment);
        values.put(AFT_VAL_EMPLOYMENT, AValEmployment);
        values.put(BRF_VAL_REMITTANCES, BValRemittances);
        values.put(AFT_VAL_REMITTANCES, AValRemittances);
        values.put(BRF_CNT_WAGEENR, BCntWageEnr);
        values.put(AFT_CNT_WAGEENR, ATCntWageEnr);
        values.put(ENTRY_BY, EntryBy); // Longitude
        values.put(ENTRY_DATE, EntryDate); // Date of creation


        values.put(VSLA_GROUP, VSLAGroup);
        values.put(SYNC_COL, is_online); // Sync Status
        values.put(W_RANK_COL, wRank); // Sync Status


        values.put(LTP_2_HECTRES_COL, LTp2Hectres);
        values.put(LT_3_FOOD_STOCK_COL, LT3mFoodStock);
        values.put(NO_MAJOR_COMMON_LIVE_STOCK_COL, NoMajorCommonLiveStock);
        values.put(RECEIVE_NO_FORMAL_WAGES_COL, ReceiveNoFormalWages);
        values.put(NO_IGA_COL, NoIGA);
        values.put(RELY_PICE_EORK_COL, RelyPiecework);


        // Inserting Row
        db.insert(REG_N_HH_TABLE, null, values);
        db.close(); // Closing database connection

        //     Log.d(TAG, "New Registration data added into Registration Table: " + id);
    }


//    public ContentValues getHouseHoldData(String c_code, String districtCode, String upazillaCode, String unionCode, String villageCode, String hhID) {
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//
//        ContentValues values = new ContentValues();
//
//
//        String selectQuery = SQLiteQuery.getHouseHoldData_sql(hhID, c_code, districtCode, upazillaCode, unionCode, villageCode);
//
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                do {
//
//
//                    values.put("country_name", cursor.getString(cursor.getColumnIndex("country_name")));
//                    values.put("str_district", cursor.getString(cursor.getColumnIndex(DISTRICT_NAME_COL)));
//                    values.put("str_upazilla", cursor.getString(cursor.getColumnIndex(UPZILLA_NAME_COL)));
//                    values.put("str_union", cursor.getString(cursor.getColumnIndex(LAY_R3_LIST_NAME)));
//                    values.put("str_village", cursor.getString(cursor.getColumnIndex(LAY_R4_LIST_NAME_COL)));
//
//                    values.put("str_c_code", cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
//                    values.put("str_districtCode", cursor.getString(cursor.getColumnIndex("R_District")));
//                    values.put("str_upazillaCode", cursor.getString(cursor.getColumnIndex("R_Upazilla")));
//                    values.put("str_unionCode", cursor.getString(cursor.getColumnIndex("R_Union")));
//                    values.put("str_villageCode", cursor.getString(cursor.getColumnIndex("R_Village")));
//
//
//                    values.put("str_reg_date", cursor.getString(cursor.getColumnIndex(REG_DATE_COL)));
//                    values.put("str_hhName", cursor.getString(cursor.getColumnIndex(PNAME_COL)));
//                    values.put("str_gender", cursor.getString(cursor.getColumnIndex(SEX_COL)));
//                    values.put("str_hhsize", cursor.getString(cursor.getColumnIndex(HH_SIZE)));
//                    values.put("str_latitude", cursor.getString(cursor.getColumnIndex(LATITUDE_COL)));
//                    values.put("str_longitude", cursor.getString(cursor.getColumnIndex(LATITUDE_COL)));
//                    values.put("str_agland", cursor.getString(cursor.getColumnIndex(AG_LAND)));
//                    values.put("str_vstatus", cursor.getString(cursor.getColumnIndex(V_STATUS)));
//                    values.put("str_mstatus", cursor.getString(cursor.getColumnIndex(M_STATUS)));
//                    values.put("str_entry_by", cursor.getString(cursor.getColumnIndex(ENTRY_BY)));
//                    values.put("str_entry_date", cursor.getString(cursor.getColumnIndex(ENTRY_DATE)));
//                }
//                while (cursor.moveToNext());
//
//
//            }
//
//
//        }
//        if (cursor != null)
//            cursor.close();
//        db.close();
//
//        return values;
//    }

    /**
     * @param cCode country code
     * @return Dt Response Month Name
     */
    public String getDtResponseMonthName(String cCode, String opMonthCode) {
        String monthName = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = SQLiteQuery.loadDtMonth_sql(cCode, "5", opMonthCode);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            monthName = cursor.getString(cursor.getColumnIndex(MONTH_LABEL_COL));

        }
        if (cursor != null)
            cursor.close();
        db.close();
        return monthName;

    }

    public void editMalawiMemberData(String cCode, String layR1Code, String layR2Code, String layR3Code,
                                     String layR4Code, String hhID,
                                     String memID, String memName, String str_gender, String str_relation, String str_lmp_date, String str_child_dob, String str_elderly, String str_disabled, String str_age, int pID, String memAgeTypeFlag) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        String where = SQLiteQuery.editMalawiMemberData_sql(cCode, layR1Code, layR2Code, layR3Code, layR4Code, hhID, memID);


        values.put(MEM_NAME_COL, memName);                                                          // Member name
        values.put(SEX_COL, str_gender);                                                            // sex
        values.put(RELATION_COL, str_relation);                                                     // relation

        values.put(MEM_AGE, str_age);                                                               // age

        values.put(MEM_TYPE_FLAG, memAgeTypeFlag);                                                  // member age type flag := AM /AF/CM/ CF
        db.update(REGISTRATION_MEMBER_TABLE, values, where, null);                                  // updating Row into local database


        db.close();                                                                                 // Closing database connection


        SQLServerSyntaxGenerator malawiMember = new SQLServerSyntaxGenerator();
        malawiMember.setAdmCountryCode(cCode);
        malawiMember.setLayR1ListCode(layR1Code);
        malawiMember.setLayR2ListCode(layR2Code);
        malawiMember.setLayR3ListCode(layR3Code);
        malawiMember.setLayR4ListCode(layR4Code);
        malawiMember.setHHID(hhID);
        malawiMember.setMemID(memID);
        malawiMember.setMmMemName(memName);
        malawiMember.setMmMemSex(str_gender);
        malawiMember.setMmHHRelation(str_relation);
        malawiMember.setMmMemAge(str_age);
        malawiMember.setMemTypeFlag(memAgeTypeFlag);


        insertIntoUploadTable(malawiMember.updateRegNMemberForMalawi());


    }

    public void editLiberiaMemberData(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String str_hhID, String str_hhMemID,
                                      String regNDate,
                                      String memOtherID, String memName_First,
                                      String memName_Middle, String memName_Last, String birthYear,
                                      String maritalStatus, String contactNo,
                                      String Photo,
                                      String type_ID, String id_NO,
                                      String v_BSCMemName1_First, String v_BSCMemName1_Mid, String v_BSCMemName1_Last,
                                      String v_BSCMem1_TitlePosition, String v_BSCMemName2_First, String v_BSCMemName2_Mid,
                                      String v_BSCMemName2_Last, String v_BSCMem2_TitlePos, String proxy_Desig, String proxy_Name_First,
                                      String proxy_Name_Mid, String proxy_Name_Last, String proxy_BirthYear,
                                      String Proxy_Photo,
                                      String proxy_Type_ID, String proxy_ID_NO, String p_BSCMemName1_First, String p_BSCMemName1_Middle,
                                      String p_BSCMemName1_Last, String p_BSCMem1_TitlePosition, String p_BSCMemName2_First, String p_BSCMemName2_Middle,
                                      String p_BSCMemName2_Last, String p_BSCMem2_TitlePosition,
                                      String str_entry_by, String str_entry_date) {
        SQLiteDatabase db = this.getWritableDatabase();

        String where = SQLiteQuery.editLiberiaMemberData_sql(str_c_code, str_district, str_upazilla, str_union, str_village, str_hhID, str_hhMemID);


        ContentValues values = new ContentValues();


        values.put(MEM_NAME_COL, memName_First); // Member name

        values.put(ENTRY_BY, str_entry_by); // entry by
        values.put(ENTRY_DATE, str_entry_date); // entry Date


        values.put(REG_DATE_COL, regNDate);


        values.put(BIRTH_YEAR_COL, birthYear);
        values.put(MARITAL_STATUS_COL, maritalStatus);
        values.put(CONTACT_NO_COL, contactNo);
        values.put(MEMBER_OTHER_ID_COL, memOtherID);
        values.put(MEM_NAME_FIRST_COL, memName_First);
        values.put(MEM_NAME_MIDDLE_COL, memName_Middle);
        values.put(MEM_NAME_LAST_COL, memName_Last);

        /** TODO : work this photo*/
        values.put(PHOTO_COL, Photo);

        values.put(TYPE_ID_COL, type_ID);
        values.put(ID_NO_COL, id_NO);
        values.put(V_BSC_MEM_1_NAME_FIRST_COL, v_BSCMemName1_First);
        values.put(V_BSC_MEM_1_NAME_MIDDLE_COL, v_BSCMemName1_Mid);
        values.put(V_BSC_MEM_1_NAME_LAST_COL, v_BSCMemName1_Last);
        values.put(V_BSC_MEM_1_TITLE_COL, v_BSCMem1_TitlePosition);

        values.put(V_BSC_MEM_2_NAME_FIRST_COL, v_BSCMemName2_First);
        values.put(V_BSC_MEM_2_NAME_MIDDLE_COL, v_BSCMemName2_Mid);
        values.put(V_BSC_MEM_2_NAME_LAST_COL, v_BSCMemName2_Last);
        values.put(V_BSC_MEM_2_TITLE_COL, v_BSCMem2_TitlePos);

        values.put(PROXY_DESIGNATION_COL, proxy_Desig);
        values.put(PROXY_NAME_FIRST_COL, proxy_Name_First);
        values.put(PROXY_NAME_MIDDLE_COL, proxy_Name_Mid);
        values.put(PROXY_NAME_LAST_COL, proxy_Name_Last);


        values.put(PROXY_BIRTH_YEAR_COL, proxy_BirthYear);

        values.put(PROXY_PHOTO_COL, Proxy_Photo);


        values.put(PROXY_TYPE_ID_COL, proxy_Type_ID);
        values.put(PROXY_ID_NO_COL, proxy_ID_NO);
        values.put(PROXY_BSC_MEM_1_NAME_FIRST_COL, p_BSCMemName1_First);
        values.put(PROXY_BSC_MEM_1_NAME_MIDDLE_COL, p_BSCMemName1_Middle);
        values.put(PROXY_BSC_MEM_1_NAME_LAST_COL, p_BSCMemName1_Last);
        values.put(PROXY_BSC_MEM_1_TITLE_COL, p_BSCMem1_TitlePosition);
        values.put(PROXY_BSC_MEM_2_NAME_FIRST_COL, p_BSCMemName2_First);
        values.put(PROXY_BSC_MEM_2_NAME_MIDDLE_COL, p_BSCMemName2_Middle);
        values.put(PROXY_BSC_MEM_2_NAME_LAST_COL, p_BSCMemName2_Last);
        values.put(PROXY_BSC_MEM_2_TITLE_COL, p_BSCMem2_TitlePosition);


        // Inserting Row into local database

        // updating Row into local database
        db.update(REGISTRATION_MEMBER_TABLE, values, where, null);


        db.close(); // Closing database connection

        //    Log.d(TAG, "Updated Member data: " + mID);

    }

    /**
     * Storing Member Data into database for Malawi
     */

    public void addMemberDataForMalawi(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String str_hhID, String str_hhMemID, String str_MemName, String str_gender, String str_relation, String str_entry_by, String str_entry_date, String lmp_date, String child_dob, String str_elderly, String str_disabled, String str_age, String regDate, int pID, String memAgeFlag) {
        addMemberData(str_c_code, str_district, str_upazilla, str_union, str_village, str_hhID, str_hhMemID, str_MemName, str_gender, str_relation, str_entry_by, str_entry_date, lmp_date, child_dob, str_elderly, str_disabled, str_age
                , regDate, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, memAgeFlag);

        SQLServerSyntaxGenerator malawiMember = new SQLServerSyntaxGenerator();
        malawiMember.setAdmCountryCode(str_c_code);
        malawiMember.setLayR1ListCode(str_district);
        malawiMember.setLayR2ListCode(str_upazilla);
        malawiMember.setLayR3ListCode(str_union);
        malawiMember.setLayR4ListCode(str_village);
        malawiMember.setHHID(str_hhID);
        malawiMember.setMemID(str_hhMemID);
        malawiMember.setMmMemName(str_MemName);
        malawiMember.setMmMemSex(str_gender);
        malawiMember.setMmHHRelation(str_relation);
        malawiMember.setEntryBy(str_entry_by);
        malawiMember.setEntryDate(str_entry_date);
        malawiMember.setMmMemAge(str_age);
        malawiMember.setRegNDate(regDate);
        malawiMember.setMemTypeFlag(memAgeFlag);

        insertIntoUploadTable(malawiMember.insertIntoRegNMemberForMalawi());


    }

//    public long addMemberDataForLiberia(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String str_hhID, String str_hhMemID,
//                                        String regNDate,
//                                        String memOtherID, String memName_First,
//                                        String memName_Middle, String memName_Last, String birthYear,
//                                        String maritalStatus, String contactNo,
//                                        String Photo,
//                                        String type_ID, String id_NO,
//                                        String v_BSCMemName1_First, String v_BSCMemName1_Mid, String v_BSCMemName1_Last,
//                                        String v_BSCMem1_TitlePosition, String v_BSCMemName2_First, String v_BSCMemName2_Mid,
//                                        String v_BSCMemName2_Last, String v_BSCMem2_TitlePos, String proxy_Desig, String proxy_Name_First,
//                                        String proxy_Name_Mid, String proxy_Name_Last, String proxy_BirthYear,
//                                        String Proxy_Photo,
//                                        String proxy_Type_ID, String proxy_ID_NO, String p_BSCMemName1_First, String p_BSCMemName1_Middle,
//                                        String p_BSCMemName1_Last, String p_BSCMem1_TitlePosition, String p_BSCMemName2_First, String p_BSCMemName2_Middle,
//                                        String p_BSCMemName2_Last, String p_BSCMem2_TitlePosition,
//                                        String str_entry_by, String str_entry_date) {
//        //    addMemberData(str_c_code, str_district,  str_upazilla,  str_union,  str_village,  str_hhID,  str_hhMemID,  str_MemName,  str_gender,  str_relation,  str_entry_by,  str_entry_date,  lmp_date,  child_dob,  str_elderly,  str_disabled,  str_age
//
//        String str_gender = null, str_relation = null, lmp_date = null, child_dob = null, str_elderly = null, str_disabled = null;
//        String str_age = null;
//        String grp_code = null;
//
//        long idRow = addMemberData(str_c_code, str_district, str_upazilla, str_union, str_village,
//                str_hhID, str_hhMemID, memName_First, str_gender, str_relation, str_entry_by, str_entry_date,
//                lmp_date, child_dob, str_elderly, str_disabled, str_age, regNDate, birthYear, maritalStatus
//                , contactNo, memOtherID, memName_First, memName_Middle, memName_Last,
//                Photo//.toString()
//                ,
//                type_ID, id_NO, v_BSCMemName1_First, v_BSCMemName1_Mid, v_BSCMemName1_Last, v_BSCMem1_TitlePosition,
//                v_BSCMemName2_First, v_BSCMemName2_Mid, v_BSCMemName2_Last, v_BSCMem2_TitlePos,
//                proxy_Desig, proxy_Name_First, proxy_Name_Mid, proxy_Name_Last, proxy_BirthYear,
//                Proxy_Photo//.toString()
//                ,
//                proxy_Type_ID, proxy_ID_NO,
//                p_BSCMemName1_First, p_BSCMemName1_Middle, p_BSCMemName1_Last, p_BSCMem1_TitlePosition,
//                p_BSCMemName2_First, p_BSCMemName2_Middle, p_BSCMemName2_Last, p_BSCMem2_TitlePosition, grp_code);
//
//
////        Log.d(TAG, " add member Liberia id: " + idRow);
//
//
//        return idRow;
//
//
//    }

    public long addMemberDataForLiberia(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String str_hhID, String str_hhMemID,
                                        String regNDate,
                                        String memOtherID, String memName_First,
                                        String memName_Middle, String memName_Last, String birthYear,
                                        String maritalStatus, String contactNo,
                                        String Photo,
                                        String type_ID, String id_NO,
                                        String v_BSCMemName1_First, String v_BSCMemName1_Mid, String v_BSCMemName1_Last,
                                        String v_BSCMem1_TitlePosition, String v_BSCMemName2_First, String v_BSCMemName2_Mid,
                                        String v_BSCMemName2_Last, String v_BSCMem2_TitlePos, String proxy_Desig, String proxy_Name_First,
                                        String proxy_Name_Mid, String proxy_Name_Last, String proxy_BirthYear,
                                        String Proxy_Photo,
                                        String proxy_Type_ID, String proxy_ID_NO, String p_BSCMemName1_First, String p_BSCMemName1_Middle,
                                        String p_BSCMemName1_Last, String p_BSCMem1_TitlePosition, String p_BSCMemName2_First, String p_BSCMemName2_Middle,
                                        String p_BSCMemName2_Last, String p_BSCMem2_TitlePosition,
                                        String str_entry_by, String str_entry_date, String memTypeFlag) {
        //    addMemberData(str_c_code, str_district,  str_upazilla,  str_union,  str_village,  str_hhID,  str_hhMemID,  str_MemName,  str_gender,  str_relation,  str_entry_by,  str_entry_date,  lmp_date,  child_dob,  str_elderly,  str_disabled,  str_age

        String str_gender = null, str_relation = null, lmp_date = null, child_dob = null, str_elderly = null, str_disabled = null;
        String str_age = null;
        String grp_code = null;

        long idRow = addMemberData(str_c_code, str_district, str_upazilla, str_union, str_village,
                str_hhID, str_hhMemID, memName_First, str_gender, str_relation, str_entry_by, str_entry_date,
                lmp_date, child_dob, str_elderly, str_disabled, str_age, regNDate, birthYear, maritalStatus
                , contactNo, memOtherID, memName_First, memName_Middle, memName_Last,
                Photo//.toString()
                ,
                type_ID, id_NO, v_BSCMemName1_First, v_BSCMemName1_Mid, v_BSCMemName1_Last, v_BSCMem1_TitlePosition,
                v_BSCMemName2_First, v_BSCMemName2_Mid, v_BSCMemName2_Last, v_BSCMem2_TitlePos,
                proxy_Desig, proxy_Name_First, proxy_Name_Mid, proxy_Name_Last, proxy_BirthYear,
                Proxy_Photo//.toString()
                ,
                proxy_Type_ID, proxy_ID_NO,
                p_BSCMemName1_First, p_BSCMemName1_Middle, p_BSCMemName1_Last, p_BSCMem1_TitlePosition,
                p_BSCMemName2_First, p_BSCMemName2_Middle, p_BSCMemName2_Last, p_BSCMem2_TitlePosition, grp_code, memTypeFlag);


//        Log.d(TAG, " add member Liberia id: " + idRow);


        return idRow;


    }

  /*  public long addMemberData(String c_code, String disCode, String upCode, String unCode, String vCode,
                              String str_hhID, String str_hhMemID, String str_MemName, String str_gender, String str_relation,
                              String str_entry_by, String str_entry_date, String lmp_date,
                              String child_dob, String elderly, String disabled,
                              String str_age, String regNDate, String birthYear,
                              String maritalStatus, String contactNo, String memOtherID, String memName_First,
                              String memName_Middle, String memName_Last,
                              String Photo,
                              String type_ID,
                              String id_NO, String v_BSCMemName1_First, String v_BSCMemName1_Mid, String v_BSCMemName1_Last,
                              String v_BSCMem1_TitlePosition, String v_BSCMemName2_First, String v_BSCMemName2_Mid,
                              String v_BSCMemName2_Last, String v_BSCMem2_TitlePos, String proxy_Desig, String proxy_Name_First,
                              String proxy_Name_Mid, String proxy_Name_Last, String proxy_BirthYear,
                              String Proxy_Photo,
                              String proxy_Type_ID, String proxy_ID_NO, String p_BSCMemName1_First, String p_BSCMemName1_Middle,
                              String p_BSCMemName1_Last, String p_BSCMem1_TitlePosition, String p_BSCMemName2_First, String p_BSCMemName2_Middle,
                              String p_BSCMemName2_Last, String p_BSCMem2_TitlePosition, String grpCode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ADM_COUNTRY_CODE_COL, c_code);                                                   // country name
        values.put(LAY_R1_LIST_CODE_COL, disCode);                                                           // district name
        values.put(LAY_R2_LIST_CODE_COL, upCode); // upazilla name
        values.put(LAY_R3_LIST_CODE_COL, unCode); // Unit name
        values.put(LAY_R4_LIST_CODE_COL, vCode); // Village name
        values.put(HHID_COL, str_hhID); // Registered ID
        values.put(HH_MEM_ID, str_hhMemID); // Member ID
        values.put(MEM_NAME_COL, str_MemName); // Member name
        values.put(SEX_COL, str_gender); // sex
        values.put(RELATION_COL, str_relation); // relation
        values.put(ENTRY_BY, str_entry_by); // entry by
        values.put(ENTRY_DATE, str_entry_date); // entry Date
        values.put(LMP_DATE, lmp_date);
        values.put(CHILD_DOB, child_dob);
        values.put(ELDERLY, elderly);
        values.put(DISABLE, disabled);
        values.put(MEM_AGE, str_age);           // member age for malawi
        values.put(REG_DATE_COL, regNDate);
        values.put(BIRTH_YEAR_COL, birthYear);
        values.put(MARITAL_STATUS_COL, maritalStatus);
        values.put(CONTACT_NO_COL, contactNo);
        values.put(MEMBER_OTHER_ID_COL, memOtherID);
        values.put(MEM_NAME_FIRST_COL, memName_First);
        values.put(MEM_NAME_MIDDLE_COL, memName_Middle);
        values.put(MEM_NAME_LAST_COL, memName_Last);
        values.put(PHOTO_COL, Photo);
        values.put(TYPE_ID_COL, type_ID);
        values.put(ID_NO_COL, id_NO);
        values.put(V_BSC_MEM_1_NAME_FIRST_COL, v_BSCMemName1_First);
        values.put(V_BSC_MEM_1_NAME_MIDDLE_COL, v_BSCMemName1_Mid);
        values.put(V_BSC_MEM_1_NAME_LAST_COL, v_BSCMemName1_Last);
        values.put(V_BSC_MEM_1_TITLE_COL, v_BSCMem1_TitlePosition);
        values.put(V_BSC_MEM_2_NAME_FIRST_COL, v_BSCMemName2_First);
        values.put(V_BSC_MEM_2_NAME_MIDDLE_COL, v_BSCMemName2_Mid);
        values.put(V_BSC_MEM_2_NAME_LAST_COL, v_BSCMemName2_Last);
        values.put(V_BSC_MEM_2_TITLE_COL, v_BSCMem2_TitlePos);
        values.put(PROXY_DESIGNATION_COL, proxy_Desig);
        values.put(PROXY_NAME_FIRST_COL, proxy_Name_First);
        values.put(PROXY_NAME_MIDDLE_COL, proxy_Name_Mid);
        values.put(PROXY_NAME_LAST_COL, proxy_Name_Last);
        values.put(PROXY_BIRTH_YEAR_COL, proxy_BirthYear);
        values.put(PROXY_PHOTO_COL, Proxy_Photo);
        values.put(PROXY_TYPE_ID_COL, proxy_Type_ID);
        values.put(PROXY_ID_NO_COL, proxy_ID_NO);
        values.put(PROXY_BSC_MEM_1_NAME_FIRST_COL, p_BSCMemName1_First);
        values.put(PROXY_BSC_MEM_1_NAME_MIDDLE_COL, p_BSCMemName1_Middle);
        values.put(PROXY_BSC_MEM_1_NAME_LAST_COL, p_BSCMemName1_Last);
        values.put(PROXY_BSC_MEM_1_TITLE_COL, p_BSCMem1_TitlePosition);
        values.put(PROXY_BSC_MEM_2_NAME_FIRST_COL, p_BSCMemName2_First);
        values.put(PROXY_BSC_MEM_2_NAME_MIDDLE_COL, p_BSCMemName2_Middle);
        values.put(PROXY_BSC_MEM_2_NAME_LAST_COL, p_BSCMemName2_Last);
        values.put(PROXY_BSC_MEM_2_TITLE_COL, p_BSCMem2_TitlePosition);
//        values.put(GROUP_CODE_COL, grpCode);


        long id = db.insert(REGISTRATION_MEMBER_TABLE, null, values);


        db.close();             // Closing database connection

        return id;
    }*/


    public long addMemberData(String c_code, String disCode, String upCode, String unCode, String vCode,
                              String str_hhID, String str_hhMemID, String str_MemName, String str_gender, String str_relation,
                              String str_entry_by, String str_entry_date, String lmp_date,
                              String child_dob, String elderly, String disabled,
                              String str_age, String regNDate, String birthYear,
                              String maritalStatus, String contactNo, String memOtherID, String memName_First,
                              String memName_Middle, String memName_Last,
                              String Photo,
                              String type_ID,
                              String id_NO, String v_BSCMemName1_First, String v_BSCMemName1_Mid, String v_BSCMemName1_Last,
                              String v_BSCMem1_TitlePosition, String v_BSCMemName2_First, String v_BSCMemName2_Mid,
                              String v_BSCMemName2_Last, String v_BSCMem2_TitlePos, String proxy_Desig, String proxy_Name_First,
                              String proxy_Name_Mid, String proxy_Name_Last, String proxy_BirthYear,
                              String Proxy_Photo,
                              String proxy_Type_ID, String proxy_ID_NO, String p_BSCMemName1_First, String p_BSCMemName1_Middle,
                              String p_BSCMemName1_Last, String p_BSCMem1_TitlePosition, String p_BSCMemName2_First, String p_BSCMemName2_Middle,
                              String p_BSCMemName2_Last, String p_BSCMem2_TitlePosition, String grpCode, String memTypeFlag) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ADM_COUNTRY_CODE_COL, c_code);                                                   // country name
        values.put(LAY_R1_LIST_CODE_COL, disCode);                                                           // district name
        values.put(LAY_R2_LIST_CODE_COL, upCode); // upazilla name
        values.put(LAY_R3_LIST_CODE_COL, unCode); // Unit name
        values.put(LAY_R4_LIST_CODE_COL, vCode); // Village name
        values.put(HHID_COL, str_hhID); // Registered ID
        values.put(HH_MEM_ID, str_hhMemID); // Member ID
        values.put(MEM_NAME_COL, str_MemName); // Member name
        values.put(SEX_COL, str_gender); // sex
        values.put(RELATION_COL, str_relation); // relation
        values.put(ENTRY_BY, str_entry_by); // entry by
        values.put(ENTRY_DATE, str_entry_date); // entry Date
        values.put(LMP_DATE, lmp_date);
        values.put(CHILD_DOB, child_dob);
        values.put(ELDERLY, elderly);
        values.put(DISABLE, disabled);
        values.put(MEM_AGE, str_age);           // member age for malawi
        values.put(REG_DATE_COL, regNDate);
        values.put(BIRTH_YEAR_COL, birthYear);
        values.put(MARITAL_STATUS_COL, maritalStatus);
        values.put(CONTACT_NO_COL, contactNo);
        values.put(MEMBER_OTHER_ID_COL, memOtherID);
        values.put(MEM_NAME_FIRST_COL, memName_First);
        values.put(MEM_NAME_MIDDLE_COL, memName_Middle);
        values.put(MEM_NAME_LAST_COL, memName_Last);
        values.put(PHOTO_COL, Photo);
        values.put(TYPE_ID_COL, type_ID);
        values.put(ID_NO_COL, id_NO);
        values.put(V_BSC_MEM_1_NAME_FIRST_COL, v_BSCMemName1_First);
        values.put(V_BSC_MEM_1_NAME_MIDDLE_COL, v_BSCMemName1_Mid);
        values.put(V_BSC_MEM_1_NAME_LAST_COL, v_BSCMemName1_Last);
        values.put(V_BSC_MEM_1_TITLE_COL, v_BSCMem1_TitlePosition);
        values.put(V_BSC_MEM_2_NAME_FIRST_COL, v_BSCMemName2_First);
        values.put(V_BSC_MEM_2_NAME_MIDDLE_COL, v_BSCMemName2_Mid);
        values.put(V_BSC_MEM_2_NAME_LAST_COL, v_BSCMemName2_Last);
        values.put(V_BSC_MEM_2_TITLE_COL, v_BSCMem2_TitlePos);
        values.put(PROXY_DESIGNATION_COL, proxy_Desig);
        values.put(PROXY_NAME_FIRST_COL, proxy_Name_First);
        values.put(PROXY_NAME_MIDDLE_COL, proxy_Name_Mid);
        values.put(PROXY_NAME_LAST_COL, proxy_Name_Last);
        values.put(PROXY_BIRTH_YEAR_COL, proxy_BirthYear);
        values.put(PROXY_PHOTO_COL, Proxy_Photo);
        values.put(PROXY_TYPE_ID_COL, proxy_Type_ID);
        values.put(PROXY_ID_NO_COL, proxy_ID_NO);
        values.put(PROXY_BSC_MEM_1_NAME_FIRST_COL, p_BSCMemName1_First);
        values.put(PROXY_BSC_MEM_1_NAME_MIDDLE_COL, p_BSCMemName1_Middle);
        values.put(PROXY_BSC_MEM_1_NAME_LAST_COL, p_BSCMemName1_Last);
        values.put(PROXY_BSC_MEM_1_TITLE_COL, p_BSCMem1_TitlePosition);
        values.put(PROXY_BSC_MEM_2_NAME_FIRST_COL, p_BSCMemName2_First);
        values.put(PROXY_BSC_MEM_2_NAME_MIDDLE_COL, p_BSCMemName2_Middle);
        values.put(PROXY_BSC_MEM_2_NAME_LAST_COL, p_BSCMemName2_Last);
        values.put(PROXY_BSC_MEM_2_TITLE_COL, p_BSCMem2_TitlePosition);
        values.put(MEM_TYPE_FLAG, memTypeFlag);
//        values.put(GROUP_CODE_COL, grpCode);


        long id = db.insert(REGISTRATION_MEMBER_TABLE, null, values);


        db.close();             // Closing database connection

        return id;
    }

    public void addLUP_TAParticipantCat(String cCode, String taGroup, String partCatCode, String partCatTitle) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(TA_GROUP_COL, taGroup);
        values.put(PART_CAT_CODE_COL, partCatCode);
        values.put(PART_CAT_TITLE_COL, partCatTitle);

        db.insert(LUP_TA_PATICIPANT_CAT_TABLE, null, values);
        db.close();
    }


    public String getHH_VerifiedStatus(String cCode, String dCode, String upCode, String unCode
            , String vCode, String personId) {

        SQLiteDatabase db = this.getReadableDatabase();
        String vStatus = "";// verifier status
        String selectQuery = " SELECT " + V_STATUS + " FROM " + REG_N_HH_TABLE + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + LAY_R1_LIST_CODE + " = '" + dCode + "' " +
                " AND " + LAY_R1_LIST_CODE + " = '" + dCode + "' " +
                " AND " + REGISTRATION_TABLE_UPZILLA_CODE_COL + " = '" + upCode + "' " +
                " AND " + REGISTRATION_TABLE_UNION_CODE_COL + " = '" + unCode + "' " +
                " AND " + REGISTRATION_TABLE_VILLAGE_CODE_COL + " = '" + vCode + "' " +
                " AND " + REGISTRATION_TABLE_HHID + " = '" + personId + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            vStatus = cursor.getString(cursor.getColumnIndex(V_STATUS));
        }
        cursor.close();
        db.close();
        return vStatus;


    }


    /**
     * This method will find out the value of MemTypeFlag, like as Get_MemAgeTypeFlag on the server end function
     * After completing registration.... need to update the value of the column named MemTypeFlag
     */

    public String getMemberAgeTypeFlag(String counryCode, String layR1Code, String layR2Code, String layR3Code,
                                       String layR4Code, String hhID, String hhMemID) {


        SQLiteDatabase db = this.getReadableDatabase();

        String memSex = "";
        int memAge = 0;
        String sql = "SELECT MemSex, MemAge from " + REGISTRATION_MEMBER_TABLE + " where " + ADM_COUNTRY_CODE_COL + " = '" + counryCode +
                "' and " + LAY_R1_LIST_CODE_COL + " = '" + layR1Code +
                "' and " + LAY_R2_LIST_CODE_COL + " = '" + layR2Code +
                "' and " + LAY_R3_LIST_CODE_COL + " = '" + layR3Code +
                "' and " + LAY_R4_LIST_CODE_COL + " = '" + layR4Code +
                "' and " + HHID_COL + " = '" + hhID +
                "' and " + HH_MEM_ID + " = '" + hhMemID + "'";


        Cursor cursor = db.rawQuery(sql, null);
        if ((cursor != null) && cursor.moveToFirst()) {
            memSex = cursor.getString(cursor.getColumnIndex("MemSex"));
            memAge = cursor.getInt(cursor.getColumnIndex("MemAge"));

            cursor.close();
        }
        db.close();


        if (memAge > 17 && memSex.equals("F")) {
            return "AF";
        } else if (memAge > 17 && memSex.equals("M")) {
            return "AM";
        } else if (memAge < 18) {
            return "CH";
        }

        return null;
    }


    public void updateMemTypeFlagInMemTable(String counryCode, String layR1Code, String layR2Code, String layR3Code,
                                            String layR4Code, String hhID, String hhMemID, String memAgeTypeFlag) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String where = ADM_COUNTRY_CODE_COL + " = '" + counryCode +
                "' and " + LAY_R1_LIST_CODE_COL + " = '" + layR1Code +
                "' and " + LAY_R2_LIST_CODE_COL + " = '" + layR2Code +
                "' and " + LAY_R3_LIST_CODE_COL + " = '" + layR3Code +
                "' and " + LAY_R4_LIST_CODE_COL + " = '" + layR4Code +
                "' and " + HHID_COL + " = '" + hhID +
                "' and " + HH_MEM_ID + " = '" + hhMemID + "'";

        values.put(MEM_TYPE_FLAG, memAgeTypeFlag);
        db.update(REGISTRATION_MEMBER_TABLE, values, where, null);

        db.close();                                                                                 // Closing database connection
    }

    public void updateRegistrationRecord(int pID, String dname, String upname, String uname,
                                         String vname, String addressCode, String pid, String r_date, String pname,
                                         String sex, String HHSize, String latitude, String longitude,
                                         String AGLand, String VStatus, String MStatus, String EntryBy,
                                         String EntryDate, String v_group, String wealthRank,
                                         String LTp2Hectres, String LT3mFoodStock, String NoMajorCommonLiveStock, String ReceiveNoFormalWages, String NoIGA, String RelyPiecework) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String where = ID_COL + "=" + pID;
        values.put(LAY_R1_LIST_CODE, dname); // district name
        values.put(REGISTRATION_TABLE_UPZILLA_CODE_COL, upname); // upazilla name
        values.put(REGISTRATION_TABLE_UNION_CODE_COL, uname); // Unit name
        values.put(REGISTRATION_TABLE_VILLAGE_CODE_COL, vname); // Unit name
        values.put(REGISTRATION_TABLE_REGN_ADDRESS_LOOKUP_CODE_COL, addressCode);
        values.put(REGISTRATION_TABLE_HHID, pid); // Personal name
        values.put(REG_DATE_COL, r_date); // Registration name
        values.put(REGISTRATION_TABLE_HH_HEAD_NAME, pname); // Person name
        values.put(REGISTRATION_TABLE_HH_HEAD_SEX, sex); // sex
        values.put(HH_SIZE, HHSize);
        values.put(REGISTRATION_TABLE_GPS_LAT, latitude); // Latitude
        values.put(REGISTRATION_TABLE_GPS_LONG, longitude); // Longitude
        values.put(AG_LAND, AGLand); // Longitude
        values.put(V_STATUS, VStatus); // Longitude
        values.put(M_STATUS, MStatus); // Longitude
        values.put(ENTRY_BY, EntryBy); // Longitude
        values.put(ENTRY_DATE, EntryDate); // Date of creation
        values.put(VSLA_GROUP, v_group);
        values.put(W_RANK_COL, wealthRank);// Date of creation
        values.put(LTP_2_HECTRES_COL, LTp2Hectres);
        values.put(LT_3_FOOD_STOCK_COL, LT3mFoodStock);
        values.put(NO_MAJOR_COMMON_LIVE_STOCK_COL, NoMajorCommonLiveStock);
        values.put(RECEIVE_NO_FORMAL_WAGES_COL, ReceiveNoFormalWages);
        values.put(NO_IGA_COL, NoIGA);
        values.put(RELY_PICE_EORK_COL, RelyPiecework);
// Inserting Row into local database
        db.update(REG_N_HH_TABLE, values, where, null);
//        updateRegistrationStatus("" + pID, 0);    // Setting Update status to false
        db.close(); // Closing database connection
//        Log.d(TAG, "Registration data edited for: " + pID);
    }


    public long addRegistrationForMalawi(String c_code, String dname, String upname, String uname, String vname, String addressName, String pid, String r_date, String pname, String sex, String HHSize, String latitude, String longitude, String AGLand, String VStatus, String MStatus, String EntryBy, String EntryDate, String v_group, String wRankCode, String LTp2Hectres, String LT3mFoodStock, String NoMajorCommonLiveStock, String ReceiveNoFormalWages, String NoIGA, String RelyPiecework) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, c_code); // country name
        values.put(LAY_R1_LIST_CODE, dname); // district name
        values.put(REGISTRATION_TABLE_UPZILLA_CODE_COL, upname); // upazilla name
        values.put(REGISTRATION_TABLE_UNION_CODE_COL, uname); // Unit name
        values.put(REGISTRATION_TABLE_VILLAGE_CODE_COL, vname); // Unit name
        values.put(REGISTRATION_TABLE_REGN_ADDRESS_LOOKUP_CODE_COL, addressName);
        values.put(REGISTRATION_TABLE_HHID, pid); // Personal code
        values.put(REG_DATE_COL, r_date); // Registration name
        values.put(REGISTRATION_TABLE_HH_HEAD_NAME, pname); // Person name
        values.put(REGISTRATION_TABLE_HH_HEAD_SEX, sex); // sex
        values.put(HH_SIZE, HHSize); // sex
        values.put(REGISTRATION_TABLE_GPS_LAT, latitude); // Latitude
        values.put(REGISTRATION_TABLE_GPS_LONG, longitude); // Longitude
        values.put(AG_LAND, AGLand); // Longitude
        values.put(V_STATUS, VStatus); // Longitude
        values.put(M_STATUS, MStatus); // Longitude
        values.put(ENTRY_BY, EntryBy); // Longitude
        values.put(ENTRY_DATE, EntryDate); // Date of creation
        values.put(VSLA_GROUP, v_group); // VSLA_GROUP
        values.put(W_RANK_COL, wRankCode); // WRANK
        values.put(LTP_2_HECTRES_COL, LTp2Hectres);
        values.put(LT_3_FOOD_STOCK_COL, LT3mFoodStock);
        values.put(NO_MAJOR_COMMON_LIVE_STOCK_COL, NoMajorCommonLiveStock);
        values.put(RECEIVE_NO_FORMAL_WAGES_COL, ReceiveNoFormalWages);
        values.put(NO_IGA_COL, NoIGA);
        values.put(RELY_PICE_EORK_COL, RelyPiecework);
// Inserting Row into local database
        long id = db.insert(REG_N_HH_TABLE, null, values);
        db.close(); // Closing database connection
//        Log.d(TAG, "New Registration data added into Registration Table: " + id);
        return id;
    }

    public boolean getHouseHoldRegistrationIsChecked(String columnName, String c_code, String dname, String upname, String uname, String vname, String pid) {
        boolean isChecked;
        String str = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + columnName + " FROM " + REG_N_HH_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + c_code + "'" // country name
                + " AND " + LAY_R1_LIST_CODE + " = '" + dname + "'" // district name
                + " AND " + REGISTRATION_TABLE_UPZILLA_CODE_COL + " = '" + upname + "'" // upazilla name
                + " AND " + REGISTRATION_TABLE_UNION_CODE_COL + " = '" + uname + "'" // Unit name
                + " AND " + REGISTRATION_TABLE_VILLAGE_CODE_COL + " = '" + vname + "'" // Unit name
                + " AND " + REGISTRATION_TABLE_HHID + " = '" + pid + "'"; // Personal code
        Cursor cursor = db.rawQuery(sql, null);


        if (cursor != null) {
            if (cursor.moveToFirst()) {
                str = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }

        if (str.equals("Y"))
            isChecked = true;
        else
            isChecked = false;


        return isChecked;


    }


    public String getSelectedCountryCode() {
        SQLiteDatabase db = this.getReadableDatabase();

        String cCode = "";
        String sql = SQLiteQuery.getSelectedCountryCodeFromSelectedVillage_sql();

        Cursor cursor = db.rawQuery(sql, null);
        if ((cursor != null) && cursor.moveToFirst()) {
            cCode = cursor.getString(0);

            cursor.close();
        }
        db.close();
        return cCode;
    }


    public String getSelectedCountryCode(String operationMode) {
        SQLiteDatabase db = this.getReadableDatabase();

        String cCode = "";
        String sql = "";
        switch (operationMode) {
            case UtilClass.REGISTRATION_OPERATION_MODE_NAME:
                sql = SQLiteQuery.getSelectedCountryCodeFromSelectedVillage_sql();
                break;

            case UtilClass.DISTRIBUTION_OPERATION_MODE_NAME:
                sql = SQLiteQuery.getSelectedCountryCodeFromSelectedFDP_sql();
                break;


            case UtilClass.SERVICE_OPERATION_MODE_NAME:
                sql = SQLiteQuery.getSelectedCountryCodeFromSelectedCenter_sql();
                break;
        }


        Cursor cursor = db.rawQuery(sql, null);
        if ((cursor != null) && cursor.moveToFirst()) {
            cCode = cursor.getString(0);

            cursor.close();
        }
        db.close();
        return cCode;
    }


    public ArrayList<VillageItem> getSelectedVillageList() {
        ArrayList<VillageItem> selectedVillage = new ArrayList<VillageItem>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = SQLiteQuery.getSelectedVillageList_sql();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                VillageItem vi = new VillageItem();
                vi.setLayRCode(cursor.getString(cursor.getColumnIndex(LAYER_CODE_COL)));
                vi.setLayR4ListName(cursor.getString(cursor.getColumnIndex(LAY_R4_LIST_NAME_COL)));
                // Log.d(TAG, " setLayRCode :" + vi.getLayRCode());
                selectedVillage.add(vi);
            } while (cursor.moveToNext());
            cursor.close();

        }
        db.close();
        return selectedVillage;
    }

    public ArrayList<FDPItem> getSelectedFDPList() {
        ArrayList<FDPItem> selectedFDP = new ArrayList<FDPItem>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + ADM_COUNTRY_CODE_COL + " , " + FDP_CODE_COL
                + "  , " + FDP_NAME_COL + " FROM " + SELECTED_FDP_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                FDPItem fdp = new FDPItem();
                fdp.setAdmCountryCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                fdp.setFDPCode(cursor.getString(cursor.getColumnIndex(FDP_CODE_COL)));
                fdp.setFDPName(cursor.getString(cursor.getColumnIndex(FDP_NAME_COL)));
//                Log.d(TAG, " setLayRCode :" + fdp.getFDPCode());
                selectedFDP.add(fdp);
            } while (cursor.moveToNext());
        }
        return selectedFDP;
    }


    public ArrayList<ServiceCenterItem> getSelectedServiceCenterList() {
        ArrayList<ServiceCenterItem> selectedSrvCenter = new ArrayList<ServiceCenterItem>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + ADM_COUNTRY_CODE_COL + " , " + SERVICE_CENTER_CODE_COL
                + "  , " + SERVICE_CENTER_NAME_COL + " FROM " + SELECTED_SERVICE_CENTER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ServiceCenterItem srvCenter = new ServiceCenterItem();
                srvCenter.setAdmCountryCode(cursor.getString(0));
                srvCenter.setServiceCenterCode(cursor.getString(1));
                srvCenter.setServiceCenterName(cursor.getString(2));
//                Log.d(TAG, " ServiceCenterCode :" + srvCenter.getServiceCenterCode());
                selectedSrvCenter.add(srvCenter);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }
        return selectedSrvCenter;
    }


    // Check Local Login
    public boolean isValidLocalLogin(final String user, final String pass) {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + LOGIN_TABLE
                + " WHERE " + USER_LOGIN_NAME + " = " + "'" + user + "' AND " + USER_LOGIN_PW + " = " + "'" + pass + "'";
        try {

            final Cursor cursor = db1.rawQuery(selectQuery, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    cursor.close();
                    return true;
                } else {
                    cursor.close();
                    return false;
                }

            }

        } catch (Exception e) {
//            Log.d(TAG, "isValidLocalLogin() Method: " + e.getMessage());

        } finally {
            // close database connection

            db1.close();
        }
        return false;
    }

    /**
     * this method check is the user is admin
     *
     * @param admin_user admin_user
     * @param admin_pass password
     * @return
     */

    public boolean isValidAdminLocalLogin(final String admin_user, final String admin_pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + STAFF_MASTER_TABLE
                + " WHERE " + USER_LOGIN_NAME + " = " + "'" + admin_user + "' "
                + " AND " + USER_LOGIN_PW + " = " + "'" + admin_pass + "' "
                + " AND " + STAFF_ADMIN_ROLE_COL + " IN ('A' ,'C') ";

        try {

            Cursor c = db.rawQuery(selectQuery, null);
            if (c != null) {
                if (c.getCount() > 0) {
                    return true;
                } else {
                    return false;
                }

            }
            c.close();
        } catch (Exception e) {
//            Log.d(TAG, "isValidLocalLogin() Method: " + e.getMessage());

        } finally {

            // close database connection
            db.close();
        }
        return false;
    }


    /**
     * Storing user details in database
     */
    public void addUser(String user_id, String country_code, String login_name, String login_pw, String first_name, String last_name, String email, String email_verification, String user_status, String entry_by, String entry_date) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, user_id);
        values.put(COUNTRY_CODE, country_code);
        values.put(USER_LOGIN_NAME, login_name);
        values.put(USER_LOGIN_PW, login_pw);
        values.put(USER_FIRST_NAME, first_name);
        values.put(USER_LAST_NAME, last_name);
        values.put(USER_EMAIL, email);
        values.put(USER_EMAIL_VERIFICATION, email_verification);
        values.put(USER_STATUS, user_status);
        values.put(ENTRY_BY, entry_by);
        values.put(ENTRY_DATE, entry_date);

        // Inserting Row
        long id = db.insert(LOGIN_TABLE, null, values);
        db.close(); // Closing database connection
        Log.d("MOR_12", "New user inserted into User Login: " + id);
    }

    /**
     * this method Insert the device moperation mood.
     *
     * @param opModeCode operation mode code of the device not business  logic
     * @param opModeName operation mode Name of the device
     * @param entryBy    entry by
     * @param entryDate  entry date
     */

    public void insertIntoDeviceOperationMode(int opModeCode, String opModeName, String entryBy, String entryDate) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SELECTED_OPERATION_MODE_CODE_COL, opModeCode);
        values.put(SELECTED_OPERATION_MODE_NAME_COL, opModeName);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        db.insert(SELECTED_OPERATION_MODE_TABLE, null, values);
        db.close();

    }


    /**
     * @return get device operation mode code registration  =1 / distributation=2 /service = 3/ other =4
     */
    public String getDeviceOperationModeName() {
        String deviceOperationModeName = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + SELECTED_OPERATION_MODE_NAME_COL + " FROM " + SELECTED_OPERATION_MODE_TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            deviceOperationModeName = cursor.getString(cursor.getColumnIndex(SELECTED_OPERATION_MODE_NAME_COL));
            cursor.close();
        }

        db.close();
        return deviceOperationModeName;
    }

    public int getDeviceOperationModeCode() {
        int deviceOperationModeCode = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + SELECTED_OPERATION_MODE_CODE_COL + " FROM " + SELECTED_OPERATION_MODE_TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            deviceOperationModeCode = cursor.getInt(cursor.getColumnIndex(SELECTED_OPERATION_MODE_CODE_COL));
            cursor.close();
        }

        db.close();
        return deviceOperationModeCode;
    }


    /**
     * @return get device operation mode code registration  =1 / distributation=2 /service = 3/ other =4
     */
  /*  public int getDeviceOperationModeName() {
        int deviceOperationMode = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + SELECTED_OPERATION_MODE_NAME_COL + " FROM " + SELECTED_OPERATION_MODE_TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            deviceOperationMode = cursor.getInt(cursor.getColumnIndex(SELECTED_OPERATION_MODE_CODE_COL));
            cursor.close();
        }

        db.close();
        return deviceOperationMode;
    }
*/

    /**
     * Getting Layer Label
     */
    public String getLayerLabel(String c_code, String l_code) {
        String layerName = "";
        String selectQuery = "SELECT  " + LAYER_NAME_COL + " FROM " + LAYER_LABEL_TABLE + " WHERE " + LAYER_LAVLE_COUNTRY_CODE + "='" + c_code + "' AND " + LAYER_CODE_COL + "='" + l_code + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            layerName = cursor.getString(0);
        }
        cursor.close();
        db.close();

        return layerName;
    }


    /**
     * Getting user data from database [for future use]
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();

        String selectQuery = "SELECT  * FROM " + LOGIN_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("code", cursor.getString(0));      // UsrID as StaffCode
            user.put("c_code", cursor.getString(1));    // CountryCode as Country Code
            user.put("username", cursor.getString(2));    // userid
            user.put("password", cursor.getString(3));    // password

            user.put("name", cursor.getString(4));      // UsrFirstName as name
            user.put("status", cursor.getString(8));    // UsrStatus as user status
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

/******************** check iop month for service
 *
 */
    /**
     * Getting date data  from om month for Service  [for future use]
     */
    public HashMap<String, String> getDateRangeForService(String countryCode, String srvOpMonthCode) {
        HashMap<String, String> dateRangeS = new HashMap<String, String>();


        String selectQuery = SQLiteQuery.getServiceDateRange_selectQuery(countryCode, srvOpMonthCode);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            dateRangeS.put("opCode", cursor.getString(0));
            dateRangeS.put("opMCode", cursor.getString(1));
            dateRangeS.put("sdate", cursor.getString(2));
            dateRangeS.put("edate", cursor.getString(3));
            dateRangeS.put("opMonthLable", cursor.getString(4));
        } else {
            dateRangeS.put("opCode", null);
            dateRangeS.put("opMCode", null);
            dateRangeS.put("sdate", null);
            dateRangeS.put("edate", null);
            dateRangeS.put("opMonthLable", null);
        }
        cursor.close();
        db.close();

        return dateRangeS;
    }


    /**
     * Getting Operation Startig date & End date data from database
     * [ For Graduation date]
     * Faisal Mohammad
     *
     * @since : 2015-10-01
     */
    public HashMap<String, String> getGRDDateRange(String cCode) {
        HashMap<String, String> dateRange = new HashMap<String, String>();

        String selectQuery = "SELECT  " + START_DATE_COL + " , " + END_DATE_COL + " FROM " + OP_MONTH_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' AND " +
                OPERATION_CODE_COL + " = '1' AND " + STATUS + " = 'A'";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            dateRange.put("c_code", cursor.getString(1));    // CountryCode as Country Code
            dateRange.put("sdate", cursor.getString(2));      // Start Date
            dateRange.put("edate", cursor.getString(3));    // End Date
        } else {
            dateRange.put("c_code", null);                  // CountryCode as Country Code
            dateRange.put("sdate", null);                   // Start Date
            dateRange.put("edate", null);                   // End Date
        }
        cursor.close();
        db.close();

        return dateRange;
    }

    /* ******************************/
    public String getGraduatedDate(String countryCode, String donorCode, String awardCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + AWARD_END_DATE_COL + " FROM "
                + ADM_COUNTRY_AWARD_TABLE +
                " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "'"
                + " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' ";
        String grdDate = "";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                grdDate = cursor.getString(cursor.getColumnIndex(AWARD_END_DATE_COL));
            }
            cursor.close();
        }
        db.close();
        return grdDate;
    }


    public HashMap<String, String> getRegistrationDateRange(String cCode) {
        HashMap<String, String> dateRange = new HashMap<>();
        String selectQuery = SQLiteQuery.get_RegNAssProgSrvRegistrationDateRangeSelectQuery(cCode);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            if (cursor.getCount() > 0) {
                dateRange.put("sdate", cursor.getString(cursor.getColumnIndex(USA_START_DATE_COL)));
                dateRange.put("edate", cursor.getString(cursor.getColumnIndex(USA_END_DATE_COL)));
            } else {
                dateRange.put("sdate", null);
                dateRange.put("edate", null);
            }
            cursor.close();
        }

        db.close();
        return dateRange;


    }

    /**
     * This method invoking Form
     *
     * @param cCode   Country Code
     * @param opMonth Op Month Code
     * @return A  Hash Map of startDate & end Date
     * @see {@link }
     * This method  return Date the Range of Dt
     */

    public HashMap<String, String> getDynamicTableDateRange(String cCode, String opMonth) {
        HashMap<String, String> dateRange = new HashMap<String, String>();


        String sql = "SELECT  "
                + "  " + START_DATE_COL
                + " , " + END_DATE_COL
                + " FROM " + OP_MONTH_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + STATUS + "= 'A'"
                + " AND " + OPERATION_CODE_COL + " = '5'"
                + " AND " + OP_MONTH_CODE_COL + " = '" + opMonth + "'";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            dateRange.put("sdate", cursor.getString(0));      // Start Date
            dateRange.put("edate", cursor.getString(1));    // End Date

            cursor.close();
            db.close();
        } else {

            dateRange.put("sdate", null);                   // Start Date
            dateRange.put("edate", null);                   // End Date
        }


        return dateRange;
    }

    /**
     * Getting user data from database [for future use]
     * review the method
     */
    public HashMap<String, String> getDateRange(String cCode) {
        HashMap<String, String> dateRange = new HashMap<String, String>();


        String sql = "SELECT  " + ADM_COUNTRY_CODE_COL
                + " , " + START_DATE_COL +
                " , " + END_DATE_COL
                + " FROM " + OP_MONTH_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + STATUS + "= 'A'"
                + " AND " + OPERATION_CODE_COL + " = '1' " +
                "  ORDER BY " + OP_MONTH_CODE_COL + "   DESC   LIMIT 1 ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            dateRange.put("c_code", cursor.getString(0));    // CountryCode as Country Code
            dateRange.put("sdate", cursor.getString(1));      // Start Date
            dateRange.put("edate", cursor.getString(2));    // End Date
        } else {
            dateRange.put("c_code", null);                  // CountryCode as Country Code
            dateRange.put("sdate", null);                   // Start Date
            dateRange.put("edate", null);                   // End Date
        }
        cursor.close();
        db.close();

        return dateRange;
    }


    public String getRelationName(String code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor qCursor = null;
        String result = null;

        String sql = "SELECT " + RELATION_NAME + " FROM " + RELATION_TABLE + " WHERE " + RELATION_CODE + "='" + code + "'";
        qCursor = db.rawQuery(sql, null);


        if (qCursor != null) {
            if (qCursor.moveToFirst()) {
                do {
                    result = qCursor.getString(qCursor.getColumnIndex(RELATION_NAME));
                } while (qCursor.moveToNext());
            }
        }
        db.close();

        return result;
    }


    public String getDataByCode(String col, String table, String condition) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor qCursor = null;
        ArrayList<String> results = null;
        String result = null;

        // TODO :: Need to modify where col=null means for all data of a row
        // to do so we need to modify the results.add function in 'do' loop

        if (col != null) {
            String sql = "SELECT " + col + " FROM " + table + " WHERE " + condition;
            qCursor = db.rawQuery(sql, null);
        } else {
            String sql = "SELECT * FROM " + table + " WHERE " + condition;
            qCursor = db.rawQuery(sql, null);
        }


        if (qCursor != null) {
            if (qCursor.moveToFirst()) {
                do {
                    result = qCursor.getString(qCursor.getColumnIndex(col));
                } while (qCursor.moveToNext());
            }
        }
        db.close();

        return result;
    }


    /**
     * 2015-10-05
     * delete the recorde from service
     */
    public int deleteService(String countryId, String donorId, String awardId, String distId, String upId, String unId, String villId, String hhId, String memId, String progId, String srvId, String opCodeId, String opMCodeId, String srvSerialNo) {


        SQLiteDatabase db = this.getWritableDatabase();
        String where = SQLiteQuery.getServiceDelete_WhereCondition(countryId, donorId, awardId, distId, upId, unId, villId, hhId, memId, progId, srvId, opMCodeId, srvSerialNo);

        int id = db.delete(SERVICE_TABLE, where, null);
        db.close();
        return id;

    }

    public int deleteDistribution(DistributionSaveDataModel distData) {


        SQLiteDatabase db = this.getWritableDatabase();
        String where = ADM_COUNTRY_CODE_COL + " = '" + distData.getCountryCode() + "'" +
                " AND " + ADM_DONOR_CODE_COL + " = '" + distData.getAdmDonorCode() + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + distData.getAdmAwardCode() + "' " +
                " AND " + LAY_R_LIST_CODE_COL + " = '" + distData.getDistrictCode() + "' " +
                " AND " + LAY_R2_LIST_CODE_COL + " = '" + distData.getUpCode() + "' " +
                " AND " + LAY_R3_LIST_CODE_COL + " = '" + distData.getUniteCode() + "' " +
                " AND " + LAY_R4_LIST_CODE_COL + " = '" + distData.getVillageCode() + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + distData.getProgCode() + "' " +
                " AND " + ADM_SRV_CODE_COL + " = '" + distData.getSrvCode() + "' " +
                " AND " + OP_MONTH_CODE_COL + " = '" + distData.getOpMonthCode() + "' " +

                " AND " + MEM_ID_15_D_COL + " = '" + distData.getID() + "' ";

        int id = db.delete(DISTRIBUTION_TABLE, where, null);

//        Log.d(TAG, "DELETE Distribution data  id: " + distData.getID());
        db.close();
        return id;

    }


    public void deleteMember(int mID) {

        SQLiteDatabase db = this.getReadableDatabase();//
        String where = ID_COL + "=" + mID;
        db.delete(REGISTRATION_MEMBER_TABLE, where, null);
        db.close();
    }


    /**
     * To delete house  hold in live server send the
     */
    public SQLServerSyntaxGenerator getDeletedMemberID(int mID) { // mID= member Id
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.getDeletedMemberIDQuery(mID);
        Cursor cursor = db.rawQuery(selectQuery, null);
        SQLServerSyntaxGenerator deletedMemberId = new SQLServerSyntaxGenerator();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                deletedMemberId.setAdmCountryCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                deletedMemberId.setLayR1ListCode(cursor.getString(cursor.getColumnIndex(DISTRICT_NAME_COL)));
                deletedMemberId.setLayR2ListCode(cursor.getString(cursor.getColumnIndex(UPZILLA_NAME_COL)));
                deletedMemberId.setLayR3ListCode(cursor.getString(cursor.getColumnIndex(LAY_R3_LIST_NAME)));
                deletedMemberId.setLayR4ListCode(cursor.getString(cursor.getColumnIndex(LAY_R4_LIST_NAME_COL)));
                deletedMemberId.setHHID(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                deletedMemberId.setMemID(cursor.getString(cursor.getColumnIndex(HH_MEM_ID)));
            }
            cursor.close();
        }
        db.close();
        return deletedMemberId;

    }

    /**
     * To delete house  hold in RegNHHTable live server db need to get HHID with layRList Codes
     */
    public SQLServerSyntaxGenerator getDeletedHouseHoldID(String pID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.getDeletedHouseHoldIDQuery(pID);
        Cursor cursor = db.rawQuery(selectQuery, null);
        SQLServerSyntaxGenerator deletedHHid = new SQLServerSyntaxGenerator();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                deletedHHid.setAdmCountryCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                deletedHHid.setLayR1ListCode(cursor.getString(cursor.getColumnIndex(DISTRICT_NAME_COL)));
                deletedHHid.setLayR2ListCode(cursor.getString(cursor.getColumnIndex(UPZILLA_NAME_COL)));
                deletedHHid.setLayR3ListCode(cursor.getString(cursor.getColumnIndex(LAY_R3_LIST_NAME)));
                deletedHHid.setLayR4ListCode(cursor.getString(cursor.getColumnIndex(LAY_R4_LIST_NAME_COL)));
                deletedHHid.setHHID(cursor.getString(cursor.getColumnIndex(PID_COL)));
            }
            cursor.close();
        }
        db.close();
        return deletedHHid;

    }

    public void deleteHouseHold(String pID) {

        SQLiteDatabase db = this.getReadableDatabase();

        // Delete from Registration table
        String where = PID_COL + "='" + pID + "'";
        db.delete(REG_N_HH_TABLE, where, null);

        // deleting from Members table
        String mWhere = HHID_COL + "='" + pID + "'";
        db.delete(REGISTRATION_MEMBER_TABLE, mWhere, null);
        db.close();
    }


    public String selectCountryCode() {

        String countryCode = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " Select distinct " + SELECTED_VILLAGE_TABLE + "." +
                ADM_COUNTRY_CODE_COL + " from " + SELECTED_VILLAGE_TABLE +
                " Inner join " + COUNTRY_TABLE +
                " on " + SELECTED_VILLAGE_TABLE + "." + ADM_COUNTRY_CODE_COL + " = " + COUNTRY_TABLE + "." + COUNTRY_COUNTRY_CODE +
                " order by " + SELECTED_VILLAGE_TABLE + "." + ADM_COUNTRY_CODE_COL;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                countryCode = cursor.getString(0);
            }
            cursor.close();
        }

        db.close();
        return countryCode;
    }

    public int selectUploadSyntextRowCount() {
        Cursor cursor;
        String count = "0";

        SQLiteDatabase db = this.getReadableDatabase();
        String query = " SELECT COUNT( " + UPLOAD_SYNTAX_TABLE + "." + SYNC_COL + ")" + " FROM " + UPLOAD_SYNTAX_TABLE + " WHERE " + UPLOAD_SYNTAX_TABLE + "." + SYNC_COL + " = " + 0;
        cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                count = cursor.getString(0);
            }
            cursor.close();
        }

        db.close();
        return Integer.valueOf(count);
    }

    public boolean checkDataAvailableOrNotInGpsLocationContentTable(String AdmCountryCode, String GrpCode, String SubGrpCode, String LocationCode, String ContentCode) {
        Cursor cursor;
        String count = "0";
        SQLiteDatabase db = this.getReadableDatabase();

        String query = " SELECT * FROM " + GPS_LOCATION_CONTENT_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + AdmCountryCode + "'"
                + " AND " + GROUP_CODE_COL + " = '" + GrpCode + "'"
                + " AND " + SUB_GROUP_CODE_COL + " = '" + SubGrpCode + "'"
                + " AND " + LOCATION_CODE_COL + " = '" + LocationCode + "'"
                + " AND " + CONTENT_CODE_COL + " = '" + ContentCode + "'";
//        Log.d(TAG, query);
        cursor = db.rawQuery(query, null);

//        Log.d(TAG, " " + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }

    }

    public void deleteRowFromGpsLocationContentTable(String AdmCountryCode, String GrpCode, String SubGrpCode, String LocationCode, String ContentCode) {
        SQLiteDatabase db = this.getWritableDatabase();

        String where = ADM_COUNTRY_CODE_COL + " = '" + AdmCountryCode + "'"
                + " AND " + GROUP_CODE_COL + " = '" + GrpCode + "'"
                + " AND " + SUB_GROUP_CODE_COL + " = '" + SubGrpCode + "'"
                + " AND " + LOCATION_CODE_COL + " = '" + LocationCode + "'"
                + " AND " + CONTENT_CODE_COL + " = '" + ContentCode + "'";

        int deletedRowNo = db.delete(GPS_LOCATION_CONTENT_TABLE, where, null);
        db.close();
//        Log.d(TAG, "" + deletedRowNo);
    }

    public void getImageFromDatabase(String AdmCountryCode, String GrpCode, String SubGrpCode, String LocationCode, String ContentCode, ImageView imageView) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + IMAGE_FILE_COL + " FROM "
                + GPS_LOCATION_CONTENT_TABLE + " WHERE "
                + ADM_COUNTRY_CODE_COL + " = '" + AdmCountryCode + "'"
                + " AND " + GROUP_CODE_COL + " = '" + GrpCode + "'"
                + " AND " + SUB_GROUP_CODE_COL + " = '" + SubGrpCode + "'"
                + " AND " + LOCATION_CODE_COL + " = '" + LocationCode + "'"
                + " AND " + CONTENT_CODE_COL + " = '" + ContentCode + "'";

        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            byte[] image = cursor.getBlob(cursor.getColumnIndex(IMAGE_FILE_COL));
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            imageView.setImageBitmap(bitmap);
        }
        cursor.close();

    }

    //Login page Query, Added By REFAT
    public void deleteFromSelectedFDP() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SELECTED_FDP_TABLE, null, null);
    }

    public void deleteFromSelectedVillage() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SELECTED_VILLAGE_TABLE, null, null);

    }

    public void deleteFromSelectedServiceCenter() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SELECTED_SERVICE_CENTER_TABLE, null, null);
    }

    public List<String> selectGeoDataFDP() {
        List<String> list;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        list = new ArrayList<String>();

        String query = "SELECT " + FDP_NAME_COL + " FROM " + SELECTED_FDP_TABLE;
        cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String fdpName = cursor.getString(cursor.getColumnIndex(FDP_NAME_COL));
                list.add(fdpName);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return list;
    }


    public List<String> selectGeoDataCenter() {
        List<String> list;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        list = new ArrayList<String>();

        String query = "SELECT " + SERVICE_CENTER_NAME_COL + " FROM " + SELECTED_SERVICE_CENTER_TABLE;
        cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String centerName = cursor.getString(cursor.getColumnIndex(SERVICE_CENTER_NAME_COL));
                list.add(centerName);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return list;
    }


    public List<String> selectGeoDataVillage() {
        List<String> list;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        list = new ArrayList<String>();

        //  String query = "SELECT " + LAY_R4_LIST_NAME_COL + " FROM " + SELECTED_VILLAGE_TABLE;
        String query = "SELECT VillageName " + " FROM " + SELECTED_VILLAGE_TABLE;
        cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String villageName = cursor.getString(0);
                list.add(villageName);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return list;
    }

    public long insertIntoLastSyncTraceStatus(String userId, String userName, String lastSyncTime) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(USER_ID, userId);
        values.put(USER_LOGIN_NAME, userName);
        values.put(LAST_SYNC_TIME_COL, lastSyncTime);

        return db.insert(LAST_SYNC_TYRACE_TABLE, null, values);


    }

    public String lastSyncStatus() {
        String query = "SELECT " + LAST_SYNC_TIME_COL + " FROM " + LAST_SYNC_TYRACE_TABLE + " ORDER BY " + ID_COL + " DESC LIMIT " + 1;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String date = "";
        if (cursor.moveToFirst()) {
            date = cursor.getString(cursor.getColumnIndex(LAST_SYNC_TIME_COL));
        }
        cursor.close();
        db.close();
        return date;
    }

    public void addIntoDTATable(String dtBasic, String dtqCode, String dtaCode, String dtaLabel, String dtaValue,
                                long dtSeq, String dtaShort, String dtScoreCode, String dtSkipDTQCode, String dtaCompareCode, String showHide,
                                String maxValue, String minValue, String dataType, String markOnGrid, String entryBy, String entryDate) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DT_BASIC_COL, dtBasic);
        values.put(DTQ_CODE_COL, dtqCode);
        values.put(DTA_CODE_COL, dtaCode);
        values.put(DTA_LABEL_COL, dtaLabel);
        values.put(DTA_VALUE_COL, dtaValue);
        values.put(DT_SEQ_COL, dtSeq);
        values.put(DTA_SHORT_COL, dtaShort);
        values.put(DT_SCORE_CODE_COL, dtScoreCode);
        values.put(DTSKIP_DTQ_CODE_COL, dtSkipDTQCode);
        values.put(DTA_COMPARE_CODE_COL, dtaCompareCode);
        values.put(SHOW_HIDE_COL, showHide);
        values.put(MAX_VALUE_COL, maxValue);
        values.put(MIN_VALUE_COL, minValue);
        values.put(DATA_TYPE_COL, dataType);
        values.put(MARK_ON_GRID_COL, markOnGrid);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        db.insert(DT_A_TABLE, null, values);
        db.close();
    }

    /**
     * reference table
     */
    public void addIntoDTBasic(String dtBasic, String dtTitle, String dtSubTitle, String dtDescription, String dtAutoScroll, String dtAutoScrollText, String dtActive, String dtCategory, String dtGeoListLevel, String dtOpMode, String dtShortName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_BASIC_COL, dtBasic);
        values.put(DT_TITLE_COL, dtTitle);
        values.put(DT_SUB_TITLE_COL, dtSubTitle);
        values.put(DT_DESCRIPTION_COL, dtDescription);
        values.put(DT_AUTO_SCROLL_COL, dtAutoScroll);
        values.put(DTAUTO_SCROLL_TEXT, dtAutoScrollText);
        values.put(DT_ACTIVE_COL, dtActive);
        values.put(DT_CATEGORY_COL, dtCategory);
        values.put(DT_GEO_LIST_LEVEL_COL, dtGeoListLevel);
        values.put(DT_OP_MODE_COL, dtOpMode);
        values.put(DT_SHORT_NAME_COL, dtShortName);
        //    values.put(ENTRY_DATE, entryDate);

        db.insert(DT_BASIC_TABLE, null, values);
        db.close();
    }

    public void addIntoDTCategory(String dtCategory, String categoryName, String frequency, String entryBy,
                                  String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_CATEGORY_COL, dtCategory);
        values.put(DT_CATEGORY_TABLE_CATEGORY_NAME_COL, categoryName);
        values.put(FREQUENCY_COL, frequency);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        db.insert(DT_CATEGORY_TABLE, null, values);
        db.close();
    }

    public void addIntoDTCountryProgram(String countryCode, String donorCode, String awardCode, String programCode,
                                        String progActivityCode, String progActivityTitle, String dtBasic, String refIdentifier,
                                        String status, String rftFrequency, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ADM_COUNTRY_CODE_COL, countryCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, programCode);
        values.put(PROG_ACTIVITY_CODE_COL, progActivityCode);
        values.put(PROG_ACTIVITY_TITLE_COL, progActivityTitle);
        values.put(DT_BASIC_COL, dtBasic);
        values.put(REF_IDENTIFIER_COL, refIdentifier);
        values.put(STATUS, status);
        values.put(RPT_FREQUENCY_COL, rftFrequency);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        db.insert(DT_COUNTRY_PROGRAM_TABLE, null, values);
        db.close();
    }

    public void addIntoDTGeoListLevel(String geoLevel, String geoLevelName, String listUDFName, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(GEO_LEVEL_COL, geoLevel);
        values.put(GEO_LEVEL_NAME_COL, geoLevelName);
        values.put(LIST_UDF_NAME_COL, listUDFName);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        db.insert(DTGEO_LIST_LEVEL_TABLE, null, values);
        db.close();
    }

    public void addIntoDTLUP(String cCode, String tableName, String listCode, String listName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(TABLE_NAME_COL, tableName);
        values.put(LIST_CODE_COL, listCode);
        values.put(LIST_NAME_COL, listName);


        db.insert(DT_LUP_TABLE, null, values);
        db.close();
    }

    public void addIntoDTASkipTable(String dtBasicCode, String dtQCode, String skipCode, String dtaCodeComB, String dtSkipDTQCode) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_BASIC_COL, dtBasicCode);
        values.put(DTQ_CODE_COL, dtQCode);
        values.put(SKIP_CODE_COL, skipCode);
        values.put(DTA_CODE_COMB_N_COL, dtaCodeComB);
        values.put(DTSKIP_DTQ_CODE_COL, dtSkipDTQCode);


        db.insert(DTA_SKIP_TABLE, null, values);
        db.close();
    }

    public void addIntoDTQResMode(String qResMode, String qResLupText, String dataType, String lookUpUDFName, String responseValueControl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(QRES_MODE_COL, qResMode);
        values.put(QRES_LUP_TEXT_COL, qResLupText);
        values.put(DATA_TYPE_COL, dataType);
        values.put(LOOK_UP_UDF_NAME_COL, lookUpUDFName);
        values.put(RESPONSE_VALUE_CONTROL_COL, responseValueControl);

        db.insert(DTQRES_MODE_TABLE, null, values);
        db.close();
    }

    public void addIntoDTQTable(String dtBasic, String dtqCode, String qText, String qResMode, String qSeq, String allowNull, String lub_tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_BASIC_COL, dtBasic);
        values.put(DTQ_CODE_COL, dtqCode);
        values.put(QTEXT_COL, qText);
        values.put(QRES_MODE_COL, qResMode);
        values.put(QSEQ_SCOL, qSeq);
        values.put(ALLOW_NULL_COL, allowNull);
        values.put(LUP_TABLE_NAME, lub_tableName);

        long id = db.insert(DTQ_TABLE, null, values);
        db.close();
    }

    /**
     * This method insert the data into {@link #DT_RESPONSE_TABLE } .
     * if the data is unsync Data then it invoke the {@link #insertIntoUploadTable(String)}
     * the query generate from {@link SQLServerSyntaxGenerator#insertIntoDTResponseTable()} }
     *
     * @param dtBasic          dt Basic Code
     * @param countryCode      countryCode
     * @param donorCode        donor Code
     * @param awardCode        award Code
     * @param programCode      program Code
     * @param dtEnuId          dt EnuId
     * @param dtqCode          dt Question  Code
     * @param dtaCode          dt Answer  Code
     * @param dtrSeq           dtr Sequence
     * @param dtaValue         dta Value
     * @param progActivityCode program Activity Code
     * @param dttTimeString    dtt Time String
     * @param opMode           op Mode
     * @param opMonthCode      op Month Code
     * @param dataType         data Type
     * @param imageString      image base64 String fromat
     * @param unSync           unSync
     */
    public long addIntoDTResponseTable(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                       String dtEnuId, String dtqCode, String dtaCode, String dtrSeq, String dtaValue,
                                       String progActivityCode, String dttTimeString, String opMode, String opMonthCode, String dataType, String imageString, boolean unSync) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_BASIC_COL, dtBasic);
        values.put(ADM_COUNTRY_CODE_COL, countryCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, programCode);
        values.put(DT_ENU_ID_COL, dtEnuId);
        values.put(DTQ_CODE_COL, dtqCode);
        values.put(DTA_CODE_COL, dtaCode);
        values.put(DT_R_SEQ_COL, dtrSeq);
        values.put(DTA_VALUE_COL, dtaValue);
        values.put(PROG_ACTIVITY_CODE_COL, progActivityCode);
        values.put(DTTIME_STRING_COL, dttTimeString);
        values.put(OP_MODE_COL, opMode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(DATA_TYPE_COL, dataType);
        values.put(U_FILE_COL, imageString);

        long row = db.insert(DT_RESPONSE_TABLE, null, values);
        db.close();
        // upload syntax section if the data is unsync
        if (unSync) {
            SQLServerSyntaxGenerator mSyntaxGenerator = new SQLServerSyntaxGenerator();
            mSyntaxGenerator.setDTBasic(dtBasic);
            mSyntaxGenerator.setAdmCountryCode(countryCode);
            mSyntaxGenerator.setAdmDonorCode(donorCode);
            mSyntaxGenerator.setAdmAwardCode(awardCode);
            mSyntaxGenerator.setAdmProgCode(programCode);
            mSyntaxGenerator.setDTEnuID(dtEnuId);
            mSyntaxGenerator.setDTQCode(dtqCode);
            mSyntaxGenerator.setDTACode(dtaCode);
            mSyntaxGenerator.setDTRSeq(String.valueOf(dtrSeq));
            mSyntaxGenerator.setDTAValue(dtaValue);
            mSyntaxGenerator.setProgActivityCode(progActivityCode);
            mSyntaxGenerator.setDTTimeString(dttTimeString);
            mSyntaxGenerator.setOpMode(opMode);
            mSyntaxGenerator.setOpMonthCode(opMonthCode);
            mSyntaxGenerator.setDataType(dataType);
            mSyntaxGenerator.setCompleteness("Y");
            mSyntaxGenerator.setUFILE(imageString);
            insertIntoUploadTable(mSyntaxGenerator.insertIntoDTResponseTable());

//            Log.i(TAG, mSyntaxGenerator.insertIntoDTResponseTable());
        }
        return row;
    }

    public void updateIntoDTResponseTable(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                          String dtEnuId, String dtqCode, String dtaCode, String dtrSeq, String dtaValue,
                                          String progActivityCode, String dttTimeString, String opMode, String opMonthCode, String dataType, String imageString) {

        SQLiteDatabase db = this.getWritableDatabase();

        String where = DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + programCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + dtEnuId + "' " +
                " AND " + DTQ_CODE_COL + " = '" + dtqCode + "' " +
                " AND " + DTA_CODE_COL + " = '" + dtaCode + "' " +
                " AND " + DT_R_SEQ_COL + " = " + dtrSeq;

        ContentValues values = new ContentValues();

        values.put(DTA_VALUE_COL, dtaValue);
        values.put(PROG_ACTIVITY_CODE_COL, progActivityCode);
        values.put(DTTIME_STRING_COL, dttTimeString);
        values.put(OP_MODE_COL, opMode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(DATA_TYPE_COL, dataType);
        values.put(U_FILE_COL, imageString);


        int id = db.update(DT_RESPONSE_TABLE, values, where, null);

        db.close();     // close the db


        SQLServerSyntaxGenerator mSyntaxGenerator = new SQLServerSyntaxGenerator();
        mSyntaxGenerator.setDTBasic(dtBasic);
        mSyntaxGenerator.setAdmCountryCode(countryCode);
        mSyntaxGenerator.setAdmDonorCode(donorCode);
        mSyntaxGenerator.setAdmAwardCode(awardCode);
        mSyntaxGenerator.setAdmProgCode(programCode);
        mSyntaxGenerator.setDTEnuID(dtEnuId);
        mSyntaxGenerator.setDTQCode(dtqCode);
        mSyntaxGenerator.setDTACode(dtaCode);
        mSyntaxGenerator.setDTRSeq(String.valueOf(dtrSeq));
        mSyntaxGenerator.setDTAValue(dtaValue);
        mSyntaxGenerator.setProgActivityCode(progActivityCode);
        mSyntaxGenerator.setDTTimeString(dttTimeString);
        mSyntaxGenerator.setOpMode(opMode);
        mSyntaxGenerator.setOpMonthCode(opMonthCode);
        mSyntaxGenerator.setDataType(dataType);
        mSyntaxGenerator.setCompleteness("Y");
        mSyntaxGenerator.setUFILE(imageString);
        insertIntoUploadTable(mSyntaxGenerator.updateIntoDTResponseTable());

        Log.d(TAG, " no of row :" + id);

    }


    public void addIntoDTSurveyTable(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                     String dtEnuId, String dtqCode, String dtaCode, String dtrSeq, String dtaValue,
                                     String progActivityCode, String dttTimeString, String opMode,
                                     String opMonthCode, String dataType, String dtqText,
                                     int surveyNumber, String image
            , String resposeController, String qResLupText, String dtaLabel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_BASIC_COL, dtBasic);
        values.put(COUNTRY_CODE_COL, countryCode);
        values.put(DONOR_CODE_COL, donorCode);

        values.put(AWARD_CODE_COL, awardCode);
        values.put(PROGRAM_CODE_COL, programCode);
        values.put(DT_ENU_ID_COL, dtEnuId);
        values.put(DTQ_CODE_COL, dtqCode);
        values.put(DTA_CODE_COL, dtaCode);
        values.put(DT_R_SEQ_COL, dtrSeq);
        values.put(DTA_VALUE_COL, dtaValue);
        values.put(PROG_ACTIVITY_CODE_COL, progActivityCode);
        values.put(DTTIME_STRING_COL, dttTimeString);
        values.put(OP_MODE_COL, opMode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(DTQ_TEXT_COL, dtqText);
        values.put(DT_SURVEY_NUM, surveyNumber);
        values.put(DATA_TYPE_COL, dataType);
        values.put(U_FILE_COL, image);
        values.put(RESPONSE_VALUE_CONTROL_COL, resposeController);
        values.put(QRES_LUP_TEXT_COL, qResLupText);
        values.put(DTA_LABEL_COL, dtaLabel);


        db.insert(DT_SURVEY_TABLE, null, values);
        db.close();
//        Log.d("DT_survey", " row: " + row);
    }

    public void updateIntoDTSurveyTable(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                        String dtEnuId, String dtqCode, String dtaCode, String dtrSeq, String dtaValue,
                                        String progActivityCode, String dttTimeString, String opMode, String opMonthCode, String dataType, String dtqText, int surveyNumber) {

        SQLiteDatabase db = this.getWritableDatabase();

        String where = DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND " + COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND " + DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + PROGRAM_CODE_COL + " = '" + programCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + dtEnuId + "' " +
                " AND " + DTQ_CODE_COL + " = '" + dtqCode + "' " +
                " AND " + DTA_CODE_COL + " = '" + dtaCode + "' " +
                " AND " + DT_R_SEQ_COL + " = " + dtrSeq +
                " AND " + DT_SURVEY_NUM + " = " + surveyNumber;

        ContentValues values = new ContentValues();

        values.put(DTA_VALUE_COL, dtaValue);
        values.put(PROG_ACTIVITY_CODE_COL, progActivityCode);
        values.put(DTTIME_STRING_COL, dttTimeString);
        values.put(OP_MODE_COL, opMode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(DTQ_TEXT_COL, dtqText);
        values.put(DATA_TYPE_COL, dataType);


        int id = db.update(DT_SURVEY_TABLE, values, where, null);

        db.close();
    }

    /**
     * @since data craft
     */

    public void deleteFromDTSurveyTable(String DTBasic, String AdmCountryCode, String AdmDonorCode, String AdmAwardCode, String AdmProgCode, String DTEnuID, int DTRSeq, String OpMode, String OpMonthCode, int surveyNum) {
        SQLServerSyntaxGenerator syntaxGenerator = new SQLServerSyntaxGenerator();

        syntaxGenerator.setDTBasic(DTBasic);
        syntaxGenerator.setAdmCountryCode(AdmCountryCode);
        syntaxGenerator.setAdmDonorCode(AdmDonorCode);
        syntaxGenerator.setAdmAwardCode(AdmAwardCode);
        syntaxGenerator.setAdmProgCode(AdmProgCode);
        syntaxGenerator.setDTEnuID(DTEnuID);
        syntaxGenerator.setOpMode(OpMode);
        syntaxGenerator.setOpMonthCode(OpMonthCode);
        syntaxGenerator.setDTRSeq(String.valueOf(DTRSeq));

        SQLiteDatabase db = this.getWritableDatabase();

        String where = DT_BASIC_COL + " = '" + DTBasic + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + AdmCountryCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + AdmDonorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + AdmAwardCode + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + AdmProgCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + DTEnuID + "' " +
                " AND " + DT_R_SEQ_COL + " = " + DTRSeq +
                " AND " + OP_MODE_COL + " = '" + OpMode + "' " +
                " AND " + OP_MONTH_CODE_COL + " = '" + OpMonthCode + "' ";
        String where2 = DT_BASIC_COL + " = '" + DTBasic + "' " +
                " AND " + COUNTRY_CODE_COL + " = '" + AdmCountryCode + "' " +
                " AND " + DONOR_CODE_COL + " = '" + AdmDonorCode + "' " +
                " AND " + AWARD_CODE_COL + " = '" + AdmAwardCode + "' " +
                " AND " + PROGRAM_CODE_COL + " = '" + AdmProgCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + DTEnuID + "' " +
                " AND " + DT_R_SEQ_COL + " = " + DTRSeq +
                " AND " + OP_MODE_COL + " = '" + OpMode + "' " +
                " AND " + OP_MONTH_CODE_COL + " = '" + OpMonthCode + "' " +
                " AND " + DT_SURVEY_NUM + " = " + surveyNum;
        int idR = db.delete(DT_RESPONSE_TABLE, where, null);
        int idS = db.delete(DT_SURVEY_TABLE, where2, null);

//        Log.e("RESPONSE", idR + "");
//        Log.e("SURVEY", idS + "");

        db.close();
        /**
         * insert into uploadTable Syntax
         */

        insertIntoUploadTable(syntaxGenerator.deleteFromDTResponseTable());
    }

    /**
     * this method check either data exits or not
     *
     * @param dtBasic     - dynamic table  basic code
     * @param countryCode - country code
     * @param donorCode   - donor Code
     * @param awardCode   - award code
     * @param programCode - program code
     * @param dtEnuId     - staff id or entry by code
     * @param dtqCode     - dynamic table question
     * @param dtaCode     - dynamic table
     * @return either data exist or not
     */
    public boolean isDataExitsInDTAResponse_Table(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                                  String dtEnuId, String dtqCode, String dtaCode, int dtRSeq) {

        DTResponseTableDataModel mDta = getDTResponseTableData(dtBasic, countryCode, donorCode, awardCode, programCode, dtEnuId, dtqCode, dtaCode, dtRSeq);
        if (mDta != null)
            return true;
        else
            return false;

    }

    /**
     * delete data from DTResponseTable for unfinished data entry
     */

    public void deleteFromDTResponseTable(String DTBasic, String AdmCountryCode, String AdmDonorCode, String AdmAwardCode, String AdmProgCode, String DTEnuID, int DTRSeq, String OpMode, String OpMonthCode) {
        SQLServerSyntaxGenerator syntaxGenerator = new SQLServerSyntaxGenerator();

        syntaxGenerator.setDTBasic(DTBasic);
        syntaxGenerator.setAdmCountryCode(AdmCountryCode);
        syntaxGenerator.setAdmDonorCode(AdmDonorCode);
        syntaxGenerator.setAdmAwardCode(AdmAwardCode);
        syntaxGenerator.setAdmProgCode(AdmProgCode);
        syntaxGenerator.setDTEnuID(DTEnuID);
        syntaxGenerator.setOpMode(OpMode);
        syntaxGenerator.setOpMonthCode(OpMonthCode);
        syntaxGenerator.setDTRSeq(String.valueOf(DTRSeq));

        SQLiteDatabase db = this.getWritableDatabase();

        String where = DT_BASIC_COL + " = '" + DTBasic + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + AdmCountryCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + AdmDonorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + AdmAwardCode + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + AdmProgCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + DTEnuID + "' " +
                " AND " + DT_R_SEQ_COL + " = " + DTRSeq +
                " AND " + OP_MODE_COL + " = '" + OpMode + "' " +
                " AND " + OP_MONTH_CODE_COL + " = '" + OpMonthCode + "' ";

        String where2 = DT_BASIC_COL + " = '" + DTBasic + "' " +
                " AND " + COUNTRY_CODE_COL + " = '" + AdmCountryCode + "' " +
                " AND " + DONOR_CODE_COL + " = '" + AdmDonorCode + "' " +
                " AND " + AWARD_CODE_COL + " = '" + AdmAwardCode + "' " +
                " AND " + PROGRAM_CODE_COL + " = '" + AdmProgCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + DTEnuID + "' " +
                " AND " + DT_R_SEQ_COL + " = " + DTRSeq +
                " AND " + OP_MODE_COL + " = '" + OpMode + "' " +
                " AND " + OP_MONTH_CODE_COL + " = '" + OpMonthCode + "' ";
        /**
         * delete from response table
         */
        db.delete(DT_RESPONSE_TABLE, where, null);
        /**
         * delete from Dt survey table
         */
        db.delete(DT_SURVEY_TABLE, where2, null);

        db.close();
        /**
         * insert into uploadTable Syntax
         */

        insertIntoUploadTable(syntaxGenerator.deleteFromDTResponseTable());
    }


    /**
     * @param dtBasic     - dynamic table  basic code
     * @param countryCode - country code
     * @param donorCode   - donor Code
     * @param awardCode   - award code
     * @param programCode - program code
     * @param dtEnuId     - staff id or entry by code
     * @param dtqCode     - dynamic table question
     * @param dtaCode     - dynamic table
     * @param dtrSeq      - dynamic Response Sequence
     * @return DTResponse Object only need {@link DTResponseTableDataModel#getDtaValue()}
     */

    public DTResponseTableDataModel getDTResponseTableData(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                                           String dtEnuId, String dtqCode, String dtaCode, int dtrSeq) {
        SQLiteDatabase db = this.getReadableDatabase();
        DTResponseTableDataModel dtResponse = null;
        String sql = "SELECT * FROM " + DT_RESPONSE_TABLE + "" +

                " WHERE " + DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + programCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + dtEnuId + "' " +
                " AND " + DTQ_CODE_COL + " = '" + dtqCode + "' " +
                " AND " + DTA_CODE_COL + " = '" + dtaCode + "' " +
                " AND " + DT_R_SEQ_COL + " = " + dtrSeq;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                dtResponse = new DTResponseTableDataModel();

                dtResponse.setDtaValue(cursor.getString(9));
                dtResponse.setDtBasic(cursor.getString(cursor.getColumnIndex(DT_BASIC_COL)));
                dtResponse.setDtqCode(cursor.getString(cursor.getColumnIndex(DTQ_CODE_COL)));
                dtResponse.setDtaCode(cursor.getString(cursor.getColumnIndex(DTA_CODE_COL)));
               /* dtResponse.setCountryCode(cursor.getString(1));
                dtResponse.setDonorCode(cursor.getString(2));
                dtResponse.setAwardCode(cursor.getString(3));
                dtResponse.setProgramCode(cursor.getString(4));
                dtResponse.setDtEnuId(cursor.getString(5));
               */


            }
            cursor.close();
            db.close();
        }
        return dtResponse;
    }

    // todo: generic it
    public DTResponseTableDataModel getRadioDTResponseTableData(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                                                String dtEnuId, String dtqCode, int dtrSeq) {
        SQLiteDatabase db = this.getReadableDatabase();
        DTResponseTableDataModel dtResponse = null;
        String sql = "SELECT * FROM " + DT_RESPONSE_TABLE + "" +

                " WHERE " + DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND " + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND " + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + ADM_PROG_CODE_COL + " = '" + programCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + dtEnuId + "' " +
                " AND " + DTQ_CODE_COL + " = '" + dtqCode + "' " +
                " AND " + DT_R_SEQ_COL + " = " + dtrSeq;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                dtResponse = new DTResponseTableDataModel();
                dtResponse.setDtaCode(cursor.getString(cursor.getColumnIndex(DTA_CODE_COL)));
                dtResponse.setDtaValue(cursor.getString(9));
                dtResponse.setDtBasic(cursor.getString(cursor.getColumnIndex(DT_BASIC_COL)));
                dtResponse.setDtqCode(cursor.getString(cursor.getColumnIndex(DTQ_CODE_COL)));
                dtResponse.setDtaCode(cursor.getString(cursor.getColumnIndex(DTA_CODE_COL)));


            }
            cursor.close();
            db.close();
        }
        return dtResponse;
    }

    public List<DTResponseTableDataModel> getCheckBoxesDTResponseTableData(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                                                           String dtEnuId, String dtqCode, int dtrSeq) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<DTResponseTableDataModel> list = new ArrayList<DTResponseTableDataModel>();

        String sql = "SELECT " +
                "  dtRes." + DTA_CODE_COL +
                " , dtRes." + DTA_VALUE_COL +
                " , dtRes." + DT_BASIC_COL +
                " , dtRes." + DTQ_CODE_COL +

                " , dtan." + DTA_LABEL_COL +
                " FROM " + DT_RESPONSE_TABLE + " AS dtRes " +

                " left join " + DT_A_TABLE + " AS dtan ON " +
                " dtRes." + DT_BASIC_COL + " =  dtan." + DT_BASIC_COL +
                " AND  dtRes." + DTQ_CODE_COL + " =  dtan." + DTQ_CODE_COL +
                " AND  dtRes." + DTA_CODE_COL + " =  dtan." + DTA_CODE_COL +
                " AND  dtRes." + DTA_VALUE_COL + " =  dtan." + DTA_VALUE_COL +

                " WHERE dtRes." + DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND dtRes." + ADM_COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND dtRes." + ADM_DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND dtRes." + ADM_AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND dtRes." + ADM_PROG_CODE_COL + " = '" + programCode + "' " +
                " AND dtRes." + DT_ENU_ID_COL + " = '" + dtEnuId + "' " +
                " AND dtRes." + DTQ_CODE_COL + " = '" + dtqCode + "' " +
                " AND dtRes." + DT_R_SEQ_COL + " = " + dtrSeq;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {

                DTResponseTableDataModel dtResponse = new DTResponseTableDataModel();
                dtResponse.setDtaCode(cursor.getString(cursor.getColumnIndex(DTA_CODE_COL)));

                dtResponse.setDtaValue(cursor.getString(cursor.getColumnIndex(DTA_VALUE_COL)));
                dtResponse.setDtBasic(cursor.getString(cursor.getColumnIndex(DT_BASIC_COL)));
                dtResponse.setDtqCode(cursor.getString(cursor.getColumnIndex(DTQ_CODE_COL)));
                dtResponse.setDtaCode(cursor.getString(cursor.getColumnIndex(DTA_CODE_COL)));
                dtResponse.setDtALabel(cursor.getString(cursor.getColumnIndex(DTA_LABEL_COL)));


                list.add(dtResponse);

            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return list;
    }

    public String getDTSkipDTQCde(String dtBasicCode, String dtQCode, String dtaCodeComB) {
        String dtSkipDTQCode = "";
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT " + DTSKIP_DTQ_CODE_COL + " FROM " + DTA_SKIP_TABLE + "" +

                " WHERE " + DT_BASIC_COL + " = '" + dtBasicCode + "' " +
                " AND " + DTQ_CODE_COL + " = '" + dtQCode + "' " +
                " AND " + DTA_CODE_COMB_N_COL + " = '" + dtaCodeComB + "' ";

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            dtSkipDTQCode = cursor.getString(cursor.getColumnIndex(DTSKIP_DTQ_CODE_COL));

            cursor.close();
        }

        db.close();
        return dtSkipDTQCode;
    }


    public ArrayList<DTSurveyTableDataModel> dtSurveyTableDataModels(int surveyNum, String dtBasic, String cCode, String donorCode, String awardCode, String prgCode, String entryBy) {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<DTSurveyTableDataModel> dtSurveyTableDataList = new ArrayList<>();

        String sql = SQLiteQuery.dtSurveyTableDataModels_sql(surveyNum, dtBasic);

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int count = 0;
                do {
                    DTSurveyTableDataModel dtSurveyTableData = new DTSurveyTableDataModel();

                    dtSurveyTableData.setDtBasic(cursor.getString(cursor.getColumnIndex(DT_BASIC_COL)));
                    dtSurveyTableData.setCountryCode(cursor.getString(cursor.getColumnIndex("CountryCode")));
                    dtSurveyTableData.setDonorCode(cursor.getString(cursor.getColumnIndex("DonorCode")));
                    dtSurveyTableData.setAwardCode(cursor.getString(cursor.getColumnIndex("AwardCode")));
                    dtSurveyTableData.setProgramCode(cursor.getString(cursor.getColumnIndex("ProgramCode")));
                    dtSurveyTableData.setDtEnuId(cursor.getString(cursor.getColumnIndex(DT_ENU_ID_COL)));
                    dtSurveyTableData.setDtqCode(cursor.getString(cursor.getColumnIndex(DTQ_CODE_COL)));
                    dtSurveyTableData.setDtaCode(cursor.getString(cursor.getColumnIndex(DTA_CODE_COL)));
                    dtSurveyTableData.setDtrSeq(cursor.getInt(cursor.getColumnIndex(DT_R_SEQ_COL)));
                    dtSurveyTableData.setDtaValue(cursor.getString(cursor.getColumnIndex(DTA_VALUE_COL)));
                    dtSurveyTableData.setProgActivityCode(cursor.getString(cursor.getColumnIndex(PROG_ACTIVITY_CODE_COL)));
                    dtSurveyTableData.setDttTimeString(cursor.getString(cursor.getColumnIndex(DTTIME_STRING_COL)));
                    dtSurveyTableData.setOpMode(cursor.getString(cursor.getColumnIndex(OP_MODE_COL)));
                    dtSurveyTableData.setOpMonthCode(cursor.getString(cursor.getColumnIndex(OP_MONTH_CODE_COL)));
                    dtSurveyTableData.setDataType(cursor.getString(cursor.getColumnIndex(DATA_TYPE_COL)));
                    dtSurveyTableData.setDtqText(cursor.getString(cursor.getColumnIndex(DTQ_TEXT_COL)));
                    dtSurveyTableData.setDtSurveyNumber(cursor.getInt(cursor.getColumnIndex(DT_SURVEY_NUM)));

                    dtSurveyTableData.setDtPhoto(cursor.getString(cursor.getColumnIndex(U_FILE_COL)));
                    dtSurveyTableData.setDtResController(cursor.getString(cursor.getColumnIndex(RESPONSE_VALUE_CONTROL_COL)));

                    dtSurveyTableData.setDtQResLupText(cursor.getString(cursor.getColumnIndex(QRES_LUP_TEXT_COL)));
                    dtSurveyTableData.setDtALabel(cursor.getString(cursor.getColumnIndex(DTA_LABEL_COL)));

                    /***
                     *  add to the list
                     */
                    dtSurveyTableDataList.add(dtSurveyTableData);
                    count++;
                } while (cursor.moveToNext());
            }
//            Log.e("TEST_GEO", test);
            cursor.close();
            db.close();
        }
        return dtSurveyTableDataList;
    }


    public void addIntoDTTableDefinition(String tableName, String fieldName, String fieldDefinition, String fieldShortName,
                                         String valueUdf, String lupTableName, String adminOnly, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TABLE_NAME_COL, tableName);
        values.put(FIELD_NAME_COL, fieldName);
        values.put(FIELD_DEFINITION_COL, fieldDefinition);
        values.put(FIELD_SHORT_NAME_COL, fieldShortName);
        values.put(VALUE_UDF_COL, valueUdf);
        values.put(LUPTABLE_NAME_COL, lupTableName);
        values.put(ADMIN_ONLY_COL, adminOnly);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        long id = db.insert(DT_TABLE_DEFINITION_TABLE, null, values);
        db.close();
    }

    public void addIntoDTTableListCategory(String tableName, String tableGroupCode, String useAdminOnly, String useReport,
                                           String useTransection, String useLup) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TABLE_NAME_COL, tableName);
        values.put(TABLE_GROUP_CODE_COL, tableGroupCode);
        values.put(USE_ADMIN_ONLY_COL, useAdminOnly);
        values.put(USE_REPORT_COL, useReport);
        values.put(USE_TRANSACTION_COL, useTransection);
        values.put(USE_LUP_COL, useLup);

        db.insert(DTTABLE_LIST_CATEGORY_TABLE, null, values);
        db.close();
    }

    public void addTemporaryAdmCountryProgram(String cCode, String donorCode, String awardCode, String programCode, String progName, String progShortName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(ADM_PROG_CODE_COL, programCode);
        values.put(PROGRAM_NAME_COL, progName);
        values.put(PROGRAM_SHORT_NAME_COL, progShortName);
  /*      values.put(ADM_SRV_CODE_COL, servCode);
        values.put(FOOD_FLAG, food);
        values.put(NON_FOOD_FLAG, nonFood);
        values.put(CASH_FLAG, cash);
        values.put(VOUCHER_FLAG, voucher);*/


        // Inserting Row
        db.insert(TEMPORARY_COUNTRY_PROGRAM_TABLE, null, values);
        db.close(); // Closing database connection


    }

    // todo: # quary for show program Names

    public List<ProgramMasterDM> getProgramListNames(String cCode) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<ProgramMasterDM> list = new ArrayList<ProgramMasterDM>();

        String sql = "SELECT " + ADM_COUNTRY_CODE_COL +
                ", " + ADM_DONOR_CODE_COL +
                ", " + ADM_AWARD_CODE_COL +
                ", " + ADM_PROG_CODE_COL +
                ", " + PROGRAM_NAME_COL +
                ", " + PROGRAM_SHORT_NAME_COL +
                " FROM " + TEMPORARY_COUNTRY_PROGRAM_TABLE + "" +


                " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' ";

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {


                ProgramMasterDM progData = new ProgramMasterDM();
                progData.setAdmCountryCode(cursor.getString(cursor.getColumnIndex(ADM_COUNTRY_CODE_COL)));
                progData.setAdmProgCode(cursor.getString(cursor.getColumnIndex(ADM_DONOR_CODE_COL)));
                progData.setAdmAwardCode(cursor.getString(cursor.getColumnIndex(ADM_AWARD_CODE_COL)));
                progData.setAdmDonorCode(cursor.getString(cursor.getColumnIndex(ADM_PROG_CODE_COL)));
                progData.setProgName(cursor.getString(cursor.getColumnIndex(PROGRAM_NAME_COL)));
                progData.setProgShortName(cursor.getString(cursor.getColumnIndex(PROGRAM_SHORT_NAME_COL)));

                list.add(progData);


            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return list;

    }

    public void addTemporaryOpMonth(String cCode, String donorCode, String awardCode, String opCode, String opMonthCode, String mLable, String usasDate, String usaeDate, String status) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ADM_COUNTRY_CODE_COL, cCode);
        values.put(ADM_DONOR_CODE_COL, donorCode);
        values.put(ADM_AWARD_CODE_COL, awardCode);
        values.put(OPERATION_CODE_COL, opCode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(MONTH_LABEL_COL, mLable);
        values.put(USA_START_DATE_COL, usasDate);
        values.put(USA_END_DATE_COL, usaeDate);
        values.put(STATUS, status);

        // Inserting Row
        db.insert(TEMPORARY_OP_MONTH_TABLE, null, values);
        db.close(); // Closing database connection


    }


    public List<TemOpMonth> getOpMonthList(String cCode) {
        SQLiteDatabase db = this.getReadableDatabase();


        List<TemOpMonth> list = new ArrayList<TemOpMonth>();

        String sql = "SELECT " + OP_MONTH_CODE_COL
                + ", " + MONTH_LABEL_COL
                + " FROM " + TEMPORARY_OP_MONTH_TABLE
                + " WHERE " + ADM_COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + OPERATION_CODE_COL + " = '2' "
                + " AND " + STATUS + " = 'A' ";

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {


                TemOpMonth opData = new TemOpMonth();
                opData.setOpMonthCode(cursor.getString(cursor.getColumnIndex(OP_MONTH_CODE_COL)));
                opData.setOpMonthLable(cursor.getString(cursor.getColumnIndex(MONTH_LABEL_COL)));


                list.add(opData);


            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return list;

    }


    public ArrayList<TaSummary> getTaSummary(String sql) {

        ArrayList<TaSummary> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                TaSummary dataModel = new TaSummary();


                dataModel.setCode(cursor.getString(cursor.getColumnIndex("code")));
                dataModel.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                dataModel.setCount(cursor.getString(cursor.getColumnIndex("count")));


                list.add(dataModel);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;


    }

    public void cleanTemTableForService() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TEMPORARY_OP_MONTH_TABLE, null, null);
            db.delete(TEMPORARY_COUNTRY_PROGRAM_TABLE, null, null);

        } catch (Exception e) {
//            Log.e(TAG, " Teptable " + e);
        }

    }


    public boolean isMultipleCountryAccessUser() {
        Cursor cursor;
        String count = "0";
        SQLiteDatabase db = this.getReadableDatabase();

        String query = " SELECT * FROM " + SELECTED_COUNTRY_TABLE;
//        Log.d(TAG, query);
        cursor = db.rawQuery(query, null);

//        Log.d(TAG, " " + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }

    }

}
