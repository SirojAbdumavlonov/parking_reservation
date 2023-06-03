package runningfile;//importing all necessary libraries
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
//


public class MainRunner extends Application{
    @Override//override built-in function start
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));//Loading fxml file
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800);//Frame size
        stage.setResizable(false);//cannot change size of frame
        stage.setTitle("Parking!");//frame name
        stage.setScene(scene);
        stage.show();//showing
    }
    public static void main(String[] args) {
        launch(args);
    }//starting program
}
