package com.d20.view.future;

import com.d20.view.MainView;
import com.d20.view.NewStatsView;
import org.springframework.beans.factory.annotation.Autowired;

public class SmartHeroView extends BaseCharacterView{

    @Autowired
    MainView mainView;

    public SmartHeroView(){
        mainView.setMainScene(new NewStatsView().getNewStatsView(false), mainView.isFullScreen());
    }

}
