# Instructions
Download [TMSimulator.jar](https://github.com/pinjaw/ot-harjoitustyo/releases).
## Configuration
The program creates a directory TMSimulator in the users working directory where the project files are stored. Carelessly changing the name or the contents of this folder might cause the program to not work.
## Launcing the program
You can launch the program from the directory that contains the jar-file with command
```
java -jar TMSimulator.jar
```
This will start the program and show the main window.

![Main window](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/Pictures/mainwindow.png)

## Creating a Turing machine
Clickin the "Create" -button in the upper left corner will open a new window where you can create a Turing machine. You are asked to give a name and to write a description explaining what the new Turing machine does. Under the description text area there are instructions on how to fill in the information needed for the transition table.

![Creation](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/Pictures/creation.png)

The transition table can be seen on the right. You can add and remove rows and columns from the buttons at the top of the table. The first row is for the alphabet and the first column is for the states. State names can be multiple characters long but in the alphabet row you should write only one character per cell.
Clicking "Cancel" returns you to the main window and clicking "Finish" saves the new Turing machine and opens it in the main window ready for simulation.
## Opening a Turing machine from a file
By clickin "Open" in the main window, you can select a previously made Turing machine to be simulated. Clicking the button will open your project folder and display all previously made projects. The file chooser window can look like this, for example.

![Open a project file](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/Pictures/open.png)

You can select a project file by clickin it and then pressing "Choose". The file name tells you the name of the Turing machine you are selecting. After selecting a file, the Turing machine will be opened in the main window ready to be simulated.
* If you don't know where your project folder is, clicking the open button in the main window is the easiest way to find out. Open-button always opens your project folder and in the file chooser, above all the files, you can see the location of the folder.
## Simulating a Turing machine
In the main window, you can see the information of the Turing machine currently attached to the simulator on the right side of the simulation area. At the top you can choose what type of a simulation you want to run. 
* Simulation without steps in-between shows you only the result of the simulation. This is good for large inputs or if you only want to know wheater the Turing machine halted in a accepting or rejecting state.
* Simulate manually allows you to simulate step by step by hand. You can advance one step by clicking a button.
* Simulate automatically shows you in the simulation area how the simulation advances. You can choose different speeds by adjusting the slider.

*The simulator is ready for simulation.*
![Ready for simulation](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/Pictures/simulator.png)

You can write input in the textfield below the simulation area and then start the simulation by pressing "Start simulation." You can start a new simulation any time by choosing the type of simulation you want to run and then clicking "Start simulation", even if the previous simulation has not ended yet. The result of the simulation will be shown at the bottom of the window. If the input contains characters not defined in the transition table, the simulation will halt.

*During simulation, steps taken are shown at the left corner of the simulation area. The visualization shows a part of the tape near the head and the current state (above the head).*
![Automatic simulation](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/Pictures/simulating.png)

*The outcome of the simulation is shown at the bottom of the window.*
![Result](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/Pictures/accepted.png)

Tape size limit limits the maximum size of the tape. Step limit sets the amount of steps after which the simulator will automatically halt the simulation. You can adjust these values freely, but usually the step limit is the only one that needs to be changed. Tape size limit cannot go under a certain value and if too small it will be adjusted to the minimum accepted value by the simulator.
## Editing a Turing machine
This feature is not supported in the current most recent release. However, if you really want to modify a previously made Turing machine, you can open the project file on your computer and directly edit that file. The file is written in the following format:

name:

description:

alphabet separated by space:

all state names separated by space:

first row of the transition table

second row of the transition table

...

last row of the transition table

In the matrice describing the transition table instructions are written in the following order: character movement state, and instructions on the same row are separated by a semicolon and space.
Be careful of not breaking this format, or the program will not be able to read and recognize the file.
## Example Turing machines
Here are some example Turing machines to simulate. You can download these Turing machines as text files from [here](https://github.com/pinjaw/ot-harjoitustyo/releases) at the same time when you download the program. Run the program once and look for the project folder "TMSimulator" in the same folder where you ran the jar-file from. If you cannot find it, see the instructions at the end of **Opening a Turing machine from a file**.
Move the text files to the project folder and next time you open the program, you should be able to see them listed when you click "Open".

**Ahkera Majava (4 tilaa)** *Busy Beaver with 4 states*

Given an empty string as an input, writes as many a's on the tape as possible for a Turing machine with 4 states and halts after `107` steps.

**Ahkera Majava (5 tilaa)** *Best busy beaver contender for 5 states*

Contender for the best busy beaver with 5 states. Writes as many ones on the tape as possible and halts after `47 176 870` states.

**Ahkera Majava -ehdokas (6 tilaa)** *Best busy beaver contender for 6 states*

Contender for the best busy beaver with 6 states. Writes as many ones on the tape as possible and is said to halt after about `7.412 * 10^36534` steps.

**Kaksinkertaistaja** *Doubler*

Given `n` ones, prints `n*2` ones on the tape.

**Neliöntäjä** *Square-maker*

Given `n` ones, prints `n^2`ones on the tape.

**Palindromi-tarkistaja** *Palindrome checker*

Checks if the given input is a palindrome!

A big thank you for [@tykkipeli](https://github.com/tykkipeli) for designing and providing these example Turing machines!
