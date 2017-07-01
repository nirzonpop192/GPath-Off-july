package com.siddiquinoor.restclient.data_model.adapters;

/**
 * Created by pop on 4/9/2017.
 */

public class TA_ParticipientAttendenceDateTimeDataModel {
    private String date;
    private boolean amSession;
    private boolean pmSession;
    private boolean checked;

    public boolean isAmSession() {
        return amSession;
    }

    public void setAmSession(boolean amSession) {
        this.amSession = amSession;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPmSession() {
        return pmSession;
    }

    public void setPmSession(boolean pmSession) {
        this.pmSession = pmSession;
    }
}
