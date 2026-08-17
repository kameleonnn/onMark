package dev.kameleonnn.onmark.controller;

import dev.kameleonnn.onmark.App;
import dev.kameleonnn.onmark.Strings;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kameleonnn
 */
public class MainWinController implements Initializable {

    @FXML
    private Menu menuFiles;
    public MenuFilesController menuFilesController;
    @FXML
    private Menu menuEdit;
    public MenuEditController menuEditController;
    public ToolbarController toolbarController;
    public PlainEditorController plainEditorController;
    public RenderPreviewController renderPreviewController;
    //@FXML
    private Stage aboutWindow;
    private Stage settingsWindow;
    @FXML
    private Menu menuView;
    @FXML
    private MenuItem menuSettings;
    @FXML
    private MenuItem menuAbout;
    @FXML
    private MenuItem menuOpenManual;
    @FXML
    private MenuItem menuBugreportCB;
    @FXML
    private MenuItem menuBugreportGH;
    @FXML
    private MenuItem menuOpenSettings;
    @FXML
    private CheckMenuItem menuViewEditor;
    @FXML
    private CheckMenuItem menuViewPreview;
    @FXML
    private SplitPane splitView;
    @FXML
    private ScrollPane plainEditor;
    @FXML
    private ScrollPane renderPreview;

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
        renderPreviewController.parent = this;

        EventHandler<ActionEvent> handler = (ActionEvent event) -> {
            switch (((MenuItem) event.getSource()).getId()) {
                case "menuAbout" -> {
                    aboutWindow = new Stage();
                    aboutWindow.setTitle("onMark - About");
                    aboutWindow.setScene(new Scene(App.loadFXML("fxml/settings/About.fxml")));
                    aboutWindow.show();
                }
                case "menuOpenSettings" -> {
                    settingsWindow = new Stage();
                    settingsWindow.setTitle("onMark - Settings");
                    settingsWindow.setScene(new Scene(App.loadFXML("fxml/settings/Settings.fxml")));
                    settingsWindow.show();
                }
                case "menuBugreportCB" -> {
                    App.webhost.showDocument(Strings.CB_ISSUES.text);
                }
                case "menuBugreportGH" -> {
                    App.webhost.showDocument(Strings.GH_ISSUES.text);
                }
                default -> {
                }
            }
        };
        menuViewEditor.setOnAction(e -> {
            paneVisibility(plainEditor, menuViewEditor.isSelected());
        });
        menuViewPreview.setOnAction(e -> {
            paneVisibility(renderPreview, menuViewPreview.isSelected());
        });

        menuAbout.setOnAction(handler);
        menuOpenSettings.setOnAction(handler);
        menuBugreportCB.setOnAction(handler);
        menuBugreportGH.setOnAction(handler);
    }

    private void paneVisibility(ScrollPane pane, boolean state) {
        pane.setDisable(!state);
        pane.setVisible(state);
        if (state == true) {
            splitView.getItems().add(pane);
        } else {
            splitView.getItems().remove(pane);
        }
    }
    
    public ScrollPane getRenderPreview(){
        return this.renderPreview;
    }

}
