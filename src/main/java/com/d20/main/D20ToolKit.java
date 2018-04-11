package com.d20.main;

import com.d20.view.MainView;
import com.d20.view.NewCharacterView;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan
public class D20ToolKit extends Application{

    @Override
    public void start(Stage primaryStage) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(D20ToolKit.class);
        context.register(BeanConfig.class);
        context.refresh();

        MainView mainView = context.getBean(MainView.class);
        mainView.getMainMenu();

    }


    public static void main(String[] args){
        launch(args);
    }
}
