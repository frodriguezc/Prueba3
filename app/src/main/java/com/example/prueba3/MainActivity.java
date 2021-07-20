package com.example.prueba3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG_MAIN";
    EditText etUser, etPass;
    Button btnLogin, btnClear, btnRegister, btnRecover;
    String userName, userPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        widgetInit();


    }

    private void widgetInit() {
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnClear = findViewById(R.id.btnClear);
        btnRegister = findViewById(R.id.btnRegister);
        btnRecover = findViewById(R.id.btnRecover);
    }



}