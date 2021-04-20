package com.georgiasouthern.project8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        String s = "https://api.covidtracking.com/v1/states/";
        EditText et = findViewById(R.id.editTextTextPersonName);
        s += et.getText().toString();
        s += "/current.json";
        new NetTask().execute(s);
    }

    class NetTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String addr = strings[0];
            try {
                URL url = new URL(addr);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                if (inputStream == null) return null;
                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                reader.close();
                return buffer.toString();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            try {
                JSONObject jo = new JSONObject(result);
                String state = jo.getString("state");
                TextView tvState = findViewById(R.id.tvState);
                tvState.setText("state: " + state);
                int positive = jo.getInt("positive");
                TextView tvTemp = findViewById(R.id.tvPositive);
                tvTemp.setText("positive: " + positive);
                int death = jo.getInt("death");
                TextView tvDeath = findViewById(R.id.tvDeath);
                tvDeath.setText("death: " + death);
                int hospitalizedCurrently = jo.getInt("hospitalizedCurrently");
                TextView tvHospitalizedCurrently = findViewById(R.id.tvHospitalizedCurrently);
                tvHospitalizedCurrently.setText("hospitalizedCurrently: " + hospitalizedCurrently);
                String dateChecked = jo.getString("dateChecked");
                TextView tvDateChecked = findViewById(R.id.tvDateChecked);
                tvDateChecked.setText("dateChecked: " + dateChecked);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}