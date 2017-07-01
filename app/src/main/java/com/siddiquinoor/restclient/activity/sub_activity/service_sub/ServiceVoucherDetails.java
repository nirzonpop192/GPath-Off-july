package com.siddiquinoor.restclient.activity.sub_activity.service_sub;
/**
 * This Class
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class ServiceVoucherDetails extends BaseActivity {

    private SQLiteHandler sqlH;
    private ArrayList<VouItemServiceExtDataModel> voItemArray = new ArrayList<VouItemServiceExtDataModel>();

    private VoucherChartAdapter adapter;

    ListView lvVoucher;

    TextView tvhouseHoldName;
    TextView tvhouseMemberID;
    TextView tvMemberName;
    Button btn_Save;
    Button btn_Service;
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
        /** to chek service value */
        testLogDebug();
        /**
         *  set op Code to service because in some way op month String set up in OpCode  */
        //   srvData.setOpCode("2");

        tvhouseHoldName.setText(srvData.getHh_name());
        String newId = srvData.getDistrictCode() + srvData.getUpazillaCode() + srvData.getUnitCode() + srvData.getVillageCode() + srvData.getHHID() + srvData.getMemberId();
        tvhouseMemberID.setText(newId);
        tvMemberName.setText(srvData.getHh_mm_name());


        loadVoucherListView(srvData.getC_code(), srvData.getDistrictCode(), srvData.getUpazillaCode(), srvData.getUnitCode(),
                srvData.getVillageCode(), srvData.getHHID(), srvData.getMemberId(), srvData.getDonor_code(),
                srvData.getAward_code(), srvData.getProgram_code(), srvData.getService_code(),
                srvData.getOpMontheCode()
        );

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveServicedData();
            }
        });
        btn_Service.setOnClickListener(new View.OnClickListener() {
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
        srvTableNsrvExtendedTable.setSrvStatus("O");    /** service status Open  */
        srvTableNsrvExtendedTable.setEntryBy(EntryBy);
        srvTableNsrvExtendedTable.setEntryDate(EntryDate);
        srvTableNsrvExtendedTable.setEntryDate(srvData.getDistFlag());

        srvTableNsrvExtendedTable.setvORefNumber(voRefNo);// Voucher reference


        ArrayList<VouItemServiceExtDataModel> alist = new ArrayList<VouItemServiceExtDataModel>();
        alist = adapter.servicedExtendedData;
        Log.d(TAG, "alit size " + alist.size());
        /** set  if condition */

        if (adapter.isArrayListNull()) {

            Toast.makeText(getApplicationContext(), "No date is selected  a valid date! Please select data!!", Toast.LENGTH_LONG).show();
        } else {
            /** if the array list is not null */

            for (int i = 0; i < lvVoucher.getAdapter().getCount(); i++) {
                if (mChecked.get(i)) {
                    // Do something
//                    Log.d("END_OH", "the number of Check ite :" + i);
                    VouItemServiceExtDataModel srvE = alist.get(i);
                    srvE.setOpCode("2");
                    /** setup SQL Server Syntax variable*/
                    if ((sqlH.isDataExitedServiceExtendedTable(srvData.getC_code(), srvData.getDistrictCode(), srvData.getUpazillaCode(), srvData.getUnitCode(),
                            srvData.getVillageCode(), srvData.getHHID(), srvData.getMemberId(), srvData.getDonor_code()
                            , srvData.getAward_code(), srvData.getProgram_code(), srvData.getService_code(), srvData.getOpMontheCode())) && i == 0) {

                        sqlH.deleteFromServiceExtendedTable(srvData.getC_code(), srvData.getDistrictCode(), srvData.getUpazillaCode(), srvData.getUnitCode(),
                                srvData.getVillageCode(), srvData.getHHID(), srvData.getMemberId(), srvData.getDonor_code()
                                , srvData.getAward_code(), srvData.getProgram_code(), srvData.getService_code(), srvData.getOpMontheCode());
                        Log.d(TAG, " Delete data from SrvExtended table SQLite  ");

                        /**  setup upload  delete syntax for Srv extended table*/

                        sqlH.insertIntoUploadTable(srvTableNsrvExtendedTable.deleteMemberFromSrvExtendedTable());
                    }
                    sqlH.addServiceExtendedTable(srvE.getC_code(), srvE.getDistrictCode(), srvE.getUpazillaCode(), srvE.getUnitCode(),
                            srvE.getVillageCode(), srvE.getHHID(), srvE.getHh_mm_id(), srvE.getDonor_code(), srvE.getAward_code(),
                            srvE.getProgram_code(), srvE.getService_code(), srvE.getOpCode(), srvE.getOpMontheCode(),
                            voItmIDInListView[i]/*vOItmSpec*/, edtVoItmUnitCode[i]/* Unite Code */, voRefNo, srvE.getVoItemCost(),"", EntryBy, EntryDate,"0");
                    /** set the variable than insert  upload Table*/

                    srvTableNsrvExtendedTable.setvOItmUnit(edtVoItmUnitCode[i]);
                    srvTableNsrvExtendedTable.setvOItmSpec(voItmIDInListView[i]);
                    srvTableNsrvExtendedTable.setvOItmCost(srvE.getVoItemCost());

                    sqlH.insertIntoUploadTable(srvTableNsrvExtendedTable.insertIntoSrvExtendedTable());


                }
            }
            String lastDate = sqlH.getLastServiceDate(srvData);

            long diff = 0;
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

            if (!lastDate.equals("")) {
                try {
                    Date date1 = myFormat.parse(srvData.getTemServiceDate());
                    Date date2 = myFormat.parse(lastDate);
                    diff = date2.getTime() - date1.getTime();

                    //  Toast.makeText(mcontext, "Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)+" diff :"+diff, Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                if (diff != 0) {
                    // insert for local device
                    //testLogDebug();

                    if (sqlH.isMemberExitsSrvTable(srvData)) {
                        /** update for local device */
                        sqlH.updateMemberIntoServiceTable(srvData, EntryBy, EntryDate);

                        /** update Syntax for upload in Sync process */
                        sqlH.insertIntoUploadTable(srvTableNsrvExtendedTable.updateInToSrvTable());
                    }
                    else {
                        /** insert for local device */
                        sqlH.addMemberIntoServiceTable(srvData, EntryBy, EntryDate);
                        /** insert for upload in Sync process */
                        sqlH.insertIntoUploadTable(srvTableNsrvExtendedTable.insertInToSrvTable());
                    }


                    /**                                         * min Srv Date                                         */
                    ServiceActivity.saveServiceMinumDate(srvData, srvData.getServiceDTCode(), srvTableNsrvExtendedTable,sqlH);

                    /**                                         * max date                                         */
                    ServiceActivity.saveServiceMaxDate(srvData, srvData.getServiceDTCode(), srvTableNsrvExtendedTable,sqlH);


                }


            } else {
               // testLogDebug();
                if (sqlH.isMemberExitsSrvTable(srvData)) {
                    /** update for local device */
                    sqlH.updateMemberIntoServiceTable(srvData, EntryBy, EntryDate);

                    /** update Syntax for upload in Sync process */
                    sqlH.insertIntoUploadTable(srvTableNsrvExtendedTable.updateInToSrvTable());
                }
                else {
                    /** insert for local device */
                    sqlH.addMemberIntoServiceTable(srvData, EntryBy, EntryDate);
                    /** insert for upload in Sync process */
                    sqlH.insertIntoUploadTable(srvTableNsrvExtendedTable.insertInToSrvTable());
                }

                /**                                         * min Srv Date                                         */
                ServiceActivity.saveServiceMinumDate(srvData, srvData.getServiceDTCode(), srvTableNsrvExtendedTable,sqlH);

                /**                                         * max date                                         */
                ServiceActivity.saveServiceMaxDate(srvData, srvData.getServiceDTCode(), srvTableNsrvExtendedTable,sqlH);


            }

            Toast.makeText(this, "save successfully", Toast.LENGTH_LONG).show();



        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void testLogDebug() {
        Log.d("Nirzon", "Service Data"
                        + " srvData.getHh_name() :" + srvData.getHh_name()
                        + " srvData.getHh_mm_name() :" + srvData.getHh_mm_name()
                        + " srvData.getC_code() :" + srvData.getC_code()
                        + " srvData.getDistrictCode() :" + srvData.getDistrictCode()
                        + " srvData.getUpazillaCode() :" + srvData.getUpazillaCode()
                        + " srvData.getUnitCode() :" + srvData.getUnitCode()
                        + " srvData.getVillageCode() :" + srvData.getVillageCode()
                        + " srvData.getAward_code() :" + srvData.getAward_code()
                        + " srvData.getDonor_code() :" + srvData.getDonor_code()
                        + " srvData.getProgram_code() :" + srvData.getProgram_code()
                        + " srvData.getService_code() :" + srvData.getService_code()
                        + " srvData.getHHID() :" + srvData.getHHID()
                        + " srvData.getMemberId() :" + srvData.getMemberId()
                        + "\n srvData.getOpCode() :" + srvData.getOpCode()
                        + "\n srvData.getOpMontheCode() :" + srvData.getOpMontheCode()
                        + "\n srvData.getServiceSLCode() :" + srvData.getServiceSLCode()
                        + "\n srvData.getServiceCenterCode() :" + srvData.getServiceCenterCode()
                        + "\n srvData.getServiceDate() :" + srvData.getServiceDTCode()
                        + "\n srvData.getTemIdServiceMonth() :" + srvData.getTemIdServiceMonth()
                        + "\n srvData.getTemStrSrvMonth() :" + srvData.getTemStrSrvMonth()
//                + "\n srvData.getServiceDate() :" + srvData.getServiceDTCode()
//
        );

    }

    private void viewReference() {
        lvVoucher = (ListView) findViewById(R.id.lv_Voucher);

        tvhouseHoldName = (TextView) findViewById(R.id.tv_srv_vouHHName);
        tvhouseMemberID = (TextView) findViewById(R.id.tv_srv_vouMemberId);
        tvMemberName = (TextView) findViewById(R.id.tv_srv_vouMemberName);
        edtVoucherRef = (EditText) findViewById(R.id.edt_vio_refNumber);
        btn_Save = (Button) findViewById(R.id.btnHomeFooter);
        btn_Save.setText("Save");
        btn_Service = (Button) findViewById(R.id.btnRegisterFooter);
        btn_Service.setText("Service");

    }


    public void loadVoucherListView(String cCode, String discode, String upCode, String unCode, String vCode, String hhId, String memId, String donorCode, String awardCode, String programCode,
                                    String serviceCode, String opMonthCode) {
        Log.d(TAG, "In load service List ");
        // use veriable to like operation
        List<VouItemServiceExtDataModel> itemlistData = sqlH.getSrvExtedVoucherDataList(cCode, discode, upCode, unCode, vCode, hhId, memId, donorCode, awardCode, programCode, serviceCode, opMonthCode);
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
         * @link: http://www.webplusandroid.com/creating-listview-with-edittext-and-textwatcher-in-android/
         */

        ArrayList<VouItemServiceExtDataModel> servicedExtendedData;

        private Activity activity;

        private SQLiteHandler sqlH = null;

        private LayoutInflater inflater;

        private String opMonthCode;
        private int count = 0;

        private ServiceDataModel srvDetails;

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

            final viewHolder holder;


            if (inflater == null)
                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                row = inflater.inflate(R.layout.list_row_voucher, null);
                holder = new viewHolder();
                holder.edtUnit = (EditText) row.findViewById(R.id.row_voucherQuantity);
                holder.txtItemName = (TextView) row.findViewById(R.id.row_txt_itemName);
//                holder.txtItemID = (TextView) row.findViewById(R.id.row_txt_voID);


                row.setTag(holder);

            } else {
                holder = (viewHolder) row.getTag();
            }

            holder.txtItemName.setText(personToBeServicedExted.getItemName());
            // to check
//            holder.txtItemID.setText(personToBeServicedExted.getVoItmSpec());


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
            Log.d("Arpon", "cost " + cost);
            personToBeServicedExted.setVoItemCost(cost);


          /*  Log.d(TAG, "ServiceExted Data"
                    // + " personToBeServicedExted.getHh_name() :" + personToBeServicedExted.getHh_name()
                    //   + " personToBeServicedExted.getHh_mm_name() :" + personToBeServicedExted.getHh_mm_name()
                    + " personToBeServicedExted.getC_code() :" + personToBeServicedExted.getC_code()
                    + " personToBeServicedExted.getDistrictCode() :" + personToBeServicedExted.getDistrictCode()
                    + " personToBeServicedExted.getUpazillaCode() :" + personToBeServicedExted.getUpazillaCode()
                    + " personToBeServicedExted.getUnitCode() :" + personToBeServicedExted.getUnitCode()
                    + " personToBeServicedExted.getVillageCode() :" + personToBeServicedExted.getVillageCode()
                    + " personToBeServicedExted.getAward_code() :" + personToBeServicedExted.getAward_code()
                    + " srvpersonToBeServicedExtedData.getDonor_code() :" + personToBeServicedExted.getDonor_code()
                    + " personToBeServicedExted.getProgram_code() :" + personToBeServicedExted.getProgram_code()
                    + " personToBeServicedExted.getService_code() :" + personToBeServicedExted.getService_code()
                    + " personToBeServicedExted.getHHID() :" + personToBeServicedExted.getHHID()
                    + "\n personToBeServicedExted.getMemberId() :" + personToBeServicedExted.getHh_mm_id()
                    + "\n personToBeServicedExted.getOpMontheCode() :" + personToBeServicedExted.getOpMontheCode()
                    + "\n personToBeServicedExted.getVoItmSpec() :" + personToBeServicedExted.getVoItmSpec()

            );*/
            // set checked change listener
            cbId_holder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                   /* Saving Checked Position
                            */
                    mChecked.put(position, isChecked);


                    // get the object of sepecific row & set the value of the
                    // save the chekbox of that particular state

                    int getPosition = (Integer) buttonView.getTag();
                    getServicedPerson(getPosition).setCheckBox(isChecked);
                    addDataToArrayList(personToBeServicedExted,
                            mChecked.get(position));


                    Log.d(TAG, " list size :" + listOFWant2Save.size());
                    /** old state*/


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

            //     Log.d(TAG, " position " + position + " the check box  is svaved " + cbId_holder.isChecked());
//            Log.d(TAG, " position " + position + " Does he have this service : " + personToBeServicedExted.isCheckBox());

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
            holder.edtUnit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    edtVoItmUnitCode[holder.reference] = s.toString();

                }
            });

            return row;
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

             /*   Log.d("ARRAY_LIST", " In the array list s Data to save" + "\n is CheckBox() :" + s.isCheckBox()
                                + " \nsrvE.getC_code() :" + s.getC_code() + " srvE.getDistrictCode() :" + s.getDistrictCode()
                                + " srvE.getUpazillaCode() :" + s.getUpazillaCode() + " srvE.getUnitCode() :" + s.getUnitCode()
                                + " srvE.getVillageCode() :" + s.getVillageCode() + " srvE.getAward_code() :" + s.getAward_code()
                                + " srvE.getDonor_code() :" + s.getDonor_code() + " srvE.getProgram_code() :" + s.getProgram_code()
                                + " srvE.getService_code() :" + s.getService_code() + " srvE.getHHID() :" + s.getHHID() + " srvE.getMemberId() :" + s.getMemberId()
                                + " srvE.getOpMontheCode() :" + s.getOpMontheCode() + " srvE.setOpCode() :" + s.getOpCode()
//
                );
                Log.d("ARRAY_SIZE", "listOFWant2Save size :" + listOFWant2Save.size());*/
            } else {
                if (listOFWant2Save.contains(s)) {// first check the data is exits ing or not

                    listOFWant2Save.remove(s);

                }

            }

        }

        // this method called from activity
        public ArrayList<VouItemServiceExtDataModel> getArrayList() {
            return listOFWant2Save;
        }

        class viewHolder {
            EditText edtUnit;
            TextView txtItemName;
            //            TextView txtItemID;
            // for save the state of current senario
            int reference;


        }
    }
}
