package com.its.bigstarsapp.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.its.bigstarsapp.Activities.Home.Admin.HomeAdminActivity;
import com.its.bigstarsapp.Activities.Home.Pengajar.HomePengajarActivity;
import com.its.bigstarsapp.Activities.Home.WaliMurid.HomeWaliMuridActivity;
import com.its.bigstarsapp.Activities._Main.MainActivity;

import java.util.HashMap;

public class SessionManager {

    public Context context;
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "LOGIN";

    public final String STATUS_LOGIN = "STATUS_LOGIN";
    public final String ID_USER = "ID_USER";
    public final String NAMA = "NAMA";
    public final String USERNAME = "USERNAME";
    public final String HAK_AKSES = "HAK_AKSES";
    public final String NO_REK = "NO_REK";

    public final String STATUS_ACTIVITY = "STATUS_ACTIVITY";

    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void setDataUser(String id_user, String nama, String username, String hak_akses, String no_rek) {
        editor.putBoolean(STATUS_LOGIN, true);
        editor.putString(ID_USER, id_user);
        editor.putString(NAMA, nama);
        editor.putString(USERNAME, username);
        editor.putString(HAK_AKSES, hak_akses);
        editor.putString(NO_REK, no_rek);
        editor.apply();
    }

    public HashMap<String, String> getDataUser() {
        HashMap<String, String> user = new HashMap<>();

        String data_default = "Data Kosong";
        user.put(ID_USER, sharedPreferences.getString(ID_USER, data_default));
        user.put(NAMA, sharedPreferences.getString(NAMA, data_default));
        user.put(USERNAME, sharedPreferences.getString(USERNAME, data_default));
        user.put(HAK_AKSES, sharedPreferences.getString(HAK_AKSES, data_default));
        user.put(NO_REK, sharedPreferences.getString(NO_REK, data_default));

        return user;
    }

    public void checkLogin() {
        if (this.getStatusLogin()) {
            String hak_akses = getHakAkses();
            Intent intent;
            switch (hak_akses) {
                case "admin":
                    intent = new Intent(context, HomeAdminActivity.class);
                    context.startActivity(intent);
                    break;
                case "pengajar":
                    intent = new Intent(context, HomePengajarActivity.class);
                    context.startActivity(intent);
                    break;
                case "wali_murid":
                    intent = new Intent(context, HomeWaliMuridActivity.class);
                    context.startActivity(intent);
                    break;
            }
        }
    }

    public boolean getStatusLogin() {
        return sharedPreferences.getBoolean(STATUS_LOGIN, false);
    }

    public void logout() {
        editor.clear();
        editor.commit();

        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public void setStatusActivity(String statusActivity) {
        editor.putString(STATUS_ACTIVITY, statusActivity);
        editor.apply();
    }

    public String getStatusActivity() {
        return sharedPreferences.getString(STATUS_ACTIVITY, "kosong");
    }

    public String getHakAkses() {
        return sharedPreferences.getString(HAK_AKSES, "kosong");
    }
}
