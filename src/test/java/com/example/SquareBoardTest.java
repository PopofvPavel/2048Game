package com.example;
import com.example.Board.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class SquareBoardTest {


    private SquareBoard<Integer> squareBoard;


    @Test
    public void testFillBoardWrongLIstSize() {
        squareBoard = new SquareBoard<>(4);
        List<Integer> list = new ArrayList<>();
        for (var i = 0; i < 17; i++) {
            list.add(i);
        }
        assertThrows(RuntimeException.class, () -> squareBoard.fillBoard(list));
    }
}
