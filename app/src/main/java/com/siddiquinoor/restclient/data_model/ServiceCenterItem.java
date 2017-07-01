package com.siddiquinoor.restclient.data_model;

/**
 * Created by USER on 6/5/2016.
 */
public class ServiceCenterItem {
    private String AdmCountryCode;
    private String ServiceCenterCode;
    private String ServiceCenterName;

    public String getAdmCountryCode() {
        return AdmCountryCode;
    }

    public void setAdmCountryCode(String admCountryCode) {
        AdmCountryCode = admCountryCode;
    }

    public String getServiceCenterCode() {
        return ServiceCenterCode;
    }

    public void setServiceCenterCode(String serviceCenterCode) {
        ServiceCenterCode = serviceCenterCode;
    }

    public String getServiceCenterName() {
        return ServiceCenterName;
    }

    public void setServiceCenterName(String serviceCenterName) {
        ServiceCenterName = serviceCenterName;
    }
}
