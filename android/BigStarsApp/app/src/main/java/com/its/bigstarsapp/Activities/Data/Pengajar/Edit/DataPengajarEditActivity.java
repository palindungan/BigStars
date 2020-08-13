package com.its.bigstarsapp.Activities.Data.Pengajar.Edit;

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

import com.its.bigstarsapp.Activities.Data.Pengajar.Edit.presenter.DataPengajarEditPresenter;
import com.its.bigstarsapp.Activities.Data.Pengajar.Edit.presenter.IDataPengajarEditPresenter;
import com.its.bigstarsapp.Activities.Data.Pengajar.Edit.view.IDataPengajarEditView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class DataPengajarEditActivity extends AppCompatActivity implements View.OnClickListener, IDataPengajarEditView {

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_USERNAME = "EXTRA_USERNAME";
    public static final String EXTRA_ALAMAT = "EXTRA_ALAMAT";
    public static final String EXTRA_NO_HP = "EXTRA_NO_HP";
    public static final String EXTRA_FOTO = "EXTRA_FOTO";
    String id_pengajar, nama, username, alamat, no_hp, foto;

    IDataPengajarEditPresenter dataPengajarEditPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    EditText edtNama, edtUsername, edtPassword, edtKonfirmasiPassword, edtAlamat, edtNoHp;
    ImageView ivFoto;
    Button btnUpdate;

    private Bitmap bitmap;
    String data_photo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengajar_edit);

        dataPengajarEditPresenter = new DataPengajarEditPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        edtNama = findViewById(R.id.edt_nama);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtKonfirmasiPassword = findViewById(R.id.edt_konfirmasi_password);
        edtAlamat = findViewById(R.id.edt_alamat);
        edtNoHp = findViewById(R.id.edt_no_hp);
        ivFoto = findViewById(R.id.iv_foto);
        btnUpdate = findViewById(R.id.btn_update);

        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);
        nama = getIntent().getStringExtra(EXTRA_NAMA);
        username = getIntent().getStringExtra(EXTRA_USERNAME);
        alamat = getIntent().getStringExtra(EXTRA_ALAMAT);
        no_hp = getIntent().getStringExtra(EXTRA_NO_HP);
        foto = getIntent().getStringExtra(EXTRA_FOTO);

        globalProcess.initActionBar(toolbar);

        inisiasiAwal(
                "" + nama,
                "" + username,
                "" + alamat,
                "" + no_hp,
                "" + foto);

        ivFoto.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    private void inisiasiAwal(String nama, String username, String alamat, String no_hp, String foto) {
        edtNama.setText(nama);
        edtUsername.setText(username);
        edtAlamat.setText(alamat);
        edtNoHp.setText(no_hp);
        String alamatFoto = globalVariable.getUrlUpload() + "image/pengajar/" + foto + ".jpg";
        Picasso.get().load(alamatFoto).resize(300, 600).centerInside().placeholder(R.drawable.ic_default_account_circle).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(ivFoto);
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
                            dataPengajarEditPresenter.onUpdate(
                                    "" + id_pengajar,
                                    "" + inputNama,
                                    "" + inputUsername,
                                    "" + inputPassword,
                                    "" + inputAlamat,
                                    "" + inputNo_hp,
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
            startActivityForResult(Intent.createChooser(intent, globalMessage.getPilihGambar()), 1);
        } else if (view.getId() == R.id.btn_update) {
            showDialogUpdate();
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