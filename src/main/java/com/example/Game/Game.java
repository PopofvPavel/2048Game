package com.example.Game;

import com.example.Board.Board;
import com.example.Board.NotEnoughSpaceException;

public interface Game {
    void init();

    boolean canMove();

    boolean move(Direction direction);

    void addItem() throws NotEnoughSpaceException;

    Board getGameBoard();

    boolean hasWin();

}
