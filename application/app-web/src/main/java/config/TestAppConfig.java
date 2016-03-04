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
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@EnableJpaRepositories(basePackages = {
        "org.masyanas.repository",
})
@EnableJpaAuditing
@EnableTransactionManagement
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

    @Bean
    DataSource dataSource()
    {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.HSQL).build();
    }

    @Bean
    EntityManagerFactory entityManagerFactory()
    {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("org.masyanas.model");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    PlatformTransactionManager transactionManager()
    {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }
}
