package com.example.ria.rsvp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Ria on 2018-02-05.
 */

public class ListAdapter extends CursorAdapter {

    private LayoutInflater myInflater;

    public ListAdapter(Context context, Cursor c) {
        super(context, c);
        myInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.list_name);
        TextView allergyTextView = view.findViewById(R.id.list_allergy);

        nameTextView.setText(cursor.getString(cursor.getColumnIndex("name")));
        allergyTextView.setText(cursor.getString(cursor.getColumnIndex("allergy")));
    }
}
