package com.edu.fpoly.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.edu.fpoly.bookmanager.database.DatabaseHelper;
import com.edu.fpoly.bookmanager.model.User;

import java.util.ArrayList;
import java.util.List;


public class UserDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "User";
    public static final String SQL_USER = "Create table " + TABLE_NAME + "(" +
            "username text primary key, " +
            "password text," +
            "phone text," +
            "hoten text);";

    public UserDAO(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<User> getAllNguoiDung()
    {
        List<User> listUser = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            User u = new User();
            u.setUsername(cursor.getString(0));
            u.setPassword(cursor.getString(1));
            u.setPhone(cursor.getString(2));
            u.setHoten(cursor.getString(3));
            listUser.add(u);
            cursor.moveToNext();
        }
        cursor.close();
        return listUser;
    }

    public int insertUser(@NonNull User u){
        ContentValues values = new ContentValues();
        values.put("username", u.getUsername());
        values.put("password", u.getPassword());
        values.put("phone", u.getPhone());
        values.put("hoten", u.getHoten());

        try{
            if(db.insert(TABLE_NAME, null, values) < 0){
                return -1;
            }
        }catch (Exception e){
            Log.i("User_Tab", e.getMessage());
        }
        return 1;
    }
    public int deleteNguoiDung(String username)
    {
        int kq = db.delete(TABLE_NAME,"username=?",new String[]{username});
        if(kq==0)
        {
            return -1;//xoa khong thanh cong
        }
        return 1;//xoa thanh cong
    }
    public int updateUser(User u)
    {
        ContentValues values = new ContentValues();
        values.put("username", u.getUsername());
        values.put("password", u.getPassword());
        values.put("phone", u.getPhone());
        values.put("hoten" ,u.getHoten());
        int kq = db.update(TABLE_NAME,values,"username=?", new String[]{u.getUsername()});
        if(kq==0)
        {
            return -1;
        }
        return 1;
    }
    public User checkUserExist(String username)
    {
        Cursor result = db.query(TABLE_NAME,null,"username=?",new String[]{username},null,null,null);
        result.moveToFirst();
        if(result.getCount() != 0)
        {
            User u = new User();
            u.setUsername(result.getString(0));
            u.setPassword(result.getString(1));
            u.setPhone(result.getString(2));
            u.setHoten(result.getString(3));
            return u;
        }else {
            return null;
        }
    }
}
