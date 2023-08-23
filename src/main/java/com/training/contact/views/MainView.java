/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.training.contact.views;

import com.training.contact.beans.Contact;
import com.training.contact.service.ContactService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *
 * @author amaury
 */
@Route("")
@PageTitle("Contacts | Amder Tutorial")
public class MainView extends VerticalLayout {

    Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filterText = new TextField();

    ContactService contactService;

    public MainView(ContactService contactService) {
        this.contactService = contactService;

        addClassName("list-view");

        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");

        grid.setSizeFull();
        grid.setColumns("id", "firstName", "lastName", "phoneNumber", "mail");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        updateList();
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Search...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList(filterText.getValue()));

        Button addContactButton = new Button("Add contact");
        addContactButton.addClickListener(event -> {
            addContactButton.getUI().ifPresent(ui -> ui.navigate(CreateContactView.class));
        });

        var toolbar = new HorizontalLayout(filterText, addContactButton);

        toolbar.addClassName("toolbar");

        return toolbar;
    }

    private void updateList() {
        grid.setItems(contactService.getContacts());
    }

    private void updateList(String name) {
        grid.setItems(contactService.getContactsByName(name));
    }
}
