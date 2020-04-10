
package simulator.dao;

import java.io.IOException;
import simulator.domain.TuringMachine;

public interface TMDao {
    
    boolean create(TuringMachine tm) throws IOException;
    
}
