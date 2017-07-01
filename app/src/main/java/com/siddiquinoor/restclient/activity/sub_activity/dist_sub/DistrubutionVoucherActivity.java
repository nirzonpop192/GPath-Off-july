package com.siddiquinoor.restclient.activity.sub_activity.dist_sub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
//import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.DistributionActivity;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.data_model.adapters.DistributionGridDataModel;
import com.siddiquinoor.restclient.data_model.adapters.DistributionSaveDataModel;
import com.siddiquinoor.restclient.views.adapters.VouItemServiceExtDataModel;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DistrubutionVoucherActivity extends BaseActivity {
    private static final String TAG = DistrubutionVoucherActivity.class.getSimpleName();

    /**
     * To save checked items, and re-add while scrolling.
     */
    SparseBooleanArray mChecked = new SparseBooleanArray();

    /**
     * For get  EditText value from listst view
     */
    private String[] edtVoItmUnitCode;
    private String[] voItmIDInListView;

    private ArrayList<VouItemServiceExtDataModel> voItemArray = new ArrayList<VouItemServiceExtDataModel>();

    Button btnSave;
    Button btnDistributionPage;
    ListView lv_distExtVou;
    TextView tv_MemberId;
    TextView tv_MemberName;
    EditText edt_vouReference;

    private SQLiteHandler sqlH;
    DistributionGridDataModel distData;

    private DistExtVoucherAdapter adapter;
    private ADNotificationManager dialog;
    private DistributionSaveDataModel distDataP;
    private boolean dataExitsInDistExtenTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribution_voucher);
        sqlH = new SQLiteHandler(this);
        dialog = new ADNotificationManager();
        viewReference();
        Intent i = getIntent();
        distData = (DistributionGridDataModel) i.getParcelableExtra(KEY.DISTRIBUTION_DATA_OBJECT_KEY);

        distDataP = new DistributionSaveDataModel();
        distDataP.setCountryCode(distData.getC_code());
        distDataP.setDistrictCode(distData.getDistrictCode());
        distDataP.setUpCode(distData.getUpazillaCode());
        distDataP.setUniteCode(distData.getUnitCode());
        distDataP.setVillageCode(distData.getVillageCode());
        distDataP.setID(distData.getRpt_id());
        distDataP.setAdmDonorCode(distData.getDonorCode());
        distDataP.setAdmAwardCode(distData.getAwardCode());
        distDataP.setProgCode(distData.getProgram_code());
        distDataP.setSrvCode(distData.getService_code());

        distDataP.setFDPCode(distData.getFdpCode());
        distDataP.setOpMonthCode(distData.getDistOpMonthCode());

        distDataP.setSrvOpMonthCode(distData.getSrvOpMonthCode());
        distDataP.setDistFlag(distData.getDistFlag());


        /** to chek service value */
        testLogDebug();

        tv_MemberId.setText(distData.getRpt_id());
        tv_MemberName.setText(distData.getRpt_name());

        if (sqlH.isDataExitedDistExtendedTable(distData.getC_code(), distData.getDistrictCode(), distData.getUpazillaCode(), distData.getUnitCode(),
                distData.getVillageCode(), distData.getRpt_id(), distData.getDonorCode()
                , distData.getAwardCode(), distData.getProgram_code(), distData.getService_code(), distData.getDistOpMonthCode(), distData.getFdpCode())) {

            String vioRefetNumber = sqlH.getVoucherRefNoFromDistExted(distData.getC_code(), distData.getDistrictCode(), distData.getUpazillaCode(), distData.getUnitCode(),
                    distData.getVillageCode(), distData.getRpt_id(), distData.getDonorCode()
                    , distData.getAwardCode(), distData.getProgram_code(), distData.getService_code(), distData.getDistOpMonthCode(), distData.getFdpCode());
            Log.d(TAG, "Voi refe no" + vioRefetNumber);

            edt_vouReference.setText(vioRefetNumber);
        }


        loadDistExtVoucherListView(distData.getC_code(), distData.getDistrictCode(), distData.getUpazillaCode(), distData.getUnitCode()
                , distData.getVillageCode(), distData.getRpt_id(), distData.getDonorCode(), distData.getAwardCode(), distData.getProgram_code(), distData.getService_code(), distData.getSrvMonthCode(), distData.getFdpCode());


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });


        btnDistributionPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent iDistActivity = new Intent(DistrubutionVoucherActivity.this, DistributionActivity.class);
                iDistActivity.putExtra(KEY.DIR_CLASS_NAME_KEY, "DistrubutionVoucherActivity");

                iDistActivity.putExtra(KEY.COUNTRY_ID, distData.getC_code());
                iDistActivity.putExtra(KEY.AWARD_CODE, distData.getAwardCode());
                iDistActivity.putExtra(KEY.AWARD_NAME, distData.getTempAwardString());
                iDistActivity.putExtra(KEY.DONOR_CODE, distData.getDonorCode());
                iDistActivity.putExtra(KEY.PROGRAM_CODE,distData.getProgram_code());
                iDistActivity.putExtra(KEY.PROGRAM_NAME,distData.getTempProgString());
                iDistActivity.putExtra(KEY.DISTRIBUTION_TYPE_CODE,distData.getTempDistTypeId());
                iDistActivity.putExtra(KEY.DISTRIBUTION_TYPE_NAME,distData.getTempDistTypeString());
                iDistActivity.putExtra(KEY.SERVICE_MONTH_CODE,distData.getSrvMonthCode());
                iDistActivity.putExtra(KEY.SERVICE_MONTH_NAME,distData.getTempsrvMonthName());
                iDistActivity.putExtra(KEY.DISTRIBUTION_MONTH_CODE,distData.getDistOpMonthCode());
                iDistActivity.putExtra(KEY.DISTRIBUTION_MONTH_NAME,distData.getTempDistMonthName());


                iDistActivity.putExtra(KEY.LAYER_2_CODE,distData.getUpazillaCode());// in liberia & layer2  is District  & in malawin Upzaller
                iDistActivity.putExtra(KEY.LAYER_2_NAME,distData.getTempUpazillaName());
                iDistActivity.putExtra(KEY.FDP_CODE,distData.getFdpCode());
                iDistActivity.putExtra(KEY.FDP_NAME,distData.getTempFDPName());
//                iService.putExtra(KEY.SERVICE_CENTER_CODE,srvData.getServiceCenterCode());
//                iService.putExtra(KEY.SERVICE_CENTER_CODE,srvData.getServiceCenterCode());
//                iService.putExtra(KEY.SERVICE_CENTER_NAME,srvData.getTemServiceCenterName());
//                iService.putExtra(KEY.SERVICE_DATE,srvData.getTemServiceDate());
//                iService.putExtra(KEY.OP_MONTH_LABLE,srvData.getOpMonthStr());
//                iService.putExtra(KEY.OP_MONTH_CODE,srvData.getOpMontheCode());
//                iService.putExtra(KEY.OP_CODE,srvData.getOpCode());
                startActivity(iDistActivity);
            }
        });

    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }

    private void saveData() {

        String voRefNo = "";
        voRefNo = edt_vouReference.getText().toString();
        String EntryBy = "";
        String EntryDate = "";

        if (voRefNo.equals("")) {
            //  show the error massage

            dialog.showErrorDialog(DistrubutionVoucherActivity.this, "Insert the reference number");
        } else {

            try {
                EntryBy = getStaffID();
                EntryDate = getDateTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            distDataP.setEntryBy(EntryBy);
            distDataP.setEntryDate(EntryDate);

            distDataP.setDistStatus("R");

            long id = sqlH.addInDistributionTable(distDataP);
            // todo : dclare  SQLServerSyntax object in once
            SQLServerSyntaxGenerator distributedData = new SQLServerSyntaxGenerator();
            distributedData.setAdmCountryCode(distDataP.getCountryCode());
            distributedData.setAdmDonorCode(distDataP.getAdmDonorCode());
            distributedData.setAdmAwardCode(distDataP.getAdmAwardCode());
            distributedData.setLayR1ListCode(distDataP.getDistrictCode());
            distributedData.setLayR2ListCode(distDataP.getUpCode());
            distributedData.setLayR3ListCode(distDataP.getUniteCode());
            distributedData.setLayR4ListCode(distDataP.getVillageCode());
            distributedData.setProgCode(distDataP.getProgCode());
            distributedData.setSrvCode(distDataP.getSrvCode());
            distributedData.setOpMonthCode(distDataP.getOpMonthCode());
            distributedData.setFDPCode(distDataP.getFDPCode());
            distributedData.setID(distDataP.getID());
            distributedData.setDistStatus(distDataP.getDistStatus());

            distributedData.setSrvOpMonthCode(distDataP.getSrvOpMonthCode());
            distributedData.setDistFlag(distDataP.getDistFlag());

            distributedData.setEntryBy(EntryBy);
            distributedData.setEntryDate(EntryDate);
            sqlH.insertIntoUploadTable(distributedData.insertIntoDistributionTable());


            ArrayList<VouItemServiceExtDataModel> alist = new ArrayList<VouItemServiceExtDataModel>();
            alist = adapter.distExtendedData;
            Log.d(TAG, "alit size " + alist.size());
            /** set  if condition */

            if (adapter.isArrayListNull()) {

                Toast.makeText(getApplicationContext(), "No date is selected  a valid date! Please select data!!", Toast.LENGTH_LONG).show();
            } else {
                /** if the array list is not null */

                for (int i = 0; i < lv_distExtVou.getAdapter().getCount(); i++) {
                    if (mChecked.get(i)) {
                        // Do something
                        Log.d(TAG, "the number of Check ite :" + i);

                        VouItemServiceExtDataModel dstE = alist.get(i);

                        Log.d("NIR2","edtVoItmUnitCode["+i+"]="+edtVoItmUnitCode[i]);

                        /** setup SQL Server Syntax variable*/


                        if ((sqlH.isDataExitedDistExtendedTable(distData.getC_code(), distData.getDistrictCode(), distData.getUpazillaCode(), distData.getUnitCode(),
                                distData.getVillageCode(), distData.getRpt_id(), distData.getDonorCode()
                                , distData.getAwardCode(), distData.getProgram_code(), distData.getService_code(), distData.getDistOpMonthCode(), distData.getFdpCode())) && i == 0) {

                            sqlH.deleteFromDistExtendedTable(distData.getC_code(), distData.getDistrictCode(), distData.getUpazillaCode(), distData.getUnitCode(),
                                    distData.getVillageCode(), distData.getRpt_id(), distData.getDonorCode()
                                    , distData.getAwardCode(), distData.getProgram_code(), distData.getService_code(), distData.getDistOpMonthCode(), distData.getFdpCode());
                            Log.d(TAG, " Delete data from DistExtend table SQLite  ");
//
//                            /** todo:  setup upload  delete syntax for Srv extended table*/
//
                            sqlH.insertIntoUploadTable(distributedData.deleteFromDistExtendedTable());
                        }
                        sqlH.addInDistributionExtendedTable(distData.getC_code(), distData.getDonorCode(), distData.getAwardCode(),
                                distData.getDistrictCode(), distData.getUpazillaCode(), distData.getUnitCode(), distData.getVillageCode(), distData.getProgram_code(),
                                distData.getService_code(), distData.getDistOpMonthCode(), distData.getFdpCode(), distData.getRpt_id(), voItmIDInListView[i], edtVoItmUnitCode[i],
                                voRefNo,distData.getSrvOpMonthCode(),distData.getDistFlag(), EntryBy, EntryDate, "0");

                        distributedData.setvOItmUnit(edtVoItmUnitCode[i]);
                        distributedData.setvOItmSpec(voItmIDInListView[i]);
                        distributedData.setvORefNumber(voRefNo);

                        /**  todo : set the variable than insert  upload Table*/

//                        srvTableNsrvExtendedTable.setvOItmUnit(edtVoItmUnitCode[i]);
//                        srvTableNsrvExtendedTable.setvOItmSpec(voItmIDInListView[i]);
//                        srvTableNsrvExtendedTable.setvOItmCost(srvE.getVoItemCost());

                        sqlH.insertIntoUploadTable(distributedData.insertIntoDistExtendedTable());


                    }
                }


            }// end of for

            Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
        }


    }


    private void testLogDebug() {


        Log.d("Nirzon", "Service Data"
                + " distData.getHh_name() :" + distData.getRpt_name()
                + " distData.getMem_name() :" + distData.getRpt_name()
                + " distData.getC_code() :" + distData.getC_code()
                + " distData.getDistrictCode() :" + distData.getDistrictCode()
                + " distData.getUpazillaCode() :" + distData.getUpazillaCode()
                + " distData.getUnitCode() :" + distData.getUnitCode()
                + " distData.getVillageCode() :" + distData.getVillageCode()
                + " distData.getDonorCode() :" + distData.getDonorCode()
                + " distData.getAwardCode() :" + distData.getAwardCode()
                + " distData.getProgram_code() :" + distData.getProgram_code()
                + " distData.getService_code() :" + distData.getService_code()
                + " distData.getHh_id() :" + distData.getRpt_id()
                + " distData.getMem_id() :" + distData.getRpt_id()
                + " distData.getSrvMonthCode() :" + distData.getSrvMonthCode()
                + " distData.getServiceCenter() :" + distData.getServiceCenter()
                + " distData.getServiceShortName() :" + distData.getServiceShortName()
                + " distData.getDistOpMonthCode() :" + distData.getDistOpMonthCode()
                + " distData.getFdpCode() :" + distData.getFdpCode()
                + " distData.getSrvOpMonthCode() :" + distData.getSrvOpMonthCode()
                + " distData.getDistFlag() :" + distData.getDistFlag()

        );

    }

    private void viewReference() {


        btnSave = (Button) findViewById(R.id.btnHomeFooter);
        btnSave.setText("Save");
        btnDistributionPage = (Button) findViewById(R.id.btnRegisterFooter);

        btnDistributionPage.setText("Distribution");
        lv_distExtVou = (ListView) findViewById(R.id.lv_DistExtVoucher);
        tv_MemberId = (TextView) findViewById(R.id.tv_dist_vouMemberId);
        tv_MemberName = (TextView) findViewById(R.id.tv_dist_vouMemberName);
        edt_vouReference = (EditText) findViewById(R.id.edt_dist_vio_refNumber);
    }

    public void loadDistExtVoucherListView(String cCode, String discode, String upCode, String unCode, String vCode, String memId, String donorCode, String awardCode, String programCode,
                                           String serviceCode, String opMonthCode, String fdpCode) {
        Log.d(TAG, "In load Dist Extention List ");



        if (sqlH.isDataExitedDistExtendedTable(distData.getC_code(), distData.getDistrictCode(), distData.getUpazillaCode(), distData.getUnitCode(),
                distData.getVillageCode(), distData.getRpt_id(), distData.getDonorCode()
                , distData.getAwardCode(), distData.getProgram_code(), distData.getService_code(), distData.getDistOpMonthCode(), fdpCode)) {
            dataExitsInDistExtenTable = true;
        } else {
            dataExitsInDistExtenTable = false;
        }
        // use veriable to like operation
        List<VouItemServiceExtDataModel> itemlistData = sqlH.getDistExtedVoucherDataList(cCode, discode, upCode, unCode, vCode, memId, donorCode, awardCode, programCode, serviceCode, opMonthCode, fdpCode, dataExitsInDistExtenTable);
        /** @date: 2015-10-13
         * @description: Make a provision where if there is not data for the selected criteria then grid will be empty.*//*
*/

        if (itemlistData.size() != 0) {
            voItemArray.clear();
            for (VouItemServiceExtDataModel data : itemlistData) {
                // add contacts data in arrayList
                voItemArray.add(data);
            }
            adapter = new DistExtVoucherAdapter(this, voItemArray/*, srvData*/);
            lv_distExtVou.setAdapter(adapter);

            lv_distExtVou.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
            lv_distExtVou.setFocusableInTouchMode(true);
        }

    }


    /**
     * adpater class
     */
    public class DistExtVoucherAdapter extends BaseAdapter {
        public ArrayList<VouItemServiceExtDataModel> distExtendedData;

        private Activity activity;

        private SQLiteHandler sqlH = null;

        private LayoutInflater inflater;
        private DistributionGridDataModel distData;
        private int count = 0;
         viewHolder holder;

        public DistExtVoucherAdapter(Activity activity, ArrayList<VouItemServiceExtDataModel> distExtendedData/*, DistributionGridDataModel distData*/) {
            this.activity = activity;
            this.distExtendedData = distExtendedData;
            this.distData = distData;

            edtVoItmUnitCode = new String[distExtendedData.size()];
            voItmIDInListView = new String[distExtendedData.size()];

            sqlH = new SQLiteHandler(activity);
            listOFWant2Save.clear();
        }

        @Override
        public int getCount() {
            count = distExtendedData.size();
            return count;
        }

        @Override
        public Object getItem(int position) {

            return distExtendedData.get(position);

        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            final VouItemServiceExtDataModel personToBeServicedExtedS = distExtendedData.get(position);
            View row = convertView;

//            final ViewHolder holder;


            if (inflater == null)
                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                row = inflater.inflate(R.layout.list_row_dist_exted_voucher, null);
                holder = new viewHolder();
                holder.edtUnit = (EditText) row.findViewById(R.id.row_dist_voucherQuantity);
                holder.txtItemName = (TextView) row.findViewById(R.id.row_txt_dist_vou_itemName);
                holder.tvItemMesusements = (TextView) row.findViewById(R.id.row_txt_dist_Measurement);


                row.setTag(holder);

            } else {
                holder = (viewHolder) row.getTag();
            }

            holder.txtItemName.setText(personToBeServicedExtedS.getItemName());
            holder.tvItemMesusements.setText(personToBeServicedExtedS.getMeasurments());

            voItmIDInListView[position] = personToBeServicedExtedS.getVoItmSpec();

            // check box reference is define here
            CheckBox cbId_holder = (CheckBox) row.findViewById(R.id.cb_dist_ext_voucher_id_holde);

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
                    addDataToArrayList(personToBeServicedExtedS,
                            true);

                    /** OLD cODE
                     *  addDataToArrayList(personToBeServicedExtedS,
                     mChecked.get(position));
                     */


                    Log.d(TAG, " list size :" + listOFWant2Save.size());
                    /** old state*/


                }
            });

            // set the sate of particular positioned check box
            cbId_holder.setTag(position);

            /**
             * Set CheckBox "TRUE" or "FALSE" if mChecked == true or in db staff have provided service to him
             */
            cbId_holder.setChecked((mChecked.get(position) == true));
//            if (personToBeServicedExted.isCheckBox() ? true : false) {
//                mChecked.put(position, true);
//            }


            if (dataExitsInDistExtenTable){
                if (personToBeServicedExtedS.getVoItmUnit() != null) {
                    if (!personToBeServicedExtedS.getVoItmUnit().equals("")) {
                        edtVoItmUnitCode[position] = personToBeServicedExtedS.getVoItmUnit();
                    }
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
//                    edtVoItmUnitCode[holder.reference] = s.toString();
                    edtVoItmUnitCode[position] = s.toString();

                }
            });
                /** set the color of bg && text*/
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
            holder.tvItemMesusements.setTextColor(color);
        }


        public boolean isArrayListNull() {
            if (listOFWant2Save.isEmpty())
                return true;
            else
                return false;


        }

        class viewHolder {
            EditText edtUnit;
            TextView txtItemName;
            TextView tvItemMesusements;

            // for save the state of current senario
            int reference;


        }

        // this method called from activity
        public ArrayList<VouItemServiceExtDataModel> getArrayList() {
            return listOFWant2Save;
        }

        VouItemServiceExtDataModel getServicedPerson(int position) {
            return (VouItemServiceExtDataModel) getItem(position);
        }


        private ArrayList<VouItemServiceExtDataModel> listOFWant2Save = new ArrayList<VouItemServiceExtDataModel>();

        private void addDataToArrayList(VouItemServiceExtDataModel s, boolean chackBoxStatus) {
            if (chackBoxStatus) {

                listOFWant2Save.add(s);


            } else {
                if (listOFWant2Save.contains(s)) {// first check the data is exits ing or not

                    listOFWant2Save.remove(s);

                }

            }

        }
    }
}
