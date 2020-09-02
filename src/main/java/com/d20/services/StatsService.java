package com.d20.services;

import com.d20.model.Stat;
import com.d20.model.Stats;
import com.google.common.collect.Lists;
import org.jasypt.util.text.BasicTextEncryptor;
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

    private DiceService diceService = new DiceService();

    public StatsService() {
    }

    private int rollStat() {
        int statsTotal = 0;

        int diceRoll = diceService.rollD6();

        List<Integer> stats = new ArrayList<>();

        while (stats.size() < 4) {
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

    public Stats rollAllStats() {

        Stats stats = new Stats();
        stats.setStrength(new Stat("Strength", rollStat()));
        stats.setDexterity(new Stat("Dexterity", rollStat()));
        stats.setConstitution(new Stat("Constitution", rollStat()));
        stats.setIntelligence(new Stat("Intelligence", rollStat()));
        stats.setWisdom(new Stat("Wisdom", rollStat()));
        stats.setCharisma(new Stat("Charisma", rollStat()));

        return stats;
    }

    public int getStatsTotal(Stats stats) {

        return stats.getStrength().getStat() +
                stats.getDexterity().getStat() +
                stats.getConstitution().getStat() +
                stats.getIntelligence().getStat() +
                stats.getWisdom().getStat() +
                stats.getCharisma().getStat();
    }

    @Async
    public void sendStats(Stats stats, Environment env) {

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

        textEncryptor.setPassword(env.getProperty("security.jwt.password"));

        String token = textEncryptor.encrypt(env.getProperty("security.jwt.password") + "-" + LocalDateTime.now().toString());

        Client client = ClientBuilder.newClient();
        List<String> blah = Lists.newArrayList();

        if (stats != null) {
            try {

                Response response = client.target(env.getProperty("d20.services.stats.endpoint"))
                        .request(MediaType.APPLICATION_JSON)
                        .header("JWT", token)
                        .put(Entity.json(stats));

                if (response.getStatus() != 200) throw new Exception();

            } catch (Exception e) {
            } finally {
                client.close();
            }
        }
    }

}
