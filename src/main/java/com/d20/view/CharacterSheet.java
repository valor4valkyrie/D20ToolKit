package com.d20.view;

import com.d20.model.Character;
import com.d20.model.Stats;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public interface CharacterSheet {

    void setStats(Stats stats);
    void setSkills(Pane skillsPane);
    void setFeats(Pane featsPane);
    void setGeneral(Pane generalPane);

    BorderPane getCharacterSheet();
}
