package com.d20.services;

import com.d20.main.Utilities;
import com.d20.model.Stat;
import com.d20.model.Stats;
import org.jasypt.util.text.BasicTextEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StatsService {

    private static Logger log = LoggerFactory.getLogger(StatsService.class);

    @Autowired
    private Environment env;

    @Autowired
    private DiceService diceService;

    @Autowired
    private Utilities utils;

    @Autowired
    private CharacterService characterService;

    public StatsService(){}

    private int statsTotal;

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

        characterService.setCharacterStats(-1L, stats);
        return stats;
    }

    public int getStatsTotal() {
        return statsTotal;
    }

    @Async
    public void sendStats(Stats stats){

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

        textEncryptor.setPassword(env.getProperty("security.jwt.password"));

        String token = textEncryptor.encrypt(env.getProperty("security.jwt.password") + "-" + LocalDateTime.now().toString());

        Client client = ClientBuilder.newClient();

        if(stats != null) {
            try {

                Response response = client.target(env.getProperty("d20.services.stats.endpoint"))
                        .request(MediaType.APPLICATION_JSON)
                        .header("JWT", token)
                        .put(Entity.json(stats));

                if (response.getStatus() != 200) throw new Exception();

            } catch (Exception e) {
                log.error("Failed to sync new character stats: {}", e);
            } finally {
                client.close();
            }
        }

    }


}
