
package simulator.domain;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import simulator.dao.ManagerDao;
import simulator.dao.TMDao;

public class Handler {
    
    private ManagerDao mao;
    private TMDao tmdao;
    
    public Handler(ManagerDao mao, TMDao tmdao) {
        this.mao = mao;
        this.tmdao = tmdao;
    }
    
    public boolean createTM(String name, String description, ArrayList<String> table) {
        TuringMachine tm = new TuringMachine(name, description, table);
        try {
            return tmdao.create(tm);
        } catch (IOException ex) {
            System.out.println("createTM: " + ex.getMessage());
            return false;
        }
    }
    
    public void simulate() {
        
    }
}
