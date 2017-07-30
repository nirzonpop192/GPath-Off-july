package com.siddiquinoor.restclient.activity.sub_activity.register_sub;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.SummaryMenuActivity;
import com.siddiquinoor.restclient.activity.OldAssignActivity;
import com.siddiquinoor.restclient.activity.DistributionActivity;
import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.activity.RegisterLiberia;
import com.siddiquinoor.restclient.activity.ServiceActivity;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.data_model.RegN_MM_libDataModel;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegisterMemberLiberia extends BaseActivity implements View.OnClickListener {


    AlertDialog goToDialog;
    Intent intent;
    String TAG = RegisterMemberLiberia.class.getSimpleName();



    private LinearLayout lLayoutMalawiContainer;
    private boolean is_edit;
    private String str_hhID;
    private String str_hhName;
    private String redirect;
    private String idCountry;
    private String str_district;
    private String str_upazilla;
    private String str_union;
    private String str_village;
    private String idDistrictCode;
    private String idUpazillaCode;
    private String idUnionCode;
    private String idVillageCode;
    private String str_entry_by;
    private String str_entry_date;
    private String str_regDate;
    private int pID;
    private TextView tvHHID;
    private TextView tvHHName;
    private EditText edtHHMemID;

    private SQLiteHandler sqlH;
    private Spinner spMarital;
    private Spinner spIDType;
    private Spinner spBscMem1Title;
    private Spinner spBscMem2Title;
    private Spinner spIDTypeForProxy;
    private Spinner spDesignatedProxy;
    private Spinner spProxyBscMem1Title;
    private Spinner spProxyBscMem2Title;
    private String codeMartial;
    private String codeIDType;
    private String codeIDTypeForProxy;
    private String codeBscMem1Title;
    private String codeBscMem2Title;
    private String codeProxyBscMem1Title;
    private String codeProxyBscMem2Title;
    private String codeDesignatedProxy;
    private Button btn_save;
    private Button btn_clear;
    private final Context mContext = RegisterMemberLiberia.this;
    private String str_hhMemID;
    private String str_MemNameFrist;
    private ADNotificationManager dialog;

    private EditText edtMemNameFirst;
    private EditText edtMemNameMiddle;
    private EditText edtMemNameLast;
    private EditText edtMemBirthYear;
    private EditText edtMemContact;
    private EditText edtMemTypedIDNo;
    private EditText edtV_bsc_Mem_1_NameFirst;
    private EditText edtV_bsc_Mem_1_NameMiddle;
    private EditText edtV_bsc_Mem_1_NameLast;

    private EditText edtV_bsc_Mem_2_NameFirst, edtV_bsc_Mem_2_NameMiddle, edtV_bsc_Mem_2_NameLast;

    private EditText edtProxyNameFirst, edtProxyNameMiddle, edtProxyNameLast;

    private EditText edtProxyBirthYear;
    private EditText edtProxyTypedIDNo;
    private EditText edtProxy_bsc_Mem_1_NameFirst, edtProxy_bsc_Mem_1_NameMiddle, edtProxy_bsc_Mem_1_NameLast;

    private EditText edtProxy_bsc_Mem_2_NameFirst, edtProxy_bsc_Mem_2_NameMiddle, edtProxy_bsc_Mem_2_NameLast;

    private EditText edtMem_lib_otherId;

    private ImageButton ibtn_memberImage;
    private ImageButton ibtn_proxyImage;
    private ImageView ivMemberImage;
    private ImageView ivProxyImage;
    // todo : image setup for proxy
    private boolean getImage;
    private Button btn_home;
    private Button btn_newHHRegistration;// new  house hold registration
    private String memImageEncoded;
    private String ProxyImageEncoded;
    private String strProgram;

   //private Spinner spAward, spProgram, spCriteria;

    private String idProgram;
    private String strAward;
    private String idAward;
    private String idCriteria;
    private String strCriteria;
    private String idDonor;
    Uri fileUri;
    private String imageName;


    private boolean savePermission;
   // private TextView tvMemRegDate;


    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public static String string = null;
    public static final int MEMBER_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int PROXY_CAPTURE_IMAGE_REQUEST_CODE = 200;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final String IMAGE_DIRECTORY_NAME = "PCIImages";

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat formatUSA = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);

    private String strRegDate;
    public String getStrRegDate() {
        return strRegDate;
    }

    public void setStrRegDate(String strRegDate) {
        this.strRegDate = strRegDate;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_nhh_mem_schema);
        viewReference();
        sqlH = new SQLiteHandler(this);
        dialog = new ADNotificationManager();

        lLayoutMalawiContainer.setVisibility(View.GONE);

        getDataFromIntent();
        tvHHID.setText(str_hhID);
        tvHHName.setText(str_hhName);
        if (is_edit) {
            getMemberDataFromDataBase(idCountry, idDistrictCode, idUpazillaCode, idUnionCode, idVillageCode, str_hhID, str_hhMemID);
            btn_save.setText("Upload");
        } else {
            setMemID(idCountry, idDistrictCode, idUpazillaCode, idUnionCode, idVillageCode, str_hhID);
        }
       /* if (getImage){
            Bundle extra=getIntent().getExtras();
            byte[] byteArray= extra.getByteArray(UniversalData.IMAGE_THAMBLE_BYTEARRAY);

         //memImageEncoded = extra.//Base64.encodeToString(byteArray, Base64.DEFAULT);
            memImageEncoded =extra.getString(UniversalData.ENCODED_IMAGE_STRING);
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
            Log.d(TAG,"memImageEncoded: "+ memImageEncoded);
            ivMemberImage.setImageBitmap(bitmap);

        }*/
        loadMaritalStatus();
        loadIdType();
        loadBscMember_1_Title();
        loadBscMember_2_Title();
        loadDesignatedProxy();
        loadIdTypeForProxy();
        loadProxyBscMember_1_Title();
        loadProxyBscMember_2_Title();
      //  loadAward(idCountry);

        setViewListener();
        // todo:disable Asssigne Button HEre
     //   llAssignController.setVisibility(View.INVISIBLE);



    }


    private void getMemberDataFromDataBase(String str_c_code, String str_districtCode, String str_upazillaCode, String str_unionCode, String str_villageCode, String str_hhID, String str_hhMemID) {
        RegN_MM_libDataModel mem = sqlH.getSingleLiberiaMemberData(str_c_code, str_districtCode, str_upazillaCode, str_unionCode, str_villageCode, str_hhID, str_hhMemID);
        codeMartial = mem.getCodeMartial();
        codeIDType = mem.getCodeIDType();
        codeIDTypeForProxy = mem.getCodeIDTypeForProxy();
        codeBscMem1Title = mem.getCodeBscMem1Title();
        codeBscMem2Title = mem.getCodeBscMem2Title();
        codeProxyBscMem1Title = mem.getCodeProxyBscMem1Title();
        codeProxyBscMem2Title = mem.getCodeProxyBscMem2Title();
        codeDesignatedProxy = mem.getCodeDesignatedProxy();

       byte[] im= mem.getMemberEncodedImage();
        setTextToEditText(str_hhMemID, mem.getStr_memOtherID(), mem.getStr_MemNameFirst(), mem.getStr_MemNameMiddle(), mem.getStr_MemNameLast(), mem.getStr_MemBirthYear(), mem.getStr_MemContact(), mem.getStrTypeIDNo(), mem.getStr_V_bsc_Mem_1_NameFirst(), mem.getStr_Proxy_bsc_Mem_1_NameMiddle(), mem.getStr_Proxy_bsc_Mem_1_NameLast(), mem.getStr_Proxy_bsc_Mem_2_NameFirst(), mem.getStr_Proxy_bsc_Mem_2_NameMiddle(), mem.getStr_Proxy_bsc_Mem_2_NameLast(), mem.getStr_ProxyNameFirst(), mem.getStr_ProxyNameMiddle(), mem.getStr_ProxyNameLast(), mem.getStr_ProxyBirthYear(), mem.getStr_ProxyTypedIDNo(), mem.getStr_Proxy_bsc_Mem_1_NameFirst(), mem.getStr_Proxy_bsc_Mem_1_NameMiddle(), mem.getStr_Proxy_bsc_Mem_1_NameLast(), mem.getStr_Proxy_bsc_Mem_2_NameFirst(), mem.getStr_Proxy_bsc_Mem_2_NameMiddle(), mem.getStr_Proxy_bsc_Mem_2_NameLast());
//        Bitmap bitmap =decodeImage(mem.getMemberEncodedImage());
        /*ivMemberImage.setImageBitmap(decodeImage(mem.getMemberEncodedImage()));
        ivProxyImage.setImageBitmap(decodeImage(mem.getProxyEncodedImage()));*/
    }

    private void setTextToEditText(String memId, String ortherID, String fName, String mName, String lName, String mBirth, String memContract, String typeIDno, String bscMem1_fName, String bscMem1_mName, String bscMem1_lName, String bscMem2_fName, String bscMem2_mName, String bscMem2_lName, String poxy_fName, String poxy_mName, String poxy_lName, String poxy_BirthY, String poxy_typeIDNo, String bscPoxy1_fName, String bscPoxy1_mName, String bscPoxy1_lName, String bscPoxy2_fName, String bscPoxy2_mName, String bscPoxy2_lName) {

        edtHHMemID.setText(memId);
        edtMemNameFirst.setText(fName);
        edtMem_lib_otherId.setText(ortherID);
        edtMemNameMiddle.setText(mName);
        edtMemNameLast.setText(lName);
        edtMemBirthYear.setText(mBirth);
        edtMemContact.setText(memContract);
        edtMemTypedIDNo.setText(typeIDno);
        edtV_bsc_Mem_1_NameFirst.setText(bscMem1_fName);
        edtV_bsc_Mem_1_NameMiddle.setText(bscMem1_mName);
        edtV_bsc_Mem_1_NameLast.setText(bscMem1_lName);
        edtV_bsc_Mem_2_NameFirst.setText(bscMem2_fName);
        edtV_bsc_Mem_2_NameMiddle.setText(bscMem2_mName);
        edtV_bsc_Mem_2_NameLast.setText(bscMem2_lName);
        edtProxyNameFirst.setText(poxy_fName);
        edtProxyNameMiddle.setText(poxy_mName);
        edtProxyNameLast.setText(poxy_lName);
        edtProxyBirthYear.setText(poxy_BirthY);
        edtProxyTypedIDNo.setText(poxy_typeIDNo);
        edtProxy_bsc_Mem_1_NameFirst.setText(bscPoxy1_fName);
        edtProxy_bsc_Mem_1_NameMiddle.setText(bscPoxy1_mName);
        edtProxy_bsc_Mem_1_NameLast.setText(bscPoxy1_lName);
        edtProxy_bsc_Mem_2_NameFirst.setText(bscPoxy2_fName);
        edtProxy_bsc_Mem_2_NameMiddle.setText(bscPoxy2_mName);
        edtProxy_bsc_Mem_2_NameLast.setText(bscPoxy2_lName);
    }

    /**
     * set all Listener here
     */

    private void setViewListener() {
        btn_save.setOnClickListener(this);
        ibtn_memberImage.setOnClickListener(this);
        btn_home.setOnClickListener(this);
        btn_home.setText("Go To ");
        btn_newHHRegistration.setOnClickListener(this);
        ibtn_proxyImage.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        //tvMemRegDate.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSaveMember:
                if (sqlH.getSavePermissionForHHEntries(idCountry, idDistrictCode, idUpazillaCode, idUnionCode, idVillageCode))
                    saveMember();
                else
                    dialog.showInfromDialog(mContext, "Save Denied", "You don't have Save Permission .Please contact with Admin");
                //  Log.d(TAG, "save button");
                break;
            case R.id.ibtnLibMemImage:
                // set the Image Name
                setImageName(idCountry + idDistrictCode + idUpazillaCode + idUnionCode + idVillageCode + str_hhID + edtHHMemID.getText().toString());
                // startActivity(gotoCamera);
                if (!isDeviceSupportCamera()) {
                    Toast.makeText(getApplicationContext(), "Sorry! Your device doesn't support camera", Toast.LENGTH_LONG).show();
                    //finish();
                } else
                    captureImage(MEMBER_CAPTURE_IMAGE_REQUEST_CODE);
                break;
            case R.id.btnHomeFooter:
                /*Intent goHome = new Intent(mContext, MainActivity.class);
                startActivity(goHome);*/
                goToAlert();

                break;

            case R.id.btnRegisterFooter:
                Intent goRegistrationPage = new Intent(mContext, RegisterLiberia.class);
                goRegistrationPage.putExtra(KEY.COUNTRY_CODE, idCountry);
                startActivity(goRegistrationPage);
                break;
            case R.id.ibtnLibProxyCamera:
                /**  Go To Camera */
                setImageName("");

                setImageName("PROXY" + idCountry + idDistrictCode + idUpazillaCode + idUnionCode + idVillageCode + str_hhID + edtHHMemID.getText().toString());
                // startActivity(gotoCamera);
                if (!isDeviceSupportCamera()) {
                    Toast.makeText(getApplicationContext(), "Sorry! Your device doesn't support camera", Toast.LENGTH_LONG).show();
                    //finish();
                } else
                    captureImage(PROXY_CAPTURE_IMAGE_REQUEST_CODE);
                break;
            case R.id.btnClearMember:
                clearPage();
                break;
           /* case R.id.tv_mm_reg_date:
                setRegDate();
                break;*/

        }
    }

    public void updateRegDate() {
        setStrRegDate(format.format(calendar.getTime()));
       // tvMemRegDate.setText(formatUSA.format(calendar.getTime()));
    }

    public void setRegDate() {
        new DatePickerDialog(mContext, d, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
           // updateRegDate();
        }
    };

    private void clearPage() {
        setTextToEditText("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        resetIds();
    }

    private void resetIds() {
        codeMartial = "0";
        codeIDType = "0";
        codeIDTypeForProxy = "0";
        codeBscMem1Title = "0";
        codeBscMem2Title = "0";
        codeProxyBscMem1Title = "0";
        codeProxyBscMem2Title = "0";
        codeDesignatedProxy = "0";
    }
    private void saveMember() {
        str_hhMemID = edtHHMemID.getText().toString();
        str_MemNameFrist = edtMemNameFirst.getText().toString();
        String str_Mem_lib_otherId = edtMem_lib_otherId.getText().toString();
        String str_MemNameMiddle = edtMemNameMiddle.getText().toString();
        String str_MemNameLast = edtMemNameLast.getText().toString();
        String str_MemBirthYear = edtMemBirthYear.getText().toString();
        String str_MemContact = edtMemContact.getText().toString();
        String str_MemTypedIDNo = edtMemTypedIDNo.getText().toString();
        String str_V_bsc_Mem_1_NameFirst = edtV_bsc_Mem_1_NameFirst.getText().toString();
        String str_V_bsc_Mem_1_NameMiddle = edtV_bsc_Mem_1_NameMiddle.getText().toString();
        String str_V_bsc_Mem_1_NameLast = edtV_bsc_Mem_1_NameLast.getText().toString();
        String str_V_bsc_Mem_2_NameFirst = edtV_bsc_Mem_2_NameFirst.getText().toString();
        String str_V_bsc_Mem_2_NameMiddle = edtV_bsc_Mem_2_NameMiddle.getText().toString();
        String str_V_bsc_Mem_2_NameLast = edtV_bsc_Mem_2_NameLast.getText().toString();
        String str_ProxyNameFirst = edtProxyNameFirst.getText().toString();
        String str_ProxyNameMiddle = edtProxyNameMiddle.getText().toString();
        String str_ProxyNameLast = edtProxyNameLast.getText().toString();
        String str_ProxyBirthYear = edtProxyBirthYear.getText().toString();
        String str_ProxyTypedIDNo = edtProxyTypedIDNo.getText().toString();
        String str_Proxy_bsc_Mem_1_NameFirst = edtProxy_bsc_Mem_1_NameFirst.getText().toString();
        String str_Proxy_bsc_Mem_1_NameMiddle = edtProxy_bsc_Mem_1_NameMiddle.getText().toString();
        String str_Proxy_bsc_Mem_1_NameLast = edtProxy_bsc_Mem_1_NameLast.getText().toString();
        String str_Proxy_bsc_Mem_2_NameFirst = edtProxy_bsc_Mem_2_NameFirst.getText().toString();
        String str_Proxy_bsc_Mem_2_NameMiddle = edtProxy_bsc_Mem_2_NameMiddle.getText().toString();
        String str_Proxy_bsc_Mem_2_NameLast = edtProxy_bsc_Mem_2_NameLast.getText().toString();

        boolean invalid = false;
        try {
            str_entry_by=getStaffID();
            str_entry_date=getDateTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // TODO :: Need to check valid date range collect from online

        if (str_hhMemID.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Enter any ID", Toast.LENGTH_SHORT).show();
        }

        else if (str_Mem_lib_otherId.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Other ID");

        }
        else if (str_MemNameFrist.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Name. Enter Person Name");

        } else if (str_V_bsc_Mem_1_NameFirst.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Verified by: BSC Member 1  First Name. Enter Person Name");

        } else if (str_V_bsc_Mem_1_NameMiddle.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Verified by: BSC Member 1  Middle. Name. Enter Person Name");

        } else if (str_V_bsc_Mem_1_NameLast.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Verified by: BSC Member 1  Last Name. Enter Person Name");

        } else if (str_V_bsc_Mem_2_NameFirst.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Verified by: BSC Member 2  First Name. Enter Person Name");

        } else if (str_V_bsc_Mem_2_NameMiddle.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Verified by: BSC Member 2  Middle. Name. Enter Person Name");

        } else if (str_V_bsc_Mem_2_NameLast.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Verified by: BSC Member 2  Last Name. Enter Person Name");

        }// else if( codeDesignatedProxy.equals("Y") ){
        // if the the Proxy designated yes Then proxy Name must be entered
        else if (str_ProxyNameFirst.equals("") && codeDesignatedProxy.equals("Y")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Proxy's  First Name. Enter Person Name");

        } else if (str_ProxyNameMiddle.equals("") && codeDesignatedProxy.equals("Y")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Proxy's  Middle. Name. Enter Person Name");

        } else if (str_ProxyNameLast.equals("") && codeDesignatedProxy.equals("Y")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Proxy's  Last Name. Enter Person Name");

        }
        else if (str_MemBirthYear.equals("") || str_MemBirthYear.length()!=4) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Invalid year");

        }
        // }

      /*  else if (Integer.valueOf(str_age) > 99) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Age exceeds allowable range.");
            //Toast.makeText(getApplicationContext(), "Please select a Age", Toast.LENGTH_SHORT).show();
        } else if (str_relation.equals("Select Relation")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing relation. Please select a Relation");
            // Toast.makeText(getApplicationContext(), "Missing relation. Please select a Relation", Toast.LENGTH_SHORT).show();
        }*/


        else if (invalid == false) {


            // Create object to Upload web end
            SQLServerSyntaxGenerator regLiberiaMember = new SQLServerSyntaxGenerator();


            regLiberiaMember.setAdmCountryCode(idCountry);
            regLiberiaMember.setLayR1ListCode(idDistrictCode);
            regLiberiaMember.setLayR2ListCode(idUpazillaCode);
            regLiberiaMember.setLayR3ListCode(idUnionCode);
            regLiberiaMember.setLayR4ListCode(idVillageCode);
            regLiberiaMember.setHHID(str_hhID);
            regLiberiaMember.setMemID(str_hhMemID);
            regLiberiaMember.setMmMemName(str_MemNameFrist);
            regLiberiaMember.setEntryBy(str_entry_by);
            regLiberiaMember.setEntryDate(str_entry_date);
            regLiberiaMember.setRegNDate(str_regDate);
            regLiberiaMember.setMmBirthYear(str_MemBirthYear);
            regLiberiaMember.setMmMaritalStatus(codeMartial);
            regLiberiaMember.setMmContactNo(str_MemContact);
            regLiberiaMember.setMmMemOtherID(str_Mem_lib_otherId);
            regLiberiaMember.setMmMemName_First(str_MemNameFrist);
            regLiberiaMember.setMmMemName_Middle(str_MemNameMiddle);
            regLiberiaMember.setMmMemName_Last(str_MemNameLast);
            if(memImageEncoded!=null)
                regLiberiaMember.setMmPhoto(memImageEncoded.toString());

            regLiberiaMember.setMmType_ID(codeIDType);
            regLiberiaMember.setMmID_NO(str_MemTypedIDNo);
            regLiberiaMember.setMmV_BSCMemName1_First(str_V_bsc_Mem_1_NameFirst);
            regLiberiaMember.setMmV_BSCMemName1_Middle(str_V_bsc_Mem_1_NameMiddle);
            regLiberiaMember.setMmV_BSCMemName1_Last(str_V_bsc_Mem_1_NameLast);
            regLiberiaMember.setMmV_BSCMem1_TitlePosition(codeBscMem1Title);
            regLiberiaMember.setMmV_BSCMemName2_First(str_V_bsc_Mem_2_NameFirst);
            regLiberiaMember.setMmV_BSCMemName2_Middle(str_V_bsc_Mem_2_NameMiddle);
            regLiberiaMember.setMmV_BSCMemName2_Last(str_V_bsc_Mem_2_NameLast);
            regLiberiaMember.setMmV_BSCMem2_TitlePosition(codeBscMem2Title);
            regLiberiaMember.setMmProxy_Designation(codeDesignatedProxy);
            regLiberiaMember.setMmProxy_Name_First(str_ProxyNameFirst);
            regLiberiaMember.setMmProxy_Name_Middle(str_ProxyNameMiddle);
            regLiberiaMember.setMmProxy_Name_Last(str_ProxyNameLast);
            regLiberiaMember.setMmProxy_BirthYear(str_ProxyBirthYear);
            if(ProxyImageEncoded!=null)
                regLiberiaMember.setMmProxy_Photo(ProxyImageEncoded.toString());

            regLiberiaMember.setMmProxy_Type_ID(codeIDTypeForProxy);
            regLiberiaMember.setMmProxy_ID_NO(str_ProxyTypedIDNo);
            regLiberiaMember.setMmP_BSCMemName1_First(str_Proxy_bsc_Mem_1_NameFirst);
            regLiberiaMember.setMmP_BSCMemName1_Middle(str_Proxy_bsc_Mem_1_NameMiddle);
            regLiberiaMember.setMmP_BSCMemName1_Last(str_Proxy_bsc_Mem_1_NameLast);
            regLiberiaMember.setMmP_BSCMem1_TitlePosition(codeBscMem1Title);
            regLiberiaMember.setMmP_BSCMemName2_First(str_Proxy_bsc_Mem_2_NameFirst);
            regLiberiaMember.setMmP_BSCMemName2_Middle(str_Proxy_bsc_Mem_2_NameMiddle);
            regLiberiaMember.setMmP_BSCMemName2_Last(str_Proxy_bsc_Mem_2_NameLast);
            regLiberiaMember.setMmP_BSCMem2_TitlePosition(codeBscMem2Title);

            if (is_edit) {


                // Update Member data
                sqlH.editLiberiaMemberData(idCountry, idDistrictCode, idUpazillaCode, idUnionCode, idVillageCode, str_hhID, str_hhMemID, str_regDate, str_Mem_lib_otherId,
                        str_MemNameFrist, str_MemNameMiddle, str_MemNameLast, str_MemBirthYear, codeMartial, str_MemContact,
                        memImageEncoded, codeIDType, str_MemTypedIDNo,
                        str_V_bsc_Mem_1_NameFirst, str_V_bsc_Mem_1_NameMiddle, str_V_bsc_Mem_1_NameLast, codeBscMem1Title
                        , str_V_bsc_Mem_2_NameFirst, str_V_bsc_Mem_2_NameMiddle, str_V_bsc_Mem_2_NameLast, codeBscMem2Title,
                        codeDesignatedProxy, str_ProxyNameFirst, str_ProxyNameMiddle, str_ProxyNameLast, str_ProxyBirthYear
                        , ProxyImageEncoded
                        , codeIDTypeForProxy,
                        str_ProxyTypedIDNo,
                        str_Proxy_bsc_Mem_1_NameFirst, str_Proxy_bsc_Mem_1_NameMiddle, str_Proxy_bsc_Mem_1_NameLast, codeBscMem1Title,
                        str_Proxy_bsc_Mem_2_NameFirst, str_Proxy_bsc_Mem_2_NameMiddle, str_Proxy_bsc_Mem_2_NameLast, codeBscMem2Title,
                        str_entry_by, str_entry_date);
                Toast.makeText(mContext, "The member has been uploaded  ", Toast.LENGTH_SHORT).show();
                sqlH.insertIntoUploadTable(regLiberiaMember.updateLiberiaMember());
                Log.d(TAG,"Liberia Member Upload Syntax : "+regLiberiaMember.updateLiberiaMember());
                // goToNextPage(idCountry, idDistrictCode, idUpazillaCode, idUnionCode, idVillageCode, str_hhID, redirect);
                // setIsMemberSaved(true);
            } else {
                /**
                 * Insert procedure
                 * */
                String value = sqlH.getMemberAgeTypeFlag(idCountry,idDistrictCode,idUpazillaCode,idUnionCode,idVillageCode,str_hhID,str_hhMemID);
                sqlH.updateMemTypeFlagInMemTable(idCountry,idDistrictCode,idUpazillaCode,idUnionCode,idVillageCode,str_hhID,str_hhMemID,value);


                long id = sqlH.addMemberDataForLiberia(idCountry, idDistrictCode, idUpazillaCode, idUnionCode, idVillageCode, str_hhID, str_hhMemID,
                        str_regDate, str_Mem_lib_otherId, str_MemNameFrist, str_MemNameMiddle, str_MemNameLast, str_MemBirthYear, codeMartial, str_MemContact, memImageEncoded, codeIDType, str_MemTypedIDNo,
                        str_V_bsc_Mem_1_NameFirst, str_V_bsc_Mem_1_NameMiddle, str_V_bsc_Mem_1_NameLast, codeBscMem1Title
                        , str_V_bsc_Mem_2_NameFirst, str_V_bsc_Mem_2_NameMiddle, str_V_bsc_Mem_2_NameLast, codeBscMem2Title,
                        codeDesignatedProxy, str_ProxyNameFirst, str_ProxyNameMiddle, str_ProxyNameLast, str_ProxyBirthYear
                        , ProxyImageEncoded
                        , codeIDTypeForProxy,
                        str_ProxyTypedIDNo,
                        str_Proxy_bsc_Mem_1_NameFirst, str_Proxy_bsc_Mem_1_NameMiddle, str_Proxy_bsc_Mem_1_NameLast, codeBscMem1Title,
                        str_Proxy_bsc_Mem_2_NameFirst, str_Proxy_bsc_Mem_2_NameMiddle, str_Proxy_bsc_Mem_2_NameLast, codeBscMem2Title,
                        str_entry_by, str_entry_date,value);

                // for web upload
                sqlH.insertIntoUploadTable(regLiberiaMember.insertIntoRegNMemberForLiberia());
                Toast.makeText(mContext, "member id :" + id, Toast.LENGTH_SHORT).show();

                // todo: enable Assigne Button here

                //   llAssignController.setVisibility(View.VISIBLE);

         /*
                Toast.makeText(getApplicationContext(), "Member added Successfully...", Toast.LENGTH_LONG).show();
                setIsMemberSaved(true);*/
                //  this.finish();

                      /*  if(!redirect.isEmpty()){
                            goToNextPage(idCountry, idDistrictCode, idUpazillaCode, idUnionCode, idVillageCode, str_hhID, redirect);
                        }else{
                            Intent dIntent = new Intent(RegisterMember.this, RegisterMember.class);

                            dIntent.putExtra("redirect", "");
                            dIntent.putExtra("str_hhID", str_hhID);
                            dIntent.putExtra("str_hhName", str_hhName);
                            dIntent.putExtra("idCountry", idCountry);

                            dIntent.putExtra("str_district", str_district);
                            dIntent.putExtra("str_upazilla", str_upazilla);
                            dIntent.putExtra("str_union", str_union);
                            dIntent.putExtra("str_village", str_village);

                            dIntent.putExtra("idDistrictCode", idDistrictCode);
                            dIntent.putExtra("idUpazillaCode", idUpazillaCode);
                            dIntent.putExtra("idUnionCode", idUnionCode);
                            dIntent.putExtra("idVillageCode", idVillageCode);


                            dIntent.putExtra("str_entry_by", str_entry_by);
                            dIntent.putExtra("str_entry_date", str_entry_date);

                            startActivity(dIntent);
                        }*/
            }
        }
    }

   /* private void saveMember() {
        str_hhMemID = edtHHMemID.getText().toString();
        str_MemNameFrist = edtMemNameFirst.getText().toString();
        String str_Mem_lib_otherId = edtMem_lib_otherId.getText().toString();
        String str_MemNameMiddle = edtMemNameMiddle.getText().toString();
        String str_MemNameLast = edtMemNameLast.getText().toString();
        String str_MemBirthYear = edtMemBirthYear.getText().toString();
        String str_MemContact = edtMemContact.getText().toString();
        String str_MemTypedIDNo = edtMemTypedIDNo.getText().toString();
        String str_V_bsc_Mem_1_NameFirst = edtV_bsc_Mem_1_NameFirst.getText().toString();
        String str_V_bsc_Mem_1_NameMiddle = edtV_bsc_Mem_1_NameMiddle.getText().toString();
        String str_V_bsc_Mem_1_NameLast = edtV_bsc_Mem_1_NameLast.getText().toString();
        String str_V_bsc_Mem_2_NameFirst = edtV_bsc_Mem_2_NameFirst.getText().toString();
        String str_V_bsc_Mem_2_NameMiddle = edtV_bsc_Mem_2_NameMiddle.getText().toString();
        String str_V_bsc_Mem_2_NameLast = edtV_bsc_Mem_2_NameLast.getText().toString();
        String str_ProxyNameFirst = edtProxyNameFirst.getText().toString();
        String str_ProxyNameMiddle = edtProxyNameMiddle.getText().toString();
        String str_ProxyNameLast = edtProxyNameLast.getText().toString();
        String str_ProxyBirthYear = edtProxyBirthYear.getText().toString();
        String str_ProxyTypedIDNo = edtProxyTypedIDNo.getText().toString();
        String str_Proxy_bsc_Mem_1_NameFirst = edtProxy_bsc_Mem_1_NameFirst.getText().toString();
        String str_Proxy_bsc_Mem_1_NameMiddle = edtProxy_bsc_Mem_1_NameMiddle.getText().toString();
        String str_Proxy_bsc_Mem_1_NameLast = edtProxy_bsc_Mem_1_NameLast.getText().toString();
        String str_Proxy_bsc_Mem_2_NameFirst = edtProxy_bsc_Mem_2_NameFirst.getText().toString();
        String str_Proxy_bsc_Mem_2_NameMiddle = edtProxy_bsc_Mem_2_NameMiddle.getText().toString();
        String str_Proxy_bsc_Mem_2_NameLast = edtProxy_bsc_Mem_2_NameLast.getText().toString();

        boolean invalid = false;
        try {
            str_entry_by=getStaffID();
            str_entry_date=getDateTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // TODO :: Need to check valid date range collect from online

        if (str_hhMemID.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Enter any ID", Toast.LENGTH_SHORT).show();
        }

        else if (str_Mem_lib_otherId.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Other ID");

        }
        else if (str_MemNameFrist.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Name. Enter Person Name");

        } else if (str_V_bsc_Mem_1_NameFirst.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Verified by: BSC Member 1  First Name. Enter Person Name");

        } else if (str_V_bsc_Mem_1_NameMiddle.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Verified by: BSC Member 1  Middle. Name. Enter Person Name");

        } else if (str_V_bsc_Mem_1_NameLast.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Verified by: BSC Member 1  Last Name. Enter Person Name");

        } else if (str_V_bsc_Mem_2_NameFirst.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Verified by: BSC Member 2  First Name. Enter Person Name");

        } else if (str_V_bsc_Mem_2_NameMiddle.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Verified by: BSC Member 2  Middle. Name. Enter Person Name");

        } else if (str_V_bsc_Mem_2_NameLast.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Verified by: BSC Member 2  Last Name. Enter Person Name");

        }// else if( codeDesignatedProxy.equals("Y") ){
        // if the the Proxy designated yes Then proxy Name must be entered
        else if (str_ProxyNameFirst.equals("") && codeDesignatedProxy.equals("Y")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Proxy's  First Name. Enter Person Name");

        } else if (str_ProxyNameMiddle.equals("") && codeDesignatedProxy.equals("Y")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Proxy's  Middle. Name. Enter Person Name");

        } else if (str_ProxyNameLast.equals("") && codeDesignatedProxy.equals("Y")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Proxy's  Last Name. Enter Person Name");

        }
        else if (str_MemBirthYear.equals("") || str_MemBirthYear.length()!=4) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Invalid year");

        }
        // }

      /*  else if (Integer.valueOf(str_age) > 99) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Age exceeds allowable range.");
            //Toast.makeText(getApplicationContext(), "Please select a Age", Toast.LENGTH_SHORT).show();
        } else if (str_relation.equals("Select Relation")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing relation. Please select a Relation");
            // Toast.makeText(getApplicationContext(), "Missing relation. Please select a Relation", Toast.LENGTH_SHORT).show();
        }*/


//        else if (invalid == false) {
//
//
//            // Create object to Upload web end
//            SQLServerSyntaxGenerator regLiberiaMember = new SQLServerSyntaxGenerator();
//
//
//            regLiberiaMember.setAdmCountryCode(idCountry);
//            regLiberiaMember.setLayR1ListCode(idDistrictCode);
//            regLiberiaMember.setLayR2ListCode(idUpazillaCode);
//            regLiberiaMember.setLayR3ListCode(idUnionCode);
//            regLiberiaMember.setLayR4ListCode(idVillageCode);
//            regLiberiaMember.setHHID(str_hhID);
//            regLiberiaMember.setMemID(str_hhMemID);
//            regLiberiaMember.setMmMemName(str_MemNameFrist);
//            regLiberiaMember.setEntryBy(str_entry_by);
//            regLiberiaMember.setEntryDate(str_entry_date);
//            regLiberiaMember.setRegNDate(str_regDate);
//            regLiberiaMember.setMmBirthYear(str_MemBirthYear);
//            regLiberiaMember.setMmMaritalStatus(codeMartial);
//            regLiberiaMember.setMmContactNo(str_MemContact);
//            regLiberiaMember.setMmMemOtherID(str_Mem_lib_otherId);
//            regLiberiaMember.setMmMemName_First(str_MemNameFrist);
//            regLiberiaMember.setMmMemName_Middle(str_MemNameMiddle);
//            regLiberiaMember.setMmMemName_Last(str_MemNameLast);
//            if(memImageEncoded!=null)
//                 regLiberiaMember.setMmPhoto(memImageEncoded.toString());
//
//            regLiberiaMember.setMmType_ID(codeIDType);
//            regLiberiaMember.setMmID_NO(str_MemTypedIDNo);
//            regLiberiaMember.setMmV_BSCMemName1_First(str_V_bsc_Mem_1_NameFirst);
//            regLiberiaMember.setMmV_BSCMemName1_Middle(str_V_bsc_Mem_1_NameMiddle);
//            regLiberiaMember.setMmV_BSCMemName1_Last(str_V_bsc_Mem_1_NameLast);
//            regLiberiaMember.setMmV_BSCMem1_TitlePosition(codeBscMem1Title);
//            regLiberiaMember.setMmV_BSCMemName2_First(str_V_bsc_Mem_2_NameFirst);
//            regLiberiaMember.setMmV_BSCMemName2_Middle(str_V_bsc_Mem_2_NameMiddle);
//            regLiberiaMember.setMmV_BSCMemName2_Last(str_V_bsc_Mem_2_NameLast);
//            regLiberiaMember.setMmV_BSCMem2_TitlePosition(codeBscMem2Title);
//            regLiberiaMember.setMmProxy_Designation(codeDesignatedProxy);
//            regLiberiaMember.setMmProxy_Name_First(str_ProxyNameFirst);
//            regLiberiaMember.setMmProxy_Name_Middle(str_ProxyNameMiddle);
//            regLiberiaMember.setMmProxy_Name_Last(str_ProxyNameLast);
//            regLiberiaMember.setMmProxy_BirthYear(str_ProxyBirthYear);
//            if(ProxyImageEncoded!=null)
//                regLiberiaMember.setMmProxy_Photo(ProxyImageEncoded.toString());
//
//            regLiberiaMember.setMmProxy_Type_ID(codeIDTypeForProxy);
//            regLiberiaMember.setMmProxy_ID_NO(str_ProxyTypedIDNo);
//            regLiberiaMember.setMmP_BSCMemName1_First(str_Proxy_bsc_Mem_1_NameFirst);
//            regLiberiaMember.setMmP_BSCMemName1_Middle(str_Proxy_bsc_Mem_1_NameMiddle);
//            regLiberiaMember.setMmP_BSCMemName1_Last(str_Proxy_bsc_Mem_1_NameLast);
//            regLiberiaMember.setMmP_BSCMem1_TitlePosition(codeBscMem1Title);
//            regLiberiaMember.setMmP_BSCMemName2_First(str_Proxy_bsc_Mem_2_NameFirst);
//            regLiberiaMember.setMmP_BSCMemName2_Middle(str_Proxy_bsc_Mem_2_NameMiddle);
//            regLiberiaMember.setMmP_BSCMemName2_Last(str_Proxy_bsc_Mem_2_NameLast);
//            regLiberiaMember.setMmP_BSCMem2_TitlePosition(codeBscMem2Title);
//
//            if (is_edit) {
//
//
//                // Update Member data
//                sqlH.editLiberiaMemberData(idCountry, idDistrictCode, idUpazillaCode, idUnionCode, idVillageCode, str_hhID, str_hhMemID, str_regDate, str_Mem_lib_otherId,
//                        str_MemNameFrist, str_MemNameMiddle, str_MemNameLast, str_MemBirthYear, codeMartial, str_MemContact,
//                        memImageEncoded, codeIDType, str_MemTypedIDNo,
//                        str_V_bsc_Mem_1_NameFirst, str_V_bsc_Mem_1_NameMiddle, str_V_bsc_Mem_1_NameLast, codeBscMem1Title
//                        , str_V_bsc_Mem_2_NameFirst, str_V_bsc_Mem_2_NameMiddle, str_V_bsc_Mem_2_NameLast, codeBscMem2Title,
//                        codeDesignatedProxy, str_ProxyNameFirst, str_ProxyNameMiddle, str_ProxyNameLast, str_ProxyBirthYear
//                        , ProxyImageEncoded
//                        , codeIDTypeForProxy,
//                        str_ProxyTypedIDNo,
//                        str_Proxy_bsc_Mem_1_NameFirst, str_Proxy_bsc_Mem_1_NameMiddle, str_Proxy_bsc_Mem_1_NameLast, codeBscMem1Title,
//                        str_Proxy_bsc_Mem_2_NameFirst, str_Proxy_bsc_Mem_2_NameMiddle, str_Proxy_bsc_Mem_2_NameLast, codeBscMem2Title,
//                        str_entry_by, str_entry_date);
//                Toast.makeText(mContext, "The member has been uploaded  ", Toast.LENGTH_SHORT).show();
//                sqlH.insertIntoUploadTable(regLiberiaMember.updateLiberiaMember());
//                Log.d(TAG,"Liberia Member Upload Syntax : "+regLiberiaMember.updateLiberiaMember());
//                // goToNextPage(idCountry, idDistrictCode, idUpazillaCode, idUnionCode, idVillageCode, str_hhID, redirect);
//                // setIsMemberSaved(true);
//            } else {
//                /**
//                 * Insert procedure
//                 * */
//                long id = sqlH.addMemberDataForLiberia(idCountry, idDistrictCode, idUpazillaCode, idUnionCode, idVillageCode, str_hhID, str_hhMemID, str_regDate, str_Mem_lib_otherId, str_MemNameFrist, str_MemNameMiddle, str_MemNameLast, str_MemBirthYear, codeMartial, str_MemContact, memImageEncoded, codeIDType, str_MemTypedIDNo,
//                        str_V_bsc_Mem_1_NameFirst, str_V_bsc_Mem_1_NameMiddle, str_V_bsc_Mem_1_NameLast, codeBscMem1Title
//                        , str_V_bsc_Mem_2_NameFirst, str_V_bsc_Mem_2_NameMiddle, str_V_bsc_Mem_2_NameLast, codeBscMem2Title,
//                        codeDesignatedProxy, str_ProxyNameFirst, str_ProxyNameMiddle, str_ProxyNameLast, str_ProxyBirthYear
//                        , ProxyImageEncoded
//                        , codeIDTypeForProxy,
//                        str_ProxyTypedIDNo,
//                        str_Proxy_bsc_Mem_1_NameFirst, str_Proxy_bsc_Mem_1_NameMiddle, str_Proxy_bsc_Mem_1_NameLast, codeBscMem1Title,
//                        str_Proxy_bsc_Mem_2_NameFirst, str_Proxy_bsc_Mem_2_NameMiddle, str_Proxy_bsc_Mem_2_NameLast, codeBscMem2Title,
//                        str_entry_by, str_entry_date);
//
//                // for web upload
//                sqlH.insertIntoUploadTable(regLiberiaMember.insertIntoRegNMemberForLiberia());
//                Toast.makeText(mContext, "member id :" + id, Toast.LENGTH_SHORT).show();

                // todo: enable Assigne Button here

             //   llAssignController.setVisibility(View.VISIBLE);

         /*
                Toast.makeText(getApplicationContext(), "Member added Successfully...", Toast.LENGTH_LONG).show();
                setIsMemberSaved(true);*/
                //  this.finish();

                      /*  if(!redirect.isEmpty()){
                            goToNextPage(idCountry, idDistrictCode, idUpazillaCode, idUnionCode, idVillageCode, str_hhID, redirect);
                        }else{
                            Intent dIntent = new Intent(RegisterMember.this, RegisterMember.class);

                            dIntent.putExtra("redirect", "");
                            dIntent.putExtra("str_hhID", str_hhID);
                            dIntent.putExtra("str_hhName", str_hhName);
                            dIntent.putExtra("idCountry", idCountry);

                            dIntent.putExtra("str_district", str_district);
                            dIntent.putExtra("str_upazilla", str_upazilla);
                            dIntent.putExtra("str_union", str_union);
                            dIntent.putExtra("str_village", str_village);

                            dIntent.putExtra("idDistrictCode", idDistrictCode);
                            dIntent.putExtra("idUpazillaCode", idUpazillaCode);
                            dIntent.putExtra("idUnionCode", idUnionCode);
                            dIntent.putExtra("idVillageCode", idVillageCode);


                            dIntent.putExtra("str_entry_by", str_entry_by);
                            dIntent.putExtra("str_entry_date", str_entry_date);

                            startActivity(dIntent);
                        }*/
//            }
//        }
//    }*/

    private void getDataFromIntent() {
        Intent cIntent = getIntent();
        is_edit = cIntent.getBooleanExtra("is_edit", false);
        str_hhMemID = cIntent.getStringExtra("MemID");

        str_hhID = cIntent.getStringExtra(KEY.HOUSE_HOLD_ID);
        str_hhName = cIntent.getStringExtra("str_hhName");
        redirect = cIntent.getStringExtra("redirect");
        idCountry = cIntent.getStringExtra(KEY.STR_COUNTRY_CODE);
        str_district = cIntent.getStringExtra("str_district");
        str_upazilla = cIntent.getStringExtra("str_upazilla");
        str_union = cIntent.getStringExtra("str_union");
        str_village = cIntent.getStringExtra("str_village");
        idDistrictCode = cIntent.getStringExtra(KEY.STR_DISTRICT_CODE);
        idUpazillaCode = cIntent.getStringExtra(KEY.STR_UPAZILLA_CODE);
        idUnionCode = cIntent.getStringExtra(KEY.STR_UNION_CODE);
        idVillageCode = cIntent.getStringExtra(KEY.STR_VILLAGE_CODE);
        str_regDate = cIntent.getStringExtra(KEY.REGESTRATION_DATE);
        str_entry_by = cIntent.getStringExtra(KEY.ENTRY_BY);
        str_entry_date = cIntent.getStringExtra(KEY.ENTRY_DATE);

        pID = cIntent.getIntExtra("pID", 20);

        //getImage=cIntent.getExtras().getBoolean(UniversalData.CAMERA_RE_DIRECTORIES,false);

    }

    private void viewReference() {
        lLayoutMalawiContainer = (LinearLayout) findViewById(R.id.lLayout_malawiViews);
        tvHHID = (TextView) findViewById(R.id.tv_Mreg_HH_id);
        tvHHName = (TextView) findViewById(R.id.tv_reg_mem_hh_name);
        edtHHMemID = (EditText) findViewById(R.id.mem_id);
//        tvMemRegDate = (TextView) findViewById(R.id.tv_mm_reg_date);

        edtMem_lib_otherId = (EditText) findViewById(R.id.edt_mem_lib_otherId);
        spMarital = (Spinner) findViewById(R.id.mem_lib_spMartial);
        spIDType = (Spinner) findViewById(R.id.mem_lib_spIdtype);
        edtMemNameFirst = (EditText) findViewById(R.id.mem_lib_First_NAME);
        edtMemNameMiddle = (EditText) findViewById(R.id.mem_lib_Middle_NAME);
        edtMemNameLast = (EditText) findViewById(R.id.mem_lib_Last_NAME);
        edtMemBirthYear = (EditText) findViewById(R.id.mem_lib_BirthYear);
        edtMemContact = (EditText) findViewById(R.id.mem_lib_contact);
        edtMemTypedIDNo = (EditText) findViewById(R.id.mem_lib_typedId);
        edtV_bsc_Mem_1_NameFirst = (EditText) findViewById(R.id.mem_lib_bsc_mem_1_First_NAME);
        edtV_bsc_Mem_1_NameMiddle = (EditText) findViewById(R.id.mem_lib_bsc_mem_1_Middle_NAME);
        edtV_bsc_Mem_1_NameLast = (EditText) findViewById(R.id.mem_lib_bsc_mem_1_Last_NAME);
        spBscMem1Title = (Spinner) findViewById(R.id.mem_lib_bsc_mem_1_spTitle);
        edtV_bsc_Mem_2_NameFirst = (EditText) findViewById(R.id.mem_lib_bsc_mem_2_First_NAME);
        edtV_bsc_Mem_2_NameMiddle = (EditText) findViewById(R.id.mem_lib_bsc_mem_2_Middle_NAME);
        edtV_bsc_Mem_2_NameLast = (EditText) findViewById(R.id.mem_lib_bsc_mem_2_Last_NAME);
        spBscMem2Title = (Spinner) findViewById(R.id.mem_lib_bsc_mem_2_spTitle);
        spDesignatedProxy = (Spinner) findViewById(R.id.mem_lib_spDesignatedProxy);
        edtProxyNameFirst = (EditText) findViewById(R.id.mem_lib_proxy_First_NAME);
        edtProxyNameMiddle = (EditText) findViewById(R.id.mem_lib_proxy_Middle_NAME);
        edtProxyNameLast = (EditText) findViewById(R.id.mem_lib_proxy_Last_NAME);
        edtProxyBirthYear = (EditText) findViewById(R.id.mem_lib_proxy_BirthYear);
        spIDTypeForProxy = (Spinner) findViewById(R.id.mem_lib_proxy_spIdType);
        edtProxyTypedIDNo = (EditText) findViewById(R.id.mem_lib_proxy_id);
        edtProxy_bsc_Mem_1_NameFirst = (EditText) findViewById(R.id.mem_lib_proxy_bsc_mem_1_fst_name);
        edtProxy_bsc_Mem_1_NameMiddle = (EditText) findViewById(R.id.mem_lib_proxy_bsc_mem_1_Mid_name);
        edtProxy_bsc_Mem_1_NameLast = (EditText) findViewById(R.id.mem_lib_proxy_bsc_mem_1_Last_name);
        spProxyBscMem1Title = (Spinner) findViewById(R.id.mem_lib_proxy_bsc_mem_1_spTitle);
        edtProxy_bsc_Mem_2_NameFirst = (EditText) findViewById(R.id.mem_lib_proxy_bsc_mem_2_First_name);
        edtProxy_bsc_Mem_2_NameMiddle = (EditText) findViewById(R.id.mem_lib_proxy_bsc_mem_2_Middle_NAME);
        edtProxy_bsc_Mem_2_NameLast = (EditText) findViewById(R.id.mem_lib_proxy_bsc_mem_2_Last_NAME);
        spProxyBscMem2Title = (Spinner) findViewById(R.id.mem_lib_proxy_bsc_mem_2_spTitle);
        btn_save = (Button) findViewById(R.id.btnSaveMember);
        ibtn_memberImage = (ImageButton) findViewById(R.id.ibtnLibMemImage);
        ivMemberImage = (ImageView) findViewById(R.id.ivMemberImage);
        ivProxyImage = (ImageView) findViewById(R.id.ivProxyImage);
        btn_home = (Button) findViewById(R.id.btnHomeFooter);
        btn_newHHRegistration = (Button) findViewById(R.id.btnRegisterFooter);
        //spProgram = (Spinner) findViewById(R.id.sp_hhm_Program);
        //spAward = (Spinner) findViewById(R.id.sp_hhm_awardList);
        //spCriteria = (Spinner) findViewById(R.id.sp_hhm_CriteriaA);
        ibtn_proxyImage = (ImageButton) findViewById(R.id.ibtnLibProxyCamera);
        btn_clear = (Button) findViewById(R.id.btnClearMember);
       // llAssignController = (LinearLayout) findViewById(R.id.lLayout_assignController); // to control layout
    }

    private void setMemID(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String hhID) {
        String next_id = sqlH.getMemberID(str_c_code, str_district, str_upazilla, str_union, str_village, hhID);
        edtHHMemID.setText(next_id);
        Log.d(TAG, "Member ID is = " + next_id);
    }

    /**
     * ** LOAD: Marital Status
     */
    private void loadMaritalStatus() {

        ArrayAdapter<CharSequence> adptMartial = ArrayAdapter.createFromResource(
                this, R.array.arrMarital, R.layout.spinner_layout);

        adptMartial.setDropDownViewResource(R.layout.spinner_layout);
        spMarital.setAdapter(adptMartial);


        spMarital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String str_martial = parent.getItemAtPosition(position).toString();

                if (str_martial.equals("Marrried"))
                    codeMartial = "1";
                else if (str_martial.equals("Single"))
                    codeMartial = "2";
                else if (str_martial.equals("Divorced"))
                    codeMartial = "3";
                else if (str_martial.equals("Widowed"))
                    codeMartial = "4";

                else
                    codeMartial = "5";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * ** LOAD: ID Type
     */
    private void loadIdType() {

        ArrayAdapter<CharSequence> adptMartial = ArrayAdapter.createFromResource(
                this, R.array.arrIDType, R.layout.spinner_layout);

        adptMartial.setDropDownViewResource(R.layout.spinner_layout);
        spIDType.setAdapter(adptMartial);
        // if code id found from db than spinner select the  automatic ly
        if (codeIDType != null) {

            spIDType.setSelection(Integer.parseInt(codeIDType));
        }


        spIDType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String str_martial = parent.getItemAtPosition(position).toString();

                if (str_martial.equals("Organization ID"))
                    codeIDType = "01";
                else if (str_martial.equals("School ID"))
                    codeIDType = "02";
                else if (str_martial.equals("Voter ID"))
                    codeIDType = "03";
                else if (str_martial.equals("Church ID"))
                    codeIDType = "04";

                else
                    codeIDType = "05";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * ** LOAD:: Verify Bsc Member 1 Title
     */
    private void loadBscMember_1_Title() {

        ArrayAdapter<CharSequence> adptMartial = ArrayAdapter.createFromResource(
                this, R.array.arrTitlePosition, R.layout.spinner_layout);

        adptMartial.setDropDownViewResource(R.layout.spinner_layout);
        spBscMem1Title.setAdapter(adptMartial);

        if (codeBscMem1Title != null) {

            spBscMem1Title.setSelection(Integer.parseInt(codeBscMem1Title));
        }

        spBscMem1Title.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String str_martial = parent.getItemAtPosition(position).toString();

                if (str_martial.equals("NA"))
                    codeBscMem1Title = "001";
                else if (str_martial.equals("Member"))
                    codeBscMem1Title = "002";
                else if (str_martial.equals("Chairman"))
                    codeBscMem1Title = "003";
                else
                    codeBscMem1Title = "004";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * ** LOAD:: Verify Bsc Member 1 Title
     */
    private void loadBscMember_2_Title() {

        ArrayAdapter<CharSequence> adptMartial = ArrayAdapter.createFromResource(
                this, R.array.arrTitlePosition, R.layout.spinner_layout);

        adptMartial.setDropDownViewResource(R.layout.spinner_layout);
        spBscMem2Title.setAdapter(adptMartial);

        if (codeBscMem2Title != null) {

            spBscMem2Title.setSelection(Integer.parseInt(codeBscMem2Title));
        }

        spBscMem2Title.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String str_martial = parent.getItemAtPosition(position).toString();

                if (str_martial.equals("NA"))
                    codeBscMem2Title = "001";
                else if (str_martial.equals("Member"))
                    codeBscMem2Title = "002";
                else if (str_martial.equals("Chairman"))
                    codeBscMem2Title = "003";
                else
                    codeBscMem2Title = "004";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * LOAD:: Designated Proxy
     */
    private void loadDesignatedProxy() {

        ArrayAdapter<CharSequence> adtDisabled = ArrayAdapter.createFromResource(
                this, R.array.arrDesignatedProxy, R.layout.spinner_layout);

        adtDisabled.setDropDownViewResource(R.layout.spinner_layout);
        spDesignatedProxy.setAdapter(adtDisabled);

        if (codeDesignatedProxy == null || !codeDesignatedProxy.equals("Y"))
            codeDesignatedProxy = "No";
        else
            codeDesignatedProxy = "Yes";

        spDesignatedProxy.setSelection(getSpinnerIndex(spDesignatedProxy, codeDesignatedProxy));

        spDesignatedProxy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                codeDesignatedProxy = parent.getItemAtPosition(position).toString();

                if (codeDesignatedProxy.equals("Yes"))
                    codeDesignatedProxy = "Y";
                else
                    codeDesignatedProxy = "N";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * ** LOAD: ID Type Proxy
     */
    private void loadIdTypeForProxy() {

        ArrayAdapter<CharSequence> adptMartial = ArrayAdapter.createFromResource(
                this, R.array.arrIDType, R.layout.spinner_layout);

        adptMartial.setDropDownViewResource(R.layout.spinner_layout);
        spIDTypeForProxy.setAdapter(adptMartial);
        /** Experiments*/
        if (codeIDTypeForProxy != null) {

            spIDTypeForProxy.setSelection(Integer.parseInt(codeIDTypeForProxy));
        }
        /*** Experiments*/


        spIDTypeForProxy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String str_martial = parent.getItemAtPosition(position).toString();

                if (str_martial.equals("Organization ID"))
                    codeIDTypeForProxy = "01";
                else if (str_martial.equals("School ID"))
                    codeIDTypeForProxy = "02";
                else if (str_martial.equals("Voter ID"))
                    codeIDTypeForProxy = "03";
                else if (str_martial.equals("Church ID"))
                    codeIDTypeForProxy = "04";

                else
                    codeIDTypeForProxy = "05";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * ** LOAD:: Verify Bsc Member 1 Title
     */
    private void loadProxyBscMember_1_Title() {

        ArrayAdapter<CharSequence> adptMartial = ArrayAdapter.createFromResource(
                this, R.array.arrTitlePosition, R.layout.spinner_layout);

        adptMartial.setDropDownViewResource(R.layout.spinner_layout);
        spProxyBscMem1Title.setAdapter(adptMartial);

        /** Experiments work*/
        if (codeProxyBscMem1Title != null) {

            spProxyBscMem1Title.setSelection(Integer.parseInt(codeProxyBscMem1Title));
        }
        /*** Experiments*/

        spProxyBscMem1Title.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String str_martial = parent.getItemAtPosition(position).toString();

                if (str_martial.equals("NA"))
                    codeProxyBscMem1Title = "001";
                else if (str_martial.equals("Member"))
                    codeProxyBscMem1Title = "002";
                else if (str_martial.equals("Chairman"))
                    codeProxyBscMem1Title = "003";
                else
                    codeProxyBscMem1Title = "004";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * ** LOAD:: Verify Bsc Member 1 Title
     */
    private void loadProxyBscMember_2_Title() {

        ArrayAdapter<CharSequence> adptMartial = ArrayAdapter.createFromResource(
                this, R.array.arrTitlePosition, R.layout.spinner_layout);

        adptMartial.setDropDownViewResource(R.layout.spinner_layout);
        spProxyBscMem2Title.setAdapter(adptMartial);
        /** Experiments*/
        if (codeProxyBscMem2Title != null) {

            spProxyBscMem2Title.setSelection(Integer.parseInt(codeProxyBscMem2Title));
        }
        /*** Experiments*/

        spProxyBscMem2Title.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String str_martial = parent.getItemAtPosition(position).toString();

                if (str_martial.equals("NA"))
                    codeProxyBscMem2Title = "001";
                else if (str_martial.equals("Member"))
                    codeProxyBscMem2Title = "002";
                else if (str_martial.equals("Chairman"))
                    codeProxyBscMem2Title = "003";
                else
                    codeProxyBscMem2Title = "004";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


/*
    */
/**
     * LOAD :: Award
     *//*

    private void loadAward(final String idCountry) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.ADM_COUNTRY_AWARD_TABLE + "." + SQLiteHandler.ADM_COUNTRY_CODE_COL + "='" + idCountry + "'";
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


                idDonor = idAward.substring(0, 2);
                loadProgram(idAward.substring(2), idDonor, idCountry);
                Log.d(TAG, "idAward : " + idAward + " donor id :" + idAward.substring(0, 2));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Award Spinner


    */
/**
     * LOAD :: Program
     *//*

    private void loadProgram(final String idAward, final String donorId, final String idcCode) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.ADM_AWARD_CODE_COL + "='" + idAward + "'"
                + " AND " + SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.ADM_DONOR_CODE_COL + "='" + donorId + "'";
        // Spinner Drop down elements for Program
        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.ADM_COUNTRY_PROGRAM_TABLE, criteria, null, false);

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
                // if(idProgram.length()>2){
                Log.d(TAG, "load Prog data " + idProgram);

                loadCriteria(idAward, donorId, idProgram, idcCode);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    */
/**
     * LOAD :: Criteria
     *//*

    private void loadCriteria(final String idAward, final String donorId, final String idProgram, final String cCode) {

        int position = 0;
        String criteria = SQLiteQuery.getCriteriaNames_WHERE_Condition(idAward, donorId, idProgram);

        // Spinner Drop down elements for Criteria
        List<SpinnerHelper> listCriteria = sqlH.getListAndID(SQLiteHandler.SERVICE_MASTER_TABLE, criteria, null, false);

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
                    // Enable
                 */
/*   loadVillage(cCode,idAward,donorId, idProgram,idCriteria );// idService=idCriteria

                    if (ext_village != null) {
                        for (int i = 0; i < spVillage.getCount(); i++) {
                            String village = spVillage.getItemAtPosition(i).toString();
                            if (village.equals(ext_village_name)) {
                                positionVillage = i;
                            }
                        }
                        spVillage.setSelection(positionVillage);
                    }*//*

                }

                // if(idCriteria.length()>2){
                Log.d(TAG, "load idCriteria data " + idCriteria + " Critrei a name " + strCriteria);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner
*/

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

   private String previewCapturedImage(ImageView imageView) {
        byte[] byteArray;
        String encodedImageString = null;
        try {
            imageView.setVisibility(View.VISIBLE);
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 4;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), bmOptions);

            // you can change the format of you image compressed for what do you want;
            // now it is set up to 170 x 196;
            Bitmap bitmapCompressed = Bitmap.createScaledBitmap(bitmap, 170, 196, true);

            // for passing the image to other activity
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            // convert the image into 100%
            bitmapCompressed.compress(Bitmap.CompressFormat.PNG, 50, stream);

            byteArray = stream.toByteArray();

           /*  convert in base 64*/
            encodedImageString = Base64.encodeToString(byteArray, Base64.DEFAULT);

//            encodedImageString = Base64.encodeToString(byteArray, Base64.NO_WRAP);
            imageView.setImageBitmap(bitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return encodedImageString;
    }


 /*   private byte [] previewCapturedImage(ImageView imageView) {
        byte[] byteArray = null;
       // String encodedImageString = null;
        try {
            imageView.setVisibility(View.VISIBLE);
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 4;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), bmOptions);

            // you can change the format of you image compressed for what do you want;
            // now it is set up to 170 x 196;
            Bitmap bitmapCompressed = Bitmap.createScaledBitmap(bitmap, 170, 196, true);

            // for passing the image to other activity
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            // convert the image into 100%
            bitmapCompressed.compress(Bitmap.CompressFormat.PNG, 100, stream);

            byteArray = stream.toByteArray();

            Log.d(TAG,"Image Byte Array of  +"+stream.toByteArray());
            Log.d(TAG,"Length of Image Byte Array of  +"+stream.toByteArray().length);
            //encodedImageString=byteArray.toString();
           /*  convert in base 64
            encodedImageString = Base64.encodeToString(byteArray, Base64.DEFAULT);

            //encodedImageString = Base64.encodeToString(byteArray, Base64.NO_WRAP);
//            imageView.setImageBitmap(bitmap);

            Bitmap bmp1 = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            imageView.setImageBitmap(bmp1);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return byteArray;
    }*/

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private File getOutputMediaFile(int type) {


        File mediaStorageDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.getExternalStorageState()),
                IMAGE_DIRECTORY_NAME);


        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "PCI_" +
                    getImageName()
                    + ".png");
            string = mediaFile.toString();

        } else {
            return null;
        }

        return mediaFile;
    }

    private void captureImage(int request) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, request);
    }

    /**
     *
     * @return
     */

    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MEMBER_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                memImageEncoded = previewCapturedImage(ivMemberImage);
            } else if (requestCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "User Canceled Image Capture", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PROXY_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                ProxyImageEncoded = previewCapturedImage(ivProxyImage);
            } else if (requestCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "User Canceled Image Capture", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Bitmap decodeImage(byte[] encodedImage) {
        Bitmap bitmap = null;
        if (encodedImage != null) {
            try {
                Log.d(TAG,"Byte array length  Before Base 64 convert : "+encodedImage.length);
//                String encodedImageString = Base64.encodeToString(encodedImage, Base64.DEFAULT);
               byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);

         //       byte[] decodedString =encodedImage;
                Log.d(TAG,"Byte array length  after Base 64 convert : "+decodedString.length);
              bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            } catch (Exception e) {
                Log.d(TAG, "Exception e :" + e);
            }

        }
        return bitmap;
    }

    public void goToAlert()
    {
        final CharSequence [] items = getResources().getStringArray(R.array.reg_mem_goto_array);
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(RegisterMemberLiberia.this, android.R.style.Theme_Holo_Light_Dialog));

        builder.setTitle("GO TO:");


        builder.setIcon(R.drawable.navigation_icon);
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                switch (item)
                {
                    case 0:
                        finish();
                        intent = new Intent(RegisterMemberLiberia.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        finish();
                        intent = new Intent(RegisterMemberLiberia.this,OldAssignActivity.class);


                        intent.putExtra(OldAssignActivity.SUB_ASSIGN_DIR,false);
                        intent.putExtra(KEY.COUNTRY_ID, sqlH.selectCountryCode());
                        startActivity(intent);
                        break;
                    case 2:
                        finish();
                        intent = new Intent(RegisterMemberLiberia.this,DistributionActivity.class);
                        intent.putExtra(KEY.DIR_CLASS_NAME_KEY,"RegisterMemberLiberia");
                        intent.putExtra(KEY.COUNTRY_ID,sqlH.selectCountryCode());
                        startActivity(intent);
                        break;
                    case 3:
                        finish();
                        intent = new Intent(RegisterMemberLiberia.this,ServiceActivity.class);
                        intent.putExtra(KEY.DIR_CLASS_NAME_KEY,"RegisterMemberLiberia");
                        intent.putExtra(KEY.COUNTRY_ID,sqlH.selectCountryCode());
                        Log.d(TAG + " Country ID ",sqlH.selectCountryCode());

                        startActivity(intent);
                        break;
                    case 4:
                        finish();
                        intent = new Intent(RegisterMemberLiberia.this,SummaryMenuActivity.class);
                        intent.putExtra(KEY.COUNTRY_ID,sqlH.selectCountryCode());
                        startActivity(intent);
                        break;
                    case 5:
                        finish();
                        intent = new Intent(RegisterMemberLiberia.this, RegisterLiberia.class);
                        startActivity(intent);
                        break;
                }
                goToDialog.dismiss();
            }
        });
        goToDialog = builder.create();
        goToDialog.show();
        int titleDividerId = goToDialog.getContext().getResources().getIdentifier("titleDivider","id","android");//("android:id/titleDivider",null,null);
        //   View titleDivider = activityDialog.findViewById(titleDividerId);
        View titleDivider = goToDialog.getWindow().getDecorView().findViewById(titleDividerId);
        if (titleDivider!=null)
            titleDivider.setBackgroundColor(getResources().getColor(R.color.blue));
// setAlertDevider();

    }
}
