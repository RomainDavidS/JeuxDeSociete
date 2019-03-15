### DA Java EE Project 3 - JDK 11

## How to compile the application ?
Compiling the application will make from the Maven runtime window by running the command -> clean install

## Launch of the IntelliJ console application:
- in debug mode execute the command:
    - java -jar "target\JeuxDeSociete-1.0.jar" dev

- in player mode execute the command:
    - java -jar "target\JeuxDeSociete-1.0.jar"

## Launching the application in a specific directory:
- Create the directory of your choice
    For example, JeuxDeSociete in the c:\temp

- JeuxDeSociete-1.0.jar files to the directory you have created

- Go to the directory c:\temp\JeuxDeSociete

- Create the .bat files by inserting the specific command line to launch the 2 application files:
    - in player mode -> java -jar "JeuxDeSociete-1.0.jar"
    - in debug mode -> java -jar "JeuxDeSociete-1.0.jar" dev

- To launch the application it will suffice to double-click on the desired .bat file

## How Generate Javadoc ?
The generate javadoc  will make from the Maven runtime window by running the command -> javadoc:javadoc

javadoc was created in the directory in ./target/site/apidocs
