package com.d20.view;

import javafx.animation.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class AnimationTest {

    public FlowPane getAnimation() {

        FlowPane pane = new FlowPane();

        final Rectangle rectPath = new Rectangle (-80, -80, 80, 80);
        rectPath.setArcHeight(10);
        rectPath.setArcWidth(10);
        rectPath.setFill(Color.GREEN);

        Path path = new Path();
        path.getElements().add(new MoveTo(20,20));
        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
        path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(rectPath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);

        FadeTransition ft = new FadeTransition(Duration.millis(3000), rectPath);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);

        ft.play();
        pathTransition.play();

        pane.getChildren().addAll(rectPath);

        return pane;
    }

    public FlowPane trasitionAnim(){

        FlowPane flowPane = new FlowPane();

        Polygon triangle = new Polygon();
        triangle.getPoints().setAll(new Double[]{
                0.0, 25.0,
                25.0, 50.0,
                50.0, 75.0,
                75.0, 100.0,
                100.0, 75.0,
                75.0, 50.0,
                50.0, 25.0,
                25.0, 0.0});

        Box box = new Box(100.0, 100.0, 100.0);

        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setSpecularColor(Color.ORANGE);
        redMaterial.setDiffuseColor(Color.RED);

        triangle.setFill(Color.GREEN);
        triangle.setTranslateX(50);
        triangle.setTranslateY(50);

        final Rectangle rectSeq = new Rectangle(25,25,50,50);
        rectSeq.setArcHeight(15);
        rectSeq.setArcWidth(15);
        rectSeq.setFill(Color.CRIMSON);
        rectSeq.setTranslateX(50);
        rectSeq.setTranslateY(50);

        FadeTransition fadeTransition =
                new FadeTransition(Duration.millis(1000), box);
        fadeTransition.setFromValue(1.0f);
        fadeTransition.setToValue(0.3f);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(true);

        TranslateTransition translateTransition =
                new TranslateTransition(Duration.millis(5000), box);
        translateTransition.setFromX(50);
        translateTransition.setToX(1500);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);

        RotateTransition rotateTransition =
                new RotateTransition(Duration.millis(2000), box);
        rotateTransition.setByAngle(180f);
        rotateTransition.setCycleCount(4);
        rotateTransition.setAutoReverse(true);

        RotateTransition rotateTop =
                new RotateTransition(Duration.millis(10000), box);
        rotateTransition.setByAngle(720);
        rotateTransition.setCycleCount(4);
        rotateTransition.setAutoReverse(true);

        ScaleTransition scaleTransition =
                new ScaleTransition(Duration.millis(2000), box);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(2);
        scaleTransition.setToY(2);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);

        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(
                fadeTransition,
                rotateTop,
                translateTransition,
                rotateTransition,
                scaleTransition);
        sequentialTransition.setCycleCount(Timeline.INDEFINITE);
        sequentialTransition.setAutoReverse(true);

        sequentialTransition.play();

        flowPane.getChildren().addAll(box);

        return flowPane;
    }
}
