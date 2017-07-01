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
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.sub_activity.commu_group_sub.CommunityGroupNDetailsRecodes;
import com.siddiquinoor.restclient.data_model.adapters.CommunityGroupDataModel;
import com.siddiquinoor.restclient.utils.KEY;


import java.util.ArrayList;

/**
 * Created by USER on 8/16/2016.
 */
public class CommunityGroupAdapter extends BaseAdapter {

    ArrayList<CommunityGroupDataModel> grpData = new ArrayList<CommunityGroupDataModel>();


    public CommunityGroupAdapter(Activity activity, ArrayList<CommunityGroupDataModel> grpData) {
        this.activity = activity;
        this.grpData = grpData;
    }

    private ViewHolder holder;
    private Activity activity;

    private LayoutInflater inflater;

    @Override
    public int getCount() {
        return grpData.size();
    }

    @Override
    public Object getItem(int position) {
        return grpData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final CommunityGroupDataModel data = grpData.get(position);

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row_group_search, null);

            holder = new ViewHolder();

            holder.tv_GroupName = (TextView) row.findViewById(R.id.groupSear_row_groupName);
            holder.tv_CatShortName = (TextView) row.findViewById(R.id.groupSear_tv_CateName);
            holder.tv_ProgShortName = (TextView) row.findViewById(R.id.groupSear_row_progShort);
            holder.imgEdit = (ImageView) row.findViewById(R.id.ibtn_group_sear_edit);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        holder.tv_GroupName.setText(data.getCommunityGroupName());  // 15 digit

        holder.tv_CatShortName.setText(data.getCommuCategoriesShortName());

        holder.tv_ProgShortName.setText(data.getProgramShortName());



        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(activity, CommunityGroupNDetailsRecodes.class);


                intent.putExtra(KEY.COMMUNITY_GRP_DATA_OBJECT_KEY, data);
                activity.finish();

                activity.startActivity(intent);


            }
        });
        // Change the color of background & text color Dynamically in list view
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

        TextView tv_GroupName;
        TextView tv_CatShortName;

        TextView tv_ProgShortName;

        ImageView imgEdit;


    }

    /**
     * The method change the color of the textView
     *
     * @param color Color of text View
     */
    private void changeTextColor(int color) {
        holder.tv_GroupName.setTextColor(color);
        holder.tv_CatShortName.setTextColor(color);
        holder.tv_ProgShortName.setTextColor(color);

    }
}
