package frame;

import javax.swing.*;
import java.awt.*;
//importing necessary libraries

import static javafx.application.Application.launch;

public class CreatorsFrame extends JFrame{
    public CreatorsFrame(){
        super("Developers' page");//frame with title Developer's page
        this.setLayout((LayoutManager)null);//center allignment
        this.setSize(1280,800);//size of frame
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setVisible(true);//visible
    }
    public static void main(String[] args) {
        launch();
    }//launching this frame

