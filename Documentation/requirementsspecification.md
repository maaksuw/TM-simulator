# Requirements specification

## Purpose of the software
The purpose of the software is to simulate a turing machine. The program constructs a Turing machine based on the users instructions, receives an input, simulates accordingly and presents the outcome with the given input. The idea is to make it easy for the user to see what the constructed turing machine does step by step with the given input and to provide an fun way to experiment with different implementations.

## UI
![Draft of the user interface](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/UIdraft.jpg)
*The draft needs to be updated. Coming soon.*

The main windown has three components: A list of menu buttons and slides at the top, the simulation window and a place for input in the middle, and information about the selected project on the right. If no project is selected the colum on the right will be empty. At the upper right corner there is an exit button that closes the program.

Clicking "Create" opens a second window, where the user can create a project by giving the turing machine a name and a description. Below these are instructions on how to fill in the necessary information for the transition table.
On the right is a table representing the transition table of the Turing machine. Above the table are buttons for adding/removing rows in the table. At the bottom of the page are buttons for saving the project or cancelling the creationg. Clicking "Finish" will create a turing machine and save it as a file to the project folder and clicking "Cancel" will cancel the creation process and all changes.

After creating a project the program returns to the main window and displays the selected turing machine's information right from the simulation area. A box for visulizating the simulation and a place for input are in the middle and at the bottom there is a button for starting the simulation. Next to the input line there are also places to limit tape size and maximum amount of steps.

Clicking "Open" opens a file chooser that by default shows the project folder. User can choose a previously made project file to be opened in the simulator. The files are named according to the name the user gave the machine when creating it.

The user can choose how to run the next simulation from the radio buttons at the top of the main window. Clicking "Start simulation" will star the simulation, visualize it and show the end result at the bottom of the screen.

## Core features
* The user is able to create a new turing machine
  * A turing machine needs a unique name, a description and a transition table
  * A new project is saved as a file
* The user is able to simulate a turing machine
  * The user can select a turing machine they wish to simulate
  * The user has to give the program an input that only contains characters defined in the transition table of the selected turing machine
* The program visualizes the simulation step by step
  * The program shows a part of the tape and the current head position and state
  * The position of the head and the state are updated as the machine reads the input
  * The program shows the user the outcome of the simulation or prints an error message at the bottom of the window
* The user is able to exit the program
