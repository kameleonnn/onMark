package dev.kameleonnn.onmark;

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
    static String fxml = "MainWin.fxml";
    public static Scene scene;
    public static String filename = "";
    public static String data = "";
    public static boolean saved = true;
    public static String[] recent = new String[6];

    /**
     * Main function
     * @param args arguments form terminal/command line execution.
     * @throws IOException from function loadFXML()
     */
    public static void main(String[] args) throws IOException {
        scene = new Scene(loadFXML(fxml));
        launch();
    }

    /**
     * Closes the program upon user request
     */
    public static void close() {
        System.exit(0);
    }

    /**
     * Implementation of start() function from JavaFX. Responsible for starting the GUI
     * @param stage JavaFX Stage class
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        setRoot(fxml);
        stage.setScene(scene);
        stage.setTitle("onMark");
        stage.show();
        stage.setOnCloseRequest((WindowEvent event) -> {
            saveCheck();
            if (saved) { Platform.exit(); }
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

    public static void saveCheck() {
        if (!saved) {
            Alert savePrompt = new Alert(Alert.AlertType.CONFIRMATION);
            savePrompt.setContentText(Strings.FILE_EXIT_SAVE_PROMPT.text);
            savePrompt.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            Optional<ButtonType> option = savePrompt.showAndWait();
            if (option.get() == ButtonType.YES) {
                try {
                    Files.save(App.data, App.filename);
                    Files.closeFile();

                } catch (IOException ex) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setContentText(Strings.FILE_SAVE_ERROR.text);
                    error.show();
                    saved = false;
                }
            }
            if (option.get() == ButtonType.NO) {
                Files.closeFile();
                saved = true;
            }
            if(option.get()==ButtonType.CANCEL){ saved = false;}
        }
    }
    
    public static void errorAlert(String msg){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setContentText(msg);
        error.show();
    }
}
