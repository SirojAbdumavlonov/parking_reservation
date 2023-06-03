package service;

import entity.Users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import repo.MySQLConnection;

import java.io.IOException;




public class Orders{

    String adminData1 = "notadmin";
    String passwordData = "notadmin";
//importing from fxml file
    @FXML private Button ENTER,ENTER1,back;
    @FXML private TableView<Users> Tablee;
    @FXML private Label adminData;
    @FXML public TableColumn<Users,String> col_name;
    @FXML public TableColumn<Users,String> col_surname;
    @FXML public TableColumn<Users,String> col_slotnum;
    @FXML public TableColumn<Users,String> col_arrivaltime;
    @FXML public TableColumn<Users,String> col_departuretime;
    @FXML public TableColumn<Users,Integer> col_carnum;
    @FXML public TableColumn<Users,Integer> col_d;
    @FXML private TextField ADMIN, PASSWORD;
    @FXML private Button SHOW;
    ObservableList<Users> listM;

    public void backB() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Orders.fxml"));//loading fxml file

        Stage window = (Stage) back.getScene().getWindow();//opening through back button

        window.setScene(new Scene(fxmlLoader.load(), 1280, 800));//opening scene in set size
        window.centerOnScreen();//opening frame on the center
    }
    public void ENTERACT1(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));//loading fxml file

        Stage window = (Stage) ENTER1.getScene().getWindow();//opening through enter1 button

        window.setScene(new Scene(fxmlLoader.load(), 1280, 800));//opening scene in set size
    }
    public void ENTERACT(ActionEvent actionEvent) throws IOException{


        String Admin = ADMIN.getText();//get text from textfield
        String Password = PASSWORD.getText();//get text from textfield

        if((Admin.length() == 0 || Password.length() == 0) || !(Admin.equals(adminData1) && Password.equals(passwordData))){
            //check equality of input password and name
            adminData.setText("Incorrect data!");
            return;
        }
        else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrdersTables.fxml"));//loading fxml file

            Stage window = (Stage) ENTER.getScene().getWindow();//opening through enter button
            window.centerOnScreen();
            window.setScene(new Scene(fxmlLoader.load(), 1280, 800));//opening scene in set size
        }
    }
    public void SHOWD(){

        col_d.setCellValueFactory(new PropertyValueFactory<>("id"));//putting data to tableview
        col_name.setCellValueFactory(new PropertyValueFactory<>("firstname"));//putting data to tableview
        col_surname.setCellValueFactory(new PropertyValueFactory<>("secondname"));//putting data to tableview
        col_carnum.setCellValueFactory(new PropertyValueFactory<>("carnum"));//putting data to tableview
        col_slotnum.setCellValueFactory(new PropertyValueFactory<>("slotnum"));//putting data to tableview
        col_arrivaltime.setCellValueFactory(new PropertyValueFactory<>("arrivaltime"));//putting data to tableview
        col_departuretime.setCellValueFactory(new PropertyValueFactory<>("departuretime"));//putting data to tableview


        listM = MySQLConnection.getDataUsers();
        Tablee.setItems(listM);
        //putting all data to table
    }

}
