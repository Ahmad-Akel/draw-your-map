package com.example.maps;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        Canvas canvas = new Canvas(600, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        gc.setFill(Color.GRAY); // set default fill color to white
        gc.setLineWidth(2); // set default brush size
        primaryStage.setTitle("Maps");
        canvas.setLayoutX(bounds.getMinX());
        canvas.setLayoutY(bounds.getMinY());
        canvas.setWidth(bounds.getWidth());
        canvas.setHeight(bounds.getHeight());

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> gc.beginPath());

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                event -> gc.lineTo(event.getX(), event.getY()));

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                event -> {
                    gc.stroke();
                    gc.fill(); // fill the shape with the current fill color
                    gc.closePath();
                });

        // adding a color picker to change the fill color of the shape
        ColorPicker fillColorPicker = new ColorPicker();
        fillColorPicker.setValue(Color.WHITE); // set default fill color
        fillColorPicker.setOnAction(e -> gc.setFill(fillColorPicker.getValue()));
        fillColorPicker.setLayoutX(150);

        // adding a color picker to change the stroke color of the shape
        ColorPicker strokeColorPicker = new ColorPicker();
        strokeColorPicker.setValue(Color.BLACK); // set default stroke color
        strokeColorPicker.setOnAction(e -> gc.setStroke(strokeColorPicker.getValue()));

        root.getChildren().addAll(canvas, fillColorPicker, strokeColorPicker);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }
    public static void main(String[] args) {
        launch();
    }
}