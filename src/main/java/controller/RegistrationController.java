package controller;

//importing all necessary libraries

import runningfile.MainRunner;
import service.Ticket;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class RegistrationController{//extending interface Initializable to class
    //declaring conditions for variables
    boolean firstName = false;
    boolean secondName = false;
    boolean slotNumber = false;
    boolean carNumber = false;
    boolean arrivalTime = false;
    boolean departureTime = false;

    TimeUnit time = TimeUnit.MILLISECONDS;
    @FXML//import component from its fxml file
    private Label OUTPUT, OUTPUT3;
    @FXML
    private Label OUTPUT1,OUTPUT221;
    @FXML
    private Label OUTPUT2,OUTPUT22;
    @FXML
    private Label OUTPUT21;
    @FXML
    private Label OUTPUT222;
    @FXML
    private Button SUBMIT;
    @FXML
    private Button exit1,CHECK;
    @FXML
    private TextField firstname, secondname, carnum, arrTime, depTime, slotNum;


    Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/parking", "root", "Siroj12@");
    //connect with database

    public RegistrationController() throws SQLException {
        //handling sqlexceptions occured in class
    }

    public void EXIT1() throws IOException {//method to switch the scene
        FXMLLoader fxmlLoader = new FXMLLoader(MainRunner.class.getResource("MainMenu.fxml"));//loading fxml file

        Stage window = (Stage) exit1.getScene().getWindow();//opening using exit1 button

        window.setScene(new Scene(fxmlLoader.load(), 1280, 800));//opening scene in set size


    }
    public boolean IFnumber(String time1, String time2){//method which checks time and its correct vies

        String[] entries1 = time1.split(":");
        String[] entries2 = time2.split(":");
        //divide string into 2 parts
        if(entries1.length != entries2.length){//method which checks their format
            OUTPUT222.setText("INCORRECT FORMAT OF\n, TIME");
            return false;
        }

        int hour1,hour2;
        int minute1,minute2;
        //declaring variables to check their correct

        hour1 = Integer.parseInt(entries1[0]);
        hour2 = Integer.parseInt(entries2[0]);
        //getting hours of input time
        minute1 = Integer.parseInt(entries1[1]);
        minute2 = Integer.parseInt(entries2[1]);
        //getting minutes of input time

        if(hour1 > 23 || hour1 < 0 || hour2 > 23 || hour2 < 0 || minute1 > 59 || minute1 < 0 || minute2 > 59 || minute2 < 0){
            //method which checks the range of time
            OUTPUT222.setText("INCORRECT FORMAT OF\n TIME");
            return false;
        }

        //condition which checks input two times
        if(hour1 == hour2) {
            if (minute1 > minute2) {
                OUTPUT222.setText("INCORRECT FORMAT OF\n TIME");
                return false;
            }
        }
        else if(hour1 > hour2){
                OUTPUT222.setText("INCORRECT FORMAT OF\n TIME");
                return false;
            }

        OUTPUT222.setText("");
        return true;
        //return true if everything is correct
    }

    public void CHECK() throws SQLException, InterruptedException {
        //method which checks all text input by user

        String slotNumber = slotNum.getText();//getting data from text
        String firstN = firstname.getText();//getting data from text
        String secondN = secondname.getText();//getting data from text
        String carN = carnum.getText();//getting data from text
        int carNUM = Integer.parseInt(carN);//converting String to Int
        String arrTIme = arrTime.getText();//getting data from text
        String depTIme = depTime.getText();//getting data from text

        int slotnum = Integer.parseInt(slotNumber);//converting String to Int

        if(slotnum > 32 || slotnum < 0){//checking range of parking slots between 1-44
                OUTPUT2.setText("INVALID SLOT NUMBER");//putting text to label
                this.slotNumber = false;
        }
        else{
                this.slotNumber = true;
                OUTPUT2.setText("");//clearing label
            }

        if(firstN.length() < 3 || firstN.length() > 20){//checking the min and max length of firstname
                OUTPUT1.setText("INCORRECT LENGTH OF NAME");//putting text to label
                this.firstName = false;
        }
        else{
                this.firstName = true;
                OUTPUT1.setText("");//clearing label
        }

        if(secondN.length() < 3 || secondN.length() > 20){//checking the min and max length of secondname
                OUTPUT.setText("INCORRECT LENGTH OF\n SECOND NAME");//putting text to label
                this.secondName = false;
            }
        else {
                this.secondName = true;
                OUTPUT.setText("");//clearing label
            }
        if(carNUM > 1000 || carNUM < 1){
                OUTPUT21.setText("INCORRECT CAR NUMBER");//putting text to label
                this.carNumber = false;

            }
        else {
                    this.carNumber = true;
                    OUTPUT21.setText("");//clearing label
                }
        this.arrivalTime = IFnumber(arrTIme,depTIme);

        this.departureTime = clientActionWithServer(arrTIme,depTIme,slotNumber);


        //condition which checks if all input texts are input correctly
        //if everything is true button will not be disabled
        boolean isDisabled = !(this.firstName && this.secondName && this.slotNumber && this.carNumber && this.arrivalTime && this.departureTime);
        SUBMIT.setDisable(isDisabled);
    }

        public void SUBMIT () throws IOException, SQLException, InterruptedException {
            //sql statement to inserting to database
            String insert = "insert into info(slotnum,firstname,secondname,carnum,arrivaltime,departuretime) values(?,?,?,?,?,?);";
            PreparedStatement prep = connect.prepareStatement(insert);


            String slotNumber = slotNum.getText();//getting data from text
            String firstN = firstname.getText();//getting data from text
            String secondN = secondname.getText();//getting data from text
            String carN = carnum.getText();//getting data from text
            int carNUM = Integer.parseInt(carN);//converting String to Int
            String arrTIme = arrTime.getText();//getting data from text
            String depTIme = depTime.getText();//getting data from text


                prep.setString(1, slotNumber);//sending data to database
                prep.setString(2, firstN);//sending data to database
                prep.setString(3, secondN);//sending data to database
                prep.setInt(4, carNUM);//sending data to database
                prep.setString(5, arrTIme);//sending data to database
                prep.setString(6, depTIme);//sending data to database

                prep.executeUpdate();//finishing the process



                //
                slotNum.clear();
                firstname.clear();
                secondname.clear();
                carnum.clear();
                arrTime.clear();
                depTime.clear();
                //clearing all textfields for next input
                OUTPUT3.setText("DATA SAVED SUCCESSFULLY!");

                SUBMIT.setDisable(true);//disable button
                CHECK.setDisable(true);//disable button
                time.sleep(500);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ticket.fxml"));//loading fxml file

            Stage window = (Stage) SUBMIT.getScene().getWindow();//opening using submit button

            window.setScene(new Scene(fxmlLoader.load(),400, 300));//opening scene in set size
            window.centerOnScreen();//opening on the center
            new Ticket();//opening class Ticket
            }
                public static int converter(String time){//method which convert time to seconds
                String[] numbers = time.split(":");//divide to hours and minutes
                int res;
                int hour = Integer.parseInt(numbers[0]);
                int min = Integer.parseInt(numbers[1]);
                res = (hour * 3600) + (min * 60);//calculating
                return res;
            }
            public boolean clientActionWithServer(String time1, String time2, String slotnum) throws SQLException, InterruptedException {
                //this method get both times and check with other times from DB
                connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/parking", "root", "Siroj12@");
                String selecting1 = "select arrivaltime, departuretime from info where slotnum = " + slotnum;
                //statements for sql connection
                Statement state = connect.createStatement();
                ResultSet selecting = state.executeQuery(selecting1);

                ArrayList<String> ARRIV = new ArrayList<>();
                ArrayList<String> DEPAR = new ArrayList<>();
                //arrays for inputting occured collisions of time
                time.sleep(800);
                OUTPUT3.setText("CHECKING AVAILABILITY...");

                int numTime1 = converter(time1);//converting to seconds
                int numTime2 = converter(time2);//converting to seconds

                while (selecting.next()) {//getting data from DB
                    String arriv1 = selecting.getString("arrivaltime");
                    String depar1 = selecting.getString("departuretime");
                    int arriv1Time = converter(arriv1);
                    int depar1Time = converter(depar1);
                    if ((arriv1Time > numTime1 && arriv1Time < numTime2) || (arriv1Time < numTime1 && depar1Time > numTime2)) {
                        //checking if data hava collisions
                        ARRIV.add(arriv1);
                        DEPAR.add(depar1);
                        //adding to array
                    }
                }
                OUTPUT3.setText("CHECKED...");
                if(ARRIV.size() > 0){//checks length of array
                    OUTPUT22.setText("IT IS BUSY AMONG\n");
                    for(int i = 0;i < ARRIV.size();i++){//displaying occured collisions of time
                        OUTPUT221.setText(ARRIV.get(i) + " - " + DEPAR.get(i));
                    }
                    return false;
                }
                else{
                    return true;
                }
            }


}


