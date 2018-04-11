package com.d20.view;

import javafx.scene.Scene;
import org.springframework.stereotype.Component;

@Component
public interface MainViewInt {

    public void setMainScene(Scene scene, boolean fullscreen);

    public void getMainMenu();

}
