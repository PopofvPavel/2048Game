package com.example.Game;

import com.example.Board.Board;
import com.example.Board.Key;
import com.example.Board.SquareBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public class Game2048 implements Game {

    private final GameHelper helper = new GameHelper();

    public static final int GAME_SIZE = 4;

    private final Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);

    Random random = new Random();

    public Game2048() {
        //this.board = board;

    }


    @Override
    public void init() {
        board.fillBoard(asList(2, 4));
    }

    @Override
    public boolean canMove() {
        return !board.availableSpace().isEmpty();
    }

    @Override
    public boolean move(Direction direction) {
        if (!canMove()) {
            return false;
        }
        List<Integer> newBoardValues = new ArrayList<>();
        switch (direction) {

            case LEFT:
                for (int i = 0; i < board.getHeight(); i++) {
                    List<Integer> list = helper.moveAndMergeEqual(board.getValues(board.getRow(i)));
                    newBoardValues.addAll(list);
                }
                board.fillBoard(newBoardValues);
                break;
            case RIGHT:
                for (int i = 0; i < board.getHeight(); i++) {
                    List<Integer> list = helper.moveAndMergeEqual(board.getValues(board.getRow(i)));
                    Collections.reverse(list);//как я понял при свайпе вправо здесь происзодит реверс
                    newBoardValues.addAll(list);
                }
                board.fillBoard(newBoardValues);
                break;
            case UP:
                //List<Integer> resultList = new ArrayList<>(GAME_SIZE * GAME_SIZE);
                for (int i = 0; i < board.getWidth(); i++) {
                    List<Integer> list = helper.moveAndMergeEqual(board.getValues(board.getColumn(i)));
                    fillResultListByColumn(newBoardValues, list, i);


                    //добавить в список (столбцов)
                    //newBoardValues.add(getStraightenedList)
                }
                //по-хорошему здесь должен быть метод fillBoard, только заполняющий не построчно, а
                // по столбцам
                board.fillBoard(newBoardValues);
                break;
            case DOWN:
                //List<Integer> resultList = new ArrayList<>(GAME_SIZE * GAME_SIZE);
                for (int i = 0; i < board.getWidth(); i++) {
                    List<Integer> list = helper.moveAndMergeEqual(board.getValues(board.getColumn(i)),false);
                    //Collections.reverse(list);
                    fillResultListByColumn(newBoardValues, list, i);
                    //добавить в список (столбцов)
                }
                //по-хорошему здесь должен быть метод fillBoard, только заполняющий не построчно, а
                // по столбцам
                board.fillBoard(newBoardValues);
                break;
          /*  default:
                if (!board.availableSpace().isEmpty()) {
                    addItem();
                }*/
        }

        if (!board.availableSpace().isEmpty()) {
            addItem();
        }
        //board.fillBoard(newBoardValues);//перезаполнение поля
        return true;
    }

    private static void fillResultListByColumn(List<Integer> resultList, List<Integer> list, int j) {
        for (int i = 0; i < GAME_SIZE * GAME_SIZE; i++) {
            resultList.add(null);
        }

        int itemIndexInList = 0;
        for (Integer item : list) {
            resultList.set(itemIndexInList * GAME_SIZE + j, item);
            itemIndexInList++;
        }
      /*  while (GAME_SIZE - itemIndexInList != 0) {
            resultList.set(itemIndexInList * GAME_SIZE, null);
            itemIndexInList++;
        }*/
    }

    @Override
    public void addItem() {
        List<Key> emptyKeys = board.availableSpace();
        Key key = emptyKeys.get(random.nextInt(emptyKeys.size()));
        board.addItem(key, getRandomValue());

    }

    private Integer getRandomValue() {
        int randomVal = random.nextInt(10);
        //как я проверил, в 90 % случаев выпадает 2, в остальные 10 % - 4
        if (randomVal == 9) {
            return 4;
        } else {
            return 2;
        }
    }

    @Override
    public Board getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return board.hasValue(2048);
    }
}
