package dev.kameleonnn.onmark.controller;

import dev.kameleonnn.onmark.App;
import dev.kameleonnn.onmark.Editor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author kameleonnn
 */
public class PlainEditorController implements Initializable {

    public MainWinController parent; // set from MainWinController
    @FXML private ScrollPane plainEditor;
    @FXML private TextArea textInput;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupRespSize();
        textInput.textProperty().addListener((final ObservableValue<? extends String> observable, final String oldValue, final String newValue) -> {
            App.saved = textInput.getText().equals(App.data);
            if (parent.getRenderPreview().isVisible()){
                parent.renderPreviewController.MDtoHTML();
            }
        });
        
        textInput.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.ENTER){
                textInput.insertText(textInput.getCaretPosition(), "  ");
            }
        });

    }

    /**
     * allows Ctrl+[key] usage through UI elements
     * @param op
     */
    public void ctrl(String op) {
        switch (op) {
            case "copy" ->
                textInput.copy();
            case "cut" ->
                textInput.cut();
            case "paste" ->
                textInput.paste();
            case "selectall" ->
                textInput.selectAll();
            case "undo" ->
                textInput.undo();
            case "redo" ->
                textInput.redo();
            default -> {
                break;
            }
        }
    }

    /**
     * wrapper method for Editor.wrapStr(tag, str). allows for editing through
     * UI elements
     *
     * @param tag markdown tag
     */
    public void edit(String tag) {
        textInput.replaceSelection(Editor.wrapStr(tag, textInput.getSelectedText()));
        textInput.requestFocus();
    }

    /**
     * wrapper method for Editor.wrapStr(tag1, tag2, str). allows for editing
     * through UI elements
     *
     * @param tag1 opening markdown tag
     * @param tag2 closing markdown tag
     */
    public void edit(String tag1, String tag2) {
        textInput.replaceSelection(Editor.wrapStr(tag1, tag2, textInput.getSelectedText()));
        textInput.requestFocus();
    }

    public void insertHyperlink() {
        textInput.replaceSelection(Editor.insertHyperlink(textInput.getSelectedText(), "URL"));
        textInput.requestFocus();
    }

    public void insertImage() {
        textInput.replaceSelection(Editor.insertImage(textInput.getSelectedText()));
        textInput.requestFocus();
    }
    
    public void insertHorLine(){
        textInput.insertText(textInput.getCaretPosition(), "  \n----  \n");
        textInput.requestFocus();
    }
    
    public void insertLineFeat(String feat){
        int init_pos = textInput.getCaretPosition();
        int caret=init_pos;
        String text = textInput.getText();
        while(caret>0){
            if(text.charAt(caret-1)=='\n'){ break;}
            caret--;
        }
        if(text.substring(caret, init_pos).length()!=0 && text.charAt(caret)=='#'){
            feat="#"; 
        }
        textInput.insertText(caret, feat);
        textInput.positionCaret(init_pos+feat.length());
        textInput.requestFocus();
    }

    /**
     * Allows to setup responsive sizing of some elements in this controller.
     */
    public void setupRespSize() {
        textInput.prefHeightProperty().bind(plainEditor.heightProperty());
        textInput.prefWidthProperty().bind(plainEditor.widthProperty());
    }

    /**
     * Loads contents of given file into the text area
     */
    public void loadFileConts() {
        if (!textInput.getText().equals("")) {
            textInput.clear();
        }
        textInput.setText(App.data);
        App.saved = true;  
    }

    /**
     * Clears current contents of text area
     */
    public void clearEditor() {
        textInput.clear();
    }

    /*
    * getter method for editor contents
    */
    public String passText() {
        return textInput.getText();
    }

}
