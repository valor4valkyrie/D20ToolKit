package com.d20.view.future;

import com.d20.main.Utilities;
import com.d20.model.Character;
import com.d20.model.CharacterClassImpl;
import com.d20.model.future.FutureClassDescription;
import com.d20.services.CharacterService;
import com.d20.services.ImageService;
import com.d20.services.ViewService;
import com.d20.view.MainMenu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class NewFutureView {

    @Autowired
    private ViewService viewService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private Utilities utilities;

    @Autowired
    private NewFutureStatsView newFutureStatsView;

    final static String SMART_HERO = "Smart Hero";
    final static String TOUGH_HERO = "Tough Hero";
    final static String FAST_HERO = "Fast Hero";
    final static String DEDICATED_HERO = "Dedicated Hero";
    final static String STRONG_HERO = "Strong Hero";
    final static String CHARISMATIC_HERO = "Charismatic Hero";

    public NewFutureView(){
    }

    public BorderPane getNewCharacter(){

        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox();
        vBox.setSpacing(50);
        vBox.setPadding(new Insets(100, 100, 100, 100));
        vBox.setMaxSize(800, 550);
        vBox.getStyleClass().add("vBox");

        Image futureImage = new Image("./images/D20_Future_Book_Cover.jpg");

        Button beginner = new Button("Beginner: Guide Me Through a New Character.", new ImageView(futureImage));
        Button advanced = new Button("Advanced: Let Me Pick Where to Go.", new ImageView(futureImage));
        Button back = new Button("Back");

        beginner.setPrefSize(750, 500);
        advanced.setPrefSize(750, 500);

        back.setOnMouseClicked(e -> viewService.getMainMenu());
        beginner.setOnMouseClicked(e -> viewService.setMainScene(classNewCharacter(), viewService.isFullScreen()));

        back.getStyleClass().add("navigation-button");
        back.setPrefSize(225, 300);

        vBox.getChildren().addAll(beginner, advanced, back);
        borderPane.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setBackground(imageService.createBackgroundImage(imageService.getFutureImage("GeneticCatCharacter"), BackgroundPosition.CENTER));
        borderPane.getStylesheets().add("../resources/css/stats.css");

        return  borderPane;
    }

    public BorderPane classNewCharacter(){
        BorderPane borderPane = new BorderPane();

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(25);
        flowPane.setAlignment(Pos.CENTER);

        VBox descriptionBox = new VBox();

        StringBuilder classDesc = new StringBuilder();

        try {
            Path classDescPath = Paths.get(getClass().getClassLoader()
                    .getResource("./guide/classGuide.txt").toURI());
            Stream<String> lines = Files.lines(classDescPath);
            lines.forEach(line -> classDesc.append(line).append("\n"));
            lines.close();
        }catch (Exception e){
            e.getMessage();
        }

        TextArea descTextArea = new TextArea(classDesc.toString());
        descTextArea.getStyleClass().add("text-area");
        descTextArea.setEditable(false);
        descTextArea.setWrapText(true);
        descTextArea.setPrefRowCount(12);
        descTextArea.setVisible(true);

        Label classDescriptionLabel = new Label("Description");
        classDescriptionLabel.getStyleClass().add("title-text");

        //Button Images
        ImageView fastImage = new ImageView(new Image("./images/Future/FutureFastHero.jpeg"));
        ImageView strongImage = new ImageView(new Image("./images/Future/FutureStrongHero.jpeg"));
        ImageView smartImage = new ImageView(new Image("./images/Future/FutureSmartHero.jpeg"));
        ImageView charismaticImage = new ImageView(new Image("./images/Future/FutureCharismaticHero.jpeg"));
        ImageView dedicatedImage = new ImageView(new Image("./images/Future/FutureDedicatedHero.jpeg"));
        ImageView toughImage = new ImageView(new Image("./images/Future/FutureToughHero.jpeg"));

        //Class Buttons
        Button fastHero = new Button("Fast Hero", fastImage);
        Button strongHero = new Button("Strong Hero", strongImage);
        Button toughHero = new Button("Tough Hero", toughImage);
        Button smartHero = new Button("Smart Hero", smartImage);
        Button dedicatedHero = new Button("Dedicated Hero", dedicatedImage);
        Button charismaticHero = new Button("Charismatic Hero", charismaticImage);
        Button back = new Button("Back");
        back.setPrefSize(225, 300);

        fastHero.getStyleClass().add("character-class");
        strongHero.getStyleClass().add("character-class");
        toughHero.getStyleClass().add("character-class");
        smartHero.getStyleClass().add("character-class");
        dedicatedHero.getStyleClass().add("character-class");
        charismaticHero.getStyleClass().add("character-class");

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefRowCount(12);

        FutureClassDescription classDescription = new FutureClassDescription();

        //Button Actions
        back.setOnMouseClicked(e -> viewService.setMainScene(getNewCharacter(), viewService.isFullScreen()));
        //--Smart Hero
        smartHero.setOnMouseClicked(e -> {
            Character character = characterService.createNewCharacter();
            characterService.addCharacterClass(character.getCharacterId(), new CharacterClassImpl(SMART_HERO, 1));
            viewService.setMainScene(newFutureStatsView.getGuidedStatsPane(), viewService.isFullScreen());
        });
        smartHero.setOnMouseEntered(e -> {
            textArea.clear();
            textArea.setText(classDescription.getSmartHeroDesc());
        });

        //--Strong Hero
        strongHero.setOnMouseClicked(e -> {
            Character character = characterService.createNewCharacter();
            characterService.addCharacterClass(character.getCharacterId(), new CharacterClassImpl(STRONG_HERO, 1));
            viewService.setMainScene(newFutureStatsView.getGuidedStatsPane(), viewService.isFullScreen());
        });
        strongHero.setOnMouseEntered(e -> {
            textArea.clear();
            textArea.setText(classDescription.getStrongHeroDesc());
        });

        //--Fast Hero
        fastHero.setOnMouseClicked(e -> {
            Character character = characterService.createNewCharacter();
            characterService.addCharacterClass(character.getCharacterId(), new CharacterClassImpl(FAST_HERO, 1));
            viewService.setMainScene(newFutureStatsView.getGuidedStatsPane(), viewService.isFullScreen());
        });
        fastHero.setOnMouseEntered(e -> {
            textArea.clear();
            textArea.setText(classDescription.getFastHeroDesc());
        });

        //--Tough Hero
        toughHero.setOnMouseClicked(e -> {
            Character character = characterService.createNewCharacter();
            characterService.addCharacterClass(character.getCharacterId(), new CharacterClassImpl(TOUGH_HERO, 1));
            viewService.setMainScene(newFutureStatsView.getGuidedStatsPane(), viewService.isFullScreen());
        });
        toughHero.setOnMouseEntered(e -> {
            textArea.clear();
            textArea.setText(classDescription.getToughHeroDesc());
        });

        //--Charismatic Hero
        charismaticHero.setOnMouseClicked(e -> {
            Character character = characterService.createNewCharacter();
            characterService.addCharacterClass(character.getCharacterId(), new CharacterClassImpl(CHARISMATIC_HERO, 1));
            viewService.setMainScene(newFutureStatsView.getGuidedStatsPane(), viewService.isFullScreen());
        });
        charismaticHero.setOnMouseEntered(e -> {
            textArea.clear();
            textArea.setText(classDescription.getCharismaticHeroDesc());
        });

        //--Dedicated Hero
        dedicatedHero.setOnMouseClicked(e -> {
            Character character = characterService.createNewCharacter();
            characterService.addCharacterClass(character.getCharacterId(), new CharacterClassImpl(DEDICATED_HERO, 1));
            viewService.setMainScene(newFutureStatsView.getGuidedStatsPane(), viewService.isFullScreen());
        });
        dedicatedHero.setOnMouseEntered(e -> {
            textArea.clear();
            textArea.setText(classDescription.getDedicatedHeroDesc());
        });

        descriptionBox.getChildren().addAll(classDescriptionLabel, textArea);
        flowPane.getChildren().addAll(fastHero, smartHero, strongHero, toughHero, charismaticHero, dedicatedHero, back);

        borderPane.setTop(descTextArea);
        borderPane.setCenter(flowPane);
        borderPane.setBottom(descriptionBox);

        back.getStyleClass().add("back-button");
        borderPane.getStylesheets().add("../resources/css/stats.css");

        return borderPane;
    }

    public BorderPane getSmartHero(){
        BorderPane borderPane = new BorderPane();

        FlowPane flowPane = new FlowPane();

        Text description = new Text("Smart Heroes are smart.");

        Button back = new Button("Back");

        //Button Actions
        back.setOnMouseClicked(e -> viewService.setMainScene(classNewCharacter(), viewService.isFullScreen()));

        back.getStyleClass().add("back-button");
        back.setPrefSize(225, 300);
        borderPane.getStylesheets().add("../resources/css/stats.css");

        flowPane.getChildren().addAll(back, description);
        borderPane.setCenter(flowPane);

        return borderPane;
    }
}
