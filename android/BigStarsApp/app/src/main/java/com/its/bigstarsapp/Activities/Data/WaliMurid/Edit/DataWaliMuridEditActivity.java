package com.its.bigstarsapp.Activities.Data.WaliMurid.Edit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.its.bigstarsapp.Activities.Data.WaliMurid.Edit.presenter.DataWaliMuridEditPresenter;
import com.its.bigstarsapp.Activities.Data.WaliMurid.Edit.presenter.IDataWaliMuridEditPresenter;
import com.its.bigstarsapp.Activities.Data.WaliMurid.Edit.view.IDataWaliMuridEditView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

public class DataWaliMuridEditActivity extends AppCompatActivity implements View.OnClickListener, IDataWaliMuridEditView {

    public static final String EXTRA_ID_WALI_MURID = "EXTRA_ID_WALI_MURID";
    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_USERNAME = "EXTRA_USERNAME";
    public static final String EXTRA_ALAMAT = "EXTRA_ALAMAT";
    public static final String EXTRA_NO_HP = "EXTRA_NO_HP";
    String id_wali_murid, nama, username, alamat, no_hp;

    IDataWaliMuridEditPresenter dataWaliMuridEditPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    EditText edtNama, edtUsername, edtPassword, edtKonfirmasiPassword, edtAlamat, edtNoHp;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_wali_murid_edit);

        dataWaliMuridEditPresenter = new DataWaliMuridEditPresenter(this, this);

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
        btnUpdate = findViewById(R.id.btn_update);

        id_wali_murid = getIntent().getStringExtra(EXTRA_ID_WALI_MURID);
        nama = getIntent().getStringExtra(EXTRA_NAMA);
        username = getIntent().getStringExtra(EXTRA_USERNAME);
        alamat = getIntent().getStringExtra(EXTRA_ALAMAT);
        no_hp = getIntent().getStringExtra(EXTRA_NO_HP);

        globalProcess.initActionBar(toolbar);

        inisiasiAwal(
                "" + nama,
                "" + username,
                "" + alamat,
                "" + no_hp);

        btnUpdate.setOnClickListener(this);
    }

    private void inisiasiAwal(String nama, String username, String alamat, String no_hp) {
        edtNama.setText(nama);
        edtUsername.setText(username);
        edtAlamat.setText(alamat);
        edtNoHp.setText(no_hp);
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
                            dataWaliMuridEditPresenter.onUpdate(
                                    "" + id_wali_murid,
                                    "" + inputNama,
                                    "" + inputUsername,
                                    "" + inputPassword,
                                    "" + inputAlamat,
                                    "" + inputNo_hp);
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
        if (view.getId() == R.id.btn_update) {
            showDialogUpdate();
        }
    }

    @Override
    public void backPressed() {
        onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}