package com.siddiquinoor.restclient.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.AssignActivity;
import com.siddiquinoor.restclient.activity.GraduationActivity;
import com.siddiquinoor.restclient.data_model.adapters.AssignDataModel;
import com.siddiquinoor.restclient.utils.KEY;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faisal
 * This Adapter Class is used for navigation through all model
 *
 * @since 8/4/2016.
 */
public class MemberSearchAdapter extends BaseAdapter {

    private ViewHolder holder;
    private Activity activity;

    private LayoutInflater inflater;
    private List<AssignDataModel> memberData = new ArrayList<AssignDataModel>();

    private String vCode,vName;

    public MemberSearchAdapter(Activity activity, List<AssignDataModel> memberData, String vCode, String vName) {
        this.activity = activity;
        this.memberData = memberData;
        this.vCode = vCode;
        this.vName = vName;
    }

    public MemberSearchAdapter() {

    }

    @Override
    public int getCount() {
        return memberData.size();
    }

    @Override
    public Object getItem(int position) {
        return memberData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final AssignDataModel memData = memberData.get(position);

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        /**
         * view Reference
         */
        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row_member_search, null);

            holder = new ViewHolder();

            holder.memberId = (TextView) row.findViewById(R.id.memS_row_memId);
            holder.tv_mmName = (TextView) row.findViewById(R.id.memS_row_mem_name);
            holder.tv_LayR4Name = (TextView) row.findViewById(R.id.tv_LayR4Name);
            holder.tv_AddressName = (TextView) row.findViewById(R.id.memSear_tv_AddressName);
            holder.imgGoToAssign = (ImageView) row.findViewById(R.id.ibtn_goTo_assign);
            holder.imgGoToGraduation = (ImageView) row.findViewById(R.id.ibtn_goTo_graduation);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


/**
 * the member are in 15 digit
 */
        holder.memberId.setText(memData.getNewId());  // 15 digit

        holder.tv_mmName.setText(memData.getHh_mm_name());

        holder.tv_LayR4Name.setText(memData.getVillageName());
        holder.tv_AddressName.setText(memData.getAddressName());

/**
 * this button take to the assign Page
 */

        holder.imgGoToAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(activity, AssignActivity.class);
                intent.putExtra(KEY.COUNTRY_ID, memData.getCountryCode());
                intent.putExtra(KEY.MEMBER_ID, memData.getNewId());
                intent.putExtra(KEY.VILLAGE_CODE, vCode);
                intent.putExtra(KEY.VILLAGE_NAME, vName);
                activity.finish();
                activity.startActivity(intent);

            }
        });

        holder.imgGoToGraduation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, GraduationActivity.class);
                intent.putExtra(KEY.COUNTRY_ID, memData.getCountryCode());
                intent.putExtra(KEY.MEMBER_ID, memData.getNewId());
                intent.putExtra(KEY.DIR_CLASS_NAME_KEY, "MemberSearchPage");
                intent.putExtra(KEY.VILLAGE_CODE, vCode);
                intent.putExtra(KEY.VILLAGE_NAME, vName);
                activity.finish();
                activity.startActivity(intent);

            }
        });
        // Change the color of background & text color Dynamically in list view
        if (position % 2 == 0) {
            row.setBackgroundColor(Color.WHITE);
            changeTextColor(activity.getResources().getColor(R.color.blue));
        } else {
            row.setBackgroundColor(activity.getResources().getColor(R.color.list_divider));
            changeTextColor(activity.getResources().getColor(R.color.black));
        }
        return row;
    }

    class ViewHolder {

        TextView memberId;
        TextView tv_mmName;

        TextView tv_LayR4Name;
        TextView tv_AddressName;
        ImageView imgGoToAssign;
        ImageView imgGoToGraduation;


    }

    /**
     * The method change the color of the textView
     *
     * @param color Color of text View
     */
    private void changeTextColor(int color) {
        holder.memberId.setTextColor(color);
        holder.tv_mmName.setTextColor(color);
        holder.tv_LayR4Name.setTextColor(color);
        holder.tv_AddressName.setTextColor(color);
        // holder.tv_age.setTextColor(color);
    }
}
