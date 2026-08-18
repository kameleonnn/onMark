package dev.kameleonnn.onmark.config;

import dev.kameleonnn.onmark.App;

/**
 *
 * @author kameleonnn
 */
public class Recents {
    
    public static final String[] recent = new String[20];
    private static final Recents instance = new Recents();
    
    private Recents(){
    }
    public static Recents getInstance(){
	return instance;
    }

     /**
     * changes recently opened files listed in
     * MainWinController.menuFileOpenRecents
     */
    public static void changeRecents() {
        if (recent[0] != null) {
            System.arraycopy(recent, 0, recent, 1, 19);
        }
        recent[0] = App.filename;
    }
    
    // when saving recents -> turn them all into one string, use string builder
    // when reading recents -> split big string into separate ones, use string builder
}
