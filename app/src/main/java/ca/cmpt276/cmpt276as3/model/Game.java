package ca.cmpt276.cmpt276as3.model;

import android.widget.Button;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/*
    Game Logic Class
    Purpose: Handles all the main menu UI functions, and obtains SharedPreferences data and stores
            data.
 */
public class Game {
    private int mines;
    private int gridX;
    private int gridY;
    private int minesHit;
    private int scans;
    private static Options optData;
    private List<Coordinate> BombLocation = new ArrayList<>();
    private List<Coordinate> ScannedCoords = new ArrayList<>();

    public Game() {
        optData = Options.getInstance();
        return;
    }

    public void getOptData() {
        gridX = optData.getGridX();
        gridY = optData.getGridY();
        mines = optData.getMines();
    }
    public int getMinesHit() {
        return minesHit;
    }
    public boolean addMinesHit() {
        minesHit++;
        if (minesHit == mines){
            return true;
        }
        return false;
    }
    public int getMines() {
        return mines;
    }
    public int getScans() {
        return scans;
    }


    public void setBombs(){
        getOptData();
        //Keep track of bombs placed
        int TotalBombs = 0;

        //Keep placing bombs till desired amount is hit
        while(TotalBombs < mines){
            //Get random numbers
            Random rand = new Random();
            int widthPOS = rand.nextInt(gridX);
            int lengthPOS = rand.nextInt(gridY);

            //Create new coordinate with random data
            Coordinate curr = new Coordinate(lengthPOS, widthPOS, false);

            //If bomb count is 0, insert
            if (TotalBombs == 0){
                BombLocation.add(curr);
                TotalBombs++;
            //Else check if position is already taken and if not place bomb and add to list
            } else {
                if (CheckPos(widthPOS,lengthPOS) == false) {
                    BombLocation.add(curr);
                    TotalBombs++;
                }
            }
        }

        setAllBombsToNotFound();
    }

    private void setAllBombsToNotFound() {
        for (int i = 0; i < BombLocation.size(); i++) {
            Coordinate curr = BombLocation.get(i);
            curr.setFound(false);
        }
    }

    public boolean CheckPos(int col, int row){
        for (int i = 0; i < BombLocation.size(); i++){
            Coordinate curr = BombLocation.get(i);
            int x = curr.getX();
            int y = curr.getY();

            if (col == x && row == y){
               curr.setFound(true);
                return true;
            }
        }
        return false;
    }

    public int scanBombs(int finalCol, int finalRow, Button[][] buttons) {
        int count = 0;

        for (int i =0; i < BombLocation.size(); i++ ){
            Coordinate bomb = BombLocation.get(i);
            int x = bomb.getX();
            int y = bomb.getY();
            boolean found = bomb.isFound();

            if (finalCol == x && found == false){
                count++;
            }
            if (finalRow == y && found == false){
                count++;
            }

        }
        return count;
    }

    public boolean inScannedList(int col, int row){
        for (int i = 0; i < ScannedCoords.size(); i++){
            Coordinate curr = ScannedCoords.get(i);
            int x = curr.getX();
            int y = curr.getY();
            if (col == x && row == y){
                curr.setFound(true);
                return true;
            }
        }
        return false;
    }

    public void addScanned(int finalCol, int finalRow) {
        Coordinate scannedBtn = new Coordinate(finalRow, finalCol, false);
        ScannedCoords.add(scannedBtn);
    }

    public void incrementScanned() {
        scans++;
    }
}
