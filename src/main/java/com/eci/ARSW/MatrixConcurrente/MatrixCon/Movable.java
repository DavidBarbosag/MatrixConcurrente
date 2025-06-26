package com.eci.ARSW.MatrixConcurrente.MatrixCon;

/**
 * Interface representing a movable game element that can run and move.
 * It extends the Runnable interface, allowing it to be executed in a thread.
 */
public interface Movable extends Runnable{
    void move();
}
