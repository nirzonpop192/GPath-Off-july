package com.siddiquinoor.restclient.data_model;

/**
 * Created by USER on 9/9/2016.
 */
public class Lup_gpsListDataModel {

    private String groupCode;
    private String subGroupCode;
    private String attributeCode;
    private String LupValueCode;
    private String LupValueText;

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getSubGroupCode() {
        return subGroupCode;
    }

    public void setSubGroupCode(String subGroupCode) {
        this.subGroupCode = subGroupCode;
    }

    public String getAttributeCode() {
        return attributeCode;
    }

    public void setAttributeCode(String attributeCode) {
        this.attributeCode = attributeCode;
    }

    public String getLupValueCode() {
        return LupValueCode;
    }

    public void setLupValueCode(String lupValueCode) {
        LupValueCode = lupValueCode;
    }

    public String getLupValueText() {
        return LupValueText;
    }

    public void setLupValueText(String lupValueText) {
        LupValueText = lupValueText;
    }
}
