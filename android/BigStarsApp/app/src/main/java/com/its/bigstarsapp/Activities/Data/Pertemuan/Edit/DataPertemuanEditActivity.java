package com.its.bigstarsapp.Activities.Data.Pertemuan.Edit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.Edit.presenter.DataKelasPertemuanEditPresenter;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.Edit.presenter.IDataKelasPertemuanEditPresenter;
import com.its.bigstarsapp.Activities.Data.Pertemuan.Edit.presenter.DataPertemuanEditPresenter;
import com.its.bigstarsapp.Activities.Data.Pertemuan.Edit.presenter.IDataPertemuanEditPresenter;
import com.its.bigstarsapp.Activities.Data.Pertemuan.Edit.view.IDataPertemuanEditView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

import java.util.HashMap;

public class DataPertemuanEditActivity extends AppCompatActivity implements View.OnClickListener, IDataPertemuanEditView {

    public static final String EXTRA_ID_PERTEMUAN = "EXTRA_ID_PERTEMUAN";
    public static final String EXTRA_HARI_PERTEMUAN = "EXTRA_HARI_PERTEMUAN";
    public static final String EXTRA_WAKTU_MULAI = "EXTRA_WAKTU_MULAI";
    public static final String EXTRA_WAKTU_BERAKHIR = "EXTRA_WAKTU_BERAKHIR";
    public static final String EXTRA_LOKASI_MULAI_LA = "EXTRA_LOKASI_MULAI_LA";
    public static final String EXTRA_LOKASI_MULAI_LO = "EXTRA_LOKASI_MULAI_LO";
    public static final String EXTRA_LOKASI_BERAKHIR_LA = "EXTRA_LOKASI_BERAKHIR_LA";
    public static final String EXTRA_LOKASI_BERAKHIR_LO = "EXTRA_LOKASI_BERAKHIR_LO";
    public static final String EXTRA_DESKRIPSI = "EXTRA_DESKRIPSI";
    public static final String EXTRA_HARGA_FEE = "EXTRA_HARGA_FEE";
    public static final String EXTRA_HARGA_SPP = "EXTRA_HARGA_SPP";
    public static final String EXTRA_STATUS_FEE = "EXTRA_STATUS_FEE";
    public static final String EXTRA_STATUS_SPP = "EXTRA_STATUS_SPP";
    public static final String EXTRA_STATUS_KONFIRMASI = "EXTRA_STATUS_KONFIRMASI";
    public static final String EXTRA_STATUS_PERTEMUAN = "EXTRA_STATUS_PERTEMUAN";

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    public static final String EXTRA_NAMA_PENGAJAR = "EXTRA_NAMA_PENGAJAR";

    public static final String EXTRA_ID_KELAS_PERTEMUAN = "EXTRA_ID_KELAS_PERTEMUAN";
    public static final String EXTRA_HARI_KELAS_PERTEMUAN = "EXTRA_HARI_KELAS_PERTEMUAN";
    public static final String EXTRA_JAM_MULAI = "EXTRA_JAM_MULAI";
    public static final String EXTRA_JAM_BERAKHIR = "EXTRA_JAM_BERAKHIR";

    public static final String EXTRA_ID_MATA_PELAJARAN = "EXTRA_ID_MATA_PELAJARAN";
    public static final String EXTRA_NAMA_MATA_PELAJARAN = "EXTRA_NAMA_MATA_PELAJARAN";

    String id_pertemuan, hari_pertemuan, waktu_mulai, waktu_berakhir, lokasi_mulai_la,
            lokasi_mulai_lo, lokasi_berakhir_la, lokasi_berakhir_lo, deskripsi, harga_fee,
            harga_spp, status_fee, status_spp, status_konfirmasi, status_pertemuan;
    String id_pengajar, nama_pengajar;
    String id_kelas_pertemuan, hari_kelas_pertemuan, jam_mulai, jam_berakhir;
    String id_mata_pelajaran, nama_mata_pelajaran;

    IDataPertemuanEditPresenter dataPertemuanEditPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    EditText edtNamaMataPelajaran, edtWaktuMulai, edtWaktuBerakhir, edtHargaFee, edtHargaSpp;
    TextView tvStatusPertemuan, tvStatusKonfirmasi;
    Button btnBatal, btnValid, btnInvalid, btnGetLokasi, btnNext;
    Fragment mapFragment;

    String statusActivity;

    public static Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pertemuan_edit);

        id_pertemuan = getIntent().getStringExtra(EXTRA_ID_PERTEMUAN);
        hari_pertemuan = getIntent().getStringExtra(EXTRA_HARI_PERTEMUAN);
        waktu_mulai = getIntent().getStringExtra(EXTRA_WAKTU_MULAI);
        waktu_berakhir = getIntent().getStringExtra(EXTRA_WAKTU_BERAKHIR);
        lokasi_mulai_la = getIntent().getStringExtra(EXTRA_LOKASI_MULAI_LA);
        lokasi_mulai_lo = getIntent().getStringExtra(EXTRA_LOKASI_MULAI_LO);
        lokasi_berakhir_la = getIntent().getStringExtra(EXTRA_LOKASI_BERAKHIR_LA);
        lokasi_berakhir_lo = getIntent().getStringExtra(EXTRA_LOKASI_BERAKHIR_LO);
        deskripsi = getIntent().getStringExtra(EXTRA_DESKRIPSI);
        harga_fee = getIntent().getStringExtra(EXTRA_HARGA_FEE);
        harga_spp = getIntent().getStringExtra(EXTRA_HARGA_SPP);
        status_fee = getIntent().getStringExtra(EXTRA_STATUS_FEE);
        status_spp = getIntent().getStringExtra(EXTRA_STATUS_SPP);
        status_konfirmasi = getIntent().getStringExtra(EXTRA_STATUS_KONFIRMASI);
        status_pertemuan = getIntent().getStringExtra(EXTRA_STATUS_PERTEMUAN);

        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);
        nama_pengajar = getIntent().getStringExtra(EXTRA_NAMA_PENGAJAR);

        id_kelas_pertemuan = getIntent().getStringExtra(EXTRA_ID_KELAS_PERTEMUAN);
        hari_kelas_pertemuan = getIntent().getStringExtra(EXTRA_HARI_KELAS_PERTEMUAN);
        jam_mulai = getIntent().getStringExtra(EXTRA_JAM_MULAI);
        jam_berakhir = getIntent().getStringExtra(EXTRA_JAM_BERAKHIR);

        id_mata_pelajaran = getIntent().getStringExtra(EXTRA_ID_MATA_PELAJARAN);
        nama_mata_pelajaran = getIntent().getStringExtra(EXTRA_NAMA_MATA_PELAJARAN);

        dataPertemuanEditPresenter = new DataPertemuanEditPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        edtNamaMataPelajaran = findViewById(R.id.edt_nama_mata_pelajaran);
        edtWaktuMulai = findViewById(R.id.edt_waktu_mulai);
        edtWaktuBerakhir = findViewById(R.id.edt_waktu_berakhir);
        edtHargaFee = findViewById(R.id.edt_harga_fee);
        edtHargaSpp = findViewById(R.id.edt_harga_spp);
        tvStatusPertemuan = findViewById(R.id.tv_status_pertemuan);
        tvStatusKonfirmasi = findViewById(R.id.tv_status_konfirmasi);
        btnBatal = findViewById(R.id.btn_batal);
        btnValid = findViewById(R.id.btn_valid);
        btnInvalid = findViewById(R.id.btn_invalid);
        btnGetLokasi = findViewById(R.id.btn_get_lokasi);
        btnNext = findViewById(R.id.btn_next);

        globalProcess.initActionBar(toolbar);

        inisiasiAwal();
    }

    private void inisiasiAwal() {
        String setNamaMataPelajaran = nama_mata_pelajaran;
        String setWaktuMulai = waktu_mulai;
        String setWaktuBerakhir = waktu_berakhir;
        String setHargaFee = harga_fee;
        String setHargaSpp = harga_spp;
        String setStatusPertemuan = "Pertemuan : " + status_pertemuan;
        String setStatusKonfirmasi = "Konfirmasi : " + status_konfirmasi;

        edtNamaMataPelajaran.setText(setNamaMataPelajaran);
        edtWaktuMulai.setText(setWaktuMulai);
        edtWaktuBerakhir.setText(setWaktuBerakhir);
        edtHargaFee.setText(setHargaFee);
        edtHargaSpp.setText(setHargaSpp);
        tvStatusPertemuan.setText(setStatusPertemuan);
        tvStatusKonfirmasi.setText(setStatusKonfirmasi);

        if (waktu_mulai.equals(waktu_berakhir)) {
            edtWaktuBerakhir.setVisibility(View.GONE);
        }

        HashMap<String, String> user = sessionManager.getDataUser();
        String hak_akses = user.get(sessionManager.HAK_AKSES);
        if (hak_akses.equals("admin")) {
            btnBatal.setVisibility(View.VISIBLE);
            btnValid.setVisibility(View.VISIBLE);
            btnInvalid.setVisibility(View.VISIBLE);
        } else if (hak_akses.equals("pengajar")) {
            btnBatal.setVisibility(View.VISIBLE);
            btnGetLokasi.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {

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