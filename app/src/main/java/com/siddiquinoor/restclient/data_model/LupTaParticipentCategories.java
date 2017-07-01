package com.siddiquinoor.restclient.data_model;

/**
 * Created by pop on 4/10/2017.
 */

public class LupTaParticipentCategories {
    private String cCode;
    private String taGroup;
    private String participentCategoriesCode;
    private String participentCategoriesName;

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public String getParticipentCategoriesCode() {
        return participentCategoriesCode;
    }

    public void setParticipentCategoriesCode(String participentCategoriesCode) {
        this.participentCategoriesCode = participentCategoriesCode;
    }

    public String getParticipentCategoriesName() {
        return participentCategoriesName;
    }

    public void setParticipentCategoriesName(String participentCategoriesName) {
        this.participentCategoriesName = participentCategoriesName;
    }

    public String getTaGroup() {
        return taGroup;
    }

    public void setTaGroup(String taGroup) {
        this.taGroup = taGroup;
    }
}
