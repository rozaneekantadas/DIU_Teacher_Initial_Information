package com.suptodas.diu.diuteacherinitialinformation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TeacherInfo extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Teacher> teacherList;
    private DatabaseHelper databaseHelper = new DatabaseHelper(this);
    private Teacher newTeacher;
    private String data;
    private String values;
    private SearchView searchView;
    private Teacher value;
    private Boolean condition = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        data = intent.getStringExtra("DATA");

        listView = findViewById(R.id.teacherlistView);
        searchView = findViewById(R.id.searchView);


        loadTeacherData();

//        TeacherAdepter adepter = new TeacherAdepter(this, teacherList);
//        listView.setAdapter(adepter);

        TextTeacherAdepter adepter = new TextTeacherAdepter(this, teacherList);
        listView.setAdapter(adepter);

        selectItem(listView, teacherList);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                ArrayList<Teacher> list = new ArrayList<>();

                if(s.isEmpty()){
                    list.addAll(teacherList);
//                    TeacherAdepter adepter = new TeacherAdepter(TeacherInfo.this, list);
//                    listView.setAdapter(adepter);

                    TextTeacherAdepter adepter = new TextTeacherAdepter(TeacherInfo.this, list);
                    listView.setAdapter(adepter);

                    selectItem(listView, list);
                }
                else {
                    for(Teacher teacher : teacherList){
                        if(teacher.getInitial().toLowerCase().contains(s.toLowerCase())){
                            list.add(teacher);
                        }
                    }

//                    TeacherAdepter adepter = new TeacherAdepter(TeacherInfo.this, list);
//                    listView.setAdapter(adepter);

                    TextTeacherAdepter adepter = new TextTeacherAdepter(TeacherInfo.this, list);
                    listView.setAdapter(adepter);

                    selectItem(listView, list);
                }
                return true;
            }
        });



    }

    public void selectItem(ListView listViewItem, final List<Teacher> tcrList){

        listViewItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                condition = true;
                value = tcrList.get(i);

                Intent intent = new Intent(TeacherInfo.this, PopUpinfo.class);
                Bundle extras = new Bundle();
                extras.putString("DATA", data);
                extras.putString("Initial", value.getInitial());
                extras.putString("Name", value.getName());
                extras.putString("Email", value.getEmail());
                extras.putString("Department", value.getDepartment());
                extras.putString("Phone", value.getPhone());
                extras.putString("Designation", value.getDesignation());
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

    }

    public void loadTeacherData(){
        Cursor cursor = databaseHelper.teacherShowData();
        teacherList = new ArrayList<>();

        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()){
                String initial = cursor.getString(0);
                String name = cursor.getString(1);
                String designation = cursor.getString(2);
                String department = cursor.getString(3);
                String phone = cursor.getString(4);
                String email = cursor.getString(5);

                newTeacher = new Teacher(initial, name, designation, department, phone, email);
                teacherList.add(newTeacher);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(data.equals("user")){
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.user_teacher_menu, menu);
            values = "UserAdd";
        }

        if(data.equals("admin")){
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.teacher_menu, menu);
            values = "AdminAdd";
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            this.finish();
        }

        if(item.getItemId() == R.id.tcradd){
            Intent intent = new Intent(this, AddTeacherInfo.class);
            intent.putExtra("DATA", values);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.tcredit){

            if(condition == false){
                Toast.makeText(getApplicationContext(), "Select an Item", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(this, AddTeacherInfo.class);
                Bundle extras = new Bundle();
                extras.putString("DATA", "edit");
                extras.putString("Initial", value.getInitial());
                extras.putString("Name", value.getName());
                extras.putString("Email", value.getEmail());
                extras.putString("Department", value.getDepartment());
                extras.putString("Phone", value.getPhone());
                extras.putString("Designation", value.getDesignation());
                intent.putExtras(extras);
                startActivity(intent);
            }

        }

        if(item.getItemId() == R.id.tcrremove){

            if(condition == false){
                Toast.makeText(getApplicationContext(), "Select an Item", Toast.LENGTH_SHORT).show();
            }
            else{
//                Intent intent = new Intent(this, AddTeacherInfo.class);
////                Bundle extras = new Bundle();
////                extras.putString("DATA", "remove");
////                extras.putString("Initial", value.getInitial());
////                intent.putExtras(extras);
////                startActivity(intent);
                int values = databaseHelper.deleteTeacherData(value.getInitial());
                if(values > 0){
                    loadTeacherData();
                    TextTeacherAdepter adepter = new TextTeacherAdepter(this, teacherList);
                    listView.setAdapter(adepter);
                    Toast.makeText(getApplicationContext(), "Delete Successfully", Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
