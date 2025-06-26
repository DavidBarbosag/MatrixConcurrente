package com.eci.ARSW.MatrixConcurrente.MatrixCon;

import java.util.*;

public class PathFinder {

    public static List<Position> bfs(GameBoard board, Position start, Position goal) {
        Queue<List<Position>> queue = new LinkedList<>();
        Set<Position> visited = new HashSet<>();

        queue.add(List.of(start));
        visited.add(start);

        while (!queue.isEmpty()) {
            List<Position> path = queue.poll();
            Position current = path.get(path.size() - 1);

            if (current.equals(goal)) return path;

            for (Position neighbor : getNeighbors(current, board)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<Position> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }

        return null;
    }

    private static List<Position> getNeighbors(Position pos, GameBoard board) {
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        List<Position> neighbors = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int newRow = pos.getRow() + dr[i];
            int newCol = pos.getCol() + dc[i];
            Position neighbor = new Position(newRow, newCol);
            if (isValid(neighbor, board)) {
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }

    private static boolean isValid(Position pos, GameBoard board) {
        if (pos.getRow() < 0 || pos.getCol() < 0 ||
                pos.getRow() >= board.rows || pos.getCol() >= board.cols)
            return false;

        GameElement e = board.getAt(pos);
        return e == null || e.isWalkable();
    }

    public static Position findNearest(GameBoard board, Position start, char targetSymbol) {
        Queue<Position> queue = new LinkedList<>();
        Set<Position> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            GameElement e = board.getAt(current);
            if (e != null && e.getSymbol() == targetSymbol) {
                return current;
            }

            for (Position neighbor : getNeighbors(current, board)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return null;
    }
}
