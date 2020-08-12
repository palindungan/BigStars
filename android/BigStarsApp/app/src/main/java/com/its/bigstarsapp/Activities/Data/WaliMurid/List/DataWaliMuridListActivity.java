package com.its.bigstarsapp.Activities.Data.WaliMurid.List;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.WaliMurid.List.view.IDataWaliMuridListView;
import com.its.bigstarsapp.Models.WaliMurid;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

public class DataWaliMuridListActivity extends AppCompatActivity implements View.OnClickListener, IDataWaliMuridListView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_wali_murid_list);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSetupListView(ArrayList<WaliMurid> dataModelArrayList) {

    }

    @Override
    public void showDialogDelete(String kode, String nama) {

    }
}