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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserInfo extends AppCompatActivity {

    private ListView listView;
    private ArrayList<User> userList;
    private DatabaseHelper databaseHelper = new DatabaseHelper(this);
    private User newUser;
    private Boolean condition = false;
    private User value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView = findViewById(R.id.userlistView);

        loadUserData();

        UserAdepter adepter = new UserAdepter(this, userList);
        listView.setAdapter(adepter);

        selectItem(listView, userList);

    }

    public void selectItem(ListView listViewItem, final List<User> usrList){

        listViewItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                condition = true;
                value = usrList.get(i);
            }
        });

    }

    public void loadUserData(){

        userList = new ArrayList<>();
        Cursor cursor = databaseHelper.userShowData();

        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()){
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String email= cursor.getString(2);
                String username = cursor.getString(3);
                String password = cursor.getString(4);

                newUser = new User(id, name, email, username, password);
                userList.add(newUser);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.user_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            this.finish();
        }

        if (item.getItemId() == R.id.add){

            Intent intent = new Intent(this, AdminUserInfo.class);
            intent.putExtra("DATA", "add");
            startActivity(intent);
        }

        if (item.getItemId() == R.id.edit){
            if(condition == false){
                Toast.makeText(getApplicationContext(), "Select an Item", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent = new Intent(this, AdminUserInfo.class);
                Bundle extras = new Bundle();
                extras.putString("DATA", "edit");
                extras.putString("Id", value.getId());
                extras.putString("Name", value.getName());
                extras.putString("Email", value.getEmail());
                extras.putString("Password", value.getPassword());
                extras.putString("Username", value.getUsername());
                intent.putExtras(extras);
                startActivity(intent);
            }

        }

        if(item.getItemId() == R.id.remove){
            if(condition == false){
                Toast.makeText(getApplicationContext(), "Select an Item", Toast.LENGTH_SHORT).show();
            }
            else {
                int values = databaseHelper.deleteUserData(value.getId());
                if(values > 0){
                    Toast.makeText(getApplicationContext(), "Data is Deleted", Toast.LENGTH_LONG).show();
                    loadUserData();
                    UserAdepter adepter = new UserAdepter(this, userList);
                    listView.setAdapter(adepter);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
