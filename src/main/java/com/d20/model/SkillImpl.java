package com.d20.model;

public class SkillImpl implements Skill{

    private String name = "Undeclared";
    private String keyAbility = "Undeclared";
    private int skillAbility = 0;
    private int skillRanks = 0;
    private int skillMisc = 0;

    @Override
    public void setSkillName(String name) {
        this.name = name;
    }

    @Override
    public String getSkillName() {
        return name;
    }

    @Override
    public void setSkillKeyAbility(String keyAbility) {
        this.keyAbility = keyAbility;
    }

    @Override
    public String getSkillKeyAbility() {
        return keyAbility;
    }

    @Override
    public int getSkillTotal() {
        return skillAbility + skillRanks + skillMisc;
    }

    @Override
    public void setSkillAbility(int skillAbility) {
        this.skillAbility = skillAbility;

    }

    @Override
    public int getSkillAbility() {
        return skillAbility;
    }

    @Override
    public void setSkillRank(int skillRanks) {
        this.skillRanks = skillRanks;
    }

    @Override
    public int getSkillRank() {
        return skillRanks;
    }

    @Override
    public void setSkillMisc(int skillMisc) {
        this.skillMisc = skillMisc;
    }

    @Override
    public int getSkillMisc() {
        return skillMisc;
    }
}
