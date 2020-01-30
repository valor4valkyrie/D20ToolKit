package com.d20.services;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService{

    public ImageService(){

    }

    public ImageView getD20FutureCover(){
        return new ImageView(new Image("./images/D20_Future_Book_Cover.jpg"));
    }

    public ImageView getImage(String image){
        return new ImageView(new Image("./images/" + image + ".jpg"));
    }

    public ImageView getFutureImage(String image){
        return new ImageView(new Image("./images/Future/" + image + ".jpg"));
    }

    public Background createBackgroundImage(ImageView backgroundImage, BackgroundPosition pos){

        BackgroundImage image = new BackgroundImage(backgroundImage.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, pos, BackgroundSize.DEFAULT);

        List<BackgroundFill> fillList = new ArrayList<>();
        List<BackgroundImage> imageList = new ArrayList<>();

        imageList.add(image);

        Background background = new Background(fillList, imageList);

        return background;
    }

}
