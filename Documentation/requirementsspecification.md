# Requirements specification

## Purpose of the software
The purpose of the software is to simulate a turing machine. The program constructs a "turing machine" based on the users instructions, receives an input, simulates accordingly and presents the outcome with the given parameters. The idea is to make it easy for the user to see what the constructed turing machine does step-by-step with the given input and to provide an fun way to experiment with different implementations.

## UI
![Draft of the user interface](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/UIdraft.jpg)
The main windown has three components: a button for making a new turing machine project and list of existing projects on the left, the simulation window in the middle, and information about the selected project on the right. If no project is selected the colum on the right will be empty. At the upper right corner there is an exit button that closes the program.

Clicking "New"-button opens a second window, where the user can create a project by giving the turing machine a name and a description that is displayed on the rightmost panel. Here it could also be possible to define a file location for the project file, but I will think about this later on.

Clicking "Continue" opens another window where the user can construct the transition table. At the top of the window there are instructions on how to do this and information about different possible ways to define the machine.

Clicking "Continue" opens the main window and displays the selected turing machine at the center component of the window. The name of the current project is displayed at the top of the page, a box for visulizating the simulation and a place for input are in the middle and at the bottom there is a button for starting the simulation.

Right-clicking a project on the list opens a menu with options to rename, close or delete a project for example.

## Core features
* The user is able to create a new turing machine
  * A turing machine needs a unique name and a transition table
  * A description is optional
  * A new project is saved as a file
* The user is able to simulate a turing machine
  * The user can select a turing machine they wish to simulate from a list
  * Before simulation the user has to give the program an input
  * The input must only contain characters defined in the transition table of the selected turing machine
* The program visualizes the simulation step-by-step
  * The program shows a part of the tape and the current position and state
  * The position of the head and the state are updated as the machine reads the input
* The user is able to exit the program

## Additional features
* The user is able to close and delete projects from the list
* The user has more options when constructing a turing machine, for example: adding an accept state, a reject state, options for no writing and erasure
* The user can create a new project by giving the program a file where the transition table is described in a right format
* The user can give a file as an input
