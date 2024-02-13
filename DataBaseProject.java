
/**
 * Author: Jacques Gueye
 * Assignment: DataBaseProject
 * Date: 06/05/21
 * Course: CS56 Adv Java (1791)
 * Description: Program implements JavaFX
 * GUI that views, inserts, and updates staff
 * information stored in a database.
 */

import java.sql.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class DataBaseProject extends Application {
    private Label status=new Label();//status/last operation
    Statement stmt;
    @Override 
    public void start(Stage primaryStage) {
        initializeDB();
        //Creating fields
        TextField idField = new TextField();
        TextField lnField = new TextField();
        TextField fnField = new TextField();
        TextField miField = new TextField();
        miField.setPrefWidth(30);
        TextField addyField = new TextField();
        TextField cityField = new TextField();
        TextField stateField = new TextField();
        stateField.setPrefWidth(35);
        TextField teleField = new TextField();
        TextField emailField= new TextField();
        
        //creating boxes for formatting
        HBox hBox0=new HBox();
        hBox0.getChildren().add(status);
        hBox0.setAlignment(Pos.CENTER);
        HBox hBox1=new HBox();
        hBox1.getChildren().addAll(new Label("ID"),idField);
        hBox1.setSpacing(5);
        HBox hBox2=new HBox();
        hBox2.getChildren().addAll(new Label("Last Name"),
            lnField,new Label("First Name"),fnField,new Label("MI"),miField);
        hBox2.setSpacing(5);
        HBox hBox3=new HBox();
        hBox3.getChildren().addAll(new Label("Address"),addyField);
        hBox3.setSpacing(5);
        HBox hBox4=new HBox();
        hBox4.getChildren().addAll(new Label("City"),cityField,
            new Label("State"),stateField);
        hBox4.setSpacing(5);
        HBox hBox5=new HBox();
        hBox5.getChildren().addAll(new Label("Telephone"),teleField,
            new Label("Email"),emailField);
        hBox5.setSpacing(5);
        VBox vBox=new VBox();
        vBox.getChildren().addAll(hBox0,hBox1,hBox2,hBox3,hBox4,hBox5);
        vBox.setSpacing(5);
        
        //buttons
        Button viewBT=new Button("View");
        Button insertBT=new Button("Insert");
        Button updateBT=new Button("Update");
        Button clearBT=new Button("Clear");
        HBox hBox6=new HBox();
        hBox6.getChildren().addAll(viewBT,insertBT,updateBT,clearBT);
        hBox6.setSpacing(5);
        hBox6.setAlignment(Pos.CENTER);
        
        //main pane
        BorderPane bPane=new BorderPane();
        bPane.setPadding(new Insets(5,5,5,5));
        bPane.setBottom(hBox6);
        bPane.setCenter(vBox);
        
        //Clicking View
        viewBT.setOnAction(e -> {
            String queryString="SELECT * FROM Staff WHERE id = "+ idField.getText().trim();
            try{
                ResultSet rset=stmt.executeQuery(queryString);
                if (rset.next()){
                    lnField.setText(rset.getString(2));
                    fnField.setText(rset.getString(3));
                    miField.setText(rset.getString(4));
                    addyField.setText(rset.getString(5));
                    cityField.setText(rset.getString(6));
                    stateField.setText(rset.getString(7));
                    teleField.setText(rset.getString(8));
                    emailField.setText(rset.getString(9));
                    status.setText("Record Found");
                }else{
                    status.setText("Record Not Found");
                    lnField.clear();
                    fnField.clear();
                    miField.clear();
                    addyField.clear();
                    cityField.clear();
                    stateField.clear();
                    teleField.clear();
                    emailField.clear();
                }
            }catch(SQLException ex){
                System.out.println("Query Failed\n"+ex);
                status.setText("Query Failed");
                
            }
        });
        //Clicking Insert
        insertBT.setOnAction(e -> {
            String queryString="INSERT INTO Staff(id,lastName,firstName,mi,address,"
                    + "city,state,telephone,email) VALUES('"
                    +idField.getText().trim()+"', '"
                    + lnField.getText().trim() +"', '"
                    + fnField.getText().trim() +"', '"
                    + miField.getText().trim() +"', '"
                    + addyField.getText().trim() +"', '"
                    + cityField.getText().trim() +"', '"
                    + stateField.getText().trim() +"', '"
                    + teleField.getText().trim() +"', '"
                    + emailField.getText().trim() +"');";
            try{
                //pointless to allow empty id
                if (!idField.getText().equals("")){
                    stmt.executeUpdate(queryString);  
                    status.setText("Record Inserted");
                }else{
                    status.setText("Empty ID");
                }
            }catch(SQLException ex){
                status.setText("Failed to Insert");
                System.out.println("Failed to Insert\n"+ex);
            }
        });
        //Clicking update
        updateBT.setOnAction(e -> {
            String queryString="UPDATE Staff SET"
                    + " lastName = '"+lnField.getText().trim()+"',"
                    + "firstName = '"+fnField.getText().trim()+"',"
                    + "mi = '"+miField.getText().trim()+"',"
                    + "address = '"+addyField.getText().trim()+"',"
                    + "city = '"+cityField.getText().trim()+"',"
                    + "state = '"+stateField.getText().trim()+"',"
                    + "telephone = '"+teleField.getText().trim()+"', "
                    + "email = '"+emailField.getText().trim()+"' "
                    +"WHERE id= '"+idField.getText().trim()+"';";
            try{
                //pointless to allow empty id
                if (!idField.getText().equals("")){
                    stmt.executeUpdate(queryString);
                    status.setText("Update Successful");
                }else{
                    status.setText("Empty ID");
                }
            }catch(SQLException ex){
                status.setText("Update Failed");
                System.out.println("Update Failed\n"+ex);
            }
        });
        //Clicking clear
        clearBT.setOnAction(e -> {
            idField.clear();
            lnField.clear();
            fnField.clear();
            miField.clear();
            addyField.clear();
            cityField.clear();
            stateField.clear();
            teleField.clear();
            emailField.clear();
            status.setText("Fields Cleared");
        });

        Scene scene = new Scene(bPane,500, 225);
        primaryStage.setTitle("DataBaseProject"); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    }
    //initialize the database
    private void initializeDB() {
        try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          System.out.println("Driver loaded");
          Connection connection = DriverManager.getConnection
            ("jdbc:mysql://localhost:3306/staffDB", "root", "password");
          System.out.println("Database connected");
          status.setText("Database Connected");
          // Create a statement
          stmt = connection.createStatement();
        }
        catch (Exception ex) {
          status.setText("Unable to connect to database");
          System.out.println("Unable to connect to database");
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
