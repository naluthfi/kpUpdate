package com.example.windows10.nyobadua;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.SharedPreferences;

import java.util.HashMap;

import static com.example.windows10.nyobadua.MainActivity.PT;
import static com.example.windows10.nyobadua.MainActivity.anggota_1;
import static com.example.windows10.nyobadua.MainActivity.anggota_2;
import static com.example.windows10.nyobadua.MainActivity.anggota_3;

public class home extends AppCompatActivity {
private ImageButton imageButton;
private Button button;
private TextView textViewCompany, nama1, nama2, nama3;
//private String anggota1, anggota2, anggota3, pt;
//public static final String anggota_1 = "anggota1", anggota_2 = "anggota2", anggota_3 = "anggota3", PT = "pt";
//public static final String anggota_1, anggota_2, anggota_3, PT;
//SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //sessionManager = new SessionManager(getApplicationContext());
        //HashMap<String, String> user = sessionManager.getUserDetails();
        //String name = user.get(SessionManager.kunci_uname);
        //textViewCompany.setText(name);
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        textViewCompany = (TextView) findViewById(R.id.textView3);
        textViewCompany.setText(sharedpreferences.getString(PT, null));
        nama1 = (TextView) findViewById(R.id.textView6);
        nama1.setText(sharedpreferences.getString(anggota_1, null));
        nama2 = (TextView) findViewById(R.id.textView7);
        nama2.setText(sharedpreferences.getString(anggota_2, null));
        nama3 = (TextView) findViewById(R.id.textView8);
        nama3.setText(sharedpreferences.getString(anggota_3, null));
        imageButton = (ImageButton) findViewById(R.id.imagebutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(home.this,kamera.class);
                home.this.startActivity(intent);
            }
        });


    }

    public void laporan (View v) {
        Intent intent = new Intent(home.this,LaporanActivity.class);
        home.this.startActivity(intent);
    }

}
