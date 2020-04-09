package com.suptodas.diu.diuteacherinitialinformation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdepter extends ArrayAdapter<User> {

    Context context;
    ArrayList<User> userList;

    public UserAdepter(Context context, ArrayList<User> userList) {
        super(context, R.layout.sample_user_list, userList);
        this.context = context;
        this.userList = userList;
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

        final User user = userList.get(i);

        id.setText(user.getId());
        name.setText(user.getName());
        email.setText(user.getEmail());
        username.setText(user.getUsername());
        password.setText(user.getPassword());

        return view;
    }
}
