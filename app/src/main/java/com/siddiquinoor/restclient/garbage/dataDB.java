package com.siddiquinoor.restclient.garbage;

import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is to hold Registration data while set/get
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.3.0
 * @since 1.0
 *
 */
// in this format use to

public class dataDB implements Comparable<dataDB>{

    public String _id = null;
    public String c_code = null;
    public String districtID = null;
    public String upID = null;
    public String unit = null;
    public String vID = null;
    public String hhID = null;
    public String regDate = null;
    public String pName = null;
    public String sex = null;
    public String hhSize = null;
    public String latitude = null;
    public String longitude = null;
    public String agLand = null;
    public String vStatus = null;
    public String mStatus = null;
    public String entry_by = null;
    public String entry_date = null;
    public String VSLAGroup = null;

    JSONArray membersData = null;
    ArrayList<ArrayList<dataMemDB>> sData = new ArrayList<ArrayList<dataMemDB>>();

    @Override
    public int compareTo(dataDB o)
    {
        //return title.toLowerCase().compareTo(o.title.toLowerCase());
        return 0;
    }

    public void setSubData(ArrayList<dataMemDB> s_data){
        sData.add(s_data);
    }

    public JSONArray getSubData() {

        JSONArray members = new JSONArray();

        for (int i = 0; i < sData.size(); i++) {

                ArrayList<dataMemDB> tmpData = sData.get(i);

                for (int j = 0; j < tmpData.size(); j++) {

                    JSONObject mData = new JSONObject();

                    //mData.add(tmpData.get(j)._id);
                    try {
                        mData.put("mem_id", tmpData.get(j).mem_ID);
                        mData.put("mem_hhID",tmpData.get(j).mem_hhID);
                        mData.put("mem_c_code",tmpData.get(j).mem_c_code);
                        mData.put("mem_district",tmpData.get(j).mem_district);
                        mData.put("mem_upazilla",tmpData.get(j).mem_upazilla);
                        mData.put("mem_unit",tmpData.get(j).mem_unit);
                        mData.put("mem_village",tmpData.get(j).mem_village);
                        mData.put("mem_sex",tmpData.get(j).mem_sex);

                        mData.put("mem_name",tmpData.get(j).mem_name);
                        mData.put("mem_relation",tmpData.get(j).mem_relation);
                        mData.put("entry_by",tmpData.get(j).entry_by);
                        mData.put("entry_date",tmpData.get(j).entry_date);

                        mData.put("mem_lmp_date",tmpData.get(j).lmp_date);
                        mData.put("mem_child_dob",tmpData.get(j).child_date);
                        mData.put("mem_elderly",tmpData.get(j).elderly);
                        mData.put("mem_disabled",tmpData.get(j).disabled);
                        mData.put("mem_age",tmpData.get(j).mem_age);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    members.put(mData);
                }
        }

        //BasicNameValuePair bp =  new BasicNameValuePair("MEMBERS", String.valueOf(members));
        Log.d("Members", members.toString());
        return members;

    }
 }