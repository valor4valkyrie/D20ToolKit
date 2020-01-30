package com.d20.view.future;

import com.d20.model.PlayerCharacter;
import com.d20.model.CharacterClass;
import com.d20.services.*;
import com.d20.model.future.FutureClassDescription;
import com.d20.view.NewStatsView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class NewFutureView {

    private EventsService eventsService;
    private CharacterService characterService;
    private NewStatsView newStatsView;

    final static String SMART_HERO = "Smart Hero";
    final static String TOUGH_HERO = "Tough Hero";
    final static String FAST_HERO = "Fast Hero";
    final static String DEDICATED_HERO = "Dedicated Hero";
    final static String STRONG_HERO = "Strong Hero";
    final static String CHARISMATIC_HERO = "Charismatic Hero";

    @Autowired
    public NewFutureView(EventsService eventsService, ViewService viewService, CharacterService characterService) {
        this.eventsService = eventsService;
        this.characterService = characterService;
        newStatsView = new NewStatsView(eventsService, viewService);
    }

    @Autowired
    public void setEventsService(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @Autowired
    public  void setCharacterService(CharacterService characterService){
        this.characterService = characterService;
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

        //back.setOnMouseClicked(e -> viewService.getMainMenu());
        beginner.setOnMouseClicked(e -> eventsService.setSceneEvent(classNewCharacter()));

        back.getStyleClass().add("navigation-button");
        back.setPrefSize(225, 300);

        vBox.getChildren().addAll(beginner, advanced, back);
        borderPane.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setBackground(new ImageService().createBackgroundImage(new ImageService().getFutureImage("GeneticCatCharacter"), BackgroundPosition.CENTER));
        borderPane.getStylesheets().addAll("../resources/css/stats.css", "../resources/css/future.css");

        return borderPane;
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
        back.setOnMouseClicked(e -> eventsService.setSceneEvent(getNewCharacter()));
        //--Smart Hero
        smartHero.setOnMouseClicked(e -> {
            PlayerCharacter character = characterService.createNewCharacter();
            characterService.addCharacterClass(character.getCharacterId(), new CharacterClass(SMART_HERO, 1));
            eventsService.setSceneEvent(newStatsView.getGuidedStatsPane());
        });
        smartHero.setOnMouseEntered(e -> {
            textArea.clear();
            textArea.setText(classDescription.getSmartHeroDesc());
        });

        //--Strong Hero
        strongHero.setOnMouseClicked(e -> {
            PlayerCharacter character = characterService.createNewCharacter();
            characterService.addCharacterClass(character.getCharacterId(), new CharacterClass(STRONG_HERO, 1));
            eventsService.setSceneEvent(newStatsView.getGuidedStatsPane());
        });
        strongHero.setOnMouseEntered(e -> {
            textArea.clear();
            textArea.setText(classDescription.getStrongHeroDesc());
        });

        //--Fast Hero
        fastHero.setOnMouseClicked(e -> {
            PlayerCharacter character = characterService.createNewCharacter();
            characterService.addCharacterClass(character.getCharacterId(), new CharacterClass(FAST_HERO, 1));
            eventsService.setSceneEvent(newStatsView.getGuidedStatsPane());
        });
        fastHero.setOnMouseEntered(e -> {
            textArea.clear();
            textArea.setText(classDescription.getFastHeroDesc());
        });

        //--Tough Hero
        toughHero.setOnMouseClicked(e -> {
            PlayerCharacter character = characterService.createNewCharacter();
            characterService.addCharacterClass(character.getCharacterId(), new CharacterClass(TOUGH_HERO, 1));
            eventsService.setSceneEvent(newStatsView.getGuidedStatsPane());
        });
        toughHero.setOnMouseEntered(e -> {
            textArea.clear();
            textArea.setText(classDescription.getToughHeroDesc());
        });

        //--Charismatic Hero
        charismaticHero.setOnMouseClicked(e -> {
            PlayerCharacter character = characterService.createNewCharacter();
            characterService.addCharacterClass(character.getCharacterId(), new CharacterClass(CHARISMATIC_HERO, 1));
            eventsService.setSceneEvent(newStatsView.getGuidedStatsPane());
        });
        charismaticHero.setOnMouseEntered(e -> {
            textArea.clear();
            textArea.setText(classDescription.getCharismaticHeroDesc());
        });

        //--Dedicated Hero
        dedicatedHero.setOnMouseClicked(e -> {
            PlayerCharacter character = characterService.createNewCharacter();
            characterService.addCharacterClass(character.getCharacterId(), new CharacterClass(DEDICATED_HERO, 1));
            eventsService.setSceneEvent(newStatsView.getGuidedStatsPane());
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
        borderPane.getStylesheets().addAll("../resources/css/stats.css", "../resources/css/future.css");

        return borderPane;
    }

}
