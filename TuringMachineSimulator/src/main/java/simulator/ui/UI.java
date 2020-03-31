
package simulator.ui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import simulator.domain.TuringMachine;

public class UI extends Application{
    
    @Override
    public void start(Stage stage){
        stage.setTitle("Turing Machine Simulator");
        
        //creates elements and sets the main scene
        Button neww = new Button("New");
        VBox menu = new VBox();
        menu.getChildren().addAll(neww);
        menu.setPadding(new Insets(10,10,10,10));
        menu.setSpacing(10);
        BorderPane layout = new BorderPane();
        layout.setLeft(menu);
        
        Scene main = new Scene(layout, 700, 700);
        
        //creates elements and sets the creation scene
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
        ArrayList<TextField> nodes = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 4; j++){
                TextField tf1 = new TextField();
                tf1.setPrefWidth(50);
                table.add(tf1, i, j);
                nodes.add(tf1);
            }
        }
        VBox v = new VBox();
        v.setPadding(new Insets(10,10,10,10));
        v.setSpacing(20);
        v.getChildren().addAll(gp,inst,table,finish);
        
        Scene creation = new Scene(v, 600, 500);
        
        //"New"-button: moves to the creation scene
        neww.setOnAction((event) -> {
            stage.setScene(creation);
        });
        
        //"Finish"-button: when finished, creates a turing machine defined by the instructions
        finish.setOnAction((event) -> {
            String nm = name.getText();
            String dsc = desc.getText();
            TuringMachine tm = new TuringMachine(nm, dsc);
            ArrayList<String> ttable = new ArrayList<>();
            for(int i = 0; i < 8; i++){
                ttable.add(nodes.get(i).getText());
            }
            tm.setTable(ttable);
            //clears the textfields before returning to main scene
            tfname.clear();
            tadesc.clear();
            for(int i = 0; i < 8; i++){
                nodes.get(i).clear();
            }
            stage.setScene(main);
        });
        
        //by default shows the main scene
        stage.setScene(main);
        stage.show();
    }
}
