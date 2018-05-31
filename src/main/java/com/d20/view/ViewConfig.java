package com.d20.view;

import com.d20.view.future.FutureView;
import com.d20.view.future.NewFutureView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ViewConfig {

    @Bean
    public MainView mainView(){ return new MainView(); }

    @Bean
    public MainMenu mainMenu(){ return new MainMenu(); }

    @Bean
    public FutureView futureView(){ return new FutureView(); }

    @Bean
    public NewFutureView newFutureView(){ return new NewFutureView(); }

    @Bean
    public NewStatsView newStatsView(){ return new NewStatsView(); }

}