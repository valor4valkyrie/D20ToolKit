package com.d20.view.future;

import com.d20.main.Utilities;
import com.d20.view.MainMenu;
import com.d20.view.MainView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class NewFutureView {

    @Autowired
    MainView view;

    @Autowired
    MainMenu mainMenu;

    @Autowired
    Utilities utilities;

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

        Text description = new Text("You must select a character class first. This will determine the starting skills and " +
                "attributes. Click on each to learn more.");

        Text heroDescription = new Text();

        Label classDescription= new Label("Description");

        Map<String, StringBuilder> heroMap = new HashMap<>();

        try {
            Path smartPath = Paths.get(getClass().getClassLoader()
                    .getResource("./classes/future/smarthero.txt").toURI());
            Stream<String> lines = Files.lines(smartPath);
            StringBuilder smartData = new StringBuilder();
            lines.forEach(line -> smartData.append(line).append("\n"));
            lines.close();
            heroMap.put("smartHero", smartData);
        }catch (Exception e){
            e.getMessage();
        }

        try {
            Path strongPath = Paths.get(getClass().getClassLoader()
                    .getResource("./classes/future/stronghero.txt").toURI());
            Stream<String> lines = Files.lines(strongPath);
            StringBuilder strongData = new StringBuilder();
            lines.forEach(line -> strongData.append(line).append("\n"));
            lines.close();
            heroMap.put("strongHero", strongData);
        }catch (Exception e){
            e.getMessage();
        }

        try {
            Path fastPath = Paths.get(getClass().getClassLoader()
                    .getResource("./classes/future/fasthero.txt").toURI());
            Stream<String> lines = Files.lines(fastPath);
            StringBuilder fastData = new StringBuilder();
            lines.forEach(line -> fastData.append(line).append("\n"));
            lines.close();
            heroMap.put("fastHero", fastData);
        }catch (Exception e){
            e.getMessage();
        }

        try {
            Path toughPath = Paths.get(getClass().getClassLoader()
                    .getResource("./classes/future/toughhero.txt").toURI());
            Stream<String> lines = Files.lines(toughPath);
            StringBuilder toughData = new StringBuilder();
            lines.forEach(line -> toughData.append(line).append("\n"));
            lines.close();
            heroMap.put("toughHero", toughData);
        }catch (Exception e){
            e.getMessage();
        }

        try {
            Path charismaPath = Paths.get(getClass().getClassLoader()
                    .getResource("./classes/future/charismatichero.txt").toURI());
            Stream<String> lines = Files.lines(charismaPath);
            StringBuilder charismaData = new StringBuilder();
            lines.forEach(line -> charismaData.append(line).append("\n"));
            lines.close();
            heroMap.put("charismaHero", charismaData);
        }catch (Exception e){
            e.getMessage();
        }

        try {
            Path dedicatedPath = Paths.get(getClass().getClassLoader()
                    .getResource("./classes/future/dedicatedhero.txt").toURI());
            Stream<String> lines = Files.lines(dedicatedPath);
            StringBuilder dedicatedData = new StringBuilder();
            lines.forEach(line -> dedicatedData.append(line).append("\n"));
            lines.close();
            heroMap.put("dedicatedHero", dedicatedData);
        }catch (Exception e){
            e.getMessage();
        }

        //heroDescription.setText(data.toString());

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
        textArea.setText(heroDescription.getText());
        textArea.setEditable(false);
        textArea.setWrapText(true);

        //Button Actions
        back.setOnMouseClicked(e -> view.setMainScene(getNewCharacter(), view.isFullScreen()));
        //--Smart Hero
        smartHero.setOnMouseClicked(e -> view.setMainScene(getSmartHero(), view.isFullScreen()));
        smartHero.setOnMouseEntered(e -> {
            heroDescription.setText(heroMap.get("smartHero").toString());
            textArea.clear();
            textArea.setText(heroDescription.getText());
        });

        strongHero.setOnMouseEntered(e -> {
            heroDescription.setText(heroMap.get("strongHero").toString());
            textArea.clear();
            textArea.setText(heroDescription.getText());
        });

        fastHero.setOnMouseEntered(e -> {
            heroDescription.setText(heroMap.get("fastHero").toString());
            textArea.clear();
            textArea.setText(heroDescription.getText());
        });

        toughHero.setOnMouseEntered(e -> {
            heroDescription.setText(heroMap.get("toughHero").toString());
            textArea.clear();
            textArea.setText(heroDescription.getText());
        });

        charismaticHero.setOnMouseEntered(e -> {
            heroDescription.setText(heroMap.get("charismaHero").toString());
            textArea.clear();
            textArea.setText(heroDescription.getText());
        });

        dedicatedHero.setOnMouseEntered(e -> {
            heroDescription.setText(heroMap.get("dedicatedHero").toString());
            textArea.clear();
            textArea.setText(heroDescription.getText());
        });
        descriptionBox.getChildren().addAll(classDescription, textArea);
        flowPane.getChildren().addAll(back, fastHero, smartHero, strongHero, toughHero, charismaticHero, dedicatedHero);

        borderPane.setTop(description);
        borderPane.setCenter(flowPane);
        borderPane.setBottom(descriptionBox);

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

    public BorderPane statsNewCharacter(){
        BorderPane borderPane = new BorderPane();

        return borderPane;
    }
}
