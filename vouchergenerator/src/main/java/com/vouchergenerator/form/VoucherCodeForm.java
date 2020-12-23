package com.vouchergenerator.form;

import com.vouchergenerator.entities.SpecialOffer;
import com.vouchergenerator.entities.VoucherCode;

import java.util.List;

public class VoucherCodeForm {
    private Long id;
    private Long recipientID;
    private String email;
    private Long specialOfferID;
    private String code;
    private List<VoucherCode> validVoucherCodeList;
    private List<VoucherCode> redeemedVoucherCodeList;
    private List<VoucherCode> expiredVoucherCodeList;
    private Boolean voucherCodeExists;
    private Boolean generateSuccess;
    private List<SpecialOffer> specialOffers;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<VoucherCode> getValidVoucherCodeList() {
        return validVoucherCodeList;
    }

    public void setValidVoucherCodeList(List<VoucherCode> validVoucherCodeList) {
        this.validVoucherCodeList = validVoucherCodeList;
    }

    public Boolean getVoucherCodeExists() {
        return voucherCodeExists;
    }

    public void setVoucherCodeExists(Boolean voucherCodeExists) {
        this.voucherCodeExists = voucherCodeExists;
    }

    public List<VoucherCode> getRedeemedVoucherCodeList() {
        return redeemedVoucherCodeList;
    }

    public void setRedeemedVoucherCodeList(List<VoucherCode> redeemedVoucherCodeList) {
        this.redeemedVoucherCodeList = redeemedVoucherCodeList;
    }

    public List<VoucherCode> getExpiredVoucherCodeList() {
        return expiredVoucherCodeList;
    }

    public void setExpiredVoucherCodeList(List<VoucherCode> expiredVoucherCodeList) {
        this.expiredVoucherCodeList = expiredVoucherCodeList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getGenerateSuccess() {
        return generateSuccess;
    }

    public void setGenerateSuccess(Boolean generateSuccess) {
        this.generateSuccess = generateSuccess;
    }

    public List<SpecialOffer> getSpecialOffers() {
        return specialOffers;
    }

    public void setSpecialOffers(List<SpecialOffer> specialOffers) {
        this.specialOffers = specialOffers;
    }
}
