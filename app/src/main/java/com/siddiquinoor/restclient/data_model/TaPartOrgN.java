package com.siddiquinoor.restclient.data_model;

/**
 * Created by pop
 * on 4/10/2017.
 */

public class TaPartOrgN {
    private String cCode;
    private String partOrgNCode;
    private String partOrgNName;
    private String srcBen;

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public String getPartOrgNCode() {
        return partOrgNCode;
    }

    public void setPartOrgNCode(String partOrgNCode) {
        this.partOrgNCode = partOrgNCode;
    }

    public String getPartOrgNName() {
        return partOrgNName;
    }

    public void setPartOrgNName(String partOrgNName) {
        this.partOrgNName = partOrgNName;
    }

    public String getSrcBen() {
        return srcBen;
    }

    public void setSrcBen(String srcBen) {
        this.srcBen = srcBen;
    }
}
