
package simulator.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import simulator.domain.TuringMachine;

public class FileTMDao implements TMDao {
    
    private String folder;
    
    
    public FileTMDao(String f) {
        folder = f;
    }
    
    //creates a file which describes the tm
    @Override
    public boolean create(TuringMachine tm) {
        File f = new File(folder + File.separator + tm.getName() + ".txt");
        try {
            if (f.createNewFile()) {
                FileWriter writer = new FileWriter(f);
                writer.write(tm.getName() + ":\n");
                writer.write(tm.getDescription() + ":\n");
                writer.write(tm.toStringAlphabet() + ":\n");
                writer.write(tm.toStringStates() + ":\n");
                writer.write(tm.toStringTable());
                writer.close();
                return true;
            } else {
                return false;
            }
        } catch (IOException ex) {
            System.out.println("create: " + ex.getMessage());
            return false;
        }
    }
}
