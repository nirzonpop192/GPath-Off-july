package com.siddiquinoor.restclient.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.data_model.adapters.TaSummary;
import com.siddiquinoor.restclient.data_model.adapters.TrainigActivBeneficiaryDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pop on 4/12/2017.
 */

public class TA_SummaryAdapter  extends BaseAdapter{
    private final String TAG = TrainingNActivityBeneficiaryAdapter.class.getName();



    private Activity activity;



    private LayoutInflater inflater;
    private List<TaSummary> dataList = new ArrayList<>();



    ViewHolder holder = null;


    public TA_SummaryAdapter(Activity activity, List<TaSummary> assignData
    ) {
        this.activity = activity;
        this.dataList = assignData;

    }


    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final TaSummary data = dataList.get(position);

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row_ta_summary, null);

            holder = new ViewHolder();

            holder.tvTitle = (TextView) row.findViewById(R.id.ta_tv_summary_Title);
            holder.tv_Count = (TextView) row.findViewById(R.id.ta_tv_summary_Count);


            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Log.d("MOR","data.getTitle() : "+data.getTitle());
        holder.tvTitle.setText(data.getTitle());  // 15 digit

        holder.tv_Count.setText(data.getCount());



        // set the sate of particular positioned check box


        /**
         *  Change the color of background & text color Dynamically in list view
         */
        if (position % 2 == 0) {
            row.setBackgroundColor(Color.WHITE);
            changeTextColor(activity.getResources().getColor(R.color.blue));
        } else {
            row.setBackgroundColor(activity.getResources().getColor(R.color.list_divider));
            changeTextColor(activity.getResources().getColor(R.color.black));
        }
        return row;
    }

    private static class ViewHolder {

        TextView tvTitle;
        TextView tv_Count;


    }

    /**
     * The method change the color of the textView
     *
     * @param color Color of text View
     */
    private void changeTextColor(int color) {
        holder.tvTitle.setTextColor(color);
        holder.tv_Count.setTextColor(color);

    }
}
