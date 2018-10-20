package com.d20.services;

import com.d20.model.Stat;
import com.d20.model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatsService {

    @Autowired
    Stats stats;

    public StatsService(){}

    public Stats getStats(){
        return stats;
    }


    private int statsTotal;

    private int rollStat(){
        Random random = new Random();

        int statsTotal = 0;

        int diceRoll = random.nextInt(6) + 1;

        List<Integer> stats = new ArrayList<>();

        while(stats.size() < 4) {
            while (diceRoll < 2) {
                diceRoll = random.nextInt(6) + 1;
            }
            stats.add(diceRoll);
        }

        Collections.sort(stats);

        stats.remove(stats.lastIndexOf(diceRoll));

        for (Integer e : stats) {
            statsTotal = statsTotal + e;
        }

        return statsTotal;
    }

    public void rollAllStats(){

        stats.setStrength(new Stat("Strength", rollStat()));
        stats.setDexterity(new Stat("Dexterity", rollStat()));
        stats.setConstitution(new Stat("Constitution", rollStat()));
        stats.setIntelligence(new Stat("Intelligence", rollStat()));
        stats.setWisdom(new Stat("Wisdom", rollStat()));
        stats.setCharisma(new Stat("Charisma", rollStat()));

        statsTotal = stats.getStrength().getStat() +
                stats.getDexterity().getStat() +
                stats.getConstitution().getStat() +
                stats.getIntelligence().getStat() +
                stats.getWisdom().getStat() +
                stats.getCharisma().getStat();
    }

    public int getStatsTotal() {
        return statsTotal;
    }

}
