package com.siddiquinoor.restclient.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.sub_activity.graduation_sub.GraduationUpdate;
import com.siddiquinoor.restclient.data_model.adapters.GraduationGridDataModel;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;

import java.util.ArrayList;

/**
 * Created by Faisal  on 9/30/2015.
 */
public class GraduationGridAdapter extends BaseAdapter {//implements View.OnClickListener {
    private Activity activity;

    private LayoutInflater inflater;
   private ArrayList<GraduationGridDataModel> graduationData = new ArrayList<GraduationGridDataModel>();
    private SQLiteHandler sqlH = null;
    private final String TAG = AssignDataModelAdapter.class.getName();
    private String awardName;
    private String proName;
    private String criteriaName;

    private String awardCode;
    private String programCode;
    private String donorCode;
    private String serviceCode;
    ViewHolder holder;


    public GraduationGridAdapter(ArrayList<GraduationGridDataModel> graduationData, Activity activity,
                                 String awardName, String proName, String criteriaName, String awardCode,
                                 String programCode, String donorCode, String serviceCode
    ) {
        this.graduationData = graduationData;
        this.activity = activity;
        this.awardName = awardName;
        this.proName = proName;
        this.criteriaName = criteriaName;

        this.awardCode = awardCode;
        this.programCode = programCode;
        this.donorCode = donorCode;
        this.serviceCode = serviceCode;
    }

    @Override
    public int getCount() {
        return graduationData.size();
    }

    @Override
    public Object getItem(int position) {
        return graduationData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View row = convertView;


        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row_graduation, null);

            holder = new ViewHolder();


            holder.tv_memberId = (TextView) row.findViewById(R.id.tv_raw_gra_idMem);
            holder.tv_memberName = (TextView) row.findViewById(R.id.tv_raw_gra_memberName);
            holder.tv_reason = (TextView) row.findViewById(R.id.tv_raw_gra_Reason);
            holder.tv_grdDate = (TextView) row.findViewById(R.id.tv_raw_gra_grdDate);
            holder.imgEdit = (ImageButton) row.findViewById(R.id.edit_graduation_holder);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        GraduationGridDataModel mGraduation = graduationData.get(position);


        holder.tv_memberId.setText(mGraduation.getnMemId());
        holder.tv_memberName.setText(mGraduation.getMember_name());
        holder.tv_reason.setText(mGraduation.getGraduationTitle());
        holder.tv_grdDate.setText(mGraduation.getGraduationDate());


        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GraduationGridDataModel mgrad = graduationData.get(position);
                mgrad.setAward_code(awardCode);
                mgrad.setProgram_code(programCode);
                mgrad.setService_code(serviceCode);
                mgrad.setDonor_code(donorCode);
                mgrad.setAward_name(awardName);
                mgrad.setProgram_name(proName);
                mgrad.setCriteria_name(criteriaName);


                final GraduationGridDataModel grad = mgrad;
                Intent igradUpdate = new Intent(activity, GraduationUpdate.class);
                igradUpdate.putExtra(KEY.GRADUATION_DETAILS_DATA_OBJECT_KEY, grad);
                activity.finish();
                activity.startActivity(igradUpdate);
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

    private class ViewHolder {

        TextView tv_memberId;
        TextView tv_memberName;
        TextView tv_reason;
        TextView tv_grdDate;
        ImageButton imgEdit;

    }

    /**
     * The method change the color of the textView
     *
     * @param color Color of text View
     */
    private void changeTextColor(int color) {
        holder.tv_memberId.setTextColor(color);
        holder.tv_memberName.setTextColor(color);
        holder.tv_reason.setTextColor(color);
        holder.tv_grdDate.setTextColor(color);


    }


}
