package dev.kameleonnn.onmark.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author kameleonnn
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
    @FXML private Button toolbarHighlight;
    @FXML private AnchorPane toolbar;
    @FXML private Button toolbarHyperlink;
    @FXML private Button toolbarImage;
    @FXML private Button toolbarHeader;
    @FXML private Button toolbarList;
    @FXML private Button toolbarChecklist;
    @FXML private Button toolbarInlineCode;
    @FXML private Button toolbarHorizontal;
    @FXML private Button toolbarQuote;
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
                case "toolbarHighlight" -> parent.plainEditorController.edit("==");
                case "toolbarStrikethrough" -> parent.plainEditorController.edit("~~");
                case "toolbarInlineCode" -> parent.plainEditorController.edit("`");
                case "toolbarCodeblock" -> parent.plainEditorController.edit("\n```\n");
                case "toolbarHyperlink" -> parent.plainEditorController.insertHyperlink();
                case "toolbarImage" -> parent.plainEditorController.insertImage();
                case "toolbarHorizontal" -> parent.plainEditorController.insertHorLine();
                case "toolbarHeader" -> parent.plainEditorController.insertLineFeat("# ");
                case "toolbarQuote" -> parent.plainEditorController.insertLineFeat("> ");
                case "toolbarList" -> parent.plainEditorController.insertLineFeat("- ");
                case "toolbarChecklist" -> parent.plainEditorController.insertLineFeat("-[ ] ");
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
        toolbarHighlight.setOnAction(handler);
        toolbarInlineCode.setOnAction(handler);
        toolbarCodeblock.setOnAction(handler);
        toolbarHyperlink.setOnAction(handler);
        toolbarImage.setOnAction(handler);
        toolbarHorizontal.setOnAction(handler);
        toolbarHeader.setOnAction(handler);
        toolbarQuote.setOnAction(handler);
        toolbarList.setOnAction(handler);
        toolbarChecklist.setOnAction(handler);
    }    
    
}
