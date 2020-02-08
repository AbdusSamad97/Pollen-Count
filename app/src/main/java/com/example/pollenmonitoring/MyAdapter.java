package com.example.pollenmonitoring;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.textViewHead.setText(listItem.getQuestion());
        holder.textViewDesc.setText(listItem.getAnwser());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private AppCompatTextView textViewHead;
        private AppCompatTextView textViewDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHead = (AppCompatTextView)itemView.findViewById(R.id.questionid);
            textViewDesc = (AppCompatTextView)itemView.findViewById(R.id.anwserid);
        }
    }
}
