
package simulator.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import simulator.domain.TuringMachine;

public class TMDao {
    
    private File projectFolder;
    
    //sets and creates the project folder
    public boolean setProjectFolder(String path) {
        projectFolder = new File(path);
        return projectFolder.mkdir();
    }
    
    public File getProjectFolder() {
        return projectFolder;
    }
    
    //adds a file to the project folder specified in projectFile
    public boolean createProjectFile(TuringMachine tm) {
        String name = tm.getName();
        File f = new File(projectFolder.getAbsolutePath() + File.separator + name + ".txt");
        try {
            if (f.createNewFile()) {
                FileWriter writer = new FileWriter(f);
                writer.write(name + "\n");
                writer.write(tm.getDesc() + "\n");
                writer.write("-\n");
                writer.write(tm.toStringTable());
                writer.close();
                return true;
            } else {
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(TMDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
