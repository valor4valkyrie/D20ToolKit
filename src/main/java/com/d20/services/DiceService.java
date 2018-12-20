package com.d20.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DiceService {

    private Random random = new Random();

    public int rollD4(){
        return this.random.nextInt(4) + 1;
    }

    public int rollD6(){ return this.random.nextInt(6) + 1; }

    public int rollD8(){ return this.random.nextInt(8) + 1; }

    public int rollD10(){
        return this.random.nextInt(10) + 1;
    }

    public int rollD12(){
        return this.random.nextInt(12) + 1;
    }

    public int rollD20(){
        return this.random.nextInt(20) + 1;
    }
}
