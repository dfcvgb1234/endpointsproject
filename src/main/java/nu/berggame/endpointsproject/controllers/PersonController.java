package nu.berggame.endpointsproject.controllers;

import nu.berggame.endpointsproject.DTO.internal.PersonDTO;
import nu.berggame.endpointsproject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("name-info")
    public ResponseEntity<PersonDTO> getPerson(@RequestParam String name) {
        PersonDTO person = personService.GetPersonByName(name);

        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}
