package com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail.DataPembayaranSppDetailActivity;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.List.presenter.DataPembayaranSppListPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.List.presenter.IDataPembayaranSppListPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.List.view.IDataPembayaranSppListView;
import com.its.bigstarsapp.Adapters.AdapterDataPembayaranSppList;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.BayarSpp;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

public class DataPembayaranSppListActivity extends AppCompatActivity implements View.OnClickListener, IDataPembayaranSppListView {

    public static final String EXTRA_ID_BAYAR_SPP = "EXTRA_ID_BAYAR_SPP";
    public static final String EXTRA_ID_WALI_MURID = "EXTRA_ID_WALI_MURID";

    IDataPembayaranSppListPresenter dataPembayaranSppListPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    String id_wali_murid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pembayaran_spp_list);

        dataPembayaranSppListPresenter = new DataPembayaranSppListPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        globalProcess.initActionBar(toolbar);

        id_wali_murid = getIntent().getStringExtra(EXTRA_ID_WALI_MURID);

        dataPembayaranSppListPresenter.onLoadDataList("" + id_wali_murid);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Your code to make your refresh action
            dataPembayaranSppListPresenter.onLoadDataList("" + id_wali_murid);

            // CallYourRefreshingMethod();
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 1000);
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSetupListView(ArrayList<BayarSpp> dataModelArrayList) {
        AdapterDataPembayaranSppList adapterDataPembayaranSppList = new AdapterDataPembayaranSppList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDataPembayaranSppList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDataPembayaranSppList.notifyDataSetChanged();

        adapterDataPembayaranSppList.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(getApplicationContext(), DataPembayaranSppDetailActivity.class);
            intent.putExtra(DataPembayaranSppDetailActivity.EXTRA_ID_BAYAR_SPP, dataModelArrayList.get(position).getId_bayar_spp());
            intent.putExtra(DataPembayaranSppDetailActivity.EXTRA_ID_WALI_MURID, dataModelArrayList.get(position).getId_wali_murid());
            startActivity(intent);
        });
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
        dataPembayaranSppListPresenter.onLoadDataList("" + id_wali_murid);
    }
}