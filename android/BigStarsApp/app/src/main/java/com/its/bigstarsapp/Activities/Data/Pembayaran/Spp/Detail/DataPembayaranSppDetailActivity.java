package com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail.presenter.IDataPembayaranSppDetailPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail.view.IDataPembayaranSppDetailView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.Pertemuan;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

public class DataPembayaranSppDetailActivity extends AppCompatActivity implements View.OnClickListener, IDataPembayaranSppDetailView {

    public static final String EXTRA_ID_BAYAR_SPP = "EXTRA_ID_BAYAR_SPP";
    public static final String EXTRA_ID_WALI_MURID = "EXTRA_ID_WALI_MURID";

    IDataPembayaranSppDetailPresenter dataPembayaranSppDetailPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    EditText edtNamaWaliMurid, edtTotalPertemuan, edtTotalSpp;
    Button btnBayar;

    String id_wali_murid, id_bayar_spp, id_user;
    String hak_akses;
    String statusActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pembayaran_spp_detail);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSetData(String setNamaWaliMurid, String totalPertemuan, String totalSpp) {

    }

    @Override
    public void onSetupListView(ArrayList<Pertemuan> dataModelArrayList) {

    }

    @Override
    public void backPressed() {

    }
}