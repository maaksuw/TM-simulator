# Instructions
Download [TMSimulator.jar](https://github.com/pinjaw/ot-harjoitustyo/releases).
## Configuration
The program creates a directory TMSimulator in the users working directory where the project files are stored. Changing the name of this folder will cause the program to not work.
## Launcing the program
You can launch the program with command
```
java -jar TMSimulator.jar
```
*I will not add screenshots yet since I still want to add one more component to the UI. Here is the text version for now!*
## Creating a Turing machine
Clickin the "Create" -button in the upper left corner will open a new window where you can create a Turing machine. You are asked to give a name and to write a description explaining what the new Turing machine does. Under the description text area there are instructions on how to fill in the transition table.
The transition table can be seen on the right. You can add and remove rows and columns from the buttons at the top of the table. The first row is for the alphabet and the first column is for the states. State names can be multiple characters long but in the alphabet row you should write only one character per cell.
Clicking "Cancel" returns you to the main window and clicking "Finish" saves the new Turing machine and opens it in the main window ready for simulation.
## Opening a Turing machine from a file
By clickin "Open" in the main window, you can select a previously made Turing machine to be simulated. Clicking the button will open your project folder and display all previously made projects. You can select a project file by clickin it and then pressing "Choose". The file name tells you the name of the Turing machine you are selecting. After selecting a file, the Turing machine will be opened in the main window ready to be simulated.
## Simulating a Turing machine
In the main window, you can see the information of the Turing machine currently attached to the simulator on the right side of the simulation area. At the top you can choose what type of a simulation you want to run. 
* Simulation without steps in between shows you only the result of the simulation. This is good for large inputs or if you only want to know wheater the Turing machine halted in a accepting or rejecting state.
* Simulate manually allows you to simulate step-by-step by hand. You can advance one step by clicking a button. (Not yet ready!)
* Simulate step-by-step shows you in the simulation area how the simulation advances, one step in a second.
* Simulate step-by-step (fast) (Not yet ready!)

You can write input in the textfield below the simulation area and then start the simulation by pressing "Start simulation." The result of the simulation will be shown at the bottom of the window. If the input contains characters not defined in the transition table, the simulation will halt.
Tape size limit limits the maximum size of the tape. Step limit sets the amount of steps after which the simulator will automatically halt the simulation. You can adjust these values, but for most inputs the default values should be fine. Tape size limit cannot go under a certain value and if too small it will be adjusted to the minimum accepted value by the simulator.
## Example Turing machines
I will put some here!
That's all for now, I will update the instructions as soon as possible!
