package com.eci.ARSW.MatrixConcurrente.MatrixCon;

public class Phone implements GameElement {

    private final Position position;

    public Phone(Position position) {
        this.position = position;
    }

    @Override
    public char getSymbol() {
        return 'T';
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
    }

}
