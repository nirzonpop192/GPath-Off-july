package com.siddiquinoor.restclient.data_model;

/**
 * Created by pop on 3/15/2017.
 */

public class FreezeDataModel {
    private int index;
    private String DTBasic;
    private String AdmCountryCode;
    private String AdmDonorCode;
    private String AdmAwardCode;
    private String AdmProgCode;
    private String DTEnuID;

    private String DTQCode;
    private String DTQText;
    private String DTACode;
    /**
     * DTRSeq is user input serial no
     */
   private int DTRSeq;
    private String DTAValue;
    private String ProgActivityCode;
    private String DTTimeString;

    private String OpMode;
    private String OpMonthCode;

    private String DataType;


    public FreezeDataModel(String admAwardCode, String admCountryCode, String admDonorCode, String admProgCode, String dataType, String DTACode, String DTAValue, String DTBasic, String DTEnuID, String DTQCode, String DTQText, int DTRSeq, String DTTimeString, int index, String opMode, String opMonthCode, String progActivityCode) {
        AdmAwardCode = admAwardCode;
        AdmCountryCode = admCountryCode;
        AdmDonorCode = admDonorCode;
        AdmProgCode = admProgCode;
        DataType = dataType;
        this.DTACode = DTACode;
        this.DTAValue = DTAValue;
        this.DTBasic = DTBasic;
        this.DTEnuID = DTEnuID;
        this.DTQCode = DTQCode;
        this.DTQText = DTQText;
        this.DTRSeq = DTRSeq;
        this.DTTimeString = DTTimeString;
        this.index = index;
        OpMode = opMode;
        OpMonthCode = opMonthCode;
        ProgActivityCode = progActivityCode;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getAdmAwardCode() {
        return AdmAwardCode;
    }

    public void setAdmAwardCode(String admAwardCode) {
        AdmAwardCode = admAwardCode;
    }

    public String getAdmCountryCode() {
        return AdmCountryCode;
    }

    public void setAdmCountryCode(String admCountryCode) {
        AdmCountryCode = admCountryCode;
    }

    public String getAdmDonorCode() {
        return AdmDonorCode;
    }

    public void setAdmDonorCode(String admDonorCode) {
        AdmDonorCode = admDonorCode;
    }

    public String getAdmProgCode() {
        return AdmProgCode;
    }

    public void setAdmProgCode(String admProgCode) {
        AdmProgCode = admProgCode;
    }

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }

    public String getDTACode() {
        return DTACode;
    }

    public void setDTACode(String DTACode) {
        this.DTACode = DTACode;
    }

    public String getDTAValue() {
        return DTAValue;
    }

    public void setDTAValue(String DTAValue) {
        this.DTAValue = DTAValue;
    }

    public String getDTBasic() {
        return DTBasic;
    }

    public void setDTBasic(String DTBasic) {
        this.DTBasic = DTBasic;
    }

    public String getDTEnuID() {
        return DTEnuID;
    }

    public void setDTEnuID(String DTEnuID) {
        this.DTEnuID = DTEnuID;
    }

    public String getDTQCode() {
        return DTQCode;
    }

    public void setDTQCode(String DTQCode) {
        this.DTQCode = DTQCode;
    }

    public String getDTQText() {
        return DTQText;
    }

    public void setDTQText(String DTQText) {
        this.DTQText = DTQText;
    }

    public int getDTRSeq() {
        return DTRSeq;
    }

    public void setDTRSeq(int DTRSeq) {
        this.DTRSeq = DTRSeq;
    }

    public String getDTTimeString() {
        return DTTimeString;
    }

    public void setDTTimeString(String DTTimeString) {
        this.DTTimeString = DTTimeString;
    }

    public String getOpMode() {
        return OpMode;
    }

    public void setOpMode(String opMode) {
        OpMode = opMode;
    }

    public String getOpMonthCode() {
        return OpMonthCode;
    }

    public void setOpMonthCode(String opMonthCode) {
        OpMonthCode = opMonthCode;
    }

    public String getProgActivityCode() {
        return ProgActivityCode;
    }

    public void setProgActivityCode(String progActivityCode) {
        ProgActivityCode = progActivityCode;
    }
}
