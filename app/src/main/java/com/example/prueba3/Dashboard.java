package com.example.prueba3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    Button btnUserDetails;
    AutoCompleteTextView actSearch;
    ListView lvEvents;
    FloatingActionButton fabAddEvent;
    ArrayList<String> userEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        widgetInit();
        Bundle b = getIntent().getExtras();
        String userName = b.get("userName").toString();

        try (DBAdmin dba = new DBAdmin(this, "BDPrueba", null, 1);
        SQLiteDatabase miBD = dba.getReadableDatabase()){
            Cursor c = miBD.rawQuery("select * from events where owner equals " + userName, null);
            if (c.moveToFirst()){
                int index = c.getPosition();
                do{
                    userEvents.add(c.getInt(0) + c.getString(1) + c.getString(4));
                } while (c.moveToNext());
            }
        }

    }

    private void widgetInit() {
        btnUserDetails = findViewById(R.id.btnUserDetails);
        actSearch = findViewById(R.id.actSearch);
        lvEvents = findViewById(R.id.lvEvents);
        fabAddEvent = findViewById(R.id.fabAddEvent);
    }
}