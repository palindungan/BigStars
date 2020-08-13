package com.its.bigstarsapp.Activities.Data.Murid.Add;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
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

import com.its.bigstarsapp.Activities.Data.Murid.Add.presenter.DataMuridAddPresenter;
import com.its.bigstarsapp.Activities.Data.Murid.Add.presenter.IDataMuridAddPresenter;
import com.its.bigstarsapp.Activities.Data.Murid.Add.view.IDataMuridAddView;
import com.its.bigstarsapp.Adapters.AdapterDataWaliMuridList;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.WaliMurid;
import com.its.bigstarsapp.R;

import java.io.IOException;
import java.util.ArrayList;

public class DataMuridAddActivity extends AppCompatActivity implements View.OnClickListener, IDataMuridAddView {

    IDataMuridAddPresenter dataMuridAddPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    AdapterDataWaliMuridList adapterDataWaliMuridList;

    Toolbar toolbar;
    RecyclerView recyclerView;
    EditText edtNama, edtNamaWaliMurid, edtAlamat;
    Button btnPilih, btnSubmit;
    ImageView ivFoto;

    private Bitmap bitmap;
    String id_wali_murid, nama_wali_murid, alamat;
    String data_photo = "";

    public static Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_murid_add);

        dataMuridAddPresenter = new DataMuridAddPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        edtNama = findViewById(R.id.edt_nama);
        edtNamaWaliMurid = findViewById(R.id.edt_nama_wali_murid);
        edtAlamat = findViewById(R.id.edt_alamat);
        ivFoto = findViewById(R.id.iv_foto);
        btnPilih = findViewById(R.id.btn_pilih);
        btnSubmit = findViewById(R.id.btn_submit);

        globalProcess.initActionBar(toolbar);

        ivFoto.setOnClickListener(this);
        btnPilih.setOnClickListener(this);
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
                    String inputNamaWaliMurid = edtNamaWaliMurid.getText().toString().trim();
                    String inputAlamat = edtAlamat.getText().toString().trim();
                    String inputFoto = data_photo;

                    boolean isEmpty = false;

                    if (TextUtils.isEmpty(inputNama)) {
                        isEmpty = true;
                        edtNama.setError(globalMessage.getValidasiNamaKosong());
                    } else if (TextUtils.isEmpty(inputNamaWaliMurid)) {
                        isEmpty = true;
                        edtNamaWaliMurid.setError(globalMessage.getValidasiPilihSalahSatuData());
                        globalProcess.onErrorMessage(globalMessage.getValidasiPilihDataWaliMurid());
                    } else if (TextUtils.isEmpty(inputAlamat)) {
                        isEmpty = true;
                        edtAlamat.setError(globalMessage.getValidasiAlamatKosong());
                    }

                    try {
                        if (!isEmpty) {
                            dataMuridAddPresenter.onSubmit(
                                    "" + id_wali_murid,
                                    "" + inputNama,
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

    private void showDialogPilih() {
        dialog = new Dialog(this);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_list);

        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> dialog.dismiss());
        dataMuridAddPresenter.onLoadDataListWaliMurid();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_foto) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), 1);
        } else if (view.getId() == R.id.btn_pilih) {
            showDialogPilih();
        } else if (view.getId() == R.id.btn_submit) {
            showDialog();
        }
    }

    @Override
    public void onSetupListView(ArrayList<WaliMurid> dataModelArrayList) {
        recyclerView = dialog.findViewById(R.id.recycler);
        adapterDataWaliMuridList = new AdapterDataWaliMuridList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(adapterDataWaliMuridList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        adapterDataWaliMuridList.setOnItemClickListener((view, position) -> {
            id_wali_murid = dataModelArrayList.get(position).getId_wali_murid();
            nama_wali_murid = dataModelArrayList.get(position).getNama();
            alamat = dataModelArrayList.get(position).getAlamat();

            edtNamaWaliMurid.setText(nama_wali_murid);
            edtAlamat.setText(alamat);
            dialog.dismiss();
        });

        dialog.show();
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