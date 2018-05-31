package com.d20.model;

public class Stat {

    private String name = null;
    private int stat = 0;
    private int tempStat = 0;

    public Stat(String name, int stat){
        this.name = name;
        this.stat = stat;

    }

    public Stat(String name, int stat, int tempStat){
        this.name = name;
        this.stat = stat;
        this.tempStat = tempStat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public int getModifier() {
        return (stat / 2) - 5;
    }

    public int getTempStat() {
        return tempStat;
    }

    public void setTempStat(int tempStat) {
        this.tempStat = tempStat;
    }

    public int getTempMod() {
        return (tempStat / 2) - 5;
    }

}
