package com.siddiquinoor.restclient.garbage;

/**
 * Created by User on 8/15/2015.
 */
public class dataServiceDB implements Comparable<dataServiceDB> {

    String _id = null;
    String c_code = null;
    String donorID= null;
    String awardID = null;


    String districtID = null;
    String upID = null;
    String unitID = null;
    String vID = null;

    String hhID = null;
    String memID = null;

    String programID = null;
    String serviceID = null;
    String opID = null;

    String opMonthID = null;

    String srvSL = null;
    String srvCenterID = null;
    String srvDT = null;

    String sStatus = null;
    String entry_by = null;
    String entry_date = null;


    @Override
    public int compareTo(dataServiceDB another) {
        return 0;
    }
}
