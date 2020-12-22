package com.vouchergenerator.form;

public class VoucherCodeForm {
    private Long id;
    private Long recipientID;
    private Long specialOfferID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(Long recipientID) {
        this.recipientID = recipientID;
    }

    public Long getSpecialOfferID() {
        return specialOfferID;
    }

    public void setSpecialOfferID(Long specialOfferID) {
        this.specialOfferID = specialOfferID;
    }
}
