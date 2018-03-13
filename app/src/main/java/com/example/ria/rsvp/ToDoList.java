package com.example.ria.rsvp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ListView;


public class ToDoList extends AppCompatActivity {

    public ListView listViewTasks;
    public Toolbar toolbar;
    public FloatingActionButton floatingActionButton;
    public FloatingActionButton floatingActionClear;
    public FloatingActionButton floatingActionUpdate;
    public CheckBox checkBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        listViewTasks = findViewById(R.id.toDoList);


        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionClear = findViewById(R.id.floatingActionClear);
        floatingActionUpdate = findViewById(R.id.floatingActionUpdate);
        checkBox = findViewById(R.id.checkBoxDone);

        setUpToDoCursor();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DBHelper dbHelper = new DBHelper(this);


        if(item.getItemId() == R.id.guestListItem){
            Intent intent = new Intent (this, Guestlist.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.todoListItem){
            Intent intent = new Intent(this, Guestlist.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpToDoCursor() {

        DBHelper dbHelper = new DBHelper(this);
        Cursor c = dbHelper.getAllTasks();
        ToDoAdapter toDoAdapter = new ToDoAdapter(this, c);
        listViewTasks.setAdapter(toDoAdapter);
        toDoAdapter.changeCursor(dbHelper.getAllTasks());


        listViewTasks.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent (ToDoList.this, Rsvp.class);
            startActivity(intent);
        });

        floatingActionButton.setOnClickListener((view)->{
            Task task = dbHelper.addTask("Handla", "180217", 1);
            toDoAdapter.changeCursor(dbHelper.getAllTasks());
        });

        floatingActionClear.setOnClickListener((view)->{
            dbHelper.deleteTask();
            toDoAdapter.changeCursor(dbHelper.getAllTasks());
        });

        floatingActionUpdate.setOnClickListener((view)-> {
            Intent intent = new Intent (this, Editing.class);
            startActivity(intent);
            toDoAdapter.changeCursor(dbHelper.getAllTasks());
        });


            /*
            Integer pos = (Integer)checkBox.getTag();
            Toast.makeText(this, "Checkbox "+pos+" clicked!", Toast.LENGTH_SHORT).show();
            */


            // TODO! Fixa så att den i checkade tasken skickas vidare till en update funktion.
            // Hur får vi tag på vilken som är vald? Och då behöver vi på något sätt skicka med det
            // objektet så att vi vet var i databasen vi ska ändra.

    }




}

