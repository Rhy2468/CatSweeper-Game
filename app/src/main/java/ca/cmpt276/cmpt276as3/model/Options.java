package ca.cmpt276.cmpt276as3.model;


/*
    Options logic class
    Purpose: Receives user input from options UI screen and sets game values based off of it, and
            shares game data across activities using Singleton
 */

public class Options {
    private int mines;
    private int gridX;
    private int gridY;

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    private static Options instance;

    private Options() {
    }
    public static Options getInstance(){
        if (instance == null){
            instance = new Options();
        }

        return instance;
    }

    public int getMines() {
        return mines;
    }

    public void setMines(int mine) {
        mines = mine;
    }

    public void setBoardSize(int finalIndex) {
        if (finalIndex == 0){
            gridY = 4;
            gridX = 6;
        } else if (finalIndex == 1){
            gridY = 5;
            gridX = 10;
        } else if (finalIndex == 2){
            gridY = 6;
            gridX = 15;
        }
    }
}
