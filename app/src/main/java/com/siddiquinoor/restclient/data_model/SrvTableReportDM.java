package com.siddiquinoor.restclient.data_model;

/**
 * Created by USER on 8/13/2016.
 */
public class SrvTableReportDM {

   private String AdmCountryCode;
    private String AdmDonorCode;
    private String AdmAwardCode;
    private String ProgCode;
    private  String SrvCenterCode;
    private String OpCode;
    private String OpMonthCode;
    private String PreparedBy;
    private String VerifiedBy;

    public String getAdmCountryCode() {
        return AdmCountryCode;
    }

    public void setAdmCountryCode(String admCountryCode) {
        AdmCountryCode = admCountryCode;
    }

    public String getAdmDonorCode() {
        return AdmDonorCode;
    }

    public void setAdmDonorCode(String admDonorCode) {
        AdmDonorCode = admDonorCode;
    }

    public String getAdmAwardCode() {
        return AdmAwardCode;
    }

    public void setAdmAwardCode(String admAwardCode) {
        AdmAwardCode = admAwardCode;
    }

    public String getProgCode() {
        return ProgCode;
    }

    public void setProgCode(String progCode) {
        ProgCode = progCode;
    }

    public String getSrvCenterCode() {
        return SrvCenterCode;
    }

    public void setSrvCenterCode(String srvCenterCode) {
        SrvCenterCode = srvCenterCode;
    }

    public String getOpCode() {
        return OpCode;
    }

    public void setOpCode(String opCode) {
        OpCode = opCode;
    }

    public String getOpMonthCode() {
        return OpMonthCode;
    }

    public void setOpMonthCode(String opMonthCode) {
        OpMonthCode = opMonthCode;
    }

    public String getPreparedBy() {
        return PreparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        PreparedBy = preparedBy;
    }

    public String getVerifiedBy() {
        return VerifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        VerifiedBy = verifiedBy;
    }
}
