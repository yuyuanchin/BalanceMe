package com.bm.balanceme.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.bm.balanceme.Domain.WorkoutDomain;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDataSource {

    private SQLiteDatabase database;
    private WorkoutDBHelper dbHelper;

    public WorkoutDataSource(Context context) {
        dbHelper = new WorkoutDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Insert a new workout into the database
    public long insertSavedWorkout(String title, String difficulty, String duration, int image) {
        ContentValues values = new ContentValues();
        values.put(WorkoutDBHelper.COLUMN_TITLE, title);
        values.put(WorkoutDBHelper.COLUMN_DIFFICULTY, difficulty);
        values.put(WorkoutDBHelper.COLUMN_DURATION, duration);
        values.put(WorkoutDBHelper.COLUMN_IMAGE, image);

        return database.insert(WorkoutDBHelper.TABLE_WORKOUT, null, values);
    }

    // Delete a saved workout from the database
    public void deleteSavedWorkout(long savedId) {
        database.delete(WorkoutDBHelper.TABLE_WORKOUT,
                WorkoutDBHelper.COLUMN_ID + " = " + savedId, null);
    }

    // Get all workouts from the database
    public List<WorkoutDomain> getAllWorkouts() {
        List<WorkoutDomain> workoutList = new ArrayList<>();
        Cursor cursor = database.query(WorkoutDBHelper.TABLE_WORKOUT,
                null, null, null, null, null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                WorkoutDomain workout = cursorToWorkout(cursor);
                workoutList.add(workout);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return workoutList;
    }

    // Convert a cursor to a Workout object
    private WorkoutDomain cursorToWorkout(Cursor cursor) {
        WorkoutDomain workout = new WorkoutDomain();

        int idColumnIndex = cursor.getColumnIndex(WorkoutDBHelper.COLUMN_ID);
        int titleColumnIndex = cursor.getColumnIndex(WorkoutDBHelper.COLUMN_TITLE);
        int difficultyColumnIndex = cursor.getColumnIndex(WorkoutDBHelper.COLUMN_DIFFICULTY);
        int durationColumnIndex = cursor.getColumnIndex(WorkoutDBHelper.COLUMN_DURATION);
        int imageColumnIndex = cursor.getColumnIndex(WorkoutDBHelper.COLUMN_IMAGE);

        // Check if the column indices are valid before retrieving values
        if (idColumnIndex >= 0) {
            workout.setId(cursor.getLong(idColumnIndex));
        }

        if (titleColumnIndex >= 0) {
            workout.setWorkoutTitle(cursor.getString(titleColumnIndex));
        }

        if (difficultyColumnIndex >= 0) {
            workout.setDifficulty(cursor.getString(difficultyColumnIndex));
        }

        if (durationColumnIndex >= 0) {
            workout.setDuration(cursor.getString(durationColumnIndex));
        }

        if (imageColumnIndex >= 0) {
            workout.setWorkoutImg(cursor.getInt(imageColumnIndex));
        }

        return workout;
    }

    public boolean isWorkoutSaved(String workoutTitle) {
        Cursor cursor = database.query(
                WorkoutDBHelper.TABLE_WORKOUT,
                null,
                WorkoutDBHelper.COLUMN_TITLE + " = ?",
                new String[]{workoutTitle},
                null,
                null,
                null
        );

        boolean isSaved = cursor.getCount() > 0;
        cursor.close();
        return isSaved;
    }

    @SuppressLint("Range")
    public long getSavedWorkoutId(String workoutTitle) {
        Cursor cursor = database.query(
                WorkoutDBHelper.TABLE_WORKOUT,
                new String[]{WorkoutDBHelper.COLUMN_ID},
                WorkoutDBHelper.COLUMN_TITLE + " = ?",
                new String[]{workoutTitle},
                null,
                null,
                null
        );

        long savedId = -1;
        if (cursor.moveToFirst()) {
            savedId = cursor.getLong(cursor.getColumnIndex(WorkoutDBHelper.COLUMN_ID));
        }

        cursor.close();
        return savedId;
    }

    public void clearAllWorkoutData() {
        dbHelper.getWritableDatabase().execSQL("DELETE FROM " + WorkoutDBHelper.TABLE_WORKOUT);
        // or
        // dbHelper.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + WorkoutDBHelper.TABLE_WORKOUT);
    }

}

