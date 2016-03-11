package config;

import org.masyanas.dto.factory.person.PersonDTOFactory;
import org.masyanas.dto.factory.person.PersonDTOFactoryImpl;
import org.masyanas.service.person.PersonLightWeightService;
import org.masyanas.service.person.PersonLightWeightServiceImpl;
import org.masyanas.service.person.PersonService;
import org.masyanas.service.person.PersonServiceImpl;
import org.masyanas.web.person.PersonController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(AppConfig.class)
public class TestAppConfig
{
    @Bean
    PersonController personController()
    {
        return new PersonController();
    }

    @Bean
    PersonLightWeightService personLightWeightService()
    {
        return new PersonLightWeightServiceImpl();
    }

    @Bean
    PersonService personService()
    {
        return new PersonServiceImpl();
    }

    @Bean
    PersonDTOFactory personDTOFactory()
    {
        return new PersonDTOFactoryImpl();
    }
}
