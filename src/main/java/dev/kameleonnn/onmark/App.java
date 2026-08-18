package dev.kameleonnn.onmark;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import static javafx.application.Application.launch;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;

/**
 * JavaFX App
 */
public class App extends Application {
    public static HostServices webhost;
    private static final String FXML = "fxml/MainWin.fxml";
    public static Scene scene;
    public static String filename = "";
    public static String data = "";
    public static boolean saved = true;

    /**
     * Main function
     *
     * @param args arguments form terminal/command line execution.
     */
    public static void main(String[] args) {
        Platform.runLater(() -> {
            scene = new Scene(loadFXML(FXML));
        });
        System.out.print(System.getProperty("os.name"));
        
        launch();
    }

    /**
     * Closes the program upon user request
     */
    public static void close() {
        System.exit(0);
    }

    /**
     * Implementation of start() function from JavaFX. Responsible for starting
     * the GUI
     *
     * @param stage JavaFX Stage class
     */
    @Override
    public void start(Stage stage) {
        webhost = getHostServices();
        stage.setScene(scene);
        stage.setTitle("onMark");
        stage.show();
        stage.setOnCloseRequest((WindowEvent event) -> {
            saveCheck();
            if (saved) {
                Platform.exit();
            }
        });
    }

    /**
     * Loads contents of main window from .fxml file
     *
     * @param fxml URL to .fxml file
     * @return loaded JavaFX scene on success
     */
    public static Parent loadFXML(String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        try {
            return fxmlLoader.load();
        } catch (IOException ex) {
            System.getLogger(App.class.getName()).log(System.Logger.Level.ERROR, Strings.PROGRAM_FATAL_LOAD_ERROR.text, ex);
            App.close();
            return null;
        }
    }

    /**
     * checks if current file is saved, prompts to save if not
     */
    public static void saveCheck() {
        if (!saved) {
            Alert savePrompt = new Alert(Alert.AlertType.CONFIRMATION);
            savePrompt.setContentText(Strings.FILE_EXIT_SAVE_PROMPT.text);
            savePrompt.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            Optional<ButtonType> option = savePrompt.showAndWait();
            if (option.get() == ButtonType.YES) {
                if (FileRW.save(App.data, App.filename)) {
                    App.saved = true;
                    FileRW.closeFile();
                } else {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setContentText(Strings.FILE_SAVE_ERROR.text);
                    error.show();
                    saved = false;
                }
            }
            if (option.get() == ButtonType.NO) {
                FileRW.closeFile();
                saved = true;
            }
            if (option.get() == ButtonType.CANCEL) {
                saved = false;
            }
        }
    }

    /**
     * global method for GUI error messages
     *
     * @param msg String
     */
    public static void errorAlert(String msg) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setContentText(msg);
        error.show();
    }

    public static void setWindowTitle(String title) {
        ((Stage) scene.getWindow()).setTitle("onMark" + title);
    }
    
    public static void openLink(String url){
        if (webhost!=null){
            webhost.showDocument(url);
        }
    }
}
