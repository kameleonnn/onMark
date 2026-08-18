package dev.kameleonnn.onmark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author kameleonnn
 */
public class Config {
    private static final Config instance=new Config();
    private static String configDir=null;
    
    private Config(){
    }
    
    public static Config getInstance(){
	return instance;
    }
    
    public static void createConfigFiles(){
        String baseDir = configDir+System.getProperty("file.separator");
        try {
            FileRW.createFile(baseDir+Strings.CONFIG_FILE.text);
            FileRW.createFile(baseDir+Strings.CONFIG_RECENTS.text);
            Files.createDirectory(Paths.get(baseDir+Strings.CONFIG_USER_THEMES.text));
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String getConfigDir(){
        if(configDir==null){
            createConfigDir();
        }
        return configDir;
    }
    
    public static void createConfigDir(){
        if(System.getProperty("os.name").equals("Linux")){
            try {
                configDir=System.getenv("HOME")+"/.config/onMark";
                Files.createDirectory(Paths.get(configDir));
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, Strings.CONFIG_CREATE_ERROR.text, ex);
            }
        } else{
            if(System.getProperty("os.name").contains("Windows")){
                try {
                    configDir=System.getenv("APPDATA")+"\\onMark";
                    Files.createDirectory(Paths.get(configDir)); 
                } catch (IOException ex) {
                    Logger.getLogger(Config.class.getName()).log(Level.SEVERE, Strings.CONFIG_CREATE_ERROR.text, ex);
                }
            }
        }
    }

}
