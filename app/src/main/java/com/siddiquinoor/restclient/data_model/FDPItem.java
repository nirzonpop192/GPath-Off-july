package com.siddiquinoor.restclient.data_model;

/**
 * Created by USER on 5/31/2016.
 */
public class FDPItem {


    private String AdmCountryCode;
    private String FDPCode;
    private String FDPName;

    public String getAdmCountryCode() {
        return AdmCountryCode;
    }

    public void setAdmCountryCode(String admCountryCode) {
        AdmCountryCode = admCountryCode;
    }

    public String getFDPCode() {
        return FDPCode;
    }

    public void setFDPCode(String FDPCode) {
        this.FDPCode = FDPCode;
    }

    public String getFDPName() {
        return FDPName;
    }

    public void setFDPName(String FDPName) {
        this.FDPName = FDPName;
    }
}
