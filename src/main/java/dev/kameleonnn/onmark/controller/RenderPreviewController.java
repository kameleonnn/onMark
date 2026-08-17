package dev.kameleonnn.onmark.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.ext.task.list.items.TaskListItemsExtension;
import org.commonmark.ext.ins.InsExtension;

/**
 * FXML Controller class
 *
 * @author kameleonnn
 */
public class RenderPreviewController implements Initializable {

    @FXML
    private WebView previewWeb;
    public MainWinController parent;
    private final List<Extension> extensions = List.of(InsExtension.create(),StrikethroughExtension.create(), TaskListItemsExtension.create());
    private final Parser parser = Parser.builder().extensions(extensions).build();
    private final HtmlRenderer render = HtmlRenderer.builder().extensions(extensions).build();
    private Node document;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
    }   
    
    public void MDtoHTML(){
        document = parser.parse(parent.plainEditorController.passText());
        previewWeb.getEngine().loadContent(render.render(document), "text/html");
    }
    
}
