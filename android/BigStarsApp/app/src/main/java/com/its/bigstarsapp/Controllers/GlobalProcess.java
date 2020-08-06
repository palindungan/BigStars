package com.its.bigstarsapp.Controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.ByteArrayOutputStream;

import es.dmoral.toasty.Toasty;

public class GlobalProcess {

    Context context;

    SessionManager sessionManager;

    public GlobalProcess(Context context) {
        this.context = context;

        sessionManager = new SessionManager(context);
    }

    public void onSuccessMessage(String message) {
        Toasty.success(context, message, Toast.LENGTH_SHORT).show();
    }

    public void onErrorMessage(String message) {
        Toasty.error(context, message, Toast.LENGTH_SHORT).show();
    }

    public String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);

        return encodedImage;
    }

    public void initActionBar(Toolbar toolbar) {
        ((AppCompatActivity) context).setSupportActionBar(toolbar);
        final ActionBar ab = ((AppCompatActivity) context).getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void dialogLogout(Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setTitle("Ingin Logout ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk Keluar Aplikasi !")
                .setPositiveButton("Ya", (dialog, id) -> {

                    try {
                        sessionManager.logout();
                    } catch (Exception e) {
                        onErrorMessage("Terjadi Kesalahan " + e.toString());
                    }

                })
                .setNegativeButton("Tidak", (dialog, id) -> dialog.cancel());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
