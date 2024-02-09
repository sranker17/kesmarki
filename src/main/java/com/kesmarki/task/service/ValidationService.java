package com.kesmarki.task.service;

import com.kesmarki.task.config.ValidationProperties;
import com.kesmarki.task.entity.Address;
import com.kesmarki.task.entity.Contact;
import com.kesmarki.task.entity.ContactType;
import com.kesmarki.task.entity.Person;
import com.kesmarki.task.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ValidationService {

    private final ValidationProperties validationProperties;

    public void validatePerson(Person person) {
        validateId(person.getId());
        validateNotNullString(person.getName());
        if (ObjectUtils.isNotEmpty(person.getPermanentAddress())) validateAddress(person.getPermanentAddress());
        if (ObjectUtils.isNotEmpty(person.getTemporaryAddress())) validateAddress(person.getTemporaryAddress());
    }

    public void validateAddress(Address address) {
        validateId(address.getId());
        validateNotNullString(address.getCountry());
        validateNotNullString(address.getCity());
        validateNotNullString(address.getZip());
        validateNotNullString(address.getStreet());
        if (!CollectionUtils.isEmpty(address.getContacts())) address.getContacts().forEach(this::validateContact);
    }

    public void validateContact(Contact contact) {
        if (ObjectUtils.isEmpty(contact)) throw new ValidationException("Contact can't be null");
        validateId(contact.getId());
        validateContactTypeWithData(contact.getContactType(), contact.getData());
    }

    public void validateId(Long id) {
        if (id == null || id < validationProperties.getValidId())
            throw new ValidationException("ID cannot be null or less than 0");
    }

    private void validateNotNullString(String s) {
        if (StringUtils.isEmpty(s) || s.length() > validationProperties.getStringLength())
            throw new ValidationException("String value can't be longer than 255 characters and cannot be null");
    }

    private void validateContactTypeWithData(ContactType contactType, String data) {
        if (contactType == null) throw new ValidationException("ContactType can't be null");
        validateNotNullString(data);
        if (contactType.equals(ContactType.EMAIL) && !Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", data))
            throw new ValidationException("Invalid email");
        if ((contactType.equals(ContactType.MOBILE_PHONE) || contactType.equals(ContactType.LANDLINE_PHONE))
                && !Pattern.matches("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", data))
            throw new ValidationException("Invalid Phone number");
    }
}
