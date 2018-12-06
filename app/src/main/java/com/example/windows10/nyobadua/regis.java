package com.example.windows10.nyobadua;

import android.content.Intent;
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
    private EditText nrpMhs1;
    private EditText regMhs2;
    private EditText nrpMhs2;
    private EditText regMhs3;
    private EditText nrpMhs3;
    private EditText company;
    private EditText dosen;
    private Button btnsignup;
    private TextView textView3;
    String popup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        regUsername = (EditText) findViewById(R.id.regUsername);
        regPasw = (EditText) findViewById(R.id.regPasw);
        regMhs1 = (EditText) findViewById(R.id.mhs1);
        nrpMhs1 = (EditText) findViewById(R.id.nrp1);
        regMhs2 = (EditText) findViewById(R.id.mhs2);
        nrpMhs2 = (EditText) findViewById(R.id.nrp2);
        regMhs3 = (EditText) findViewById(R.id.mhs3);
        nrpMhs3 = (EditText) findViewById(R.id.nrp3);
        company = (EditText) findViewById(R.id.company);
        dosen = (EditText) findViewById(R.id.dosen);
        //textView3 = (TextView) findViewById(R.id.textView3);
        btnsignup = (Button) findViewById(R.id.btnsignup);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_in = regUsername.getText().toString();
                String password_in = regPasw.getText().toString();
                String mhs1 = regMhs1.getText().toString();
                String nrp1 = nrpMhs1.getText().toString();
                String mhs2 = regMhs2.getText().toString();
                String nrp2 = nrpMhs2.getText().toString();
                String mhs3 = regMhs3.getText().toString();
                String nrp3 = nrpMhs3.getText().toString();
                String companyName = company.getText().toString();
                String dosenName = dosen.getText().toString();
                sendPost(username_in, password_in, mhs1, nrp1, mhs2, nrp2, mhs3, nrp3, companyName, dosenName);
            }
        });
    }

    public void sendPost(
            final String username,
            final String password,
            final String mhs1,
            final String nrp1,
            final String mhs2,
            final String nrp2,
            final String mhs3,
            final String nrp3,
            final String companyName,
            final String dosenName
    ) {
        try { //192.168.43.7
            String url = "http://pot-valiant-pine.000webhostapp.com/auth/signup?" +
                    "username="+ username +"&" +
                    "password="+ password +"&" +
                    "dosen="+ dosenName +"&" +
                    "perusahaan="+ companyName +"&" +
                    "anggota_kelompok1="+ mhs1 +"&" +
                    "nrp_anggota_kelompok1="+ nrp1 +"&" +
                    "anggota_kelompok2="+ mhs2 +"&" +
                    "nrp_anggota_kelompok2="+ nrp2 +"&" +
                    "anggota_kelompok3="+ mhs3 +"&" +
                    "nrp_anggota_kelompok3="+ nrp3;
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
            BufferedReader reader=null;
            String text = "";
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setUseCaches(true);
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
                int myNum=0;
                myNum = Integer.parseInt(status);
                if (myNum==201){
                    popup = "Registrasi berhasil. Akun anda dalam proses validasi.";
                    Intent intent = new Intent(regis.this,MainActivity.class);
                    regis.this.startActivity(intent);
                }
                else if (myNum==422){
                    popup = "Registrasi gagal. Username anda sudah digunakan.";
                }
                else{
                    popup = "Registrasi Gagal. Silahkan coba lagi.";
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
}
