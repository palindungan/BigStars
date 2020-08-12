package com.its.bigstarsapp.Activities.Data.WaliMurid.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.its.bigstarsapp.R;

public class DataWaliMuridEditActivity extends AppCompatActivity {

    public static final String EXTRA_ID_WALI_MURID = "EXTRA_ID_WALI_MURID";
    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_USERNAME = "EXTRA_USERNAME";
    public static final String EXTRA_ALAMAT = "EXTRA_ALAMAT";
    public static final String EXTRA_NO_HP = "EXTRA_NO_HP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_wali_murid_edit);
    }
}