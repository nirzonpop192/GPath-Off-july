package com.siddiquinoor.restclient.data_model;

/**
 * Created by USER on 8/13/2016.
 */
public class ProgramMasterDM {
   private String AdmCountryCode;
   private String AdmProgCode;
   private String AdmAwardCode;
   private String AdmDonorCode;
   private String ProgName;
   private String ProgShortName;
   private String MultipleSrv;

    public String getAdmCountryCode() {
        return AdmCountryCode;
    }

    public void setAdmCountryCode(String admCountryCode) {
        AdmCountryCode = admCountryCode;
    }

    public String getAdmProgCode() {
        return AdmProgCode;
    }

    public void setAdmProgCode(String admProgCode) {
        AdmProgCode = admProgCode;
    }

    public String getAdmAwardCode() {
        return AdmAwardCode;
    }

    public void setAdmAwardCode(String admAwardCode) {
        AdmAwardCode = admAwardCode;
    }

    public String getAdmDonorCode() {
        return AdmDonorCode;
    }

    public void setAdmDonorCode(String admDonorCode) {
        AdmDonorCode = admDonorCode;
    }

    public String getProgName() {
        return ProgName;
    }

    public void setProgName(String progName) {
        ProgName = progName;
    }

    public String getProgShortName() {
        return ProgShortName;
    }

    public void setProgShortName(String progShortName) {
        ProgShortName = progShortName;
    }

    public String getMultipleSrv() {
        return MultipleSrv;
    }

    public void setMultipleSrv(String multipleSrv) {
        MultipleSrv = multipleSrv;
    }
}
