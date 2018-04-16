package com.d20.view.future;

import com.d20.view.MainMenu;
import com.d20.view.MainView;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class NewFutureView {

    @Autowired
    MainView view;

    @Autowired
    MainMenu mainMenu;

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

        Text description = new Text("You must select a character class first. This will determine the starting skills and " +
                "attributes. Click on each to learn more.");

        Button fastHero = new Button("Fast Hero");
        Button strongHero = new Button("Strong Hero");
        Button toughHero = new Button("Tough Hero");
        Button smartHero = new Button("Smart Hero");
        Button dedicatedHero = new Button("Dedicated Hero");
        Button charismaticHero = new Button("Charismatic Hero");
        Button back = new Button("Back");

        back.setOnMouseClicked(e -> view.setMainScene(getNewCharacter(), view.isFullScreen()));

        flowPane.getChildren().addAll(back, fastHero, smartHero, strongHero, toughHero, charismaticHero, dedicatedHero);

        borderPane.setTop(description);
        borderPane.setCenter(flowPane);

        return borderPane;
    }

    public BorderPane getSmartHero(){
        BorderPane borderPane = new BorderPane();

        FlowPane flowPane = new FlowPane();

        Text description = new Text("Smart Heroes are smart.");

        return borderPane;
    }

    public BorderPane statsNewCharacter(){
        BorderPane borderPane = new BorderPane();

        return borderPane;
    }
}
