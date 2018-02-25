package com.oproom;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView ipTextView = findViewById(R.id.ipTextView);
        ipTextView.setText(getIp());

        SharedPreferences.Editor actEditor = getPreferences(Context.MODE_PRIVATE).edit();
        actEditor.putString("act-test-key", "act-test-value");
        actEditor.commit();

        SharedPreferences.Editor sharedEditor = ((Context)this).getSharedPreferences("shared-prefs-name", Context.MODE_PRIVATE).edit();
        sharedEditor.putString("shared-test-key", "shared-test-value");
        sharedEditor.commit();

    }

    public String getIp() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
        final String formattedIpAddress = String.format("%d.%d.%d.%d",
                (ipAddress & 0xff),
                (ipAddress >> 8 & 0xff),
                (ipAddress >> 16 & 0xff),
                (ipAddress >> 24 & 0xff));
        return formattedIpAddress;
    }
}
