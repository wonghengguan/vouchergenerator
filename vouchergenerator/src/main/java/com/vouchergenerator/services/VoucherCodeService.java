package com.vouchergenerator.services;

import com.vouchergenerator.entities.VoucherCode;
import com.vouchergenerator.form.VoucherCodeForm;

import java.text.ParseException;
import java.util.List;

public interface VoucherCodeService {
    void generateVoucherCode(VoucherCodeForm form) throws ParseException;
    List<VoucherCode> getValidVoucherCodeForRecipient(String email) throws ParseException;
    List<VoucherCode> getExpiredVoucherForRecipient(String email) throws ParseException;
    List<VoucherCode> getRedeemedVoucherForRecipient(String email);
    List<VoucherCode> getVoucherCodeListBySpecialOfferID(Long specialOfferID);
    VoucherCodeForm useVoucherCode(VoucherCodeForm form) throws ParseException;
    Boolean checkIsVoucherCodeExpired(Long voucherCodeID);
    Boolean checkIsVoucherCodeUsed(Long voucherCodeID);
}
