package com.georgiasouthern.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);
    EditText etId;
    EditText etVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etId = findViewById(R.id.editTextTextPersonName);
        etVal = findViewById(R.id.editTextTextPersonName2);
    }

    public void onInsert(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("id", etId.getText().toString());
        row.put("val", etVal.getText().toString());
        db.insert("mytable", null, row);
        db.close();
    }

    public void onSelect(View view) {

    }
}