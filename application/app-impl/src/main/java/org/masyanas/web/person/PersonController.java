package org.masyanas.web.person;

import org.masyanas.dto.IdentifierOutDTO;
import org.masyanas.dto.person.PersonInDTO;
import org.masyanas.dto.person.PersonOutDTO;
import org.masyanas.service.person.PersonLightWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "person", headers = "Accept=application/json", produces = "application/json")
public class PersonController
{

    @Autowired
    PersonLightWeightService personLightWeightService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public PersonOutDTO findById(@PathVariable(value = "id") Long personId)
    {
        return personLightWeightService.findById(personId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<PersonOutDTO> findAll()
    {
        return personLightWeightService.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public PersonOutDTO update(@PathVariable(value = "id") Long personId, @RequestBody PersonInDTO personInDTO)
    {
        return personLightWeightService.update(personId, personInDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public PersonOutDTO create(@RequestBody PersonInDTO personInDTO)
    {
        return personLightWeightService.create(personInDTO);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public IdentifierOutDTO delete(@PathVariable(value = "id") Long personId)
    {
        return personLightWeightService.delete(personId);
    }

}
