package com.d20.view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Component;

@Component
public interface MainViewInt {


    public void setMainScene(Pane pane, boolean fullscreen);

    public void getMainMenu();

}
