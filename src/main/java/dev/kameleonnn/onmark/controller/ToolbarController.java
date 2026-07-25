package dev.kameleonnn.onmark.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author szef
 */
public class ToolbarController implements Initializable {
    public MainWinController parent;
     @FXML
    private Button buttonFileSave;
    @FXML
    private Button buttonFileNew;
    @FXML
    private Button buttonFileOpen;
    @FXML
    private Button buttonEditBold;
    @FXML
    private Button buttonEditItalic;
    @FXML
    private Button buttonEditCodeblock;
    @FXML
    private Button buttonEditUnderline;
    @FXML
    private Button buttonEditStrikethrough;
    @FXML
    private Button buttonEditHighlight;
    @FXML
    private Button buttonEditSubscript;
    @FXML
    private Button buttonEditSupscript;
    @FXML
    private AnchorPane toolbar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        EventHandler<ActionEvent> handler = event -> {
            switch (((Button) event.getSource()).getId()) {
                case "buttonFileSave" -> parent.menuFilesController.saveFile();
                case "buttonFileNew" -> parent.menuFilesController.newFile();
                case "buttonFileOpen" -> parent.menuFilesController.openFile();
                case "buttonEditBold" -> parent.plainEditorController.edit("**");
                case "buttonEditItalic" -> parent.plainEditorController.edit("__");
                case "buttonEditUnderline" -> parent.plainEditorController.edit("<ins>", "</ins>");
                case "buttonEditStrikethrough" -> parent.plainEditorController.edit("~~");
                case "buttonEditCodeblock" -> parent.plainEditorController.edit("\n```\n");
                default -> {
                }
            }
        };

        buttonEditUnderline.setOnAction(handler);
        buttonEditStrikethrough.setOnAction(handler);
        buttonFileSave.setOnAction(handler);
        buttonFileNew.setOnAction(handler);
        buttonFileOpen.setOnAction(handler);
        buttonEditBold.setOnAction(handler);
        buttonEditItalic.setOnAction(handler);
        buttonEditCodeblock.setOnAction(handler);
        // TODO
        
        
    }    
    
}
