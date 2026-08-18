package dev.kameleonnn.onmark.highlight;

import java.util.Set;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlWriter;
/**
 *
 * @author kameleonnn
 */
public class HighlightNodeRenderer implements NodeRenderer{
    private final HtmlWriter html;

    public HighlightNodeRenderer(HtmlNodeRendererContext context){
        this.html = context.getWriter();
    }
    
    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return Set.of(Highlight.class);
    }

    @Override
    public void render(Node node) {
        Highlight highlight = (Highlight) node;
        html.tag("mark");
        html.text(highlight.getLiteral());
        html.tag("/mark");
    }
}