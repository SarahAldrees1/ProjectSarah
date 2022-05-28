package com.example.se3282022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.se3282022.firebase.FirebaseMainActivity;
import com.example.se3282022.sqlite.SqliteMainActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnFirebase = findViewById(R.id.btn_firebase);
        Button btnSqlite = findViewById(R.id.btn_sqlite);
        Button btnWeather = findViewById(R.id.btn_weather);

        btnFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFirebaseActivity();
            }
        });

        btnSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSqliteActivity();
            }
        });

        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWeatherActivity();
            }
        });
    }

    private void openFirebaseActivity() {
        startActivity(new Intent(MainActivity.this, FirebaseMainActivity.class));
    }

    private void openSqliteActivity() {
        startActivity(new Intent(MainActivity.this, SqliteMainActivity.class));
    }

    private void openWeatherActivity() {
        startActivity(new Intent(MainActivity.this, WeatherMainActivity.class));
    }
}