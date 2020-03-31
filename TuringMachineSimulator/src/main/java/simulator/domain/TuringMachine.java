
package simulator.domain;

import java.util.ArrayList;

public class TuringMachine {
    
    private String name;
    private String desc;
    private ArrayList<String> table;
    
    public TuringMachine(String name, String description){
        this.name = name;
        desc = description;
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
    
    public void setTable(ArrayList<String> t){
        table = t;
    }
    
    public String toStringTable(){
        String s = "";
        int m = 2; //the size of the alphabet
        for(int i = 0; i < table.size(); i++){
            if(i%m == 0 && i != 0 && i != table.size() - 1) s += "\n";
            if(i%m == (m-1)) s += table.get(i);
            else {
                s += table.get(i) + " ";
            }
        }
        return s;
    }
}
