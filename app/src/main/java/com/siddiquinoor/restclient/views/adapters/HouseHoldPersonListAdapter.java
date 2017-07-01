package com.siddiquinoor.restclient.views.adapters;

/**
 * This class is a List Adapter helper
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.6.0
 * @since 1.0
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.Register;
import com.siddiquinoor.restclient.activity.RegisterLiberia;
import com.siddiquinoor.restclient.activity.sub_activity.summary_sub.ViewRecordDetail;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.util.ArrayList;

public class HouseHoldPersonListAdapter extends BaseAdapter {


    private Activity activity;
    private LayoutInflater inflater;
    ArrayList<ListDataModel> registrationData = new ArrayList<ListDataModel>();

    int pId;
    String regID;
    String country_code;
    String country_name;
    String personName;

    String district;
    String upazilla;
    String unit;
    String village;

    String districtCode;
    String upazillaCode;
    String unitCode;
    String villageCode;

    String regDate;
    String sex;
    String hhSize;
    String agLand;
    String vstatus;
    String mstatus;
    String entryBy;
    String entryDate;
    String vsla_group;
    String address_code;
    String address_name;
    String rankCode;

    ArrayList<String> occupation;
    //String sOccupation;
    String income;
    String latitude;
    String longitude;

    private SQLiteHandler sqlH = null;


    public HouseHoldPersonListAdapter(Activity activity, ArrayList<ListDataModel> registrationData) {
        this.activity = activity;
        this.registrationData = registrationData;
        sqlH = new SQLiteHandler(activity);
    }

    @Override
    public int getCount() {
        return registrationData.size();
    }

    @Override
    public Object getItem(int location) {
        return registrationData.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ListHolder holder = null;

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row, null);

            holder = new ListHolder();

            holder.hhID = (TextView) row.findViewById(R.id.s_row_hhId);
            holder.hhName = (TextView) row.findViewById(R.id.hh_name);
            holder.imgEdit = (ImageView) row.findViewById(R.id.edit_service_holder);
//            holder.imgDelete = (ImageView) row.findViewById(R.id.delete_house_hold);
            holder.imgView = (ImageView) row.findViewById(R.id.servicedHolderDataView);


            holder.hhName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoSummaryReportPage(position);
                }
            });


            holder.imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoSummaryReportPage(position);
                }
            });

            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //do other stuff
                    notifyDataSetChanged();
                    int selectedID = registrationData.get(position).getID();
                    // get all data, putExtra then start Activity Register
                    // Intent dIntent = new Intent(activity, Register.class);
                    Intent dIntent;

                    activity.finish();

                    country_name = registrationData.get(position).getCountryName();
                    district = registrationData.get(position).getDistrict();
                    upazilla = registrationData.get(position).getUpazilla();
                    unit = registrationData.get(position).getUnitName();
                    village = registrationData.get(position).getVillage();

                    country_code = registrationData.get(position).getCountryCode();                 // here is country id
                    districtCode = registrationData.get(position).getDistrictCode();                // spinner as 01/02
                    upazillaCode = registrationData.get(position).getUpazillaCode();                // spinner as 01/02
                    unitCode = registrationData.get(position).getUnitNameCode();                    // spinner as 01/02
                    villageCode = registrationData.get(position).getVillageCode();                  // spinner as 01/02

                    regID = registrationData.get(position).getRegID();
                    regDate = registrationData.get(position).getRegDate();
                    personName = registrationData.get(position).getName();
                    sex = registrationData.get(position).getSex();
                    hhSize = registrationData.get(position).getHhSize();
                    latitude = registrationData.get(position).getLatitude();
                    longitude = registrationData.get(position).getLongitude();
                    agLand = registrationData.get(position).getAgLand();
                    vstatus = registrationData.get(position).getvStatus();
                    mstatus = registrationData.get(position).getmStatus();
                    entryBy = registrationData.get(position).getEntryBy();
                    entryDate = registrationData.get(position).getEntryDate();
                    vsla_group = registrationData.get(position).getVSLAGroup();
                    pId = registrationData.get(position).getID();

                    address_code=registrationData.get(position).getAddressCode();
                    address_name=registrationData.get(position).getAddressName();

                    rankCode=registrationData.get(position).getRank();
                  //  Log.d("MI","address_code:"+address_code+" address_name: "+address_name);

                    if (country_code.equals("0004")) {
                        dIntent = new Intent(activity, RegisterLiberia.class);
                    }
                    else {
                        //todo:  for  laiberia need  for work
                        dIntent = new Intent(activity, Register.class);
                    }
                        dIntent.putExtra(KEY.IS_EDIT, true);
                        dIntent.putExtra(KEY.REDIRECT, "");

                        dIntent.putExtra(KEY.HOUSE_HOLD_UNIQUE_ID, selectedID);


                        dIntent.putExtra(KEY.COUNTRY_NAME, country_name);
                        dIntent.putExtra(KEY.DISTRICT, district);
                        dIntent.putExtra(KEY.UPAZILLA, upazilla);
                        dIntent.putExtra(KEY.UNIT, unit);
                        dIntent.putExtra(KEY.VILLAGE_NAME, village);

                        dIntent.putExtra(KEY.COUNTRY_CODE, country_code);
                        dIntent.putExtra(KEY.DISTRICT_CODE, districtCode);
                        dIntent.putExtra(KEY.UPAZILLA_CODE, upazillaCode);

                        dIntent.putExtra(KEY.UNIT_CODE, unitCode);
                        dIntent.putExtra(KEY.VILLAGE_CODE, villageCode);

                        dIntent.putExtra(KEY.REG_ID, regID);
                        dIntent.putExtra(KEY.REG_DATE, regDate);
                        dIntent.putExtra(KEY.PERSON_NAME, personName);
                        dIntent.putExtra(KEY.SEX, sex);
                        dIntent.putExtra(KEY.HH_SIZE, hhSize);
                        dIntent.putExtra(KEY.LATITUDE, latitude);
                        dIntent.putExtra(KEY.LONGITUDE, longitude);
                        dIntent.putExtra(KEY.AG_LAND, agLand);
                        dIntent.putExtra(KEY.VSTATUS, vstatus);
                        dIntent.putExtra(KEY.MSTATUS, mstatus);
                        dIntent.putExtra(KEY.ENTRY_BY, entryBy);
                        dIntent.putExtra(KEY.ENTRY_DATE, entryDate);
                        dIntent.putExtra(KEY.VSLA_GROUP, vsla_group);

                        dIntent.putExtra(KEY.ADDRESS_CODE, address_code);
                        dIntent.putExtra(KEY.ADDRESS_NAME, address_name);
                        dIntent.putExtra(KEY.WALTH_RANK, rankCode);


                        //dIntent.putExtra("pId", pId);
                 //   }
                    activity.startActivity(dIntent);



                }
            });

      /*      holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    *//**  check delete permission  if ok than delete*//*
                    // dialog.showInfromDialog(mContext,"Save Denied","You don't have Save Permission .Pls contact with Admin");
                    if (sqlH.getDeletePermissionForHHEntries(registrationData.get(position).getCountryCode(), registrationData.get(position).getDistrictCode(), registrationData.get(position).getUpazillaCode(), registrationData.get(position).getUnitNameCode(), registrationData.get(position).getVillageCode())) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
                        alertDialog.setTitle("Confirm Delete...");
                        alertDialog.setMessage("Are you sure delete this Household?");
                        alertDialog.setPositiveButton("YES",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int selectedID) {

                                        String deleteID = registrationData.get(position).getRegID();
                                        //  get house hold id with LayRList
                                        SQLServerSyntaxGenerator HHId = sqlH.getDeletedHouseHoldID(deleteID);

                                        // delete query for House hold table
                                        sqlH.insertIntoUploadTable(HHId.deleteHouseHoldFormRegNHouseHoldTable());
                                        // delete query for member table
                                        sqlH.insertIntoUploadTable(HHId.deleteHouseHoldFormRegNHHMemberTable());

                                        sqlH.deleteHouseHold(deleteID);

                                        registrationData.remove(position);

//                                        Toast.makeText(activity, "One Household deleted successfully!", Toast.LENGTH_LONG).show();
                                        notifyDataSetChanged();

                                    }
                                });

                        alertDialog.setNegativeButton("NO",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        alertDialog.show();
                    } //end of the if
                    // show
                    else {
                        ADNotificationManager dialog = new ADNotificationManager();
                        dialog.showInfromDialog(activity, "Delete Denied", "You don't have Delete Permission .Please contact with Admin");

                    }

                }
            });*/


            row.setTag(holder);
        } else {
            holder = (ListHolder) row.getTag();
        }


        ListDataModel m = registrationData.get(position);

        holder.hhID.setText(m.getRegID());  // Registration ID or Holding ID
        holder.hhName.setText(m.getName());
        return row;
    }

    private void gotoSummaryReportPage(int position) {

        Intent dIntent = new Intent(activity, ViewRecordDetail.class);


        country_name = registrationData.get(position).getCountryName();     // spinner as 01/02
        district = registrationData.get(position).getDistrict();     // spinner as 01/02
        upazilla = registrationData.get(position).getUpazilla();     // spinner as 01/02
        unit = registrationData.get(position).getUnitName();     // spinner as 01/02
        village = registrationData.get(position).getVillage();      // spinner as 01/02

        country_code = registrationData.get(position).getCountryCode();        // spinner
        districtCode = registrationData.get(position).getDistrictCode();     // spinner as 01/02
        upazillaCode = registrationData.get(position).getUpazillaCode();     // spinner as 01/02
        unitCode = registrationData.get(position).getUnitNameCode();     // spinner as 01/02
        villageCode = registrationData.get(position).getVillageCode();      // spinner as 01/02


        regID =districtCode+upazillaCode+unitCode+villageCode+ registrationData.get(position).getRegID();
        regDate = registrationData.get(position).getRegDate();
        personName = registrationData.get(position).getName();
        sex = registrationData.get(position).getSex();          // spinner as 'M'/'F'
        hhSize = registrationData.get(position).getHhSize();
        latitude = registrationData.get(position).getLatitude();
        longitude = registrationData.get(position).getLongitude();
        agLand = registrationData.get(position).getAgLand();
        vstatus = registrationData.get(position).getvStatus();      // spinner as 'Y'/'N'
        mstatus = registrationData.get(position).getmStatus();      // spinner as 'Y'/'N'
        entryBy = registrationData.get(position).getEntryBy();
        entryDate = registrationData.get(position).getEntryDate();
        vsla_group = registrationData.get(position).getVSLAGroup();

        pId = registrationData.get(position).getID();

        address_code=registrationData.get(position).getAddressCode();
        address_name=registrationData.get(position).getAddressName();


        dIntent.putExtra(KEY.COUNTRY_NAME, country_name);
        dIntent.putExtra(KEY.DISTRICT, district);
        dIntent.putExtra(KEY.UPAZILLA, upazilla);
        dIntent.putExtra(KEY.UNIT, unit);
        dIntent.putExtra(KEY.VILLAGE_NAME, village);
        dIntent.putExtra(KEY.COUNTRY_CODE, country_code);
        dIntent.putExtra(KEY.DISTRICT_CODE, districtCode);
        dIntent.putExtra(KEY.UPAZILLA_CODE, upazillaCode);
        dIntent.putExtra(KEY.UNIT_CODE, unitCode);
        dIntent.putExtra(KEY.VILLAGE_CODE, villageCode);
        dIntent.putExtra(KEY.REG_ID, regID);
        dIntent.putExtra(KEY.REG_DATE, regDate);
        dIntent.putExtra(KEY.PERSON_NAME, personName);
        dIntent.putExtra(KEY.SEX, sex);
        dIntent.putExtra(KEY.HH_SIZE, hhSize);
        dIntent.putExtra(KEY.LATITUDE, latitude);
        dIntent.putExtra(KEY.LONGITUDE, longitude);
        dIntent.putExtra(KEY.AG_LAND, agLand);
        dIntent.putExtra(KEY.VSTATUS, vstatus);
        dIntent.putExtra(KEY.MSTATUS, mstatus);
        dIntent.putExtra(KEY.ENTRY_BY, entryBy);
        dIntent.putExtra(KEY.ENTRY_DATE, entryDate);
        dIntent.putExtra(KEY.VSLA_GROUP, vsla_group);
        dIntent.putExtra(KEY.P_ID, pId);
        dIntent.putExtra(KEY.ADDRESS_CODE, address_code);
        dIntent.putExtra(KEY.ADDRESS_NAME, address_name);

        dIntent.putExtra(KEY.WALTH_RANK, rankCode);

        activity.startActivity(dIntent);

        //activity.finish();
    }


    static class ListHolder {
        TextView hhID;
        TextView hhName;
        ImageView imgEdit;
       // ImageView imgDelete;
        ImageView imgView;

    }
}
