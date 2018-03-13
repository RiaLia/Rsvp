package com.example.ria.rsvp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Ria on 2018-02-10.
 */

public class ToDoAdapter extends CursorAdapter {

    private LayoutInflater mylayout;

    public ToDoAdapter(Context context, Cursor c) {
        super(context, c);
        mylayout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_todo,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView taskView = view.findViewById(R.id.list_task);
        TextView descView = view.findViewById(R.id.list_desc);
        TextView ownerView = view.findViewById(R.id.list_owner);
        CheckBox doneCheck = view.findViewById(R.id.checkBoxDone);


        taskView.setText(cursor.getString(cursor.getColumnIndex("taskName")));
        descView.setText(cursor.getString(cursor.getColumnIndex("taskDueDate")));
        ownerView.setText(cursor.getString(cursor.getColumnIndex("taskOwner")));


    }
}
