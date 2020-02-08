package com.example.pollenmonitoring;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import static com.example.pollenmonitoring.home.SelectedFragment;

public class AlertFragment extends Fragment {


    private static final String CHANNEL_ID = "pollen_monitoring";
    private static final String CHANNEL_NAME="Pollen Monitoring";
    private static final String CHANNEL_DESC="Pollen Monitoring Notification";
    private ProgressBar progressbar;
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_alert,container,false);
        mAuth =FirebaseAuth.getInstance();

        progressbar = v.findViewById(R.id.progressbar);
        email = v.findViewById(R.id.editTextEmail);
        password = v.findViewById(R.id.editTextPassword);

        v.findViewById(R.id.signUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

//        final TextView textView = v.findViewById(R.id.textView4);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }






        return v;
    }
    private void createUser(){
        final String eml = email.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        if(eml.isEmpty()){
            email.setError("Email required");
            email.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            password.setError("Password Required");
            password.requestFocus();
            return;
        }

        if(pass.length() <6){
            password.setError("Password should be atleast 6 characters long");
            password.requestFocus();
            return;
        }
        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(eml,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startNewActivity();
                }else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                            userLogin(eml,pass);
                    }
                    else{
                        progressbar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getActivity().getApplicationContext(),
                                task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void userLogin(String eml,String pass){
        mAuth.signInWithEmailAndPassword(eml,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startNewActivity();
                }
                else{
                    progressbar.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            SelectedFragment = new AlertFragment2();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    SelectedFragment).commit();
        }
    }

    private void startNewActivity(){
      SelectedFragment = new AlertFragment2();
      getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                SelectedFragment).commit();


    }

    private void DisplayNotification(){
        NotificationCompat.Builder mbuilder= new
        NotificationCompat.Builder(getActivity().getApplicationContext(),CHANNEL_ID)
                .setSmallIcon(R.drawable.alarm)
                .setContentTitle("Hurrah It is working")
                .setContentText("Your first Notification...")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat mNotificationMgr=NotificationManagerCompat.from(getActivity().getApplicationContext());
        mNotificationMgr.notify(1,mbuilder.build());
    }

}
