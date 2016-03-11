import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import config.TestAppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.masyanas.dto.person.PersonInDTO;
import org.masyanas.dto.person.PersonOutDTO;
import org.masyanas.web.person.PersonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        TestAppConfig.class
})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class,
})
@DatabaseSetup(FirstTest.DATASET)
@WebAppConfiguration
public class FirstTest
{

    protected static final String DATASET = "database-init.xml";

    @Autowired
    PersonController personController;

    @Test
    public void testFindAll()
    {
        assertEquals(personController.findAll().size(), 3L);
    }

    @Test
    public void testFindByName()
    {
        assertNotNull(personController.findById(1L));
    }

    @Test
    @ExpectedDatabase(value = "personData_afterDeleteExpected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testDelete()
    {
        personController.delete(1L);
    }

    @Test
    @ExpectedDatabase(value = "personData_afterUpdateExpected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testUpdate()
    {
        PersonInDTO personIdDTO = new PersonInDTO();
        personIdDTO.setName("Gregoriy");
        personIdDTO.setSurname("Petrosyan");
        personController.update(1L, personIdDTO);
    }

    @Test
    @Transactional
    public void testCreate()
    {
        PersonInDTO personIdDTO = new PersonInDTO();
        personIdDTO.setName("Veniamin");
        personIdDTO.setSurname("Kozlov");
        PersonOutDTO personOutDTO = personController.create(personIdDTO);
        assertEquals(personOutDTO.getId().longValue(), 1L);
        assertEquals(personOutDTO.getName(), "Veniamin");
        assertEquals(personOutDTO.getSurname(), "Kozlov");
    }

}
