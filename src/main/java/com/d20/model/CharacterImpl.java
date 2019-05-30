package com.d20.model;

import jersey.repackaged.com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;

public class CharacterImpl implements Character {

    private Long id;
    private List<CharacterClass> classList = Lists.newArrayList();
    private Stats stats;
    private Set<Skill> skills;
    private List<Feat> feats;

    @Override
    public void setCharacterId(Long id){
        this.id = id;
    }

    @Override
    public Long getCharacterId(){
        return id;
    }

    @Override
    public void addClasses(CharacterClass characterClass) {
        classList.add(characterClass);
    }

    @Override
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @Override
    public List<CharacterClass> getClasses() {
        return classList;
    }

    @Override
    public Stats getStats() {
        return stats;
    }

    @Override
    public Set<Skill> getSkills() {
        return skills;
    }

    @Override
    public List<Feat> getFeats() {
        return feats;
    }

    @Override
    public void addSkills(Skill skill) {
        skills.add(skill);
    }

    @Override
    public void addFeat(Feat feat) {
        feats.add(feat);
    }
}
