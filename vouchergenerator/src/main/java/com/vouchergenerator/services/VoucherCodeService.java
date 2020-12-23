package com.vouchergenerator.services;

import com.vouchergenerator.entities.Recipient;
import com.vouchergenerator.entities.SpecialOffer;
import com.vouchergenerator.entities.VoucherCode;
import com.vouchergenerator.form.VoucherCodeForm;

import java.text.ParseException;
import java.util.List;

public interface VoucherCodeService {
    VoucherCodeForm generateAll(VoucherCodeForm form) throws ParseException;
    List<VoucherCode> generateVoucherCode(List<SpecialOffer> specialOffers, List<Recipient> recipients) throws ParseException;
    VoucherCodeForm generateVoucherCodeForSpecialOffer(VoucherCodeForm form) throws ParseException;
    List<VoucherCode> getValidVoucherCodeForRecipient(String email) throws ParseException;
    List<VoucherCode> getExpiredVoucherForRecipient(String email) throws ParseException;
    List<VoucherCode> getRedeemedVoucherForRecipient(String email);
    List<VoucherCode> getVoucherCodeListBySpecialOfferID(Long specialOfferID);
    VoucherCodeForm useVoucherCode(VoucherCodeForm form) throws ParseException;
    Boolean checkIsVoucherCodeExpired(Long voucherCodeID);
    Boolean checkIsVoucherCodeUsed(Long voucherCodeID);
}
