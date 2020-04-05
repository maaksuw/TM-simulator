
package simulator.ui;

import java.io.File;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import simulator.domain.Handler;

public class UI extends Application{
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage stage){
        stage.setTitle("Turing Machine Simulator");
        Handler handle = new Handler();
        
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
        HBox h2 = new HBox();
        h2.setSpacing(10);
        h2.getChildren().addAll(tfname,hox);
        Label projectlb = new Label("Project folder: ");
        Button chooseloc = new Button("Choose location");
        Label location = new Label("");
        HBox h1 = new HBox();
        h1.setSpacing(10);
        String loc = handle.getDefaultFolderLocation();
        if(loc == null){
            loc = "Choose a directory where the project folder will be created."
                    + "\nBy default all projects will be placed in the project folder.";
        }
        location.setText(loc);
        h1.getChildren().addAll(chooseloc,location);
        Label desc = new Label("Description: ");
        TextArea tadesc = new TextArea();
        tadesc.setPrefWidth(400);
        tadesc.setMaxWidth(400);
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10,10,10,10));
        gp.setHgap(10);
        gp.setVgap(10);
        gp.add(name, 0, 0);
        gp.add(h2, 1, 0);
        gp.add(projectlb, 0, 1);
        gp.add(h1, 1, 1);
        gp.add(desc, 0, 2);
        gp.add(tadesc, 1, 2);
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
        
        //"Choose location" -button: Chooses the project folder location if no default folder is set
        chooseloc.setOnAction((event) -> {
            DirectoryChooser dc = new DirectoryChooser();
            dc.setInitialDirectory(new File(System.getProperty("user.home")));
            File selectedDirectory = dc.showDialog(stage);
            if(selectedDirectory != null){
                String path = selectedDirectory.getAbsolutePath() + "/TuringMachineProjects";
                location.setText(path);
            }
        });
        
        //"Cancel" -button: exits the creation scene an returns to main scene with no changes made
        cancel.setOnAction((event) -> {
            //clears the textfields before returning to main scene
            String loca = handle.getDefaultFolderLocation();
            if(loca == null){
                loca = "Choose a directory where the project folder will be created."
                        + "\nBy default all projects will be placed in the project folder.";
            }
            location.setText(loca);
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
            handle.setDefaultFolderLocation(location.getText());
            handle.createProjectFolder();
            if(!handle.create(nm,dsc,ttable)){
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
