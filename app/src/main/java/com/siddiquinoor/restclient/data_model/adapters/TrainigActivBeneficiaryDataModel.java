package com.siddiquinoor.restclient.data_model.adapters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pop on 4/8/2017.
 */

public class TrainigActivBeneficiaryDataModel implements Parcelable {
    private String cCode;
    private String layR4Name;
    private String addressName;
    private String newId;
    private String hh_name; // house hold id
    private String hh_mm_name;
    private String member_sex;
    private String member_age;
    private boolean isCheckedBoxChecked;

    public TrainigActivBeneficiaryDataModel() {
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public String getHh_mm_name() {
        return hh_mm_name;
    }

    public void setHh_mm_name(String hh_mm_name) {
        this.hh_mm_name = hh_mm_name;
    }

    public String getHh_name() {
        return hh_name;
    }

    public void setHh_name(String hh_name) {
        this.hh_name = hh_name;
    }

    public String getMember_age() {
        return member_age;
    }

    public void setMember_age(String member_age) {
        this.member_age = member_age;
    }

    public String getMember_sex() {
        return member_sex;
    }

    public void setMember_sex(String member_sex) {
        this.member_sex = member_sex;
    }

    public String getNewId() {
        return newId;
    }

    public boolean isCheckedBoxChecked() {
        return isCheckedBoxChecked;
    }

    public void setCheckedBoxChecked(boolean checkedBoxChecked) {
        isCheckedBoxChecked = checkedBoxChecked;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public String getLayR4Name() {
        return layR4Name;
    }

    public void setLayR4Name(String layR4Name) {
        this.layR4Name = layR4Name;
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public TrainigActivBeneficiaryDataModel createFromParcel(Parcel in) {
            return new TrainigActivBeneficiaryDataModel(in);
        }

        @Override
        public TrainigActivBeneficiaryDataModel[] newArray(int size) {
            return new TrainigActivBeneficiaryDataModel[size];
        }
    };

    public TrainigActivBeneficiaryDataModel(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {


        cCode = in.readString();
        layR4Name = in.readString();
        addressName = in.readString();
        newId = in.readString();
        hh_name = in.readString();
        hh_mm_name = in.readString();
        member_sex = in.readString();
        member_age = in.readString();



    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cCode);
        dest.writeString(layR4Name);
        dest.writeString(addressName);
        dest.writeString(newId);
        dest.writeString(hh_name);
        dest.writeString(hh_mm_name);
        dest.writeString(member_sex);
        dest.writeString(member_age);
    }
}
