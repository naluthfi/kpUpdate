package com.example.windows10.nyobadua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class regis extends AppCompatActivity {
    private EditText regUsername;
    private EditText regPasw;
    private EditText regMhs1;
    private EditText regMhs2;
    private EditText regMhs3;
    private EditText company;
    private EditText dosen;
    private Button btnsignup;
    private EditText textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        regUsername = (EditText) findViewById(R.id.regUsername);


    }
}
