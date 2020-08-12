package com.its.bigstarsapp.Activities.Data.WaliMurid.Add;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.WaliMurid.Add.view.IDataWaliMuridAddView;
import com.its.bigstarsapp.R;

public class DataWaliMuridAddActivity extends AppCompatActivity implements View.OnClickListener, IDataWaliMuridAddView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_wali_murid_add);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void backPressed() {

    }
}