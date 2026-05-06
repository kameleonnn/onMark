package dev.kameleonnn.onmark;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.IO.print;
//LOGGING IMPORTS
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Singleton class for file related operations and information
 * @author kameleonnn
 */
public class FileOp {
    private Logger LOGGER = Logger.getLogger(FileOp.class.getName());
    private static FileOp instance;
    private BufferedReader dataRead;
    private BufferedWriter dataWrite;
    private File file;
    private boolean saved = true;
    private String filename = "";
    
    private FileOp(){
    }
    
    /**
     * Allows access to FileOp class
     * @return this instance of FileOp class
     */
    public static FileOp getInstance(){
	if(instance==null){instance=new FileOp();}
	return instance;
    }
    
    /**
     * Returns URL of file
     * @return this.dataFile
     */
    public String getFilename(){
        return filename;
    }
    /**
     * Checks if file can be opened by program
     * @return 'true' if successful, 'false' on failure
     */
    public boolean checkIfFileCanOpen(){
	try{
	    dataRead=new BufferedReader(new FileReader(filename));
            LOGGER.log(Level.INFO, Strings.FILE_OPEN_SUCCESS.text);
	    return true;
	}catch(FileNotFoundException e){
            LOGGER.log(Level.WARNING, Strings.FILE_OPEN_ERROR.text);
	    return false;
	}
    }
    /**
     * Creates new file from default values
     * @return 'true' if file was created successfully, 'false' on failure
     * @throws IOException if file stream cannot be created
     */
    // TODO przerobic calkiem
    public boolean createFile() throws IOException{
        LOGGER.log(Level.INFO, Strings.FILE_OPEN_SUCCESS.text);
        file = new File("FILE");
        return file.createNewFile();
    }
    
    //for opening file
    void setFile(String filename){
        this.filename=filename;
    }
    
    public boolean isSaved(){
        return saved;
    }

    private void clearFile() throws IOException{
	dataWrite=new BufferedWriter(new FileWriter(file, false));
	dataWrite.close();
    } 
    /**
     * Reads contents of file line after line and generates database contents ('Database.task')
     * @throws IOException if file cannot be read
     */
    // TODO przerobic calkiem
    public void readFile() throws IOException{
        dataRead = new BufferedReader(new FileReader(filename));
        String line;
        while((line=dataRead.readLine())!=null){
            if(!line.equals("")){
            LOGGER.log(Level.INFO, line);
            App.data = App.data + line;
            
            }
            
        }
        dataRead.close();
    }
    /**
     * Writes contents of program database (Database.task) to database file. Overwrites existing file and sets 'Database.saved' as 'true'
     * @param data String
     * @throws IOException if file cannot be written to
     */
    public void save(String data) throws IOException{
	clearFile();
	dataWrite=new BufferedWriter(new FileWriter(file,false));
	for(int i=0;i<data.length();i++){
	    if(!data.equals("")){
                LOGGER.log(Level.INFO, "writing to file");
		dataWrite.write(data);
                dataWrite.flush();
	    }
	}
	saved=true;
        dataWrite.close();
    }  
}