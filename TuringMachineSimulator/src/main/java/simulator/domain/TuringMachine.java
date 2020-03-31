
package simulator.domain;

import java.util.ArrayList;

public class TuringMachine {
    
    private String name;
    private String desc;
    private ArrayList<String> ttable;
    
    public TuringMachine(String name, String description){
        this.name = name;
        desc = description;
    }
    
    public void setTable(ArrayList<String> t){
        t = this.ttable;
    }
    
    public void printTable(){
        int n = ttable.size()/2;
        int m = 2;
        int idx = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                System.out.print(ttable.get(idx) + "");
            }
            System.out.println("");
        }
    }
    
}
