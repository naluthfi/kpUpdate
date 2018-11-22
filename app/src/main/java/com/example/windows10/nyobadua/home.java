package com.example.windows10.nyobadua;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;

public class home extends AppCompatActivity {
private ImageButton imageButton;
private Button laporan;
private TextView textViewCompany;
SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        laporan = (Button) findViewById(R.id.laporan);
        laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,LaporanActivity.class);
                home.this.startActivity(intent);
            }
        });
        //sessionManager = new SessionManager(getApplicationContext());
        //HashMap<String, String> user = sessionManager.getUserDetails();
        //String name = user.get(SessionManager.kunci_uname);
        //textViewCompany.setText(name);

        imageButton = (ImageButton) findViewById(R.id.imagebutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(home.this,kamera.class);
                home.this.startActivity(intent);
            }
        });


    }

}
