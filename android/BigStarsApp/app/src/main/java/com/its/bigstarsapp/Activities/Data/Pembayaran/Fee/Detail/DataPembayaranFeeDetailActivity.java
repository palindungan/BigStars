package com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail.presenter.DataPembayaranFeeDetailPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail.presenter.IDataPembayaranFeeDetailPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail.view.IDataPembayaranFeeDetailView;
import com.its.bigstarsapp.Activities.Data.Pertemuan.Edit.DataPertemuanEditActivity;
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
            String id_pertemuan = dataModelArrayList.get(position).getId_pertemuan();
            String hari_pertemuan = dataModelArrayList.get(position).getHari_pertemuan();
            String waktu_mulai = dataModelArrayList.get(position).getWaktu_mulai();
            String waktu_berakhir = dataModelArrayList.get(position).getWaktu_berakhir();
            String lokasi_mulai_la = dataModelArrayList.get(position).getLokasi_mulai_la();
            String lokasi_mulai_lo = dataModelArrayList.get(position).getLokasi_mulai_lo();
            String lokasi_berakhir_la = dataModelArrayList.get(position).getLokasi_berakhir_la();
            String lokasi_berakhir_lo = dataModelArrayList.get(position).getLokasi_berakhir_lo();
            String deskripsi = dataModelArrayList.get(position).getDeskripsi();
            String harga_fee = dataModelArrayList.get(position).getHarga_fee();
            String harga_spp = dataModelArrayList.get(position).getHarga_spp();
            String status_fee = dataModelArrayList.get(position).getStatus_fee();
            String status_spp = dataModelArrayList.get(position).getStatus_spp();
            String status_konfirmasi = dataModelArrayList.get(position).getStatus_konfirmasi();
            String status_pertemuan = dataModelArrayList.get(position).getStatus_pertemuan();
            String id_pengajar = dataModelArrayList.get(position).getId_pengajar();
            String nama_pengajar = dataModelArrayList.get(position).getNama_pengajar();
            String id_kelas_pertemuan = dataModelArrayList.get(position).getId_kelas_pertemuan();
            String hari_kelas_pertemuan = dataModelArrayList.get(position).getHari_kelas_pertemuan();
            String jam_mulai = dataModelArrayList.get(position).getJam_mulai();
            String jam_berakhir = dataModelArrayList.get(position).getJam_berakhir();
            String id_mata_pelajaran = dataModelArrayList.get(position).getId_mata_pelajaran();
            String nama_mata_pelajaran = dataModelArrayList.get(position).getNama_mata_pelajaran();

            Intent intent = new Intent(getApplicationContext(), DataPertemuanEditActivity.class);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_ID_PERTEMUAN, id_pertemuan);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_HARI_PERTEMUAN, hari_pertemuan);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_WAKTU_MULAI, waktu_mulai);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_WAKTU_BERAKHIR, waktu_berakhir);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_LOKASI_MULAI_LA, lokasi_mulai_la);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_LOKASI_MULAI_LO, lokasi_mulai_lo);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_LOKASI_BERAKHIR_LA, lokasi_berakhir_la);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_LOKASI_BERAKHIR_LO, lokasi_berakhir_lo);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_DESKRIPSI, deskripsi);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_HARGA_FEE, harga_fee);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_HARGA_SPP, harga_spp);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_STATUS_FEE, status_fee);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_STATUS_SPP, status_spp);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_STATUS_KONFIRMASI, status_konfirmasi);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_STATUS_PERTEMUAN, status_pertemuan);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_ID_PENGAJAR, id_pengajar);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_NAMA_PENGAJAR, nama_pengajar);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_ID_KELAS_PERTEMUAN, id_kelas_pertemuan);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_HARI_KELAS_PERTEMUAN, hari_kelas_pertemuan);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_JAM_MULAI, jam_mulai);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_JAM_BERAKHIR, jam_berakhir);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_ID_MATA_PELAJARAN, id_mata_pelajaran);
            intent.putExtra(DataPertemuanEditActivity.EXTRA_NAMA_MATA_PELAJARAN, nama_mata_pelajaran);
            startActivity(intent);
        });
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