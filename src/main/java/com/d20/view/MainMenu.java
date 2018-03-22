package com.d20.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;


public class MainMenu implements MainMenuInt{

    private Image ddBackgroundImage = new Image("./images/DDBackground.jpg");
    private BackgroundImage background = new BackgroundImage(ddBackgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

    public void MainMenu() {}

    public Scene getMainMenu(){
        BorderPane borderPane = new BorderPane();
        Background background1 = new Background(new BackgroundFill(javafx.scene.paint.Color.color(0, 0,  0, 0.6), null, null));
        borderPane.setBackground(background1);
        MenuBar menubar = new MenuBar();
        menubar.setPrefWidth(1800);
        Menu file = new Menu("File");
        MenuItem addFile = new MenuItem("...Add File");
        file.getItems().add(addFile);
        menubar.getMenus().add(file);
        TabPane tabPane = new TabPane();
        Tab statsPane = new Tab();
        statsPane.setText("Stats");
        tabPane.getTabs().add(statsPane);
        borderPane.setTop(menubar);
        borderPane.setCenter(tabPane);
        //button.setOnAction(e -> new MainMenu().setNewScene());
        Scene scene = new Scene(borderPane, 800, 800);

        return scene;
    }
}
