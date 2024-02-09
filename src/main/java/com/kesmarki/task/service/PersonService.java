package com.kesmarki.task.service;

import com.kesmarki.task.entity.Person;
import com.kesmarki.task.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    public Person getPerson(Long id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) return person.get();
        else throw new EntityNotFoundException("Did not found Person entity with id: " + id);
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }
}
