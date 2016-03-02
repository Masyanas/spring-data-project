package org.masyanas.service.person;

import org.masyanas.dao.person.PersonDAO;
import org.masyanas.model.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PersonServiceImpl implements PersonService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    PersonDAO personDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<Person> findAll()
    {
        LOGGER.debug("... find all persons");
        return personDAO.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
    public Person findById(final Long personId)
    {
        LOGGER.debug("... find Person (id={})", personId);
        return personDAO.findById(personId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Person create(Person person)
    {
        LOGGER.debug("... create new Person ({})", person);
        personDAO.create(person);
        return person;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Person update(Person person)
    {
        LOGGER.debug("... update Person (id={})", person.getId());
        return personDAO.update(person);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Person delete(final Long personId)
    {

        LOGGER.debug("... delete Person (id={})", personId);
        final Person person = findById(personId);
        personDAO.delete(person);
        return person;

    }
}
