package com.example.wificonnect;

public interface IUser {


    String TABLE_USER = "user";

    String COLUMN_ID = "user_id";
    String COLUMN_USER_NAME = "username";
    String COLUMN_PASSWORD = "password";

    String USER_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "+
            TABLE_USER +" ("+
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+
            COLUMN_USER_NAME+ " VARCHAR(50) NOT NULL UNIQUE ,"+
            COLUMN_PASSWORD+" VARCHAR(50) NOT NULL "+
            ");";

    public String[] USER_COLUMNS = new String[] {
            COLUMN_ID,
            COLUMN_USER_NAME,
            COLUMN_PASSWORD
    };

    public String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS "+TABLE_USER;
}
