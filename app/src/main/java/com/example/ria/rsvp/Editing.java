package com.example.ria.rsvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Editing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }



    public void saveChangesClicked (View view){
        DBHelper dbHelper = new DBHelper(this);

        EditText editTask = findViewById(R.id.editTask);
        EditText editDate = findViewById(R.id.editDueDate);
        EditText editOwner = findViewById(R.id.editOwner);

        String taskName = editTask.getText().toString();
        String taskDate = editDate.getText().toString();

        dbHelper.updateTask(taskName, taskDate);

        Intent intent = new Intent(this, ToDoList.class);
        startActivity(intent);


    }
}
