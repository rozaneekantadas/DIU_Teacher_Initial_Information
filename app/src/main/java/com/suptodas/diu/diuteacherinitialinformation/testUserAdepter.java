package com.suptodas.diu.diuteacherinitialinformation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class testUserAdepter extends BaseAdapter {

    Context context;
    ArrayList<User> userList;

    public testUserAdepter(Context context, ArrayList<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return this.userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.sample_user_list, null);

        TextView id, name, email, username, password;

        id = view.findViewById(R.id.listItemId);
        name = view.findViewById(R.id.listItemName);
        email = view.findViewById(R.id.listItemEmail);
        username = view.findViewById(R.id.listItemUsername);
        password = view.findViewById(R.id.listItemPassword);

        User user = userList.get(i);

        id.setText(user.getId());
        name.setText(user.getName());
        email.setText(user.getEmail());
        username.setText(user.getUsername());
        password.setText(user.getPassword());

        return view;
    }
}
