package com.kesmarki.task.controller;

import com.kesmarki.task.entity.Contact;
import com.kesmarki.task.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ContactController {

    private final ContactService contactService;

    @GetMapping(value = "/contacts")
    public ResponseEntity<List<Contact>> getContacts() {
        log.info("getContacts called");
        return status(OK).body(contactService.getContacts());
    }

    @GetMapping(value = "/contact/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable("id") Long id) {
        log.info("getContact called");
        return status(OK).body(contactService.getContact(id));
    }

    @PostMapping(value = "/contact")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        log.info("addContact called");
        return status(CREATED).body(contactService.addContact(contact));
    }

    @PutMapping(value = "/contact")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
        log.info("updateContact called");
        return status(CREATED).body(contactService.updateContact(contact));
    }
}
