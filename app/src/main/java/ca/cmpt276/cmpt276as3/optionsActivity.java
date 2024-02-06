package ca.cmpt276.cmpt276as3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import ca.cmpt276.cmpt276as3.model.Options;

/*
    Options Activity UI Screen
    Purpose:Creates Radio buttons on screen to which the user can use to change
            game data. Also stores game data across executions using
            SharedPreferences.
 */
public class optionsActivity extends AppCompatActivity {
    private Options opt = Options.getInstance();

    public static Intent makeLaunchIntent(Context c){
        return new Intent(c, optionsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        //populate radio groups with options
        createRadioMinesButtons();
        createRadioButtons();
        opt.setMines(getNumMines(this));
        opt.setBoardSize(getBoardIndex(this));
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
    private void createRadioMinesButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_mine);
        int[] num = getResources().getIntArray(R.array.num_mines);

        for (int i = 0; i < 4; i++) {
            int size = num[i];

            RadioButton MineButton = new RadioButton(this);
            MineButton.setText(" " + size);

            MineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    opt.setMines(size);
                    saveNumMines(size);
                }
            });

            group.addView(MineButton);

            //select default button
            if (size == getNumMines(this)){
                MineButton.setChecked(true);
            }
        }

    }

    private void createRadioButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_boardSize);

        String[] num_size = getResources().getStringArray(R.array.num_board_size);
        //Create buttons
        for (int i = 0; i < 3; i++){
            String size = num_size[i];

            RadioButton button = new RadioButton(this);
            button.setText(size);

            //Set on-click callbacks
            int finalIndex = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    opt.setBoardSize(finalIndex);
                    saveBoardSize(size, finalIndex);
                }
            });

            //Add to radio groups
            group.addView(button);
            //select default button
            if (finalIndex == getBoardIndex(this)){
                button.setChecked(true);
            }
        }
    }

    private void saveNumMines(int num) {
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Num mines", num);
        editor.apply();
    }

    static public int getNumMines(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        int defaultVal = context.getResources().getInteger(R.integer.default_mine_total);
        return prefs.getInt("Num mines", defaultVal);
    }

    private void saveBoardSize(String size, int index) {
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("index", index);
        editor.apply();
    }

    static public int getBoardIndex(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        int defaultVal = context.getResources().getInteger(R.integer.default_board_size);
        return prefs.getInt("index", defaultVal);
    }

}
