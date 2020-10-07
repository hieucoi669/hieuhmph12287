package com.edu.fpoly.bookmanager;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.fpoly.bookmanager.dao.UserDAO;
import com.edu.fpoly.bookmanager.model.User;


public class NguoiDungActivity extends AppCompatActivity {
    Button btnThemNguoiDung;

    TextInputLayout edUser, edPass,edRePass, edPhone, edFullName;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        setTitle("THÊM NGƯỜI DÙNG");
        Log.i("Test", "1");
        btnThemNguoiDung = (Button) findViewById(R.id.btnAddUser);
        edUser = findViewById(R.id.edUserName);
        edPass = findViewById(R.id.edPassword);
        edPhone = findViewById(R.id.edPhone);
        edFullName = findViewById(R.id.edFullName);
        edRePass = findViewById(R.id.edRePassword);


    }

    public void showUsers(View view) {
        finish();
    }

    public void addUser(View view){
        userDAO = new UserDAO(this);
        User u = new User(edUser.getEditText().getText().toString(),
                edPass.getEditText().getText().toString(),
                edPhone.getEditText().getText().toString(),
                edFullName.getEditText().getText().toString());
        try{
            if(userDAO.insertUser(u) > 0){
                Toast.makeText(this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Thêm không thành công!", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Log.i("Add", e.toString());
        }
    }
}
