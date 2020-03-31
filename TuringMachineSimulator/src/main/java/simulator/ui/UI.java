
package simulator.ui;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import simulator.domain.MachineCreator;
import simulator.domain.TuringMachine;

public class UI extends Application{
    
    @Override
    public void start(Stage stage){
        stage.setTitle("Turing Machine Simulator");
        MachineCreator maker = new MachineCreator();
        
        Button neww = new Button("New");
        VBox menu = new VBox();
        menu.getChildren().addAll(neww);
        menu.setPadding(new Insets(10,10,10,10));
        menu.setSpacing(10);
        BorderPane layout = new BorderPane();
        layout.setLeft(menu);
        
        Scene main = new Scene(layout, 700, 700);
        
        Label name = new Label("Name: ");
        TextField tfname = new TextField();
        tfname.setMaxWidth(200);
        Label desc = new Label("Description: ");
        TextArea tadesc = new TextArea();
        tadesc.setPrefWidth(400);
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10,10,10,10));
        gp.setHgap(10);
        gp.setVgap(10);
        gp.add(name, 0, 0);
        gp.add(tfname, 1, 0);
        gp.add(desc, 0, 1);
        gp.add(tadesc, 1, 1);
        Button finish = new Button("Finish");
        Label inst = new Label("Instructions here!");
        GridPane table = new GridPane();
        table.setVgap(20);
        table.setHgap(20);
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 4; j++){
                TextField tf1 = new TextField();
                tf1.setPrefWidth(50);
                table.add(tf1, i, j);
            }
        }
        VBox v = new VBox();
        v.setPadding(new Insets(10,10,10,10));
        v.setSpacing(20);
        v.getChildren().addAll(gp,inst,table,finish);
        Scene creation = new Scene(v, 600, 500);
        
        neww.setOnAction((event) -> {
            stage.setScene(creation);
        });
        
        finish.setOnAction((event) -> {
            String nm = name.getText();
            String dsc = desc.getText();
            TuringMachine tm = maker.create(nm, dsc);
            stage.setScene(main);
        });
        
        stage.setScene(main);
        stage.show();
    }
    
    public void startCmd(Scanner reader){
        
        MachineCreator maker = new MachineCreator();
        
        System.out.println("Commands: "
                + "\nc) Create a new turing machine"
                + "\nx) Exit the program");
                
        
        while(true){
            System.out.print("Command: ");
            String cmd = reader.nextLine();
            
            if(cmd.equals("x")){
                break;
            }
            else if(cmd.equals("c")){
                
                System.out.println("");
                System.out.println("New turing machine");
                System.out.println("");
                System.out.println("Currently the only acceptable characters are 0 and 1. States will be numbered 0...n. Maximum amount of states is 10."
                        + "\nGive the instructions for the states in order from 0 to n, always first for character 0 and then 1."
                        + "\nGive the instructions in format 'state,character,movement'."
                        + "\nWhen done, press x.");
                
                System.out.print("Name: ");
                String name = reader.nextLine();
                System.out.println("Description:");
                String desc = reader.nextLine();
                
                System.out.println("Input a transition table:");
                ArrayList<String> list = new ArrayList<>();
                while(true){
                    String in = reader.nextLine();
                    if(in.equals("x")) break;
                    if(in.matches("[0-9]{1}[0-1]{1}(L|R)")){
                        list.add(in);
                    }
                }
                System.out.println("");
                maker.create(name, desc);
                System.out.println("Turing machine created succesfully.");
                
            } else {
                System.out.println("Unknown command.");
            }
            
        }
    }

}
