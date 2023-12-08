package com.example.Board;

public class Key {
    private int i;
    private int j;

    public Key(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    @Override
    public int hashCode() {
        int result = 23;
        result = 34 * result + i;
        result = 34 * result + j;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        //рефлексивность
        if (obj == this) {
            return true;
        }
        //проверка на null и тип
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Key key = (Key)obj;
        return key.getI() == this.getI() && key.getJ() == this.getJ();

    }

    @Override
    public String toString() {
        return "[" + getI() + ", " + getJ() + "]";
    }
}

