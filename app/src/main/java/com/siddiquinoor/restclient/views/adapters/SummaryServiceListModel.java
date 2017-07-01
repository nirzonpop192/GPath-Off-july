package com.siddiquinoor.restclient.views.adapters;

/**
 * Created by USER on 9/4/2015.
 */
public class SummaryServiceListModel {
    private String customId;
  //  private String hh_mm_id;
    private String serviceCount;
    private String memberName;
   // private String serviceCount;
   // private String srv_date;
   private String per_unit_cost;
   private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPer_unit_cost() {
        return per_unit_cost;
    }

    public void setPer_unit_cost(String per_unit_cost) {
        this.per_unit_cost = per_unit_cost;
    }

    public SummaryServiceListModel() {
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

  /*  public String getMemberId() {
        return hh_mm_id;
    }

    public void setMemberId(String hh_mm_id) {
        this.hh_mm_id = hh_mm_id;
    }*/

    public String getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(String serviceCount) {
        this.serviceCount = serviceCount;
    }

   /* public String getSrv_date() {
        return srv_date;
    }

    public void setSrv_date(String srv_date) {
        this.srv_date = srv_date;
    }*/

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
