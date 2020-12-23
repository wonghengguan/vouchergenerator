package com.vouchergenerator.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="voucherCodes")
public class VoucherCode extends Base {
    @Column(name="code")
    private String code;

    @Column(name="expirationDate")
    private Date expirationDate;

    @Column(name="redeemed")
    private Boolean redeemed;

    @Column(name="usedDate")
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

    public Boolean getRedeemed() {
        return redeemed;
    }

    public void setRedeemed(Boolean used) {
        redeemed = used;
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
