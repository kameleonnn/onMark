package dev.kameleonnn.onmark;

import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.IO.print;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;

/**
 * JavaFX App
 */
public class App extends Application {
    private static final Logger LOGGER=Logger.getLogger(App.class.getName());
    static Scanner input = new Scanner(System.in);
    static String fxml = "MainWin.fxml";
    private static Scene scene;
    public static String data = "";


    /**
     * Main function
     * @param args arguments form terminal/command line execution.
     * @throws IOException from function loadFXML()
     */

    public static void main(String[] args) throws IOException{
        scene = new Scene(loadFXML(fxml), 600, 750);
        launch();
    }
    /**
     * Closes the program upon user request
     */
    public static void close(){
        System.exit(0);
    }
    
    /**
     * Implementtion of start() function from JavaFX. 
     * Responsible for starting the GUI
     * @param stage JavaFX Stage class
     * @throws IOException from setRoot(string)
     * @throws IOException from initDatabase()
     */
    @Override
    public void start(Stage stage) throws IOException {
        setRoot(fxml);
        stage.setScene(scene);
        stage.setTitle("onMark");
        stage.show();
        
        stage.setOnCloseRequest((WindowEvent event) -> {
	    if(FileOp.getInstance().isSaved()==false){
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(Strings.FILE_EXIT_SAVE_PROMPT.text);
                Optional<ButtonType> option = alert.showAndWait();
                if(option.get()==ButtonType.YES){
                    try {
                        FileOp.getInstance().save(data);
                    } catch (IOException ex) {
                        Alert error = new Alert(Alert.AlertType.ERROR);
                        error.setContentText(Strings.FILE_SAVE_ERROR.text);
                        error.show();
                    }
                }
                if(option.get()==ButtonType.NO){
                    Platform.exit();
                }
	    }
	});
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    /**
     * Loads contents of main window from .fxml file
     * @param fxml URL to .fxml file
     * @return loaded JavaFX scene
     * @throws IOException from fxmlLoader.load()
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        return fxmlLoader.load();
    }

}