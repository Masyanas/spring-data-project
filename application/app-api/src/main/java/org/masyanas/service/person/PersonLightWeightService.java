package org.masyanas.service.person;

import org.masyanas.dto.IdentifierOutDTO;
import org.masyanas.dto.person.PersonInDTO;
import org.masyanas.dto.person.PersonOutDTO;

import java.util.List;

public interface PersonLightWeightService
{

    PersonOutDTO findById(final Long personId);

    List<PersonOutDTO> findAll();

    PersonOutDTO create(final PersonInDTO personInDTO);

    PersonOutDTO update(final Long personId, final PersonInDTO personInDTO);

    IdentifierOutDTO delete(final Long personId);

}
