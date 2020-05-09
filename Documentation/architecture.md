# Architecture
The program has three main packages: simulator.ui, simulator.domain and simulator.dao. The packages follow the structure depicted in the graph below.

**Package structure graph here.**

Simulator.ui contains the classes responsible of building the user interface. simulator.domain has the code responsible for actual application logic and simulator.dao contains the classes reading and writing to files.
## UI
simulator.ui packacge had two classes: UI and AnimationTimerExtra. UI is responsible for building the whole graphical user interface. The UI class has Stage and Scene objects for the main window and for the creation window respectively. AnimatonTimerExtra class is an abstract class UI uses to animate the simulation.

The main responsibilities of the UI class are to construct and set the main and creation scenes and to pass the user's input on from the graphical components to Handler class in domain package. UI class is also responsible for showing the animation when simulating. This is done through an AnimationTimerExtra object that extends Java's AnimationTimer class. Actual objects of AnimationTimerExtra receive an integer as a parameter that define the interval in which the AnimationTimer's handle method is called.

The UI-class interacts with the other classes solely though the Handler class.
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

**A class diagram here.**

## Saving data
simultor.dao package contains the FileTMDao class responsible for managing the project folder and files. Handler class interacts with FileTMDao through an interface named TMDao. TMDao provides methods for creating the project folder, retrieving the project folder's path and creating and reading a project file. FileTMDao had two constructors FileTMDao() and FileTMDao(String f). The first one is used to create the project folder. The second one is used to set FileTMDao's project folder field to point to a temporary folder whose path is given as a parameter. This is constructor used in testing.

FileTMDao creates a project folder named "TMSimulator" to the current working directory. All Turing machine files are saved here as normal text files. The files are written in the following format,

name:

description:

alphabet separated by space:

all state names separated by space:

first row of the transition table

second row of the transition table

...

last row of the transition table

In the rows describing the transition table, instructions are written in the following order: character movement state, and instructions on the same row are separated by a semicolon and space.
## Main functions
### Creating a Turing Machine
When a user creates a turing machine by clicking "Finish" -button, given that a machine with the same name doesn't already exists, the following procedure will start.

### Opening a Turing machine file

### Simulating a Turing machine

Simulating manually and automatically follows a very similar pattern, only that an AnimatorTimer object is used to print the current situation of the tape on the simuation canvas when a button is pressed or in predefined intervals.
