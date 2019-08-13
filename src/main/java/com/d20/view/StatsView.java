package com.d20.view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public interface StatsView {

    GridPane getStatsView(boolean showTemp);

    Pane getStatsGuideText(String stat);
}