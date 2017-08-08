package com.siddiquinoor.restclient.activity.sub_activity.service_sub;
/**
 * This Class
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.ServiceActivity;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;

import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.ServiceDataModel;
import com.siddiquinoor.restclient.views.adapters.VouItemServiceExtDataModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ServiceVoucherDetails extends BaseActivity {

    private SQLiteHandler sqlH;
    private ArrayList<VouItemServiceExtDataModel> voItemArray = new ArrayList<VouItemServiceExtDataModel>();

    private VoucherChartAdapter adapter;

    ListView lvVoucher;

    TextView tvhouseHoldName;
    TextView tvhouseMemberID;
    TextView tvMemberName;
    Button btnSave;
    Button btnService;
    ServiceDataModel srvData;
    EditText edtVoucherRef;
    /**
     * To save checked items, and re-add while scrolling.
     */
    SparseBooleanArray mChecked = new SparseBooleanArray();

    /**
     * For get  EditText value from listst view
     */
    private String[] edtVoItmUnitCode;
    private String[] voItmIDInListView;
    private ArrayList<TemTestUnit> mEdtUnit = new ArrayList<TemTestUnit>();

    private final String TAG = ServiceVoucherDetails.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_voucher_details);
        sqlH = new SQLiteHandler(this);

        viewReference();


        // Then you can pull them back out with getParcelableExtra():

        Intent i = getIntent();
        srvData = (ServiceDataModel) i.getParcelableExtra(KEY.SERVICE_DATA_OBJECT_KEY);

        String tem = sqlH.getVoucherReferenceNumberCol(srvData.getC_code(), srvData.getDistrictCode(), srvData.getUpazillaCode(), srvData.getUnitCode(),
                srvData.getVillageCode(), srvData.getHHID(), srvData.getMemberId(), srvData.getDonor_code(),
                srvData.getAward_code(), srvData.getProgram_code(), srvData.getService_code(),
                srvData.getOpMontheCode());


        edtVoucherRef.setText(tem);

        /**
         *  set op Code to service because in some way op month String set up in OpCode  */


        tvhouseHoldName.setText(srvData.getHh_name());
        String newId = srvData.getDistrictCode() + srvData.getUpazillaCode() + srvData.getUnitCode() + srvData.getVillageCode() + srvData.getHHID() + srvData.getMemberId();
        tvhouseMemberID.setText(newId);
        tvMemberName.setText(srvData.getHh_mm_name());


        loadVoucherListView(srvData.getC_code(), srvData.getDistrictCode(), srvData.getUpazillaCode(), srvData.getUnitCode(),
                srvData.getVillageCode(), srvData.getHHID(), srvData.getMemberId(), srvData.getDonor_code(),
                srvData.getAward_code(), srvData.getProgram_code(), srvData.getService_code(),
                srvData.getOpMontheCode()
        );

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveServicedData();
            }
        });
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();

                finish();
                Intent iService = new Intent(ServiceVoucherDetails.this, ServiceActivity.class);
                iService.putExtra(KEY.DIR_CLASS_NAME_KEY, "ServiceVoucherDetails");


                iService.putExtra(KEY.COUNTRY_ID, srvData.getC_code());
                iService.putExtra(KEY.AWARD_CODE, srvData.getAward_code());
                iService.putExtra(KEY.AWARD_NAME, srvData.getAwardName());
                iService.putExtra(KEY.DONOR_CODE, srvData.getDonor_code());
                iService.putExtra(KEY.CRITERIA_NAME, srvData.getCriteriaName());
                iService.putExtra(KEY.CRITERIA_CODE, srvData.getCriteriaId());
                iService.putExtra(KEY.SERVICE_CENTER_CODE, srvData.getServiceCenterCode());
                iService.putExtra(KEY.SERVICE_CENTER_NAME, srvData.getTemServiceCenterName());
                iService.putExtra(KEY.SERVICE_DATE, srvData.getTemServiceDate());
                iService.putExtra(KEY.OP_MONTH_LABLE, srvData.getOpMonthStr());
                iService.putExtra(KEY.OP_MONTH_CODE, srvData.getOpMontheCode());
                iService.putExtra(KEY.OP_CODE, srvData.getOpCode());

                iService.putExtra(KEY.SERVICE_MONTH_CODE, srvData.getTemIdServiceMonth());
                iService.putExtra(KEY.SERVICE_MONTH_NAME, srvData.getTemStrSrvMonth());

                iService.putExtra(KEY.GROUP_NAME, srvData.getTemStrGroup());
                iService.putExtra(KEY.GROUP_CODE, srvData.getTemIdGroup());
                iService.putExtra(KEY.GROUP_CATEGORY_CODE, srvData.getTemIdGroupCat());
                iService.putExtra(KEY.GROUP_CATEGORY_NAME, srvData.getTemStrGroupCat());

                startActivity(iService);
            }
        });
    }


    private void saveServicedData() {
        String voRefNo = "";
        voRefNo = edtVoucherRef.getText().toString();
        String EntryBy = "";
        String EntryDate = "";


        try {
            EntryBy = getStaffID();
            EntryDate = getDateTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        srvData.setWorkingDay("1");
        srvData.setServiceDTCode(srvData.getTemServiceDate());

        SQLServerSyntaxGenerator srvTableNsrvExtendedTable = new SQLServerSyntaxGenerator();
        srvTableNsrvExtendedTable.setAdmCountryCode(srvData.getC_code());
        srvTableNsrvExtendedTable.setAdmDonorCode(srvData.getDonor_code());
        srvTableNsrvExtendedTable.setAdmAwardCode(srvData.getAward_code());
        srvTableNsrvExtendedTable.setLayR1ListCode(srvData.getDistrictCode());
        srvTableNsrvExtendedTable.setLayR2ListCode(srvData.getUpazillaCode());
        srvTableNsrvExtendedTable.setLayR3ListCode(srvData.getUnitCode());
        srvTableNsrvExtendedTable.setLayR4ListCode(srvData.getVillageCode());
        srvTableNsrvExtendedTable.setHHID(srvData.getHHID());
        srvTableNsrvExtendedTable.setMemID(srvData.getMemberId());
        srvTableNsrvExtendedTable.setProgCode(srvData.getProgram_code());
        srvTableNsrvExtendedTable.setSrvCode(srvData.getService_code());
        srvTableNsrvExtendedTable.setOpCode(srvData.getOpCode());
        srvTableNsrvExtendedTable.setOpMonthCode(srvData.getOpMontheCode());
        srvTableNsrvExtendedTable.setSrvSL(srvData.getServiceSLCode());
        srvTableNsrvExtendedTable.setSrvCenterCode(srvData.getServiceCenterCode());
        srvTableNsrvExtendedTable.setSrvDT(srvData.getServiceDTCode());
        srvTableNsrvExtendedTable.setSrvStatus("O");                                                /** service status Open  */
        srvTableNsrvExtendedTable.setEntryBy(EntryBy);
        srvTableNsrvExtendedTable.setEntryDate(EntryDate);
        srvTableNsrvExtendedTable.setDistFlag(srvData.getDistFlag());
        srvTableNsrvExtendedTable.setWD(srvData.getWorkingDay());

        srvTableNsrvExtendedTable.setvORefNumber(voRefNo);// Voucher reference


        ArrayList<VouItemServiceExtDataModel> alist = new ArrayList<VouItemServiceExtDataModel>();
        alist = adapter.servicedExtendedData;

        /** set  if condition */

        if (adapter.isArrayListNull()) {

            Toast.makeText(getApplicationContext(), "No date is selected  a valid date! Please select data!!", Toast.LENGTH_LONG).show();
        } else {
            /** if the array list is not null */

            boolean isDataDeleted = false;
            for (int i = 0; i < lvVoucher.getAdapter().getCount(); i++) {
                if (mChecked.get(i)) {

                    VouItemServiceExtDataModel srvE = alist.get(i);
                    srvE.setOpCode("2");
                    /** setup SQL Server Syntax variable*/
                    if (!isDataDeleted && (sqlH.isDataExitedServiceExtendedTable(srvData))) {

                        sqlH.deleteFromServiceExtendedTable(srvData.getC_code(), srvData.getDistrictCode(), srvData.getUpazillaCode(), srvData.getUnitCode(),
                                srvData.getVillageCode(), srvData.getHHID(), srvData.getMemberId(), srvData.getDonor_code()
                                , srvData.getAward_code(), srvData.getProgram_code(), srvData.getService_code(), srvData.getOpMontheCode());


                        /**  setup upload  delete syntax for Srv extended table*/

                        sqlH.insertIntoUploadTable(srvTableNsrvExtendedTable.deleteMemberFromSrvExtendedTable());
                        isDataDeleted = true;
                    }


                    sqlH.addServiceExtendedTable(srvE.getC_code(), srvE.getDistrictCode(), srvE.getUpazillaCode(), srvE.getUnitCode(),
                            srvE.getVillageCode(), srvE.getHHID(), srvE.getHh_mm_id(), srvE.getDonor_code(), srvE.getAward_code(),
                            srvE.getProgram_code(), srvE.getService_code(), srvE.getOpCode(), srvE.getOpMontheCode(),
                            voItmIDInListView[i], edtVoItmUnitCode[i], voRefNo, srvE.getVoItemCost(), "", EntryBy, EntryDate);
                    /** set the variable than insert  upload Table*/

                    srvTableNsrvExtendedTable.setvOItmUnit(edtVoItmUnitCode[i]);
                    srvTableNsrvExtendedTable.setvOItmSpec(voItmIDInListView[i]);
                    srvTableNsrvExtendedTable.setvOItmCost(srvE.getVoItemCost());

                    sqlH.insertIntoUploadTable(srvTableNsrvExtendedTable.insertIntoSrvExtendedTable());


                }
            }
            String lastDate = sqlH.getLastServiceDate(srvData);

            long diff = 0;
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            SimpleDateFormat myFormat_2 = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);

            if (!lastDate.equals("")) {
                try {
                    Date date1 = myFormat.parse(srvData.getTemServiceDate());
                    Date date2 = myFormat.parse(lastDate);
                    diff = date2.getTime() - date1.getTime();


                } catch (ParseException e) {
                    e.printStackTrace();
                }


                if (diff != 0) {


                    if (sqlH.isMemberExitsSrvTable(srvData)) {

                        sqlH.updateMemberIntoServiceTable(srvData, EntryBy, EntryDate);             /** update for local device */


                        sqlH.insertIntoUploadTable(srvTableNsrvExtendedTable.updateInToSrvTable());  /** update Syntax for upload in Sync process */
                    } else {

                        sqlH.addMemberIntoServiceTable(srvData, EntryBy, EntryDate);                /** insert for local device */

                        sqlH.insertIntoUploadTable(srvTableNsrvExtendedTable.insertInToSrvTable());   /** insert for upload in Sync process */
                    }


                    /**                                         * min Srv Date                                         */
                    ServiceActivity.saveServiceMinumDate(srvData, srvData.getServiceDTCode(), srvTableNsrvExtendedTable, sqlH);

                    /**                                         * max date                                         */
                    ServiceActivity.saveServiceMaxDate(srvData, srvData.getServiceDTCode(), srvTableNsrvExtendedTable, sqlH);


                }


            } else {

                if (sqlH.isMemberExitsSrvTable(srvData)) {


                    sqlH.updateMemberIntoServiceTable(srvData, EntryBy, EntryDate);                   /** update for local device */


                    sqlH.insertIntoUploadTable(srvTableNsrvExtendedTable.updateInToSrvTable());      /** update Syntax for upload in Sync process */
                } else {

                    sqlH.addMemberIntoServiceTable(srvData, EntryBy, EntryDate);                     /** insert for local device */

                    sqlH.insertIntoUploadTable(srvTableNsrvExtendedTable.insertInToSrvTable());        /** insert for upload in Sync process */
                }

                /**                                         * min Srv Date                                         */
                ServiceActivity.saveServiceMinumDate(srvData, srvData.getServiceDTCode(), srvTableNsrvExtendedTable, sqlH);

                /**                                         * max date                                         */
                ServiceActivity.saveServiceMaxDate(srvData, srvData.getServiceDTCode(), srvTableNsrvExtendedTable, sqlH);


            }

            Toast.makeText(this, "save successfully", Toast.LENGTH_LONG).show();


        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void viewReference() {
        lvVoucher = (ListView) findViewById(R.id.lv_Voucher);

        tvhouseHoldName = (TextView) findViewById(R.id.tv_srv_vouHHName);
        tvhouseMemberID = (TextView) findViewById(R.id.tv_srv_vouMemberId);
        tvMemberName = (TextView) findViewById(R.id.tv_srv_vouMemberName);
        edtVoucherRef = (EditText) findViewById(R.id.edt_vio_refNumber);
        btnSave = (Button) findViewById(R.id.btnHomeFooter);

        btnService = (Button) findViewById(R.id.btnRegisterFooter);


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

        addIconServiceButton();
        setUpSaveButton();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpSaveButton() {
        btnSave.setText("");
        Drawable saveImage = getResources().getDrawable(R.drawable.save_b);
        btnSave.setCompoundDrawablesRelativeWithIntrinsicBounds(null, saveImage, null, null);
        btnSave.setPadding(-1, 20, -1, 20);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addIconServiceButton() {
        btnService.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.service);
        btnService.setCompoundDrawablesRelativeWithIntrinsicBounds(null, imageHome, null, null);
        btnService.setPadding(-1, 20, -1, 20);
    }


    public void loadVoucherListView(String cCode, String discode, String upCode, String unCode, String vCode, String hhId, String memId, String donorCode, String awardCode, String programCode,
                                    String serviceCode, String opMonthCode) {


        List<VouItemServiceExtDataModel> itemlistData = sqlH.getSrvExtVoucherList(cCode, discode,
                upCode, unCode, vCode, hhId, memId, donorCode, awardCode, programCode, serviceCode,
                opMonthCode);
        /** @date: 2015-10-13
         * @description: Make a provision where if there is not data for the selected criteria then grid will be empty.*/


        if (itemlistData.size() != 0) {
            voItemArray.clear();
            for (VouItemServiceExtDataModel data : itemlistData) {
                // add contacts data in arrayList
                voItemArray.add(data);
            }
            adapter = new VoucherChartAdapter(this, voItemArray, srvData);
            lvVoucher.setAdapter(adapter);

            lvVoucher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
            lvVoucher.setFocusableInTouchMode(true);
        }

    }

    public class VoucherChartAdapter extends BaseAdapter {

        /***
         * for Edit Text
         *
         * @link {http://www.webplusandroid.com/creating-listview-with-edittext-and-textwatcher-in-android/}
         * @see {@link {https://vikaskanani.wordpress.com/2011/07/27/android-focusable-edittext-inside-listview/}}
         */

        ArrayList<VouItemServiceExtDataModel> servicedExtendedData;

        private Activity activity;

        private SQLiteHandler sqlH = null;

        private LayoutInflater inflater;

        private String opMonthCode;
        private int count = 0;

        private ServiceDataModel srvDetails;
        ViewHolder holder;

        public VoucherChartAdapter(Activity activity, ArrayList<VouItemServiceExtDataModel> servicedExtendedData, ServiceDataModel srvDetails) {
            this.activity = activity;
            this.servicedExtendedData = servicedExtendedData;
            edtVoItmUnitCode = new String[servicedExtendedData.size()];
            voItmIDInListView = new String[servicedExtendedData.size()];
            this.srvDetails = srvDetails;
            sqlH = new SQLiteHandler(activity);
            listOFWant2Save.clear();
        }


        @Override
        public int getCount() {
            count = servicedExtendedData.size();
            return count;

        }

        @Override
        public Object getItem(int position) {
            return servicedExtendedData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {


            final VouItemServiceExtDataModel personToBeServicedExted = servicedExtendedData.get(position);
            View row = convertView;


            if (inflater == null)
                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                row = inflater.inflate(R.layout.list_row_voucher, null);
                holder = new ViewHolder();
                holder.edtUnit = (EditText) row.findViewById(R.id.row_voucherQuantity);
                holder.txtItemName = (TextView) row.findViewById(R.id.row_txt_itemName);


                row.setTag(holder);

            } else {
                holder = (ViewHolder) row.getTag();
            }

            holder.txtItemName.setText(personToBeServicedExted.getItemName());


            voItmIDInListView[position] = personToBeServicedExted.getVoItmSpec();

            // check box reference is define here
            CheckBox cbId_holder = (CheckBox) row.findViewById(R.id.cb_voucher_id_holde);

            personToBeServicedExted.setC_code(srvDetails.getC_code());
            personToBeServicedExted.setDistrictCode(srvDetails.getDistrictCode());
            personToBeServicedExted.setUpazillaCode(srvDetails.getUpazillaCode());
            personToBeServicedExted.setUnitCode(srvDetails.getUnitCode());
            personToBeServicedExted.setVillageCode(srvDetails.getVillageCode());
            personToBeServicedExted.setDonor_code(srvDetails.getDonor_code());
            personToBeServicedExted.setAward_code(srvDetails.getAward_code());
            personToBeServicedExted.setProgram_code(srvDetails.getProgram_code());
            personToBeServicedExted.setService_code(srvDetails.getService_code());
            personToBeServicedExted.setHHID(srvDetails.getHHID());
            personToBeServicedExted.setHh_mm_id(srvDetails.getMemberId());
            personToBeServicedExted.setOpMontheCode(srvDetails.getOpMontheCode());
            String cost = sqlH.get_VOUnitCost(srvDetails.getC_code(), srvDetails.getDonor_code(), srvDetails.getAward_code(), srvDetails.getProgram_code(), srvDetails.getService_code(), personToBeServicedExted.getVoItmSpec());

            personToBeServicedExted.setVoItemCost(cost);


            // set checked change listener
            cbId_holder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                   /* Saving Checked Position
                            */
                    mChecked.put(position, isChecked);


                    // get the object of specific row & set the value of the
                    // save the checkbox of that particular state

                    int getPosition = (Integer) buttonView.getTag();
                    getServicedPerson(getPosition).setCheckBox(isChecked);
                    addDataToArrayList(personToBeServicedExted,
                            mChecked.get(position));


                }
            });

            // set the sate of particular positioned check box
            cbId_holder.setTag(position);
            // than set the checked sate

            /**
             * Set CheckBox "TRUE" or "FALSE" if mChecked == true or in db staff have provided service to him
             */
            cbId_holder.setChecked(((mChecked.get(position) == true) || personToBeServicedExted.isCheckBox() ? true : false));
            if (personToBeServicedExted.isCheckBox() ? true : false) {
                mChecked.put(position, true);
            }


            if (personToBeServicedExted.isCheckBox()) {
                mChecked.put(position, true);
            }
            if (personToBeServicedExted.getVoItmUnit() != null) {
                if (!personToBeServicedExted.getVoItmUnit().equals("")) {
                    edtVoItmUnitCode[position] = personToBeServicedExted.getVoItmUnit();
                }
            }
            holder.reference = position;

            holder.edtUnit.setText(edtVoItmUnitCode[position]);
            holder.edtUnit.setId(position);

            holder.edtUnit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        final int position = v.getId();
                        final EditText Caption = (EditText) v;
                        edtVoItmUnitCode[position] = Caption.getText().toString();

                    }
                }
            });


            if (position % 2 == 0) {
                row.setBackgroundColor(Color.WHITE);
                changeTextColor(activity.getResources().getColor(R.color.blue));
            } else {
                row.setBackgroundColor(activity.getResources().getColor(R.color.list_divider));
                changeTextColor(activity.getResources().getColor(R.color.black));
            }

            return row;
        }

        private void changeTextColor(int color) {
            holder.txtItemName.setTextColor(color);
        }

        VouItemServiceExtDataModel getServicedPerson(int position) {
            return (VouItemServiceExtDataModel) getItem(position);
        }


        public boolean isArrayListNull() {
            if (listOFWant2Save.isEmpty())
                return true;
            else
                return false;


        }

        private ArrayList<VouItemServiceExtDataModel> listOFWant2Save = new ArrayList<VouItemServiceExtDataModel>();

        private void addDataToArrayList(VouItemServiceExtDataModel s, boolean chackBoxStatus) {
            if (chackBoxStatus) {

                listOFWant2Save.add(s);


            } else {
                if (listOFWant2Save.contains(s)) {                                                  // first check the data is exits ing or not

                    listOFWant2Save.remove(s);

                }

            }

        }

        // this method called from activity
        public ArrayList<VouItemServiceExtDataModel> getArrayList() {
            return listOFWant2Save;
        }

        private class ViewHolder {
            EditText edtUnit;
            TextView txtItemName;

            int reference;                                                                           // for save the state of current state


        }
    }

    class TemTestUnit {
        int pos;
        String unit;

        public TemTestUnit(int pos, String unit) {
            this.pos = pos;
            this.unit = unit;
        }

        public int getPos() {
            return pos;
        }

        public String getUnit() {
            return unit;
        }
    }
}
