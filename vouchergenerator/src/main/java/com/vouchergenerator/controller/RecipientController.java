package com.vouchergenerator.controller;

import com.vouchergenerator.entities.Recipient;
import com.vouchergenerator.form.RecipientForm;
import com.vouchergenerator.services.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/recipient")
public class RecipientController {
    @Autowired
    RecipientService recipientService;

    @RequestMapping(value="/getRecipientByEmail", method = RequestMethod.POST)
    public Recipient getRecipientByEmail(@RequestBody RecipientForm form) {
        return recipientService.getRecipientByEmail(form.getEmail());
    }

    @RequestMapping(value="/getRecipientByID", method = RequestMethod.POST)
    public Recipient getRecipientByID(@RequestBody RecipientForm form) {
        return recipientService.getRecipientByID(form.getId());
    }

    @RequestMapping(value="/newRecipient", method = RequestMethod.POST)
    public RecipientForm newRecipient(@RequestBody RecipientForm form) {
        return recipientService.newRecipient(form);
    }
}
