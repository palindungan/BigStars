package com.its.bigstarsapp.Activities.Data.Murid.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.its.bigstarsapp.Activities.Data.Murid.List.presenter.IDataMuridListPresenter;
import com.its.bigstarsapp.Activities.Data.Murid.List.view.IDataMuridListView;
import com.its.bigstarsapp.Adapters.AdapterDataMuridList;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.Murid;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

public class DataMuridListActivity extends AppCompatActivity implements View.OnClickListener, IDataMuridListView {

    IDataMuridListPresenter dataMuridListPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    private AdapterDataMuridList adapterDataMuridList;

    Toolbar toolbar;
    FloatingActionButton fab;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    String statusActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_murid_list);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSetupListView(ArrayList<Murid> dataModelArrayList) {

    }

    @Override
    public void showDialogDelete(String kode, String nama) {

    }
}