
package simulator.domain;

import java.util.ArrayList;
import simulator.ui.UI;

public class Main {
    public static void main(String[] args) {
        /* This weeks goals:
            - remove source.txt
            - figure how to make junit tests for daos
        
            Todos UI:
            - create gui for simulation
            - create the menu for main scene
            - make it so that you can't finish without giving the proper information in creation scene    
            - Add "-" column, (initial state) tag, alphabet and states labels to the table
            - fill the table with default properties
            - make it so that "new"-button opens a new window instead of changing the view
            - fix description label alignment
            - ...
        
            Todos domain:
            - make it so that user can define initial state, accepting and rejecting states
            - more choices in creation "ignore all characters but x, ..."
            - ...
        */
        char[] alphabet = new char[3];
        alphabet[0] = 'a';
        alphabet[1] = 'b';
        alphabet[2] = '-';
        String[] states = new String[7];
        states[0] = "qA";
        states[1] = "qar";
        states[2] = "qbr";
        states[3] = "qCa";
        states[4] = "qCb";
        states[5] = "qL";
        states[6] = "qB";
        Instruction[][] t2 = new Instruction[7][3];
        t2[0][0] = new Instruction('A','R',"qA");
        t2[0][2] = new Instruction('-','R',"qA");
        Instruction[][] t = new Instruction[7][3];
        t[0][0] = new Instruction('-','R',"qar");
        t[0][1] = new Instruction('-','R',"qbr");
        t[0][2] = new Instruction("qa");
        t[1][0] = new Instruction('a','R',"qar");
        t[1][1] = new Instruction('b','R',"qar");
        t[1][2] = new Instruction('-','L',"qCa");
        t[2][0] = new Instruction('a','R',"qbr");
        t[2][1] = new Instruction('b','R',"qbr");
        t[2][2] = new Instruction('-','L',"qCb");
        t[3][0] = new Instruction('-','L',"qL");
        t[3][1] = new Instruction("qr");
        t[3][2] = new Instruction("qa");
        t[4][0] = new Instruction("qr");
        t[4][1] = new Instruction('-','L',"qL");
        t[4][2] = new Instruction("qa");
        t[5][0] = new Instruction('a','L',"qB");
        t[5][1]= new Instruction('b','L',"qB");
        t[5][2] = new Instruction("qa");
        t[6][0] = new Instruction('a','L',"qB");
        t[6][1] = new Instruction('b','L',"qB");
        t[6][2] = new Instruction('-','R',"qA");
        TuringMachine tm = new TuringMachine("Makke","Auttaa Pinjaa testaamaan.",t,alphabet,states);
        TuringMachine tm2 = new TuringMachine("Masa","Toinen testikaveri.",t2,alphabet,states);
        String input2 = "aaa";
        String input;
        String str = "babbabbbababbababbababbbaabbaaaa";
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            str2 = str.charAt(i) + str2;
        }
        input = str+str2;
        input = "abbaabaabba";
        Simulator sakke = new Simulator(tm);
        System.out.println("\n" + sakke.simulate(input));
//        sakke.setTm(tm2);
//        System.out.println("\n" + sakke.simulate(input2));
        UI.main(args);
    }
}
