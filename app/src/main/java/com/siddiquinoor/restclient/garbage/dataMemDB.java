package com.siddiquinoor.restclient.garbage;

/**
 * Activity for presenting the list of all entry in a list view
 * with image and details
 *
 * @author Siddiqui Noor
 * @version 1.6.0
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @since 1.6
 * Created on 12-May-15
 */
public class dataMemDB implements Comparable<dataMemDB>
{

    public String _id = null;

    // For Members
   public String mem_c_code = null;
   public String mem_district = null;
   public String mem_upazilla = null;
   public String mem_unit = null;
   public String mem_village = null;
   public String mem_hhID = null;
   public String mem_ID = null;
   public String mem_name = null;
   public String mem_sex = null;
   public String mem_relation = null;
   public String entry_by = null;
   public String entry_date = null;
   public String lmp_date = null;
   public String child_date = null;
   public String elderly = null;
   public String disabled = null;
   public String mem_age = null;

    @Override
    public int compareTo(dataMemDB o)
    {
        //return title.toLowerCase().compareTo(o.title.toLowerCase());
        return 0;
    }
}
