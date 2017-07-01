package com.siddiquinoor.restclient.activity.sub_activity.summary_sub;

/**
 * Activity for presenting the detail view of a list
 * with image and details
 *
 * @author Faisal Mohammad
 * @desc Software Engineer , TechnoDhaka.
 * @link
 * @version 1.3.0
 * @since 1.0
 */

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.Register;
import com.siddiquinoor.restclient.activity.RegisterLiberia;
import com.siddiquinoor.restclient.activity.sub_activity.register_sub.RegisterMember;
import com.siddiquinoor.restclient.activity.sub_activity.register_sub.RegisterMemberLiberia;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.data_model.RegN_HH_libDataModel;
import com.siddiquinoor.restclient.views.adapters.MemberListAdapter;
import com.siddiquinoor.restclient.views.adapters.MemberModel;

import java.util.ArrayList;
import java.util.List;

import static com.siddiquinoor.restclient.R.id;


public class ViewRecordDetail extends BaseActivity implements View.OnClickListener {


    private static final String MALAWI = "0002";
    private static final String LIBERIA = "0004";
    TextView tv_regID;
    TextView tv_personID;
    TextView tv_country, tv_district, tv_upazilla, tv_unit, tv_village, tv_Address;
    TextView tv_person_name, tv_regDate;


    TextView tv_LayR4Name, tv_hhSize, tv_Latitude, tv_Longitude,/* tv_agland,*/
            tv_vstatus, tv_mstatus;


    TextView tv_hhType;
    TextView entrydate;
    RelativeLayout rLayoutMalawi, rLayoutLiberia;

    int pID;
    String str_hhID = null;                                                                         // layR1Code+layR2Code+layR3Code+layR4Code+hhID
    String str_hhName = null;


    String str_c_name = null;
    String str_district = null;
    String str_upazilla = null;
    String str_union = null;
    String str_village = null;
    String str_Address = null;

    String c_code = null;
    String districtCode = null;
    String upazillaCode = null;
    String unionCode = null;
    String villageCode = null;


    String str_entry_by = null;
    String str_entry_date = null;


    private Button btnReg;
    private Button btnRecords;
    private Button btnSummary;
    private Button btnHome;
    private Button btnAddMember;

    private ArrayList<MemberModel> memberArray = new ArrayList<MemberModel>();

    private ListView listView;
    private MemberListAdapter adapter;
    private SQLiteHandler sqlH;
    private TextView tv_childL2M, tv_childL2F, tv_child2to5M, tv_child2to5F, tv_child6to12M, tv_child6to12F, tv_child13to17M, tv_child13to17F, tv_orphan18M, tv_orphan18F, tv_adult18to59M, tv_adult18to59F, tv_yelderly60M, tv_elderly60F;

    private TextView tv_No_PLW_HH, tvNoChroni_ill,
            tv_hasTakeCForEbola, tv_DidContractEbola, tv_hasTakeEForEbola, tv_hasTakeChrIllForEbola,
            tv_bf_cattle_Count, tv_bf_cattle_Value, tv_af_cattle_count, tv_af_cattle_Value,
            tv_bf_sheep_Count, tv_bf_sheep_Value, tv_af_sheep_Count, tv_af_sheep_Value,
            tv_bf_poultry_Count, tv_bf_poultry_Value, tv_af_poultry_Count, tv_af_poultry_Value,
            tv_bf_other_Count, tv_bf_other_Value, tv_af_other_Count, tv_af_other_Value,
            tv_bf_Cultivable_Accre, tv_bf_Cultivable_Value, tv_af_Cultivable_Accre, tv_af_Cultivable_Value,
            tv_bf_NCultivable_Accre, tv_bf_NCultivable_Value, tv_af_NCultivable_Accre, tv_af_NCultivable_Value,
            tv_bf_Orchards_Accre, tv_bf_Orchards_Value, tv_af_Orchards_Accre, tv_af_Orchards_Value,
            tv_bf_corp_ld, tv_af_corp_ld, tv_bf_liveStock_ld, tv_af_liveStock_ld,
            tv_bf_smallBusiness_ld, tv_af_smallBusiness_ld, tv_bf_employments_ld, tv_af_employments_ld,
            tv_bf_remitance_ld, tv_af_remitance_ld, tv_bf_noOfEarner, tv_af_noOfEarner;
    private String hhID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record_detail);

        sqlH = new SQLiteHandler(getApplicationContext());

        viewsReference();
        setListeners();


        /**
         * getting intent data from search and previous screen
         */

        Intent intnt = getIntent();
        pID = intnt.getIntExtra(KEY.P_ID, -1);

        str_hhID = intnt.getStringExtra(KEY.REG_ID);
        if (str_hhID.length() > 8)
            hhID = str_hhID.substring(8);

        str_hhName = intnt.getStringExtra(KEY.PERSON_NAME);


        str_c_name = intnt.getStringExtra(KEY.COUNTRY_NAME);
        str_district = intnt.getStringExtra(KEY.DISTRICT);
        str_upazilla = intnt.getStringExtra(KEY.UPAZILLA);
        str_union = intnt.getStringExtra(KEY.UNIT);
        str_village = intnt.getStringExtra(KEY.VILLAGE_NAME);

        str_Address = intnt.getStringExtra(KEY.ADDRESS_NAME);


        c_code = intnt.getStringExtra(KEY.COUNTRY_CODE);
        districtCode = intnt.getStringExtra(KEY.DISTRICT_CODE);
        upazillaCode = intnt.getStringExtra(KEY.UPAZILLA_CODE);
        unionCode = intnt.getStringExtra(KEY.UNIT_CODE);
        villageCode = intnt.getStringExtra(KEY.VILLAGE_CODE);


        str_entry_by = intnt.getStringExtra(KEY.ENTRY_BY);
        str_entry_date = intnt.getStringExtra(KEY.ENTRY_DATE);

        tv_country.setText(str_c_name);
        tv_district.setText(str_district);
        tv_upazilla.setText(str_upazilla);
        tv_unit.setText(str_union);
        tv_village.setText(str_village);
        tv_Address.setText("Address :" + str_Address);
        tv_regID.setText(str_hhID);

        tv_regDate.setText(intnt.getStringExtra(KEY.REG_DATE));
        tv_person_name.setText(str_hhName);
        /**
         *  set Condition here if malaw than below code will execute */
        if (c_code.equals(MALAWI)) {
            rLayoutLiberia.setVisibility(View.GONE);
            tv_LayR4Name.setText(intnt.getStringExtra(KEY.SEX));
            tv_hhSize.setText(intnt.getStringExtra(KEY.HH_SIZE));
            tv_Latitude.setText(intnt.getStringExtra(KEY.LATITUDE));
            tv_Longitude.setText(intnt.getStringExtra(KEY.LONGITUDE));
            // tv_agland.setText(intnt.getStringExtra(KEY.AG_LAND));
            tv_vstatus.setText(intnt.getStringExtra(KEY.VSTATUS));
            tv_mstatus.setText(intnt.getStringExtra(KEY.MSTATUS));

        } else {
            rLayoutMalawi.setVisibility(View.GONE);

            // for test
            setLibetiasValuesToTextView();


        }
     /*   entryby.setText(str_entry_by);
        entrydate.setText(str_entry_date);*/

        //String member =  intnt.getStringExtra("member");

        String temId = str_hhID.substring(8);

        loadMemberListData(c_code, districtCode, upazillaCode, unionCode, villageCode, temId);

    }

    private void setLibetiasValuesToTextView() {
        RegN_HH_libDataModel libHHData = sqlH.getSingleLiberiaHouseHoldData(c_code, districtCode, upazillaCode, unionCode, villageCode, hhID);

        if (libHHData != null) {
            tv_hhType.setText(libHHData.getHHTypeString());

            tv_childL2M.setText(libHHData.getLT2yrsM());
            tv_childL2F.setText(libHHData.getLT2yrsF());
            tv_child2to5M.setText(libHHData.getM2to5yers());
            tv_child2to5F.setText(libHHData.getF2to5yrs());
            tv_child6to12M.setText(libHHData.getM6to12yrs());
            tv_child6to12F.setText(libHHData.getF6to12yrs());
            tv_child13to17M.setText(libHHData.getM13to17yrs());
            tv_child13to17F.setText(libHHData.getF13to17yrs());
            tv_orphan18M.setText(libHHData.getOrphn_LT18yrsM());
            tv_orphan18F.setText(libHHData.getOrphn_LT18yrsF());
            tv_adult18to59M.setText(libHHData.getAdlt_18to59M());
            tv_adult18to59F.setText(libHHData.getAdlt_18to59F());
            tv_yelderly60M.setText(libHHData.getEld_60pM());
            tv_elderly60F.setText(libHHData.getEld_60pF());
            tv_No_PLW_HH.setText(libHHData.getPLW());
            tvNoChroni_ill.setText(libHHData.getChronicallyIll());

            tv_hasTakeCForEbola.setText(libHHData.getLivingDeceasedContractEbola().equals("Y") ? "Yes" : "No");
            tv_DidContractEbola.setText(libHHData.getExtraChildBecauseEbola().equals("Y") ? "Yes" : "No");
            tv_hasTakeEForEbola.setText(libHHData.getExtraelderlyPersonBecauseEbola().equals("Y") ? "Yes" : "No");
            tv_hasTakeChrIllForEbola.setText(libHHData.getExtraChronicallyIllDisabledPersonBecauseEbola().equals("Y") ? "Yes" : "No");

            tv_bf_cattle_Count.setText(libHHData.getBrfCntCattle());
            tv_bf_cattle_Value.setText(libHHData.getBrfValCattle());
            tv_af_cattle_count.setText(libHHData.getAftCntCattle());
            tv_af_cattle_Value.setText(libHHData.getAftValCattle());
            tv_bf_sheep_Count.setText(libHHData.getBrfCntSheepGoats());
            tv_bf_sheep_Value.setText(libHHData.getBrfValSheepGoats());
            tv_af_sheep_Count.setText(libHHData.getAftCntSheepGoats());
            tv_af_sheep_Value.setText(libHHData.getAftValSheepGoats());
            tv_bf_poultry_Count.setText(libHHData.getBrfCntPoultry());
            tv_bf_poultry_Value.setText(libHHData.getBrfValPoultry());
            tv_af_poultry_Count.setText(libHHData.getAftCntPoultry());
            tv_af_poultry_Value.setText(libHHData.getAftValPoultry());
            tv_bf_other_Count.setText(libHHData.getBrfCntOther());
            tv_bf_other_Value.setText(libHHData.getBrfValOther());
            tv_af_other_Count.setText(libHHData.getAftCntOther());
            tv_af_other_Value.setText(libHHData.getAftValOther());
            tv_bf_Cultivable_Accre.setText(libHHData.getBrfAcreCultivable());
            tv_bf_Cultivable_Value.setText(libHHData.getBrfValCultivable());
            tv_af_Cultivable_Accre.setText(libHHData.getAftAcreCultivable());
            tv_af_Cultivable_Value.setText(libHHData.getAftValCultivable());
            tv_bf_NCultivable_Accre.setText(libHHData.getBrfAcreNonCultivable());
            tv_bf_NCultivable_Value.setText(libHHData.getBrfValNonCultivable());
            tv_af_NCultivable_Accre.setText(libHHData.getAftAcreNonCultivable());
            tv_af_NCultivable_Value.setText(libHHData.getAftValNonCultivable());
            tv_bf_Orchards_Accre.setText(libHHData.getBrfAcreOrchards());
            tv_bf_Orchards_Value.setText(libHHData.getBrfValOrchards());
            tv_af_Orchards_Accre.setText(libHHData.getAftAcreOrchards());
            tv_af_Orchards_Value.setText(libHHData.getAftValOrchards());
            tv_bf_corp_ld.setText(libHHData.getBrfValCrop());
            tv_af_corp_ld.setText(libHHData.getAftValCrop());
            tv_bf_liveStock_ld.setText(libHHData.getBrfValLivestock());
            tv_af_liveStock_ld.setText(libHHData.getAftValLivestock());
            tv_bf_smallBusiness_ld.setText(libHHData.getBrfValSmallBusiness());
            tv_af_smallBusiness_ld.setText(libHHData.getAftValSmallBusiness());
            tv_bf_employments_ld.setText(libHHData.getBrfValEmployment());
            tv_af_employments_ld.setText(libHHData.getAftValEmployment());
            tv_bf_remitance_ld.setText(libHHData.getBrfValRemittances());
            tv_af_remitance_ld.setText(libHHData.getAftValRemittances());
            tv_bf_noOfEarner.setText(libHHData.getBrfCntWageEnr());
            tv_af_noOfEarner.setText(libHHData.getAftCntWageEnr());
        }


    }

    private void setListeners() {
        btnReg.setOnClickListener(this);
        btnHome.setOnClickListener(this);
        btnAddMember.setOnClickListener(this);
    }


    private void viewsReference() {


        btnReg = (Button) findViewById(id.btnRegisterFooter);

        btnHome = (Button) findViewById(id.btnHomeFooter);

        btnAddMember = (Button) findViewById(R.id.btnAddMember);

        listView = (ListView) findViewById(id.listView_member); // in activity_view_record_detail.xml

        tv_personID = (TextView) findViewById(id.reg_id);
        tv_country = (TextView) findViewById(id.countryName);
        tv_district = (TextView) findViewById(id.district);
        tv_upazilla = (TextView) findViewById(id.upazilla);
        tv_unit = (TextView) findViewById(id.unit);
        tv_village = (TextView) findViewById(id.village);
        tv_Address = (TextView) findViewById(id.lblAddress);
        tv_regID = (TextView) findViewById(id.regid);
        tv_regDate = (TextView) findViewById(id.regdate);
        tv_person_name = (TextView) findViewById(id.person_name);
        tv_LayR4Name = (TextView) findViewById(id.sex);
        tv_hhSize = (TextView) findViewById(id.hhsize);
        tv_Latitude = (TextView) findViewById(id.lblLatitudeVal);
        tv_Longitude = (TextView) findViewById(id.lblLongitudeVal);
        ///tv_agland = (TextView) findViewById(id.agland);
        tv_vstatus = (TextView) findViewById(id.vstatus);
        tv_mstatus = (TextView) findViewById(id.mstatus);

        tv_hhType = (TextView) findViewById(R.id.HouseHoldType);
       /* entrydate = (TextView) findViewById(id.entrydate);
*/
        rLayoutMalawi = (RelativeLayout) findViewById(id.rl_malawi);
        rLayoutLiberia = (RelativeLayout) findViewById(id.rl_liberia);

        tv_childL2M = (TextView) findViewById(R.id.tv_childL2M_Value);
        tv_childL2F = (TextView) findViewById(R.id.tv_childL2F_Value);
        tv_child2to5M = (TextView) findViewById(R.id.tv_child2to5M_Value);
        tv_child2to5F = (TextView) findViewById(R.id.tv_child2to5F_Value);
        tv_child6to12M = (TextView) findViewById(R.id.tv_child6to12M_Value);
        tv_child6to12F = (TextView) findViewById(R.id.tv_child6to12F_Value);
        tv_child13to17M = (TextView) findViewById(R.id.tv_child13to17M_Value);
        tv_child13to17F = (TextView) findViewById(R.id.tv_child13to17F_Value);
        tv_orphan18M = (TextView) findViewById(R.id.tv_orphan18M_Value);
        tv_orphan18F = (TextView) findViewById(R.id.tv_orphan18F_Value);
        tv_adult18to59M = (TextView) findViewById(R.id.tv_adult18to59M_Value);
        tv_adult18to59F = (TextView) findViewById(R.id.tv_adult18to59F_Value);
        tv_yelderly60M = (TextView) findViewById(R.id.tv_yelderly60M_Value);
        tv_elderly60F = (TextView) findViewById(R.id.tv_elderly60F_Value);
        tv_elderly60F = (TextView) findViewById(R.id.tv_elderly60F_Value);

        tv_No_PLW_HH = (TextView) findViewById(R.id.tv_No_PLW_HH);
        tvNoChroni_ill = (TextView) findViewById(R.id.tvNoChroni_ill);

        tv_hasTakeCForEbola = (TextView) findViewById(R.id.tv_hasTakeCForEbola_Value);
        tv_DidContractEbola = (TextView) findViewById(R.id.tv_DidContractEbola_Value);
        tv_hasTakeEForEbola = (TextView) findViewById(R.id.tv_hasTakeEForEbola_Value);
        tv_hasTakeChrIllForEbola = (TextView) findViewById(R.id.tv_hasTakeChrIllForEbola_Value);

        tv_bf_cattle_Count = (TextView) findViewById(R.id.tv_bf_cattle_Count);
        tv_bf_cattle_Value = (TextView) findViewById(R.id.tv_bf_cattle_Value);
        tv_af_cattle_count = (TextView) findViewById(R.id.tv_af_cattle_count);
        tv_af_cattle_Value = (TextView) findViewById(R.id.tv_af_cattle_Value);

        tv_bf_sheep_Count = (TextView) findViewById(R.id.tv_bf_sheep_Count);
        tv_bf_sheep_Value = (TextView) findViewById(R.id.tv_bf_sheep_Value);
        tv_af_sheep_Count = (TextView) findViewById(R.id.tv_af_sheep_Count);
        tv_af_sheep_Value = (TextView) findViewById(R.id.tv_af_sheep_Value);

        tv_bf_poultry_Count = (TextView) findViewById(R.id.tv_bf_poultry_Count);
        tv_bf_poultry_Value = (TextView) findViewById(R.id.tv_bf_poultry_Value);
        tv_af_poultry_Count = (TextView) findViewById(R.id.tv_af_poultry_Count);
        tv_af_poultry_Value = (TextView) findViewById(R.id.tv_af_poultry_Value);

        tv_bf_other_Count = (TextView) findViewById(R.id.tv_bf_other_Count);
        tv_bf_other_Value = (TextView) findViewById(R.id.tv_bf_other_Value);
        tv_af_other_Count = (TextView) findViewById(R.id.tv_af_other_Count);
        tv_af_other_Value = (TextView) findViewById(R.id.tv_af_other_Value);


        tv_bf_Cultivable_Accre = (TextView) findViewById(R.id.tv_bf_Cultivable_Accre);
        tv_bf_Cultivable_Value = (TextView) findViewById(R.id.tv_bf_Cultivable_Value);
        tv_af_Cultivable_Accre = (TextView) findViewById(R.id.tv_af_Cultivable_Accre);
        tv_af_Cultivable_Value = (TextView) findViewById(R.id.tv_af_Cultivable_Value);

        tv_bf_NCultivable_Accre = (TextView) findViewById(R.id.tv_bf_NCultivable_Accre);
        tv_bf_NCultivable_Value = (TextView) findViewById(R.id.tv_bf_NCultivable_Value);
        tv_af_NCultivable_Accre = (TextView) findViewById(R.id.tv_af_NCultivable_Accre);
        tv_af_NCultivable_Value = (TextView) findViewById(R.id.tv_af_NCultivable_Value);


        tv_bf_Orchards_Accre = (TextView) findViewById(R.id.tv_bf_Orchards_Accre);
        tv_bf_Orchards_Value = (TextView) findViewById(R.id.tv_bf_Orchards_Value);
        tv_af_Orchards_Accre = (TextView) findViewById(R.id.tv_af_Orchards_Accre);
        tv_af_Orchards_Value = (TextView) findViewById(R.id.tv_af_Orchards_Value);

        tv_bf_corp_ld = (TextView) findViewById(R.id.tv_bf_corp_ld);
        tv_af_corp_ld = (TextView) findViewById(R.id.tv_af_corp_ld);
        tv_bf_liveStock_ld = (TextView) findViewById(R.id.tv_bf_liveStock_ld);
        tv_af_liveStock_ld = (TextView) findViewById(R.id.tv_af_liveStock_ld);
        tv_bf_smallBusiness_ld = (TextView) findViewById(R.id.tv_bf_smallBusiness_ld);
        tv_af_smallBusiness_ld = (TextView) findViewById(R.id.tv_af_smallBusiness_ld);
        tv_bf_employments_ld = (TextView) findViewById(R.id.tv_bf_employments_ld);
        tv_af_employments_ld = (TextView) findViewById(R.id.tv_af_employments_ld);
        tv_bf_remitance_ld = (TextView) findViewById(R.id.tv_bf_remitance_ld);
        tv_af_remitance_ld = (TextView) findViewById(R.id.tv_af_remitance_ld);
        tv_bf_noOfEarner = (TextView) findViewById(R.id.tv_bf_noOfEarner);
        tv_af_noOfEarner = (TextView) findViewById(R.id.tv_af_noOfEarner);

//        addIconHomeButton();
        addIconRegistrationButton();
        setAddMember();
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setAddMember() {
        btnAddMember.setText("");
        Drawable addMemberIcon = getResources().getDrawable(R.drawable.add_member);
        btnAddMember.setCompoundDrawablesRelativeWithIntrinsicBounds(addMemberIcon, null, null, null);
        btnAddMember.setPadding(80, 5, 80, 5);
    }

//    private void addIconHomeButton() {
//
//        btnHome.setText("");
//        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
//        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
//        btnHome.setPadding(180, 5, 180, 5);
//    }

    /**
     * calling getWidth() and getHeight() too early:
     * When  the UI has not been sized and laid out on the screen yet..
     *
     * @param hasFocus the value will be true when UI is focus
     */

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        setUpHomeButton();
        addIconRegistrationButton();
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addIconRegistrationButton() {
        btnReg.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.registration);
        btnReg.setCompoundDrawablesRelativeWithIntrinsicBounds(null, imageHome, null, null);
        btnReg.setPadding(-1, 10, -1, 10);
    }


    private void loadMemberListData(String str_c_code, String str_districtCode, String str_upazillaCode, String str_unionCode, String str_villageCode, String str_hhID) {

        List<MemberModel> listData = sqlH.getMemberData(str_c_code, str_districtCode, str_upazillaCode, str_unionCode, str_villageCode, str_hhID);

        for (MemberModel data : listData) {
            // add contacts data in arrayList
            memberArray.add(data);
        }

        adapter = new MemberListAdapter(this, memberArray);

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //hidePDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case id.btnRegisterFooter:
                Intent iReg;
                if (c_code.equals(MALAWI))  // if it Malawi Go to Malawi Member page
                    iReg = new Intent(getApplicationContext(), Register.class);
                else // else goto Liberia Page
                    iReg = new Intent(getApplicationContext(), RegisterLiberia.class);
                //Intent iReg = new Intent(getApplicationContext(), Register.class);
                iReg.putExtra(KEY.COUNTRY_CODE, c_code);
                startActivity(iReg);
                finish();
                break;

            case id.btnHomeFooter:

                Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHome);
                finish();
                break;

            case id.btnAddMember:

                // Intent intentAdd = new Intent(getApplicationContext(), RegisterMember.class);
                Intent intentAdd;
                if (c_code.equals(MALAWI))  // if it Malawi Go to Malawi Member page
                    intentAdd = new Intent(getApplicationContext(), RegisterMember.class);
                else // else goto Liberia Page
                    intentAdd = new Intent(getApplicationContext(), RegisterMemberLiberia.class);
                //  reference the key
                intentAdd.putExtra("pID", pID);
                intentAdd.putExtra("redirect", "view_detail");
                intentAdd.putExtra("str_hhID", str_hhID);
                intentAdd.putExtra("str_hhName", str_hhName);
                intentAdd.putExtra("str_c_code", c_code);
                intentAdd.putExtra("str_district", str_district);
                intentAdd.putExtra("str_upazilla", str_upazilla);
                intentAdd.putExtra("str_union", str_union);
                intentAdd.putExtra("str_village", str_village);
                intentAdd.putExtra("str_districtCode", districtCode);
                intentAdd.putExtra("str_upazillaCode", upazillaCode);
                intentAdd.putExtra("str_unionCode", unionCode);
                intentAdd.putExtra("str_villageCode", villageCode);
                intentAdd.putExtra("str_entry_by", str_entry_by);
                intentAdd.putExtra("str_entry_date", str_entry_date);
                startActivity(intentAdd);

                finish();
                break;

        }
    }
}