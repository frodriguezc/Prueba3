package com.example.prueba3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static android.system.Os.close;

public class Register extends AppCompatActivity {
    private static final String TAG = "TAG_REGISTRATION";
    EditText etUser, etPass, etAnswer;
    ArrayList<String> questions;
    Button btnRegister, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        widgetInit();

    }

    private void widgetInit() {
        etUser = findViewById(R.id.etUserEnter);
        etPass = findViewById(R.id.etPassEnter);
        btnRegister = findViewById(R.id.btnRegisterOK);
        btnCancel = findViewById(R.id.btnCancelReg);
        questions.add("Cual es su comida favorita");
        questions.add("En que cuidad nacio");
        questions.add("Nombre de su primera mascota");
    }

    private void btnsHandler(){
        btnRegister.setOnClickListener(this::buttonsAction);
        btnCancel.setOnClickListener(this::buttonsAction);
    }

    void buttonsAction(View v) {
        if(v == btnRegister){
            register();
        } else if(v == btnCancel){
            finish();
        }
    }

    void register(){
        String user = etUser.toString();
        String password = etPass.toString();
        try (DBAdmin dba = new DBAdmin(this,"BDPrueba", null, 1);
        SQLiteDatabase miBD = dba.getWritableDatabase()) {
            String[] parameters = {user, password};
            miBD.execSQL("insert into users values (?,?)", parameters);

            Cursor c = miBD.rawQuery("select * from users where name equals " + user, null);
            if (c.moveToFirst()){
                do{
                    Toast.makeText(this, "User Created" + c.getString(0), Toast.LENGTH_LONG).show();
                } while(c.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "register: ", e);
        }
    }

}