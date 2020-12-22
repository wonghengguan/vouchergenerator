package com.vouchergenerator.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="voucherCodes")
public class VoucherCode extends Base {
    @Column(name="name")
    private String code;

    @Column(name="discountPercentage")
    private Date expirationDate;

    @Column(name="name")
    private Boolean isUsed;

    @Column(name="discountPercentage")
    private Date usedDate;

    @JoinColumn(name = "recipientID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Recipient recipient;

    @JoinColumn(name = "specialOfferID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SpecialOffer specialOffer;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Date getUsedDate() {
        return usedDate;
    }

    public void setUsedDate(Date usedDate) {
        this.usedDate = usedDate;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public SpecialOffer getSpecialOffer() {
        return specialOffer;
    }

    public void setSpecialOffer(SpecialOffer specialOffer) {
        this.specialOffer = specialOffer;
    }
}
