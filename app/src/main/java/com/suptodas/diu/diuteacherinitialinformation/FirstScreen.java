package com.suptodas.diu.diuteacherinitialinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstScreen extends AppCompatActivity implements View.OnClickListener {

    private Button admin, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        admin = findViewById(R.id.admin);
        user = findViewById(R.id.user);

        admin.setOnClickListener(this);
        user.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.admin){

            Intent intent = new Intent(FirstScreen.this, AdminPage.class);
            startActivity(intent);

        }

        if(view.getId() == R.id.user){

            Intent intent = new Intent(FirstScreen.this, UserPage.class);
            startActivity(intent);

        }
    }
}
