package com.example.pollenmonitoring;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Activity activity;
    Context context;
    List<UserModel> users;
    LayoutInflater inflater;

    //short to create constructer using command+n for mac & Alt+Insert for window


    public CustomAdapter(Activity activity) {
        this.activity = activity;
    }

    public CustomAdapter(Activity activity, List<UserModel> users) {
        this.activity   = activity;
        this.users      = users;

        inflater        = activity.getLayoutInflater();
    }

    public CustomAdapter(Context context, List<UserModel> users) {
        this.context = context;
        this.users = users;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if (view == null){

            view = inflater.inflate(R.layout.list_view, viewGroup, false);

            holder = new ViewHolder();

            holder.tvUserName = (TextView)view.findViewById(R.id.tv_user_name);
            holder.ivCheckBox = (ImageView) view.findViewById(R.id.iv_check_box);

            view.setTag(holder);
        }else
            holder = (ViewHolder)view.getTag();

        UserModel model = users.get(i);

        holder.tvUserName.setText(model.getUserName());

        if (model.isSelected())
            holder.ivCheckBox.setBackgroundResource(R.drawable.checked);

        else
            holder.ivCheckBox.setBackgroundResource(R.drawable.check);

        return view;

    }

    public void updateRecords(List<UserModel> users){
        this.users = users;

        notifyDataSetChanged();
    }

    class ViewHolder{

        TextView tvUserName;
        ImageView ivCheckBox;

    }
}

