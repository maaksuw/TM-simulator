# Test documentation
The program has been tested by automated JUnit tests. The user interface was tested manually and the whole system has been tested continuously throughout the development.
## Unit and integration testing
The program has been integration tested through HandlerTest class that tests some of the main functions as a whole. However, most of the testing is done through unit testing the Simulator, TuringMachine and Instruction classes. TuringMachine and Instruction classes have simple unit tests that test the basic methods of the classes. Simulator classes most important methods, both simulate() and simulateStep() methods and the three private methods initiateTape(), growTape() and printTape(), are tested with realistic inputs in different scenarios to make sure that the Simulator class simulates correctly and does not fail.
### Testing the DAO-layer
The only DAO-class was tested by using an alternative constructor of FileTMDao that allowed giving the constructor a temporary folder's path as parameter. This made testing FileTMDao fairly easy. HandlerTest also uses a temporary folder in the integration tests when creating a TMDao object.
### Test coverage
Overall 86% of the instructions and 83% of all brances were covered by the automated tests.
![Test coverage](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/Pictures/jacocoreport.png)
Handler class's setUpTM method and some private helper methods are still missing automated tests.
## System testing
System tests were done manually, consistently throughout the development. Following actions were the most important functions to be tested manually:
* Launching the program when no project folder exists (for the first time)
* Launching the program when a project folder already exists
* All text fields in creation window were tested for bad inputs
* Cancelling or exiting the creation window
* Cancelling the file chooser and not choosing a file
* All text fields in the main window were tested for bad inputs
* Starting simulation in the middle of another simulation
* The simulator was tested also with fairly large inputs, but performance is not yet that important since manual input is the only input method currently available
## Improvements to be made
Overall the integration tests were quite minimalistic and the test coverage could still be higher. Also a few of the SimulatorTests methods test exceptions that after refactoring are not possible anymore. SimulatorTest could also have used multiple proper Turing machines for testing to make sure that the tests are valid.
It might also be slightly confusing that currently even if the user, in the middle of manual simulation, chooses to simulate in another style, the "Simulate step" -button still remains until user clicks "Start simulation" again. Clicking "Simulate step" again just continues the previous simulation. I will make this better in future releases. 
