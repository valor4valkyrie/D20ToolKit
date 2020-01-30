package com.d20;

import com.d20.services.EventsService;
import com.d20.services.ViewService;
import javafx.stage.Stage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource(value = "../resources/application.properties")
public class BeanConfig {

    @Bean
    public Stage stage(){
        return new Stage();
    }

    @Bean
    public ViewService viewService() {
        return new ViewService(stage());
    }

    @Bean
    public EventsService eventsService(){
        return new EventsService(viewService());
    }

}