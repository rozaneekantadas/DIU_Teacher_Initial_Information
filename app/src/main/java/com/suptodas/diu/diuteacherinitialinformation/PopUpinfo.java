package com.suptodas.diu.diuteacherinitialinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PopUpinfo extends AppCompatActivity implements View.OnClickListener {

    private TextView popInitial, popName, popDepartment, popDesignation, popEmail, popPhone, closeBtn;
    private String data, tcrname, tcrinitial, tcrdesignation, tcrphone, tcremail, tcrdepartment;
    private Button editinfoBtn, removeinfoBtn;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_upinfo);

        closeBtn = findViewById(R.id.closebtn);
        popInitial = findViewById(R.id.popupinitial);
        popName = findViewById(R.id.popupname);
        popDepartment = findViewById(R.id.popupdepartment);
        popDesignation = findViewById(R.id.popupdesignation);
        popEmail = findViewById(R.id.popupemail);
        popPhone = findViewById(R.id.popupcontact);
        editinfoBtn = findViewById(R.id.editButton);
        removeinfoBtn = findViewById(R.id.deleteButton);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9), (int)(height*.6) );

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        data = extras.getString("DATA");
        tcrinitial = extras.getString("Initial");
        tcrname = extras.getString("Name");
        tcrdepartment = extras.getString("Department");
        tcrdesignation = extras.getString("Designation");
        tcrphone = extras.getString("Phone");
        tcremail = extras.getString("Email");

        popName.setText(tcrname);
        popDesignation.setText(tcrdesignation);
        popDepartment.setText(tcrdepartment);
        popPhone.setText(tcrphone);
        popEmail.setText(tcremail);
        popInitial.setText(tcrinitial);

        if(data.equals("admin")){
            editinfoBtn.setVisibility(View.VISIBLE);
            removeinfoBtn.setVisibility(View.VISIBLE);
        }
        if(data.equals("user")){
            editinfoBtn.setVisibility(View.GONE);
            removeinfoBtn.setVisibility(View.GONE);
        }

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        editinfoBtn.setOnClickListener(this);
        removeinfoBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.editButton){
            Intent intent = new Intent(this, AddTeacherInfo.class);
            Bundle extras = new Bundle();
            extras.putString("DATA", "edit");
            extras.putString("Initial", tcrinitial);
            extras.putString("Name", tcrname);
            extras.putString("Email", tcremail);
            extras.putString("Department", tcrdepartment);
            extras.putString("Phone", tcrphone);
            extras.putString("Designation", tcrdesignation);
            intent.putExtras(extras);
            startActivity(intent);
        }

        if(view.getId() == R.id.deleteButton){
            int values = databaseHelper.deleteTeacherData(tcrinitial);
            if(values > 0){
                Toast.makeText(getApplicationContext(), "Delete Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, TeacherInfo.class);
                intent.putExtra("DATA", "user");
                finish();
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();
            }
        }
    }
}
