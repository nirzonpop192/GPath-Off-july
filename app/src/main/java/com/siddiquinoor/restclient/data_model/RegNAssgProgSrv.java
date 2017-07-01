package com.siddiquinoor.restclient.data_model;

/**
 * Created by Faisal on 2/23/2016.
 */
public class RegNAssgProgSrv extends PrimaryDataModel {
    private String donorCode;
    private String awardCode;
    private String programCode;
    private String serviceCode;
    private String memberId;
    private String regDate;
    private String grdCode;
    private String grdDate;
    private String grdStatus;
    private String regNStatus;

    public String getDonorCode() {
        return donorCode;
    }

    public void setDonorCode(String donorCode) {
        this.donorCode = donorCode;
    }

    public String getAwardCode() {
        return awardCode;
    }

    public void setAwardCode(String awardCode) {
        this.awardCode = awardCode;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getGrdCode() {
        return grdCode;
    }

    public void setGrdCode(String grdCode) {
        this.grdCode = grdCode;
    }

    public String getGrdDate() {
        return grdDate;
    }

    public void setGrdDate(String grdDate) {
        this.grdDate = grdDate;
    }

    public String getGrdStatus() {
        return grdStatus;
    }

    public void setGrdStatus(String grdStatus) {
        this.grdStatus = grdStatus;
    }

    public String getRegNStatus() {
        return regNStatus;
    }

    public void setRegNStatus(String regNStatus) {
        this.regNStatus = regNStatus;
    }
}
