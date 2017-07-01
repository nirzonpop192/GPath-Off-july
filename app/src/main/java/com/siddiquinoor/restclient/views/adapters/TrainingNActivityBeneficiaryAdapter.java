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

import com.siddiquinoor.restclient.R;

import com.siddiquinoor.restclient.data_model.adapters.TrainigActivBeneficiaryDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pop
 * on 4/8/2017.
 */

public class TrainingNActivityBeneficiaryAdapter extends BaseAdapter {
    private final String TAG = TrainingNActivityBeneficiaryAdapter.class.getName();

    private SparseBooleanArray mChecked = new SparseBooleanArray();

    private Activity activity;
    private List<TrainigActivBeneficiaryDataModel> mSelectedEligibleBeneList = new ArrayList<>();

    public List<TrainigActivBeneficiaryDataModel> getSelectedEligibleBeneficiaryList(){
        return mSelectedEligibleBeneList;
    }
    private LayoutInflater inflater;
    private List<TrainigActivBeneficiaryDataModel> dataList = new ArrayList<>();



    ViewHolder holder = null;


    public TrainingNActivityBeneficiaryAdapter(Activity activity, List<TrainigActivBeneficiaryDataModel> assignData
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        View row = convertView;
        final TrainigActivBeneficiaryDataModel memData = dataList.get(position);

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row_training_activity_beneficaiary, null);

            holder = new ViewHolder();

            holder.memberId = (TextView) row.findViewById(R.id.ta_row_beneficiary_tv_memId);
            holder.tv_mmName = (TextView) row.findViewById(R.id.ta_row_beneficiary_tv_memName);
            holder.tv_sex = (TextView) row.findViewById(R.id.ta_row_beneficiary_tv_sex);
            holder.tv_layR4Name = (TextView) row.findViewById(R.id.ta_row_beneficiary_tv_layR4name);


            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        holder.memberId.setText(" Id :" + memData.getNewId());  // 15 digit

        holder.tv_mmName.setText(" Name :" + memData.getHh_mm_name());
        holder.tv_sex.setText(" Sex :" + memData.getMember_sex());
        holder.tv_layR4Name.setText(" Village  :" + memData.getLayR4Name());
        CheckBox cbId_holder = (CheckBox) row.findViewById(R.id.ta_row_beneficiary_cBox);

        cbId_holder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                /**
                 *  Saving Checked Position
                 */
                mChecked.put(position, isChecked);

                getEligibleBeneficiary((Integer) buttonView.getTag()).setCheckedBoxChecked(isChecked);

                /** old state*/
                addDataToArrayList(getEligibleBeneficiary((Integer) buttonView.getTag()),
                        getEligibleBeneficiary((Integer) buttonView.getTag()).isCheckedBoxChecked());

            }
        });

        // set the sate of particular positioned check box
        cbId_holder.setTag(position);
        // than set the checked sate

        /**
         * Set CheckBox "TRUE" or "FALSE" if mChecked == true
         */
        cbId_holder.setChecked((mChecked.get(position)));

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

    private TrainigActivBeneficiaryDataModel getEligibleBeneficiary(Integer pos) {
        return (TrainigActivBeneficiaryDataModel) getItem(pos);
    }

    private void addDataToArrayList(TrainigActivBeneficiaryDataModel data, boolean checkBoxStatus) {
        if (checkBoxStatus) {
            mSelectedEligibleBeneList.add(data);
        } else {
            if (mSelectedEligibleBeneList.contains(data)) {// first check the data is exits ing or not
                mSelectedEligibleBeneList.remove(data);
            }

        }

    }


    private static class ViewHolder {

        TextView memberId;
        TextView tv_mmName;
        TextView tv_sex;
        TextView tv_layR4Name;

    }


    /**
     * The method change the color of the textView
     *
     * @param color Color of text View
     */
    private void changeTextColor(int color) {
        holder.memberId.setTextColor(color);
        holder.tv_mmName.setTextColor(color);
        holder.tv_sex.setTextColor(color);
        holder.tv_layR4Name.setTextColor(color);
    }
}
