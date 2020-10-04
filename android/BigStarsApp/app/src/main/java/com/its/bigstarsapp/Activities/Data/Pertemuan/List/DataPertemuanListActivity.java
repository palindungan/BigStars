package com.its.bigstarsapp.Activities.Data.Pertemuan.List;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Pertemuan.List.view.IDataPertemuanListView;
import com.its.bigstarsapp.Models.Pertemuan;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

public class DataPertemuanListActivity extends AppCompatActivity implements View.OnClickListener, IDataPertemuanListView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pertemuan_list);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSetupListView(ArrayList<Pertemuan> dataModelArrayList) {

    }
}