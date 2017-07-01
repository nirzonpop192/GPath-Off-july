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

/**
 * Created by USER on 9/4/2015.
 */
public class SummaryServiceListAdapter extends BaseAdapter {

    Activity activity;
    private LayoutInflater inflater;
    private ArrayList<SummaryServiceListModel> srvListData=new ArrayList<SummaryServiceListModel>();
    ViewHolderS holder;

    public SummaryServiceListAdapter(Activity activity, ArrayList<SummaryServiceListModel> srvListData) {
        this.activity = activity;
        this.srvListData = srvListData;
    }

    @Override
    public int getCount() {        return srvListData.size();
    }

    @Override
    public Object getItem(int position) {   return srvListData.get(position);
    }

    @Override
    public long getItemId(int position) {       return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row =convertView;
        //SSCViewHolder holder=null;
        // ViewHolder holder;//=new ViewHolder();

        if (inflater==null)
            inflater= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            row=inflater.inflate(R.layout.list_row_summary_service_attendance_list,null);
            holder=new ViewHolderS();
            holder.tv_hh_id     = (TextView) row.findViewById(R.id.tv_id_srvAId);

            holder.tv_srvCount      = (TextView) row.findViewById(R.id.tv_srvA_SrvSl);
            holder.tv_memberName = (TextView) row.findViewById(R.id.tv_Srv_memberName);
            holder.tv_srvStatus = (TextView) row.findViewById(R.id.tv_SrvAtten_status);




            row.setTag(holder);
        }
        else {
            holder= (ViewHolderS) row.getTag();
        }
        SummaryServiceListModel srvS=new SummaryServiceListModel();
        srvS=srvListData.get(position);

        holder.tv_hh_id.setText(srvS.getCustomId());

        holder.tv_srvCount.setText(srvS.getServiceCount());
        holder.tv_memberName.setText(srvS.getMemberName());
        holder.tv_srvStatus.setText(srvS.getStatus().equals("O")?"Open":"Close");
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

        holder.tv_hh_id.setTextColor(color);
        holder.tv_srvCount.setTextColor(color);
        holder.tv_memberName.setTextColor(color);
        holder.tv_srvStatus.setTextColor(color);
    }

    static class ViewHolderS{
        TextView tv_hh_id;
        TextView tv_memberName;
        TextView tv_srvCount;
        TextView tv_srvStatus;
      //  TextView tv_cost;
    }
}
