package com.bm.balanceme.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WorkoutDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "workout.db";
    private static final int DATABASE_VERSION = 1;

    // Table and columns for the main workout data
    public static final String TABLE_WORKOUT = "workouts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DIFFICULTY = "difficulty";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_IMAGE = "image";

    private static final String DATABASE_CREATE_WORKOUT = "create table "
            + TABLE_WORKOUT + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_TITLE
            + " text not null, " + COLUMN_DIFFICULTY
            + " text not null, " + COLUMN_DURATION
            + " text not null, " + COLUMN_IMAGE
            + " integer not null);";

    public WorkoutDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_WORKOUT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle upgrades if needed
    }

    private void clearWorkoutData(SQLiteDatabase database) {
        database.execSQL("DELETE FROM " + TABLE_WORKOUT); // Delete all rows
        // or
        // database.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUT); // Drop the table
    }
}

