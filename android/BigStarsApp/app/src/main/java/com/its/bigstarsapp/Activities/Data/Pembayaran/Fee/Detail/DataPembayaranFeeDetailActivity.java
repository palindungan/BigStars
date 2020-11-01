package com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail.presenter.DataPembayaranFeeDetailPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail.presenter.IDataPembayaranFeeDetailPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail.view.IDataPembayaranFeeDetailView;
import com.its.bigstarsapp.Adapters.AdapterDataPertemuanList;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.Pertemuan;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

public class DataPembayaranFeeDetailActivity extends AppCompatActivity implements View.OnClickListener, IDataPembayaranFeeDetailView {

    public static final String EXTRA_ID_BAYAR_FEE = "EXTRA_ID_BAYAR_FEE";
    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";

    IDataPembayaranFeeDetailPresenter dataPembayaranFeeDetailPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    EditText edtNamaPengajar, edtTotalPertemuan, edtTotalFee;
    Button btnBayar;

    String id_pengajar, id_bayar_fee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pembayaran_fee_detail);

        dataPembayaranFeeDetailPresenter = new DataPembayaranFeeDetailPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        edtNamaPengajar = findViewById(R.id.edt_nama_pengajar);
        edtTotalPertemuan = findViewById(R.id.edt_total_pertemuan);
        edtTotalFee = findViewById(R.id.edt_total_fee);

        btnBayar = findViewById(R.id.btn_bayar);

        globalProcess.initActionBar(toolbar);

        id_bayar_fee = getIntent().getStringExtra(EXTRA_ID_BAYAR_FEE);
        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);

        onLoadData();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Your code to make your refresh action
            onLoadData();

            // CallYourRefreshingMethod();
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 1000);
        });

        btnBayar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSetData() {

    }

    @Override
    public void onSetupListView(ArrayList<Pertemuan> dataModelArrayList) {
        AdapterDataPertemuanList adapterDataPertemuanList = new AdapterDataPertemuanList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDataPertemuanList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDataPertemuanList.notifyDataSetChanged();

        adapterDataPertemuanList.setOnItemClickListener((view, position) -> {

        });
    }

    @Override
    public void backPressed() {

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
        onLoadData();
    }

    private void onLoadData() {
        dataPembayaranFeeDetailPresenter.onLoadDataDetail(
                "" + id_bayar_fee,
                "" + id_pengajar);

        dataPembayaranFeeDetailPresenter.onLoadDataListPertemuan(
                "" + id_bayar_fee,
                "" + id_pengajar);
    }
}