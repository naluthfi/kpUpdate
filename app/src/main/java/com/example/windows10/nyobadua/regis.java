package com.example.windows10.nyobadua;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class regis extends AppCompatActivity {
    private EditText regUsername;
    private EditText regPasw;
    private EditText regMhs1;
    private EditText regMhs2;
    private EditText regMhs3;
    private EditText company;
    private EditText dosen;
    private Button btnsignup;
    private TextView textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        regUsername = (EditText) findViewById(R.id.regUsername);
        regPasw = (EditText) findViewById(R.id.regPasw);
        regMhs1 = (EditText) findViewById(R.id.regMhs1);
        regMhs2 = (EditText) findViewById(R.id.regMhs2);
        regMhs3 = (EditText) findViewById(R.id.regMhs3);
        company = (EditText) findViewById(R.id.company);
        dosen = (EditText) findViewById(R.id.dosen);
        textView3 = (TextView) findViewById(R.id.textView3);
        btnsignup = (Button) findViewById(R.id.btnsignup);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_in = regUsername.getText().toString();
                String password_in = regPasw.getText().toString();
                String mhs1 = regMhs1.getText().toString();
                String mhs2 = regMhs2.getText().toString();
                String mhs3 = regMhs3.getText().toString();
                String companyName = company.getText().toString();
                String dosenName = dosen.getText().toString();
                sendPost(username_in, password_in, mhs1, mhs2, mhs3, companyName, dosenName);
            }
        });
    }

    public void sendPost(
            final String username,
            final String password,
            final String mhs1,
            final String mhs2,
            final String mhs3,
            final String companyName,
            final String dosenName
    ) {
        try {
            String url = "http://10.122.14.219/kpupdate/public/auth/signup?" +
                    "username="+ username +"&" +
                    "password="+ password +"&" +
                    "dosen="+ dosenName +"&" +
                    "perusahaan="+ companyName +"&" +
                    "anggota_kelompok1="+ mhs1 +"&" +
                    "anggota_kelompok2="+ mhs2 +"&" +
                    "anggota_kelompok3="+ mhs3;
            String result = new SignUpRequest().execute(url).get();
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG
            ).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG
            ).show();
            e.printStackTrace();
        }
    }

    private class SignUpRequest extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                //conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                //conn.setRequestProperty("Accept","application/json");
                conn.setUseCaches(false);
                conn.setAllowUserInteraction(false);

                Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                Log.i("MSG" , conn.getResponseMessage());

                BufferedReader br;
                if (200 <= conn.getResponseCode() && conn.getResponseCode() <= 299) {
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }
                StringBuilder sb;
                sb = new StringBuilder();
                String output;
                while ((output = br.readLine()) != null) {
                    sb.append(output);
                }
                Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_LONG
                ).show();
                JSONObject jsonObject = new JSONObject(sb.toString());
                String result;
                if (jsonObject.getInt("status") == 201) {
                    result = "success";
                    System.out.println("success");
                } else {
                    result = jsonObject.getString("message");
                    System.out.println(jsonObject.getString("message"));
                }
                conn.disconnect();
                return result;
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG
                ).show();
                e.printStackTrace();
                return e.toString();
            }
        }
    }
}
