package com.d20.main;

import com.d20.view.MainMenu;
import com.d20.view.MainMenuInt;
import com.d20.view.MainView;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class D20ToolKit extends Application{

    @Override
    public void start(Stage primaryStage) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        context.getBean(MainView.class);
    }


    public static void main(String[] args){
        launch(args);
    }
}
