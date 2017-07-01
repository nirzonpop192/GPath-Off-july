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


/**
 * @author Faisal
 * @since 9/6/2016.
 * this class generate list row in the in @see #IdListInGroupSummar  Class
 */
public class SummaryGroupMembersAdapter extends BaseAdapter {

    private ArrayList<SummaryIdListInGroupDataModel> arrayList = new ArrayList<SummaryIdListInGroupDataModel>();
    private Activity mActivity;
    private LayoutInflater inflater;

    /**
     *
     * @param mActivity invoking activity
     * @param arrayList idListed array
     */

    public SummaryGroupMembersAdapter(Activity mActivity, ArrayList<SummaryIdListInGroupDataModel> arrayList) {
        this.mActivity = mActivity;
        this.arrayList = arrayList;
    }

    /**
     *
     * @return size of the array
     */

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public SummaryIdListInGroupDataModel getItem(int position) {
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
            row = inflater.inflate(R.layout.list_row_summary_id_listed_in_group, null);
            holder = new ViewHolder();
            holder.tv_grpName = (TextView) row.findViewById(R.id.list_row_summ_mem_id_in_grp_tv_grpName);
            holder.tv_memId = (TextView) row.findViewById(R.id.list_row_summ_mem_id_in_grp_tv_memId);
            holder.tv_memName = (TextView) row.findViewById(R.id.list_row_summ_mem_id_in_grp_tv_memName);
            holder.srvName = (TextView) row.findViewById(R.id.list_row_summ_mem_id_in_grp_tv_srvName);


            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        SummaryIdListInGroupDataModel data = getItem(position);

        holder.tv_grpName.setText(data.getGrpName());
        holder.tv_memId.setText(data.getnMemId());
        holder.tv_memName.setText(data.getMemName());
        holder.srvName.setText(data.getSrvName());

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
        TextView tv_grpName;
        TextView tv_memId;
        TextView tv_memName;
        TextView srvName;
    }

    private void changeTextColor(int color) {
        holder.tv_grpName.setTextColor(color);
        holder.tv_memId.setTextColor(color);
        holder.tv_memName.setTextColor(color);
        holder.srvName.setTextColor(color);
    }
}
