package com.example.pollenmonitoring;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.ViewHolder> {

    private List<ListView> listItems;
    private Context context;

    public WeekAdapter(List<ListView> listItems, Context context) {
        this.listItems = listItems;
        this.context =context;
    }


    @NonNull
    @Override
    public WeekAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weekday_cart,parent,false);
        return new WeekAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekAdapter.ViewHolder holder, int position) {
        ListView listItem = listItems.get(position);

        holder.Date.setText(listItem.getDate());
        holder.Day.setText(listItem.getDay());
        holder.Category.setText(listItem.getCategory());
        if (listItem.getCategory().equals("Extreme")) {
            holder.heatweek.setBackgroundColor(Color.parseColor("#FF0000"));
            holder.Category.setTextColor(Color.parseColor("#FF0000"));
            holder.Category.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.red_border));
        } else if (listItem.getCategory().equals("High")) {
            holder.heatweek.setBackgroundColor(Color.parseColor("#ffa500"));
            holder.Category.setTextColor(Color.parseColor("#ffa500"));
            holder.Category.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.orangeborder));
        } else if (listItem.getCategory().equals("Moderate")) {
            holder.heatweek.setBackgroundColor(Color.parseColor("#F08080"));
            holder.Category.setTextColor(Color.parseColor("#F08080"));
            holder.Category.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.yellowborder));
        } else if (listItem.getCategory().equals("Low")) {
            holder.heatweek.setBackgroundColor(Color.parseColor("#00ff00"));
            holder.Category.setTextColor(Color.parseColor("#00ff00"));
            holder.Category.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.greenborder));
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private AppCompatTextView Day;
        private AppCompatTextView Date;
        private AppCompatTextView Category;
        private LinearLayout heatweek;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Day = (AppCompatTextView)itemView.findViewById(R.id.day);
            Date = (AppCompatTextView)itemView.findViewById(R.id.date);
            Category = (AppCompatTextView)itemView.findViewById(R.id.category);
            heatweek = itemView.findViewById(R.id.heatbar1);
        }
    }

}
