package com.its.bigstarsapp.Activities.Data.MataPelajaran.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.MataPelajaran.Edit.view.IDataMataPelajaranEditView;
import com.its.bigstarsapp.R;

public class DataMataPelajaranEditActivity extends AppCompatActivity implements View.OnClickListener, IDataMataPelajaranEditView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mata_pelajaran_edit);
    }

    @Override
    public void onClick(View view) {
        
    }
}