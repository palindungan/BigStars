package com.its.bigstarsapp.Activities.Data.Pembayaran.Preview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Preview.presenter.DataPembayaranPreviewPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Preview.presenter.IDataPembayaranPreviewPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Preview.view.IDataPembayaranPreviewView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

public class DataPembayaranPreviewActivity extends AppCompatActivity implements View.OnClickListener, IDataPembayaranPreviewView {

    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_TOTAL_PERTEMUAN = "EXTRA_TOTAL_PERTEMUAN";
    public static final String EXTRA_TOTAL_BAYAR = "EXTRA_TOTAL_BAYAR";
    String nama, total_pertemuan, total_bayar;

    IDataPembayaranPreviewPresenter dataPembayaranPreviewPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    // EditText edtBulan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pembayaran_preview);

        dataPembayaranPreviewPresenter = new DataPembayaranPreviewPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        // edtBulan = findViewById(R.id.edt_bulan);

        nama = getIntent().getStringExtra(EXTRA_NAMA);
        total_pertemuan = getIntent().getStringExtra(EXTRA_TOTAL_PERTEMUAN);
        total_bayar = getIntent().getStringExtra(EXTRA_TOTAL_BAYAR);

        globalProcess.initActionBar(toolbar);

        inisiasiAwal(
                "" + nama,
                "" + total_pertemuan,
                "" + total_bayar);
    }

    private void inisiasiAwal(String nama, String total_pertemuan, String total_bayar) {
        globalProcess.onSuccessMessage(nama + total_pertemuan + total_bayar);
    }

    @Override
    public void onClick(View view) {

    }
}