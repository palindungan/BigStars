package com.its.bigstarsapp.Activities._Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.its.bigstarsapp.Activities._Login.presenter.ILoginPresenter;
import com.its.bigstarsapp.Activities._Login.presenter.LoginPresenter;
import com.its.bigstarsapp.Activities._Login.view.ILoginView;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

    public static final String EXTRA_HAK_AKSES = "EXTRA_HAK_AKSES";
    String hak_akses;

    ILoginPresenter loginPresenter;
    GlobalProcess globalProcess;

    EditText edtUsername, edtPassword;
    Button btnLogin;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter(this, this);
        globalProcess = new GlobalProcess(this);

        toolbar = findViewById(R.id.toolbar);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);

        hak_akses = getIntent().getStringExtra(EXTRA_HAK_AKSES);

        globalProcess.initActionBar(toolbar);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {

            boolean isEmpty = false;

            String inputUsername = edtUsername.getText().toString().trim();
            String inputPassword = edtPassword.getText().toString().trim();

            if (TextUtils.isEmpty(inputUsername)) {
                isEmpty = true;
                edtUsername.setError("Isi Username Dengan Lengkap");
            } else if (TextUtils.isEmpty(inputPassword)) {
                isEmpty = true;
                edtPassword.setError("Isi Password Dengan Lengkap");
            }

            if (!isEmpty) {
                if (!hak_akses.equals("wali_murid")) {
                    loginPresenter.onLogin(
                            "" + inputUsername,
                            "" + inputPassword,
                            "" + hak_akses);
//                    globalProcess.onSuccessMessage(inputUsername + inputPassword + hak_akses);
                } else {
                    globalProcess.onErrorMessage("Fitur Wali Murid Masih Belum Tersedia");
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}