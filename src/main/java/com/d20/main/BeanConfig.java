package com.d20.main;

import javafx.stage.Stage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@PropertySource(value = "/resources/application.properties")
public class BeanConfig {

    @Bean
    public Stage stage(){
        return new Stage();
    }

    @Bean
    public MainView mainView(Stage stage){
        return new MainView(stage);
    }

}
