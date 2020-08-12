package com.its.bigstarsapp.Activities.Data.Murid.List;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Murid.List.view.IDataMuridListView;
import com.its.bigstarsapp.Models.Murid;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

public class DataMuridListActivity extends AppCompatActivity implements View.OnClickListener, IDataMuridListView {

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