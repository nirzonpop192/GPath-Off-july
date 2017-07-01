package com.siddiquinoor.restclient.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is Adapter Class
 * Created by Faisal
 * on 5/28/2016.
 */
public class SummaryDistributionListAttendanceAdapter extends BaseAdapter {
    Activity activity;
    private LayoutInflater inflater;
    private List<SummaryServiceListModel> srvListData = new ArrayList<SummaryServiceListModel>();
    ViewHolderS holder;

    public SummaryDistributionListAttendanceAdapter(Activity activity, List<SummaryServiceListModel> srvListData) {
        this.activity = activity;
        this.srvListData = srvListData;
    }

    @Override
    public int getCount() {
        return srvListData.size();
    }

    @Override
    public Object getItem(int position) {
        return srvListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;


        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row_summary_dist_attendance, null);
            holder = new ViewHolderS();
            holder.tv_member_id = (TextView) row.findViewById(R.id.tv_id_distAId);

            holder.tv_distStatus = (TextView) row.findViewById(R.id.tv_Status_distASummary);
            holder.tv_memberName = (TextView) row.findViewById(R.id.tv_distA_memberName);


            row.setTag(holder);
        } else {
            holder = (ViewHolderS) row.getTag();
        }
        SummaryServiceListModel srvS = new SummaryServiceListModel();
        srvS = srvListData.get(position);

        holder.tv_member_id.setText(srvS.getCustomId());

        holder.tv_distStatus.setText(srvS.getServiceCount());
        holder.tv_memberName.setText(srvS.getMemberName());
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

        holder.tv_member_id.setTextColor(color);
        holder.tv_distStatus.setTextColor(color);
        holder.tv_memberName.setTextColor(color);
    }

    static class ViewHolderS {
        TextView tv_member_id;
        TextView tv_memberName;
        TextView tv_distStatus;
        //  TextView tv_cost;
    }

}
