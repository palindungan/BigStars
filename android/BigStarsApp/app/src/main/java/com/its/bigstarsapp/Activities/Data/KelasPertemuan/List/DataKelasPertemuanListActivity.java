package com.its.bigstarsapp.Activities.Data.KelasPertemuan.List;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.its.bigstarsapp.R;

public class DataKelasPertemuanListActivity extends AppCompatActivity {

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kelas_pertemuan_list);
    }
}