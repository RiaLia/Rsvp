package com.example.ria.rsvp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ria on 2018-02-05.
 */

public class DBHelper extends SQLiteOpenHelper {

    public List<User> userList = new ArrayList<>();
    SQLiteDatabase db = getReadableDatabase();


    public static final int DB_VERSION = 12;


    public DBHelper(Context context) {
        super(context, "RsvpDataBase2", null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS Invited ( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "allergy TEXT," +
                "status INTEGER);";

        String sql2 = "CREATE TABLE IF NOT EXISTS Users ( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "login TEXT NOT NULL," +
                "password INTEGER," +
                "clearance INTEGER);";

        String sql3 = "CREATE TABLE IF NOT EXISTS Tasks ( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "taskName TEXT NOT NULL," +
                "taskDueDate TEXT," +
                "taskOwner INTEGER," +
                "FOREIGN KEY (taskOwner) REFERENCES Users (_id));";

        String insertSql = "INSERT INTO Users (login, password, clearance) VALUES ('Ria', '123', '1');";
        String insertSql2 = "INSERT INTO Users (login, password, clearance) VALUES ('Johanna', '456', '1');";
        String insertSql3 = "INSERT INTO Users (login, password, clearance) VALUES ('Max', '789', '1');";
        String insertSql4 = "INSERT INTO Users (login, password, clearance) VALUES ('Gäst', '111', '0');";

        String insertTask = "INSERT INTO Tasks (taskName, taskDueDate, taskOwner) VALUES ('Baka', '180217', '1');";
        String insertTask2 = "INSERT INTO Tasks (taskName, taskDueDate, taskOwner) VALUES ('Prova klänning', '190125', '2');";
        String insertTask3 = "INSERT INTO Tasks (taskName, taskDueDate, taskOwner) VALUES ('Bestämma meny', '190314', '3');";

        db.execSQL(sql);
        db.execSQL(sql2);
        db.execSQL(sql3);

        db.execSQL(insertSql);
        db.execSQL(insertSql2);
        db.execSQL(insertSql3);
        db.execSQL(insertSql4);

        db.execSQL(insertTask);
        db.execSQL(insertTask2);
        db.execSQL(insertTask3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < newVersion){
            db.delete("Invited",null,null);
            db.delete("Users",null,null);
            db.delete("Tasks", null,null);
            onCreate(db);
        }

    }


    public Invited addInvited(String name, String allergy, int status){
        Invited invited = new Invited();
        invited.invitedName = name;
        invited.invitedAllergy = allergy;
        invited.invitedStatus = status;

        return addInvited(invited);

    }



    public Invited addInvited(Invited invited) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", invited.invitedName);
        contentValues.put("allergy", invited.invitedAllergy);
        contentValues.put("status", invited.invitedStatus);
        invited._id = db.insert("Invited", null,contentValues);
        return invited;
    }


    public Cursor getAllInvitedCursor(){
        return db.query("Invited",null,null,null,null,null,null);
    }

    public Cursor getSortedOnName(){
        return db.query("Invited",null,null,null,null,null,"name");

    }

    public Cursor getSortedOnAllergy(){
        return db.query("Invited",null,null,null,null,null,"allergy DESC");

    }

    public Cursor getAllGuests(){
        String sql = "SELECT * FROM Invited WHERE status = 1;";
        return db.rawQuery(sql, null, null);
    }

    public Cursor getAllTasks(){
        return db.query("Tasks",null,null,null,null,null,null);
    }



    public List<User> getAllUsers(){
        Cursor c = db.query("Users", null,null,null,null,null,null);

        boolean sucsess = c.moveToFirst();
        if (sucsess) {
            do {
                User user = new User();
                user._id = c.getLong(0);
                user.login = c.getString(1);
                user.password = c.getInt(2);
                user.clearance = c.getInt(3);

                userList.add(user);

                Log.d("UserList", user._id + "," + user.login + "," + user.password + "," + user.clearance);
            } while (c.moveToNext());
        }
        c.close();
        return userList;
    }

    public Task addTask(Task task) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("taskName", task.taskName);
        contentValues.put("taskDueDate", task.taskDueDate);
        contentValues.put("taskOwner", task.taskOwner);
        task._id = db.insert("Tasks", null,contentValues);
        return task;
    }


    public Task addTask(String name, String dueDate, int owner){
        Task task = new Task();
        task.taskName = name;
        task.taskDueDate = dueDate;
        task.taskOwner = owner;

        return addTask(task);

    }

    public void deleteTask(){
        SQLiteDatabase db = this.getReadableDatabase();
        int maxId = getMaxID();

        String sql = " _id = " + maxId + ";";
        db.delete("Tasks", sql, null);
    }


    public void updateTask(String name, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        int maxId = getMaxID();

        String sql = "UPDATE Tasks SET taskName = '" + name + "' WHERE _id = " + maxId +";";
        String sql2 = "UPDATE Tasks SET taskDueDate = '" + date + "' WHERE _id = " + maxId + ";";
        db.execSQL(sql);
        db.execSQL(sql2);
    }


    public int getMaxID(){
        int maxId = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT max(_id) FROM Tasks;", new String[] {});

        if (cursor != null)
            if (cursor.moveToFirst()){
            maxId = cursor.getInt(0);
            }
        assert cursor != null;
        cursor.close();

        return maxId;
    }

}

