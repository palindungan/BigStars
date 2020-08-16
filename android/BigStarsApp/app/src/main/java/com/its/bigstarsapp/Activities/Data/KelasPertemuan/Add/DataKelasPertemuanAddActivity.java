package com.its.bigstarsapp.Activities.Data.KelasPertemuan.Add;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
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
import com.its.bigstarsapp.R;

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
        edtNamaPelajaran = findViewById(R.id.edt_nama_pelajaran);
        edtHari = findViewById(R.id.edt_hari);
        edtHargaFee = findViewById(R.id.edt_harga_fee);
        edtHargaSpp = findViewById(R.id.edt_harga_spp);
        btnPilih = findViewById(R.id.btn_pilih);
        btnJamMulai = findViewById(R.id.btn_jam_mulai);
        btnJamBerakhir = findViewById(R.id.btn_jam_berakhir);
        btnSubmit = findViewById(R.id.btn_submit);

        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);

        globalProcess.initActionBar(toolbar);

        btnPilih.setOnClickListener(this);
        btnJamMulai.setOnClickListener(this);
        btnJamBerakhir.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}