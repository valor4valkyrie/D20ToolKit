package com.d20.model;

public class CharacterClassImpl implements CharacterClass {

    private String className;
    private int classLevel;

    public CharacterClassImpl(){}

    public CharacterClassImpl(String className, int classLevel){
        this.className = className;
        this.classLevel = classLevel;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void setClassLevel(int classLevel) {
        this.classLevel = classLevel;
    }

    @Override
    public int getClassLevel() {
        return classLevel;
    }
}
