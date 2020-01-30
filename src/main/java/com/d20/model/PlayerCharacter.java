package com.d20.model;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Set;

public class PlayerCharacter {

    private Long id;
    private List<CharacterClass> classList = Lists.newArrayList();
    private Stats stats;
    private Set<Skill> skills;
    private List<Feat> feats;

    public void setCharacterId(Long id){
        this.id = id;
    }

    public Long getCharacterId(){
        return id;
    }

    public void addClasses(CharacterClass characterClass) {
        classList.add(characterClass);
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public List<CharacterClass> getClasses() {
        return classList;
    }

    public Stats getStats() {
        return stats;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public List<Feat> getFeats() {
        return feats;
    }

    public void addSkills(Skill skill) {
        skills.add(skill);
    }

    public void addFeat(Feat feat) {
        feats.add(feat);
    }
}
