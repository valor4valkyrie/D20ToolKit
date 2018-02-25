package com.d20.main;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class MainView {

    @Autowired
    private Stage stage;

    public MainView(Stage primaryStage){
        BorderPane borderPane = new BorderPane();
        MenuBar menubar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem addFile = new MenuItem("...Add File");
        file.getItems().add(addFile);
        menubar.getMenus().add(file);
        TabPane tabPane =  new TabPane();
        Tab statsPane = new Tab();
        Button button = new Button();
        button.setText("Test");
        button.setMaxSize(50, 50);
        statsPane.setText("Stats");
        tabPane.getTabs().add(statsPane);
        borderPane.setTop(menubar);
        borderPane.setCenter(tabPane);
        borderPane.setTop(button);
        button.setOnAction(e -> buttonFunction());
        Scene scene = new Scene(borderPane, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public void buttonFunction(){
        BorderPane borderPane = new BorderPane();
        Button button = new Button();
        button.setText("Test2");
        borderPane.setCenter(button);
        Scene scene = new Scene(borderPane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }
}
