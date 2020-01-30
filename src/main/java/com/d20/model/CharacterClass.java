package com.d20.model;

public class CharacterClass {

    private String className;
    private int classLevel;

    public CharacterClass(){}

    public CharacterClass(String className, int classLevel){
        this.className = className;
        this.classLevel = classLevel;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassLevel(int classLevel) {
        this.classLevel = classLevel;
    }

    public int getClassLevel() {
        return classLevel;
    }
}
