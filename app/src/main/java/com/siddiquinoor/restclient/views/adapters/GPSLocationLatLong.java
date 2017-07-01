package com.siddiquinoor.restclient.views.adapters;

/**
 * Created by Faisal Mohammad on 8/24/2015.
 */
public class GPSLocationLatLong {

  private String latitude;
  private String longitude;
    private String groupId;
    private String subGroupId;
    private String locationId;

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    private String countryId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSubGroupId() {
        return subGroupId;
    }

    public void setSubGroupId(String subGroupId) {
        this.subGroupId = subGroupId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }


    public GPSLocationLatLong(String countryId, String groupId, String subGroupId, String locationId,String latitude, String longitude) {
        this.countryId = countryId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.groupId = groupId;
        this.subGroupId = subGroupId;
        this.locationId = locationId;

    }



        public GPSLocationLatLong() {
        }



        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
}
