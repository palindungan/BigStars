package com.its.bigstarsapp.Activities.Data.KelasPertemuan.Edit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.Edit.presenter.DataKelasPertemuanEditPresenter;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.Edit.presenter.IDataKelasPertemuanEditPresenter;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.Edit.view.IDataKelasPertemuanEditView;
import com.its.bigstarsapp.Activities.Data.Murid.Edit.DataMuridEditActivity;
import com.its.bigstarsapp.Adapters.AdapterDataMataPelajaranList;
import com.its.bigstarsapp.Adapters.AdapterDataMuridList;
import com.its.bigstarsapp.Adapters.AdapterDataPengajarList;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.MataPelajaran;
import com.its.bigstarsapp.Models.Murid;
import com.its.bigstarsapp.Models.Pengajar;
import com.its.bigstarsapp.R;

import java.util.ArrayList;
import java.util.Calendar;

public class DataKelasPertemuanEditActivity extends AppCompatActivity implements View.OnClickListener, IDataKelasPertemuanEditView {

    public static final String EXTRA_ID_KELAS_PERTEMUAN = "EXTRA_ID_KELAS_PERTEMUAN";
    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    public static final String EXTRA_ID_MATA_PELAJARAN = "EXTRA_ID_MATA_PELAJARAN";
    public static final String EXTRA_NAMA_MATA_PELAJARAN = "EXTRA_NAMA_MATA_PELAJARAN";
    public static final String EXTRA_HARI = "EXTRA_HARI";
    public static final String EXTRA_JAM_MULAI = "EXTRA_JAM_MULAI";
    public static final String EXTRA_JAM_BERAKHIR = "EXTRA_JAM_BERAKHIR";
    public static final String EXTRA_HARGA_FEE = "EXTRA_HARGA_FEE";
    public static final String EXTRA_HARGA_SPP = "EXTRA_HARGA_SPP";
    public static final String EXTRA_ID_SHARING = "EXTRA_ID_SHARING";
    public static final String EXTRA_NAMA_SHARING = "EXTRA_NAMA_SHARING";

    String id_kelas_pertemuan, hari, jam_mulai, jam_berakhir, harga_fee, harga_spp, id_sharing, nama_sharing;
    String id_mata_pelajaran, nama_mata_pelajaran;
    String id_pengajar;

    IDataKelasPertemuanEditPresenter dataKelasPertemuanEditPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    EditText edtNamaMataPelajaran, edtHari, edtHargaFee, edtHargaSpp;
    Button btnPilih, btnJamMulai, btnJamBerakhir, btnUpdate, btnAbsen;
    TextView tvStatusSharing;
    ImageButton ibSharing, ibDeleteSharing;

    String statusActivity;

    public static Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kelas_pertemuan_edit);

        dataKelasPertemuanEditPresenter = new DataKelasPertemuanEditPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        edtNamaMataPelajaran = findViewById(R.id.edt_nama_mata_pelajaran);
        edtHari = findViewById(R.id.edt_hari);
        edtHargaFee = findViewById(R.id.edt_harga_fee);
        edtHargaSpp = findViewById(R.id.edt_harga_spp);
        btnPilih = findViewById(R.id.btn_pilih);
        btnJamMulai = findViewById(R.id.btn_jam_mulai);
        btnJamBerakhir = findViewById(R.id.btn_jam_berakhir);
        btnUpdate = findViewById(R.id.btn_update);
        tvStatusSharing = findViewById(R.id.tv_status_sharing);
        ibSharing = findViewById(R.id.ib_sharing);
        ibDeleteSharing = findViewById(R.id.ib_delete_sharing);

        btnUpdate = findViewById(R.id.btn_update);

        fab = findViewById(R.id.fab);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        id_kelas_pertemuan = getIntent().getStringExtra(EXTRA_ID_KELAS_PERTEMUAN);
        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);
        id_mata_pelajaran = getIntent().getStringExtra(EXTRA_ID_MATA_PELAJARAN);
        nama_mata_pelajaran = getIntent().getStringExtra(EXTRA_NAMA_MATA_PELAJARAN);
        hari = getIntent().getStringExtra(EXTRA_HARI);
        jam_mulai = getIntent().getStringExtra(EXTRA_JAM_MULAI);
        jam_berakhir = getIntent().getStringExtra(EXTRA_JAM_BERAKHIR);
        harga_fee = getIntent().getStringExtra(EXTRA_HARGA_FEE);
        harga_spp = getIntent().getStringExtra(EXTRA_HARGA_SPP);
        id_sharing = getIntent().getStringExtra(EXTRA_ID_SHARING);
        nama_sharing = getIntent().getStringExtra(EXTRA_NAMA_SHARING);

        globalProcess.initActionBar(toolbar);

        inisiasiAwal(
                "" + nama_mata_pelajaran,
                "" + hari,
                "" + jam_mulai,
                "" + jam_berakhir,
                "" + harga_fee,
                "" + harga_spp);

        dataKelasPertemuanEditPresenter.onLoadDataListMurid("" + id_kelas_pertemuan);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Your code to make your refresh action
            dataKelasPertemuanEditPresenter.onLoadDataListMurid("" + id_kelas_pertemuan);

            // CallYourRefreshingMethod();
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 1000);
        });

        statusActivity = sessionManager.getStatusActivity();
        if (statusActivity.equals("listPengajar->view->editKelasPertemuan")) {
            fab.show();

            fab.setOnClickListener(this);
            btnPilih.setOnClickListener(this);
            btnJamMulai.setOnClickListener(this);
            btnJamBerakhir.setOnClickListener(this);
            btnUpdate.setOnClickListener(this);
            ibSharing.setOnClickListener(this);
            ibDeleteSharing.setOnClickListener(this);
        } else if (statusActivity.equals("homePengajar->view->dataKelasPertemuanEdit")) {
            btnPilih.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.GONE);
            ibSharing.setVisibility(View.GONE);
            ibDeleteSharing.setVisibility(View.GONE);

            edtHari.setFocusable(false);
            edtHargaFee.setFocusable(false);
            edtHargaSpp.setFocusable(false);

            btnAbsen.setVisibility(View.VISIBLE);
            btnAbsen.setOnClickListener(this);
        }
    }

    private void inisiasiAwal(String nama_mata_pelajaran, String hari, String jam_mulai, String jam_berakhir, String harga_fee, String harga_spp) {
        edtNamaMataPelajaran.setText(nama_mata_pelajaran);
        edtHari.setText(hari);
        btnJamMulai.setText(jam_mulai);
        btnJamBerakhir.setText(jam_berakhir);
        edtHargaFee.setText(harga_fee);
        edtHargaSpp.setText(harga_spp);

        if (id_sharing.equals("null")) {
            ibSharing.setVisibility(View.VISIBLE);
            String txtStatusSharing = "Status : Tidak Sedang Dibagikan";
            tvStatusSharing.setText(txtStatusSharing);
        } else {
            ibDeleteSharing.setVisibility(View.VISIBLE);
            String txtStatusSharing = "Dibagikan Kepada " + nama_sharing;
            tvStatusSharing.setText(txtStatusSharing);
        }
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle(globalMessage.getValidasiUpdateData());
        alertDialogBuilder
                .setMessage(globalMessage.getPilihYaUpdateData())
                .setPositiveButton(globalMessage.getYa(), (dialog, id) -> {

                    String inputNamaPelajaran = edtNamaMataPelajaran.getText().toString().trim();
                    String inputHari = edtHari.getText().toString().trim();
                    String inputJamMulai = jam_mulai;
                    String inputJamBerakhir = jam_berakhir;
                    String inputHargaFee = edtHargaFee.getText().toString().trim();
                    String inputHargaSpp = edtHargaSpp.getText().toString().trim();

                    boolean isEmpty = false;

                    if (TextUtils.isEmpty(inputNamaPelajaran)) {
                        isEmpty = true;
                        edtNamaMataPelajaran.setError(globalMessage.getValidasiNamaMataPelajaranKosong());
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
                            dataKelasPertemuanEditPresenter.onUpdate(
                                    "" + id_kelas_pertemuan,
                                    "" + id_pengajar,
                                    "" + id_mata_pelajaran,
                                    "" + inputHari,
                                    "" + inputJamMulai,
                                    "" + inputJamBerakhir,
                                    "" + inputHargaFee,
                                    "" + inputHargaSpp);
                        }

                    } catch (Exception e) {
                        globalProcess.onErrorMessage(globalMessage.getErrorUpdateData() + e.toString());
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
        dataKelasPertemuanEditPresenter.onLoadDataListMataPelajaran();
    }

    private void showDialogSharing() {
        dialog = new Dialog(this);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_list);

        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> dialog.dismiss());
        dataKelasPertemuanEditPresenter.onLoadDataListPengajar();
    }

    private void showDialogDeleteSharing() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle(globalMessage.getValidasiHapusSharing());
        alertDialogBuilder
                .setMessage(globalMessage.getPilihYaHapusData())
                .setPositiveButton(globalMessage.getYa(), (dialog, id) -> {

                    try {
                        dataKelasPertemuanEditPresenter.onDeleteSharingKelasPertemuan("" + id_kelas_pertemuan);
                    } catch (Exception e) {
                        globalProcess.onErrorMessage(globalMessage.getErrorHapusData() + e.toString());
                    }

                })
                .setNegativeButton(globalMessage.getTidak(), (dialog, id) -> dialog.cancel());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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

    private void showDialogAddMurid() {
        dialog = new Dialog(this);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_list);

        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> dialog.dismiss());
        dataKelasPertemuanEditPresenter.onLoadDataListMuridSemua();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_pilih) {
            showDialogPilih();
        } else if (view.getId() == R.id.btn_jam_mulai) {
            showDialogTimePicker(btnJamMulai, "jam_mulai");
        } else if (view.getId() == R.id.btn_jam_berakhir) {
            showDialogTimePicker(btnJamBerakhir, "jam_berakhir");
        } else if (view.getId() == R.id.btn_update) {
            showDialog();
        } else if (view.getId() == R.id.ib_sharing) {
            showDialogSharing();
        } else if (view.getId() == R.id.ib_delete_sharing) {
            showDialogDeleteSharing();
        } else if (view.getId() == R.id.fab) {
            showDialogAddMurid();
        } else if (view.getId() == R.id.btn_absen) {
            // 
        }
    }

    @Override
    public void onSetupListViewMataPelajaran(ArrayList<MataPelajaran> dataModelArrayList) {
        recyclerView = dialog.findViewById(R.id.recycler);
        AdapterDataMataPelajaranList adapterDataMataPelajaranList = new AdapterDataMataPelajaranList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(adapterDataMataPelajaranList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        adapterDataMataPelajaranList.setOnItemClickListener((view, position) -> {
            id_mata_pelajaran = dataModelArrayList.get(position).getId_mata_pelajaran();
            nama_mata_pelajaran = dataModelArrayList.get(position).getNama();

            edtNamaMataPelajaran.setText(nama_mata_pelajaran);
            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    public void onSetupListViewPengajar(ArrayList<Pengajar> dataModelArrayList) {
        recyclerView = dialog.findViewById(R.id.recycler);
        AdapterDataPengajarList adapterDataPengajarList = new AdapterDataPengajarList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(adapterDataPengajarList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        adapterDataPengajarList.setOnItemClickListener((view, position) -> {
            id_sharing = dataModelArrayList.get(position).getId_pengajar();
            nama_sharing = dataModelArrayList.get(position).getNama();

            if (id_pengajar.equals(id_sharing)) {
                globalProcess.onErrorMessage("Pilih Pengajar Lain !");
            } else {
                dataKelasPertemuanEditPresenter.onSharingKelasPertemuan(
                        "" + id_kelas_pertemuan,
                        "" + id_sharing,
                        "" + nama_sharing);
            }

            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    public void onSetupListViewMuridSemua(ArrayList<Murid> dataModelArrayList) {
        recyclerView = dialog.findViewById(R.id.recycler);
        AdapterDataMuridList adapterDataMuridList = new AdapterDataMuridList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(adapterDataMuridList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        adapterDataMuridList.setOnItemClickListener((view, position) -> {
            String id_murid = dataModelArrayList.get(position).getId_murid();

            dataKelasPertemuanEditPresenter.onSubmitkelasPertemuanDetail(
                    "" + id_kelas_pertemuan,
                    "" + id_murid);
            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    public void backPressed() {
        onBackPressed();
    }

    @Override
    public void onSetupListViewMurid(ArrayList<Murid> dataModelArrayList) {
        AdapterDataMuridList adapterDataMuridList = new AdapterDataMuridList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setAdapter(adapterDataMuridList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    if (statusActivity.equals("listPengajar->view->editKelasPertemuan")) {
                        fab.show();
                    }
                }
            }
        });

        adapterDataMuridList.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(getApplicationContext(), DataMuridEditActivity.class);
            intent.putExtra(DataMuridEditActivity.EXTRA_ID_MURID, dataModelArrayList.get(position).getId_murid());
            intent.putExtra(DataMuridEditActivity.EXTRA_NAMA, dataModelArrayList.get(position).getNama());
            intent.putExtra(DataMuridEditActivity.EXTRA_ID_WALI_MURID, dataModelArrayList.get(position).getId_wali_murid());
            intent.putExtra(DataMuridEditActivity.EXTRA_NAMA_WALI_MURID, dataModelArrayList.get(position).getNama_wali_murid());
            intent.putExtra(DataMuridEditActivity.EXTRA_ALAMAT, dataModelArrayList.get(position).getAlamat());
            intent.putExtra(DataMuridEditActivity.EXTRA_FOTO, dataModelArrayList.get(position).getFoto());
            startActivity(intent);
        });
    }

    @Override
    public void showDialogDeleteMurid(String id_kelas_pertemuan_detail, String nama) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle(globalMessage.getValidasiHapusData() + nama + " ?");
        alertDialogBuilder
                .setMessage(globalMessage.getPilihYaHapusData())
                .setPositiveButton(globalMessage.getYa(), (dialog, id) -> {

                    try {
                        dataKelasPertemuanEditPresenter.onDeleteMurid(
                                "" + id_kelas_pertemuan_detail,
                                "" + id_kelas_pertemuan);
                    } catch (Exception e) {
                        globalProcess.onErrorMessage(globalMessage.getErrorHapusData() + e.toString());
                    }

                })
                .setNegativeButton(globalMessage.getTidak(), (dialog, id) -> dialog.cancel());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataKelasPertemuanEditPresenter.onLoadDataListMurid("" + id_kelas_pertemuan);
    }
}