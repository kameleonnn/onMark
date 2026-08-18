package dev.kameleonnn.onmark.config;

import dev.kameleonnn.onmark.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import dev.kameleonnn.onmark.config.Config;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 *
 * @author kameleonnn
 */
public class ConfigFilesTest {
   
    @Test
    public void configDirExists(){
        String dir = Config.getConfigDir();
        Assertions.assertAll("Config dir: ",
            () -> Assertions.assertTrue(Files.exists(Paths.get(dir))),
            () -> Assertions.assertTrue(Files.isDirectory(Paths.get(dir)))
        );
    }
    
    @Test
    public void configFilesExist(){
        String dir = Config.getConfigDir() + System.getProperty("file.separator");
        Config.createConfigFiles();
        Assertions.assertAll( "Config files: ",
                () -> Assertions.assertTrue(Files.isRegularFile(Paths.get(dir+Strings.CONFIG_FILE.text))),
                () ->Assertions.assertTrue(Files.isRegularFile(Paths.get(dir+Strings.CONFIG_RECENTS.text))),
                () -> Assertions.assertTrue(Files.isDirectory(Paths.get(dir+Strings.CONFIG_USER_THEMES.text))));
    }
    
}
