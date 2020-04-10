
package simulator.dao;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class FileManagerDao implements ManagerDao {
    
    private String sourcef;
    private String projectf;
    
    public FileManagerDao() {
        sourcef = createProgramFolder();
        projectf = createProjectFolder();
    }
    
    //creates a program folder in home directory
    private String createProgramFolder() {
        File f = new File("TMSimulator");
        if(f.mkdir()){
            File file = new File(f.getAbsolutePath() + File.separator + "source.txt");
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println("createProgramFolder: " + ex.getMessage());;
            }
        }
        return System.getProperty("user.dir") + File.separator + "TMSimulator";
    }
    
    //sets and creates the project folder
    private String createProjectFolder() {
        File f = new File(sourcef + File.separator + "Projects");
        f.mkdir();
        return sourcef + File.separator + "Projects";
    }
    
    @Override
    public String getProjectFolder(){
        System.out.println(projectf);
        return projectf;
    }
    
}
