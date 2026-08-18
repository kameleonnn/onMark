package dev.kameleonnn.onmark.controller;

import dev.kameleonnn.onmark.App;
import dev.kameleonnn.onmark.highlight.HighlightNodeRenderer;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.ext.task.list.items.TaskListItemsExtension;
import org.commonmark.ext.ins.InsExtension;
import org.commonmark.renderer.html.HtmlNodeRendererContext;

/**
 * FXML Controller class
 *
 * @author kameleonnn
 */
public class RenderPreviewController implements Initializable {

    @FXML
    private WebView previewWeb;
    private WebEngine engine;
    public MainWinController parent;
    private final List<Extension> extensions = List.of(InsExtension.create(),StrikethroughExtension.create(), TaskListItemsExtension.create());
    private final Parser parser = Parser.builder().extensions(extensions).build();
    private final HtmlRenderer render;
    private Node document;

    public RenderPreviewController() {
        this.render = HtmlRenderer.builder().nodeRendererFactory((HtmlNodeRendererContext context) -> new HighlightNodeRenderer(context)).extensions(extensions).build();
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        engine = previewWeb.getEngine();

        engine.locationProperty().addListener((ObservableValue<? extends String> observable, String oldVal, String newVal) -> {
            if(!engine.getLocation().equals("")){
                App.openLink(engine.getLocation());
                MDtoHTML();
            }
        });
    }   
    
    public void MDtoHTML(){
        document = parser.parse(parent.plainEditorController.passText());
        engine.loadContent(render.render(document), "text/html");
    }
    
}
