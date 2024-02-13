/**
 * Author: Jacques Gueye
 * Assignment 5: ColorApp
 * Date: 04/23/21
 * Course: CS56 Adv Java (1791)
 * Description: Program uses scroll bars to select the foreground color for a label.
 * Four horizontal scroll bars are used for selecting the red, green, blue, and opacity
 * components of the color.
 *
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ContentDisplay;
import javafx.scene.paint.Color;

public class ColorApp extends Application {
    @Override
    public void start(Stage primaryStage) {
      
       //Creating ScrollBars
        ScrollBar redSli = new ScrollBar();
        ScrollBar greenSli = new ScrollBar();
        ScrollBar blueSli = new ScrollBar();
        ScrollBar opacitySli = new ScrollBar();
        opacitySli.setValue(100);
        
        //Creating gridpane with bars
        GridPane gPane = new GridPane();
        gPane.setAlignment(Pos.CENTER);
        gPane.add(new Label("Red:"),0,1);
        gPane.add(redSli, 1, 1);
        gPane.add(new Label("Green:"),0,2);
        gPane.add(greenSli, 1,2 );
        gPane.add(new Label("Blue:"),0,3);
        gPane.add(blueSli, 1, 3);
        gPane.add(new Label("Opacity:"),0,4);
        gPane.add(opacitySli, 1, 4);
        
        //Creating Label
        Label label= new Label ("Show Color",gPane);
        label.setContentDisplay(ContentDisplay.BOTTOM);
      
        
        //new pane
        StackPane sPane = new StackPane();
        sPane.setAlignment(Pos.CENTER);
        sPane.getChildren().add(label); 
        
        //events        
        redSli.valueProperty().addListener(ov -> 
            changeColor(label,redSli,blueSli,greenSli,opacitySli));
        greenSli.valueProperty().addListener(ov -> 
            changeColor(label,redSli,blueSli,greenSli,opacitySli));
        blueSli.valueProperty().addListener(ov -> 
            changeColor(label,redSli,blueSli,greenSli,opacitySli));
        opacitySli.valueProperty().addListener(ov -> 
            changeColor(label,redSli,blueSli,greenSli,opacitySli));
        
        //create scene
        Scene scene = new Scene(sPane, 300, 150);
        primaryStage.setTitle("ColorApp"); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    }
    //changes the color of the label based on bars
    public void changeColor(Label label,ScrollBar redSli,ScrollBar blueSli,
                 ScrollBar greenSli, ScrollBar opacitySli){
        label.setTextFill(new Color(redSli.valueProperty().doubleValue()/100,
        greenSli.valueProperty().doubleValue()/100, blueSli.valueProperty().doubleValue()/100,
        opacitySli.valueProperty().doubleValue()/100));
    }

    public static void main(String[] args) {
        launch(args);
    }
}