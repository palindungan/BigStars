package com.its.bigstarsapp.Activities.Data.Pertemuan.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Pertemuan.Edit.view.IDataPertemuanEditView;
import com.its.bigstarsapp.R;

public class DataPertemuanEditActivity extends AppCompatActivity implements View.OnClickListener, IDataPertemuanEditView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pertemuan_edit);
    }

    @Override
    public void onClick(View view) {

    }
}