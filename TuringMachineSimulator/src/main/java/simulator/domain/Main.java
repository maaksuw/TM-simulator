
package simulator.domain;

import javafx.application.Application;
import simulator.ui.UI;

public class Main {
    public static void main(String[] args){
        /* This weeks goals:
        
            Todos UI:
            - make it so that "new"-button opens a new window instead of changing the view
            - make it so that the user can add more rows to table in creation2 
            - fix description label alignment
            - add a file chooser to creation1
            - connect graphical ui and machine creator
            - ...
        
            Todos domain:
            - created tm is saved in a file instead of printing it
            - when creating a tm check if the name is unique
            - interface for machinecreator?
        */
        UI ui = new UI();
        Application.launch(UI.class); //graphical ui
        
    }
}
