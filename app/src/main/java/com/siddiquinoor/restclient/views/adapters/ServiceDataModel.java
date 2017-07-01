package com.siddiquinoor.restclient.views.adapters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author  Faisal Mohammad
 * @since  8/19/2015.
 * to pass class object from 1 activity to other activity
 */
public class ServiceDataModel implements Parcelable {


    /**
     * Default Construct tor
     */

    public ServiceDataModel() {
    }

    private String hh_name;      // house hold id


    private String hh_mm_name;


    private String getSrvMemCount;
    private String c_code;
    private String donor_code;
    private String award_code;
    private String awardName;

    private String districtCode;
    private String upazillaCode;
    private String unitCode;
    private String villageCode;
    private String HHID;
    private String memberId;        // house hold member id
    private String criteriaName;
    private String criteriaId;



    private String program_code;
    private String service_code;
    private String newID;
    private String opCode;

    private String opMonthCode;
    private String ServiceSLCode;
    private String ServiceCenterCode;

    private String FPDCode;
    private String ServiceDTCode;
    private String ServiceStatusCode;

    private String workingDay;


    private String opMonthStr;
    private String villageName;

    private boolean checkBox;

    private String temServiceCenterName;
    private String temServiceDate;
    private String temStrSrvMonth;
    private String temIdServiceMonth;

    private String temIdGroup;
    private String temStrGroup;

    private String temIdGroupCat;
    private String temStrGroupCat;
    private String distFlag;




    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public ServiceDataModel createFromParcel(Parcel in) {
            return new ServiceDataModel(in);
        }

        public ServiceDataModel[] newArray(int size) {
            return new ServiceDataModel[size];
        }
    };

    public ServiceDataModel(Parcel in) {
        readFromParcel(in);
    }

    /**
     *
     * @param in  the Parcel Object
     */

    private void readFromParcel(Parcel in) {

        c_code = in.readString();
        donor_code = in.readString();
        award_code = in.readString();
        districtCode = in.readString();
        upazillaCode = in.readString();
        unitCode = in.readString();
        villageCode = in.readString();
        HHID = in.readString();
        memberId = in.readString();

        hh_name = in.readString();
        hh_mm_name = in.readString();
        getSrvMemCount = in.readString();

        program_code = in.readString();
        service_code = in.readString();
        newID = in.readString();
        opCode = in.readString();
        opMonthCode = in.readString();
        ServiceSLCode = in.readString();
        ServiceCenterCode = in.readString();

        FPDCode = in.readString();
        ServiceDTCode = in.readString();
        ServiceStatusCode = in.readString();
        workingDay = in.readString();

        criteriaId = in.readString();
        criteriaName = in.readString();
        awardName = in.readString();

        opMonthStr = in.readString();
        villageName = in.readString();

        temServiceCenterName = in.readString();
        temServiceDate = in.readString();
        temStrSrvMonth = in.readString();
        temIdServiceMonth = in.readString();

        temIdGroup = in.readString();
        temStrGroup = in.readString();
        temIdGroupCat = in.readString();
        temStrGroupCat = in.readString();

        distFlag = in.readString();




    }


    @Override
    public int describeContents() {
        return 0;
    }

    /**
     *
     * @param dest destination
     * @param flags flag of some thing
     */

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeString(c_code);
        dest.writeString(donor_code);
        dest.writeString(award_code);
        dest.writeString(districtCode);
        dest.writeString(upazillaCode);
        dest.writeString(unitCode);
        dest.writeString(villageCode);
        dest.writeString(HHID);
        dest.writeString(memberId);

        dest.writeString(hh_name);
        dest.writeString(hh_mm_name);
        dest.writeString(getSrvMemCount);

        dest.writeString(program_code);
        dest.writeString(service_code);
        dest.writeString(newID);
        dest.writeString(opCode);
        dest.writeString(opMonthCode);
        dest.writeString(ServiceSLCode);
        dest.writeString(ServiceCenterCode);

        dest.writeString(FPDCode);
        dest.writeString(ServiceDTCode);
        dest.writeString(ServiceStatusCode);
        dest.writeString(workingDay);

        dest.writeString(criteriaId);
        dest.writeString(criteriaName);
        dest.writeString(awardName);

        dest.writeString(opMonthStr);
        dest.writeString(villageName);

        dest.writeString(temServiceCenterName);
        dest.writeString(temServiceDate);
        dest.writeString(temStrSrvMonth);
        dest.writeString(temIdServiceMonth);


        dest.writeString(temIdGroup);
        dest.writeString(temStrGroup);
        dest.writeString(temIdGroupCat);
        dest.writeString(temStrGroupCat);

        dest.writeString(distFlag);




    }

    public String getDistFlag() {
        return distFlag;
    }

    public void setDistFlag(String distFlag) {
        this.distFlag = distFlag;
    }

    public String getTemIdGroup() {
        return temIdGroup;
    }

    public void setTemIdGroup(String temIdGroup) {
        this.temIdGroup = temIdGroup;
    }

    public String getTemStrGroup() {
        return temStrGroup;
    }

    public void setTemStrGroup(String temStrGroup) {
        this.temStrGroup = temStrGroup;
    }

    public String getTemIdGroupCat() {
        return temIdGroupCat;
    }

    public void setTemIdGroupCat(String temIdGroupCat) {
        this.temIdGroupCat = temIdGroupCat;
    }

    public String getTemStrGroupCat() {
        return temStrGroupCat;
    }

    public void setTemStrGroupCat(String temStrGroupCat) {
        this.temStrGroupCat = temStrGroupCat;
    }

    public String getOpMonthCode() {
        return opMonthCode;
    }

    public void setOpMonthCode(String opMonthCode) {
        this.opMonthCode = opMonthCode;
    }

    /**
     *
     * @return working day
     */

    public String getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(String workingDay) {
        this.workingDay = workingDay;
    }

    /**
     *
     * @return  get the FPD Code
     */

    public String getFPDCode() {
        return FPDCode;
    }

    /**
     *
     * @param FPDCode Food Distribution Point  Code
     */

    public void setFPDCode(String FPDCode) {
        this.FPDCode = FPDCode;
    }

    public String getTemStrSrvMonth() {
        return temStrSrvMonth;
    }

    public void setTemStrSrvMonth(String temStrSrvMonth) {
        this.temStrSrvMonth = temStrSrvMonth;
    }

    public String getTemIdServiceMonth() {
        return temIdServiceMonth;
    }

    public void setTemIdServiceMonth(String temIdServiceMonth) {
        this.temIdServiceMonth = temIdServiceMonth;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }


    public String getServiceCenterCode() {
        return ServiceCenterCode;
    }

    public void setServiceCenterCode(String serviceCenterCode) {
        ServiceCenterCode = serviceCenterCode;
    }

    public String getTemServiceDate() {
        return temServiceDate;
    }

    public void setTemServiceDate(String temServiceDate) {
        this.temServiceDate = temServiceDate;
    }

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    public String getOpMontheCode() {
        return opMonthCode;
    }

    public void setOpMontheCode(String opMontheCode) {
        this.opMonthCode = opMontheCode;
    }

    public String getServiceSLCode() {
        return ServiceSLCode;
    }

    public void setServiceSLCode(String serviceSLCode) {
        ServiceSLCode = serviceSLCode;
    }

    public String getServiceDTCode() {
        return ServiceDTCode;
    }

    public void setServiceDTCode(String serviceDTCode) {
        ServiceDTCode = serviceDTCode;
    }

    public String getServiceStatusCode() {
        return ServiceStatusCode;
    }

    public void setServiceStatusCode(String serviceStatusCode) {
        ServiceStatusCode = serviceStatusCode;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }


    public String getHh_name() {
        return hh_name;
    }

    public void setHh_name(String hh_name) {
        this.hh_name = hh_name;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getHh_mm_name() {
        return hh_mm_name;
    }

    public void setHh_mm_name(String hh_mm_name) {
        this.hh_mm_name = hh_mm_name;
    }


    public String getGetSrvMemCount() {
        return getSrvMemCount;
    }

    public void setGetSrvMemCount(String getSrvMemCount) {
        this.getSrvMemCount = getSrvMemCount;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
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

    public String getHHID() {
        return HHID;
    }

    public void setHHID(String HHID) {
        this.HHID = HHID;
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

    public String getNewID() {
        return newID;
    }

    /**
     *
     * @param newID is the 15 digit member id  of member
     */

    public void setNewID(String newID) {
        this.newID = newID;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteria) {
        criteriaName = criteria;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getOpMonthStr() {
        return opMonthStr;
    }

    public void setOpMonthStr(String opMonthStr) {
        this.opMonthStr = opMonthStr;
    }

    public String getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(String criteriaId) {
        this.criteriaId = criteriaId;
    }

    public String getTemServiceCenterName() {
        return temServiceCenterName;
    }

    public void setTemServiceCenterName(String temServiceCenterName) {
        this.temServiceCenterName = temServiceCenterName;
    }
}
