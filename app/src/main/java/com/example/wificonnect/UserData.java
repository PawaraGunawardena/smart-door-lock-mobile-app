package com.example.wificonnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserData {

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public UserData(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(IUser.COLUMN_USER_NAME, user.getUsername());
        values.put(IUser.COLUMN_PASSWORD, user.getPassword());

        db.insert(IUser.TABLE_USER, null, values);

    }

    public User getUser(String user_name) {
        final String selectionArgs[] = {String.valueOf(user_name)};
        final String selection = IUser.COLUMN_USER_NAME + " = ? ";
        User user = null;
        Cursor cursor = db.query(
                IUser.TABLE_USER,
                IUser.USER_COLUMNS,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                user = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();

        }
        return user;
    }

    private User cursorToEntity(Cursor cursor) {
        String user_name = null;
        String password = null;

        int iuser_name;
        int ipassword;

        if(cursor != null ){
            if(cursor.getColumnIndex(IUser.COLUMN_USER_NAME) !=-1){
                iuser_name = cursor.getColumnIndexOrThrow(IUser.COLUMN_USER_NAME);
                user_name = cursor.getString(iuser_name);
            }

            if(cursor.getColumnIndex(IUser.COLUMN_PASSWORD) !=-1){
                ipassword = cursor.getColumnIndexOrThrow(IUser.COLUMN_PASSWORD);
                password = cursor.getString(ipassword);
            }

        }else{

        }
        return new User(user_name, password);
    }
}
