package org.masyanas.repository.person;

import org.masyanas.model.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PersonRepository extends JpaRepository<Person, Long>
{

    List<Person> findByName(@Param("name") String name);

    List<Person> findBySurname(@Param("surname") String surname);

    List<Person> findAllByOrderByIdAsc();

    Person findOne(Long id);

    @Modifying
    @Transactional
    Person save(Person person);

    @Modifying
    @Transactional
    @Query("update Person p set p.name = :name, p.surname = :surname where p.id = :id")
    void updateById(@Param("id") Long id, @Param("name") String name, @Param("surname") String surname);

    @Modifying
    @Transactional
    void delete(Long personId);

}
