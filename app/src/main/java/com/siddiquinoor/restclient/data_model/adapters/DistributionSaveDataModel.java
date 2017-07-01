package com.siddiquinoor.restclient.data_model.adapters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by USER on 2/8/2016.
 */
public class DistributionSaveDataModel  implements Parcelable {




    private String countryCode;
    private String AdmDonorCode ;
    private String AdmAwardCode ;
    private String districtCode ;
    private String upCode ;
    private String uniteCode ;
    private String villageCode ;
    private String ProgCode ;
    private String SrvCode ;
    private String OpMonthCode ;
    private String FDPCode ;
    private String ID ; // the Id can be house hold or member aslo
    private String DistStatus ;
    private String EntryBy ;
    private String EntryDate ;
    private String distFlag ;
    private String srvOpMonthCode ;
    private String wd ;

    public DistributionSaveDataModel() {
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public DistributionSaveDataModel createFromParcel(Parcel in) {
            return new DistributionSaveDataModel(in);
        }

        public DistributionSaveDataModel[] newArray(int size) {
            return new DistributionSaveDataModel[size];
        }
    };

    public DistributionSaveDataModel(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {





        countryCode = in.readString();
        AdmDonorCode = in.readString();
        AdmAwardCode = in.readString();
        districtCode = in.readString();
        upCode = in.readString();
        uniteCode = in.readString();
        villageCode = in.readString();
        ProgCode = in.readString();
        SrvCode = in.readString();
        OpMonthCode = in.readString();
        districtCode = in.readString();
        FDPCode = in.readString();
        ID = in.readString();
        villageCode = in.readString();
        DistStatus = in.readString();

        EntryBy = in.readString();
        EntryDate = in.readString();

        distFlag = in.readString();
        srvOpMonthCode = in.readString();
        wd = in.readString();


    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeString(countryCode);
        dest.writeString(AdmDonorCode);
        dest.writeString(AdmAwardCode);
        dest.writeString(districtCode);
        dest.writeString(upCode);
        dest.writeString(uniteCode);
        dest.writeString(villageCode);
        dest.writeString(ProgCode);
        dest.writeString(SrvCode);
        dest.writeString(OpMonthCode);
        dest.writeString(districtCode);
        dest.writeString(FDPCode);
        dest.writeString(ID);
        dest.writeString(villageCode);
        dest.writeString(DistStatus);

        dest.writeString(EntryBy);
        dest.writeString(EntryDate);

        dest.writeString(distFlag);
        dest.writeString(srvOpMonthCode);
        dest.writeString(wd);


    }


    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getDistFlag() {
        return distFlag;
    }

    public void setDistFlag(String distFlag) {
        this.distFlag = distFlag;
    }

    public String getSrvOpMonthCode() {
        return srvOpMonthCode;
    }

    public void setSrvOpMonthCode(String srvOpMonthCode) {
        this.srvOpMonthCode = srvOpMonthCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAdmDonorCode() {
        return AdmDonorCode;
    }

    public void setAdmDonorCode(String admDonorCode) {
        AdmDonorCode = admDonorCode;
    }

    public String getAdmAwardCode() {
        return AdmAwardCode;
    }

    public void setAdmAwardCode(String admAwardCode) {
        AdmAwardCode = admAwardCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getUpCode() {
        return upCode;
    }

    public void setUpCode(String upCode) {
        this.upCode = upCode;
    }

    public String getUniteCode() {
        return uniteCode;
    }

    public void setUniteCode(String uniteCode) {
        this.uniteCode = uniteCode;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getProgCode() {
        return ProgCode;
    }

    public void setProgCode(String progCode) {
        ProgCode = progCode;
    }

    public String getSrvCode() {
        return SrvCode;
    }

    public void setSrvCode(String srvCode) {
        SrvCode = srvCode;
    }

    public String getOpMonthCode() {
        return OpMonthCode;
    }

    public void setOpMonthCode(String opMonthCode) {
        OpMonthCode = opMonthCode;
    }

    public String getFDPCode() {
        return FDPCode;
    }

    public void setFDPCode(String FDPCode) {
        this.FDPCode = FDPCode;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDistStatus() {
        return DistStatus;
    }

    public void setDistStatus(String distStatus) {
        DistStatus = distStatus;
    }

    public String getEntryBy() {
        return EntryBy;
    }

    public void setEntryBy(String entryBy) {
        EntryBy = entryBy;
    }

    public String getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(String entryDate) {
        EntryDate = entryDate;
    }
}
