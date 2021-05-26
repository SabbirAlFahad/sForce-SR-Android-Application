package com.itbl.sforce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    Button btn_pSettings,btn_order_list,btn_logout, btn_sEntry;
    TextView txt_pSettings, txt_oList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        btn_pSettings =  findViewById(R.id.btn_pSettings);
        btn_order_list =  findViewById(R.id.btn_order_list);
        btn_logout =  findViewById(R.id.btn_logout);
        txt_pSettings =  findViewById(R.id.txt_pSettings);
        txt_oList =  findViewById(R.id.txt_oList);
        btn_sEntry =  findViewById(R.id.btn_sEntry);

        txt_pSettings.setTypeface(ResourcesCompat.getFont(this, R.font.kaushan_script));
        txt_oList.setTypeface(ResourcesCompat.getFont(this, R.font.autour_one));

        btn_pSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToProfile();
            }
        });

        btn_order_list.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToShopList();
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });

        btn_sEntry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToShopEntry();
            }
        });

    }

    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void goToShopEntry() {
        Intent intent = new Intent(this, ShopEntry.class);
        startActivity(intent);
    }

    private void goToProfile() {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    private void goToShopList() {
        Intent intent = new Intent(this, OrderList.class);
        startActivity(intent);
    }

}
