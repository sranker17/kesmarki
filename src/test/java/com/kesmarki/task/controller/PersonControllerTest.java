package com.kesmarki.task.controller;

import com.kesmarki.task.IntegrationTest;
import com.kesmarki.task.entity.Address;
import com.kesmarki.task.entity.Contact;
import com.kesmarki.task.entity.ContactType;
import com.kesmarki.task.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@DisplayName("Integration tests of PersonController")
class PersonControllerTest extends IntegrationTest {

    @Autowired
    private PersonController personController;

    private Person person;

    @BeforeEach
    void setUp() {
        Contact contact = new Contact(ContactType.MOBILE_PHONE, "+36303333333");
        contact.setId(UUID.randomUUID());
        Address address = new Address("Hungary", "Budapest", "1111", "Test road", Set.of(contact));
        address.setId(UUID.randomUUID());
        person = new Person("Jeff", null, address);
        person.setId(UUID.randomUUID());
    }

    @Test
    void getPeople() {
        when(personService.getPeople()).thenReturn(List.of(person));
        ResponseEntity<List<Person>> people = personController.getPeople();
        assertEquals(HttpStatus.OK, people.getStatusCode());
        assertNotNull(people.getBody());
        assertEquals(1, people.getBody().size());
    }

    @Test
    void getPerson() {
        when(personService.getPerson(person.getId())).thenReturn(person);
        ResponseEntity<Person> responseAddress = personController.getPerson(person.getId());
        assertEquals(HttpStatus.OK, responseAddress.getStatusCode());
        assertNotNull(responseAddress.getBody());
        assertEquals(person.getId(), responseAddress.getBody().getId());
    }

    @Test
    void addPerson() {
        when(personService.addPerson(person)).thenReturn(person);
        ResponseEntity<Person> responseAddress = personController.addPerson(person);
        assertEquals(HttpStatus.CREATED, responseAddress.getStatusCode());
        assertNotNull(responseAddress.getBody());
        assertEquals(person.getId(), responseAddress.getBody().getId());
    }

    @Test
    void updatePerson() {
        when(personService.updatePerson(person)).thenReturn(person);
        ResponseEntity<Person> responseAddress = personController.updatePerson(person);
        assertEquals(HttpStatus.CREATED, responseAddress.getStatusCode());
        assertNotNull(responseAddress.getBody());
        assertEquals(person.getId(), responseAddress.getBody().getId());
    }
}