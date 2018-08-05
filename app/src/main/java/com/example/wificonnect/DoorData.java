package com.example.wificonnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DoorData {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    ArrayList<Door> available_doors ;

    public DoorData(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void addDoor(Door door) {
        ContentValues values = new ContentValues();
        values.put(IDoor.COLUMN_USER_NAME, door.getUsername());
        values.put(IDoor.COLUMN_SSID, door.getSsid());
        values.put(IDoor.COLUMN_WIFI_PASSWORD, door.getWifi_password());
        values.put(IDoor.COLUMN_DOOR_PASSWORD, door.getDoor_password());

        db.insert(IDoor.TABLE_DOOR, null, values);
        System.out.println("Door has added 11111111111");
    }

    public ArrayList<Door> getUserDoor(String username) {

        available_doors = new ArrayList<Door>();
        try {
            final String selectionArgs[] = {String.valueOf(username)};
            final String selection = IDoor.COLUMN_USER_NAME + " = ? ";
            Door door;
            System.out.println("SERD 11111111111");
            Cursor cursor = db.query(
                    IDoor.TABLE_DOOR,
                    IDoor.DOOR_COLUMNS,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );
            System.out.println("NERD 11111111111");
            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    door = cursorToEntity(cursor);
                    available_doors.add(door);
                    cursor.moveToNext();
                }
                cursor.close();
                System.out.println("Door called 11111111111");
            }else{
                System.out.println("Null door cursor 11111111111");
            }

        }catch(Exception e){
            System.out.println("Error 123: 11111111111");
            System.out.println(e.getMessage());
        }
        return available_doors;
    }



    private Door cursorToEntity(Cursor cursor) {
        String username = null;
        String ssid = null;
        String wifi_password = null;
        String user_door_password = null;

        int iuser_name;
        int issid;
        int iwifi_password;
        int iuser_door_password;

        System.out.println("WWWWWW 11111111111");

        if(cursor != null ){

            System.out.println("VVVVVVV 11111111111");

            if(cursor.getColumnIndex(IDoor.COLUMN_USER_NAME) !=-1){
                iuser_name = cursor.getColumnIndexOrThrow(IDoor.COLUMN_USER_NAME);
                username = cursor.getString(iuser_name);
                System.out.println(username);
            }

            if(cursor.getColumnIndex(IDoor.COLUMN_SSID) !=-1){
                issid = cursor.getColumnIndexOrThrow(IDoor.COLUMN_SSID);
                ssid = cursor.getString(issid);
                System.out.println(ssid);
            }
            if(cursor.getColumnIndex(IDoor.COLUMN_WIFI_PASSWORD) !=-1){
                iwifi_password = cursor.getColumnIndexOrThrow(IDoor.COLUMN_WIFI_PASSWORD);
                wifi_password = cursor.getString(iwifi_password);
                System.out.println(wifi_password);
            }
            if(cursor.getColumnIndex(IDoor.COLUMN_DOOR_PASSWORD) !=-1){
                iuser_door_password = cursor.getColumnIndexOrThrow(IDoor.COLUMN_DOOR_PASSWORD);
                user_door_password = cursor.getString(iuser_door_password);
                System.out.println(user_door_password);
            }

        }else{
            System.out.println("Error code: 11111111111");
        }

        return new Door( username, ssid, wifi_password, user_door_password);
    }
}
