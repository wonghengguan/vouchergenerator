package com.vouchergenerator.controller;

import com.vouchergenerator.entities.VoucherCode;
import com.vouchergenerator.form.VoucherCodeForm;
import com.vouchergenerator.services.VoucherCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/voucherCode")
public class VoucherCodeController {

    @Autowired
    VoucherCodeService voucherCodeService;

    @RequestMapping(value="/generateVoucherCodeForSpecialOffer", method = RequestMethod.POST)
    public VoucherCodeForm generateVoucherCodeForSpecialOffer(@RequestBody VoucherCodeForm form) throws ParseException {
        return voucherCodeService.generateVoucherCodeForSpecialOffer(form);
    }

    @RequestMapping(value="/generateAll", method = RequestMethod.POST)
    public VoucherCodeForm generateAll(@RequestBody VoucherCodeForm form) throws ParseException {
        return voucherCodeService.generateAll(form);
    }

    @RequestMapping(value="/getValidVoucherForRecipient", method = RequestMethod.POST)
    public List<VoucherCode> getValidVoucherForRecipient(@RequestBody VoucherCodeForm form) throws ParseException {
        List<VoucherCode> voucherCodeList = voucherCodeService.getValidVoucherCodeForRecipient(form.getEmail());

        if(voucherCodeList!=null && voucherCodeList.size()>0) {
            return voucherCodeList;
        }

        return null;
    }

    @RequestMapping(value="/getExpiredVoucherForRecipient", method = RequestMethod.POST)
    public List<VoucherCode> getExpiredVoucherForRecipient(@RequestBody VoucherCodeForm form) throws ParseException {
        List<VoucherCode> voucherCodeList = voucherCodeService.getExpiredVoucherForRecipient(form.getEmail());

        if(voucherCodeList!=null && voucherCodeList.size()>0) {
            return voucherCodeList;
        }

        return null;
    }

    @RequestMapping(value="/getRedeemedVoucherForRecipient", method = RequestMethod.POST)
    public List<VoucherCode> getRedeemedVoucherForRecipient(@RequestBody VoucherCodeForm form) {
        List<VoucherCode> voucherCodeList = voucherCodeService.getRedeemedVoucherForRecipient(form.getEmail());

        if(voucherCodeList!=null && voucherCodeList.size()>0) {
            return voucherCodeList;
        }

        return null;
    }

    @RequestMapping(value="/getVoucherCodeListBySpecialOfferID", method = RequestMethod.POST)
    public List<VoucherCode> getVoucherCodeListBySpecialOfferID(@RequestBody VoucherCodeForm form) {
        List<VoucherCode> voucherCodeList = voucherCodeService.getVoucherCodeListBySpecialOfferID(form.getSpecialOfferID());

        if(voucherCodeList!=null && voucherCodeList.size()>0) {
            return voucherCodeList;
        }

        return null;
    }

    @RequestMapping(value="/useVoucherCode", method = RequestMethod.POST)
    public VoucherCodeForm useVoucherCode(@RequestBody VoucherCodeForm form) throws ParseException {
        return voucherCodeService.useVoucherCode(form);
    }
}
