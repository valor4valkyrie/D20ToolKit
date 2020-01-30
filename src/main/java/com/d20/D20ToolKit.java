package com.d20;

import com.d20.services.ViewService;
import com.d20.view.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class D20ToolKit extends Application{

    @Override
    public void start(Stage primaryStage) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(D20ToolKit.class);
        context.register(BeanConfig.class);
        context.refresh();

        ViewService viewService = context.getBean(ViewService.class);
        MainMenu mainMenu = context.getBean(MainMenu.class);

        viewService.setMainScene(mainMenu.getMainMenu());

    }

    public static void main(final String[] args){
        launch(D20ToolKit.class, args);
    }
}
