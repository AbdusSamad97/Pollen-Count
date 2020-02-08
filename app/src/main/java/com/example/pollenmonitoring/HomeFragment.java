package com.example.pollenmonitoring;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    private FirebaseDatabase database;
    DatabaseReference myRef ;
    ProgressDialog p ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =    inflater.inflate(R.layout.fragment_home,container,false);
        p = new ProgressDialog(view.getContext());
        p.setMessage("Loading Data");
        p.show();

        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            listItems = new ArrayList<ListItem>();
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("Information");

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    listItems.clear();
                    for(DataSnapshot itemSnapshot :dataSnapshot.getChildren()){



//                        Log.i("hello",itemSnapshot.child("Anwser").getValue().toString());
                        ListItem listItem = new ListItem(itemSnapshot.child("Anwser").getValue().toString()
                                ,itemSnapshot.child("Question").getValue().toString());
////
////
                         listItems.add(listItem);
                    }
                    p.dismiss();
                    adapter = new MyAdapter(listItems,view.getContext());
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



//            adapter = new MyAdapter(listItems,container.getContext());
//            recyclerView.setAdapter(adapter);
        return view;
    }
}
