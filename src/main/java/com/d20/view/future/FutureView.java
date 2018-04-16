package com.d20.view.future;

import com.d20.view.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FutureView {

    @Autowired
    MainView view;

    @Autowired
    NewFutureView newFutureView;

    public FutureView(){

    }

    public void newFutureView(){
        view.setMainScene(newFutureView.getNewCharacter(), view.isFullScreen());
    }
    
}
