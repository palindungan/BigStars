package com.its.bigstarsapp.Activities.Data.Pengajar.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Pengajar.Edit.view.IDataPengajarEditView;
import com.its.bigstarsapp.R;

public class DataPengajarEditActivity extends AppCompatActivity implements View.OnClickListener, IDataPengajarEditView {

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_USERNAME = "EXTRA_USERNAME";
    public static final String EXTRA_ALAMAT = "EXTRA_ALAMAT";
    public static final String EXTRA_NO_HP = "EXTRA_NO_HP";
    public static final String EXTRA_FOTO = "EXTRA_FOTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengajar_edit);
    }

    @Override
    public void onClick(View view) {

    }
}