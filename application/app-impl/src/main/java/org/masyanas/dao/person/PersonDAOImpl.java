package org.masyanas.dao.person;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.masyanas.model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO
{

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public List<Person> findAll()
    {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);

        return criteria.list();

    }

    @Override
    public Person findById(Long id)
    {
        return (Person)sessionFactory.getCurrentSession().get(Person.class, id);
    }

    @Override
    public void create(Person person)
    {
        sessionFactory.getCurrentSession().persist(person);
    }

    @Override
    public Person update(Person person)
    {
        return (Person)sessionFactory.getCurrentSession().merge(person);
    }

    @Override
    public void delete(Person person)
    {
        sessionFactory.getCurrentSession().delete(person);
    }
}
