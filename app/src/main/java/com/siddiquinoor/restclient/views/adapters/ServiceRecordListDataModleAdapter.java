package com.siddiquinoor.restclient.views.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;

import java.util.ArrayList;

/**
 * Created by Faisal on 9/14/2015.
 */
public class ServiceRecordListDataModleAdapter extends BaseAdapter {

    private Activity activity;

    private LayoutInflater inflater;
    ArrayList<ServiceSlDataModle> srvSlData = new ArrayList<ServiceSlDataModle>();
    private SQLiteHandler sqlH;
    private ServiceDataModel servPerson;

    private final String TAG = ServiceRecordListDataModleAdapter.class.getName();

    public ServiceRecordListDataModleAdapter(Activity activity, ArrayList<ServiceSlDataModle> srvSlData, ServiceDataModel servPerson) {
        this.activity = activity;
        this.srvSlData = srvSlData;
        sqlH = new SQLiteHandler(activity);
        this.servPerson = servPerson;
    }

    @Override
    public int getCount() {
        return srvSlData.size();
    }

    @Override
    public Object getItem(int position) {
        return srvSlData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ServSlViewHolder holder;
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row_srv_sl_dt, null);
            holder = new ServSlViewHolder();
            holder.tv_srvSl = (TextView) row.findViewById(R.id.tv_srv_sl_no);
            holder.tv_srvDate = (TextView) row.findViewById(R.id.tv_srv_date);
            holder.tv_srvStatus = (TextView) row.findViewById(R.id.tv_srv_status);
            holder.im_Delete = (ImageButton) row.findViewById(R.id.delete_srv_member_sl);
            row.setTag(holder);

        } else {
            holder = (ServSlViewHolder) row.getTag();
        }



        final ServiceSlDataModle slDetails = srvSlData.get(position);
        holder.tv_srvDate.setText(slDetails.getServiceDate());

        holder.tv_srvSl.setText(slDetails.getSrvSerial());
        /** @date : 2015-10-12
         * @ description: Status 'O' need to show as 'Open' and Status 'C' need to show as 'Close'*/
        String mStatus = slDetails.getServiceStatus();
        String strStatus = "";
        if (mStatus.equals("O") || mStatus.equals("o") || mStatus.equals("0")) {
            strStatus = "Open";
        } else {
            strStatus = "Close";
        }

        holder.tv_srvStatus.setText(strStatus);

        holder.im_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String status = slDetails.getServiceStatus();
                if (status.equals("O") || status.equals("o") || status.equals("0")) {

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
                    alertDialog.setTitle("Confirm ");
                    alertDialog.setMessage("Delete ?");
                    alertDialog.setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int selectedID) {

                                    SQLServerSyntaxGenerator srvTable = new SQLServerSyntaxGenerator();
                                    srvTable.setAdmCountryCode(servPerson.getC_code());
                                    srvTable.setAdmDonorCode(servPerson.getDonor_code());
                                    srvTable.setAdmAwardCode(servPerson.getAward_code());
                                    srvTable.setLayR1ListCode(servPerson.getDistrictCode());
                                    srvTable.setLayR2ListCode(servPerson.getUpazillaCode());
                                    srvTable.setLayR3ListCode(servPerson.getUnitCode());
                                    srvTable.setLayR4ListCode(servPerson.getVillageCode());
                                    srvTable.setHHID(servPerson.getHHID());
                                    srvTable.setMemID(servPerson.getMemberId());
                                    srvTable.setProgCode(servPerson.getProgram_code());
                                    srvTable.setSrvCode(servPerson.getService_code());
                                    srvTable.setOpCode("2");
                                    srvTable.setOpMonthCode(servPerson.getOpMontheCode());
//
                                    srvTable.setSrvSL(slDetails.getSrvSerial());
                                    Log.d(TAG,"Service in delete  Serial no :"+slDetails.getSrvSerial());

                                    int id = sqlH.deleteService(servPerson.getC_code(), servPerson.getDonor_code(),
                                            servPerson.getAward_code(), servPerson.getDistrictCode(),
                                            servPerson.getUpazillaCode(), servPerson.getUnitCode(),
                                            servPerson.getVillageCode(), servPerson.getHHID(),
                                            servPerson.getMemberId(), servPerson.getProgram_code(),
                                            servPerson.getService_code(), servPerson.getOpCode(),
                                            servPerson.getOpMontheCode(), slDetails.getSrvSerial()
                                    );
                                    sqlH.insertIntoUploadTable(srvTable.deleteMemberFromSrvTable());
                                    sqlH.insertIntoUploadTable(srvTable.deleteFromSrvSpecific());
                                    srvSlData.remove(position);

                                    Toast.makeText(activity, "One Member deleted successfully!", Toast.LENGTH_LONG).show();
                                    notifyDataSetChanged();

                                }
                            });

                    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();


                } else if (status.equals("C") || status.equals("c")) {
                    Toast.makeText(activity, "you cann't delete this data ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return row;
    }

    class ServSlViewHolder {
        TextView tv_srvSl;
        TextView tv_srvDate;
        TextView tv_srvStatus;
        ImageButton im_Delete;
    }
}
