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
 * Created by USER on 5/29/2016.
 */
public class DistributionSummaryCriteriaListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<SummaryCriteriaModel> criteriaData = new ArrayList<SummaryCriteriaModel>();
    ViewHolder holder;
    /*private String countryId;
    private String donorId;
    private String awardId;*/

    public DistributionSummaryCriteriaListAdapter(Activity activity, ArrayList<SummaryCriteriaModel> criteriaData
            /*, String countryId, String donorId, String awardId*/) {
        this.activity = activity;
        this.criteriaData = criteriaData;

    }

    @Override
    public int getCount() {
        return criteriaData.size();
    }

    @Override
    public Object getItem(int position) {
        return criteriaData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final SummaryCriteriaModel cri = (SummaryCriteriaModel) getItem(position);

        View row = convertView;

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row_distribution_summary_paln_criteria, null);
            holder = new ViewHolder();

            holder.tv_criteria = (TextView) row.findViewById(R.id.tv_row_dist_s_criteriaName);

            holder.tv_plan = (TextView) row.findViewById(R.id.tv_row_dist_s_plan_total);
            holder.tv_Balance = (TextView) row.findViewById(R.id.tv_row_dist_s_balance);

            holder.tv_Receive = (TextView) row.findViewById(R.id.tv_row_dist_s_recieve_total);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        int balance = cri.getPlan() - cri.getRecord();
        holder.tv_criteria.setText(cri.getCriteria_name());

        holder.tv_Receive.setText(String.valueOf(cri.getRecord()));
        holder.tv_plan.setText(String.valueOf(cri.getPlan()));
        holder.tv_Balance.setText(String.valueOf(balance));


        /** set row color*/

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
        holder.tv_criteria.setTextColor(color);
        holder.tv_Receive.setTextColor(color);
    }

    static class ViewHolder {
        TextView tv_criteria;
        TextView tv_plan;
        TextView tv_Balance;
        TextView tv_Receive;

    }
}
