package com.d20.view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;


public class MainMenu implements MainMenuInt{

    private Image ddBackgroundImage = new Image("./images/DDBackground.jpg");
    private BackgroundImage background = new BackgroundImage(ddBackgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

    public void MainMenu() {}

    public Scene getMainMenu(){
        BorderPane borderPane = new BorderPane();
        Background background1 = new Background(new BackgroundFill(javafx.scene.paint.Color.color(0, 0,  0, 0.6), null, null));
        borderPane.setBackground(background1);

        //MenuBar
        MenuBar menubar = new MenuBar();
        menubar.setPrefWidth(1800);

        //Menus
        Menu file = new Menu("File");
        Menu dmTools = new Menu("DM Tools");

        //MenuItems
        MenuItem exit = new MenuItem("Exit");
        MenuItem loadCharacter = new MenuItem("Load Character");
        MenuItem newCharacter = new MenuItem("New Character");
        MenuItem createNPC = new MenuItem("Create NPC");
        MenuItem createEncounter = new MenuItem("Create Encounter");

        //MenuItems actions
        exit.setOnAction(e -> Platform.exit());
        newCharacter.setOnAction(e -> new NewCharacterView().getNewCharacterMenu());

        //Add MenuItems to Menus
        file.getItems().addAll(newCharacter, loadCharacter, exit);
        dmTools.getItems().addAll(createNPC, createEncounter);

        //Add Menus to the Menubar
        menubar.getMenus().add(file);
        menubar.getMenus().add(dmTools);

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