package com.siddiquinoor.restclient.data_model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Faisal Mohammad on 1/24/2016.
 */
public class RegN_MM_libDataModel implements Parcelable {

    private String hh_id;
    private String hh_name;      // house hold id
    private String hh_mm_id;        // house hold member id
    private String member_Id;
    private String hh_mm_name;


    private String c_code;                // country code
    private String donor_code;
    private String award_code;
    private String awardStr;

    private String districtCode;         // from RegNAssignProgSrv
    private String upazillaCode;            // from RegNAssignProgSrv
    private String unitCode;              // from RegNAssignProgSrv
    private String villageCode;          // from RegNAssignProgSrv
    private String criteriaStr;
    private String criteriaId;

    private String str_hhID;
    private String str_hhName;

    private String str_c_code;
    private String str_districtName;
    private String str_upazillaName;
    private String str_unionName;
    private String str_villageName;


    private String str_entry_by;
    private String str_entry_date;
    private String str_regDate;
    private String str_memOtherID;

    private String codeMartial;
    private String codeIDType;
    private String codeIDTypeForProxy;
    private String codeBscMem1Title;
    private String codeBscMem2Title;
    private String codeProxyBscMem1Title;
    private String codeProxyBscMem2Title;
    private String codeDesignatedProxy;

    private String str_MemNameFirst;
    private String str_MemNameMiddle;
    private String str_MemNameLast;
    private String str_MemBirthYear;
    private String str_MemContact;
    private String str_MemTypedIDNo;
    private String str_V_bsc_Mem_1_NameFirst;
    private String str_V_bsc_Mem_1_NameMiddle;
    private String str_V_bsc_Mem_1_NameLast;
    private String str_V_bsc_Mem_2_NameFirst;
    private String str_V_bsc_Mem_2_NameMiddle;
    private String str_V_bsc_Mem_2_NameLast;
    private String str_ProxyNameFirst;
    private String str_ProxyNameMiddle;
    private String str_ProxyNameLast;
    private String str_ProxyBirthYear;
    private String str_ProxyTypedIDNo;
    private String str_Proxy_bsc_Mem_1_NameFirst;
    private String str_Proxy_bsc_Mem_1_NameMiddle;
    private String str_Proxy_bsc_Mem_1_NameLast;
    private String str_Proxy_bsc_Mem_2_NameFirst;
    private String str_Proxy_bsc_Mem_2_NameMiddle;
    private String str_Proxy_bsc_Mem_2_NameLast;

    public String getCodeDesignatedProxy() {
        return codeDesignatedProxy;
    }

    public void setCodeDesignatedProxy(String codeDesignatedProxy) {
        this.codeDesignatedProxy = codeDesignatedProxy;
    }

    private byte[]  memberEncodedImage;
    private byte[] ProxyEncodedImage;
    private String strTypeIDNo;

    public String getStrTypeIDNo() {
        return strTypeIDNo;
    }

    public void setStrTypeIDNo(String strTypeIDNo) {
        this.strTypeIDNo = strTypeIDNo;
    }

    public String getStr_memOtherID() {
        return str_memOtherID;
    }

    public void setStr_memOtherID(String str_memOtherID) {
        this.str_memOtherID = str_memOtherID;
    }

    public RegN_MM_libDataModel() {
    }

    public String getStr_MemNameFirst() {
        return str_MemNameFirst;
    }

    public void setStr_MemNameFirst(String str_MemNameFirst) {
        this.str_MemNameFirst = str_MemNameFirst;
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

    public String getHh_mm_id() {
        return hh_mm_id;
    }

    public void setHh_mm_id(String hh_mm_id) {
        this.hh_mm_id = hh_mm_id;
    }

    public String getMember_Id() {
        return member_Id;
    }

    public void setMember_Id(String member_Id) {
        this.member_Id = member_Id;
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

    public String getAwardStr() {
        return awardStr;
    }

    public void setAwardStr(String awardStr) {
        this.awardStr = awardStr;
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

    public String getCriteriaStr() {
        return criteriaStr;
    }

    public void setCriteriaStr(String criteriaStr) {
        this.criteriaStr = criteriaStr;
    }

    public String getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(String criteriaId) {
        this.criteriaId = criteriaId;
    }

    public String getStr_hhID() {
        return str_hhID;
    }

    public void setStr_hhID(String str_hhID) {
        this.str_hhID = str_hhID;
    }

    public String getStr_hhName() {
        return str_hhName;
    }

    public void setStr_hhName(String str_hhName) {
        this.str_hhName = str_hhName;
    }

    public String getStr_c_code() {
        return str_c_code;
    }

    public void setStr_c_code(String str_c_code) {
        this.str_c_code = str_c_code;
    }

    public String getStr_districtName() {
        return str_districtName;
    }

    public void setStr_districtName(String str_districtName) {
        this.str_districtName = str_districtName;
    }

    public String getStr_upazillaName() {
        return str_upazillaName;
    }

    public void setStr_upazillaName(String str_upazillaName) {
        this.str_upazillaName = str_upazillaName;
    }

    public String getStr_unionName() {
        return str_unionName;
    }

    public void setStr_unionName(String str_unionName) {
        this.str_unionName = str_unionName;
    }

    public String getStr_villageName() {
        return str_villageName;
    }

    public void setStr_villageName(String str_villageName) {
        this.str_villageName = str_villageName;
    }

    public String getStr_entry_by() {
        return str_entry_by;
    }

    public void setStr_entry_by(String str_entry_by) {
        this.str_entry_by = str_entry_by;
    }

    public String getStr_entry_date() {
        return str_entry_date;
    }

    public void setStr_entry_date(String str_entry_date) {
        this.str_entry_date = str_entry_date;
    }

    public String getStr_regDate() {
        return str_regDate;
    }

    public void setStr_regDate(String str_regDate) {
        this.str_regDate = str_regDate;
    }

    public String getCodeMartial() {
        return codeMartial;
    }

    public void setCodeMartial(String codeMartial) {
        this.codeMartial = codeMartial;
    }

    public String getCodeIDType() {
        return codeIDType;
    }

    public void setCodeIDType(String codeIDType) {
        this.codeIDType = codeIDType;
    }

    public String getCodeIDTypeForProxy() {
        return codeIDTypeForProxy;
    }

    public void setCodeIDTypeForProxy(String codeIDTypeForProxy) {
        this.codeIDTypeForProxy = codeIDTypeForProxy;
    }

    public String getCodeBscMem1Title() {
        return codeBscMem1Title;
    }

    public void setCodeBscMem1Title(String codeBscMem1Title) {
        this.codeBscMem1Title = codeBscMem1Title;
    }

    public String getCodeBscMem2Title() {
        return codeBscMem2Title;
    }

    public void setCodeBscMem2Title(String codeBscMem2Title) {
        this.codeBscMem2Title = codeBscMem2Title;
    }

    public String getCodeProxyBscMem1Title() {
        return codeProxyBscMem1Title;
    }

    public void setCodeProxyBscMem1Title(String codeProxyBscMem1Title) {
        this.codeProxyBscMem1Title = codeProxyBscMem1Title;
    }

    public String getCodeProxyBscMem2Title() {
        return codeProxyBscMem2Title;
    }

    public void setCodeProxyBscMem2Title(String codeProxyBscMem2Title) {
        this.codeProxyBscMem2Title = codeProxyBscMem2Title;
    }

    public String getStr_MemNameMiddle() {
        return str_MemNameMiddle;
    }

    public void setStr_MemNameMiddle(String str_MemNameMiddle) {
        this.str_MemNameMiddle = str_MemNameMiddle;
    }

    public String getStr_MemNameLast() {
        return str_MemNameLast;
    }

    public void setStr_MemNameLast(String str_MemNameLast) {
        this.str_MemNameLast = str_MemNameLast;
    }

    public String getStr_MemBirthYear() {
        return str_MemBirthYear;
    }

    public void setStr_MemBirthYear(String str_MemBirthYear) {
        this.str_MemBirthYear = str_MemBirthYear;
    }

    public String getStr_MemContact() {
        return str_MemContact;
    }

    public void setStr_MemContact(String str_MemContact) {
        this.str_MemContact = str_MemContact;
    }

    public String getStr_MemTypedIDNo() {
        return str_MemTypedIDNo;
    }

    public void setStr_MemTypedIDNo(String str_MemTypedIDNo) {
        this.str_MemTypedIDNo = str_MemTypedIDNo;
    }

    public String getStr_V_bsc_Mem_1_NameFirst() {
        return str_V_bsc_Mem_1_NameFirst;
    }

    public void setStr_V_bsc_Mem_1_NameFirst(String str_V_bsc_Mem_1_NameFirst) {
        this.str_V_bsc_Mem_1_NameFirst = str_V_bsc_Mem_1_NameFirst;
    }

    public String getStr_V_bsc_Mem_1_NameMiddle() {
        return str_V_bsc_Mem_1_NameMiddle;
    }

    public void setStr_V_bsc_Mem_1_NameMiddle(String str_V_bsc_Mem_1_NameMiddle) {
        this.str_V_bsc_Mem_1_NameMiddle = str_V_bsc_Mem_1_NameMiddle;
    }

    public String getStr_V_bsc_Mem_1_NameLast() {
        return str_V_bsc_Mem_1_NameLast;
    }

    public void setStr_V_bsc_Mem_1_NameLast(String str_V_bsc_Mem_1_NameLast) {
        this.str_V_bsc_Mem_1_NameLast = str_V_bsc_Mem_1_NameLast;
    }

    public String getStr_V_bsc_Mem_2_NameFirst() {
        return str_V_bsc_Mem_2_NameFirst;
    }

    public void setStr_V_bsc_Mem_2_NameFirst(String str_V_bsc_Mem_2_NameFirst) {
        this.str_V_bsc_Mem_2_NameFirst = str_V_bsc_Mem_2_NameFirst;
    }

    public String getStr_V_bsc_Mem_2_NameMiddle() {
        return str_V_bsc_Mem_2_NameMiddle;
    }

    public void setStr_V_bsc_Mem_2_NameMiddle(String str_V_bsc_Mem_2_NameMiddle) {
        this.str_V_bsc_Mem_2_NameMiddle = str_V_bsc_Mem_2_NameMiddle;
    }

    public String getStr_V_bsc_Mem_2_NameLast() {
        return str_V_bsc_Mem_2_NameLast;
    }

    public void setStr_V_bsc_Mem_2_NameLast(String str_V_bsc_Mem_2_NameLast) {
        this.str_V_bsc_Mem_2_NameLast = str_V_bsc_Mem_2_NameLast;
    }

    public String getStr_ProxyNameFirst() {
        return str_ProxyNameFirst;
    }

    public void setStr_ProxyNameFirst(String str_ProxyNameFirst) {
        this.str_ProxyNameFirst = str_ProxyNameFirst;
    }

    public String getStr_ProxyNameMiddle() {
        return str_ProxyNameMiddle;
    }

    public void setStr_ProxyNameMiddle(String str_ProxyNameMiddle) {
        this.str_ProxyNameMiddle = str_ProxyNameMiddle;
    }

    public String getStr_ProxyNameLast() {
        return str_ProxyNameLast;
    }

    public void setStr_ProxyNameLast(String str_ProxyNameLast) {
        this.str_ProxyNameLast = str_ProxyNameLast;
    }

    public String getStr_ProxyBirthYear() {
        return str_ProxyBirthYear;
    }

    public void setStr_ProxyBirthYear(String str_ProxyBirthYear) {
        this.str_ProxyBirthYear = str_ProxyBirthYear;
    }

    public String getStr_ProxyTypedIDNo() {
        return str_ProxyTypedIDNo;
    }

    public void setStr_ProxyTypedIDNo(String str_ProxyTypedIDNo) {
        this.str_ProxyTypedIDNo = str_ProxyTypedIDNo;
    }

    public String getStr_Proxy_bsc_Mem_1_NameFirst() {
        return str_Proxy_bsc_Mem_1_NameFirst;
    }

    public void setStr_Proxy_bsc_Mem_1_NameFirst(String str_Proxy_bsc_Mem_1_NameFirst) {
        this.str_Proxy_bsc_Mem_1_NameFirst = str_Proxy_bsc_Mem_1_NameFirst;
    }

    public String getStr_Proxy_bsc_Mem_1_NameMiddle() {
        return str_Proxy_bsc_Mem_1_NameMiddle;
    }

    public void setStr_Proxy_bsc_Mem_1_NameMiddle(String str_Proxy_bsc_Mem_1_NameMiddle) {
        this.str_Proxy_bsc_Mem_1_NameMiddle = str_Proxy_bsc_Mem_1_NameMiddle;
    }

    public String getStr_Proxy_bsc_Mem_1_NameLast() {
        return str_Proxy_bsc_Mem_1_NameLast;
    }

    public void setStr_Proxy_bsc_Mem_1_NameLast(String str_Proxy_bsc_Mem_1_NameLast) {
        this.str_Proxy_bsc_Mem_1_NameLast = str_Proxy_bsc_Mem_1_NameLast;
    }

    public String getStr_Proxy_bsc_Mem_2_NameFirst() {
        return str_Proxy_bsc_Mem_2_NameFirst;
    }

    public void setStr_Proxy_bsc_Mem_2_NameFirst(String str_Proxy_bsc_Mem_2_NameFirst) {
        this.str_Proxy_bsc_Mem_2_NameFirst = str_Proxy_bsc_Mem_2_NameFirst;
    }

    public String getStr_Proxy_bsc_Mem_2_NameMiddle() {
        return str_Proxy_bsc_Mem_2_NameMiddle;
    }

    public void setStr_Proxy_bsc_Mem_2_NameMiddle(String str_Proxy_bsc_Mem_2_NameMiddle) {
        this.str_Proxy_bsc_Mem_2_NameMiddle = str_Proxy_bsc_Mem_2_NameMiddle;
    }

    public String getStr_Proxy_bsc_Mem_2_NameLast() {
        return str_Proxy_bsc_Mem_2_NameLast;
    }

    public void setStr_Proxy_bsc_Mem_2_NameLast(String str_Proxy_bsc_Mem_2_NameLast) {
        this.str_Proxy_bsc_Mem_2_NameLast = str_Proxy_bsc_Mem_2_NameLast;
    }

    public byte[]  getMemberEncodedImage() {
        return memberEncodedImage;
    }

    public void setMemberEncodedImage(byte[] memberEncodedImage) {
        this.memberEncodedImage = memberEncodedImage;
    }

    public byte[] getProxyEncodedImage() {
        return ProxyEncodedImage;
    }

    public void setProxyEncodedImage(byte[]  proxyEncodedImage) {
        ProxyEncodedImage = proxyEncodedImage;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        /**
         * @param in
         * @see :http://www.survivingwithandroid.com/2015/05/android-parcelable-tutorial-list-class.html
         * @description: createFromParcel is an abstruct method*/
        public RegN_MM_libDataModel createFromParcel(Parcel in) {
            return new RegN_MM_libDataModel(in);
        }

        public RegN_MM_libDataModel[] newArray(int size) {
            return new RegN_MM_libDataModel[size];
        }

    };

    public RegN_MM_libDataModel(Parcel in) {
        readFromParcel(in);
    }

    /**
     * @param in de-Parcel the object
     */
    private void readFromParcel(Parcel in) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
      /*  ;
        str_hhName;
        ;
        ;
        ;
        str_unionName;
        str_villageName;

        str_entry_by;
        str_entry_date;
        str_regDate;
        codeMartial;
        codeIDType;
        codeIDTypeForProxy;
        codeBscMem1Title;
        codeBscMem2Title;
        codeProxyBscMem1Title;
        codeProxyBscMem2Title;

        str_V_bsc_Mem_2_NameLast;
        str_ProxyNameFirst;
        str_ProxyNameMiddle;
        str_ProxyNameLast;
        str_ProxyBirthYear;
        str_ProxyTypedIDNo;
        str_Proxy_bsc_Mem_1_NameFirst;
        str_Proxy_bsc_Mem_1_NameMiddle;
        str_Proxy_bsc_Mem_1_NameLast;
        str_Proxy_bsc_Mem_2_NameFirst;
        str_Proxy_bsc_Mem_2_NameMiddle;
        str_Proxy_bsc_Mem_2_NameLast;*/
        dest.writeString(str_c_code);
        dest.writeString(districtCode);
        dest.writeString(str_districtName);
        dest.writeString(upazillaCode);
        dest.writeString(str_upazillaName);
        dest.writeString(unitCode);
        dest.writeString(str_unionName);
        dest.writeString(villageCode);
        dest.writeString(str_villageName);
        dest.writeString(str_hhID);


        dest.writeString(str_MemNameMiddle);
        dest.writeString(str_MemNameLast);
        dest.writeString(str_MemBirthYear);
        dest.writeString(str_MemContact);
        dest.writeString(str_MemTypedIDNo);
        dest.writeString(str_V_bsc_Mem_1_NameFirst);
        dest.writeString(str_V_bsc_Mem_1_NameMiddle);
        dest.writeString(str_V_bsc_Mem_1_NameLast);
        dest.writeString(str_V_bsc_Mem_2_NameFirst);
        dest.writeString(str_V_bsc_Mem_2_NameMiddle);
       /* dest.writeString();
        dest.writeString();
        dest.writeString();
        dest.writeString();
        dest.writeString();
        dest.writeString();*/

    }
}
