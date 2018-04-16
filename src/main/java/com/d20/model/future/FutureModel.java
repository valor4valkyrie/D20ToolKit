package com.d20.model.future;

import com.d20.view.MainView;
import com.d20.view.future.FutureView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FutureModel {

    @Autowired
    MainView view;

    @Autowired
    FutureView futureView;

    public void guidedCharacterView(){

    }

    public void getFutureCharacter(){

    }

}
