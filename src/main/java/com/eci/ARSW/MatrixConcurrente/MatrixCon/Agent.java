package com.eci.ARSW.MatrixConcurrente.MatrixCon;
import java.util.List;

public class Agent implements GameElement, Movable {

    private Position position;
    private final GameBoard board;
    private volatile boolean running = true;

    public Agent(Position start, GameBoard board) {
        this.position = start;
        this.board = board;
    }

    @Override
    public void run() {
        while (running) {
            Position neoPos = findNeo();
            if (neoPos == null) continue;

            List<Position> path = PathFinder.bfs(board, position, neoPos);
            if (path != null && path.size() > 1) {
                Position next = path.get(1);
                board.moveElement(position, next);
            }

            board.printBoard();

            if (position.equals(neoPos)) {
                System.out.println("¡Los agentes han atrapado a Neo!");
                System.exit(0);
            }

            try { Thread.sleep(500); } catch (InterruptedException e) { break; }
        }
    }

    @Override
    public char getSymbol() { return 'A'; }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void move() {
    }

    private Position findNeo() {
        return PathFinder.findNearest(board, position, 'N');
    }
}

