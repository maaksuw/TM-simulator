
package simulator.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileDao {
    
    private File source;
    
    //creates a program folder in home directory
    public boolean createProgramFolder(){
        String path = System.getProperty("user.home") + File.separator + "TuringMachineSimulator";
        File f = new File(path);
        if(!f.mkdir()){
            return false;
        } else {
            return createSourceFile(f);
        }
    }
    
    //creates source file where the information about the users project folders is saved
    private boolean createSourceFile(File dir){
        String path = dir.getAbsolutePath() + File.separator + "source.txt";
        source = new File(path);
        try {
            return source.createNewFile();
        } catch (IOException ex) {
            System.out.println("Unable to create source file." + ex.getMessage());
            return false;
        }
    }

    public File getSource() {
        return source;
    }
    
    public void setSource(String path){
        source = new File(path);
    }
    
    //writes the absolute path of default folder to source file
    public boolean setDefaultFolderLocation(String path){
        try {
            PrintWriter writer = new PrintWriter(source);
            writer.print(path);
            writer.close();
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to find source file." + ex.getMessage());
            return false;
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
            return null;
        }
    }    
    
}
