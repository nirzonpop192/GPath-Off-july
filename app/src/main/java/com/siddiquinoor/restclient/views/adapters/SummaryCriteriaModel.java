package com.siddiquinoor.restclient.views.adapters;

/**
 * Created by USER on 9/2/2015.
 */
public class SummaryCriteriaModel {
    private int record=0;
    private String criteria_name="";
    private String criteria_id="";
    private int plan=0;

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public SummaryCriteriaModel() {  }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public String getCriteria_name() {
        return criteria_name;
    }

    public void setCriteria_name(String criteria_name) {
        this.criteria_name = criteria_name;
    }

    public String getCriteria_id() {
        return criteria_id;
    }

    public void setCriteria_id(String criteria_id) {
        this.criteria_id = criteria_id;
    }
}
