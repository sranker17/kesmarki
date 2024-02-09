package com.kesmarki.task.service;

import com.kesmarki.task.config.ValidationProperties;
import com.kesmarki.task.entity.Address;
import com.kesmarki.task.entity.Contact;
import com.kesmarki.task.entity.ContactType;
import com.kesmarki.task.entity.Person;
import com.kesmarki.task.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("Unit tests of ValidationService")
class ValidationServiceTest {

    @Mock
    private ValidationProperties validationProperties;
    @InjectMocks
    private ValidationService validationService;

    private static Stream<Arguments> provideContacts() {
        Contact contact1 = new Contact(null, "a@a.com");
        contact1.setId(UUID.randomUUID());
        Contact contact2 = new Contact(ContactType.EMAIL, null);
        contact2.setId(UUID.randomUUID());
        Contact contact3 = new Contact(ContactType.EMAIL, "aa.com");
        contact3.setId(UUID.randomUUID());
        Contact contact4 = new Contact(ContactType.MOBILE_PHONE, "436344");
        contact4.setId(UUID.randomUUID());
        Contact contact5 = new Contact(ContactType.LANDLINE_PHONE, "aa.com");
        contact5.setId(UUID.randomUUID());
        Contact contact6 = new Contact(ContactType.LANDLINE_PHONE, "123456789012345678901");
        contact5.setId(UUID.randomUUID());
        return Stream.of(
                Arguments.of((Object) null),
                Arguments.of(contact1),
                Arguments.of(contact2),
                Arguments.of(contact3),
                Arguments.of(contact4),
                Arguments.of(contact5),
                Arguments.of(contact6)
        );
    }

    @BeforeEach
    void setUp() {
        when(validationProperties.getStringLength()).thenReturn(20L);
    }

    @Test
    void validateIdWorksCorrectly() {
        validationService.validateId(UUID.randomUUID());
    }

    @Test
    void validateIdThrowsValidationErrorForNull() {
        try {
            validationService.validateId(null);
            fail();
        } catch (ValidationException validationException) {
            assertEquals("ID cannot be null", validationException.getMessage());
        }
    }

    @Test
    void validateContactWorksCorrectlyWithEmail() {
        Contact contact = new Contact();
        contact.setId(UUID.randomUUID());
        contact.setContactType(ContactType.EMAIL);
        contact.setData("test@test.com");
        validationService.validateContact(contact);
    }

    @Test
    void validateContactWorksCorrectlyWithPhoneNumber() {
        Contact contact = new Contact();
        contact.setId(UUID.randomUUID());
        contact.setContactType(ContactType.LANDLINE_PHONE);
        contact.setData("0617077711");
        validationService.validateContact(contact);
    }

    @MethodSource("provideContacts")
    @ParameterizedTest(name = "test {index}, Contact validation throws ValidationError")
    void validateContactThrowsValidationError(Contact contact) {
        try {
            validationService.validateContact(contact);
            fail();
        } catch (Exception e) {
            assertInstanceOf(ValidationException.class, e);
        }
    }

    @Test
    void validateAddressWorksCorrectly() {
        Address address = new Address("Hungary", "Budapest", "1011", "Test street", null);
        address.setId(UUID.randomUUID());
        validationService.validateAddress(address);
    }

    @Test
    void validatePersonWorksCorrectly() {
        Person person = new Person("Jeff", null, null);
        person.setId(UUID.randomUUID());
        validationService.validatePerson(person);
    }

    @Test
    void validatePersonWorksCorrectlyWithFullData() {
        Contact contact1 = new Contact(ContactType.EMAIL, "a@a.com");
        contact1.setId(UUID.randomUUID());
        Contact contact2 = new Contact(ContactType.MOBILE_PHONE, "06202020020");
        contact2.setId(UUID.randomUUID());
        Contact contact3 = new Contact(ContactType.EMAIL, "aaaa@a.com");
        contact3.setId(UUID.randomUUID());
        Address tempoararyAddress = new Address("Hungary", "Budapest", "1011", "Test street", Set.of(contact1));
        tempoararyAddress.setId(UUID.randomUUID());
        Address permanentAddress = new Address("Hungary", "Budapest", "1033", "Test2 street", Set.of(contact2, contact3));
        permanentAddress.setId(UUID.randomUUID());
        Person person = new Person("Jeff", tempoararyAddress, permanentAddress);
        person.setId(UUID.randomUUID());
        validationService.validatePerson(person);
    }
}