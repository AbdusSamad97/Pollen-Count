package com.example.pollenmonitoring;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.List;

import static com.example.pollenmonitoring.home.SelectedFragment;

public class AlertFragment2 extends Fragment {

    int preSelectedIndex = -1;
    private FirebaseAuth mAuth;
    private AppCompatEditText Allergy;
    private RadioButton Extreme;
    private RadioButton High;
    private RadioButton Moderate;
    private RadioButton Low;
    private RadioGroup radio;
    private RadioButton None;
    private String select;
    private static final String NODE_USERS="users";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_alert2,container,false);
        mAuth = FirebaseAuth.getInstance();

        Allergy = v.findViewById(R.id.allergy);
        Extreme = v.findViewById(R.id.Extreme);
        Moderate = v.findViewById(R.id.Moderate);
        High = v.findViewById(R.id.High);
        Low = v.findViewById(R.id.Low);
        radio = v.findViewById(R.id.rad);
        None = v.findViewById(R.id.None);


        v.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String alergy = Allergy.getText().toString().trim();
                String ext = Extreme.getText().toString().trim();
                String moderate = Moderate.getText().toString().trim();
                String high = High.getText().toString().trim();
                String none = None.getText().toString().trim();

                radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId){
                            case R.id.Extreme:
                                select= "Extreme";
                                break;
                            case R.id.High:
                                select= "High";
                                break;
                            case R.id.Moderate:
                                select= "Moderate";
                                break;
                            case R.id.Low:
                                select = "Low";
                                break;
                            case R.id.None:
                                select= "None";
                                break;
                        }
                    }
                });


                DatabaseReference db = FirebaseDatabase.getInstance().getReference(NODE_USERS);
                if(alergy.equals("Paper Mulbery")) {
                   db.child(mAuth.getCurrentUser().getUid()).child("paper_Mulbery").setValue(select).
                   addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(getActivity(),"Saved",Toast.LENGTH_LONG).show();
                           }
                           else{
                               Toast.makeText(getActivity(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                           }
                       }
                   });
                }
                else if(alergy.equals("Acacia")) {
                    db.child(mAuth.getCurrentUser().getUid()).child("acacia").setValue(select).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getActivity(),"Saved",Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(getActivity(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });;
                }
                else if(alergy.equals("Eucalyptus")) {
                    db.child(mAuth.getCurrentUser().getUid()).child("eucalyptus").setValue(select).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getActivity(),"Saved",Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(getActivity(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });;
                }
                else if(alergy.equals("Pines")) {
                    db.child(mAuth.getCurrentUser().getUid()).child("pines").setValue(select).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getActivity(),"Saved",Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(getActivity(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });;
                }
                else if(alergy.equals("Grasses")) {
                    db.child(mAuth.getCurrentUser().getUid()).child("grasses").setValue(select).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getActivity(),"Saved",Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(getActivity(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });;
                }
                else if(alergy.equals("Cannabis")) {
                    db.child(mAuth.getCurrentUser().getUid()).child("cannabis").setValue(select).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getActivity(),"Saved",Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(getActivity(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });;
                }
                else if(alergy.equals("Dandelion")) {
                    db.child(mAuth.getCurrentUser().getUid()).child("dandelion").setValue(select).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getActivity(),"Saved",Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(getActivity(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });;
                }
                else if(alergy.equals("Alternaria")) {
                    db.child(mAuth.getCurrentUser().getUid()).child("alternaria").setValue(select).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getActivity(),"Saved",Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(getActivity(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });;
                }


            }
        });

        FirebaseInstanceId.getInstance().getInstanceId().
                addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(task.isSuccessful()){
                            String token = task.getResult().getToken();
                            saveToken(token);

                        }
                        else{
//                            textView.setText(task.getException().getMessage());
                        }
                    }
                });

        android.widget.ListView listView = new ListView(v.getContext());
        listView.setBackgroundColor(this.getResources().getColor(R.color.colorBlack));

        final List<UserModel> users = new ArrayList<>();
        users.add(new UserModel(false, "Paper Mulbery"));
        users.add(new UserModel(false, "Acacia"));
        users.add(new UserModel(false, "Eucalyptus"));
        users.add(new UserModel(false, "Pines"));
        users.add(new UserModel(false, "Grasses"));
        users.add(new UserModel(false, "Cannabis"));
        users.add(new UserModel(false, "Dandelion"));
        users.add(new UserModel(false, "Alternaria"));

        final CustomAdapter adapter = new CustomAdapter(v.getContext(), users);
        listView.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setView(listView);


        final AlertDialog dialog = builder.create();
        v.findViewById(R.id.allergy).setOnClickListener(new View.OnClickListener() {
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
                AppCompatEditText allergy =  v.findViewById(R.id.allergy);
                allergy.setText(users.get(i).userName.toString());

            }
        });





        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() == null){
            SelectedFragment = new AlertFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    SelectedFragment).commit();
        }
    }

    private void  saveToken(String token){
        final Boolean[] child = {false};
        String email = mAuth.getCurrentUser().getEmail();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference(NODE_USERS);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(mAuth.getCurrentUser().getUid())){
                    child[0] = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        if(!child[0]) {
//
//            User user = new User(email, token, "", "", "", "",
//                    "", "", "", "");
//            DatabaseReference dbusers = FirebaseDatabase.getInstance().getReference(NODE_USERS);
//            dbusers.child(mAuth.getCurrentUser().getUid()).
//                    setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if (task.isSuccessful()) {
//                        Toast.makeText(getActivity().getApplicationContext(), "Token Saved", Toast.LENGTH_LONG).show();
//                    } else {
//                        Log.i("hello", task.getException().getMessage());
//                    }
//                }
//            });
//        }
//        else{
            DatabaseReference dbusers = FirebaseDatabase.getInstance().getReference(NODE_USERS);
            dbusers.child(mAuth.getCurrentUser().getUid()).child("token").setValue(token).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getActivity().getApplicationContext(), "Token Saved", Toast.LENGTH_LONG).show();
                }
            });
            dbusers.child(mAuth.getCurrentUser().getUid()).child("email").setValue(email);
//        }

        }

    }



