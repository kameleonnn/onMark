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
    
    private FileRW(){}
    
    /**
     * Creates new file from default values
     * @param filename string
     * @return 'true' if file was created successfully
     */
    public static boolean createFile(String filename){
        try {
            return new File(filename).createNewFile();
        } catch (IOException ex) {
            System.getLogger(FileRW.class.getName()).log(System.Logger.Level.ERROR, Strings.FILE_CREATE_ERROR.text, ex);
            return false;
        }
    }
    
    /**
     * Clear contents of given file
     * @param filename string
     * @return 'true' on success,  'false' on failure
     */
    public static boolean clearFile(String filename) {
        try {
            new BufferedWriter(new FileWriter(filename, false)).close();
            return true;
        } catch (IOException ex) {
            System.getLogger(FileRW.class.getName()).log(System.Logger.Level.ERROR, Strings.FILE_ERROR.text, ex);
            return false;
        }
    } 
    
    /**
     * Reads contents of file line after line
     * @param filename
     * @return 'true' if entire file was read successfully
     */
    public static String readFile(String filename){
        try (BufferedReader dataRead = new BufferedReader( new FileReader(filename))) {
            String line, target = "";
            while((line=dataRead.readLine())!=null){
                if(!line.equals("")){
                    target = target + line+"\n"; //change this to use string builder
                }
            }
            return target;
        } catch (IOException ex) {
            System.getLogger(FileRW.class.getName()).log(System.Logger.Level.ERROR, Strings.FILE_OPEN_ERROR.text, ex);
            return null;
        }
    }
    
    /**
     * Saves contents of given string to file with given filename line by line
     * @param data string to be written to file
     * @param filename file to be written to
     * @return 'true' if file was saved successfully
     */
    public static boolean save(String data, String filename){
	clearFile(filename);
        try (BufferedWriter dataWrite = new BufferedWriter(new FileWriter(filename))) {
            for(int i=0;i<data.length();i++){
                    dataWrite.write(data.charAt(i));
            }
            return true;
        } catch (IOException ex) {
            System.getLogger(FileRW.class.getName()).log(System.Logger.Level.ERROR, Strings.FILE_SAVE_ERROR.text, ex);
            return false;
        }
    }  
    
    /**
     * checks if file with given filename can be opened
     * @param filename string
     * @return 'true' if file is readable
     */
    public static boolean checkIfFileCanOpen(String filename){
        return Files.isReadable(new File(filename).toPath()); 
    }

    /**
     * "closes" file currently opened in editor
     */
    public static void closeFile(){
        App.data = "";
        App.filename = "";
    }

}
