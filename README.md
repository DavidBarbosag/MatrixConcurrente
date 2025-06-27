# MatrixConcurrente
## Author
David Alfonso Barbosa Gómez

## Description

A concurrent console simulation inspired by The Matrix. Neo must reach a phone while avoiding Agents in an environment filled with walls. All elements move concurrently using threads and a Breadth-First Search (BFS) pathfinding algorithm.

---

## Features

-  Automatic movement of Neo and Agents using BFS (Breadth-First Search).
-  Concurrency management using multiple threads.
-  Walls and phones are randomly placed on the board.
-  Real-time game board rendering in the console.

---

##  Requirements

- Java 17 or higher
- Maven 3.x
- Spring Boot 3.x

---

## Installation and Execution

1. Clone the repositor:
   ```
   git clone https://github.com/tu-usuario/MatrixConcurrente.git
   cd MatrixConcurrente
   ```
   
3. Build the project:

   ```
   mvn clean package
   ```
   
4. Run the generated .jar file:
   ```
    java -jar target/MatrixCon-0.0.1-SNAPSHOT.jar
   ```

## Game Structure

Neo: The main character; his goal is to reach a phone.

Agent: Chases Neo.

Phone: Neo's objective.

Wall: : Impassable obstacle.

GameBoard: Maintains the board state and synchronizes concurrent access.

PathFinder: Implements the BFS algorithm to calculate optimal paths.

## Project Structure

src/

├── main/

│   ├── java/

│   │   └── com.eci.ARSW.MatrixConcurrente.MatrixCon/

│   │       ├── MatrixConApplication.java

│   │       ├── GameBoard.java

│   │       ├── GameElement.java

│   │       ├── Neo.java

│   │       ├── Agent.java

│   │       ├── Phone.java

│   │       ├── Wall.java

│   │       ├── Position.java

│   │       ├── PathFinder.java

│   │       └── GameInitializer.java


## Concurrency and Synchronization


This game simulates multiple mobile entities acting simultaneously. To achieve this, Java concurrent programming is used:

* Each mobile character (Neo and the Agents) runs in its own thread, allowing them to act in parallel.

```
Neo neo = new Neo(neoPos, board);
        board.setAt(neoPos, neo);

        for (int i = 0; i < 3; i++) {
            Position pos = randomFreePosition(board, rand);
            Agent agent = new Agent(pos, board);
            board.setAt(pos, agent);
            new Thread(agent, "Agent-" + i).start();
        }

        new Thread(neo, "Neo").start();
```
* The board (GameBoard) is shared among all threads, so it is protected using a synchronized block and a lock to ensure that only one thread can read or modify a cell at a time.

```
public void moveElement(Position from, Position to) {
        synchronized (lock) {
            GameElement e = grid[from.getRow()][from.getCol()];
            grid[from.getRow()][from.getCol()] = null;
            grid[to.getRow()][to.getCol()] = e;
            e.setPosition(to);
        }
    }
```
* Critical operations such as checking if a cell is free (isFree), moving elements (moveElement), and retrieving objects at a position (getAt) are all synchronized to avoid race conditions.

Synchronization ensures that:

* Neo does not move into a cell occupied by an Agent.

* Agents do not collide with each other.

* The board state remains consistent during rendering or pathfinding.

## Class Diagram

![ClassDiagram](https://github.com/user-attachments/assets/742d9463-932a-438a-b58b-4ff091583100)

