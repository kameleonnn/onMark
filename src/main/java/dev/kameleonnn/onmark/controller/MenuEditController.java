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
 * @author szef
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
    private MenuItem menuEditSub;
    @FXML
    private MenuItem menuEditSup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        EventHandler<ActionEvent> handler = event -> {
            switch (((MenuItem) event.getSource()).getId()) {
                case "menuEditCopy" ->
                    parent.getEditorCtrl().ctrl("copy");
                case "menuEditCut" ->
                    parent.getEditorCtrl().ctrl("cut");
                case "menuEditPaste" ->
                    parent.getEditorCtrl().ctrl("paste");
                case "menuEditSelectAll" ->
                    parent.getEditorCtrl().ctrl("selectall");
                case "menuEditUndo" ->
                    parent.getEditorCtrl().ctrl("undo");
                case "menuEditRedo" ->
                    parent.getEditorCtrl().ctrl("redo");
                case "menuEditBold" ->
                    parent.getEditorCtrl().edit("**");
                case "menuEditItalic" ->
                    parent.getEditorCtrl().edit("__");
                /*case "menuEditUnderline" ->
                    parent.editor.edit("<ins>", "</ins");
                case "menuEditStrikethrough" ->
                    parent.editor.edit("~~");
                case "menuEditCodeblock" ->
                    parent.editor.edit("\n```\n");*/
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
