package com.siddiquinoor.restclient.garbage;

/**
 * Created by USER on 11/9/2015.
 */
public class dataCardRequestDB implements  Comparable<dataCardRequestDB> {
    public String _id = null;
    public String c_code = null;
    public String districtID = null;
    public String upID = null;
    public String unit = null;
    public String vID = null;
    public String donorID = null;
    public String awardID = null;
    public String hhID = null;
    public String hh_mmID = null;
    public String report_groupID= null;
    public String reportID = null;
    public String card_request_slID = null;
    public String card_print_reasonID= null;
    public String request_date= null;
    public String print_date = null;
    public String print_by = null;
    public String delivery_date = null;
    public String delivery_by = null;
    public String delivery_status = null;
    public String entry_by = null;
    public String entry_date = null;



    @Override
    public int compareTo(dataCardRequestDB another) {
        return 0;
    }
}
