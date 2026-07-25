package dev.kameleonnn.onmark.controller;

import dev.kameleonnn.onmark.App;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kameleonnn
 */
public class MainWinController implements Initializable {
    @FXML private Menu menuFiles;
    public MenuFilesController menuFilesController;
    @FXML private Menu menuEdit;
    public MenuEditController menuEditController;
    public ToolbarController toolbarController;
    public PlainEditorController plainEditorController;
    //@FXML
    private Stage aboutWindow;
    
    @FXML
    private Menu menuView;
    @FXML
    private Menu menuSettings;
    @FXML
    private Menu menuAbout;
    @FXML
    private Menu menuHelp;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuFilesController.parent = this;
        menuEditController.parent = this;
        plainEditorController.parent = this;
        toolbarController.parent = this;

        EventHandler<ActionEvent> handler = event -> {
            switch (((MenuItem) event.getSource()).getId()) {
                case "menuAbout" -> {
                    aboutWindow = new Stage();
                    aboutWindow.setTitle("onMark • About");
                    aboutWindow.setScene(new Scene(App.loadFXML("fxml/About.fxml")));
                    aboutWindow.show();
                }
                default -> {
                }
            }
        };
        
        menuAbout.setOnAction(handler);
    }
    public void setEditingEnabled(boolean option){
        menuEdit.setDisable(option);
        plainEditorController.setTextInputEnabled(option);
    }
}
