package com.eci.ARSW.MatrixConcurrente.MatrixCon;

import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.util.Random;

@Component
public class GameInitializer {

    @EventListener(ApplicationReadyEvent.class)
    public void startGame() {
        int rows = 10, cols = 10;
        GameBoard board = new GameBoard(rows, cols);
        Random rand = new Random();

        for (int i = 0; i < 15; i++) {
            Position pos = randomFreePosition(board, rand);
            Wall wall = new Wall(pos);
            board.setAt(pos, wall);
        }

        for (int i = 0; i < 3; i++) {
            Position pos = randomFreePosition(board, rand);
            Phone phone = new Phone(pos);
            board.setAt(pos, phone);
        }

        Position neoPos = randomFreePosition(board, rand);
        Neo neo = new Neo(neoPos, board);
        board.setAt(neoPos, neo);

        for (int i = 0; i < 3; i++) {
            Position pos = randomFreePosition(board, rand);
            Agent agent = new Agent(pos, board);
            board.setAt(pos, agent);
            new Thread(agent, "Agent-" + i).start();
        }

        new Thread(neo, "Neo").start();
    }

    private Position randomFreePosition(GameBoard board, Random rand) {
        Position pos;
        do {
            int r = rand.nextInt(board.getRows());
            int c = rand.nextInt(board.getCols());
            pos = new Position(r, c);
        } while (!board.isFree(pos));
        return pos;
    }
}
