
package simulator.dao;

import java.io.File;

/**
 * A class responsible for managing the program folder and project folder.
 */
public class FileManagerDao implements ManagerDao {
    
    private String programf;
    private String projectf;
    
    public FileManagerDao() {
        programf = createProgramFolder();
        System.out.println(programf);
        projectf = createProjectFolder();
        System.out.println(projectf);
    }
    
    /*
     * Creates a program folder.
     * For now the program folder only holds the project folder.
     * However, later there might be use for this.
     * @return The absolute path of the program folder.
     */
    private String createProgramFolder() {
        File f = new File("TMSimulator");
        f.mkdir();
        return System.getProperty("user.dir") + File.separator + "TMSimulator";
    }
    
    /*
     * Creates a project folder if it does not already exists.
     * All of the project files are stored in the project folder.
     * @return Absolute path of the project file.
     */
    private String createProjectFolder() {
        File f = new File(programf + File.separator + "Projects");
        f.mkdir();
        return programf + File.separator + "Projects";
    }
    
    /**
     * Returns the absolute path of the project folder.
     * @return Absolute path of the project folder.
     */
    @Override
    public String getProjectFolder() {
        return projectf;
    }
    
}
