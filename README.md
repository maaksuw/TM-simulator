# Turing machine simulator
The purpose of the software is to simulate a turing machine. The program constructs a "turing machine" based on the users instructions, receives an input, simulates accordingly and presents the outcome with the given parameters. The idea is to make it easy for the user to see what the constructed turing machine does step-by-step with the given input and to provide an fun way to experiment with different implementations.
## Documentation
[**Instructions**](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/instructions.md)

[**Requirements specification**](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/requirementsspecification.md)

[**Record of working hours**](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/recordofworkinghours.md)
## Command Line commands
Runs the program from the command line, if you have cloned the project.
```
mvn compile exec:java -Dexec.mainClass=simulator.domain.Main
```

### Testing
Runs the tests for the program.
```
mvn test
```
Creates a code coverage report.
```
mvn jacoco:report
```
Running the command creates a report file target/site/jacoco/index.html that can be viewed in a browser.
### Generating a jar
Generates a jar file for executing the program.
```
mvn package
```
Running the command creates a jar file TuringMachineSimulator-1.0-SNAPSHOT.jar to the *target* folder.
### JavaDoc
To be added.
### CheckStyle
To be added.
