package com.d20.view;

import com.d20.model.Stat;
import com.d20.model.Stats;
import com.d20.services.CharacterService;
import com.d20.services.ImageService;
import com.d20.services.StatsService;
import com.d20.services.ViewService;
import com.d20.view.future.FutureView;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class NewStatsView {

    @Autowired
    private ViewService viewService;

    @Autowired
    private StatsService statsService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CharacterService characterService;

    public NewStatsView() {
    }

    public NewStatsView(String style) {}

    public GridPane statsView(Stat aStat, boolean showTemp){

        GridPane statsBox = new GridPane();

        ColumnConstraints col = new ColumnConstraints();
        col.setPrefWidth(225);
        col.setHalignment(HPos.CENTER);

        Text statDescription = new Text(aStat.getName());
        Text statValue = new Text(String.valueOf(aStat.getStat()));
        Text statMod = new Text(String.valueOf(aStat.getModifier()));
        Text statTemp = new Text(String.valueOf(aStat.getTempStat()));
        Text statTempMod = new Text(String.valueOf(aStat.getTempMod()));

        TextArea statExplaination = new TextArea();

        if (showTemp) {
            statsBox.addColumn(0, statDescription);
            statsBox.addColumn(1, statValue);
            statsBox.addColumn(2, statMod);
            statsBox.addColumn(3, statTemp);
            statsBox.addColumn(4, statTempMod);
            statsBox.getColumnConstraints().addAll(col, col, col, col, col);
        } else {
            statsBox.addColumn(0, statDescription);
            statsBox.addColumn(1, statValue);
            statsBox.addColumn(2, statMod);
            statsBox.getColumnConstraints().addAll(col, col, col);
        }

        try {
            Path smartPath = Paths.get(getClass().getClassLoader()
                    .getResource("./guide/strengthStatGuide.txt").toURI());
            statsBox.getStyleClass().add("stats-box");
        } catch (URISyntaxException e){System.out.print(e);}

        return statsBox;

    }

    public FlowPane getNewStatsView(Stats stats, boolean showTemp){
        return getNewStatsView(stats, showTemp, false);
    }

    public FlowPane getNewStatsView(Stats stats, boolean showTemp, boolean showTotal) {

        FlowPane flowPane = new FlowPane();
        VBox statVbox = new VBox();
        GridPane statsDescBox = new GridPane();

        flowPane.getStylesheets().add("../resources/css/stats.css");

        ColumnConstraints col = new ColumnConstraints();
        col.setPrefWidth(225);
        col.setHalignment(HPos.CENTER);

        Text name = new Text("Abilities");
        Text stat = new Text("Ability Score");
        Text modifier = new Text("Ability Modifier");

        statsDescBox.addColumn(0, name);
        statsDescBox.addColumn(1, stat);
        statsDescBox.addColumn(2, modifier);
        statsDescBox.getColumnConstraints().addAll(col, col, col);

        statVbox.getChildren().add(statsDescBox);

        statsDescBox.getStyleClass().add("stats-header");

        statVbox.getChildren().add(statsView(stats.getStrength(), showTemp));
        statVbox.getChildren().add(statsView(stats.getDexterity(), showTemp));
        statVbox.getChildren().add(statsView(stats.getConstitution(), showTemp));
        statVbox.getChildren().add(statsView(stats.getIntelligence(), showTemp));
        statVbox.getChildren().add(statsView(stats.getWisdom(), showTemp));
        statVbox.getChildren().add(statsView(stats.getCharisma(), showTemp));

        HBox statBox = new HBox();
        statBox.setSpacing(50);
        statBox.setAlignment(Pos.CENTER);
        statVbox.getChildren().add(statBox);

        if(showTotal){
            HBox statsTotal = new HBox(15);
            statsTotal.setAlignment(Pos.CENTER);
            Text statsTotalText = new Text("Ability Totals: ");
            Text statsTotalValue = new Text(String.valueOf(stats.getStatsTotal()));

            Button selectStats = new Button("Select These Stats");
            selectStats.setTextAlignment(TextAlignment.CENTER);
            selectStats.setOnMouseClicked(event -> {
                selectStats.setDisable(true);
                statsService.sendStats(stats);
            });

            statsTotal.getChildren().addAll(statsTotalText, statsTotalValue, selectStats);

            statVbox.getChildren().addAll(statsTotal);
        }

        statBox.getStyleClass().add("stats-box");
        statsDescBox.getStyleClass().add("stats-box");
        statVbox.getStyleClass().add("statspane");

        flowPane.getChildren().add(statVbox);

        statsService.getStatsTotal();

        return flowPane;
    }

    public Pane getSingleRolledStats() {

        BorderPane pane = new BorderPane();

        VBox vBox = new VBox(15);

        FutureView f = viewService.getFutureView();

        Button backButton = new Button("Back");
        backButton.setOnMouseClicked(e -> viewService.getMainMenu());

        vBox.getChildren().addAll(getNewStatsView(statsService.rollAllStats(), false), backButton);

        pane.setLeft(vBox);

        backButton.getStyleClass().add("back-button");

        return pane;

    }

    public Pane getAllRolledStats() {
        BorderPane pane = new BorderPane();

        HBox hBox = new HBox(15);
        HBox hBox2 = new HBox(15);
        hBox.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(15,15,15,15));
        hBox2.setPadding(new Insets(15,15,15,15));

        hBox.getChildren().addAll(getNewStatsView(statsService.rollAllStats(), false, true),
                getNewStatsView(statsService.rollAllStats(), false, true));
        hBox2.getChildren().add(getNewStatsView(statsService.rollAllStats(), false, true));

        StringBuilder statsDesc = new StringBuilder();

        try {
            Path smartPath = Paths.get(getClass().getClassLoader()
                    .getResource("./guide/rolledStatsGuide.txt").toURI());
            Stream<String> lines = Files.lines(smartPath);
            lines.forEach(line -> statsDesc.append(line));
            lines.close();
        } catch (Exception e) {
            e.getMessage();
        }

        pane.setTop(hBox);
        pane.setCenter(hBox2);
        pane.setBackground(imageService.createBackgroundImage(imageService.getFutureImage("Explorer"), BackgroundPosition.CENTER));


        TextArea textArea = new TextArea(statsDesc.toString());
        textArea.getStyleClass().add("text-area");
        textArea.setWrapText(true);

        pane.setBottom(textArea);

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

    public Pane getStatsGuideText(String stat){
        FlowPane pane = new FlowPane();

        StringBuilder statsDesc = new StringBuilder();

        try {
            Path smartPath = Paths.get(getClass().getClassLoader()
                    .getResource("./guide/" + stat + "StatGuide.txt").toURI());
            Stream<String> lines = Files.lines(smartPath);
            lines.forEach(line -> statsDesc.append(line).append("\n"));
            lines.close();
        } catch (Exception e) {
            e.getMessage();
        }

        TextArea textArea = new TextArea(statsDesc.toString());
        textArea.setEditable(false );
        textArea.getStyleClass().add("text-area");
        textArea.setWrapText(true);
        textArea.setMinHeight(viewService.getScreenHeight() / 4);
        textArea.setMinWidth(viewService.getScreenWidth() - 200);

        pane.getChildren().add(textArea);
        pane.getStyleClass().add("pane");

        return pane;
    }

    public Pane getGuidedStatsPane(){

        BorderPane pane = new BorderPane();
        Button statsStartButton = new Button("Start Stats");
        statsStartButton.getStyleClass().add("");
        statsStartButton.setOnMouseClicked(e -> {getGuidedStrength();});
        HBox topBox = new HBox(getGuidedStatsText());
        HBox centerBox = new HBox(getSingleRolledStats());
        HBox bottomBox = new HBox(statsStartButton);
        HBox leftBox = new HBox();
        HBox rightBox = new HBox();

        pane.setBackground(imageService.createBackgroundImage(imageService.getFutureImage("HelixWarrior"), BackgroundPosition.CENTER));

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
        backStat.setOnMouseClicked(e -> viewService.setMainScene(getGuidedStatsPane(), true));

        bottomBox.getChildren().addAll(backStat, nextStat);
        hBox.getChildren().add(getStatsGuideText("strength"));
        pane.setCenter(hBox);
        pane.setBottom(bottomBox);

        pane.setBackground(imageService.createBackgroundImage(imageService.getFutureImage("HelixWarrior"), BackgroundPosition.CENTER));

        viewService.setMainScene(pane, true);
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
        pane.setCenter(hBox);
        pane.setBottom(bottomBox);

        pane.setBackground(imageService.createBackgroundImage(imageService.getFutureImage("Dogfighter"), BackgroundPosition.CENTER));

        viewService.setMainScene(pane, true);
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
        pane.setCenter(hBox);
        pane.setBottom(bottomBox);

        pane.setBackground(imageService.createBackgroundImage(imageService.getFutureImage("Dreadnought"), BackgroundPosition.CENTER));

        viewService.setMainScene(pane, true);
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
        pane.setCenter(hBox);
        pane.setBottom(bottomBox);

        pane.setBackground(imageService.createBackgroundImage(imageService.getFutureImage("Engineer"), BackgroundPosition.CENTER));

        viewService.setMainScene(pane, true);
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
        pane.setCenter(hBox);
        pane.setBottom(bottomBox);

        pane.setBackground(imageService.createBackgroundImage(imageService.getFutureImage("Explorer"), BackgroundPosition.CENTER));

        viewService.setMainScene(pane, true);
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
        nextStat.setOnMouseClicked(e -> viewService.setMainScene(getAllRolledStats(), true));
        backStat.setOnMouseClicked(e -> getGuidedWisdom());

        bottomBox.getChildren().addAll(backStat, nextStat);
        hBox.getChildren().add(getStatsGuideText("charisma"));
        pane.setCenter(hBox);
        pane.setBottom(bottomBox);

        pane.setBackground(imageService.createBackgroundImage(imageService.getFutureImage("Ambassador"), BackgroundPosition.CENTER));

        viewService.setMainScene(pane, true);
    }

}
