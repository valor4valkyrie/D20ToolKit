package com.d20.model;

import com.d20.model.future.FutureModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {

    @Bean
    public FutureModel futureModel(){ return new FutureModel(); }

    @Bean
    public Stats stats(){
        return new Stats();
    }

}
