package com.vouchergenerator.repo;

import com.vouchergenerator.entities.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialOfferRepo extends JpaRepository<SpecialOffer, Long> {
    @Query("select so from SpecialOffer so "
            + "where so.name = :name")
    SpecialOffer getSpecialOfferByName(
            @Param("name") String name
    );

    @Query("select so from SpecialOffer so "
            + "where so.discountPercentage = :discountPercentage")
    List<SpecialOffer> getSpecialOfferByDiscount(
            @Param("discountPercentage") double discountPercentage
    );
}
