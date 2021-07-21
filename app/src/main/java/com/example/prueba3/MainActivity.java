package com.example.prueba3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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

    private void btnsEvent() {

    }


    public void btnsActions(View v) {
        if (v == btnLogin){
            userName = etUser.getText().toString();
            userPassword = etPass.getText().toString();

            try (DBAdmin adbs = new DBAdmin(this, "BDPrueba", null, 1);
                 SQLiteDatabase miBD = adbs.getWritableDatabase()) {

                if (miBD != null) {
                    Cursor c = miBD.rawQuery("select * from users where name equals " + userName + " " +
                                    "and password equals " + userPassword,
                            null);
                    if (c.moveToFirst()){
                        Log.d(TAG, "onLogin: USER ENCONTRADO");
                        User user = new User(c.getString(0), c.getString(1));

                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "onLogin: ", e);
            }
        }
        if (v == btnRegister){
            Intent i = new Intent(this, Register.class);
            startActivity(i);
        }
        if (v == btnClear){
            etUser.setText(null);
            etPass.setText(null);
        }

    }

}