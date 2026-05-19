package dev.kameleonnn.onmark;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author kameleonnn
 */
public class Files {
    
    /**
     * Creates new file from default values
     * @param filename string
     * @return 'true' if file was created successfully, 'false' on failure
     * @throws IOException if file stream cannot be created
     */
    public boolean createFile(String filename) throws IOException{
        return new File(filename).createNewFile();
    }
    
    /**
     * Clear contents of given file
     * @param filename string
     * @throws IOException
     */
    public static void clearFile(String filename) throws IOException{
	new BufferedWriter(new FileWriter(filename, false)).close();
    } 
    
    /**
     * Reads contents of file line after line
     * @param filename
     * @throws IOException if file cannot be read
     */
    public static void readFile(String filename) throws IOException{
        try (BufferedReader dataRead = checkIfFileCanOpen(filename)) {
            String line;
            while((line=dataRead.readLine())!=null){
                if(!line.equals("")){
                    App.data = App.data + line+"\n"; //zepsute do data jest static
                }
            }
        }
    }
    
    /**
     * Saves contents of given string to file with given filename line by line
     * @param data string to be written to file
     * @param filename file to be written to
     * @return true after completing method
     * @throws IOException
     */
    public static void save(String data, String filename) throws IOException{
	clearFile(filename);
        try (BufferedWriter dataWrite = new BufferedWriter(new FileWriter(filename))) {
            for(int i=0;i<data.length();i++){
                    dataWrite.write(data.charAt(i));
            }
            App.saved = true;
        }
    }  
    
    /**
     * checks if file with given filename can be opened
     * @param filename string
     * @return BufferedReader on success
     * @throws IOException
     */
    public static BufferedReader checkIfFileCanOpen(String filename) throws IOException{
        return new BufferedReader(new FileReader(filename)); 
    }

    public static void closeFile(){
        App.data = "";
        App.filename = "";
    }

}
