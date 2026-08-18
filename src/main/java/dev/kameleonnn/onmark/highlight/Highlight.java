package dev.kameleonnn.onmark.highlight;
import org.commonmark.node.CustomNode;
import org.commonmark.node.Delimited;

/**
 *
 * @author kameleonnn
 */
public class Highlight extends CustomNode implements Delimited{
    
    private static final String DELIMITER = "==";

    @Override
    public String getOpeningDelimiter() {
        return DELIMITER;
    }

    @Override
    public String getClosingDelimiter() {
        return DELIMITER;
    }
    
    public String getLiteral(){
        return "a";
    }

}
