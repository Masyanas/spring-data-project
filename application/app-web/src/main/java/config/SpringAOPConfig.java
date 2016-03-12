package config;

import org.masyanas.aspects.SystemArchitecture;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class SpringAOPConfig {

    @Bean
    SystemArchitecture systemArchitecture()
    {
        return new SystemArchitecture();
    }

}
