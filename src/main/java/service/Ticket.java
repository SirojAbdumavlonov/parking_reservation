package service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;


public class Ticket {
    //importing components from fxml file
    @FXML
    private Label Tname;
    @FXML
    private Label Tsurname;
    @FXML
    private Label Tcarnum;
    @FXML
    private Label Tslotnum;
    @FXML
    private Label Tarriv;
    @FXML
    private Label Tdepar;
    @FXML
    private Label ID1;
    @FXML
    private Button OKButton1,OKButton;



    public void ok2(ActionEvent actionEvent) throws IOException {//method for opening frame
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));//loading fxml file

        Stage window = (Stage) OKButton.getScene().getWindow();//opening through OKbutton search
        window.centerOnScreen();//opening frame on the center
        window.setScene(new Scene(fxmlLoader.load(), 1280, 800));//opening scene in set size
        window.centerOnScreen();


    }
    public void ok1() throws SQLException {

        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/parking", "root", "Siroj12@");
        Statement state = connect.createStatement();
        //statement to connect sql db

        String get = "select id from info order by id desc limit 1";
        ResultSet res1 = state.executeQuery(get);
        //statement to connect sql db

        int id;
        while(res1.next()) {
            id = res1.getInt("id");

            String getall = "select * from info where id = " + id;
            //statement to connect sql db
            PreparedStatement prep = connect.prepareStatement(getall);
            ResultSet select = prep.executeQuery();
            //statement to connect sql db


            while (select.next()) {
                String slotnum = select.getString("slotnum");//getting data from DB
                String firstname = select.getString("firstname");//getting data from DB
                String secondname = select.getString("secondname");//getting data from DB
                int carnum = select.getInt("carnum");//getting data from DB
                String Carnum = String.valueOf(carnum);//convering Int to String
                String arrivaltime = select.getString("arrivaltime");//getting data from DB
                String departuretime = select.getString("departuretime");//getting data from DB
                int id1 = select.getInt("id");//getting data from DB
                String ID = String.valueOf(id1);//convering Int to String

                Tname.setText(firstname);//putting data to label
                Tsurname.setText(secondname);//putting data to label
                Tslotnum.setText(slotnum);//putting data to label
                Tcarnum.setText(Carnum);//putting data to label
                Tarriv.setText(arrivaltime);//putting data to label
                Tdepar.setText(departuretime);//putting data to label
                ID1.setText(ID);//putting data to label
            }
        }
        connect.close();//close connection with DB
        OKButton1.setDisable(true);
        OKButton.setDisable(false);
    }
}
