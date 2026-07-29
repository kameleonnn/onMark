package dev.kameleonnn.onmark.controller;

import dev.kameleonnn.onmark.App;
import dev.kameleonnn.onmark.FileRW;
import dev.kameleonnn.onmark.Strings;
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

/**
 * FXML Controller class
 *
 * @author szef
 */
public class MenuFilesController implements Initializable {

    private final FileChooser fileChooser = new FileChooser();
    @FXML
    private Menu menuFileOpenRecent;
    @FXML
    private MenuItem fileRecent1;
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fileChooser.setTitle("Open file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Markdown (*.md)", "*.md"),
                new FileChooser.ExtensionFilter("Text (*.txt)", "*.txt"),
                new FileChooser.ExtensionFilter("All types", "*")
        );

        EventHandler<ActionEvent> handler = event -> {
            switch (((MenuItem) event.getSource()).getId()) {
                case "menuFileSave" ->
                    saveFile();
                case "menuFileNew" ->
                    newFile();
                case "menuFileOpen" ->
                    openFile();
                case "buttonFileOpen" ->
                    openFile();
                case "menuFileClose" ->
                    closeFile();
                case "menuFileSaveAs" -> {
                    fileChooser.setTitle("Save as...");
                    saveInNewFile();
                }
                case "menuAppClose" -> {
                    App.saveCheck();
                    if (App.saved) {
                        App.close();
                    }
                }
                default -> {
                }
            }
        };

        menuFileSaveAs.setOnAction(handler);
        menuFileClose.setOnAction(handler);
        menuAppClose.setOnAction(handler);
        menuFileSave.setOnAction(handler);
        menuFileNew.setOnAction(handler);
        menuFileOpen.setOnAction(handler);

    }

    public void closeFile() {
        App.saveCheck();
        if (App.saved) {
            App.changeRecents();
            parent.setEditingEnabled(true);
            parent.getEditorCtrl().clearEditor();
            App.setWindowTitle("");
        }
    }

    /**
     * opens file and prepares program for use
     */
    public void openFile() {
        File file = fileChooser.showOpenDialog(App.scene.getWindow());
        if (file != null) {
            App.filename = file.getAbsolutePath();
            if (FileRW.checkIfFileCanOpen(App.filename)) {
                if (FileRW.readFile(App.filename)) {
                    parent.setEditingEnabled(false);
                    App.setWindowTitle(" - " + App.filename);
                    App.changeRecents();
                    parent.getEditorCtrl().loadFileConts();
                } else {
                    App.errorAlert(Strings.FILE_LOAD_ERROR.text);
                }
            } else {
                App.errorAlert(Strings.FILE_OPEN_ERROR.text);
            }

        }
    }

    /**
     * Saves loaded file
     */
    public void saveFile() {
        if (App.filename.equals("")) {
            fileChooser.setTitle("New file...");
            saveInNewFile();
        } else {
            if (!FileRW.save(App.data, App.filename)) {
                App.errorAlert(Strings.FILE_SAVE_ERROR.text);
            } else {
                App.data = parent.getEditorCtrl().passText();
            }
        }
    }

    /**
     * Creates a new file and prepares the editor for use
     */
    public void newFile() {
        App.saveCheck();
        if (App.saved) {
            parent.getEditorCtrl().clearEditor();
            FileRW.closeFile();
            App.setWindowTitle(" - New file");
        }
    }

    private void saveInNewFile() {
        fileChooser.setInitialFileName(App.filename);
        File file = fileChooser.showSaveDialog(App.scene.getWindow());
        if ((file != null) && (FileRW.save(App.data, file.getAbsolutePath()))) {
            App.filename = file.getAbsolutePath() + getExtension();
            App.data = parent.getEditorCtrl().passText();
            FileRW.save(App.data, App.filename);
            App.setWindowTitle(" - " + App.filename);
            App.changeRecents();
        } else {
            App.errorAlert(Strings.FILE_SAVE_ERROR.text);
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
