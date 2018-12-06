package com.example.windows10.nyobadua;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText loguser;
    private EditText logpass;
    private Button btnlogin;
    private Button button;
    String popup;
    //SessionManager sessionManager;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Uname = "UnameKey";
    public static final String Dosen = "DosenKey";
    public static final String PT = "PTKey";
    public static final String anggota_1 = "Anggota1Key";
    public static final String anggota_2 = "Anggota2Key";
    public static final String anggota_3 = "Anggota3Key";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loguser = (EditText) findViewById(R.id.loguser);
        logpass = (EditText) findViewById(R.id.logpass);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_in = loguser.getText().toString();
                String password_in = logpass.getText().toString();
                sendPostLogin(username_in, password_in);
            }
        });

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        //sessionManager = new SessionManager(getApplicationContext());
    }
    public void sendPostLogin(
            final String username,
            final String password
    ) {
        try { //192.168.43.7
            //10.122.14.219
            String url = "http://pot-valiant-pine.000webhostapp.com/auth/signin?" +
                    "username="+ username +"&" +
                    "password="+ password;
            String result = new LoginRequest().execute(url).get();
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG
            ).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG
            ).show();
            e.printStackTrace();
        }
    }

    private class LoginRequest extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings) {
            BufferedReader reader=null;
            String text = "";
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setUseCaches(false);
                conn.setAllowUserInteraction(false);

                Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                Log.i("MSG" , conn.getResponseMessage());

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = reader.readLine()) != null)
                {
                    // Append server response in string
                    sb.append(line + "\n");
                }
                text = sb.toString();
                JSONObject data = new JSONObject(text);
                String status = data.getString("status");
                String uname = data.getString("username");
                String dosen = data.getString("dosen");
                String perusahaan = data.getString("perusahaan");
                String anggota1 = data.getString("anggota_kelompok1");
                String anggota2 = data.getString("anggota_kelompok2");
                String anggota3 = data.getString("anggota_kelompok3");
                int myNum=0;
                myNum = Integer.parseInt(status);
                if (myNum==200){
                    popup = "Login Berhasil";
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(Uname, uname);
                    editor.putString(Dosen, dosen);
                    editor.putString(PT, perusahaan);
                    editor.putString(anggota_1, anggota1);
                    editor.putString(anggota_2, anggota2);
                    editor.putString(anggota_3, anggota3);
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this,home.class);
                    MainActivity.this.startActivity(intent);

                }
                else{
                    popup = "Login Gagal";
                }
                conn.disconnect();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG
                ).show();
                e.printStackTrace();
            }
            return (popup);
        }
    }

    public void signup(View v) {
        Intent intent = new Intent(MainActivity.this,regis.class);
        MainActivity.this.startActivity(intent);
    }

}