package com.siddiquinoor.restclient.data_model;

/**
 * Created by USER on 6/21/2016.
 */


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Faisal Mohammad on 8/19/2015.
 * to pass class object from 1 activity to other activity
 */
public class GPS_LocationDataModel extends PrimaryDataModel implements Parcelable {
    private String admCountryCode;
    private String groupCode;
    private String subGroupCode;
    private String locationCode;
    private String groupName;
    private String subGroupName;
    private String locationName;
    private String lat;
    private String lng;



    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public GPS_LocationDataModel createFromParcel(Parcel in) {
            return new GPS_LocationDataModel(in);
        }

        public GPS_LocationDataModel[] newArray(int size) {
            return new GPS_LocationDataModel[size];
        }
    };

    public GPS_LocationDataModel(Parcel in) {
        readFromParcel(in);
    }

    public GPS_LocationDataModel() {
    }

    private void readFromParcel(Parcel in) {
        admCountryCode = in.readString();
        groupCode = in.readString();
        subGroupCode = in.readString();
        locationCode = in.readString();
        groupName = in.readString();
        subGroupName = in.readString();
        locationName = in.readString();
        lat = in.readString();
        lng = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(admCountryCode);
        dest.writeString(groupCode);
        dest.writeString(subGroupCode);
        dest.writeString(locationCode);
        dest.writeString(groupName);
        dest.writeString(subGroupName);
        dest.writeString(locationName);

        dest.writeString(lat);
        dest.writeString(lng);
    }

    public String getAdmCountryCode() {
        return admCountryCode;
    }

    public void setAdmCountryCode(String admCountryCode) {
        this.admCountryCode = admCountryCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getSubGroupCode() {
        return subGroupCode;
    }

    public void setSubGroupCode(String subGroupCode) {
        this.subGroupCode = subGroupCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSubGroupName() {
        return subGroupName;
    }

    public void setSubGroupName(String subGroupName) {
        this.subGroupName = subGroupName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
