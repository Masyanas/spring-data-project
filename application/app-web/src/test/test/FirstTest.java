import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import config.AppConfig;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.masyanas.repository.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        AppConfig.class
})
@TestExecutionListeners({
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

//    @Autowired
//    PersonLightWeightService personLightWeightService;

    @Autowired
    PersonRepository personRepository;

    @Test
    public void testFindAll() {
        Assert.assertEquals(personRepository.findAllByOrderByIdAsc().size(), 3L);
    }
}
