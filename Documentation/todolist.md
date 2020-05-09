# To do in the future
## Creating a Turing machine
- [ ] Let user name the accepting and rejecting states and define the blank symbol (that the tape will be initialized with)
- [ ] Make it possible to create a Turing machine by giving the program a file in the right format
- [ ] Add more default fill-in's to make it easier to create a table by hand
- [ ] Make it so that the user has to define input symbols and the input must contain only those symbols
## Main window
- [ ] **Make it possible to edit previously made Turing machines**
- [ ] Make a setting page where the user can change the tape size limit and the input limit and decide the default speed for automatic simulation. Hide tape size limit from the main window.
- [ ] Create a page or a list where the user can select a previously made Turing machine just by clicking a name on the list. It would also be nice to be able to view the description at the same time.
## Simulator
- [ ] **Add a possibility to pause and continue the simulation**
- [ ] **When paused, make it possible to pan the tape left and right to see what the whole tape looks like**
- [ ] Add a possibility to simulate steps backwards in manual simulation
- [ ] Show the end result when simulating without steps in between
- [ ] Make a better animation for manual and automatic simulation
- [ ] Make input from file possible
## Miscellaneous
- [ ] Create a better overall look with CSS
- [ ] Create a working layout for fullscreen
- [ ] Example Turing machines come with the program and won't have to be downloaded by the user
- [ ] Code building the user interface is ridiculously long. It need to be refactored. There is also some repetitive code. It could also be possible to make it so that Handler class does not have a Turing machine as a field. It is a little confusing and maybe unnecessary.
