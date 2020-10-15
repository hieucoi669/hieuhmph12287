package com.edu.fpoly.bookmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("QUẢN LÝ SÁCH");
        setContentView(R.layout.activity_main);
        if (checkLogin()<0){
            Intent i = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);
        }
    }

    public int checkLogin(){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        boolean chk = pref.getBoolean("REMEMBER",false);
        if(chk){
            username = pref.getString("USERNAME", "");
            return 1;
        }else{
            Intent i = getIntent();
            username = i.getStringExtra("Logged");
            if(username != null){
                return 1;
            }else{
                return -1;
            }
        }
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
