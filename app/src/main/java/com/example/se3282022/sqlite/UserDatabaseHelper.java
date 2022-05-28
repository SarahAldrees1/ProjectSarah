package com.example.se3282022.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.se3282022.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Se328";
    private static final String TABLE_USERS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURE_NAME = "sure_name";
    private static final String KEY_FATHER_NAME = "father_name";
    private static final String KEY_NATIONAL_ID = "national_id";
    private static final String KEY_DOB = "dob";
    private static final String KEY_GENDER = "gender";

    public UserDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTable = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_SURE_NAME + " TEXT,"
                + KEY_FATHER_NAME + " TEXT,"
                + KEY_NATIONAL_ID + " TEXT,"
                + KEY_DOB + " TEXT,"
                + KEY_GENDER + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        onCreate(sqLiteDatabase);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, user.getId());
        values.put(KEY_NAME, user.getName());
        values.put(KEY_SURE_NAME, user.getSureName());
        values.put(KEY_FATHER_NAME, user.getFatherName());
        values.put(KEY_NATIONAL_ID, user.getNationalID());
        values.put(KEY_DOB, user.getDob());
        values.put(KEY_GENDER, user.getGender());
        int rows = db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        if (rows == 0)
            db.insertOrThrow(TABLE_USERS, null, values);
    }

    User getUser(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{KEY_ID,
                        KEY_NAME, KEY_SURE_NAME, KEY_FATHER_NAME, KEY_NATIONAL_ID, KEY_DOB, KEY_GENDER},
                KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();
            User user = new User(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
            return user;
        }
        return null;
    }


    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getString(0));
                user.setName(cursor.getString(1));
                user.setSureName(cursor.getString(2));
                user.setFatherName(cursor.getString(3));
                user.setNationalID(cursor.getString(4));
                user.setDob(cursor.getString(5));
                user.setGender(cursor.getString(6));

                userList.add(user);
            } while (cursor.moveToNext());
        }
        return userList;
    }


    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_SURE_NAME, user.getSureName());
        values.put(KEY_FATHER_NAME, user.getFatherName());
        values.put(KEY_NATIONAL_ID, user.getNationalID());
        values.put(KEY_DOB, user.getDob());
        values.put(KEY_GENDER, user.getGender());

        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
    }


    public int deleteUser(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int key = db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return key;
    }
}
