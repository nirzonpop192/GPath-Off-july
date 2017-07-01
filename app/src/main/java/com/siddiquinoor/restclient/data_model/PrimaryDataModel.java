package com.siddiquinoor.restclient.data_model;

/**
 * Created by USER on 2/22/2016.
 */
public class PrimaryDataModel {

    private String countryCode;
    private String districtCode;
    private String upazillaCode;
    private String unitCode;
    private String villageCode;

    private String hhId;

    private String entryBy;
    private String entryDate;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getUpazillaCode() {
        return upazillaCode;
    }

    public void setUpazillaCode(String upazillaCode) {
        this.upazillaCode = upazillaCode;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getHhId() {
        return hhId;
    }

    public void setHhId(String hhId) {
        this.hhId = hhId;
    }

    public String getEntryBy() {
        return entryBy;
    }

    public void setEntryBy(String entryBy) {
        this.entryBy = entryBy;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }
}
