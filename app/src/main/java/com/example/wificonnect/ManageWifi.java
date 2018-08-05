package com.example.wificonnect;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import java.util.List;

public class ManageWifi {

    WifiManager mainWifi;

    public ManageWifi(Context context) {
        mainWifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }


    //this is used to connect with the given wifi network
    public void connectToDoor(String givenSSID, String givenPass) {

        onWifi();

        String networkSSID = givenSSID;
        String networkPass = givenPass;

        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"" + networkSSID + "\"";
        conf.preSharedKey = "\"" + networkPass + "\"";

        mainWifi.addNetwork(conf);
        List<WifiConfiguration> list = mainWifi.getConfiguredNetworks();
        for (WifiConfiguration i : list) {
            if (i.SSID != null && i.SSID.equals("\"" + networkSSID + "\"")) {
                mainWifi.disconnect();
                mainWifi.enableNetwork(i.networkId, true);
                mainWifi.reconnect();

                break;
            }
        }
    }

    public void onWifi() {
        if (!mainWifi.isWifiEnabled()) {
            mainWifi.setWifiEnabled(true);
            System.out.println("WiFi enabled");
        }
        //for(String a: receiverWifi)
    }

    public void offWifi() {
        if (mainWifi.isWifiEnabled()) {
            mainWifi.setWifiEnabled(false);
            System.out.println("WiFi Disabled");
        }
    }

}
