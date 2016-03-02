package org.masyanas.dto.factory.person;

import org.masyanas.dto.IdentifierOutDTO;
import org.masyanas.dto.person.PersonInDTO;
import org.masyanas.dto.person.PersonOutDTO;
import org.masyanas.model.person.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDTOFactoryImpl implements PersonDTOFactory
{

    @Override
    public Person createModel(PersonInDTO personInDTO)
    {

        final Person person = new Person();

        initModel(person, personInDTO);

        return person;

    }

    @Override
    public void updateModel(PersonInDTO personInDTO, Person model)
    {
        initModel(model, personInDTO);
    }

    @Override
    public PersonOutDTO createOutDTO(Person model)
    {

        PersonOutDTO dto = null;
        if (model != null)
        {

            dto = new PersonOutDTO();
            dto.setId(model.getId());
            dto.setName(model.getName());
            dto.setSurname(model.getSurname());

        }
        return dto;

    }

    @Override
    public List<PersonOutDTO> createListOutDTO(List<Person> personList)
    {

        final List<PersonOutDTO> outList = new ArrayList<PersonOutDTO>();
        if (personList != null)
        {
            for (Person person: personList)
            {
                outList.add(createOutDTO(person));
            }
        }
        return outList;

    }

    @Override
    public IdentifierOutDTO createIdentifierOutDTO(Person person)
    {

        final IdentifierOutDTO outDTO = new IdentifierOutDTO();
        if (person != null)
        {
            outDTO.setId(person.getId());
        }
        return outDTO;

    }

    private void initModel(final Person person, final PersonInDTO personInDTO)
    {

        person.setName(personInDTO.getName());
        person.setSurname(personInDTO.getSurname());

    }
}
