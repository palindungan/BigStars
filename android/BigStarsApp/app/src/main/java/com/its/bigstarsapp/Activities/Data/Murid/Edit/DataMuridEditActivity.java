package com.its.bigstarsapp.Activities.Data.Murid.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Murid.Edit.view.IDataMuridEditView;
import com.its.bigstarsapp.R;

public class DataMuridEditActivity extends AppCompatActivity implements View.OnClickListener, IDataMuridEditView {

    public static final String EXTRA_ID_MURID = "EXTRA_ID_MURID";
    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_ID_WALI_MURID = "EXTRA_ID_WALI_MURID";
    public static final String EXTRA_NAMA_WALI_MURID = "EXTRA_NAMA_WALI_MURID";
    public static final String EXTRA_ALAMAT = "EXTRA_ALAMAT";
    public static final String EXTRA_FOTO = "EXTRA_FOTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_murid_edit);
    }

    @Override
    public void onClick(View view) {
        
    }
}