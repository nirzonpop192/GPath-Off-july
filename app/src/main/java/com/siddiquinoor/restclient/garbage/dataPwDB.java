package com.siddiquinoor.restclient.garbage;

/**
 * Created by USER on 9/7/2015.
 */
public class dataPwDB implements Comparable<dataPwDB> {
    public String pw_id=null;
    public String pw_c_code = null;
    public String pw_districtID = null;
    public String pw_upID = null;
    public String pw_unit = null;
    public String pw_vID = null;
    public String pw_donorID = null;
    public String pw_awardID = null;
    public String pw_hhID = null;
    public String pw_hh_mmID = null;
    public String pw_regDate = null;
    public String pw_lmpDate = null;
    public String pw_programID = null;
    public String pw_serviceID = null;
    public String pw_grdID = null;
    public String pw_pw_grdDate = null;
    public String pw_entry_by = null;
    public String pw_entry_date = null;

    @Override
    public int compareTo(dataPwDB another) {
        return 0;
    }
}
