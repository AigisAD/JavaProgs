/**
 * Author: Jacques Gueye
 * Assignment: ThreadProject 
 * Date: 05/22/21
 * Course: CS56 Adv Java (1791)
 * Description: Program implements an application
 * that keeps track of current time in California, 
 * Hawaii, and Korea, and has fans for each location 
 * that can be started automatically at chosen times.
 */
package threadproject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;



public class ThreadProject extends Application{
    volatile boolean caliRotate=false;
    volatile boolean koreaRotate=false;
    volatile boolean hawaiiRotate=false;
    volatile boolean fan3timerON=false;
    volatile boolean fan2timerON=false;
    volatile boolean fan1timerON=false;
    
    @Override
    public void start(Stage primaryStage) {
        
        //creating main pane
        GridPane pane=new GridPane();
        pane.getColumnConstraints().add(new ColumnConstraints(250)); 
        pane.getColumnConstraints().add(new ColumnConstraints(250)); 
        pane.getColumnConstraints().add(new ColumnConstraints(250));
        pane.getRowConstraints().add(new RowConstraints(250));
        pane.getRowConstraints().add(new RowConstraints(250));
        pane.getRowConstraints().add(new RowConstraints(250));
        
        //Creating Clocks and Fans
        StillClock caliClock=new StillClock();
        StillClock koreaClock=new StillClock((caliClock.getHour()+16)%24,caliClock.getMinute(),
                                    caliClock.getSecond());
        StillClock hawaiiClock=new StillClock((caliClock.getHour()+21)%24,caliClock.getMinute(),
                                    caliClock.getSecond());
        Label lbl1=new Label("California");
        Label lbl2=new Label ("Korea");
        Label lbl3=new Label ("Hawaii");
        DrawArcs caliFan=new DrawArcs();
        DrawArcs koreaFan=new DrawArcs();
        DrawArcs hawaiiFan=new DrawArcs();
        
        //Creating panes for clocks
        BorderPane bPane1 = new BorderPane();
        bPane1.setCenter(caliClock);
        bPane1.setBottom(lbl1);
        bPane1.setAlignment(lbl1,Pos.CENTER);
        pane.add(bPane1,0,0);
        BorderPane bPane2 = new BorderPane();
        bPane2.setCenter(koreaClock);
        bPane2.setBottom(lbl2);
        bPane1.setAlignment(lbl2,Pos.CENTER);
        pane.add(bPane2,1,0);
        BorderPane bPane3 = new BorderPane();
        bPane3.setCenter(hawaiiClock);
        bPane3.setBottom(lbl3);
        bPane3.setAlignment(lbl3,Pos.CENTER);
        pane.add(bPane3,2,0);
        
        //adding fans to main pane
        pane.add(caliFan,0,1);
        pane.add(koreaFan,1,1);
        pane.add(hawaiiFan,2,1);
        
        
        //bottom left pane
        GridPane gPane1=new GridPane();
        Button fStart1=new Button("Force Start");
        Button fStop1=new Button("Force Stop");
        Button sTimer1=new Button("Start Timer");
        TextField startField1=new TextField();
        TextField endField1=new TextField();
        Label lblTimer1=new Label("Timer OFF");
        gPane1.add(new Label("Start Time (HH:MM:SS): "),0,0);
        gPane1.add(startField1, 1, 0);
        gPane1.add(new Label("End Time (HH:MM:SS): "),0,1);
        gPane1.add(endField1, 1, 1);
        gPane1.add(fStart1, 0, 2);
        gPane1.add(fStop1, 1, 2);
        gPane1.add(sTimer1, 0, 3);
        gPane1.add(lblTimer1, 1, 3);
        gPane1.getColumnConstraints().add(new ColumnConstraints(150));
        gPane1.setAlignment(Pos.CENTER);
        pane.add(gPane1, 0, 2);
        
        //bottom center pane
        GridPane gPane2=new GridPane();
        Button fStart2=new Button("Force Start");
        Button fStop2=new Button("Force Stop");
        Button sTimer2=new Button("Start Timer");
        TextField startField2=new TextField();
        TextField endField2=new TextField();
        Label lblTimer2=new Label("Timer OFF");
        gPane2.add(new Label("Start Time (HH:MM:SS): "),0,0);
        gPane2.add(startField2, 1, 0);
        gPane2.add(new Label("End Time (HH:MM:SS): "),0,1);
        gPane2.add(endField2, 1, 1);
        gPane2.add(fStart2, 0, 2);
        gPane2.add(fStop2, 1, 2);
        gPane2.add(sTimer2, 0, 3);
        gPane2.add(lblTimer2, 1, 3);
        gPane2.getColumnConstraints().add(new ColumnConstraints(150));
        gPane2.setAlignment(Pos.CENTER);
        pane.add(gPane2, 1, 2);    
        
        //bottom right pane
        GridPane gPane3=new GridPane();
        Button fStart3=new Button("Force Start");
        Button fStop3=new Button("Force Stop");
        Button sTimer3=new Button("Start Timer");
        TextField startField3=new TextField();
        TextField endField3=new TextField();
        Label lblTimer3=new Label("Timer OFF");
        gPane3.add(new Label("Start Time (HH:MM:SS): "),0,0);
        gPane3.add(startField3, 1, 0);
        gPane3.add(new Label("End Time (HH:MM:SS): "),0,1);
        gPane3.add(endField3, 1, 1);
        gPane3.add(fStart3, 0, 2);
        gPane3.add(fStop3, 1, 2);
        gPane3.add(sTimer3, 0, 3);
        gPane3.add(lblTimer3, 1, 3);
        gPane3.getColumnConstraints().add(new ColumnConstraints(150));
        gPane3.setAlignment(Pos.CENTER);
        pane.add(gPane3, 2, 2);            
        
        GridPane.setMargin(gPane1,new Insets(5,5,5,5));
        GridPane.setMargin(gPane2,new Insets(5,5,5,5));
        GridPane.setMargin(gPane3,new Insets(5,5,5,5));
        //threads
        
        //thread to make clock move
        Thread clockThread=new Thread(new Runnable(){
        @Override
        public void run (){
            try{
          while( true){
          Platform.runLater(new Runnable(){
              @Override
              public void run(){
                  caliClock.setCurrentTime();
                  koreaClock.setMinute(caliClock.getMinute());
                  koreaClock.setSecond(caliClock.getSecond());
                  hawaiiClock.setMinute(caliClock.getMinute());
                  hawaiiClock.setSecond(caliClock.getSecond());
                  koreaClock.setHour((caliClock.getHour()+16)%24);
                  hawaiiClock.setHour((caliClock.getHour()+21)%24);
              }
          });
               Thread.sleep(1000);   
              }
          }catch(Exception e){ }
     }
      });
      clockThread.setDaemon(true);
      clockThread.start();
      
      //thread to make california fan move
      Thread fan1Thread=new Thread(new Runnable(){
          int rotate=0;
        @Override
        public void run (){
            try{
          while( caliRotate){
          Platform.runLater(new Runnable(){
              @Override
              public void run(){
                  caliFan.setRotate(rotate);
                  rotate=(rotate+10)%360;
              }
          });
               Thread.sleep(100);   
              }
          }catch(Exception e){ }
     }
      });
      
      //thread to make korea fan move
      Thread fan2Thread=new Thread(new Runnable(){
          int rotate=0;
        @Override
        public void run (){
            try{
          while(koreaRotate){
          Platform.runLater(new Runnable(){
              @Override
              public void run(){
                  koreaFan.setRotate(rotate);
                  rotate=(rotate+10)%360;
              }
          });
               Thread.sleep(100);   
              }
          }catch(Exception e){ }
     }
      });
    
      //thread to make hawaii fan move
      Thread fan3Thread=new Thread(new Runnable(){
          int rotate=0;
        @Override
        public void run (){
            try{
          while(hawaiiRotate){
          Platform.runLater(new Runnable(){
              @Override
              public void run(){
                  hawaiiFan.setRotate(rotate);
                  rotate=(rotate+10)%360;
              }
          });
               Thread.sleep(100);   
              }
          }catch(Exception e){ }
     }
      });

    //action handlers
        //force start california
        fStart1.setOnAction((ActionEvent e) -> {
            if(!caliRotate){
                caliRotate=true;
                Thread tempThread=new Thread(fan1Thread);
                tempThread.setDaemon(true);
                try{
                    tempThread.start();
                }catch(Exception ex){}
            }

        });
        //force stop california
        fStop1.setOnAction((ActionEvent e) -> {
            caliRotate=false;
            
        }); 
        //force start korea
        fStart2.setOnAction((ActionEvent e) -> {
            if(!koreaRotate){
                koreaRotate=true;
                Thread tempThread=new Thread(fan2Thread);
                tempThread.setDaemon(true);
                try{
                tempThread.start();
                }catch(Exception ex){}
            }
            
        });
        //force stop korea
        fStop2.setOnAction((ActionEvent e) -> {
            koreaRotate=false;
        });      
        //force start Hawaii
        fStart3.setOnAction((ActionEvent e) -> {
            if(!hawaiiRotate){
                hawaiiRotate=true;
                Thread tempThread=new Thread(fan3Thread);
                tempThread.setDaemon(true);
                try{
                tempThread.start();
                }catch(Exception ex){}
            }
        });
        //Force stop hawaii
        fStop3.setOnAction((ActionEvent e) -> {
            hawaiiRotate=false;
        });    
        //Start timer california
        sTimer1.setOnAction((ActionEvent e) -> {
            if(lblTimer1.getText().equals("Timer OFF")){
                try {
                    Date starter=new SimpleDateFormat("HH:mm:ss").parse(startField1.getText());
                    int starterHour=Integer.parseInt(starter.toString().substring(11,13));
                    int starterMinute=Integer.parseInt(starter.toString().substring(14,16));
                    int starterSecond=Integer.parseInt(starter.toString().substring(17,19));
                    Date ender=new SimpleDateFormat("HH:mm:ss").parse(endField1.getText());
                    int enderHour=Integer.parseInt(ender.toString().substring(11,13));
                    int enderMinute=Integer.parseInt(ender.toString().substring(14,16));
                    int enderSecond=Integer.parseInt(ender.toString().substring(17,19));
                    
                    fan1timerON=true;
                    Thread autoFan1Thread=new Thread(new Runnable(){
                        @Override
                        public void run (){
                          try{
                          while(fan1timerON){
                          Platform.runLater(new Runnable(){
                              @Override
                              public void run(){
                                  if(caliClock.getHour()==starterHour&&
                                    caliClock.getMinute()==starterMinute&&
                                          caliClock.getSecond()==starterSecond){
                                   
                                      fStart1.fire();
                                  }
                                  if(caliClock.getHour()==enderHour&&
                                    caliClock.getMinute()==enderMinute&&
                                          caliClock.getSecond()==enderSecond){
                                  
                                      fStop1.fire();
                                    }                                 
                                }
                            });
                            Thread.sleep(1000);   
                            }
                            }catch(Exception e){ }
                        }
                    });
                    autoFan1Thread.start();
                    lblTimer1.setText("Timer ON");

                } catch (ParseException ex) {}
            }else{
                lblTimer1.setText("Timer OFF");
                fan1timerON=false;  
            }
        }); 
        //korea Start timer
        sTimer2.setOnAction((ActionEvent e) -> {
            if(lblTimer2.getText().equals("Timer OFF")){
                try {
                    Date starter=new SimpleDateFormat("HH:mm:ss").parse(startField2.getText());
                    int starterHour=Integer.parseInt(starter.toString().substring(11,13));
                    int starterMinute=Integer.parseInt(starter.toString().substring(14,16));
                    int starterSecond=Integer.parseInt(starter.toString().substring(17,19));
                    Date ender=new SimpleDateFormat("HH:mm:ss").parse(endField2.getText());
                    int enderHour=Integer.parseInt(ender.toString().substring(11,13));
                    int enderMinute=Integer.parseInt(ender.toString().substring(14,16));
                    int enderSecond=Integer.parseInt(ender.toString().substring(17,19));
                    
                    fan2timerON=true;
                    Thread autoFan2Thread=new Thread(new Runnable(){
                        @Override
                        public void run (){
                          try{
                          while(fan2timerON){
                          Platform.runLater(new Runnable(){
                              @Override
                              public void run(){
                                  if(koreaClock.getHour()==starterHour&&
                                    koreaClock.getMinute()==starterMinute&&
                                          koreaClock.getSecond()==starterSecond){
                                      fStart2.fire();
                                    }
                                  if(koreaClock.getHour()==enderHour&&
                                    koreaClock.getMinute()==enderMinute&&
                                          koreaClock.getSecond()==enderSecond){
                                  
                                      fStop2.fire();
                                
                                  }                                 
                                }
                            });
                            Thread.sleep(1000);   
                            }
                            }catch(Exception e){ }
                        }
                    });
                    autoFan2Thread.start();
                    lblTimer2.setText("Timer ON");
                } catch (ParseException ex) {}   
            }else{
                lblTimer2.setText("Timer OFF");
                fan2timerON=false;    
            }
        });  
        //Hawaii start timer
        sTimer3.setOnAction((ActionEvent e) -> {
            if(lblTimer3.getText().equals("Timer OFF")){
                try {
                    Date starter=new SimpleDateFormat("HH:mm:ss").parse(startField3.getText());
                    int starterHour=Integer.parseInt(starter.toString().substring(11,13));
                    int starterMinute=Integer.parseInt(starter.toString().substring(14,16));
                    int starterSecond=Integer.parseInt(starter.toString().substring(17,19));
                    Date ender=new SimpleDateFormat("HH:mm:ss").parse(endField3.getText());
                    int enderHour=Integer.parseInt(ender.toString().substring(11,13));
                    int enderMinute=Integer.parseInt(ender.toString().substring(14,16));
                    int enderSecond=Integer.parseInt(ender.toString().substring(17,19));
                    
                    fan3timerON=true;
                    Thread autoFan3Thread=new Thread(new Runnable(){
                        @Override
                        public void run (){
                          try{
                          while(fan3timerON){
                          Platform.runLater(new Runnable(){
                              @Override
                              public void run(){
                                  if(hawaiiClock.getHour()==starterHour&&
                                    hawaiiClock.getMinute()==starterMinute&&
                                          hawaiiClock.getSecond()==starterSecond){
                                   
                                      fStart3.fire();
                                    }
                                  if(hawaiiClock.getHour()==enderHour&&
                                    hawaiiClock.getMinute()==enderMinute&&
                                          hawaiiClock.getSecond()==enderSecond){
                                  
                                      fStop3.fire();
                                    }                                 
                                }
                            });
                            Thread.sleep(1000);   
                            }
                          }catch(Exception e){ }
                        }
                    });
                    autoFan3Thread.start();
                    lblTimer3.setText("Timer ON");
                } catch (ParseException ex) {}
            }else{
                lblTimer3.setText("Timer OFF");
                fan3timerON=false; 
            }
        });
        //stopping threads when force quit
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
               Platform.exit();
               System.exit(0);
            }
        });        
        Scene scene = new Scene(pane, 760, 750, Color.WHITE);
        primaryStage.setTitle("ThreadProject");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }

}
