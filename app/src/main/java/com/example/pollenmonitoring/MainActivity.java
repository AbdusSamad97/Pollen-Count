package com.example.pollenmonitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.pollenmonitoring.checkState;

public class MainActivity extends AppCompatActivity {
    public static SharedPreferences preferences = null;
    public Boolean firstState = null;

    public void clickStarted(View v){
        Intent intent = new Intent(this,Selection.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        consultState();




    }

    public void consultState(){
        checkState c = new checkState();
        if(firstState == null){

            preferences=this.getSharedPreferences("com.example.pollenmonitoring", Context.MODE_PRIVATE);
            firstState = preferences.getBoolean("first",true);

            if(firstState == true){
                c.setState(true);
            }
            else{
                Intent intent = new Intent(this,Selection.class);
                startActivity(intent);
                finish();
            }
            c.setState(false);

        }

    }
}
