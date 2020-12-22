package com.vouchergenerator.services.impl;

import com.vouchergenerator.entities.SpecialOffer;
import com.vouchergenerator.repo.SpecialOfferRepo;
import com.vouchergenerator.services.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("specialOfferService")
@Transactional
public class SpecialOfferImpl implements SpecialOfferService {
    @Autowired
    SpecialOfferRepo specialOfferRepo;

    @Override
    public List<SpecialOffer> getAllSpecialOffer() {
        return specialOfferRepo.findAll();
    }

    @Override
    public List<SpecialOffer> getSpecialOfferByDiscount(double discountPercentage) {
        return specialOfferRepo.getSpecialOfferByDiscount(discountPercentage);
    }
}
