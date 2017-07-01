package com.siddiquinoor.restclient.views.adapters;

import android.app.Activity;
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
 * Created by Faisal on 9/5/2016.
 */
public class SummaryGroupListAdapter extends BaseAdapter {
    private List<SummaryGroupListDataModel> arrayList = new ArrayList<SummaryGroupListDataModel>();
    private Activity mActivity;
    private LayoutInflater inflater;

    public SummaryGroupListAdapter(Activity mActivity, List<SummaryGroupListDataModel> arrayList) {
        this.mActivity = mActivity;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public SummaryGroupListDataModel getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHolder holder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (inflater == null) {
            inflater = (LayoutInflater) mActivity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row_summary_grp_list, null);
            holder = new ViewHolder();
            holder.tv_LayR3Name = (TextView) row.findViewById(R.id.list_row_summ_grp_tv_LayR3_Name);
            holder.tv_groupName = (TextView) row.findViewById(R.id.list_row_summ_grp_tv_Grp_Name);
            holder.tv_groupCatName = (TextView) row.findViewById(R.id.list_row_summ_grp_tv_Grp_CatName);

            holder.tv_count = (TextView) row.findViewById(R.id.list_row_summ_grp_tv_count);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        SummaryGroupListDataModel data = getItem(position);

        holder.tv_LayR3Name.setText(getItem(position).getLayR3Name());
        holder.tv_groupName.setText(getItem(position).getGroupName());
        holder.tv_groupCatName.setText(getItem(position).getGroupCatShortName());
        holder.tv_count.setText(getItem(position).getCount());

        if (position % 2 == 0) {
            row.setBackgroundColor(Color.WHITE);
            changeTextColor(mActivity.getResources().getColor(R.color.blue));
        } else {
            row.setBackgroundColor(mActivity.getResources().getColor(R.color.list_divider));
            changeTextColor(mActivity.getResources().getColor(R.color.black));
        }

        return row;
    }

    class ViewHolder {
        TextView tv_LayR3Name;
        TextView tv_groupName;
        TextView tv_groupCatName;
        TextView tv_count;
    }

    private void changeTextColor(int color) {
        holder.tv_LayR3Name.setTextColor(color);
        holder.tv_groupName.setTextColor(color);
        holder.tv_groupCatName.setTextColor(color);
        holder.tv_count.setTextColor(color);
    }
}
