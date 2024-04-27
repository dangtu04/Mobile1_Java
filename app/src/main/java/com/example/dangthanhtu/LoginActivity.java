package com.example.dangthanhtu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dangthanhtu.api.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    private EditText edtUserName;
    private EditText edtPassword;
    private Button btnLogin;
    private List<User> mListUser;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edtUserName = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);

        getListUsers();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLogin();
            }
        });

        //đi đến trang đăng ký
        TextView tvRegister = findViewById(R.id.tv_register);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });



    }

    private void clickLogin() {

        String strUsername = edtUserName.getText().toString().trim();
        String strPassword = edtPassword.getText().toString().trim();

        if(mListUser == null || mListUser.isEmpty()) {
            return;
        }

        boolean isHasUser = false;
        for(User user : mListUser) {
            if(strUsername.equals(user.getUsername()) && strPassword.equals(user.getPassword())) {
                isHasUser = true;
                mUser = user;
                break;
            }
        }

        if(isHasUser) {
            //HomeActivity
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("object_user", mUser);
            intent.putExtras(bundle);
            startActivity(intent);

        } else {
            Toast.makeText(LoginActivity.this,"Tên đăng nhập hoặc mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }


    private void getListUsers() {
        ApiService.apiService.getListUsers("desc").enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mListUser = response.body();
                Log.e("List User", mListUser.size() + "");
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Không gọi được API", Toast.LENGTH_SHORT).show();
            }
        });
    }


}