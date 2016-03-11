package org.masyanas.service.person;

import org.masyanas.dto.IdentifierOutDTO;
import org.masyanas.dto.factory.person.PersonDTOFactory;
import org.masyanas.dto.person.PersonInDTO;
import org.masyanas.dto.person.PersonOutDTO;
import org.masyanas.model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonLightWeightServiceImpl implements PersonLightWeightService
{

    @Autowired
    PersonService personService;

    @Autowired
    PersonDTOFactory personDTOFactory;

    @Override
    public PersonOutDTO findById(final Long personId)
    {
        return personDTOFactory.createOutDTO(personService.findById(personId));
    }

    @Override
    public List<PersonOutDTO> findByName(String name)
    {
        return personDTOFactory.createListOutDTO(personService.findByName(name));
    }

    @Override
    public List<PersonOutDTO> findBySurname(String surname)
    {
        return personDTOFactory.createListOutDTO(personService.findBySurname(surname));
    }

    @Override
    public List<PersonOutDTO> findAll()
    {
        return personDTOFactory.createListOutDTO(personService.findAll());
    }

    @Override
    public PersonOutDTO create(PersonInDTO personInDTO)
    {

        final Person person = personService.create(personDTOFactory.createModel(personInDTO));

        return personDTOFactory.createOutDTO(person);

    }

    @Override
    public PersonOutDTO update(Long personId, PersonInDTO personInDTO)
    {

        final Person person = personService.findById(personId);

        personDTOFactory.updateModel(personInDTO, person);

        return personDTOFactory.createOutDTO(personService.update(person));

    }

    @Override
    public IdentifierOutDTO delete(Long personId)
    {
        return personDTOFactory.createIdentifierOutDTO(personService.delete(personId));
    }

}
