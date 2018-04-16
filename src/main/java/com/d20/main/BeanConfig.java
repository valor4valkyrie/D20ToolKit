package com.d20.main;

import com.d20.model.future.FutureModel;
import com.d20.view.MainMenu;
import com.d20.view.MainView;
import com.d20.view.future.FutureView;
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
    public MainView mainView(){ return new MainView(); }

    @Bean
    public MainMenu mainMenu(){ return new MainMenu(); }

}