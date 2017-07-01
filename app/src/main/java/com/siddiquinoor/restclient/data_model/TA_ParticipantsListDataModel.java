package com.siddiquinoor.restclient.data_model;

/**
 * Created by pop on 4/11/2017.
 */

public class TA_ParticipantsListDataModel {
    private String cCode;
    private String eventCode;
    private String partId;
    private String idCatCode;
    private String partName;
    private String partOrgNCode;
    private String sex;
    private String partCatCode;
    private String posCode;
    private String amSession;
    private String pmSession;
    private String atdnDate;
    private String taGroup;

    public String getTaGroup() {
        return taGroup;
    }

    public void setTaGroup(String taGroup) {
        this.taGroup = taGroup;
    }

    public String getAmSession() {
        return amSession;
    }

    public void setAmSession(String amSession) {
        this.amSession = amSession;
    }

    public String getAtdnDate() {
        return atdnDate;
    }

    public void setAtdnDate(String atdnDate) {
        this.atdnDate = atdnDate;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getIdCatCode() {
        return idCatCode;
    }

    public void setIdCatCode(String idCatCode) {
        this.idCatCode = idCatCode;
    }

    public String getPartCatCode() {
        return partCatCode;
    }

    public void setPartCatCode(String partCatCode) {
        this.partCatCode = partCatCode;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartOrgNCode() {
        return partOrgNCode;
    }

    public void setPartOrgNCode(String partOrgNCode) {
        this.partOrgNCode = partOrgNCode;
    }

    public String getPmSession() {
        return pmSession;
    }

    public void setPmSession(String pmSession) {
        this.pmSession = pmSession;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
