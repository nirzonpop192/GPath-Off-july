package com.siddiquinoor.restclient.data_model;

/**
 * Created by pop on 4/10/2017.
 */

public class TAPosParticipants {
    private String cCode;
    private String posCode;
    private String posTitle;

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getPosTitle() {
        return posTitle;
    }

    public void setPosTitle(String posTitle) {
        this.posTitle = posTitle;
    }
}
