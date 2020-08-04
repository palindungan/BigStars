package com.its.bigstarsapp.Controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import es.dmoral.toasty.Toasty;

public class GlobalProcess {

    Context context;

    public GlobalProcess(Context context) {
        this.context = context;
    }

    public void onSuccessMessage(String message){
        Toasty.success(context, message, Toast.LENGTH_SHORT).show();
    }

    public void onErrorMessage(String message){
        Toasty.error(context, message, Toast.LENGTH_SHORT).show();
    }

    public String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);

        return encodedImage;
    }
    // end of image process
}
