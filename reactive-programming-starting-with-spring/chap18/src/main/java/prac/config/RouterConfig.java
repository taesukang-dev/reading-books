package prac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;


@Configuration
public class RouterConfig {
    @Bean
    public Validator javaxValidator() {
//        return (Validator) new LocalValidatorFactoryBean();
        return null;
    }
}
