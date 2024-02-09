package com.kesmarki.task.service;

import com.kesmarki.task.entity.Contact;
import com.kesmarki.task.entity.ContactType;
import com.kesmarki.task.repository.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ValidationService validationService;

    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    public Contact getContact(UUID id) {
        validationService.validateId(id);
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) return contact.get();
        else throw new EntityNotFoundException("Did not found Contact entity with id: " + id);
    }

    public Contact addContact(Contact contact) {
        validationService.validateContact(contact);
        Contact c = new Contact(ContactType.EMAIL, "adw@ad.com");
        c.setCreateDate(OffsetDateTime.now());
        return contactRepository.save(c);
    }

    public Contact updateContact(Contact contact) {
        validationService.validateId(contact.getId());
        validationService.validateContact(contact);
        return contactRepository.save(contact);
    }
}
