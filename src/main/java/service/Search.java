package service;
//import all necessary libraries
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import runningfile.MainRunner;


public class Search {

    //importing components from fxml file
    @FXML
    Label ID,FIRSTNAME,SECONDNAME,SLOTNUMBER,CARNUMBER,ARRIVALTIME,DEPARTURETIME,out;
    @FXML private TextField Sname, Ssecondname, Scarnumber;
    @FXML private Button exit;


    public void exit() throws IOException {//method for opening frame
        FXMLLoader fxmlLoader = new FXMLLoader(MainRunner.class.getResource("MainMenu.fxml"));//loading fxml file

        Stage window = (Stage) exit.getScene().getWindow();//opening through button search
        window.centerOnScreen();//opening frame on the center
        window.setScene(new Scene(fxmlLoader.load(),1280, 800));//opening scene in set size
        window.centerOnScreen();
    }
    public void LookFor() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/parking", "root", "Siroj12@");

        String Snam = Sname.getText();//getting data from textfield
        String Ssecond = Ssecondname.getText();//getting data from textfield
        String Scarn = Scarnumber.getText();//getting data from textfield
        int carN = Integer.parseInt(Scarn);

        PreparedStatement prep = conn.prepareStatement("select * from info WHERE firstname = '"+Snam +"' AND secondname ='" + Ssecond +"' AND carnum = '" +Scarn +"';");
        ResultSet res = prep.executeQuery();
        //statement to connect sql db
        int count = 0;
        while(res.next()){

            String slotnum = res.getString("slotnum");//getting data from DB
            String name = res.getString("firstname");//getting data from DB
            String second = res.getString("secondname");//getting data from DB
            int carn = res.getInt("carnum");//getting data from DB
            String C = String.valueOf(carn);//converting Int to String
            String arriv = res.getString("arrivaltime");//getting data from DB
            String dep = res.getString("departuretime");//getting data from DB
            int id = res.getInt("id");//getting data from DB
            String Id = String.valueOf(id);//converting Int to String

            SLOTNUMBER.setText(slotnum);//putting data to label
            FIRSTNAME.setText(name);//putting data to label
            SECONDNAME.setText(second);//putting data to label
            CARNUMBER.setText(C);//putting data to label
            ARRIVALTIME.setText(arriv);//putting data to label
            DEPARTURETIME.setText(dep);//putting data to label
            ID.setText(Id);//putting data to label
            out.setText("");//putting data to label
            count++;//quantity of found data
        }
        if(count == 0){
            out.setText("NOT FOUND!");
            SLOTNUMBER.setText("");//clearing label
            FIRSTNAME.setText("");//clearing label
            SECONDNAME.setText("");//clearing label
            CARNUMBER.setText("");//clearing label
            ARRIVALTIME.setText("");//clearing label
            DEPARTURETIME.setText("");//clearing label
            ID.setText("");//clearing label
        }
    }
}
