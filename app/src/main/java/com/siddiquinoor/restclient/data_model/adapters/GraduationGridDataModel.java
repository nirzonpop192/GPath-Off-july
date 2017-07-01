package com.siddiquinoor.restclient.data_model.adapters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by USER on 9/30/2015.
 */
public class GraduationGridDataModel implements Parcelable{

    private String hh_id;
    private String member_Id;
    private String member_name;
    private String donor_code;
    private String award_code;
    //private String awardStr;
    private String award_name;

    private String countryCode;                // country code
    private String districtCode;         // from RegNAssignProgSrv
    private String upazillaCode;            // from RegNAssignProgSrv
    private String unitCode;              // from RegNAssignProgSrv
    private String villageCode;          // from RegNAssignProgSrv


    private String program_code;        // from RegNAssignProgSrv
    private String program_name;        // from RegNAssignProgSrv
    private String  service_code;         // from RegNAssignProgSrv


    private  String graduationDate;
    private  String graduationTitle; // reasons
    private String criteria_name;


    private String villageName;
    /**
     * 15 digit memid
     */
    private  String nMemId;
    private GraduationGridDataModel mGraduationGridDataModel;

    public static final Parcelable.Creator<GraduationGridDataModel> CREATOR
            = new Parcelable.Creator<GraduationGridDataModel>(){
        public GraduationGridDataModel createFromParcel(Parcel in){
            return  new GraduationGridDataModel(in);

        }

        public GraduationGridDataModel[] newArray(int size){
            return new GraduationGridDataModel[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hh_id);
        dest.writeString(member_Id);
        dest.writeString(member_name);
        dest.writeString(donor_code);
        dest.writeString(award_code);
        dest.writeString(award_name);
        dest.writeString(countryCode);
        dest.writeString(districtCode);
        dest.writeString(upazillaCode);
        dest.writeString(unitCode);
        dest.writeString(villageCode);
        dest.writeString(program_code);
        dest.writeString(program_name);
        dest.writeString(service_code);
        dest.writeString(graduationDate);
        dest.writeString(graduationTitle);
        dest.writeString(criteria_name);
        dest.writeString(villageName);
        dest.writeString(nMemId);



    }

    public GraduationGridDataModel() {
    }

    private GraduationGridDataModel(Parcel in) {
        readFromParcel(in);
    }

    public GraduationGridDataModel(GraduationGridDataModel mGraduationGridDataModel) {
        this.mGraduationGridDataModel = mGraduationGridDataModel;
    }

    public void readFromParcel(Parcel in) {
        hh_id=in.readString();
        member_Id=in.readString();
        member_name=in.readString();
        donor_code=in.readString();
        award_code=in.readString();
        award_name=in.readString();
        countryCode=in.readString();
        districtCode=in.readString();
        upazillaCode=in.readString();
        unitCode=in.readString();
        villageCode=in.readString();
        program_code=in.readString();
        program_name=in.readString();
        service_code=in.readString();
        graduationDate=in.readString();
        graduationTitle=in.readString();
        criteria_name =in.readString();
       // awardStr=in.readString();
        villageName=in.readString();
        nMemId=in.readString();

    }

    public String getnMemId() {
        return nMemId;
    }

    public void setnMemId(String nMemId) {
        this.nMemId = nMemId;
    }

    public String getHh_id() {
        return hh_id;
    }

    public void setHh_id(String hh_id) {
        this.hh_id = hh_id;
    }

    public String getMember_Id() {
        return member_Id;
    }

    public void setMember_Id(String member_Id) {
        this.member_Id = member_Id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getDonor_code() {
        return donor_code;
    }

    public void setDonor_code(String donor_code) {
        this.donor_code = donor_code;
    }

    public String getAward_code() {
        return award_code;
    }

    public void setAward_code(String award_code) {
        this.award_code = award_code;
    }

    public String getAward_name() {
        return award_name;
    }

    public void setAward_name(String award_name) {
        this.award_name = award_name;
    }

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

    public String getProgram_code() {
        return program_code;
    }

    public void setProgram_code(String program_code) {
        this.program_code = program_code;
    }

    public String getProgram_name() {
        return program_name;
    }

    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }

    public String getService_code() {
        return service_code;
    }

    public void setService_code(String service_code) {
        this.service_code = service_code;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getGraduationTitle() {
        return graduationTitle;
    }

    public void setGraduationTitle(String graduationTitle) {
        this.graduationTitle = graduationTitle;
    }

    public String getCriteria_name() {
        return criteria_name;
    }

    public void setCriteria_name(String criteria_name) {
        this.criteria_name = criteria_name;
    }

  /*  public String getAwardName() {
        return awardStr;
    }

    public void setAwardName(String awardStr) {
        this.awardStr = awardStr;
    }*/

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public GraduationGridDataModel getmGraduationGridDataModel() {
        return mGraduationGridDataModel;
    }

    public void setmGraduationGridDataModel(GraduationGridDataModel mGraduationGridDataModel) {
        this.mGraduationGridDataModel = mGraduationGridDataModel;
    }
}
