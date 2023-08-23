/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.training.contact.controller;

import com.training.contact.beans.Contact;
import com.training.contact.service.ContactService;
import java.rmi.ServerException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author amaury
 */
@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public List<Contact> getContacts(@PathVariable(name = "name") String name) {
        return name != null ? contactService.getContacts() : contactService.getContactsByName(name);
    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> create(@RequestBody Contact contact) throws ServerException {
        Contact responseContact = contactService.createContact(contact);

        if (responseContact == null) {
            throw new ServerException("Error while creating Contact");
        } else {
            return new ResponseEntity<>(responseContact, HttpStatus.CREATED);
        }
    }

}
