package com.its.bigstarsapp.Activities.Data.Pengajar.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Pengajar.Edit.view.IDataPengajarEditView;
import com.its.bigstarsapp.R;

public class DataPengajarEditActivity extends AppCompatActivity implements View.OnClickListener, IDataPengajarEditView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengajar_edit);
    }

    @Override
    public void onClick(View view) {

    }
}