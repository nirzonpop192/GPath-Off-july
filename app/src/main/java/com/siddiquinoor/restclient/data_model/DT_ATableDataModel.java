package com.siddiquinoor.restclient.data_model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Faisal on 10/1/2016.
 */
public class DT_ATableDataModel implements Parcelable {

    private String DTBasic;
    private String dt_QCode;
    private String dt_ACode;
    private String dt_ALabel;
    private String dt_AValue;
    private String dt_Seq;
    private String dt_AShort;
    private String dt_ScoreCode;
    private String dt_SkipDTQCode;
    private String dt_ACompareCode;
    private String ShowHide;
    private String maxValue;
    private String minValue;
    private String dataType;
    private String markOnGrid;
    private String entryBy;
    private String entryDate;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public DT_ATableDataModel createFromParcel(Parcel in) {
            return new DT_ATableDataModel(in);
        }

        @Override
        public DT_ATableDataModel[] newArray(int size) {
            return new DT_ATableDataModel[size];
        }
    };

    public DT_ATableDataModel(String DTBasic, String dt_QCode, String dt_ACode, String dt_ALabel, String dt_AValue, String dt_Seq, String dt_AShort, String dt_ScoreCode, String dt_SkipDTQCode, String dt_ACompareCode, String showHide, String maxValue, String minValue, String dataType, String markOnGrid) {
        this.DTBasic = DTBasic;
        this.dt_QCode = dt_QCode;
        this.dt_ACode = dt_ACode;
        this.dt_ALabel = dt_ALabel;
        this.dt_AValue = dt_AValue;
        this.dt_Seq = dt_Seq;
        this.dt_AShort = dt_AShort;
        this.dt_ScoreCode = dt_ScoreCode;
        this.dt_SkipDTQCode = dt_SkipDTQCode;
        this.dt_ACompareCode = dt_ACompareCode;
        ShowHide = showHide;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.dataType = dataType;
        this.markOnGrid = markOnGrid;
    }

    /**
     * Constructor
     */

    public DT_ATableDataModel() {
    }

    public DT_ATableDataModel(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {

        DTBasic = in.readString();
        dt_QCode = in.readString();
        dt_ACode = in.readString();
        dt_ALabel = in.readString();
        dt_AValue = in.readString();
        dt_Seq = in.readString();
        dt_AShort = in.readString();
        dt_ScoreCode = in.readString();
        dt_SkipDTQCode = in.readString();
        dt_ACompareCode = in.readString();
        ShowHide = in.readString();
        maxValue = in.readString();
        minValue = in.readString();
        dataType = in.readString();
        markOnGrid = in.readString();
        entryBy = in.readString();
        entryDate = in.readString();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(DTBasic);
        dest.writeString(dt_QCode);
        dest.writeString(dt_ACode);
        dest.writeString(dt_ALabel);
        dest.writeString(dt_AValue);
        dest.writeString(dt_Seq);
        dest.writeString(dt_AShort);
        dest.writeString(dt_ScoreCode);
        dest.writeString(dt_SkipDTQCode);
        dest.writeString(dt_ACompareCode);
        dest.writeString(ShowHide);
        dest.writeString(maxValue);
        dest.writeString(minValue);
        dest.writeString(dataType);
        dest.writeString(markOnGrid);
        dest.writeString(entryBy);
        dest.writeString(entryDate);

    }

    public String getDTBasic() {
        return DTBasic;
    }

    public void setDTBasic(String DTBasic) {
        this.DTBasic = DTBasic;
    }

    public String getDt_QCode() {
        return dt_QCode;
    }

    public void setDt_QCode(String dt_QCode) {
        this.dt_QCode = dt_QCode;
    }

    public String getDt_ACode() {
        return dt_ACode;
    }

    public void setDt_ACode(String dt_ACode) {
        this.dt_ACode = dt_ACode;
    }

    public String getDt_ALabel() {
        return dt_ALabel;
    }

    public void setDt_ALabel(String dt_ALabel) {
        this.dt_ALabel = dt_ALabel;
    }

    public String getDt_AValue() {
        return dt_AValue;
    }

    public void setDt_AValue(String dt_AValue) {
        this.dt_AValue = dt_AValue;
    }

    public String getDt_Seq() {
        return dt_Seq;
    }

    public void setDt_Seq(String dt_Seq) {
        this.dt_Seq = dt_Seq;
    }

    public String getDt_AShort() {
        return dt_AShort;
    }

    public void setDt_AShort(String dt_AShort) {
        this.dt_AShort = dt_AShort;
    }

    public String getDt_ScoreCode() {
        return dt_ScoreCode;
    }

    public void setDt_ScoreCode(String dt_ScoreCode) {
        this.dt_ScoreCode = dt_ScoreCode;
    }

    public String getDt_SkipDTQCode() {
        return dt_SkipDTQCode;
    }

    public void setDt_SkipDTQCode(String dt_SkipDTQCode) {
        this.dt_SkipDTQCode = dt_SkipDTQCode;
    }

    public String getDt_ACompareCode() {
        return dt_ACompareCode;
    }

    public void setDt_ACompareCode(String dt_ACompareCode) {
        this.dt_ACompareCode = dt_ACompareCode;
    }

    public String getShowHide() {
        return ShowHide;
    }

    public void setShowHide(String showHide) {
        ShowHide = showHide;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getMarkOnGrid() {
        return markOnGrid;
    }

    public void setMarkOnGrid(String markOnGrid) {
        this.markOnGrid = markOnGrid;
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

    @Override
    public int describeContents() {
        return 0;
    }
}
