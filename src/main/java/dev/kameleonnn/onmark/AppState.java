package dev.kameleonnn.onmark;

/**
 *
 * @author kameleonnn
 */
public class AppState {
    private static String filename = "";
    private static String data = "";
    private static boolean saved = true;
    
    private AppState(){
        throw new IllegalStateException("This is a utility class");
    }
    
    public static String getFilename(){
        return filename;
    }
    public static void setFilename(String val){
        filename = val;
    }
    
    public static String getData(){
        return data;
    }
    public static void setData(String val){
        data = val;
    }
    
    public static boolean isSaved(){
        return saved;
    }
    public static void setSaved(boolean val){
        saved = val;
    }
    
}
