package org.masyanas.service.person;

import org.masyanas.model.person.Person;
import org.masyanas.repository.person.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonServiceImpl implements PersonService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Person> findAll()
    {
        LOGGER.debug("... find all persons");
        return personRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Person findById(final Long personId)
    {
        LOGGER.debug("... find Person (id={})", personId);
        return personRepository.findOne(personId);
    }

    @Override
    public List<Person> findByName(String name)
    {
        LOGGER.debug("... find Person (name={})", name);
        return personRepository.findByName(name);
    }

    @Override
    public List<Person> findBySurname(String surname)
    {
        LOGGER.debug("... find Person (surname={})", surname);
        return personRepository.findBySurname(surname);
    }

    @Override
    public Person create(Person person)
    {
        LOGGER.debug("... create new Person ({})", person);
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person)
    {
        LOGGER.debug("... update Person (id={})", person.getId());
        personRepository.updateById(person.getId(), person.getName(), person.getSurname());
        return person;
    }

    @Override
    public Person delete(final Long personId)
    {

        LOGGER.debug("... delete Person (id={})", personId);
        final Person person = findById(personId);
        personRepository.delete(personId);
        return person;

    }
}
