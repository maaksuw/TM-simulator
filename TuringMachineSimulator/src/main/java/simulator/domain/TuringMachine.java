
package simulator.domain;

import java.util.ArrayList;

public class TuringMachine {
    
    private String name;
    private String desc;
    private ArrayList<String> table;
    
    public TuringMachine(String name, String desc, ArrayList<String> t) {
        this.name = name;
        this.desc = desc;
        this.table = t;
    }
    
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public ArrayList<String> getTable() {
        return table;
    }
    
    public String toStringTable() {
        String s = "";
        for (int i = 0; i < table.size(); i++) {
            if (i == table.size() - 1) {
                s += table.get(i);
            } else {
                s += table.get(i) + "\n";
            }
        }
        return s;
    }
}
