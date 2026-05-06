package dev.kameleonnn.onmark;

/**
 * enum containing string constants used within the program.
 * @author kameleonnn
 */
public enum Strings {
    //tui-specific
    TUI_WELCOME("TOODLES! - the niceys to-do list\n"),
    TUI_INIT_FILE_PROMPT("Options:\n1 - Create new file\n2 - Choose existing file\n3 - Exit program\n> "),
    TUI_FILE_NAME_PROMPT("Input name of new file (.tsv): "),
    
    UNRECOGNIZED_ARGUMENT("Unrecognized argument.\n"),
    
    //config
    CONFIG_FILE("toodles-config.properties"),
    CONFIG_SAVE_ERROR("An error occured saving configuration."),
    FILE_DEFAULT_FILE("toodles-data.tsv"),
    
    FILE_OPEN_ERROR("File cannot be opened."),
    FILE_OPEN_SUCCESS("File was opened successfully."),
    
    FILE_LOAD_ERROR("An error occured trying to read File."),
    FILE_LOAD_SUCCESS("File read successfully."),
    
    FILE_CREATE_TRY("Attempting to create new file..."),
    FILE_CREATE_ERROR("New file could not be created."),
    FILE_CREATE_SUCCESS("File was created succesfully."),
        
    FILE_EXIT_SAVE_PROMPT("You have unsaved changes. Do you wish to save them?"),
    FILE_SAVE_ERROR("An error occured while saving file."),
    FILE_SAVE_SUCCESS("File was saved successfully."),
    
    TASK_SAVE_ERROR("An error occured saving task information.")
    
    ;
    
    @SuppressWarnings("unused")
    public String text;
    Strings(String text){
        this.text=text;
    }
    
}
