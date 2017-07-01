package com.siddiquinoor.restclient.manager.sqlsyntax;

import android.util.Log;

import com.siddiquinoor.restclient.data_model.LayRCodes;

/**
 * Created by USER on 1/12/2016.
 */
public class SQLServerSyntaxGenerator {
    private static final String TAG = SQLServerSyntaxGenerator.class.getSimpleName();

    /**
     * Global Variable for All table
     */
    private String admCountryCode;
    private String LayR1ListCode;
    private String LayR2ListCode;
    private String LayR3ListCode;
    private String LayR4ListCode;
    private String HHID;
    private String RegNDate;

    private String entryBy;
    private String entryDate;

    private String MemID;

    private String AdmDonorCode;
    private String AdmAwardCode;
    private String ProgCode;
    private String SrvCode;
    private String OpCode;
    private String OpMonthCode;
    private String GRDCode;
    private String MemTypeFlag;

    public String getMemTypeFlag() {
        return MemTypeFlag;
    }

    public void setMemTypeFlag(String memTypeFlag) {
        memTypeFlag = checkStringNull(memTypeFlag);
        MemTypeFlag = memTypeFlag;
    }

    public String getGRDCode() {
        return GRDCode;
    }

    public void setGRDCode(String GRDCode) {
        GRDCode = checkStringNull(GRDCode);
        this.GRDCode = GRDCode;
    }

    public String getGRDDate() {
        return GRDDate;
    }

    public void setGRDDate(String GRDDate) {
        GRDDate = checkStringNull(GRDDate);
        this.GRDDate = GRDDate;
    }

    private String GRDDate;


    public String getAdmDonorCode() {
        return AdmDonorCode;
    }

    public void setAdmDonorCode(String admDonorCode) {
        admDonorCode = checkStringNull(admDonorCode);
        AdmDonorCode = admDonorCode;
    }

    public String getAdmAwardCode() {
        return AdmAwardCode;
    }

    public void setAdmAwardCode(String admAwardCode) {
        admAwardCode = checkStringNull(admAwardCode);
        AdmAwardCode = admAwardCode;
    }

    public String getProgCode() {
        return ProgCode;
    }

    public void setProgCode(String progCode) {
        progCode = checkStringNull(progCode);
        ProgCode = progCode;
    }

    public String getSrvCode() {
        return SrvCode;
    }

    public void setSrvCode(String srvCode) {
        srvCode = checkStringNull(srvCode);
        SrvCode = srvCode;
    }

    public String getOpCode() {
        return OpCode;
    }

    public void setOpCode(String opCode) {
        opCode = checkStringNull(opCode);
        OpCode = opCode;
    }

    public String getOpMonthCode() {
        return OpMonthCode;
    }

    public void setOpMonthCode(String opMonthCode) {
        opMonthCode = checkStringNull(opMonthCode);
        OpMonthCode = opMonthCode;
    }


    public String getLayR1ListCode() {
        return LayR1ListCode;
    }

    public void setLayR1ListCode(String layR1ListCode) {
        layR1ListCode = checkStringNull(layR1ListCode);
        LayR1ListCode = layR1ListCode;
    }

    public String getLayR2ListCode() {
        return LayR2ListCode;
    }

    public void setLayR2ListCode(String layR2ListCode) {
        layR2ListCode = checkStringNull(layR2ListCode);
        LayR2ListCode = layR2ListCode;
    }

    public String getLayR3ListCode() {
        return LayR3ListCode;
    }

    public void setLayR3ListCode(String layR3ListCode) {
        layR3ListCode = checkStringNull(layR3ListCode);
        LayR3ListCode = layR3ListCode;
    }

    public String getLayR4ListCode() {
        return LayR4ListCode;
    }

    public void setLayR4ListCode(String layR4ListCode) {
        layR4ListCode = checkStringNull(layR4ListCode);
        LayR4ListCode = layR4ListCode;
    }

    public String getHHID() {
        return HHID;
    }

    public void setHHID(String HHID) {
        HHID = checkStringNull(HHID);
        this.HHID = HHID;
    }

    public String getRegNDate() {
        return RegNDate;
    }

    public void setRegNDate(String regNDate) {
        regNDate = checkStringNull(regNDate);
        RegNDate = regNDate;
    }


    public String getMemID() {
        return MemID;
    }

    public void setMemID(String memID) {
        memID = checkStringNull(memID);
        MemID = memID;
    }


    public String getAdmCountryCode() {
        return admCountryCode;
    }

    public void setAdmCountryCode(String admCountryCode) {
        admCountryCode = checkStringNull(admCountryCode);
        this.admCountryCode = admCountryCode;
    }


    public String getEntryBy() {
        return entryBy;
    }

    public void setEntryBy(String entryBy) {
        entryBy = checkStringNull(entryBy);
        this.entryBy = entryBy;
    }

    public String getEntryDate() {

        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        entryDate = checkStringNull(entryDate);
        this.entryDate = entryDate;
    }

    private String checkStringNull(String tem) {

        if (tem != null) {
            if (tem.equals("null") || tem.length() == 0 || tem.equals("")) {
                tem = null;
            } else {
                tem = "'" + tem + "'";
            }

        }
        return tem;


    }

    private String checkIntNull(String tem) {

        if (tem != null) {
            if (tem.equals("null") || tem.length() == 0 || tem.equals("")) {
                tem = null;
            } else {
                tem = " " + tem + " ";
            }

        }
        return tem;


    }

    private String checkIsPhotoURLNull(String tem) {

        if (tem != null) {
            if (tem.equals("null")) {
                tem = null;
            } else {
                tem = "convert(varbinary(max) , '" + tem + "' )";
            }

        }
        return tem;


    }

    /**
     * Bulk Variable for HouseHold table Called '[SrvTable]'
     */
    private String SrvSL;
    private String SrvCenterCode;
    private String SrvDT;
    private String SrvStatus;

    public String getSrvSL() {
        return SrvSL;
    }

    public void setSrvSL(String srvSL) {
        srvSL = checkStringNull(srvSL);
        SrvSL = srvSL;
    }

    public String getSrvCenterCode() {
        return SrvCenterCode;
    }

    public void setSrvCenterCode(String srvCenterCode) {
        srvCenterCode = checkStringNull(srvCenterCode);
        SrvCenterCode = srvCenterCode;
    }

    public String getSrvDT() {
        return SrvDT;
    }

    public void setSrvDT(String srvDT) {
        srvDT = checkStringNull(srvDT);
        SrvDT = srvDT;
    }

    public String getSrvStatus() {
        return SrvStatus;
    }

    public void setSrvStatus(String srvStatus) {
        srvStatus = checkStringNull(srvStatus);
        SrvStatus = srvStatus;
    }

    /**
     * Bulk Variable for HouseHold table Called '[RegNHHTable]'
     */


    private String hhRegNDate;
    private String hhHHHeadName;
    private String hhHHHeadSex;
    private String hhHHSize;
    private String hhGPSLate;
    private String hhGPSLong;
    private String hhAGLand;
    private String hhVStatus;
    private String hhMStatus;
    private String hhVSLAGroup;
    private String hhGPSLongSwap;
    private String hhHHHeadCat;
    private String hhLT2yrsM;
    private String hhLT2yrsF;
    private String hhM2to5yers;
    private String hhF2to5yrs;
    private String hhM6to12yrs;
    private String hhF6to12yrs;
    private String hhM13to17yrs;
    private String hhF13to17yrs;
    private String hhOrphn_LT18yrsM;
    private String hhOrphn_LT18yrsF;
    private String hhAdlt_18to59M;
    private String hhAdlt_18to59F;
    private String hhEld_60pM;
    private String hhEld_60pF;
    private String hhPLW;
    private String hhChronicallyIll;
    private String hhLivingDeceasedContractEbola;
    private String hhExtraChildBecauseEbola;
    private String hhExtraElderlyPersonBecauseEbola;
    private String hhExtraChronicallyIllDisabledPersonBecauseEbola;
    private String hhBRFCntCattle;
    private String hhBRFValCattle;
    private String hhAFTCntCattle;
    private String hhAFTValCattle;
    private String hhBRFCntSheepGoats;
    private String hhBRFValSheepGoats;
    private String hhAFTCntSheepGoats;
    private String hhAFTValSheepGoats;
    private String hhBRFCntPoultry;
    private String hhBRFValPoultry;
    private String hhAFTCntPoultry;
    private String hhAFTValPoultry;
    private String hhBRFCntOther;
    private String hhBRFValOther;
    private String hhAFTCntOther;
    private String hhAFTValOther;
    private String hhBRFAcreCultivable;
    private String hhBRFValCultivable;
    private String hhAFTAcreCultivable;
    private String hhAFTValCultivable;
    private String hhBRFAcreNonCultivable;
    private String hhBRFValNonCultivable;
    private String hhAFTAcreNonCultivable;
    private String hhAFTValNonCultivable;
    private String hhBRFAcreOrchards;
    private String hhBRFValOrchards;
    private String hhAFTAcreOrchards;
    private String hhAFTValOrchards;
    private String hhBRFValCrop;
    private String hhAFTValCrop;
    private String hhBRFValLivestock;
    private String hhAFTValLivestock;
    private String hhBRFValSmallBusiness;
    private String hhAFTValSmallBusiness;
    private String hhBRFValEmployment;
    private String hhAFTValEmployment;
    private String hhBRFValRemittances;
    private String hhAFTValRemittances;
    private String hhBRFCntWageEnr;
    private String hhAFTCntWageEnr;


    public String getHhGPSLate() {
        return hhGPSLate;
    }

    public void setHhGPSLate(String hhGPSLate) {
        hhGPSLate = checkStringNull(hhGPSLate);
        this.hhGPSLate = hhGPSLate;
    }

    public String getHhHHSize() {
        return hhHHSize;
    }

    public void setHhHHSize(String hhHHSize) {
        hhHHSize = checkStringNull(hhHHSize);
        this.hhHHSize = hhHHSize;
    }

    public String getHhHHHeadSex() {
        return hhHHHeadSex;
    }

    public void setHhHHHeadSex(String hhHHHeadSex) {
        hhHHHeadSex = checkStringNull(hhHHHeadSex);
        this.hhHHHeadSex = hhHHHeadSex;
    }

    public String getHhHHHeadName() {
        return hhHHHeadName;
    }

    public void setHhHHHeadName(String hhHHHeadName) {
        hhHHHeadName = checkStringNull(hhHHHeadName);
        this.hhHHHeadName = hhHHHeadName;
    }

    public String getHhRegNDate() {
        return hhRegNDate;
    }

    public void setHhRegNDate(String hhRegNDate) {
        hhRegNDate = checkStringNull(hhRegNDate);
        this.hhRegNDate = hhRegNDate;
    }


    public String getHhGPSLong() {
        return hhGPSLong;
    }

    public void setHhGPSLong(String hhGPSLong) {
        hhGPSLong = checkStringNull(hhGPSLong);
        this.hhGPSLong = hhGPSLong;
    }

    public String getHhAGLand() {
        return hhAGLand;
    }

    public void setHhAGLand(String hhAGLand) {
        hhAGLand = checkStringNull(hhAGLand);
        this.hhAGLand = hhAGLand;
    }

    public String getHhVStatus() {
        return hhVStatus;
    }

    public void setHhVStatus(String hhVStatus) {
        hhVStatus = checkStringNull(hhVStatus);
        this.hhVStatus = hhVStatus;
    }

    public String getHhMStatus() {
        return hhMStatus;
    }

    public void setHhMStatus(String hhMStatus) {
        hhMStatus = checkStringNull(hhMStatus);
        this.hhMStatus = hhMStatus;
    }

    public String getHhVSLAGroup() {
        return hhVSLAGroup;
    }

    public void setHhVSLAGroup(String hhVSLAGroup) {
        hhVSLAGroup = checkStringNull(hhVSLAGroup);
        this.hhVSLAGroup = hhVSLAGroup;
    }

    public String getHhGPSLongSwap() {
        return hhGPSLongSwap;
    }

    public void setHhGPSLongSwap(String hhGPSLongSwap) {
        hhGPSLongSwap = checkStringNull(hhGPSLongSwap);
        this.hhGPSLongSwap = hhGPSLongSwap;
    }

    public String getHhHHHeadCat() {
        return hhHHHeadCat;
    }

    public void setHhHHHeadCat(String hhHHHeadCat) {
        hhHHHeadCat = checkStringNull(hhHHHeadCat);
        this.hhHHHeadCat = hhHHHeadCat;
    }

    public String getHhLT2yrsM() {
        return hhLT2yrsM;
    }

    public void setHhLT2yrsM(String hhLT2yrsM) {
        hhLT2yrsM = checkIntNull(hhLT2yrsM);
        this.hhLT2yrsM = hhLT2yrsM;
    }

    public String getHhLT2yrsF() {
        return hhLT2yrsF;
    }

    public void setHhLT2yrsF(String hhLT2yrsF) {
        hhLT2yrsF = checkIntNull(hhLT2yrsF);
        this.hhLT2yrsF = hhLT2yrsF;
    }

    public String getHhM2to5yers() {
        return hhM2to5yers;
    }

    public void setHhM2to5yers(String hhM2to5yers) {
        hhM2to5yers = checkIntNull(hhM2to5yers);
        this.hhM2to5yers = hhM2to5yers;
    }

    public String getHhF2to5yrs() {
        return hhF2to5yrs;
    }

    public void setHhF2to5yrs(String hhF2to5yrs) {
        hhF2to5yrs = checkIntNull(hhF2to5yrs);
        this.hhF2to5yrs = hhF2to5yrs;
    }

    public String getHhM6to12yrs() {
        return hhM6to12yrs;
    }

    public void setHhM6to12yrs(String hhM6to12yrs) {
        hhM6to12yrs = checkIntNull(hhM6to12yrs);
        this.hhM6to12yrs = hhM6to12yrs;
    }

    public String getHhF6to12yrs() {
        return hhF6to12yrs;
    }

    public void setHhF6to12yrs(String hhF6to12yrs) {
        hhF6to12yrs = checkIntNull(hhF6to12yrs);
        this.hhF6to12yrs = hhF6to12yrs;
    }

    public String getHhM13to17yrs() {
        return hhM13to17yrs;
    }

    public void setHhM13to17yrs(String hhM13to17yrs) {
        hhM13to17yrs = checkIntNull(hhM13to17yrs);
        this.hhM13to17yrs = hhM13to17yrs;
    }

    public String getHhF13to17yrs() {
        return hhF13to17yrs;
    }

    public void setHhF13to17yrs(String hhF13to17yrs) {
        hhF13to17yrs = checkIntNull(hhF13to17yrs);
        this.hhF13to17yrs = hhF13to17yrs;
    }

    public String getHhOrphn_LT18yrsM() {
        return hhOrphn_LT18yrsM;
    }

    public void setHhOrphn_LT18yrsM(String hhOrphn_LT18yrsM) {
        hhOrphn_LT18yrsM = checkIntNull(hhOrphn_LT18yrsM);
        this.hhOrphn_LT18yrsM = hhOrphn_LT18yrsM;
    }

    public String getHhOrphn_LT18yrsF() {
        return hhOrphn_LT18yrsF;
    }

    public void setHhOrphn_LT18yrsF(String hhOrphn_LT18yrsF) {
        hhOrphn_LT18yrsF = checkIntNull(hhOrphn_LT18yrsF);
        this.hhOrphn_LT18yrsF = hhOrphn_LT18yrsF;
    }

    public String getHhAdlt_18to59M() {
        return hhAdlt_18to59M;
    }

    public void setHhAdlt_18to59M(String hhAdlt_18to59M) {
        hhAdlt_18to59M = checkIntNull(hhAdlt_18to59M);
        this.hhAdlt_18to59M = hhAdlt_18to59M;
    }

    public String getHhAdlt_18to59F() {
        return hhAdlt_18to59F;
    }

    public void setHhAdlt_18to59F(String hhAdlt_18to59F) {
        hhAdlt_18to59F = checkIntNull(hhAdlt_18to59F);
        this.hhAdlt_18to59F = hhAdlt_18to59F;
    }

    public String getHhEld_60pM() {
        return hhEld_60pM;
    }

    public void setHhEld_60pM(String hhEld_60pM) {
        hhEld_60pM = checkIntNull(hhEld_60pM);
        this.hhEld_60pM = hhEld_60pM;
    }

    public String getHhEld_60pF() {
        return hhEld_60pF;
    }

    public void setHhEld_60pF(String hhEld_60pF) {
        hhEld_60pF = checkIntNull(hhEld_60pF);
        this.hhEld_60pF = hhEld_60pF;
    }

    public String getHhPLW() {
        return hhPLW;
    }

    public void setHhPLW(String hhPLW) {
        hhPLW = checkIntNull(hhPLW);
        this.hhPLW = hhPLW;
    }

    public String getHhChronicallyIll() {
        return hhChronicallyIll;
    }

    public void setHhChronicallyIll(String hhChronicallyIll) {
        hhChronicallyIll = checkIntNull(hhChronicallyIll);
        this.hhChronicallyIll = hhChronicallyIll;
    }

    public String getHhLivingDeceasedContractEbola() {
        return hhLivingDeceasedContractEbola;
    }

    public void setHhLivingDeceasedContractEbola(String hhLivingDeceasedContractEbola) {
        hhLivingDeceasedContractEbola = checkStringNull(hhLivingDeceasedContractEbola);
        this.hhLivingDeceasedContractEbola = hhLivingDeceasedContractEbola;
    }

    public String getHhExtraChildBecauseEbola() {
        return hhExtraChildBecauseEbola;
    }

    public void setHhExtraChildBecauseEbola(String hhExtraChildBecauseEbola) {
        hhExtraChildBecauseEbola = checkStringNull(hhExtraChildBecauseEbola);
        this.hhExtraChildBecauseEbola = hhExtraChildBecauseEbola;
    }

    public String getHhExtraElderlyPersonBecauseEbola() {
        return hhExtraElderlyPersonBecauseEbola;
    }

    public void setHhExtraElderlyPersonBecauseEbola(String hhExtraElderlyPersonBecauseEbola) {
        hhExtraElderlyPersonBecauseEbola = checkStringNull(hhExtraElderlyPersonBecauseEbola);
        this.hhExtraElderlyPersonBecauseEbola = hhExtraElderlyPersonBecauseEbola;
    }

    public String getHhExtraChronicallyIllDisabledPersonBecauseEbola() {
        return hhExtraChronicallyIllDisabledPersonBecauseEbola;
    }

    public void setHhExtraChronicallyIllDisabledPersonBecauseEbola(String hhExtraChronicallyIllDisabledPersonBecauseEbola) {
        hhExtraChronicallyIllDisabledPersonBecauseEbola = checkStringNull(hhExtraChronicallyIllDisabledPersonBecauseEbola);
        this.hhExtraChronicallyIllDisabledPersonBecauseEbola = hhExtraChronicallyIllDisabledPersonBecauseEbola;
    }

    public String getHhBRFCntCattle() {
        return hhBRFCntCattle;
    }

    public void setHhBRFCntCattle(String hhBRFCntCattle) {
        hhBRFCntCattle = checkIntNull(hhBRFCntCattle);
        this.hhBRFCntCattle = hhBRFCntCattle;
    }

    public String getHhBRFValCattle() {
        return hhBRFValCattle;
    }

    public void setHhBRFValCattle(String hhBRFValCattle) {
        hhBRFValCattle = checkIntNull(hhBRFValCattle);
        this.hhBRFValCattle = hhBRFValCattle;
    }

    public String getHhAFTCntCattle() {
        return hhAFTCntCattle;
    }

    public void setHhAFTCntCattle(String hhAFTCntCattle) {
        hhAFTCntCattle = checkIntNull(hhAFTCntCattle);
        this.hhAFTCntCattle = hhAFTCntCattle;
    }

    public String getHhAFTValCattle() {
        return hhAFTValCattle;
    }

    public void setHhAFTValCattle(String hhAFTValCattle) {
        hhAFTValCattle = checkIntNull(hhAFTValCattle);
        this.hhAFTValCattle = hhAFTValCattle;
    }

    public String getHhBRFCntSheepGoats() {
        return hhBRFCntSheepGoats;
    }

    public void setHhBRFCntSheepGoats(String hhBRFCntSheepGoats) {
        hhBRFCntSheepGoats = checkIntNull(hhBRFCntSheepGoats);
        this.hhBRFCntSheepGoats = hhBRFCntSheepGoats;
    }

    public String getHhBRFValSheepGoats() {
        return hhBRFValSheepGoats;
    }

    public void setHhBRFValSheepGoats(String hhBRFValSheepGoats) {
        hhBRFValSheepGoats = checkIntNull(hhBRFValSheepGoats);
        this.hhBRFValSheepGoats = hhBRFValSheepGoats;
    }

    public String getHhAFTCntSheepGoats() {
        return hhAFTCntSheepGoats;
    }

    public void setHhAFTCntSheepGoats(String hhAFTCntSheepGoats) {
        hhAFTCntSheepGoats = checkIntNull(hhAFTCntSheepGoats);
        this.hhAFTCntSheepGoats = hhAFTCntSheepGoats;
    }

    public String getHhAFTValSheepGoats() {
        return hhAFTValSheepGoats;
    }

    public void setHhAFTValSheepGoats(String hhAFTValSheepGoats) {
        hhAFTValSheepGoats = checkIntNull(hhAFTValSheepGoats);
        this.hhAFTValSheepGoats = hhAFTValSheepGoats;
    }

    public String getHhBRFCntPoultry() {
        return hhBRFCntPoultry;
    }

    public void setHhBRFCntPoultry(String hhBRFCntPoultry) {
        hhBRFCntPoultry = checkIntNull(hhBRFCntPoultry);
        this.hhBRFCntPoultry = hhBRFCntPoultry;
    }

    public String getHhBRFValPoultry() {
        return hhBRFValPoultry;
    }

    public void setHhBRFValPoultry(String hhBRFValPoultry) {
        hhBRFValPoultry = checkIntNull(hhBRFValPoultry);
        this.hhBRFValPoultry = hhBRFValPoultry;
    }

    public String getHhAFTCntPoultry() {
        return hhAFTCntPoultry;
    }

    public void setHhAFTCntPoultry(String hhAFTCntPoultry) {
        hhAFTCntPoultry = checkIntNull(hhAFTCntPoultry);
        this.hhAFTCntPoultry = hhAFTCntPoultry;
    }

    public String getHhAFTValPoultry() {
        return hhAFTValPoultry;
    }

    public void setHhAFTValPoultry(String hhAFTValPoultry) {
        hhAFTValPoultry = checkIntNull(hhAFTValPoultry);
        this.hhAFTValPoultry = hhAFTValPoultry;
    }

    public String getHhBRFCntOther() {
        return hhBRFCntOther;
    }

    public void setHhBRFCntOther(String hhBRFCntOther) {
        hhBRFCntOther = checkIntNull(hhBRFCntOther);
        this.hhBRFCntOther = hhBRFCntOther;
    }

    public String getHhBRFValOther() {
        return hhBRFValOther;
    }

    public void setHhBRFValOther(String hhBRFValOther) {
        hhBRFValOther = checkIntNull(hhBRFValOther);
        this.hhBRFValOther = hhBRFValOther;
    }

    public String getHhAFTCntOther() {
        return hhAFTCntOther;
    }

    public void setHhAFTCntOther(String hhAFTCntOther) {
        hhAFTCntOther = checkIntNull(hhAFTCntOther);
        this.hhAFTCntOther = hhAFTCntOther;
    }

    public String getHhAFTValOther() {
        return hhAFTValOther;
    }

    public void setHhAFTValOther(String hhAFTValOther) {
        hhAFTValOther = checkIntNull(hhAFTValOther);
        this.hhAFTValOther = hhAFTValOther;
    }

    public String getHhBRFAcreCultivable() {
        return hhBRFAcreCultivable;
    }

    public void setHhBRFAcreCultivable(String hhBRFAcreCultivable) {
        hhBRFAcreCultivable = checkIntNull(hhBRFAcreCultivable);
        this.hhBRFAcreCultivable = hhBRFAcreCultivable;
    }

    public String getHhBRFValCultivable() {
        return hhBRFValCultivable;
    }

    public void setHhBRFValCultivable(String hhBRFValCultivable) {
        hhBRFValCultivable = checkIntNull(hhBRFValCultivable);
        this.hhBRFValCultivable = hhBRFValCultivable;
    }

    public String getHhAFTAcreCultivable() {
        return hhAFTAcreCultivable;
    }

    public void setHhAFTAcreCultivable(String hhAFTAcreCultivable) {
        hhAFTAcreCultivable = checkIntNull(hhAFTAcreCultivable);
        this.hhAFTAcreCultivable = hhAFTAcreCultivable;
    }

    public String getHhAFTValCultivable() {
        return hhAFTValCultivable;
    }

    public void setHhAFTValCultivable(String hhAFTValCultivable) {
        hhAFTValCultivable = checkIntNull(hhAFTValCultivable);
        this.hhAFTValCultivable = hhAFTValCultivable;
    }

    public String getHhBRFAcreNonCultivable() {
        return hhBRFAcreNonCultivable;
    }

    public void setHhBRFAcreNonCultivable(String hhBRFAcreNonCultivable) {
        hhBRFAcreNonCultivable = checkIntNull(hhBRFAcreNonCultivable);
        this.hhBRFAcreNonCultivable = hhBRFAcreNonCultivable;
    }

    public String getHhBRFValNonCultivable() {
        return hhBRFValNonCultivable;
    }

    public void setHhBRFValNonCultivable(String hhBRFValNonCultivable) {
        hhBRFValNonCultivable = checkIntNull(hhBRFValNonCultivable);
        this.hhBRFValNonCultivable = hhBRFValNonCultivable;
    }

    public String getHhAFTAcreNonCultivable() {
        return hhAFTAcreNonCultivable;
    }

    public void setHhAFTAcreNonCultivable(String hhAFTAcreNonCultivable) {
        hhAFTAcreNonCultivable = checkIntNull(hhAFTAcreNonCultivable);
        this.hhAFTAcreNonCultivable = hhAFTAcreNonCultivable;
    }

    public String getHhAFTValNonCultivable() {
        return hhAFTValNonCultivable;
    }

    public void setHhAFTValNonCultivable(String hhAFTValNonCultivable) {
        hhAFTValNonCultivable = checkIntNull(hhAFTValNonCultivable);
        this.hhAFTValNonCultivable = hhAFTValNonCultivable;
    }

    public String getHhBRFAcreOrchards() {
        return hhBRFAcreOrchards;
    }

    public void setHhBRFAcreOrchards(String hhBRFAcreOrchards) {
        hhBRFAcreOrchards = checkIntNull(hhBRFAcreOrchards);
        this.hhBRFAcreOrchards = hhBRFAcreOrchards;
    }

    public String getHhBRFValOrchards() {
        return hhBRFValOrchards;
    }

    public void setHhBRFValOrchards(String hhBRFValOrchards) {
        hhBRFValOrchards = checkIntNull(hhBRFValOrchards);
        this.hhBRFValOrchards = hhBRFValOrchards;
    }

    public String getHhAFTAcreOrchards() {
        return hhAFTAcreOrchards;
    }

    public void setHhAFTAcreOrchards(String hhAFTAcreOrchards) {
        hhAFTAcreOrchards = checkIntNull(hhAFTAcreOrchards);
        this.hhAFTAcreOrchards = hhAFTAcreOrchards;
    }

    public String getHhAFTValOrchards() {
        return hhAFTValOrchards;
    }

    public void setHhAFTValOrchards(String hhAFTValOrchards) {
        hhAFTValOrchards = checkIntNull(hhAFTValOrchards);
        this.hhAFTValOrchards = hhAFTValOrchards;
    }

    public String getHhBRFValCrop() {
        return hhBRFValCrop;
    }

    public void setHhBRFValCrop(String hhBRFValCrop) {
        hhBRFValCrop = checkIntNull(hhBRFValCrop);
        this.hhBRFValCrop = hhBRFValCrop;
    }

    public String getHhAFTValCrop() {
        return hhAFTValCrop;
    }

    public void setHhAFTValCrop(String hhAFTValCrop) {
        hhAFTValCrop = checkIntNull(hhAFTValCrop);
        this.hhAFTValCrop = hhAFTValCrop;
    }

    public String getHhBRFValLivestock() {
        return hhBRFValLivestock;
    }

    public void setHhBRFValLivestock(String hhBRFValLivestock) {
        hhBRFValLivestock = checkIntNull(hhBRFValLivestock);
        this.hhBRFValLivestock = hhBRFValLivestock;
    }

    public String getHhAFTValLivestock() {
        return hhAFTValLivestock;
    }

    public void setHhAFTValLivestock(String hhAFTValLivestock) {
        hhAFTValLivestock = checkIntNull(hhAFTValLivestock);
        this.hhAFTValLivestock = hhAFTValLivestock;
    }

    public String getHhBRFValSmallBusiness() {
        return hhBRFValSmallBusiness;
    }

    public void setHhBRFValSmallBusiness(String hhBRFValSmallBusiness) {
        hhBRFValSmallBusiness = checkIntNull(hhBRFValSmallBusiness);
        this.hhBRFValSmallBusiness = hhBRFValSmallBusiness;
    }

    public String getHhAFTValSmallBusiness() {
        return hhAFTValSmallBusiness;
    }

    public void setHhAFTValSmallBusiness(String hhAFTValSmallBusiness) {
        hhAFTValSmallBusiness = checkIntNull(hhAFTValSmallBusiness);
        this.hhAFTValSmallBusiness = hhAFTValSmallBusiness;
    }

    public String getHhBRFValEmployment() {
        return hhBRFValEmployment;
    }

    public void setHhBRFValEmployment(String hhBRFValEmployment) {
        hhBRFValEmployment = checkIntNull(hhBRFValEmployment);
        this.hhBRFValEmployment = hhBRFValEmployment;
    }

    public String getHhAFTValEmployment() {
        return hhAFTValEmployment;
    }

    public void setHhAFTValEmployment(String hhAFTValEmployment) {
        hhAFTValEmployment = checkIntNull(hhAFTValEmployment);
        this.hhAFTValEmployment = hhAFTValEmployment;
    }

    public String getHhBRFValRemittances() {
        return hhBRFValRemittances;
    }

    public void setHhBRFValRemittances(String hhBRFValRemittances) {
        hhBRFValRemittances = checkIntNull(hhBRFValRemittances);
        this.hhBRFValRemittances = hhBRFValRemittances;
    }

    public String getHhAFTValRemittances() {
        return hhAFTValRemittances;
    }

    public void setHhAFTValRemittances(String hhAFTValRemittances) {
        hhAFTValRemittances = checkIntNull(hhAFTValRemittances);
        this.hhAFTValRemittances = hhAFTValRemittances;
    }

    public String getHhBRFCntWageEnr() {
        return hhBRFCntWageEnr;
    }

    public void setHhBRFCntWageEnr(String hhBRFCntWageEnr) {
        hhBRFCntWageEnr = checkIntNull(hhBRFCntWageEnr);
        this.hhBRFCntWageEnr = hhBRFCntWageEnr;
    }

    public String getHhAFTCntWageEnr() {
        return hhAFTCntWageEnr;
    }

    public void setHhAFTCntWageEnr(String hhAFTCntWageEnr) {
        hhAFTCntWageEnr = checkIntNull(hhAFTCntWageEnr);
        this.hhAFTCntWageEnr = hhAFTCntWageEnr;
    }


    /**
     * For RegNAssProgSrv
     */

    private String RegNStatus;
    private String GRDStatus;

    public String getRegNStatus() {
        return RegNStatus;
    }

    public void setRegNStatus(String regNStatus) {
        regNStatus = checkStringNull(regNStatus);
        RegNStatus = regNStatus;
    }

    public String getGRDStatus() {
        return GRDStatus;
    }

    public void setGRDStatus(String GRDStatus) {
        GRDStatus = checkStringNull(GRDStatus);
        this.GRDStatus = GRDStatus;
    }


    private String PWGRDDate;
    private String LMPDate;

    public String getPWGRDDate() {
        return PWGRDDate;
    }

    public void setPWGRDDate(String PWGRDDate) {
        PWGRDDate = checkStringNull(PWGRDDate);
        this.PWGRDDate = PWGRDDate;
    }

    public String getLMPDate() {
        return LMPDate;
    }

    public void setLMPDate(String LMPDate) {
        LMPDate = checkStringNull(LMPDate);
        this.LMPDate = LMPDate;
    }

    /**
     * For RegNLM
     */
    private String LMDOB;
    private String LMGRDDate;

    public String getLMDOB() {
        return LMDOB;
    }

    public void setLMDOB(String LMDOB) {
        LMDOB = checkStringNull(LMDOB);
        this.LMDOB = LMDOB;
    }

    public String getLMGRDDate() {
        return LMGRDDate;
    }

    public void setLMGRDDate(String LMGRDDate) {
        LMGRDDate = checkStringNull(LMGRDDate);
        this.LMGRDDate = LMGRDDate;
    }

    /**
     * For RegN CA2
     */
    private String CA2DOB;
    private String CA2GRDDate;

    public String getCA2DOB() {
        return CA2DOB;
    }

    public void setCA2DOB(String CA2DOB) {
        CA2DOB = checkStringNull(CA2DOB);
        this.CA2DOB = CA2DOB;
    }

    public String getCA2GRDDate() {
        return CA2GRDDate;
    }

    public void setCA2GRDDate(String CA2GRDDate) {
        CA2GRDDate = checkStringNull(CA2GRDDate);
        this.CA2GRDDate = CA2GRDDate;
    }

    public String getCU2DOB() {
        return CU2DOB;
    }

    public void setCU2DOB(String CU2DOB) {
        CU2DOB = checkStringNull(CU2DOB);
        this.CU2DOB = CU2DOB;
    }

    public String getCU2GRDDate() {
        return CU2GRDDate;
    }

    public void setCU2GRDDate(String CU2GRDDate) {
        CU2GRDDate = checkStringNull(CU2GRDDate);
        this.CU2GRDDate = CU2GRDDate;
    }

    /**
     * For RegN CA2
     */
    private String CU2DOB;
    private String CU2GRDDate;

    /**
     * A bulk data coloumn for  Member Table
     */
    private String mmMemName;
    private String mmMemSex;
    private String mmHHRelation;
    private String mmLMPDate;
    private String mmChildDOB;
    private String mmElderly;
    private String mmDisabled;
    private String mmMemAge;
    private String mmBirthYear;
    private String mmMaritalStatus;
    private String mmContactNo;
    private String mmMemOtherID;
    private String mmMemName_First;
    private String mmMemName_Middle;
    private String mmMemName_Last;
    private String mmPhoto;
    private String mmType_ID;
    private String mmID_NO;
    private String mmV_BSCMemName1_First;
    private String mmV_BSCMemName1_Middle;
    private String mmV_BSCMemName1_Last;
    private String mmV_BSCMem1_TitlePosition;
    private String mmV_BSCMemName2_First;
    private String mmV_BSCMemName2_Middle;
    private String mmV_BSCMemName2_Last;
    private String mmV_BSCMem2_TitlePosition;
    private String mmProxy_Designation;
    private String mmProxy_Name_First;
    private String mmProxy_Name_Middle;
    private String mmProxy_Name_Last;
    private String mmProxy_BirthYear;
    private String mmProxy_Photo;
    private String mmProxy_Type_ID;
    private String mmProxy_ID_NO;
    private String mmP_BSCMemName1_First;
    private String mmP_BSCMemName1_Middle;
    private String mmP_BSCMemName1_Last;
    private String mmP_BSCMem1_TitlePosition;
    private String mmP_BSCMemName2_First;
    private String mmP_BSCMemName2_Middle;
    private String mmP_BSCMemName2_Last;
    private String mmP_BSCMem2_TitlePosition;

    public String getMmMemName() {
        return mmMemName;
    }

    public void setMmMemName(String mmMemName) {
        mmMemName = checkStringNull(mmMemName);
        this.mmMemName = mmMemName;
    }

    public String getMmMemSex() {
        return mmMemSex;
    }

    public void setMmMemSex(String mmMemSex) {
        mmMemSex = checkStringNull(mmMemSex);
        this.mmMemSex = mmMemSex;
    }

    public String getMmHHRelation() {
        return mmHHRelation;
    }

    public void setMmHHRelation(String mmHHRelation) {
        mmHHRelation = checkStringNull(mmHHRelation);
        this.mmHHRelation = mmHHRelation;
    }


    public String getMmLMPDate() {
        return mmLMPDate;
    }

    public void setMmLMPDate(String mmLMPDate) {
        mmLMPDate = checkStringNull(mmLMPDate);
        this.mmLMPDate = mmLMPDate;
    }

    public String getMmChildDOB() {
        return mmChildDOB;
    }

    public void setMmChildDOB(String mmChildDOB) {
        mmChildDOB = checkStringNull(mmChildDOB);
        this.mmChildDOB = mmChildDOB;
    }

    public String getMmElderly() {
        return mmElderly;
    }

    public void setMmElderly(String mmElderly) {
        mmElderly = checkStringNull(mmElderly);
        this.mmElderly = mmElderly;
    }

    public String getMmDisabled() {
        return mmDisabled;
    }

    public void setMmDisabled(String mmDisabled) {
        mmDisabled = checkStringNull(mmDisabled);
        this.mmDisabled = mmDisabled;
    }

    public String getMmMemAge() {
        return mmMemAge;
    }

    public void setMmMemAge(String mmMemAge) {
        mmMemAge = checkStringNull(mmMemAge);
        this.mmMemAge = mmMemAge;
    }

    public String getMmBirthYear() {
        return mmBirthYear;
    }

    public void setMmBirthYear(String mmBirthYear) {
        mmBirthYear = checkStringNull(mmBirthYear);
        this.mmBirthYear = mmBirthYear;
    }

    public String getMmMaritalStatus() {
        return mmMaritalStatus;
    }

    public void setMmMaritalStatus(String mmMaritalStatus) {
        mmMaritalStatus = checkStringNull(mmMaritalStatus);
        this.mmMaritalStatus = mmMaritalStatus;
    }

    public String getMmContactNo() {
        return mmContactNo;
    }

    public void setMmContactNo(String mmContactNo) {
        mmContactNo = checkStringNull(mmContactNo);
        this.mmContactNo = mmContactNo;
    }

    public String getMmMemOtherID() {
        return mmMemOtherID;
    }

    public void setMmMemOtherID(String mmMemOtherID) {
        mmMemOtherID = checkStringNull(mmMemOtherID);
        this.mmMemOtherID = mmMemOtherID;
    }

    public String getMmMemName_First() {
        return mmMemName_First;
    }

    public void setMmMemName_First(String mmMemName_First) {
        mmMemName_First = checkStringNull(mmMemName_First);
        this.mmMemName_First = mmMemName_First;
    }

    public String getMmMemName_Middle() {
        return mmMemName_Middle;
    }

    public void setMmMemName_Middle(String mmMemName_Middle) {
        mmMemName_Middle = checkStringNull(mmMemName_Middle);
        this.mmMemName_Middle = mmMemName_Middle;
    }

    public String getMmMemName_Last() {
        return mmMemName_Last;
    }

    public void setMmMemName_Last(String mmMemName_Last) {
        mmMemName_Last = checkStringNull(mmMemName_Last);
        this.mmMemName_Last = mmMemName_Last;
    }

    public String getMmPhoto() {
        return mmPhoto;
    }

    public void setMmPhoto(String mmPhoto) {
        mmPhoto = checkIsPhotoURLNull(mmPhoto);
        //mmPhoto=checkStringNull(mmPhoto);
        this.mmPhoto = mmPhoto;
    }

    public String getMmType_ID() {
        return mmType_ID;
    }

    public void setMmType_ID(String mmType_ID) {
        mmType_ID = checkStringNull(mmType_ID);
        this.mmType_ID = mmType_ID;
    }

    public String getMmID_NO() {
        return mmID_NO;
    }

    public void setMmID_NO(String mmID_NO) {
        mmID_NO = checkStringNull(mmID_NO);
        this.mmID_NO = mmID_NO;
    }

    public String getMmV_BSCMemName1_First() {
        return mmV_BSCMemName1_First;
    }

    public void setMmV_BSCMemName1_First(String mmV_BSCMemName1_First) {
        mmV_BSCMemName1_First = checkStringNull(mmV_BSCMemName1_First);
        this.mmV_BSCMemName1_First = mmV_BSCMemName1_First;
    }

    public String getMmV_BSCMemName1_Middle() {
        return mmV_BSCMemName1_Middle;
    }

    public void setMmV_BSCMemName1_Middle(String mmV_BSCMemName1_Middle) {
        mmV_BSCMemName1_Middle = checkStringNull(mmV_BSCMemName1_Middle);
        this.mmV_BSCMemName1_Middle = mmV_BSCMemName1_Middle;
    }

    public String getMmV_BSCMemName1_Last() {
        return mmV_BSCMemName1_Last;
    }

    public void setMmV_BSCMemName1_Last(String mmV_BSCMemName1_Last) {
        mmV_BSCMemName1_Last = checkStringNull(mmV_BSCMemName1_Last);
        this.mmV_BSCMemName1_Last = mmV_BSCMemName1_Last;
    }

    public String getMmV_BSCMem1_TitlePosition() {
        return mmV_BSCMem1_TitlePosition;
    }

    public void setMmV_BSCMem1_TitlePosition(String mmV_BSCMem1_TitlePosition) {
        mmV_BSCMem1_TitlePosition = checkStringNull(mmV_BSCMem1_TitlePosition);
        this.mmV_BSCMem1_TitlePosition = mmV_BSCMem1_TitlePosition;
    }

    public String getMmV_BSCMemName2_First() {
        return mmV_BSCMemName2_First;
    }

    public void setMmV_BSCMemName2_First(String mmV_BSCMemName2_First) {
        mmV_BSCMemName2_First = checkStringNull(mmV_BSCMemName2_First);
        this.mmV_BSCMemName2_First = mmV_BSCMemName2_First;
    }

    public String getMmV_BSCMemName2_Middle() {
        return mmV_BSCMemName2_Middle;
    }

    public void setMmV_BSCMemName2_Middle(String mmV_BSCMemName2_Middle) {
        mmV_BSCMemName2_Middle = checkStringNull(mmV_BSCMemName2_Middle);
        this.mmV_BSCMemName2_Middle = mmV_BSCMemName2_Middle;
    }

    public String getMmV_BSCMemName2_Last() {
        return mmV_BSCMemName2_Last;
    }

    public void setMmV_BSCMemName2_Last(String mmV_BSCMemName2_Last) {
        mmV_BSCMemName2_Last = checkStringNull(mmV_BSCMemName2_Last);
        this.mmV_BSCMemName2_Last = mmV_BSCMemName2_Last;
    }

    public String getMmV_BSCMem2_TitlePosition() {
        return mmV_BSCMem2_TitlePosition;
    }

    public void setMmV_BSCMem2_TitlePosition(String mmV_BSCMem2_TitlePosition) {
        mmV_BSCMem2_TitlePosition = checkStringNull(mmV_BSCMem2_TitlePosition);
        this.mmV_BSCMem2_TitlePosition = mmV_BSCMem2_TitlePosition;
    }

    public String getMmProxy_Designation() {
        return mmProxy_Designation;
    }

    public void setMmProxy_Designation(String mmProxy_Designation) {
        mmProxy_Designation = checkStringNull(mmProxy_Designation);
        this.mmProxy_Designation = mmProxy_Designation;
    }

    public String getMmProxy_Name_First() {
        return mmProxy_Name_First;
    }

    public void setMmProxy_Name_First(String mmProxy_Name_First) {
        mmProxy_Name_First = checkStringNull(mmProxy_Name_First);
        this.mmProxy_Name_First = mmProxy_Name_First;
    }

    public String getMmProxy_Name_Middle() {
        return mmProxy_Name_Middle;
    }

    public void setMmProxy_Name_Middle(String mmProxy_Name_Middle) {
        mmProxy_Name_Middle = checkStringNull(mmProxy_Name_Middle);
        this.mmProxy_Name_Middle = mmProxy_Name_Middle;
    }

    public String getMmProxy_Name_Last() {
        return mmProxy_Name_Last;
    }

    public void setMmProxy_Name_Last(String mmProxy_Name_Last) {
        mmProxy_Name_Last = checkStringNull(mmProxy_Name_Last);
        this.mmProxy_Name_Last = mmProxy_Name_Last;
    }

    public String getMmProxy_BirthYear() {
        return mmProxy_BirthYear;
    }

    public void setMmProxy_BirthYear(String mmProxy_BirthYear) {
        mmProxy_BirthYear = checkStringNull(mmProxy_BirthYear);
        this.mmProxy_BirthYear = mmProxy_BirthYear;
    }

    public String getMmProxy_Photo() {
        return mmProxy_Photo;
    }

    public void setMmProxy_Photo(String mmProxy_Photo) {
        mmProxy_Photo = checkIsPhotoURLNull(mmProxy_Photo);
        // mmProxy_Photo=checkStringNull(mmProxy_Photo);
        this.mmProxy_Photo = mmProxy_Photo;
    }

    public String getMmProxy_Type_ID() {
        return mmProxy_Type_ID;
    }

    public void setMmProxy_Type_ID(String mmProxy_Type_ID) {
        mmProxy_Type_ID = checkStringNull(mmProxy_Type_ID);
        this.mmProxy_Type_ID = mmProxy_Type_ID;
    }

    public String getMmProxy_ID_NO() {
        return mmProxy_ID_NO;
    }

    public void setMmProxy_ID_NO(String mmProxy_ID_NO) {
        mmProxy_ID_NO = checkStringNull(mmProxy_ID_NO);
        this.mmProxy_ID_NO = mmProxy_ID_NO;
    }

    public String getMmP_BSCMemName1_First() {
        return mmP_BSCMemName1_First;
    }

    public void setMmP_BSCMemName1_First(String mmP_BSCMemName1_First) {
        mmP_BSCMemName1_First = checkStringNull(mmP_BSCMemName1_First);
        this.mmP_BSCMemName1_First = mmP_BSCMemName1_First;
    }

    public String getMmP_BSCMemName1_Middle() {
        return mmP_BSCMemName1_Middle;
    }

    public void setMmP_BSCMemName1_Middle(String mmP_BSCMemName1_Middle) {
        mmP_BSCMemName1_Middle = checkStringNull(mmP_BSCMemName1_Middle);
        this.mmP_BSCMemName1_Middle = mmP_BSCMemName1_Middle;
    }

    public String getMmP_BSCMemName1_Last() {
        return mmP_BSCMemName1_Last;
    }

    public void setMmP_BSCMemName1_Last(String mmP_BSCMemName1_Last) {
        mmP_BSCMemName1_Last = checkStringNull(mmP_BSCMemName1_Last);
        this.mmP_BSCMemName1_Last = mmP_BSCMemName1_Last;
    }

    public String getMmP_BSCMem1_TitlePosition() {
        return mmP_BSCMem1_TitlePosition;
    }

    public void setMmP_BSCMem1_TitlePosition(String mmP_BSCMem1_TitlePosition) {
        mmP_BSCMem1_TitlePosition = checkStringNull(mmP_BSCMem1_TitlePosition);
        this.mmP_BSCMem1_TitlePosition = mmP_BSCMem1_TitlePosition;
    }

    public String getMmP_BSCMemName2_First() {
        return mmP_BSCMemName2_First;
    }

    public void setMmP_BSCMemName2_First(String mmP_BSCMemName2_First) {
        mmP_BSCMemName2_First = checkStringNull(mmP_BSCMemName2_First);
        this.mmP_BSCMemName2_First = mmP_BSCMemName2_First;
    }

    public String getMmP_BSCMemName2_Middle() {
        return mmP_BSCMemName2_Middle;
    }

    public void setMmP_BSCMemName2_Middle(String mmP_BSCMemName2_Middle) {
        mmP_BSCMemName2_Middle = checkStringNull(mmP_BSCMemName2_Middle);
        this.mmP_BSCMemName2_Middle = mmP_BSCMemName2_Middle;
    }

    public String getMmP_BSCMemName2_Last() {
        return mmP_BSCMemName2_Last;
    }

    public void setMmP_BSCMemName2_Last(String mmP_BSCMemName2_Last) {
        mmP_BSCMemName2_Last = checkStringNull(mmP_BSCMemName2_Last);
        this.mmP_BSCMemName2_Last = mmP_BSCMemName2_Last;
    }

    public String getMmP_BSCMem2_TitlePosition() {
        return mmP_BSCMem2_TitlePosition;
    }

    public void setMmP_BSCMem2_TitlePosition(String mmP_BSCMem2_TitlePosition) {
        mmP_BSCMem2_TitlePosition = checkStringNull(mmP_BSCMem2_TitlePosition);
        this.mmP_BSCMem2_TitlePosition = mmP_BSCMem2_TitlePosition;
    }

    /*  NO NEED UNTIL CLIENT WANT TO INSERT THE LOACATION FROM DEVICE/*/

    private String LocationName;


    //geolocation Table

    private String GrpCode;
    private String SubGrpCode;
    private String LocationCode;
    // private String LocationName;
    private String Long;
    private String Latd;


    public String getGrpCode() {
        return GrpCode;
    }

    public void setGrpCode(String grpCode) {
        grpCode = checkStringNull(grpCode);
        this.GrpCode = grpCode;
    }

    public String getSubGrpCode() {
        return SubGrpCode;
    }

    public void setSubGrpCode(String subGrpCode) {
        subGrpCode = checkStringNull(subGrpCode);
        this.SubGrpCode = subGrpCode;
    }

    public String getLocationCode() {
        return LocationCode;
    }

    public void setLocationCode(String locationCode) {
        locationCode = checkStringNull(locationCode);
        this.LocationCode = locationCode;
    }

 /*   public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }*/

    public String getLong() {
        return Long;
    }

    public void setLong(String aLong) {
        aLong = checkStringNull(aLong);
        this.Long = aLong;
    }

    public String getLatd() {
        return Latd;
    }

    public void setLatd(String latd) {

        latd = checkStringNull(latd);
        this.Latd = latd;
    }


    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }


    public String insertIntoGeoLocationTable() {
        return "INSERT INTO [dbo].[GPSLocationTable]" +
                "           ([AdmCountryCode]" +
                "           ,[GrpCode]" +
                "           ,[SubGrpCode]" +
                "           ,[LocationCode]" +
                "           ,[LocationName]" +
                "           ,[Long]" +
                "           ,[Latd]" +
                "           ,[EntryBy]" +
                "           ,[EntryDate])" +
                "     VALUES" + "(" + getAdmCountryCode()
                + " , " + getGrpCode()
                + " , " + getSubGrpCode()
                + " , " + getLocationCode()
                + " , " + getLocationName()
                + " , " + getLong()
                + " , " + getLatd()
                + " , " + getEntryBy()
                + " , " + getEntryDate() +
                ")";

    }

    public String updateGPS_GeoLocationTable() {
        return "UPDATE [dbo].[GPSLocationTable] " +
                "SET [Long] = " + getLong() + " " +
                ",[Latd] = " + getLatd() + " " +
                ",[EntryBy] = " + getEntryBy() + " " +
                ",[EntryDate] = " + getEntryDate() + " " +
                "  WHERE [AdmCountryCode] = " + getAdmCountryCode() + " " +
                " AND [GrpCode] = " + getGrpCode() + " " +
                " AND [SubGrpCode] = " + getSubGrpCode() + " " +
                " AND [LocationCode] = " + getLocationCode();

    }

    private String WD;

    public String getWD() {
        return WD;
    }

    public void setWD(String WD) {
        WD = checkStringNull(WD);
        this.WD = WD;
    }

    public String insertInToSrvTable() {
        return " INSERT INTO [dbo].[SrvTable]" +
                "    ([AdmCountryCode]" +
                "    ,[AdmDonorCode]" +
                "    ,[AdmAwardCode]" +
                "    ,[LayR1ListCode]" +
                "    ,[LayR2ListCode]" +
                "    ,[LayR3ListCode]" +
                "    ,[LayR4ListCode]" +
                "    ,[HHID]" +
                "    ,[MemID]" +
                "    ,[ProgCode]" +
                "    ,[SrvCode]" +
                "    ,[OpCode]" +
                "    ,[OpMonthCode]" +
                "    ,[SrvSL]" +
                "    ,[SrvCenterCode]" +
                "    ,[SrvDT]" +
                "    ,[SrvStatus]" +
                "    ,[FDPCode]" +
                "    ,[WD]" +
                "    ,[DistFlag]" +
                "    ,[EntryBy]" +
                "    ,[EntryDate])" +
                "    VALUES" +
                "            (" + getAdmCountryCode() +
                "    , " + getAdmDonorCode() +
                "    , " + getAdmAwardCode() +
                "    , " + getLayR1ListCode() +
                "    , " + getLayR2ListCode() +
                "    , " + getLayR3ListCode() +
                "    , " + getLayR4ListCode() +
                "    , " + getHHID() +
                "    , " + getMemID() +
                "    , " + getProgCode() +
                "    , " + getSrvCode() +
                "    , " + getOpCode() +
                "    , " + getOpMonthCode() +
                "    , " + getSrvSL() +
                "    , " + getSrvCenterCode() +
                "    , " + getSrvDT() +
                "    , " + getSrvStatus() +
                "    , " + getFDPCode() +
                "    , " + getWD() +
                "    , " + getDistFlag() +
                "    , " + getEntryBy() +
                "    , " + getEntryDate() + " )";

        // return insert;
    }

    public String updateInToSrvTable() {
        return "update  SrvTable " +
                "  set WD =" + getWD() +

                " WHERE AdmCountryCode = " + getAdmCountryCode() +
                " AND AdmDonorCode = " + getAdmDonorCode() +
                " AND AdmAwardCode = " + getAdmAwardCode() +
                " AND LayR1ListCode = " + getLayR1ListCode() +
                " AND LayR2ListCode = " + getLayR2ListCode() +
                " AND LayR3ListCode = " + getLayR3ListCode() +
                " AND LayR4ListCode = " + getLayR4ListCode() +
                " AND HHID = " + getHHID() +
                " AND MemID = " + getMemID() +
                " AND ProgCode = " + getProgCode() +
                " AND SrvCode = " + getSrvCode() +
                " AND OpCode = " + getOpCode() +
                " AND OpMonthCode = " + getOpMonthCode() +
                " AND SrvDT = " + getSrvDT() +
                " AND SrvSL = " + getSrvSL() +
                " AND DistFlag = " + getDistFlag();
    }


    private String previousLayR1ListCode;
    private String previousLayR2ListCode;
    private String previousLayR3ListCode;
    private String previousLayR4ListCode;

    public String getPreviousLayR1ListCode() {
        return previousLayR1ListCode;
    }

    public void setPreviousLayR1ListCode(String previousLayR1ListCode) {
        previousLayR1ListCode = checkStringNull(previousLayR1ListCode);
        this.previousLayR1ListCode = previousLayR1ListCode;
    }

    public String getPreviousLayR2ListCode() {
        return previousLayR2ListCode;
    }

    public void setPreviousLayR2ListCode(String previousLayR2ListCode) {
        previousLayR2ListCode = checkStringNull(previousLayR2ListCode);
        this.previousLayR2ListCode = previousLayR2ListCode;
    }

    public String getPreviousLayR3ListCode() {
        return previousLayR3ListCode;
    }

    public void setPreviousLayR3ListCode(String previousLayR3ListCode) {
        previousLayR3ListCode = checkStringNull(previousLayR3ListCode);
        this.previousLayR3ListCode = previousLayR3ListCode;
    }

    public String getPreviousLayR4ListCode() {
        return previousLayR4ListCode;
    }

    public void setPreviousLayR4ListCode(String previousLayR4ListCode) {
        previousLayR4ListCode = checkStringNull(previousLayR4ListCode);
        this.previousLayR4ListCode = previousLayR4ListCode;
    }

    public String updateRegNHHtableForLiberia() {
        return "UPDATE [dbo].[RegNHHTable] " +
                "SET " +
                "   [LayR1ListCode]   = " + getLayR1ListCode() +
                "  ,[LayR2ListCode]   = " + getLayR2ListCode() +
                "  ,[LayR3ListCode]   = " + getLayR3ListCode() +
                "  ,[LayR4ListCode]   = " + getLayR3ListCode() +
                " ,[RegNDate] = " + getHhRegNDate() +
                " ,[HHHeadName] = " + getHhHHHeadName() +
                " ,[HHHeadSex]      = " + getHhHHHeadSex() +
                " ,[HHSize]         = " + getHhHHSize() +
                " ,[GPSLat]         = " + getHhGPSLate() +
                " ,[GPSLong]        = " + getHhGPSLong() +
                " ,[AGLand]         = " + getHhAGLand() +
                " ,[VStatus]        = " + getHhVStatus() +
                " ,[MStatus]        = " + getHhMStatus() +
                " ,[EntryBy]        = " + getEntryBy() +
                " ,[EntryDate]      = " + getEntryDate() +
                " ,[VSLAGroup]      = " + getHhVSLAGroup() +
                " ,[GPSLongSwap]    = " + getHhGPSLongSwap() +
                " ,[HHHeadCat]      = " + getHhHHHeadCat() +
                " ,[LT2yrsM]        = " + getHhLT2yrsM() +
                " ,[LT2yrsF]        = " + getHhLT2yrsF() +
                " ,[M2to5yrs]       = " + getHhM2to5yers() +
                " ,[F2to5yrs]       = " + getHhF2to5yrs() +
                " ,[M6to12yrs]      = " + getHhM6to12yrs() +
                " ,[F6to12yrs]      = " + getHhF6to12yrs() +
                " ,[M13to17yrs]     = " + getHhM13to17yrs() +
                " ,[F13to17yrs]     = " + getHhF13to17yrs() +
                " ,[Orphn_LT18yrsM] = " + getHhOrphn_LT18yrsM() +
                " ,[Orphn_LT18yrsF] = " + getHhOrphn_LT18yrsF() +
                " ,[Adlt_18to59M]   = " + getHhAdlt_18to59M() +
                " ,[Adlt_18to59F]   = " + getHhAdlt_18to59F() +
                " ,[Eld_60pM]       = " + getHhEld_60pM() +
                " ,[Eld_60pF]       = " + getHhEld_60pF() +
                " ,[PLW]                                            =  " + getHhPLW() +
                " ,[ChronicallyIll]                                 =  " + getHhChronicallyIll() +
                " ,[LivingDeceasedContractEbola]                    =  " + getHhLivingDeceasedContractEbola() +
                " ,[ExtraChildBecauseEbola]                         =  " + getHhExtraChildBecauseEbola() +
                " ,[ExtraElderlyPersonBecauseEbola]                 =  " + getHhExtraElderlyPersonBecauseEbola() +
                " ,[ExtraChronicallyIllDisabledPersonBecauseEbola]  =  " + getHhExtraChronicallyIllDisabledPersonBecauseEbola() +
                " ,[BRFCntCattle]                                   =  " + getHhBRFCntCattle() +
                " ,[BRFValCattle]                                   =  " + getHhBRFValCattle() +
                " ,[AFTCntCattle]                                   =  " + getHhAFTCntCattle() +
                " ,[AFTValCattle]                                   =  " + getHhAFTValCattle() +
                " ,[BRFCntSheepGoats]                               =  " + getHhBRFCntSheepGoats() +
                " ,[BRFValSheepGoats]                               =  " + getHhBRFValSheepGoats() +
                " ,[AFTCntSheepGoats]                               =  " + getHhAFTCntSheepGoats() +
                " ,[AFTValSheepGoats]                               =  " + getHhAFTValSheepGoats() +
                " ,[BRFCntPoultry]                                  =  " + getHhBRFCntPoultry() +
                " ,[BRFValPoultry]                                  =  " + getHhBRFValPoultry() +
                " ,[AFTCntPoultry]                                  =  " + getHhAFTCntPoultry() +
                " ,[AFTValPoultry]                                  =  " + getHhAFTValPoultry() +
                " ,[BRFCntOther]                = " + getHhBRFCntOther() +
                " ,[BRFValOther]                = " + getHhBRFValOther() +
                " ,[AFTCntOther]                = " + getHhAFTCntOther() +
                " ,[AFTValOther]                = " + getHhAFTValOther() +
                " ,[BRFAcreCultivable]          = " + getHhBRFAcreCultivable() +
                " ,[BRFValCultivable]           = " + getHhBRFValCultivable() +
                " ,[AFTAcreCultivable]          = " + getHhAFTAcreCultivable() +
                " ,[AFTValCultivable]           = " + getHhAFTValCultivable() +
                " ,[BRFAcreNonCultivable]       = " + getHhBRFAcreNonCultivable() +
                " ,[BRFValNonCultivable]        = " + getHhBRFValNonCultivable() +
                " ,[AFTAcreNonCultivable]       = " + getHhAFTAcreNonCultivable() +
                " ,[AFTValNonCultivable]        = " + getHhAFTValNonCultivable() +
                " ,[BRFAcreOrchards]            = " + getHhBRFAcreOrchards() +
                " ,[BRFValOrchards]             = " + getHhBRFValOrchards() +
                " ,[AFTAcreOrchards]            = " + getHhAFTAcreOrchards() +
                " ,[AFTValOrchards]             = " + getHhAFTValOrchards() +
                " ,[BRFValCrop]                 = " + getHhBRFValCrop() +
                " ,[AFTValCrop]                 = " + getHhAFTValCrop() +
                " ,[BRFValLivestock]            = " + getHhBRFValLivestock() +
                " ,[AFTValLivestock]            = " + getHhAFTValLivestock() +
                " ,[BRFValSmallBusiness]        = " + getHhBRFValSmallBusiness() +
                " ,[AFTValSmallBusiness]        = " + getHhAFTValSmallBusiness() +
                " ,[BRFValEmployment]           = " + getHhBRFValEmployment() +
                " ,[AFTValEmployment]           = " + getHhAFTValEmployment() +
                " ,[BRFValRemittances]          = " + getHhBRFValRemittances() +
                " ,[AFTValRemittances]          = " + getHhAFTValRemittances() +
                " ,[BRFCntWageEnr]              = " + getHhBRFCntWageEnr() +
                " ,[AFTCntWageEnr]              = " + getHhAFTCntWageEnr() +


                " WHERE[AdmCountryCode] = " + getAdmCountryCode() +
                " AND [LayR1ListCode]   = " + getPreviousLayR1ListCode() +
                " AND [LayR2ListCode]   = " + getPreviousLayR2ListCode() +
                " AND [LayR3ListCode]   = " + getPreviousLayR3ListCode() +
                " AND [LayR4ListCode]   = " + getPreviousLayR4ListCode() +
                " AND [HHID]            = " + getHHID();
    }

    public String insertIntoRegNHHtableForLiberia() {
        return "INSERT INTO [dbo].[RegNHHTable]" +
                "           ([AdmCountryCode]" +
                "           ,[LayR1ListCode]" +
                "           ,[LayR2ListCode]" +
                "           ,[LayR3ListCode]" +
                "           ,[LayR4ListCode]" +
                "           ,[HHID]" +
                "           ,[RegNDate]" +
                "           ,[HHHeadName]" +
                "           ,[HHHeadSex]" +
                "           ,[HHSize]" +
                "           ,[GPSLat]" +
                "           ,[GPSLong]" +
                "           ,[AGLand]" +
                "           ,[VStatus]" +
                "           ,[MStatus]" +
                "           ,[EntryBy]" +
                "           ,[EntryDate]" +
                "           ,[VSLAGroup]" +
                "           ,[GPSLongSwap]" +
                "           ,[HHHeadCat]" +
                "           ,[LT2yrsM]" +
                "           ,[LT2yrsF]" +
                "           ,[M2to5yrs]" +
                "           ,[F2to5yrs]" +
                "           ,[M6to12yrs]" +
                "           ,[F6to12yrs]" +
                "           ,[M13to17yrs]" +
                "           ,[F13to17yrs]" +
                "           ,[Orphn_LT18yrsM]" +
                "           ,[Orphn_LT18yrsF]" +
                "           ,[Adlt_18to59M]" +
                "           ,[Adlt_18to59F]" +
                "           ,[Eld_60pM]" +
                "           ,[Eld_60pF]" +
                "           ,[PLW]" +
                "           ,[ChronicallyIll]" +
                "           ,[LivingDeceasedContractEbola]" +
                "           ,[ExtraChildBecauseEbola]" +
                "           ,[ExtraElderlyPersonBecauseEbola]" +
                "           ,[ExtraChronicallyIllDisabledPersonBecauseEbola]" +
                "           ,[BRFCntCattle]" +
                "           ,[BRFValCattle]" +
                "           ,[AFTCntCattle]" +
                "           ,[AFTValCattle]" +
                "           ,[BRFCntSheepGoats]" +
                "           ,[BRFValSheepGoats]" +
                "           ,[AFTCntSheepGoats]" +
                "           ,[AFTValSheepGoats]" +
                "           ,[BRFCntPoultry]" +
                "           ,[BRFValPoultry]" +
                "           ,[AFTCntPoultry]" +
                "           ,[AFTValPoultry]" +
                "           ,[BRFCntOther]" +
                "           ,[BRFValOther]" +
                "           ,[AFTCntOther]" +
                "           ,[AFTValOther]" +
                "           ,[BRFAcreCultivable]" +
                "           ,[BRFValCultivable]" +
                "           ,[AFTAcreCultivable]" +
                "           ,[AFTValCultivable]" +
                "           ,[BRFAcreNonCultivable]" +
                "           ,[BRFValNonCultivable]" +
                "           ,[AFTAcreNonCultivable]" +
                "           ,[AFTValNonCultivable]" +
                "           ,[BRFAcreOrchards]" +
                "           ,[BRFValOrchards]" +
                "           ,[AFTAcreOrchards]" +
                "           ,[AFTValOrchards]" +
                "           ,[BRFValCrop]" +
                "           ,[AFTValCrop]" +
                "           ,[BRFValLivestock]" +
                "           ,[AFTValLivestock]" +
                "           ,[BRFValSmallBusiness]" +
                "           ,[AFTValSmallBusiness]" +
                "           ,[BRFValEmployment]" +
                "           ,[AFTValEmployment]" +
                "           ,[BRFValRemittances]" +
                "           ,[AFTValRemittances]" +
                "           ,[BRFCntWageEnr]" +
                "           ,[AFTCntWageEnr])" +
                "VALUES " +
                "("
                + getAdmCountryCode()
                + "," + getLayR1ListCode()
                + "," + getLayR2ListCode()
                + "," + getLayR3ListCode()
                + "," + getLayR4ListCode()
                + "," + getHHID()
                + "," + getHhRegNDate()
                + "," + getHhHHHeadName()
                + "," + getHhHHHeadSex()
                + "," + getHhHHSize()
                + "," + getHhGPSLate()
                + "," + getHhGPSLong()
                + "," + getHhAGLand()
                + "," + getHhVStatus()
                + "," + getHhMStatus()
                + "," + getEntryBy()
                + "," + getEntryDate()
                + "," + getHhVSLAGroup()
                + "," + getHhGPSLongSwap()
                + "," + getHhHHHeadCat()
                + "," + getHhLT2yrsM()
                + "," + getHhLT2yrsF()
                + "," + getHhM2to5yers()
                + "," + getHhF2to5yrs()
                + "," + getHhM6to12yrs()
                + "," + getHhF6to12yrs()
                + "," + getHhM13to17yrs()
                + "," + getHhF13to17yrs()
                + "," + getHhOrphn_LT18yrsM()
                + "," + getHhOrphn_LT18yrsF()
                + "," + getHhAdlt_18to59M()
                + "," + getHhAdlt_18to59F()
                + "," + getHhEld_60pM()
                + "," + getHhEld_60pF()
                + "," + getHhPLW()
                + "," + getHhChronicallyIll()
                + "," + getHhLivingDeceasedContractEbola()
                + "," + getHhExtraChildBecauseEbola()
                + "," + getHhExtraElderlyPersonBecauseEbola()
                + "," + getHhExtraChronicallyIllDisabledPersonBecauseEbola()
                + "," + getHhBRFCntCattle()
                + "," + getHhBRFValCattle()
                + "," + getHhAFTCntCattle()
                + "," + getHhAFTValCattle()
                + "," + getHhBRFCntSheepGoats()
                + "," + getHhBRFValSheepGoats()
                + "," + getHhAFTCntSheepGoats()
                + "," + getHhAFTValSheepGoats()
                + "," + getHhBRFCntPoultry()
                + "," + getHhBRFValPoultry()
                + "," + getHhAFTCntPoultry()
                + "," + getHhAFTValPoultry()
                + "," + getHhBRFCntOther()
                + "," + getHhBRFValOther()
                + "," + getHhAFTCntOther()
                + "," + getHhAFTValOther()
                + "," + getHhBRFAcreCultivable()
                + "," + getHhBRFValCultivable()

                + "," + getHhAFTAcreCultivable()
                + "," + getHhAFTValCultivable()
                + "," + getHhBRFAcreNonCultivable()
                + "," + getHhBRFValNonCultivable()
                + "," + getHhAFTAcreNonCultivable()
                + "," + getHhAFTValNonCultivable()
                + "," + getHhBRFAcreOrchards()
                + "," + getHhBRFValOrchards()
                + "," + getHhAFTAcreOrchards()
                + "," + getHhAFTValOrchards()
                + "," + getHhBRFValCrop()
                + "," + getHhAFTValCrop()
                + "," + getHhBRFValLivestock()
                + "," + getHhAFTValLivestock()
                + "," + getHhBRFValSmallBusiness()
                + "," + getHhAFTValSmallBusiness()
                + "," + getHhBRFValEmployment()
                + "," + getHhAFTValEmployment()
                + "," + getHhBRFValRemittances()
                + "," + getHhAFTValRemittances()
                + "," + getHhBRFCntWageEnr()
                + "," + getHhAFTCntWageEnr()
                + ")";
        //return insert;
    }

    private String RegNAddLookupCode;

    public String getRegNAddLookupCode() {
        return RegNAddLookupCode;
    }

    public void setRegNAddLookupCode(String regNAddLookupCode) {
        regNAddLookupCode = checkStringNull(regNAddLookupCode);
        RegNAddLookupCode = regNAddLookupCode;
    }

    private String WRank;

    public String getWRank() {
        return WRank;
    }

    public void setWRank(String WRank) {
        WRank = checkStringNull(WRank);
        this.WRank = WRank;
    }

    /*public String insertIntoRegNHHtableForMalawi() {
        return "INSERT INTO [dbo].[RegNHHTable]" +
                "           ([AdmCountryCode]" +
                "           ,[LayR1ListCode]" +
                "           ,[LayR2ListCode]" +
                "           ,[LayR3ListCode]" +
                "           ,[LayR4ListCode]" +
                "           ,[HHID]" +
                "           ,[RegNDate]" +
                "           ,[HHHeadName]" +
                "           ,[HHHeadSex]" +
                "           ,[HHSize]" +
                "           ,[GPSLat]" +
                "           ,[GPSLong]" +
                "           ,[AGLand]" +
                "           ,[VStatus]" +
                "           ,[MStatus]" +
                "           ,[RegNAddLookupCode]" +
                "           ,[WRank]" +
                "           ,[EntryBy]" +
                "           ,[EntryDate]" +
                "           ,[VSLAGroup])" +
                "VALUES " +
                "("
                + getAdmCountryCode()
                + "," + getLayR1ListCode()
                + "," + getLayR2ListCode()
                + "," + getLayR3ListCode()
                + "," + getLayR4ListCode()
                + "," + getHHID()
                + "," + getHhRegNDate()
                + "," + getHhHHHeadName()
                + "," + getHhHHHeadSex()
                + "," + getHhHHSize()
                + "," + getHhGPSLate()
                + "," + getHhGPSLong()
                + "," + getHhAGLand()
                + "," + getHhVStatus()
                + "," + getHhMStatus()
                + "," + getRegNAddLookupCode()
                + "," + getWRank()
                + "," + getEntryBy()
                + "," + getEntryDate()
                + "," + getHhVSLAGroup()
                + " )";

    }*/

    public String updateRegNHHtableForMalawi() {
        return "DELETE FROM [dbo].[RegNHHTable]" +
                " WHERE " +
                "[AdmCountryCode]     =" + getAdmCountryCode() +
                " AND [LayR1ListCode] =" + getLayR1ListCode() +
                " AND [LayR2ListCode] =" + getLayR2ListCode() +
                " AND [LayR3ListCode] =" + getLayR3ListCode() +
                " AND [LayR4ListCode] =" + getLayR4ListCode() +
                " AND [HHID] = " + getHHID() + " \n" +
                insertIntoRegNHHtableForMalawi();

    }

    public String updateGraduation() {
        return "UPDATE [dbo].[RegNAssignProgSrv]" +
                "    SET     [GRDCode] = " + getGRDCode() +
                "            ,[GRDDate] = " + getGRDDate() +
                "            ,[EntryBy] = " + getEntryBy() +
                "            ,[EntryDate] = " + getEntryDate() +
                "    WHERE [AdmCountryCode] = " + getAdmCountryCode() +
                " AND    [LayR1ListCode]  = " + getLayR1ListCode() +
                " AND   [LayR2ListCode]  = " + getLayR2ListCode() +
                " AND   [LayR3ListCode]  = " + getLayR3ListCode() +
                " AND   [LayR4ListCode]  = " + getLayR4ListCode() +
                " AND   [AdmDonorCode]  = " + getAdmDonorCode() +
                " AND   [AdmAwardCode]  = " + getAdmAwardCode() +
                " AND   [HHID]  = " + getHHID() +
                " AND   [MemID]  = " + getMemID() +
                " AND   [ProgCode]  = " + getProgCode() +
                " AND   [SrvCode]  = " + getSrvCode();
    }

    public String insertIntoRegAssProgSrv() {
        return "INSERT INTO [dbo].[RegNAssignProgSrv]" +
                "        ([AdmCountryCode]" +
                "        ,[LayR1ListCode]" +
                "        ,[LayR2ListCode]" +
                "        ,[LayR3ListCode]" +
                "        ,[LayR4ListCode]" +
                "        ,[AdmDonorCode]" +
                "        ,[AdmAwardCode]" +
                "        ,[HHID]" +
                "        ,[MemID]" +
                "        ,[ProgCode]" +
                "        ,[SrvCode]" +
                "        ,[RegNDate]" +
                "        ,[GRDCode]" +
                "        ,[GRDDate]" +
                "        ,[EntryBy]" +
                "        ,[EntryDate])" +
                "        VALUES " +
                "        ( " + getAdmCountryCode() +
                " , " + getLayR1ListCode() +
                " , " + getLayR2ListCode() +
                " , " + getLayR3ListCode() +
                " , " + getLayR4ListCode() +
                " , " + getAdmDonorCode() +
                " , " + getAdmAwardCode() +
                " , " + getHHID() +
                " , " + getMemID() +
                " , " + getProgCode() +
                " , " + getSrvCode() +
                " , " + getRegNDate() +
                " , " + getGRDCode() +
                " , " + getGRDDate() +
                " , " + getEntryBy() +
                " , " + getEntryDate() +
                " ) ";


    }

    // need to see the logic
    public String updateRegAssProgSrvForAssign() {
        return "UPDATE [dbo].[RegNAssignProgSrv] " +
                "SET [RegNDate] = " + getRegNDate() +
                " ,[GRDDate] = " + getGRDDate() +
                " WHERE   [AdmCountryCode] = " + getAdmCountryCode() +
                "        AND [LayR1ListCode] = " + getLayR1ListCode() +
                "        AND [LayR2ListCode] = " + getLayR2ListCode() +
                "        AND [LayR3ListCode] = " + getLayR3ListCode() +
                "        AND [LayR4ListCode] = " + getLayR4ListCode() +
                "        AND [AdmDonorCode] = " + getAdmDonorCode() +
                "        AND [AdmAwardCode] = " + getAdmAwardCode() +
                "        AND [HHID] = " + getHHID() +
                "        AND [MemID] = " + getMemID();

    }

    public String updateRegNPWForPregnantWomen() {
        return "UPDATE [dbo].[RegN_PW] " +
                "SET [RegNDate] = " + getRegNDate() +
                " , [LMPDate] = " + getLMPDate() +
                " , [GRDCode] = " + getGRDCode() +
                " , [PWGRDDate] = " + getPWGRDDate() +
                " , [EntryBy] = " + getEntryBy() +
                " , [EntryDate] = " + getEntryDate() +
                "WHERE   [AdmCountryCode] = " + getAdmCountryCode() +
                "        AND [LayR1ListCode] = " + getLayR1ListCode() +
                "        AND [LayR2ListCode] = " + getLayR2ListCode() +
                "        AND [LayR3ListCode] = " + getLayR3ListCode() +
                "        AND [LayR4ListCode] = " + getLayR4ListCode() +
                "        AND [HHID] = " + getHHID() +
                "        AND [MemID] = " + getMemID();


    }

    public String insertRegNPWForPregnantWomen() {
        return "INSERT INTO [dbo].[RegN_PW]" +
                "        ([AdmCountryCode]" +
                "        ,[LayR1ListCode]" +
                "        ,[LayR2ListCode]" +
                "        ,[LayR3ListCode]" +
                "        ,[LayR4ListCode]" +
                "        ,[HHID]" +
                "        ,[MemID]" +
                "        ,[RegNDate]" +
                "        ,[LMPDate]" +
                "        ,[AdmProgCode]" +
                "        ,[AdmSrvCode]" +
                "        ,[GRDCode]" +
                "        ,[PWGRDDate]" +
                "        ,[EntryBy]" +
                "        ,[EntryDate])" +
                "        VALUES " +
                "        ( " + getAdmCountryCode() +
                " , " + getLayR1ListCode() +
                " , " + getLayR2ListCode() +
                " , " + getLayR3ListCode() +
                " , " + getLayR4ListCode() +
                " , " + getHHID() +
                " , " + getMemID() +
                " , " + getRegNDate() +
                " , " + getLMPDate() +
                " , " + getProgCode() +
                " , " + getSrvCode() +
                " , " + getGRDCode() +
                " , " + getPWGRDDate() +
                " , " + getEntryBy() +
                " , " + getEntryDate() +
                " ) ";

    }

    public String insertIntoRegNLMForLactatedMother() {
        return "INSERT INTO [dbo].[RegN_LM]" +
                "        ([AdmCountryCode]" +
                "        ,[LayR1ListCode]" +
                "        ,[LayR2ListCode]" +
                "        ,[LayR3ListCode]" +
                "        ,[LayR4ListCode]" +
                "        ,[HHID]" +
                "        ,[MemID]" +
                "        ,[RegNDate]" +
                "        ,[LMDOB]" +
                "        ,[AdmProgCode]" +
                "        ,[AdmSrvCode]" +
                "        ,[GRDCode]" +
                "        ,[LMGRDDate]" +
                "        ,[EntryBy]" +
                "        ,[EntryDate])" +
                "        VALUES " +
                "        ( " + getAdmCountryCode() +
                " , " + getLayR1ListCode() +
                " , " + getLayR2ListCode() +
                " , " + getLayR3ListCode() +
                " , " + getLayR4ListCode() +
                " , " + getHHID() +
                " , " + getMemID() +
                " , " + getRegNDate() +
                " , " + getLMDOB() +
                " , " + getProgCode() +
                " , " + getSrvCode() +
                " , " + getGRDCode() +
                " , " + getLMGRDDate() +
                " , " + getEntryBy() +
                " , " + getEntryDate() +
                " ) ";

    }

    public String updateRegNLMForLactingMother() {
        return "UPDATE [dbo].[RegN_LM] " +
                "SET [RegNDate] = " + getRegNDate() +
                " , [LMDOB] = " + getLMDOB() +
                " , [GRDCode] = " + getGRDCode() +
                " , [LMGRDDate] = " + getLMGRDDate() +
                " , [EntryBy] = " + getEntryBy() +
                " , [EntryDate] = " + getEntryDate() +
                "WHERE   [AdmCountryCode] = " + getAdmCountryCode() +
                "        AND [LayR1ListCode] = " + getLayR1ListCode() +
                "        AND [LayR2ListCode] = " + getLayR2ListCode() +
                "        AND [LayR3ListCode] = " + getLayR3ListCode() +
                "        AND [LayR4ListCode] = " + getLayR4ListCode() +
                "        AND [HHID] = " + getHHID() +
                "        AND [MemID] = " + getMemID();


    }


    public String insertIntoRegNCA2ForChildAbove() {
        return "INSERT INTO [dbo].[RegN_CA2]" +
                "        ([AdmCountryCode]" +
                "        ,[LayR1ListCode]" +
                "        ,[LayR2ListCode]" +
                "        ,[LayR3ListCode]" +
                "        ,[LayR4ListCode]" +
                "        ,[HHID]" +
                "        ,[MemID]" +
                "        ,[RegNDate]" +
                "        ,[CA2DOB]" +
                "        ,[AdmProgCode]" +
                "        ,[AdmSrvCode]" +
                "        ,[GRDCode]" +
                "        ,[CA2GRDDate]" +
                "        ,[EntryBy]" +
                "        ,[EntryDate])" +
                "        VALUES " +
                "        ( " + getAdmCountryCode() +
                " , " + getLayR1ListCode() +
                " , " + getLayR2ListCode() +
                " , " + getLayR3ListCode() +
                " , " + getLayR4ListCode() +
                " , " + getHHID() +
                " , " + getMemID() +
                " , " + getRegNDate() +
                " , " + getCA2DOB() +
                " , " + getProgCode() +
                " , " + getSrvCode() +
                " , " + getGRDCode() +
                " , " + getCA2GRDDate() +
                " , " + getEntryBy() +
                " , " + getEntryDate() +
                " ) ";

    }

    public String updateRegNCA2ForChildAbove() {
        return "UPDATE [dbo].[RegN_CA2] " +
                "SET [RegNDate] = " + getRegNDate() +
                " , [CA2DOB] = " + getCA2DOB() +
                " , [GRDCode] = " + getGRDCode() +
                " , [CA2GRDDate] = " + getCA2GRDDate() +
                " , [EntryBy] = " + getEntryBy() +
                " , [EntryDate] = " + getEntryDate() +
                "WHERE   [AdmCountryCode] = " + getAdmCountryCode() +
                "        AND [LayR1ListCode] = " + getLayR1ListCode() +
                "        AND [LayR2ListCode] = " + getLayR2ListCode() +
                "        AND [LayR3ListCode] = " + getLayR3ListCode() +
                "        AND [LayR4ListCode] = " + getLayR4ListCode() +
                "        AND [HHID] = " + getHHID() +
                "        AND [MemID] = " + getMemID();


    }

    public String insertIntoRegNCU2ForChildUnder() {
        return "INSERT INTO [dbo].[RegN_CU2]" +
                "        ([AdmCountryCode]" +
                "        ,[LayR1ListCode]" +
                "        ,[LayR2ListCode]" +
                "        ,[LayR3ListCode]" +
                "        ,[LayR4ListCode]" +
                "        ,[HHID]" +
                "        ,[MemID]" +
                "        ,[RegNDate]" +
                "        ,[CU2DOB]" +
                "        ,[AdmProgCode]" +
                "        ,[AdmSrvCode]" +
                "        ,[GRDCode]" +
                "        ,[CU2GRDDate]" +
                "        ,[EntryBy]" +
                "        ,[EntryDate])" +
                "        VALUES " +
                "        ( " + getAdmCountryCode() +
                " , " + getLayR1ListCode() +
                " , " + getLayR2ListCode() +
                " , " + getLayR3ListCode() +
                " , " + getLayR4ListCode() +
                " , " + getHHID() +
                " , " + getMemID() +
                " , " + getRegNDate() +
                " , " + getCU2DOB() +
                " , " + getProgCode() +
                " , " + getSrvCode() +
                " , " + getGRDCode() +
                " , " + getCU2GRDDate() +
                " , " + getEntryBy() +
                " , " + getEntryDate() +
                " ) ";

    }

    public String updateRegNCU2ForChildUnder() {
        return "UPDATE [dbo].[RegN_CU2] " +
                "SET [RegNDate] = " + getRegNDate() +
                " , [CU2DOB] = " + getCA2DOB() +
                " , [GRDCode] = " + getGRDCode() +
                " , [CU2GRDDate] = " + getCA2GRDDate() +
                " , [EntryBy] = " + getEntryBy() +
                " , [EntryDate] = " + getEntryDate() +
                "WHERE   [AdmCountryCode] = " + getAdmCountryCode() +
                "        AND [LayR1ListCode] = " + getLayR1ListCode() +
                "        AND [LayR2ListCode] = " + getLayR2ListCode() +
                "        AND [LayR3ListCode] = " + getLayR3ListCode() +
                "        AND [LayR4ListCode] = " + getLayR4ListCode() +
                "        AND [HHID] = " + getHHID() +
                "        AND [MemID] = " + getMemID();


    }

    public String insertIntoRegNMemberForLiberia() {
        return "INSERT INTO [dbo].[RegNHHMem]" +
                "           ([AdmCountryCode]" +
                "           ,[LayR1ListCode]" +
                "           ,[LayR2ListCode]" +
                "           ,[LayR3ListCode]" +
                "           ,[LayR4ListCode]" +
                "           ,[HHID]" +
                "           ,[HHMemID]" +
                "           ,[MemName]" +
                "           ,[EntryBy]" +
                "           ,[EntryDate]" +
                "           ,[RegNDate]" +
                "           ,[BirthYear]" +
                "           ,[MaritalStatus]" +
                "           ,[ContactNo]" +
                "           ,[MemOtherID]" +
                "           ,[MemName_First]" +
                "           ,[MemName_Middle]" +
                "           ,[MemName_Last]" +
                "           ,[Photo]" +
                "           ,[Type_ID]" +
                "           ,[ID_NO]" +
                "           ,[V_BSCMemName1_First]" +
                "           ,[V_BSCMemName1_Middle]" +
                "           ,[V_BSCMemName1_Last] " +
                "           ,[V_BSCMem1_TitlePosition] " +
                "           ,[V_BSCMemName2_First] " +
                "           ,[V_BSCMemName2_Middle] " +
                "           ,[V_BSCMemName2_Last] " +
                "           ,[V_BSCMem2_TitlePosition] " +
                "           ,[Proxy_Designation] " +
                "           ,[Proxy_Name_First] " +
                "           ,[Proxy_Name_Middle] " +
                "           ,[Proxy_Name_Last] " +
                "           ,[Proxy_BirthYear] " +
                "           ,[Proxy_Photo] " +
                "           ,[Proxy_Type_ID] " +
                "           ,[Proxy_ID_NO] " +
                "           ,[P_BSCMemName1_First] " +
                "           ,[P_BSCMemName1_Middle] " +
                "           ,[P_BSCMemName1_Last] " +
                "           ,[P_BSCMem1_TitlePosition] " +
                "           ,[P_BSCMemName2_First] " +
                "           ,[P_BSCMemName2_Middle] " +
                "           ,[P_BSCMemName2_Last] " +
                "           ,[P_BSCMem2_TitlePosition]" +
                "           ,[ImageTypeFlag]" +
                ")" +
                "     VALUES " +
                "           ( " + getAdmCountryCode() +
                "           , " + getLayR1ListCode() +
                "           , " + getLayR2ListCode() +
                "           , " + getLayR3ListCode() +
                "           , " + getLayR4ListCode() +
                "           , " + getHHID() +
                "           , " + getMemID() +
                "           , " + getMmMemName() +
                "           , " + getEntryBy() +
                "           , " + getEntryDate() +
                "           , " + getRegNDate() +
                "           , " + getMmBirthYear() +
                "           , " + getMmMaritalStatus() +
                "           , " + getMmContactNo() +
                "           , " + getMmMemOtherID() +
                "           , " + getMmMemName_First() +
                "           , " + getMmMemName_Middle() +
                "           , " + getMmMemName_Last() +
                "           , " + getMmPhoto() +
                "           , " + getMmType_ID() +
                "           , " + getMmID_NO() +
                "           , " + getMmV_BSCMemName1_First() +
                "           , " + getMmV_BSCMemName1_Middle() +
                "           , " + getMmV_BSCMemName1_Last() +
                "           , " + getMmV_BSCMem1_TitlePosition() +
                "           , " + getMmV_BSCMemName2_First() +
                "           , " + getMmV_BSCMemName2_Middle() +
                "           , " + getMmV_BSCMemName2_Last() +
                "           , " + getMmV_BSCMem2_TitlePosition() +
                "           , " + getMmProxy_Designation() +
                "           , " + getMmProxy_Name_First() +
                "           , " + getMmProxy_Name_Middle() +
                "           , " + getMmProxy_Name_Last() +
                "           , " + getMmProxy_BirthYear() +
                "           , " + getMmProxy_Photo() +
                "           , " + getMmProxy_Type_ID() +
                "           , " + getMmProxy_ID_NO() +
                "           , " + getMmP_BSCMemName1_First() +
                "           , " + getMmP_BSCMemName1_Middle() +
                "           , " + getMmP_BSCMemName1_Last() +
                "           , " + getMmP_BSCMem1_TitlePosition() +
                "           , " + getMmP_BSCMemName2_First() +
                "           , " + getMmP_BSCMemName2_Middle() +
                "           , " + getMmP_BSCMemName2_Last() +
                "           , " + getMmP_BSCMem2_TitlePosition() +
                "           , '0' " +
                ")";

    }

//    public String insertIntoRegNMemberForMalawi() {
//        return "INSERT INTO [dbo].[RegNHHMem]" +
//                "           ([AdmCountryCode]" +
//                "           ,[LayR1ListCode]" +
//                "           ,[LayR2ListCode]" +
//                "           ,[LayR3ListCode]" +
//                "           ,[LayR4ListCode]" +
//                "           ,[HHID]" +
//                "           ,[HHMemID]" +
//                "           ,[MemName]" +
//                "           ,[MemSex]" +
//                "           ,[HHRelation]" +
//                "           ,[MemAge]" +
//                "           ,[EntryBy]" +
//                "           ,[EntryDate]" +
//                "           ,[RegNDate]" +
//                "            )" +
//                "     VALUES " +
//                "           ( " + getAdmCountryCode() +
//                "           , " + getLayR1ListCode() +
//                "           , " + getLayR2ListCode() +
//                "           , " + getLayR3ListCode() +
//                "           , " + getLayR4ListCode() +
//                "           , " + getHHID() +
//                "           , " + getMemID() +
//                "           , " + getMmMemName() +
//                "           , " + getMmMemSex() +
//                "           , " + getMmHHRelation() +
//                "           , " + getMmMemAge() +
//                "           , " + getEntryBy() +
//                "           , " + getEntryDate() +
//                "           , " + getRegNDate() +
//
//                " )";
//
//    }

    public String insertIntoRegNMemberForMalawi() {

        return "INSERT INTO [dbo].[RegNHHMem]" +
                "           ([AdmCountryCode]" +
                "           ,[LayR1ListCode]" +
                "           ,[LayR2ListCode]" +
                "           ,[LayR3ListCode]" +
                "           ,[LayR4ListCode]" +
                "           ,[HHID]" +
                "           ,[HHMemID]" +
                "           ,[MemName]" +
                "           ,[MemSex]" +
                "           ,[HHRelation]" +
                "           ,[MemAge]" +
                "           ,[EntryBy]" +
                "           ,[EntryDate]" +
                "           ,[RegNDate]" +
                "           ,[MemTypeFlag]" +
                "            )" +
                "     VALUES " +
                "           ( " + getAdmCountryCode() +
                "           , " + getLayR1ListCode() +
                "           , " + getLayR2ListCode() +
                "           , " + getLayR3ListCode() +
                "           , " + getLayR4ListCode() +
                "           , " + getHHID() +
                "           , " + getMemID() +
                "           , " + getMmMemName() +
                "           , " + getMmMemSex() +
                "           , " + getMmHHRelation() +
                "           , " + getMmMemAge() +
                "           , " + getEntryBy() +
                "           , " + getEntryDate() +
                "           , " + getRegNDate() +
                "           , " + getMemTypeFlag() +

                " )";

//        Log.e("ShuvoSynctex",method);

//        return method;

    }

    public String updateRegNMemberForMalawi() {
        String up = "UPDATE [dbo].[RegNHHMem] " +
                "    SET " +
                "    ,[MemName] = " + getMmMemName() +
                "            ,[MemSex] = " + getMmMemSex() +
                "            ,[HHRelation] = " + getMmHHRelation() +
                "            ,[MemAge] = " + getMmMemAge() +
                "            ,[MemTypeFlag] = " + getMemTypeFlag() +
                "    WHERE " +

                "            [AdmCountryCode] = " + getAdmCountryCode() +
                "           AND [LayR1ListCode] = " + getLayR1ListCode() +
                "           AND [LayR2ListCode] = " + getLayR2ListCode() +
                "           AND [LayR3ListCode] = " + getLayR3ListCode() +
                "           AND [LayR4ListCode] = " + getLayR4ListCode() +
                "           AND [HHID] = " + getHHID() +
                "           AND [HHMemID] = " + getMemID();
        Log.d(TAG, up);

        return up;
    }


    /**
     * CARD PRINT TABLE REQUEST
     */
    private String rptGroup;
    private String rptCode;
    private String requestSL;
    private String reasonCode;
    private String responseCode;
    private String requestDate;
    private String printDate;
    private String receivedDate;
    private String delevaryDate;
    private String deliveredBy;

    public String getDeliveredBy() {
        return deliveredBy;
    }

    public void setDeliveredBy(String deliveredBy) {
        deliveredBy = checkStringNull(deliveredBy);
        this.deliveredBy = deliveredBy;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        reasonCode = checkStringNull(reasonCode);
        this.reasonCode = reasonCode;
    }

    public String getRptCode() {
        return rptCode;
    }

    public void setRptCode(String rptCode) {
        rptCode = checkStringNull(rptCode);
        this.rptCode = rptCode;
    }

    public String getRptGroup() {
        return rptGroup;
    }

    public void setRptGroup(String rptGroup) {
        rptGroup = checkStringNull(rptGroup);
        this.rptGroup = rptGroup;
    }

    public String getDelevaryDate() {
        return delevaryDate;
    }

    public void setDelevaryDate(String delevaryDate) {
        delevaryDate = checkStringNull(delevaryDate);
        this.delevaryDate = delevaryDate;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        receivedDate = checkStringNull(receivedDate);
        this.receivedDate = receivedDate;
    }

    public String getPrintDate() {
        return printDate;
    }

    public void setPrintDate(String printDate) {
        printDate = checkStringNull(printDate);
        this.printDate = printDate;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        requestDate = checkStringNull(requestDate);
        this.requestDate = requestDate;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        responseCode = checkStringNull(responseCode);
        this.responseCode = responseCode;
    }

    public String getRequestSL() {
        return requestSL;
    }

    public void setRequestSL(String requestSL) {
        requestSL = checkStringNull(requestSL);
        this.requestSL = requestSL;
    }


    public String insertIntoRegNCardPrintTable() {

        return "INSERT INTO [dbo].[RegNMemCardPrintTable]" +
                "           ([AdmCountryCode]" +
                "           ,[AdmDonorCode]" +
                "           ,[AdmAwardCode]" +
                "           ,[LayR1ListCode]" +
                "           ,[LayR2ListCode]" +
                "           ,[LayR3ListCode]" +
                "           ,[LayR4ListCode]" +
                "           ,[HHID]" +
                "           ,[MemID]" +
                "           ,[RptGroup]" +
                "           ,[RptCode]" +
                "           ,[RequestSL]" +
                "           ,[ReasonCode]" +
                "           ,[RequestDate]" +
                "           ,[DelStatus]" +
                "           ,[EntryBy]" +
                "           ,[EntryDate])" +
                "     VALUES " +
                "           ( " + getAdmCountryCode() +
                "            ," + getAdmDonorCode() +
                "            ," + getAdmAwardCode() +
                "            ," + getLayR1ListCode() +
                "            ," + getLayR2ListCode() +
                "            ," + getLayR3ListCode() +
                "            ," + getLayR4ListCode() +
                "            ," + getHHID() +
                "            ," + getMemID() +
                "            ," + getRptGroup() +
                "            ," + getRptCode() +
                "            ," + getRequestSL() +
                "            ," + getReasonCode() +
                "            ," + getRequestDate() +
                "               ,'N'    " +// when insert method the delevery status should be N
                "            ," + getEntryBy() +
                "            ," + getEntryDate() +
                " )";
    }


    public String addCardDeliveryDateIntoRegNCardPrintTable() {


        return "UPDATE [dbo].[RegNMemCardPrintTable] " +
                " SET " +
                " [DeliveryDate] = " + getDelevaryDate() +
                ",[DeliveredBy] = " + getDeliveredBy() +
                ",[EntryBy]     = " + getEntryBy() +
                ",[EntryDate]   = " + getEntryDate() +
                ",[DelStatus]   = " + " 'Y' " +
                "WHERE " +
                "[AdmCountryCode] = " + getAdmCountryCode() +
                " AND [AdmDonorCode]  = " + getAdmDonorCode() +
                " AND [AdmAwardCode]  = " + getAdmAwardCode() +
                " AND [LayR1ListCode] = " + getLayR1ListCode() +
                " AND [LayR2ListCode] = " + getLayR2ListCode() +
                " AND [LayR3ListCode] = " + getLayR3ListCode() +
                " AND [LayR4ListCode] = " + getLayR4ListCode() +
                " AND [HHID]          = " + getHHID() +
                " AND [MemID]         = " + getMemID() +
                " AND [RptGroup]      = " + getRptGroup() +
                " AND [RptCode]       = " + getRptCode() +
                " AND [RequestSL]     = " + getRequestSL() +
                " AND [ReasonCode]    = " + getReasonCode();


    }


    public String updateLiberiaMember() {
        return "UPDATE [dbo].[RegNHHMem]" +
                "   SET " +
                "       [MemName] = " + getMmMemName() +
                "      ,[EntryBy] = " + getEntryBy() +
                "      ,[EntryDate] = " + getEntryDate() +
                "      ,[RegNDate] =" + getRegNDate() +
                "      ,[BirthYear] = " + getMmBirthYear() +
                "      ,[MaritalStatus] = " + getMmMaritalStatus() +
                "      ,[ContactNo] = " + getMmContactNo() +
                "      ,[MemOtherID] = " + getMmMemOtherID() +
                "      ,[MemName_First] = " + getMmMemName_First() +
                "      ,[MemName_Middle] = " + getMmMemName_Middle() +
                "      ,[MemName_Last] = " + getMmMemName_Last() +
                "      ,[Photo] = " + getMmPhoto() +
                "      ,[Type_ID] = " + getMmType_ID() +
                "      ,[ID_NO] = " + getMmID_NO() +
                "      ,[V_BSCMemName1_First] = " + getMmV_BSCMemName1_First() +
                "      ,[V_BSCMemName1_Middle] = " + getMmV_BSCMemName1_Middle() +
                "      ,[V_BSCMemName1_Last] = " + getMmV_BSCMemName1_Last() +
                "      ,[V_BSCMem1_TitlePosition] = " + getMmV_BSCMem1_TitlePosition() +
                "      ,[V_BSCMemName2_First] = " + getMmV_BSCMemName2_First() +
                "      ,[V_BSCMemName2_Middle] = " + getMmV_BSCMemName2_Middle() +
                "      ,[V_BSCMemName2_Last] = " + getMmV_BSCMemName2_Last() +
                "      ,[V_BSCMem2_TitlePosition] = " + getMmV_BSCMem2_TitlePosition() +
                "      ,[Proxy_Designation] = " + getMmProxy_Designation() +
                "      ,[Proxy_Name_First] = " + getMmProxy_Name_First() +
                "      ,[Proxy_Name_Middle] = " + getMmProxy_Name_Middle() +
                "      ,[Proxy_Name_Last] = " + getMmProxy_Name_Last() +
                "      ,[Proxy_BirthYear] = " + getMmProxy_BirthYear() +
                "      ,[Proxy_Photo] = " + getMmProxy_Photo() +
                "      ,[Proxy_Type_ID] = " + getMmProxy_Type_ID() +
                "      ,[Proxy_ID_NO] = " + getMmProxy_ID_NO() +
                "      ,[P_BSCMemName1_First] = " + getMmP_BSCMemName1_First() +
                "      ,[P_BSCMemName1_Middle] = " + getMmP_BSCMemName1_Middle() +
                "      ,[P_BSCMemName1_Last] = " + getMmP_BSCMemName1_Last() +
                "      ,[P_BSCMem1_TitlePosition] = " + getMmP_BSCMem1_TitlePosition() +
                "      ,[P_BSCMemName2_First] = " + getMmP_BSCMemName2_First() +
                "      ,[P_BSCMemName2_Middle] = " + getMmP_BSCMemName2_Middle() +
                "      ,[P_BSCMemName2_Last] = " + getMmP_BSCMemName2_Last() +
                "      ,[P_BSCMem2_TitlePosition] = " + getMmP_BSCMem2_TitlePosition() +
                "      ,[ImageTypeFlag] = '0' " +
                " WHERE " +
                "   [AdmCountryCode]        = " + getAdmCountryCode() +
                "     AND [LayR1ListCode]   = " + getLayR1ListCode() +
                "     AND [LayR2ListCode]   = " + getLayR2ListCode() +
                "     AND [LayR3ListCode]   = " + getLayR3ListCode() +
                "     AND [LayR4ListCode]   = " + getLayR4ListCode() +
                "     AND [HHID]            = " + getHHID() +
                "     AND [HHMemID]         = " + getMemID();
    }


    private String c11_CT_PR;
    private String c21_CT_PR;
    private String c31_CT_PR;
    private String c32_CT_PR;
    private String c33_CT_PR;
    private String c34_CT_PR;
    private String c35_CT_PR;
    private String c36_CT_PR;
    private String c37_CT_PR;
    private String c38_CT_PR;

    public String getC11_CT_PR() {
        return c11_CT_PR;
    }

    public void setC11_CT_PR(String c11_CT_PR) {
        c11_CT_PR = checkStringNull(c11_CT_PR);
        this.c11_CT_PR = c11_CT_PR;
    }

    public String getC21_CT_PR() {
        return c21_CT_PR;
    }

    public void setC21_CT_PR(String c21_CT_PR) {
        c21_CT_PR = checkStringNull(c21_CT_PR);
        this.c21_CT_PR = c21_CT_PR;
    }

    public String getC31_CT_PR() {
        return c31_CT_PR;
    }

    public void setC31_CT_PR(String c31_CT_PR) {
        c31_CT_PR = checkStringNull(c31_CT_PR);
        this.c31_CT_PR = c31_CT_PR;
    }

    public String getC32_CT_PR() {
        return c32_CT_PR;
    }

    public void setC32_CT_PR(String c32_CT_PR) {
        c32_CT_PR = checkStringNull(c32_CT_PR);
        this.c32_CT_PR = c32_CT_PR;
    }

    public String getC33_CT_PR() {
        return c33_CT_PR;
    }

    public void setC33_CT_PR(String c33_CT_PR) {
        c33_CT_PR = checkStringNull(c33_CT_PR);
        this.c33_CT_PR = c33_CT_PR;
    }

    public String getC35_CT_PR() {
        return c35_CT_PR;
    }

    public void setC35_CT_PR(String c35_CT_PR) {
        c35_CT_PR = checkStringNull(c35_CT_PR);
        this.c35_CT_PR = c35_CT_PR;
    }

    public String getC34_CT_PR() {
        return c34_CT_PR;
    }

    public void setC34_CT_PR(String c34_CT_PR) {
        c34_CT_PR = checkStringNull(c34_CT_PR);
        this.c34_CT_PR = c34_CT_PR;
    }

    public String getC36_CT_PR() {
        return c36_CT_PR;
    }

    public void setC36_CT_PR(String c36_CT_PR) {
        c36_CT_PR = checkStringNull(c36_CT_PR);
        this.c36_CT_PR = c36_CT_PR;
    }

    public String getC37_CT_PR() {
        return c37_CT_PR;
    }

    public void setC37_CT_PR(String c37_CT_PR) {
        c37_CT_PR = checkStringNull(c37_CT_PR);
        this.c37_CT_PR = c37_CT_PR;
    }

    public String getC38_CT_PR() {
        return c38_CT_PR;
    }

    public void setC38_CT_PR(String c38_CT_PR) {
        c38_CT_PR = checkStringNull(c38_CT_PR);
        this.c38_CT_PR = c38_CT_PR;
    }


    public String insertIntoRegN_CT() {
        return "INSERT INTO [dbo].[RegN_CT]" +
                "           ([AdmCountryCode]" +
                "           ,[LayR1ListCode]" +
                "           ,[LayR2ListCode]" +
                "           ,[LayR3ListCode]" +
                "           ,[LayR4ListCode]" +
                "           ,[HHID]" +
                "           ,[MemID]" +
                "           ,[C11_CT_PR]" +
                "           ,[C21_CT_PR]" +
                "           ,[C31_CT_PR]" +
                "           ,[C32_CT_PR]" +
                "           ,[C33_CT_PR]" +
                "           ,[C34_CT_PR]" +
                "           ,[C35_CT_PR]" +
                "           ,[C36_CT_PR]" +
                "           ,[C37_CT_PR]" +
                "           ,[C38_CT_PR]" +
                "           ,[EntryBy]" +
                "           ,[EntryDate]"
                + ")"
                + "VALUES"
                + "("
                + getAdmCountryCode()
                + "," + getLayR1ListCode()
                + "," + getLayR2ListCode()
                + "," + getLayR3ListCode()
                + "," + getLayR4ListCode()
                + "," + getHHID()
                + "," + getMemID()
                + "," + getC11_CT_PR()
                + "," + getC21_CT_PR()
                + "," + getC31_CT_PR()
                + "," + getC32_CT_PR()
                + "," + getC33_CT_PR()
                + "," + getC34_CT_PR()
                + "," + getC35_CT_PR()
                + "," + getC36_CT_PR()
                + "," + getC37_CT_PR()
                + "," + getC38_CT_PR()
                + "," + getEntryBy()
                + "," + getEntryDate()
                + ")";

    }


    public String deleteHouseHoldFormRegNHouseHoldTable() {
        return "DELETE FROM [dbo].[RegNHHTable] " +
                " WHERE [AdmCountryCode] = " + getAdmCountryCode() + "  " +
                "           AND [LayR1ListCode] = " + getLayR1ListCode() + "  " +
                "           AND [LayR2ListCode] = " + getLayR2ListCode() + "  " +
                "           AND [LayR3ListCode] = " + getLayR3ListCode() + "  " +
                "           AND [LayR4ListCode] = " + getLayR4ListCode() + "  " +
                "           AND [HHID] = " + getHHID() + "  ";
    }


    public String deleteHouseHoldFormRegNHHMemberTable() {
        return "DELETE FROM [dbo].[RegNHHMem] " +
                " WHERE [AdmCountryCode] = " + getAdmCountryCode() + "  " +
                "           AND [LayR1ListCode] = " + getLayR1ListCode() + "  " +
                "           AND [LayR2ListCode] = " + getLayR2ListCode() + "  " +
                "           AND [LayR3ListCode] = " + getLayR3ListCode() + "  " +
                "           AND [LayR4ListCode] = " + getLayR4ListCode() + "  " +
                "           AND [HHID] = " + getMemID() + "  ";
    }


    public String deleteMemberFormRegNHHMemberTable() {
        return "DELETE FROM [dbo].[RegNHHMem] " +
                " WHERE [AdmCountryCode] = " + getAdmCountryCode() + "  " +
                "           AND [LayR1ListCode] = " + getLayR1ListCode() + "  " +
                "           AND [LayR2ListCode] = " + getLayR2ListCode() + "  " +
                "           AND [LayR3ListCode] = " + getLayR3ListCode() + "  " +
                "           AND [LayR4ListCode] = " + getLayR4ListCode() + "  " +
                "           AND [HHID] = " + getHHID() + "  " +
                "           AND [HHMemID] = " + getHHID() + "  "
                ;
    }
  /*  DELETE FROM [GPath_Android_Test].[dbo].[RegNHHMem]
    WHERE <Search Conditions,,>*/

    private String FDPCode;
    private String ID;
    private String DistStatus;

    public String getDistStatus() {
        return DistStatus;
    }

    public void setDistStatus(String distStatus) {
        distStatus = checkStringNull(distStatus);
        DistStatus = distStatus;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {

        ID = checkStringNull(ID);
        this.ID = ID;
    }

    public String getFDPCode() {
        return FDPCode;
    }

    public void setFDPCode(String FDPCode) {
        FDPCode = checkStringNull(FDPCode);
        this.FDPCode = FDPCode;
    }

    public String SrvOpMonthCode;
    public String DistFlag;

    public String getSrvOpMonthCode() {
        return SrvOpMonthCode;
    }

    public void setSrvOpMonthCode(String srvOpMonthCode) {
        srvOpMonthCode = checkStringNull(srvOpMonthCode);
        SrvOpMonthCode = srvOpMonthCode;
    }

    public String getDistFlag() {
        return DistFlag;
    }

    public void setDistFlag(String distFlag) {
        distFlag = checkStringNull(distFlag);
        DistFlag = distFlag;
    }

    public String insertIntoDistributionTable() {
        return "INSERT INTO [dbo].[DistTable] " +
                "           ([AdmCountryCode] " +
                "           ,[AdmDonorCode]   " +
                "           ,[AdmAwardCode]   " +
                "           ,[LayR1ListCode]  " +
                "           ,[LayR2ListCode]  " +
                "           ,[LayR3ListCode]  " +
                "           ,[LayR4ListCode]  " +
                "           ,[ProgCode]       " +
                "           ,[SrvCode]        " +
                "           ,[OpMonthCode]    " +
                "           ,[FDPCode]        " +
                "           ,[ID]             " +
                "           ,[DistStatus]     " +
                "           ,[SrvOpMonthCode] " +
                "           ,[DistFlag]        " +
                "           ,[WD]        " +
                "           ,[EntryBy]        " +
                "           ,[EntryDate])     " +
                "     VALUES    " +
                "           ( " + getAdmCountryCode() +
                "           , " + getAdmDonorCode() +
                "           , " + getAdmAwardCode() +
                "           , " + getLayR1ListCode() +
                "           , " + getLayR2ListCode() +
                "           , " + getLayR3ListCode() +
                "           , " + getLayR4ListCode() +
                "           , " + getProgCode() +
                "           , " + getSrvCode() +
                "           , " + getOpMonthCode() +
                "           , " + getFDPCode() +
                "           , " + getID() +
                "           , " + getDistStatus() +
                "           , " + getSrvOpMonthCode() +
                "           , " + getDistFlag() +
                "           , " + getWD() +
                "           , " + getEntryBy() +
                "           , " + getEntryDate() +
                " )";
    }

    String ElderlyYN;
    String LandSize;
    String DependOnGanyu;
    String Willingness;
    String WinterCultivation;
    String VulnerableHH;
    String PlantingValueChainCrop;

    public String getDependOnGanyu() {
        return DependOnGanyu;
    }

    public void setDependOnGanyu(String dependOnGanyu) {
        dependOnGanyu = checkStringNull(dependOnGanyu);

        this.DependOnGanyu = dependOnGanyu;
    }

    public String getLandSize() {
        return LandSize;
    }

    public void setLandSize(String landSize) {
        landSize = checkIntNull(landSize);
        this.LandSize = landSize;
    }

    public String getWillingness() {
        return Willingness;
    }

    public void setWillingness(String willingness) {
        willingness = checkStringNull(willingness);
        this.Willingness = willingness;
    }

    public String getWinterCultivation() {
        return WinterCultivation;
    }

    public void setWinterCultivation(String winterCultivation) {
        winterCultivation = checkStringNull(winterCultivation);
        this.WinterCultivation = winterCultivation;
    }

    public String getVulnerableHH() {
        return VulnerableHH;
    }

    public void setVulnerableHH(String vulnerableHH) {
        vulnerableHH = checkStringNull(vulnerableHH);

        this.VulnerableHH = vulnerableHH;
    }

    public String getPlantingValueChainCrop() {
        return PlantingValueChainCrop;
    }

    public void setPlantingValueChainCrop(String plantingValueChainCrop) {
        plantingValueChainCrop = checkStringNull(plantingValueChainCrop);
        PlantingValueChainCrop = plantingValueChainCrop;
    }


    public String getElderlyYN() {
        return ElderlyYN;
    }

    public void setElderlyYN(String elderlyYN) {
        elderlyYN = checkStringNull(elderlyYN);


        this.ElderlyYN = elderlyYN;
    }

    public String agoInvc;
    public String agoNasfam;

    public String getAgoInvc() {
        return agoInvc;
    }

    public void setAgoInvc(String agoInvc) {
        agoInvc = checkStringNull(agoInvc);
        this.agoInvc = agoInvc;
    }

    public String getAgoNasfam() {
        return agoNasfam;
    }

    public void setAgoNasfam(String agoNasfam) {
        agoNasfam = checkStringNull(agoNasfam);
        this.agoNasfam = agoNasfam;
    }

    public String getAgoCu() {
        return agoCu;
    }

    public void setAgoCu(String agoCu) {
        agoCu = checkStringNull(agoCu);
        this.agoCu = agoCu;
    }

    public String getAgoOther() {
        return agoOther;
    }

    public void setAgoOther(String agoOther) {
        agoOther = checkStringNull(agoOther);
        this.agoOther = agoOther;
    }

    public int getLsGoat() {
        return lsGoat;
    }

    public void setLsGoat(int lsGoat) {

        this.lsGoat = lsGoat;
    }

    public int getLsChicken() {
        return lsChicken;
    }

    public void setLsChicken(int lsChicken) {
        this.lsChicken = lsChicken;
    }

    public int getLsPigeon() {
        return lsPigeon;
    }

    public void setLsPigeon(int lsPigeon) {
        this.lsPigeon = lsPigeon;
    }

    public int getLsOther() {
        return lsOther;
    }

    public void setLsOther(int lsOther) {
        this.lsOther = lsOther;
    }

    public String agoCu;
    public String agoOther;

    public int lsGoat;
    public int lsChicken;
    public int lsPigeon;
    public int lsOther;


    public String insertIntoRegN_Agr_Table() {
        return "INSERT INTO [dbo].[RegN_AGR]" +
                "           ([AdmCountryCode]" +
                "           ,[LayR1ListCode]" +
                "           ,[LayR2ListCode]" +
                "           ,[LayR3ListCode]" +
                "           ,[LayR4ListCode]" +
                "           ,[HHID]" +
                "           ,[MemID]" +
                "           ,[RegNDate]" +
                "           ,[ElderlyYN]" +
                "           ,[EntryBy]" +
                "           ,[EntryDate]" +
                "           ,[LandSize]" +
                "           ,[DependOnGanyu]" +
                "           ,[Willingness]" +
                "           ,[WinterCultivation]" +
                "           ,[VulnerableHH]" +
                "           ,[PlantingValueChainCrop]" +
                "           ,[AGOINVC] " +
                "           ,[AGONASFAM]" +
                "           ,[AGOCU]" +
                "           ,[AGOOther]" +
                "           ,[LSGoat]" +
                "           ,[LSChicken]" +
                "           ,[LSPigeon]" +
                "           ,[LSOther]" +


                " )" +
                "     VALUES   " +
                "           ( " + getAdmCountryCode() +
                "           , " + getLayR1ListCode() +
                "           , " + getLayR2ListCode() +
                "           , " + getLayR3ListCode() +
                "           , " + getLayR4ListCode() +
                "           , " + getHHID() +
                "           , " + getMemID() +
                "           , " + getRegNDate() +
                "           , " + getElderlyYN() +
                "           , " + getEntryBy() +
                "           , " + getEntryDate() +
                "           , " + getLandSize() +
                "           , " + getDependOnGanyu() +
                "           , " + getWillingness() +
                "           , " + getWinterCultivation() +
                "           , " + getVulnerableHH() +
                "           , " + getPlantingValueChainCrop() +
                "           , " + getAgoInvc() +
                "           , " + getAgoNasfam() +
                "           , " + getAgoCu() +
                "           , " + getAgoOther() +
                "           , " + getLsGoat() +
                "           , " + getLsChicken() +
                "           , " + getLsPigeon() +
                "           , " + getLsOther() +
                " ) ";
    }

    private String DisabledYN;

    public String getDisabledYN() {
        return DisabledYN;
    }

    public void setDisabledYN(String disabledYN) {
        disabledYN = checkStringNull(disabledYN);
        DisabledYN = disabledYN;
    }

    public String insertIntoRegN_VUL_table() {
        return " INSERT INTO [dbo].[RegN_VUL] " +
                "            ([AdmCountryCode] " +
                "            ,[LayR1ListCode] " +
                "            ,[LayR2ListCode] " +
                "            ,[LayR3ListCode] " +
                "            ,[LayR4ListCode] " +
                "            ,[HHID]" +
                "            ,[MemID]" +
                "            ,[RegNDate]" +
                "            ,[DisabledYN]" +
                "            ,[EntryBy]" +
                "            ,[EntryDate])" +
                "    VALUES " +
                "            ( " + getAdmCountryCode() +
                "            , " + getLayR1ListCode() +
                "            , " + getLayR2ListCode() +
                "            , " + getLayR3ListCode() +
                "            , " + getLayR4ListCode() +
                "            , " + getHHID() +
                "            , " + getMemID() +
                "            , " + getRegNDate() +
                "            , " + getDisabledYN() +
                "            , " + getEntryBy() +
                "            , " + getEntryDate() +
                " )";
    }

    // service delete
    public String deleteMemberFromSrvTable() {
        return "DELETE FROM SrvTable " +
                "WHERE AdmCountryCode = " + getAdmCountryCode() +
                "  AND AdmDonorCode = " + getAdmDonorCode() +
                "  AND AdmAwardCode = " + getAdmAwardCode() +
                " AND LayR1ListCode = " + getLayR1ListCode() +
                " AND LayR2ListCode = " + getLayR2ListCode() +
                " AND LayR3ListCode = " + getLayR3ListCode() +
                " AND LayR4ListCode = " + getLayR4ListCode() +
                "  AND HHID          = " + getHHID() +
                " AND MemID         = " + getMemID() +
                " AND ProgCode      = " + getProgCode() +
                " AND SrvCode       = " + getSrvCode() +
                " AND OpCode        = " + getOpCode() +
                " AND OpMonthCode   = " + getOpMonthCode() +
                " AND SrvSL   = " + getSrvSL();

    }

    // Insert Into SrvExtendedTable syntext
    String vOItmSpec;
    String vOItmUnit;
    String vORefNumber;
    String vOItmCost;

    public String getvOItmUnit() {

        return vOItmUnit;
    }

    public void setvOItmUnit(String vOItmUnit) {
        vOItmUnit = checkIntNull(vOItmUnit);
        this.vOItmUnit = vOItmUnit;
    }

    public String getvOItmSpec() {
        return vOItmSpec;
    }

    public void setvOItmSpec(String vOItmSpec) {
        vOItmSpec = checkStringNull(vOItmSpec);
        this.vOItmSpec = vOItmSpec;
    }

    public String getvORefNumber() {
        return vORefNumber;
    }

    public void setvORefNumber(String vORefNumber) {
        vORefNumber = checkStringNull(vORefNumber);
        this.vORefNumber = vORefNumber;
    }

    public String getvOItmCost() {
        return vOItmCost;
    }

    public void setvOItmCost(String vOItmCost) {
        vOItmCost = checkIntNull(vOItmCost);

        this.vOItmCost = vOItmCost;
    }

    public String insertIntoSrvExtendedTable() {
        return "INSERT INTO [dbo].[SrvExtendedTable] " +
                " ([AdmCountryCode] " +
                " , [AdmDonorCode] " +
                " , [AdmAwardCode] " +
                " , [LayR1ListCode] " +
                " , [LayR2ListCode] " +
                " , [LayR3ListCode] " +
                " , [LayR4ListCode] " +
                " , [HHID] " +
                " , [MemID] " +
                " , [ProgCode] " +
                " , [SrvCode] " +
                " , [OpCode] " +
                " , [OpMonthCode] " +
                " , [VOItmSpec] " +
                " , [VOItmUnit] " +
                " , [VORefNumber] " +
                " , [EntryDate] " +
                " , [EntryBy] " +
                " , [VOItmCost]" +
                " , [DistFlag]"
                + " ) " +
                "  VALUES " +
                " ( " + getAdmCountryCode() +
                " , " + getAdmDonorCode() +
                " , " + getAdmAwardCode() +
                " , " + getLayR1ListCode() +
                " , " + getLayR2ListCode() +
                " , " + getLayR3ListCode() +
                " , " + getLayR4ListCode() +
                " , " + getHHID() +
                " , " + getMemID() +
                " , " + getProgCode() +
                " , " + getSrvCode() +
                " , " + getOpCode() +
                " , " + getOpMonthCode() +
                " , " + getvOItmSpec() +
                " , " + getvOItmUnit() +
                " , " + getvORefNumber() +
                " , " + getEntryDate() +
                " , " + getEntryBy() +
                " , " + getvOItmCost() +
                " , " + getDistFlag() +
                " ) ";
    }


    // service delete
    public String deleteMemberFromSrvExtendedTable() {
        return "DELETE FROM [dbo].[SrvExtendedTable] " +
                " WHERE AdmCountryCode = " + getAdmCountryCode() +
                " AND AdmDonorCode = " + getAdmDonorCode() +
                " AND AdmAwardCode = " + getAdmAwardCode() +
                " AND LayR1ListCode = " + getLayR1ListCode() +
                " AND LayR2ListCode = " + getLayR2ListCode() +
                " AND LayR3ListCode = " + getLayR3ListCode() +
                " AND LayR4ListCode = " + getLayR4ListCode() +
                " AND HHID          = " + getHHID() +
                " AND MemID         = " + getMemID() +
                " AND ProgCode      = " + getProgCode() +
                " AND SrvCode       = " + getSrvCode() +
                " AND OpCode        = " + getOpCode() +
                " AND OpMonthCode   = " + getOpMonthCode();


    }

    // service delete
    public String deleteFromDistExtendedTable() {
        return "DELETE FROM [dbo].[DistExtendedTable] " +
                " WHERE AdmCountryCode = " + getAdmCountryCode() +
                " AND AdmDonorCode = " + getAdmDonorCode() +
                " AND AdmAwardCode = " + getAdmAwardCode() +
                " AND LayR1ListCode = " + getLayR1ListCode() +
                " AND LayR2ListCode = " + getLayR2ListCode() +
                " AND LayR3ListCode = " + getLayR3ListCode() +
                " AND LayR4ListCode = " + getLayR4ListCode() +
                " AND ProgCode      = " + getProgCode() +
                " AND SrvCode       = " + getSrvCode() +
                " AND FDPCode        = " + getFDPCode() +
                " AND ID   = " + getID();


    }

    public String insertIntoDistExtendedTable() {
        return "INSERT INTO [dbo].[DistExtendedTable] " +
                " ([AdmCountryCode] " +
                " , [AdmDonorCode] " +
                " , [AdmAwardCode] " +
                " , [LayR1ListCode] " +
                " , [LayR2ListCode] " +
                " , [LayR3ListCode] " +
                " , [LayR4ListCode] " +
                " , [ProgCode] " +
                " , [SrvCode] " +
                " , [OpMonthCode] " +
                " , [FDPCode] " +
                " , [ID] " +
                " , [VOItmSpec] " +
                " , [VOItmUnit] " +
                " , [VORefNumber] " +
                " , [SrvOpMonthCode] " +
                " , [DistFlag] " +
                " , [EntryDate] " +
                " , [EntryBy] " +

                " ) " +
                "  VALUES " +
                " ( " + getAdmCountryCode() +
                " , " + getAdmDonorCode() +
                " , " + getAdmAwardCode() +
                " , " + getLayR1ListCode() +
                " , " + getLayR2ListCode() +
                " , " + getLayR3ListCode() +
                " , " + getLayR4ListCode() +
                " , " + getProgCode() +
                " , " + getSrvCode() +
                " , " + getOpMonthCode() +
                " , " + getFDPCode() +
                " , " + getID() +
                " , " + getvOItmSpec() +
                " , " + getvOItmUnit() +
                " , " + getvORefNumber() +

                " , " + getSrvOpMonthCode() +
                " , " + getDistFlag() +
                " , " + getEntryDate() +
                " , " + getEntryBy() +

                " ) ";
    }

    public String update_SrvMinDate() {
        return "update RegNAssignProgSrv "
                + " set SrvMin =convert(date," + getSrvDT() + ") "
                + " where AdmCountryCode=" + getAdmCountryCode() + " and "
                + " AdmDonorCode = " + getAdmDonorCode() + " and "
                + " AdmAwardCode = " + getAdmAwardCode() + " and "
                + " ProgCode = " + getProgCode() + " and "
                + " SrvCode  = " + getSrvCode() + " and "
                + " LayR1ListCode   = " + getLayR1ListCode() + " and "
                + " LayR2ListCode   = " + getLayR2ListCode() + " and "
                + " LayR3ListCode   = " + getLayR3ListCode() + " and "
                + " LayR4ListCode   = " + getLayR4ListCode() + " and "
                + " HHID   = " + getHHID() + " and "
                + " MemID   = " + getMemID();
    }

    public String update_SrvMaxDate() {
        return "update RegNAssignProgSrv "
                + " set SrvMax =convert(date," + getSrvDT() + ") "
                + " where AdmCountryCode=" + getAdmCountryCode() + " and "
                + " AdmDonorCode = " + getAdmDonorCode() + " and "
                + " AdmAwardCode = " + getAdmAwardCode() + " and "
                + " ProgCode = " + getProgCode() + " and "
                + " SrvCode  = " + getSrvCode() + " and "
                + " LayR1ListCode   = " + getLayR1ListCode() + " and "
                + " LayR2ListCode   = " + getLayR2ListCode() + " and "
                + " LayR3ListCode   = " + getLayR3ListCode() + " and "
                + " LayR4ListCode   = " + getLayR4ListCode() + " and "
                + " HHID   = " + getHHID() + " and "
                + " MemID   = " + getMemID();
    }


    public String deleteMemberFromDistTable() {
        return "DELETE FROM [DistTable] " +
                "WHERE AdmCountryCode = " + getAdmCountryCode() +
                "  AND AdmDonorCode = " + getAdmDonorCode() +
                "  AND AdmAwardCode = " + getAdmAwardCode() +
                " AND LayR1ListCode = " + getLayR1ListCode() +
                " AND LayR2ListCode = " + getLayR2ListCode() +
                " AND LayR3ListCode = " + getLayR3ListCode() +
                " AND LayR4ListCode = " + getLayR4ListCode() +
                " AND ProgCode      = " + getProgCode() +
                " AND SrvCode       = " + getSrvCode() +
                " AND [OpMonthCode]       = " + getOpMonthCode() +
                " AND [ID]        = " + getID();

    }

    private String AttributeCode;
    private String AttributeValue;

    public String getAttributeCode() {
        return AttributeCode;
    }

    public void setAttributeCode(String attributeCode) {

        attributeCode = checkStringNull(attributeCode);
        AttributeCode = attributeCode;
    }

    public String getAttributeValue() {
        return AttributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        attributeValue = checkStringNull(attributeValue);
        AttributeValue = attributeValue;
    }

    private String AttPhoto;

    public String getAttPhoto() {
        return AttPhoto;
    }

    public void setAttPhoto(String attPhoto) {

        attPhoto = checkStringNull(attPhoto);
        AttPhoto = attPhoto;
    }

    public String insertIntoGPSLocationAttributesTable() {
        return " INSERT INTO [dbo].[GPSLocationAttributes] " +
                "            ([AdmCountryCode] " +
                "            ,[GrpCode] " +
                "            ,[SubGrpCode] " +
                "            ,[LocationCode] " +
                "            ,[AttributeCode] " +
                "            ,[AttributeValue] " +
                "            ,[AttPhoto] " +
                "            ,[EntryBy] " +
                "            ,[EntryDate]) " +
                "    VALUES " +
                "            ( " + getAdmCountryCode() +
                "            , " + getGrpCode() +
                "            , " + getSubGrpCode() +
                "            , " + getLocationCode() +
                "            ,  " + getAttributeCode() +
                "            , " + getAttributeValue() +
                "            , " + getAttPhoto() +
                "            ,  " + getEntryBy() +
                "            , " + getEntryDate() + ")";

    }


    private String BabyStatus;
    private String GMPAttendace;
    private String WeightStatus;
    private String NutAttendance;
    private String VitA_Under5;
    private String Exclusive_CurrentlyBF;
    private String DateCompFeeding;
    private String CMAMRef;
    private String CMAMAdd;
    private String ANCVisit;
    private String PNCVisit_2D;
    private String PNCVisit_1W;
    private String PNCVisit_6W;
    private String DeliveryStaff_1;
    private String DeliveryStaff_2;
    private String DeliveryStaff_3;
    private String HomeSupport24H_1d;
    private String HomeSupport24H_2d;
    private String HomeSupport24H_3d;
    private String HomeSupport24H_8d;
    private String HomeSupport24H_14d;
    private String HomeSupport24H_21d;
    private String HomeSupport24H_30d;
    private String HomeSupport24H_60d;
    private String HomeSupport24H_90d;
    private String HomeSupport48H_1d;
    private String HomeSupport48H_3d;
    private String HomeSupport48H_8d;
    private String HomeSupport48H_30d;
    private String HomeSupport48H_60d;
    private String HomeSupport48H_90d;
    private String Maternal_Bleeding;
    private String Maternal_Seizure;
    private String Maternal_Infection;
    private String Maternal_ProlongedLabor;
    private String Maternal_ObstructedLabor;
    private String Maternal_PPRM;
    private String NBorn_Asphyxia;
    private String NBorn_Sepsis;
    private String NBorn_Hypothermia;
    private String NBorn_Hyperthermia;
    private String NBorn_NoSuckling;
    private String NBorn_Jaundice;
    private String Child_Diarrhea;
    private String Child_Pneumonia;
    private String Child_Fever;
    private String Child_CerebralPalsy;
    private String Immu_Polio;
    private String Immu_BCG;
    private String Immu_Measles;
    private String Immu_DPT_HIB;
    private String Immu_Lotta;
    private String Immu_Other;
    private String FPCounsel_MaleCondom;
    private String FPCounsel_FemaleCondom;
    private String FPCounsel_Pill;
    private String FPCounsel_Depo;
    private String FPCounsel_LongParmanent;
    private String FPCounsel_NoMethod;
    private String CropCode;
    private String LoanSource;
    private String LoanAMT;
    private String AnimalCode;
    private String LeadCode;


    public String getBabyStatus() {
        return BabyStatus;
    }

    public void setBabyStatus(String babyStatus) {
        babyStatus = checkStringNull(babyStatus);
        BabyStatus = babyStatus;
    }

    public String getGMPAttendace() {
        return GMPAttendace;
    }

    public void setGMPAttendace(String GMPAttendace) {
        GMPAttendace = checkStringNull(GMPAttendace);
        this.GMPAttendace = GMPAttendace;
    }

    public String getWeightStatus() {
        return WeightStatus;
    }

    public void setWeightStatus(String weightStatus) {
        weightStatus = checkStringNull(weightStatus);
        WeightStatus = weightStatus;
    }

    public String getNutAttendance() {
        return NutAttendance;
    }

    public void setNutAttendance(String nutAttendance) {
        nutAttendance = checkStringNull(nutAttendance);
        NutAttendance = nutAttendance;
    }

    public String getVitA_Under5() {
        return VitA_Under5;
    }

    public void setVitA_Under5(String vitA_Under5) {
        vitA_Under5 = checkStringNull(vitA_Under5);
        VitA_Under5 = vitA_Under5;
    }

    public String getExclusive_CurrentlyBF() {

        return Exclusive_CurrentlyBF;
    }

    public void setExclusive_CurrentlyBF(String exclusive_CurrentlyBF) {
        exclusive_CurrentlyBF = checkStringNull(exclusive_CurrentlyBF);
        Exclusive_CurrentlyBF = exclusive_CurrentlyBF;
    }

    public String getDateCompFeeding() {
        return DateCompFeeding;
    }

    public void setDateCompFeeding(String dateCompFeeding) {
        dateCompFeeding = checkStringNull(dateCompFeeding);
        DateCompFeeding = dateCompFeeding;
    }

    public String getCMAMRef() {
        return CMAMRef;
    }

    public void setCMAMRef(String CMAMRef) {
        CMAMRef = checkStringNull(CMAMRef);
        this.CMAMRef = CMAMRef;
    }

    public String getCMAMAdd() {

        return CMAMAdd;
    }

    public void setCMAMAdd(String CMAMAdd) {
        CMAMAdd = checkStringNull(CMAMAdd);
        this.CMAMAdd = CMAMAdd;
    }

    public String getANCVisit() {
        return ANCVisit;
    }

    public void setANCVisit(String ANCVisit) {
        ANCVisit = checkStringNull(ANCVisit);
        this.ANCVisit = ANCVisit;
    }

    public String getPNCVisit_2D() {
        return PNCVisit_2D;
    }

    public void setPNCVisit_2D(String PNCVisit_2D) {
        PNCVisit_2D = checkStringNull(PNCVisit_2D);
        this.PNCVisit_2D = PNCVisit_2D;
    }

    public String getPNCVisit_1W() {
        return PNCVisit_1W;
    }

    public void setPNCVisit_1W(String PNCVisit_1W) {
        PNCVisit_1W = checkStringNull(PNCVisit_1W);
        this.PNCVisit_1W = PNCVisit_1W;
    }

    public String getPNCVisit_6W() {
        return PNCVisit_6W;
    }

    public void setPNCVisit_6W(String PNCVisit_6W) {
        PNCVisit_6W = checkStringNull(PNCVisit_6W);
        this.PNCVisit_6W = PNCVisit_6W;
    }

    public String getDeliveryStaff_1() {
        return DeliveryStaff_1;
    }

    public void setDeliveryStaff_1(String deliveryStaff_1) {
        deliveryStaff_1 = checkStringNull(deliveryStaff_1);
        DeliveryStaff_1 = deliveryStaff_1;
    }

    public String getDeliveryStaff_2() {
        return DeliveryStaff_2;
    }

    public void setDeliveryStaff_2(String deliveryStaff_2) {
        deliveryStaff_2 = checkStringNull(deliveryStaff_2);
        DeliveryStaff_2 = deliveryStaff_2;
    }

    public String getDeliveryStaff_3() {
        return DeliveryStaff_3;
    }

    public void setDeliveryStaff_3(String deliveryStaff_3) {
        deliveryStaff_3 = checkStringNull(deliveryStaff_3);
        DeliveryStaff_3 = deliveryStaff_3;
    }

    public String getHomeSupport24H_1d() {
        return HomeSupport24H_1d;
    }

    public void setHomeSupport24H_1d(String homeSupport24H_1d) {
        homeSupport24H_1d = checkStringNull(homeSupport24H_1d);
        HomeSupport24H_1d = homeSupport24H_1d;
    }

    public String getHomeSupport24H_2d() {
        return HomeSupport24H_2d;
    }

    public void setHomeSupport24H_2d(String homeSupport24H_2d) {
        homeSupport24H_2d = checkStringNull(homeSupport24H_2d);
        HomeSupport24H_2d = homeSupport24H_2d;
    }

    public String getHomeSupport24H_3d() {
        return HomeSupport24H_3d;
    }

    public void setHomeSupport24H_3d(String homeSupport24H_3d) {
        homeSupport24H_3d = checkStringNull(homeSupport24H_3d);
        HomeSupport24H_3d = homeSupport24H_3d;
    }

    public String getHomeSupport24H_8d() {
        return HomeSupport24H_8d;
    }

    public void setHomeSupport24H_8d(String homeSupport24H_8d) {
        homeSupport24H_8d = checkStringNull(homeSupport24H_8d);
        HomeSupport24H_8d = homeSupport24H_8d;
    }

    public String getHomeSupport24H_14d() {
        return HomeSupport24H_14d;
    }

    public void setHomeSupport24H_14d(String homeSupport24H_14d) {
        homeSupport24H_14d = checkStringNull(homeSupport24H_14d);
        HomeSupport24H_14d = homeSupport24H_14d;
    }

    public String getHomeSupport24H_21d() {
        return HomeSupport24H_21d;
    }

    public void setHomeSupport24H_21d(String homeSupport24H_21d) {
        homeSupport24H_21d = checkStringNull(homeSupport24H_21d);
        HomeSupport24H_21d = homeSupport24H_21d;
    }

    public String getHomeSupport24H_30d() {
        return HomeSupport24H_30d;
    }

    public void setHomeSupport24H_30d(String homeSupport24H_30d) {
        homeSupport24H_30d = checkStringNull(homeSupport24H_30d);
        HomeSupport24H_30d = homeSupport24H_30d;
    }

    private String getHomeSupport24H_60d() {

        return HomeSupport24H_60d;
    }

    public void setHomeSupport24H_60d(String homeSupport24H_60d) {
        homeSupport24H_60d = checkStringNull(homeSupport24H_60d);
        HomeSupport24H_60d = homeSupport24H_60d;
    }

    public String getHomeSupport24H_90d() {
        return HomeSupport24H_90d;
    }

    public void setHomeSupport24H_90d(String homeSupport24H_90d) {
        homeSupport24H_90d = checkStringNull(homeSupport24H_90d);
        HomeSupport24H_90d = homeSupport24H_90d;
    }

    public String getHomeSupport48H_1d() {
        return HomeSupport48H_1d;
    }

    public void setHomeSupport48H_1d(String homeSupport48H_1d) {
        homeSupport48H_1d = checkStringNull(homeSupport48H_1d);
        HomeSupport48H_1d = homeSupport48H_1d;
    }

    public String getHomeSupport48H_3d() {
        return HomeSupport48H_3d;
    }

    public void setHomeSupport48H_3d(String homeSupport48H_3d) {
        homeSupport48H_3d = checkStringNull(homeSupport48H_3d);
        HomeSupport48H_3d = homeSupport48H_3d;
    }

    public String getHomeSupport48H_8d() {
        return HomeSupport48H_8d;
    }

    public void setHomeSupport48H_8d(String homeSupport48H_8d) {
        homeSupport48H_8d = checkStringNull(homeSupport48H_8d);
        HomeSupport48H_8d = homeSupport48H_8d;
    }

    public String getHomeSupport48H_30d() {
        return HomeSupport48H_30d;
    }

    public void setHomeSupport48H_30d(String homeSupport48H_30d) {
        homeSupport48H_30d = checkStringNull(homeSupport48H_30d);
        HomeSupport48H_30d = homeSupport48H_30d;
    }

    public String getHomeSupport48H_60d() {
        return HomeSupport48H_60d;
    }

    public void setHomeSupport48H_60d(String homeSupport48H_60d) {
        homeSupport48H_60d = checkStringNull(homeSupport48H_60d);
        HomeSupport48H_60d = homeSupport48H_60d;
    }

    public String getHomeSupport48H_90d() {
        return HomeSupport48H_90d;
    }

    public void setHomeSupport48H_90d(String homeSupport48H_90d) {
        homeSupport48H_90d = checkStringNull(homeSupport48H_90d);
        HomeSupport48H_90d = homeSupport48H_90d;
    }

    public String getMaternal_Bleeding() {
        return Maternal_Bleeding;
    }

    public void setMaternal_Bleeding(String maternal_Bleeding) {
        maternal_Bleeding = checkStringNull(maternal_Bleeding);
        Maternal_Bleeding = maternal_Bleeding;
    }

    public String getMaternal_Seizure() {

        return Maternal_Seizure;
    }

    public void setMaternal_Seizure(String maternal_Seizure) {
        maternal_Seizure = checkStringNull(maternal_Seizure);
        Maternal_Seizure = maternal_Seizure;
    }

    public String getMaternal_Infection() {
        return Maternal_Infection;
    }

    public void setMaternal_Infection(String maternal_Infection) {
        maternal_Infection = checkStringNull(maternal_Infection);
        Maternal_Infection = maternal_Infection;
    }

    public String getMaternal_ProlongedLabor() {
        return Maternal_ProlongedLabor;
    }

    public void setMaternal_ProlongedLabor(String maternal_ProlongedLabor) {
        maternal_ProlongedLabor = checkStringNull(maternal_ProlongedLabor);
        Maternal_ProlongedLabor = maternal_ProlongedLabor;
    }

    public String getMaternal_ObstructedLabor() {
        return Maternal_ObstructedLabor;
    }

    public void setMaternal_ObstructedLabor(String maternal_ObstructedLabor) {
        maternal_ObstructedLabor = checkStringNull(maternal_ObstructedLabor);
        Maternal_ObstructedLabor = maternal_ObstructedLabor;
    }

    public String getMaternal_PPRM() {
        return Maternal_PPRM;
    }

    public void setMaternal_PPRM(String maternal_PPRM) {
        maternal_PPRM = checkStringNull(maternal_PPRM);
        Maternal_PPRM = maternal_PPRM;
    }

    public String getNBorn_Asphyxia() {
        return NBorn_Asphyxia;
    }

    public void setNBorn_Asphyxia(String NBorn_Asphyxia) {
        NBorn_Asphyxia = checkStringNull(NBorn_Asphyxia);
        this.NBorn_Asphyxia = NBorn_Asphyxia;
    }

    public String getNBorn_Sepsis() {
        return NBorn_Sepsis;
    }

    public void setNBorn_Sepsis(String NBorn_Sepsis) {
        NBorn_Sepsis = checkStringNull(NBorn_Sepsis);
        this.NBorn_Sepsis = NBorn_Sepsis;
    }

    public String getNBorn_Hypothermia() {
        return NBorn_Hypothermia;
    }

    public void setNBorn_Hypothermia(String NBorn_Hypothermia) {
        NBorn_Hypothermia = checkStringNull(NBorn_Hypothermia);
        this.NBorn_Hypothermia = NBorn_Hypothermia;
    }

    public String getNBorn_Hyperthermia() {
        return NBorn_Hyperthermia;
    }

    public void setNBorn_Hyperthermia(String NBorn_Hyperthermia) {
        NBorn_Hyperthermia = checkStringNull(NBorn_Hyperthermia);
        this.NBorn_Hyperthermia = NBorn_Hyperthermia;
    }

    public String getNBorn_NoSuckling() {
        return NBorn_NoSuckling;
    }

    public void setNBorn_NoSuckling(String NBorn_NoSuckling) {
        NBorn_NoSuckling = checkStringNull(NBorn_NoSuckling);
        this.NBorn_NoSuckling = NBorn_NoSuckling;
    }

    public String getNBorn_Jaundice() {
        return NBorn_Jaundice;
    }

    public void setNBorn_Jaundice(String NBorn_Jaundice) {
        NBorn_Jaundice = checkStringNull(NBorn_Jaundice);
        this.NBorn_Jaundice = NBorn_Jaundice;
    }

    public String getChild_Diarrhea() {
        return Child_Diarrhea;
    }

    public void setChild_Diarrhea(String child_Diarrhea) {
        child_Diarrhea = checkStringNull(child_Diarrhea);
        Child_Diarrhea = child_Diarrhea;
    }

    public String getChild_Pneumonia() {
        return Child_Pneumonia;
    }

    public void setChild_Pneumonia(String child_Pneumonia) {
        child_Pneumonia = checkStringNull(child_Pneumonia);
        Child_Pneumonia = child_Pneumonia;
    }

    public String getChild_Fever() {
        return Child_Fever;
    }

    public void setChild_Fever(String child_Fever) {
        child_Fever = checkStringNull(child_Fever);
        Child_Fever = child_Fever;
    }

    public String getChild_CerebralPalsy() {
        return Child_CerebralPalsy;
    }

    public void setChild_CerebralPalsy(String child_CerebralPalsy) {
        child_CerebralPalsy = checkStringNull(child_CerebralPalsy);
        Child_CerebralPalsy = child_CerebralPalsy;
    }

    public String getImmu_Polio() {
        return Immu_Polio;
    }

    public void setImmu_Polio(String immu_Polio) {
        immu_Polio = checkStringNull(immu_Polio);
        Immu_Polio = immu_Polio;
    }

    public String getImmu_BCG() {
        return Immu_BCG;
    }

    public void setImmu_BCG(String immu_BCG) {
        immu_BCG = checkStringNull(immu_BCG);
        Immu_BCG = immu_BCG;
    }

    public String getImmu_Measles() {
        return Immu_Measles;
    }

    public void setImmu_Measles(String immu_Measles) {
        immu_Measles = checkStringNull(immu_Measles);
        Immu_Measles = immu_Measles;
    }

    public String getImmu_DPT_HIB() {
        return Immu_DPT_HIB;
    }

    public void setImmu_DPT_HIB(String immu_DPT_HIB) {
        immu_DPT_HIB = checkStringNull(immu_DPT_HIB);
        Immu_DPT_HIB = immu_DPT_HIB;
    }

    public String getImmu_Lotta() {
        return Immu_Lotta;
    }

    public void setImmu_Lotta(String immu_Lotta) {
        immu_Lotta = checkStringNull(immu_Lotta);
        Immu_Lotta = immu_Lotta;
    }

    public String getImmu_Other() {
        return Immu_Other;
    }

    public void setImmu_Other(String immu_Other) {
        immu_Other = checkStringNull(immu_Other);
        Immu_Other = immu_Other;
    }

    public String getFPCounsel_MaleCondom() {
        return FPCounsel_MaleCondom;
    }

    public void setFPCounsel_MaleCondom(String FPCounsel_MaleCondom) {
        FPCounsel_MaleCondom = checkStringNull(FPCounsel_MaleCondom);
        this.FPCounsel_MaleCondom = FPCounsel_MaleCondom;
    }

    public String getFPCounsel_FemaleCondom() {
        return FPCounsel_FemaleCondom;
    }

    public void setFPCounsel_FemaleCondom(String FPCounsel_FemaleCondom) {
        FPCounsel_FemaleCondom = checkStringNull(FPCounsel_FemaleCondom);
        this.FPCounsel_FemaleCondom = FPCounsel_FemaleCondom;
    }

    public String getFPCounsel_Pill() {
        return FPCounsel_Pill;
    }

    public void setFPCounsel_Pill(String FPCounsel_Pill) {
        FPCounsel_Pill = checkStringNull(FPCounsel_Pill);
        this.FPCounsel_Pill = FPCounsel_Pill;
    }

    public String getFPCounsel_Depo() {
        return FPCounsel_Depo;
    }

    public void setFPCounsel_Depo(String FPCounsel_Depo) {
        FPCounsel_Depo = checkStringNull(FPCounsel_Depo);
        this.FPCounsel_Depo = FPCounsel_Depo;
    }

    public String getFPCounsel_LongParmanent() {
        return FPCounsel_LongParmanent;
    }

    public void setFPCounsel_LongParmanent(String FPCounsel_LongParmanent) {
        FPCounsel_LongParmanent = checkStringNull(FPCounsel_LongParmanent);
        this.FPCounsel_LongParmanent = FPCounsel_LongParmanent;
    }

    public String getFPCounsel_NoMethod() {
        return FPCounsel_NoMethod;
    }

    public void setFPCounsel_NoMethod(String FPCounsel_NoMethod) {
        FPCounsel_NoMethod = checkStringNull(FPCounsel_NoMethod);
        this.FPCounsel_NoMethod = FPCounsel_NoMethod;
    }

    public String getCropCode() {
        return CropCode;
    }

    public void setCropCode(String cropCode) {
        cropCode = checkStringNull(cropCode);
        CropCode = cropCode;
    }

    public String getLoanSource() {
        return LoanSource;
    }

    public void setLoanSource(String loanSource) {
        loanSource = checkStringNull(loanSource);
        LoanSource = loanSource;
    }

    public String getLoanAMT() {
        return LoanAMT;
    }

    public void setLoanAMT(String loanAMT) {
        loanAMT = checkStringNull(loanAMT);
        LoanAMT = loanAMT;
    }

    public String getAnimalCode() {
        return AnimalCode;
    }

    public void setAnimalCode(String animalCode) {
        animalCode = checkStringNull(animalCode);
        AnimalCode = animalCode;
    }

    public String getLeadCode() {
        return LeadCode;
    }

    public void setLeadCode(String leadCode) {
        leadCode = checkStringNull(leadCode);
        LeadCode = leadCode;
    }

    public String insertIntoSrvSpecificTable() {
        return "INSERT INTO [dbo].[SrvSpecific]" +
                "           ([AdmCountryCode] " +
                "           ,[AdmDonorCode]" +
                "           ,[AdmAwardCode] " +
                "           ,[LayR1ListCode] " +
                "           ,[LayR2ListCode] " +
                "           ,[LayR3ListCode] " +
                "           ,[LayR4ListCode] " +
                "           ,[HHID] " +
                "           ,[MemID] " +
                "           ,[ProgCode] " +
                "           ,[SrvCode] " +
                "           ,[OpCode] " +
                "           ,[OpMonthCode] " +
                "           ,[SrvCenterCode] " +
                "           ,[FDPCode] " +
                "           ,[SrvStatus] " +
                "           ,[BabyStatus] " +
                "           ,[GMPAttendace] " +
                "           ,[WeightStatus] " +
                "           ,[NutAttendance] " +
                "           ,[VitA_Under5] " +
                "           ,[Exclusive_CurrentlyBF] " +
                "           ,[DateCompFeeding] " +
                "           ,[CMAMRef] " +
                "           ,[CMAMAdd] " +
                "           ,[ANCVisit] " +
                "           ,[PNCVisit_2D] " +
                "           ,[PNCVisit_1W] " +
                "           ,[PNCVisit_6W] " +
                "           ,[DeliveryStaff_1] " +
                "           ,[DeliveryStaff_2] " +
                "           ,[DeliveryStaff_3] " +
                "           ,[HomeSupport24H_1d] " +
                "           ,[HomeSupport24H_2d] " +
                "           ,[HomeSupport24H_3d] " +
                "           ,[HomeSupport24H_8d] " +
                "           ,[HomeSupport24H_14d] " +
                "           ,[HomeSupport24H_21d] " +
                "           ,[HomeSupport24H_30d] " +
                "           ,[HomeSupport24H_60d] " +
                "           ,[HomeSupport24H_90d] " +
                "           ,[HomeSupport48H_1d] " +
                "           ,[HomeSupport48H_3d] " +
                "           ,[HomeSupport48H_8d] " +
                "           ,[HomeSupport48H_30d] " +
                "           ,[HomeSupport48H_60d] " +
                "           ,[HomeSupport48H_90d] " +
                "           ,[Maternal_Bleeding] " +
                "           ,[Maternal_Seizure] " +
                "           ,[Maternal_Infection] " +
                "           ,[Maternal_ProlongedLabor] " +
                "           ,[Maternal_ObstructedLabor] " +
                "           ,[Maternal_PPRM] " +
                "           ,[NBorn_Asphyxia] " +
                "           ,[NBorn_Sepsis] " +
                "           ,[NBorn_Hypothermia] " +
                "           ,[NBorn_Hyperthermia] " +
                "           ,[NBorn_NoSuckling] " +
                "           ,[NBorn_Jaundice] " +
                "           ,[Child_Diarrhea] " +
                "           ,[Child_Pneumonia] " +
                "           ,[Child_Fever] " +
                "           ,[Child_CerebralPalsy] " +
                "           ,[Immu_Polio] " +
                "           ,[Immu_BCG] " +
                "           ,[Immu_Measles] " +
                "           ,[Immu_DPT_HIB] " +
                "           ,[Immu_Lotta] " +
                "           ,[Immu_Other] " +
                "           ,[FPCounsel_MaleCondom] " +
                "           ,[FPCounsel_FemaleCondom] " +
                "           ,[FPCounsel_Pill] " +
                "           ,[FPCounsel_Depo] " +
                "           ,[FPCounsel_LongParmanent] " +
                "           ,[FPCounsel_NoMethod] " +
                "           ,[CropCode] " +
                "           ,[LoanSource] " +
                "           ,[LoanAMT] " +
                "           ,[AnimalCode] " +
                "           ,[LeadCode] " +
                "           ,[EntryBy] " +
                "           ,[EntryDate]) " +
                "     VALUES " +
                "           (  " + getAdmCountryCode() +
                "           ,  " + getAdmDonorCode() +
                "           ,  " + getAdmAwardCode() +
                "           ,  " + getLayR1ListCode() +
                "           ,  " + getLayR2ListCode() +
                "           ,  " + getLayR3ListCode() +
                "           ,  " + getLayR4ListCode() +
                "           ,  " + getHHID() +
                "           ,  " + getMemID() +
                "           ,  " + getProgCode() +
                "           ,  " + getSrvCode() +
                "           ,  " + getOpCode() +
                "           ,  " + getOpMonthCode() +
                "           ,  " + getSrvCenterCode() +
                "           ,  " + getFDPCode() +
                "           ,  " + getSrvStatus() +
                "           ,  " + getBabyStatus() +
                "           ,  " + getGMPAttendace() +
                "           ,  " + getWeightStatus() +
                "           ,  " + getNutAttendance() +
                "           ,  " + getVitA_Under5() +
                "           ,  " + getExclusive_CurrentlyBF() +
                "           ,  " + getDateCompFeeding() +
                "           ,  " + getCMAMRef() +
                "           ,  " + getCMAMAdd() +
                "           ,  " + getANCVisit() +
                "           ,  " + getPNCVisit_2D() +
                "           ,  " + getPNCVisit_1W() +
                "           ,  " + getPNCVisit_6W() +
                "           ,  " + getDeliveryStaff_1() +
                "           ,  " + getDeliveryStaff_2() +
                "           ,  " + getDeliveryStaff_3() +
                "           ,  " + getHomeSupport24H_1d() +
                "           ,  " + getHomeSupport24H_2d() +
                "           ,  " + getHomeSupport24H_3d() +
                "           ,  " + getHomeSupport24H_8d() +
                "           ,  " + getHomeSupport24H_14d() +
                "           ,  " + getHomeSupport24H_21d() +
                "           ,  " + getHomeSupport24H_30d() +
                "           ,  " + getHomeSupport24H_60d() +
                "           ,  " + getHomeSupport24H_90d() +
                "           ,  " + getHomeSupport48H_1d() +
                "           ,  " + getHomeSupport48H_3d() +
                "           ,  " + getHomeSupport48H_8d() +
                "           ,  " + getHomeSupport48H_30d() +
                "           ,  " + getHomeSupport48H_60d() +
                "           ,  " + getHomeSupport48H_90d() +
                "           ,  " + getMaternal_Bleeding() +
                "           ,  " + getMaternal_Seizure() +
                "           ,  " + getMaternal_Infection() +
                "           ,  " + getMaternal_ProlongedLabor() +
                "           ,  " + getMaternal_ObstructedLabor() +
                "           ,  " + getMaternal_PPRM() +
                "           ,  " + getNBorn_Asphyxia() +
                "           ,  " + getNBorn_Hyperthermia() +
                "           ,  " + getNBorn_Hypothermia() +
                "           ,  " + getNBorn_Hyperthermia() +
                "           ,  " + getNBorn_NoSuckling() +
                "           ,  " + getNBorn_Jaundice() +
                "           ,  " + getChild_Diarrhea() +
                "           ,  " + getChild_Pneumonia() +
                "           ,  " + getChild_Fever() +
                "           ,  " + getChild_CerebralPalsy() +
                "           ,  " + getImmu_Polio() +
                "           ,  " + getImmu_BCG() +
                "           ,  " + getImmu_Measles() +
                "           ,  " + getImmu_DPT_HIB() +
                "           ,  " + getImmu_Lotta() +
                "           ,  " + getImmu_Other() +
                "           ,  " + getFPCounsel_MaleCondom() +
                "           ,  " + getFPCounsel_FemaleCondom() +
                "           ,  " + getFPCounsel_Pill() +
                "           ,  " + getFPCounsel_Depo() +
                "           ,  " + getFPCounsel_LongParmanent() +
                "           ,  " + getFPCounsel_NoMethod() +
                "           ,  " + getCropCode() +
                "           ,  " + getLoanSource() +
                "           ,  " + getLoanAMT() +
                "           ,  " + getAnimalCode() +
                "           ,  " + getLeadCode() +
                "           ,  " + getEntryBy() +
                "           ,  " + getEntryDate() +
                " )";
    }

    public String updoadIntoSrvSpecific() {
        return "UPDATE [dbo].[SrvSpecific] " +
                "   SET  " +
                "       " +
                "      [BabyStatus] =  " + getBabyStatus() +
                "      ,[GMPAttendace] =  " + getGMPAttendace() +
                "      ,[WeightStatus] =  " + getWeightStatus() +
                "      ,[NutAttendance] =  " + getNutAttendance() +
                "      ,[VitA_Under5] =  " + getVitA_Under5() +
                "      ,[Exclusive_CurrentlyBF] =  " + getExclusive_CurrentlyBF() +
                "      ,[DateCompFeeding] =  " + getDateCompFeeding() +
                "      ,[CMAMRef] = " + getCMAMRef() +
                "      ,[CMAMAdd] =  " + getCMAMAdd() +
                "      ,[ANCVisit] =  " + getANCVisit() +
                "      ,[PNCVisit_2D] =  " + getPNCVisit_2D() +
                "      ,[PNCVisit_1W] =  " + getPNCVisit_1W() +
                "      ,[PNCVisit_6W] =  " + getPNCVisit_6W() +
                "      ,[DeliveryStaff_1] =  " + getDeliveryStaff_1() +
                "      ,[DeliveryStaff_2] =  " + getDeliveryStaff_2() +
                "      ,[DeliveryStaff_3] =  " + getDeliveryStaff_3() +
                "      ,[HomeSupport24H_1d] =  " + getHomeSupport24H_1d() +
                "      ,[HomeSupport24H_2d] =  " + getHomeSupport24H_2d() +
                "      ,[HomeSupport24H_3d] =  " + getHomeSupport24H_3d() +
                "      ,[HomeSupport24H_8d] =  " + getHomeSupport24H_8d() +
                "      ,[HomeSupport24H_14d] =  " + getHomeSupport24H_14d() +
                "      ,[HomeSupport24H_21d] =  " + getHomeSupport24H_21d() +
                "      ,[HomeSupport24H_30d] =  " + getHomeSupport24H_30d() +
                "      ,[HomeSupport24H_60d] =  " + getHomeSupport24H_60d() +
                "      ,[HomeSupport24H_90d] =  " + getHomeSupport24H_90d() +
                "      ,[HomeSupport48H_1d] =  " + getHomeSupport48H_1d() +
                "      ,[HomeSupport48H_3d] =  " + getHomeSupport48H_3d() +
                "      ,[HomeSupport48H_8d] =  " + getHomeSupport48H_8d() +
                "      ,[HomeSupport48H_30d] = " + getHomeSupport48H_30d() +
                "      ,[HomeSupport48H_60d] = " + getHomeSupport48H_60d() +
                "      ,[HomeSupport48H_90d] =  " + getHomeSupport48H_90d() +
                "      ,[Maternal_Bleeding] =  " + getMaternal_Bleeding() +
                "      ,[Maternal_Seizure] =  " + getMaternal_Seizure() +
                "      ,[Maternal_Infection] =  " + getMaternal_Infection() +
                "      ,[Maternal_ProlongedLabor] =  " + getMaternal_ProlongedLabor() +
                "      ,[Maternal_ObstructedLabor] =  " + getMaternal_ObstructedLabor() +
                "      ,[Maternal_PPRM] =  " + getMaternal_PPRM() +
                "      ,[NBorn_Asphyxia] =  " + getNBorn_Asphyxia() +
                "      ,[NBorn_Sepsis] =  " + getNBorn_Hyperthermia() +
                "      ,[NBorn_Hypothermia] =  " + getNBorn_Hypothermia() +
                "      ,[NBorn_Hyperthermia] =  " + getNBorn_Hyperthermia() +
                "      ,[NBorn_NoSuckling] =  " + getNBorn_NoSuckling() +
                "      ,[NBorn_Jaundice] =  " + getNBorn_Jaundice() +
                "      ,[Child_Diarrhea] =  " + getChild_Diarrhea() +
                "      ,[Child_Pneumonia] =  " + getChild_Pneumonia() +
                "      ,[Child_Fever] =  " + getChild_Fever() +
                "      ,[Child_CerebralPalsy] =  " + getChild_CerebralPalsy() +
                "      ,[Immu_Polio] =  " + getImmu_Polio() +
                "      ,[Immu_BCG] =  " + getImmu_BCG() +
                "      ,[Immu_Measles] =  " + getImmu_Measles() +
                "      ,[Immu_DPT_HIB] =  " + getImmu_DPT_HIB() +
                "      ,[Immu_Lotta] =  " + getImmu_Lotta() +
                "      ,[Immu_Other] =  " + getImmu_Other() +
                "      ,[FPCounsel_MaleCondom] =  " + getFPCounsel_MaleCondom() +
                "      ,[FPCounsel_FemaleCondom] =  " + getFPCounsel_FemaleCondom() +
                "      ,[FPCounsel_Pill] =  " + getFPCounsel_Pill() +
                "      ,[FPCounsel_Depo] =  " + getFPCounsel_Depo() +
                "      ,[FPCounsel_LongParmanent] =  " + getFPCounsel_LongParmanent() +
                "      ,[FPCounsel_NoMethod] = " + getFPCounsel_NoMethod() +
                "      ,[CropCode] =  " + getCropCode() +
                "      ,[LoanSource] =  " + getLoanSource() +
                "      ,[LoanAMT] =  " + getLoanAMT() +
                "      ,[AnimalCode] = " + getAnimalCode() +
                "      ,[LeadCode] =  " + getLeadCode() +
                "      ,[EntryBy] = " + getEntryBy() +
                "      ,[EntryDate] =  " + getEntryDate() +
                " WHERE  " +
                " " +
                " " +
                " [AdmCountryCode] =  " + getAdmCountryCode() +
                "     AND [AdmDonorCode]  =  " + getAdmDonorCode() +
                "     AND [AdmAwardCode]  =  " + getAdmAwardCode() +
                "     AND [LayR1ListCode] =  " + getLayR1ListCode() +
                "     AND [LayR2ListCode] =  " + getLayR2ListCode() +
                "     AND [LayR3ListCode] =  " + getLayR3ListCode() +
                "     AND [LayR4ListCode] =  " + getLayR4ListCode() +
                "     AND [HHID] =  " + getHHID() +
                "     AND [MemID] =  " + getMemID() +
                "     AND [ProgCode] =  " + getProgCode() +
                "     AND [SrvCode] =  " + getSrvCode() +
                "     AND [OpCode] =  " + getOpCode() +
                "     AND [OpMonthCode] =  " + getOpMonthCode() +
                "     AND [SrvCenterCode] =  " + getSrvCenterCode() +
                "     AND [FDPCode] = " + getFDPCode()
                ;
    }

    public String deleteFromSrvSpecific() {
        return "DELETE FROM [dbo].[SrvSpecific]" +
                "      WHERE [AdmCountryCode]= " + getAdmCountryCode() +
                "      AND [AdmDonorCode]  = " + getAdmDonorCode() +
                "      AND [AdmAwardCode]  = " + getAdmAwardCode() +
                "      AND [LayR1ListCode]  = " + getLayR1ListCode() +
                "      AND [LayR2ListCode]  = " + getLayR2ListCode() +
                "      AND [LayR3ListCode]  = " + getLayR3ListCode() +
                "      AND [LayR4ListCode]  = " + getLayR4ListCode() +
                "      AND [HHID]      = " + getHHID() +
                "      AND [MemID]      = " + getMemID() +
                "      AND [ProgCode]    = " + getProgCode() +
                "      AND [SrvCode]      = " + getSrvCode() +
                "      AND [OpCode]      = " + getOpCode() +
                "      AND [OpMonthCode]    = " + getOpMonthCode() +
                "      ";
    }

    private String contentCode;
    private String imageFile;
    private String remarks;
    private String fileType;

    public String getContentCode() {
        return contentCode;
    }

    public void setContentCode(String contentCode) {
        contentCode = checkStringNull(contentCode);
        this.contentCode = contentCode;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = checkStringNull(imageFile);

    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        remarks = checkStringNull(remarks);
        this.remarks = remarks;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        fileType = checkStringNull(fileType);
        this.fileType = fileType;
    }


    public String InsertIntoGPSLocationContentTable() {
        return "INSERT INTO [dbo].[GPSLocationContent]" +
                "(  [AdmCountryCode] " +
                ",  [GrpCode] " +
                ",  [SubGrpCode] " +
                ",  [LocationCode] " +
                ",  [ContentCode] " +
                ",  [ImageFile] " +
                ",  [Remarks] " +
                ",  [EntryBy] " +
                ",  [EntryDate] " +
                ",  [ImageFileString] " +
                ",  [FromANdroid] " +
                " ) " +
                "   VALUES "
                + "(" + getAdmCountryCode()
                + " , " + getGrpCode()
                + " , " + getSubGrpCode()
                + " , " + getLocationCode()
                + " , " + getContentCode()
                + " , convert(varbinary(max)," + getImageFile() + ")"
                + " , " + getRemarks()
                + " , " + getEntryBy()
                + " , " + getEntryDate()
                + " , " + getImageFile()
                + " , " + "'1'"
                + " ) ";
    }

    public String deleteFromGPSLocationContentTable() {
        return "DELETE FROM [dbo].[GPSLocationContent] " +
                " WHERE [AdmCountryCode] = " + getAdmCountryCode() +
                " AND [GrpCode] = " + getGrpCode() +
                " AND [SubGrpCode] = " + getSubGrpCode() +
                " AND [LocationCode] = " + getLocationCode() +
                " AND [ContentCode] = " + getContentCode();

    }

    private String Active;

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        active = checkStringNull(active);
        Active = active;
    }

    private String GrpLayR1ListCode;
    private String GrpLayR2ListCode;
    private String GrpLayR3ListCode;

    public String getGrpLayR1ListCode() {
        return GrpLayR1ListCode;
    }

    public void setGrpLayR1ListCode(String grpLayR1ListCode) {
        GrpLayR1ListCode = checkStringNull(grpLayR1ListCode);
    }

    public String getGrpLayR2ListCode() {
        return GrpLayR2ListCode;
    }

    public void setGrpLayR2ListCode(String grpLayR2ListCode) {
        GrpLayR2ListCode = checkStringNull(grpLayR2ListCode);
    }

    public String getGrpLayR3ListCode() {
        return GrpLayR3ListCode;
    }

    public void setGrpLayR3ListCode(String grpLayR3ListCode) {
        GrpLayR3ListCode = checkStringNull(grpLayR3ListCode);
    }

    public String insertInToRegNMemProgGrp() {
        return "INSERT INTO [dbo].[RegNMemProgGrp]"
                + " ([AdmCountryCode] "
                + " ,[LayR1ListCode] "
                + " ,[LayR2ListCode] "
                + " ,[LayR3ListCode] "
                + " ,[LayR4ListCode] "
                + " ,[AdmDonorCode] "
                + " ,[AdmAwardCode] "
                + " ,[HHID] "
                + " ,[MemID] "
                + " ,[ProgCode] "
                + " ,[SrvCode] "
                + " ,[GrpCode] "
                + " ,[Active] "
                + " ,[EntryBy] "
                + " ,[EntryDate]"
                + " ,[GrpLayR1ListCode] "
                + " ,[GrpLayR2ListCode] "
                + " ,[GrpLayR3ListCode] "
                + " ) "
                + "  VALUES "
                + "          (" + getAdmCountryCode()
                + " , " + getLayR1ListCode()
                + " , " + getLayR2ListCode()
                + " , " + getLayR3ListCode()
                + " , " + getLayR4ListCode()
                + " , " + getAdmDonorCode()
                + " , " + getAdmAwardCode()
                + " , " + getHHID()
                + " , " + getMemID()
                + " , " + getProgCode()
                + " , " + getSrvCode()
                + " , " + getGrpCode()
                + " , " + getActive()
                + " , " + getEntryBy()
                + " , " + getEntryDate()
                + " , " + getGrpLayR1ListCode()
                + " , " + getGrpLayR2ListCode()
                + " , " + getGrpLayR3ListCode()
                + " ) ";
    }

    public String UpdateRegNMemProgGrp() {
        return "UPDATE [dbo].[RegNMemProgGrp] "
                + " SET "
                + "[GrpCode] = " + getGrpCode()
                + ",[Active] =" + getActive()
                + ",[EntryBy] = " + getEntryBy()
                + ",[EntryDate] = " + getEntryDate()
                + ",[GrpLayR1ListCode] = " + getGrpLayR1ListCode()
                + ",[GrpLayR2ListCode] = " + getGrpLayR2ListCode()
                + " WHERE "
                + "[AdmCountryCode] = " + getAdmCountryCode()
                + " AND [LayR1ListCode] = " + getLayR1ListCode()
                + " AND [LayR2ListCode] = " + getLayR2ListCode()
                + " AND [LayR3ListCode] = " + getLayR3ListCode()
                + " AND [LayR4ListCode] = " + getLayR4ListCode()
                + " AND [AdmDonorCode] = " + getAdmDonorCode()
                + " AND [AdmAwardCode] = " + getAdmAwardCode()
                + " AND [HHID] = " + getHHID()
                + " AND [MemID] = " + getMemID()
                + " AND [ProgCode] = " + getProgCode()
                + " AND [SrvCode] = " + getSrvCode();
    }


    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        organizationCode = checkStringNull(organizationCode);
        this.organizationCode = organizationCode;
    }

    String organizationCode;

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        staffCode = checkStringNull(staffCode);
        this.staffCode = staffCode;
    }

    String staffCode;

    public String getLandSizeUnderIrrigation() {
        return landSizeUnderIrrigation;
    }

    public void setLandSizeUnderIrrigation(String landSizeUnderIrrigation) {
        landSizeUnderIrrigation = checkStringNull(landSizeUnderIrrigation);
        this.landSizeUnderIrrigation = landSizeUnderIrrigation;
    }

    String landSizeUnderIrrigation;

    public String getIrrigationSystemUsed() {
        return irrigationSystemUsed;
    }

    public void setIrrigationSystemUsed(String irrigationSystemUsed) {
        irrigationSystemUsed = checkStringNull(irrigationSystemUsed);
        this.irrigationSystemUsed = irrigationSystemUsed;
    }

    String irrigationSystemUsed;

    public String getFundSupport() {
        return fundSupport;
    }

    public void setFundSupport(String fundSupport) {
        fundSupport = checkStringNull(fundSupport);
        this.fundSupport = fundSupport;
    }

    String fundSupport;

    public String getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(String representativeName) {
        representativeName = checkStringNull(representativeName);
        this.representativeName = representativeName;
    }

    String representativeName;

    public String getRepresentativePhoneNumber() {
        return representativePhoneNumber;
    }

    public void setRepresentativePhoneNumber(String representativePhoneNumber) {
        representativePhoneNumber = checkIntNull(representativePhoneNumber);
        this.representativePhoneNumber = representativePhoneNumber;
    }

    String representativePhoneNumber;

    public String getFormationDate() {
        return formationDate;
    }

    public void setFormationDate(String formationDate) {
        formationDate = checkStringNull(formationDate);
        this.formationDate = formationDate;
    }

    String formationDate;

    public String getTypeOfGroup() {
        return typeOfGroup;
    }

    public void setTypeOfGroup(String typeOfGroup) {
        typeOfGroup = checkStringNull(typeOfGroup);
        this.typeOfGroup = typeOfGroup;
    }

    String typeOfGroup;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;

    public String getProjectNo() {
        return ProjectNo;
    }

    public void setProjectNo(String projectNo) {
        projectNo = checkStringNull(projectNo);
        ProjectNo = projectNo;
    }

    String ProjectNo;

    public String getProjectTitle() {
        return ProjectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        projectTitle = checkStringNull(projectTitle);
        ProjectTitle = projectTitle;
    }

    String ProjectTitle;


    public String insertIntoCommunityGrpDetail() {
        return "INSERT INTO [dbo].[CommunityGrpDetail]" +
                " ( [AdmCountryCode] "
                + "  ,  [AdmDonorCode] "
                + "   , [AdmAwardCode] "
                + "   , [AdmProgCode]  "
                + "   , [GrpCode] "
                + "   , [OrgCode] "
                + "   , [StfCode] "
                + "   , [LandSizeUnderIrrigation] "
                + "   , [IrrigationSystemUsed] "
                + "   , [FundSupport] "
                + "   , [ActiveStatus] "
                + "   , [RepName] "
                + "   , [RepPhoneNumber] "
                + "   , [FormationDate] "
                + "   , [TypeOfGroup] "
                + "   , [Status] "
                + "   , [EntryBy] "
                + "   , [EntryDate] "
                + "   , [ProjectNo] "
                + "   , [ProjectTitle] "
                + "   , [LayR1Code] "
                + "   , [LayR2Code] "
                + "   , [LayR3Code] "
                + " )"
                + " VALUES "
                + " ( "
                + getAdmCountryCode()
                + " , " + getAdmDonorCode()
                + " , " + getAdmAwardCode()
                + " , " + getProgCode()
                + " , " + getGrpCode()
                + " , " + getOrganizationCode()
                + " , " + getStaffCode()
                + " , " + getLandSizeUnderIrrigation()
                + " , " + getIrrigationSystemUsed()
                + " , " + getFundSupport()
                + " , " + getActive()
                + " , " + getRepresentativeName()
                + " , " + getRepresentativePhoneNumber()
                + " , " + getFormationDate()
                + " , " + getTypeOfGroup()
                + " , " + getStatus()
                + " , " + getEntryBy()
                + " , " + getEntryDate()
                + " , " + getProjectNo()
                + " , " + getProjectTitle()
                + " , " + getLayR1ListCode()
                + " , " + getLayR2ListCode()
                + " , " + getLayR3ListCode()
                + " ) ";

    }

    private String GrpName;
    private String GrpCatCode;

    public String getGrpName() {
        return GrpName;
    }

    public void setGrpName(String grpName) {
        grpName = checkStringNull(grpName);
        GrpName = grpName;
    }

    public String getGrpCatCode() {
        return GrpCatCode;
    }

    public void setGrpCatCode(String grpCatCode) {
        grpCatCode = checkStringNull(grpCatCode);
        GrpCatCode = grpCatCode;
    }
    // TODO: 10/6/2016  there is a na error

    /**
     * this Sp insert  Community Group
     *
     * @return string
     */

    public String insertIntoCommunityGroupTable() {
        return "INSERT INTO [dbo].[CommunityGroup] " +
                "([AdmCountryCode] " +
                " ,[AdmDonorCode] " +
                " ,[AdmAwardCode] " +
                " ,[AdmProgCode] " +
                " ,[GrpCode] " +
                " ,[GrpName] " +
                " ,[GrpCatCode] " +
                " ,[LayR1Code] " +
                " ,[LayR2Code] " +
                " ,[LayR3Code] " +
                " ,[EntryBy] " +
                " ,[EntryDate] " +
                " ) " +
                " VALUES " +
                "         ( " + getAdmCountryCode() +
                " , " + getAdmDonorCode() +
                " , " + getAdmAwardCode() +
                " , " + getProgCode() +
                " , " + getGrpCode() +
                " , " + getGrpName() +
                " , " + getGrpCatCode() +
                " , " + getLayR1ListCode() +
                " , " + getLayR2ListCode() +
                " , " + getLayR3ListCode() +
                " , " + getEntryBy() +
                " , " + getEntryDate() +

                " )";
    }


    public String updateIntoCommunityGrpDetail(LayRCodes oldLayRCodes) {
        String condition;

        if (oldLayRCodes.getLayR1Code().length() > 0 && oldLayRCodes.getLayR2Code().length() > 0 && oldLayRCodes.getLayR2Code().length() > 0) {
            condition = " AND            [LayR1Code] = " + checkStringNull(oldLayRCodes.getLayR1Code()) +
                    " AND            [LayR2Code] = " + checkStringNull(oldLayRCodes.getLayR2Code()) +
                    " AND            [LayR3Code] = " + checkStringNull(oldLayRCodes.getLayR3Code());
        } else {
            condition = "";
        }

        return "UPDATE [dbo].[CommunityGrpDetail] " +
                " SET " + "   [OrgCode] = " + getOrganizationCode() +
                "     ,          [StfCode] = " + getStaffCode() +
                "     ,          [LandSizeUnderIrrigation] = " + getLandSizeUnderIrrigation() +
                "     ,          [IrrigationSystemUsed] = " + getIrrigationSystemUsed() +
                "     ,          [FundSupport] = " + getFundSupport() +
                "     ,          [ActiveStatus] = " + getActive() +
                "     ,          [RepName] = " + getRepresentativeName() +
                "     ,          [RepPhoneNumber] = " + getRepresentativePhoneNumber() +
                "     ,          [FormationDate] = " + getFormationDate() +
                "     ,          [TypeOfGroup] = " + getTypeOfGroup() +
                "     ,          [Status] = " + getStatus() +
                "     ,          [EntryBy] = " + getEntryBy() +
                "     ,          [EntryDate] = " + getEntryDate() +
                "     ,          [ProjectNo] = " + getProjectNo() +
                "     ,          [ProjectTitle] = " + getProjectTitle() +
                "     ,          [LayR1Code] = " + getLayR1ListCode() +
                "     ,          [LayR2Code] = " + getLayR2ListCode() +
                "     ,          [LayR3Code] = " + getLayR3ListCode() +
                " WHERE          [AdmCountryCode] = " + getAdmCountryCode() +
                " AND            [AdmDonorCode] = " + getAdmDonorCode() +
                " AND            [AdmAwardCode] = " + getAdmAwardCode() +
                " AND            [AdmProgCode] = " + getProgCode() +
                " AND            [GrpCode] = " + getGrpCode() +
                condition


                ;
    }


    public String updateIntoCommunityGrpDetailIfLayR3CodeNotExit(LayRCodes oldLayRCodes) {
        String condition;

        if (oldLayRCodes.getLayR1Code().length() > 0 && oldLayRCodes.getLayR2Code().length() > 0) {
            condition = " AND            [LayR1Code] = " + checkStringNull(oldLayRCodes.getLayR1Code()) +
                    " AND            [LayR2Code] = " + checkStringNull(oldLayRCodes.getLayR2Code());

        } else {
            condition = "";
        }

        return "UPDATE [dbo].[CommunityGrpDetail] " +
                " SET " + "   [OrgCode] = " + getOrganizationCode() +
                "     ,          [StfCode] = " + getStaffCode() +
                "     ,          [LandSizeUnderIrrigation] = " + getLandSizeUnderIrrigation() +
                "     ,          [IrrigationSystemUsed] = " + getIrrigationSystemUsed() +
                "     ,          [FundSupport] = " + getFundSupport() +
                "     ,          [ActiveStatus] = " + getActive() +
                "     ,          [RepName] = " + getRepresentativeName() +
                "     ,          [RepPhoneNumber] = " + getRepresentativePhoneNumber() +
                "     ,          [FormationDate] = " + getFormationDate() +
                "     ,          [TypeOfGroup] = " + getTypeOfGroup() +
                "     ,          [Status] = " + getStatus() +
                "     ,          [EntryBy] = " + getEntryBy() +
                "     ,          [EntryDate] = " + getEntryDate() +
                "     ,          [ProjectNo] = " + getProjectNo() +
                "     ,          [ProjectTitle] = " + getProjectTitle() +
                "     ,          [LayR1Code] = " + getLayR1ListCode() +
                "     ,          [LayR2Code] = " + getLayR2ListCode() +
                "     ,          [LayR3Code] = " + getLayR3ListCode() +
                " WHERE          [AdmCountryCode] = " + getAdmCountryCode() +
                " AND            [AdmDonorCode] = " + getAdmDonorCode() +
                " AND            [AdmAwardCode] = " + getAdmAwardCode() +
                " AND            [AdmProgCode] = " + getProgCode() +
                " AND            [GrpCode] = " + getGrpCode() +
                condition


                ;
    }

    /**
     * this method implemented  in
     *
     * @return
     * @see
     */
    public String updateIntoCommunityGroupTable(LayRCodes oldLayRCodes) {

        String condition;
        if (oldLayRCodes.getLayR1Code().length() > 0 && oldLayRCodes.getLayR2Code().length() > 0 && oldLayRCodes.getLayR2Code().length() > 0) {
            condition = " AND            [LayR1Code] = " + checkStringNull(oldLayRCodes.getLayR1Code()) +
                    " AND            [LayR2Code] = " + checkStringNull(oldLayRCodes.getLayR2Code()) +
                    " AND            [LayR3Code] = " + checkStringNull(oldLayRCodes.getLayR3Code());
        } else {
            condition = "";
        }
        return "UPDATE CommunityGroup SET "
                + " GrpName = " + getGrpName()
                + ",GrpCatCode = " + getGrpCatCode()
                // TODO: 10/18/2016  layR3 Code

                + "   ,  LayR1Code = " + getLayR1ListCode()
                + "   , LayR2Code = " + getLayR2ListCode()
                + "   , LayR3Code = " + getLayR3ListCode()
                + " , EntryBy = " + getEntryBy()
                + " , EntryDate = " + getEntryDate()
                + "        WHERE AdmCountryCode = " + getAdmCountryCode()
                + "       AND AdmDonorCode = " + getAdmDonorCode()
                + "       AND AdmAwardCode = " + getAdmAwardCode()
                + "       AND AdmProgCode = " + getProgCode()
                + "       AND GrpCode = " + getGrpCode()
                + condition

                ;
    }

    public String sqlSpRegNMemAwardProgCombN_Save() {
        return " RegNMemAwardProgCombN_Save "
                + getAdmCountryCode() + " , "
                + getAdmDonorCode() + " , "
                + getAdmAwardCode() + " , "
                + getLayR1ListCode() + " , "
                + getLayR2ListCode() + " , "
                + getLayR3ListCode() + " , "
                + getLayR4ListCode() + " , "
                + getHHID() + " , "
                + getMemID() + " , "
                + getEntryBy() + " , "
                + getEntryDate();
    }

    private String WealthRanking;
    private String MemberExtGroup;

    public String getMemberExtGroup() {
        return MemberExtGroup;
    }

    public void setMemberExtGroup(String memberExtGroup) {
        MemberExtGroup = checkStringNull(memberExtGroup);
    }

    public String getWealthRanking() {
        return WealthRanking;
    }

    public void setWealthRanking(String wealthRanking) {
        WealthRanking = checkStringNull(wealthRanking);
    }

    public String sqlSpRegN_WE_Save_MW() {
        return " RegN_WE_Save_MW "
                + getAdmCountryCode() + " , "
                + getLayR1ListCode() + " , "
                + getLayR2ListCode() + " , "
                + getLayR3ListCode() + " , "
                + getLayR4ListCode() + " , "
                + getHHID() + " , "
                + getMemID() + " , "
                + getRegNDate() + " , "
                + getWealthRanking() + " , "
                + getMemberExtGroup() + " , "
                + getEntryBy() + " , "
                + getEntryDate();
    }

    public String sqlSpRegNAssignProgSrv_Save() {
        return " RegNAssignProgSrv_Save "
                + getAdmCountryCode() + " , "
                + getLayR1ListCode() + " , "
                + getLayR2ListCode() + " , "
                + getLayR3ListCode() + " , "
                + getLayR4ListCode() + " , "
                + getAdmDonorCode() + " , "
                + getAdmAwardCode() + " , "
                + getHHID() + " , "
                + getMemID() + " , "
                + getProgCode() + " , "
                + getSrvCode() + " , "
                + getRegNDate() + " , "
                + getEntryBy() + " , "
                + getEntryDate();

    }


    /**
     * insert into the DTResponse table there are relevant tables that needs to be updated accordingly
     * the sp insert the data into relevent table
     *
     * @return store procedure of sql server DTShortName_Save
     */
    public String sqlSpDTShortName_Save() {
        return " DTShortName_Save "
                + getDTBasic() + " , "
                + getDtShortName() + " , " // table Name
                + getAdmCountryCode() + " , "
                + getAdmDonorCode() + " , "
                + getAdmAwardCode() + " , "
                + getAdmProgCode() + " , "
                + getDTEnuID() + " , "
                + getOpMonthCode();
    }

    private String getLTp2Hectres() {
        return LTp2Hectres;
    }

    public void setLTp2Hectres(String LTp2Hectres) {
        LTp2Hectres = checkStringNull(LTp2Hectres);
        this.LTp2Hectres = LTp2Hectres;
    }

    private String LTp2Hectres;

    public String getLT3mFoodStock() {
        return LT3mFoodStock;
    }

    public void setLT3mFoodStock(String LT3mFoodStock) {
        LT3mFoodStock = checkStringNull(LT3mFoodStock);
        this.LT3mFoodStock = LT3mFoodStock;
    }

    private String LT3mFoodStock;

    public String getNoMajorCommonLiveStock() {
        return NoMajorCommonLiveStock;
    }

    public void setNoMajorCommonLiveStock(String noMajorCommonLiveStock) {
        noMajorCommonLiveStock = checkStringNull(noMajorCommonLiveStock);
        NoMajorCommonLiveStock = noMajorCommonLiveStock;
    }

    private String NoMajorCommonLiveStock;

    public String getReceiveNoFormalWages() {
        return ReceiveNoFormalWages;
    }

    public void setReceiveNoFormalWages(String receiveNoFormalWages) {
        receiveNoFormalWages = checkStringNull(receiveNoFormalWages);
        ReceiveNoFormalWages = receiveNoFormalWages;
    }

    private String ReceiveNoFormalWages;

    public String getNoIGA() {
        return NoIGA;
    }

    public void setNoIGA(String noIGA) {
        noIGA = checkStringNull(noIGA);
        NoIGA = noIGA;
    }

    private String NoIGA;

    public String getRelyPiecework() {
        return RelyPiecework;
    }

    public void setRelyPiecework(String relyPiecework) {
        relyPiecework = checkStringNull(relyPiecework);
        RelyPiecework = relyPiecework;
    }

    private String RelyPiecework;


    public String insertIntoRegNHHtableForMalawi() {
        return "INSERT INTO [dbo].[RegNHHTable]" +
                "           ([AdmCountryCode]" +
                "           ,[LayR1ListCode]" +
                "           ,[LayR2ListCode]" +
                "           ,[LayR3ListCode]" +
                "           ,[LayR4ListCode]" +
                "           ,[HHID]" +
                "           ,[RegNDate]" +
                "           ,[HHHeadName]" +
                "           ,[HHHeadSex]" +
                "           ,[HHSize]" +
                "           ,[GPSLat]" +
                "           ,[GPSLong]" +
                "           ,[AGLand]" +
                "           ,[VStatus]" +
                "           ,[MStatus]" +
                "           ,[RegNAddLookupCode]" +
                "           ,[WRank]" +
                "           ,[EntryBy]" +
                "           ,[EntryDate]" +
                "           ,[VSLAGroup]" +
                "           ,[LTp2Hectres]" +
                "           ,[LT3mFoodStock]" +
                "           ,[NoMajorCommonLiveStock]" +
                "           ,[ReceiveNoFormalWages]" +
                "           ,[NoIGA]" +
                "           ,[RelyPiecework]" +
                ")" +
                "VALUES " +
                "("
                + getAdmCountryCode()
                + "," + getLayR1ListCode()
                + "," + getLayR2ListCode()
                + "," + getLayR3ListCode()
                + "," + getLayR4ListCode()
                + "," + getHHID()
                + "," + getHhRegNDate()
                + "," + getHhHHHeadName()
                + "," + getHhHHHeadSex()
                + "," + getHhHHSize()
                + "," + getHhGPSLate()
                + "," + getHhGPSLong()
                + "," + getHhAGLand()
                + "," + getHhVStatus()
                + "," + getHhMStatus()
                + "," + getRegNAddLookupCode()
                + "," + getWRank()
                + "," + getEntryBy()
                + "," + getEntryDate()
                + "," + getHhVSLAGroup()
                + "," + getLTp2Hectres()
                + "," + getLT3mFoodStock()
                + "," + getNoMajorCommonLiveStock()
                + "," + getReceiveNoFormalWages()
                + "," + getNoIGA()
                + "," + getRelyPiecework()
                + " )";
    }

    /*public String updateIntoCommunityGrpDetail() {
        return "UPDATE [dbo].[CommunityGrpDetail] " +
                " SET " + "[OrgCode] = " + getOrganizationCode() +
                " , " + "[StfCode] = " + getStaffCode() +
                " , " + "[LandSizeUnderIrrigation] = " + getLandSizeUnderIrrigation() +
                " , " + "[IrrigationSystemUsed] = " + getIrrigationSystemUsed() +
                " , " + "[FundSupport] = " + getFundSupport() +
                " , " + "[ActiveStatus] = " + getActive() +
                " , " + "[RepName] = " + getRepresentativeName() +
                " , " + "[RepPhoneNumber] = " + getRepresentativePhoneNumber() +
                " , " + "[FormationDate] = " + getFormationDate() +
                " , " + "[TypeOfGroup] = " + getTypeOfGroup() +
                " , " + "[Status] = " + getStatus() +
                " , " + "[EntryBy] = " + getEntryBy() +
                " , " + "[EntryDate] = " + getEntryDate() +
                " , " + "[ProjectNo] = " + getProjectNo() +
                " , " + "[ProjectTitle] = " + getProjectTitle() +
                " WHERE " + "[AdmCountryCode] = " + getAdmCountryCode() +
                " AND " + "[AdmDonorCode] = " + getAdmDonorCode() +
                " AND " + "[AdmAwardCode] = " + getAdmAwardCode() +
                " AND " + "[AdmProgCode] =" + getProgCode() +
                " AND " + "[GrpCode] = " + getGrpCode();
    }*/


    private String OrphanedChildren;
    private String ChildHeaded;
    private String ElderlyHeaded;
    private String ChronicallyIll;
    private String FemaleHeaded;
    private String CropFailure;
    private String ChildrenRecSuppFeedN;

    public String getOrphanedChildren() {
        return OrphanedChildren;
    }

    public void setOrphanedChildren(String orphanedChildren) {
        orphanedChildren = checkStringNull(orphanedChildren);
        OrphanedChildren = orphanedChildren;
    }

    public String getChildHeaded() {
        return ChildHeaded;
    }

    public void setChildHeaded(String childHeaded) {
        childHeaded = checkStringNull(childHeaded);
        ChildHeaded = childHeaded;
    }

    public String getElderlyHeaded() {
        return ElderlyHeaded;
    }

    public void setElderlyHeaded(String elderlyHeaded) {
        elderlyHeaded = checkStringNull(elderlyHeaded);
        ElderlyHeaded = elderlyHeaded;
    }

    public String getChronicallyIll() {
        return ChronicallyIll;
    }

    public void setChronicallyIll(String chronicallyIll) {
        chronicallyIll = checkStringNull(chronicallyIll);
        ChronicallyIll = chronicallyIll;
    }

    public String getFemaleHeaded() {
        return FemaleHeaded;
    }

    public void setFemaleHeaded(String femaleHeaded) {
        femaleHeaded = checkStringNull(femaleHeaded);
        FemaleHeaded = femaleHeaded;
    }

    public String getCropFailure() {
        return CropFailure;
    }

    public void setCropFailure(String cropFailure) {
        cropFailure = checkStringNull(cropFailure);
        CropFailure = cropFailure;
    }

    public String getChildrenRecSuppFeedN() {
        return ChildrenRecSuppFeedN;
    }

    public void setChildrenRecSuppFeedN(String childrenRecSuppFeedN) {
        childrenRecSuppFeedN = checkStringNull(childrenRecSuppFeedN);
        ChildrenRecSuppFeedN = childrenRecSuppFeedN;
    }

    public String insertIntoRegN_FFA_table() {
        return "INSERT INTO [dbo].[RegN_FFA] "
                + " ( [AdmCountryCode] "
                + " , [LayR1ListCode] "
                + " , [LayR2ListCode] "
                + " , [LayR3ListCode] "
                + " , [LayR4ListCode] "
                + " , [HHID] "
                + " , [MemID] "
                + " , [OrphanedChildren]"
                + " , [ChildHeaded] "
                + " , [ElderlyHeaded] "
                + " , [ChronicallyIll] "
                + " , [FemaleHeaded] "
                + " , [CropFailure] "
                + " , [ChildrenRecSuppFeedN] "
                + " , [Willingness] "
                + " , [EntryBy] "
                + " , [EntryDate] ) "
                + "VALUES ( "
                + getAdmCountryCode()
                + " , " + getLayR1ListCode()
                + " , " + getLayR2ListCode()
                + " , " + getLayR3ListCode()
                + " , " + getLayR4ListCode()
                + " , " + getHHID()
                + " , " + getMemID()
                + " , " + getOrphanedChildren()
                + " , " + getChildHeaded()
                + " , " + getElderlyHeaded()
                + " , " + getChronicallyIll()
                + " , " + getFemaleHeaded()
                + " , " + getCropFailure()
                + " , " + getChildrenRecSuppFeedN()
                + " , " + getWillingness()
                + " , " + getEntryBy()
                + " , " + getEntryDate()
                + " ) ";
    }


    public String updateIntoRegN_FFA_table() {
        return " UPDATE [dbo].[RegN_FFA] "
                + "  SET "
                + "          [ChildHeaded]	= 	 " + getChildHeaded()
                + "  ,[ElderlyHeaded] = 	  " + getElderlyHeaded()
                + "  ,[ChronicallyIll] =	  " + getChronicallyIll()
                + "  ,[FemaleHeaded] =	  " + getFemaleHeaded()
                + "  ,[CropFailure]	= 	  " + getCropFailure()
                + "  ,[ChildrenRecSuppFeedN]= " + getChildrenRecSuppFeedN()
                + "  ,[Willingness] = " + getWillingness()
                + "  ,[EntryBy] =  " + getEntryBy()
                + "  ,[EntryDate]	=   " + getEntryDate()
                + "  WHERE MemID =  " + getMemID()
                + "          AND AdmCountryCode =  " + getAdmCountryCode()
                + "          AND LayR1ListCode =  " + getLayR1ListCode()
                + "          AND LayR2ListCode =  " + getLayR2ListCode()
                + "          AND LayR3ListCode =  " + getLayR3ListCode()
                + "          AND LayR4ListCode =  " + getLayR4ListCode()
                + "          AND HHID =  " + getHHID();
    }

// TODO: 10/6/2016  add syntax t for the Insert method for DTResponse table
    // TODO: 10/6/2016  add syntax for the update method for the DTResponse  Table
    // TODO: 10/6/2016  add syntax for the delete method for the  DTResponse  Table

    private String DTBasic;
    private String DtShortName;

    public String getDtShortName() {
        return DtShortName;
    }

    public void setDtShortName(String dtShortName) {
        DtShortName = checkStringNull(dtShortName);
    }

    public String getAdmProgCode() {
        return AdmProgCode;
    }

    public void setAdmProgCode(String admProgCode) {
        AdmProgCode = checkStringNull(admProgCode);
    }

    private String AdmProgCode;
    private String DTEnuID;

    public String getDTAValue() {
        return DTAValue;
    }

    public void setDTAValue(String DTAValue) {
        this.DTAValue = checkStringNull(DTAValue);
    }

    private String DTAValue;

    public String getDTRSeq() {
        return DTRSeq;
    }

    public void setDTRSeq(String DTRSeq) {
        this.DTRSeq = checkIntNull(DTRSeq);
    }

    private String DTRSeq;

    public String getDTACode() {
        return DTACode;
    }

    public void setDTACode(String DTACode) {
        this.DTACode = checkStringNull(DTACode);
    }

    private String DTACode;

    public String getDTBasic() {
        return DTBasic;
    }

    public void setDTBasic(String DTBasic) {
        this.DTBasic = checkStringNull(DTBasic);
    }

    public String getDTEnuID() {
        return DTEnuID;
    }

    public void setDTEnuID(String DTEnuID) {
        this.DTEnuID = checkStringNull(DTEnuID);
    }

    public String getProgActivityCode() {
        return ProgActivityCode;
    }

    public void setProgActivityCode(String progActivityCode) {
        ProgActivityCode = checkStringNull(progActivityCode);
    }

    private String ProgActivityCode;

    public String getDTTimeString() {
        return DTTimeString;
    }

    public void setDTTimeString(String DTTimeString) {
        this.DTTimeString = checkStringNull(DTTimeString);
    }

    private String DTTimeString;

    public String getOpMode() {
        return OpMode;
    }

    public void setOpMode(String opMode) {
        OpMode = checkStringNull(opMode);
    }

    private String OpMode;

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String dataType) {
        DataType = checkStringNull(dataType);
    }

    private String DataType;

    public String getCompleteness() {
        return Completeness;
    }

    public void setCompleteness(String completeness) {
        Completeness = checkStringNull(completeness);
    }

    private String Completeness;
    private String UFILE;

    public String getUFILE() {
        return UFILE;
    }

    public void setUFILE(String UFILE) {
        this.UFILE = checkStringNull(UFILE);
    }

    public String getDTQCode() {
        return DTQCode;
    }

    public void setDTQCode(String DTQCode) {
        this.DTQCode = checkStringNull(DTQCode);
    }

    private String DTQCode;
// // TODO: 2/16/2017 add photo

    public String insertIntoDTResponseTable() {
        return " INSERT INTO [dbo].[DTResponseTable]  " +
                "            ([DTBasic]  " +
                "            ,[AdmCountryCode]  " +
                "            ,[AdmDonorCode]  " +
                "            ,[AdmAwardCode]  " +
                "            ,[AdmProgCode]  " +
                "            ,[DTEnuID]  " +
                "            ,[DTQCode]  " +
                "            ,[DTACode]  " +
                "            ,[DTRSeq]  " +
                "            ,[DTAValue]  " +
                "            ,[ProgActivityCode]  " +
                "            ,[DTTimeString]  " +
                "            ,[OpMode]  " +
                "            ,[OpMonthCode]  " +
                "            ,[DataType]  " +
                "            ,[Completeness]" +
                "            ,[UFILE]" +
                " ) " +
                "    VALUES " +
                "            ( " + getDTBasic() +
                "            , " + getAdmCountryCode() +
                "            , " + getAdmDonorCode() +
                "            , " + getAdmAwardCode() +
                "            , " + getAdmProgCode() +
                "            , " + getDTEnuID() +
                "            , " + getDTQCode() +
                "            , " + getDTACode() +
                "            , " + getDTRSeq() +
                "            , " + getDTAValue() +
                "            , " + getProgActivityCode() +
                "            , " + getDTTimeString() +
                "            , " + getOpMode() +
                "            , " + getOpMonthCode() +
                "            , " + getDataType() +
                "            ," + getCompleteness() +
                "            ," + getUFILE()
                + ")";
    }


    public String updateIntoDTResponseTable() {
        return " UPDATE [dbo].[DTResponseTable]\n" +
                "        SET " +
                "        [DTAValue] = " + getDTAValue() +
                "        ,[ProgActivityCode] =  " + getProgActivityCode() +
                "        ,[DTTimeString] =  " + getDTTimeString() +
                "        ,[OpMode] =  " + getOpMode() +
                "        ,[OpMonthCode] =  " + getOpMonthCode() +
                "        ,[DataType] = " + getDataType() +
                "        ,[Completeness] = " + getCompleteness() +
                "        ,[UFILE] = " + getUFILE() +
                "        WHERE DTBasic =  " + getDTBasic()
                + "          AND AdmCountryCode =  " + getAdmCountryCode()
                + "          AND AdmDonorCode =  " + getAdmDonorCode()
                + "          AND AdmAwardCode =  " + getAdmAwardCode()
                + "          AND AdmProgCode =  " + getAdmProgCode()
                + "          AND DTEnuID =  " + getDTEnuID()
                + "          AND DTQCode =  " + getDTQCode()
                + "          AND DTACode =  " + getDTACode()
                + "          AND DTRSeq =  " + getDTRSeq();


    }

    public String deleteFromDTResponseTable() {
        return
                "DELETE FROM [dbo].[DTResponseTable] " +
                        "       WHERE DTBasic =  " + getDTBasic()
                        + "          AND AdmCountryCode =  " + getAdmCountryCode()
                        + "          AND AdmDonorCode =  " + getAdmDonorCode()
                        + "          AND AdmAwardCode =  " + getAdmAwardCode()
                        + "          AND AdmProgCode =  " + getAdmProgCode()
                        + "          AND DTEnuID =  " + getDTEnuID()
                        + "          AND DTRSeq =  " + getDTRSeq()
                        + "          AND OpMode =  " + getOpMode()
                        + "          AND OpMonthCode =  " + getOpMonthCode();


    }

    private String EventCode;
    private String PartID;
    private String IDCategory;
    private String PartName;
    private String PartOrgNCode;
    private String Sex;
    private String PartCatCode;
    private String PosCode;
    private String AMSession;
    private String PMSession;
    private String AtdnDate;
    private String TAGroup;

    public String getAMSession() {
        return AMSession;
    }

    public void setAMSession(String AMSession) {
        this.AMSession = checkIntNull(AMSession);
    }

    public String getAtdnDate() {
        return AtdnDate;
    }

    public void setAtdnDate(String atdnDate) {
        AtdnDate = checkStringNull(atdnDate);
    }

    public String getEventCode() {
        return EventCode;
    }

    public void setEventCode(String eventCode) {
        EventCode = checkStringNull(eventCode);
    }

    public String getIDCategory() {
        return IDCategory;
    }

    public void setIDCategory(String IDCategory) {
        this.IDCategory = checkStringNull(IDCategory);
    }

    public String getPartCatCode() {
        return PartCatCode;
    }

    public void setPartCatCode(String partCatCode) {
        PartCatCode = checkStringNull(partCatCode);
    }

    public String getPartID() {
        return PartID;
    }

    public void setPartID(String partID) {
        PartID = checkStringNull(partID);
    }

    public String getPartName() {
        return PartName;
    }

    public void setPartName(String partName) {
        PartName = checkStringNull(partName);
    }

    public String getPartOrgNCode() {
        return PartOrgNCode;
    }

    public void setPartOrgNCode(String partOrgNCode) {
        PartOrgNCode = checkStringNull(partOrgNCode);
    }

    public String getPMSession() {
        return PMSession;
    }

    public void setPMSession(String PMSession) {
        this.PMSession = checkIntNull(PMSession);
    }

    public String getPosCode() {
        return PosCode;
    }

    public void setPosCode(String posCode) {
        PosCode = checkStringNull(posCode);
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = checkStringNull(sex);
    }

    public String getTAGroup() {
        return TAGroup;
    }

    public void setTAGroup(String TAGroup) {
        this.TAGroup = checkStringNull(TAGroup);
    }

    public String insertIntoTAParticipantsList() {
        return
                "INSERT INTO [dbo].[TAParticipantsList] "
                        + " ([AdmCountryCode] "
                        + " ,[EventCode]  "
                        + " ,[PartID] "
                        + " ,[IDCategory] "
                        + " ,[PartName]   "
                        + " ,[PartOrgNCode]   "
                        + " ,[Sex]    "
                        + " ,[PartCatCode]    "
                        + " ,[PosCode]    "
                        + " ,[AMSession]  "
                        + " ,[PMSession]  "
                        + " ,[AtdnDate]   "
                        + " ,[EntryBy]    "
                        + " ,[EntryDate]  "
                        + " ,[TAGroup])   "
                        + " VALUES    "
                        + "         ( " + getAdmCountryCode()
                        + " , " + getEventCode()
                        + " , " + getPartID()
                        + " , " + getIDCategory()
                        + " , " + getPartName()
                        + " , " + getPartOrgNCode()
                        + " , " + getSex()
                        + " , " + getPartCatCode()
                        + " , " + getPosCode()
                        + " , " + getAMSession()
                        + " , " + getPMSession()
                        + " , " + getAtdnDate()
                        + " , " + getEntryBy()
                        + " , " + getEntryDate()
                        + " , " + getTAGroup()
                        + " )";
    }


    public String updateTAParticipantsList() {
        return " UPDATE  TAParticipantsList "
                + " SET     IDCategory = " + getIDCategory()
                + " , " + " PartName =  " + getPartName()
                + " , " + " PartOrgNCode =  " + getPartOrgNCode()
                + " , " + " Sex = " + getSex()
                + " , " + " PartCatCode =   " + getPartCatCode()
                + " , " + " PosCode =   " + getPosCode()
                + " , " + " AMSession =   " + getAMSession()
                + " , " + " PMSession =   " + getPMSession()
                + " , " + " EntryBy =   " + getEntryBy()
                + " , " + " EntryDate =  " + getEntryDate()
                + " , " + " TAGroup =  " + getTAGroup()
                + " , " + " AtdnDate = " + getAtdnDate()
                + " WHERE AdmCountryCode = " + getAdmCountryCode()
                + " AND EventCode = " + getEventCode()
                + " AND PartID = " + getPartID()
                + " AND AtdnDate = " + getAtdnDate();
    }
}
