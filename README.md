DA Java EE Project 3

How to compile the application ?
Compiling the application will make from the Maven runtime window by running the command -> clean install

Launch of the IntelliJ console application:
- in debug mode execute the command:
    - java -jar "target\JeuxDeSociete-1.0.jar" dev
    or
    - java -jar "target\JeuxDeSociete.exe" dev

- in player mode execute the command:
    - java -jar "target\JeuxDeSociete-1.0.jar"
    or
    - java -jar "target\JeuxDeSociete.exe"

Launching the application in a specific directory:
- Create the directory of your choice
    For example, JeuxDeSociete in the c:\temp

- Copy the JeuxDeSociete.exe and JeuxDeSociete-1.0.jar files to the directory you have created

- Go to the directory c:\temp\JeuxDeSociete

- Create the .bat files by inserting the specific command line to launch the 2 application files:
    for JeuxDeSociete-1.0.jar:
        - in player mode -> java -jar "JeuxDeSociete-1.0.jar"
        - in debug mode -> java -jar "JeuxDeSociete-1.0.jar" dev

    for JeuxDeSociete.exe:
        - in player mode -> java -jar "JeuxDeSociete.exe"
        - in debug mode -> java -jar "JeuxDeSociete.exe" dev

- To launch the application it will suffice to double-click on the desired .bat file