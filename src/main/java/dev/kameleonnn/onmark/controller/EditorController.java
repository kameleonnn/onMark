package dev.kameleonnn.onmark.controller;

import dev.kameleonnn.onmark.App;
import java.io.IOException;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        buttonFileSave.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.saveFile();
            }
        }));
        
        buttonFileNew.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.newFile();
            }
        }));
        
        buttonFileOpen.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.openFile();
            }
        }));
        
    } 
    
    public void getMain(MainWinController controller){
        main = controller;
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
