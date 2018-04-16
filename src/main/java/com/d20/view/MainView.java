package com.d20.view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.nio.ch.sctp.SctpNet;

@Component
public class MainView implements MainViewInt{

    @Autowired
    MainMenu menu;

    @Autowired
    Stage stage;

    public MainView(){
    }

    @Override
    public void setMainScene(Pane pane, boolean fullscreen){
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(getMenuBar());
        borderPane.setCenter(pane);
        Scene scene = new Scene(borderPane, 800, 800);
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
        MenuItem mainMenu = new MenuItem("Main Menu");
        MenuItem createNPC = new MenuItem("Create NPC");
        MenuItem createEncounter = new MenuItem("Create Encounter");

        //MenuItems actions
        exit.setOnAction(e -> Platform.exit());
        mainMenu.setOnAction(e -> this.getMainMenu());

        //Add MenuItems to Menus
        file.getItems().addAll(mainMenu, loadCharacter, exit);
        dmTools.getItems().addAll(createNPC, createEncounter);

        //Add Menus to the Menubar
        menubar.getMenus().add(file);
        menubar.getMenus().add(dmTools);

        return menubar;
    }
}
