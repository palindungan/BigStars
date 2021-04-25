package com.its.bigstarsapp.Activities.Data.Pembayaran.Preview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Preview.presenter.DataPembayaranPreviewPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Preview.presenter.IDataPembayaranPreviewPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Preview.view.IDataPembayaranPreviewView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

import java.util.Calendar;
import java.util.HashMap;

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
    TextView tvNama, tvTotalPertemuan, tvTotalBayar, tvNoRek;
    EditText edtBulan;

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
        tvNama = findViewById(R.id.tv_nama);
        tvTotalPertemuan = findViewById(R.id.tv_total_pertemuan);
        tvTotalBayar = findViewById(R.id.tv_total_bayar);
        tvNoRek = findViewById(R.id.tv_no_rek);
        edtBulan = findViewById(R.id.edt_bulan);

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
        HashMap<String, String> user = sessionManager.getDataUser();
        Calendar c = Calendar.getInstance();
        String[] monthName = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        int perhitunganPerPertemuan = Integer.parseInt(total_bayar) / Integer.parseInt(total_pertemuan);
        String perPertemuan = String.valueOf(perhitunganPerPertemuan);

        String setTotalPertemuan = total_pertemuan + " (" + perPertemuan + ")";
        String setTotalBayar = "Rp " + total_bayar;
        String setNoRek = user.get(sessionManager.NO_REK);
        String setBulan = monthName[c.get(Calendar.MONTH)];

        tvNama.setText(nama);
        tvTotalPertemuan.setText(total_pertemuan);
        tvTotalBayar.setText(setTotalBayar);
        tvNoRek.setText(setNoRek);
        edtBulan.setText(setBulan);
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
        return true;
    }
}