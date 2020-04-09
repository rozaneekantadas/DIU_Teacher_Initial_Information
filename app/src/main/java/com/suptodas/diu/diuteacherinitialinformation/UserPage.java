package com.suptodas.diu.diuteacherinitialinformation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class UserPage extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout loginLayout, signupLayout;
    private EditText name, username, email, password, userNameEditText, passwordEditText;
    private Button nextButton, loginButton, signUp, signInButton;
    private DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        loginLayout = findViewById(R.id.loginLayout);
        signupLayout = findViewById(R.id.signupLayout);
        nextButton = findViewById(R.id.next);
        loginButton = findViewById(R.id.login);
        signUp = findViewById(R.id.signUpButton);
        signInButton = findViewById(R.id.signInButton);

        userNameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        nextButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        signUp.setOnClickListener(this);
        signInButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.next){

            signupLayout.setVisibility(View.VISIBLE);
            loginLayout.setVisibility(View.GONE);

        }

        if(view.getId() == R.id.login){
            signupLayout.setVisibility(View.GONE);
            loginLayout.setVisibility(View.VISIBLE);
        }

        if(view.getId() == R.id.signInButton){
            String personUsername = userNameEditText.getText().toString();
            String personPassword = passwordEditText.getText().toString();

            if(personUsername.isEmpty() || personPassword.isEmpty()){
                if(personUsername.isEmpty()){
                    userNameEditText.setError("Enter Username");
                    userNameEditText.requestFocus();
                }

                if(personPassword.isEmpty()){
                    passwordEditText.setError("Enter Password");
                    passwordEditText.requestFocus();
                }
            }
            else {
                boolean result = databaseHelper.findPassword(personUsername, personPassword);
                if(result == true){
                    finish();
                    Intent intent = new Intent(getApplicationContext(), TeacherInfo.class);
                    intent.putExtra("DATA", "user");
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), personUsername+" Logged In", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Username or Password", Toast.LENGTH_LONG).show();
                }
            }
        }

        if(view.getId() == R.id.signUpButton){
            String personName = name.getText().toString();
            String personEmail = email.getText().toString();
            String personUsername = username.getText().toString();
            String personPassword = password.getText().toString();

            if(personName.isEmpty() || personEmail.isEmpty() || personUsername.isEmpty() || personPassword.isEmpty()){
                if(personName.isEmpty()){
                    name.setError("Enter Name");
                    name.requestFocus();
                }

                if(personEmail.isEmpty()){
                    email.setError("Enter Email");
                    email.requestFocus();
                }

                if(personUsername.isEmpty()){
                    username.setError("Enter Username");
                    username.requestFocus();
                }

                if(personPassword.isEmpty()){
                    password.setError("Enter Password");
                    password.requestFocus();
                }
            }
            else {

                long rowID = databaseHelper.insertUserData(personName, personEmail, personUsername, personPassword);
                if(rowID > 0){
                    Toast.makeText(getApplicationContext(), "Sign Up Successful", Toast.LENGTH_LONG).show();
                    name.setText("");
                    email.setText("");
                    username.setText("");
                    password.setText("");
                    loginLayout.setVisibility(View.VISIBLE);
                    signupLayout.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Sign Up Failed", Toast.LENGTH_LONG).show();
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
