                                                                                                                                                                    package com.georgiasouthern.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
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
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from mytable where id=?",
                new String[]{etId.getText().toString()});
        if (cursor.moveToFirst()) {
            etVal.setText(cursor.getString(1));
        } else {
            etVal.setText("[null]");
        }
    }

    public void onUpdate(View view) {
        SQLiteDatabase dbWrite = helper.getWritableDatabase();
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues row = new ContentValues();
        row.put("id", etId.getText().toString());
        row.put("val", etVal.getText().toString());
        Cursor cursor = db.rawQuery("select * from mytable where id=?",
                new String[]{etId.getText().toString()});
        if (cursor.moveToFirst()) {
            dbWrite.update("mytable", row, "id=?", new String[]{etId.getText().toString()});
        } else {
            db.insert("mytable", null, row);
        }
    }
}