package com.example.Board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SquareBoard<V> extends Board<Key, V> {


    public SquareBoard(int size) {
        super(size, size);

    }

    @Override
    public void fillBoard(List<V> list) {
        if (list.size() > this.getHeight() * this.getWidth()) {
            throw new RuntimeException("Initialization error: list has got more elements than board can fill");
        }

        Iterator<V> iterator = list.iterator();
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                if (iterator.hasNext()) {
                    V el = iterator.next();
                    this.getBoard().put(new Key(i, j), el);
                } else {
                    this.getBoard().put(new Key(i, j), null);
                }
            }
        }


    }

    @Override
    public List<Key> availableSpace() {
        Map<Key, V> board = this.getBoard();
        List<Key> nullKeys = new ArrayList<>();
        for (Key key : board.keySet()) {
            if (board.get(key) == null) {
                nullKeys.add(key);
            }
        }
        return nullKeys;
    }

    @Override

    public void addItem(Key key, V value) {
        if (this.getBoard().containsKey(key)) {
            this.getBoard().put(key, value);
        } else {
            throw new IllegalArgumentException("Key not found: " + key);
        }
    }


    @Override
    public Key getKey(int i, int j) {
        Key searchKey = new Key(i, j);
        if (this.getBoard().containsKey(searchKey)) {
            for (Key key : this.getBoard().keySet()) {
                if (key.equals(searchKey)) {
                    return key;
                }
            }
        }
        return null;

    }

    @Override
    public V getValue(Key key) {
        if (this.getBoard().containsKey(key)) {
            return this.getBoard().get(key);
        } else {
            throw new IllegalArgumentException("Key not found: " + key);
        }
    }

    @Override
    public List<Key> getColumn(int j) {
        if (j > this.getWidth() - 1 || j < 0) {
            throw new IllegalArgumentException("Illegal column index");
        }
        List<Key> column = new ArrayList<>();
        for (int i = 0; i < this.getHeight(); i++) {
            Key key = new Key(i, j);
            if (this.getBoard().containsKey(key)) {
                column.add(key);
            } else {
                throw new IllegalArgumentException("Key not found: " + key);
            }
        }
        return column;
    }

    @Override
    public List<Key> getRow(int i) {
        if (i > this.getHeight() - 1 || i < 0) {
            throw new IllegalArgumentException("Illegal row index");
        }
        List<Key> row = new ArrayList<>();
        for (int j = 0; j < this.getHeight(); j++) {
            Key key = new Key(i, j);
            if (this.getBoard().containsKey(key)) {
                row.add(key);
            } else {
                throw new IllegalArgumentException("Key not found: " + key);
            }
        }
        return row;
    }


    @Override
    public boolean hasValue(V value) {
        return this.getBoard().containsValue(value);

    }

    @Override
    public List<V> getValues(List<Key> keys) {
        List<V> valuesList = new ArrayList<>();
        for (Key key : keys) {
            if (this.getBoard().containsKey(key)) {
                valuesList.add(this.getBoard().get(key));
            } else {
                throw new IllegalArgumentException("Key not found: " + key);
            }
        }
        return valuesList;

    }
}
