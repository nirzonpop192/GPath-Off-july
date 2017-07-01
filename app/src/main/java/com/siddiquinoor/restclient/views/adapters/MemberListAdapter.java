package com.siddiquinoor.restclient.views.adapters;

/**
 * This class is a List Adapter helper
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.6.0
 * @since 1.0
 *
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.sub_activity.register_sub.RegisterMember;
import com.siddiquinoor.restclient.activity.sub_activity.register_sub.RegisterMemberLiberia;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;

import java.util.ArrayList;

public class MemberListAdapter extends BaseAdapter {
    public AlertDialog.Builder alert;
    private Activity activity;
    private LayoutInflater inflater;
    ArrayList<MemberModel> MemberData = new ArrayList<MemberModel>();

    int mId;
    int pID;
    String HhID;

    String cName;
    String cCode;
    String dName;
    String dCode;
    String upName;
    String upCode;
    String uName;
    String uCode;
    String vName;
    String vCode;


    String Hhname;
    String MemID;
    String MemberName;
    String gender;
    String age;
    String relation;
    String relation_code;
    String str_elderly;
    String str_disabled;
    String str_lmp_date;
    String str_child_dob;

    String str_entry_by;
    String str_entry_date;

    private SQLiteHandler sqlH = null;




    public MemberListAdapter(Activity activity, ArrayList<MemberModel> MemberData) {
        this.activity = activity;
        this.MemberData = MemberData;
        sqlH = new SQLiteHandler(activity);
    }

    @Override
    public int getCount() {
        return MemberData.size();
    }

    @Override
    public Object getItem(int location) {
        return MemberData.get(location);
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

        if (convertView == null)
        {
            row = inflater.inflate(R.layout.list_members, null);

            holder = new ListHolder();

            holder.member_ID = (TextView)row.findViewById(R.id.member_id);
            holder.member_Name = (TextView)row.findViewById(R.id.member_name);
            holder.memberEdit = (ImageView)row.findViewById(R.id.edit_member);
      /*      holder.memberDelete = (ImageView)row.findViewById(R.id.delete_member);
            holder.memberView = (ImageView)row.findViewById(R.id.memberView);*/



      /*      holder.memberView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    goToNextPage(position);
                }
            });*/



            holder.member_Name.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    goToNextPage(position);
                }
            });



            holder.memberEdit.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    goToNextPage(position);
                }
            });

           /* holder.memberDelete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
                    alertDialog.setTitle("Confirm Delete...");
                    alertDialog.setMessage("Are you sure to delete this Member?");
                    alertDialog.setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int selectedID) {

                                    int deleteID = MemberData.get(position).getID();
                                    SQLServerSyntaxGenerator deletedMemberId=sqlH.getDeletedMemberID(deleteID );
                                    sqlH.deleteMember(deleteID);
                                    MemberData.remove(position);
                                    sqlH.insertIntoUploadTable(deletedMemberId.deleteMemberFormRegNHHMemberTable());
//                                    Toast.makeText(activity, "One Member deleted successfully!", Toast.LENGTH_LONG).show();
                                    notifyDataSetChanged();

                                }
                            });

                    alertDialog.setNegativeButton("NO",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    alertDialog.show();


                    // Delete database operation

                }
            });*/

            row.setTag(holder);
        }
        else
        {
            holder = (ListHolder)row.getTag();
        }


        MemberModel m = MemberData.get(position);
        holder.member_ID.setText(m.getMemID());  // Member ID
        holder.member_Name.setText(m.getMemberName());
        return row;
    }

    private void goToNextPage(int position)
    {


        pID             = MemberData.get(position).getPID();    // Primary key of Household table
        mId             = MemberData.get(position).getID();     // Primary key of Member table
        HhID            = MemberData.get(position).getRegID();  // Household Registration ID
        Hhname          = MemberData.get(position).getName();
        MemID           = MemberData.get(position).getMemID();  // Member registration ID
        MemberName      = MemberData.get(position).getMemberName();
        gender          = MemberData.get(position).getGender();
        age             = MemberData.get(position).getMemberAge();
        relation        = MemberData.get(position).getRelation();
        relation_code   = MemberData.get(position).getRelationCode();

        str_elderly     = MemberData.get(position).getElderly();
        str_disabled    = MemberData.get(position).getDisabled();

        str_lmp_date    = MemberData.get(position).getLMPDate();
        str_child_dob    = MemberData.get(position).getChildDOB();

        cCode           = MemberData.get(position).getCountryCode();
        dName           = MemberData.get(position).getDistrict();
        dCode           = MemberData.get(position).getDistrictCode();
        upName          = MemberData.get(position).getUpazilla();
        upCode          = MemberData.get(position).getUpazillaCode();
        uName           = MemberData.get(position).getUnitName();
        uCode           = MemberData.get(position).getUnitNameCode();
        vName           = MemberData.get(position).getVillage();
        vCode           = MemberData.get(position).getVillageCode();

        str_entry_by    = MemberData.get(position).getEntryBy();
        str_entry_date  = MemberData.get(position).getEntryDate();

        Intent dIntent ;
        if (cCode.equals("0002"))
         dIntent = new Intent(activity, RegisterMember.class);
        else
            dIntent = new Intent(activity, RegisterMemberLiberia.class);

        dIntent.putExtra("page_from", "ViewRecordDetail");
        dIntent.putExtra("is_edit", true);

        dIntent.putExtra("pID", pID);
        dIntent.putExtra("mId", mId);
        dIntent.putExtra("str_hhID", dCode+upCode+uCode+vCode+HhID);
        dIntent.putExtra("str_hhName", Hhname);
        dIntent.putExtra("MemID", MemID);
        dIntent.putExtra("MemberName", MemberName);
        dIntent.putExtra("gender", gender);
        dIntent.putExtra("age", age);
        dIntent.putExtra("relation_code", relation_code);
        dIntent.putExtra("relation", relation);

        dIntent.putExtra("Elderly", str_elderly);
        dIntent.putExtra("Disabled", str_disabled);

        dIntent.putExtra("LMPDate", str_lmp_date);
        dIntent.putExtra("ChildDOB", str_child_dob);

        dIntent.putExtra("str_c_code", cCode);
        dIntent.putExtra("str_district", dName);
        dIntent.putExtra("str_upazilla", upName);
        dIntent.putExtra("str_union", uName);
        dIntent.putExtra("str_village", vName);

        dIntent.putExtra("str_districtCode", dCode);
        dIntent.putExtra("str_upazillaCode", upCode);
        dIntent.putExtra("str_unionCode", uCode);
        dIntent.putExtra("str_villageCode", vCode);

        dIntent.putExtra("str_entry_by", str_entry_by);
        dIntent.putExtra("str_entry_date", str_entry_date);

        activity.startActivity(dIntent);

        notifyDataSetChanged();
    }


    static class ListHolder
    {
        TextView member_ID;
        TextView member_Name;
        ImageView memberEdit;
        //ImageView memberDelete;
       // ImageView memberView;


    }




}
