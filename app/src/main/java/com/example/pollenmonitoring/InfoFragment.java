package com.example.pollenmonitoring;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import static java.lang.Thread.sleep;

public class InfoFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter1;
    private RecyclerView.Adapter recyclerAdapter;
    private List<com.example.pollenmonitoring.ListView> listItems;

    int preSelectedIndex = 1;
    int preSelectedIndex1 = 1;
    AppCompatEditText pollenType;
    TextView yesterday_Cg;
    AppCompatTextView today_category;
    AppCompatTextView today_date;
    AppCompatTextView today_pollen;
    LinearLayout heatbar;
    LinearLayout heatweek;
    ProgressDialog p;
    private FirebaseDatabase database;
    DatabaseReference myRef;
    AppCompatEditText pollenStation;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.weekrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listItems = new ArrayList<com.example.pollenmonitoring.ListView>();

        pollenStation = view.findViewById(R.id.txtStatus);
        heatweek = view.findViewById(R.id.heatbar1);

        //default data to display considering G-7 and Paper Mulbery
        p = new ProgressDialog(view.getContext());
        p.setMessage("Loading Data");
        p.show();

        loading_data("G-7","Paper Mulbery");

        weekloadingData("G-7","Paper Mulbery");
//        recyclerAdapter = new WeekAdapter(listItems,view.getContext());
//        recyclerView.setAdapter(recyclerAdapter);






        ListView listView = new ListView(view.getContext());
        listView.setBackgroundColor(this.getResources().getColor(R.color.colorBlack));

        final List<UserModel> users = new ArrayList<>();
        users.add(new UserModel(false, "G-11"));
        users.add(new UserModel(false, "H-12"));
        users.add(new UserModel(false, "F-8"));
        users.add(new UserModel(false, "G-7"));

        final CustomAdapter adapter = new CustomAdapter(view.getContext(), users);
        listView.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setView(listView);


        final AlertDialog dialog = builder.create();


        pollenStation.setOnClickListener(new View.OnClickListener() {
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

                if (preSelectedIndex > -1) {

                    UserModel preRecord = users.get(preSelectedIndex);
                    preRecord.setSelected(false);

                    users.set(preSelectedIndex, preRecord);

                }

                preSelectedIndex = i;

                //now update adapter so we are going to make a update method in adapter
                //now declare adapter final to access in inner method

                adapter.updateRecords(users);
                pollenStation.setText(users.get(i).userName.toString());
                pollenType.setText("- Select Pollen Type -");
            }
        });


        // task for pollen Type

        pollenType = view.findViewById(R.id.pollenType);


        ListView listView1 = new ListView(view.getContext());
        listView1.setBackgroundColor(this.getResources().getColor(R.color.colorBlack));

        final List<UserModel> users1 = new ArrayList<>();
        users1.add(new UserModel(false, "Paper Mulbery"));
        users1.add(new UserModel(false, "Acacia"));
        users1.add(new UserModel(false, "Eucalyptus"));
        users1.add(new UserModel(false, "Pines"));
        users1.add(new UserModel(false, "Grasses"));
        users1.add(new UserModel(false, "Cannabis"));
        users1.add(new UserModel(false, "Dandelion"));
        users1.add(new UserModel(false, "Alternaria"));


        final CustomAdapter adapter1 = new CustomAdapter(view.getContext(), users1);
        listView1.setAdapter(adapter1);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setCancelable(true);
        builder1.setView(listView1);


        final AlertDialog dialog1 = builder1.create();

        pollenType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.show();
            }
        });


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                UserModel model = users1.get(i); //changed it to model because viewers will confused about it

                model.setSelected(true);

                users1.set(i, model);

                if (preSelectedIndex1 > -1) {

                    UserModel preRecord = users1.get(preSelectedIndex1);
                    preRecord.setSelected(false);

                    users1.set(preSelectedIndex1, preRecord);

                }

                preSelectedIndex1 = i;

                //now update adapter so we are going to make a update method in adapter
                //now declare adapter final to access in inner method

                adapter1.updateRecords(users1);

                pollenType.setText(users1.get(i).userName.toString());

                dialog1.dismiss();

                p = new ProgressDialog(view.getContext());
                p.setMessage("Loading Data");
                p.show();

                loading_data(pollenStation.getText().toString(),pollenType.getText().toString());
                weekloadingData(pollenStation.getText().toString(),pollenType.getText().toString());

//                recyclerAdapter = new WeekAdapter(listItems,view.getContext());
//                recyclerView.setAdapter(recyclerAdapter);

            }
        });


        //today cast
        heatbar = view.findViewById(R.id.heatbar);
        heatweek = view.findViewById(R.id.heatbar1);
        today_pollen = view.findViewById(R.id.today_pollen);
        today_date = view.findViewById(R.id.today_date);
        today_category = view.findViewById(R.id.today_category);
        yesterday_Cg = view.findViewById(R.id.yesterday_category);


        return view;
    }

    public void loading_data(final String PollenStation, final String PollenType) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("forecast").child("Sector").child(PollenStation).
                child(PollenType);

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String category = dataSnapshot.child("Day1").child("Category").getValue().toString();
                String date = dataSnapshot.child("Day1").child("Date").getValue().toString();
                String yesterday = dataSnapshot.child("Day1").child("yesterday_Cg").getValue().toString();

                Log.i("hello", category);
                Log.i("hello", date);

                if (category.equals("Extreme")) {
                    heatbar.setBackgroundColor(Color.parseColor("#FF0000"));
                } else if (category.equals("High")) {
                    heatbar.setBackgroundColor(Color.parseColor("#ffa500"));
                } else if (category.equals("Moderate")) {
                    heatbar.setBackgroundColor(Color.parseColor("#F08080"));
                } else if (category.equals("Low")) {
                    heatbar.setBackgroundColor(Color.parseColor("#00ff00"));
                }

                today_pollen.setText(PollenStation + " "
                        + PollenType + " \n" + "Forecast");

                today_date.setText(date);
                today_category.setText(category);
                yesterday_Cg.setText("It was " + yesterday.toUpperCase() + " yesterday");



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void weekloadingData(String PollenStation, String PollenType){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("forecast").child("Sector").child(PollenStation).
                child(PollenType);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listItems.clear();
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    com.example.pollenmonitoring.ListView listItem = new com.example.pollenmonitoring.ListView(
                            itemSnapshot.child("Date").getValue().toString(),itemSnapshot.child("Day").getValue().toString(),
                            itemSnapshot.child("Category").getValue().toString()


                    );

                    String category =itemSnapshot.child("Category").getValue().toString();
//                    heatweek = getActivity().findViewById(R.id.heatbar1);
//                    if (category.equals("Extreme")) {
//                        heatweek.setBackgroundColor(Color.parseColor("#FF0000"));
//                    } else if (category.equals("High")) {
//                        heatweek.setBackgroundColor(Color.parseColor("#ffa500"));
//                    } else if (category.equals("Moderate")) {
//                        heatweek.setBackgroundColor(Color.parseColor("#ffff00"));
//                    } else if (category.equals("Low")) {
//                        heatweek.setBackgroundColor(Color.parseColor("#00ff00"));
//                    }

                    listItems.add(listItem);

                }
                p.dismiss();
                recyclerAdapter = new WeekAdapter(listItems,getContext());
                recyclerView.setAdapter(recyclerAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}