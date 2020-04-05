
package simulator.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileDao {
    
    private File source;
    
    //creates a program folder in home directory and initiates it with a source file
    public void createProgramFolder(){
        String path = System.getProperty("user.home") + "/TuringMachineSimulator";
        File f = new File(path);
        f.mkdir();
        createSourceFile(f);
    }
    
    //creates source file where the information about the users project folders is saved
    private void createSourceFile(File dir){
        String path = dir.getAbsolutePath() + "/source.txt";
        this.source = new File(path);
        try {
            this.source.createNewFile();
        } catch (IOException ex) {
            System.out.println("Unable to create source file.");
            Logger.getLogger(TMDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //writes the absolute path of default folder to source file
    public void setDefaultFolderLocation(String path){
        try {
            PrintWriter writer = new PrintWriter(source);
            writer.print(path);
            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to write to source file.");
            Logger.getLogger(TMDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //reads and returns the current default folder
    public String getDefaultFolderLocation(){
        try(Scanner reader = new Scanner(source)){
            if(reader.hasNextLine()){
                return reader.nextLine();
            } else {
                return null;
            }
            
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }    
    
}
