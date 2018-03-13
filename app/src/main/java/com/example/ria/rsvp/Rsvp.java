package com.example.ria.rsvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Rsvp extends AppCompatActivity {

    public String guestName;
    public String guestAllergy;
    public CheckBox yesBox;
    public CheckBox noBox;
    public int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsvp);
        yesBox = findViewById(R.id.checkBoxYes);
        noBox = findViewById(R.id.checkBoxNO);

    }

    public void yesClicked(View view){
        if (noBox.isChecked()){
            noBox.setChecked(false);
        }
    }

    public void noClicked(View view){
        if(yesBox.isChecked()) {
            yesBox.setChecked(false);
        }
    }


    public int getCheckBox(){

        if(yesBox.isChecked()){
            status = 1;
        }
        else if(noBox.isChecked()){
            status = 0;
        }
        return status;


    }

    public void saveInfo (View view){
        DBHelper dbHelper = new DBHelper(this);
        EditText rsvpName = findViewById(R.id.rsvpName);
        EditText rsvpAllergy = findViewById(R.id.rsvpAllergy);

        guestName = rsvpName.getText().toString();
        guestAllergy = rsvpAllergy.getText().toString();

        getCheckBox();

        Invited invited = dbHelper.addInvited(guestName, guestAllergy, status);


        Toast.makeText(this, "SAVED", Toast.LENGTH_SHORT).show();

      //  Intent intent = new Intent (this, Guestlist.class);
      //  startActivity(intent);

        // TODO! Toast istället för intent.

    }

}
