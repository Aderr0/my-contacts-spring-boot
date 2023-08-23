/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.training.contact.views;

import com.training.contact.beans.Contact;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author amaury
 */
@Route("add-contact")
@PageTitle("Create contacts | Amder Tutorial")
public class CreateContactView extends VerticalLayout {

    VerticalLayout cardLayout = new VerticalLayout();

    // Form fields
    TextField firstName;
    TextField lastName;
    TextField phoneNumber;
    EmailField email;

    public CreateContactView() {
        addClassName("add-contact-form-view");

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        configureCard();

        add(cardLayout);
    }

    private void configureCard() {
        FormLayout formLayout = getFormLayout();
        HorizontalLayout validationButtonsLayout = getValidationButtonsLayout();

        cardLayout.setAlignItems(Alignment.CENTER);
        cardLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        cardLayout.setSpacing(true);
        cardLayout.setWidth("40%");

        cardLayout.add(formLayout, validationButtonsLayout);
    }

    private FormLayout getFormLayout() {
        FormLayout formLayout = new FormLayout();

        firstName = new TextField("First name");
        lastName = new TextField("Last name");
        phoneNumber = new TextField("Phone number");
        email = new EmailField("Email");

        firstName.setClearButtonVisible(true);
        lastName.setClearButtonVisible(true);
        phoneNumber.setClearButtonVisible(true);
        email.setClearButtonVisible(true);
        formLayout.add(firstName, lastName, phoneNumber, email);

        formLayout.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 2)
        );

        return formLayout;
    }

    private HorizontalLayout getValidationButtonsLayout() {
        HorizontalLayout validationButtonsLayout = new HorizontalLayout();

        Button save = new Button("Create");
        Button cancel = new Button("Cancel");

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);

        save.addClickShortcut(Key.ENTER);

        save.addClickListener(event -> validateAndSaveHandler());
        cancel.addClickListener(event -> cancelHandler());

        validationButtonsLayout.setWidthFull();
        validationButtonsLayout.setAlignItems(Alignment.CENTER);
        validationButtonsLayout.setJustifyContentMode(JustifyContentMode.END);

        validationButtonsLayout.add(save, cancel);

        return validationButtonsLayout;
    }

    private void validateAndSaveHandler() {

        Contact contact = new Contact();
        contact.setFirstName(firstName.getValue());
        contact.setLastName(lastName.getValue());
        contact.setPhoneNumber(phoneNumber.getValue());
        contact.setMail(email.getValue());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Contact> response = restTemplate.postForEntity("http://localhost:9000/contacts", contact, Contact.class);

        if (response.getStatusCode() != HttpStatus.CREATED) {
            firstName.setInvalid(true);
            lastName.setInvalid(true);
            phoneNumber.setInvalid(true);
            email.setInvalid(true);
        } else {
            getUI().ifPresent(ui -> ui.navigate(""));
        }
    }

    private void cancelHandler() {
        getUI().ifPresent(ui -> ui.navigate(""));
    }

}
