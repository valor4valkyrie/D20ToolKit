package com.d20.model.future;

import javafx.scene.text.Text;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FutureClassDescription {

    private StringBuilder smartHeroDesc = new StringBuilder();
    private StringBuilder strongHeroDesc = new StringBuilder();
    private StringBuilder fastHeroDesc = new StringBuilder();
    private StringBuilder toughHerDesc = new StringBuilder();
    private StringBuilder dedicatedHeroDesc = new StringBuilder();
    private StringBuilder charismaticHeroDesc = new StringBuilder();

    public FutureClassDescription(){
        try {
            Path smartPath = Paths.get(getClass().getClassLoader()
                    .getResource("./classes/future/smarthero.txt").toURI());
            Stream<String> lines = Files.lines(smartPath);
            lines.forEach(line -> smartHeroDesc.append(line).append("\n"));
            lines.close();
        }catch (Exception e){
            e.getMessage();
        }

        try {
            Path strongPath = Paths.get(getClass().getClassLoader()
                    .getResource("./classes/future/stronghero.txt").toURI());
            Stream<String> lines = Files.lines(strongPath);
            StringBuilder strongData = new StringBuilder();
            lines.forEach(line -> strongHeroDesc.append(line).append("\n"));
            lines.close();
        }catch (Exception e){
            e.getMessage();
        }

        try {
            Path fastPath = Paths.get(getClass().getClassLoader()
                    .getResource("./classes/future/fasthero.txt").toURI());
            Stream<String> lines = Files.lines(fastPath);
            StringBuilder fastData = new StringBuilder();
            lines.forEach(line -> fastHeroDesc.append(line).append("\n"));
            lines.close();
        }catch (Exception e){
            e.getMessage();
        }

        try {
            Path toughPath = Paths.get(getClass().getClassLoader()
                    .getResource("./classes/future/toughhero.txt").toURI());
            Stream<String> lines = Files.lines(toughPath);
            lines.forEach(line -> toughHerDesc.append(line).append("\n"));
            lines.close();
        }catch (Exception e){
            e.getMessage();
        }

        try {
            Path charismaPath = Paths.get(getClass().getClassLoader()
                    .getResource("./classes/future/charismatichero.txt").toURI());
            Stream<String> lines = Files.lines(charismaPath);
            lines.forEach(line -> charismaticHeroDesc.append(line).append("\n"));
            lines.close();
        }catch (Exception e){
            e.getMessage();
        }

        try {
            Path dedicatedPath = Paths.get(getClass().getClassLoader()
                    .getResource("./classes/future/dedicatedhero.txt").toURI());
            Stream<String> lines = Files.lines(dedicatedPath);
            lines.forEach(line -> dedicatedHeroDesc.append(line).append("\n"));
            lines.close();
        }catch (Exception e){
            e.getMessage();
        }

    }

    public String getSmartHeroDesc(){
        return smartHeroDesc.toString();
    }
    public String getStrongHeroDesc(){
        return strongHeroDesc.toString();
    }
    public String getToughHeroDesc(){
        return toughHerDesc.toString();
    }
    public String getFastHeroDesc(){
        return fastHeroDesc.toString();
    }
    public String getDedicatedHeroDesc(){
        return dedicatedHeroDesc.toString();
    }
    public String getCharismaticHeroDesc(){
        return charismaticHeroDesc.toString();
    }
}
