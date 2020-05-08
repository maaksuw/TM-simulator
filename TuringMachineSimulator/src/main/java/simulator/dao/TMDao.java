
package simulator.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import simulator.domain.TuringMachine;

public interface TMDao {
    
    boolean create(TuringMachine tm) throws IOException;
    
    String getProjectFolder();
    
    File createFile(TuringMachine tm) throws IOException;
    
    void prepareReader(File f) throws FileNotFoundException;
    
    String readName();
    
    String readDescription();
    
    String readAlphabet();
    
    String readStates();
    
    String[][] readTable(int alphabets, int states);
    
}
