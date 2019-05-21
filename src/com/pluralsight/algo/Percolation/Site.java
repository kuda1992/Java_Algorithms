package com.pluralsight.algo.Percolation;

public class Site {
    private int x;
    private int y;
    private boolean isOpen;
    private int value;

    public Site(int x, int y, boolean isOpen, int value) {
        this.setX(x);
        this.setY(y);
        this.setOpen(isOpen);
        this.setValue(value);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        return "x: " + this.getX() + " , y: " + this.getY() + " , isOpen: " + this.isOpen() + " , value: " + this.getValue();
    }

    public boolean compareTo(int x, int y) {
        if (this.getX() == x && this.getY() == y) {
            return true;
        }
        return false;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
