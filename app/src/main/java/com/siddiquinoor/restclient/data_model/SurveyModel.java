package com.siddiquinoor.restclient.data_model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Zahidul_Islam_George on 12-December-2016.
 */
public class SurveyModel implements Parcelable {
    private int surveyNum;
    private ArrayList<DTSurveyTableDataModel> dtSurveyTableDataModels;

    protected SurveyModel(Parcel in) {
        surveyNum = in.readInt();
        dtSurveyTableDataModels = in.createTypedArrayList(DTSurveyTableDataModel.CREATOR);
    }

    public SurveyModel() {
    }

    public static final Creator<SurveyModel> CREATOR = new Creator<SurveyModel>() {
        @Override
        public SurveyModel createFromParcel(Parcel in) {
            return new SurveyModel(in);
        }

        @Override
        public SurveyModel[] newArray(int size) {
            return new SurveyModel[size];
        }
    };

    public ArrayList<DTSurveyTableDataModel> getDtSurveyTableDataModels() {
        return dtSurveyTableDataModels;
    }

    public void setDtSurveyTableDataModels(ArrayList<DTSurveyTableDataModel> dtSurveyTableDataModels) {
        this.dtSurveyTableDataModels = dtSurveyTableDataModels;
    }

    public int getSurveyNum() {
        return surveyNum;
    }

    public void setSurveyNum(int surveyNum) {
        this.surveyNum = surveyNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(surveyNum);
        dest.writeTypedList(dtSurveyTableDataModels);
    }
}
