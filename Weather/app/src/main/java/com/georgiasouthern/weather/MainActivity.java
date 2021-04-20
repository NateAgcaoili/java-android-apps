package com.georgiasouthern.weather;

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
        String s = "https://api.openweathermap.org/data/2.5/weather?q=";
        EditText et = findViewById(R.id.editTextTextPersonName);
        s += et.getText().toString();
        s += "&APPID=0a898072afd99c0538a46a0714c89fa7";
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
                JSONArray ja = jo.getJSONArray("weather");
                JSONObject jo1 = ja.getJSONObject(0);
                String desc = jo1.getString("description");
                TextView tvDesc = findViewById(R.id.tvDesc);
                tvDesc.setText(desc);
                JSONObject joMain = jo.getJSONObject("main");
                double temp = joMain.getDouble("temp");
                TextView tvTemp = findViewById(R.id.tvTemp);
                tvTemp.setText(""+temp);
                JSONObject joWind = jo.getJSONObject("wind");
                double windSpeed = joWind.getDouble("speed");
                TextView tvWind = findViewById(R.id.tvWind);
                tvWind.setText("" + windSpeed);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}