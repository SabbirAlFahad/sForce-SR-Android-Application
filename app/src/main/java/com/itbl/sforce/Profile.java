package com.itbl.sforce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Profile extends AppCompatActivity {

    Button btn_pShopList, btn_pInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        btn_pShopList =  findViewById(R.id.btn_pShopList);
        btn_pInfo =  findViewById(R.id.btn_pInfo);

        btn_pShopList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToPshopList();
            }
        });

        btn_pInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                profileSettings();
            }
        });

    }

    private void profileSettings() {
        Intent intent = new Intent(this, ProfileSettings.class);
        startActivity(intent);
    }

    private void goToPshopList(){
            Intent intent = new Intent(this, PshopList.class);
            startActivity(intent);
        }

}
