
package BankMultiClientServer;

import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class BankClient extends Application {
  DataOutputStream toServer = null;
  DataInputStream fromServer = null;
  @Override 
  public void start(Stage primaryStage) {
    // Panel to hold the label and text field
    GridPane gPane=new GridPane();
    gPane.add(new Label("Account Number"), 0, 0);
    gPane.add(new Label("Amount $"), 0, 1);
    TextField accountField = new TextField();
    TextField amountField = new TextField();
    gPane.add(accountField,2,0,2,1);
    gPane.add(amountField, 2, 1,2,1);
    
    BorderPane paneForTextField = new BorderPane();
    paneForTextField.setPadding(new Insets(5, 5, 5, 5)); 
    gPane.setStyle("-fx-border-color: green");
    gPane.setAlignment(Pos.CENTER);
    
    Button btBalance=new Button("Balance");
    Button btDeposit=new Button("Deposit");
    Button btWithdraw=new Button("Withdraw");
    Button btQuit=new Button("Quit");
    HBox hBox= new HBox(btBalance,btDeposit,btWithdraw,btQuit);
    
    
    gPane.add(hBox, 0, 2,4,1);

    BorderPane mainPane = new BorderPane();
    TextArea ta = new TextArea();
    mainPane.setCenter(new ScrollPane(ta));
    mainPane.setTop(gPane);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(mainPane, 450, 250);
    primaryStage.setTitle("Client"); 
    primaryStage.setScene(scene); 
    primaryStage.show(); 
    
    //Balance button
    btBalance.setOnAction(e -> {
      try {
       
        String accNum = accountField.getText().trim();
      
        toServer.writeInt(1);
        toServer.writeUTF(accNum);
        toServer.flush();
  
        double balance = fromServer.readDouble();
  
        // Display to the text area
        ta.appendText("Balance " + accNum + "\n");
        ta.appendText("From Server>>> Your balance in account "+accNum+" is " 
                + balance + '\n'); 
      }
      catch (Exception ex) {
        //System.err.println(ex);
      }
    });
    //Deposit Button
    btDeposit.setOnAction(e -> {
      try {
      
        String accNum = accountField.getText().trim();
        double amount=Double.parseDouble(amountField.getText().trim());
        
        toServer.writeInt(2);
        toServer.writeUTF(accNum);
        toServer.writeDouble(amount);
        toServer.flush();
       
        double balance = fromServer.readDouble();
        // Display to the text area
        ta.appendText("Deposit "+accNum+ " " + amount + '\n');
        ta.appendText("From Server>>> You deposited " +amount+" in account "+accNum+
                          ". New balance is " + balance + '\n'); 
      }
      catch (Exception ex) {
        //System.err.println(ex);
      }
    });
    //Withdraw button
    btWithdraw.setOnAction(e -> {
      try {
      
        String accNum = accountField.getText().trim();
        double amount=Double.parseDouble(amountField.getText().trim());
        
        toServer.writeInt(3);
        toServer.writeUTF(accNum);
        toServer.writeDouble(amount);
        toServer.flush();

        boolean result =fromServer.readBoolean();
        double balance = fromServer.readDouble();
   
        ta.appendText("Withdraw "+accNum+ " "  + amount + '\n');
        if(result){
          ta.appendText("From Server>>> You withdrew " +amount+" from account "+accNum+
                  ". Your new balance is " + balance + '\n'); 
        }else{
          ta.appendText("From Server>>> You cannot withdraw " +amount+" from account "+accNum+
                  ". Balance is " + balance + '\n');                       
        }
      }
      catch (Exception ex) {
       // System.err.println(ex);
      }
    }); 
    //Quit Button
    btQuit.setOnAction(e -> {
        try{
            ta.appendText("From Server>>> Quit\n");
            toServer.writeInt(4);
            toServer.close();
            fromServer.close();
            btQuit.setDisable(true);
            btDeposit.setDisable(true);
            btBalance.setDisable(true);
            btWithdraw.setDisable(true);
        } catch (Exception ex) { 
        }
      
    }); 
    try {
      // Create a socket to connect to the server
      Socket socket = new Socket("localhost", 8000);
      fromServer = new DataInputStream(socket.getInputStream());
      toServer = new DataOutputStream(socket.getOutputStream());
      ta.appendText("From Server>>> Connection to client succesful\n");
    }
    catch (IOException ex) {
      ta.appendText(ex.toString() + '\n');
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
