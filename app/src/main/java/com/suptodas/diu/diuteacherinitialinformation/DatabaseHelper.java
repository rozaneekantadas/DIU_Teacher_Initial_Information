package com.suptodas.diu.diuteacherinitialinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.widget.Toast;


public class DatabaseHelper extends SQLiteOpenHelper {
    private  Context context;

    private static final String DATABASE_NAME = "Lab_Project";
    private static final String USER_TABLE_NAME = "user_info";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private static final String TEACHER_TABLE_NAME = "teacher_info";
    private static final String INITIAL = "initial";
    private static final String TEACHER_NAME = "teacher_name";
    private static final String DESIGNATION = "designation";
    private static final String DEPARTMENT = "department";
    private static final String TEACHER_EMAIL = "teacher_email";
    private static final String TEACHER_PHONE = "teacher_phone";


    private static final int VERSION = 1;

    private static final String CREATE_USER_TABLE = "CREATE TABLE "+USER_TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(120), "+EMAIL+" TEXT NOT NULL, "+USERNAME+" TEXT NOT NULL, "+PASSWORD+" VARCHAR(120));";
    private static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS "+USER_TABLE_NAME;

    private static final String CREATE_TEACHER_TABLE = "CREATE TABLE "+TEACHER_TABLE_NAME+"("+INITIAL+" VARCHAR(120), "+TEACHER_NAME+" VARCHAR(120), "+DESIGNATION+" TEXT NOT NULL, "+DEPARTMENT+" TEXT NOT NULL, "+TEACHER_PHONE+" TEXT NOT NULL, "+TEACHER_EMAIL+" TEXT NOT NULL);";
    private static final String DROP_TEACHER_TABLE = "DROP TABLE IF EXISTS "+TEACHER_TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            sqLiteDatabase.execSQL(CREATE_USER_TABLE);
            sqLiteDatabase.execSQL(CREATE_TEACHER_TABLE);
            Toast.makeText(context, "Table is Created", Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try{
            sqLiteDatabase.execSQL(DROP_USER_TABLE);
            sqLiteDatabase.execSQL(DROP_TEACHER_TABLE);
            onCreate(sqLiteDatabase);
            Toast.makeText(context, "Table is Upgraded", Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_LONG).show();
        }

    }

    public long insertUserData(String name, String email, String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(EMAIL, email);
        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);

        long rowID = sqLiteDatabase.insert(USER_TABLE_NAME, null, contentValues);
        return rowID;
    }

    public Cursor userShowData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+USER_TABLE_NAME, null);
        return cursor;
    }

    public boolean findPassword(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+USER_TABLE_NAME, null);

        boolean result = false;

        while (cursor.moveToNext()){
            if(username.equals(cursor.getString(3)) && password.equals(cursor.getString(4))){
                result = true;
            }
        }
        return result;
    }

    public boolean updateUserData(String id, String name, String email, String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(NAME, name);
        contentValues.put(EMAIL, email);
        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);

        sqLiteDatabase.update(USER_TABLE_NAME, contentValues, ID+" = ?", new String[]{ id });
        return true;
    }

    public int deleteUserData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(USER_TABLE_NAME, ID+" = ?", new String[]{id});
    }

    public long insertTeacherData(String initial, String name, String designation, String department, String phone, String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put(INITIAL, initial);
        contentValues.put(TEACHER_NAME, name);
        contentValues.put(DESIGNATION, designation);
        contentValues.put(DEPARTMENT, department);
        contentValues.put(TEACHER_PHONE, phone);
        contentValues.put(TEACHER_EMAIL, email);

        long rowId = sqLiteDatabase.insert(TEACHER_TABLE_NAME, null, contentValues);
        return rowId;

    }

    public Cursor teacherShowData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TEACHER_TABLE_NAME, null);
        return cursor;
    }

    public int updateTeacherData(String initial, String name, String designation, String department, String phone, String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put(INITIAL, initial);
        contentValues.put(TEACHER_NAME, name);
        contentValues.put(DESIGNATION, designation);
        contentValues.put(DEPARTMENT, department);
        contentValues.put(TEACHER_PHONE, phone);
        contentValues.put(TEACHER_EMAIL, email);

        return sqLiteDatabase.update(TEACHER_TABLE_NAME, contentValues, INITIAL+" = ?", new String[]{ initial });
    }

    public int deleteTeacherData(String initial){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TEACHER_TABLE_NAME, INITIAL+" = ?", new String[]{initial});
    }

    public Cursor searchDara(String text){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM "+TEACHER_TABLE_NAME+" WHERE "+INITIAL+" LIKE '%"+text+"%'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }
}
