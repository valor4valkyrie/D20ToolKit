package com.d20.services;

import com.d20.view.future.NewFutureView;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FutureViewService {

    private EventsService eventsService;
    private CharacterService characterService;
    private ViewService viewService;

    @Autowired
    public FutureViewService(EventsService eventsService, ViewService viewService, CharacterService characterService) {
        this.eventsService = eventsService;
        this.characterService = characterService;
        this.viewService = viewService;
    }

    public Pane getFutureCharacterView() {
        return new NewFutureView(eventsService, viewService, characterService).getNewCharacter();
    }
}
