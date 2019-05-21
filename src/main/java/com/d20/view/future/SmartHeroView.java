package com.d20.view.future;

import com.d20.services.StatsService;
import com.d20.services.ViewService;
import com.d20.view.NewStatsView;
import org.springframework.beans.factory.annotation.Autowired;

public class SmartHeroView extends BaseCharacterView{

    @Autowired
    private ViewService viewService;

    @Autowired
    private StatsService statsService;

    public SmartHeroView(){
        viewService.setMainScene(new NewStatsView().getNewStatsView(statsService.rollAllStats(), false), viewService.isFullScreen());
    }

}
