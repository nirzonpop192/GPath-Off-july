package com.siddiquinoor.restclient.data_model;

/**
 * Created by USER on 5/22/2016.
 */
public class VillageItem {
    private String GeoLayRName;
    private String AdmCountryCode;
    private String LayRCode;
    private String LayR4ListName;

    public String getGeoLayRName() {
        return GeoLayRName;
    }

    public void setGeoLayRName(String geoLayRName) {
        GeoLayRName = geoLayRName;
    }

    public String getAdmCountryCode() {
        return AdmCountryCode;
    }

    public void setAdmCountryCode(String admCountryCode) {
        AdmCountryCode = admCountryCode;
    }

    public String getLayRCode() {
        return LayRCode;
    }

    public void setLayRCode(String layRCode) {
        LayRCode = layRCode;
    }

    public String getLayR4ListName() {
        return LayR4ListName;
    }

    public void setLayR4ListName(String layR4ListName) {
        LayR4ListName = layR4ListName;
    }

    public VillageItem() {
    }

    public VillageItem(String admCountryCode, String geoLayRName, String layR4ListName, String layRCode) {
        AdmCountryCode = admCountryCode;
        GeoLayRName = geoLayRName;
        LayR4ListName = layR4ListName;
        LayRCode = layRCode;
    }
}

