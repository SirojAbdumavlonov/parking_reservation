package controller;

//importing all necessary libraries

import runningfile.MainRunner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class MainMenuController {

    // importing components from fxml file
    public AnchorPane firstPane;
    @FXML
    private Button rstButton, start111,OrdersButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button start,SearchB;


    public void OrderPage() throws IOException{//method for opening orders frame
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Orders.fxml"));//loading fxml file

        Stage window = (Stage) OrdersButton.getScene().getWindow();//opening through orders button

        window.setScene(new Scene(fxmlLoader.load(), 1280, 800));//opening scene in set size
    }
    public void exitFunc(){//method for exiting program
        Stage stage = (Stage) start111.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    public void STARTB() throws IOException{//method for staring a program clicking button start
        FXMLLoader fxmlLoader = new FXMLLoader(MainRunner.class.getResource("Registration.fxml"));//loading fxml file

        Stage window = (Stage) start.getScene().getWindow();//button used

        window.setScene(new Scene(fxmlLoader.load(),1280, 800));//opening scene in set size
    }
    public void exitButton1() throws IOException {//method for opening main menu
        FXMLLoader fxmlLoader = new FXMLLoader(MainRunner.class.getResource("MainMenu.fxml"));//loading fxml file

        Stage window = (Stage) exitButton.getScene().getWindow();//opening through button exit

        window.setScene(new Scene(fxmlLoader.load(),1280, 800));//opening scene in set size
    }
    public void buttonHon() throws IOException {//method for opening creators frame
        FXMLLoader fxmlLoader = new FXMLLoader(MainRunner.class.getResource("CreatorsFrame.fxml"));//loading fxml file

        Stage window = (Stage) rstButton.getScene().getWindow();//opening through button rst
        window.setScene(new Scene(fxmlLoader.load(),1280, 800));//opening scene in set size
    }
    public void SearchF() throws IOException {//method for opening Search frame
        FXMLLoader fxmlLoader = new FXMLLoader(MainRunner.class.getResource("Search.fxml"));//loading fxml file

        Stage window = (Stage) SearchB.getScene().getWindow();//opening through button search
        window.setScene(new Scene(fxmlLoader.load(),1280, 800));//opening scene in set size
    }
}
