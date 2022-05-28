package com.example.se3282022.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.se3282022.R;
import com.example.se3282022.model.CustomWeather;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseMainActivity extends AppCompatActivity {
    TextView city, weather, temp, humidity;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference databaseReference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("weather");
        city = findViewById(R.id.city_name);
        weather = findViewById(R.id.weather_type);
        temp = findViewById(R.id.temperature);
        humidity = findViewById(R.id.humidity);

        Button btnAddRecord = findViewById(R.id.btn_add_record);
        Button btnUpdate = findViewById(R.id.btn_update_record);
        Button btnDelete = findViewById(R.id.btn_delete);
        Button btnFetch = findViewById(R.id.btn_fetch_record);

        btnAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddRecord();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpdateRecord();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDelete();
            }
        });

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirebaseMainActivity.this, FirebaseFetchRecordActivity.class));
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                CustomWeather customWeather = snapshot.getValue(CustomWeather.class);
                city.setText(customWeather.getCity());
                weather.setText(customWeather.getWeather());
                temp.setText(customWeather.getTemp());
                humidity.setText(customWeather.getHumidity());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void openDelete() {
        startActivity(new Intent(FirebaseMainActivity.this, FirebaseDeleteRecordActivity.class));
    }

    private void openUpdateRecord() {
        startActivity(new Intent(FirebaseMainActivity.this, FirebaseUpdateRecordActivity.class));
    }

    private void openAddRecord(){
        startActivity(new Intent(FirebaseMainActivity.this, FirebaseAddRecordActivity.class));
    }
}