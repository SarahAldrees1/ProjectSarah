package com.example.se3282022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.se3282022.api.ApiClient;
import com.example.se3282022.api.ApiInterface;
import com.example.se3282022.model.CustomWeather;
import com.example.se3282022.model.WeatherResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherMainActivity extends AppCompatActivity {
    TextView city, weather, temp, humidity;
    EditText etCityName;
    ProgressBar progressBar;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference databaseReference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("weather");
        etCityName = findViewById(R.id.et_city);
        city = findViewById(R.id.city_name);
        weather = findViewById(R.id.weather_type);
        temp = findViewById(R.id.temperature);
        humidity = findViewById(R.id.humidity);
        progressBar = findViewById(R.id.progressbar);
        findViewById(R.id.btn_search_city).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etCityName.getText().toString().isEmpty()){
                    getWeatherDataFromApi(etCityName.getText().toString());
                }else
                    Toasty.error(WeatherMainActivity.this, "Please enter city name.", Toast.LENGTH_SHORT, true).show();
            }
        });
        getWeatherDataFromApi("berlin");
    }

    private void getWeatherDataFromApi(String city) {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<WeatherResponse> call = apiService.getWeather(city, "de28661d0d4037b2741cb13c123cb0a9", "metric");
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateViewsAndFirebase(response.body());
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toasty.error(WeatherMainActivity.this, "Error in response.", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toasty.error(WeatherMainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT, true).show();

            }
        });
    }

    private void updateViewsAndFirebase(WeatherResponse response) {
        city.setText(response.getName());
        weather.setText(response.getWeather().get(0).getMain());
        temp.setText(response.getMain().getTemp().toString());
        humidity.setText(response.getMain().getHumidity().toString());

        CustomWeather weather =new CustomWeather(
                response.getName(),
                response.getMain().getTemp().toString(),
                response.getWeather().get(0).getMain(),
                response.getMain().getHumidity().toString());
        databaseReference.setValue(weather).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}