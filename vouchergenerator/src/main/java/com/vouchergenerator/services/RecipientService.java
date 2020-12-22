package com.vouchergenerator.services;

import com.vouchergenerator.entities.Recipient;
import com.vouchergenerator.form.RecipientForm;

public interface RecipientService {
    Long getRecipientByEmail(String email);
    RecipientForm newRecipient(RecipientForm form);
    Recipient getRecipientByID(Long id);
}
