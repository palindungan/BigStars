package com.its.bigstarsapp.Activities.Data.KelasPertemuan.Add;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.its.bigstarsapp.Activities.Data.KelasPertemuan.Add.presenter.DataKelasPertemuanAddPresenter;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.Add.presenter.IDataKelasPertemuanAddPresenter;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.Add.view.IDataKelasPertemuanAddView;
import com.its.bigstarsapp.Adapters.AdapterDataMataPelajaranList;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.MataPelajaran;
import com.its.bigstarsapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class DataKelasPertemuanAddActivity extends AppCompatActivity implements View.OnClickListener, IDataKelasPertemuanAddView {

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    String id_pengajar;

    IDataKelasPertemuanAddPresenter dataKelasPertemuanAddPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    AdapterDataMataPelajaranList adapterDataMataPelajaranList;

    Toolbar toolbar;
    RecyclerView recyclerView;
    EditText edtNamaPelajaran, edtHari, edtHargaFee, edtHargaSpp;
    Button btnPilih, btnJamMulai, btnJamBerakhir, btnSubmit;

    String id_mata_pelajaran, nama_mata_pelajaran;
    String jam_mulai = "kosong";
    String jam_berakhir = "kosong";

    public static Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kelas_pertemuan_add);

        dataKelasPertemuanAddPresenter = new DataKelasPertemuanAddPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        edtNamaPelajaran = findViewById(R.id.edt_nama_mata_pelajaran);
        edtHari = findViewById(R.id.edt_hari);
        edtHargaFee = findViewById(R.id.edt_harga_fee);
        edtHargaSpp = findViewById(R.id.edt_harga_spp);
        btnPilih = findViewById(R.id.btn_pilih);
        btnJamMulai = findViewById(R.id.btn_jam_mulai);
        btnJamBerakhir = findViewById(R.id.btn_jam_berakhir);
        btnSubmit = findViewById(R.id.btn_submit);

        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);

        globalProcess.initActionBar(toolbar);

        HashMap<String, String> user = sessionManager.getDataUser();
        String hak_akses = user.get(sessionManager.HAK_AKSES);
        if (hak_akses != null) {
            if (hak_akses.equals("admin")) {

            } else if (hak_akses.equals("pengajar")) {
                edtHargaSpp.setVisibility(View.GONE);
            } else if (hak_akses.equals("wali_murid")) {
                edtHargaFee.setVisibility(View.GONE);
            } else {
                edtHargaFee.setVisibility(View.GONE);
                edtHargaSpp.setVisibility(View.GONE);
            }
        }

        btnPilih.setOnClickListener(this);
        btnJamMulai.setOnClickListener(this);
        btnJamBerakhir.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle(globalMessage.getValidasiAddData());
        alertDialogBuilder
                .setMessage(globalMessage.getPilihYaAddData())
                .setPositiveButton(globalMessage.getYa(), (dialog, id) -> {

                    String inputNamaPelajaran = edtNamaPelajaran.getText().toString().trim();
                    String inputHari = edtHari.getText().toString().trim();
                    String inputJamMulai = jam_mulai;
                    String inputJamBerakhir = jam_berakhir;
                    String inputHargaFee = edtHargaFee.getText().toString().trim();
                    String inputHargaSpp = edtHargaSpp.getText().toString().trim();

                    boolean isEmpty = false;

                    if (TextUtils.isEmpty(inputNamaPelajaran)) {
                        isEmpty = true;
                        edtNamaPelajaran.setError(globalMessage.getValidasiNamaMataPelajaranKosong());
                        globalProcess.onErrorMessage(globalMessage.getValidasiNamaMataPelajaranKosong());
                    } else if (TextUtils.isEmpty(inputHari)) {
                        isEmpty = true;
                        edtHari.setError(globalMessage.getValidasiHariKosong());
                    } else if (inputJamMulai.equals("kosong")) {
                        isEmpty = true;
                        btnJamMulai.setError(globalMessage.getValidasiJamMulaiKosong());
                        globalProcess.onErrorMessage(globalMessage.getValidasiJamMulaiKosong());
                    } else if (inputJamBerakhir.equals("kosong")) {
                        isEmpty = true;
                        btnJamBerakhir.setError(globalMessage.getValidasiJamBerakhirKosong());
                        globalProcess.onErrorMessage(globalMessage.getValidasiJamBerakhirKosong());
                    } else if (TextUtils.isEmpty(inputHargaFee)) {
                        isEmpty = true;
                        edtHargaFee.setError(globalMessage.getValidasiHargaFeeKosong());
                    } else if (TextUtils.isEmpty(inputHargaSpp)) {
                        isEmpty = true;
                        edtHargaSpp.setError(globalMessage.getValidasiHargaSppKosong());
                    }

                    try {

                        if (!isEmpty) {
                            dataKelasPertemuanAddPresenter.onSubmit(
                                    "" + id_pengajar,
                                    "" + id_mata_pelajaran,
                                    "" + inputHari,
                                    "" + inputJamMulai,
                                    "" + inputJamBerakhir,
                                    "" + inputHargaFee,
                                    "" + inputHargaSpp);
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
        dataKelasPertemuanAddPresenter.onLoadDataListMataPelajaran();
    }

    private void showDialogTimePicker(Button btn, String kode) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute1) -> {

                    String result = hourOfDay + ":" + minute1;

                    btn.setText(result);

                    if (kode.equals("jam_mulai")) {
                        jam_mulai = result;
                    } else if (kode.equals("jam_berakhir")) {
                        jam_berakhir = result;
                    }

                }, hour, minute, true);
        timePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_pilih) {
            showDialogPilih();
        } else if (view.getId() == R.id.btn_jam_mulai) {
            showDialogTimePicker(btnJamMulai, "jam_mulai");
        } else if (view.getId() == R.id.btn_jam_berakhir) {
            showDialogTimePicker(btnJamBerakhir, "jam_berakhir");
        } else if (view.getId() == R.id.btn_submit) {
            showDialog();
        }
    }

    @Override
    public void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList) {
        recyclerView = dialog.findViewById(R.id.recycler);
        adapterDataMataPelajaranList = new AdapterDataMataPelajaranList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(adapterDataMataPelajaranList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        adapterDataMataPelajaranList.setOnItemClickListener((view, position) -> {
            id_mata_pelajaran = dataModelArrayList.get(position).getId_mata_pelajaran();
            nama_mata_pelajaran = dataModelArrayList.get(position).getNama();

            edtNamaPelajaran.setText(nama_mata_pelajaran);
            dialog.dismiss();
        });

        dialog.show();
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