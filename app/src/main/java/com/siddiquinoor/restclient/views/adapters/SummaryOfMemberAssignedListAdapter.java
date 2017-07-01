package com.siddiquinoor.restclient.views.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;

import java.util.ArrayList;

/**
 * Created by Faisal on 10/16/2015.
 */
public class SummaryOfMemberAssignedListAdapter extends BaseAdapter{
    private ArrayList<SummaryOfMemberAssignedListModel> arrayList=new ArrayList<SummaryOfMemberAssignedListModel>();
    private Activity mActivity;
    private LayoutInflater inflater;
    private  ViewHolder holder;

    public SummaryOfMemberAssignedListAdapter(Activity mactivity, ArrayList<SummaryOfMemberAssignedListModel> arrayList) {
        this.mActivity = mactivity;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public SummaryOfMemberAssignedListModel getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;

        if (inflater==null){
            inflater= (LayoutInflater) mActivity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView==null){
            row=inflater.inflate(R.layout.list_row_summary_assigne_list,null);
            holder=new ViewHolder();
            holder.tv_id= (TextView) row.findViewById(R.id.tv_row_assign_id);
            holder.tv_name= (TextView) row.findViewById(R.id.tv_row_assign_name);
            holder.tv_regDate= (TextView) row.findViewById(R.id.tv_row_assign_regDate);
            holder.tv_ass_group_name = (TextView) row.findViewById(R.id.mems_row_group_name);

           row.setTag(holder);
        }
        else {
            holder= (ViewHolder) row.getTag();
        }
        SummaryOfMemberAssignedListModel assignData=getItem(position);

        holder.tv_id.setText(assignData.getCustomId());
        holder.tv_name.setText(assignData.getMemberName());
        holder.tv_regDate.setText(assignData.getRegDate());
     //   Log.d("NIR_SU", assignData.getGroupName());
        holder.tv_ass_group_name.setText(assignData.getGroupName());

        /**
         *  Change the color of background & text color Dynamically in list view
         */
        if (position % 2 == 0) {
            row.setBackgroundColor(Color.WHITE);
            changeTextColor(mActivity.getResources().getColor(R.color.blue));
        } else {
            row.setBackgroundColor(mActivity.getResources().getColor(R.color.list_divider));
            changeTextColor(mActivity.getResources().getColor(R.color.black));
        }

        return row;
    }
    static class ViewHolder{
        TextView tv_id;
        TextView tv_name;
        TextView tv_regDate;
        TextView tv_ass_group_name;

    }
    /**
     * The method change the color of the textView
     *
     * @param color Color of text View
     */
    private void changeTextColor(int color) {
        holder.tv_id.setTextColor(color);
        holder.tv_name.setTextColor(color);
        holder.tv_regDate.setTextColor(color);
        holder.tv_ass_group_name.setTextColor(color);
    }
}
