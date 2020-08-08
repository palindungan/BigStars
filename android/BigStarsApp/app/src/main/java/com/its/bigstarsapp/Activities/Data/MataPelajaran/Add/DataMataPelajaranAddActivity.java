package com.its.bigstarsapp.Activities.Data.MataPelajaran.Add;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.its.bigstarsapp.Activities.Data.MataPelajaran.Add.presenter.DataMataPelajaranAddPresenter;
import com.its.bigstarsapp.Activities.Data.MataPelajaran.Add.presenter.IDataMataPelajaranAddPresenter;
import com.its.bigstarsapp.Activities.Data.MataPelajaran.Add.view.IDataMataPelajaranAddView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

public class DataMataPelajaranAddActivity extends AppCompatActivity implements View.OnClickListener, IDataMataPelajaranAddView {

    IDataMataPelajaranAddPresenter dataMataPelajaranAddPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    EditText edtNama;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mata_pelajaran_add);

        dataMataPelajaranAddPresenter = new DataMataPelajaranAddPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        edtNama = findViewById(R.id.edt_nama);
        btnSubmit = findViewById(R.id.btn_submit);

        globalProcess.initActionBar(toolbar);

        btnSubmit.setOnClickListener(this);
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle(globalMessage.getValidasiAddDataMataPelajaran());
        alertDialogBuilder
                .setMessage(globalMessage.getPilihYaAddDataMataPelajaran())
                .setPositiveButton(globalMessage.getYa(), (dialog, id) -> {
                    String inputNama = edtNama.getText().toString().trim();
                    boolean isEmpty = false;
                    if (TextUtils.isEmpty(inputNama)) {
                        isEmpty = true;
                        edtNama.setError(globalMessage.getValidasiNamaKosong());
                    }
                    try {
                        if (!isEmpty) {
                            dataMataPelajaranAddPresenter.onSubmit(
                                    "" + inputNama
                            );
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