package dev.kameleonnn.onmark;

/**
 * @author kameleonnn
 */
public class Editor {
    
    /**
     * wraps a given string in markdown tags
     * @param tag markdown tag
     * @param str string to be wrapped
     * @return resulting string
     */
    public static String wrapStr(String tag, String str){
        App.saved=false;
        if (str.contains(tag)){
            return str.replace("tag", "");
        }
        return tag+str+tag;
    }

    /**
     * wraps a given string in markdown tags
     * @param tag1 opening markdown tag
     * @param tag2 closing markdown tag
     * @param str string to be wrapped
     * @return resulting string
     */
    public static String wrapStr(String tag1, String tag2, String str){
        App.saved=false;
        if (str.contains(tag1) || str.contains(tag2)){
            return str.replace(tag2, "").replace(tag1, "");
        }
        return tag1+str+tag2;
    }
    
    // TODO
    public String insertTable(int col, int row){
        return col+" "+row;
    }
    
    public static String insertHyperlink(String text, String url){
        App.saved=false;
        if(text==null || text.equals("")){
            text = "Link";
        }
        return "["+text+"]("+url+")";
    }
    
    public static String insertImage(String url){
        return "![Alt text]("+url+")\"Title\"";
    }
}
