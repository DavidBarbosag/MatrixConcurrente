package com.eci.ARSW.MatrixConcurrente.MatrixCon;

/**
 * GameElement interface represents a basic element in a game.
 */
public interface GameElement {

    char getSymbol();
    boolean isWalkable();
    Position getPosition();
    void setPosition(Position p);
}
