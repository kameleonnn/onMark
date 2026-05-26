package dev.kameleonnn.onmark.controller;

import dev.kameleonnn.onmark.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    } 
    
    public void setupRespSize(AnchorPane mainCont){
        editorMainCont.prefWidthProperty().bind(mainCont.widthProperty());
        editorMainCont.prefHeightProperty().bind(mainCont.heightProperty());
        editorSplitView.prefWidthProperty().bind(editorMainCont.widthProperty());
        editorSplitView.prefHeightProperty().bind(editorMainCont.heightProperty());
        textInput.prefHeightProperty().bind(splitInput.heightProperty());
        textInput.prefWidthProperty().bind(splitInput.widthProperty());
    }
    
    public void loadFileConts(){
        textInput.setText(App.data);
    }
    public void clearEditor(){
        textInput.setText("");
    }
    
}
