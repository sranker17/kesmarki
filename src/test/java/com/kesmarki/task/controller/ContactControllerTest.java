package com.kesmarki.task.controller;

import com.kesmarki.task.IntegrationTest;
import com.kesmarki.task.entity.Contact;
import com.kesmarki.task.entity.ContactType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@DisplayName("Integration tests of ContactController")
class ContactControllerTest extends IntegrationTest {

    @Autowired
    private ContactController contactController;

    private Contact contact;

    @BeforeEach
    void setUp() {
        contact = new Contact(ContactType.EMAIL, "a@a.com");
        contact.setId(UUID.randomUUID());
    }

    @Test
    void getContacts() {
        when(contactService.getContacts()).thenReturn(List.of(contact));
        ResponseEntity<List<Contact>> contacts = contactController.getContacts();
        assertEquals(HttpStatus.OK, contacts.getStatusCode());
        assertNotNull(contacts.getBody());
        assertEquals(1, contacts.getBody().size());
    }

    @Test
    void getContact() {
        when(contactService.getContact(contact.getId())).thenReturn(contact);
        ResponseEntity<Contact> responseContact = contactController.getContact(contact.getId());
        assertEquals(HttpStatus.OK, responseContact.getStatusCode());
        assertNotNull(responseContact.getBody());
        assertEquals(contact.getId(), responseContact.getBody().getId());
    }

    @Test
    void addContact() {
        when(contactService.addContact(contact)).thenReturn(contact);
        ResponseEntity<Contact> responseContact = contactController.addContact(contact);
        assertEquals(HttpStatus.CREATED, responseContact.getStatusCode());
        assertNotNull(responseContact.getBody());
        assertEquals(contact.getId(), responseContact.getBody().getId());
    }

    @Test
    void updateContact() {
        when(contactService.updateContact(contact)).thenReturn(contact);
        ResponseEntity<Contact> responseContact = contactController.updateContact(contact);
        assertEquals(HttpStatus.CREATED, responseContact.getStatusCode());
        assertNotNull(responseContact.getBody());
        assertEquals(contact.getId(), responseContact.getBody().getId());
    }
}