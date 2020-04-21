
package simulator.ui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
        //name-row
        Label lname = new Label("Name: ");
        TextField tfname = new TextField();
        tfname.setMaxWidth(200);
        Label hox = new Label("");
        HBox h = new HBox();
        h.setSpacing(10);
        h.getChildren().addAll(tfname, hox);
        //description-row
        Label desc = new Label("Description: ");
        TextArea tadesc = new TextArea();
        tadesc.setPrefWidth(400);
        tadesc.setMaxWidth(400);
        //gridpane for the elements above
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10,10,10,10));
        gp.setHgap(10);
        gp.setVgap(10);
        gp.add(lname, 0, 0);
        gp.add(h, 1, 0);
        gp.add(desc, 0, 1);
        gp.add(tadesc, 1, 1);
        //instructions
        Label inst = new Label("Instructions:"
                            + "\nSimulator uses \"-\" as a blank symbol. \"qa\" is the accepting state"
                            + "\nand \"qr\" is the rejecting state (these are not acceptable names "
                            + "\nfor your own states). Possible movements are left, right "
                            + "\nand no movement written as L, R or N.\n" 
                            + "\nThe first row is for the alphabet, enter one character into one cell."
                            + "\nThe first column is for naming the states, the first state will be used "
                            + "\nas the initial state.\n"
                            + "\nThe other cells are for the instructions: give the instruction for "
                            + "\nthe corresponding state of the row and the character of the column."
                            + "\nGive instructions in the following order separated by space: "
                            + "\ncharacter movement state."
                            + "\nFor example: "
                            + "\na R qar"
                            + "\nwhere 'a' is the character, 'R' is the movement and 'qar' is the state.");
        inst.setMaxWidth(600);
        //transition table
        GridPane table = new GridPane();
        table.setGridLinesVisible(true);
        ArrayList<TextField> nodes = new ArrayList<>();
        ArrayList<TextField> stateslist = new ArrayList<>();
        ArrayList<TextField> alphablist = new ArrayList<>();
        int rows  = 3;
        int columns = 3;
        for(int i = 1; i < columns; i++){
            TextField tf1 = new TextField();
            tf1.setPrefWidth(70);
            table.add(tf1, i, 0);
            alphablist.add(tf1);
        }
        for(int j = 1; j < rows; j++){
            TextField tf1 = new TextField();
            tf1.setPrefWidth(70);
            table.add(tf1, 0, j);
            stateslist.add(tf1);
        }
        for(int i = 1; i < columns; i++){
            for(int j = 1; j < rows; j++){
                TextField tf1 = new TextField();
                tf1.setPrefWidth(70);
                table.add(tf1, i, j);
                nodes.add(tf1);
                
            }
        }
        //transition table related buttons and labels
        Label transitionTable = new Label("Transition table: ");
        transitionTable.setFont(new Font("Arial",15));
        transitionTable.setPadding(new Insets(10,10,10,0));
        Button addColumn = new Button("Add Column");
        Button addRow = new Button("Add Row");
        Button removeColumn = new Button("Remove Column");
        Button removeRow = new Button("Remove Row");
        Label initialState = new Label("(initial state)"); //add later
        HBox h2 = new HBox();
        h2.setPadding(new Insets(10,10,10,0));
        h2.setSpacing(10);
        h2.getChildren().addAll(addRow, addColumn, removeRow, removeColumn);
        //button-row and error label
        Button finish = new Button("Finish");
        Button cancel = new Button("Cancel");
        Label error = new Label("");
        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.getChildren().addAll(finish, cancel);
        //overall layout of creation scene
        VBox v = new VBox();
        v.setPadding(new Insets(10,10,10,10));
        v.setSpacing(20);
        v.getChildren().addAll(gp,inst,error,buttons);
        VBox v2 = new VBox();
        v2.setPadding(new Insets(10,10,10,0));
        v2.setSpacing(10);
        v2.getChildren().addAll(transitionTable,h2,table);
        HBox overall = new HBox();
        overall.setPadding(new Insets(10,10,10,10));
        overall.setSpacing(10);
        overall.getChildren().addAll(v, v2);
        
        Scene creation = new Scene(overall, 1150, 700);
        
        //"New"-button: moves to the creation scene
        neww.setOnAction((event) -> {
            stage.setScene(creation);
        });
        
        //"Add Row" -button
        addRow.setOnAction((event) -> {
            int columnCount = table.getColumnCount();
            Node[] nodess = new Node[columnCount];
            for(int i = 0; i < columnCount; i++){
                TextField tf1 = new TextField();
                tf1.setPrefWidth(70);
                nodess[i] = tf1;
                if(i == 0) stateslist.add(tf1);
                else { nodes.add(tf1); }
            }
            int rowCount = table.getRowCount();
            table.addRow(rowCount, nodess);
        });
        
        //"Add Column" -button
        addColumn.setOnAction((event) -> {
            int rowCount = table.getRowCount();
            Node[] nodess = new Node[rowCount];
            for(int i = 0; i < rowCount; i++){
                TextField tf1 = new TextField();
                tf1.setPrefWidth(70);
                nodess[i] = tf1;
                if(i == 0) alphablist.add(tf1);
                else { nodes.add(tf1); }
            }
            int columnCount = table.getColumnCount();
            table.addColumn(columnCount, nodess);
        });
        
        //"Remove Row" -button
        removeRow.setOnAction((event) -> {
            ObservableList<Node> children = table.getChildren();
            int rowCount = table.getRowCount();
            if(rowCount <= 2) return;
            Node[] nodess = new Node[table.getColumnCount()];
            int idx = 0;
            for(Node n: children){
                if(GridPane.getRowIndex(n) != null && GridPane.getRowIndex(n) == (rowCount - 1)) {
                    nodess[idx] = n;
                    idx++;
                    if(GridPane.getColumnIndex(n) == 0) {
                        stateslist.remove((TextField) n);
                    }
                    else { nodes.remove((TextField) n); }
                }
            }
            table.getChildren().removeAll(nodess);
        });
        
        //"Remove Column" -button
        removeColumn.setOnAction((event) -> {
            ObservableList<Node> children = table.getChildren();
            int columnCount = table.getColumnCount();
            if(columnCount <= 2) return;
            Node[] nodess = new Node[table.getRowCount()];
            int idx = 0;
            for(Node n: children){
                if(GridPane.getColumnIndex(n) != null && GridPane.getColumnIndex(n) == (columnCount - 1)){
                    nodess[idx] = n;
                    idx++;
                    if(GridPane.getRowIndex(n) == 0){
                        alphablist.remove((TextField) n);
                    } else { nodes.remove((TextField) n); }
                }
            }
            table.getChildren().removeAll(nodess);
        });
        
        //"Cancel" -button: exits the creation scene an returns to main scene with no changes made
        cancel.setOnAction((event) -> {
            //clears the textfields before returning to main scene
            tfname.clear();
            tadesc.clear();
            hox.setText("");
            for(Node n: table.getChildren()){
                if((GridPane.getRowIndex(n) == null || GridPane.getColumnIndex(n) == null) || (GridPane.getRowIndex(n) == 0 && GridPane.getColumnIndex(n) == 0)) continue;
                TextField t = (TextField) n;
                t.clear();
            }
            stage.setScene(main);
        });
        
        //"Finish"-button: when finished, creates a turing machine defined by the instructions
        finish.setOnAction((event) -> {
            String name = tfname.getText().trim();
            String dsc = tadesc.getText().trim();
            int rowCount = table.getRowCount();
            int columnCount = table.getColumnCount();
            String[][] ttable = new String[rowCount - 1][columnCount - 1];
            for(int i = 0; i < nodes.size(); i++){
                Node n = nodes.get(i);
                int row = GridPane.getRowIndex(n);
                int column = GridPane.getColumnIndex(n);
                ttable[row-1][column-1] = nodes.get(i).getText().trim();
            }
            
            int alphasize = alphablist.size();
            char[] alphabet = new char[alphasize];
            for(int i = 0; i < alphasize; i++){
                Node n = alphablist.get(i);
                int column = GridPane.getColumnIndex(n);
                if(alphablist.get(i).getText().trim().length() > 1) {} //lets check here the characters are really just one character
                alphabet[column-1] = alphablist.get(i).getText().trim().charAt(0);
            }
            
            int statesize = stateslist.size();
            String[] states = new String[statesize];
            for(int i = 0; i < statesize; i++){
                Node n = stateslist.get(i);
                int row = GridPane.getRowIndex(n);
                states[row-1] = stateslist.get(i).getText().trim();
            }
            
            boolean create = handle.createTM(name, dsc, ttable, alphabet, states);
            if(!create){
                hox.setText("Project with the same name already exists.");
                return;
            }
            //clears the textfields before returning to main scene
            tfname.clear();
            tadesc.clear();
            hox.setText("");
            for(Node n: table.getChildren()){
                if((GridPane.getRowIndex(n) == null || GridPane.getColumnIndex(n) == null) || (GridPane.getRowIndex(n) == 0 && GridPane.getColumnIndex(n) == 0)) continue;
                TextField t = (TextField) n;
                t.clear();
            }
            stage.setScene(main);
        });
        
        //by default shows the main scene
        stage.setScene(main);
        stage.show();
    }
    
}
