package com.example.Test;

import com.example.Board.Board;
import com.example.Board.Key;
import com.example.Board.SquareBoard;
import com.example.Game.Game;
import com.example.Game.Game2048;
import com.example.Game.GameHelper;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class TestClass {
    public static void main(String[] args) {
        Board board = new SquareBoard(4);
        Game game2048 = new Game2048();
        System.out.println(game2048.canMove());
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(2);
        list.add(7);
        board.fillBoard(list);
        System.out.println(board.getBoard());
        System.out.println("Available keys = " + board.availableSpace());
        board.addItem(new Key(3, 3), 15);
        System.out.println(board.getBoard());
        System.out.println(board.getKey(0,0));
        System.out.println(board.getValue(new Key(0,0)));
        System.out.println(board.getValue(new Key(3,3)));
        System.out.println(board.getValue(new Key(2,2)));
        System.out.println("Get column = " + board.getColumn(0));
        System.out.println("Get column = " + board.getColumn(1));
        System.out.println("Get column = " + board.getColumn(3));
        System.out.println("Get row = " + board.getRow(0));
        System.out.println("Get row = " + board.getRow(1));
        System.out.println("Get row = " + board.getRow(3));
        System.out.println(board.hasValue(5));
        System.out.println(board.hasValue(15));
        System.out.println(board.hasValue(-15));
        System.out.println(board.hasValue(3));
        System.out.println(board.hasValue(7));
        System.out.println(board.hasValue(null));
        System.out.println(board.getValues(asList(new Key(0,0), new Key(3,3),new Key(1,1))));

        GameHelper gameHelper = new GameHelper();


        List<Integer> result1 = gameHelper.moveAndMergeEqual(asList(1, 2, null, 3));
        List<Integer> result2 = gameHelper.moveAndMergeEqual(asList(2, 2, null, 3));
        List<Integer> result3 = gameHelper.moveAndMergeEqual(asList(2, 2, 4, 4));
        List<Integer> result4 = gameHelper.moveAndMergeEqual(asList(2, 2, 2, 3));
        List<Integer> result5 = gameHelper.moveAndMergeEqual(asList(2, null, null, 2));
        List<Integer> result6 = gameHelper.moveAndMergeEqual(asList(null, null, null, null));
        List<Integer> result7 = gameHelper.moveAndMergeEqual(asList(1, 2, 3, 4));

        System.out.println(result1); // [1, 2, 3, null]
        System.out.println(result2); // [4, 3, null, null]
        System.out.println(result3); // [4, 8, null, null]
        System.out.println(result4); // [4, 2, 3, null]
        System.out.println(result5); // [4, null, null, null]
        System.out.println(result6); // [null, null, null, null]
        System.out.println(result7);

        Board board2 = new SquareBoard(2);
        Game game2048_2 = new Game2048();
        board2.fillBoard(asList(1, 2, 3, 4));
        board2.fillBoard(asList(7, 6, 5, 0  ));
        System.out.println(board2.getBoard());


    }
    }
