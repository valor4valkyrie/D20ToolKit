package com.d20.model;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "STATS")
public class Stats {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(name = "STAT_STR")
    private Stat strength;

    @Column(name = "STAT_DEX")
    private Stat dexterity;

    @Column(name = "STAT_CON")
    private Stat constitution;

    @Column(name = "STAT_INT")
    private Stat intelligence;

    @Column(name = "STAT_WIS")
    private Stat wisdom;

    @Column(name = "STAT_CHA")
    private Stat charisma;

    public Stats(Map<String, Stat> statMap){
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
