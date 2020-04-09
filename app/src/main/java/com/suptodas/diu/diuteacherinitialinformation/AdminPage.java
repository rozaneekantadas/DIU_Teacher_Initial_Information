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

public class AdminPage extends AppCompatActivity {

    private EditText adminname, adminPassword;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        adminname = findViewById(R.id.adminusernameEditText);
        adminPassword = findViewById(R.id.adminpasswordEditText);

        signIn = findViewById(R.id.adminsingInButton);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = adminname.getText().toString();
                String password = adminPassword.getText().toString();

                if(name.isEmpty() || password.isEmpty()){
                    if(name.isEmpty()){
                        adminname.setError("Enter Username");
                        adminname.requestFocus();
                    }
                    if(password.isEmpty()){
                        adminPassword.setError("Enter Password");
                        adminPassword.requestFocus();
                    }
                }
                else{
                    if(name.equals("admin") && password.equals("admin")){
                        finish();
                        Intent intent = new Intent(AdminPage.this, AdminView.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Wrong Username or Password!", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
