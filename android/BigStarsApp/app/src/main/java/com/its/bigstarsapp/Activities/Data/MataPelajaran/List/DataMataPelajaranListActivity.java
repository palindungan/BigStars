package com.its.bigstarsapp.Activities.Data.MataPelajaran.List;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.MataPelajaran.List.view.IDataMataPelajaranListView;
import com.its.bigstarsapp.R;

public class DataMataPelajaranListActivity extends AppCompatActivity implements View.OnClickListener, IDataMataPelajaranListView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mata_pelajaran_list);
    }

    @Override
    public void onClick(View view) {
        
    }
}