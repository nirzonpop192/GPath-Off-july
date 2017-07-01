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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.sub_activity.training.IdTypeSelection;
import com.siddiquinoor.restclient.activity.sub_activity.training.TaSummaryPage;
import com.siddiquinoor.restclient.data_model.adapters.TrainingNActivityIndexDataModel;
import com.siddiquinoor.restclient.utils.KEY;

import java.util.ArrayList;

/**
 * Created by Nirzon pop (Faisal Mohammad )
 * on 4/5/2017.
 */

public class TrainingNActivityIndexAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;

    private ArrayList<TrainingNActivityIndexDataModel> data = new ArrayList<>();

    public TrainingNActivityIndexAdapter(Activity activity, ArrayList<TrainingNActivityIndexDataModel> data) {
        this.activity = activity;
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public TrainingNActivityIndexDataModel getDynamicDataIndex(int pos) {
        return (TrainingNActivityIndexDataModel) getItem(pos);
    }

    TrainingNActivityIndexAdapter.ViewHolder holder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        final TrainingNActivityIndexDataModel data = getDynamicDataIndex(position);


        if (inflater == null)                                                                        // convert xml layout  to java object
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row_training_activity_event_index, null);

            holder = new TrainingNActivityIndexAdapter.ViewHolder();

                                                                                                     //view reference
            holder.llRow = (LinearLayout) row.findViewById(R.id.ta_llRow);
            holder.tv_taTitle = (TextView) row.findViewById(R.id.ta_index_row_tv_taTitle);
            holder.tv_startNEndDate = (TextView) row.findViewById(R.id.ta_index_row_tv_StartEndDate);
            holder.tv_venue = (TextView) row.findViewById(R.id.ta_index_row_tv_Venue);
            holder.tv_Address = (TextView) row.findViewById(R.id.ta_index_row_tv_Address);
            holder.iv_Go = (ImageView) row.findViewById(R.id.ta_index_row_ibtn_go);
            holder.iv_view = (ImageView) row.findViewById(R.id.ta_index_row_ibtn_view);

            row.setTag(holder);
        } else {
            holder = (TrainingNActivityIndexAdapter.ViewHolder) row.getTag();
        }

        holder.tv_taTitle.setText(data.getEventTittle());
        holder.tv_startNEndDate.setText(data.getStartDate()+"  to  " + data.getEndDate());

        holder.tv_venue.setText  (""+"Venue     : " + data.getVenueName().trim());
        holder.tv_Address.setText(""+"Address : " + data.getAddressName().trim());


        holder.iv_Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity.getApplicationContext(), IdTypeSelection.class);
                intent.putExtra(KEY.EVENT_INDEX_DATA_OBJECT_KEY, data);
                activity.startActivity(intent);

            }
        });

        holder.iv_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity.getApplicationContext(), TaSummaryPage.class);
                intent.putExtra(KEY.EVENT_INDEX_DATA_OBJECT_KEY, data);
                activity.startActivity(intent);

            }
        });

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


    private void changeTextColor(int color) {
        holder.tv_taTitle.setTextColor(color);
        holder.tv_startNEndDate.setTextColor(color);
        holder.tv_venue.setTextColor(color);
        holder.tv_Address.setTextColor(color);
    }

    private static class ViewHolder {
        LinearLayout llRow;
        TextView tv_taTitle;
        TextView tv_startNEndDate;
        TextView tv_venue;
        TextView tv_Address;
        ImageView iv_Go;
        ImageView iv_view;
    }
}
