package com.d20.model;

import org.springframework.stereotype.Component;
import org.testng.collections.Lists;

import java.io.Serializable;
import java.util.*;

@Component
public class Stats implements Serializable {

    private final static long serialVersionUID = 1L;

    private String id;
    private Stat strength;
    private Stat dexterity;
    private Stat constitution;
    private Stat intelligence;
    private Stat wisdom;
    private Stat charisma;

    public Stats(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Stat getStrength() {
        return strength;
    }

    public void setStrength(Stat strength) {
        this.strength = strength;
    }

    public Stat getDexterity() {
        return dexterity;
    }

    public void setDexterity(Stat dexterity) {
        this.dexterity = dexterity;
    }

    public Stat getConstitution() {
        return constitution;
    }

    public void setConstitution(Stat constitution) {
        this.constitution = constitution;
    }

    public Stat getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Stat intelligence) {
        this.intelligence = intelligence;
    }

    public Stat getWisdom() {
        return wisdom;
    }

    public void setWisdom(Stat wisdom) {
        this.wisdom = wisdom;
    }

    public Stat getCharisma() {
        return charisma;
    }

    public void setCharisma(Stat charisma) {
        this.charisma = charisma;
    }

    public List<Stat> getStatsList(){
        List<Stat> statList = Lists.newArrayList();

        statList.add(getStrength());
        statList.add(getDexterity());
        statList.add(getConstitution());
        statList.add(getIntelligence());
        statList.add(getWisdom());
        statList.add(getCharisma());

        return statList;

    }
}
