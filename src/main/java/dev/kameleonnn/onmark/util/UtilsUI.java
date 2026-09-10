package dev.kameleonnn.onmark.util;

import static dev.kameleonnn.onmark.App.webhost;
import dev.kameleonnn.onmark.AppState;
import java.util.Optional;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 *
 * @author kameleonnn
 */
public class UtilsUI {
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
    
    public static void openLink(String url){
        if (webhost!=null){
            webhost.showDocument(url);
        }
    }

    /**
     * checks if current file is saved, prompts to save if not
     */
    public static boolean saveCheck() {
        if (AppState.isSaved()) {
            return true;
        } else {
            Alert savePrompt = new Alert(Alert.AlertType.CONFIRMATION);
            savePrompt.setContentText(Strings.FILE_EXIT_SAVE_PROMPT.text);
            savePrompt.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            Optional<ButtonType> option = savePrompt.showAndWait();
            if (option.get() == ButtonType.YES) {
                if (FileRW.save(AppState.getData(), AppState.getFilename())) {
                    AppState.setSaved(true);
                    FileRW.closeFile();
                    return true;
                } else {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setContentText(Strings.FILE_SAVE_ERROR.text);
                    error.show();
                    AppState.setSaved(false);
                    return false;
                }
            }
            if (option.get() == ButtonType.NO) {
                FileRW.closeFile();
                AppState.setSaved(true);
                return true;
            }
            if (option.get() == ButtonType.CANCEL) {
                AppState.setSaved(false);
                return false;
            }
            return false;
        }
    }

    public static void setWindowTitle(Node node, String title) {
        ((Stage) node.getScene().getWindow()).setTitle("onMark" + title); 
    }
}
