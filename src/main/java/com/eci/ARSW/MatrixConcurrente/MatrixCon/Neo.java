package com.eci.ARSW.MatrixConcurrente.MatrixCon;

import java.util.List;
import java.util.Queue;

public class Neo implements GameElement, Movable {

    private Position position;
    private final GameBoard board;
    private volatile boolean running = true;

    public Neo(Position start, GameBoard board) {
        this.position = start;
        this.board = board;
    }

    @Override
    public void run() {
        while (running) {
            Position phone = findNearestPhone();
            if (phone == null) continue;

            List<Position> path = PathFinder.bfs(board, position, phone);
            if (path != null && path.size() > 1) {
                Position next = path.get(1);
                board.moveElement(position, next);
            }

            board.printBoard();

            if (position.equals(phone)) {
                System.out.println("¡Neo ha ganado!");
                System.exit(0);
            }

            try { Thread.sleep(500); } catch (InterruptedException e) { break; }
        }
    }

    @Override
    public char getSymbol() { return 'N'; }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public Position getPosition() { return position; }

    @Override
    public void setPosition(Position position) { this.position = position; }

    @Override
    public void move() {}

    private Position findNearestPhone() {
        return PathFinder.findNearest(board, position, 'T');
    }



}

