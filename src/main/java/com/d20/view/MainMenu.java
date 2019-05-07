package com.d20.view;

import com.d20.services.ImageService;
import com.d20.services.ViewService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMenu implements MainMenuInt {

    @Autowired
    private ViewService viewService;

    @Autowired
    private ImageService imageService;

    public void MainMenu() {}

    public BorderPane getMainMenu(){

        HBox hBox = new HBox();
        hBox.setSpacing(25);
        hBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        hBox.getStyleClass().add("hbox");
        hBox.setMaxSize(viewService.getScreenWidth() - 100, viewService.getScreenHeight() / 2);

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
            viewService.getFutureView().newFutureView();
            viewService.setStyle("future");

        });
        futureButton.setOnKeyPressed(e -> {
            if(e.getCode().toString() == "ENTER"){
                viewService.getFutureView().newFutureView();
                viewService.setStyle("future");
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