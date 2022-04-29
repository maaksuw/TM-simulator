# Turing machine simulator
The purpose of the software is to simulate a turing machine. The program constructs a Turing machine based on the users instructions, receives an input, simulates accordingly and presents the outcome of the simulation. The idea is to make it easy for the user to see what the constructed Turing machine does step by step with the given input and to provide an fun way to experiment with different implementations.

Työ on toteutettu harjoitustyönä Helsingin yliopiston kurssilla Ohjelmistotekniikka.

## Documentation
[**Instructions**](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/instructions.md)

[**Requirements specification**](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/requirementsspecification.md)

[**Architecture**](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/architecture.md)

[**Test documentation**](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/testdocument.md)

[**Record of working hours**](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/recordofworkinghours.md)
## Releases
[Final release](https://github.com/pinjaw/ot-harjoitustyo/releases/tag/final)

[Week 6](https://github.com/pinjaw/ot-harjoitustyo/releases/tag/week6)

[Week 5](https://github.com/pinjaw/ot-harjoitustyo/releases/tag/week5)
## Future releases
Here is a list of improvements and additional features I am planning on including in future releases. I am open to feedback and new ideas on how to further improve the program!

[**Improvements and features to be added**](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/todolist.md)
## Known issues
If you notice any bugs or errors in the program or find a part of the user interface difficult to use or understand, I kindly ask you to message me so that I can fix the issue. I would appreciate it very much.
## Command Line commands
All commands should be run from the folder that contains pom.xml file.

This command runs the program from the command line, if you have cloned the project.
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
mvn test jacoco:report
```
Running the command creates a report file target/site/jacoco/index.html that can be viewed in a browser.
### Generating a jar
Generates a jar file for executing the program.
```
mvn package
```
Running the command creates a jar file TuringMachineSimulator-1.0-SNAPSHOT.jar to the *target* folder.
### JavaDoc
Generates a JavaDoc for the project.
```
mvn javadoc:javadoc
```
### CheckStyle
Generates a CheckStyle report checkstyle.html in the target/site folder.
```
mvn jxr:jxr checkstyle:checkstyle
``` 
