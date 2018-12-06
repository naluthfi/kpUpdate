package com.example.windows10.nyobadua;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    private static final String app_name = "KP-Update";
    private static final String is_login = "islogin";
    public static final String keyUname = "uname";
    public static final String keyCompany = "company";

    public SessionManager(Context context) {
        this.context = context;
        //pref = context.getSharedPreferences(app_name, mode);
        editor = pref.edit();
    }

    public void createSession(String username, String company){
        editor.putBoolean(is_login, true);
        editor.putString(keyUname, username);
        editor.putString(keyCompany, company);
        editor.commit();
    }

    public void checkLogin(){
        if (!this.is_login()){
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }else {
            Intent i = new Intent(context, home.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    private boolean is_login() {
        return pref.getBoolean(is_login, false);
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(app_name, pref.getString(app_name, null));
        user.put(keyUname, pref.getString(keyUname, null));
        return user;
    }
}
