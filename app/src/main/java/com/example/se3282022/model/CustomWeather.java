package com.example.se3282022.model;

public class CustomWeather {
    String city;
    String temp;
    String weather;
    String humidity;

    public CustomWeather() {
    }

    public CustomWeather(String city, String temp, String weather, String humidity) {
        this.city = city;
        this.weather = weather;
        this.temp = temp;
        this.humidity = humidity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
