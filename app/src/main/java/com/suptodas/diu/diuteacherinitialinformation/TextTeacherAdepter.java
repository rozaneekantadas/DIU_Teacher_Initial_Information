package com.suptodas.diu.diuteacherinitialinformation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TextTeacherAdepter extends BaseAdapter {

    Context context;
    ArrayList<Teacher> tcrList;

    public TextTeacherAdepter(Context context, ArrayList<Teacher> tcrList) {
        this.context = context;
        this.tcrList = tcrList;
    }

    @Override
    public int getCount() {
        return this.tcrList.size();
    }

    @Override
    public Object getItem(int i) {
        return tcrList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.sample_teache_info, null);


        TextView initial, name, designation, department, phone, email;

        initial = view.findViewById(R.id.initial);
        name = view.findViewById(R.id.name);
        designation = view.findViewById(R.id.designation);
        department = view.findViewById(R.id.department);
        phone = view.findViewById(R.id.contact);
        email = view.findViewById(R.id.email);

        Teacher teacher = tcrList.get(i);

        initial.setText(teacher.getInitial());
        name.setText(teacher.getName());
        designation.setText(teacher.getDesignation());
        department.setText(teacher.getDepartment());
        email.setText(teacher.getEmail());
        phone.setText(teacher.getPhone());

        return view;
    }
}
