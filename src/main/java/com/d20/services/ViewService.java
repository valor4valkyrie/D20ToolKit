package com.d20.services;

import com.d20.view.CharacterSheetView;
import com.d20.view.MainMenu;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewService {

    private static Stage stage;
    private MainMenu mainMenu;

    private String style = "default";

    private CharacterSheetView characterSheetView;

    private Image ddBackgroundImage = new Image("./images/DDBackground.jpg");
    private BackgroundImage backgroundImage = new BackgroundImage(ddBackgroundImage, BackgroundRepeat.REPEAT,
            BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

    public ViewService(){}

    @Autowired
    public ViewService(Stage stage){
        this.stage = stage;
    }

    public Scene getCurrentScene(){
        return stage.getScene();
    }

    public ScrollPane getScrollPane(Pane pane){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(pane);

        return scrollPane;
    }

    public Pane setPaneStyling(Pane pane) {

        switch (style) {
            case "future":
                pane.getStylesheets().add("../resources/css/future.css");
                break;
            default:
                pane.getStylesheets().add("../resources/css/stats.css");
                break;
        }

        pane.setPadding(new Insets(15, 15, 15, 15));

        pane.setMinSize(getScreenWidth(), getScreenHeight());

        return pane;
    }

    public void setMainScene(Pane pane){
        setMainScene(pane, null);
    }

    public void setMainScene(TabPane pane){
        Pane newPane = new Pane();
        pane.setMinSize(getScreenWidth(), getScreenHeight());
        newPane.setMinSize(getScreenWidth(), getScreenHeight());
        newPane.getChildren().addAll(pane);
        setMainScene(newPane);
    }

    public void setMainScene(Pane pane, Stage customStage){

        ScrollPane scrollPane = getScrollPane(setPaneStyling(pane));

        BorderPane borderPane = new BorderPane();
        borderPane.setMinSize(getScreenWidth(), getScreenHeight());
        borderPane.setTop(getMenuBar());
        borderPane.setCenter(scrollPane);
        borderPane.getCenter().autosize();

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(borderPane);
        stackPane.setMinSize(getScreenWidth(), getScreenHeight());

        Scene scene = new Scene(stackPane,  getScreenWidth(), getScreenHeight());
        scene.getStylesheets().add("../resources/css/general.css");
        if(customStage != null) {
            customStage.setScene(scene);
            customStage.setFullScreen(true);
            customStage.setTitle("D20 Toolkit");
            customStage.show();
        } else {
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setTitle("D20 Toolkit");
            stage.show();
        }
    }

    public MenuBar getMenuBar(){
        //MenuBar
        MenuBar menubar = new MenuBar();
        menubar.setPrefWidth(this.getStage().getWidth());

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
        //mainMenu.setOnAction(e -> this.getMainMenu(imageService, futureViewService));

        //Add MenuItems to Menus
        file.getItems().addAll(mainMenu, loadCharacter, exit);
        dmTools.getItems().addAll(createNPC, createEncounter);

        //Add Menus to the Menubar
        menubar.getMenus().add(file);
        menubar.getMenus().add(dmTools);

        return menubar;
    }

    public static Stage getStage() {
        return stage;
    }

    public double getScreenWidth(){
        return Screen.getPrimary().getVisualBounds().getWidth() - 15;
    }

    public double getScreenHeight() {
        return Screen.getPrimary().getVisualBounds().getHeight() - 15;
    }

    public String getStyle() { return style; }

    public void setStyle(String style) { this.style = style; }

    public List<Node> getSceneElements(){
        List<Node> nodesList = stage.getScene().getRoot().getChildrenUnmodifiable();
        return nodesList;
    }
}
