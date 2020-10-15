package com.edu.fpoly.bookmanager;

import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.edu.fpoly.bookmanager.dao.UserDAO;
import com.edu.fpoly.bookmanager.model.User;

import java.util.ArrayList;
import java.util.List;


public class NguoiDungActivity extends AppCompatActivity {

    TextInputLayout edUser, edPass, edRePass, edPhone, edFullName;
    UserDAO userDAO;
    List<String> nameList;
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        setTitle("THÊM NGƯỜI DÙNG");

        edUser = findViewById(R.id.edUserName);
        edPass = findViewById(R.id.edPassword);
        edPhone = findViewById(R.id.edPhone);
        edFullName = findViewById(R.id.edFullName);
        edRePass = findViewById(R.id.edRePassword);

    }

    public void addUser(View view){
        userDAO = new UserDAO(this);
        String username = checkUsername();
        String fullname = checkFullname();
        String phone = checkPhone();
        String pass = checkPassword();
        boolean match = checkRePassword();

        if(username != null && fullname != null && phone != null && pass != null && match){

            User u = new User(username, pass, phone, fullname);

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
            finish();
        }else{
            Toast.makeText(this,"Tạo tài khoản không thành công!", Toast.LENGTH_SHORT).show();
        }
    }

    private String checkUsername(){
        String name = null;
        nameList = new ArrayList<>();
        userList = userDAO.getAllNguoiDung();
        for(User u : userList){
            nameList.add(u.getUsername());
        }
        edUser.setError(null);
        try{
            name = edUser.getEditText().getText().toString();
            if(name.length() == 0){
                edUser.setError("Username không được để trống!");
                return null;
            }
            if(name.length() < 8 || name.length() > 20){
                edUser.setError("Username có độ dài từ 8-20 ký tự!");
                return null;
            }
            for(int i=0; i<nameList.size(); i++){
                if(name.equalsIgnoreCase(nameList.get(i))){
                    Log.i("test","123");
                    edUser.setError("Username đã tồn tại!");
                    return null;
                }
            }
        }catch(Exception e){
            edUser.setError("Username không hợp lệ!");
        }
        return name;
    }

    private String checkPassword(){
        String pass = null;
        edPass.setError(null);
        try{
            pass = edPass.getEditText().getText().toString();
            if(pass.length() == 0){
                edPass.setError("Password không được để trống!");
                return null;
            }
            if(pass.length() < 8 || pass.length() > 20){
                edPass.setError("Password có độ dài từ 8-20 ký tự!");
                return null;
            }
        }catch(Exception e){
            edPass.setError("Password không hợp lệ!");
        }
        return pass;
    }

    private String checkPhone(){
        String num = null;
        edPhone.setError(null);
        try{
            num = edPhone.getEditText().getText().toString();
            if(num.length() == 0){
                edPhone.setError("Số điện thoại không được để trống!");
                return null;
            }
            if(num.length() != 10){
                edPhone.setError("Số điện thoại phải có 10 số!");
                return null;
            }
        }catch(Exception e){
            edPhone.setError("Số điện thoại không hợp lệ!");
        }
        return num;
    }

    private String checkFullname(){
        String fullname = null;
        edFullName.setError(null);
        try{
            fullname = edFullName.getEditText().getText().toString();
            if(fullname.length() == 0){
                edFullName.setError("Họ tên không được để trống!");
                return null;
            }
            if(fullname.length() < 5 || fullname.length() > 50){
                edFullName.setError("Họ tên có độ dài từ 5-50 ký tự!");
                return null;
            }
        }catch(Exception e){
            edFullName.setError("Họ tên không hợp lệ!");
        }
        return fullname;
    }

    private boolean checkRePassword() {
        edRePass.setError(null);
        try{
            String pass1 = edPass.getEditText().getText().toString();
            String pass2 = edRePass.getEditText().getText().toString();
            if(pass2.length() == 0){
                edRePass.setError("Password không được để trống!");
                return false;
            }
            if(!pass1.equals(pass2)){
                edRePass.setError("Password không trùng khớp!");
                return false;
            }
        }catch (Exception e){
            edRePass.setError("Password không hợp lệ!");
        }
        return true;
    }

    public void clearForm(View view) {
        edUser.getEditText().setText("");
        edPass.getEditText().setText("");
        edPhone.getEditText().setText("");
        edRePass.getEditText().setText("");
        edFullName.getEditText().setText("");

        edUser.setError(null);
        edPass.setError(null);
        edPhone.setError(null);
        edRePass.setError(null);
        edFullName.setError(null);
    }

}
