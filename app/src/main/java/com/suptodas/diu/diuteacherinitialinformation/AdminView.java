package com.suptodas.diu.diuteacherinitialinformation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AdminView extends AppCompatActivity implements View.OnClickListener {

    private Button userInfo, teacherInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        userInfo = findViewById(R.id.userInfo);
        teacherInfo = findViewById(R.id.teacherInfo);

        userInfo.setOnClickListener(this);
        teacherInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.userInfo){
            Intent intent = new Intent(AdminView.this, UserInfo.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.teacherInfo){
            Intent intent = new Intent(AdminView.this, TeacherInfo.class);
            intent.putExtra("DATA", "admin");
            startActivity(intent);
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
