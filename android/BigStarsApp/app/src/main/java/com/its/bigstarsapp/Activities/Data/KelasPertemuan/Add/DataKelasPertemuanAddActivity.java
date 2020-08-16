package com.its.bigstarsapp.Activities.Data.KelasPertemuan.Add;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.its.bigstarsapp.Activities.Data.KelasPertemuan.Add.presenter.IDataKelasPertemuanAddPresenter;
import com.its.bigstarsapp.Adapters.AdapterDataMataPelajaranList;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

public class DataKelasPertemuanAddActivity extends AppCompatActivity {

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
    }
}