package com.vouchergenerator.controller;

import com.vouchergenerator.entities.SpecialOffer;
import com.vouchergenerator.form.SpecialOfferForm;
import com.vouchergenerator.services.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/specialOffer")
public class SpecialOfferController {

    @Autowired
    SpecialOfferService specialOfferService;

    @RequestMapping(value="/getAllSpecialOffer", method = RequestMethod.POST)
    public List<SpecialOffer> getAllSpecialOffer(@RequestBody SpecialOfferForm form) {
        return specialOfferService.getAllSpecialOffer();
    }

    @RequestMapping(value="/newSpecialOffer", method = RequestMethod.POST)
    public List<SpecialOffer> newSpecialOffer(@RequestBody SpecialOfferForm form) {
        return specialOfferService.newSpecialOffer(form);
    }
}
