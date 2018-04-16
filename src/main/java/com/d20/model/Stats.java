package com.d20.model;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Stats {

    private int strengthNum = 0;
    private int dexterityNum = 0;
    private int constitutionNum = 0;
    private int intelligenceNum = 0;
    private int wisdomNum = 0;
    private int charismaNum = 0;

    private int strengthMod = 0;
    private int dexterityMod = 0;
    private int constitutionMod = 0;
    private int intelligenceMod = 0;
    private int wisdomMod = 0;
    private int charismaMod = 0;

    public Stats(){}

    private int rollStat(){
        Random random = new Random();

        int diceRoll = random.nextInt(6) + 1;

        System.out.println("\n" + diceRoll + "\n");

        while(diceRoll < 2){
            diceRoll = random.nextInt(6) + 1;
        }

        return diceRoll;
    }

     public void setStrengthNum(int strengthNum){
        this.strengthNum = strengthNum;
     }

     public int getStrengthNum(){
        return strengthNum;
     }

     public void setDexterityNum(int dexterityNum){
        this.dexterityNum = dexterityNum;
     }

     public int getDexterityNum(){
        return  dexterityNum;
     }

     public void setConstitutionNum(int constitutionNum){
        this.constitutionNum = constitutionNum;
     }

     public int getIntelligenceNum(){
        return intelligenceNum;
     }
     public void setIntelligenceNum(int intelligenceNum){
        this.intelligenceNum = intelligenceNum;
     }

     public int getWisdomNum(){
        return wisdomNum;
     }

}
