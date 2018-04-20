package com.d20.main;

import com.d20.model.ModelConfig;
import com.d20.view.MainView;
import com.d20.view.ViewConfig;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@Configurable
public class D20ToolKit extends Application{

    @Override
    public void start(Stage primaryStage) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(D20ToolKit.class);
        context.register(BeanConfig.class);
        context.register(ModelConfig.class);
        context.register(ViewConfig.class);
        context.refresh();

        MainView mainView = context.getBean(MainView.class);
        mainView.getMainMenu();

    }


    public static void main(String[] args){
        launch(D20ToolKit.class, args);
    }
}
