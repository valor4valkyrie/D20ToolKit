package com.d20.services;

import com.d20.model.PlayerCharacter;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class EventsService implements EnvironmentAware {

    private ViewService viewService;
    private String statsEndpoint;
    private String password;

    @Override
    public void setEnvironment(Environment environment) {
        statsEndpoint = environment.getProperty("d20.services.stats.endpoint");
        password = environment.getProperty("security.jwt.password");
    }

    public EventsService(ViewService viewService){
        this.viewService = viewService;
    }

    public void setSceneEvent(Pane pane){
        viewService.setMainScene(pane);
    }

    public void setSceneEvent(TabPane pane){
        viewService.setMainScene(pane);
    }

    public PlayerCharacter getCharacter(){
        CharacterService characterService = new CharacterService(statsEndpoint, password);
        return characterService.getCharacter(-1L);
    }

    public void saveCharacter(PlayerCharacter pc){
        CharacterService characterService = new CharacterService(statsEndpoint, password);

        if(pc != null) {
            characterService.sendStats(pc.getStats());
        }
    }

}
