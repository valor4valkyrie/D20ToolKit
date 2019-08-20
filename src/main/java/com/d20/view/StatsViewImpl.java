package com.d20.view;

import com.d20.model.Character;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StatsViewImpl implements StatsView {

    @Autowired
    private ViewService viewService;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private StatsService statsService;

    @Autowired
    private ImageService imageService;

    private HBox hBox = new HBox(15);
    private HBox hBox2 = new HBox(15);

    private Stats stats;

    StatsViewImpl(){}

    StatsViewImpl(Stats stats){
        this.stats = stats;
    }

    public void setStats(Stats stats){
        this.stats = stats;
    }

    public Pane getStatsView(boolean showTemp){

        GridPane statsBox = new GridPane();

        statsBox.setHgap(25);
        statsBox.setPadding(new Insets(25));

        ColumnConstraints col = new ColumnConstraints();
        col.setHalignment(HPos.CENTER);

        Label name = new Label("Abilities");
        Label stat = new Label("Ability Score");
        Label modifier = new Label("Ability Modifier");

        name.getStyleClass().add("stats-header");
        stat.getStyleClass().add("stats-header");
        modifier.getStyleClass().add("stats-header");

        statsBox.setHgap(25);
        statsBox.setPadding(new Insets(15));
        statsBox.addColumn(0, name);
        statsBox.addColumn(1, stat);
        statsBox.addColumn(2, modifier);

        if(showTemp){
            statsBox.getColumnConstraints().addAll(col, col, col, col, col);
        } else {
            statsBox.getColumnConstraints().addAll(col, col, col);
        }


        if (stats == null || stats.getStatsList().isEmpty()) {
            stats = statsService.rollAllStats();
        }

        stats.getStatsList().forEach(aStat -> {
            Text statDescription = new Text(aStat.getName());
            Text statValue = new Text(String.valueOf(aStat.getStat()));
            Text statMod = new Text(String.valueOf(aStat.getModifier()));
            Text statTemp = new Text(String.valueOf(aStat.getTempStat()));
            Text statTempMod = new Text(String.valueOf(aStat.getTempMod()));

            if (showTemp == true) {
                statsBox.addColumn(0, statDescription);
                statsBox.addColumn(1, statValue);
                statsBox.addColumn(2, statMod);
                statsBox.addColumn(3, statTemp);
                statsBox.addColumn(4, statTempMod);
            } else {
                statsBox.addColumn(0, statDescription);
                statsBox.addColumn(1, statValue);
                statsBox.addColumn(2, statMod);
            }

        });

        return statsBox;

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

    public FlowPane getNewStatsView(Stats stats, boolean showTemp){
        return getNewStatsView(stats, showTemp, false);
    }

    public FlowPane getNewStatsView(Stats stats, boolean showTemp, boolean showTotal) {

        FlowPane flowPane = new FlowPane();
        VBox statVbox = new VBox();
        GridPane statsDescBox = new GridPane();

        flowPane.getStylesheets().add("../resources/css/stats.css");

        statVbox.getChildren().add(statsDescBox);

        statVbox.getChildren().add(getStatsView(false));

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
                Character character = characterService.createNewCharacter(stats);
                viewService.setCharacterSheet(character);
                viewService.setMainScene(viewService.getCharacterSheet(-1L), true);
            });

            statsTotal.getChildren().addAll(statsTotalText, statsTotalValue, selectStats);

            statVbox.getChildren().addAll(statsTotal);
        }

        statBox.getStyleClass().add("stats-box");
        statsDescBox.getStyleClass().add("stats-box");
        statVbox.getStyleClass().add("statspane");

        flowPane.getChildren().add(statVbox);

        stats.getStatsTotal();

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
}
