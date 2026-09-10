package dev.kameleonnn.onmark.config;

import dev.kameleonnn.onmark.util.FileRW;
import dev.kameleonnn.onmark.util.Strings;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author kameleonnn
 */
public class Recents {
    
    private static final ArrayList<String> recent = new ArrayList<>();
    
    private Recents(){
        throw new IllegalStateException("This is a utility class");
    }
    
    public static void recentsInit(){
        if(!Files.exists(Config.getConfigDir().resolve(Strings.CONFIG_RECENTS.text))){
            FileRW.createFile(Config.getConfigDir().resolve(Strings.CONFIG_FILE.text).toString());
        } else {
            readRecents();
        }
    }
    
    public static List<String> getRecents(){
        return recent;
    }
    
    private static void readRecents(){
        String[] read = FileRW.readFile(Config.getConfigDir().resolve(Strings.CONFIG_RECENTS.text).toString()).split(System.lineSeparator());
        recent.addAll(Arrays.asList(read));

    }

    public static void writeRecents(){
        StringBuilder builder = new StringBuilder();
        for(String item : recent){
            builder=builder.append(item).append(System.lineSeparator());
        }
        FileRW.save(builder.toString(), Config.getConfigDir().resolve(Strings.CONFIG_RECENTS.text).toString());
    }

     /**
     * changes recently opened files listed in
     * MainWinController.menuFileOpenRecents
     */
    public static boolean newItem(String filename) {
        if(recent.contains(filename)){
            return false;
        }
        recent.add(filename);
        return true;
    }
    
    public static void removeItem(int index){
        recent.remove(index);
    }
}
