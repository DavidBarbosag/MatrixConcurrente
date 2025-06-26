package com.eci.ARSW.MatrixConcurrente.MatrixCon;

public class GameBoard {
    final int rows;
    final int cols;
    private final GameElement[][] grid;
    private final Object lock = new Object();

    public GameBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new GameElement[rows][cols];
    }

    public boolean isFree(Position pos) {
        synchronized (lock) {
            return grid[pos.getRow()][pos.getCol()] == null;
        }
    }

    public void moveElement(Position from, Position to) {
        synchronized (lock) {
            GameElement e = grid[from.getRow()][from.getCol()];
            grid[from.getRow()][from.getCol()] = null;
            grid[to.getRow()][to.getCol()] = e;
            e.setPosition(to);
        }
    }

    public GameElement getAt(Position pos) {
        synchronized (lock) {
            return grid[pos.getRow()][pos.getCol()];
        }
    }

    public void setAt(Position pos, GameElement e) {
        synchronized (lock) {
            grid[pos.getRow()][pos.getCol()] = e;
        }
    }

    public Position getRandomFreePosition() {
        java.util.Random rand = new java.util.Random();
        Position pos;
        do {
            pos = new Position(rand.nextInt(rows), rand.nextInt(cols));
        } while (!isFree(pos));
        return pos;
    }


    public void printBoard() {
        synchronized (lock) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    GameElement e = grid[i][j];
                    System.out.print(e == null ? "." : e.getSymbol());
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }
}

