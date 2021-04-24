package com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Cetak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Cetak.presenter.IDataPembayaranSppCetakPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Cetak.view.IDataPembayaranSppCetakView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

public class DataPembayaranSppCetakActivity extends AppCompatActivity implements View.OnClickListener, IDataPembayaranSppCetakView {

    public static final String EXTRA_NAMA_WALI_MURID = "EXTRA_NAMA_WALI_MURID";
    public static final String EXTRA_TOTAL_PERTEMUAN = "EXTRA_TOTAL_PERTEMUAN";
    public static final String EXTRA_TOTAL_SPP = "EXTRA_TOTAL_SPP";
    String nama_wali_murid, total_pertemuan, total_spp;

    IDataPembayaranSppCetakPresenter dataPembayaranSppCetakPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pembayaran_spp_cetak);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSetData(String setNamaWaliMurid, String totalPertemuan, String totalSpp) {

    }

    @Override
    public void backPressed() {
        onBackPressed();
    }
}