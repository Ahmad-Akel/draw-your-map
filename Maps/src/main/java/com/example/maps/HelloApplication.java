package com.example.maps;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Canvas canvas = new Canvas(1200, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.GRAY); // set default fill color to white
        gc.setLineWidth(2); // set default brush size

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
        javafx.scene.control.ColorPicker fillColorPicker = new javafx.scene.control.ColorPicker();
        fillColorPicker.setValue(Color.WHITE); // set default fill color
        fillColorPicker.setOnAction(e -> gc.setFill(fillColorPicker.getValue()));
        fillColorPicker.setLayoutX(150);

        // adding a color picker to change the stroke color of the shape
        javafx.scene.control.ColorPicker strokeColorPicker = new javafx.scene.control.ColorPicker();
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