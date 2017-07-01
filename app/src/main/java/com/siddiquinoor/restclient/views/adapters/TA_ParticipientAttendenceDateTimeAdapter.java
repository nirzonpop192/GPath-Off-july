package com.siddiquinoor.restclient.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.data_model.adapters.TA_ParticipientAttendenceDateTimeDataModel;
import com.siddiquinoor.restclient.data_model.adapters.TrainigActivBeneficiaryDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pop
 * on 4/9/2017.
 */

public class TA_ParticipientAttendenceDateTimeAdapter extends BaseAdapter {


    private List<TA_ParticipientAttendenceDateTimeDataModel> mSelectedDateTime = new ArrayList<>();

    public List<TA_ParticipientAttendenceDateTimeDataModel> getmSelectedDateTime(){
        return mSelectedDateTime;
    }
    public TA_ParticipientAttendenceDateTimeAdapter(Activity activity, List<TA_ParticipientAttendenceDateTimeDataModel> dataList) {
        this.activity = activity;
        this.dataList = dataList;
    }

    private SparseBooleanArray mChecked = new SparseBooleanArray();
    private SparseBooleanArray mAMCheckedSection = new SparseBooleanArray();
    private SparseBooleanArray mPMCheckedSection = new SparseBooleanArray();
    private List<TA_ParticipientAttendenceDateTimeDataModel> dataList = new ArrayList<>();

    private LayoutInflater inflater;
    ViewHolder holder = null;
    private Activity activity;

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
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final TA_ParticipientAttendenceDateTimeDataModel data = dataList.get(position);

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row_ta_participent_atten_date_time, null);

            holder = new ViewHolder();

            holder.tv_Date = (TextView) row.findViewById(R.id.row_ta_patici_atten_Date);


            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        holder.tv_Date.setText(data.getDate());
        mChecked.put(position, data.isChecked());
        mAMCheckedSection.put(position, data.isAmSession());
        mPMCheckedSection.put(position, data.isPmSession());

        CheckBox cbId_holder = (CheckBox) row.findViewById(R.id.row_ta_patici_atten_cBox);
        final CheckBox cbPM = (CheckBox) row.findViewById(R.id.row_ta_patici_atten_cb_pm);
        final CheckBox cbAM = (CheckBox) row.findViewById(R.id.row_ta_patici_atten_cb_am);
        cbId_holder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                mChecked.put(position, isChecked);                                                   // Saving into custom boolean array list in which can saved the position wise checked status    Checked Position

                getAttendanceDateTime((Integer) buttonView.getTag()).setChecked(isChecked);

                if (mChecked.get(position) && mAMCheckedSection.get(position)) {
                    getAttendanceDateTime((Integer) buttonView.getTag()).setAmSession(true);
                } else if (mChecked.get(position) && mPMCheckedSection.get(position)) {
                    getAttendanceDateTime((Integer) buttonView.getTag()).setPmSession(true);
                } else if (mChecked.get(position) && !mAMCheckedSection.get(position) && !mPMCheckedSection.get(position)) {
                    getAttendanceDateTime((Integer) buttonView.getTag()).setAmSession(false);
                    getAttendanceDateTime((Integer) buttonView.getTag()).setPmSession(false);

                } else if (mChecked.get(position) && !mAMCheckedSection.get(position) && mPMCheckedSection.get(position)) {
                    getAttendanceDateTime((Integer) buttonView.getTag()).setPmSession(true);
                } else if (mChecked.get(position) && mAMCheckedSection.get(position) && !mPMCheckedSection.get(position)) {
                    getAttendanceDateTime((Integer) buttonView.getTag()).setAmSession(true);
                }else if(mChecked.get(position) && !mAMCheckedSection.get(position) && !mPMCheckedSection.get(position)){
                    getAttendanceDateTime((Integer) buttonView.getTag()).setAmSession(true);
                    getAttendanceDateTime((Integer) buttonView.getTag()).setPmSession(true);
                }

                /** old state*/
                addDataToArrayList(getAttendanceDateTime((Integer) buttonView.getTag()),
                        getAttendanceDateTime((Integer) buttonView.getTag()).isChecked());


            }
        });

        // set the sate of particular positioned check box
        cbId_holder.setTag(position);
        // than set the checked sate

        /**
         * Set CheckBox "TRUE" or "FALSE" if mChecked == true
         */
        cbId_holder.setChecked((mChecked.get(position)));

        cbAM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mAMCheckedSection.put(position, isChecked);                                                   // Saving into custom boolean array list in which can saved the position wise checked status    Checked Position


                if (isChecked) {
                    //cbPM.setChecked(false);
                    getAttendanceDateTime(position).setAmSession(true);
                    //getAttendanceDateTime(position).setPmSession(false);

                } else {
                    getAttendanceDateTime(position).setAmSession(false);
                  //  getAttendanceDateTime(position).setPmSession(false);
                }

            }
        });

        // set the sate of particular positioned check box
        cbAM.setTag(position);
        // than set the checked sate

        /**
         * Set CheckBox "TRUE" or "FALSE" if mChecked == true
         */
        cbAM.setChecked((mAMCheckedSection.get(position)));

        cbPM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mPMCheckedSection.put(position, isChecked);                                                   // Saving into custom boolean array list in which can saved the position wise checked status    Checked Position


                if (isChecked) {
                 //   cbAM.setChecked(false);
                  //  getAttendanceDateTime(position).setAmSession(false);
                    getAttendanceDateTime(position).setPmSession(true);
                } else {
                  //  getAttendanceDateTime(position).setAmSession(false);
                    getAttendanceDateTime(position).setPmSession(false);
                }


            }
        });
        // set the sate of particular positioned check box
        cbPM.setTag(position);
        // than set the checked sate

        /**
         * Set CheckBox "TRUE" or "FALSE" if mChecked == true
         */
        cbPM.setChecked((mPMCheckedSection.get(position)));

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

    public TA_ParticipientAttendenceDateTimeDataModel getAttendanceDateTime(int pos) {
        return (TA_ParticipientAttendenceDateTimeDataModel) getItem(pos);
    }

    private static class ViewHolder {
        TextView tv_Date;
    }

    /**
     * The method change the color of the textView
     *
     * @param color Color of text View
     */
    private void changeTextColor(int color) {
        holder.tv_Date.setTextColor(color);

    }

    private void addDataToArrayList(TA_ParticipientAttendenceDateTimeDataModel data, boolean checkBoxStatus) {
        if (checkBoxStatus) {
            mSelectedDateTime.add(data);
        } else {
            if (mSelectedDateTime.contains(data)) {// first check the data is exits ing or not
                mSelectedDateTime.remove(data);
            }

        }

    }

}
