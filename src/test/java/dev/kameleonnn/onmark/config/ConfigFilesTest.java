package dev.kameleonnn.onmark.config;

import dev.kameleonnn.onmark.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.nio.file.Files;
import java.nio.file.Path;
/**
 *
 * @author kameleonnn
 */
public class ConfigFilesTest {
   
    @Test
     void configDirExists(){
        Path dir = Config.getConfigDir();
        Assertions.assertAll("Config dir: ",
            () -> Assertions.assertTrue(Files.exists(dir)),
            () -> Assertions.assertTrue(Files.isDirectory(dir))
        );
    }
    
    @Test
     void configFilesExist(){
        Path dir = Config.getConfigDir();
        Config.createConfigFiles();
        Assertions.assertAll( "Config files: ",
                () -> Assertions.assertTrue(Files.isRegularFile(dir.resolve(Strings.CONFIG_FILE.text))),
                () ->Assertions.assertTrue(Files.isRegularFile(dir.resolve(Strings.CONFIG_RECENTS.text))),
                () -> Assertions.assertTrue(Files.isDirectory(dir.resolve(Strings.CONFIG_USER_THEMES.text))));
    }
    
}
