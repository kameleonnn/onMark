package dev.kameleonnn.onmark.config;

import dev.kameleonnn.onmark.util.Strings;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kameleonnn
 */
public class Config {
    private static final Config instance = new Config();
    private static Path configDir = null;

    private Config() {
    }

    public static Config getInstance() {
        return instance;
    }

    public static void configInit(){
        getConfigDir();
        if(!Files.exists(configDir.resolve(Strings.CONFIG_FILE.text))){
            try {
                Files.copy(Path.of("defaults", "default_config.properties"), configDir.resolve(Strings.CONFIG_FILE.text));
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            readConfig();
        }
    }
    
    public static void readConfig(){
        
    }

    public static Path getConfigDir() {
        if (configDir == null) {
            createConfigDir();
        }
        return configDir;
    }

    private static void createConfigDir() {
        String os_name = System.getProperty("os.name").toLowerCase();
        if (os_name.contains("nix") || os_name.contains("nux")) {
            configDir = Path.of(System.getProperty("user.home"), ".config", "onMark");
        }
        else if (os_name.contains("windows")) {
            configDir = Path.of(System.getProperty("user.home"), "AppData", "Roaming","onMark");
        }
        else if (os_name.contains("mac") || os_name.contains("darwin")){
            configDir  = Path.of(System.getProperty("user.home"), "Library", "Application Support", "onMark");
        } else {
            Logger.getLogger(Config.class.getName()).log(Level.WARNING, "Unsupported system, configuration files will be created in user directory");
            configDir = Path.of(System.getProperty("user.home"), "onMark");
        }

        try {
            Files.createDirectories(configDir);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, Strings.CONFIG_CREATE_ERROR.text, ex);
        }
    }

}
