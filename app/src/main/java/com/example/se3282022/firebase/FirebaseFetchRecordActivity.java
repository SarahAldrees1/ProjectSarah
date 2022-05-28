package com.example.se3282022.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.se3282022.R;
import com.example.se3282022.model.CustomWeather;
import com.example.se3282022.model.User;
import com.example.se3282022.sqlite.UserDatabaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class FirebaseFetchRecordActivity extends AppCompatActivity {
    String id;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference databaseReference ;
    DatabaseReference dbWeatherReference ;
    TextView city, weather, temp, humidity;

    UserDatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_fetch_record);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("user");
        dbWeatherReference = firebaseDatabase.getReference("weather");
        db = new UserDatabaseHelper(this);

        EditText etId = findViewById(R.id.et_id);

        city = findViewById(R.id.city_name);
        weather = findViewById(R.id.weather_type);
        temp = findViewById(R.id.temperature);
        humidity = findViewById(R.id.humidity);

        Button btnFetchSingle = findViewById(R.id.btn_single_record);
        Button btnFetchAll = findViewById(R.id.btn_all_record);

        btnFetchSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = etId.getText().toString();
                if (id != null && !id.isEmpty()){
                    fetchSingleRecord();
                }
                else Toasty.error(FirebaseFetchRecordActivity.this, "Please input all values.", Toast.LENGTH_SHORT, true).show();

            }
        });

        btnFetchAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirebaseFetchRecordActivity.this, FirebaseAllRecordActivity.class));
            }
        });

        dbWeatherReference.addValueEventListener(new ValueEventListener() {
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

    public void fetchSingleRecord(){
        final User[] user = new User[1];
        databaseReference.child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    user[0] = task.getResult().getValue(User.class);
                    if (user[0] != null) {
                        Toasty.success(FirebaseFetchRecordActivity.this, user[0].toString(), Toast.LENGTH_SHORT).show();
                        db.addUser(user[0]);
                    }else
                    Toasty.error(FirebaseFetchRecordActivity.this, "No record found", Toast.LENGTH_SHORT).show();
                }
                else Toasty.error(FirebaseFetchRecordActivity.this, "Can not fetch record.", Toast.LENGTH_SHORT, true).show();
            }
        });
    }
}