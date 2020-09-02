package com.d20.view;

import com.d20.model.PlayerCharacter;
import com.d20.model.Stats;
import com.d20.services.EventsService;
import com.d20.services.StatsService;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CharacterSheetView {

    private EventsService eventsService;

    private TabPane tabPane = new TabPane();
    private Tab statsTab = new Tab("Stats");
    private Tab skillsTab = new Tab("Skills");
    private Tab featsTab = new Tab("Feats");
    private Tab weaponsTab = new Tab("Weapons");
    private Tab armorTab = new Tab("Armor");
    private Tab equipmentTab = new Tab("Equipment");
    private Tab notesTab = new Tab("Notes");

    private Pane SkillsPane;
    private Pane FeatsPane;
    private Pane WeaponsPane;
    private Pane GeneralPane;

    public CharacterSheetView() {
    }

    public void setSkills(Pane skillsPane) {
//        characterSheet.setRight(skillsPane);
    }

    public void setFeats(Pane featsPane) {
        //characterSheet.setBottom(featsPane);
    }

    public Pane getCharacterSheetStatsView(Stats stats) {
        VBox vBox = new VBox();
        vBox.setMaxSize(700, 250);
        vBox.getChildren().add(new StatsView(eventsService).getStatsView(stats, false));
        vBox.getStyleClass().add("vBox");
        return vBox;
    }

    public void setGeneral(Pane generalPane) {
        //characterSheet.setCenter(generalPane);
    }

    public FlowPane getTabFlowPane(){
        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(15, 15, 15, 15));
        return flowPane;
    }

    public TabPane getCharacterSheetView(PlayerCharacter playerCharacter){

        //Stats tab
        FlowPane statsPane = getTabFlowPane();
        statsPane.getChildren().add(getCharacterSheetStatsView(playerCharacter.getStats()));
        statsTab.setContent(statsPane);
        statsTab.closableProperty().setValue(false);

        //Skills tab
        FlowPane skillsPane = getTabFlowPane();
        skillsTab.setContent(skillsPane);
        skillsTab.closableProperty().setValue(false);

        //Feats tab
        FlowPane featsPane = getTabFlowPane();
        featsTab.setContent(featsPane);
        featsTab.closableProperty().setValue(false);

        //Weapons tab
        FlowPane weaponsPane = getTabFlowPane();
        weaponsTab.setContent(weaponsPane);
        weaponsTab.closableProperty().setValue(false);

        //Armor tab
        FlowPane armorPane = getTabFlowPane();
        armorTab.setContent(armorPane);
        armorTab.closableProperty().setValue(false);

        //Equipment tab
        FlowPane equipmentPane = getTabFlowPane();
        equipmentTab.setContent(equipmentPane);
        equipmentTab.closableProperty().setValue(false);

        //Notes tab
        FlowPane notesPane = getTabFlowPane();
        notesTab.setContent(notesPane);
        notesTab.closableProperty().setValue(false);

        tabPane.getStyleClass().add("tab-pane");

        tabPane.getTabs().addAll(statsTab, skillsTab, featsTab, weaponsTab, armorTab, equipmentTab, notesTab);

        return tabPane;
    }
}
