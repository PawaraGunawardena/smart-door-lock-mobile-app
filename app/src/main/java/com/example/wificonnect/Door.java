package com.example.wificonnect;

public class Door {

    private String username;
    private String ssid;
    private String wifi_password;
    private String door_password;

    public Door(String username, String ssid, String wifi_password, String door_password) {
        this.username = username;
        this.ssid = ssid;
        this.wifi_password = wifi_password;
        this.door_password = door_password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getWifi_password() {
        return wifi_password;
    }

    public void setWifi_password(String wifi_password) {
        this.wifi_password = wifi_password;
    }

    public String getDoor_password() {
        return door_password;
    }

    public void setDoor_password(String door_password) {
        this.door_password = door_password;
    }

    public Door() {

    }
}
