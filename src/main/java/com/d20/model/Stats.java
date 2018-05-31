package com.d20.model;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Stats {

    private LinkedHashMap<String, Stat> statsMap = new LinkedHashMap<String, Stat>();

    private int statsTotal;

    public Stats(){
    }

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

        statsMap.put("Strength", new Stat("Strength", rollStat()));
        statsMap.put("Dexterity", new Stat("Dexterity", rollStat()));
        statsMap.put("Constitution", new Stat("Constitution", rollStat()));
        statsMap.put("Intelligence", new Stat("Intelligence", rollStat()));
        statsMap.put("Wisdom", new Stat("Wisdom", rollStat()));
        statsMap.put("Charisma", new Stat("Charisma", rollStat()));

        statsTotal = 0;
        statsMap.entrySet().forEach(s -> {
            statsTotal = statsTotal + s.getValue().getStat();
        });
    }

    public Map<String, Stat> getStatsMap(){
        return statsMap;
    }

    public int getStatsTotal() {
        return statsTotal;
    }
}
