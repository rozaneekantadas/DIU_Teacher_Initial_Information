package com.suptodas.diu.diuteacherinitialinformation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTeacherInfo extends AppCompatActivity implements View.OnClickListener {

    private EditText initial, name, designation, department, phone, email;
    private Button add;
    private DatabaseHelper databaseHelper = new DatabaseHelper(this);
    private String data, tcrname, tcrinitial, tcrdesignation, tcrphone, tcremail, tcrdepartment;
    private String values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        data = extras.getString("DATA");
        tcrinitial = extras.getString("Initial");
        tcrname = extras.getString("Name");
        tcrdepartment = extras.getString("Department");
        tcrdesignation = extras.getString("Designation");
        tcrphone = extras.getString("Phone");
        tcremail = extras.getString("Email");


        initial = findViewById(R.id.initialEditText);
        name = findViewById(R.id.nameEditText);
        designation = findViewById(R.id.designationEditText);
        department = findViewById(R.id.departmentEditText);
        phone = findViewById(R.id.contactEditText);
        email = findViewById(R.id.emailEditText);

        add = findViewById(R.id.tcrInfoAdd);

        add.setOnClickListener(this);

        if(data.equals("edit")){
            name.setText(tcrname);
            designation.setText(tcrdesignation);
            department.setText(tcrdepartment);
            phone.setText(tcrphone);
            email.setText(tcremail);
            initial.setText(tcrinitial);
            add.setText("Edit");
        }
        if(data.equals("remove")){
            initial.setText(tcrinitial);
            add.setText("Remove");
            name.setVisibility(View.GONE);
            designation.setVisibility(View.GONE);
            department.setVisibility(View.GONE);
            phone.setVisibility(View.GONE);
            email.setVisibility(View.GONE);
        }
        if(data.equals("UserAdd")){
            values = "user";
        }
        if(data.equals("AdminAdd")){
            values = "admin";
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        String tcrInitial = initial.getText().toString();
        String tcrName = name.getText().toString();
        String tcrDesignation = designation.getText().toString();
        String tcrDepartment = department.getText().toString();
        String tcrPhone = phone.getText().toString();
        String tcrEmail = email.getText().toString();

        if(view.getId() == R.id.tcrInfoAdd){

            if(data.equals("UserAdd") || data.equals("AdminAdd")){

                if(tcrInitial.isEmpty() || tcrName.isEmpty()|| tcrDesignation.isEmpty() || tcrEmail.isEmpty() || tcrDepartment.isEmpty() || tcrPhone.isEmpty()){

                    if(tcrInitial.isEmpty()){
                        initial.setError("Enter Initial");
                        initial.requestFocus();
                    }
                    if(tcrName.isEmpty()){
                        name.setError("Enter Initial");
                        name.requestFocus();
                    }
                    if(tcrDesignation.isEmpty()){
                        designation.setError("Enter Initial");
                        designation.requestFocus();
                    }
                    if(tcrDepartment.isEmpty()){
                        department.setError("Enter Initial");
                        department.requestFocus();
                    }
                    if(tcrEmail.isEmpty()){
                        email.setError("Enter Initial");
                        email.requestFocus();
                    }
                    if(tcrPhone.isEmpty()){
                        phone.setError("Enter Initial");
                        phone.requestFocus();
                    }

                }

                else {
                    long rowId = databaseHelper.insertTeacherData(tcrInitial, tcrName, tcrDesignation, tcrDepartment, tcrPhone, tcrEmail);

                    if(rowId > 0){
                        Toast.makeText(getApplicationContext(), "Teacher Info Added", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(this, TeacherInfo.class);
                        intent.putExtra("DATA", values);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                    }
                }

            }

            if(data.equals("edit")){
                if(tcrInitial.isEmpty() || tcrName.isEmpty()|| tcrDesignation.isEmpty() || tcrEmail.isEmpty() || tcrDepartment.isEmpty() || tcrPhone.isEmpty()){

                    if(tcrInitial.isEmpty()){
                        initial.setError("Enter Initial");
                        initial.requestFocus();
                    }
                    if(tcrName.isEmpty()){
                        name.setError("Enter Initial");
                        name.requestFocus();
                    }
                    if(tcrDesignation.isEmpty()){
                        designation.setError("Enter Initial");
                        designation.requestFocus();
                    }
                    if(tcrDepartment.isEmpty()){
                        department.setError("Enter Initial");
                        department.requestFocus();
                    }
                    if(tcrEmail.isEmpty()){
                        email.setError("Enter Initial");
                        email.requestFocus();
                    }
                    if(tcrPhone.isEmpty()){
                        phone.setError("Enter Initial");
                        phone.requestFocus();
                    }

                }

                else {
                    int result = databaseHelper.updateTeacherData(tcrInitial, tcrName, tcrDesignation, tcrDepartment, tcrPhone, tcrEmail);

                    if(result > 0){
                        Toast.makeText(getApplicationContext(), "Teacher Info Edited", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(this, TeacherInfo.class);
                        intent.putExtra("DATA", "admin");
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();
                    }
                }
            }

            if(data.equals("remove")){
                if(tcrInitial.isEmpty()){
                    initial.setError("Enter Initial");
                    initial.requestFocus();
                }
                else{
                    int values = databaseHelper.deleteTeacherData(tcrInitial);
                    if(values > 0){
                        Toast.makeText(getApplicationContext(), "Delete Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(this, TeacherInfo.class);
                        intent.putExtra("DATA", "admin");
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}
