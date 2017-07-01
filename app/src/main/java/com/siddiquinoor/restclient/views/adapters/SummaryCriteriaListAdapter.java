package com.siddiquinoor.restclient.views.adapters;

import android.app.Activity;
import android.content.Context;
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
 * Created by USER on 9/2/2015.
 */
public class SummaryCriteriaListAdapter extends BaseAdapter {


    private Activity activity;
    private LayoutInflater inflater;
    ArrayList<SummaryCriteriaModel> criteriaData = new ArrayList<SummaryCriteriaModel>();
    ViewHolder holder;
    private String countryId;
    private String donorId;
    private String awardId;

    public SummaryCriteriaListAdapter(Activity activity, ArrayList<SummaryCriteriaModel> criteriaData
            , String countryId, String donorId, String awardId) {
        this.activity = activity;
        this.criteriaData = criteriaData;
        this.countryId = countryId;
        this.donorId = donorId;
        this.awardId = awardId;
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
            row = inflater.inflate(R.layout.list_row_summary_service_c, null);
            holder = new ViewHolder();

            holder.tv_criteria = (TextView) row.findViewById(R.id.tv_criteriaName);

            holder.tv_Record = (TextView) row.findViewById(R.id.tv_criteria_total);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        holder.tv_criteria.setText(cri.getCriteria_name());

        holder.tv_Record.setText(String.valueOf(cri.getRecord()));

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
        holder.tv_Record.setTextColor(color);
    }

    static class ViewHolder {
        TextView tv_criteria;
        TextView tv_Record;

    }
}
