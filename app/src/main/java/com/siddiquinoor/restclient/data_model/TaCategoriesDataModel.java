package com.siddiquinoor.restclient.data_model;

/**
 * Created by pop on 4/12/2017.
 */

public class TaCategoriesDataModel {
    private String cCode;
    private String taCatCode;
    private String taCatName;
    private String srcBen;

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public String getSrcBen() {
        return srcBen;
    }

    public void setSrcBen(String srcBen) {
        this.srcBen = srcBen;
    }

    public String getTaCatCode() {
        return taCatCode;
    }

    public void setTaCatCode(String taCatCode) {
        this.taCatCode = taCatCode;
    }

    public String getTaCatName() {
        return taCatName;
    }

    public void setTaCatName(String taCatName) {
        this.taCatName = taCatName;
    }
}
