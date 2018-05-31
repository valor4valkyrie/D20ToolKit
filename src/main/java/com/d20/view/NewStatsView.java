package com.d20.view;

import com.d20.model.Stat;
import com.d20.model.Stats;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class NewStatsView {

    @Autowired
    MainView view;

    @Autowired
    Stats stats;

    public FlowPane getNewStatsView(boolean showTemp){

        stats.rollAllStats();

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

        Button select = new Button("Select These Stats");
        //select.setOnMouseClicked(e -> view.setMainScene(getNewStatsView(), view.isFullScreen()));
        statsDescBox.getStyleClass().add("stats-header");

        Text statsTotalField = new Text("Ability Totals: " + String.valueOf(stats.getStatsTotal()));

        stats.getStatsMap().entrySet().forEach(oneStat -> {

                Stat aStat = oneStat.getValue();

                GridPane statsBox = new GridPane();

                Text statDescription = new Text(aStat.getName());
                Text statValue = new Text(String.valueOf(aStat.getStat()));
                Text statMod = new Text(String.valueOf(aStat.getModifier()));
                Text statTemp = new Text(String.valueOf(aStat.getTempStat()));
                Text statTempMod = new Text(String.valueOf(aStat.getTempMod()));

                if(showTemp) {
                    statsBox.addColumn(0, statDescription);
                    statsBox.addColumn(1, statValue);
                    statsBox.addColumn(2, statMod);
                    statsBox.addColumn(3, statTemp);
                    statsBox.addColumn(4, statTempMod);
                    statsBox.getColumnConstraints().addAll(col, col, col, col, col);
                }else{
                    statsBox.addColumn(0, statDescription);
                    statsBox.addColumn(1, statValue);
                    statsBox.addColumn(2, statMod);
                    statsBox.getColumnConstraints().addAll(col, col, col);
                }

                statsBox.getStyleClass().add("stats-box");

                firstStat.getChildren().add(statsBox);

        });

        HBox statBox = new HBox();
        statBox.getChildren().addAll(statsTotalField, select);
        statBox.setSpacing(50);
        statBox.setAlignment(Pos.CENTER);
        firstStat.getChildren().add(statBox);

        statBox.getStyleClass().add("stats-box");
        select.getStyleClass().add("button");
        statsDescBox.getStyleClass().add("stats-box");
        firstStat.getStyleClass().add("statspane");

        flowPane.getChildren().add(firstStat);

        stats.getStatsTotal();

        return flowPane;
    }

    public Pane getSingleRolledStats(){

        BorderPane pane = new BorderPane();

        VBox vBox = new VBox(15);

        vBox.getChildren().addAll(getNewStatsView(false));

        StringBuilder statsDesc = new StringBuilder();

        try {
            Path smartPath = Paths.get(getClass().getClassLoader()
                    .getResource("./guide/statsGuide.txt").toURI());
            Stream<String> lines = Files.lines(smartPath);
            lines.forEach(line -> statsDesc.append(line).append("\n"));
            lines.close();
        }catch (Exception e){
            e.getMessage();
        }

        pane.setLeft(vBox);

        TextArea textArea = new TextArea(statsDesc.toString());
        textArea.setPrefSize(800, 800);
        textArea.getStyleClass().add("text-area");
        textArea.setWrapText(true);

        pane.getStylesheets().add("../resources/css/stats.css");
        pane.setCenter(textArea);

        return pane;

    }

    public Pane getAllRolledStats(){
        BorderPane pane = new BorderPane();

        VBox vBox = new VBox(15);

        vBox.getChildren().addAll(getNewStatsView(false), getNewStatsView(false),
                getNewStatsView(false));

        StringBuilder statsDesc = new StringBuilder();
/**
        try {
            Path smartPath = Paths.get(getClass().getClassLoader()
                    .getResource("./guide/guide.txt").toURI());
            Stream<String> lines = Files.lines(smartPath);
            lines.forEach(line -> statsDesc.append(line).append("\n"));
            lines.close();
        }catch (Exception e){
            e.getMessage();
        }
*/
        pane.setLeft(vBox);

        TextArea textArea = new TextArea(statsDesc.toString());
        textArea.getStyleClass().add("text-area");
        textArea.setWrapText(true);

        pane.getStylesheets().add("../resources/css/stats.css");
        pane.setCenter(textArea);

        return pane;
    }

}
