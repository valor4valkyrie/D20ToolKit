package com.d20.view;

import com.d20.services.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class NewStatsView extends StatsView{

    private ViewService viewService;

    @Autowired
    public NewStatsView(EventsService eventsService, ViewService viewService) {
        super(eventsService);
        this.viewService = viewService;
    }

    public Group starShape(){
        Group group = new Group();

        // Create a Triangle
        javafx.scene.shape.Path triangle = new javafx.scene.shape.Path(new MoveTo(0, 0),
                new LineTo(0, 50),
                new LineTo(50, 50),
                new ClosePath());

        // Create a Star
        javafx.scene.shape.Path star = new javafx.scene.shape.Path(new MoveTo(30, 0),
                new LineTo(0, 30),
                new LineTo(60, 30),
                new ClosePath(),
                new MoveTo(0, 10),
                new LineTo(60, 10),
                new LineTo(30, 40),
                new ClosePath());

        group.getChildren().add(star);


        return group;
    }


    public void selectAnimations(FlowPane flowPane){
        BorderPane pane = new BorderPane();


        /*javafx.scene.shape.Path path = new javafx.scene.shape.Path();
        path.getElements().add(new MoveTo(viewService.getScreenWidth() / 2,viewService.getScreenHeight() / 2));
        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
        path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(hbox);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);

        pathTransition.play();*/

        viewService.setMainScene(pane);
    }

    public void removeNodes(){
        Scene scene = viewService.getCurrentScene();
        BorderPane borderPane = (BorderPane) scene.getRoot().getChildrenUnmodifiable().get(0);
        ScrollPane scrollPane = (ScrollPane) borderPane.getChildren().get(1);
        StackPane stack = (StackPane) scrollPane.getChildrenUnmodifiable().get(3);
        stack.getChildren().forEach(each -> each.setVisible(false));

        viewService.setMainScene(borderPane);
    }



    public Pane getGuidedStatsPane(){

        BorderPane pane = new BorderPane();
        Button statsStartButton = new Button("Start Stats");
        statsStartButton.setOnMouseClicked(e -> {getGuidedStrength();});
        HBox topBox = new HBox(getGuidedStatsText());
        HBox centerBox = new HBox(getSingleRolledStats());
        HBox bottomBox = new HBox(statsStartButton);
        HBox leftBox = new HBox();
        HBox rightBox = new HBox();

        pane.setBackground(new ImageService().createBackgroundImage(new ImageService().getFutureImage("HelixWarrior"), BackgroundPosition.CENTER));

        topBox.setAlignment(Pos.CENTER);
        centerBox.setAlignment(Pos.CENTER);
        bottomBox.setAlignment(Pos.CENTER);
        leftBox.setAlignment(Pos.CENTER);
        rightBox.setAlignment(Pos.CENTER);

        pane.setPadding(new Insets(10,10,10,10));
        topBox.setPadding(new Insets(10,10,10,10));
        centerBox.setPadding(new Insets(10,10,10,10));
        bottomBox.setPadding(new Insets(10,10,10,10));

        pane.setTop(topBox);
        pane.setCenter(centerBox);
        pane.setBottom(bottomBox);
        pane.setLeft(leftBox);
        pane.setRight(rightBox);

        viewService.setStyle("future");

        return pane;

    }

    public Pane getGuidedStatsText(){
        FlowPane pane = new FlowPane();

        StringBuilder statsDesc = new StringBuilder();

        try {
            Path smartPath = Paths.get(getClass().getClassLoader()
                    .getResource("./guide/statsGuide.txt").toURI());
            Stream<String> lines = Files.lines(smartPath);
            lines.forEach(line -> statsDesc.append(line).append("\n"));
            lines.close();
        } catch (Exception e) {
            e.getMessage();
        }

        TextArea textArea = new TextArea(statsDesc.toString());
        textArea.setPrefWidth(viewService.getScreenWidth() - 100);
        textArea.getStyleClass().add("text-area");
        textArea.setWrapText(true);
        textArea.setMinHeight(viewService.getScreenHeight() / 4);
        textArea.setEditable(false);

        pane.getChildren().add(textArea);

        return pane;
    }

    public void getGuidedStrength(){
        BorderPane pane = new BorderPane();
        HBox hBox = new HBox();
        HBox bottomBox = new HBox();

        hBox.setSpacing(25);
        hBox.setAlignment(Pos.CENTER);
        bottomBox.setSpacing(25);
        bottomBox.setAlignment(Pos.CENTER);

        Button nextStat = new Button("Next to Dexterity");
        Button backStat = new Button("Back to Stats Overview");
        nextStat.getStyleClass().add("navigation-button");
        backStat.getStyleClass().add("navigation-button");
        nextStat.setOnMouseClicked(e -> getGuidedDexterity());
        backStat.setOnMouseClicked(e -> viewService.setMainScene(getGuidedStatsPane()));

        bottomBox.getChildren().addAll(backStat, nextStat);
        hBox.getChildren().add(getStatsGuideText("strength"));
        hBox.setAlignment(Pos.CENTER_LEFT);
        pane.setRight(hBox);
        pane.setBottom(bottomBox);
        ImageView image = new ImageService().getFutureImage("HelixWarrior");
        image.setFitHeight(viewService.getScreenHeight() / 1.15);
        HBox imageBox =  new HBox();
        imageBox.setAlignment(Pos.CENTER_RIGHT);
        imageBox.getChildren().add(image);
        pane.setLeft(imageBox);

        viewService.setMainScene(pane);
    }

    public void getGuidedDexterity(){
        BorderPane pane = new BorderPane();
        HBox hBox = new HBox();
        HBox bottomBox = new HBox();

        hBox.setSpacing(25);
        hBox.setAlignment(Pos.CENTER);
        bottomBox.setSpacing(25);
        bottomBox.setAlignment(Pos.CENTER);

        Button nextStat = new Button("Next to Constitution");
        Button backStat = new Button("Back to Strength");
        nextStat.setOnMouseClicked(e -> getGuidedConstitution());
        backStat.setOnMouseClicked(e -> getGuidedStrength());

        bottomBox.getChildren().addAll(backStat, nextStat);
        hBox.getChildren().add(getStatsGuideText("dexterity"));
        pane.setRight(hBox);
        pane.setBottom(bottomBox);
        ImageView image = new ImageService().getFutureImage("Dogfighter");
        image.setFitHeight(viewService.getScreenHeight() / 1.15);
        pane.setLeft(image);

        viewService.setMainScene(pane);
    }

    public void getGuidedConstitution(){
        BorderPane pane = new BorderPane();
        HBox hBox = new HBox();
        HBox bottomBox = new HBox();

        hBox.setSpacing(25);
        hBox.setAlignment(Pos.CENTER);
        bottomBox.setSpacing(25);
        bottomBox.setAlignment(Pos.CENTER);

        Button nextStat = new Button("Next to Intelligence");
        Button backStat = new Button("Back to Dexterity");
        nextStat.setOnMouseClicked(e -> getGuidedIntelligence());
        backStat.setOnMouseClicked(e -> getGuidedDexterity());

        bottomBox.getChildren().addAll(backStat, nextStat);
        hBox.getChildren().add(getStatsGuideText("constitution"));
        pane.setRight(hBox);
        pane.setBottom(bottomBox);
        ImageView image = new ImageService().getFutureImage("Dreadnought");
        image.setFitHeight(viewService.getScreenHeight() / 1.15);
        pane.setLeft(image);

        viewService.setMainScene(pane);
    }

    public void getGuidedIntelligence(){
        BorderPane pane = new BorderPane();
        HBox hBox = new HBox();
        HBox bottomBox = new HBox();

        hBox.setSpacing(25);
        hBox.setAlignment(Pos.CENTER);
        bottomBox.setSpacing(25);
        bottomBox.setAlignment(Pos.CENTER);

        Button nextStat = new Button("Next to Wisdom");
        Button backStat = new Button("Back to Constitution");
        nextStat.setOnMouseClicked(e -> getGuidedWisdom());
        backStat.setOnMouseClicked(e -> getGuidedConstitution());

        bottomBox.getChildren().addAll(backStat, nextStat);
        hBox.getChildren().add(getStatsGuideText("intelligence"));
        pane.setRight(hBox);
        pane.setBottom(bottomBox);
        ImageView image = new ImageService().getFutureImage("Engineer");
        image.setFitHeight(viewService.getScreenHeight() / 1.15);
        pane.setLeft(image);

        viewService.setMainScene(pane);
    }

    public void getGuidedWisdom(){
        BorderPane pane = new BorderPane();
        HBox hBox = new HBox();
        HBox bottomBox = new HBox();

        hBox.setSpacing(25);
        hBox.setAlignment(Pos.CENTER);
        bottomBox.setSpacing(25);
        bottomBox.setAlignment(Pos.CENTER);

        Button nextStat = new Button("Next to Charisma");
        Button backStat = new Button("Back to Intelligence");
        nextStat.setOnMouseClicked(e -> getGuidedCharisma());
        backStat.setOnMouseClicked(e -> getGuidedIntelligence());

        bottomBox.getChildren().addAll(backStat, nextStat);
        hBox.getChildren().add(getStatsGuideText("wisdom"));
        pane.setRight(hBox);
        pane.setBottom(bottomBox);
        ImageView image = new ImageService().getFutureImage("Explorer");
        image.setFitHeight(viewService.getScreenHeight() / 1.15);
        pane.setLeft(image);

        viewService.setMainScene(pane);
    }

    public void getGuidedCharisma(){
        BorderPane pane = new BorderPane();
        HBox hBox = new HBox();
        HBox bottomBox = new HBox();

        hBox.setSpacing(25);
        hBox.setAlignment(Pos.CENTER);
        bottomBox.setSpacing(25);
        bottomBox.setAlignment(Pos.CENTER);

        Button nextStat = new Button("Roll My Stats!");
        Button backStat = new Button("Back to Wisdom");
        nextStat.setOnMouseClicked(e -> viewService.setMainScene(getAllRolledStats()));
        backStat.setOnMouseClicked(e -> getGuidedWisdom());

        bottomBox.getChildren().addAll(backStat, nextStat);
        hBox.getChildren().add(getStatsGuideText("charisma"));
        pane.setRight(hBox);
        pane.setBottom(bottomBox);
        ImageView image = new ImageService().getFutureImage("Ambassador");
        image.setFitHeight(viewService.getScreenHeight() / 1.15);
        pane.setLeft(image);

        viewService.setMainScene(pane);
    }

}
