package com.d20.view.future;

import com.d20.services.ViewService;
import com.d20.view.NewStatsView;
import org.springframework.beans.factory.annotation.Autowired;

public class SmartHeroView extends BaseCharacterView{

    @Autowired
    private ViewService viewService;

    public SmartHeroView(){
        viewService.setMainScene(new NewStatsView().getNewStatsView(false), viewService.isFullScreen());
    }

}
