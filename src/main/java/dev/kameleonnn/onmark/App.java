package dev.kameleonnn.onmark;

import dev.kameleonnn.onmark.config.Config;
import dev.kameleonnn.onmark.config.Recents;
import dev.kameleonnn.onmark.util.Strings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.application.HostServices;
import dev.kameleonnn.onmark.util.UtilsUI;

/**
 * JavaFX App
 */
public class App extends Application {
    public static HostServices webhost;

    /**
     * Main function
     *
     * @param args arguments form terminal/command line execution.
     */
    public static void main(String[] args) {
        Config.configInit();
        Recents.recentsInit();
        launch();
    }

    /**
     * Closes the program upon user request
     */
    public static void close() {
        Recents.writeRecents();
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
        Scene scene = new Scene(loadFXML("fxml/MainWin.fxml"));
        stage.setScene(scene);
        stage.setTitle("onMark");
        stage.show();
        stage.setOnCloseRequest((event) -> {
            if (!UtilsUI.saveCheck()) {
                event.consume();
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

}
