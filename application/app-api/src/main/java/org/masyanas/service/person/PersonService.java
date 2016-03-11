package org.masyanas.service.person;

import org.masyanas.model.person.Person;

import java.util.List;


public interface PersonService
{

    List<Person> findAll();

    Person findById(final Long personId);

    List<Person> findByName(final String name);

    List<Person> findBySurname(final String surname);

    Person create(Person person);

    Person update(Person person);

    Person delete(final Long personId);

}
