package com.d20.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DiceService {

    public static int rollD4(){ return new Random().nextInt(4) + 1; }

    public static int rollD6(){ return new Random().nextInt(6) + 1; }

    public static int rollD8(){ return new Random().nextInt(8) + 1; }

    public static int rollD10(){ return new Random().nextInt(10) + 1; }

    public static int rollD12(){ return new Random().nextInt(12) + 1; }

    public static int rollD20(){ return new Random().nextInt(20) + 1; }
}
