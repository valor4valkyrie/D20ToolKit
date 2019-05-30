package com.d20.model;

import java.util.List;
import java.util.Set;

public interface Character {

    void setCharacterId(Long id);

    void addClasses(CharacterClass characterClass);

    void setStats(Stats stats);

    void addSkills(Skill skill);

    void addFeat(Feat feat);

    Long getCharacterId();

    List<CharacterClass> getClasses();

    Stats getStats();

    Set<Skill> getSkills();

    List<Feat> getFeats();

}
