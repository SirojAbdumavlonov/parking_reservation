package repo;
//import libraries
import entity.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.*;
import java.sql.*;

public class MySQLConnection {

    Connection conn = null;//declaring

    public static Connection ConnectDB(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/parking", "root", "Siroj12@");
            //db connection
            JOptionPane.showMessageDialog(null,"Connection established");
            //message that db is connected
            return conn;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;

        }
    }
    public static ObservableList<Users> getDataUsers(){
        Connection conn = ConnectDB();
        ObservableList<Users> list = FXCollections.observableArrayList();
        //arraylist
        try{
            assert conn != null;
            PreparedStatement prep = conn.prepareStatement("select * from info");
            ResultSet res = prep.executeQuery();
            //statement for connecting sql db
            while(res.next()){
                //getting data and add to user
                list.add(new Users(res.getString("slotnum"),res.getString("firstname"),res.getString("secondname"),Integer.parseInt(res.getString("carnum")),res.getString("arrivaltime"), res.getString("departuretime"),Integer.parseInt(res.getString("id")) ));
            }
        }catch(Exception ignored){
        }
return list;
    }
}
