package com.example.Board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Board<K,V> {
    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Map<K, V> getBoard() {
        return board;
    }

    private final Map<K, V> board = new HashMap<>();


    public Board(int weight, int height) {
        this.width = weight;
        this.height = height;
    }

    public abstract void fillBoard(List<V> list);

    public abstract List<K> availableSpace();

    public abstract void addItem(K key, V value);

    public abstract K getKey(int i, int j);

    public abstract V getValue(K key);

    public abstract List<K> getColumn(int j);

    public abstract List<K> getRow(int i);

    public abstract boolean hasValue(V value);

    public abstract List<V> getValues(List<K> keys);

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<K, V> entry : board.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            result.append("Key: ").append(key).append(" val = ").append(value).append("\n");
        }
        return result.toString();
    }
}
