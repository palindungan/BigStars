package com.its.bigstarsapp.Activities.Data.WaliMurid.Add;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.its.bigstarsapp.Activities.Data.WaliMurid.Add.presenter.DataWaliMuridAddPresenter;
import com.its.bigstarsapp.Activities.Data.WaliMurid.Add.presenter.IDataWaliMuridAddPresenter;
import com.its.bigstarsapp.Activities.Data.WaliMurid.Add.view.IDataWaliMuridAddView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

public class DataWaliMuridAddActivity extends AppCompatActivity implements View.OnClickListener, IDataWaliMuridAddView {

    IDataWaliMuridAddPresenter dataWaliMuridAddPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    EditText edtNama, edtUsername, edtPassword, edtKonfirmasiPassword, edtAlamat, edtNoHp;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_wali_murid_add);

        dataWaliMuridAddPresenter = new DataWaliMuridAddPresenter(this, this);

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
        btnSubmit = findViewById(R.id.btn_submit);

        globalProcess.initActionBar(toolbar);

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
                            dataWaliMuridAddPresenter.onSubmit(
                                    "" + inputNama,
                                    "" + inputUsername,
                                    "" + inputPassword,
                                    "" + inputAlamat,
                                    "" + inputNo_hp);
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
        if (view.getId() == R.id.btn_submit) {
            showDialog();
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
        return true;
    }
}