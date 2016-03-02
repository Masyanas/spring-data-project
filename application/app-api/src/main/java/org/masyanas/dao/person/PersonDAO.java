package org.masyanas.dao.person;

import org.masyanas.model.person.Person;

import java.util.List;

public interface PersonDAO
{

    List<Person> findAll();

    Person findById(final Long id);

    void create(Person person);

    Person update(Person person);

    void delete(Person person);

}
