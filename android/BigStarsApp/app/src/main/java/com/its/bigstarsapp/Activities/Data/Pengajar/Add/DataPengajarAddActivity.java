package com.its.bigstarsapp.Activities.Data.Pengajar.Add;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Pengajar.Add.view.IDataPengajarAddView;
import com.its.bigstarsapp.R;

public class DataPengajarAddActivity extends AppCompatActivity implements View.OnClickListener, IDataPengajarAddView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengajar_add);
    }

    @Override
    public void onClick(View view) {
        
    }
}