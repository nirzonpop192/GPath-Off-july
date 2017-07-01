package com.siddiquinoor.restclient.data_model.adapters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Faisal
 * @since 8/28/2015.
 */


public class AssignDataModel implements Parcelable {


    private String cCode;
    /**
     * country code
     */
    private String donorCode;
    private String awardCode;

    private String districtCode;
    private String upazillaCode;
    private String unitCode;
    private String villageCode;
    private String villageName;
    private String addressName;

    private String hh_id;
    private String memId; // house hold member id

    private String newId;
    /**
     * 15 digit member id
     */


    private String hh_name; // house hold id

    private String hh_mm_name;

    private String prgCode;
    /**
     * Program Code
     */
    private String srvCode;


    private String assign_criteria;
    private String assignYN;
    /**
     * assign Yes No
     */

    private String regNDate;
    /**
     * Registration Date
     */

    private String dobDate;
    /**
     * Date of Birth
     */
    private String lmpDate;
    private String lmDate;


    private String grdCode;
    private String grdDate;


    private String member_sex;
    private String member_age;
    private String hh_relation;


    private String entryBy;
    private String entryDate;

    private String temAwardString;
    private String temProgramString;
    private String temCriteriaString;
    private String temVillageString;

    private String groupCode;
    private String groupName;
    private String groupCatCode;
    private String groupCatName;
    private String activeCode;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public AssignDataModel createFromParcel(Parcel in) {
            return new AssignDataModel(in);
        }

        @Override
        public AssignDataModel[] newArray(int size) {
            return new AssignDataModel[size];
        }
    };

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

    public String getGroupCatCode() {
        return groupCatCode;
    }

    public void setGroupCatCode(String groupCatCode) {
        this.groupCatCode = groupCatCode;
    }

    public String getGroupCatName() {
        return groupCatName;
    }

    public void setGroupCatName(String groupCatName) {
        this.groupCatName = groupCatName;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public AssignDataModel(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {


        cCode = in.readString();
        donorCode = in.readString();
        awardCode = in.readString();
        districtCode = in.readString();
        upazillaCode = in.readString();
        unitCode = in.readString();
        villageCode = in.readString();
        villageName = in.readString();
        addressName = in.readString();
        hh_id = in.readString();
        memId = in.readString();
        newId = in.readString();
        hh_name = in.readString();
        hh_mm_name = in.readString();
        prgCode = in.readString();
        srvCode = in.readString();
        assign_criteria = in.readString();
        assignYN = in.readString();
        regNDate = in.readString();
        dobDate = in.readString();
        lmpDate = in.readString();
        lmDate = in.readString();
        grdCode = in.readString();
        grdDate = in.readString();
        member_sex = in.readString();
        member_age = in.readString();
        hh_relation = in.readString();
        temAwardString = in.readString();
        temProgramString = in.readString();
        temCriteriaString = in.readString();
        temVillageString = in.readString();

        groupCode = in.readString();
        groupName = in.readString();
        groupCatCode = in.readString();
        groupCatName = in.readString();
        activeCode = in.readString();




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
        dest.writeString(districtCode);
        dest.writeString(upazillaCode);
        dest.writeString(unitCode);
        dest.writeString(villageCode);
        dest.writeString(villageName);
        dest.writeString(addressName);
        dest.writeString(hh_id);
        dest.writeString(memId);
        dest.writeString(newId);
        dest.writeString(hh_name);
        dest.writeString(hh_mm_name);
        dest.writeString(prgCode);
        dest.writeString(srvCode);
        dest.writeString(assign_criteria);
        dest.writeString(assignYN);
        dest.writeString(regNDate);
        dest.writeString(dobDate);
        dest.writeString(lmpDate);
        dest.writeString(lmDate);
        dest.writeString(grdCode);
        dest.writeString(grdDate);
        dest.writeString(member_sex);
        dest.writeString(member_age);
        dest.writeString(hh_relation);
        dest.writeString(temAwardString);
        dest.writeString(temProgramString);
        dest.writeString(temCriteriaString);
        dest.writeString(temVillageString);

        dest.writeString(groupCode);
        dest.writeString(groupName);
        dest.writeString(groupCatCode);
        dest.writeString(groupCatName);
        dest.writeString(activeCode);




    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public AssignDataModel() {
    }

    public String getLmDate() {
        return lmDate;
    }

    public void setLmDate(String lmDate) {
        this.lmDate = lmDate;
    }

    public String getHh_id() {
        return hh_id;
    }

    public void setHh_id(String hh_id) {
        this.hh_id = hh_id;
    }

    public String getHh_name() {
        return hh_name;
    }

    public void setHh_name(String hh_name) {
        this.hh_name = hh_name;
    }


    /**
     * @return Criterial
     */
    public String getAssign_criteria() {
        return assign_criteria;
    }

    public void setAssign_criteria(String assign_criteria) {
        this.assign_criteria = assign_criteria;
    }


    public String getService_code() {
        return srvCode;
    }

    public void setService_code(String service_code) {
        this.srvCode = service_code;
    }


    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getHh_mm_name() {
        return hh_mm_name;
    }

    public void setHh_mm_name(String hh_mm_name) {
        this.hh_mm_name = hh_mm_name;
    }

    public String getMember_sex() {
        return member_sex;
    }

    public void setMember_sex(String member_sex) {
        this.member_sex = member_sex;
    }

    public String getMember_age() {
        return member_age;
    }

    public void setMember_age(String member_age) {
        this.member_age = member_age;
    }

    public String getHh_relation() {
        return hh_relation;
    }

    public void setHh_relation(String hh_relation) {
        this.hh_relation = hh_relation;
    }

    public String getAssignYN() {
        return assignYN;
    }

    public void setAssignYN(String assignYN) {
        this.assignYN = assignYN;
    }

    public String getCountryCode() {
        return cCode;
    }

    public void setC_code(String c_code) {
        this.cCode = c_code;
    }

    public String getDonor_code() {
        return donorCode;
    }

    public void setDonor_code(String donor_code) {
        this.donorCode = donor_code;
    }

    public String getAward_code() {
        return awardCode;
    }

    public void setAward_code(String award_code) {
        this.awardCode = award_code;
    }

    public String getProgram_code() {
        return prgCode;
    }

    public void setProgram_code(String program_code) {
        this.prgCode = program_code;
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

    public String getRegNDate() {
        return regNDate;
    }

    public void setRegNDate(String regNDate) {
        this.regNDate = regNDate;
    }

    public String getLmpDate() {
        return lmpDate;
    }

    public void setLmpDate(String lmpDate) {
        this.lmpDate = lmpDate;
    }

    public String getDobDate() {
        return dobDate;
    }

    public void setDobDate(String dobDate) {
        this.dobDate = dobDate;
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

    public String getGrdCode() {
        return grdCode;
    }

    public void setGrdCode(String grdCode) {
        this.grdCode = grdCode;
    }

    public String getGrdDate() {
        return grdDate;
    }

    public void setGrdDate(String grdDate) {
        this.grdDate = grdDate;
    }

    public String getTemAwardString() {
        return temAwardString;
    }

    public void setTemAwardString(String temAwardString) {
        this.temAwardString = temAwardString;
    }

    public String getTemProgramString() {
        return temProgramString;
    }

    public void setTemProgramString(String temProgramString) {
        this.temProgramString = temProgramString;
    }

    public String getTemCriteriaString() {
        return temCriteriaString;
    }

    public void setTemCriteriaString(String temCriteriaString) {
        this.temCriteriaString = temCriteriaString;
    }

    public String getTemVillageString() {
        return temVillageString;
    }

    public void setTemVillageString(String temVillageString) {
        this.temVillageString = temVillageString;
    }

    /**
     * Created by USER on 9/26/2016.
     */
    public static class DynamicDataIndexDataModel implements Parcelable {
        private String dtTittle;
        private String dtBasicCode;
        private String awardCode;
        private String awardName;
        private String programCode;
        private String programName;
        private String prgActivityTitle;
        private String cCode;
        private String opMode;
        private String donorCode;
        private String OpMonthCode;
        private String OpMonthDate;
        private String programActivityCode;
        private String dtShortName;

        public DynamicDataIndexDataModel() {
        }

        public static final Creator CREATOR = new Creator() {
            public DynamicDataIndexDataModel createFromParcel(Parcel in) {
                return new DynamicDataIndexDataModel(in);
            }

            public DynamicDataIndexDataModel[] newArray(int size) {
                return new DynamicDataIndexDataModel[size];
            }
        };

        public DynamicDataIndexDataModel(Parcel in) {
            readFromParcel(in);
        }


        private void readFromParcel(Parcel in) {
            dtTittle = in.readString();
            dtBasicCode = in.readString();
            awardCode = in.readString();
            awardName = in.readString();
            programCode = in.readString();
            programName = in.readString();
            prgActivityTitle = in.readString();
            cCode = in.readString();
            opMode = in.readString();
            donorCode = in.readString();
            OpMonthCode = in.readString();
            OpMonthDate = in.readString();
            programActivityCode = in.readString();
            dtShortName = in.readString();

        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(dtTittle);
            dest.writeString(dtBasicCode);
            dest.writeString(awardCode);
            dest.writeString(awardName);
            dest.writeString(programCode);
            dest.writeString(programName);
            dest.writeString(prgActivityTitle);
            dest.writeString(cCode);
            dest.writeString(opMode);
            dest.writeString(donorCode);
            dest.writeString(OpMonthCode);
            dest.writeString(OpMonthDate);
            dest.writeString(programActivityCode);
            dest.writeString(dtShortName);
        }

        public String getProgramActivityCode() {
            return programActivityCode;
        }

        public void setProgramActivityCode(String programActivityCode) {
            this.programActivityCode = programActivityCode;
        }

        public String getOpMonthDate() {
            return OpMonthDate;
        }

        public void setOpMonthDate(String opMonthDate) {
            OpMonthDate = opMonthDate;
        }

        public String getOpMonthCode() {
            return OpMonthCode;
        }

        public void setOpMonthCode(String opMonthCode) {
            OpMonthCode = opMonthCode;
        }

        public String getDonorCode() {
            return donorCode;
        }

        public void setDonorCode(String donorCode) {
            this.donorCode = donorCode;
        }

        public String getOpMode() {
            return opMode;
        }

        public void setOpMode(String opMode) {
            this.opMode = opMode;
        }

        public String getcCode() {
            return cCode;
        }

        public void setcCode(String cCode) {
            this.cCode = cCode;
        }

        public String getDtShortName() {
            return dtShortName;
        }

        public void setDtShortName(String dtShortName) {
            this.dtShortName = dtShortName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public String getDtTittle() {
            return dtTittle;
        }

        public void setDtTittle(String dtTittle) {
            this.dtTittle = dtTittle;
        }

        public String getDtBasicCode() {
            return dtBasicCode;
        }

        public void setDtBasicCode(String dtBasicCode) {
            this.dtBasicCode = dtBasicCode;
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

        public String getProgramCode() {
            return programCode;
        }

        public void setProgramCode(String programCode) {
            this.programCode = programCode;
        }

        public String getProgramName() {
            return programName;
        }

        public void setProgramName(String programName) {
            this.programName = programName;
        }

        public String getPrgActivityTitle() {
            return prgActivityTitle;
        }

        public void setPrgActivityTitle(String prgActivityTitle) {
            this.prgActivityTitle = prgActivityTitle;
        }
    }
}
