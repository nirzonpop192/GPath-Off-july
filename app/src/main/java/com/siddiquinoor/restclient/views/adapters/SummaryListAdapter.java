package com.siddiquinoor.restclient.views.adapters;
/**
 * Activity for presenting the list of all entry in a list view
 * with image and details
 *
 * @author Siddiqui Noor
 * @version 1.3.0
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @since 1.3.0
 * Created on 03-Apr-15
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.siddiquinoor.restclient.R;

import java.util.ArrayList;


public class SummaryListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    ArrayList<SummaryModel> villageData = new ArrayList<SummaryModel>();


    public SummaryListAdapter(Activity activity, ArrayList<SummaryModel> villageData) {
        this.activity = activity;
        this.villageData = villageData;
    }

    @Override
    public int getCount() {
        return villageData.size();
    }

    @Override
    public Object getItem(int location) {
        return villageData.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ListHolder holder = null;

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row_summary, null);

            holder = new ListHolder();
            holder.VillageName = (TextView)row.findViewById(R.id.tv_village_name);
            holder.Records =  (TextView) row.findViewById(R.id.lblRecords);
            row.setTag(holder);
        }
        else
        {
            holder = (ListHolder)row.getTag();
        }

        // getting movie data for the row
        SummaryModel m = villageData.get(position);

        holder.VillageName.setText(m.getVillName());
        holder.Records.setText(String.valueOf(m.getRecords()));

        return row;
    }

    static class ListHolder
    {
        TextView VillageName;
        TextView Records;
    }
}
