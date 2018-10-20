package com.d20.view;

import com.d20.services.ViewService;
import com.d20.view.future.FutureView;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMenu implements MainMenuInt {

    @Autowired
    private ViewService viewService;

    public void MainMenu() {}

    public FlowPane getMainMenu(){

        HBox hBox = new HBox();
        hBox.setSpacing(50);

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

        //Actions for the buttons
        futureButton.setOnMouseClicked(e -> viewService.getFutureView().newFutureView());
        futureButton.setOnKeyPressed(e -> {
            if(e.getCode().toString() == "ENTER"){
                viewService.getFutureView().newFutureView();
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

        //FlowPane for the game buttons
        FlowPane flowPane = new FlowPane();
        hBox.getChildren().addAll(pathfinderButton, futureButton, mutantsButton, modernButton, spycraftButton, ddButton);
        flowPane.getChildren().add(hBox);
        /*TabPane tabPane = new TabPane();
        Tab statsPane = new Tab();
        statsPane.setText("Stats");
        tabPane.getTabs().add(statsPane);*/

        return flowPane;
    }
}