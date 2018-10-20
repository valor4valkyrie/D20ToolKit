package com.d20.view.future;

import com.d20.services.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FutureView {

    @Autowired
    private ViewService viewService;

    @Autowired
    private NewFutureView newFutureView;

    public FutureView(){

    }

    public void newFutureView(){
        viewService.setMainScene(newFutureView.getNewCharacter(), viewService.isFullScreen());
    }
    
}
