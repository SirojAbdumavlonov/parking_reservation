package entity;
//importing necessary libraries
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Users {
    //declaring necessary datafields
    int id, carnum;
    String slotnum, arrivaltime, departuretime;
    final StringProperty first = new SimpleStringProperty();
    final StringProperty second = new SimpleStringProperty();


    public Users(String slotnum, String firstname, String secondname, int carnum, String arrivaltime, String departuretime, int id){
        //constructor with declared parameters
        first.set(firstname);
        second.set(secondname);
        this.slotnum = slotnum;
        this.id = id;
        this.arrivaltime = arrivaltime;
        this.departuretime = departuretime;
        this.carnum = carnum;
    }
    public final StringProperty firstnameProperty() {
        return first;
    }
    public final StringProperty secondnameProperty(){
        return second;
    }
    public void setCarnum(int carnum) {
        this.carnum = carnum;
    }

    public void setArrivaltime(String arrivaltime) {//setter method
        this.arrivaltime = arrivaltime;
    }

    public void setDeparturetime(String departuretime) {//setter method
        this.departuretime = departuretime;
    }

    public void setId(int id) {
        this.id = id;
    }//setter method

    public final void setName(String firstname) {//setter method

        first.set(firstname);
    }

    public void setSlotnum(String slotnum) {//setter method
        this.slotnum = slotnum;
    }

    public final void setSurname(String secondname) {//setter method
        second.set(secondname);
    }
    public int getCarnum() {//getter method
        return carnum;
    }

    public int getId() {//getter method
        return id;
    }

    public String getArrivaltime() {//getter method
        return arrivaltime;
    }

    public String getDeparturetime() {//getter method
        return departuretime;
    }

    public final String getName() {//getter method
        return first.get();
    }

    public String getSlotnum() {//getter method
        return slotnum;
    }

    public final String getSurname() {//getter method
        return second.get();
    }
}
