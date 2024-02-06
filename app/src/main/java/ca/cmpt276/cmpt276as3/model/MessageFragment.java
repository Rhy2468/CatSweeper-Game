package ca.cmpt276.cmpt276as3.model;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import ca.cmpt276.cmpt276as3.R;
/*
    Alert Dialog logic
    Purpose: Creates an alert dialog which appears when user has found all bombs.
            Clicking the button on dialog brings user back to main menu, clicking
            off brings them back to game
 */
public class MessageFragment extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Create the view to show
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.alert_layout, null);

        //Create a button listener
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        getActivity().finish();
                    case DialogInterface.BUTTON_NEGATIVE:
                        getActivity().finish();
                }

            }
        };
        //Build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle("You Win!!!")
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .create();
    }
}
