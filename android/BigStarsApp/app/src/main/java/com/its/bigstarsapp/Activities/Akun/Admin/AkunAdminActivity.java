package com.its.bigstarsapp.Activities.Akun.Admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.its.bigstarsapp.Activities.Akun.Admin.presenter.AkunAdminPresenter;
import com.its.bigstarsapp.Activities.Akun.Admin.presenter.IAkunAdminPresenter;
import com.its.bigstarsapp.Activities.Akun.Admin.view.IAkunAdminView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;

public class AkunAdminActivity extends AppCompatActivity implements View.OnClickListener, IAkunAdminView {

    IAkunAdminPresenter akunAdminPresenter;
    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    EditText edtNama, edtUsername, edtPassword, edtKonfirmasiPassword;
    ImageView ivFoto;
    Button btnUpdate;

    String id_admin;

    private Bitmap bitmap;
    String data_photo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun_admin);

        akunAdminPresenter = new AkunAdminPresenter(this, this);
        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        edtNama = findViewById(R.id.edt_nama);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtKonfirmasiPassword = findViewById(R.id.edt_konfirmasi_password);
        ivFoto = findViewById(R.id.iv_foto);
        btnUpdate = findViewById(R.id.btn_update);

        HashMap<String, String> user = sessionManager.getDataUser();
        id_admin = user.get(sessionManager.ID_USER);

        globalProcess.initActionBar(toolbar);

        if (!id_admin.isEmpty()) {
            akunAdminPresenter.onLoadData("" + id_admin);
        } else {
            sessionManager.logout();
        }

        ivFoto.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    private void showDialogUpdate() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle(globalMessage.getValidasiUpdateData());
        alertDialogBuilder
                .setMessage(globalMessage.getPilihYaUpdateData())
                .setPositiveButton(globalMessage.getYa(), (dialog, id) -> {

                    String inputNama = edtNama.getText().toString().trim();
                    String inputUsername = edtUsername.getText().toString().trim();
                    String inputPassword = edtPassword.getText().toString().trim();
                    String inputKonfirmasi_password = edtKonfirmasiPassword.getText().toString().trim();
                    String inputFoto = data_photo;

                    boolean isEmpty = false;
                    boolean isInvalidKonfirmasi = false;

                    if (TextUtils.isEmpty(inputNama)) {
                        isEmpty = true;
                        edtNama.setError(globalMessage.getValidasiNamaKosong());
                    } else if (TextUtils.isEmpty(inputUsername)) {
                        isEmpty = true;
                        edtUsername.setError(globalMessage.getValidasiUsernameKosong());
                    }

                    if (!inputPassword.equals(inputKonfirmasi_password)) {
                        isInvalidKonfirmasi = true;
                        edtKonfirmasiPassword.setError(globalMessage.getValidasiKonfirmasiPasswordSalah());
                    }

                    try {

                        if (!isEmpty && !isInvalidKonfirmasi) {
                            akunAdminPresenter.onUpdate(
                                    "" + id_admin,
                                    "" + inputNama,
                                    "" + inputUsername,
                                    "" + inputPassword,
                                    "" + inputFoto);
                        }

                    } catch (Exception e) {
                        globalProcess.onErrorMessage(globalMessage.getErrorUpdateData() + e.toString());
                    }
                })
                .setNegativeButton(globalMessage.getTidak(), (dialog, id) -> dialog.cancel());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_foto) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), 1);
        } else if (view.getId() == R.id.btn_update) {
            showDialogUpdate();
        }
    }

    @Override
    public void setNilaiDefault(String nama, String username, String foto) {
        edtNama.setText(nama);
        edtUsername.setText(username);
        String alamatFoto = globalVariable.getUrlUpload() + "image/admin/" + foto + ".jpg";
        Picasso.get().load(alamatFoto).placeholder(R.drawable.ic_default_account_circle).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(ivFoto);
    }

    @Override
    public void backPressed() {
        onBackPressed();
    }

    // proses pengolahan gambar
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ivFoto.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
                globalProcess.onErrorMessage(globalMessage.getErrorLoadingGambar() + e.toString());
            }

            data_photo = globalProcess.getStringImage(bitmap);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}