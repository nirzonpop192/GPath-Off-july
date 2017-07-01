package com.siddiquinoor.restclient.views.adapters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Faisal on 9/5/2016.
 * For Group SumRegLay4TotalHHRecords
 */
public class SummaryGroupListDataModel implements   Parcelable {
    private String cCode;
    private String donorCode;
    private String awardCode;
    private String programCode;
    private String serviceCode;
    private String groupCatCode;
    private String groupCatShortName;
    private String groupCode;
    private String groupName;
    private String srvShortName;
    private String count;
    private String layR3Name;


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public SummaryGroupListDataModel createFromParcel(Parcel in) {
            return new SummaryGroupListDataModel(in);
        }

        @Override
        public SummaryGroupListDataModel[] newArray(int size) {
            return new SummaryGroupListDataModel[size];
        }
    };

    public SummaryGroupListDataModel() {
    }

    public SummaryGroupListDataModel(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {
        cCode = in.readString();
        donorCode = in.readString();
        awardCode = in.readString();
        programCode = in.readString();
        serviceCode = in.readString();
        groupCatCode = in.readString();
        groupCatShortName = in.readString();
        groupCode = in.readString();
        groupName = in.readString();
        srvShortName = in.readString();
        count = in.readString();
        layR3Name = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cCode);
        dest.writeString(donorCode);
        dest.writeString(awardCode);
        dest.writeString(programCode);
        dest.writeString(serviceCode);
        dest.writeString(groupCatCode);
        dest.writeString(groupCatShortName);
        dest.writeString(groupCode);
        dest.writeString(groupName);
        dest.writeString(srvShortName);
        dest.writeString(count);
        dest.writeString(layR3Name);


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

    public String getAwardCode() {
        return awardCode;
    }

    public void setAwardCode(String awardCode) {
        this.awardCode = awardCode;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getGroupCatCode() {
        return groupCatCode;
    }

    public void setGroupCatCode(String groupCatCode) {
        this.groupCatCode = groupCatCode;
    }

    public String getGroupCatShortName() {
        return groupCatShortName;
    }

    public void setGroupCatShortName(String groupCatShortName) {
        this.groupCatShortName = groupCatShortName;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSrvShortName() {
        return srvShortName;
    }

    public void setSrvShortName(String srvShortName) {
        this.srvShortName = srvShortName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getLayR3Name() {
        return layR3Name;
    }

    public void setLayR3Name(String layR3Name) {
        this.layR3Name = layR3Name;
    }
}
