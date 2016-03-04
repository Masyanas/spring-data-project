import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import config.TestAppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.masyanas.repository.person.PersonRepository;
import org.masyanas.service.person.PersonLightWeightService;
import org.masyanas.web.person.PersonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        TestAppConfig.class
})
@TestExecutionListeners({
        DirtiesContextTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
})
@DatabaseSetup(FirstTest.DATASET)
@DirtiesContext
@WebAppConfiguration
public class FirstTest
{

    protected static final String DATASET = "database-init.xml";

    @Autowired
    PersonController personController;

    @Autowired
    PersonLightWeightService personLightWeightService;

    @Autowired
    PersonRepository personRepository;

    @Test
    public void testFindAll()
    {
        assertEquals(personRepository.findAllByOrderByIdAsc().size(), 3L);
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

//    @Test
//    @ExpectedDatabase(value = "personData_afterUpdateExpected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
//    public void testUpdate()
//    {
//        PersonInDTO personIdDTO = new PersonInDTO();
//        personIdDTO.setName("Gregoriy");
//        personIdDTO.setSurname("Petrosyan");
//        personController.update(1L, personIdDTO);
//    }

}
