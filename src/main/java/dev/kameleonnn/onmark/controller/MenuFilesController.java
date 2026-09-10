package dev.kameleonnn.onmark.controller;

import dev.kameleonnn.onmark.App;
import dev.kameleonnn.onmark.AppState;
import dev.kameleonnn.onmark.util.FileRW;
import dev.kameleonnn.onmark.util.Strings;
import dev.kameleonnn.onmark.config.Recents;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import dev.kameleonnn.onmark.util.UtilsUI;
import javafx.application.Platform;

/**
 * FXML Controller class
 *
 * @author kameleonnn
 */
public class MenuFilesController implements Initializable {

    private final FileChooser fileChooser = new FileChooser();
    @FXML
    private Menu menuFileOpenRecent;
    @FXML
    private MenuItem menuRecentClear;
    @FXML
    private MenuItem menuFileSaveAs;
    @FXML
    private MenuItem menuFileClose;
    @FXML
    private MenuItem menuFileOpen;
    @FXML
    public MenuItem menuFileSave;
    @FXML
    private MenuItem menuAppClose;
    @FXML
    private MenuItem menuFileNew;
    public MainWinController parent;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (String recent : Recents.getRecents()) {
            addToRecents(recent);
        }

        fileChooser.setTitle("Open file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Markdown (*.md)", "*.md"),
                new FileChooser.ExtensionFilter("Text (*.txt)", "*.txt"),
                new FileChooser.ExtensionFilter("All types", "*")
        );

        EventHandler<ActionEvent> handler = (ActionEvent event) -> {
            switch (((MenuItem) event.getSource()).getId()) {
                case "menuFileSave" ->
                    saveFile();
                case "menuFileNew" ->
                    newFile();
                case "menuFileOpen" ->
                    chooseFile();
                case "buttonFileOpen" ->
                    chooseFile();
                case "menuFileClose" ->
                    closeFile();
                case "menuFileSaveAs" -> {
                    saveInNewFile();
                }
                case "menuRecentClear" -> {
                    clearRecents();
                }
                case "menuAppClose" -> {
                    closeFile();
                    App.close();
                }
                default -> {
                    break;
                }
            }
        };

        menuFileSaveAs.setOnAction(handler);
        menuFileClose.setOnAction(handler);
        menuAppClose.setOnAction(handler);
        menuFileSave.setOnAction(handler);
        menuFileNew.setOnAction(handler);
        menuFileOpen.setOnAction(handler);
        menuRecentClear.setOnAction(handler);

    }

    private void addToRecents(String filename) {
        MenuItem newItem = new MenuItem();
        newItem.setText(filename.strip());
        newItem.getProperties().put("isRecent", true);
        newItem.setOnAction((ActionEvent e) -> {
            openFile(filename);
        });
        menuFileOpenRecent.getItems().addFirst(newItem);
    }

    private void removeRecent(String filename) {
        int index = Recents.getRecents().indexOf(filename);
        if (index != -1) {
            menuFileOpenRecent.getItems().remove(index);
            Recents.removeItem(index);
        }
    }

    private void clearRecents() {
        Platform.runLater(() -> {
            menuFileOpenRecent.getItems().removeIf(item -> Boolean.TRUE.equals(item.getProperties().get("isRecent")));
            Recents.getRecents().clear();
        });

    }

    public void closeFile() {
        UtilsUI.saveCheck();
        if (AppState.isSaved()) {
            Recents.newItem(AppState.getFilename());
            addToRecents(AppState.getFilename());
            FileRW.closeFile();
            parent.plainEditorController.clearEditor();
            UtilsUI.setWindowTitle(parent.getRoot(), "");
        }
    }
    
    /**
     * opens file and prepares program for use
     */
    private void openFile(String filename) {
        if ((!AppState.getFilename().equals(filename)
                && !AppState.getFilename().equals(""))
                && Recents.newItem(AppState.getFilename())) {
            addToRecents(AppState.getFilename());
        }
        AppState.setFilename(filename);
        if (FileRW.checkIfFileCanOpen(filename)) {
            AppState.setData(FileRW.readFile(filename));
            if (AppState.getData() != null) {
                AppState.setSaved(true);
                UtilsUI.setWindowTitle(parent.getRoot(), " - " + filename);
                removeRecent(AppState.getFilename());
                parent.plainEditorController.loadFileConts();
            } else {
                UtilsUI.errorAlert(Strings.FILE_LOAD_ERROR.text);
            }
        } else {
            UtilsUI.errorAlert(Strings.FILE_OPEN_ERROR.text);
        }
    }

    
    public void chooseFile() {
        fileChooser.setTitle("Open file");
        File file = fileChooser.showOpenDialog(parent.getRoot().getScene().getWindow());
        if (file != null) {
            openFile(file.getAbsolutePath());
        }
    }

    /**
     * Saves loaded file
     */
    public void saveFile() {
        if (AppState.getFilename().equals("")) {
            fileChooser.setTitle("New file...");
            saveInNewFile();
        } else {
            if (!FileRW.save(parent.plainEditorController.passText(), AppState.getFilename())) {
                AppState.setSaved(false);
                UtilsUI.errorAlert(Strings.FILE_SAVE_ERROR.text);
            } else {
                AppState.setData(parent.plainEditorController.passText());
                AppState.setSaved(true);
            }
        }
    }

    /**
     * Creates a new file and prepares the editor for use
     */
    public void newFile() {
        UtilsUI.saveCheck();
        if (AppState.isSaved()) {
            parent.plainEditorController.clearEditor();
            FileRW.closeFile();
            UtilsUI.setWindowTitle(parent.getRoot(), " - New file");
        }
    }

    private void saveInNewFile() {
        fileChooser.setTitle("Save as...");
        fileChooser.setInitialFileName(AppState.getFilename());
        File file = fileChooser.showSaveDialog(parent.getRoot().getScene().getWindow());
        AppState.setFilename(file.getAbsolutePath() + getExtension());
        AppState.setData(parent.plainEditorController.passText());
        if (FileRW.save(AppState.getData(), AppState.getFilename())) {
            AppState.setSaved(true);
            UtilsUI.setWindowTitle(parent.getRoot(), " - " + AppState.getFilename());
        } else {
            AppState.setSaved(false);
            UtilsUI.errorAlert(Strings.FILE_SAVE_ERROR.text);
        }
    }

    private String getExtension() {
        String ext = fileChooser.getSelectedExtensionFilter().getDescription();
        ext = switch (ext) {
            case "Markdown (*.md)" ->
                ".md";
            case "Text (*.txt)" ->
                ".txt";
            default ->
                "";
        };
        return ext;
    }
}
