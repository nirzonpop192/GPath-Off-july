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
 * @author Faisal
 * @since 8/29/2015.
 */
public class AssignDataModelAdapter extends BaseAdapter {
    private String villageStr;
    private String criteriaStr;
    private Activity activity;

    private LayoutInflater inflater;
    ArrayList<AssignDataModel> assignData = new ArrayList<AssignDataModel>();

    private final String TAG = AssignDataModelAdapter.class.getName();

    ViewHolder holder = null;
    private String awardCode;
    private String awardStr;
    private String proCode;
    private String programStr;

    private String srvCode;
    private String assPageTitle;
    private String criteriaId;
    private String donorId;
    private String entryBy;
    private String entryDate;


    // donor code name
    private static final String USAID = "01";
    private static final String FFP = "03";
    private static final String OFDA = "02";

    // award code name
    private static final String NJIRA = "01";
    private static final String PEER = "10";
    private static final String LAUNC = "09";
    private static final String EC3P = "06";
    private static final String EBOLA = "07";
    private static final String RAPID = "08";

    // program code name
    private static final String MCHN = "001";
    private static final String UCT = "008";
    private static final String AIV = "009";
    private static final String CFW = "010";
    private static final String EC3 = "004";
    private static final String ETU = "005";
    private static final String REACT = "006";
    private static final String AGRP = "003";

    //service code
    private static final String PREGNANT_WOMEN = "01";
    private static final String LACTATING_MOTHER = "02";
    private static final String CHILDEN_UNDER_2 = "03";
    private static final String CHILD_ABOVE_2 = "04";

    private static final String EVD_C1 = "01";
    private static final String CFED_C2 = "02";
    private static final String PLW_C3 = "03";
    private static final String MS = "01";
    private static final String MTS = "01";
    private static final String MA = "01";

    private static final String AGR = "01";
    private static final String PG = "03";
    private static final String IG = "04";
    private static final String LG = "05";
    private static final String MG = "06";

    private static final String DDR = "002";
    private static final String VUL = "01";
    private static final String FFA = "02";


    public String getAwardCode() {
        return awardCode;
    }

    public String getProCode() {
        return proCode;
    }

    public String getSrvCode() {
        return srvCode;
    }

    public String getDonorId() {
        return donorId;
    }

    public String getEntryBy() {
        return entryBy;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public AssignDataModelAdapter(Activity activity, ArrayList<AssignDataModel> assignData,
                                  String awardCode, String awardStr,
                                  String proCode, String programStr,
                                  String srvCode, String donorCode, String assPageTitle,
                                  String criteriaId, String criteriaStr,
                                  String villageStr,
                                  String entryBy, String entryDate
    ) {
        this.activity = activity;
        this.assignData = assignData;
        this.awardCode = awardCode;
        this.proCode = proCode;
        this.srvCode = srvCode;
        this.assPageTitle = assPageTitle;
        this.criteriaId = criteriaId;
        this.criteriaStr = criteriaStr;
        this.donorId = donorCode;
        this.entryBy = entryBy;
        this.entryDate = entryDate;
        this.awardStr = awardStr;
        this.programStr = programStr;

        this.villageStr = villageStr;

    }

    public AssignDataModelAdapter() {
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


        memData.setAward_code(getAwardCode());
        memData.setProgram_code(getProCode());
        memData.setService_code(getSrvCode());
        memData.setDonor_code(getDonorId());
        memData.setEntryBy(getEntryBy());
        memData.setEntryDate(getEntryDate());

        holder.memberId.setText(memData.getNewId());  // 15 digit

        holder.tv_mmName.setText(memData.getHh_mm_name());
        holder.tv_assign.setText(memData.getAssignYN());


        /**
         * donot delte the code
         * *//*
      * holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssignDataModel tem=assignData.get(position);
                //String memAge1=tem.getMember_age();

                Intent iSubAssignClass = null;
                switch (donorId) {
                    case USAID: //DONOR ID USAID OPEN
                        switch (awardCode) {
                            case NJIRA://AWARD CODE NJIRA OPEN
                                switch (proCode) {
                                    case MCHN: //PROGRAM CODE MCHN OPEN
                                        switch (criteriaId) {
                                            case PREGNANT_WOMEN:
                                                iSubAssignClass = new Intent(activity, PW.class);
                                                break;
                                            case LACTATING_MOTHER:
                                                iSubAssignClass = new Intent(activity, LM.class);
                                                break;
                                            case CHILDEN_UNDER_2:
                                                iSubAssignClass = new Intent(activity, CU2.class);
                                                break;
                                            case CHILD_ABOVE_2:
                                                iSubAssignClass = new Intent(activity, CA2.class);
                                                break;
                                        }
                                        break;//PROGRAM CODE MCHN CLOSE

                                    case DDR://PROGRAM CODE DDR START
                                        switch (srvCode)
                                        {
                                            case VUL:
                                                iSubAssignClass = new Intent(activity, AssignForDDRMalwaiVUL.class);
                                                break;
                                            case FFA:
                                                iSubAssignClass = new Intent(activity, FFA.class);
                                                break;
                                        }
                                        break;//PROGRAM CODE DDR CLOSE

                                    case AGRP://program code AGRP OPEN
                                        switch (srvCode) {
                                            case AGR:
                                                iSubAssignClass = new Intent(activity, AGR.class);
                                                break;
                                            case PG:
                                                iSubAssignClass = new Intent(activity, AGR.class);
                                                break;
                                            case IG:
                                                iSubAssignClass = new Intent(activity, AGR.class);
                                                break;
                                            case LG:
                                                iSubAssignClass = new Intent(activity, AGR.class);
                                                break;
                                            case MG:
                                                iSubAssignClass = new Intent(activity, AGR.class);
                                                break;
                                        }
                                        break;//program code AGRP ClOSE
                                }
                                break;//AWARD CODE NJIRA CLOSE
                        }
                        break; // DONOR ID USAID CLOSE

                    case FFP://DONAR CODE FFP OPEN
                        switch (awardCode) {
                            case PEER://AWARD CODE PEER OPEN
                                switch (proCode) {
                                    case UCT://PROGRAM CODE UCT OPEN
                                        switch (srvCode) {
                                            case EVD_C1:
                                                iSubAssignClass = new Intent(activity, AssignForLiberiaUCT.class);
                                                break;
                                            case CFED_C2:
                                                iSubAssignClass = new Intent(activity, AssignForLiberiaUCT.class);
                                                break;
                                            case PLW_C3:
                                                iSubAssignClass = new Intent(activity, AssignForLiberiaUCT.class);
                                                break;

                                        }
                                        break;//PROGRAM CODE UCT CLOSE

                                    case AIV://program Code AIV Start
                                        switch (srvCode) {
                                            case EVD_C1:
                                                iSubAssignClass = new Intent(activity, AssignForLiberiaAIV.class);
                                                break;
                                            case CFED_C2:
                                                iSubAssignClass = new Intent(activity, AssignForLiberiaAIV.class);
                                                break;
                                            case PLW_C3:
                                                iSubAssignClass = new Intent(activity, AssignForLiberiaAIV.class);
                                                break;
                                        }
                                        break;//program Code AIV CLOSE
                                    case CFW://program code CFW start
                                        switch (srvCode) {
                                            case EVD_C1:
                                                iSubAssignClass = new Intent(activity, AssignForLiberiaCFW.class);
                                                break;
                                            case CFED_C2:
                                                iSubAssignClass = new Intent(activity, AssignForLiberiaCFW.class);
                                                break;
                                            case PLW_C3:
                                                iSubAssignClass = new Intent(activity, AssignForLiberiaCFW.class);
                                                break;
                                        }
                                        break;//program code CFW close

                                }
                                break;//AWARD CODE PEER CLOSE
                        }

                        break;//DONAR CODE FFP CLOSE
                    case OFDA://DONAR CODE OFDA OPEN
                        switch (awardCode) {
                            case EC3P://AWARD EC3P OPEN
                                switch (proCode) {
                                    case EC3://PROGRAM CODE EC3 OPEN
                                        switch (srvCode) {
                                            case MS://SERVICE CODE MS OPEN

                                                break;//SERVICE CODE MS CLOSE
                                        }
                                        break;//PROGRAM CODE EC3 CLOSE
                                }
                                break;//AWARD EC3P CLOSE
                            case EBOLA:*//**AWARD EBOLA START*//*
                                switch (proCode) {
                                    case ETU://PROGRAM CODE ETU OPEN
                                        switch (srvCode) {
                                            case MTS://SERVICE CODE MTS OPEN

                                                break;//SERVICE CODE MTS CLOSE
                                        }
                                        break;//PROGRAM CODE ETU CLOSE
                                }
                                break;//AWARD EBOLA CLOSE
                            case RAPID://AWARD CODE RAPID OPEN
                                switch (proCode) {
                                    case REACT://PROGRAM CODE REACT OPEN
                                        switch (srvCode) {
                                            case MA://SERVICE CODE MA OPEN

                                                break;//SERVICE CODE MA CLOSE
                                        }
                                        break;//PROGRAM CODE REACT CLOSE
                                }
                                break;//AWARD CODE RAPID CLOSE
                        }

                        break;//DONAR CODE OFDA CLOSE
                }


                if (iSubAssignClass != null) {


                    iSubAssignClass.putExtra(KEY.PAGE_TITLE, assPageTitle);
                    iSubAssignClass.putExtra(KEY.DIRECTORY, "Assign");
                    iSubAssignClass.putExtra(KEY.ASSIGN_HOUSEHOLD_ID, memData.getHh_id());
                    iSubAssignClass.putExtra(KEY.ASSIGN_HOUSEHOLD_NAME, memData.getHh_name());
                    iSubAssignClass.putExtra(KEY.ASSIGN_HOUSEHOLD_MEMBER_ID, memData.getMemId());
                    iSubAssignClass.putExtra(KEY.ASSIGN_HOUSEHOLD_MEMBER_NAME, memData.getHh_mm_name());
                    iSubAssignClass.putExtra(KEY.ASSIGN_CRITERIA, memData.getAssign_criteria());
                    iSubAssignClass.putExtra(KEY.ASSIGN_COUNTRY_CODE, memData.getCountryCode());
                    iSubAssignClass.putExtra(KEY.ASSIGN_DISTRICT_CODE, memData.getDistrictCode());
                    iSubAssignClass.putExtra(KEY.ASSIGN_UPOZILLA_CODE, memData.getUpazillaCode());
                    iSubAssignClass.putExtra(KEY.ASSIGN_UNIT_CODE, memData.getUnitCode());
                    iSubAssignClass.putExtra(KEY.ASSIGN_VILLAGE_CODE, memData.getVillageCode());
                    memData.setTemVillageString(villageStr);
                    iSubAssignClass.putExtra(KEY.ASSIGN_VILLAGE_STRING, villageStr);
                    iSubAssignClass.putExtra(KEY.ASSIGN_AWARD_CODE, memData.getAward_code());
                    memData.setTemAwardString(awardStr);
                    iSubAssignClass.putExtra(KEY.ASSIGN_AWARD_STRING, awardStr);
                    iSubAssignClass.putExtra(KEY.ASSIGN_PROGRAM_CODE, memData.getProgram_code());
                    memData.setTemProgramString(programStr);
                    iSubAssignClass.putExtra(KEY.ASSIGN_PROGRAM_STRING, programStr);
                    *//** service Code is criteria Code **//*
                    iSubAssignClass.putExtra(KEY.ASSIGN_SERVICE_CODE, memData.getService_code());
                    memData.setTemCriteriaString(criteriaStr);
                    iSubAssignClass.putExtra(KEY.ASSIGN_CRITERIA_STRING, criteriaStr);

                    iSubAssignClass.putExtra(KEY.ASSIGN_DONER_CODE, memData.getDonor_code());
                    iSubAssignClass.putExtra(KEY.ASSIGN_DATA_OBJECT_KEY, memData);

                    activity.finish();
                    activity.startActivity(iSubAssignClass);
                }


            }
        });*/
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
        // holder.tv_mmID.setTextColor(color);
        holder.tv_mmName.setTextColor(color);
        holder.tv_assign.setTextColor(color);
    }

}
