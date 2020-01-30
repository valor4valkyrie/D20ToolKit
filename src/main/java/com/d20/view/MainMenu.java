package com.d20.view;

import com.d20.services.EventsService;
import com.d20.services.ImageService;
import com.d20.services.ViewService;
import com.d20.services.FutureViewService;
import com.d20.view.future.NewFutureView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.text.View;

@Component
public class MainMenu {

    private ViewService viewService;
    private ImageService imageService;
    private FutureViewService futureViewService;
    private EventsService eventsService;

    @Autowired
    public MainMenu(ViewService viewService, ImageService imageService, FutureViewService futureViewService, EventsService eventsService) {
        this.viewService = viewService;
        this.imageService = imageService;
        this.futureViewService = futureViewService;
        this.eventsService = eventsService;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @Autowired
    public void setFutureViewService(FutureViewService futureViewService) {
        this.futureViewService = futureViewService;
    }

    @Autowired
    public void setEventsService(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    public BorderPane getMainMenu(){

        HBox hBox = new HBox();
        hBox.setSpacing(25);
        hBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        hBox.getStyleClass().add("hbox");
        hBox.setMaxSize(viewService.getScreenWidth() / 2, viewService.getScreenHeight() - 500);

        //Button Images
        ImageView mutantsImage = new ImageView(new Image("./images/MutantsMasterminds.jpg"));
        ImageView modernImage = new ImageView(new Image("./images/D20_Modern_Book_Cover.jpg"));
        ImageView spycraftImage = new ImageView(new Image("./images/Spycraft.jpg"));
        ImageView ddImage = new ImageView(new Image("./images/DungeonsDragonsPlayers.jpg"));
        ImageView pathfinderImage = new ImageView(new Image("./images/Pathfinder_Book_Cover.jpg"));
        ImageView futureImage = new ImageView(new Image("./images/D20_Future_Book_Cover.jpg"));

        //Buttons for games
        Button futureButton = new Button("",futureImage);
        Button mutantsButton = new Button("", mutantsImage);
        Button modernButton = new Button("", modernImage);
        Button spycraftButton = new Button("", spycraftImage);
        Button ddButton = new Button("", ddImage);
        Button pathfinderButton = new Button("", pathfinderImage);

        futureButton.getStyleClass().add("button");
        mutantsButton.getStyleClass().add("button");
        modernButton.getStyleClass().add("button");
        spycraftButton.getStyleClass().add("button");
        ddButton.getStyleClass().add("button");
        pathfinderButton.getStyleClass().add("button");

        //Actions for the buttons
        futureButton.setOnMouseClicked(e -> {
            eventsService.setSceneEvent(futureViewService.getFutureCharacterView());

        });
        futureButton.setOnKeyPressed(e -> {
            if(e.getCode().toString() == "ENTER"){
                futureViewService.getFutureCharacterView();
            }
        });

        /** Tooltips and Popup windows
        Tooltip tip = new Tooltip("Future");
        futureButton.tooltipProperty().setValue(tip);
        futureButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("I have a great message for you!");

            alert.showAndWait();
        });
        **/

        BorderPane borderPane = new BorderPane();
        hBox.getChildren().addAll(pathfinderButton, futureButton, mutantsButton, modernButton, spycraftButton, ddButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        borderPane.setCenter(hBox);
        borderPane.setBackground(imageService.createBackgroundImage(imageService.getFutureImage("GeneticCatCharacter"), BackgroundPosition.CENTER));
        borderPane.getStylesheets().add("../resources/css/general.css");
        borderPane.getStyleClass().add("pane");
        /*TabPane tabPane = new TabPane();
        Tab statsPane = new Tab();
        statsPane.setText("Stats");
        tabPane.getTabs().add(statsPane);*/

        return borderPane;
    }
}