package dev.kameleonnn.onmark.controller;

import dev.kameleonnn.onmark.Files;
import dev.kameleonnn.onmark.App;
import dev.kameleonnn.onmark.Strings;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Menu;

import javafx.scene.control.MenuItem;
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
    private MenuItem menuFileClose;
    private MenuItem menuFileOpen;
    private MenuItem menuFileSave;
    private MenuItem menuAppClose;
    private MenuItem menuHelpAbout;
    private MenuItem menuFileNew;
    private Menu menuEdit;
    private Menu menuFileOpenRecent;
    private final FileChooser fileChooser = new FileChooser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileChooser.setTitle("Open file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Markdown", "*.md"));

        menuFileOpen.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = fileChooser.showOpenDialog(App.scene.getWindow());
                App.filename = file.getAbsolutePath();
                try {
                    Files.readFile(App.filename);
                    menuEdit.setDisable(false);
                    changeRecents();
                    // TODO
                    //App.data wysłać do edytora - osobny kontroler
                } catch (IOException ex) {
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
                }
                // TODO
                // clear editor
            }
        }));

        menuFileSave.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Files.save(App.data, App.filename);
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
                    // TODO
                    //clear editor
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
                Files.save(App.data, file.getAbsolutePath());
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
}