package com.example.windows10.nyobadua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void login(View v) {
        Intent intent = new Intent(MainActivity.this,home.class);
        MainActivity.this.startActivity(intent);
    }

    public void signup(View v) {
        Intent intent = new Intent(MainActivity.this,regis.class);
        MainActivity.this.startActivity(intent);
    }
}