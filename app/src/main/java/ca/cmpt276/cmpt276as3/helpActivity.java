package ca.cmpt276.cmpt276as3;

import static android.R.color.holo_purple;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

/*
    Help screen activity
    Purpose: Displays how the game works and how to play it. Also displays the authors and resources
            used in the game like pictures.
 */
public class helpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //To make the link in text clickable
        TextView hyperlink = findViewById(R.id.tvlink);
        hyperlink.setLinkTextColor(getResources().getColor(holo_purple));
        hyperlink.setText(Html.fromHtml("<a href=https://opencoursehub.cs.sfu.ca/bfraser/grav-cms/cmpt276/home> Link to Course Website "));
        hyperlink.setMovementMethod((LinkMovementMethod.getInstance()));

        TextView hyperlink1 = findViewById(R.id.resources1);
        hyperlink1.setLinkTextColor(getResources().getColor(holo_purple));
        hyperlink1.setText(Html.fromHtml("<a href=https://www.dreamstime.com/grandmother-omg-scared-grandma-oh-my-god-emoji-old-lady-vector-grandmother-omg-scared-grandma-oh-my-god-emoji-old-lady-vector-image103955386> Link to Granny Image "));
        hyperlink1.setMovementMethod((LinkMovementMethod.getInstance()));

        TextView hyperlink2 = findViewById(R.id.resources2);
        hyperlink2.setLinkTextColor(getResources().getColor(holo_purple));
        hyperlink2.setText(Html.fromHtml("<a href=https://www.istockphoto.com/vector/spring-landscape-at-city-park-in-the-morning-natural-public-park-with-flowers-gm1363654226-435133979> Link to Park Image "));
        hyperlink2.setMovementMethod((LinkMovementMethod.getInstance()));

        TextView hyperlink3 = findViewById(R.id.resources3);
        hyperlink3.setLinkTextColor(getResources().getColor(holo_purple));
        hyperlink3.setText(Html.fromHtml("<a href=https://www.clipartmax.com/middle/m2K9A0d3m2i8i8A0_bush-clipart-bush-plant-clip-art/> Link to Bush 1 Image "));
        hyperlink3.setMovementMethod((LinkMovementMethod.getInstance()));

        TextView hyperlink4 = findViewById(R.id.resources4);
        hyperlink4.setLinkTextColor(getResources().getColor(holo_purple));
        hyperlink4.setText(Html.fromHtml("<a href=https://www.kindpng.com/imgv/bomhh_collection-of-transparent-bush-cartoon-free-png-png/> Link to Bush 2 Image "));
        hyperlink4.setMovementMethod((LinkMovementMethod.getInstance()));

        TextView hyperlink6 = findViewById(R.id.resources6);
        hyperlink6.setLinkTextColor(getResources().getColor(holo_purple));
        hyperlink6.setText(Html.fromHtml("<a href=https://www.dreamstime.com/illustration-calligraphic-inscription-congratulations-vector-element-design-illustration-calligraphic-inscription-image149833255> Link to Congratulation Image "));
        hyperlink6.setMovementMethod((LinkMovementMethod.getInstance()));
    }
}