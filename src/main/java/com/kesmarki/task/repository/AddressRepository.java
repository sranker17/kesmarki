package com.kesmarki.task.repository;

import com.kesmarki.task.entity.Address;
import com.kesmarki.task.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
