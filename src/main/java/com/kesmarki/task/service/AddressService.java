package com.kesmarki.task.service;

import com.kesmarki.task.entity.Address;
import com.kesmarki.task.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final ValidationService validationService;

    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddress(Long id) {
        validationService.validateId(id);
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) return address.get();
        else throw new EntityNotFoundException("Did not found Address entity with id: " + id);
    }

    public Address addAddress(Address address) {
        validationService.validateAddress(address);
        return addressRepository.save(address);
    }

    public Address updateAddress(Address address) {
        validationService.validateAddress(address);
        return addressRepository.save(address);
    }
}
