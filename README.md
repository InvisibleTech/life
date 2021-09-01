# Life

A basic version of the Game Of Life. The board is finite and has an
edge. Cells beyond the edge are considered dead.

This code was written both for fun, to experiment with the Producer
Consumer pattern per **Java Concurrency In Practice** and to support 
the interview process.

Eventually, would like to add a visual version leveraging a tool
such as **OpenCV Java** or any other graphics API that can render a window driven by the Producer Consumer model.

## Building and Running Outside IDE

To compile and test, basically:

    mvn clean package
 
 To run the main non-concurrent version:
 
    mvn compile exec:java -Dexec.mainClass="org.invisibletech.life.BasicLife"

  Similarly for the concurrent version:

## What About Checkstyle and PMD?

Not really for this scale of project done by a single developer. Also, this is meant to be demonstrative of design, show some code and be fun.
