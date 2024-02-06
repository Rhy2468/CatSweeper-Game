package ca.cmpt276.cmpt276as3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/*
    Welcome Screen Activity
    Purpose: Welcomes users into game using a basic animation and displays a start
            button that brings users into main menu
*/
public class welcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //make image move
        //reference
        //https://stackoverflow.com/questions/2032304/android-imageview-animation
        RotateAnimation anim = new RotateAnimation(0f, -360f, 300f, 300f);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(900);

        // Start animating the image
        final ImageView img = (ImageView) findViewById(R.id.granny);
        img.startAnimation(anim);

        //register button click
        Button start = findViewById(R.id.helpGranny);
        start.setBackgroundColor(Color.rgb(255, 165, 0));
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}