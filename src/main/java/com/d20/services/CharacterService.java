package com.d20.services;

import com.d20.model.Character;
import com.d20.model.CharacterClass;
import com.d20.model.CharacterImpl;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class CharacterService {

    private Set<Character> characterSet = new HashSet<Character>();

    public Character createNewCharacter(){
        CharacterImpl character = new CharacterImpl();
        character.setCharacterId(new Random().nextLong());
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

}
