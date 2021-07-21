package com.example.prueba3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;

import static android.system.Os.close;

public class NewEvent extends AppCompatActivity {
    String userName;
    EditText etEvTitle, etEvObs;
    CalendarView calendarView;
    Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        try {
            initView();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        btnsHandler();


    }

    @SuppressLint("SimpleDateFormat")
    private void initView() throws ParseException {
        etEvTitle = findViewById(R.id.etEvTitle);
        etEvObs = findViewById(R.id.etEvObs);
        calendarView = findViewById(R.id.calendarView);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        Bundle b = getIntent().getExtras();
        userName = b.get("userName").toString();
        String selectedDate = "20/07/2021";
        calendarView.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(selectedDate).getTime(), true, true);
    }

    public void btnsHandler(){
        btnSave.setOnClickListener(this::buttonsAction);
        btnCancel.setOnClickListener(this::buttonsAction);
    }

    private void buttonsAction(View v){
        if (v == btnSave){
            try (DBAdmin dba = new DBAdmin(this, "BDPrueba", null, 1);
            SQLiteDatabase miBD = dba.getWritableDatabase()){
                Random rand = new Random();
                int n = rand.nextInt(9999999);
                String[] params = {String.valueOf(n), etEvTitle.getText().toString(),
                        String.valueOf(calendarView.getDate()), String.valueOf(false),
                        etEvObs.getText().toString(), "TBD",
                        String.valueOf(calendarView.getDate()), userName};
                miBD.execSQL("insert into events values (?,?,?,?,?,?)", params);
            }
        }
        if (v == btnCancel){
            finish();
        }
    }
}