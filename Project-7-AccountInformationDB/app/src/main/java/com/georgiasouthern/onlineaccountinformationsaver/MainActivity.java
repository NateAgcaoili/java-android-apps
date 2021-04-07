package com.georgiasouthern.onlineaccountinformationsaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);
    EditText etSite;
    EditText etUser;
    EditText etPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etSite = findViewById(R.id.siteEdit);
        etUser = findViewById(R.id.userEdit);
        etPass = findViewById(R.id.passEdit);
    }

    public void insertClick(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("site", etSite.getText().toString());
        row.put("user", etUser.getText().toString());
        row.put("pass", etPass.getText().toString());
        db.insert("accounts", null, row);
        db.close();
    }

    public void retrieveClick(View view) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from accounts where site=?", new String[]{etSite.getText().toString()});
        if (cursor.moveToFirst()) {
            etUser.setText(cursor.getString(1));
            etPass.setText(cursor.getString(2));
        } else {
            etUser.setText("[null]");
            etPass.setText("[null]");
        }
    }
}