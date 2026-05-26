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
    private Menu menuFile;
    @FXML
    private Menu menuView;
    @FXML
    private Menu menuHelp;
    @FXML
    private AnchorPane editorContPane;
    private EditorController editor;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileChooser.setTitle("Open file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Markdown", "*.md"));
        
        try {
            editorSet();
        } catch (IOException ex) {
            System.out.print("shite");
            System.getLogger(MainWinController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        menuFileOpen.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = fileChooser.showOpenDialog(App.scene.getWindow());
                App.filename = file.getAbsolutePath();
                try {
                    if(FileRW.checkIfFileCanOpen(App.filename)){
                        FileRW.readFile(App.filename);
                        menuEdit.setDisable(false);
                        changeRecents();
                        editor.loadFileConts();
                    } else {
                        App.errorAlert(Strings.FILE_OPEN_ERROR.text);
                    }
                } catch (IOException ex) {
                    App.errorAlert(Strings.FILE_LOAD_ERROR.text);
                    System.getLogger(MainWinController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        }));

        menuFileClose.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                App.saveCheck();
                if (App.saved) {
                    changeRecents();
                    menuEdit.setDisable(true);
                    editor.clearEditor();
                }
            }
        }));

        menuFileSave.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FileRW.save(App.data, App.filename);
                } catch (IOException ex) {
                    App.errorAlert(Strings.FILE_SAVE_ERROR.text);
                    System.getLogger(MainWinController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
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
                App.saveCheck();
                if (App.saved) {
                    fileChooser.setTitle("New file...");
                    saveInNewFile();
                    editor.clearEditor();
                }
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
                try {
                    showAbout();
                } catch (IOException ex) {
                    System.getLogger(MainWinController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        }));
    }

    /**
     * Creates and opens window showing information about program
     *
     * @throws IOException from App.loadFXML(string)
     */
    private void showAbout() throws IOException {
        Stage aboutWindow = new Stage();
        aboutWindow.setTitle("onMark • About");
        Scene aboutScene = new Scene(App.loadFXML("About.fxml"));
        aboutWindow.setScene(aboutScene);
        aboutWindow.show();
    }

    private void changeRecents() {
        ((Stage) App.scene.getWindow()).setTitle("onMark - " + App.filename);
        if (App.recent[0] != null) {
            System.arraycopy(App.recent, 0, App.recent, 1, 5);
        }
        App.recent[0] = App.filename;
    }

    private void saveInNewFile() {
        fileChooser.setInitialFileName(App.filename);
        File file = fileChooser.showSaveDialog(App.scene.getWindow());
        if (file.getAbsolutePath() != null) {
            try {
                FileRW.save(App.data, file.getAbsolutePath());
                App.filename = file.getAbsolutePath();
                changeRecents();
            } catch (IOException ex) {
                App.errorAlert(Strings.FILE_SAVE_ERROR.text);
                System.getLogger(MainWinController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } else {
            App.errorAlert(Strings.FILE_SAVE_ERROR.text);
        }
    }
    
    public void editorSet() throws IOException{
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Editor.fxml"));
        editorContPane.getChildren().setAll((Node) loader.load());
        editor = (EditorController) loader.getController();
        editor.setupRespSize(editorContPane);
    }    
}