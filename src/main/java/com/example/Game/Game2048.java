package com.example.Game;

import com.example.Board.Board;
import com.example.Board.Key;
import com.example.Board.NotEnoughSpaceException;
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
        // Если есть свободное место, то ход возможен
        if (!board.availableSpace().isEmpty()) {
            return true;
        }

        //Проверяем значения, есть ли у них одинаковые соседи
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                Integer currentVal = board.getValue(new Key(i, j));
                //Если справа такое же значение, то можно еще ходить
                if (j != board.getWidth() - 1 && currentVal.equals(board.getValue(new Key(i, j + 1)))) {
                    return true;
                }
                //Если внизу такое же значение, то можно еще ходить
                if (i < board.getHeight() - 1 && currentVal.equals(board.getValue(new Key(i + 1, j)))) {
                    return true;
                }
            }

        }
        //Если нет пустых мест и одинаковых ячеек рядом, но ходить больше нельзя
        return false;
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
                    List<Integer> list = helper.moveAndMergeEqual(board.getValues(board.getRow(i)),false);
                    //Collections.reverse(list);
                    newBoardValues.addAll(list);
                }
                board.fillBoard(newBoardValues);
                break;
            case UP:

                for (int i = 0; i < board.getWidth(); i++) {
                    List<Integer> list = helper.moveAndMergeEqual(board.getValues(board.getColumn(i)));
                    fillResultListByColumn(newBoardValues, list, i);



                }

                board.fillBoard(newBoardValues);
                break;
            case DOWN:

                for (int i = 0; i < board.getWidth(); i++) {
                    List<Integer> list = helper.moveAndMergeEqual(board.getValues(board.getColumn(i)), false);
                    fillResultListByColumn(newBoardValues, list, i);
                    //добавить в список (столбцов)
                }

                board.fillBoard(newBoardValues);
                break;

        }

        if (!board.availableSpace().isEmpty()) {
            try {
                addItem();
            } catch (NotEnoughSpaceException e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }

    private static void fillResultListByColumn(List<Integer> resultList, List<Integer> list, int j) {
        if (resultList.isEmpty()) {
            for (int i = 0; i < GAME_SIZE * GAME_SIZE; i++) {
                resultList.add(null);
            }
        }

        int itemIndexInList = 0;
        for (Integer item : list) {
            resultList.set(itemIndexInList * GAME_SIZE + j, item);
            itemIndexInList++;
        }

    }

    @Override
    public void addItem() throws NotEnoughSpaceException {
        List<Key> emptyKeysList = board.availableSpace();
        if (emptyKeysList.isEmpty()) {
            throw new NotEnoughSpaceException();
        }

        Key key = emptyKeysList.get(random.nextInt(emptyKeysList.size()));
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
