package com.vouchergenerator.controller;

import com.vouchergenerator.entities.VoucherCode;
import com.vouchergenerator.form.VoucherCodeForm;
import com.vouchergenerator.services.VoucherCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/voucherCode")
public class VoucherCodeController {

    @Autowired
    VoucherCodeService voucherCodeService;

    @RequestMapping(value="/generateVoucherCode", method = RequestMethod.POST)
    public void generateVoucherCode(@RequestBody VoucherCodeForm form) {
        voucherCodeService.generateVoucherCode(form);
    }

    @RequestMapping(value="/getVoucherCodeListByRecipientID", method = RequestMethod.POST)
    public List<VoucherCode> getVoucherCodeListByRecipientID(@RequestBody VoucherCodeForm form) {
        List<VoucherCode> voucherCodeList = voucherCodeService.getVoucherCodeListByRecipientID(form.getRecipientID());

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
    public List<VoucherCode> useVoucherCode(@RequestBody VoucherCodeForm form) {
        List<VoucherCode> voucherCodeList = voucherCodeService.useVoucherCode(form);

        if(voucherCodeList!=null && voucherCodeList.size()>0) {
            return voucherCodeList;
        }

        return null;
    }
}
