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
    public void start(Stage primaryStage)  {
        Group root = new Group();
        Canvas canvas = new Canvas(600, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> gc.beginPath());

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                event -> gc.lineTo(event.getX(), event.getY()));

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                event -> {
                    gc.stroke();
                    gc.closePath();
                });

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}