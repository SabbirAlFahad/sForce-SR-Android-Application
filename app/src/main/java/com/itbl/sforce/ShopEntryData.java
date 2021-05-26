package com.itbl.sforce;

public class ShopEntryData {

    String cPerson1;
    String cPerson2;
    String sMobile1;
    String sMobile2;
    String sAddress1;
    String sAddress2;
    String sWebAddress;
    String sEmail;
    String sTradeLIC;
    String sVatNo;
    String sCreatedBy;
    String sCreateDate;

    public String getcPerson1() {
        return cPerson1;
    }

    public void setcPerson1(String cPerson1) {
        this.cPerson1 = cPerson1;
    }

    public String getcPerson2() {
        return cPerson2;
    }

    public void setcPerson2(String cPerson2) {
        this.cPerson2 = cPerson2;
    }

    public String getsMobile1() {
        return sMobile1;
    }

    public void setsMobile1(String sMobile1) {
        this.sMobile1 = sMobile1;
    }

    public String getsMobile2() {
        return sMobile2;
    }

    public void setsMobile2(String sMobile2) {
        this.sMobile2 = sMobile2;
    }

    public String getsAddress1() {
        return sAddress1;
    }

    public void setsAddress1(String sAddress1) {
        this.sAddress1 = sAddress1;
    }

    public String getsAddress2() {
        return sAddress2;
    }

    public void setsAddress2(String sAddress2) {
        this.sAddress2 = sAddress2;
    }

    public String getsWebAddress() {
        return sWebAddress;
    }

    public void setsWebAddress(String sWebAddress) {
        this.sWebAddress = sWebAddress;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsTradeLIC() {
        return sTradeLIC;
    }

    public void setsTradeLIC(String sTradeLIC) {
        this.sTradeLIC = sTradeLIC;
    }

    public String getsVatNo() {
        return sVatNo;
    }

    public void setsVatNo(String sVatNo) {
        this.sVatNo = sVatNo;
    }

    public String getsCreatedBy() {
        return sCreatedBy;
    }

    public void setsCreatedBy(String sCreatedBy) {
        this.sCreatedBy = sCreatedBy;
    }

    public String getsCreateDate() {
        return sCreateDate;
    }

    public void setsCreateDate(String sCreateDate) {
        this.sCreateDate = sCreateDate;
    }

    public ShopEntryData(String cPerson1, String cPerson2, String sMobile1, String sMobile2, String sAddress1,
                         String sAddress2, String sWebAddress, String sEmail, String sTradeLIC, String sVatNo,
                         String sCreatedBy, String sCreateDate) {

        this.cPerson1 = cPerson1;
        this.cPerson2 = cPerson2;
        this.sMobile1 = sMobile1;
        this.sMobile2 = sMobile2;
        this.sAddress1 = sAddress1;
        this.sAddress2 = sAddress2;
        this.sWebAddress = sWebAddress;
        this.sEmail = sEmail;
        this.sTradeLIC = sTradeLIC;
        this.sVatNo = sVatNo;
        this.sCreatedBy = sCreatedBy;
        this.sCreateDate = sCreateDate;
    }

}
