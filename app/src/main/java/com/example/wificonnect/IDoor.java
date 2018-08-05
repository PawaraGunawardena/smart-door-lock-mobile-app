package com.example.wificonnect;

public interface IDoor {

    String TABLE_DOOR = "door";

    String COLUMN_ID = "door_id";
    String COLUMN_USER_NAME = "username";
    String COLUMN_SSID = "ssid";
    String COLUMN_WIFI_PASSWORD = "wifi_password";
    String COLUMN_DOOR_PASSWORD = "door_password";

    String DOOR_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "+
            TABLE_DOOR +" ("+
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+
            COLUMN_USER_NAME+ " VARCHAR(50) NOT NULL ,"+
            COLUMN_SSID+" VARCHAR(50) NOT NULL ,"+
            COLUMN_WIFI_PASSWORD+" VARCHAR(50) NOT NULL ,"+
            COLUMN_DOOR_PASSWORD+" VARCHAR(50) NOT NULL "+
            ");";

    public String[] DOOR_COLUMNS = new String[] {
            COLUMN_ID,
            COLUMN_USER_NAME,
            COLUMN_SSID,
            COLUMN_WIFI_PASSWORD,
            COLUMN_DOOR_PASSWORD
    };

    public String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS "+TABLE_DOOR;
}
