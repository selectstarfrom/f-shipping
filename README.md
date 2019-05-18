# Friend-Shipping Application
Friend-Shipping is an application that helps you in finding the cheapest possible solution for delivering your packets via your Friends Network.
All Users in the network may or may not be your direct friend. So, irrespective of whether the destination is your direct friend or not, the system will gives you a route via which you can deliver your packets.

## Algorithm
The algorithm used in this application is Dijkstra's Shortest Path Algorithm.
Dijkstra's algorithm is for finding the shortest paths between nodes in a graph.
For more info [Dijkstra](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm).

## Friend-Shipping App & Dijkstra's Algorithm
  1. The complete network(connected Users) of Users forms the Graph.
  2. Each User in the application act as Node.
  3. Hardness of reaching from one User to its immediate child or nieghbor will act as Edge/Distance.

## How to Build
Note: Requires JDK 8, Maven
###### Steps
    1. Checkout the code from https://github.com/selectstarfrom/dijkstra.git.
    2. Open command prompt or terminal.
    2. Navigate to project root folder.
    3. Run command: mvn clean install
    
## How to Run
Application be invoked in 2 modes.
  1) Without any run-time arguments.
  2) With run-time arguments.
   
   ##### 1- Without any run-time arguments.
    1. Navigate to target folder inside the project root folder.
    2. Run command: java -jar fship.jar
    3. Frien-Shipping Application Menu will be displayed.
    4. Simply follow the menu options and the hints. Menu will be as given below.

        Options:
            1: Solve single input csv file in directory
            2: Solve all input csv file in directory
            3: Exit
        Please enter your choice:
        
     5. As the option clearly says, Select option-1 for solving single input csv file and 
        Select option-2 for solving multiple files at a time located in a given directory.
        
   ##### 2- With run-time arguments.
    1. Navigate to target folder inside the project root folder.
    2. Run command: java -jar fship.jar {X} {Y}
        value of X can be F or D.
          F -for single file.
          D -for all csv files within specified directory.
        Y is the Filename or Directory location.
    3. Run time arguments should be exactly 2.
    4. Based on the user inputs, application will be executed.

## JUnit
  1. /friend-shipping/src/test/java/networks/services/TestDataReaderService.java
  2. /friend-shipping/src/test/java/networks/services/TestPathResolverService.java
  3. /friend-shipping/src/test/java/networks/services/TestUserService.java
  4. /friend-shipping/src/test/java/networks/utils/TestFileUtil.java

## How to Run from Eclipse or Any IDE.
  ###### Main class:
      /friend-shipping/src/main/java/networks/NetworkApp.java
      
## Scope for Improvement(s)
  1. Use Logger for logging instead of System.out.println.
  2. More Unit test cases.
  2. More Exception handling.

Enjoy Friend-Shipping...........