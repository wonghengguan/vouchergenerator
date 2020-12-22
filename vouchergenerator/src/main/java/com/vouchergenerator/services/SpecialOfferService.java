package com.vouchergenerator.services;

import com.vouchergenerator.entities.SpecialOffer;

import java.util.List;

public interface SpecialOfferService {
    List<SpecialOffer> getAllSpecialOffer();
    List<SpecialOffer> getSpecialOfferByDiscount(double discountPercentage);
}
