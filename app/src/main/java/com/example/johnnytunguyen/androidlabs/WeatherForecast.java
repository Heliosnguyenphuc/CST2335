package com.example.johnnytunguyen.androidlabs;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class WeatherForecast extends Activity {

    ProgressBar prB ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        prB = findViewById(R.id.progressBar);
        prB.setVisibility(View.VISIBLE);


    }


    private class ForecastQuery extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
