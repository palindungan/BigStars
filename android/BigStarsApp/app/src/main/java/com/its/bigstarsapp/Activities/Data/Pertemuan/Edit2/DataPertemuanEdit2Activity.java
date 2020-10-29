package com.its.bigstarsapp.Activities.Data.Pertemuan.Edit2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.its.bigstarsapp.Activities.Data.Pertemuan.Edit2.view.IDataPertemuanEdit2View;
import com.its.bigstarsapp.R;

public class DataPertemuanEdit2Activity extends AppCompatActivity implements View.OnClickListener, IDataPertemuanEdit2View, OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pertemuan_edit2);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}