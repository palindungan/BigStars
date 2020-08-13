package com.its.bigstarsapp.Activities.Data.Murid.Add;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Murid.Add.view.IDataMuridAddView;
import com.its.bigstarsapp.Models.WaliMurid;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

public class DataMuridAddActivity extends AppCompatActivity implements View.OnClickListener, IDataMuridAddView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_murid_add);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void backPressed() {

    }

    @Override
    public void onSetupListView(ArrayList<WaliMurid> dataModelArrayList) {

    }
}