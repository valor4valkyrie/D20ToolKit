package com.d20.services;

import com.d20.view.MainMenu;
import com.d20.view.future.FutureView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewService {

    @Autowired
    Stage stage;

    @Autowired
    FutureView futureView;

    @Autowired
    private MainMenu mainMenu;

    private Image ddBackgroundImage = new Image("./images/DDBackground.jpg");
    private BackgroundImage backgroundImage = new BackgroundImage(ddBackgroundImage, BackgroundRepeat.REPEAT,
            BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

    public ViewService(){}

    public void setMainScene(Pane pane, boolean fullscreen){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(pane);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(getMenuBar());
        borderPane.setCenter(scrollPane);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(borderPane);

/*        Background background = new Background(backgroundImage);;
        borderPane.setBackground(background);
        pane.setBackground(background);*/

        Scene scene = new Scene(stackPane,  stage.getWidth(), stage.getHeight());
        scene.getStylesheets().add("../resources/css/general.css");
        stage.setScene(scene);
        stage.setFullScreen(fullscreen);
        stage.setTitle("D20 Toolkit");
        stage.show();
    }

    public void getMainMenu(){
        setMainScene(mainMenu.getMainMenu(), true);
    }

    public boolean isFullScreen(){
        return stage.isFullScreen();
    }

    public MenuBar getMenuBar(){
        //MenuBar
        MenuBar menubar = new MenuBar();
        menubar.setPrefWidth(stage.getWidth());

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

    public Scene getCurrentScene(){return stage.getScene();}

    public Stage getStage() {
        return stage;
    }

    public double getScreenWidth(){
        return this.stage.getWidth();
    }

    public double getScreenHeight(){
        return this.stage.getHeight();
    }

    public FutureView getFutureView() {
        return futureView;
    }

}
