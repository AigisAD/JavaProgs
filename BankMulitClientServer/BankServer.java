
package BankMultiClientServer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BankServer extends Application{
  ArrayList<Bank> users=new ArrayList<>();
  TextArea ta = new TextArea();
  @Override 
  public void start(Stage primaryStage) {
    
    Scene scene = new Scene(new ScrollPane(ta), 450, 200);
    primaryStage.setTitle("Server"); 
    primaryStage.setScene(scene); 
    primaryStage.show(); 
    
    //stopping threads when force quit
    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent e) {
           Platform.exit();
           System.exit(0);
        }
    });
    new Thread( () -> {
      try {
        // Create a server socket
        ServerSocket serverSocket = new ServerSocket(8000);
        ta.appendText("Server started at " 
          + new Date() + '\n');
    
        while (true) {
          // Listen for a new connection request
          Socket socket = serverSocket.accept();
    
          
          Platform.runLater( () -> {
            ta.appendText("Connection to client successful \n");
          });
          
          // Create and start a new thread for the connection
          new Thread(new HandleAClient(socket)).start();
        }
      }
      catch(IOException ex) {
        //System.err.println(ex);
      }
    }).start();      
  }
  class HandleAClient implements Runnable {
  private Socket socket; // A connected socket
 
  /** Construct a thread */
  public HandleAClient(Socket socket) {
    this.socket = socket;
    
  }
      public void run(){
          try {
          // Create data input and output streams
           DataInputStream inputFromClient = new DataInputStream(
            socket.getInputStream());
          DataOutputStream outputToClient = new DataOutputStream(
            socket.getOutputStream());
          

          while (true) {
            int operation= inputFromClient.readInt(); 
            //could use switch cases for operations
            if (operation==1){//balance
                String accNum = inputFromClient.readUTF();
                Bank workAcc = null;
                boolean newAcc=true;
                for(Bank user:users){
                    if (accNum.equals(user.getAccountNumber())){
                        workAcc=user;
                        newAcc=false;
                    }
                }
                if (newAcc){
                    workAcc=new Bank(accNum,0);
                    users.add(workAcc);
                }
                double bal=workAcc.getBalance();
                outputToClient.writeDouble(workAcc.getBalance());

                Platform.runLater(() -> {
                  ta.appendText("From client>>> Balance " + accNum + '\n'); 
                  ta.appendText("Your balance in account "+accNum+" is " + bal + '\n'); 
                });
            }
            if (operation==2){//Deposit
                String accNum = inputFromClient.readUTF();
                Bank workAcc = null;
                boolean newAcc=true;
                for(Bank user:users){
                    if (accNum.equals(user.getAccountNumber())){
                        workAcc=user;
                        newAcc=false;
                    }
                }
                if (newAcc){
                    workAcc=new Bank(accNum,0);
                    users.add(workAcc);
                }
                double amount = inputFromClient.readDouble();
                workAcc.deposit(amount);
                double newBal=workAcc.getBalance();
                outputToClient.writeDouble(workAcc.getBalance());

                Platform.runLater(() -> {
                  ta.appendText("From client>>> Deposit "+accNum+ " " 
                    + amount + '\n');
                  ta.appendText("You deposited " +amount+" in account "+accNum+
                          ". New balance is " + newBal + '\n'); 
                });
            }
            if (operation==3){ //withdraw
                String accNum = inputFromClient.readUTF();
                Bank workAcc = null;
                boolean newAcc=true;
                for(Bank user:users){
                    if (accNum.equals(user.getAccountNumber())){
                        workAcc=user;
                        newAcc=false;
                    }
                }
                if (newAcc){
                    workAcc=new Bank(accNum,0);
                    users.add(workAcc);
                }
                double amount = inputFromClient.readDouble();
                boolean result=workAcc.withdraw(amount);
                double newBal=workAcc.getBalance();
                outputToClient.writeBoolean(result);
                outputToClient.writeDouble(workAcc.getBalance());

                Platform.runLater(() -> {
                  ta.appendText("From client>>> Withdraw "+accNum+ " " 
                    + amount + '\n');
                  if(result){
                    ta.appendText("You withdrew " +amount+" from account "+accNum+
                            ". Your new balance is " + newBal + '\n'); 
                  }else{
                    ta.appendText("You cannot withdraw " +amount+" from account "+accNum+
                            ". Balance is " + newBal + '\n');                       
                  }
                });
            }
            if  (operation==4){//Quit
                Platform.runLater(() -> {
                     ta.appendText("From client>>> Quit \n");
                     ta.appendText("Quit\n");
                 });
                socket.close();
                inputFromClient.close();
                outputToClient.close();
            }
          }
        }
        catch(IOException ex) {
          //ex.printStackTrace();
        }
        } 
    }

  public static void main(String[] args) {
    launch(args);
  }    
}

