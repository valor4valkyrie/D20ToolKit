package com.d20.main;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "../resources/application.properties")
public class BeanConfig {

    @Autowired
    Environment env;

    @Bean
    public Stage stage(){
        return new Stage();
    }

    @Bean
    public Utilities utilities(){
        return new Utilities();
    }

    @Bean
    public ApplicationContextProvider applicationContextProvider(){
        return new ApplicationContextProvider();
    }

}