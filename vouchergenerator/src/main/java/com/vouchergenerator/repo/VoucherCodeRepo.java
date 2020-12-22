package com.vouchergenerator.repo;

import com.vouchergenerator.entities.VoucherCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoucherCodeRepo extends JpaRepository<VoucherCode, Long> {
    @Query("select vc from VoucherCode vc "
            + "where vc.recipient.id = :recipientID")
    List<VoucherCode> getVoucherCodeByRecipient(
            @Param("recipientID") Long recipientID
    );

    @Query("select vc from VoucherCode vc "
            + "where vc.specialOffer.id = :specialOfferID")
    List<VoucherCode> getVoucherCodeBySpecialOffer(
            @Param("specialOfferID") Long specialOfferID
    );
}
