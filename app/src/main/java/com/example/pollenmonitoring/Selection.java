package com.example.pollenmonitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Selection extends AppCompatActivity {
    private String selected="";
    private TextInputLayout textlay ;
    private TextInputEditText textId ;
    int preSelectedIndex = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);


        textlay = (TextInputLayout)findViewById(R.id.textlay);
        textId = (TextInputEditText) findViewById(R.id.edittext);
        MaterialButton proceed = (MaterialButton)findViewById(R.id.proceed);

        RadioGroup radio = (RadioGroup)findViewById(R.id.radio);



        textlay.setCounterEnabled(true);
        textlay.setCounterMaxLength(20);
        ListView listView = new ListView(this);
        listView.setBackgroundColor(this.getResources().getColor(R.color.colorBlack));

        final List<UserModel> users = new ArrayList<>();
        users.add(new UserModel(false, "G-11"));
        users.add(new UserModel(false, "H-12"));
        users.add(new UserModel(false, "F-8"));
        users.add(new UserModel(false, "G-7"));

        final CustomAdapter adapter = new CustomAdapter(this, users);
        listView.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(Selection.this);
        builder.setCancelable(true);
        builder.setView(listView);


        final AlertDialog dialog = builder.create();
        textId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                UserModel model = users.get(i); //changed it to model because viewers will confused about it

                model.setSelected(true);

                users.set(i, model);

                if (preSelectedIndex > -1){

                    UserModel preRecord = users.get(preSelectedIndex);
                    preRecord.setSelected(false);

                    users.set(preSelectedIndex, preRecord);

                }

                preSelectedIndex = i;

                //now update adapter so we are going to make a update method in adapter
                //now declare adapter final to access in inner method

                adapter.updateRecords(users);
                textId.setText(users.get(i).userName.toString());

            }
        });




        textId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(textId.getText().toString().equals("")){
                    textlay.setErrorEnabled(true);
                    textlay.setError("Please Enter your Location");
                }
                else{
                    textlay.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        textId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                textlay.setErrorEnabled(false);
            }
        });

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.gps){
                    textlay.setVisibility(View.INVISIBLE);
                    selected = "gps";
                }
                else if(checkedId == R.id.location){
                    textlay.setVisibility(View.VISIBLE);
                    selected = "location";
                }
            }
        });



    }



    public boolean isNetworkConnected() {
        final ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null) {
            if (Build.VERSION.SDK_INT < 23) {
                final NetworkInfo ni = cm.getActiveNetworkInfo();

                if (ni != null) {
                    return (ni.isConnected() && (ni.getType() == ConnectivityManager.TYPE_WIFI || ni.getType() == ConnectivityManager.TYPE_MOBILE));
                }
            } else {
                final Network n = cm.getActiveNetwork();

                if (n != null) {
                    final NetworkCapabilities nc = cm.getNetworkCapabilities(n);

                    return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                }
            }
        }

        return false;
    }
    public void proceedClicked(View view) throws IOException {
        if(isNetworkConnected()){
            if(selected.equals("gps")){
                Intent intent = new Intent(this,MapsActivity.class);
                startActivity(intent);
            }
            else if(selected.equals("location")){
                String Location = textId.getText().toString();
                Location+=",Islamabad";
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> address = geocoder.getFromLocationName(Location,1);
                if(address.size() > 0 && address.get(0) != null) {

                    Intent intent = new Intent(this, MapsActivity2.class);
                    String latitude = Double.toString(address.get(0).getLatitude());
                    String longitude = Double.toString(address.get(0).getLongitude());
                    intent.putExtra("Latitude",latitude);
                    intent.putExtra("Longitude",longitude);
                    intent.putExtra("Name",address.get(0).getLocality().toString());

                    startActivity(intent);
                }

            }
        }
        else{
            Toast.makeText(getApplicationContext()," No Internet Connection!!",Toast.LENGTH_LONG).show();
        }
    }


}
