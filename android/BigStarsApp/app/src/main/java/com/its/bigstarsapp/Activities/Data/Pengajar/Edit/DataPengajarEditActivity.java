package com.its.bigstarsapp.Activities.Data.Pengajar.Edit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.its.bigstarsapp.Activities.Data.Pengajar.Edit.presenter.IDataPengajarEditPresenter;
import com.its.bigstarsapp.Activities.Data.Pengajar.Edit.view.IDataPengajarEditView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

public class DataPengajarEditActivity extends AppCompatActivity implements View.OnClickListener, IDataPengajarEditView {

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_USERNAME = "EXTRA_USERNAME";
    public static final String EXTRA_ALAMAT = "EXTRA_ALAMAT";
    public static final String EXTRA_NO_HP = "EXTRA_NO_HP";
    public static final String EXTRA_FOTO = "EXTRA_FOTO";
    String id_pengajar, nama, username, alamat, no_hp, foto;

    IDataPengajarEditPresenter dataPengajarEditPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    EditText edtNama, edtUsername, edtPassword, edtKonfirmasiPassword, edtAlamat, edtNoHp;
    ImageView ivFoto;
    Button btnUpdate;

    private Bitmap bitmap;
    String data_photo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengajar_edit);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void backPressed() {

    }
}