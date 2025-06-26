package com.eci.ARSW.MatrixConcurrente.MatrixCon;

public class Wall implements GameElement {

    private final Position position;

    public Wall(Position position) {
        this.position = position;
    }

    @Override
    public char getSymbol() {
        return '#';
    }

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
    }
}
