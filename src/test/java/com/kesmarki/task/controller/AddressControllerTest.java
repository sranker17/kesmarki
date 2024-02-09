package com.kesmarki.task.controller;

import com.kesmarki.task.IntegrationTest;
import com.kesmarki.task.entity.Address;
import com.kesmarki.task.entity.Contact;
import com.kesmarki.task.entity.ContactType;
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

@DisplayName("Integration tests of AddressController")
class AddressControllerTest extends IntegrationTest {

    @Autowired
    private AddressController addressController;

    private Address address;

    @BeforeEach
    void setUp() {
        Contact contact = new Contact(ContactType.MOBILE_PHONE, "+36303333333");
        contact.setId(UUID.randomUUID());
        address = new Address("Hungary", "Budapest", "1111", "Test road", Set.of(contact));
        address.setId(UUID.randomUUID());
    }

    @Test
    void getAddresses() {
        when(addressService.getAddresses()).thenReturn(List.of(address));
        ResponseEntity<List<Address>> addresses = addressController.getAddresses();
        assertEquals(HttpStatus.OK, addresses.getStatusCode());
        assertNotNull(addresses.getBody());
        assertEquals(1, addresses.getBody().size());
    }

    @Test
    void getAddress() {
        when(addressService.getAddress(address.getId())).thenReturn(address);
        ResponseEntity<Address> responseAddress = addressController.getAddress(address.getId());
        assertEquals(HttpStatus.OK, responseAddress.getStatusCode());
        assertNotNull(responseAddress.getBody());
        assertEquals(address.getId(), responseAddress.getBody().getId());
    }

    @Test
    void addAddress() {
        when(addressService.addAddress(address)).thenReturn(address);
        ResponseEntity<Address> responseAddress = addressController.addAddress(address);
        assertEquals(HttpStatus.CREATED, responseAddress.getStatusCode());
        assertNotNull(responseAddress.getBody());
        assertEquals(address.getId(), responseAddress.getBody().getId());
    }

    @Test
    void updateAddress() {
        when(addressService.updateAddress(address)).thenReturn(address);
        ResponseEntity<Address> responseAddress = addressController.updateAddress(address);
        assertEquals(HttpStatus.CREATED, responseAddress.getStatusCode());
        assertNotNull(responseAddress.getBody());
        assertEquals(address.getId(), responseAddress.getBody().getId());
    }
}