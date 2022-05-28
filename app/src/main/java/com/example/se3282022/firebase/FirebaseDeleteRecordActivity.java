package com.example.se3282022.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.se3282022.R;
import com.example.se3282022.model.CustomWeather;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class FirebaseDeleteRecordActivity extends AppCompatActivity {
    Firebase firebase ;
    String id;
    TextView city, weather, temp, humidity;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference databaseReference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_delete_record);
        firebase = new Firebase(this);
        EditText etId = findViewById(R.id.et_id);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("weather");
        city = findViewById(R.id.city_name);
        weather = findViewById(R.id.weather_type);
        temp = findViewById(R.id.temperature);
        humidity = findViewById(R.id.humidity);

        Button btnDelete = findViewById(R.id.btn_delete_record);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = etId.getText().toString();
                deleteRecordInFirebase();
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

    private void deleteRecordInFirebase() {
        if (id != null || !id.isEmpty()){
            firebase.deleteRecord(id);
        }
        else
            Toasty.error(this, "Please input user ID.", Toast.LENGTH_SHORT, true).show();
    }
}