
package simulator.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import simulator.dao.FileTMDao;
import simulator.dao.TMDao;
import simulator.domain.Handler;

public class UI extends Application {
    
    private Handler handle;
    private final int canvasWidth = 803;
    private final int canvasHeight = 200;
    private final int mainWindowWidth = 1105;
    private final int mainWindowHeight = 450;
    private final int creationWindowWidth = 1050;
    private final int creationWindowHeight = 650;
    private final int drawerFontSize = 14;
    private final int tapeLength = 49;
    private double[] headX = new double[]{(canvasWidth/2) + 2, (canvasWidth/2) + 9, (canvasWidth/2) + 16};
    private double[] headY = new double[]{78,90,78};
    private final int inputLimit = 5000000;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        TMDao tmdao = new FileTMDao();
        handle = new Handler(tmdao);
    }
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("Turing Machine Simulator");
        stage.setMaxWidth(mainWindowWidth);
        stage.setMaxHeight(mainWindowHeight);
        stage.setResizable(false);
        Stage creationWindow = new Stage();
        creationWindow.setMaxWidth(creationWindowWidth);
        creationWindow.setMaxHeight(creationWindowHeight);
        creationWindow.setResizable(false);
        
        //**creates elements for the main scene**
        //*menu buttons at the top*
        Button createNew = new Button("Create");
        Button open = new Button("Open");
        ToggleGroup choises = new ToggleGroup();
        RadioButton atOnce = new RadioButton("Simulate without showing steps in-between");
        atOnce.setSelected(true);
        atOnce.setToggleGroup(choises);
        RadioButton manually = new RadioButton("Simulate manually");
        manually.setToggleGroup(choises);
        VBox automaticVBox = new VBox();
        automaticVBox.setSpacing(10);
        RadioButton automatic = new RadioButton("Simulate automatically");
        automatic.setToggleGroup(choises);
        Slider howFast = new Slider(1,4,1);
        howFast.setShowTickMarks(true);
        howFast.setShowTickLabels(true);
        howFast.setSnapToTicks(true);
        howFast.setMajorTickUnit(1);
        howFast.setMinorTickCount(0);
        howFast.setLabelFormatter(new StringConverter<Double>() {
            
            @Override
            public String toString(Double d) {
                if (d < 2) return "Slow";
                if (d < 3) return "Normal";
                if (d < 4) return "Fast";
                else {
                    return "Very fast";
                }
            }

            @Override
            public Double fromString(String string) {
                if (string.equals("Slow")) return 1d;
                if (string.equals("Normal")) return 2d;
                if (string.equals("Fast")) return 3d;
                else {
                    return 4d;
                }
            }
            
        });
        automaticVBox.getChildren().addAll(automatic, howFast);
        HBox mainMenu = new HBox();
        mainMenu.getChildren().addAll(createNew, open, atOnce, manually, automaticVBox);
        mainMenu.setPadding(new Insets(10,10,2,60));
        mainMenu.setSpacing(15);
        
        //*creates the visualizer, input box and simulate-buttons*
        VBox simulationArea = new VBox();
        simulationArea.setAlignment(Pos.CENTER_RIGHT);
        simulationArea.setPadding(new Insets(0,20,20,20));
        simulationArea.setSpacing(10);
        
        //canvas
        Label tmName = new Label("");
        tmName.setFont(new Font("Arial",18));
        Canvas canvas = new Canvas(canvasWidth,canvasHeight);
        GraphicsContext drawer = canvas.getGraphicsContext2D();
        drawer.setFill(Color.WHITE);
        drawer.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawer.setFill(Color.LAVENDER);
        drawer.fillRoundRect(401, 93, 18, 24, 5, 5);
        drawer.setFill(Color.BLACK);
        drawer.setFont(Font.font(java.awt.Font.MONOSPACED,drawerFontSize));
        StringBuilder tape = new StringBuilder();
        for (int i = 0; i < tapeLength; i++) {
            tape.append("_ ");
        }
        drawer.strokeText(tape.toString(), 1, 110);
        drawer.fillPolygon(headX, headY, 3);
        drawer.strokeText("Steps: ", 25, 30);
        
        //input-line
        HBox inputLine = new HBox();
        inputLine.setSpacing(8);
        inputLine.setAlignment(Pos.CENTER_RIGHT);
        Label inputLabel = new Label("Input: ");
        TextField input = new TextField();
        input.setPrefWidth(345);
        Label stepLimitLabel = new Label("Step limit: ");
        TextField stepLimit = new TextField();
        stepLimit.setText("1000000");
        stepLimit.setPrefWidth(100);
        Label tapeSizeLabel = new Label("Tape size limit: ");
        TextField tapeSize = new TextField("");
        tapeSize.setText("3000000");
        tapeSize.setPrefWidth(100);
        inputLine.getChildren().addAll(tapeSizeLabel,tapeSize,stepLimitLabel,stepLimit,inputLabel,input);
        
        //simulate-buttons
        HBox startButtons = new HBox();
        startButtons.setSpacing(10);
        startButtons.setAlignment(Pos.CENTER_RIGHT);
        Button start = new Button("Start simulation");
        startButtons.getChildren().add(start);
        start.setDisable(true);
        Button simulateStep = new Button("Simulate step");
        HBox results = new HBox();
        results.setAlignment(Pos.CENTER_RIGHT);
        Label resultLabel = new Label("Result: ");
        Label result = new Label("");
        results.getChildren().addAll(resultLabel,result);
        
        simulationArea.getChildren().addAll(tmName,canvas,inputLine,results,startButtons);
        
        //*information - name, description, alphabet and states listed on the right*
        VBox information = new VBox();
        information.setPadding(new Insets(0,20,20,0));
        information.setSpacing(10);
        information.setAlignment(Pos.CENTER_LEFT);
        Label currentTMname = new Label("");
        currentTMname.setWrapText(true);
        currentTMname.setMaxWidth(200);
        currentTMname.setPrefWidth(200);
        Label currentTMdescription = new Label("");
        currentTMdescription.setWrapText(true);
        currentTMdescription.setMaxWidth(200);
        Label currentTMalphabet = new Label("");
        currentTMalphabet.setWrapText(true);
        currentTMalphabet.setMaxWidth(200);
        information.getChildren().addAll(currentTMname, currentTMdescription, currentTMalphabet);
        
        //*AnimationTimer-object for animating the simulation*
        AnimationTimerExtra loopF = new AnimationTimerExtra() {
            
            long previous = 0;
            long interval = 0;
            boolean first = true;
            
            @Override
            public void setInterval(long interval) {
                this.interval = interval * 1000000;
            }
            
            @Override
            public void resetFirst() {
                this.first = true;
            }
            
            @Override
            public void handle(long now) {
                if ((now - previous) < interval) {
                    return;
                }
                if (first) {
                    drawer.strokeText(handle.getTape(), 1, 110);
                    drawer.strokeText(handle.getState(), 405, 70);
                    drawer.fillPolygon(headX, headY, 3);
                    drawer.strokeText("Steps: " + handle.getSteps(), 25, 30);
                    this.previous = now;
                    first = false;
                    return;
                }
                String step = handle.simulateStep();
                if (step.equals("Accepted") || step.equals("Rejected") || step.equals("Tape limit exceeded.") || step.equals("Undefined character and state combination.") || step.equals("Bad input for this machine.")) {
                    result.setText(step);
                    this.stop();
                    first = true;
                    return;
                } else if (step.equals("Turing machine did not halt after")) {
                    result.setText(step + " " + stepLimit.getText() + " steps.");
                    this.stop();
                    first = true;
                    return;
                }
                drawer.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                drawer.setFill(Color.WHITE);
                drawer.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                drawer.setFill(Color.LAVENDER);
                drawer.fillRoundRect(401, 93, 18, 24, 5, 5);
                drawer.setFill(Color.BLACK);
                drawer.strokeText(step, 1, 110);
                drawer.strokeText(handle.getState(), 405, 70);
                drawer.fillPolygon(headX, headY, 3);
                drawer.strokeText("Steps: " + handle.getSteps(), 25, 30);
                this.previous = now; 
            }
            
        };
        
        //*overall layout*
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(mainMenu);
        mainLayout.setCenter(simulationArea);
        mainLayout.setRight(information);
        
        Scene main = new Scene(mainLayout, mainWindowWidth, mainWindowHeight);
        
        //**builds the creation scene**
        //name-row
        Label nameLabel = new Label("Name: ");
        TextField nameTextField = new TextField();
        nameTextField.setMaxWidth(200);
        
        //description-row
        Label descriptionLabel = new Label("Description: ");
        TextArea descriptionTextArea = new TextArea();
        descriptionTextArea.setPrefWidth(400);
        descriptionTextArea.setMaxWidth(400);
        
        //gridpane for the elements above
        GridPane nameDescriptionGridPane = new GridPane();
        nameDescriptionGridPane.setPadding(new Insets(10,10,10,10));
        nameDescriptionGridPane.setHgap(10);
        nameDescriptionGridPane.setVgap(10);
        nameDescriptionGridPane.add(nameLabel, 0, 0);
        nameDescriptionGridPane.add(nameTextField, 1, 0);
        nameDescriptionGridPane.add(descriptionLabel, 0, 1);
        nameDescriptionGridPane.add(descriptionTextArea, 1, 1);
        
        //instructions
        Label instructions = new Label("Instructions:"
                            + "\nSimulator uses \"_\" as a blank symbol. \"qa\" is the accepting state and \"qr\" is the rejecting state (these are not acceptable names for your own states). "
                            + "Possible movements are left, right and no movement written as L, R or N.\n" 
                            + "\nThe first row is for the alphabet, enter one character into one cell."
                            + "\nThe first column is for naming the states, the first state will be used as the initial state.\n"
                            + "\nThe other cells are for the instructions: give the instruction for the corresponding state of the row and the character of the column."
                            + "\nGive instructions in the following order separated by space: "
                            + "\ncharacter movement state"
                            + "\nFor example: "
                            + "\na R qar"
                            + "\nwhere 'a' is the character, 'R' is the movement and 'qar' is the state.");
        instructions.setWrapText(true);
        instructions.setMaxWidth(500);
        instructions.setPrefWidth(500);
        
        // transition table label and add/remove row/column -buttons
        Label transitiontableLabel = new Label("Transition table: ");
        transitiontableLabel.setFont(new Font("Arial",15));
        transitiontableLabel.setPadding(new Insets(10,10,10,0));
        Button addColumn = new Button("Add Column");
        Button addRow = new Button("Add Row");
        Button removeColumn = new Button("Remove Column");
        Button removeRow = new Button("Remove Row");
        HBox addRemoveButtons = new HBox();
        addRemoveButtons.setPadding(new Insets(10,10,10,0));
        addRemoveButtons.setSpacing(10);
        addRemoveButtons.getChildren().addAll(addRow, removeRow, addColumn, removeColumn);
        
        //transition table
        GridPane transitiontable = new GridPane();
        transitiontable.setGridLinesVisible(true);
        ArrayList<TextField> nodes = new ArrayList<>();
        ArrayList<TextField> stateslist = new ArrayList<>();
        ArrayList<TextField> alphablist = new ArrayList<>();
        TextField blankSymbol = new TextField();
        int rows  = 3;
        int columns = 4;
        char defaultCharacter = 'a';
        for (int i = 1; i < columns; i++) {
            if (i == 1) {
                blankSymbol.setText("_");
                blankSymbol.setEditable(false);
                blankSymbol.setPrefWidth(70);
                transitiontable.add(blankSymbol, i, 0);
                alphablist.add(blankSymbol);
            } else {
                TextField tf = new TextField();
                tf.setPrefWidth(70);
                tf.setText(defaultCharacter + "");
                transitiontable.add(tf, i, 0);
                alphablist.add(tf);
                defaultCharacter++;
            }
        }
        for (int j = 1; j < rows; j++) {
            TextField tf = new TextField();
            tf.setPrefWidth(70);
            tf.setText("state" + j);
            transitiontable.add(tf, 0, j);
            stateslist.add(tf);
        }
        for (int i = 1; i < columns; i++) {
            for (int j = 1; j < rows; j++) {
                TextField tf = new TextField();
                tf.setPrefWidth(70);
                transitiontable.add(tf, i, j);
                nodes.add(tf);
            }
        }
        
        //scrollpane for transition table and initial state label
        HBox tableBox = new HBox();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(transitiontable);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setMaxWidth(368);
        Label initialState = new Label("(initial state)");
        initialState.setPadding(new Insets(30,0,0,0));
        tableBox.getChildren().addAll(initialState, scrollPane);
        tableBox.setSpacing(10);
        
        //finish/cancel -buttons and error label
        Button finish = new Button("Finish");
        Button cancel = new Button("Cancel");
        Label error = new Label("");
        error.setTextFill(Color.RED);
        error.setMaxWidth(500);
        error.setPrefHeight(50);
        error.setWrapText(true);
        HBox finishCancelbuttons = new HBox();
        finishCancelbuttons.setSpacing(10);
        finishCancelbuttons.getChildren().addAll(finish, cancel);
        
        //*overall layout of creation scene*
        VBox leftSide = new VBox();
        leftSide.setPadding(new Insets(10,10,10,10));
        leftSide.setSpacing(10);
        leftSide.getChildren().addAll(nameDescriptionGridPane,instructions,error,finishCancelbuttons);
        VBox rightSide = new VBox();
        rightSide.setPadding(new Insets(10,10,10,0));
        rightSide.setSpacing(10);
        rightSide.getChildren().addAll(transitiontableLabel,addRemoveButtons,tableBox);
        HBox creationLayout = new HBox();
        creationLayout.setPadding(new Insets(10,10,10,10));
        creationLayout.setSpacing(10);
        creationLayout.getChildren().addAll(leftSide, rightSide);
        
        Scene creation = new Scene(creationLayout, creationWindowWidth, creationWindowHeight);
        
        //**all button actions**
        //"Start simulation" -button starts the simulation
        start.setOnAction((event) -> {
            //reading and checking the inputs
            result.setText("");
            String stepLimitString = stepLimit.getText().trim();
            String tapeSizeLimitString = tapeSize.getText().trim();
            if (!isNumeric(stepLimitString)) {
                result.setText("Invalid step limit.");
                return;
            }
            if (!isNumeric(tapeSizeLimitString)) {
                result.setText("Invalid tape size limit.");
                return;
            }
            long stepLimitLong = format(stepLimitString);
            long tapeLimitLong = format(tapeSizeLimitString);
            String inputString = input.getText().trim();
            int inputSize = inputString.length();
            if (inputSize > inputLimit) {
                result.setText("Input is too large.");
                return;
            }
            if (tapeLimitLong < 500) {
                tapeLimitLong = 500;
                tapeSize.setText("" + tapeLimitLong);
            }
            
            //initializing the visualizer, resetting the animation loop and checking if simulate step -button needs to be removed
            drawer.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            drawer.setFill(Color.WHITE);
            drawer.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            drawer.setFill(Color.LAVENDER);
            drawer.fillRoundRect(401, 93, 18, 24, 5, 5);
            drawer.setFill(Color.BLACK);
            loopF.stop();
            loopF.resetFirst();
            if (startButtons.getChildren().contains(simulateStep)) {
                startButtons.getChildren().remove(simulateStep);
            }
            
            //simulating according to selected style
            if (atOnce.isSelected()) {
                drawer.strokeText(tape.toString(), 1, 110);
                drawer.fillPolygon(headX, headY, 3);
                String outcome = handle.simulate(inputString, stepLimitLong, tapeLimitLong);
                drawer.strokeText("Steps: " + handle.getSteps(), 25, 30);
                if (outcome.equals("Turing machine did not halt after")) {
                    result.setText(outcome + " " + stepLimitLong + " steps.");
                } else {
                    result.setText(outcome);
                }
            }
            if (manually.isSelected()) {
                if (handle.setUpStepByStep(inputString, stepLimitLong, tapeLimitLong)) {
                    drawer.strokeText(handle.getTape(), 1, 110);
                    drawer.strokeText(handle.getState(), 405, 70);
                    drawer.fillPolygon(headX, headY, 3);
                    drawer.strokeText("Steps: " + handle.getSteps(), 25, 30);
                    startButtons.getChildren().add(simulateStep);
                } else {
                    result.setText("Input size exceeds tape limit.");
                }
            }
            if (automatic.isSelected()) {
                if (handle.setUpStepByStep(inputString, stepLimitLong, tapeLimitLong)) {
                    int level = (int) howFast.getValue();
                    if (level == 1) {
                        level = 1000;
                    } else if (level == 2) {
                        level = 200;
                    } else if (level == 3) {
                        level = 20;
                    } else {
                        level = 5;
                    }
                    loopF.setInterval(level);
                    loopF.start();
                } else {
                    result.setText("Input size exceeds tape limit.");
                }
            }
        });
        
        //"Simulate step" -button simulates a step
        simulateStep.setOnAction((event) -> {
            String step = handle.simulateStep();
            long counter = handle.getSteps();
                if (step.equals("Accepted") || step.equals("Rejected") || step.equals("Tape limit exceeded.") || step.equals("Undefined character and state combination.") || step.equals("Bad input for this machine.")) {
                    result.setText(step);
                    startButtons.getChildren().remove(simulateStep);
                    return;
                } else if (step.equals("Turing machine did not halt after")) {
                    result.setText(step + " " + stepLimit.getText() + " steps.");
                    startButtons.getChildren().remove(simulateStep);
                    return;
                }
                drawer.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                drawer.setFill(Color.WHITE);
                drawer.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                drawer.setFill(Color.LAVENDER);
                drawer.fillRoundRect(401, 93, 18, 24, 5, 5);
                drawer.setFill(Color.BLACK);
                drawer.strokeText(step, 1, 110);
                drawer.strokeText(handle.getState(), 405, 70);
                drawer.fillPolygon(headX, headY, 3);
                drawer.strokeText("Steps: " + counter, 25, 30);
        });
        
        //"Create"-button: moves to the creation scene
        createNew.setOnAction((event) -> {
            creationWindow.setTitle("Create a Turing Machine");
            creationWindow.setScene(creation);
            blankSymbol.setText("_");
            creationWindow.show();
        });
        
        //"Open" -button: opens a file chooser
        open.setOnAction((event) -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Choose Turing Machine");
            fc.setInitialDirectory(new File(handle.getProjectFolder()));
            File f = fc.showOpenDialog(stage);
            if (f != null) {
                handle.setUpTM(f);
                tmName.setText(handle.getCurrentTMName());
                currentTMname.setText("Name: " + handle.getCurrentTMName());
                currentTMdescription.setText("Description: " + handle.getCurrentTMDescription());
                currentTMalphabet.setText("Alphabet: " + handle.getCurrentTMAlphabet());
                start.setDisable(false);
            }
        });
        
        //"Add Row" -button
        addRow.setOnAction((event) -> {
            int columnCount = transitiontable.getColumnCount();
            Node[] nodeList = new Node[columnCount];
            for (int i = 0; i < columnCount; i++) {
                TextField tf = new TextField();
                tf.setPrefWidth(70);
                nodeList[i] = tf;
                if (i == 0) stateslist.add(tf);
                else { nodes.add(tf); }
            }
            int rowCount = transitiontable.getRowCount();
            transitiontable.addRow(rowCount, nodeList);
        });
        
        //"Add Column" -button
        addColumn.setOnAction((event) -> {
            int rowCount = transitiontable.getRowCount();
            Node[] nodeList = new Node[rowCount];
            for (int i = 0; i < rowCount; i++) {
                TextField tf = new TextField();
                tf.setPrefWidth(70);
                nodeList[i] = tf;
                if (i == 0) alphablist.add(tf);
                else { nodes.add(tf); }
            }
            int columnCount = transitiontable.getColumnCount();
            transitiontable.addColumn(columnCount, nodeList);
            if (columnCount >= 5) {
                transitiontable.setPadding(new Insets(0,0,17,0));
            }
        });
        
        //"Remove Row" -button
        removeRow.setOnAction((event) -> {
            ObservableList<Node> children = transitiontable.getChildren();
            int rowCount = transitiontable.getRowCount();
            if (rowCount <= 2) return;
            Node[] nodess = new Node[transitiontable.getColumnCount()];
            int idx = 0;
            for (Node n: children) {
                if (GridPane.getRowIndex(n) != null && GridPane.getRowIndex(n) == (rowCount - 1)) {
                    nodess[idx] = n;
                    idx++;
                    if (GridPane.getColumnIndex(n) == 0) {
                        stateslist.remove((TextField) n);
                    } else { nodes.remove((TextField) n); }
                }
            }
            transitiontable.getChildren().removeAll(nodess);
        });
        
        //"Remove Column" -button
        removeColumn.setOnAction((event) -> {
            ObservableList<Node> children = transitiontable.getChildren();
            int columnCount = transitiontable.getColumnCount();
            if (columnCount <= 2) return;
            if (columnCount <= 6) {
                transitiontable.setPadding(new Insets(0,0,0,0));
            }
            Node[] nodeList = new Node[transitiontable.getRowCount()];
            int idx = 0;
            for (Node n: children) {
                if (GridPane.getColumnIndex(n) != null && GridPane.getColumnIndex(n) == (columnCount - 1)) {
                    nodeList[idx] = n;
                    idx++;
                    if (GridPane.getRowIndex(n) == 0) {
                        alphablist.remove((TextField) n);
                    } else { nodes.remove((TextField) n); }
                }
            }
            transitiontable.getChildren().removeAll(nodeList);
        });
        
        //"Cancel" -button: exits the creation scene an returns to main scene with no changes made
        cancel.setOnAction((event) -> {
            //clears the textfields before returning to main scene
            nameTextField.clear();
            descriptionTextArea.clear();
            error.setText("");
            for (Node n: transitiontable.getChildren()) {
                if ((GridPane.getRowIndex(n) == null || GridPane.getColumnIndex(n) == null) || (GridPane.getRowIndex(n) == 0 && GridPane.getColumnIndex(n) == 0)) continue;
                TextField t = (TextField) n;
                t.clear();
            }
            creationWindow.close();
            stage.setScene(main);
        });
        
        //"Finish"-button: when finished, creates a turing machine defined by the instructions
        finish.setOnAction((event) -> {
            //checks the name
            String name = nameTextField.getText().trim();
            if (name.isEmpty()) {
                error.setText("Give a name to the Turing machine.");
                return;
            }
            //checks the description
            String description = descriptionTextArea.getText().trim();
            if (description.isEmpty()) {
                error.setText("Describe the Turing machine shortly.");
                return;
            }
            //checks the alphabet
            boolean[] checkerAlpha = new boolean[130];
            int alphasize = alphablist.size();
            char[] alphabet = new char[alphasize];
            for (int i = 0; i < alphasize; i++) {
                Node n = alphablist.get(i);
                int column = GridPane.getColumnIndex(n);
                String characterCanditate = alphablist.get(i).getText().trim();
                if (characterCanditate.length() > 1) {
                    error.setText("The alphabet must only contain characters.");
                    return;
                } else if (characterCanditate.length() < 1) {
                    error.setText("Please don't leave any empty columns or rows.");
                    return;
                } else if (!characterCanditate.matches("[a-z]|[A-Z]|å|Å|ä|Ä|ö|Ö|_|[0-9]|#|£|<|>|!|@|$|%|&|\\?|\\*|-|\\+")) {
                    error.setText("Please only use characters a-z, A-Z, å-ö, Å-Ö, special characters #, £, <, >, !, @, $, %, &, ?, *, -, + or numbers in the alphabet.");
                    return;
                }
                char character = characterCanditate.charAt(0);
                if (checkerAlpha[character] == false) {
                    checkerAlpha[character] = true;
                } else {
                    error.setText("Characters in the alphabet must be defined only once.");
                    return;
                }
                alphabet[column-1] = character;
            }
            
            //checks the states
            HashSet<String> checkerBeta = new HashSet<>();
            int stateSize = stateslist.size();
            String[] states = new String[stateSize];
            for (int i = 0; i < stateSize; i++) {
                Node node = stateslist.get(i);
                int row = GridPane.getRowIndex(node);
                String stateName = stateslist.get(i).getText().trim();
                if (stateName.isEmpty()) {
                    error.setText("Please don't leave any empty columns or rows.");
                    return;
                } else if (!stateName.matches("([a-z]|[A-Z]|å|Å|ä|Ä|ö|Ö|[0-9]|_|#|£|<|>|!|@|$|%|&|\\?|\\*|-|\\+)*")) {
                    error.setText("Please only use characters a-z, A-Z, å-ö, Å-Ö, special characters _, #, £, <, >, !, @, $, %, &, ?, *, -, + or numbers in the state names.");
                    return;
                } else if (stateName.equals("qa") || stateName.equals("qr")) {
                    error.setText("\"qa\" and \"qr\" are not valid state names.");
                    return;
                }
                if (checkerBeta.contains(stateName)) {
                    error.setText("State names must be unique.");
                    return;
                } else {
                    checkerBeta.add(stateName);
                }
                states[row-1] = stateName;
            }
            
            //checks the instructions
            int rowCount = transitiontable.getRowCount();
            int columnCount = transitiontable.getColumnCount();
            String[][] ttable = new String[rowCount - 1][columnCount - 1];
            for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);
                int row = GridPane.getRowIndex(node);
                int column = GridPane.getColumnIndex(node);
                String instruction = nodes.get(i).getText().trim();
                if (instruction.isEmpty()) {
                    error.setText("Fill in all of the instructions.");
                    return;
                }
                if (!instruction.matches("(([a-z]|[A-Z]|å|Å|ä|Ä|ö|Ö|_|[0-9]|#|£|<|>|!|@|$|%|&|\\?|\\*|-|\\+)( L | R | N )([a-z]|[A-Z]|å|Å|ä|Ä|ö|Ö|[0-9]|_|#|£|<|>|!|@|$|%|&|\\?|\\*|-|\\+)*)|qa|qr")) {
                    error.setText("Instruction \"" + instruction + "\" is not a valid instruction.");
                    return;
                }
                if (instruction.length() > 2) {
                    char character = instruction.charAt(0);
                    String stateName = instruction.substring(4, instruction.length());
                    if (checkerAlpha[character] == false) {
                        error.setText("Instruction \"" + instruction + "\" is not a valid instruction.");
                        return;
                    } else if (!checkerBeta.contains(stateName) && !stateName.equals("qa") && !stateName.equals("qr")) {
                        error.setText("Instruction \"" + instruction + "\" is not a valid instruction.");
                        return;
                    }
                }
                ttable[row-1][column-1] = instruction;
            }
            
            //if all the checks are passed, creates a new Turing machine
            boolean create = handle.createTM(name, description, ttable, alphabet, states);
            if (!create) {
                error.setText("Project with the same name already exists.");
                return;
            }
            
            //clears the textfields before returning to main scene
            nameTextField.clear();
            descriptionTextArea.clear();
            error.setText("");
            char defaultCharacter2 = 'a' - 1;
            int number = 1;
            for (Node n: transitiontable.getChildren()) {
                if ((GridPane.getRowIndex(n) == null && GridPane.getColumnIndex(n) == null)) continue;
                TextField tf = (TextField) n;
                if (GridPane.getRowIndex(n) == null || GridPane.getRowIndex(n) == 0) {
                    tf.setText(defaultCharacter2 + "");
                    defaultCharacter2++;
                } else if (GridPane.getColumnIndex(n) == null || GridPane.getColumnIndex(n) == 0) {
                    tf.setText("state" + number);
                    number++;
                } else {
                    tf.clear();
                }
            }
            tmName.setText(handle.getCurrentTMName());
            currentTMname.setText("Name: " + handle.getCurrentTMName());
            currentTMdescription.setText("Description: " + handle.getCurrentTMDescription());
            currentTMalphabet.setText("Alphabet: "  +handle.getCurrentTMAlphabet());
            creationWindow.close();
            start.setDisable(false);
            stage.setScene(main);
        });
        
        //**shows the main scene**
        stage.setScene(main);
        stage.show();
    }
    
    private boolean isNumeric(String s) {
        try {
            Long.parseLong(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    private long format(String s) {
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                number.append(s.charAt(i));
            }
        }
        return Long.valueOf(number.toString());
    }
    
}
