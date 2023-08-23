/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.training.contact.service;

import com.training.contact.beans.Contact;
import com.training.contact.repository.ContactRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author amaury
 */
@Data
@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    public List<Contact> getContactsByName(String name) {
        ArrayList<Contact> matchingContacts = new ArrayList<>();

        List<Contact> contacts = contactRepository.findAll();
        for (Contact contact : contacts) {
            if (contact.getFirstName().contains(name) || contact.getLastName().contains(name)) {
                matchingContacts.add(contact);
            }
        }

        return matchingContacts;
    }

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

}
