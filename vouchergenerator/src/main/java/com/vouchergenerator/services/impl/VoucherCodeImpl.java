package com.vouchergenerator.services.impl;

import com.vouchergenerator.entities.Recipient;
import com.vouchergenerator.entities.SpecialOffer;
import com.vouchergenerator.entities.VoucherCode;
import com.vouchergenerator.form.VoucherCodeForm;
import com.vouchergenerator.repo.RecipientRepo;
import com.vouchergenerator.repo.SpecialOfferRepo;
import com.vouchergenerator.repo.VoucherCodeRepo;
import com.vouchergenerator.services.VoucherCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service("voucherCodeService")
@Transactional
public class VoucherCodeImpl implements VoucherCodeService {
    @Autowired
    RecipientRepo recipientRepo;

    @Autowired
    SpecialOfferRepo specialOfferRepo;

    @Autowired
    VoucherCodeRepo voucherCodeRepo;

    @Override
    public void generateVoucherCode(VoucherCodeForm form) {
        Recipient recipient = recipientRepo.getOne(form.getRecipientID());
        SpecialOffer specialOffer = specialOfferRepo.getOne(form.getSpecialOfferID());
        if(recipient!=null && specialOffer!=null) {
            int voucherCodeLength = 8;
            char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
            StringBuilder sb = new StringBuilder(voucherCodeLength);
            Random random = new SecureRandom();
            for (int i = 0; i < voucherCodeLength; i++) {
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }
            String voucherCodeString = sb.toString();
            VoucherCode voucherCode = new VoucherCode();
            voucherCode.setCode(voucherCodeString);
            voucherCode.setExpirationDate(new Date());
            voucherCode.setUsed(false);
            voucherCode.setRecipient(recipient);
            voucherCode.setSpecialOffer(specialOffer);

            voucherCodeRepo.save(voucherCode);
        }
    }

    @Override
    public List<VoucherCode> getVoucherCodeListByRecipientID(Long recipientID) {
        return voucherCodeRepo.getVoucherCodeByRecipient(recipientID);
    }

    @Override
    public List<VoucherCode> getVoucherCodeListBySpecialOfferID(Long specialOfferID) {
        return voucherCodeRepo.getVoucherCodeBySpecialOffer(specialOfferID);
    }

    @Override
    public List<VoucherCode> useVoucherCode(VoucherCodeForm form) {
        VoucherCode voucherCode = voucherCodeRepo.getOne(form.getId());
        Date today = new Date();
        if(!voucherCode.getUsed() && today.before(voucherCode.getExpirationDate())) {
            voucherCode.setUsed(true);
            voucherCode.setUsedDate(today);
            voucherCodeRepo.save(voucherCode);
        }
        return this.getVoucherCodeListByRecipientID(form.getRecipientID());
    }

    @Override
    public Boolean checkIsVoucherCodeExpired(Long voucherCodeID) {
        VoucherCode voucherCode = voucherCodeRepo.getOne(voucherCodeID);
        if(voucherCode!=null) {
            Date today = new Date();
            return voucherCode.getExpirationDate().after(today);
        }
        return false;
    }

    @Override
    public Boolean checkIsVoucherCodeUsed(Long voucherCodeID) {
        VoucherCode voucherCode = voucherCodeRepo.getOne(voucherCodeID);
        if(voucherCode!=null) {
            return voucherCode.getUsed();
        }
        return false;
    }
}
