package com.vouchergenerator.services;

import com.vouchergenerator.entities.SpecialOffer;
import com.vouchergenerator.form.SpecialOfferForm;

import java.util.List;

public interface SpecialOfferService {
    List<SpecialOffer> getAllSpecialOffer();
    List<SpecialOffer> newSpecialOffer(SpecialOfferForm form);
}
