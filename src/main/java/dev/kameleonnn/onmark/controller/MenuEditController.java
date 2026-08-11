package dev.kameleonnn.onmark.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author kameleonnn
 */
public class MenuEditController implements Initializable {
    public MainWinController parent;
    @FXML
    private MenuItem menuEditUndo;
    @FXML
    private MenuItem menuEditRedo;
    @FXML
    private MenuItem menuEditCopy;
    @FXML
    private MenuItem menuEditCut;
    @FXML
    private MenuItem menuEditPaste;
    @FXML
    private MenuItem menuEditSelectAll;
    @FXML
    private MenuItem menuEditItalics;
    @FXML
    private MenuItem menuEditBold;
    @FXML
    private MenuItem menuEditStrike;
    @FXML
    private MenuItem menuEditHigh;
    @FXML

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        EventHandler<ActionEvent> handler = event -> {
            switch (((MenuItem) event.getSource()).getId()) {
                case "menuEditCopy" ->
                    parent.plainEditorController.ctrl("copy");
                case "menuEditCut" ->
                    parent.plainEditorController.ctrl("cut");
                case "menuEditPaste" ->
                    parent.plainEditorController.ctrl("paste");
                case "menuEditSelectAll" ->
                    parent.plainEditorController.ctrl("selectall");
                case "menuEditUndo" ->
                    parent.plainEditorController.ctrl("undo");
                case "menuEditRedo" ->
                    parent.plainEditorController.ctrl("redo");
                case "menuEditBold" ->
                    parent.plainEditorController.edit("**");
                case "menuEditItalic" ->
                    parent.plainEditorController.edit("__");
                case "menuEditUnderline" ->
                    parent.plainEditorController.edit("<ins>", "</ins");
                case "menuEditStrikethrough" ->
                    parent.plainEditorController.edit("~~");
                case "menuEditCodeblock" ->
                    parent.plainEditorController.edit("\n```\n");
                case "menuInsertImage" -> {
                    parent.plainEditorController.insertImage();
                }
                case "menuInsertHyperlink" -> {
                    parent.plainEditorController.insertHyperlink();
                }
                default -> {
                }
            }
        }  ;
        menuEditBold.setOnAction(handler);
        menuEditItalics.setOnAction(handler);
        menuEditCopy.setOnAction(handler);
        menuEditCut.setOnAction(handler);
        menuEditPaste.setOnAction(handler);
        menuEditSelectAll.setOnAction(handler);
        menuEditUndo.setOnAction(handler);
        menuEditRedo.setOnAction(handler);
        //menuEditCodeblock.setOnAction(handler);
    }
}
