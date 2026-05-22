package dev.kameleonnn.onmark;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author kameleonnn
 */
public class FileRW {

    public FileRW(File file) {
    }
    
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
        try (BufferedReader dataRead = new BufferedReader( new FileReader(filename))) {
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
    public static boolean checkIfFileCanOpen(String filename) throws IOException{
        return Files.isReadable(new File(filename).toPath()); 
    }

    public static void closeFile(){
        App.data = "";
        App.filename = "";
    }

}
