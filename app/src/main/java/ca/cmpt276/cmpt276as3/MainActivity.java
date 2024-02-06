package ca.cmpt276.cmpt276as3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import ca.cmpt276.cmpt276as3.model.Options;


/*
    Main Menu Activity
    Purpose: Handles all the main menu UI functions, and obtains SharedPreferences data and stores
            data.
 */
public class MainActivity extends AppCompatActivity {

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, MainActivity.class);
    }
    private Options opt = Options.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        opt.setBoardSize(getBoardIndex(this));
        opt.setMines(getNumMines(this));
        //Get start game and options buttons
        Button startGame = findViewById(R.id.btnStartGame);
        Button options = findViewById(R.id.btnOptions);
        Button help = findViewById(R.id.btnHelp);

        startGame.setBackgroundColor(Color.rgb(255, 165, 0));
        options.setBackgroundColor(Color.rgb(255, 165, 0));
        help.setBackgroundColor(Color.rgb(255, 165, 0));

        //if start game pressed, start game activity
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGame = gameActivity.makeLaunchIntent(MainActivity.this);
                startActivity(iGame);
            }
        });
        //if options button is pressed, start options activity
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iOptions = optionsActivity.makeLaunchIntent(MainActivity.this);
                startActivity(iOptions);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, helpActivity.class);
                startActivity(intent);
            }
        });
    }

    static public int getBoardIndex(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return prefs.getInt("index", 0);
    }

    static public int getNumMines(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return prefs.getInt("Num mines", 0);
    }
}