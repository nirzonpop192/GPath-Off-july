package com.siddiquinoor.restclient.garbage;

/**
 * Created by USER on 10/9/2015.
 */
public class dataCU2DB implements  Comparable<dataCU2DB> {

    public String cu_id=null;
    public String cu_c_code = null;
    public String cu_districtID = null;
    public String cu_upID = null;
    public String cu_unit = null;
    public String cu_vID = null;
    public String cu_donorID = null;
    public String cu_awardID = null;
    public String cu_hhID = null;
    public String cu_hh_mmID = null;
    public String cu_regDate = null;
    public String cu_cu2dob = null;
    public String cu_programID = null;
    public String cu_serviceID = null;
    public String cu_grdID = null;
    public String cu_cu_grdDate = null;
    public String cu_entry_by = null;
    public String cu_entry_date = null;


    @Override
    public int compareTo(dataCU2DB another) {
        return 0;
    }
}
