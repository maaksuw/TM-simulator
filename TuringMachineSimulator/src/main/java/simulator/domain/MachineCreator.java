
package simulator.domain;

import java.util.ArrayList;

public class MachineCreator {
    
    public TuringMachine create(String name, String desc){
        //to be added: check if the name is unique, if not, return false
        TuringMachine tm = new TuringMachine(name, desc);
        return tm;
    }
    
    public void define(TuringMachine tm, ArrayList<String> in){
        tm.setTable(in);
    }
    
}
