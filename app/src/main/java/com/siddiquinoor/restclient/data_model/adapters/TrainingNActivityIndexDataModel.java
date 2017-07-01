package com.siddiquinoor.restclient.data_model.adapters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nirzon pop (Faisal Mohammad )
 * on 4/5/2017.
 */

public class TrainingNActivityIndexDataModel implements Parcelable {
    private String cCode;
    private String eventTittle;
    private String eventCode;
    private String donorCode;
    private String awardCode;
    private String taGroupCode;
    private String taSubGroupCode;
    private String taOrgCode;
    private String startDate;
    private String endDate;
    private String venueName;
    private String addressName;
    private String activeStatus;
    private String totalDays;
    private String hourPerDay;

    public TrainingNActivityIndexDataModel() {
    }

    public static final Creator CREATOR = new Creator() {
        public TrainingNActivityIndexDataModel createFromParcel(Parcel in) {
            return new TrainingNActivityIndexDataModel(in);
        }

        public TrainingNActivityIndexDataModel[] newArray(int size) {
            return new TrainingNActivityIndexDataModel[size];
        }
    };

    public TrainingNActivityIndexDataModel(Parcel in) {
        readFromParcel(in);
    }


    private void readFromParcel(Parcel in) {


        cCode = in.readString();
        eventTittle = in.readString();
        eventCode = in.readString();
        donorCode = in.readString();
        awardCode = in.readString();
        taGroupCode = in.readString();
        taSubGroupCode = in.readString();
        taOrgCode = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        venueName = in.readString();
        addressName = in.readString();
        activeStatus = in.readString();
        totalDays = in.readString();
        hourPerDay = in.readString();

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cCode);
        dest.writeString(eventTittle);
        dest.writeString(eventCode);
        dest.writeString(donorCode);
        dest.writeString(awardCode);
        dest.writeString(taGroupCode);
        dest.writeString(taSubGroupCode);
        dest.writeString(taOrgCode);
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeString(venueName);
        dest.writeString(addressName);
        dest.writeString(activeStatus);
        dest.writeString(totalDays);
        dest.writeString(hourPerDay);
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAwardCode() {
        return awardCode;
    }

    public void setAwardCode(String awardCode) {
        this.awardCode = awardCode;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public String getDonorCode() {
        return donorCode;
    }

    public void setDonorCode(String donorCode) {
        this.donorCode = donorCode;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventTittle() {
        return eventTittle;
    }

    public void setEventTittle(String eventTittle) {
        this.eventTittle = eventTittle;
    }

    public String getHourPerDay() {
        return hourPerDay;
    }

    public void setHourPerDay(String hourPerDay) {
        this.hourPerDay = hourPerDay;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTaGroupCode() {
        return taGroupCode;
    }

    public void setTaGroupCode(String taGroupCode) {
        this.taGroupCode = taGroupCode;
    }

    public String getTaOrgCode() {
        return taOrgCode;
    }

    public void setTaOrgCode(String taOrgCode) {
        this.taOrgCode = taOrgCode;
    }

    public String getTaSubGroupCode() {
        return taSubGroupCode;
    }

    public void setTaSubGroupCode(String taSubGroupCode) {
        this.taSubGroupCode = taSubGroupCode;
    }

    public String getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(String totalDays) {
        this.totalDays = totalDays;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
}
