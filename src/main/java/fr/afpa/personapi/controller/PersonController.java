package fr.afpa.personapi.controller;

import fr.afpa.personapi.model.Person;
import fr.afpa.personapi.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        return personService.save(person);
    }

    @GetMapping
    public List<Person> getAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable Long id) {
        return personService.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personService.deleteById(id);
    }
}