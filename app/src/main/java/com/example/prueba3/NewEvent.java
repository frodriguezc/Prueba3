package com.example.prueba3;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

public class NewEvent extends AppCompatActivity {
    private static final String TAG = "TAG_NEW_EVENT";
    String userName;
    EditText etEvTitle, etEvObs;
    CalendarView calendarView;
    Button btnSave, btnCancel;
    SimpleDateFormat df;


    @RequiresApi(api = Build.VERSION_CODES.N)
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

        df = new SimpleDateFormat("yyyy-MM-dd");



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

    public void btnsHandler() {
        btnSave.setOnClickListener(this::buttonsAction);
        btnCancel.setOnClickListener(this::buttonsAction);
    }

    private void buttonsAction(View v) {
        if (v == btnSave) {
            try (DBAdmin dba = new DBAdmin(this, "BDPrueba", null, 1);
                 SQLiteDatabase miBD = dba.getWritableDatabase()) {
                Random rand = new Random();
                int n = rand.nextInt(9999999);
                String[] params = {String.valueOf(n), etEvTitle.getText().toString(),
                        df.format(calendarView.getDate()),
                        String.valueOf(false),
                        etEvObs.getText().toString(), "TBD",
                        df.format(calendarView.getDate()), userName};
                miBD.rawQuery("insert into events values (?,?,?,?,?,?,?,?)", params);
                finish();
            } catch (Exception e) {
                Log.e(TAG, "buttonsAction: ", e);
            }
        }
        if (v == btnCancel) {
            finish();
        }
    }
}