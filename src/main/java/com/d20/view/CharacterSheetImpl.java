package com.d20.view;

import com.d20.model.Stats;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class CharacterSheetImpl implements CharacterSheet {

    private BorderPane characterSheet = new BorderPane();
    private StatsView StatsPane;
    private Pane SkillsPane;
    private Pane FeatsPane;
    private Pane WeaponsPane;
    private Pane GeneralPane;

    public CharacterSheetImpl(com.d20.model.Character character){
        character.setStats(character.getStats());
    }

    @Override
    public void setStats(Stats stats) {

        StatsView statsView = new StatsViewImpl(stats);

        characterSheet.setTop(statsView.getStatsView(false));
    }

    @Override
    public void setSkills(Pane skillsPane) {
        characterSheet.setRight(skillsPane);
    }

    @Override
    public void setFeats(Pane featsPane) {
        characterSheet.setBottom(featsPane);
    }

    @Override
    public void setGeneral(Pane generalPane) {
        characterSheet.setCenter(generalPane);
    }

    @Override
    public BorderPane getCharacterSheet(){
        return characterSheet;
    }
}
