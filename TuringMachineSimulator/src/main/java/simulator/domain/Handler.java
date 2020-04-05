
package simulator.domain;

import java.util.ArrayList;
import simulator.dao.FileDao;
import simulator.dao.TMDao;

public class Handler {
    
    private FileDao fao;
    private TMDao tmdao;
    
    public Handler(){
        fao = new FileDao();
        tmdao = new TMDao();
        initiate();
    }
    
    private void initiate(){
        fao.createProgramFolder();
    }
    
    public void setDefaultFolderLocation(String path){
        fao.setDefaultFolderLocation(path);
    }
    
    public String getDefaultFolderLocation(){
        return fao.getDefaultFolderLocation();
    }
    
    public void createProjectFolder(){
        tmdao.setProjectFolder(fao.getDefaultFolderLocation());
        tmdao.createProjectFolder();
    }
    
    public boolean create(String name, String description, ArrayList<String> table){
        TuringMachine tm = new TuringMachine();
        tmdao.setProjectFolder(fao.getDefaultFolderLocation());
        tm.create(name, description, table);
        return tmdao.createProjectFile(tm);
    }
    
    public void simulate(){
        
    }
}
