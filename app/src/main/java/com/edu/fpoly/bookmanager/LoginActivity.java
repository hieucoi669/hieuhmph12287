package com.edu.fpoly.bookmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fpoly.bookmanager.dao.UserDAO;
import com.edu.fpoly.bookmanager.model.User;


public class LoginActivity extends AppCompatActivity {

    TextInputLayout edUsername, edPassword;
    CheckBox chkRemember;
    UserDAO userDAO;
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("ĐĂNG NHẬP");

        edUsername = findViewById(R.id.edUserNameLogin);
        edPassword = findViewById(R.id.edPasswordLogin);
        chkRemember = findViewById(R.id.chkRememberPass);
        userDAO = new UserDAO(this);
    }

    public void login(View v){
        String user = checkUserName();
        String pass = checkPassword();

        if(user != null && pass != null){

            SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if(chkRemember.isChecked()){
                editor.putString("USERNAME", user);
                editor.putString("PASSWORD", pass);
                editor.putBoolean("REMEMBER", true);
            }else{
                editor.clear();
            }
            editor.commit();

            Toast.makeText(this, "Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("Logged", user);
            startActivity(i);
        }else{
            Toast.makeText(this, "Đăng nhập không thành công!",Toast.LENGTH_SHORT).show();
        }
    }

    public void dangKy(View view) {
        Intent i = new Intent(this, NguoiDungActivity.class);
        startActivity(i);
    }

    private String checkUserName(){
        edUsername.setError(null);
        try{
            String username = edUsername.getEditText().getText().toString();
            if(username.length() == 0){
                edUsername.setError("Vui lòng nhập tên tài khoản!");
                return null;
            }

            u = userDAO.checkUserExist(username);
            if(u == null){
                edUsername.setError("Tên tài khoản không tồn tại!");
                return null;
            }else{
                return username;
            }
        }catch (Exception e){
            edUsername.setError("Tên tài khoản không hợp lệ!");
            return null;
        }
    }

    private String checkPassword() {
        edPassword.setError(null);
        try{
            String pass = edPassword.getEditText().getText().toString();
            if(pass.length() == 0){
                edPassword.setError("Vui lòng nhập mật khẩu!");
                return null;
            }
            if(u != null){
                if(u.getPassword().equals(pass)){
                    return pass;
                }else{
                    edPassword.setError("Mật khẩu bạn nhập không đúng!");
                    return null;
                }
            }else{
                return null;
            }
        }catch (Exception e){
            edPassword.setError("Mật khẩu không hợp lệ!");
            return null;
        }
    }
}
