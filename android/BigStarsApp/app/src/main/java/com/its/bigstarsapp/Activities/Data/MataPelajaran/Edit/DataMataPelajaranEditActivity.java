package com.its.bigstarsapp.Activities.Data.MataPelajaran.Edit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.its.bigstarsapp.Activities.Data.MataPelajaran.Edit.presenter.DataMataPelajaranEditPresenter;
import com.its.bigstarsapp.Activities.Data.MataPelajaran.Edit.presenter.IDataMataPelajaranEditPresenter;
import com.its.bigstarsapp.Activities.Data.MataPelajaran.Edit.view.IDataMataPelajaranEditView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

public class DataMataPelajaranEditActivity extends AppCompatActivity implements View.OnClickListener, IDataMataPelajaranEditView {

    public static final String EXTRA_ID_MATA_PELAJARAN = "EXTRA_ID_MATA_PELAJARAN";
    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    String id_mata_pelajaran, nama;

    IDataMataPelajaranEditPresenter dataMataPelajaranEditPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    EditText edtNama;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mata_pelajaran_edit);

        dataMataPelajaranEditPresenter = new DataMataPelajaranEditPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        edtNama = findViewById(R.id.edt_nama);
        btnUpdate = findViewById(R.id.btn_update);

        id_mata_pelajaran = getIntent().getStringExtra(EXTRA_ID_MATA_PELAJARAN);
        nama = getIntent().getStringExtra(EXTRA_NAMA);

        globalProcess.initActionBar(toolbar);

        inisiasiAwal(
                "" + nama);

        btnUpdate.setOnClickListener(this);
    }

    private void inisiasiAwal(String nama) {
        edtNama.setText(nama);
    }

    private void showDialogUpdate() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle(globalMessage.getValidasiUpdateData());
        alertDialogBuilder
                .setMessage(globalMessage.getPilihYaUpdateData())
                .setPositiveButton(globalMessage.getYa(), (dialog, id) -> {
                    String inputNama = edtNama.getText().toString().trim();
                    boolean isEmpty = false;
                    if (TextUtils.isEmpty(inputNama)) {
                        isEmpty = true;
                        edtNama.setError(globalMessage.getValidasiNamaKosong());
                    }
                    try {
                        if (!isEmpty) {
                            dataMataPelajaranEditPresenter.onUpdate(
                                    "" + id_mata_pelajaran,
                                    "" + inputNama);
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