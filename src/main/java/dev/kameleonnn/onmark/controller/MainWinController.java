package dev.kameleonnn.onmark.controller;

import dev.kameleonnn.onmark.FileRW;
import dev.kameleonnn.onmark.App;
import dev.kameleonnn.onmark.Strings;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kameleonnn
 */
public class MainWinController implements Initializable {

    @FXML
    private MenuItem menuFileSaveAs;
    @FXML
    private MenuItem menuFileClose;
    @FXML
    private MenuItem menuFileOpen;
    @FXML
    private MenuItem menuFileSave;
    @FXML
    private MenuItem menuAppClose;
    @FXML
    private MenuItem menuHelpAbout;
    @FXML
    private MenuItem menuFileNew;
    @FXML
    private Menu menuEdit;
    @FXML
    private Menu menuFileOpenRecent;
    private final FileChooser fileChooser = new FileChooser();
    @FXML
    private Menu menuView;
    @FXML
    private Menu menuHelp;
    @FXML
    private AnchorPane editorContPane;
    private EditorController editor;
    private Stage aboutWindow;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileChooser.setTitle("Open file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Markdown", "*.md"));
        editorSet();

        menuFileOpen.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openFile();
            }
        }));

        menuFileClose.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                App.saveCheck();
                if (App.saved) {
                    App.changeRecents();
                    menuEdit.setDisable(true);
                    editor.clearEditor();
                }
            }
        }));

        menuFileSave.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveFile();
            }
        }));

        menuFileSaveAs.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileChooser.setTitle("Save as...");
                saveInNewFile();
            }

        }));

        menuFileNew.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newFile();
            }
        }));

        menuAppClose.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                App.saveCheck();
                if (App.saved) {
                    App.close();
                }
            }
        }));

        menuHelpAbout.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                aboutWindow = new Stage();
                aboutWindow.setTitle("onMark • About");
                aboutWindow.setScene(new Scene(App.loadFXML("fxml/About.fxml")));
                aboutWindow.show();
            }
        }));
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
                    menuEdit.setDisable(false);
                    ((Stage) App.scene.getWindow()).setTitle("onMark - " + App.filename);
                    App.changeRecents();
                    editor.loadFileConts();
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
            }
        }
    }

    /**
     * Creates a new file and prepares the editor for use
     */
    public void newFile() {
        App.saveCheck();
        if (App.saved) {
            editor.clearEditor();
            FileRW.closeFile();
            ((Stage) App.scene.getWindow()).setTitle("onMark - New file");
        }
    }

    private void saveInNewFile() {
        fileChooser.setInitialFileName(App.filename);
        File file = fileChooser.showSaveDialog(App.scene.getWindow());
        if ((file != null) && (FileRW.save(App.data, file.getAbsolutePath()))) {
            App.filename = file.getAbsolutePath();
            ((Stage) App.scene.getWindow()).setTitle("onMark - " + App.filename);
            App.changeRecents();
        } else {
            App.errorAlert(Strings.FILE_SAVE_ERROR.text);
        }
    }

    /**
     * Creates and sets up the editor component of the program
     */
    public void editorSet() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Editor.fxml"));
            editorContPane.getChildren().setAll((Node) loader.load());
            editor = (EditorController) loader.getController();
            editor.setupRespSize(editorContPane);
            editor.getMain(this);
        } catch (IOException ex) {
            System.getLogger(MainWinController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            App.errorAlert(Strings.PROGRAM_FATAL_LOAD_ERROR.text);
            App.close();
        }
    }
}
