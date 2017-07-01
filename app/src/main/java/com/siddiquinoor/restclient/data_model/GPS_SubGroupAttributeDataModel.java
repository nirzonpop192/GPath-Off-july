package com.siddiquinoor.restclient.data_model;

/**
 * Created by Faisal on 6/22/2016.
 */
public class GPS_SubGroupAttributeDataModel  extends PrimaryDataModel{
    private String attributeCode;
    private String attributeTitle;
    private String dataType;
    private String lookUpCode;
    private String groupCode;
    private String subGroupCode;

    public String getAttributeCode() {
        return attributeCode;
    }

    public void setAttributeCode(String attributeCode) {
        this.attributeCode = attributeCode;
    }

    public String getAttributeTitle() {
        return attributeTitle;
    }

    public void setAttributeTitle(String attributeTitle) {
        this.attributeTitle = attributeTitle;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getLookUpCode() {
        return lookUpCode;
    }

    public void setLookUpCode(String lookUpCode) {
        this.lookUpCode = lookUpCode;
    }

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
}
