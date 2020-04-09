package com.suptodas.diu.diuteacherinitialinformation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminUserInfo extends AppCompatActivity implements View.OnClickListener {
    private EditText name, email, username, password, id;
    private Button addButton;
    private String data, Userusername, userpassword, userid, userName, userEmail;
    private DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        data = extras.getString("DATA");
        Userusername = extras.getString("Username");
        userName = extras.getString("Name");
        userid = extras.getString("Id");
        userpassword = extras.getString("Password");
        userEmail = extras.getString("Email");


        name = findViewById(R.id.adminlayout_name);
        email = findViewById(R.id.adminlayout_email);
        username = findViewById(R.id.adminlayout_username);
        password = findViewById(R.id.adminlayout_password);
        id = findViewById(R.id.adminlayout_id);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(this);

        if (data.equals("edit")) {
            addButton.setText("Edit");
            id.setVisibility(View.VISIBLE);
            id.setText(userid);
            name.setText(userName);
            username.setText(Userusername);
            password.setText(userpassword);
            email.setText(userEmail);
        }

        if (data.equals("remove")) {
            addButton.setText("Remove");
            id.setVisibility(View.VISIBLE);
            name.setVisibility(View.GONE);
            email.setVisibility(View.GONE);
            username.setVisibility(View.GONE);
            password.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View view) {

        if (data.equals("add")) {

            String personName = name.getText().toString();
            String personEmail = email.getText().toString();
            String personUsername = username.getText().toString();
            String personPassword = password.getText().toString();

            if (personName.isEmpty() || personEmail.isEmpty() || personUsername.isEmpty() || personPassword.isEmpty()) {
                if (personName.isEmpty()) {
                    name.setError("Enter Name");
                    name.requestFocus();
                }

                if (personEmail.isEmpty()) {
                    email.setError("Enter Email");
                    email.requestFocus();
                }

                if (personUsername.isEmpty()) {
                    username.setError("Enter Username");
                    username.requestFocus();
                }

                if (personPassword.isEmpty()) {
                    password.setError("Enter Password");
                    password.requestFocus();
                }

            } else {
                long rowID = databaseHelper.insertUserData(personName, personEmail, personUsername, personPassword);
                if (rowID > 0) {
                    Toast.makeText(getApplicationContext(), "User Added", Toast.LENGTH_LONG).show();
                    name.setText("");
                    email.setText("");
                    username.setText("");
                    password.setText("");
                    Intent intent = new Intent(AdminUserInfo.this, UserInfo.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                }
            }

        }

        if (data.equals("edit")) {
            String personid = id.getText().toString();
            String personName = name.getText().toString();
            String personEmail = email.getText().toString();
            String personUsername = username.getText().toString();
            String personPassword = password.getText().toString();

            if (personid.isEmpty() || personName.isEmpty() || personEmail.isEmpty() || personUsername.isEmpty() || personPassword.isEmpty()) {
                if (personid.isEmpty()) {
                    id.setError("Enter ID");
                    id.requestFocus();
                }

                if (personName.isEmpty()) {
                    name.setError("Enter Name");
                    name.requestFocus();
                }

                if (personEmail.isEmpty()) {
                    email.setError("Enter Email");
                    email.requestFocus();
                }

                if (personUsername.isEmpty()) {
                    username.setError("Enter Username");
                    username.requestFocus();
                }

                if (personPassword.isEmpty()) {
                    password.setError("Enter Password");
                    password.requestFocus();
                }
            }
            else {
                boolean values = databaseHelper.updateUserData(personid, personName, personEmail, personUsername, personPassword);
                if(values == true) {
                    Toast.makeText(getApplicationContext(), "Data is Updated", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AdminUserInfo.this, UserInfo.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Data is not updated", Toast.LENGTH_LONG).show();
                }
            }
        }

        if(data.equals("remove")){
            String personId = id.getText().toString();
            if(personId.isEmpty()){
                id.setError("Enter ID");
                id.requestFocus();
            }
            else {
                int values = databaseHelper.deleteUserData(personId);
                if(values > 0){
                    Toast.makeText(getApplicationContext(), "Data is Deleted", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AdminUserInfo.this, UserInfo.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
