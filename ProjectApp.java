import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Polyline;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Author: Jacques Gueye
 * Assignment 4: ProjectApp
 * Date: 03/27/21
 * Course: CS56 Adv Java (1791)
 * Description: Program creates polyline shapes dynamically using mouse clicks.
 * Each mouse click adds a new line segment to the current polyline 
 * from the previous point to the current mouse position; ends the current polyline with double click.
 * Includes clear button.
 *
 */
public class ProjectApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        //polyline creation
        Pane pane = new Pane();
        Polyline polyline = new Polyline();
        polyline.setStroke(Color.BLACK);
        ObservableList<Double> list = polyline.getPoints();
        pane.getChildren().add(polyline);
        
        //clear button creation
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        Button btClear = new Button("Clear");
        hbox.getChildren().add(btClear);
        btClear.setOnAction((ActionEvent e) -> {
            pane.getChildren().clear();
            list.clear();
            pane.getChildren().add(polyline);
        });
                
        //combine panes
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(hbox);

        borderPane.setOnMouseClicked((MouseEvent e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.getClickCount() == 2) {//double click
                    Polyline permaPolyline = new Polyline();
                    permaPolyline.getPoints().addAll(list);
                    list.clear();
                    pane.getChildren().add(permaPolyline); //solidifies past lines
                } else if (e.getClickCount() == 1) {//single click
                    list.add(e.getSceneX());
                    list.add(e.getSceneY());
                }
            }
        });

        Scene scene = new Scene(borderPane, 400, 400);
        primaryStage.setTitle("ProjectApp"); 
        primaryStage.setScene(scene); 
        primaryStage.show(); // Display the stage
    }

    /**
     * The main method is only needed for the IDE with limited JavaFX support.
     * Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
