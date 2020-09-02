package com.d20.services;

import com.d20.model.CharacterClass;
import com.d20.model.PlayerCharacter;
import com.d20.model.Stats;
import com.google.common.collect.Lists;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterService {

    private Set<PlayerCharacter> characterSet = new HashSet<PlayerCharacter>();
    private String statsEndpoint;
    private String password;

    public CharacterService () {

    }

    public CharacterService(String statsEndpoint, String password){
        this.statsEndpoint = statsEndpoint;
        this.password = password;
    }

    public PlayerCharacter createNewCharacter(){
        PlayerCharacter character = new PlayerCharacter();
        character.setCharacterId(-1L);
        characterSet.add(character);
        return character;
    }

    public PlayerCharacter createNewCharacter(Stats stats){
        PlayerCharacter character = new PlayerCharacter();
        character.setCharacterId(-1L);
        character.setStats(stats);
        characterSet.add(character);
        return character;
    }

    public PlayerCharacter getCharacter(Long id){
        Optional<PlayerCharacter> characterOptional = characterSet.stream().filter(object -> object.getCharacterId() == id).findFirst();

        if(characterOptional.isPresent()){
            return characterOptional.get();
        }

        return new PlayerCharacter();
    }

    public void addCharacterClass(Long id, CharacterClass characterClass){
        PlayerCharacter character = getCharacter(id);
        character.addClasses(characterClass);
    }

    public void addCharacter(PlayerCharacter character){
        characterSet.add(character);
    }

    public void setCharacterStats(Long id, Stats stats){
        PlayerCharacter character = getCharacter(id);
        character.setStats(stats);
    }
}
