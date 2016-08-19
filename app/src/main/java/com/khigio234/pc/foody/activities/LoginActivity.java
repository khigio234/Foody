package com.khigio234.pc.foody.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.khigio234.pc.foody.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 8/19/2016.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGN_UP = 0;

    @BindView(R.id.et_login_email) EditText mEmailText;
    @BindView(R.id.et_login_password) EditText mPasswordText;
    @BindView(R.id.btn_login) Button mLoginButton;
    @BindView(R.id.tv_login_link_register) TextView mRegisterLink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        mRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void login() {
        if (!validate()) {
            onLoginFailed();
            return;
        }

        mLoginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Chờ xíu...");
        progressDialog.show();

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoginSuccess();
                progressDialog.dismiss();
            }
        },3000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGN_UP) {
            if (requestCode == RESULT_OK) {
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void onLoginSuccess() {
        mLoginButton.setEnabled(true);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
        mLoginButton.setEnabled(true);
    }

    private boolean validate() {
        boolean valid = true;

        String email = mEmailText.getText().toString();
        String password = mPasswordText.getText().toString();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailText.setError("Nhập địa chỉ email sai định dạng");
            valid = false;
        } else {
            mEmailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 20) {
            mPasswordText.setError("Nhập mật khẩu lớn hơn 3 và bé hơn 20 ký tự");
            valid = false;
        } else {
            mPasswordText.setError(null);
        }
        return valid;
    }
}
