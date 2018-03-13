package com.example.ria.rsvp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class Guestlist extends AppCompatActivity {
    public ListView listView;
    public Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView = findViewById(R.id.invitedList);
        setupCursorAdapter();
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DBHelper dbHelper = new DBHelper(this);


        if(item.getItemId() == R.id.todoListItem){
            Intent intent = new Intent (this, ToDoList.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        else if(item.getItemId() == R.id.Namn) {
            Cursor c = dbHelper.getSortedOnName();
            ListAdapter listAdapter = new ListAdapter(this, c);
            listView.setAdapter(listAdapter);
            listAdapter.changeCursor(dbHelper.getSortedOnName());
            return true;
        }
        else if(item.getItemId() == R.id.Allergi){
            Cursor c = dbHelper.getSortedOnAllergy();
            ListAdapter listAdapter = new ListAdapter(this, c);
            listView.setAdapter(listAdapter);
            listAdapter.changeCursor(dbHelper.getSortedOnAllergy());
            return true;

        }
        else if (item.getItemId() == R.id.Alla){
            Cursor c = dbHelper.getAllInvitedCursor();
            ListAdapter listAdapter = new ListAdapter(this, c);
            listView.setAdapter(listAdapter);
            listAdapter.changeCursor(dbHelper.getAllInvitedCursor());
            return true;

        }
        else if (item.getItemId() == R.id.Gäster){
            Cursor c = dbHelper.getAllGuests();
            ListAdapter listAdapter = new ListAdapter(this, c);
            listView.setAdapter(listAdapter);
            listAdapter.changeCursor(dbHelper.getAllGuests());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupCursorAdapter() {

        DBHelper dbHelper = new DBHelper(this);
        Cursor c = dbHelper.getAllGuests();
        ListAdapter listAdapter = new ListAdapter(this, c);
        listView.setAdapter(listAdapter);
        listAdapter.changeCursor(dbHelper.getAllGuests());


        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent (getApplicationContext(), Rsvp.class);
            startActivity(intent);
        });


        }

}

