package com.d20.services;

import com.d20.model.Stat;
import com.d20.model.Stats;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StatsService {

    private int statsTotal;
    private DiceService diceService = new DiceService();

    public StatsService(){}

    private int rollStat(){

        int statsTotal = 0;

        int diceRoll = diceService.rollD6();

        List<Integer> stats = new ArrayList<>();

        while(stats.size() < 4) {
            while (diceRoll < 2) {
                diceRoll = diceService.rollD6();
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

    public Stats rollAllStats(){

        Stats stats = new Stats();
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

        return stats;
    }

    public int getStatsTotal() {
        return statsTotal;
    }

}
