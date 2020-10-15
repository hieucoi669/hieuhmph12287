package com.edu.fpoly.bookmanager;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fpoly.bookmanager.dao.UserDAO;
import com.edu.fpoly.bookmanager.model.User;

public class ChangePasswordActivity extends AppCompatActivity {

    TextView tvUsername;
    UserDAO userDAO;
    String username;
    TextInputLayout edOldPassword, edNewPassword, edReNewPassword;
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setTitle("Đổi mật khẩu");

        edOldPassword = findViewById(R.id.edOldPassword);
        edNewPassword = findViewById(R.id.edNewPassword);
        edReNewPassword = findViewById(R.id.edReNewPassword);
        tvUsername = findViewById(R.id.tvUsername);
        userDAO = new UserDAO(this);

        username = MainActivity.username;
        tvUsername.setText("Tên tài khoản: " + username);
        u = userDAO.checkUserExist(username);
    }

    public void changePass(View view) {

        boolean checkPass1 = checkOldPass();
        String newPass = checkPassword();
        boolean checkPass2 = checkRePassword();

        if(checkPass1 && checkPass2 && newPass != null){
            u.setPassword(newPass);
            if(userDAO.updateUser(u) > 0){
                Toast.makeText(this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(this, "Đổi mật khẩu không thành công!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkOldPass(){
        edOldPassword.setError(null);
        String oldPass1 = u.getPassword();
        try{
            String oldPass2 = edOldPassword.getEditText().getText().toString();
            if(oldPass2.length() == 0){
                edOldPassword.setError("Password không được để trống!");
                return false;
            }
            if(oldPass1.equals(oldPass2)){
                return true;
            }else{
                edOldPassword.setError("Password không chính xác!");
                return false;
            }
        }catch(Exception e){
            edOldPassword.setError("Password không hợp lệ!");
            return false;
        }
    }

    private String checkPassword(){
        String pass = null;
        edNewPassword.setError(null);
        try{
            pass = edNewPassword.getEditText().getText().toString();
            if(pass.length() == 0){
                edNewPassword.setError("Password không được để trống!");
                return null;
            }
            if(pass.length() < 8 || pass.length() > 20){
                edNewPassword.setError("Password có độ dài từ 8-20 ký tự!");
                return null;
            }
        }catch(Exception e){
            edNewPassword.setError("Password không hợp lệ!");
        }
        return pass;
    }

    private boolean checkRePassword() {
        edReNewPassword.setError(null);
        try{
            String pass1 = edNewPassword.getEditText().getText().toString();
            String pass2 = edReNewPassword.getEditText().getText().toString();
            if(pass2.length() == 0){
                edReNewPassword.setError("Password không được để trống!");
                return false;
            }
            if(!pass1.equals(pass2)){
                edReNewPassword.setError("Password không trùng khớp!");
                return false;
            }
        }catch (Exception e){
            edReNewPassword.setError("Password không hợp lệ!");
        }
        return true;
    }
}