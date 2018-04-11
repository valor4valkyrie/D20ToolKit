package com.d20.view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainView implements MainViewInt{

    @Autowired
    MainMenu menu;

    @Autowired
    NewCharacterView newCharacterView;

    @Autowired
    Stage stage;

    public MainView(){
    }

    @Override
    public void setMainScene(Scene scene, boolean fullscreen){
        stage.setScene(scene);
        stage.setFullScreen(fullscreen);
        stage.show();
    }

    public void getMainMenu(){
        setMainScene(menu.getMainMenu(), true);
    }

    public boolean isFullScreen(){
        return stage.isFullScreen();
    }

    public MenuBar getMenuBar(){
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
        MenuItem mainMenu = new MenuItem("Main Menu");
        MenuItem createNPC = new MenuItem("Create NPC");
        MenuItem createEncounter = new MenuItem("Create Encounter");

        //MenuItems actions
        exit.setOnAction(e -> Platform.exit());
        newCharacter.setOnAction(e -> newCharacterView.getNewCharacterMenu());
        mainMenu.setOnAction(e -> this.getMainMenu());

        //Add MenuItems to Menus
        file.getItems().addAll(mainMenu, newCharacter, loadCharacter, exit);
        dmTools.getItems().addAll(createNPC, createEncounter);

        //Add Menus to the Menubar
        menubar.getMenus().add(file);
        menubar.getMenus().add(dmTools);

        return menubar;
    }
}
