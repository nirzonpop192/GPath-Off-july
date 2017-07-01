package com.siddiquinoor.restclient.views.adapters;

/**
 * Created by Faisal on 10/16/2015.
 */
public class SummaryOfMemberAssignedListModel {
    private String customId;
    private String memberName;
    private String regDate;
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }



    public SummaryOfMemberAssignedListModel() {
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
}
