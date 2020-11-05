package com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.List;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.List.presenter.DataPembayaranFeeListPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.List.presenter.IDataPembayaranFeeListPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.List.view.IDataPembayaranFeeListView;
import com.its.bigstarsapp.Adapters.AdapterDataPembayaranFeeList;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.BayarFee;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

public class DataPembayaranFeeListActivity extends AppCompatActivity implements View.OnClickListener, IDataPembayaranFeeListView {

    public static final String EXTRA_ID_BAYAR_FEE = "EXTRA_ID_BAYAR_FEE";
    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";

    IDataPembayaranFeeListPresenter dataPembayaranFeeListPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    String id_pengajar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pembayaran_fee_list);

        dataPembayaranFeeListPresenter = new DataPembayaranFeeListPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        globalProcess.initActionBar(toolbar);

        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);

        dataPembayaranFeeListPresenter.onLoadDataList("" + id_pengajar);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Your code to make your refresh action
            dataPembayaranFeeListPresenter.onLoadDataList("" + id_pengajar);

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
    public void onSetupListView(ArrayList<BayarFee> dataModelArrayList) {
        AdapterDataPembayaranFeeList adapterDataPembayaranFeeList = new AdapterDataPembayaranFeeList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDataPembayaranFeeList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDataPembayaranFeeList.notifyDataSetChanged();

        adapterDataPembayaranFeeList.setOnItemClickListener((view, position) -> {

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
        dataPembayaranFeeListPresenter.onLoadDataList("" + id_pengajar);
    }
}