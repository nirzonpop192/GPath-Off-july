package com.siddiquinoor.restclient.activity.sub_activity.service_sub;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.ServiceActivity;
import com.siddiquinoor.restclient.data_model.ServiceSpecificDataModel;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.ServiceDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ServiceSpecification extends BaseActivity {
    /**
     * constant
     */
    private static final String TAG = "ServiceSpecification";
    /**
     * CA2 is program type
     */
    public static final String CA2 = "010100104";
    public static final String CU2 = "010100103";
    public static final String LM = "010100102";
    public static final String PW = "010100101";
    public static final String MG = "010100306";
    public static final String IG = "010100304";
    private final Context CONTEXT = ServiceSpecification.this;
    /**
     *
     */
    private Spinner spBabyStatus, spGMPAttendance, spWeightStatus, spNutAttendance,
            spVitA_Under5, spExclusive_CurrentlyBF, spCMAMRef, spCMAMAdd
            ,spAnimalType,spCropType, spLeadPosition,spLoanSource;

    private TextView lbBabyStatus, lbGMPAttendance, lbWeightStatus, lbNutAttendance,
            lbVitA_Under5, lbDateCompFeeding, lbCMAMRef, lbExclusive_CurrentlyBF, lbCMAMAdd, tvDateCompFeeding;

    private TextView tvId, tvName, tvAward, tvCriteria, tvServiceMonth, tvGroupName;

    //   private String gmpAttendanceCode;

    private TextView lbDateANCVisit, tvDateANCVisit, lbFacilityPostnatal, lbDeliveryStaff,
            lbHomeSupport24H, lbHomeSupport48H, lbMaternalComplications, lbNewbornComplications,
            lbChildhoodIllnesses, lbImmunizations, lbFPCounsel
            ,lbAnimalType,lbCropType,lbLoanAMT,lbLeadPosition ,lbLoanSource;



    private RadioGroup rg_DeliveryStaff;
    private RadioButton rbtn_DeliveryStaff_1, rbtn_DeliveryStaff_2, rbtn_DeliveryStaff_3;

    ServiceDataModel srvData;

    private RelativeLayout rlayout_DateCompFeeding, rlayout_DateANCVisit;
    /**
     * the linear layout holds the Check box
     */
    private LinearLayout llayout_FacilityPostnatal_cb_group, llay_HomeSupport24H_checkbox,
            llay_HomeSupport48H_checkbox, llay_MaternalComplications_checkbox, llay_NewbornComplications_checkbox,
            llay_ChildhoodIllnesses_checkbox, llay_Immunizations_checkbox, llay_FPCounsel_checkbox;

    private CheckBox cb_PNCVisit_2D, cb_PNCVisit_1W, cb_PNCVisit_6W,
            cb_HomeSupport24H_1d, cb_HomeSupport24H_2d, cb_HomeSupport24H_3d, cb_HomeSupport24H_8d,
            cb_HomeSupport24H_14d, cb_HomeSupport24H_21d, cb_HomeSupport24H_30d, cb_HomeSupport24H_60d,
            cb_HomeSupport24H_90d, cb_HomeSupport48H_1d, cb_HomeSupport48H_3d, cb_HomeSupport48H_8d,
            cb_HomeSupport48H_30d, cb_HomeSupport48H_60d, cb_HomeSupport48H_90d;

    private CheckBox cb_Maternal_Bleeding, cb_Maternal_Seizure,
            cb_Maternal_Infection, cb_Maternal_ProlongedLabor,
            cb_Maternal_ObstructedLabor, cb_Maternal_PPRM;

    private CheckBox cb_NBron_Asphyxia, cb_NBron_Sepsis,
            cb_NBron_Hypothermia, cb_NBron_Hyperthermia,
            cb_NBron_NoSuckling, cb_NBron_Jaundice;
    private CheckBox cb_CIllness_Diarrhea, cb_CIllness_Pneumonia,
            cb_CIllness_Fever, cb_CIllness_Cerebral_Palsy;

    private CheckBox cb_Immu_Polio, cb_Immu_BCG,
            cb_Immu_Measles, cb_Immu_DPT_HIB,
            cb_Immu_Lotta, cb_Immu_Other;
    private CheckBox cb_FPCounsel_Male_Condoms, cb_FPCounsel_Female_Condoms,
            cb_FPCounsel_Pills, cb_FPCounsel_Depo,
            cb_FPCounsel_LongParmanen, cb_FPCounsel_NoMethod;
    private Button btnSave, btnGoToService, btnHome, btnDelete;
    private SQLiteHandler sqlH;


    private String babyStatus;
    private String gmpAttendence, weightStatus, nutAttendance, vitaUnder5, exclCurrentLybf;
    private String dateComFeed, camRef, camAdd;
    private String dateAncVisit;
    private String pncVisit2D, pncVisit1W, pncVisit6W;
    private String deliveryStaff_1, deliveryStaff_2, deliveryStaff_3;
    private String homeSupport24H_1D, homeSupport24H_2D, homeSupport24H_3D, homeSupport24H_8D,
            homeSupport24H_14D, homeSupport24H_21D, homeSupport24H_30D, homeSupport24H_60D, homeSupport24H_90D;

    private String homeSupport48H_1D, homeSupport48H_3D, homeSupport48H_8D, homeSupport48H_30D,
            homeSupport48H_60D, homeSupport48H_90D;

    private String maternal_bleeed, maternal_seizure, maternal_infection,
            maternal_proLongedLabor, maternal_obstructedLabor, maternal_pprm;
    private String nBorn_Aspyxia, nBorn_Sepsis, nBorn_HypoThermai, nBorn_HyperThermai,
            nBorn_noSuckling, nBorn_Jaundices;
    private String child_Diarrhea, child_Pneumonia, child_Fever, child_CerebralPalsy;
    private String immu_Polio, immu_BCG, immu_Measles, immu_DPT_HIB, immu_Lotta, immU_Other;
    private String fpCounsel_MaleCondom, fpCounsel_FemaleCondom, fpCounsel_Pill, fpCounsel_Depo,
            fpCounsel_LongParmanent, fpCounsel_NoMethod;
    private String cropCode, loanSource;
    private String loanAMT, animalCode;
    private String leadCode;
    private boolean mDataExits = false;
    private EditText edtLoanAMT;
    private String strAnimalType,strCropType,strLoanSource,strLeadCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_specification);
        initalize();


        // Then you can pull them back out with getParcelableExtra():

        Intent i = getIntent();
        srvData = (ServiceDataModel) i.getParcelableExtra(KEY.SERVICE_DATA_OBJECT_KEY);
        String criteriaCode = srvData.getDonor_code() + srvData.getAward_code() + srvData.getProgram_code() + srvData.getService_code();
        Log.d("NIR0","Service Record: idGroup idGroupCat:"+srvData.getTemIdGroupCat()+" strGroupCat: "+srvData.getTemStrGroupCat()
                        +"   idGroup:"+srvData.getTemIdGroup()+" strGroup : "+srvData.getTemStrGroup());

        Log.d("Nir", "criteriaCode : " + criteriaCode);

        switch (criteriaCode){
            case CA2:
            case CU2:
                showViewInCA_N_CU();
                break;
            case PW:
            case LM:
                showViewInPLW();
                break;
            case MG:
                showViewInMG();
                break;
            case IG:
                showViewInIG();
                break;

        }

       /* if (criteriaCode.equals(CA2) || criteriaCode.equals(CU2)) {
            showViewInCA_N_CU();
        } else {
            showViewInPLW();
        }*/

        String memId15Digit = "";
        memId15Digit = srvData.getDistrictCode() + srvData.getUpazillaCode() + srvData.getUnitCode() + srvData.getVillageCode()
                + srvData.getHHID() + srvData.getMemberId();


        setDataInTextView(memId15Digit);

        /**
         * if member exits in Service Specific Table
         */
        if (sqlH.isDataExitsInServiceSpecificTable(srvData.getC_code(), srvData.getDonor_code(), srvData.getAward_code()
                , srvData.getProgram_code(), srvData.getService_code(), "2", srvData.getOpMontheCode(), srvData.getServiceCenterCode()
                , srvData.getFPDCode(), memId15Digit)) {

            ServiceSpecificDataModel data = sqlH.getSrvSpecificByMemberId(srvData.getC_code(), srvData.getDonor_code(), srvData.getAward_code()
                    , srvData.getProgram_code(), srvData.getService_code(), "2", srvData.getOpMontheCode(), srvData.getServiceCenterCode()
                    , srvData.getFPDCode(), memId15Digit);
            getSrvSpecificDetails(data);
            mDataExits = true;
        }


        testLogDebug();

        loadBabyStatus();
        loadGMPAttendance();
        loadWeightStatus();
        loadNutAttendance();
        loadVitaminA_under5();
        loadExclusive_currentlyBF();
        loadCMAMRef();
        loadCMAMAdd();

        loadAnimalType();
        loadCropType();
        loadLoanSource();
        loadLeadPostion();

        tvDateCompFeeding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateCompFeeding();
            }
        });


        tvDateANCVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setANCVisitDate();
            }
        });
        saveData();

        goToServicePage();
        gotoHome();
        deleteRecord();


    }

    private void deleteRecord() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CONTEXT);
                alertDialog.setTitle("Confirm ");
                alertDialog.setMessage("Delete ?");
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int selectedID) {
                                /**
                                 * Service page the ei page asar somy
                                 * serial no 1 barai niya ase
                                 * tai delete korle sei ager sl no thake na
                                 * delete sl no 1 minus kore niyasi
                                 */


                                Log.d(TAG, "Service in delete  Serial no :" + srvData.getServiceSLCode());

                                int sl = Integer.parseInt(srvData.getServiceSLCode());

                                if (sl > 1) {
                                    sl = sl - 1;
                                    String temp = "0" + String.valueOf(sl);
                                    srvData.setServiceSLCode(temp);

                                }


                                SQLServerSyntaxGenerator srvSpecfic = new SQLServerSyntaxGenerator();

                                srvSpecfic.setAdmCountryCode(srvData.getC_code());
                                srvSpecfic.setAdmDonorCode(srvData.getDonor_code());
                                srvSpecfic.setAdmAwardCode(srvData.getAward_code());
                                srvSpecfic.setLayR1ListCode(srvData.getDistrictCode());
                                srvSpecfic.setLayR2ListCode(srvData.getUpazillaCode());
                                srvSpecfic.setLayR3ListCode(srvData.getUnitCode());
                                srvSpecfic.setLayR4ListCode(srvData.getVillageCode());
                                srvSpecfic.setHHID(srvData.getHHID());
                                srvSpecfic.setMemID(srvData.getMemberId());
                                srvSpecfic.setProgCode(srvData.getProgram_code());
                                srvSpecfic.setSrvCode(srvData.getService_code());
                                srvSpecfic.setOpCode("2");
                                srvSpecfic.setOpMonthCode(srvData.getOpMontheCode());
                                srvSpecfic.setSrvSL(srvData.getServiceSLCode());


                                int id = sqlH.deleteService(srvData.getC_code(), srvData.getDonor_code(),
                                        srvData.getAward_code(), srvData.getDistrictCode(),
                                        srvData.getUpazillaCode(), srvData.getUnitCode(),
                                        srvData.getVillageCode(), srvData.getHHID(),
                                        srvData.getMemberId(), srvData.getProgram_code(),
                                        srvData.getService_code(), srvData.getOpCode(),
                                        srvData.getOpMontheCode(), srvData.getServiceSLCode()
                                );
                                /**
                                 * for test del log
                                 */
                               // deleteLog();


                                ResetViews();

                                sqlH.deleteFromServSpecificTable(srvData.getC_code(), srvData.getDonor_code(), srvData.getAward_code()
                                        , srvData.getDistrictCode(), srvData.getUpazillaCode(), srvData.getUnitCode(), srvData.getVillageCode(),
                                        srvData.getHHID(), srvData.getMemberId(), srvData.getProgram_code(), srvData.getService_code(), "2"
                                        , srvData.getOpMontheCode());

                                sqlH.insertIntoUploadTable(srvSpecfic.deleteMemberFromSrvTable());
                                sqlH.insertIntoUploadTable(srvSpecfic.deleteFromSrvSpecific());

                                Toast.makeText(CONTEXT, "One Member deleted successfully!", Toast.LENGTH_LONG).show();


                            }
                        });

                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();


            }
        });
    }

    private void deleteLog() {
        Log.d("Delete", "getC_code:" + srvData.getC_code() + "getDonor_code:" + srvData.getDonor_code()
                + " getAward_code" + srvData.getAward_code() + " getDistrictCode :" + srvData.getDistrictCode()
                + " getUpazillaCode:" + srvData.getUpazillaCode() + " getUnitCode:" + srvData.getUnitCode()
                + " getVillageCode: " + srvData.getVillageCode() + " getHHID:" + srvData.getHHID()
                + " getMemberId:" + srvData.getMemberId() + " getProgram_code:" + srvData.getProgram_code()
                + " getService_code:" + srvData.getService_code() + " getOpCode: " + srvData.getOpCode()
                + " getOpMontheCode : " + srvData.getOpMontheCode() + " getServiceSLCode : " + srvData.getServiceSLCode());
    }

    private void gotoHome() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity(ServiceSpecification.this);
            }
        });
    }

    /**
     * This method will distribute the views state against the data of Service Specific.
     *
     * @param data
     */

    private void getSrvSpecificDetails(ServiceSpecificDataModel data) {


        if (spBabyStatus.getVisibility() == View.VISIBLE) {
            babyStatus = data.getBabyStatus();
        }


        if (spGMPAttendance.getVisibility() == View.VISIBLE) {
            gmpAttendence = data.getGmpAttendence();
        }
        if (spWeightStatus.getVisibility() == View.VISIBLE) {
            weightStatus = data.getWeightStatus();
        }
        if (spNutAttendance.getVisibility() == View.VISIBLE) {
            nutAttendance = data.getNutAttendance();
        }
        if (spVitA_Under5.getVisibility() == View.VISIBLE) {
            vitaUnder5 = data.getVitaUnder5();
        }
        if (spExclusive_CurrentlyBF.getVisibility() == View.VISIBLE) {
            exclCurrentLybf = data.getExclCurrentLybf();
        }
        /**
         * date type
         */
        if (tvDateCompFeeding.getVisibility() == View.VISIBLE) {
            dateComFeed = data.getDateComFeed();
            tvDateCompFeeding.setText(dateComFeed);
        }
        if (spCMAMRef.getVisibility() == View.VISIBLE) {
            camRef = data.getCamRef();
        }
        if (spCMAMAdd.getVisibility() == View.VISIBLE) {
            camAdd = data.getCamAdd();
        }
        /**
         * date type
         */
        if (tvDateANCVisit.getVisibility() == View.VISIBLE) {
            dateAncVisit = data.getDateAncVisit();
            tvDateANCVisit.setText(dateAncVisit);
        }

        if (llayout_FacilityPostnatal_cb_group.getVisibility() == View.VISIBLE) {
            pncVisit2D = data.getPncVisit2D();
            pncVisit1W = data.getPncVisit1W();
            pncVisit6W = data.getPncVisit6W();


            cb_PNCVisit_2D.setChecked(pncVisit2D.equals("1"));
            cb_PNCVisit_1W.setChecked(pncVisit1W.equals("1"));
            cb_PNCVisit_6W.setChecked(pncVisit6W.equals("1"));


        }
        if (rg_DeliveryStaff.getVisibility() == View.VISIBLE) {
            deliveryStaff_1 = data.getDeliveryStaff_1();
            deliveryStaff_2 = data.getDeliveryStaff_2();
            deliveryStaff_3 = data.getDeliveryStaff_3();

            rbtn_DeliveryStaff_1.setChecked(deliveryStaff_1.equals("1"));
            rbtn_DeliveryStaff_2.setChecked(deliveryStaff_2.equals("1"));
            rbtn_DeliveryStaff_3.setChecked(deliveryStaff_3.equals("1"));

        }
        if (llay_HomeSupport24H_checkbox.getVisibility() == View.VISIBLE) {
            homeSupport24H_1D = data.getHomeSupport24H_1D();
            homeSupport24H_2D = data.getHomeSupport24H_2D();
            homeSupport24H_3D = data.getHomeSupport24H_3D();
            homeSupport24H_8D = data.getHomeSupport24H_8D();
            homeSupport24H_14D = data.getHomeSupport24H_14D();
            homeSupport24H_21D = data.getHomeSupport24H_21D();
            homeSupport24H_30D = data.getHomeSupport24H_30D();
            homeSupport24H_60D = data.getHomeSupport24H_60D();
            homeSupport24H_90D = data.getHomeSupport24H_90D();


            cb_HomeSupport24H_1d.setChecked(homeSupport24H_1D.equals("1"));
            cb_HomeSupport24H_2d.setChecked(homeSupport24H_2D.equals("1"));
            cb_HomeSupport24H_3d.setChecked(homeSupport24H_3D.equals("1"));
            cb_HomeSupport24H_8d.setChecked(homeSupport24H_8D.equals("1"));
            cb_HomeSupport24H_14d.setChecked(homeSupport24H_14D.equals("1"));
            cb_HomeSupport24H_21d.setChecked(homeSupport24H_21D.equals("1"));
            cb_HomeSupport24H_30d.setChecked(homeSupport24H_30D.equals("1"));
            cb_HomeSupport24H_60d.setChecked(homeSupport24H_60D.equals("1"));
            cb_HomeSupport24H_90d.setChecked(homeSupport24H_90D.equals("1"));

        }

        if (llay_HomeSupport48H_checkbox.getVisibility() == View.VISIBLE) {

            homeSupport48H_1D = data.getHomeSupport48H_1D();
            homeSupport48H_3D = data.getHomeSupport48H_3D();
            homeSupport48H_8D = data.getHomeSupport48H_8D();
            homeSupport48H_30D = data.getHomeSupport48H_30D();
            homeSupport48H_60D = data.getHomeSupport48H_60D();
            homeSupport48H_90D = data.getHomeSupport48H_90D();

            cb_HomeSupport48H_1d.setChecked(homeSupport48H_1D.equals("1"));
            cb_HomeSupport48H_3d.setChecked(homeSupport48H_3D.equals("1"));
            cb_HomeSupport48H_8d.setChecked(homeSupport48H_8D.equals("1"));
            cb_HomeSupport48H_30d.setChecked(homeSupport48H_30D.equals("1"));
            cb_HomeSupport48H_60d.setChecked(homeSupport48H_60D.equals("1"));
            cb_HomeSupport48H_90d.setChecked(homeSupport48H_90D.equals("1"));
        }
        if (llay_MaternalComplications_checkbox.getVisibility() == View.VISIBLE) {

            maternal_bleeed = data.getMaternal_bleeed();
            maternal_seizure = data.getMaternal_seizure();
            maternal_infection = data.getMaternal_infection();
            maternal_proLongedLabor = data.getMaternal_proLongedLabor();
            maternal_obstructedLabor = data.getMaternal_obstructedLabor();
            maternal_pprm = data.getMaternal_pprm();


            cb_Maternal_Bleeding.setChecked(maternal_bleeed.equals("1"));
            cb_Maternal_Seizure.setChecked(maternal_seizure.equals("1"));
            cb_Maternal_Infection.setChecked(maternal_infection.equals("1"));
            cb_Maternal_ProlongedLabor.setChecked(maternal_proLongedLabor.equals("1"));
            cb_Maternal_ObstructedLabor.setChecked(maternal_obstructedLabor.equals("1"));
            cb_Maternal_PPRM.setChecked(maternal_pprm.equals("1"));
        }
        if (llay_NewbornComplications_checkbox.getVisibility() == View.VISIBLE) {
            nBorn_Aspyxia = data.getnBorn_Aspyxia();
            nBorn_Sepsis = data.getnBorn_Sepsis();
            nBorn_HypoThermai = data.getnBorn_HypoThermai();
            nBorn_HyperThermai = data.getnBorn_HyperThermai();
            nBorn_noSuckling = data.getnBorn_noSuckling();
            nBorn_Jaundices = data.getnBorn_Jaundices();


            cb_NBron_Asphyxia.setChecked(nBorn_Aspyxia.equals("1"));
            cb_NBron_Sepsis.setChecked(nBorn_Sepsis.equals("1"));
            cb_NBron_Hypothermia.setChecked(nBorn_HypoThermai.equals("1"));
            cb_NBron_Hyperthermia.setChecked(nBorn_HyperThermai.equals("1"));
            cb_NBron_NoSuckling.setChecked(nBorn_noSuckling.equals("1"));
            cb_NBron_Jaundice.setChecked(nBorn_Jaundices.equals("1"));
        }
        if (llay_ChildhoodIllnesses_checkbox.getVisibility() == View.VISIBLE) {
            child_Diarrhea = data.getChild_Diarrhea();
            child_Pneumonia = data.getChild_Pneumonia();
            child_Fever = data.getChild_Fever();
            child_CerebralPalsy = data.getChild_CerebralPalsy();

            cb_CIllness_Diarrhea.setChecked(child_Diarrhea.equals("1"));
            cb_CIllness_Pneumonia.setChecked(child_Pneumonia.equals("1"));
            cb_CIllness_Fever.setChecked(child_Fever.equals("1"));
            cb_CIllness_Cerebral_Palsy.setChecked(child_CerebralPalsy.equals("1"));
        }
        if (llay_Immunizations_checkbox.getVisibility() == View.VISIBLE) {

            immu_Polio = data.getImmu_Polio();
            immu_BCG = data.getImmu_BCG();
            immu_Measles = data.getImmu_Measles();
            immu_DPT_HIB = data.getImmu_DPT_HIB();
            immu_Lotta = data.getImmu_Lotta();
            immU_Other = data.getImmU_Other();


            cb_Immu_Polio.setChecked(immu_Polio.equals("1"));
            cb_Immu_BCG.setChecked(immu_BCG.equals("1"));
            cb_Immu_Measles.setChecked(immu_Measles.equals("1"));
            cb_Immu_DPT_HIB.setChecked(immu_DPT_HIB.equals("1"));
            cb_Immu_Lotta.setChecked(immu_Lotta.equals("1"));
            cb_Immu_Other.setChecked(immU_Other.equals("1"));
        }
        if (llay_FPCounsel_checkbox.getVisibility() == View.VISIBLE) {

            fpCounsel_MaleCondom = data.getFpCounsel_MaleCondom();
            fpCounsel_FemaleCondom = data.getFpCounsel_FemaleCondom();
            fpCounsel_Pill = data.getFpCounsel_Pill();
            fpCounsel_Depo = data.getFpCounsel_Depo();
            fpCounsel_LongParmanent = data.getFpCounsel_LongParmanent();
            fpCounsel_NoMethod = data.getFpCounsel_NoMethod();


            cb_FPCounsel_Male_Condoms.setChecked(fpCounsel_MaleCondom.equals("1"));
            cb_FPCounsel_Female_Condoms.setChecked(fpCounsel_FemaleCondom.equals("1"));
            cb_FPCounsel_Pills.setChecked(fpCounsel_Pill.equals("1"));
            cb_FPCounsel_Depo.setChecked(fpCounsel_Depo.equals("1"));
            cb_FPCounsel_LongParmanen.setChecked(fpCounsel_LongParmanent.equals("1"));
            cb_FPCounsel_NoMethod.setChecked(fpCounsel_NoMethod.equals("1"));
        }


        /** todo: do it later
         private String cropCode, loanSource;
         private String loanAMT, animalCode;
         private String leadCode;*/


    }

    private void ResetViews() {


        if (spBabyStatus.getVisibility() == View.VISIBLE) {
            babyStatus = null;
        }


        if (spGMPAttendance.getVisibility() == View.VISIBLE) {
            gmpAttendence = null;
        }
        if (spWeightStatus.getVisibility() == View.VISIBLE) {
            weightStatus = null;
        }
        if (spNutAttendance.getVisibility() == View.VISIBLE) {
            nutAttendance = null;
        }
        if (spVitA_Under5.getVisibility() == View.VISIBLE) {
            vitaUnder5 = null;
        }
        if (spExclusive_CurrentlyBF.getVisibility() == View.VISIBLE) {
            exclCurrentLybf = null;
        }
        /**
         * date type
         */
        if (tvDateCompFeeding.getVisibility() == View.VISIBLE) {
            dateComFeed = "date";
            tvDateCompFeeding.setText(dateComFeed);
        }
        if (spCMAMRef.getVisibility() == View.VISIBLE) {
            camRef = null;
        }
        if (spCMAMAdd.getVisibility() == View.VISIBLE) {
            camAdd = null;
        }
        /**
         * date type
         */
        if (tvDateANCVisit.getVisibility() == View.VISIBLE) {
            dateAncVisit = "date";
            tvDateANCVisit.setText(dateAncVisit);
        }

        if (llayout_FacilityPostnatal_cb_group.getVisibility() == View.VISIBLE) {
            pncVisit2D = "0";
            pncVisit1W = "0";
            pncVisit6W = "0";


            cb_PNCVisit_2D.setChecked(pncVisit2D.equals("1"));
            cb_PNCVisit_1W.setChecked(pncVisit1W.equals("1"));
            cb_PNCVisit_6W.setChecked(pncVisit6W.equals("1"));


        }
        if (rg_DeliveryStaff.getVisibility() == View.VISIBLE) {
            deliveryStaff_1 = "0";
            deliveryStaff_2 = "0";
            deliveryStaff_3 = "0";

            rbtn_DeliveryStaff_1.setChecked(deliveryStaff_1.equals("1"));
            rbtn_DeliveryStaff_2.setChecked(deliveryStaff_2.equals("1"));
            rbtn_DeliveryStaff_3.setChecked(deliveryStaff_3.equals("1"));

        }
        if (llay_HomeSupport24H_checkbox.getVisibility() == View.VISIBLE) {
            homeSupport24H_1D = "0";
            homeSupport24H_2D = "0";
            homeSupport24H_3D = "0";
            homeSupport24H_8D = "0";
            homeSupport24H_14D = "0";
            homeSupport24H_21D = "0";
            homeSupport24H_30D = "0";
            homeSupport24H_60D = "0";
            homeSupport24H_90D = "0";


            cb_HomeSupport24H_1d.setChecked(homeSupport24H_1D.equals("1"));
            cb_HomeSupport24H_2d.setChecked(homeSupport24H_2D.equals("1"));
            cb_HomeSupport24H_3d.setChecked(homeSupport24H_3D.equals("1"));
            cb_HomeSupport24H_8d.setChecked(homeSupport24H_8D.equals("1"));
            cb_HomeSupport24H_14d.setChecked(homeSupport24H_14D.equals("1"));
            cb_HomeSupport24H_21d.setChecked(homeSupport24H_21D.equals("1"));
            cb_HomeSupport24H_30d.setChecked(homeSupport24H_30D.equals("1"));
            cb_HomeSupport24H_60d.setChecked(homeSupport24H_60D.equals("1"));
            cb_HomeSupport24H_90d.setChecked(homeSupport24H_90D.equals("1"));

        }

        if (llay_HomeSupport48H_checkbox.getVisibility() == View.VISIBLE) {

            homeSupport48H_1D = "0";
            homeSupport48H_3D = "0";
            homeSupport48H_8D = "0";
            homeSupport48H_30D = "0";
            homeSupport48H_60D = "0";
            homeSupport48H_90D = "0";

            cb_HomeSupport48H_1d.setChecked(homeSupport48H_1D.equals("1"));
            cb_HomeSupport48H_3d.setChecked(homeSupport48H_3D.equals("1"));
            cb_HomeSupport48H_8d.setChecked(homeSupport48H_8D.equals("1"));
            cb_HomeSupport48H_30d.setChecked(homeSupport48H_30D.equals("1"));
            cb_HomeSupport48H_60d.setChecked(homeSupport48H_60D.equals("1"));
            cb_HomeSupport48H_90d.setChecked(homeSupport48H_90D.equals("1"));
        }
        if (llay_MaternalComplications_checkbox.getVisibility() == View.VISIBLE) {

            maternal_bleeed = "0";
            maternal_seizure = "0";
            maternal_infection = "0";
            maternal_proLongedLabor = "0";
            maternal_obstructedLabor = "0";
            maternal_pprm = "0";


            cb_Maternal_Bleeding.setChecked(maternal_bleeed.equals("1"));
            cb_Maternal_Seizure.setChecked(maternal_seizure.equals("1"));
            cb_Maternal_Infection.setChecked(maternal_infection.equals("1"));
            cb_Maternal_ProlongedLabor.setChecked(maternal_proLongedLabor.equals("1"));
            cb_Maternal_ObstructedLabor.setChecked(maternal_obstructedLabor.equals("1"));
            cb_Maternal_PPRM.setChecked(maternal_pprm.equals("1"));
        }
        if (llay_NewbornComplications_checkbox.getVisibility() == View.VISIBLE) {
            nBorn_Aspyxia = "0";
            nBorn_Sepsis = "0";
            nBorn_HypoThermai = "0";
            nBorn_HyperThermai = "0";
            nBorn_noSuckling = "0";
            nBorn_Jaundices = "0";


            cb_NBron_Asphyxia.setChecked(nBorn_Aspyxia.equals("1"));
            cb_NBron_Sepsis.setChecked(nBorn_Sepsis.equals("1"));
            cb_NBron_Hypothermia.setChecked(nBorn_HypoThermai.equals("1"));
            cb_NBron_Hyperthermia.setChecked(nBorn_HyperThermai.equals("1"));
            cb_NBron_NoSuckling.setChecked(nBorn_noSuckling.equals("1"));
            cb_NBron_Jaundice.setChecked(nBorn_Jaundices.equals("1"));
        }
        if (llay_ChildhoodIllnesses_checkbox.getVisibility() == View.VISIBLE) {
            child_Diarrhea = "0";
            child_Pneumonia = "0";
            child_Fever = "0";
            child_CerebralPalsy = "0";

            cb_CIllness_Diarrhea.setChecked(child_Diarrhea.equals("1"));
            cb_CIllness_Pneumonia.setChecked(child_Pneumonia.equals("1"));
            cb_CIllness_Fever.setChecked(child_Fever.equals("1"));
            cb_CIllness_Cerebral_Palsy.setChecked(child_CerebralPalsy.equals("1"));
        }
        if (llay_Immunizations_checkbox.getVisibility() == View.VISIBLE) {

            immu_Polio = "0";
            immu_BCG = "0";
            immu_Measles = "0";
            immu_DPT_HIB = "0";
            immu_Lotta = "0";
            immU_Other = "0";


            cb_Immu_Polio.setChecked(immu_Polio.equals("1"));
            cb_Immu_BCG.setChecked(immu_BCG.equals("1"));
            cb_Immu_Measles.setChecked(immu_Measles.equals("1"));
            cb_Immu_DPT_HIB.setChecked(immu_DPT_HIB.equals("1"));
            cb_Immu_Lotta.setChecked(immu_Lotta.equals("1"));
            cb_Immu_Other.setChecked(immU_Other.equals("1"));
        }
        if (llay_FPCounsel_checkbox.getVisibility() == View.VISIBLE) {

            fpCounsel_MaleCondom = "0";
            fpCounsel_FemaleCondom = "0";
            fpCounsel_Pill = "0";
            fpCounsel_Depo = "0";
            fpCounsel_LongParmanent = "0";
            fpCounsel_NoMethod = "0";


            cb_FPCounsel_Male_Condoms.setChecked(fpCounsel_MaleCondom.equals("1"));
            cb_FPCounsel_Female_Condoms.setChecked(fpCounsel_FemaleCondom.equals("1"));
            cb_FPCounsel_Pills.setChecked(fpCounsel_Pill.equals("1"));
            cb_FPCounsel_Depo.setChecked(fpCounsel_Depo.equals("1"));
            cb_FPCounsel_LongParmanen.setChecked(fpCounsel_LongParmanent.equals("1"));
            cb_FPCounsel_NoMethod.setChecked(fpCounsel_NoMethod.equals("1"));
        }


        /** todo: do it later
         private String cropCode, loanSource;
         private String loanAMT, animalCode;
         private String leadCode;*/

    }

    /**
     * This method will bring to the service page again
     */

    private void goToServicePage() {
        btnGoToService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iService = new Intent(ServiceSpecification.this, ServiceActivity.class);
                iService.putExtra(KEY.DIR_CLASS_NAME_KEY, "ServiceSpecification");
                iService.putExtra(KEY.COUNTRY_ID, srvData.getC_code());
                iService.putExtra(KEY.AWARD_CODE, srvData.getAward_code());
                iService.putExtra(KEY.AWARD_NAME, srvData.getAwardName());
                iService.putExtra(KEY.DONOR_CODE, srvData.getDonor_code());
                iService.putExtra(KEY.CRITERIA_NAME, srvData.getCriteriaName());
                iService.putExtra(KEY.CRITERIA_CODE, srvData.getCriteriaId());
                iService.putExtra(KEY.SERVICE_CENTER_CODE, srvData.getServiceCenterCode());
                iService.putExtra(KEY.SERVICE_CENTER_NAME, srvData.getTemServiceCenterName());
                iService.putExtra(KEY.SERVICE_DATE, srvData.getTemServiceDate());
                iService.putExtra(KEY.SERVICE_MONTH_CODE, srvData.getTemIdServiceMonth());
                iService.putExtra(KEY.SERVICE_MONTH_NAME, srvData.getTemStrSrvMonth());

                iService.putExtra(KEY.GROUP_NAME, srvData.getTemStrGroup());
                iService.putExtra(KEY.GROUP_CODE, srvData.getTemIdGroup());
                iService.putExtra(KEY.GROUP_CATEGORY_CODE, srvData.getTemIdGroupCat());
                iService.putExtra(KEY.GROUP_CATEGORY_NAME, srvData.getTemStrGroupCat());
                finish();
                startActivity(iService);
            }
        });
    }

    /**
     * this method save the Service Specific data & as well as Service data
     */

    private void saveData() {

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String EntryBy = "";
                String EntryDate = "";

                try {
                    EntryBy = getStaffID();
                    EntryDate = getDateTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                /**
                 * Here save the Service Record
                 */

                saveServiceDataProcess(EntryBy, EntryDate);

                saveServiceSpecificDataProcess(EntryBy, EntryDate);

                Toast.makeText(CONTEXT, "Save Successfully", Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void saveServiceSpecificDataProcess(String entryBy, String entryDate) {

        pncVisit2D = cb_PNCVisit_2D.isChecked() ? "1" : "0";
        pncVisit1W = cb_PNCVisit_1W.isChecked() ? "1" : "0";
        pncVisit6W = cb_PNCVisit_6W.isChecked() ? "1" : "0";


        deliveryStaff_1 = rbtn_DeliveryStaff_1.isChecked() ? "1" : "0";
        deliveryStaff_2 = rbtn_DeliveryStaff_2.isChecked() ? "1" : "0";
        deliveryStaff_3 = rbtn_DeliveryStaff_3.isChecked() ? "1" : "0";


        homeSupport24H_1D = cb_HomeSupport24H_1d.isChecked() ? "1" : "0";
        homeSupport24H_2D = cb_HomeSupport24H_2d.isChecked() ? "1" : "0";
        homeSupport24H_3D = cb_HomeSupport24H_3d.isChecked() ? "1" : "0";
        homeSupport24H_8D = cb_HomeSupport24H_8d.isChecked() ? "1" : "0";
        homeSupport24H_14D = cb_HomeSupport24H_14d.isChecked() ? "1" : "0";
        homeSupport24H_21D = cb_HomeSupport24H_21d.isChecked() ? "1" : "0";
        homeSupport24H_30D = cb_HomeSupport24H_30d.isChecked() ? "1" : "0";
        homeSupport24H_60D = cb_HomeSupport24H_60d.isChecked() ? "1" : "0";
        homeSupport24H_90D = cb_HomeSupport24H_90d.isChecked() ? "1" : "0";


        homeSupport48H_1D = cb_HomeSupport48H_1d.isChecked() ? "1" : "0";
        homeSupport48H_3D = cb_HomeSupport48H_3d.isChecked() ? "1" : "0";
        homeSupport48H_8D = cb_HomeSupport48H_8d.isChecked() ? "1" : "0";
        homeSupport48H_30D = cb_HomeSupport48H_30d.isChecked() ? "1" : "0";
        homeSupport48H_60D = cb_HomeSupport48H_60d.isChecked() ? "1" : "0";
        homeSupport48H_90D = cb_HomeSupport48H_90d.isChecked() ? "1" : "0";


        maternal_bleeed = cb_Maternal_Bleeding.isChecked() ? "1" : "0";
        maternal_seizure = cb_Maternal_Seizure.isChecked() ? "1" : "0";
        maternal_infection = cb_Maternal_Infection.isChecked() ? "1" : "0";
        maternal_proLongedLabor = cb_Maternal_ProlongedLabor.isChecked() ? "1" : "0";
        maternal_obstructedLabor = cb_Maternal_ObstructedLabor.isChecked() ? "1" : "0";
        maternal_pprm = cb_Maternal_PPRM.isChecked() ? "1" : "0";


        nBorn_Aspyxia = cb_NBron_Asphyxia.isChecked() ? "1" : "0";
        nBorn_Sepsis = cb_NBron_Sepsis.isChecked() ? "1" : "0";
        nBorn_HypoThermai = cb_NBron_Hypothermia.isChecked() ? "1" : "0";
        nBorn_HyperThermai = cb_NBron_Hyperthermia.isChecked() ? "1" : "0";
        nBorn_noSuckling = cb_NBron_NoSuckling.isChecked() ? "1" : "0";
        nBorn_Jaundices = cb_NBron_Jaundice.isChecked() ? "1" : "0";


        child_Diarrhea = cb_CIllness_Diarrhea.isChecked() ? "1" : "0";
        child_Pneumonia = cb_CIllness_Pneumonia.isChecked() ? "1" : "0";
        child_Fever = cb_CIllness_Fever.isChecked() ? "1" : "0";
        child_CerebralPalsy = cb_CIllness_Cerebral_Palsy.isChecked() ? "1" : "0";


        immu_Polio = cb_Immu_Polio.isChecked() ? "1" : "0";
        immu_BCG = cb_Immu_BCG.isChecked() ? "1" : "0";
        immu_Measles = cb_Immu_Measles.isChecked() ? "1" : "0";
        immu_DPT_HIB = cb_Immu_DPT_HIB.isChecked() ? "1" : "0";
        immu_Lotta = cb_Immu_Lotta.isChecked() ? "1" : "0";
        immU_Other = cb_Immu_Other.isChecked() ? "1" : "0";


        fpCounsel_MaleCondom = cb_FPCounsel_Male_Condoms.isChecked() ? "1" : "0";
        fpCounsel_FemaleCondom = cb_FPCounsel_Female_Condoms.isChecked() ? "1" : "0";
        fpCounsel_Pill = cb_FPCounsel_Pills.isChecked() ? "1" : "0";
        fpCounsel_Depo = cb_FPCounsel_Depo.isChecked() ? "1" : "0";
        fpCounsel_LongParmanent = cb_FPCounsel_LongParmanen.isChecked() ? "1" : "0";
        fpCounsel_NoMethod = cb_FPCounsel_NoMethod.isChecked() ? "1" : "0";


        /**
         *
         *
         */

        if (tvDateCompFeeding.getVisibility() == View.VISIBLE) {
            dateComFeed = tvDateCompFeeding.getText().toString();
        }
        if (tvDateANCVisit.getVisibility() == View.VISIBLE) {
            dateAncVisit = tvDateANCVisit.getText().toString();
        }

        SQLServerSyntaxGenerator srvSpecfic = new SQLServerSyntaxGenerator();

        srvSpecfic.setAdmCountryCode(srvData.getC_code());
        srvSpecfic.setAdmDonorCode(srvData.getDonor_code());
        srvSpecfic.setAdmAwardCode(srvData.getAward_code());
        srvSpecfic.setLayR1ListCode(srvData.getDistrictCode());
        srvSpecfic.setLayR2ListCode(srvData.getUpazillaCode());
        srvSpecfic.setLayR3ListCode(srvData.getUnitCode());
        srvSpecfic.setLayR4ListCode(srvData.getVillageCode());
        srvSpecfic.setHHID(srvData.getHHID());
        srvSpecfic.setMemID(srvData.getMemberId());
        srvSpecfic.setProgCode(srvData.getProgram_code());
        srvSpecfic.setSrvCode(srvData.getService_code());
        srvSpecfic.setOpCode("2");
        srvSpecfic.setOpMonthCode(srvData.getOpMontheCode());
        srvSpecfic.setSrvCenterCode(srvData.getServiceCenterCode());
        srvSpecfic.setFDPCode(srvData.getFPDCode());
        srvSpecfic.setSrvStatus(srvData.getServiceStatusCode());
        srvSpecfic.setBabyStatus(babyStatus);
        srvSpecfic.setGMPAttendace(gmpAttendence);
        srvSpecfic.setWeightStatus(weightStatus);
        srvSpecfic.setNutAttendance(nutAttendance);
        srvSpecfic.setVitA_Under5(vitaUnder5);
        srvSpecfic.setExclusive_CurrentlyBF(exclCurrentLybf);
        srvSpecfic.setDateCompFeeding(dateComFeed);
        srvSpecfic.setCMAMRef(camRef);
        srvSpecfic.setCMAMAdd(camAdd);
        srvSpecfic.setANCVisit(dateAncVisit);
        srvSpecfic.setPNCVisit_2D(pncVisit2D);
        srvSpecfic.setPNCVisit_1W(pncVisit1W);
        srvSpecfic.setPNCVisit_6W(pncVisit6W);
        srvSpecfic.setDeliveryStaff_1(deliveryStaff_1);
        srvSpecfic.setDeliveryStaff_2(deliveryStaff_2);
        srvSpecfic.setDeliveryStaff_3(deliveryStaff_3);
        srvSpecfic.setHomeSupport24H_1d(homeSupport24H_1D);
        srvSpecfic.setHomeSupport24H_2d(homeSupport24H_2D);
        srvSpecfic.setHomeSupport24H_3d(homeSupport24H_3D);
        srvSpecfic.setHomeSupport24H_8d(homeSupport24H_8D);
        srvSpecfic.setHomeSupport24H_14d(homeSupport24H_14D);
        srvSpecfic.setHomeSupport24H_21d(homeSupport24H_21D);
        srvSpecfic.setHomeSupport24H_30d(homeSupport24H_30D);
        srvSpecfic.setHomeSupport24H_60d(homeSupport24H_60D);
        srvSpecfic.setHomeSupport24H_90d(homeSupport24H_90D);
        srvSpecfic.setHomeSupport48H_1d(homeSupport48H_1D);
        srvSpecfic.setHomeSupport48H_3d(homeSupport48H_3D);
        srvSpecfic.setHomeSupport48H_8d(homeSupport48H_8D);
        srvSpecfic.setHomeSupport48H_30d(homeSupport48H_30D);
        srvSpecfic.setHomeSupport48H_60d(homeSupport48H_60D);
        srvSpecfic.setHomeSupport48H_90d(homeSupport48H_90D);
        srvSpecfic.setMaternal_Bleeding(maternal_bleeed);
        srvSpecfic.setMaternal_Seizure(maternal_seizure);
        srvSpecfic.setMaternal_Infection(maternal_infection);
        srvSpecfic.setMaternal_ProlongedLabor(maternal_proLongedLabor);
        srvSpecfic.setMaternal_ObstructedLabor(maternal_obstructedLabor);
        srvSpecfic.setMaternal_PPRM(maternal_pprm);
        srvSpecfic.setNBorn_Asphyxia(nBorn_Aspyxia);
        srvSpecfic.setNBorn_Hyperthermia(nBorn_Sepsis);
        srvSpecfic.setNBorn_Hypothermia(nBorn_HypoThermai);
        srvSpecfic.setNBorn_Hyperthermia(nBorn_HyperThermai);
        srvSpecfic.setNBorn_NoSuckling(nBorn_noSuckling);
        srvSpecfic.setNBorn_Jaundice(nBorn_Jaundices);
        srvSpecfic.setChild_Diarrhea(child_Diarrhea);
        srvSpecfic.setChild_Pneumonia(child_Pneumonia);
        srvSpecfic.setChild_Fever(child_Fever);
        srvSpecfic.setChild_CerebralPalsy(child_CerebralPalsy);
        srvSpecfic.setImmu_Polio(immu_Polio);
        srvSpecfic.setImmu_BCG(immu_BCG);
        srvSpecfic.setImmu_Measles(immu_Measles);
        srvSpecfic.setImmu_DPT_HIB(immu_DPT_HIB);
        srvSpecfic.setImmu_Lotta(immu_Lotta);
        srvSpecfic.setImmu_Other(immU_Other);
        srvSpecfic.setFPCounsel_MaleCondom(fpCounsel_MaleCondom);
        srvSpecfic.setFPCounsel_FemaleCondom(fpCounsel_FemaleCondom);
        srvSpecfic.setFPCounsel_Pill(fpCounsel_Pill);
        srvSpecfic.setFPCounsel_Depo(fpCounsel_Depo);
        srvSpecfic.setFPCounsel_LongParmanent(fpCounsel_LongParmanent);
        srvSpecfic.setFPCounsel_NoMethod(fpCounsel_NoMethod);
        srvSpecfic.setCropCode(cropCode);
        srvSpecfic.setLoanSource(loanSource);
        srvSpecfic.setLoanAMT(loanAMT);
        srvSpecfic.setAnimalCode(animalCode);
        srvSpecfic.setLeadCode(leadCode);
        srvSpecfic.setEntryBy(entryBy);
        srvSpecfic.setEntryDate(entryDate);


        String memId15Digit = "";
        memId15Digit = srvData.getDistrictCode() + srvData.getUpazillaCode() + srvData.getUnitCode() + srvData.getVillageCode()
                + srvData.getHHID() + srvData.getMemberId();


        /**
         * if member exits in Service Specific Table
         */
        if (sqlH.isDataExitsInServiceSpecificTable(srvData.getC_code(), srvData.getDonor_code(), srvData.getAward_code()
                , srvData.getProgram_code(), srvData.getService_code(), "2", srvData.getOpMontheCode(), srvData.getServiceCenterCode()
                , srvData.getFPDCode(), memId15Digit)) {
            sqlH.uploadIntoServiceSpecificTable(srvData.getC_code(), srvData.getDonor_code(), srvData.getAward_code()
                    , srvData.getDistrictCode(), srvData.getUpazillaCode(), srvData.getUnitCode(), srvData.getVillageCode(),
                    srvData.getHHID(), srvData.getMemberId(), srvData.getProgram_code(), srvData.getService_code(), "2"
                    , srvData.getOpMontheCode(), srvData.getServiceCenterCode(), srvData.getFPDCode()
                    , babyStatus, gmpAttendence, weightStatus, nutAttendance, vitaUnder5, exclCurrentLybf, dateComFeed,
                    camRef, camAdd
                    , dateAncVisit, pncVisit2D, pncVisit1W, pncVisit6W
                    , deliveryStaff_1, deliveryStaff_2, deliveryStaff_3, homeSupport24H_1D
                    , homeSupport24H_2D, homeSupport24H_3D, homeSupport24H_8D, homeSupport24H_14D, homeSupport24H_21D, homeSupport24H_30D
                    , homeSupport24H_60D, homeSupport24H_90D, homeSupport48H_1D, homeSupport48H_3D, homeSupport48H_8D, homeSupport48H_30D
                    , homeSupport48H_60D, homeSupport48H_90D, maternal_bleeed, maternal_seizure, maternal_infection, maternal_proLongedLabor
                    , maternal_obstructedLabor, maternal_pprm, nBorn_Aspyxia, nBorn_Sepsis, nBorn_HypoThermai, nBorn_HyperThermai
                    , nBorn_noSuckling, nBorn_Jaundices, child_Diarrhea, child_Pneumonia, child_Fever, child_CerebralPalsy
                    , immu_Polio, immu_BCG, immu_Measles, immu_DPT_HIB, immu_Lotta, immU_Other, fpCounsel_MaleCondom, fpCounsel_FemaleCondom
                    , fpCounsel_Pill, fpCounsel_Depo, fpCounsel_LongParmanent, fpCounsel_NoMethod, cropCode, loanSource, loanAMT, animalCode
                    , leadCode, entryBy, entryDate);


            sqlH.insertIntoUploadTable(srvSpecfic.updoadIntoSrvSpecific());
        } else {

            sqlH.addServiceSpecificTable(srvData.getC_code(), srvData.getDonor_code(), srvData.getAward_code()
                    , srvData.getDistrictCode(), srvData.getUpazillaCode(), srvData.getUnitCode(), srvData.getVillageCode(),
                    srvData.getHHID(), srvData.getMemberId(), srvData.getProgram_code(), srvData.getService_code(), "2"
                    , srvData.getOpMontheCode(), srvData.getServiceCenterCode(), srvData.getFPDCode(), srvData.getServiceStatusCode()
                    , babyStatus, gmpAttendence, weightStatus, nutAttendance, vitaUnder5, exclCurrentLybf, dateComFeed,
                    camRef, camAdd
                    , dateAncVisit, pncVisit2D, pncVisit1W, pncVisit6W
                    , deliveryStaff_1, deliveryStaff_2, deliveryStaff_3, homeSupport24H_1D
                    , homeSupport24H_2D, homeSupport24H_3D, homeSupport24H_8D, homeSupport24H_14D, homeSupport24H_21D, homeSupport24H_30D
                    , homeSupport24H_60D, homeSupport24H_90D, homeSupport48H_1D, homeSupport48H_3D, homeSupport48H_8D, homeSupport48H_30D
                    , homeSupport48H_60D, homeSupport48H_90D, maternal_bleeed, maternal_seizure, maternal_infection, maternal_proLongedLabor
                    , maternal_obstructedLabor, maternal_pprm, nBorn_Aspyxia, nBorn_Sepsis, nBorn_HypoThermai, nBorn_HyperThermai
                    , nBorn_noSuckling, nBorn_Jaundices, child_Diarrhea, child_Pneumonia, child_Fever, child_CerebralPalsy
                    , immu_Polio, immu_BCG, immu_Measles, immu_DPT_HIB, immu_Lotta, immU_Other, fpCounsel_MaleCondom, fpCounsel_FemaleCondom
                    , fpCounsel_Pill, fpCounsel_Depo, fpCounsel_LongParmanent, fpCounsel_NoMethod, cropCode, loanSource, loanAMT, animalCode
                    , leadCode, entryBy, entryDate);


            sqlH.insertIntoUploadTable(srvSpecfic.insertIntoSrvSpecificTable());
        }


        /**
         * if data data exits the update method will be execute  other wise  insert method will be execute
         */


    }

    private void saveServiceDataProcess(String entryBy, String entryDate) {


        /**
         * reset the date inService Date Column
         */
        srvData.setServiceDTCode(srvData.getTemServiceDate());
        srvData.setServiceStatusCode("O");

        /**
         * get last Serviced Date
         */
        String lastServicedDate = sqlH.getLastServiceDate(srvData);

        long diff = 0;
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        SQLServerSyntaxGenerator serviceTable = new SQLServerSyntaxGenerator();
        serviceTable.setAdmCountryCode(srvData.getC_code());
        serviceTable.setAdmDonorCode(srvData.getDonor_code());
        serviceTable.setAdmAwardCode(srvData.getAward_code());
        serviceTable.setLayR1ListCode(srvData.getDistrictCode());
        serviceTable.setLayR2ListCode(srvData.getUpazillaCode());
        serviceTable.setLayR3ListCode(srvData.getUnitCode());
        serviceTable.setLayR4ListCode(srvData.getVillageCode());
        serviceTable.setHHID(srvData.getHHID());
        serviceTable.setMemID(srvData.getMemberId());
        serviceTable.setProgCode(srvData.getProgram_code());
        serviceTable.setSrvCode(srvData.getService_code());
        serviceTable.setOpCode(srvData.getOpCode());
        serviceTable.setOpMonthCode(srvData.getOpMontheCode());
        serviceTable.setSrvSL(srvData.getServiceSLCode());
        serviceTable.setSrvCenterCode(srvData.getServiceCenterCode());
        serviceTable.setSrvDT(srvData.getServiceDTCode());
        serviceTable.setSrvStatus(srvData.getServiceStatusCode());
        serviceTable.setFDPCode(srvData.getFPDCode());
        serviceTable.setEntryBy(entryBy);
        serviceTable.setEntryDate(entryDate);
        serviceTable.setDistFlag(srvData.getDistFlag());
        /**
         * if the man get service more than one time
         */
        if (!lastServicedDate.equals("")) {
            try {
                Date date1 = myFormat.parse(srvData.getTemServiceDate());
                Date date2 = myFormat.parse(lastServicedDate);
                diff = date2.getTime() - date1.getTime();

            } catch (ParseException e) {
                e.printStackTrace();
            }

            /**
             * if the last serviced Date & present Service date are not Same
             * than the data will be inserted
             * A man cannot get 2 service in the same day
             */
            if (diff != 0) {
                /** check the data exit for Service                                     *                                          */
                if (sqlH.isMemberExitsSrvTable(srvData)) {
                    /** update for local device */
                    sqlH.updateMemberIntoServiceTable(srvData, entryBy, entryDate);

                    /** update Syntax for upload in Sync process */
                    sqlH.insertIntoUploadTable(serviceTable.updateInToSrvTable());
                }
                else {
                    /** insert for local device */
                    sqlH.addMemberIntoServiceTable(srvData, entryBy, entryDate);
                    /** insert for upload in Sync process */
                    sqlH.insertIntoUploadTable(serviceTable.insertInToSrvTable());
                }

                /**                                         * min Srv Date                                         */
                ServiceActivity.saveServiceMinumDate(srvData, srvData.getServiceDTCode(), serviceTable,sqlH);

                /**                                         * max date                                         */
                ServiceActivity.saveServiceMaxDate(srvData, srvData.getServiceDTCode(), serviceTable,sqlH);



            }
        } /** if the man get service for first time */
        else {
            if (sqlH.isMemberExitsSrvTable(srvData)) {
                /** update for local device */
                sqlH.updateMemberIntoServiceTable(srvData, entryBy, entryDate);

                /** update Syntax for upload in Sync process */
                sqlH.insertIntoUploadTable(serviceTable.updateInToSrvTable());
            }
            else {
                /** insert for local device */
                sqlH.addMemberIntoServiceTable(srvData, entryBy, entryDate);
                /** insert for upload in Sync process */
                sqlH.insertIntoUploadTable(serviceTable.insertInToSrvTable());
            }

            /**                                         * min Srv Date                                         */
            ServiceActivity.saveServiceMinumDate(srvData, srvData.getServiceDTCode(), serviceTable,sqlH);

            /**                                         * max date                                         */
            ServiceActivity.saveServiceMaxDate(srvData, srvData.getServiceDTCode(), serviceTable,sqlH);

        }
    }

    private void showViewInPLW() {
        setBabyStatusVisibility(View.VISIBLE);
        setDateANCVisitVisibility(View.VISIBLE);
        setFacilityPostnatalVisibility(View.VISIBLE);
        setHomeSupport24HVisibility(View.VISIBLE);
        setDeliveryStaffVisibility(View.VISIBLE);
        setHomeSupport48HVisibility(View.VISIBLE);
        setMaternalComplicationsVisibility(View.VISIBLE);
        setNewbornComplicationsVisibility(View.VISIBLE);
        setChildhoodIllnessesVisibility(View.VISIBLE);
        setImmunizationsVisibility(View.VISIBLE);
        setFPCounselVisibility(View.VISIBLE);


    }

    private void showViewInCA_N_CU() {

        setBabyStatusVisibility(View.VISIBLE);
        setGMPAttendanceVisibility(View.VISIBLE);
        setWeightStatusVisibility(View.VISIBLE);
        setNutAttendanceVisibility(View.VISIBLE);
        setVitA_Under5Visibility(View.VISIBLE);
        setExclusive_CurrentlyBFVisibility(View.VISIBLE);
        setDateCompFeedingVisibility(View.VISIBLE);
        setCMAMRefVisibility(View.VISIBLE);
        setCMAMAddVisibility(View.VISIBLE);

    }



    private void showViewInMG() {

        setAnimalTypeVisibility(View.VISIBLE);
        setCropTypeVisibility(View.VISIBLE);
        setLoanAMTVisibility(View.VISIBLE);
        setLeadPositionVisibility(View.VISIBLE);
        setLoanSourceVisibility(View.VISIBLE);

    }

    private void showViewInIG() {


        setCropTypeVisibility(View.VISIBLE);
        setLoanAMTVisibility(View.VISIBLE);
        setLeadPositionVisibility(View.VISIBLE);
        setLoanSourceVisibility(View.VISIBLE);

    }

    private void setDataInTextView(String id15Digit) {

        tvId.setText(id15Digit);
        tvName.setText(srvData.getHh_mm_name());
        tvAward.setText(srvData.getAwardName());
        tvCriteria.setText(srvData.getCriteriaName());
        tvServiceMonth.setText(srvData.getTemIdServiceMonth());
        String GroupName = sqlH.getComunityGroupNameFromServiceTable(srvData.getC_code(), srvData.getDonor_code()
                , srvData.getAward_code(), srvData.getDistrictCode(), srvData.getUpazillaCode(), srvData.getUnitCode()
                , srvData.getVillageCode(), srvData.getHHID(), srvData.getMemberId(), srvData.getProgram_code()
                , srvData.getService_code());
        tvGroupName.setText(GroupName);
    }

    private void initalize() {
        viewReference();
        setAllViewsGone();

        sqlH = new SQLiteHandler(this);

    }

    /**
     * By default Despairs all Views
     */


    private void setAllViewsGone() {
        setBabyStatusVisibility(View.GONE);
        setGMPAttendanceVisibility(View.GONE);
        setWeightStatusVisibility(View.GONE);
        setNutAttendanceVisibility(View.GONE);
        setVitA_Under5Visibility(View.GONE);
        setExclusive_CurrentlyBFVisibility(View.GONE);
        setDateCompFeedingVisibility(View.GONE);
        setCMAMRefVisibility(View.GONE);
        setCMAMAddVisibility(View.GONE);

        setDateANCVisitVisibility(View.GONE);
        setFacilityPostnatalVisibility(View.GONE);
        setHomeSupport24HVisibility(View.GONE);
        setDeliveryStaffVisibility(View.GONE);
        setHomeSupport48HVisibility(View.GONE);
        setMaternalComplicationsVisibility(View.GONE);
        setNewbornComplicationsVisibility(View.GONE);
        setChildhoodIllnessesVisibility(View.GONE);
        setImmunizationsVisibility(View.GONE);
        setFPCounselVisibility(View.GONE);

        setAnimalTypeVisibility(View.GONE);
        setCropTypeVisibility(View.GONE);
        setLoanAMTVisibility(View.GONE);
        setLeadPositionVisibility(View.GONE);
        setLoanSourceVisibility(View.GONE);


    }


    private void viewReference() {
        spBabyStatus = (Spinner) findViewById(R.id.srv_spec_spBabyStatus);
        lbBabyStatus = (TextView) findViewById(R.id.tv_srvSpc_BabyStatus);

        spGMPAttendance = (Spinner) findViewById(R.id.srv_spec_spGMPAttendance);
        lbGMPAttendance = (TextView) findViewById(R.id.tv_srvSpc_GMPAttendance);

        spWeightStatus = (Spinner) findViewById(R.id.srv_spec_spWeightStatus);
        lbWeightStatus = (TextView) findViewById(R.id.tv_srvSpc_WeightStatus);

        spNutAttendance = (Spinner) findViewById(R.id.srv_spec_spNutAttendance);
        lbNutAttendance = (TextView) findViewById(R.id.tv_srvSpc_NutAttendance);

        spVitA_Under5 = (Spinner) findViewById(R.id.srv_spec_spVitA_Under5);
        lbVitA_Under5 = (TextView) findViewById(R.id.tv_srvSpc_VitA_Under5);

        spExclusive_CurrentlyBF = (Spinner) findViewById(R.id.srv_spec_spExclusive_CurrentlyBF);
        lbExclusive_CurrentlyBF = (TextView) findViewById(R.id.tv_srvSpc_Exclusive_CurrentlyBF);

        lbDateCompFeeding = (TextView) findViewById(R.id.tv_srvSpc_DateCompFeeding);
        tvDateCompFeeding = (TextView) findViewById(R.id.srv_spec_DateCompFeeding);
        rlayout_DateCompFeeding = (RelativeLayout) findViewById(R.id.rl_DateCompFeed);

        spCMAMRef = (Spinner) findViewById(R.id.srv_spec_spCMAMRef);
        lbCMAMRef = (TextView) findViewById(R.id.tv_srvSpc_CMAMRef);

        spCMAMAdd = (Spinner) findViewById(R.id.srv_spec_spCMAMAdd);
        lbCMAMAdd = (TextView) findViewById(R.id.tv_srvSpc_CMAMAdd);

        /**
         * new
         */
        lbDateANCVisit = (TextView) findViewById(R.id.tv_srvSpc_DateANCVisit);
        tvDateANCVisit = (TextView) findViewById(R.id.srv_spec_DateANCVisit);
        rlayout_DateANCVisit = (RelativeLayout) findViewById(R.id.rl_srv_spec_DateANCVisit);


        lbFacilityPostnatal = (TextView) findViewById(R.id.tv_srvSpc_FacilityPostnatal);
        llayout_FacilityPostnatal_cb_group = (LinearLayout) findViewById(R.id.llayout_FacilityPostnatal_cb_group);
        cb_PNCVisit_2D = (CheckBox) findViewById(R.id.cb_PNCVisit_2D);
        cb_PNCVisit_1W = (CheckBox) findViewById(R.id.cb_PNCVisit_1W);
        cb_PNCVisit_6W = (CheckBox) findViewById(R.id.cb_PNCVisit_6W);


        lbDeliveryStaff = (TextView) findViewById(R.id.tv_srvSpc_DeliveryStaff);
        rg_DeliveryStaff = (RadioGroup) findViewById(R.id.rg_srvSpc__DeliveryStaff);
        rbtn_DeliveryStaff_1 = (RadioButton) findViewById(R.id.rbtn_srvSpc_DeliveryStaff_1);
        rbtn_DeliveryStaff_2 = (RadioButton) findViewById(R.id.rbtn_srvSpc_DeliveryStaff_2);
        rbtn_DeliveryStaff_3 = (RadioButton) findViewById(R.id.rbtn_srvSpc_DeliveryStaff_3);

        lbHomeSupport24H = (TextView) findViewById(R.id.tv_srvSpc_HomeSupport24H);
        llay_HomeSupport24H_checkbox = (LinearLayout) findViewById(R.id.llay_HomeSupport24H_checkbox);
        cb_HomeSupport24H_1d = (CheckBox) findViewById(R.id.cb_srvSpc_HomeSupport24H_1d);
        cb_HomeSupport24H_2d = (CheckBox) findViewById(R.id.cb_srvSpc_HomeSupport24H_2d);
        cb_HomeSupport24H_3d = (CheckBox) findViewById(R.id.cb_srvSpc_HomeSupport24H_3d);
        cb_HomeSupport24H_8d = (CheckBox) findViewById(R.id.cb_srvSpc_HomeSupport24H_8d);
        cb_HomeSupport24H_14d = (CheckBox) findViewById(R.id.cb_srvSpc_HomeSupport24H_14d);
        cb_HomeSupport24H_21d = (CheckBox) findViewById(R.id.cb_srvSpc_HomeSupport24H_21d);
        cb_HomeSupport24H_30d = (CheckBox) findViewById(R.id.cb_srvSpc_HomeSupport24H_30d);
        cb_HomeSupport24H_60d = (CheckBox) findViewById(R.id.cb_srvSpc_HomeSupport24H_60d);
        cb_HomeSupport24H_90d = (CheckBox) findViewById(R.id.cb_srvSpc_HomeSupport24H_90d);


        lbHomeSupport48H = (TextView) findViewById(R.id.tv_srvSpc_HomeSupport48H);
        llay_HomeSupport48H_checkbox = (LinearLayout) findViewById(R.id.llay_HomeSupport48H_checkbox);
        cb_HomeSupport48H_1d = (CheckBox) findViewById(R.id.cb_srvSpc_HomeSupport48H_1d);
        cb_HomeSupport48H_3d = (CheckBox) findViewById(R.id.cb_srvSpc_HomeSupport48H_3d);
        cb_HomeSupport48H_8d = (CheckBox) findViewById(R.id.cb_srvSpc_HomeSupport48H_8d);
        cb_HomeSupport48H_30d = (CheckBox) findViewById(R.id.cb_srvSpc_HomeSupport48H_30d);
        cb_HomeSupport48H_60d = (CheckBox) findViewById(R.id.cb_srvSpc_HomeSupport48H_60d);
        cb_HomeSupport48H_90d = (CheckBox) findViewById(R.id.cb_srvSpc_HomeSupport48H_90d);

        lbMaternalComplications = (TextView) findViewById(R.id.tv_srvSpc_MaternalComplications);
        llay_MaternalComplications_checkbox = (LinearLayout) findViewById(R.id.llay_MaternalComplications_checkbox);

        cb_Maternal_Bleeding = (CheckBox) findViewById(R.id.cb_srvSpc_Maternal_Bleeding);
        cb_Maternal_Seizure = (CheckBox) findViewById(R.id.cb_srvSpc_Maternal_Seizure);
        cb_Maternal_Infection = (CheckBox) findViewById(R.id.cb_srvSpc_Maternal_Infection);
        cb_Maternal_ProlongedLabor = (CheckBox) findViewById(R.id.cb_srvSpc_Maternal_ProlongedLabor);
        cb_Maternal_ObstructedLabor = (CheckBox) findViewById(R.id.cb_srvSpc_Maternal_ObstructedLabor);
        cb_Maternal_PPRM = (CheckBox) findViewById(R.id.cb_srvSpc_Maternal_PPRM);


        lbNewbornComplications = (TextView) findViewById(R.id.tv_srvSpc_NewbornComplications);
        llay_NewbornComplications_checkbox = (LinearLayout) findViewById(R.id.llay_NewbornComplications_checkbox);

        cb_NBron_Asphyxia = (CheckBox) findViewById(R.id.cb_srvSpc_NBron_Asphyxia);
        cb_NBron_Sepsis = (CheckBox) findViewById(R.id.cb_srvSpc_NBron_Sepsis);
        cb_NBron_Hypothermia = (CheckBox) findViewById(R.id.cb_srvSpc_NBron_Hypothermia);
        cb_NBron_Hyperthermia = (CheckBox) findViewById(R.id.cb_srvSpc_NBron_Hyperthermia);
        cb_NBron_NoSuckling = (CheckBox) findViewById(R.id.cb_srvSpc_NBron_NoSuckling);
        cb_NBron_Jaundice = (CheckBox) findViewById(R.id.cb_srvSpc_NBron_Jaundice);


        lbChildhoodIllnesses = (TextView) findViewById(R.id.tv_srvSpc_ChildhoodIllnesses);
        llay_ChildhoodIllnesses_checkbox = (LinearLayout) findViewById(R.id.llay_ChildhoodIllnesses_checkbox);

        cb_CIllness_Diarrhea = (CheckBox) findViewById(R.id.cb_srvSpc_CIllness_Diarrhea);
        cb_CIllness_Pneumonia = (CheckBox) findViewById(R.id.cb_srvSpc_CIllness_Pneumonia);
        cb_CIllness_Fever = (CheckBox) findViewById(R.id.cb_srvSpc_CIllness_Fever);
        cb_CIllness_Cerebral_Palsy = (CheckBox) findViewById(R.id.cb_srvSpc_CIllness_Cerebral_Palsy);


        lbImmunizations = (TextView) findViewById(R.id.tv_srvSpc_Immunizations);
        llay_Immunizations_checkbox = (LinearLayout) findViewById(R.id.llay_Immunizations_checkbox);

        cb_Immu_Polio = (CheckBox) findViewById(R.id.cb_srvSpc_Immu_Polio);
        cb_Immu_BCG = (CheckBox) findViewById(R.id.cb_srvSpc_Immu_BCG);
        cb_Immu_Measles = (CheckBox) findViewById(R.id.cb_srvSpc_Immu_Measles);
        cb_Immu_DPT_HIB = (CheckBox) findViewById(R.id.cb_srvSpc_Immu_DPT_HIB);
        cb_Immu_Lotta = (CheckBox) findViewById(R.id.cb_srvSpc_Immu_Lotta);
        cb_Immu_Other = (CheckBox) findViewById(R.id.cb_srvSpc_Immu_Other);

        lbFPCounsel = (TextView) findViewById(R.id.tv_srvSpc_FPCounsel);
        llay_FPCounsel_checkbox = (LinearLayout) findViewById(R.id.llay_FPCounsel_checkbox);

        cb_FPCounsel_Male_Condoms = (CheckBox) findViewById(R.id.cb_srvSpc_FPCounsel_Male_Condoms);
        cb_FPCounsel_Female_Condoms = (CheckBox) findViewById(R.id.cb_srvSpc_FPCounsel_Female_Condoms);
        cb_FPCounsel_Pills = (CheckBox) findViewById(R.id.cb_srvSpc_FPCounsel_Pills);
        cb_FPCounsel_Depo = (CheckBox) findViewById(R.id.cb_srvSpc_FPCounsel_Depo);
        cb_FPCounsel_LongParmanen = (CheckBox) findViewById(R.id.cb_srvSpc_FPCounsel_LongParmanen);
        cb_FPCounsel_NoMethod = (CheckBox) findViewById(R.id.cb_srvSpc_FPCounsel_NoMethod);

        lbAnimalType    = (TextView) findViewById(R.id.tv_srvSpc_spAnimalType);
        spAnimalType    = (Spinner) findViewById(R.id.srv_spec_spAnimalType);

        lbCropType      = (TextView) findViewById(R.id.tv_srvSpc_spCropType);
        spCropType      = (Spinner) findViewById(R.id.srv_spec_spCropType);

        lbLoanAMT       = (TextView) findViewById(R.id.tv_srvSpc_spLoanAMT);
        edtLoanAMT       = (EditText) findViewById(R.id.srv_spec_edtLoanAMT);

        lbLoanSource = (TextView) findViewById(R.id.tv_srvSpc_spLoanSource);
        spLoanSource  = (Spinner) findViewById(R.id.srv_spec_spLoanSource);


        lbLeadPosition  = (TextView) findViewById(R.id.tv_srvSpc_spLeadPosition);
        spLeadPosition  = (Spinner) findViewById(R.id.srv_spec_spLeadPosition);



        tvId = (TextView) findViewById(R.id.tv_srvSpc_MemberId);
        tvName = (TextView) findViewById(R.id.tv_srvSpec_MemberName);
        tvAward = (TextView) findViewById(R.id.tv_srvSpec_AwardName);
        tvCriteria = (TextView) findViewById(R.id.tv_srvSpec_CriteriaName);
        tvServiceMonth = (TextView) findViewById(R.id.tv_srvSpec_ServiceName);
        tvGroupName = (TextView) findViewById(R.id.tv_srvSpec_GroupName);

        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnGoToService = (Button) findViewById(R.id.btnRegisterFooter);
        btnSave = (Button) findViewById(R.id.btn_srvSpcSave);
        btnDelete = (Button) findViewById(R.id.btn_srvSpcDelete);



        setUpSaveButton();
        setUpGoToServiceButton();
        setUpHomeButton();
        setUpDeleteButton();


    }

    private void setUpSaveButton() {
        btnSave.setText("");
        Drawable saveImage = getResources().getDrawable(R.drawable.save_b);
        btnSave.setCompoundDrawablesRelativeWithIntrinsicBounds(saveImage, null, null, null);
        btnSave.setPadding(180, 10, 180, 10);
    }

    private void setUpGoToServiceButton() {
        btnGoToService.setText("");
        Drawable saveImage = getResources().getDrawable(R.drawable.goto_back);
        btnGoToService.setCompoundDrawablesRelativeWithIntrinsicBounds(saveImage, null, null, null);
        btnGoToService.setPadding(180, 10, 180, 10);
    }


    private void setUpHomeButton() {

        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        btnHome.setPadding(180, 10, 180, 10);
    }


    private void setUpDeleteButton() {

        btnDelete.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.delete);
        btnDelete.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        btnDelete.setPadding(180, 10, 180, 10);
    }

    private void testLogDebug() {
        Log.d("Nirzon", "Service Data : "
                        + "\n srvData.getHh_name() :" + srvData.getHh_name()
                        + "\n srvData.getHh_mm_name() :" + srvData.getHh_mm_name()
                        + "\n srvData.getC_code() :" + srvData.getC_code()
                        + " srvData.getDistrictCode() :" + srvData.getDistrictCode()
                        + " srvData.getUpazillaCode() :" + srvData.getUpazillaCode()
                        + " srvData.getUnitCode() :" + srvData.getUnitCode()
                        + " srvData.getVillageCode() :" + srvData.getVillageCode()
                        + "\n srvData.getAward_code() :" + srvData.getAward_code()
                        + " srvData.getDonor_code() :" + srvData.getDonor_code()
                        + " srvData.getProgram_code() :" + srvData.getProgram_code()
                        + " srvData.getService_code() :" + srvData.getService_code()
                        + "\n srvData.getHHID() :" + srvData.getHHID()
                        + "\n srvData.getMemberId() :" + srvData.getMemberId()
                        + "\n srvData.getOpCode() :" + srvData.getOpCode()
                        + " srvData.getOpMontheCode() :" + srvData.getOpMontheCode()
                        + "\n srvData.getServiceSLCode() :" + srvData.getServiceSLCode()
                        + " srvData.getServiceCenterCode() :" + srvData.getServiceCenterCode()
                        + "\n srvData.setTemServiceDate() :" + srvData.getTemServiceDate()
                        + "\n srvData.getTemIdServiceMonth() :" + srvData.getTemIdServiceMonth()
                        + "\n srvData.getTemStrSrvMonth() :" + srvData.getTemStrSrvMonth()
                        + "\n srvData.getFPDCode() :" + srvData.getFPDCode()
//
        );

    }





    /**
     * LOAD:: Crop Type
     * <p>
     * Database
     *
     * </p>
     */
    private void loadCropType() {



        int position = 0;
        String criteria =""; //" WHERE " + SQLiteHandler.ADM_COUNTRY_AWARD_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + "='" + cCode + "'";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.LUP_PROG_GROUP_CROP_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listAward);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spCropType.setAdapter(dataAdapter);


        if (cropCode != null) {
            for (int i = 0; i < spCropType.getCount(); i++) {
                String award = spCropType.getItemAtPosition(i).toString();
                if (award.equals(strCropType)) {
                    position = i;
                }
            }
            spCropType.setSelection(position);
        }


        spCropType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCropType = ((SpinnerHelper) spCropType.getSelectedItem()).getValue();
                cropCode = ((SpinnerHelper) spCropType.getSelectedItem()).getId();

                Log.d(TAG, "cropCode" + cropCode + " strCropType : " + strCropType );

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }



    /**
     * LOAD:: LoanSource
     * <p>
     * Database
     *
     * </p>
     */
    private void loadLoanSource() {



        int position = 0;
        String criteria =""; //" WHERE " + SQLiteHandler.ADM_COUNTRY_AWARD_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + "='" + cCode + "'";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.LUP_COMMUNITY_LOAN_SOURCE_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listAward);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spLoanSource.setAdapter(dataAdapter);


        if (loanSource != null) {
            for (int i = 0; i < spLoanSource.getCount(); i++) {
                String award = spLoanSource.getItemAtPosition(i).toString();
                if (award.equals(strLoanSource)) {
                    position = i;
                }
            }
            spLoanSource.setSelection(position);
        }


        spLoanSource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strLoanSource = ((SpinnerHelper) spLoanSource.getSelectedItem()).getValue();
                loanSource = ((SpinnerHelper) spLoanSource.getSelectedItem()).getId();

                Log.d(TAG, "loanSource" + loanSource + " strLoanSource : " + strLoanSource );

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }



    /**
     * LOAD:: Lead Position
     * <p>
     * Database
     *
     * </p>
     */
    private void loadLeadPostion() {



        int position = 0;
        String criteria =""; //" WHERE " + SQLiteHandler.ADM_COUNTRY_AWARD_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + "='" + cCode + "'";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listLeadPos = sqlH.getListAndID(SQLiteHandler.LUP_COMMUNITY_LEAD_POSITION_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listLeadPos);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spLeadPosition.setAdapter(dataAdapter);


        if (leadCode != null) {
            for (int i = 0; i < spLeadPosition.getCount(); i++) {
                String leadPos = spLeadPosition.getItemAtPosition(i).toString();
                if (leadPos.equals(strLeadCode)) {
                    position = i;
                }
            }
            spLeadPosition.setSelection(position);
        }


        spLeadPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strLeadCode = ((SpinnerHelper) spLeadPosition.getSelectedItem()).getValue();
                leadCode = ((SpinnerHelper) spLeadPosition.getSelectedItem()).getId();

                Log.d(TAG, "leadCode" + leadCode + " strLeadCode : " + strLeadCode );

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    /**
     * LOAD:: Animal Type
     * <p>
     * Database
     *
     * </p>
     */
    private void loadAnimalType() {



        int position = 0;
        String criteria =""; //" WHERE " + SQLiteHandler.ADM_COUNTRY_AWARD_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + "='" + cCode + "'";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.LUP_COMMUNITY_ANIMAL_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listAward);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spAnimalType.setAdapter(dataAdapter);


        if (animalCode != null) {
            for (int i = 0; i < spAnimalType.getCount(); i++) {
                String award = spAnimalType.getItemAtPosition(i).toString();
                if (award.equals(strAnimalType)) {
                    position = i;
                }
            }
            spAnimalType.setSelection(position);
        }


        spAnimalType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strAnimalType = ((SpinnerHelper) spAnimalType.getSelectedItem()).getValue();
                animalCode = ((SpinnerHelper) spAnimalType.getSelectedItem()).getId();

                Log.d(TAG, "animalCode" + animalCode + " strAnimalType : " + strAnimalType );

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    /**
     * LOAD:: Baby Status
     * <p>
     * Normal = 1
     * PT/LBW = 2
     * </p>
     */
    private void loadBabyStatus() {

        ArrayAdapter<CharSequence> adtBabyStatus = ArrayAdapter.createFromResource(
                this, R.array.arr_baby_status, R.layout.spinner_layout);

        adtBabyStatus.setDropDownViewResource(R.layout.spinner_layout);
        spBabyStatus.setAdapter(adtBabyStatus);


        if (babyStatus != null) {
            /**
             * Due to Array index is zero index 1 should be minus from the values
             */

            spBabyStatus.setSelection(Integer.parseInt(babyStatus) - 1);
        }

        spBabyStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String temString = parent.getItemAtPosition(position).toString();

                if (temString.equals("PT/LBW"))
                    babyStatus = "2";
                else
                    babyStatus = "1";

                Log.d(TAG, "temString :" + temString + " babyStatusCode:" + babyStatus);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * LOAD:: GMP Attendance
     * <p>
     * Just joined = 1
     * Attending = 2
     * </p>
     */

    private void loadGMPAttendance() {

        ArrayAdapter<CharSequence> adtBabyStatus = ArrayAdapter.createFromResource(
                this, R.array.arr_gmp_attendance, R.layout.spinner_layout);

        adtBabyStatus.setDropDownViewResource(R.layout.spinner_layout);
        spGMPAttendance.setAdapter(adtBabyStatus);


        if (gmpAttendence != null) {
            /**
             * Due to Array index is zero index 1 should be minus from the values
             */

            spGMPAttendance.setSelection(Integer.parseInt(gmpAttendence) - 1);
        }


        spGMPAttendance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String temString = parent.getItemAtPosition(position).toString();

                if (temString.equals("Attending"))
                    gmpAttendence = "2";
                else
                    gmpAttendence = "1";

                Log.d(TAG, " gmpAttendence:" + gmpAttendence);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * LOAD:: Weight Status
     * <p>
     * Just joined = 1
     * Attending = 2
     * </p>
     */

    private void loadWeightStatus() {

        ArrayAdapter<CharSequence> adtBabyStatus = ArrayAdapter.createFromResource(
                this, R.array.arr_weight_status, R.layout.spinner_layout);

        adtBabyStatus.setDropDownViewResource(R.layout.spinner_layout);
        spWeightStatus.setAdapter(adtBabyStatus);


        if (weightStatus != null) {
            /**
             * Due to Array index is zero index 1 should be minus from the values
             */

            spWeightStatus.setSelection(Integer.parseInt(weightStatus) - 1);
        }


        spWeightStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String temString = parent.getItemAtPosition(position).toString();

                if (temString.equals("No weight change"))
                    weightStatus = "3";
                else if (temString.equals("Weight loss"))
                    weightStatus = "2";
                else
                    weightStatus = "1";

                Log.d(TAG, "weightStatus: " + temString + " weightStatusCode:" + weightStatus);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * LOAD:: NutAttendance Status
     * <p>
     * CCFLS = 1
     * SFP/CTC = 2
     * </p>
     */
    private void loadNutAttendance() {

        ArrayAdapter<CharSequence> adtBabyStatus = ArrayAdapter.createFromResource(
                this, R.array.arr_nut_attendance, R.layout.spinner_layout);

        adtBabyStatus.setDropDownViewResource(R.layout.spinner_layout);
        spNutAttendance.setAdapter(adtBabyStatus);


        if (nutAttendance != null) {
            /**
             * Due to Array index is zero index 1 should be minus from the values
             */

            spNutAttendance.setSelection(Integer.parseInt(nutAttendance) - 1);
        }


        spNutAttendance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String temString = parent.getItemAtPosition(position).toString();

                if (temString.equals("SFP/CTC"))
                    nutAttendance = "2";

                else
                    nutAttendance = "1";

                Log.d(TAG, "nutAttendance: " + temString + " nutAttendance:" + nutAttendance);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadVitaminA_under5() {

        ArrayAdapter<CharSequence> adtBabyStatus = ArrayAdapter.createFromResource(
                this, R.array.arr_vitA_under5, R.layout.spinner_layout);

        adtBabyStatus.setDropDownViewResource(R.layout.spinner_layout);
        spVitA_Under5.setAdapter(adtBabyStatus);


        if (vitaUnder5 != null) {
            /**
             * Due to Array index is zero index 1 should be minus from the values
             */

            spVitA_Under5.setSelection(Integer.parseInt(vitaUnder5) - 1);
        }


        spVitA_Under5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String temString = parent.getItemAtPosition(position).toString();

                if (temString.equals("No"))
                    vitaUnder5 = "2";

                else
                    vitaUnder5 = "1";

                Log.d(TAG, "vitA_Under5: " + temString + " vitaUnder5:" + vitaUnder5);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void loadExclusive_currentlyBF() {

        ArrayAdapter<CharSequence> adtBabyStatus = ArrayAdapter.createFromResource(this, R.array.arr_exclusive_currentlyBF, R.layout.spinner_layout);

        adtBabyStatus.setDropDownViewResource(R.layout.spinner_layout);
        spExclusive_CurrentlyBF.setAdapter(adtBabyStatus);


        if (exclCurrentLybf != null) {
            /**
             * Due to Array index is zero index 1 should be minus from the values
             */

            spExclusive_CurrentlyBF.setSelection(Integer.parseInt(exclCurrentLybf) - 1);
        }


        spExclusive_CurrentlyBF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String temString = parent.getItemAtPosition(position).toString();

                if (temString.equals("No"))
                    exclCurrentLybf = "2";

                else
                    exclCurrentLybf = "1";

                Log.d(TAG, "exclusive_CurrentlyBF: " + temString + " exclCurrentLybf:" + exclCurrentLybf);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void loadCMAMRef() {

        ArrayAdapter<CharSequence> adtBabyStatus = ArrayAdapter.createFromResource(
                this, R.array.arr_CMAM_ref, R.layout.spinner_layout);

        adtBabyStatus.setDropDownViewResource(R.layout.spinner_layout);
        spCMAMRef.setAdapter(adtBabyStatus);


        if (camRef != null) {
            /**
             * Due to Array index is zero index 1 should be minus from the values
             */

            spCMAMRef.setSelection(Integer.parseInt(camRef) - 1);
        }


        spCMAMRef.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String temString = parent.getItemAtPosition(position).toString();

                if (temString.equals("CCFLS"))
                    camRef = "4";

                else if (temString.equals("OTP"))
                    camRef = "3";

                else if (temString.equals("SFP"))
                    camRef = "2";

                else
                    camRef = "1";

                Log.d(TAG, "cmamRef: " + temString + " camRef:" + camRef);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void loadCMAMAdd() {

        ArrayAdapter<CharSequence> adtBabyStatus = ArrayAdapter.createFromResource(
                this, R.array.arr_CMAM_add, R.layout.spinner_layout);

        adtBabyStatus.setDropDownViewResource(R.layout.spinner_layout);
        spCMAMAdd.setAdapter(adtBabyStatus);


        if (camAdd != null) {
            /**
             * Due to Array index is zero index 1 should be minus from the values
             */

            spCMAMAdd.setSelection(Integer.parseInt(camAdd) - 1);
        }


        spCMAMAdd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String temString = parent.getItemAtPosition(position).toString();


                if (temString.equals("OTP"))
                    camAdd = "3";

                else if (temString.equals("SFP"))
                    camAdd = "2";

                else
                    camAdd = "1";

                Log.d(TAG, "cmamAddCode: " + temString + " camAdd:" + camAdd);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * Date picker setting
     */

    private SimpleDateFormat formatUSA = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
    private Calendar calendar = Calendar.getInstance();

    public void updateDateCompFeeding() {

        tvDateCompFeeding.setText(formatUSA.format(calendar.getTime()));
    }

    public void setDateCompFeeding() {
        new DatePickerDialog(CONTEXT, datePCompFeeding, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();


    }

    DatePickerDialog.OnDateSetListener datePCompFeeding = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateCompFeeding();
        }
    };


    DatePickerDialog.OnDateSetListener datePANCVisit = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateANCVisitDate();
        }
    };

    public void setANCVisitDate() {
        new DatePickerDialog(CONTEXT, datePANCVisit, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();


    }


    public void updateANCVisitDate() {
        tvDateANCVisit.setText(formatUSA.format(calendar.getTime()));
    }


    private void setBabyStatusVisibility(int visibility) {
        spBabyStatus.setVisibility(visibility);
        lbBabyStatus.setVisibility(visibility);
    }

    private void setGMPAttendanceVisibility(int visibility) {
        spGMPAttendance.setVisibility(visibility);
        lbGMPAttendance.setVisibility(visibility);
    }

    private void setWeightStatusVisibility(int visibility) {
        spWeightStatus.setVisibility(visibility);
        lbWeightStatus.setVisibility(visibility);
    }

    private void setNutAttendanceVisibility(int visibility) {
        spNutAttendance.setVisibility(visibility);
        lbNutAttendance.setVisibility(visibility);
    }

    private void setVitA_Under5Visibility(int visibility) {
        spVitA_Under5.setVisibility(visibility);
        lbVitA_Under5.setVisibility(visibility);
    }

    private void setExclusive_CurrentlyBFVisibility(int visibility) {
        spExclusive_CurrentlyBF.setVisibility(visibility);
        lbExclusive_CurrentlyBF.setVisibility(visibility);
    }

    private void setDateCompFeedingVisibility(int visibility) {
        lbDateCompFeeding.setVisibility(visibility);
        tvDateCompFeeding.setVisibility(visibility);
        rlayout_DateCompFeeding.setVisibility(visibility);
    }

    private void setCMAMRefVisibility(int visibility) {
        spCMAMRef.setVisibility(visibility);
        lbCMAMRef.setVisibility(visibility);
    }

    private void setCMAMAddVisibility(int visibility) {
        spCMAMAdd.setVisibility(visibility);
        lbCMAMAdd.setVisibility(visibility);
    }

    private void setDateANCVisitVisibility(int visibility) {
        lbDateANCVisit.setVisibility(visibility);
        tvDateANCVisit.setVisibility(visibility);
        rlayout_DateANCVisit.setVisibility(visibility);
    }

    private void setFacilityPostnatalVisibility(int visibility) {
        lbFacilityPostnatal.setVisibility(visibility);
        llayout_FacilityPostnatal_cb_group.setVisibility(visibility);
    }

    private void setHomeSupport24HVisibility(int visibility) {
        lbHomeSupport24H.setVisibility(visibility);
        llay_HomeSupport24H_checkbox.setVisibility(visibility);
    }

    private void setDeliveryStaffVisibility(int visibility) {
        lbDeliveryStaff.setVisibility(visibility);
        rg_DeliveryStaff.setVisibility(visibility);
    }

    private void setHomeSupport48HVisibility(int visibility) {
        lbHomeSupport48H.setVisibility(visibility);
        llay_HomeSupport48H_checkbox.setVisibility(visibility);
    }


    private void setMaternalComplicationsVisibility(int visibility) {
        lbMaternalComplications.setVisibility(visibility);
        llay_MaternalComplications_checkbox.setVisibility(visibility);
    }


    private void setNewbornComplicationsVisibility(int visibility) {
        lbNewbornComplications.setVisibility(visibility);
        llay_NewbornComplications_checkbox.setVisibility(visibility);
    }

    private void setChildhoodIllnessesVisibility(int visibility) {
        lbChildhoodIllnesses.setVisibility(visibility);
        llay_ChildhoodIllnesses_checkbox.setVisibility(visibility);
    }

    private void setImmunizationsVisibility(int visibility) {
        lbImmunizations.setVisibility(visibility);
        llay_Immunizations_checkbox.setVisibility(visibility);
    }


    private void setFPCounselVisibility(int visibility) {
        lbFPCounsel.setVisibility(visibility);
        llay_FPCounsel_checkbox.setVisibility(visibility);
    }


    private void setAnimalTypeVisibility(int visibility) {
        lbAnimalType.setVisibility(visibility);
        spAnimalType.setVisibility(visibility);
    }

    private void setCropTypeVisibility(int visibility) {
        lbCropType.setVisibility(visibility);
        spCropType.setVisibility(visibility);
    }

    private void setLoanAMTVisibility(int visibility) {
        lbLoanAMT.setVisibility(visibility);
        edtLoanAMT.setVisibility(visibility);
    }

    private void setLeadPositionVisibility(int visibility) {
        lbLeadPosition.setVisibility(visibility);
        spLeadPosition.setVisibility(visibility);
    }


    private void setLoanSourceVisibility(int visibility) {
        lbLoanSource.setVisibility(visibility);
        spLoanSource.setVisibility(visibility);
    }




    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
