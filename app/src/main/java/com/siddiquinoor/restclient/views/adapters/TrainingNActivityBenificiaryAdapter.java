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
import com.siddiquinoor.restclient.data_model.adapters.AssignDataModel;

import java.util.ArrayList;

/**
 * Created by pop on 4/8/2017.
 */

public class TrainingNActivityBenificiaryAdapter extends BaseAdapter {


    private Activity activity;

    private LayoutInflater inflater;
    ArrayList<AssignDataModel> assignData = new ArrayList<AssignDataModel>();

    private final String TAG = com.siddiquinoor.restclient.views.adapters.AssignDataModelAdapter.class.getName();

    ViewHolder holder = null;


    public TrainingNActivityBenificiaryAdapter(Activity activity, ArrayList<AssignDataModel> assignData
    ) {
        this.activity = activity;
        this.assignData = assignData;

    }


    @Override
    public int getCount() {
        return assignData.size();
    }

    @Override
    public Object getItem(int position) {
        return assignData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View row = convertView;
        final AssignDataModel memData = assignData.get(position);

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row_assign, null);

            holder = new ViewHolder();

            holder.memberId = (TextView) row.findViewById(R.id.memS_row_memId);

            holder.tv_mmName = (TextView) row.findViewById(R.id.as_row_mm_name);
            holder.tv_assign = (TextView) row.findViewById(R.id.as_row_tv_assignView);
            //     holder.imgEdit = (ImageView) row.findViewById(R.id.mem_ibtn_toAssigne);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        holder.memberId.setText(memData.getNewId());  // 15 digit

        holder.tv_mmName.setText(memData.getHh_mm_name());
        holder.tv_assign.setText(memData.getAssignYN());


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


    class ViewHolder {

        TextView memberId;
        TextView tv_mmName;
        TextView tv_assign;
        //  ImageView imgEdit;


    }


    /**
     * The method change the color of the textView
     *
     * @param color Color of text View
     */
    private void changeTextColor(int color) {
        holder.memberId.setTextColor(color);
        holder.tv_mmName.setTextColor(color);
        holder.tv_assign.setTextColor(color);
    }
}
