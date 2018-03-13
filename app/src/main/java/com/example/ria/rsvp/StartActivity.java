package com.example.ria.rsvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class StartActivity extends AppCompatActivity {
    EditText userName;
    EditText userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        userName = findViewById(R.id.userName);
        userPassword = findViewById(R.id.userPassword);
    }

    public void goForwardClicked(View view) {
        DBHelper dbHelper = new DBHelper(this);
        String login = userName.getText().toString().toLowerCase();
        int password = Integer.parseInt(userPassword.getText().toString());

        dbHelper.getAllUsers();

        for (int i = 0; i < dbHelper.userList.size(); i++) {
            String userLogin = dbHelper.userList.get(i).login.toLowerCase();
            int userPassword = dbHelper.userList.get(i).password;
            int clear = dbHelper.userList.get(i).clearance;


            if (Objects.equals(userLogin, login) && password == userPassword) {
                if (clear == 1){
                    Intent intent = new Intent(this, Guestlist.class);
                    startActivity(intent);
                    break;
                }
                else if (clear == 0){
                    Intent intent = new Intent (this, Rsvp.class);
                    startActivity(intent);
                    break;
                }

            }

            else {
                Toast.makeText(this, "WRONG USERNAME/PASSWORD, TRY AGAIN!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

