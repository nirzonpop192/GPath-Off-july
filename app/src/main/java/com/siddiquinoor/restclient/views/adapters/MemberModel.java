package com.siddiquinoor.restclient.views.adapters;
/**
 * This class is a Model of Registration
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.6.0
 * @since 1.0
 *
 */
import java.util.ArrayList;

public class MemberModel {

    private int _id;  // Primary key of Member table
    private int pid;   // Primary key of Household table
    private String c_code;

    private String district;
    private String upazilla;
    private String unit;
    private String village;

    private String districtCode;
    private String upazillaCode;
    private String unitCode;
    private String villageCode;


    private String regID;
    private String regDate;
    private String name;        // house hold name
    private String entryBy;
    private String entryDate;
    private String MemID;
    private String MemberName;
    private String memAge;
    private String gender;
    private String relation;
    private String relation_code;
    private String str_elderly;
    private String str_disabled;

    private String str_lmp_date;
    private String str_child_dob;



    private ArrayList<String> occupation;

    public MemberModel() {
    }

    public MemberModel(String name,String district) {
        this.name = name;
        this.district = district;
    }

    public int getID() {
        return this._id;
    }
    public void setID(int keyId) {
        this._id = keyId;
    }

    public int getPID() {
        return this.pid;
    }
    public void setPID(int pid) {
        this.pid = pid;
    }

    public String getCountryCode()
    { return this.c_code;}

    public void setCountryCode(String country) {
        this.c_code=country;
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

    public String getUnitNameCode(){
        return unitCode;
    }
    public void setUnitNameCode(String unitCode){
        this.unitCode=unitCode;
    }

    public String getVillageCode() {
        return villageCode;
    }
    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }

    public String getUpazilla() {
        return upazilla;
    }
    public void setUpazilla(String upazilla) {
        this.upazilla = upazilla;
    }

    public String getUnitName(){
        return unit;
    }
    public void setUnitName(String unit){
        this.unit=unit;
    }

    public String getVillage() {
        return village;
    }
    public void setVillage(String village) {
        this.village = village;
    }


    public String getRegID() {
        return this.regID;
    }
    public void setRegID(String keyId) {
        this.regID = keyId;
    }

    public String getRegDate() {
        return this.regDate;
    }
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setEntryBy(String entryBy) {
        this.entryBy = entryBy;
    }
    public String getEntryBy() {
        return entryBy;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }
    public String getEntryDate() {
        return entryDate;
    }

    public void setMemID(String MemID) {
        this.MemID = MemID;
    }
    public String getMemID() {
        return MemID;
    }

    public void setMemberName(String MemberName) {
        this.MemberName = MemberName;
    }
    public String getMemberName() {
        return MemberName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }

    public void setMemberAge(String memAge) {
        this.memAge = memAge;
    }
    public String getMemberAge() {
        return memAge;
    }

    public void setRelationCode(String relation_code) {
        this.relation_code = relation_code;
    }
    public String getRelationCode() {
        return relation_code;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
    public String getRelation() {
        return relation;
    }

    public void setElderly(String str_elderly) {
        this.str_elderly = str_elderly;
    }
    public String getElderly() {
        return str_elderly;
    }

    public void setDisabled(String str_disabled) {
        this.str_disabled = str_disabled;
    }
    public String getDisabled() {
        return str_disabled;
    }

    public void setLMPDate(String str_lmp_date) {
        this.str_lmp_date = str_lmp_date;
    }
    public String getLMPDate() {
        return str_lmp_date;
    }

    public void setChildDOB(String str_child_dob) {
        this.str_child_dob = str_child_dob;
    }
    public String getChildDOB() {
        return str_child_dob;
    }
    // End Member //

}
