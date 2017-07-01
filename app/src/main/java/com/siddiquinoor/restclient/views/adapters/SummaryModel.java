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
public class SummaryModel {

    private int records = 0;
    private String vill_name = "";
    private String vill_code = "";

    public SummaryModel(){}

    public void setVillageName(String name){this.vill_name=name;}
    public String getVillName(){return this.vill_name;}

    public void setVillageCode(String vill_code){this.vill_code=vill_code;}
    public String getVillCode(){return this.vill_code;}

    public void setRecords(int record){this.records=record;}
    public int getRecords(){return this.records;}
}
