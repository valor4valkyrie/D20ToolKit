package com.d20.services;

import com.d20.model.Stats;
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

    public ViewService(){
    }

    @Autowired
    public ViewService(Stage stage){
        this.stage = stage;
    }

    public Scene getCurrentScene(){
        return stage.getScene();
    }

    public void setMainScene(Pane pane){
        pane.setPadding(new Insets(15, 15, 25, 15));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(pane);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(getMenuBar());
        borderPane.setCenter(scrollPane);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(borderPane);

        switch (style) {
            case "future":
                pane.getStylesheets().add("../resources/css/future.css");
                break;
            default:
                pane.getStylesheets().add("../resources/css/stats.css");
                break;
        }

        Scene scene = new Scene(stackPane,  stage.getWidth() - 100, stage.getHeight());
        scene.getStylesheets().add("../resources/css/general.css");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle("D20 Toolkit");
        stage.show();
    }

    // TODO: Refactor out both 'setMainScene(Pane)' and 'setMainScene(TabPane)' to share functionality
    public void setMainScene(TabPane pane){
        pane.setPadding(new Insets(15, 15, 25, 15));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(pane);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(getMenuBar());
        borderPane.setCenter(scrollPane);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(borderPane);

        switch (style) {
            case "future":
                pane.getStylesheets().add("../resources/css/future.css");
                break;
            default:
                pane.getStylesheets().add("../resources/css/stats.css");
                break;
        }

        Scene scene = new Scene(stackPane,  stage.getWidth() - 100, stage.getHeight());
        scene.getStylesheets().add("../resources/css/general.css");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle("D20 Toolkit");
        stage.show();
    }

    public void setMainScene(Pane pane, Stage stage){
        pane.setPadding(new Insets(15, 15, 25, 15));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(pane);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(getMenuBar());
        borderPane.setCenter(scrollPane);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(borderPane);

        switch (style) {
            case "future":
                pane.getStylesheets().add("../resources/css/future.css");
                break;
            default:
                pane.getStylesheets().add("../resources/css/stats.css");
                break;
        }

        Scene scene = new Scene(stackPane,  getScreenWidth() - 100, getScreenHeight());
        scene.getStylesheets().add("../resources/css/general.css");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle("D20 Toolkit");
        stage.show();
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
        return Screen.getPrimary().getBounds().getWidth() - 25;
    }

    public double getScreenHeight() {
        return Screen.getPrimary().getBounds().getHeight();
    }

    public String getStyle() { return style; }

    public void setStyle(String style) { this.style = style; }

    public List<Node> getSceneElements(){
        List<Node> nodesList = stage.getScene().getRoot().getChildrenUnmodifiable();
        return nodesList;
    }

    public TabPane getCharacterSheet(long id){
        //PlayerCharacter character = characterService.getCharacter(id);

        /*if(character == null) {
            Stats stats = new Stats();
            stats.setStrength(new Stat("Charisma", 10));
            stats.setDexterity(new Stat("Charisma", 10));
            stats.setConstitution(new Stat("Constitution", 10));
            stats.setIntelligence(new Stat("Charisma", 10));
            stats.setWisdom(new Stat("Charisma", 10));
            stats.setCharisma(new Stat("Charisma", 10));
            characterSheet = new CharacterSheetImpl();
            character.setStats(stats);
            characterSheet.getCharacterSheet();
            return new TabPane();
        }*/

        return new TabPane();

    }

    public void setCharacterStats(Stats stats){

    }
}
