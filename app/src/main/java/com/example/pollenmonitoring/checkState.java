package com.example.pollenmonitoring;
import com.example.pollenmonitoring.MainActivity;
public class checkState {

    public void setState(Boolean bool){
        MainActivity.preferences.edit().putBoolean("first",bool).apply();
    }
}
