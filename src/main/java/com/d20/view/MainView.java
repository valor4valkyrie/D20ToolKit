package com.d20.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

public class MainView implements MainViewInt{

    @Autowired
    private Stage stage;

    public MainView(Stage primaryStage){
        stage = primaryStage;
        getMainMenu();
    }

    @Override
    public void setMainScene(Scene scene){
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void getMainMenu(){
        MainMenu menu = new MainMenu();
        setMainScene(menu.getMainMenu());
    }
}
