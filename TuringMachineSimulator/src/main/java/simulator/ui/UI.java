
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import simulator.dao.FileManagerDao;
import simulator.dao.FileTMDao;
import simulator.dao.ManagerDao;
import simulator.dao.TMDao;
import simulator.domain.Handler;

public class UI extends Application {
    
    private Handler handle;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        ManagerDao mao = new FileManagerDao();
        TMDao tmdao = new FileTMDao(mao.getProjectFolder());
        handle = new Handler(mao, tmdao);
    }
    
    @Override
    public void start(Stage stage) {
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
        Label hox = new Label("");
        HBox h = new HBox();
        h.setSpacing(10);
        h.getChildren().addAll(tfname, hox);
        Label desc = new Label("Description: ");
        TextArea tadesc = new TextArea();
        tadesc.setPrefWidth(400);
        tadesc.setMaxWidth(400);
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10,10,10,10));
        gp.setHgap(10);
        gp.setVgap(10);
        gp.add(name, 0, 0);
        gp.add(h, 1, 0);
        gp.add(desc, 0, 1);
        gp.add(tadesc, 1, 1);
        Button finish = new Button("Finish");
        Label inst = new Label("Currently the alphabet consists only of 0 and 1 and there must be 4 stages for every machine."
                + "\nGive the instructions in the following order: state, character, movement.");
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
        Button cancel = new Button("Cancel");
        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.getChildren().addAll(finish, cancel);
        VBox v = new VBox();
        v.setPadding(new Insets(10,10,10,10));
        v.setSpacing(20);
        v.getChildren().addAll(gp,inst,table,buttons);
        
        Scene creation = new Scene(v, 700, 600);
        
        //"New"-button: moves to the creation scene
        neww.setOnAction((event) -> {
            stage.setScene(creation);
        }); 
        
        //"Cancel" -button: exits the creation scene an returns to main scene with no changes made
        cancel.setOnAction((event) -> {
            //clears the textfields before returning to main scene
            tfname.clear();
            tadesc.clear();
            hox.setText("");
            for(int i = 0; i < 8; i++){
                nodes.get(i).clear();
            }
            stage.setScene(main);
        });
        
        //"Finish"-button: when finished, creates a turing machine defined by the instructions
        finish.setOnAction((event) -> {
            String nm = tfname.getText();
            String dsc = tadesc.getText();
            ArrayList<String> ttable = new ArrayList<>();
            for(int i = 0; i < 8; i++){
                ttable.add(nodes.get(i).getText());
            }
            if(!handle.createTM(nm,dsc,ttable)){
                hox.setText("Project with the same name already exists.");
                return;
            }
            //clears the textfields before returning to main scene
            tfname.clear();
            tadesc.clear();
            hox.setText("");
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
