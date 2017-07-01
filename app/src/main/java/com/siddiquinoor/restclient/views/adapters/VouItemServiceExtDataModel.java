package com.siddiquinoor.restclient.views.adapters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Faisal on 5/3/2016.
 * Voucher item & service Extended
 */
public class VouItemServiceExtDataModel implements Parcelable {
    private String itemName;
    private  boolean  checkBox;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public VouItemServiceExtDataModel createFromParcel(Parcel in) {
            return new VouItemServiceExtDataModel(in);
        }

        public VouItemServiceExtDataModel[] newArray(int size) {
            return new VouItemServiceExtDataModel[size];
        }
    };

    public VouItemServiceExtDataModel(Parcel in) {
        readFromParcel(in);
    }
    // default
    public VouItemServiceExtDataModel() {

    }

    public String getMeasurments() {
        return measurments;
    }

    public void setMeasurments(String measurments) {
        this.measurments = measurments;
    }

    private void readFromParcel(Parcel in) {



        hh_name = in.readString();
        hh_mm_id = in.readString();
        hh_mm_name = in.readString();
        c_code = in.readString();
        districtCode = in.readString();
        upazillaCode = in.readString();
        unitCode = in.readString();
        villageCode = in.readString();
        HHID = in.readString();

        donor_code = in.readString();
        award_code = in.readString();
        program_code = in.readString();
        service_code = in.readString();

        opCode = in.readString();
        opMontheCode = in.readString();

        voItmSpec= in.readString();
        voItmUnit= in.readString();
        voRefNumber= in.readString();
        entryBy= in.readString();
        entryDate= in.readString();


        itemName= in.readString();
        voItemCost= in.readString();
//        checkBox= in.readString();


    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {



        dest.writeString(hh_name);
        dest.writeString(hh_mm_id);
        dest.writeString(hh_mm_name);

        dest.writeString(c_code);
        dest.writeString(donor_code);
        dest.writeString(award_code);
        dest.writeString(districtCode);
        dest.writeString(upazillaCode);
        dest.writeString(unitCode);
        dest.writeString(villageCode);
        dest.writeString(HHID);

        // dest.writeString(MEMID);
        dest.writeString(program_code);
        dest.writeString(service_code);

        dest.writeString(opCode);
        dest.writeString(opMontheCode);

        dest.writeString(voItmSpec);
        dest.writeString(voItmUnit);
        dest.writeString(voRefNumber);
        dest.writeString(entryBy);
        dest.writeString(entryDate);

        dest.writeString(itemName);
        dest.writeString(voItemCost);

        // have to think about some thing
//        dest.writeBool(checkBox);

    }


    private String HHID;
    private String hh_name;
    private String hh_mm_id;        // house hold member id
    private String hh_mm_name;

    private String c_code;                // country code
    private String districtCode;         // from RegNAssignProgSrv
    private String upazillaCode;            // from RegNAssignProgSrv
    private String unitCode;              // from RegNAssignProgSrv
    private String villageCode;          // from RegNAssignProgSrv



    private String donor_code;
    private String award_code;
    private String program_code;        // from RegNAssignProgSrv
    private String service_code;         // from RegNAssignProgSrv

    private String opCode;
    private String opMontheCode;
    private String measurments;



    private String voItmSpec;
    private String voItmUnit;
    private String  voRefNumber;
    private String  voItemCost;

    private String entryBy;
    private String entryDate;




    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }

    public String getHHID() {
        return HHID;
    }

    public void setHHID(String HHID) {
        this.HHID = HHID;
    }

    public String getHh_name() {
        return hh_name;
    }

    public void setHh_name(String hh_name) {
        this.hh_name = hh_name;
    }

    public String getHh_mm_id() {
        return hh_mm_id;
    }

    public void setHh_mm_id(String hh_mm_id) {
        this.hh_mm_id = hh_mm_id;
    }

    public String getHh_mm_name() {
        return hh_mm_name;
    }

    public void setHh_mm_name(String hh_mm_name) {
        this.hh_mm_name = hh_mm_name;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
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

    public String getProgram_code() {
        return program_code;
    }

    public void setProgram_code(String program_code) {
        this.program_code = program_code;
    }

    public String getService_code() {
        return service_code;
    }

    public void setService_code(String service_code) {
        this.service_code = service_code;
    }

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    public String getOpMontheCode() {
        return opMontheCode;
    }

    public void setOpMontheCode(String opMontheCode) {
        this.opMontheCode = opMontheCode;
    }

    public String getVoItmSpec() {
        return voItmSpec;
    }

    public void setVoItmSpec(String voItmSpec) {
        this.voItmSpec = voItmSpec;
    }

    public String getVoItmUnit() {
        return voItmUnit;
    }

    public void setVoItmUnit(String voItmUnit) {
        this.voItmUnit = voItmUnit;
    }

    public String getVoRefNumber() {
        return voRefNumber;
    }

    public void setVoRefNumber(String voRefNumber) {
        this.voRefNumber = voRefNumber;
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

    public String getVoItemCost() {
        return voItemCost;
    }

    public void setVoItemCost(String voItemCost) {
        this.voItemCost = voItemCost;
    }
}
