package com.vouchergenerator.services;

import com.vouchergenerator.entities.VoucherCode;
import com.vouchergenerator.form.VoucherCodeForm;

import java.util.List;

public interface VoucherCodeService {
    void generateVoucherCode(VoucherCodeForm form);
    List<VoucherCode> getVoucherCodeListByRecipientID(Long recipientID);
    List<VoucherCode> getVoucherCodeListBySpecialOfferID(Long specialOfferID);
    void useVoucherCode(VoucherCodeForm form);
}
