package com.example.windows10.nyobadua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaporanActivity extends AppCompatActivity {

    private Button lokasi;
    private Button foto;
    private Button simpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);

        lokasi = (Button) findViewById(R.id.lokasi);
        lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaporanActivity.this,MapsActivity.class);
                LaporanActivity.this.startActivity(intent);
            }
        });

        foto = (Button) findViewById(R.id.foto);
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaporanActivity.this,kamera.class);
                LaporanActivity.this.startActivity(intent);
            }
        });
        simpan = (Button) findViewById(R.id.simpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}
