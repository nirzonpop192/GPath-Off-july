package com.siddiquinoor.restclient.activity;
/**
 * This class is for Graduate the member
 *
 * @author: Faisal Mohammad
 * @virsion:
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.GraduationGridAdapter;
import com.siddiquinoor.restclient.data_model.adapters.GraduationGridDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;

import java.util.ArrayList;
import java.util.List;


public class GraduationActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private final String TAG = GraduationActivity.class.getName();
    /**
     * code id(Country, Donor , Program,Award, Criteria)
     * For spinner
     */

    private String idCountry, idDonor, idAward, idProgram, idCriteria, idService;

    /**
     * string value (Country, Program,Award, Criteria)
     * For spinner
     */
    private String strCounty, strAward, strProgram, strCriteria;

    /**
     * spinner
     */

    private Spinner spAward, spProgram, spCriteria;


    private Context mContext;
    private SQLiteHandler sqlH;

    /**
     * Class View
     */

    private ListView lv_garduation;
    private EditText edt_searchId;
    private Button btnHome;
    private Button btnBackMemberSearch;

    private GraduationGridAdapter adapter;
    /**
     * help to restore village Sate in MemberSearch page
     */
    private String tempSpinVillageName;
    private String tempSpinVillageCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_graduation);

        initial();


        Intent intent = getIntent();


        String dir = intent.getStringExtra(KEY.DIR_CLASS_NAME_KEY);
        if (dir.equals("MemberSearchPage")) {
            idCountry = intent.getStringExtra(KEY.COUNTRY_ID);

            tempSpinVillageName = intent.getStringExtra(KEY.VILLAGE_NAME);
            tempSpinVillageCode = intent.getStringExtra(KEY.VILLAGE_CODE);

            String memberId = intent.getStringExtra(KEY.MEMBER_ID);
            if (memberId.length() > 5) {
                edt_searchId.setText(memberId);
                loadAward(idCountry, memberId);
            }


        } else if (dir.equals("GraduationUpdate")) {


            idCountry = intent.getStringExtra(KEY.COUNTRY_ID);
            String memberId = intent.getStringExtra(KEY.MEMBER_ID);
            if (memberId.length() > 5) {
                edt_searchId.setText(memberId);


                idAward = intent.getStringExtra(KEY.AWARD_CODE);
                strAward = intent.getStringExtra(KEY.AWARD_NAME);
                idProgram = intent.getStringExtra(KEY.PROGRAM_CODE);
                strProgram = intent.getStringExtra(KEY.PROGRAM_NAME);
                idCriteria = intent.getStringExtra(KEY.CRITERIA_CODE);
                strCriteria = intent.getStringExtra(KEY.CRITERIA_NAME);

                loadAward(idCountry, memberId);
            }

        }
        /**
         * for safety purpose
         */
        else {
            idCountry = intent.getStringExtra(KEY.COUNTRY_ID);
            strCounty = intent.getStringExtra(KEY.STR_COUNTRY);


            loadAward(idCountry, "");

        }


        Log.d(TAG, "id Country : id " + idCountry);


        setAllListener();

    }

    private void initial() {
        mContext = GraduationActivity.this;
        sqlH = new SQLiteHandler(this);
        viewReference();
    }


    private void setAllListener() {

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity((Activity) mContext);
            }
        });
        btnBackMemberSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iMemSearch = new Intent(getApplicationContext(), MemberSearchPage.class);
                iMemSearch.putExtra(KEY.COUNTRY_ID, idCountry);
                iMemSearch.putExtra(KEY.DIR_CLASS_NAME_KEY, "GraduationActivity");
                iMemSearch.putExtra(KEY.VILLAGE_NAME, tempSpinVillageName);
                iMemSearch.putExtra(KEY.VILLAGE_CODE, tempSpinVillageCode);

                finish();
                startActivity(iMemSearch);
//                goToSummaryActivity((Activity) mContext, idCountry);
            }
        });
    }

    private void viewReference() {
        /**
         * spinner
         */
        spAward = (Spinner) findViewById(R.id.sp_Award_Graduation);
        spProgram = (Spinner) findViewById(R.id.sp_Program_Graduation);
        spCriteria = (Spinner) findViewById(R.id.sp_Criteria_Graduation);
        /**
         * button
         */

        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnBackMemberSearch = (Button) findViewById(R.id.btnRegisterFooter);


        lv_garduation = (ListView) findViewById(R.id.lv_graduationList);
        edt_searchId = (EditText) findViewById(R.id.edt_searchId);


    }


    /**
     * LOAD :: Award
     */
    private void loadAward(final String cCode, final String memId) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.ADM_COUNTRY_AWARD_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + "='" + cCode + "'";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.ADM_COUNTRY_AWARD_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listAward);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spAward.setAdapter(dataAdapter);


        if (idAward != null) {
            for (int i = 0; i < spAward.getCount(); i++) {
                String award = spAward.getItemAtPosition(i).toString();
                if (award.equals(strAward)) {
                    position = i;
                }
            }
            spAward.setSelection(position);
        }


        spAward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strAward = ((SpinnerHelper) spAward.getSelectedItem()).getValue();
                idAward = ((SpinnerHelper) spAward.getSelectedItem()).getId();


                if (idAward.length() > 2) {
                    idDonor = idAward.substring(0, 2);
                    idAward = idAward.substring(2);
                    loadProgram(cCode, idDonor, idAward, memId);
                }
                Log.d(TAG, "idAward : " + idAward.substring(2) + " donor id :" + idAward.substring(0, 2));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * LOAD :: Program
     */
    private void loadProgram(final String idcCode, final String donorCode, final String awardCode, final String memId) {

        int position = 0;
        String criteria = SQLiteQuery.loadProgramWhereMemberAreAssigned_sql(idcCode, donorCode, awardCode, memId);


        // Spinner Drop down elements for District
        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listProgram);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spProgram.setAdapter(dataAdapter);


        if (idProgram != null) {
            for (int i = 0; i < spProgram.getCount(); i++) {
                String prog = spProgram.getItemAtPosition(i).toString();
                if (prog.equals(strProgram)) {
                    position = i;
                }
            }
            spProgram.setSelection(position);
        }


        spProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getValue();
                idProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getId();


                loadCriteria(idcCode, donorCode, awardCode, idProgram, memId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    /**
     * LOAD :: Criteria
     */
    private void loadCriteria(final String cCode, final String donorCode, final String awardCode, final String programCode, final String memId) {

        int position = 0;
        String criteria = SQLiteQuery.loadCriteria_sql(cCode, donorCode, awardCode, programCode, memId);

        // Spinner Drop down elements for District
        List<SpinnerHelper> listCriteria = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listCriteria);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spCriteria.setAdapter(dataAdapter);


        if (idCriteria != null) {
            for (int i = 0; i < spCriteria.getCount(); i++) {
                String award = spCriteria.getItemAtPosition(i).toString();
                if (award.equals(strCriteria)) {
                    position = i;
                }
            }
            spCriteria.setSelection(position);
        }


        spCriteria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCriteria = ((SpinnerHelper) spCriteria.getSelectedItem()).getValue();
                idCriteria = ((SpinnerHelper) spCriteria.getSelectedItem()).getId();

                if (Integer.parseInt(idCriteria) > 0) {
                    idService = idCriteria;
                    /**
                     * safety Block
                     */
                    if (edt_searchId.getText().toString().length() > 5)
                        loadGraduationGrid(cCode, donorCode, awardCode, programCode, idService, edt_searchId.getText().toString());
                    else
                        loadGraduationGrid(cCode, donorCode, awardCode, programCode, idService, "");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * LOAD :: Grid
     * This method load the list of GraduatedPeople
     *
     * @param countryCode Country Code
     * @param donorCode   Donor Code
     * @param awardCode   Award Code
     * @param programCode Program Code
     * @param serviceCode Service Code
     * @param memid       Member id
     */

    public void loadGraduationGrid(String countryCode, String donorCode, String awardCode, String programCode,
                                   String serviceCode, String memid) {
        Log.d(TAG, "In loadGraduationGrid List ");
        // use variable to like operation

        ArrayList<GraduationGridDataModel> graduationList = sqlH.getMemberGraduationStatusList(countryCode, donorCode, awardCode, programCode, serviceCode, memid);

        adapter = new GraduationGridAdapter(graduationList, this, strAward, strProgram, strCriteria, awardCode, programCode, donorCode, serviceCode);
        adapter.notifyDataSetChanged();
        lv_garduation.setAdapter(adapter);
        lv_garduation.setOnItemClickListener(this);
        lv_garduation.setFocusableInTouchMode(true);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * back press button is disable
     */
    @Override
    public void onBackPressed() {

    }

    /**
     * calling getWidth() and getHeight() too early:
     * When  the UI has not been sized and laid out on the screen yet..
     *
     * @param hasFocus the value will be true when UI is focus
     */

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        setUpMemberSearchButton();
        setUpHomeButton();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpMemberSearchButton() {
        btnBackMemberSearch.setText("");
        Drawable summeryImage = getResources().getDrawable(R.drawable.goto_back);
        btnBackMemberSearch.setCompoundDrawablesRelativeWithIntrinsicBounds(summeryImage, null, null, null);
        setPaddingButton(mContext, summeryImage, btnBackMemberSearch);
    }

    /**
     * Icon set by the method
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpHomeButton() {
        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, btnHome);
    }


}
