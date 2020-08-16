package com.its.bigstarsapp.Activities.Data.KelasPertemuan.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.List.presenter.IDataKelasPertemuanListPresenter;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.List.view.IDataKelasPertemuanListView;
import com.its.bigstarsapp.Activities.Data.Pengajar.List.presenter.IDataPengajarListPresenter;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

public class DataKelasPertemuanListActivity extends AppCompatActivity implements View.OnClickListener, IDataKelasPertemuanListView {

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    String id_pengajar;

    IDataKelasPertemuanListPresenter dataKelasPertemuanListPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    FloatingActionButton fab;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    String statusActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kelas_pertemuan_list);
    }

    @Override
    public void onClick(View view) {

    }
}