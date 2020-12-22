package com.vouchergenerator.repo;

import com.vouchergenerator.entities.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecipientRepo extends JpaRepository<Recipient, Long> {
    @Query("select r from Recipient r "
            + "where r.email = :email")
    Recipient getRecipientByEmail(
            @Param("email") String email
    );
}
