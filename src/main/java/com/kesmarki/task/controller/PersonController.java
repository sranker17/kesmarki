package com.kesmarki.task.controller;

import com.kesmarki.task.entity.Person;
import com.kesmarki.task.service.PersonService;
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
public class PersonController {

    private final PersonService personService;

    @GetMapping(value = "/people")
    public ResponseEntity<List<Person>> getPeople() {
        log.info("getPeople called");
        return status(OK).body(personService.getPeople());
    }

    @GetMapping(value = "/person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") Long id) {
        log.info("getPerson called");
        return status(OK).body(personService.getPerson(id));
    }

    @PostMapping(value = "/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        log.info("addPerson called");
        return status(CREATED).body(personService.addPerson(person));
    }

    @PutMapping(value = "/person")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        log.info("updatePerson called");
        return status(CREATED).body(personService.updatePerson(person));
    }
}
