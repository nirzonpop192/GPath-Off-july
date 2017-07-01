package com.siddiquinoor.restclient.data_model.adapters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by USER on 2/7/2016.
 */
public class DistributionGridDataModel implements Parcelable {



    private String c_code;
    private String districtCode;         // from Service Table
    private String upazillaCode;            // from Service Table
    private String unitCode;              // from Service Table
    private String villageCode;          // from Service Table
    private String donorCode;
    private String awardCode;
    private String program_code;        // from Service Table
    private String service_code;         // from Service Table

    private String rpt_id;
    private String rpt_name;

    private String srvMonthCode;
    private String serviceCenter;
    private String serviceShortName;
    private String distOpMonthCode;
    private String fdpCode;
    private String srvOpMonthCode;
    private String distFlag;
    private String nMId;
    //  private String distOpMonthCode;
    private String status;

    private String tempAwardString;
    private String tempProgString;
    private String tempDistTypeId;
    private String tempDistTypeString;
    private String tempsrvMonthName;
    private String tempDistMonthName;
    private String tempUpazillaName; // in liberia & layer2  is District  & in malawi Upazilla
    private String tempFDPName;
    private String wd;

    public DistributionGridDataModel() {
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public DistributionGridDataModel createFromParcel(Parcel in) {
            return new DistributionGridDataModel(in);
        }

        public DistributionGridDataModel[] newArray(int size) {
            return new DistributionGridDataModel[size];
        }
    };

    public DistributionGridDataModel(Parcel in) {
        readFromParcel(in);
    }


    private void readFromParcel(Parcel in) {



        c_code = in.readString();
        districtCode = in.readString();
        upazillaCode = in.readString();
        districtCode = in.readString();
        unitCode = in.readString();
        villageCode = in.readString();
        donorCode = in.readString();
        awardCode = in.readString();
        program_code = in.readString();
        service_code = in.readString();
        districtCode = in.readString();

        rpt_id = in.readString();
        rpt_name = in.readString();

        villageCode = in.readString();

        srvMonthCode = in.readString();
        serviceCenter = in.readString();
        serviceShortName = in.readString();
        distOpMonthCode = in.readString();
        fdpCode = in.readString();
        tempAwardString = in.readString();
        tempProgString = in.readString();
        tempDistTypeId = in.readString();
        tempDistTypeString = in.readString();
        tempsrvMonthName = in.readString();
        tempDistMonthName = in.readString();
        tempUpazillaName = in.readString();
        tempFDPName = in.readString();
        srvOpMonthCode = in.readString();
        distFlag = in.readString();


        nMId = in.readString();
        //  distOpMonthCode = in.readString();
        status = in.readString();

        wd = in.readString();


    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeString(c_code);
        dest.writeString(districtCode);
        dest.writeString(upazillaCode);
        dest.writeString(districtCode);
        dest.writeString(unitCode);
        dest.writeString(villageCode);
        dest.writeString(donorCode);
        dest.writeString(awardCode);
        dest.writeString(program_code);
        dest.writeString(service_code);
        dest.writeString(districtCode);
        dest.writeString(rpt_id);
        dest.writeString(rpt_name);



        dest.writeString(villageCode);

        dest.writeString(srvMonthCode);
        dest.writeString(serviceCenter);
        dest.writeString(serviceShortName);
        dest.writeString(distOpMonthCode);
        dest.writeString(fdpCode);
        dest.writeString(tempAwardString);
        dest.writeString(tempProgString);

        dest.writeString(tempDistTypeId);
        dest.writeString(tempDistTypeString);
        dest.writeString(tempsrvMonthName);
        dest.writeString(tempDistMonthName);
        dest.writeString(tempUpazillaName);
        dest.writeString(tempFDPName);

        dest.writeString(srvOpMonthCode);
        dest.writeString(distFlag);

        dest.writeString(nMId);
        //    dest.writeString(distOpMonthCode);
        dest.writeString(status);
        dest.writeString(wd);


    }


    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getRpt_id() {
        return rpt_id;
    }

    public void setRpt_id(String rpt_id) {
        this.rpt_id = rpt_id;
    }

    public String getRpt_name() {
        return rpt_name;
    }

    public void setRpt_name(String rpt_name) {
        this.rpt_name = rpt_name;
    }

    public String getnMId() {
        return nMId;
    }

    public void setnMId(String nMId) {
        this.nMId = nMId;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSrvOpMonthCode() {
        return srvOpMonthCode;
    }

    public void setSrvOpMonthCode(String srvOpMonthCode) {
        this.srvOpMonthCode = srvOpMonthCode;
    }

    public String getDistFlag() {
        return distFlag;
    }

    public void setDistFlag(String distFlag) {
        this.distFlag = distFlag;
    }

    public String getTempFDPName() {
        return tempFDPName;
    }

    public void setTempFDPName(String tempFDPName) {
        this.tempFDPName = tempFDPName;
    }

    public String getTempUpazillaName() {
        return tempUpazillaName;
    }

    public void setTempUpazillaName(String tempUpazillaName) {
        this.tempUpazillaName = tempUpazillaName;
    }

    public String getTempDistMonthName() {
        return tempDistMonthName;
    }

    public void setTempDistMonthName(String tempDistMonthName) {
        this.tempDistMonthName = tempDistMonthName;
    }




    public String getTempsrvMonthName() {
        return tempsrvMonthName;
    }

    public void setTempsrvMonthName(String tempsrvMonthName) {
        this.tempsrvMonthName = tempsrvMonthName;
    }

    public String getTempDistTypeId() {
        return tempDistTypeId;
    }

    public void setTempDistTypeId(String tempDistTypeId) {
        this.tempDistTypeId = tempDistTypeId;
    }

    public String getTempDistTypeString() {
        return tempDistTypeString;
    }

    public void setTempDistTypeString(String tempDistTypeString) {
        this.tempDistTypeString = tempDistTypeString;
    }

    public String getTempProgString() {
        return tempProgString;
    }

    public void setTempProgString(String tempProgString) {
        this.tempProgString = tempProgString;
    }

    public String getTempAwardString() {
        return tempAwardString;
    }

    public void setTempAwardString(String tempAwardString) {
        this.tempAwardString = tempAwardString;
    }

    public String getDistOpMonthCode() {
        return distOpMonthCode;
    }

    public void setDistOpMonthCode(String distOpMonthCode) {
        this.distOpMonthCode = distOpMonthCode;
    }

    public String getFdpCode() {
        return fdpCode;
    }

    public void setFdpCode(String fdpCode) {
        this.fdpCode = fdpCode;
    }

    public String getDonorCode() {
        return donorCode;
    }

    public void setDonorCode(String donorCode) {
        this.donorCode = donorCode;
    }

    public String getAwardCode() {
        return awardCode;
    }

    public void setAwardCode(String awardCode) {
        this.awardCode = awardCode;
    }

    public String getServiceShortName() {
        return serviceShortName;
    }

    public void setServiceShortName(String serviceShortName) {
        this.serviceShortName = serviceShortName;
    }

    private boolean checkBoxHouseHold;
    private boolean checkBoxMember;

    public boolean isCheckBoxMember() {
        return checkBoxMember;
    }

    public void setCheckBoxMember(boolean checkBoxMember) {
        this.checkBoxMember = checkBoxMember;
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



    public String getSrvMonthCode() {
        return srvMonthCode;
    }

    public void setSrvMonthCode(String srvMonthCode) {
        this.srvMonthCode = srvMonthCode;
    }

    public String getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    public boolean isCheckBoxHouseHold() {
        return checkBoxHouseHold;
    }

    public void setCheckBoxHouseHold(boolean checkBoxHouseHold) {
        this.checkBoxHouseHold = checkBoxHouseHold;
    }
}
