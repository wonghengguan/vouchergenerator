package com.vouchergenerator.repo;

import com.vouchergenerator.entities.VoucherCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface VoucherCodeRepo extends JpaRepository<VoucherCode, Long> {

    @Query("select vc from VoucherCode vc "
            + "where vc.specialOffer.id = :specialOfferID")
    List<VoucherCode> getVoucherCodeBySpecialOffer(
            @Param("specialOfferID") Long specialOfferID
    );

    @Query("select vc from VoucherCode vc "
            + "where vc.recipient.email = :email "
            + "and vc.redeemed = 0 "
            + "and vc.expirationDate >= :today"
    )
    List<VoucherCode> getValidVoucherCodeForRecipient(
            @Param("email") String email,
            @Param("today") Date today
    );

    @Query("select vc from VoucherCode vc "
            + "where vc.recipient.email = :email "
            + "and vc.redeemed = 0 "
            + "and vc.expirationDate < :today"
    )
    List<VoucherCode> getExpiredVoucherCodeForRecipient(
            @Param("email") String email,
            @Param("today") Date today
    );

    @Query("select vc from VoucherCode vc "
            + "where vc.recipient.email = :email "
            + "and vc.redeemed = 1 "
    )
    List<VoucherCode> getRedeemedVoucherCodeForRecipient(
            @Param("email") String email
    );

    @Query("select vc from VoucherCode vc "
            + "where vc.code = :code "
            + "and vc.recipient.email = :email"
    )
    VoucherCode getVoucherCodeByCodeAndRecipientEmail(
            @Param("code") String code,
            @Param("email") String email
    );

    @Query("select vc from VoucherCode vc "
            + "where vc.recipient.email = :email "
            + "and vc.specialOffer.id = :specialOfferID"
    )
    VoucherCode getVoucherCodeByRecipientEmailAndSpecialOfferID(
            @Param("email") String email,
            @Param("specialOfferID") Long specialOfferID
    );

    @Query("select vc.code from VoucherCode vc ")
    List<String> getAllCode();
}
