package com.d20.view.future;

import com.d20.main.Utilities;
import com.d20.model.future.FutureClassDescription;
import com.d20.view.MainMenu;
import com.d20.view.MainView;
import com.d20.view.NewStatsView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
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
    MainView view;

    @Autowired
    MainMenu mainMenu;

    @Autowired
    Utilities utilities;

    @Autowired
    NewStatsView newStatsView;

    public NewFutureView(){
    }

    public BorderPane getNewCharacter(){

        BorderPane borderPane = new BorderPane();
        FlowPane flowPane = new FlowPane();

        Image futureImage = new Image("./images/D20_Future_Book_Cover.jpg");

        Button beginner = new Button("Beginner: Guide Me Through a New Character.", new ImageView(futureImage));
        Button advanced = new Button("Advanced: Let Me Pick Where to Go.", new ImageView(futureImage));
        Button back = new Button("Back");

        back.setOnMouseClicked(e -> view.setMainScene(mainMenu.getMainMenu(), view.isFullScreen()));
        beginner.setOnMouseClicked(e -> view.setMainScene(classNewCharacter(), view.isFullScreen()));

        flowPane.getChildren().addAll(back, beginner, advanced);
        borderPane.setCenter(flowPane);

        return  borderPane;
    }

    public BorderPane classNewCharacter(){
        BorderPane borderPane = new BorderPane();

        FlowPane flowPane = new FlowPane();

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
        descTextArea.setVisible(true);

        Label classDescriptionLabel = new Label("Description");

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

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);

        FutureClassDescription classDescription = new FutureClassDescription();

        //Button Actions
        back.setOnMouseClicked(e -> view.setMainScene(getNewCharacter(), view.isFullScreen()));
        //--Smart Hero
        smartHero.setOnMouseClicked(e -> view.setMainScene(newStatsView.getSingleRolledStats(), view.isFullScreen()));
        smartHero.setOnMouseEntered(e -> {
            textArea.clear();
            textArea.setText(classDescription.getSmartHeroDesc());
        });

        strongHero.setOnMouseEntered(e -> {
            textArea.clear();
            textArea.setText(classDescription.getStrongHeroDesc());
        });

        fastHero.setOnMouseEntered(e -> {
            textArea.clear();
            textArea.setText(classDescription.getFastHeroDesc());
        });

        toughHero.setOnMouseEntered(e -> {
            textArea.clear();
            textArea.setText(classDescription.getToughHeroDesc());
        });

        charismaticHero.setOnMouseEntered(e -> {
            textArea.clear();
            textArea.setText(classDescription.getCharismaticHeroDesc());
        });

        dedicatedHero.setOnMouseEntered(e -> {
            textArea.clear();
            textArea.setText(classDescription.getDedicatedHeroDesc());
        });
        descriptionBox.getChildren().addAll(classDescriptionLabel, textArea);
        flowPane.getChildren().addAll(back, fastHero, smartHero, strongHero, toughHero, charismaticHero, dedicatedHero);

        borderPane.setTop(descTextArea);
        borderPane.setCenter(flowPane);
        borderPane.setBottom(descriptionBox);

        borderPane.getStylesheets().add("../resources/css/stats.css");

        return borderPane;
    }

    public BorderPane getSmartHero(){
        BorderPane borderPane = new BorderPane();

        FlowPane flowPane = new FlowPane();

        Text description = new Text("Smart Heroes are smart.");

        Button back = new Button("Back");

        //Button Actions
        back.setOnMouseClicked(e -> view.setMainScene(classNewCharacter(), view.isFullScreen()));

        flowPane.getChildren().addAll(back, description);
        borderPane.setCenter(flowPane);

        return borderPane;
    }
}
