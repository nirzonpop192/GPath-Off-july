package com.siddiquinoor.restclient.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class CardRequestActivity extends BaseActivity  implements View.OnClickListener{

    private static final String TAG =CardRequestActivity.class.getSimpleName() ;
    private Spinner spAward;
    private Spinner spProgram;
    private Spinner spCardType;
    private Spinner spPrintReason;
    private View btnGone;

    private String strAward;

    private SQLiteHandler sqlH;
    private final Context mContext=CardRequestActivity.this;
    private String idCountry;
    private String strCounty;

    private String idSpAward;
    private String idAward;
    private String idDonor;
    private String idProgram;
    private String idCardType;
    private String idPrintReason;
    private String idReportGroup;
    private String idReportCode;

    private String strProgram;
    private String strCardType;

    private Button btnHome;
    private Button btnRequestSave;
    private Button btnDelevarySave;
    private ImageButton btnSearchCardReqt;
    private String strPrintReason;

    private ADNotificationManager errorDialog;
    private EditText edt_searchId;
    private TextView tv_LastCardSl;
    private TextView tv_GrdDate;
    private TextView tv_MemberName;
    private TextView tv_RequestDate;
    private TextView tv_PrintDate;
    private TextView tv_DeliveryDate;
    private TextView tv_Status;

    private String lastCardSl;
    private String grdDate;
    private String memberName;
    private String requestDate;
    private String printDate;
    private String deliveryDate;
    private String deliveryStatus;

   private String idDistrict;
   private String idUpzella;
   private String idUnion;
   private String idVillage;
   private String hhid;
   private String mmid;
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private String EntryBy;
    private String EntryDate;
    private String DelivaryBy;
    private String DelivaryDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_card_request);
        viewReference();
        sqlH=new SQLiteHandler(mContext);
        errorDialog=new ADNotificationManager();
        Intent intent=getIntent();
        idCountry=intent.getStringExtra("ID_COUNTRY");
        strCounty=intent.getStringExtra("STR_COUNTRY");
        loadAward(idCountry);
        loadCardType(idCountry);
       // loadCardPrintReason();

        setListener();

    }

    private void setListener() {
        btnHome.setOnClickListener(this);
        btnSearchCardReqt.setOnClickListener(this);
        btnRequestSave.setOnClickListener(this);
        btnDelevarySave.setOnClickListener(this);
    }

    private void viewReference() {
        spAward= (Spinner) findViewById(R.id.sp_Award_CardR);
        spProgram= (Spinner) findViewById(R.id.sp_Program_CardR);
        spCardType= (Spinner) findViewById(R.id.sp_CardTypeCR);
        btnHome= (Button) findViewById(R.id.btnHomeFooter);
        btnGone=  findViewById(R.id.btnRegisterFooter);
        btnGone.setVisibility(View.GONE);
        spPrintReason= (Spinner) findViewById(R.id.sp_cpReasonCR);
        btnSearchCardReqt= (ImageButton) findViewById(R.id.btn_searchCardRequest);
        edt_searchId= (EditText) findViewById(R.id.edt_searchId_CR);
        tv_LastCardSl= (TextView) findViewById(R.id.tv_lastCardSl_CR);
        tv_GrdDate= (TextView) findViewById(R.id.tv_graduation_CR);
        tv_MemberName= (TextView) findViewById(R.id.tv_memberName_CR);
        tv_RequestDate= (TextView) findViewById(R.id.tv_RequestDate_CR);
        tv_PrintDate= (TextView) findViewById(R.id.tv_PrintDate_CR);
        tv_DeliveryDate= (TextView) findViewById(R.id.tv_DeliveryDate_CR);
        tv_Status= (TextView) findViewById(R.id.tv_Status_CR);
        btnRequestSave= (Button) findViewById(R.id.btn_saveRequest_CR);
        btnDelevarySave= (Button) findViewById(R.id.btn_saveDelivary_CR);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnHomeFooter:
                finish();
                startActivity(new Intent(mContext,MainActivity.class));
                break;
            case R.id.btn_searchCardRequest:

                searchCardRequest();

                break;
           case  R.id.btn_saveRequest_CR :

               saveCardRequest();



            break;
            case R.id.btn_saveDelivary_CR:
                cardDelivaryDataSave();

                break;
        }
    }

    private void searchCardRequest() {

        String customId=edt_searchId.getText().toString();
        if (customId.length()<15){

            errorDialog.showErrorDialog(mContext,"Invalid Id "+
                    String.valueOf(15 - customId.length())+" digits are missing");
        }
        else if (idCardType.length()<3){
            errorDialog.showErrorDialog(mContext,"the Card type haven't select yet");
        }
        else if (idAward.equals("")||idAward==null||idAward.length()==0){
            errorDialog.showErrorDialog(mContext,"the Awarad");
        }

        else {
            loadData(customId);



        }

    }

    private void cardDelivaryDataSave() {

        if (requestDate.equals("")||printDate.equals("")){
            errorDialog.showInvalidDialog(mContext, "Invalid", "Save atame denied");}
        else if(!requestDate.equals("")&&printDate.equals("")){
            errorDialog.showInvalidDialog(mContext, "Invalid", "Save atame denied1");}
        else {
            if (!requestDate.equals("")&&!printDate.equals("")){
                try {
                    DelivaryBy = getStaffID();
                    DelivaryDate = getDateTime();
                    long id = sqlH.addCardDelivaryDate(idCountry, idDonor, idAward, idDistrict, idUpzella, idUnion
                            , idVillage, hhid, mmid, idReportGroup, idReportCode, tv_LastCardSl.getText().toString(),
                            idPrintReason, DelivaryDate, DelivaryBy);


                    SQLServerSyntaxGenerator cardDeliviry =new SQLServerSyntaxGenerator();
                    cardDeliviry.setAdmCountryCode(idCountry);
                    cardDeliviry.setAdmDonorCode(idDonor);
                    cardDeliviry.setAdmAwardCode(idAward);
                    cardDeliviry.setLayR1ListCode(idDistrict);
                    cardDeliviry.setLayR2ListCode(idUpzella);
                    cardDeliviry.setLayR3ListCode(idUnion);
                    cardDeliviry.setLayR4ListCode(idVillage);
                    cardDeliviry.setHHID(hhid);
                    cardDeliviry.setMemID(mmid);
                    cardDeliviry.setRptGroup(idReportGroup);
                    cardDeliviry.setRptCode(idReportCode);
                    cardDeliviry.setReasonCode(idPrintReason);
                    cardDeliviry.setRequestSL(tv_LastCardSl.getText().toString());
                    cardDeliviry.setDelevaryDate(DelivaryDate);
                    cardDeliviry.setDeliveredBy(DelivaryBy);
                    cardDeliviry.setEntryBy(EntryBy);
                    cardDeliviry.setEntryDate(EntryDate);
                    sqlH.insertIntoUploadTable(cardDeliviry.addCardDeliveryDateIntoRegNCardPrintTable());
                    Toast.makeText(mContext,"Save Delivary Date  id: "+id,Toast.LENGTH_SHORT).show();
                }catch (ParseException e){
                    Log.d(TAG,"Error");
                }

            }
        }
    }
    /**
     * Save the Card Request */

    private void saveCardRequest() {
        String customId=edt_searchId.getText().toString();
        if (tv_RequestDate.getText().toString().equals("")){
            updateDate();
        }
        try {
            EntryBy = getStaffID();
            EntryDate = getDateTime();
            if(Integer.parseInt(tv_LastCardSl.getText().toString())==0 && !strPrintReason.equals("NEW") ){
                errorDialog.showInvalidDialog(mContext, "Invalid", "Set Reason NEW");
            }
            else if(Integer.parseInt(tv_LastCardSl.getText().toString())>0 && strPrintReason.equals("NEW") ){
                errorDialog.showInvalidDialog(mContext, "Invalid", "Set Reason appropriately");
            }
            else if(!strPrintReason.equals("NEW")&&!requestDate.equals("")&&(printDate.equals("")|| deliveryDate.equals("")) ){}

            else {
                String lasSl="";
                if(requestDate.equals("")&&(printDate.equals("")|| deliveryDate.equals(""))){
                    int sl=Integer.parseInt(tv_LastCardSl.getText().toString())+1;
                    String strSlNo="";
                    strSlNo=strSlNo+String.valueOf(sl);

                    if (strSlNo.length()>0){
                        int padd=3-strSlNo.length();
                        for(int i=0;i<padd;i++){
                            lasSl="0"+strSlNo;
                        }
                    }
                    else {lasSl="000";}
                    String requestDate=tv_RequestDate.getText().toString();
                    long id = sqlH.addCardRequestDate(idCountry, idDonor, idAward, idDistrict, idUpzella, idUnion
                            , idVillage, hhid, mmid, idReportGroup, idReportCode, idPrintReason, lasSl,
                            requestDate, EntryBy, EntryDate);
                    SQLServerSyntaxGenerator cardRequest=new SQLServerSyntaxGenerator();
                    cardRequest.setAdmCountryCode(idCountry);
                    cardRequest.setAdmDonorCode(idDonor);
                    cardRequest.setAdmAwardCode(idAward);
                    cardRequest.setLayR1ListCode(idDistrict);
                    cardRequest.setLayR2ListCode(idUpzella);
                    cardRequest.setLayR3ListCode(idUnion);
                    cardRequest.setLayR4ListCode(idVillage);
                    cardRequest.setHHID(hhid);
                    cardRequest.setMemID(mmid);
                    cardRequest.setRptGroup(idReportGroup);
                    cardRequest.setRptCode(idReportCode);
                    cardRequest.setReasonCode(idPrintReason);
                    cardRequest.setRequestSL(lasSl);
                    cardRequest.setRequestDate(requestDate);
                    cardRequest.setEntryBy(EntryBy);
                    cardRequest.setEntryDate(EntryDate);
                    sqlH.insertIntoUploadTable(cardRequest.insertIntoRegNCardPrintTable());
                    Toast.makeText(mContext,"Save id: "+id,Toast.LENGTH_SHORT).show();
                    loadData(customId);
                }
                else {
                    errorDialog.showInvalidDialog(mContext,"Invalid","Invalid attame to save");
                }
            }
        }catch (ParseException e){
            Log.d(TAG," parse Exption ");
        }
    }

    private void loadData(String customId) {
        idDistrict =customId.substring(0,2);
        idUpzella =customId.substring(2,4);
        idUnion =customId.substring(4,6);
        idVillage =customId.substring(6,8);
        hhid=customId.substring(8, 13);
        mmid = customId.substring(13);

        lastCardSl = sqlH.getMaxCardRequesSl(idCountry,idDonor,idAward, idDistrict, idUpzella, idUnion, idVillage, hhid, mmid, idReportGroup, idReportCode);
        tv_LastCardSl.setText(lastCardSl);
        grdDate=sqlH.getProgramGraduationDateOfMember(idCountry, idDistrict, idUpzella, idUnion, idVillage, hhid, mmid, idDonor, idAward, idProgram);
        tv_GrdDate.setText(grdDate);
        memberName=sqlH.getMemberName(idCountry, idDistrict, idUpzella, idUnion, idVillage, hhid, mmid);
        tv_MemberName.setText(memberName);

        requestDate="";
        requestDate=sqlH.getCardRequestDate(idCountry, idDonor, idAward, idDistrict, idUpzella, idUnion, idVillage, hhid, mmid, idReportGroup, idReportCode, lastCardSl);
      /* Problem for Viewer for distugis the diffrence
        if (requestDate.equals("")){
            updateDate();
        }else {tv_RequestDate.setText(requestDate);}
*/
        tv_RequestDate.setText(requestDate);
        printDate=sqlH.getCardPrintDate(idCountry, idDonor, idAward, idDistrict, idUpzella, idUnion, idVillage, hhid, mmid, idReportGroup, idReportCode, lastCardSl);
        tv_PrintDate.setText(printDate);

        deliveryDate=sqlH.getCardDeliveryDate(idCountry, idDonor, idAward, idDistrict, idUpzella, idUnion, idVillage, hhid, mmid, idReportGroup, idReportCode, lastCardSl);
        tv_DeliveryDate.setText(deliveryDate);

        deliveryStatus=sqlH.getCardDeliveryStatus(idCountry, idDonor, idAward, idDistrict, idUpzella, idUnion, idVillage, hhid, mmid, idReportGroup, idReportCode, lastCardSl);
        String status;
        if (deliveryStatus.equals("Y")) { status="Yes";}
        else {status="No";}

        tv_Status.setText(status);
    }

    /**
     * DatePicker code Start
     **/
    public void updateDate(){
        tv_RequestDate.setText(format.format( calendar.getTime()));
    }

    /**
     * LOAD :: Award
     */
    private void loadAward(final String idCountry){

        int position=0;
        String criteria = " WHERE " + SQLiteHandler.ADM_COUNTRY_AWARD_TABLE +"."+ SQLiteHandler.ADM_COUNTRY_CODE_COL + "='" + idCountry + "'";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.ADM_COUNTRY_AWARD_TABLE, criteria, null,false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listAward);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spAward.setAdapter(dataAdapter);


        if (idSpAward != null) {
            for (int i = 0; i < spAward.getCount(); i++) {
                String award = spAward.getItemAtPosition(i).toString();
                if (award.equals(strAward)) {
                    position = i;
                }
            }
            spAward.setSelection( position );
        }



        spAward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strAward = ((SpinnerHelper) spAward.getSelectedItem()).getValue();
                idSpAward = ((SpinnerHelper) spAward.getSelectedItem()).getId();
                /** the Idaward= idSpAward+ idDonor*/

                // String donorId=idSpAward.substring(0,1)
                if (idSpAward.length()>2) {
                    idDonor = idSpAward.substring(0, 2);
                    idAward=idSpAward.substring(2);
                    loadProgram(idAward, idDonor, idCountry);
                }

                Log.d(TAG, "idSpAward : " + idSpAward.substring(2) + " donor id :" + idSpAward.substring(0, 2));
                //Log.d(TAG, "ID is: " + idDist);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * LOAD :: Program
     */
    private void loadProgram (final String idAward, final String donorId, final String idcCode){

        int position=0;
        String criteria = " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE +"."+ SQLiteHandler.ADM_AWARD_CODE_COL + "='" + idAward + "'"
                +" AND " +SQLiteHandler.COUNTRY_PROGRAM_TABLE +"."+ SQLiteHandler.ADM_DONOR_CODE_COL + "='" + donorId + "'" ;
        // Spinner Drop down elements for District
        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.COUNTRY_PROGRAM_TABLE, criteria, null,false);

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
            spProgram.setSelection( position );
        }

       /* if (!idDist.isEmpty()) {
            spDistrict.setSelection( getSpinnerIndex(spDistrict,idDist) );
        }*/

        spProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strProgram   =  ( (SpinnerHelper) spProgram.getSelectedItem() ).getValue();
                idProgram   = ( (SpinnerHelper) spProgram.getSelectedItem () ).getId();


                    Log.d(TAG, "load Prog data " + idProgram);

                loadCardPrintReason();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * LOAD :: Reason
     */
    private void loadCardPrintReason ( ){

        int position=0;
        String criteria ="";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.CARD_PRINT_REASON_TABLE, criteria, null,false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listProgram);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spPrintReason.setAdapter(dataAdapter);


        if (idPrintReason != null) {
            for (int i = 0; i < spPrintReason.getCount(); i++) {
                String cardPrintReason = spPrintReason.getItemAtPosition(i).toString();
                if (cardPrintReason.equals(strPrintReason)) {
                    position = i;
                }
            }
            spPrintReason.setSelection( position );
        }

       /* if (!idDist.isEmpty()) {
            spDistrict.setSelection( getSpinnerIndex(spDistrict,idDist) );
        }*/

        spPrintReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strPrintReason   =  ( (SpinnerHelper) spPrintReason.getSelectedItem () ).getValue();
                idPrintReason   = ( (SpinnerHelper) spPrintReason.getSelectedItem () ).getId();
                // if(idProgram.length()>2){
                Log.d(TAG, "load PrintReason data " + idPrintReason);
                // loadDistributionListView(idcCode,donorId,idSpAward,idCriteria.substring(0,3),idCriteria.substring(3));
                //  }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner





    /**
     * LOAD :: CardType
     */
    private void loadCardType (final String countryCode ){

        int position=0;
        String criteria =" WHERE "+SQLiteHandler.ADM_COUNTRY_CODE_COL +" = '"+countryCode +"' ";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.REPORT_TEMPLATE_TABLE, criteria, null,false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listProgram);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spCardType.setAdapter(dataAdapter);


        if (idCardType != null) {
            for (int i = 0; i < spCardType.getCount(); i++) {
                String cardType = spCardType.getItemAtPosition(i).toString();
                if (cardType.equals(strCardType)) {
                    position = i;
                }
            }
            spCardType.setSelection( position );
        }

       /* if (!idDist.isEmpty()) {
            spDistrict.setSelection( getSpinnerIndex(spDistrict,idDist) );
        }*/

        spCardType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCardType   =  ( (SpinnerHelper) spCardType.getSelectedItem () ).getValue();
                idCardType   = ( (SpinnerHelper) spCardType.getSelectedItem () ).getId();
                 if(idCardType.length()>2){
                     idReportGroup=idCardType.substring(0,2);
                     idReportCode=idCardType.substring(2);

                 }
                Log.d(TAG, "Card Report data: " + idCardType +" CardType : "+strCardType);
                // loadDistributionListView(idcCode,donorId,idSpAward,idCriteria.substring(0,3),idCriteria.substring(3));
                //  }

                //   loadCriteria(idSpAward, donorId, idProgram, idcCode);
                // Log.d(TAG, "idCriteria : " + idProgram);
                //Log.d(TAG, "ID is: " + idDist);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
    }
}
