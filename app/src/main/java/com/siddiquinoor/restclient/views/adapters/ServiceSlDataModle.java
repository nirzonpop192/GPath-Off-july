package com.siddiquinoor.restclient.views.adapters;

/**
 * Created by USER on 9/14/2015.
 */
public class ServiceSlDataModle {

    private String srvSerial;
    private String serviceDate;
    private String serviceStatus;

    public ServiceSlDataModle(String srvSerial, String serviceDate, String serviceStatus) {
        this.srvSerial = srvSerial;
        this.serviceDate = serviceDate;
        this.serviceStatus = serviceStatus;
    }

    public ServiceSlDataModle() {
    }

    public String getSrvSerial() {
        return srvSerial;
    }

    public void setSrvSerial(String srvSerial) {
        this.srvSerial = srvSerial;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}
