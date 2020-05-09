# Architecture
The program has three main packages: simulator.ui, simulator.domain and simulator.dao. The packages follow the structure depicted in the graph below.

![Package structure graph here](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/Graphs/packagestructurediagram.jpg)

Simulator.ui contains the classes responsible of building the user interface. simulator.domain has the code responsible for actual application logic and simulator.dao contains the classes reading and writing to files.
## UI
simulator.ui packacge has two classes: UI and AnimationTimerExtra. UI is responsible for building the whole graphical user interface. The UI class has Stage and Scene objects for the main window and for the creation window respectively. AnimatonTimerExtra class is an abstract class UI uses to animate the simulation.

The main responsibilities of the UI class are to construct and set the main and creation scenes and to pass the user's input on from the graphical components to Handler class in domain package. UI class is also responsible for showing the animation when simulating. This is done through an AnimationTimerExtra object that extends Java's AnimationTimer class. Actual objects of AnimationTimerExtra receive an integer as a parameter that define the interval in which the AnimationTimer's handle method is called.

The UI class interacts with the other classes solely though the Handler class.
## Application logic
simulator.domain package has four main classes: Handler, Simulator, TuringMachine and Instruction. Main class is also located in the domain package.

Handler class has methods for all the main functions of the program. Handler has a private TMDao object and a Simulator that are created when the constructor is called and a private TuringMachine that can be set by a method.
* createTM(String name, String description, String[][] transitiontable, char[] alphabet, String[] states) creates a new Turing machine
* setUpTM(File f) constructs a Turing machine from the file and sets is as the Handler's current TuringMachine
* simulate(String input, long stepLimit, long tapeLimit) simulates from beginning to end at once, using Handler's private Simulator and TuringMachine
* simulateStep() simulates one step using Handler's private Simulator and TuringMachine

Handler uses TMDao object to create the program folder if it does not already exist and to manage the Turing machine files. Handler class also has helper methods to pass information from the other domain classes to the UI. The main responsibility of the Handler class is to act as an interface between the UI and the domain classes, and provide methods for all the main functions that the UI can call. When the program is launced, a new Handler object is created before anything else. 

Simulator class is capable of simulating a Turing machine. Its main public methods are for simulating one step or simulating from the beginning to the end at once. It also has private methods for initializing the tape with the given input, growing the tape size when needed and returning a string representation of the tape. Simulator has a private TuringMachine that is set through setTM() method. When asked to simulate, Simulator simulates this TuringMachine with the given input.

TuringMachine class represents a TuringMachine. A TuringMachine is initiated with a name (String), a description (String), a table of instructions (Instruction[][]), alphabet (char[]) and a set of states (String[]). TuringMachine class has methods for retrieving information like its name, description or a certain character of the alphabet.

Instruction is a class representing one instruction in the transition table. It consists of a character (char), a movement (char) and a state (String).

*Class diagram of Turing Machine Simulator.*

![A class diagram here.](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/Graphs/classdiagram.jpg)

## Saving data
simultor.dao package contains the FileTMDao class responsible for managing the project folder and files. Handler class interacts with FileTMDao through an interface named TMDao. TMDao provides methods for creating the project folder, retrieving the project folder's path and creating and reading a project file. FileTMDao had two constructors FileTMDao() and FileTMDao(String f). The first one is used to create the project folder. The second one is used to set FileTMDao's project folder field to point to a temporary folder whose path is given as a parameter. This is constructor used in testing.

FileTMDao creates a project folder named "TMSimulator" to the current working directory. All Turing machine files are saved here as normal text files. The files are written in the following format,

```
name:

description:

alphabet separated by space:

all state names separated by space:

first row of the transition table

second row of the transition table

...

last row of the transition table
```

In the rows describing the transition table, instructions are written in the following order: character movement state, and instructions on the same row are separated by a semicolon and space.
## Main functions
Main functions of the program are creating a Turing machine, opening a pre-existing Turing machine from a file and simulating a selected Turing machine. Here is a short explanation on how the main functions are executed in the program.
### Creating a Turing Machine
When user creates a Turing machine by clicking "Finish" -button, given that a machine with the same name doesn't already exists, the following procedure will occur.
![Creating a Turing machine](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/Graphs/Create%20a%20Turing%20machine.png)
First UI class gathers the necessary data from the GUI-components. Then it will call Handler class's createTM method. Handler creates an Instruction table and creates a new TuringMachine. Then Handler calls TMDao's createTM method and gives the createn TuringMachine as a parameter. TMDao creates a new project file describing the Turing machine and returns true if no error occurred. Handler returns true if the whole procedure ran smoothly and UI clears the data from the creation scene GUI-components. Finally UI closes the creation window and shows the main scene with the new Turing machines information on the right.
### Opening a Turing machine file
When user clicks the "Open" -button and chooses a file, the following procedure takes plase.
![Opening a Turing machine](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/Graphs/Open%20a%20Turing%20machine%20file.png)
If the user chose a file, UI calls Handler's method setUpTM and gives the chosen file as a parameter. Handler then first calls TMDao's method prepareReader to set the FileTMDao's scanner to read from the file given as a parameter. Then Handler and TMDao begin a conversation, where Handler asks for the Turing machine's name, description, alphabet, states and transition table and TMDao reads them from the file and returns the information as a String. In between the calls Handler avokes its own methods to change the alphabet, states and transitiontable from String to a necessary form. Then Handler creates a TuringMachine object from the information and sets it as Handler's and Simulator's current TuringMachine. Handler returns true to tell the UI that the reading of the file was succesfull and finally UI sets the GUI-components to match the newly opened Turing machine.
### Simulating a Turing machine
When user has selected "Simulate without showing steps in-between", had given a proper input and clicks "Start simulation", the following procedure occurs.
![Simulating a Turing machine](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/Graphs/Simulate%20a%20Turing%20machine.png)
First UI checks if the input is numeric and formats it to avoid errors. Then Handler's simulate method is called. Handler receives the input, tape size limit and step limit as parameters and passes them on to Simulator by calling Simulator's simulate-method. Simulator first sets itself up by calling setUpSimulator-method. If setUpSimulator does not throw any exception, Simulator calls its current TuringMachine's getTable()-method and receives an Instruction table. After that Simulator beings a loop where it read the instructions from the Instruction table and adjusts the contents on the tape accordingly before halting or reaching the step limit. In this example the simulation ended in an accepting state and the simulate-method returns 1. Handler passes this information on to the UI that sets the result text to "Accepted".

Simulating manually and automatically follow a very similar pattern, only that an AnimatorTimerExtra object is used to print the current situation on the tape on the simulation canvas, in user defined intervals or when a button is pressed.
