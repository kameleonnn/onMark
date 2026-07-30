package dev.kameleonnn.onmark.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author szef
 */
public class ToolbarController implements Initializable {
    public MainWinController parent;
    @FXML private Button toolbarFileSave;
    @FXML private Button toolbarFileNew;
    @FXML private Button toolbarFileOpen;
    @FXML private Button toolbarBold;
    @FXML private Button toolbarItalic;
    @FXML private Button toolbarCodeblock;
    @FXML private Button toolbarUnderline;
    @FXML private Button toolbarStrikethrough;
    @FXML private Button toolbarUndo;
    @FXML private Button toolbarRedo;
    @FXML private Button toolbarEditBold;
    @FXML private Button toolbarHighlight;
    @FXML private Button toolbarSubscript;
    @FXML private Button toolbarSupscript;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        EventHandler<ActionEvent> handler = event -> {
            switch (((Button) event.getSource()).getId()) {
                case "toolbarFileSave" -> parent.menuFilesController.saveFile();
                case "toolbarFileNew" -> parent.menuFilesController.newFile();
                case "toolbarFileOpen" -> parent.menuFilesController.openFile();
                case "toolbarUndo" -> parent.plainEditorController.ctrl("undo");
                case "toolbarRedo" -> parent.plainEditorController.ctrl("redo");
                case "toolbarBold" -> parent.plainEditorController.edit("**");
                case "toolbarItalic" -> parent.plainEditorController.edit("__");
                case "toolbarUnderline" -> parent.plainEditorController.edit("<ins>", "</ins>");
                case "toolbarStrikethrough" -> parent.plainEditorController.edit("~~");
                case "toolbarCodeblock" -> parent.plainEditorController.edit("\n```\n");
                default -> {
                }
            }
        };
        
        toolbarFileSave.setOnAction(handler);
        toolbarFileNew.setOnAction(handler);
        toolbarFileOpen.setOnAction(handler);
        toolbarUndo.setOnAction(handler);
        toolbarRedo.setOnAction(handler);
        toolbarBold.setOnAction(handler);
        toolbarItalic.setOnAction(handler);
        toolbarUnderline.setOnAction(handler);
        toolbarStrikethrough.setOnAction(handler);
        toolbarCodeblock.setOnAction(handler);
        // TODO
        
        
    }    
    
}
