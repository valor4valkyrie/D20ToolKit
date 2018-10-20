package com.d20.model;

import org.springframework.stereotype.Component;

@Component
public interface StatsRepository{

    public void saveStats(Stats stats);

}
