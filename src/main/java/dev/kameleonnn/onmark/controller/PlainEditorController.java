package dev.kameleonnn.onmark.controller;

import dev.kameleonnn.onmark.App;
import dev.kameleonnn.onmark.Editor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;


/**
 * FXML Controller class
 *
 * @author kameleonnn
 */
public class PlainEditorController implements Initializable {
    public MainWinController parent;
    @FXML
    private ScrollPane plainEditor;
    @FXML
    private TextArea textInput;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupRespSize(); 
    }

    /**
     * allows Ctrl+[key] usage through UI elements
     * @param op
     */
    public void ctrl(String op) {
        switch (op) {
            case "copy" -> textInput.copy();
            case "cut" -> textInput.cut();
            case "paste" -> textInput.paste();
            case "selectall" -> textInput.selectAll();
            case "undo" -> textInput.undo();
            case "redo" -> textInput.redo();
            default -> {
            }
        }
    }

    /**
     * wrapper method for Editor.wrapStr(tag, str). allows for editing through UI elements
     * @param tag markdown tag 
     */
    public void edit(String tag) {
        textInput.replaceSelection(Editor.wrapStr(tag, textInput.getSelectedText()));
    }
    
    /**
     * wrapper method for Editor.wrapStr(tag1, tag2, str). allows for editing through UI elements
     * @param tag1 opening markdown tag
     * @param tag2 closing markdown tag
     */
    public void edit(String tag1, String tag2) {
        textInput.replaceSelection(Editor.wrapStr(tag1, tag2, textInput.getSelectedText()));
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
        if(!textInput.getText().equals("")){
            textInput.clear();
        }
        textInput.setText(App.data);
        App.saved=true;
    }

    /**
     * Clears current contents of text area
     */
    public void clearEditor() {
        textInput.clear();
    }
    
    public String passText(){
        return textInput.getText();
    }
    
    public void setTextInputEnabled(boolean option){
        textInput.setEditable(option);
        textInput.setDisable(option);
    }

}
