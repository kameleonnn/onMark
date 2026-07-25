package dev.kameleonnn.onmark.controller;

import dev.kameleonnn.onmark.App;
import dev.kameleonnn.onmark.Editor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;

import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author kameleonnn
 */
public class EditorController implements Initializable {

    @FXML
    private AnchorPane editorMainCont;
    @FXML
    private SplitPane editorSplitView;
    @FXML
    private ScrollPane splitInput;
    @FXML
    private TextArea textInput;
    @FXML
    private ScrollPane splitPreview;
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

    private MainWinController main;
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

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        EventHandler<ActionEvent> handler = event -> {
            switch (((Button) event.getSource()).getId()) {
                case "buttonFileSave" -> main.saveFile();
                case "buttonFileNew" -> main.newFile();
                case "buttonFileOpen" -> main.openFile();
                case "buttonEditBold" -> edit("**");
                case "buttonEditItalic" -> edit("__");
                case "buttonEditUnderline" -> edit("<ins>", "</ins>");
                case "buttonEditStrikethrough" -> edit("~~");
                case "buttonEditCodeblock" -> edit("\n```\n");
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
     * Passes controller of the main window to this instance of EditorController. Allows using some methods of that class
     * @param controller MainWinController
     */
    public void getMain(MainWinController controller) {
        main = controller;
    }

    /**
     * Allows to setup responsive sizing of some elements in this controller. This is done from MainWinController
     * @param mainCont AnchorPane in the main window containing elements of this editor
     */
    public void setupRespSize(AnchorPane mainCont) {
        editorMainCont.prefWidthProperty().bind(mainCont.widthProperty());
        editorMainCont.prefHeightProperty().bind(mainCont.heightProperty());
        editorSplitView.prefWidthProperty().bind(editorMainCont.widthProperty());
        editorSplitView.prefHeightProperty().bind(editorMainCont.heightProperty());
        textInput.prefHeightProperty().bind(splitInput.heightProperty());
        textInput.prefWidthProperty().bind(splitInput.widthProperty());
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

}
