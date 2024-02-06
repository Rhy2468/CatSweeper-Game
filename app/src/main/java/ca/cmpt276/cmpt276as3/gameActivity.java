package ca.cmpt276.cmpt276as3;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import ca.cmpt276.cmpt276as3.model.Game;
import ca.cmpt276.cmpt276as3.model.MessageFragment;
import ca.cmpt276.cmpt276as3.model.Options;

/*
    Game Activity UI Code
    Purpose: Injects values from Game Class (Game Logic) onto the screen and sets images onto buttons
            for the game.
 */

public class gameActivity extends AppCompatActivity {
    private Options opt = Options.getInstance();
    private int NUM_ROWS;
    private int NUM_COLS;
    Random rand = new Random();
    Button buttons[][] = new Button[opt.getGridY()][opt.getGridX()];
    public Game game;

    public static Intent makeLaunchIntent(Context c){
        return new Intent(c, gameActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        NUM_COLS = opt.getGridX();
        NUM_ROWS = opt.getGridY();
        //Create new game, set bomb locations, and create button matrix
        game = new Game();
        game.setBombs();
        populateButtons();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private void populateButtons() {

        //Table for button matrix
        TableLayout table = findViewById(R.id.tlGame);

        //For loop to generate fixed number of buttons based on rows/cols
        for (int row = 0; row < NUM_ROWS; row++){
            updateScores();
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < NUM_COLS; col++){
                final int finalCol = col;
                final int finalRow = row;

                Button btn = new Button(this);
                buttons[row][col] = btn;
                btn.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                //Make text not clip on small buttons
                btn.setPadding(0,0,0,0);
                bushToButton(btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    int buttonPressed = 0;
                    @Override
                    public void onClick(View v) {
                        Animation rotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                        btn.startAnimation(rotateAnimation);
                        buttonPressed++;
                        //On click, check if button is bomb or not with helper func
                        if (game.CheckPos(finalCol, finalRow) && buttonPressed == 1){
                            //if a bomb, call helper func that prints out bomb picture
                            bombPosClicked(finalCol, finalRow);
                            updateScans();
                            //check if all bombs found, if so call helper func to end game
                            if (game.addMinesHit() == true){
                                congrats();
                            }
                            //Else, get scan for bombs in respective col/row and display on button
                        } else {
                            //call animation for scan here
                            scanAnimation(finalRow, finalCol);

                            game.addScanned(finalCol, finalRow);
                            game.incrementScanned();
                            btn.setTextColor(getApplication().getResources().getColor(R.color.white));
                            SpannableString scan = new SpannableString("" + game.scanBombs(finalCol, finalRow, buttons));
                            scan.setSpan(new ForegroundColorSpan(Color.WHITE), 0, scan.length(), 0);
                            btn.setText(scan);
                            btn.setEnabled(false);
                        }
                        //Call helper func to update bombs found(title) and scans and disable button
                        updateScores();
                    }
                });
                tableRow.addView(btn);
            }
        }
    }

    private void scanAnimation(int row, int col){
        for (int currRow = 0; currRow < NUM_ROWS; currRow++){
            for (int currCol = 0; currCol < NUM_COLS; currCol++){
                if(currRow == row || currCol == col){
                    Button btn = buttons[currRow][currCol];
                    Animation rotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                    btn.startAnimation(rotateAnimation);

                }
            }
        }
    }

    private void updateScans() {
        for (int i = 0; i < NUM_ROWS ; i++){
            for(int j = 0; j < NUM_COLS; j++){
                Button btn = buttons[i][j];

                int count = game.scanBombs(j, i, buttons);
                if(game.inScannedList(j, i)){
                    SpannableString scan = new SpannableString("" + count);
                    scan.setSpan(new ForegroundColorSpan(Color.WHITE), 0, scan.length(), 0);
                    btn.setText(scan);
                }
            }
        }
    }

    private void bushToButton(Button btn){
        int rng = rand.nextInt(2);

        String filename = "bush" + rng;

        int id = getResources().getIdentifier(filename, "drawable", gameActivity.this.getPackageName());

        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),id);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, 100, 100, true);
        Resources resource = getResources();

        btn.setBackground(new BitmapDrawable(resource, scaledBitmap));

    }

    private void congrats() {
        //IMAGE FROM: https://www.dreamstime.com/illustration-calligraphic-inscription-congratulations-vector-element-design-illustration-calligraphic-inscription-image149833255
        FragmentManager manager = getSupportFragmentManager();
        MessageFragment dialog = new MessageFragment();
        dialog.show(manager, "MessageDialog");
    }

    private void bombPosClicked(int col, int row) {
        Button button = buttons[row][col];
        lockButtonSizes();
        //Randomly get picture index
        int rng = rand.nextInt(4);

        //Come up with name of file we want
        String filename = "cat" + rng;

        int id = getResources().getIdentifier(filename, "drawable", gameActivity.this.getPackageName());
        //Scale image to button
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),id);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));
    }

    private void lockButtonSizes() {
        for (int row = 0; row < NUM_ROWS; row++){
            for (int col = 0; col < NUM_COLS; col++){
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);

            }
        }
    }

    private void updateScores(){
        TextView minesFound = findViewById(R.id.tvMinesFound);
        TextView scans = findViewById(R.id.tvScansUsed);

        String bombs = getString(R.string.bombs, game.getMinesHit(), game.getMines());
        String scanedMined = getString(R.string.scansUsed, game.getScans());
        minesFound.setText(bombs);
        scans.setText(scanedMined);
    }
}