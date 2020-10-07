package com.edu.fpoly.bookmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String strUserName, strPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("QUẢN LÝ SÁCH");
        setContentView(R.layout.activity_main);
        if (checkLoginShap()<0){
            Intent i = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);
        }
    }

    public int checkLoginShap(){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        boolean chk = pref.getBoolean("REMEMBER",false);
        if (chk){
            strUserName = pref.getString("USERNAME","");
            strPassword = pref.getString("PASSWORD","");
            return 1;
        }
        return -1;
    }
    public void viewNguoiDung(View v){
        Intent intent = new Intent(MainActivity.this,ListNguoiDungActivity.class);
        startActivity(intent);
    }

    public void viewTheLoai(View v){
        Intent intent = new Intent(MainActivity.this,ListTheLoaiActivity.class);
        startActivity(intent);
    }

    public void SachActivity(View v){
        Intent intent = new Intent(MainActivity.this,SachActivity.class);
        startActivity(intent);
    }
    public void HoaDonActivity(View v){
        Intent intent = new Intent(MainActivity.this,HoaDonActivity.class);
        startActivity(intent);
    }
    public void SachBanChayActivity(View v){
        Intent intent = new Intent(MainActivity.this,SachBanChayActivity.class);
        startActivity(intent);
    }
    public void ThongKeActivity(View v){
        Intent intent = new Intent(MainActivity.this,ThongKeActivity.class);
        startActivity(intent);
    }

}