package com.d20.main;

import javafx.stage.Stage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "../resources/application.properties")
public class BeanConfig {

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