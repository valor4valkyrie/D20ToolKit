package com.d20.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

public class main extends Application{

    @Override
    public void start(Stage primaryStage) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        context.getBean(MainView.class);
    }


    public static void main(String[] args){
        launch(args);
    }
}
