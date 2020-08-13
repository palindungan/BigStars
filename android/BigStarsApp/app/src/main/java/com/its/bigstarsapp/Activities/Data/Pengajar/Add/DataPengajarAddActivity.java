package com.its.bigstarsapp.Activities.Data.Pengajar.Add;

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

import com.its.bigstarsapp.Activities.Data.Pengajar.Add.presenter.DataPengajarAddPresenter;
import com.its.bigstarsapp.Activities.Data.Pengajar.Add.presenter.IDataPengajarAddPresenter;
import com.its.bigstarsapp.Activities.Data.Pengajar.Add.view.IDataPengajarAddView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

import java.io.IOException;

public class DataPengajarAddActivity extends AppCompatActivity implements View.OnClickListener, IDataPengajarAddView {

    IDataPengajarAddPresenter dataPengajarAddPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    ImageView ivFoto;
    EditText edtNama, edtUsername, edtPassword, edtKonfirmasiPassword, edtAlamat, edtNoHp;
    Button btnSubmit;

    private Bitmap bitmap;
    String data_photo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengajar_add);

        dataPengajarAddPresenter = new DataPengajarAddPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        ivFoto = findViewById(R.id.iv_foto);
        edtNama = findViewById(R.id.edt_nama);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtKonfirmasiPassword = findViewById(R.id.edt_konfirmasi_password);
        edtAlamat = findViewById(R.id.edt_alamat);
        edtNoHp = findViewById(R.id.edt_no_hp);
        btnSubmit = findViewById(R.id.btn_submit);

        globalProcess.initActionBar(toolbar);

        ivFoto.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle(globalMessage.getValidasiAddData());
        alertDialogBuilder
                .setMessage(globalMessage.getPilihYaAddData())
                .setPositiveButton(globalMessage.getYa(), (dialog, id) -> {

                    String inputNama = edtNama.getText().toString().trim();
                    String inputUsername = edtUsername.getText().toString().trim();
                    String inputPassword = edtPassword.getText().toString().trim();
                    String inputKonfirmasi_password = edtKonfirmasiPassword.getText().toString().trim();
                    String inputAlamat = edtAlamat.getText().toString().trim();
                    String inputNo_hp = edtNoHp.getText().toString().trim();
                    String inputFoto = data_photo;

                    boolean isEmpty = false;
                    boolean isInvalidKonfirmasi = false;

                    if (TextUtils.isEmpty(inputNama)) {
                        isEmpty = true;
                        edtNama.setError(globalMessage.getValidasiNamaKosong());
                    } else if (TextUtils.isEmpty(inputUsername)) {
                        isEmpty = true;
                        edtUsername.setError(globalMessage.getValidasiUsernameKosong());
                    } else if (TextUtils.isEmpty(inputPassword)) {
                        isEmpty = true;
                        edtPassword.setError(globalMessage.getValidasiPasswordKosong());
                    } else if (TextUtils.isEmpty(inputAlamat)) {
                        isEmpty = true;
                        edtAlamat.setError(globalMessage.getValidasiAlamatKosong());
                    } else if (TextUtils.isEmpty(inputNo_hp)) {
                        isEmpty = true;
                        edtNoHp.setError(globalMessage.getValidasiNoHpKosong());
                    }

                    if (!inputPassword.equals(inputKonfirmasi_password)) {
                        isInvalidKonfirmasi = true;
                        edtKonfirmasiPassword.setError(globalMessage.getValidasiKonfirmasiPasswordSalah());
                    }

                    try {
                        if (!isEmpty && !isInvalidKonfirmasi) {
                            dataPengajarAddPresenter.onSubmit(
                                    "" + inputNama,
                                    "" + inputUsername,
                                    "" + inputPassword,
                                    "" + inputAlamat,
                                    "" + inputNo_hp,
                                    "" + inputFoto);
                        }
                    } catch (Exception e) {
                        globalProcess.onErrorMessage(globalMessage.getErrorAddData() + e.toString());
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
            startActivityForResult(Intent.createChooser(intent, globalMessage.getPilihGambar()), 1);
        } else if (view.getId() == R.id.btn_submit) {
            showDialog();
        }
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
        return true;
    }
}