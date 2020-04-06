
package simulator.domain;

import java.io.File;
import java.util.ArrayList;
import simulator.dao.FileDao;
import simulator.dao.TMDao;

public class Handler {
    
    private FileDao fao;
    private TMDao tmdao;
    
    public Handler(FileDao fdao, TMDao tmdao){
        this.fao = fdao;
        this.tmdao = tmdao;
    }
    
    public boolean initiate(){
        if(!fao.createProgramFolder()){
            fao.setSource(System.getProperty("user.home") + File.separator + "TuringMachineSimulator" + File.separator + "source.txt");
        }
        return true;
    }
    
    public void setDefaultFolderLocation(String path){
        fao.setDefaultFolderLocation(path);
    }
    
    public String getDefaultFolderLocation(){
        return fao.getDefaultFolderLocation();
    }
    
    public void createProjectFolder(){
        tmdao.setProjectFolder(fao.getDefaultFolderLocation());
    }
    
    public boolean createProject(String name, String description, ArrayList<String> table){
        tmdao.setProjectFolder(fao.getDefaultFolderLocation());
        TuringMachine tm = new TuringMachine(name, description, table);
        return tmdao.createProjectFile(tm);
    }
    
    public void simulate(){
        
    }
}
