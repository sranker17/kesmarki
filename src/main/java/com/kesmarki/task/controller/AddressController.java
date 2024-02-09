package com.kesmarki.task.controller;

import com.kesmarki.task.entity.Address;
import com.kesmarki.task.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AddressController {

    private final AddressService addressService;

    @GetMapping(value = "/addresses")
    public ResponseEntity<List<Address>> getAddresses() {
        log.info("getAddresses called");
        return status(OK).body(addressService.getAddresses());
    }

    @GetMapping(value = "/address/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable("id") UUID id) {
        log.info("getAddress called");
        return status(OK).body(addressService.getAddress(id));
    }

    @PostMapping(value = "/address")
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
        log.info("addAddress called");
        return status(CREATED).body(addressService.addAddress(address));
    }

    @PutMapping(value = "/address")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
        log.info("updateAddress called");
        return status(CREATED).body(addressService.updateAddress(address));
    }
}
