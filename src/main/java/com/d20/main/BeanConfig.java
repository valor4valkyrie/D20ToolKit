package com.d20.main;

import com.d20.view.MainMenu;
import com.d20.view.MainView;
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

    @Bean
    public MainMenu mainMenu(){ return new MainMenu(); }

}
