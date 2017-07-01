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
import java.util.Date;

public class ListDataModel {

    private int _id;
    private String c_code;
    private String c_name;

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
    private String redDate;
    private String name;
    private String sex;
    private String hhSize;
    private String latitude;
    private String longitude;
    private String agLand;
    private String vStatus;
    private String mStatus;
    private String entryBy;
    private String entryDate;
    private String vsla_group;
    private String MemID;
    private String MemberName;
    private String gender;
    private String memAge;
    private String relation;
    private String str_PW;
    private String str_CU2;
    private String str_CA2;
    private String addressCode;
    private String addressName;
    private String rank;
   // private String status;


    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    private ArrayList<String> occupation;

    public ListDataModel() {
    }

    public ListDataModel(String name, String thumbnailUrl, String redDate, String district,
                 ArrayList<String> occupation) {
        this.name = name;
        //this.thumbnailUrl = thumbnailUrl;

        this.redDate = redDate;
        this.district = district;
        this.occupation = occupation;
    }

    public int getID() {
        return this._id;
    }
    public void setID(int keyId) {
        this._id = keyId;
    }

    public String getCountryCode()
    { return this.c_code;}

    public void setCountryCode(String country) {
        this.c_code=country;
    }


    public String getCountryName()
    { return this.c_name;}

    public void setCountryName(String country) {
        this.c_name=country;
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

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHhSize() {
        return hhSize;
    }
    public void setHhSize(String hhSize) {
        this.hhSize = hhSize;
    }


    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLatitude() {
        return latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getLongitude() {
        return longitude;
    }

    public void setAgLand(String agLand) {
        this.agLand = agLand;
    }
    public String getAgLand() {
        return agLand;
    }

    public void setvStatus(String vStatus) {
        this.vStatus = vStatus;
    }
    public String getvStatus() {
        return vStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }
    public String getmStatus() {
        return mStatus;
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

    public void setVSLAGroup(String v_group) {
        this.vsla_group = v_group;
    }
    public String getVSLAGroup() {
        return vsla_group;
    }


    // For Member //
/*
    public void setHhID(String HhID) {
        this.HhID = HhID;
    }
    public String getHhID() {
        return HhID;
    }

    public void setHhname(String Hhname) {
        this.Hhname = Hhname;
    }
    public String getHhname() {
        return Hhname;
    }
*/

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

    public void setRelation(String relation) {
        this.relation = relation;
    }
    public String getRelation() {
        return relation;
    }

    public void setPW(String str_PW) {
        this.str_PW = str_PW;
    }
    public String getPW() {
        return str_PW;
    }

    public void setCU2(String str_CU2) {
        this.str_CU2 = str_CU2;
    }
    public String getCU2() {
        return str_CU2;
    }

    public void setCA2(String str_CA2) {
        this.str_CA2 = str_CA2;
    }
    public String getCA2() {
        return str_CA2;
    }

    // End Member //


    public ArrayList<String> getOccupation() {
        return occupation;
    }
    public void setOccupation(ArrayList<String> occupation) {
        this.occupation = occupation;
    }








}
