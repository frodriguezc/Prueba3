package com.example.prueba3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    private static final String TAG = "TAG_DASH";
    String userName;
    Button btnUserDetails;
    AutoCompleteTextView actSearch;
    ListView lvEvents;
    FloatingActionButton fabAddEvent;
    ArrayList<String> userEvents;
    ArrayAdapter userEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        widgetInit();


    }

    protected void onStart() {
        widgetInit();
        super.onStart();
    }

    private void widgetInit() {
        btnUserDetails = findViewById(R.id.btnUserDetails);
        actSearch = findViewById(R.id.actSearch);
        lvEvents = findViewById(R.id.lvEvents);
        fabAddEvent = findViewById(R.id.fabAddEvent);

        Bundle b = getIntent().getExtras();
        userName = b.get("userName").toString();

        try (DBAdmin dba = new DBAdmin(this, "BDPrueba", null, 1);
             SQLiteDatabase miBD = dba.getWritableDatabase()) {
            Cursor c = miBD.rawQuery("select * from events", null);
//            Cursor c = miBD.rawQuery("select * from events where owner = '" + userName + "'", null);
            if (c.moveToFirst()) {
                String index = String.valueOf(c.getPosition());
                do {
                    userEvents.add(index + " " + c.getString(1) + " " + c.getString(4));
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "onCreate: QUERY ", e);
        }

        userEventAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                userEvents);
        if (userEvents != null) {
            lvEvents.setAdapter(userEventAdapter);
        }
    }

    public void addEvent(View v){
        Intent i = new Intent(this, NewEvent.class);
        i.putExtra("userName", userName);
        startActivity(i);
    }
}