package com.d20.services;

import com.d20.model.PlayerCharacter;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class EventsService implements EnvironmentAware {

    private Environment env;
    private ViewService viewService;
    private String statsEndpoint;
    private String password;

    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
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
        StatsService statsService = new StatsService();

        if(pc != null) {
            statsService.sendStats(pc.getStats(), env);
        }
    }

}
