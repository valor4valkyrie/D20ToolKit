package com.d20.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewCharacterView implements NewCharacterViewInt {

    @Autowired
    MainView view;

    @Override
    public void getNewCharacterMenu() {
        BorderPane border = new BorderPane();
        Button button = new Button("Character");
        border.setTop(view.getMenuBar());
        border.setCenter(button);
        Scene scene = new Scene(border, 800, 800);
        view.setMainScene(scene, view.isFullScreen());
    }
}
