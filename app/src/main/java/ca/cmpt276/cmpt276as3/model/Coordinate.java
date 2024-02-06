package ca.cmpt276.cmpt276as3.model;

/*
    Coordinate Class
    Purpose: Class that stores the coordinate of a button and indicates whether or
            not it has been clicked/found. Used to store bomb locations and buttons
            that have been chosen to use a scan on
 */
public class Coordinate {
    private int x;
    private int y;
    private boolean found = false;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public Coordinate(int x, int y, boolean state) {
        this.y = x;
        this.x = y;
        found = state;
    }
}
