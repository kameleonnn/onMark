package dev.kameleonnn.onmark.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setupRespSize();
    } 
    
    public void setupRespSize(){
        //editorMainCont.setTopAnchor();
        //editorMainCont.setPrefHeight(400);
        //editorMainCont.setPrefWidth(600);
        editorSplitView.prefWidthProperty().bind(editorMainCont.widthProperty());
        editorSplitView.prefHeightProperty().bind(editorMainCont.heightProperty());
    }
    
}
