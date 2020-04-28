# Turing machine simulator
The purpose of the software is to simulate a turing machine. The program constructs a "turing machine" based on the users instructions, receives an input, simulates accordingly and presents the outcome with the given parameters. The idea is to make it easy for the user to see what the constructed turing machine does step-by-step with the given input and to provide an fun way to experiment with different implementations.
## Documentation
[**Instructions**](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/instructions.md)

[**Architecture**](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/architecture.md)

[**Requirements specification**](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/requirementsspecification.md)

[**Record of working hours**](https://github.com/pinjaw/ot-harjoitustyo/blob/master/Documentation/recordofworkinghours.md)
## Releases
[Week 6](https://github.com/pinjaw/ot-harjoitustyo/releases/tag/week6)

[Week 5](https://github.com/pinjaw/ot-harjoitustyo/releases/tag/week5)
## Known issues
* Simulating without a Turing machine causes an error.
* Entering tape size limit or step limit with spaces between numbers causes an error.

How ever neither of these seem to cause the program to fail. I will work on fixing these issues!
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
