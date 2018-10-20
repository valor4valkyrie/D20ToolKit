package com.d20.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "STATS")
public class StatsModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name = "STR")
    private Stat strength;

    @Column(name = "DEX")
    private Stat dexterity;

    @Column(name = "CON")
    private Stat constitution;

    @Column(name = "INT")
    private Stat intelligence;

    @Column(name = "WIS")
    private Stat wisdom;

    @Column(name = "CHA")
    private Stat charisma;

    public StatsModel(){}

    public StatsModel(Map<String, Stat> statMap){
        strength = statMap.get("Strength");
        dexterity = statMap.get("Dexterity");
        constitution = statMap.get("Constitution");
        intelligence = statMap.get("Intelligence");
        wisdom = statMap.get("Wisdom");
        charisma = statMap.get("Charisma");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
