package com.d20.services;

import com.d20.model.Character;
import com.d20.model.CharacterClass;
import com.d20.model.CharacterImpl;
import com.d20.model.Stats;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterService {

    private Set<Character> characterSet = new HashSet<Character>();

    public Character createNewCharacter(){
        CharacterImpl character = new CharacterImpl();
        character.setCharacterId(-1L);
        characterSet.add(character);
        return character;
    }

    public Character createNewCharacter(Stats stats){
        CharacterImpl character = new CharacterImpl();
        character.setCharacterId(-1L);
        character.setStats(stats);
        characterSet.add(character);
        return character;
    }

    public Character getCharacter(Long id){
        Optional<Character> characterOptional = characterSet.stream().filter(object -> object.getCharacterId() == id).findFirst();

        if(characterOptional.isPresent()){
            return characterOptional.get();
        }

        return null;
    }

    public void addCharacterClass(Long id, CharacterClass characterClass){
        Character character = getCharacter(id);
        character.addClasses(characterClass);
    }

    public void addCharacter(Character character){
        characterSet.add(character);
    }

    public void setCharacterStats(Long id, Stats stats){
        Character character = getCharacter(id);
        character.setStats(stats);
    }

}
