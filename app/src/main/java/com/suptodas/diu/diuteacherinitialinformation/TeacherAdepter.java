package com.suptodas.diu.diuteacherinitialinformation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TeacherAdepter extends ArrayAdapter<Teacher> {

    Context context;
    ArrayList<Teacher> teacherList;

    public TeacherAdepter(Context context, ArrayList<Teacher> teacherList) {
        super(context, R.layout.sample_teache_info, teacherList);
        this.context = context;
        this.teacherList = teacherList;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.sample_teache_info, null);

        TextView initial, name, designation, department, phone, email;

        initial = view.findViewById(R.id.initial);
        name = view.findViewById(R.id.name);
        designation = view.findViewById(R.id.designation);
        department = view.findViewById(R.id.department);
        phone = view.findViewById(R.id.contact);
        email = view.findViewById(R.id.email);

        final Teacher teacher = teacherList.get(i);

        initial.setText(teacher.getInitial());
        name.setText(teacher.getName());
        designation.setText(teacher.getDesignation());
        department.setText(teacher.getDepartment());
        email.setText(teacher.getEmail());
        phone.setText(teacher.getPhone());

        return view;
    }
}
