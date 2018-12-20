package com.d20.view;

import com.d20.model.Stat;
import com.d20.model.Stats;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class NewStatsView {

    @Autowired
    private ViewService viewService;

    @Autowired
    private StatsService statsService;

    @Autowired
    private ImageService imageService;


    private String style = null;

    public NewStatsView() {
    }

    public NewStatsView(String style) {
        this.style = style;
    }

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

    public FlowPane getNewStatsView(boolean showTemp) {

        statsService.rollAllStats();

        FlowPane flowPane = new FlowPane();
        VBox firstStat = new VBox();
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

        firstStat.getChildren().add(statsDescBox);

        statsDescBox.getStyleClass().add("stats-header");

        Text statsTotalField = new Text("Ability Totals: " + String.valueOf(statsService.getStatsTotal()));

        Stats stats = statsService.getStats();
        firstStat.getChildren().add(statsView(stats.getStrength(), showTemp));
        firstStat.getChildren().add(statsView(stats.getDexterity(), showTemp));
        firstStat.getChildren().add(statsView(stats.getConstitution(), showTemp));
        firstStat.getChildren().add(statsView(stats.getIntelligence(), showTemp));
        firstStat.getChildren().add(statsView(stats.getWisdom(), showTemp));
        firstStat.getChildren().add(statsView(stats.getCharisma(), showTemp));

        HBox statBox = new HBox();
        statBox.setSpacing(50);
        statBox.setAlignment(Pos.CENTER);
        firstStat.getChildren().add(statBox);

        statBox.getStyleClass().add("stats-box");
        statsDescBox.getStyleClass().add("stats-box");
        firstStat.getStyleClass().add("statspane");

        flowPane.getChildren().add(firstStat);

        statsService.getStatsTotal();

        return flowPane;
    }

    public Pane getSingleRolledStats() {

        BorderPane pane = new BorderPane();

        VBox vBox = new VBox(15);

        FutureView f = viewService.getFutureView();

        Button backButton = new Button("Back");
        backButton.setOnMouseClicked(e -> viewService.getMainMenu());
        vBox.getChildren().addAll(getNewStatsView(false), backButton);

        pane.setLeft(vBox);

        backButton.getStyleClass().add("back-button");


        switch (style) {
            case "future":
                pane.getStylesheets().add("../resources/css/future.css");
                break;
            default:
                pane.getStylesheets().add("../resources/css/stats.css");
                break;
        }

        return pane;

    }

    public Pane getAllRolledStats() {
        BorderPane pane = new BorderPane();

        VBox vBox = new VBox(15);

        vBox.getChildren().addAll(getNewStatsView(false), getNewStatsView(false),
                getNewStatsView(false));

        StringBuilder statsDesc = new StringBuilder();

        pane.setLeft(vBox);

        TextArea textArea = new TextArea(statsDesc.toString());
        textArea.getStyleClass().add("text-area");
        textArea.setWrapText(true);

        switch (style) {
            case "future":
                pane.getStylesheets().add("../resources/css/future.css");
                break;
            default:
                pane.getStylesheets().add("../resources/css/stats.css");
                break;
        }
        pane.setCenter(textArea);

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
        textArea.getStyleClass().add("text-area");
        textArea.setWrapText(true);
        textArea.setMinWidth(viewService.getScreenWidth() - 200);

        pane.getChildren().add(textArea);

        return pane;
    }

    public Pane getGuidedStats(){

        BorderPane pane = new BorderPane();
        HBox topBox = new HBox(getGuidedStatsText());
        HBox centerBox = new HBox(getSingleRolledStats());
        HBox bottomBox = new HBox(getStatsGuideText("strength"));
        HBox leftBox = new HBox();
        HBox rightBox = new HBox();

        pane.setBackground(imageService.createBackgroundImage(imageService.getHelixWarrior(), BackgroundPosition.CENTER));

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

        switch (style) {
            case "future":
                pane.getStylesheets().add("../resources/css/future.css");
                break;
            default:
                pane.getStylesheets().add("../resources/css/stats.css");
                break;
        }

        return pane;

    }

}
