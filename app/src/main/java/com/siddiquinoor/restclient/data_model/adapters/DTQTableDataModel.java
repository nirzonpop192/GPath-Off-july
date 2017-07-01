package com.siddiquinoor.restclient.data_model.adapters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by USER on 9/27/2016.
 *
 * DTQTableDataModel
 */
public class DTQTableDataModel implements Parcelable {
    private String dtBasicCode;
    private String dtQCode;
    private String qText;
    private String qResModeCode;
    private String qSeq;
    private String allowNullFlag;
    private String lup_TableName;

    // to pass the class object
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public DTQTableDataModel createFromParcel(Parcel in) {
            return new DTQTableDataModel(in);
        }

        @Override
        public DTQTableDataModel[] newArray(int size) {
            return new DTQTableDataModel[size];
        }
    };

    public DTQTableDataModel() {
    }

    public DTQTableDataModel(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {

        dtBasicCode = in.readString();
        dtQCode = in.readString();
        qText = in.readString();
        qResModeCode = in.readString();
        qSeq = in.readString();
        allowNullFlag = in.readString();
        lup_TableName = in.readString();


    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(dtBasicCode);
        dest.writeString(dtQCode);
        dest.writeString(qText);
        dest.writeString(qResModeCode);
        dest.writeString(qSeq);
        dest.writeString(allowNullFlag);
        dest.writeString(lup_TableName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getLup_TableName() {
        return lup_TableName;
    }

    public void setLup_TableName(String lup_TableName) {
        this.lup_TableName = lup_TableName;
    }

    public String getDtBasicCode() {
        return dtBasicCode;
    }

    public void setDtBasicCode(String dtBasicCode) {
        this.dtBasicCode = dtBasicCode;
    }

    public String getDtQCode() {
        return dtQCode;
    }

    public void setDtQCode(String dtQCode) {
        this.dtQCode = dtQCode;
    }

    public String getqText() {
        return qText;
    }

    public void setqText(String qText) {
        this.qText = qText;
    }

    public String getqResModeCode() {
        return qResModeCode;
    }

    public void setqResModeCode(String qResModeCode) {
        this.qResModeCode = qResModeCode;
    }

    public String getqSeq() {
        return qSeq;
    }

    public void setqSeq(String qSeq) {
        this.qSeq = qSeq;
    }

    public String getAllowNullFlag() {
        return allowNullFlag;
    }

    public void setAllowNullFlag(String allowNullFlag) {
        this.allowNullFlag = allowNullFlag;
    }
}
