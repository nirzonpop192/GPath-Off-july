package com.siddiquinoor.restclient.data_model.adapters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by USER on 8/16/2016.
 */
public class CommunityGroupDataModel implements Parcelable {

    private String communityGroupName;
    private String communityGroupCode;
    private String programShortName;
    private String programName;
    private String programCode;
    private String commuCategoriesShortName;
    private String commuCategoriesName;
    private String commuCategoriesCode;
    private String orgonizationCode;
    private String orgonizationName;
    private String awardCode;
    private String awardName;
    private String layr1Code;
    private String layr2Code;
    private String layr3Code;
    private String layr3Name;
    private String staffCode;
    private String staffName;

    private String landSizeUnderIrrigation;
    private String irrigationSystemUsed;
    private String fundSupport;
    private String active;
    private String repName;
    private String repPhoneNo;
    private String formation;
    private String typeOfGroup;
    private String status;
    private String projectNo;
    private String projectTitle;


    public String getLayr1Code() {
        return layr1Code;
    }

    public void setLayr1Code(String layr1Code) {
        this.layr1Code = layr1Code;
    }

    public String getLayr2Code() {
        return layr2Code;
    }

    public void setLayr2Code(String layr2Code) {
        this.layr2Code = layr2Code;
    }

    public CommunityGroupDataModel() {
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public CommunityGroupDataModel createFromParcel(Parcel in) {
            return new CommunityGroupDataModel(in);
        }

        @Override
        public CommunityGroupDataModel[] newArray(int size) {
            return new CommunityGroupDataModel[size];
        }
    };

    public CommunityGroupDataModel(Parcel in) {
        readFromParcel(in);
    }


    private void readFromParcel(Parcel in) {

        communityGroupName = in.readString();
        communityGroupCode = in.readString();
        programShortName = in.readString();
        programName = in.readString();
        programCode = in.readString();
        commuCategoriesShortName = in.readString();
        commuCategoriesName = in.readString();
        commuCategoriesCode = in.readString();
        orgonizationCode = in.readString();
        orgonizationName = in.readString();
        awardCode = in.readString();
        awardName = in.readString();


        layr1Code = in.readString();
        layr2Code = in.readString();
        layr3Code = in.readString();
        layr3Name = in.readString();
        staffCode = in.readString();
        staffName = in.readString();

        landSizeUnderIrrigation = in.readString();
        irrigationSystemUsed = in.readString();
        fundSupport = in.readString();
        active = in.readString();
        repName = in.readString();
        repPhoneNo = in.readString();
        formation = in.readString();
        typeOfGroup = in.readString();
        status = in.readString();
        projectNo = in.readString();
        projectTitle = in.readString();



    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeString(communityGroupName);
        dest.writeString(communityGroupCode);
        dest.writeString(programShortName);
        dest.writeString(programName);
        dest.writeString(programCode);
        dest.writeString(commuCategoriesShortName);
        dest.writeString(commuCategoriesName);
        dest.writeString(commuCategoriesCode);
        dest.writeString(orgonizationCode);
        dest.writeString(orgonizationName);
        dest.writeString(awardCode);
        dest.writeString(awardName);
        dest.writeString(layr1Code);
        dest.writeString(layr2Code);
        dest.writeString(layr3Code);
        dest.writeString(layr3Name);
        dest.writeString(staffCode);
        dest.writeString(staffName);

       dest.writeString(landSizeUnderIrrigation);
       dest.writeString(irrigationSystemUsed);
       dest.writeString(fundSupport);
       dest.writeString(active);
       dest.writeString(repName);
       dest.writeString(repPhoneNo);
       dest.writeString(formation);
       dest.writeString(typeOfGroup);
       dest.writeString(status);
       dest.writeString(projectNo);
       dest.writeString(projectTitle);


    }

    public String getLandSizeUnderIrrigation() {
        return landSizeUnderIrrigation;
    }

    public void setLandSizeUnderIrrigation(String landSizeUnderIrrigation) {
        this.landSizeUnderIrrigation = landSizeUnderIrrigation;
    }

    public String getIrrigationSystemUsed() {
        return irrigationSystemUsed;
    }

    public void setIrrigationSystemUsed(String irrigationSystemUsed) {
        this.irrigationSystemUsed = irrigationSystemUsed;
    }

    public String getFundSupport() {
        return fundSupport;
    }

    public void setFundSupport(String fundSupport) {
        this.fundSupport = fundSupport;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public String getRepPhoneNo() {
        return repPhoneNo;
    }

    public void setRepPhoneNo(String repPhoneNo) {
        this.repPhoneNo = repPhoneNo;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getTypeOfGroup() {
        return typeOfGroup;
    }

    public void setTypeOfGroup(String typeOfGroup) {
        this.typeOfGroup = typeOfGroup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getLayr3Code() {
        return layr3Code;
    }

    public void setLayr3Code(String layr3Code) {
        this.layr3Code = layr3Code;
    }

    public String getLayr3Name() {
        return layr3Name;
    }

    public void setLayr3Name(String layr3Name) {
        this.layr3Name = layr3Name;
    }

    public String getAwardCode() {
        return awardCode;
    }

    public void setAwardCode(String awardCode) {
        this.awardCode = awardCode;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getOrgonizationCode() {
        return orgonizationCode;
    }

    public void setOrgonizationCode(String orgonizationCode) {
        this.orgonizationCode = orgonizationCode;
    }

    public String getOrgonizationName() {
        return orgonizationName;
    }

    public void setOrgonizationName(String orgonizationName) {
        this.orgonizationName = orgonizationName;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getCommuCategoriesName() {
        return commuCategoriesName;
    }

    public void setCommuCategoriesName(String commuCategoriesName) {
        this.commuCategoriesName = commuCategoriesName;
    }

    public String getCommuCategoriesCode() {
        return commuCategoriesCode;
    }

    public void setCommuCategoriesCode(String commuCategoriesCode) {
        this.commuCategoriesCode = commuCategoriesCode;
    }

    public String getCommunityGroupName() {
        return communityGroupName;
    }

    public void setCommunityGroupName(String communityGroupName) {
        this.communityGroupName = communityGroupName;
    }

    public String getCommunityGroupCode() {
        return communityGroupCode;
    }

    public void setCommunityGroupCode(String communityGroupCode) {
        this.communityGroupCode = communityGroupCode;
    }

    public String getProgramShortName() {
        return programShortName;
    }

    public void setProgramShortName(String programShortName) {
        this.programShortName = programShortName;
    }

    public String getCommuCategoriesShortName() {
        return commuCategoriesShortName;
    }

    public void setCommuCategoriesShortName(String commuCategoriesShortName) {
        this.commuCategoriesShortName = commuCategoriesShortName;
    }
}
