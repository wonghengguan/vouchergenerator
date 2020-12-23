package com.vouchergenerator.services.impl;

import com.vouchergenerator.entities.Recipient;
import com.vouchergenerator.form.RecipientForm;
import com.vouchergenerator.repo.RecipientRepo;
import com.vouchergenerator.services.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("recipientService")
@Transactional
public class RecipientImpl implements RecipientService {
    @Autowired
    RecipientRepo recipientRepo;

    @Override
    public Recipient getRecipientByEmail(String email) {
        Recipient recipient = recipientRepo.getRecipientByEmail(email);

        return recipient;
    }

    @Override
    public RecipientForm newRecipient(RecipientForm form) {
        Recipient recipient = recipientRepo.getRecipientByEmail(form.getEmail());
        Boolean exists = false;
        if(recipient==null) {
            recipient = new Recipient();
            recipient.setName(form.getName());
            recipient.setEmail(form.getEmail());
            recipientRepo.save(recipient);
        } else {
            exists = true;
        }
        form.setExists(exists);

        return form;
    }

    @Override
    public Recipient getRecipientByID(Long id) {
        return recipientRepo.getOne(id);
    }
}
