package org.masyanas.dto.factory.person;


import org.masyanas.dto.IdentifierOutDTO;
import org.masyanas.dto.person.PersonInDTO;
import org.masyanas.dto.person.PersonOutDTO;
import org.masyanas.model.person.Person;

import java.util.List;

public interface PersonDTOFactory
{

    Person createModel(final PersonInDTO personInDTO);

    void updateModel(final PersonInDTO personInDTO, final Person model);

    PersonOutDTO createOutDTO(final Person model);

    List<PersonOutDTO> createListOutDTO(final List<Person> personList);

    IdentifierOutDTO createIdentifierOutDTO(final Person person);

}
