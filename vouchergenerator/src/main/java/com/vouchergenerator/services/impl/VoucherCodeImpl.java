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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public VoucherCodeForm generateAll(VoucherCodeForm form) throws ParseException {
        List<SpecialOffer> specialOffers = specialOfferRepo.findAll();
        List<Recipient> recipients = recipientRepo.findAll();

        List<VoucherCode> voucherCodeList = this.generateVoucherCode(specialOffers,recipients);
        form.setSpecialOffers(specialOffers);
        form.setGenerateSuccess(voucherCodeList!=null && voucherCodeList.size()>0);
        return form;
    }

    @Override
    public List<VoucherCode> generateVoucherCode(List<SpecialOffer> specialOffers, List<Recipient> recipients) throws ParseException {
        int voucherCodeLength = 8;
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

        List<VoucherCode> voucherCodeList = new ArrayList<>();
        for(SpecialOffer specialOffer:specialOffers) {
            for(Recipient recipient:recipients) {
                if (specialOffer != null) {
                    VoucherCode voucherCode = voucherCodeRepo.getVoucherCodeByRecipientEmailAndSpecialOfferID(recipient.getEmail(), specialOffer.getId());
                    if(voucherCode==null) {
                        StringBuilder sb = new StringBuilder(voucherCodeLength);
                        Random random = new SecureRandom();
                        for (int i = 0; i < voucherCodeLength; i++) {
                            char c = chars[random.nextInt(chars.length)];
                            sb.append(c);
                        }

                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.MONTH, 3);
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date expirationDate = calendar.getTime();
                        expirationDate = formatter.parse(formatter.format(expirationDate));

                        String voucherCodeString = sb.toString();
                        voucherCode = new VoucherCode();
                        voucherCode.setCode(voucherCodeString);
                        voucherCode.setExpirationDate(expirationDate);
                        voucherCode.setRedeemed(false);
                        voucherCode.setRecipient(recipient);
                        voucherCode.setSpecialOffer(specialOffer);

                        voucherCodeRepo.save(voucherCode);
                    }
                    voucherCodeList.add(voucherCode);
                }
            }
        }
        return voucherCodeList;
    }

    @Override
    public VoucherCodeForm generateVoucherCodeForSpecialOffer(VoucherCodeForm form) throws ParseException {
        List<Recipient> recipients = recipientRepo.findAll();
        List<SpecialOffer> specialOffers = new ArrayList<>();

        SpecialOffer specialOffer = specialOfferRepo.getOne(form.getSpecialOfferID());
        specialOffers.add(specialOffer);
        List<VoucherCode> voucherCodeList = this.generateVoucherCode(specialOffers,recipients);
        Boolean generateSuccess = voucherCodeList!=null && voucherCodeList.size()>0;

        form.setSpecialOffers(specialOffers);
        form.setGenerateSuccess(generateSuccess);

        return form;
    }

    @Override
    public List<VoucherCode> getValidVoucherCodeForRecipient(String email) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        today = formatter.parse(formatter.format(today));
        return voucherCodeRepo.getValidVoucherCodeForRecipient(email, today);
    }

    @Override
    public List<VoucherCode> getExpiredVoucherForRecipient(String email) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        today = formatter.parse(formatter.format(today));
        return voucherCodeRepo.getExpiredVoucherCodeForRecipient(email, today);
    }

    @Override
    public List<VoucherCode> getRedeemedVoucherForRecipient(String email) {
        return voucherCodeRepo.getRedeemedVoucherCodeForRecipient(email);
    }

    @Override
    public List<VoucherCode> getVoucherCodeListBySpecialOfferID(Long specialOfferID) {
        return voucherCodeRepo.getVoucherCodeBySpecialOffer(specialOfferID);
    }

    @Override
    public VoucherCodeForm useVoucherCode(VoucherCodeForm form) throws ParseException {
        Boolean voucherCodeExists = false;
        VoucherCode voucherCode = voucherCodeRepo.getVoucherCodeByCodeAndRecipientEmail(form.getCode(),form.getEmail());
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        today = formatter.parse(formatter.format(today));

        if(voucherCode!=null && voucherCode.getExpirationDate().after(today) && !voucherCode.getRedeemed()) {
            voucherCodeExists=true;
            if (!voucherCode.getRedeemed() && today.before(voucherCode.getExpirationDate())) {
                voucherCode.setRedeemed(true);
                voucherCode.setUsedDate(today);
                voucherCodeRepo.save(voucherCode);
            }
        }


        form.setValidVoucherCodeList(voucherCodeRepo.getValidVoucherCodeForRecipient(form.getEmail(), today));
        form.setRedeemedVoucherCodeList(voucherCodeRepo.getRedeemedVoucherCodeForRecipient(form.getEmail()));
        form.setExpiredVoucherCodeList(voucherCodeRepo.getExpiredVoucherCodeForRecipient(form.getEmail(), today));
        form.setVoucherCodeExists(voucherCodeExists);
        return form;
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
            return voucherCode.getRedeemed();
        }
        return false;
    }
}
